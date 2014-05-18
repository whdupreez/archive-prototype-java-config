package com.willydupreez.prototype.config.provider;

import java.util.Optional;

import com.willydupreez.prototype.config.ConfigurationException;

/**
 * Provides properties using {@link System#getProperty(String)}.
 * Used to load properties provided using environment variables
 * or set using the -D option.
 *
 * @author Willy du Preez
 *
 */
public class SystemPropertyProvider implements PropertyProvider {

	@Override
	public Optional<String> getProperty(String key) {
		try {
			return Optional.ofNullable(System.getProperty(key));
		} catch (SecurityException e) {
			throw new ConfigurationException("Cannot load system property: " + key, e);
		}
	}

}
