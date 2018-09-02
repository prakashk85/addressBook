package com.prakash.addressbook;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.prakash.addressbook.bean.AddressBook;
import com.prakash.addressbook.bean.Contact;
import com.prakash.addressbook.bean.User;
import com.prakash.addressbook.util.Util;

public class AddressUtility {

	public static void main(String[] args) {
		AddressUtility myObj = new AddressUtility();
		User user = myObj.createUser("Prakash");
		if (null == user) {
			System.out.println("User name cannot be empty!");
		} else {
			String abook1 = "book1";
			String abook2 = "book2";
			String abook3 = "book3";
			String message = myObj.createAddressBook(user, abook1);
			printMessage(message);
			if (null == message) {

				myObj.addContact(user, abook1, true, "Prakash Kalaiselven", "+61 123 456 789");
				printMessage(message);
				myObj.addContact(user, abook1, true, "Suganya G", "+61 123 456 789");
				printMessage(message);
				myObj.addContact(user, abook1, true, "Ariadne Prakash", "+61 123 456 789");
				printMessage(message);

				myObj.addContact(user, abook2, true, "Ariadne Prakash", "+61 123 456 789");
				printMessage(message);
				myObj.addContact(user, abook2, true, "Ariadne PK", "+61 123 456 789");
				printMessage(message);

				myObj.addContact(user, abook3, true, "Ariadne Prakash", "+61 123 456 789");
				printMessage(message);
				myObj.addContact(user, abook3, true, "Suganya Prakash", "+61 123 456 789");
				printMessage(message);

				myObj.deleteContactByName(user, abook1, "Prakash Kalaiselvan");
				printMessage(message);

				myObj.printAllContacts(user, abook1);
			}
			myObj.printUniqueContacts(user);
		}
	}

	public static void printMessage(String message) {
		if (null != message) {
			System.out.println(message);
		}
	}

	public User createUser(String userName) {
		if (Util.isEmptyString(userName)) {
			return null;
		}
		return new User(userName);
	}

	public String createAddressBook(User user, String aBookName) {
		if (null == user) {
			return "User cannot be empty!";
		}
		user.addNewAddressBook(aBookName);
		return null;
	}

	public String addContact(User user, String aBookName, boolean createIfABNotFound, String contactName,
			String contactPhone) {
		StringBuffer message = new StringBuffer();
		// Input Validations
		if (Util.isEmptyString(contactName)) {
			message.append("Contact name cannot be empty!\n");
		}
		if (Util.isEmptyString(contactPhone)) {
			message.append("Contact phone number cannot be empty!\n");
		}
		if (Util.isEmptyString(aBookName)) {
			message.append("Address book name cannot be empty!\n");
		}
		if (message.length() > 0) {
			return message.toString();
		}

		// address book validation
		if (!createIfABNotFound && (null == user.getAddressBooks() || !user.getAddressBooks().containsKey(aBookName))) {
			return message.append("Given address book (").append(aBookName).append(") not found!").toString();
		}

		Contact contact = new Contact(contactName, contactPhone);

		if (null == user.getAddressBooks() || !user.getAddressBooks().containsKey(aBookName)) {
			createAddressBook(user, aBookName);
		}

		return user.getAddressBook(aBookName).addContact(contact);
	}

	public String deleteContactByName(User user, String aBookName, String contactName) {
		StringBuffer message = new StringBuffer();
		// Input Validations
		if (null == user) {
			message.append("User cannot be empty!");
		}
		if (Util.isEmptyString(contactName)) {
			return "Contact name cannot be empty!";
		}
		if (message.length() > 0) {
			return message.toString();
		}

		AddressBook abook = user.getAddressBook(aBookName);

		if (null == abook) {
			return "No valid address book found for given name!";
		} else {

			if (0 == abook.getContacts().stream().filter(contact -> contactName.equals(contact.getName())).count()) {
				return "No contact found for given name!";
			}

			abook.getContacts().removeIf(contact -> contactName.equals(contact.getName()));
		}

		return null;
	}

	public void printAllContacts(User user, String aBookName) {

		if (null == user) {
			System.out.println("User cannot be empty!");
		} else if (null == user.getAddressBooks()) {
			System.out.println("User does not have any address book!");
		} else {
			AddressBook abook = user.getAddressBooks().get(aBookName);

			if (null == abook) {
				System.out.println("No valid address book found for given name!");
			} else if (null == abook.getContacts() || 0 == abook.getContacts().size()) {
				System.out.println("No contacts found for given address book!");
			} else {
				System.out.println("Printing All Contacts from address book with name - " + aBookName + ", for user - "
						+ user.getUserName() + ":-");
				abook.getContacts().forEach(System.out::println);
			}

		}

	}

	public void printUniqueContacts(User user) {
		Iterator<AddressBook> iter = user.getAddressBooks().values().iterator();

		if (iter.hasNext()) {
			Set<Contact> contacts = new HashSet<>(iter.next().getContacts());

			System.out.println("Printing unique set of contacts (name & phone number both) across all address books:-");

			while (iter.hasNext()) {
				contacts.retainAll(iter.next().getContacts());
			}

			if (contacts.size() > 0) {
				contacts.forEach(System.out::println);
			} else {
				System.out.println("No such contacts found!");
			}

		}
	}

}