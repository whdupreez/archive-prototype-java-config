package com.willydupreez.prototype.config;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.willydupreez.prototype.config.testprops.DefaultTestProperties;
import com.willydupreez.prototype.config.testprops.FileBasedProperties;
import com.willydupreez.prototype.config.testprops.InvalidNameProps;
import com.willydupreez.prototype.config.testprops.MissingFieldProperties;
import com.willydupreez.prototype.config.testprops.MissingFileProperties;
import com.willydupreez.prototype.config.testprops.NoConstructorProperties;
import com.willydupreez.prototype.config.testprops.UnsupportedTypeProperties;

public class PathBasedPropertiesFactoryTest {

	private PropertiesFactory factory;

	@Before
	public void before() {
		factory = new PathBasedPropertiesFactory(TestTools.getResourceDirectory());
	}

	@Test
	public void test_DefaultTestProperties() {
		DefaultTestProperties props = factory.create(DefaultTestProperties.class);
		assertThat(props.getPropertyOne(), is("dtOne"));
		assertThat(props.getPropertyTwo(), is("dtTwo"));
		assertThat(props.getPropertyThree(), is("dtThree"));
	}

	@Test
	public void test_FileBasedProperties() {
		FileBasedProperties props = factory.create(FileBasedProperties.class);
		assertThat(props.isBoolVal(), is(true));
		assertThat(props.getoBoolVal(), is(true));
		assertThat(props.getIntVal(), is(321));
		assertThat(props.getOIntVal(), is(123));
		assertThat(props.getLongVal(), is(54321L));
		assertThat(props.getoLongVal(), is(12345L));
		assertThat(props.getStrVal(), is("A string"));
		assertThat(props.getListOfStrings(), is(Arrays.asList("str1", "str2", "str3")));
	}

	@Test(expected = ConfigurationException.class)
	public void test_MissingFileProperties() {
		factory.create(MissingFileProperties.class);
	}

	@Test(expected = ConfigurationException.class)
	public void test_MissingFieldProperties() {
		factory.create(MissingFieldProperties.class);
	}

	@Test(expected = ConfigurationException.class)
	public void test_NoConstructorProperties() {
		factory.create(NoConstructorProperties.class);
	}

	@Test(expected = ConfigurationException.class)
	public void test_InvalidClassNameProps() {
		factory.create(InvalidNameProps.class);
	}

	@Test(expected = ConfigurationException.class)
	public void test_UnsupportedType() {
		factory.create(UnsupportedTypeProperties.class);
	}

}
