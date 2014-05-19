package com.willydupreez.prototype.config.bean;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.willydupreez.prototype.config.provider.JavaPropertyProvider;
import com.willydupreez.prototype.config.provider.MapPropertyProvider;
import com.willydupreez.prototype.config.provider.PropertyProvider;
import com.willydupreez.prototype.config.testprops.DefaultTestProperties;

public class DefaultPropertiesBeanBuilderTest {

	private List<PropertyProvider> propertyProviders;

	@Before
	public void before() {

		System.setProperty("property.two", "system_two");

		Map<String, String> mapProperties = new HashMap<>();
		mapProperties.put("property.one", "map_one");
		mapProperties.put("property.two", "map_two");

		propertyProviders = Arrays.asList(new JavaPropertyProvider(), new MapPropertyProvider(mapProperties));
	}

	@After
	public void after() {
		System.clearProperty("property.two");
	}

	@Test
	public void test() {
		DefaultPropertiesBeanBuilder builder = new DefaultPropertiesBeanBuilder();
		DefaultTestProperties properties = builder.buildPropertiesBean(DefaultTestProperties.class, propertyProviders);

		assertThat(properties.getPropertyOne(), is("map_one"));
		assertThat(properties.getPropertyTwo(), is("system_two"));
		assertThat(properties.getPropertyThree(), is("dtThree"));
	}

}
