package com.willydupreez.prototype.config.convert;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IntegerConverterTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testConvertToType() {
		assertThat(new IntegerConverter().convertToType("154"), is(154));
		assertThat(new IntegerConverter().convertToType("-15274322"), is(-15274322));
		assertThat(new IntegerConverter().convertToType(String.valueOf(Integer.MIN_VALUE)), is(Integer.MIN_VALUE));
		assertThat(new IntegerConverter().convertToType(String.valueOf(Integer.MAX_VALUE)), is(Integer.MAX_VALUE));
	}

	@Test
	public void testConvertToType_IllegalValue() {
		thrown.expect(TypeConversionException.class);
		thrown.expectCause(isA(NumberFormatException.class));
		thrown.expectMessage(containsString("154L"));

		new IntegerConverter().convertToType("154L");
	}

	@Test
	public void testConvertToType_OutOfBounds_Min() {
		thrown.expect(TypeConversionException.class);
		thrown.expectCause(isA(NumberFormatException.class));
		thrown.expectMessage(containsString(String.valueOf(Long.MIN_VALUE)));

		new IntegerConverter().convertToType(String.valueOf(Long.MIN_VALUE));
	}

	@Test
	public void testConvertToType_OutOfBounds_Max() {
		thrown.expect(TypeConversionException.class);
		thrown.expectCause(isA(NumberFormatException.class));
		thrown.expectMessage(containsString(String.valueOf(Long.MAX_VALUE)));

		new IntegerConverter().convertToType(String.valueOf(Long.MAX_VALUE));
	}

}
