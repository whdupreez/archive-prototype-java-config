package com.willydupreez.prototype.config.provider;

import static com.willydupreez.prototype.config.util.Resources.asPropertiesMap;
import static com.willydupreez.prototype.config.util.Resources.fromPath;

import java.nio.file.Path;

/**
 * Provides properties from a Java properties file.
 *
 * @author Willy du Preez
 *
 */
public class PathPropertyProvider extends MapPropertyProvider {

	public PathPropertyProvider(String path) {
		super(asPropertiesMap(fromPath(path)));
	}

	public PathPropertyProvider(Path path) {
		super(asPropertiesMap(fromPath(path)));
	}

}
