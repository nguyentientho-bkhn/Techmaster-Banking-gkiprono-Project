package com.kiprono.daos;

import java.sql.Connection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kiprono.models.Customers;
import com.kiprono.utils.DatabaseConnection;
import com.kiprono.utils.EncryptDecrypt;
import com.kiprono.utils.PasswordUtils;

public class LoginDA {
	private static final Logger LOG = LogManager.getLogger(TransactionsDAImpl.class);
    private PreparedStatement statement;
    private Connection connection;
    ResultSet resultSet;
    private EncryptDecrypt encryption = EncryptDecrypt.getEncryptor();

    // query database for customer with given username and password
    // get username annd password from database
    // if username and password match, return customer object
    // else return null
    public Customers finCustomer(String uname){
        Customers customer = new Customers();
        String query = "SELECT * FROM customers WHERE username = ?";
        LOG.trace(String.valueOf(System.currentTimeMillis()) + ": Customer accessed");
        connection = DatabaseConnection.getConnection();
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, uname);
            resultSet = statement.executeQuery();
            resultSet.next();
            customer.setUserId(resultSet.getInt("userid"));
            customer.setUserName(resultSet.getString("username"));
            customer.setPasswd(resultSet.getString("passwd"));
            customer.setFirstName(resultSet.getString("firstname"));
            customer.setMiddleInitial(resultSet.getString("middleinitial"));
            customer.setLastName(resultSet.getString("lastname"));
            customer.setPhoneNumber(resultSet.getString("phonenumber"));
            customer.setAddress(resultSet.getString("address"));
            customer.setCity(resultSet.getString("city"));
            customer.setState(resultSet.getString("state"));
            customer.setZipCode(resultSet.getInt("zipcode"));
            customer.setAccountNumber(resultSet.getInt("accountnumber"));
            customer.setKey(resultSet.getString("secret_key"));
            
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Wrong User id");
            LOG.error(String.valueOf(System.currentTimeMillis()) + e.getMessage());
        } finally{
            closeResources();
        }
        
        return customer;        
    }

    // update customer password
    public void updateCustomer(Customers customer) {
    	LOG.trace(String.valueOf(System.currentTimeMillis()) + ": Customer updated");
        String query = "UPDATE customers SET passwd = ? WHERE username = ?";
        connection = DatabaseConnection.getConnection();
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, customer.getPasswd());
            statement.setString(2, customer.getUserName());
            statement.executeUpdate();
        } catch (SQLException e) {
        	LOG.error(String.valueOf(System.currentTimeMillis()) + e.getMessage());
        } finally {
            closeResources();
        }
    }

    private void closeResources() {
        try {
            if (connection != null && !statement.isClosed()) {
                connection.close();
                statement.close();
            }
        } catch (SQLException e) {
        	LOG.error(String.valueOf(System.currentTimeMillis()) + e.getMessage());
        }
    }
    
    // create main method to test the class
    public static void main(String[] args) {
        LoginDA loginDA = new LoginDA();
        Customers customer = loginDA.finCustomer("JohnAForsyth");
        System.out.println(customer.getUserName());
        customer.setPasswd("newPass");
        loginDA.updateCustomer(customer);
    }

}
