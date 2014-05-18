package com.willydupreez.prototype.config;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.willydupreez.prototype.config.provider.MapPropertyProvider;
import com.willydupreez.prototype.config.provider.SystemPropertyProvider;
import com.willydupreez.prototype.config.testprops.DefaultTestProperties;

public class HierarchicalPropertiesFactoryTest {

	private HierarchicalPropertiesFactory propertiesFactory;

	@Before
	public void before() {

		System.setProperty("property.two", "system_two");

		Map<String, String> mapProperties = new HashMap<>();
		mapProperties.put("property.one", "map_one");
		mapProperties.put("property.two", "map_two");

		propertiesFactory = new HierarchicalPropertiesFactory(
				new SystemPropertyProvider(),
				new MapPropertyProvider(mapProperties));
	}

	@After
	public void after() {
		System.clearProperty("property.two");
	}

	@Test
	public void test() {
		DefaultTestProperties properties = propertiesFactory.create(DefaultTestProperties.class);
		assertThat(properties.getPropertyOne(), is("map_one"));
		assertThat(properties.getPropertyTwo(), is("system_two"));
		assertThat(properties.getPropertyThree(), is("dtThree"));
	}

}
