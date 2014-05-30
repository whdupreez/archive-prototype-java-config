package com.willydupreez.prototype.config.convert;

public class StringConverter implements TypeConverter<String> {

	@Override
	public String convertToType(String property) {
		return property;
	}

	@Override
	public Class<String> getType() {
		return String.class;
	}

}
