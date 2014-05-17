package com.willydupreez.prototype.config.convert;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class LongConverterTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testConvertToType() {
		assertThat(new LongConverter().convertToType("154"), is(154L));
		assertThat(new LongConverter().convertToType("+7532"), is(7532L));
		assertThat(new LongConverter().convertToType("-15274322"), is(-15274322L));
		assertThat(new LongConverter().convertToType(String.valueOf(Long.MIN_VALUE)), is(Long.MIN_VALUE));
		assertThat(new LongConverter().convertToType(String.valueOf(Long.MAX_VALUE)), is(Long.MAX_VALUE));
	}

	@Test
	public void testConvertToType_IllegalValue() {
		thrown.expect(TypeConversionException.class);
		thrown.expectCause(isA(NumberFormatException.class));
		thrown.expectMessage(containsString("154L"));

		new LongConverter().convertToType("154L");
	}

}
