package com.willydupreez.prototype.config.provider;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Properties;

import com.willydupreez.prototype.config.ConfigurationException;

public class PropertiesLoader {

	public Properties fromClasspath(String resourceLocation) {
		try (InputStream in = this.getClass().getResourceAsStream(resourceLocation)) {
			Properties properties = new Properties();
			properties.load(in);
			return properties;
		} catch (Exception e) {
			throw new ConfigurationException(
					"Failed to load properties from classpath: " + resourceLocation, e);
		}
	}

	public Properties fromPath(Path path) {
		try (InputStream in = Files.newInputStream(path, StandardOpenOption.READ)) {
			Properties properties = new Properties();
			properties.load(in);
			return properties;
		} catch (Exception e) {
			throw new ConfigurationException(
					"Failed to load properties from path: " + path.toAbsolutePath().toString(), e);
		}
	}

	public Properties fromFile(File file) {
		try (FileInputStream in = new FileInputStream(file)) {
			Properties properties = new Properties();
			properties.load(in);
			return properties;
		} catch (Exception e) {
			throw new ConfigurationException(
					"Failed to load properties from file: " + file.getAbsolutePath(), e);
		}
	}

}
