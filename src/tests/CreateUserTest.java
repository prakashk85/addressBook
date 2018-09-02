package tests;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.prakash.addressbook.AddressUtility;
import com.prakash.addressbook.bean.User;

public class CreateUserTest {
	private static AddressUtility aUtility = new AddressUtility();

	@Test
	public void createNewUser() {
		String uName = "Prakash";
		User user = aUtility.createUser(uName);
		assertThat(uName, is(user.getUserName()));
	}

	@Test(expected = NullPointerException.class)
	public void CreateNullUser() {
		String uName = "";
		User user = aUtility.createUser(uName);
		assertThat(uName, is(user.getUserName()));
	}
}