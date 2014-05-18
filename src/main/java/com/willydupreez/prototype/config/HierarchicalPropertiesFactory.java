package com.willydupreez.prototype.config;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.willydupreez.prototype.config.provider.PropertyProvider;

/**
 * Searches for properties in a list of properties.
 *
 * @author Willy du Preez
 *
 */
public class HierarchicalPropertiesFactory implements PropertiesFactory {

	private List<PropertyProvider> propertyProviders;

	public HierarchicalPropertiesFactory(PropertyProvider ... providers) {
		this(Arrays.asList(providers));
	}

	public HierarchicalPropertiesFactory(List<PropertyProvider> providers) {
		this.propertyProviders = new ArrayList<>(providers);
	}

	@Override
	public <T> T create(Class<T> propertiesBeanType) {
		try {
			T propertiesBean = propertiesBeanType.newInstance();
			List<PropertyDescriptor> descriptors = descriptors(propertiesBeanType);
			setBeanValues(propertiesBean, descriptors);
			return propertiesBean;
		} catch (Exception e) {
			throw new ConfigurationException("Failed to initialise DefaultPropertiesBeanBuilder: " + e.getMessage(), e);
		}
	}

	private <T> void setBeanValues(T propertiesBean, List<PropertyDescriptor> descriptors)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		for (PropertyDescriptor descriptor : descriptors) {
			String key = toPropertyKey(descriptor.getName());
			String value = getProperty(key);
			if (value != null) {
				descriptor.getWriteMethod().invoke(propertiesBean, value);
			}
		}
	}

	private List<PropertyDescriptor> descriptors(Class<?> propertiesBeanType) throws Exception {
		BeanInfo propertiesBeanInfo = Introspector.getBeanInfo(propertiesBeanType);
		return Arrays.asList(propertiesBeanInfo.getPropertyDescriptors()).stream()
				.filter(descriptor -> isReadable(descriptor))
				.filter(descriptor -> isWritable(descriptor))
				.collect(Collectors.toList());
	}

	private boolean isReadable(PropertyDescriptor descriptor) {
		return descriptor.getReadMethod() != null;
	}

	private boolean isWritable(PropertyDescriptor descriptor) {
		return descriptor.getWriteMethod() != null;
	}

	private String toPropertyKey(String propertyMethodName) {
		return propertyMethodName.replaceAll("(.)([A-Z])", "$1.$2").toLowerCase();
	}

	private String getProperty(String key) {
		for (PropertyProvider provider : propertyProviders) {
			Optional<String> value = provider.getProperty(key);
			if (value.isPresent()) {
				return value.get();
			}
		}
		return null;
	}

}
