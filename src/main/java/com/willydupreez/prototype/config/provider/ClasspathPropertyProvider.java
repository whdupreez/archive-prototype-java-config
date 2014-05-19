package com.willydupreez.prototype.config.provider;

import java.util.Optional;
import java.util.Properties;

public class ClasspathPropertyProvider implements PropertyProvider {

	private final Properties properties;

	public static ClasspathPropertyProvider from(String resourceLocation) {
		Properties properties;
		if (ClasspathPropertyProvider.class.getResource(resourceLocation) != null) {
			properties = new PropertiesLoader().fromClasspath(resourceLocation);
		} else {
			properties = new Properties();
		}
		return new ClasspathPropertyProvider(properties);
	}

	private ClasspathPropertyProvider(Properties properties) {
		this.properties = properties;
	}

	@Override
	public Optional<String> getProperty(String key) {
		return Optional.ofNullable(properties.getProperty(key));
	}

}
