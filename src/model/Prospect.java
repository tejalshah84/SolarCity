/*
 * This is a model class that simply stores information (name, age, address, interest, etc) about a prospective client.
 * Access to the information is provided through getter/setters.
 */
package model;

public class Prospect {

	private String lastName;
	private String firstName;
	private int age;
	private String email;
	private String phoneNumber;
	private String addressStreet;
	private String addressCity;
	private String addressState;
	private int addressZip;
	private boolean installation;
	private String reasonInterested;

	/*
	 * No-Arg constructor
	 */
	public Prospect(){

	}

	/*
	 * Constructor with parameters string, string, string, string, string, string, int.
	 */
	public Prospect(String lastName, String firstName, String phoneNumber, String addressStreet, String addressCity, String addressState, int addressZip){
		this.lastName = lastName;
		this.firstName = firstName;
		this.phoneNumber = phoneNumber;
		this.addressStreet = addressStreet;
		this.addressCity = addressCity;
		this.addressState = addressState;
		this.addressZip = addressZip;
	}

	/*
	 * Constructor with parameters string, string, int, string, string, string, string, string, int, boolean, string.
	 */
	public Prospect(String lastName, String firstName, int age, String email, String phoneNumber, String addressStreet, String addressCity, String addressState, int addressZip, boolean installation, String reasonInterested){
		this.lastName = lastName;
		this.firstName = firstName;
		this.age = age;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.addressStreet = addressStreet;
		this.addressCity = addressCity;
		this.addressState = addressState;
		this.addressZip = addressZip;
		this.installation = installation;
		this.reasonInterested = reasonInterested;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddressStreet() {
		return addressStreet;
	}

	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public String getAddressState() {
		return addressState;
	}

	public void setAddressState(String addressState) {
		this.addressState = addressState;
	}

	public int getAddressZip() {
		return addressZip;
	}

	public void setAddressZip(int addressZip) {
		this.addressZip = addressZip;
	}

	public boolean isInstallation() {
		return installation;
	}

	public void setInstallation(boolean installation) {
		this.installation = installation;
	}

	public String getReasonInterested() {
		return reasonInterested;
	}

	public void setReasonInterested(String reasonInterested) {
		this.reasonInterested = reasonInterested;
	}

	public String printSuccessMessage(){
		return "Thank you " + this.getFirstName() + " " + this.getLastName() +" for your inquiry. You will be contacted soon by a Solar City sales representative at " + this.getPhoneNumber() +".";  
	}

	public String printErrorMessage(){
		return "Apologies for the inconvenience " + this.getFirstName() + " " + this.getLastName() +". We were not able to register your inquiry at this time. Please try again later or call a sales representative at 1-800-SLR-CITY.";  
	}

}
