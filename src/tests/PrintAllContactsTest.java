package tests;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import com.prakash.addressbook.AddressUtility;
import com.prakash.addressbook.bean.User;

public class PrintAllContactsTest {
	private static String userName = "Prakash";
	private static String addressBookName = "Book1";
	private static String contactName = "Prakash Kalaiselven";
	private static String contactPhone = "+61 469 123 456";
	private String contactName1 = "Prakash1";
	private String contactPhone1 = "+61 000 000 000";

	private static AddressUtility aUtility = new AddressUtility();
	private static User user;

	@Before
	public void createUser() {
		user = aUtility.createUser(userName);
	}

	@Test
	public void printAllContacts() {
		aUtility.addContact(user, addressBookName, true, contactName, contactPhone);
		aUtility.addContact(user, addressBookName, true, contactName1, contactPhone1);

		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

		System.setOut(new PrintStream(outContent));
		aUtility.printAllContacts(user, addressBookName);

		String check = "Printing All Contacts from address book with name - Book1, for user - Prakash:-"
				+ System.getProperty("line.separator") + "Contact name=Prakash Kalaiselven, phone=+61 469 123 456"
				+ System.getProperty("line.separator") + "Contact name=Prakash1, phone=+61 000 000 000"
				+ System.getProperty("line.separator");
		assertThat(check, is(outContent.toString()));
	}

	@Test
	public void printAllContactsEmptyUser() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

		System.setOut(new PrintStream(outContent));
		aUtility.printAllContacts(null, addressBookName);

		String check = "User cannot be empty!" + System.getProperty("line.separator");
		assertThat(check, is(outContent.toString()));
	}

	@Test
	public void printAllContactsEmptyAddressBook() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

		System.setOut(new PrintStream(outContent));
		aUtility.printAllContacts(new User(), addressBookName);

		String check = "User does not have any address book!" + System.getProperty("line.separator");
		assertThat(check, is(outContent.toString()));
	}

	@Test
	public void printAllContactsInvalidBookName() {
		aUtility.addContact(user, addressBookName, true, contactName, contactPhone);
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

		System.setOut(new PrintStream(outContent));
		aUtility.printAllContacts(user, "testBook");

		String check = "No valid address book found for given name!" + System.getProperty("line.separator");
		assertThat(check, is(outContent.toString()));
	}

	@Test
	public void printAllContactsEmptyBook() {
		aUtility.createAddressBook(user, addressBookName);
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

		System.setOut(new PrintStream(outContent));
		aUtility.printAllContacts(user, addressBookName);

		String check = "No contacts found for given address book!" + System.getProperty("line.separator");
		assertThat(check, is(outContent.toString()));
	}
}
