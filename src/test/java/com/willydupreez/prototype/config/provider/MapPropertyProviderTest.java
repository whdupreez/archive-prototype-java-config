package com.willydupreez.prototype.config.provider;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MapPropertyProviderTest {

	private static final String KEY_ONE = "provider.map.one";
	private static final String VAL_ONE = "one_val";

	private static final String KEY_TWO = "provider.map.two";
	private static final String VAL_TWO = "one_val";

	private static final String NO_SUCH_KEY = "provider.map.noSuchKey";

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private Map<String, String> testProperties;
	private MapPropertyProvider provider;

	@Before
	public void before() {
		this.testProperties = new HashMap<>();
		this.testProperties.put(KEY_ONE, VAL_ONE);
		this.testProperties.put(KEY_TWO, VAL_TWO);

		this.provider = new MapPropertyProvider(testProperties);
	}

	@Test
	public void testGetProperty() {
		assertThat(provider.getProperty(KEY_ONE).get(), is(VAL_ONE));
		assertThat(provider.getProperty(KEY_TWO).get(), is(VAL_TWO));

		assertThat(provider.getProperty(NO_SUCH_KEY).isPresent(), is(false));
	}

	@Test
	public void testGetProperty_NoSuchKey() {
		thrown.expect(NoSuchElementException.class);

		provider.getProperty(NO_SUCH_KEY).get();
	}

}
