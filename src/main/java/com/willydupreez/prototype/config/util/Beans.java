package com.willydupreez.prototype.config.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Bean utilities.
 *
 * @author Willy du Preez
 *
 */
public final class Beans {

	private Beans() {
	}

	public static boolean isReadable(PropertyDescriptor descriptor) {
		return descriptor.getReadMethod() != null;
	}

	public static boolean isWritable(PropertyDescriptor descriptor) {
		return descriptor.getWriteMethod() != null;
	}

	public static List<PropertyDescriptor> descriptors(Class<?> propertiesBeanType) throws Exception {
		BeanInfo propertiesBeanInfo = Introspector.getBeanInfo(propertiesBeanType);
		return Arrays.asList(propertiesBeanInfo.getPropertyDescriptors()).stream()
				.filter(descriptor -> isReadable(descriptor))
				.filter(descriptor -> isWritable(descriptor))
				.collect(Collectors.toList());
	}

}
