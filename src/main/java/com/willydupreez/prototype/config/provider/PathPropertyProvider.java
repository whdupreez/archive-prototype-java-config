package com.willydupreez.prototype.config.provider;

import java.nio.file.Path;

/**
 * Provides properties from a Java properties file.
 *
 * @author Willy du Preez
 *
 */
public class PathPropertyProvider extends MapPropertyProvider {

	public PathPropertyProvider(Path path) {
		super(PropertiesUtils.fromPath(path));
	}

}
