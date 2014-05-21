package com.willydupreez.prototype.config.bean;

import static java.util.Arrays.asList;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

import com.willydupreez.prototype.config.ConfigurationException;
import com.willydupreez.prototype.config.convert.BooleanConverter;
import com.willydupreez.prototype.config.convert.IntegerConverter;
import com.willydupreez.prototype.config.convert.LongConverter;
import com.willydupreez.prototype.config.convert.StringConverter;
import com.willydupreez.prototype.config.convert.TypeConverter;
import com.willydupreez.prototype.config.provider.PropertyProvider;
import com.willydupreez.prototype.config.util.Beans;

public class DefaultPropertiesBeanBuilder implements PropertiesBeanBuilder {

	private final List<TypeConverter<?>> typeConverters;

	public DefaultPropertiesBeanBuilder() {
		this(asList(
				new StringConverter(),
				new BooleanConverter(),
				new IntegerConverter(),
				new LongConverter()));
	}

	public DefaultPropertiesBeanBuilder(List<TypeConverter<?>> typeConverters) {
		this.typeConverters = typeConverters;
	}

	@Override
	public <T> T buildPropertiesBean(Class<T> propertiesBeanType, List<PropertyProvider> providers) {
		try {
			T propertiesBean = propertiesBeanType.newInstance();
			List<PropertyDescriptor> descriptors = Beans.descriptors(propertiesBeanType);
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
