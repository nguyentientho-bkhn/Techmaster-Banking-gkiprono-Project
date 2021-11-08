package tests.java.services;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.lang.NullPointerException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.kiprono.controllers.SignUp;
import com.kiprono.models.*;


public class SignUpTester {
	private static SignUp signup = new SignUp();
	
	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println("Runs once before alls test.");
	}
	
	@Before
	public void setUpBeforeEachTest() {
		System.out.println("Runs before each test.");
	}
	
	@After
	public void tearDownAfterEachTest() {
		System.out.println("Runs after each test.");
	}
	
	@AfterClass
	public static void TearDownAfterClass() {
		System.out.println("Runs once after alls test.");
	}
	
	@Test // instance of integer class
	public void testGenerateAccount() {
		assertTrue(Integer.class.isInstance(SignUp.generateAccountId()));
	}
	
	@Test // add customer return object of customers class
	public void testAddCustomer() {
		//assertTrue(Customers.class.isInstance(SignUp.addCustomer()));
	}
	
	@Test // returns int
	public void testGenerateAccountNumber() {
		assertTrue(Integer.class.isInstance(SignUp.generateAccountNumber()));
	}
	
	@Test // return int
	public void testGenerateUserId() {
		assertTrue(Integer.class.isInstance(SignUp.generateUserId()));
	}
	
	@Test(expected = NullPointerException.class) //return string fails intentionally
	public void testGenerateUserName() {
		assertTrue(String.class.isInstance(SignUp.userNameGenerator()));
	}
	
	
}
