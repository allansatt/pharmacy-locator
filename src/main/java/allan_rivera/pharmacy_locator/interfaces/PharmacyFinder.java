package allan_rivera.pharmacy_locator.interfaces;

import allan_rivera.pharmacy_locator.objects.PharmacyBean;


public interface PharmacyFinder {
	public PharmacyBean getClosestPharmacy(long latitude, long longitude);
}
