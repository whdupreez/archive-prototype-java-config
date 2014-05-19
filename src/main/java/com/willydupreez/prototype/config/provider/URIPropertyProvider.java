package com.willydupreez.prototype.config.provider;

import java.net.URI;
import java.net.URL;

import com.willydupreez.prototype.config.ConfigurationException;

public class URIPropertyProvider extends URLPropertyProvider {

	private static URL toURL(URI uri) {
		try {
			return uri.toURL();
		} catch (Exception e) {
			throw new ConfigurationException(
					"Failed to create provider from URI: " + uri.toString(), e);
		}
	}

	public URIPropertyProvider(URI uri) {
		super(toURL(uri));
	}

}
