package com.willydupreez.prototype.config.convert;

public class BooleanConverter implements TypeConverter<Boolean> {

	private static final String TRUE = "true";
	private static final String FALSE = "false";

	@Override
	public Boolean convertToType(String property) {
		if (TRUE.equalsIgnoreCase(property)) {
			return true;
		} else if (FALSE.equalsIgnoreCase(property)) {
			return false;
		} else {
			throw new TypeConversionException("Cannot convert to Boolean: " + property);
		}
	}

	@Override
	public Class<Boolean> getType() {
		return Boolean.class;
	}

}
