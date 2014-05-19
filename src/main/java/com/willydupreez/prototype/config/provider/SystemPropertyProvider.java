package com.willydupreez.prototype.config.provider;

import java.util.Map;

/**
 * Provides properties from system environment variables using
 * {@link System#getenv()}.
 *
 * Note that these properties are not namespaced, and are
 * therefore considered to be in the global namespace.
 *
 * @author Willy du Preez
 *
 */
public class SystemPropertyProvider extends MapPropertyProvider {

	public SystemPropertyProvider(Map<String, String> properties) {
		super(System.getenv());
	}

}
