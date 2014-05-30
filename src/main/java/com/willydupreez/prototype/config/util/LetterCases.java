package com.willydupreez.prototype.config.util;

/**
 * Letter case utilities.
 *
 * @author Willy du Preez
 *
 */
public final class LetterCases {

	/**
	 * Converts from camel case to dot notation.
	 *
	 * @param camelCase String in camel case
	 * @return String converted to dot notation
	 */
	public static String camelToDotNotation(String camelCase) {
		return camelCase.replaceAll("(.)([A-Z])", "$1.$2").toLowerCase();
	}

	private LetterCases() {
	}

}
