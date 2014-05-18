package com.willydupreez.prototype.config.testprops;

public class DefaultTestProperties {

	private String propertyOne = "dtOne";
	private String propertyTwo = "dtTwo";
	private String propertyThree = "dtThree";

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

}
