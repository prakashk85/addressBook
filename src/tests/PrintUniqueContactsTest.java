package tests;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import com.prakash.addressbook.AddressUtility;
import com.prakash.addressbook.bean.User;

public class PrintUniqueContactsTest {

	private static AddressUtility aUtility = new AddressUtility();
	private static User user;

	@Before
	public void setup() {
		String userName = "Prakash";

		String addressBookName1 = "Book1";
		String contactName10 = "Prakash Kalaiselven";
		String contactPhone10 = "+61 469 123 456";
		String contactName11 = "Prakash1";
		String contactPhone11 = "+61 000 000 000";

		String addressBookName3 = "Book3";
		String contactName30 = "Prakash2";
		String contactPhone30 = "+61 469 123 456";
		String contactName31 = "Prakash1";
		String contactPhone31 = "+61 000 000 000";

		user = aUtility.createUser(userName);

		aUtility.addContact(user, addressBookName1, true, contactName10, contactPhone10);
		aUtility.addContact(user, addressBookName1, true, contactName11, contactPhone11);

		aUtility.addContact(user, addressBookName3, true, contactName30, contactPhone30);
		aUtility.addContact(user, addressBookName3, true, contactName31, contactPhone31);
	}

	private void addExtra(String name21) {
		String addressBookName2 = "Book2";
		String contactName21 = name21;
		String contactPhone21 = "+61 000 000 000";
		String contactName20 = "Prakash2";
		String contactPhone20 = "+61 469 123 456";

		aUtility.addContact(user, addressBookName2, true, contactName20, contactPhone20);
		aUtility.addContact(user, addressBookName2, true, contactName21, contactPhone21);
	}

	@Test
	public void printUniqueContacts() {
		addExtra("Prakash1");
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

		System.setOut(new PrintStream(outContent));

		aUtility.printUniqueContacts(user);

		String check = "Printing unique set of contacts (name & phone number both) across all address books:-"
				+ System.getProperty("line.separator") + "Contact name=Prakash1, phone=+61 000 000 000"
				+ System.getProperty("line.separator");
		assertThat(check, is(outContent.toString()));
	}

	@Test
	public void printNoUniqueContacts() {
		addExtra("Prakash3");
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

		System.setOut(new PrintStream(outContent));

		aUtility.printUniqueContacts(user);

		String check = "Printing unique set of contacts (name & phone number both) across all address books:-"
				+ System.getProperty("line.separator") + "No such contacts found!"
				+ System.getProperty("line.separator");
		assertThat(check, is(outContent.toString()));
	}
}
