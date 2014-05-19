package com.willydupreez.prototype.config.provider;

public class ClasspathPropertyProvider extends MapPropertyProvider {

	public ClasspathPropertyProvider(String resourceLocation) {
		super(PropertiesUtils.fromClasspath(resourceLocation));
	}

}
