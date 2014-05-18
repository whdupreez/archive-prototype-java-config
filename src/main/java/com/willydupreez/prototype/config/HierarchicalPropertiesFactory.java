package com.willydupreez.prototype.config;

import java.util.ArrayList;
import java.util.List;

import com.willydupreez.prototype.config.bean.DefaultPropertiesBeanBuilder;
import com.willydupreez.prototype.config.provider.PropertyProvider;

/**
 * Searches for properties in a list of properties.
 *
 * @author Willy du Preez
 *
 */
public class HierarchicalPropertiesFactory implements PropertiesFactory {

	public static class Builder {

		private Builder() {
		}



	}

	public static Builder builder() {
		return new Builder();
	}

	private List<Class<? extends PropertyProvider>> propertyProviderTypes;
	private DefaultPropertiesBeanBuilder defaultPropertiesBeanBuilder;

	public HierarchicalPropertiesFactory(List<Class<? extends PropertyProvider>> providerTypes) {
		this.propertyProviderTypes = new ArrayList<>(providerTypes);
		this.defaultPropertiesBeanBuilder = new DefaultPropertiesBeanBuilder();
	}

	@Override
	public <T> T create(Class<T> propertiesBeanType) {
		try {
			return defaultPropertiesBeanBuilder.buildPropertiesBean(
					propertiesBeanType,
					propertyProviders());
		} catch (Exception e) {
			throw new ConfigurationException("Failed to create properties for type: "
		+ propertiesBeanType.getName(), e);
		}
	}

	private List<PropertyProvider> propertyProviders() throws Exception {
		List<PropertyProvider> propertyProviders = new ArrayList<>();
		for (Class<? extends PropertyProvider> propertyProviderType : propertyProviderTypes) {
			propertyProviders.add(propertyProviderType.newInstance());
		}
		return propertyProviders;
	}

}
