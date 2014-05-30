package com.willydupreez.prototype.config.bean;

import static java.util.Arrays.asList;

import java.beans.PropertyDescriptor;
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
import com.willydupreez.prototype.config.util.Types;

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

	private <T> void setBeanValues(T propertiesBean, List<PropertyDescriptor> descriptors,
			List<PropertyProvider> propertyProviders) {

		for (PropertyDescriptor descriptor : descriptors) {
			String key = descriptor.getName();
			String stringValue = findProperty(key, propertyProviders);
			if (stringValue != null) {
				Object value = convertToType(descriptor.getPropertyType(), stringValue);
				Beans.setProperty(propertiesBean, descriptor, value);
			}
		}
	}

	private Object convertToType(Class<?> propertyType, String value) {
		if (propertyType.isPrimitive()) {
			propertyType = Types.autoboxType(propertyType);
		}
		for (TypeConverter<?> typeConverter : typeConverters) {
			if (typeConverter.getType().equals(propertyType)) {
				return typeConverter.convertToType(value);
			}
		}
		throw new ConfigurationException("No type converter registered for type: " + propertyType);
	}

	private String findProperty(String key, List<PropertyProvider> propertyProviders) {
		for (PropertyProvider provider : propertyProviders) {
			Optional<String> value = provider.getProperty(key);
			if (value.isPresent()) {
				return value.get();
			}
		}
		return null;
	}

}
