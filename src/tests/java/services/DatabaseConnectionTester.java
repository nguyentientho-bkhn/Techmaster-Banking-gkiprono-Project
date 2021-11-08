package tests.java.services;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.kiprono.utils.DatabaseConnection;


public class DatabaseConnectionTester {
	private static Connection conn;
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
	
	// test getConnection not null
	@Test
	public void ConnectionTest() {
		assertNotEquals(conn, DatabaseConnection.getConnection());
	}
	
	// assert theyre of the same class
	@Test
	public void testType() {
		assertTrue(Connection.class.isInstance(DatabaseConnection.getConnection()));
	}
}
