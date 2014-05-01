package com.willydupreez.prototype.config;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DefaultPropertiesFactoryTest {

	private PropertiesFactory factory;

	@Before
	public void before() {
		factory = new DefaultPropertiesFactory();
	}

	@Test
	public void test_DefaultTestProperties() {
		DefaultTestProperties props = factory.create(DefaultTestProperties.class);
		assertThat(props.getPropertyOne(), is("dtOne"));
		assertThat(props.getPropertyTwo(), is("dtTwo"));
		assertThat(props.getPropertyThree(), is("dtThree"));
	}

	@Test
	public void test_ClasspathProperties() {
		ClasspathProperties props = factory.create(ClasspathProperties.class);
		assertThat(props.getPropertyOne(), is("cpOne"));
		assertThat(props.getPropertyTwo(), is("cpTwo"));
		assertThat(props.getPropertyThree(), is("cpThree"));
	}

	@Test
	public void test_InvalidPropertiesClassname() {
		assertTrue(false);
	}

}
