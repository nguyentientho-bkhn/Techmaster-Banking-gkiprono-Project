package tests.java.services;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class MainMenuTester {
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
	
	@Test
	public void testContextMenu() {
		
	}
	
}
