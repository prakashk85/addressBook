package com.prakash.addressbook.bean;

import java.util.HashMap;
import java.util.Map;

import com.prakash.addressbook.util.Util;

public class User {
	String userName;
	Map<String, AddressBook> addressBooks;

	public User() {
		super();
	}

	public User(String userName) {
		super();
		this.userName = userName;
	}

	public Map<String, AddressBook> getAddressBooks() {
		return addressBooks;
	}

	public void setAddressBooks(Map<String, AddressBook> addressBooks) {
		this.addressBooks = addressBooks;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @param abook
	 * @return addressBook element from list of address books for this user.
	 *         Null in case no address book is found
	 */
	public AddressBook getAddressBook(String abook) {

		if (null != addressBooks) {
			return addressBooks.get(abook);
		}

		return null;
	}

	/**
	 * Creates and adds a new AddressBook object to addressBooks list
	 * 
	 * @param aBookName
	 * @return validation message as String or Null in case address book is
	 *         successfully created
	 */
	public String addNewAddressBook(String aBookName) {

		if (Util.isEmptyString(aBookName)) {
			return "Address Book name cannot be empty!";
		}

		if (null == addressBooks) {
			addressBooks = new HashMap<>();
		} else if (addressBooks.containsKey(aBookName)) {
			return "Address Book with name " + aBookName + " already exists!";
		}

		addressBooks.put(aBookName, new AddressBook());

		return null;
	}
}