package allan_rivera.pharmacy_locator.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToDoubleFunction;

import com.opencsv.bean.CsvToBeanBuilder;

import allan_rivera.pharmacy_locator.objects.PharmacyBean;

public class PharmaciesAccessor {
	private static List<PharmacyBean> pharmacies;

	private static List<PharmacyBean> getPharmacies(String filename)
			throws IllegalStateException, FileNotFoundException {
		if (pharmacies != null) {
			return pharmacies;
		}
		pharmacies = new CsvToBeanBuilder<PharmacyBean>(new FileReader(filename)).withType(PharmacyBean.class).build()
				.parse();
		return pharmacies;
	}
	
	/**
	 * Returns the formula used to compute the distance between the provided point and a pharmacy bean.
	 * @param latitude the latitude of the provided point
	 * @param longitude the longitude of the provided point
	 * @return
	 */
	private static ToDoubleFunction<PharmacyBean> getDistanceFormula(Double latitude, Double longitude) {
		return (pharmacy) -> {
			return Math.pow(latitude - pharmacy.getLatitude(), 2) + Math.pow(longitude - pharmacy.getLongitude(), 2);
		};
	}
	
	/**
	 * Returns the closest pharmacy located in the csv file provided.
	 * @param filename the name of the csv file containing the pharmacies under consideration.
	 * @param latitude the latitude of the location to be considered
	 * @param longitude the longitude of the location to be considered
	 * @return the PharmacyBean representing the pharmacy closest to the provided location
	 * @throws IllegalStateException if the csv files provided does not contain any entries
	 * @throws FileNotFoundException if the csv fails to be located
	 */
	public static PharmacyBean getClosestPharmacy(String filename, Double latitude, Double longitude)
			throws IllegalStateException, FileNotFoundException {
		return getPharmacies(filename).stream().min(Comparator.comparingDouble(getDistanceFormula(latitude, longitude)))
				.get();
	}

}
