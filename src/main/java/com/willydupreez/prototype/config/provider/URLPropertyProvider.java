package com.willydupreez.prototype.config.provider;

import java.net.URL;

public class URLPropertyProvider extends MapPropertyProvider {

	public URLPropertyProvider(URL url) {
		super(PropertiesUtils.fromUrl(url));
	}

}
