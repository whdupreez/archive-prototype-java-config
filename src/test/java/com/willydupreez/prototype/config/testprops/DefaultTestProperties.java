package com.willydupreez.prototype.config.testprops;

public class DefaultTestProperties {

	private String propertyOne = "dtOne";
	private String propertyTwo = "dtTwo";
	private String propertyThree = "dtThree";

	private Integer propertyFour = 4;
	private int propertyFive = 5;

	private Long propertySix = 6L;
	private long propertySeven = 7;

	private Boolean propertyEight = true;
	private boolean propertyNine = true;

	private String readonly;
	private String writeonly;

	public String getPropertyOne() {
		return propertyOne;
	}

	public void setPropertyOne(String propertyOne) {
		this.propertyOne = propertyOne;
	}

	public String getPropertyTwo() {
		return propertyTwo;
	}

	public void setPropertyTwo(String propertyTwo) {
		this.propertyTwo = propertyTwo;
	}

	public String getPropertyThree() {
		return propertyThree;
	}

	public void setPropertyThree(String propertyThree) {
		this.propertyThree = propertyThree;
	}

	public String notAProperty() {
		return writeonly;
	}

	public String getReadonly() {
		return readonly;
	}

	public void setWriteonly(String writeonly) {
		this.writeonly = writeonly;
	}

	public Integer getPropertyFour() {
		return propertyFour;
	}

	public void setPropertyFour(Integer propertyFour) {
		this.propertyFour = propertyFour;
	}

	public int getPropertyFive() {
		return propertyFive;
	}

	public void setPropertyFive(int propertyFive) {
		this.propertyFive = propertyFive;
	}

	public Long getPropertySix() {
		return propertySix;
	}

	public void setPropertySix(Long propertySix) {
		this.propertySix = propertySix;
	}

	public long getPropertySeven() {
		return propertySeven;
	}

	public void setPropertySeven(long propertySeven) {
		this.propertySeven = propertySeven;
	}

	public Boolean getPropertyEight() {
		return propertyEight;
	}

	public void setPropertyEight(Boolean propertyEight) {
		this.propertyEight = propertyEight;
	}

	public boolean isPropertyNine() {
		return propertyNine;
	}

	public void setPropertyNine(boolean propertyNine) {
		this.propertyNine = propertyNine;
	}

}
