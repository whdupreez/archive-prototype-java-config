package com.willydupreez.prototype.config;

public class DefaultPropertiesFactory implements PropertiesFactory {

	@Override
	public <T> T create(Class<T> propertiesType) {
		System.out.println(getResourceName(propertiesType));
		try {
			return propertiesType.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new ConfigurationException("Failed to create instance of property type: "
					+ propertiesType.getName(), e);
		}
	}

	private void populateClasspathProperties() {

	}

	private void hasClasspathProperties() {

	}

	private String getResourceName(Class<?> propertiesType) {

		if (!propertiesType.getName().endsWith("Properties")) {
			throw new ConfigurationException(
					"Properties class name should end with 'Properties', i.e. ApplicationProperties");
		}

		String packageName = propertiesType.getPackage().getName();
		String className = propertiesType.getName();
		if (Character.isUpperCase(className.charAt(0))) {
			className = className;
		}
		className = className
				.replaceFirst("Properties$", ".properties")
				.replaceAll("(.)([A-Z])", "$1_$2")
				.toLowerCase();

		return packageName + "." + className;
	}

}
