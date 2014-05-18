package com.willydupreez.prototype.config.testprops;

import java.util.List;

public class FileBasedProperties {

	private boolean boolVal;
	private Boolean oBoolVal;

	private int intVal;
	private Integer oIntVal;

	private long longVal;
	private Long oLongVal;

	private String strVal;

	private List<String> listOfStrings;

	public boolean isBoolVal() {
		return boolVal;
	}

	public void setBoolVal(boolean boolVal) {
		this.boolVal = boolVal;
	}

	public Boolean getoBoolVal() {
		return oBoolVal;
	}

	public void setOBoolVal(Boolean oBoolVal) {
		this.oBoolVal = oBoolVal;
	}

	public int getIntVal() {
		return intVal;
	}

	public void setIntVal(int intVal) {
		this.intVal = intVal;
	}

	public Integer getOIntVal() {
		return oIntVal;
	}

	public void setOIntVal(Integer oIntVal) {
		this.oIntVal = oIntVal;
	}

	public long getLongVal() {
		return longVal;
	}

	public void setLongVal(long longVal) {
		this.longVal = longVal;
	}

	public Long getoLongVal() {
		return oLongVal;
	}

	public void setoLongVal(Long oLongVal) {
		this.oLongVal = oLongVal;
	}

	public String getStrVal() {
		return strVal;
	}

	public void setStrVal(String strVal) {
		this.strVal = strVal;
	}

	public List<String> getListOfStrings() {
		return listOfStrings;
	}

	public void setListOfStrings(List<String> listOfStrings) {
		this.listOfStrings = listOfStrings;
	}

}
