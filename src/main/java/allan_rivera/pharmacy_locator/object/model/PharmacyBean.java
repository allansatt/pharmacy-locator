package allan_rivera.pharmacy_locator.object.model;

import java.io.Serializable;

import com.opencsv.bean.CsvBindByName;

/**
 * Bean to hold pharmacy information stored in a csv file.
 * @author Allan Sattelberg Rivera
 *
 */
public class PharmacyBean implements Serializable {
	private static final long serialVersionUID = 913159675724789554L;
	
	@CsvBindByName(column="address")
	private String streetAddress;
	@CsvBindByName
	private String city;
	@CsvBindByName
	private Double latitude;
	@CsvBindByName
	private Double longitude;
	@CsvBindByName
	private String name;
	@CsvBindByName
	private String state;
	@CsvBindByName
	private String zip;
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
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
	 * @return the latitude to set, in degrees
	 */
	public Double getLatitude() {
		return latitude;
	}
	/**
	 * @return the longitude to set, in degrees
	 */
	public Double getLongitude() {
		return longitude;
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
	 * @param streetAddress the streetAddress to set
	 */
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @param latitude the latitude to set, in degrees
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	/**
	 * @param longitude the longitude to set, in degrees
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
}
