package tests;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.prakash.addressbook.AddressUtility;
import com.prakash.addressbook.bean.User;

public class DeleteContactTest {
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
	public void deleteContact() {
		aUtility.addContact(user, addressBookName, true, contactName, contactPhone);
		aUtility.deleteContactByName(user, addressBookName, contactName);
		assertEquals(null, user.getAddressBook(addressBookName).getContactByName(contactName));
	}

	@Test
	public void deleteContactInvalidBookName() {
		String result = aUtility.deleteContactByName(user, "testbook", contactName);
		assertThat("No valid address book found for given name!", is(result));
	}

	@Test
	public void deleteContactInvalidContactName() {
		aUtility.addContact(user, addressBookName, true, contactName, contactPhone);
		String result = aUtility.deleteContactByName(user, addressBookName, "testContactName");
		assertThat("No contact found for given name!", is(result));
	}

	@Test
	public void deleteContactEmptyContactName() {
		String result = aUtility.deleteContactByName(user, addressBookName, "");
		assertThat("Contact name cannot be empty!", is(result));
	}
}
