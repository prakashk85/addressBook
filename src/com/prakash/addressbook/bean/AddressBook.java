package com.prakash.addressbook.bean;

import java.util.HashSet;
import java.util.Set;

public class AddressBook {
	Set<Contact> contacts;

	public Set<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

	/**
	 * Adds contact object to contacts list
	 * 
	 * @param contact
	 * @return validation message as String or Null in case contact is
	 *         successfully added
	 */
	public String addContact(Contact contact) {

		if (null == contact) {
			return "Contact cannot be empty!";
		}

		if (null == contacts) {
			contacts = new HashSet<>();
		}

		boolean result = contacts.add(contact);
		
		if(!result){
			return "Contact is already found in given address book!";
		}

		return null;
	}

	public Contact getContactByName(String contactName) {
		if (null != contacts && null != contactName) {
			return contacts.stream().filter(contact -> contactName.equals(contact.getName())).findFirst().orElse(null);
		}
		return null;
	}
}