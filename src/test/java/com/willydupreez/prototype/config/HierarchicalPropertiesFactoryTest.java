package com.willydupreez.prototype.config;

import java.util.Arrays;

import org.junit.Test;

import com.willydupreez.prototype.config.provider.SystemPropertyProvider;

public class HierarchicalPropertiesFactoryTest {

	@Test
	public void test() {
		new HierarchicalPropertiesFactory(Arrays.asList(
				SystemPropertyProvider.class));
	}

}
