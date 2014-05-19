package com.willydupreez.prototype.config.provider;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Properties;

/**
 * Provides properties from a Java properties file.
 *
 * @author Willy du Preez
 *
 */
public class FilePropertyProvider implements PropertyProvider {

	public static FilePropertyProvider from(Path path) {
		Properties properties;
		if (Files.exists(path)) {
			properties = new PropertiesLoader().fromPath(path);
		} else {
			properties = new Properties();
		}
		return new FilePropertyProvider(properties);
	}

	private final Properties properties;

	public FilePropertyProvider(Properties properties) {
		this.properties = properties;
	}

	@Override
	public Optional<String> getProperty(String key) {
		return Optional.ofNullable(properties.getProperty(key));
	}

}
