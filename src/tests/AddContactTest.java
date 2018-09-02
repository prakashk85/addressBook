package tests;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.prakash.addressbook.AddressUtility;
import com.prakash.addressbook.bean.User;

public class AddContactTest {
	private static String userName = "Prakash";
	private static String addressBookName = "Book1";
	private static String contactName = "Prakash Kalaiselven";
	private static String contactPhone = "+61 469 123 456";

	private static AddressUtility aUtility = new AddressUtility();
	private static User user;

	@Before
	public void createUser() {
		user = aUtility.createUser(userName);
	}

	@Test
	public void addContact() {
		aUtility.addContact(user, addressBookName, true, contactName, contactPhone);
		assertThat(contactName, is(user.getAddressBook(addressBookName).getContactByName(contactName).getName()));
	}

	@Test
	public void addContactToNewBook() {
		String result = aUtility.addContact(user, addressBookName, false, contactName, contactPhone);
		assertThat("Given address book (" + addressBookName + ") not found!", is(result));
	}

	@Test
	public void addContact1() {
		String result = aUtility.addContact(user, addressBookName, true, contactName, contactPhone);
		assertThat("Given address book (" + addressBookName + ") not found!", not(result));
	}

	@Test
	public void addContactEmptyName() {
		String result = aUtility.addContact(user, addressBookName, false, "", contactPhone);
		assertThat("Contact name cannot be empty!\n", is(result));
	}

	@Test
	public void addContactEmptyPhone() {
		String result = aUtility.addContact(user, addressBookName, false, contactName, "");
		assertThat("Contact phone number cannot be empty!\n", is(result));
	}

	@Test
	public void addContactEmptyBookName() {
		String result = aUtility.addContact(user, "", false, contactName, contactPhone);
		assertThat("Address book name cannot be empty!\n", is(result));
	}

	@Test
	public void addContactTwice() {
		aUtility.addContact(user, addressBookName, true, contactName, contactPhone);
		String result = aUtility.addContact(user, addressBookName, true, contactName, contactPhone);
		assertThat("Contact is already found in given address book!", is(result));
	}
}