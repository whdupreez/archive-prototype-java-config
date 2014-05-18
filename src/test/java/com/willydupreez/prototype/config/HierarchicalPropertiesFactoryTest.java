package com.willydupreez.prototype.config;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.willydupreez.prototype.config.provider.MapPropertyProvider;
import com.willydupreez.prototype.config.testprops.DefaultTestProperties;

public class HierarchicalPropertiesFactoryTest {

	private HierarchicalPropertiesFactory propertiesFactory;

	@Before
	public void before() {

		Map<String, String> mapProperties = new HashMap<>();
		mapProperties.put("property.one", "map_one");
		mapProperties.put("property.two", "map_two");
		mapProperties.put("property.three", "map_three");

		MapPropertyProvider mapProvider = new MapPropertyProvider(mapProperties);

		propertiesFactory = new HierarchicalPropertiesFactory(
				mapProvider);
	}

	@Test
	public void test() {
		DefaultTestProperties properties = propertiesFactory.create(DefaultTestProperties.class);
		assertThat(properties.getPropertyOne(), is("map_one"));
	}

}
