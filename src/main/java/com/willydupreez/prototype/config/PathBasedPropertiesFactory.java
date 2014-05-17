package com.willydupreez.prototype.config;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Properties;

/**
 * Loads properties from file in a specified {@link Path}. The
 * file names are derived from the fully qualified class name
 * of the properties bean, e.g. a properties bean class:
 *
 * <pre>com.abc.DbConnProperties</pre>
 *
 * will result in properties being loaded from the following file:
 *
 * <pre>com.abc.db_conn.properties</pre>
 *
 * @author Willy du Preez
 *
 */
public class PathBasedPropertiesFactory implements PropertiesFactory {

	private Path etc;

	public PathBasedPropertiesFactory(String etcPath) {
		etc = Paths.get(etcPath);
	}

	@Override
	public <T> T create(Class<T> propertiesType) {
		try {
			T properties = propertiesType.newInstance();
			populateFromFile(propertiesType, properties);
			return properties;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new ConfigurationException("Failed to create instance of property type: "
					+ propertiesType.getName(), e);
		}
	}

	private <T> void populateFromFile(Class<T> propertiesType, T properties) {
		Properties fileProps = loadProperties(getResourceName(propertiesType));
		for (Object keyObj : fileProps.keySet()) {
			String key = keyObj.toString();
			String propertyName = propertyName(key);
			Field property = propertyField(propertiesType, propertyName, key);
			setPropertyValue(property, properties, fileProps.getProperty(key));
		}
	}

	private String getResourceName(Class<?> propertiesType) {

		if (!propertiesType.getName().endsWith("Properties")) {
			throw new ConfigurationException(
					"Properties class name should end with 'Properties', i.e. ApplicationProperties");
		}

		// 1. Replace the ending Properties of the class name with .properties
		//      com.abc.DbConnProperties -> com.abc.DbConn.properties
		// 2. Append "_" in front of all capital letters
		//	    com.abc.DbConn.properties -> com.abc._Db_Conn.properties
		// 3. Replace the first "._" with a "."
		//	    com.abc.DbConn.properties -> com.abc.Db_Conn.properties
		// 4. All lower case
		//	    com.abc.DbConn.properties -> com.abc.db_conn.properties
		String className = propertiesType.getName()
				.replaceFirst("Properties$", ".properties")
				.replaceAll("(.)([A-Z])", "$1_$2")
				.replaceAll("\\._", ".")
				.toLowerCase();

		return className;
	}

	private Properties loadProperties(String filename) {
		try (InputStream in = Files.newInputStream(etc.resolve(filename), StandardOpenOption.READ)) {
			Properties javaProps = new Properties();
			javaProps.load(in);
			return javaProps;
		} catch (IOException e) {
			throw new ConfigurationException("Failed to read properties file: " + filename, e);
		}
	}

	private String propertyName(String key) {
		StringBuilder propertyName = new StringBuilder();
		for (String word : key.split("\\.")) {
			if (propertyName.length() == 0) {
				propertyName.append(Character.toLowerCase(word.charAt(0)));
			} else {
				propertyName.append(Character.toUpperCase(word.charAt(0)));
			}
			if (word.length() > 1) {
				propertyName.append(word.substring(1));
			}
		};
		return propertyName.toString();
	}

	// TODO Support searching method names which searches super classes and interfaces.
	private Field propertyField(Class<?> propertiesType, String propertyName, String key) {
		try {
			Field field = propertiesType.getDeclaredField(propertyName);
			field.setAccessible(true);
			return field;
		} catch (NoSuchFieldException | SecurityException e) {
			throw new ConfigurationException("Property [" + propertyName + "] for key [" + key + "] not found on class: " + propertiesType.getName());
		}
	}

	// TODO better error messages
	private void setPropertyValue(Field field, Object properties, String value) {
		try {
			switch (field.getType().getName()) {
			case "boolean":
			case "java.lang.Boolean":
				field.set(properties, Boolean.parseBoolean(value));
				break;
			case "int":
			case "java.lang.Integer":
				field.set(properties, Integer.parseInt(value));
				break;
			case "long":
			case "java.lang.Long":
				field.set(properties, Long.parseLong(value));
				break;
			case "java.lang.String":
				field.set(properties, value);
				break;
			case "java.util.List":
				field.set(properties, Arrays.asList(value.split(",")));
				break;
			default:
				throw new ConfigurationException("Unsupported return type [" +
							field.getType().getName() + "] for property: " + field.getName());
			}

		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new ConfigurationException("Failed to set property: " + field.getName(), e);
		}
	}

}
