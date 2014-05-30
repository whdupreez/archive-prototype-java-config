package com.willydupreez.prototype.config.util;

import static com.willydupreez.prototype.config.util.LetterCases.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class LetterCasesTest {

	private static final String SIMPLE_CAMEL_CASE 				= "simpleCamelCase";
	private static final String SIMPLE_DOT_NOTATION 			= "simple.camel.case";
	private static final String SIMPLE_SNAKE_CASE 				= "simple_camel_case";
	private static final String SIMPLE_TRAIN_CASE 				= "simple-camel-case";

	@Test
	public void testDotted() {
		assertThat(camelToDotNotation(SIMPLE_CAMEL_CASE), is(SIMPLE_DOT_NOTATION));
	}

}
