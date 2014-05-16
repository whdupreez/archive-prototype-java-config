package com.willydupreez.prototype.config.convert;

public interface TypeConverter<T> {

	T convertToType(String property);

}
