package com.willydupreez.prototype.config.bean;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.willydupreez.prototype.config.ConfigurationException;
import com.willydupreez.prototype.config.provider.PropertyProvider;

public class DefaultPropertiesBeanBuilder implements PropertiesBeanBuilder {

	@Override
	public <T> T buildPropertiesBean(Class<T> propertiesBeanType, List<PropertyProvider> providers) {
		try {
			T propertiesBean = propertiesBeanType.newInstance();
			List<PropertyDescriptor> descriptors = descriptors(propertiesBeanType);
			setBeanValues(propertiesBean, descriptors, providers);
			return propertiesBean;
		} catch (Exception e) {
			throw new ConfigurationException("Failed to initialise DefaultPropertiesBeanBuilder: " + e.getMessage(), e);
		}
	}

	private <T> void setBeanValues(T propertiesBean, List<PropertyDescriptor> descriptors, List<PropertyProvider> propertyProviders)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		for (PropertyDescriptor descriptor : descriptors) {
			String key = toPropertyKey(descriptor.getName());
			String value = getProperty(key, propertyProviders);
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

	private String getProperty(String key, List<PropertyProvider> propertyProviders) {
		for (PropertyProvider provider : propertyProviders) {
			Optional<String> value = provider.getProperty(key);
			if (value.isPresent()) {
				return value.get();
			}
		}
		return null;
	}

}
