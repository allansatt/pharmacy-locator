package allan_rivera.pharmacy_locator.object.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;

import org.springframework.core.io.ClassPathResource;

import com.opencsv.bean.CsvToBeanBuilder;

import allan_rivera.pharmacy_locator.object.response.ClosestPharmacyResponsePOJO;

/**
 * Class to control access to and operations on pharmacy data.
 * 
 * @author Allan Sattelberg Rivera
 *
 */
public class PharmacyController {
	private static final double EARTH_RADIUS_MILES = 3958.8;
	private static final double DEG_TO_RAD = Math.PI / 180;
	private static final String PHARMACIES_FILE = "pharmacies.csv";
	private static List<PharmacyBean> pharmacies;

	/**
	 * Returns the pharmacies stored in the csv file, loading them into memory if
	 * necessary.
	 * 
	 * @return a list of PharmacyBeans initialized with the data in the csv file
	 * @throws IOException           if the csv file could not be opened
	 * @throws IllegalStateException if the csv contains unparsable data
	 */
	public static List<PharmacyBean> getPharmacies() throws IllegalStateException, IOException {
		if (pharmacies != null) {
			return pharmacies;
		}
		pharmacies = new CsvToBeanBuilder<PharmacyBean>(
				new InputStreamReader(new ClassPathResource(PHARMACIES_FILE).getInputStream()))
						.withType(PharmacyBean.class).build().parse();
		return pharmacies;
	}

	/**
	 * Returns the distance between two points using the haversine formula.
	 * 
	 * @param latA  the latitude of the first point in radians
	 * @param longA the longitude of the first point in radians
	 * @param latB  the latitude of the second point in radians
	 * @param longB the longitude of the second point in radians
	 * @return the distance between the two points in meters
	 */
	private static Double haversineFormula(Double latA, Double longA, Double latB, Double longB) {
		Double latDifference = latB - latA;
		Double longDifference = longB - longA;

		Double a = (Math.sin(latDifference / 2) * Math.sin(latDifference / 2))
				+ (Math.cos(latA) * Math.cos(latB)) * (Math.sin(longDifference / 2) * Math.sin(longDifference / 2));
		return EARTH_RADIUS_MILES * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	}

	/**
	 * Populates a ClosestPharmacyResponsePOJO with the information provided on
	 * pharm and the distance
	 * 
	 * @param pharm    the pharmacy information to be sent
	 * @param distance the distance from the request point and the pharmacy
	 * @return a ClosestPharmacyResponsePOJO with the ifnormation of the closest
	 *         pharmacy
	 */
	private static ClosestPharmacyResponsePOJO constructResponse(PharmacyBean pharm, Double distance) {
		return new ClosestPharmacyResponsePOJO().setCity(pharm.getCity()).setDistance(distance).setName(pharm.getName())
				.setState(pharm.getState()).setStreetAddress(pharm.getStreetAddress()).setZip(pharm.getZip());
	}

	/**
	 * Finds the entry in the pharmacies.csv file that is the closest to the
	 * provided coordinates.
	 * 
	 * @param latitude  the latitude, in degrees
	 * @param longitude the longitude, in degrees
	 * @return ClosestPharmacyResponsePOJO populated with the correct pharmacy's
	 *         information
	 * @throws IllegalStateException see getPharmacies()
	 * @throws FileNotFoundException see getPharmacies()
	 */
	public static Optional<ClosestPharmacyResponsePOJO> getClosestPharmacy(Double latitude, Double longitude)
			throws IllegalStateException, IOException {
		ToDoubleFunction<PharmacyBean> distFormula = (pharm) -> haversineFormula(latitude * DEG_TO_RAD,
				longitude * DEG_TO_RAD, pharm.getLatitude() * DEG_TO_RAD, pharm.getLongitude() * DEG_TO_RAD);
		Function<PharmacyBean, ClosestPharmacyResponsePOJO> responseConstructor = (pharm) -> constructResponse(pharm,
				distFormula.applyAsDouble(pharm));

		return getPharmacies().stream().min(Comparator.comparingDouble(distFormula)).map(responseConstructor);
	}

}
