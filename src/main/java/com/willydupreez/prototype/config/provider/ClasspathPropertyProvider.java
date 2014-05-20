package com.willydupreez.prototype.config.provider;

import static com.willydupreez.prototype.config.util.Resources.asPropertiesMap;
import static com.willydupreez.prototype.config.util.Resources.fromClasspath;

/**
 * Provides properties from a classpath resource.
 *
 * @author Willy du Preez
 *
 */
public class ClasspathPropertyProvider extends MapPropertyProvider {

	public ClasspathPropertyProvider(String resource) {
		super(asPropertiesMap(fromClasspath(resource)));
	}

}
