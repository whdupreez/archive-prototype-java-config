package com.willydupreez.prototype.config.provider;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SystemPropertyProviderTest {

	private static final String KEY = "system.prop.test";
	private static final String VALUE = "systemValue";

	@Before
	public void before() {
		System.setProperty(KEY, VALUE);
	}

	@After
	public void after() {
		System.clearProperty(KEY);
	}

	@Test
	public void testSystemPropertyProvider() {
		SystemPropertyProvider provider = new SystemPropertyProvider();
		assertThat(provider.getProperty(KEY).get(), is(VALUE));
		assertThat(provider.getProperty("non.existent").isPresent(), is(false));
	}

}
