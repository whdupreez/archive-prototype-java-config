package com.willydupreez.prototype.config.util;

import java.io.File;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

public final class Resources {

	public static Properties asProperties(Optional<URL> optionalUrl) {
		Properties properties = new Properties();
		optionalUrl.ifPresent(url -> {
			try (InputStreamReader reader = new InputStreamReader(url.openStream(), "UTF-8")) {
				properties.load(reader);
			} catch (Exception e) {
				throw new ResourceException(
						"Failed to load properties from URL: " + url.toString(), e);
			}
		});
		return properties;
	}

	public static Map<String, String> asPropertiesMap(Properties properties) {
		Map<String, String> map = new HashMap<>();
		properties.forEach((key, value) -> {
			if (value != null) {
				map.put(key.toString(), value.toString());
			}
		});
		return map;
	}

	public static Map<String, String> asPropertiesMap(Optional<URL> url) {
		return asPropertiesMap(asProperties(url));
	}

	public static Optional<URL> fromClasspath(String resource) {
		return Optional.ofNullable(Resources.class.getResource(resource));
	}

	public static Optional<URL> fromUri(URI uri) throws ResourceException {
		try {
			return Optional.of(uri.toURL());
		} catch (MalformedURLException | IllegalArgumentException e) {
			throw new ResourceException("Failed to convert to URL: " + uri.toString(), e);
		}
	}

	public static Optional<URL> fromFile(File file) throws ResourceException {
		try {
			if (file.isFile()) {
				return Optional.of(fromUri(file.toURI()).orElse(null));
			} else {
				return Optional.empty();
			}
		} catch (SecurityException e) {
			throw new ResourceException("Invalid permissions to access: " + file, e);
		}
	}

	public static Optional<URL> fromPath(String file) throws ResourceException {
		try {
			return Optional.ofNullable(fromPath(Paths.get(file)).orElse(null));
		} catch (InvalidPathException e) {
			throw new ResourceException("Invalid path specified: " + file, e);
		}
	}

	public static Optional<URL> fromPath(Path path) throws ResourceException {
		try {
			if (Files.isRegularFile(path)) {
				return Optional.of(fromUri(path.toUri()).orElse(null));
			} else {
				return Optional.empty();
			}
		} catch (SecurityException e) {
			throw new ResourceException("Invalid permissions to access: " + path.toString(), e);
		}
	}

	/**
	 * Creates a URL from a string. Note that the string is not
	 * encoded. To URI encode strings (i.e. HTTP URL's), use the
	 * {@link #fromUri(String)} method instead.
	 *
	 * @param url the URL string
	 * @return the URL
	 * @throws ResourceException if the URL string is malformed.
	 */
	public static Optional<URL> fromUrl(String url) throws ResourceException {
		try {
			return Optional.of(new URL(url));
		} catch (MalformedURLException e) {
			throw new ResourceException("Failed to convert to URL: " + url, e);
		}
	}

	/**
	 * Creates a URL from a URI string. The string is encoded
	 * before creating the URL. This is useful for HTTP URL
	 * strings that need to be encoded first.
	 *
	 * @param uri the URI string
	 * @return the URL created from the URI string
	 * @throws ResourceException
	 */
	public static Optional<URL> fromUri(String uri) throws ResourceException {
		try {
			return Optional.ofNullable(fromUri(new URI(uri)).orElse(null));
		} catch (URISyntaxException e) {
			throw new ResourceException("Failed to convert to URL: " + uri, e);
		}
	}

	private Resources() {
	}

}
