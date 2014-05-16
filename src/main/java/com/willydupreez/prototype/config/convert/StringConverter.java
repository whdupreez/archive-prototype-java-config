package com.willydupreez.prototype.config.convert;

public class StringConverter implements TypeConverter<String> {

	@Override
	public String convertToType(String property) {
		if (property == null) {

		}
		return property;
	}

}
