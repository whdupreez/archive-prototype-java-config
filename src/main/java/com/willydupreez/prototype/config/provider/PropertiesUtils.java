package com.willydupreez.prototype.config.provider;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.willydupreez.prototype.config.ConfigurationException;

public class PropertiesUtils {

	public static Map<String, String> convertToMap(Properties properties) {
		Map<String, String> map = new HashMap<String, String>();
		properties.forEach((key, value) -> {
			map.put(key.toString(), value.toString());
		});
		return map;
	}

	public static Map<String, String> fromClasspath(String resourceLocation) {
		try (InputStream in = PropertiesUtils.class.getResourceAsStream(resourceLocation)) {
			Properties properties = new Properties();
			properties.load(in);
			return convertToMap(properties);
		} catch (Exception e) {
			throw new ConfigurationException(
					"Failed to load properties from classpath: " + resourceLocation, e);
		}
	}

	public static Map<String, String> fromPath(Path path) {
		try (InputStream in = Files.newInputStream(path, StandardOpenOption.READ)) {
			Properties properties = new Properties();
			properties.load(in);
			return convertToMap(properties);
		} catch (Exception e) {
			throw new ConfigurationException(
					"Failed to load properties from path: " + path.toAbsolutePath().toString(), e);
		}
	}

	public static Properties fromFile(File file) {
		try (FileInputStream in = new FileInputStream(file)) {
			Properties properties = new Properties();
			properties.load(in);
			return properties;
		} catch (Exception e) {
			throw new ConfigurationException(
					"Failed to load properties from file: " + file.getAbsolutePath(), e);
		}
	}

	public static Map<String, String> fromUrl(URL url) {
		try (InputStream in = url.openStream()) {
			Properties properties = new Properties();
			properties.load(in);
			return convertToMap(properties);
		} catch (Exception e) {
			throw new ConfigurationException(
					"Failed to load properties from file: " + url.toString(), e);
		}
	}

}
