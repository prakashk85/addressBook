package com.prakash.addressbook.util;

public class Util {

	/**
	 * Checks if given string is not empty
	 * 
	 * @param str
	 * @return true if given string is not null & not empty, false if its either
	 *         empty or null
	 */
	public static boolean isEmptyString(String str) {
		if (null == str || str.trim().equalsIgnoreCase("")) {
			return true;
		}
		return false;
	}

}