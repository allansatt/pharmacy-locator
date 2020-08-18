package allan_rivera.pharmacy_locator.object.response;

/**
 * Class used to store the information required for a ClosestPharmacyLookup
 * response using the named parameter idiom.
 *
 * @author Allan Sattelberg Rivera
 *
 */
public class ClosestPharmacyResponsePOJO {

	private String streetAddress;
	private String city;
	private String name;
	private String state;
	private String zip;
	private Double distance;

	/**
	 * @return the streetAddress
	 */
	public String getStreetAddress() {
		return streetAddress;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @return the distance in miles
	 */
	public Double getDistance() {
		return distance;
	}

	/**
	 * @param streetAddress the streetAddress to set
	 * @return this
	 */
	public ClosestPharmacyResponsePOJO setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
		return this;
	}

	/**
	 * @param city the city to set
	 * @return this
	 */
	public ClosestPharmacyResponsePOJO setCity(String city) {
		this.city = city;
		return this;
	}

	/**
	 * @param name the name to set
	 * @return this
	 */
	public ClosestPharmacyResponsePOJO setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * @param state the state to set
	 * @return this
	 */
	public ClosestPharmacyResponsePOJO setState(String state) {
		this.state = state;
		return this;
	}

	/**
	 * @param zip the zip to set
	 * @return this
	 */
	public ClosestPharmacyResponsePOJO setZip(String zip) {
		this.zip = zip;
		return this;
	}

	/**
	 * @param distance the distance to set, in miles
	 * @return this
	 */
	public ClosestPharmacyResponsePOJO setDistance(Double distance) {
		this.distance = distance;
		return this;
	}
}
