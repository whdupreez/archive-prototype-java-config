package com.willydupreez.prototype.config.util;

import java.beans.PropertyDescriptor;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BeansTest {

	public class Bean {

		private int propOne;

		public int getPropOne() {
			return propOne;
		}

		public void setPropOne(int propOne) {
			this.propOne = propOne;
		}

	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void test() throws Exception {
		List<PropertyDescriptor> descriptors = Beans.descriptors(Bean.class);
		assertThat(descriptors.size(), is(1));
	}

	@Test
	public void testReadable() throws Exception {
		Beans.isReadable(Beans.descriptors(Bean.class).get(0));
	}

	@Test
	public void testWritable() throws Exception {
		Beans.isWritable(Beans.descriptors(Bean.class).get(0));
	}

}
