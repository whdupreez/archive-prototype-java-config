package com.willydupreez.prototype.config;

public interface PropertiesFactory {

	public <T> T create(Class<T> propertiesBeanType);

}
