/*
 * This is a model class that simply stores information (name, price, etc) about a type of solar cell.
 * Access to the information is provided through getter/setters.
 */
package model;

public class SolarCell {
	private String solarCellType;
	private String solarCellCode;
	private int pricePerWatt;

	/*
	 * No-Arg constructor
	 */
	public SolarCell(){

	}

	/*
	 * Constructor with parameters String and int
	 */
	public SolarCell(String solarCellType, int pricePerWatt){
		this.solarCellType = solarCellType;
		this.pricePerWatt = pricePerWatt;
	}

	/*
	 * Constructor with parameters String, String and int
	 */
	public SolarCell(String solarCellType, String solarCellCode, int pricePerWatt){
		this.solarCellType = solarCellType;
		this.solarCellCode = solarCellCode;
		this.pricePerWatt = pricePerWatt;
	}


	public String getSolarCellType() {
		return solarCellType;
	}

	public void setSolarCellType(String solarCellType) {
		this.solarCellType = solarCellType;
	}

	public String getSolarCellCode() {
		return solarCellCode;
	}

	public void setSolarCellCode(String solarCellCode) {
		this.solarCellCode = solarCellCode;
	}

	public int getPricePerWatt() {
		return pricePerWatt;
	}

	public void setPricePerWatt(int pricePerWatt) {
		this.pricePerWatt = pricePerWatt;
	}

}
