package com.willydupreez.prototype.config.provider;


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

	public SystemPropertyProvider() {
		super(System.getenv());
	}

}
