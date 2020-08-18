package allan_rivera.pharmacy_locator;

import java.io.FileNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import allan_rivera.pharmacy_locator.object.model.PharmacyController;
import allan_rivera.pharmacy_locator.object.response.ClosestPharmacyResponsePOJO;

/**
 * Front controller for ClosestPharmacy API
 * 
 * @author Allan Sattelberg Rivera
 *
 */
@Controller
public class ClosestPharmacyEndpoint {
	/**
	 * Fetch the closest PharmacyBean based on the provided distance formula and
	 * return the result.
	 * 
	 * @param latitude  the latitude of the reference point
	 * @param longitude the longitude of the reference point
	 * @return a response entity containing the address, name, and distance to the
	 *         closest pharmacy, or an error message if a Throwable is thrown
	 * @throws FileNotFoundException
	 * @throws IllegalStateException
	 */
	@GetMapping("/")
	public ResponseEntity<ClosestPharmacyResponsePOJO> getClosest(@RequestParam Double latitude,
			@RequestParam Double longitude) throws IllegalStateException, FileNotFoundException {
		return PharmacyController.getClosestPharmacy(latitude, longitude).map(ResponseEntity::ok)
				.orElse(new ResponseEntity<ClosestPharmacyResponsePOJO>(HttpStatus.NO_CONTENT));
	}
}