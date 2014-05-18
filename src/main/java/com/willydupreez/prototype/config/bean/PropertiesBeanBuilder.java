package com.willydupreez.prototype.config.bean;

import java.util.List;

import com.willydupreez.prototype.config.provider.PropertyProvider;

public interface PropertiesBeanBuilder {

	<T> T buildPropertiesBean(Class<T> propertiesBeanType, List<PropertyProvider> providers);

}
