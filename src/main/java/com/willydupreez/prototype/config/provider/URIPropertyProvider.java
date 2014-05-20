package com.willydupreez.prototype.config.provider;

import static com.willydupreez.prototype.config.util.Resources.asPropertiesMap;
import static com.willydupreez.prototype.config.util.Resources.fromUri;

import java.net.URI;

public class URIPropertyProvider extends MapPropertyProvider {

	public URIPropertyProvider(String uri) {
		super(asPropertiesMap(fromUri(uri)));
	}

	public URIPropertyProvider(URI uri) {
		super(asPropertiesMap(fromUri(uri)));
	}

}
