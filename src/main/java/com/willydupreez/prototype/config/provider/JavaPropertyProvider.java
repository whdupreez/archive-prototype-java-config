package com.willydupreez.prototype.config.provider;

import com.willydupreez.prototype.config.util.Resources;

/**
 * Provides Java properties supplied using the -D environment
 * or set using {@link System#setProperty(String, String)} or
 * {@link System#setProperties(java.util.Properties)}.
 *
 * Note that these properties are not namespaced, and are
 * therefore considered to be in the global namespace.
 *
 * @author Willy du Preez
 *
 */
public class JavaPropertyProvider extends MapPropertyProvider {

	public JavaPropertyProvider() {
		super(Resources.asPropertiesMap(System.getProperties()));
	}

}
