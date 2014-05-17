package com.willydupreez.prototype.config.provider;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Provides properties from a {@link Map} source.
 *
 * @author Willy du Preez
 *
 */
public class MapPropertyProvider implements PropertyProvider {

	private Map<String, String> source;

	/**
	 * Constructs a provider and copies the properties
	 * into a source map from which property values will
	 * be retrieved.
	 *
	 * @param properties the properties
	 */
	public MapPropertyProvider(Map<String, String> properties) {
		this.source = new HashMap<>(properties);
	}

	@Override
	public Optional<String> getProperty(String key) {
		return Optional.ofNullable(source.get(key));
	}

}
