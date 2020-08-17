package allan_rivera.pharmacy_locator.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToDoubleFunction;

import com.opencsv.bean.CsvToBeanBuilder;

import allan_rivera.pharmacy_locator.object.model.PharmacyBean;

public class PharmacyController {
	private static final double EARTH_RADIUS_MILES = 3958.8;
	private static final double DEG_TO_RAD = Math.PI / 180;
	private static final String PHARMACIES_FILE = "C:\\Users\\Allan\\eclipse-workspace\\pharmacy-locator\\src\\main\\resources\\pharmacies.csv";
	private static List<PharmacyBean> pharmacies;

	/**
	 * Returns the pharmacies stored in the csv file, loading them into memory if
	 * necessary.
	 * 
	 * @return a list of PharmacyBeans initialized with the data in the csv file
	 * @throws IllegalStateException if the csv contains unparsable data
	 * @throws FileNotFoundException if the csv file does not exist
	 */
	public static List<PharmacyBean> getPharmacies() throws IllegalStateException, FileNotFoundException {
		if (pharmacies != null) {
			return pharmacies;
		}
		pharmacies = new CsvToBeanBuilder<PharmacyBean>(new FileReader(PHARMACIES_FILE)).withType(PharmacyBean.class)
				.build().parse();
		return pharmacies;
	}

	/**
	 * Returns the formula used to compute the distance between the provided point
	 * and a pharmacy bean.
	 * 
	 * @param latitude  the latitude of the provided point in degrees
	 * @param longitude the longitude of the provided point in degrees
	 * @return the formula used to compute the distance between the provided point
	 *         and a pharmacy bean.
	 */
	public static ToDoubleFunction<PharmacyBean> getDistanceFormula(Double latitude, Double longitude) {
		return (pharmacy) -> {
			return haversineFormula(latitude * DEG_TO_RAD, longitude * DEG_TO_RAD, pharmacy.getLatitude() * DEG_TO_RAD,
					pharmacy.getLongitude() * DEG_TO_RAD);
		};
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
				+ (Math.cos(latA) * Math.cos(latB))
				+ (Math.sin(longDifference / 2) * Math.sin(longDifference / 2));
		return EARTH_RADIUS_MILES * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	}

	/**
	 * Returns the closest pharmacy located in the csv file provided.
	 * 
	 * @param filename  the name of the csv file containing the pharmacies under
	 *                  consideration.
	 * @param latitude  the latitude of the location to be considered
	 * @param longitude the longitude of the location to be considered
	 * @return the PharmacyBean representing the pharmacy closest to the provided
	 *         location
	 * @throws IllegalStateException if the csv files provided does not contain any
	 *                               entries
	 * @throws FileNotFoundException if the csv fails to be located
	 */
	public static PharmacyBean getClosestPharmacy(Double latitude, Double longitude)
			throws IllegalStateException, FileNotFoundException {
		return getPharmacies().stream().min(Comparator.comparingDouble(getDistanceFormula(latitude, longitude))).get();
	}

}
