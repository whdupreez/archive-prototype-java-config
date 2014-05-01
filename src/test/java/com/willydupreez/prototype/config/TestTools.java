package com.willydupreez.prototype.config;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class TestTools {

	public static String getResourceDirectory() {
		URL url = TestTools.class.getResource("/etc");
		try {
			return Paths.get(url.toURI()).toString();
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

}
