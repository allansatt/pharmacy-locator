package allan_rivera.pharmacy_locator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import allan_rivera.pharmacy_locator.object.model.PharmacyBean;
import allan_rivera.pharmacy_locator.object.response.ClosestPharmacyResponsePOJO;
import allan_rivera.pharmacy_locator.utils.PharmacyController;

@RestController
public class ClosestPharmacyEndpoint {
	@RequestMapping("/")
	public ResponseEntity<ClosestPharmacyResponsePOJO> getClosest(@RequestParam Double latitude,
			@RequestParam Double longitude) {
		try {
			PharmacyBean pharmacy = PharmacyController.getClosestPharmacy(latitude, longitude);
			return new ResponseEntity<>(new ClosestPharmacyResponsePOJO().setCity(pharmacy.getCity())
					.setDistance(PharmacyController.getDistanceFormula(latitude, longitude).applyAsDouble(pharmacy))
					.setName(pharmacy.getName()).setState(pharmacy.getState())
					.setStreetAddress(pharmacy.getStreetAddress()).setZip(pharmacy.getZip()), HttpStatus.OK);
		} catch (Throwable e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ClosestPharmacyResponsePOJO().setErrorMessage(e.toString()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}