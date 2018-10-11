package com.lpl.utils.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.lpl.config.ConfigProperties;
import com.lpl.utils.IDataUtil;
import com.lpl.utils.Util;

public class MySqlImpl implements IDataUtil {

	String mySQLDriver = Util.MySQLDriver;
	String FARMDBConnectionString = Util.FARMDBConnectionString;
	private static Connection mySQLConnection = null;

	public MySqlImpl() {
		if (mySQLConnection == null) {

			getConnection();
		}
	}

	private synchronized Connection getConnection() {
		// Load the MySQL driver
		try {
			Class.forName(mySQLDriver);
		} catch (ClassNotFoundException e) {
			System.out.println("Mysql driver not found");
			e.printStackTrace();
		}

		// Create the MySQL Connection
		try {
			mySQLConnection = DriverManager.getConnection(FARMDBConnectionString);
			return mySQLConnection;
		} catch (SQLException e) {
			System.err.println("Mysql Connection not found");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public HashMap<String, String> getTestData(int intScriptID, int intEnvID) {
		HashMap<String, String> testDataMap = new HashMap<>();

		CallableStatement callableStatement = null;
		ResultSet testDataResultSet = null;
		String TestDataStoredProcedure = "";
		// Create the CallableStatement to execute the Stored Procedure
		try {
			callableStatement = mySQLConnection.prepareCall("{call " + TestDataStoredProcedure + "(?,?)}");
		} catch (SQLException e) {
			System.out.println("Sql Exception in Creating the CallableStatement");
			e.printStackTrace();
		}

		// Set the input data to execute the Stored Procedure
		try {
			callableStatement.setInt(1, intEnvID);
			callableStatement.setInt(2, intScriptID);
		} catch (SQLException e) {
			System.out.println("Sql Exception while setting the input data to execute the Stored Procedure");
			e.printStackTrace();
		}

		// Execute the Stored Procedure to get the ResultSet
		try {
			testDataResultSet = callableStatement.executeQuery();
		} catch (SQLException e) {
			System.out.println("Sql Exception while executing the Stored Procedure to get the ResultSet");
			e.printStackTrace();
		}

		// Validate if it is null
		if (testDataResultSet != null) {
			System.out.println("Successfully Fetched Test Data");

			// Iterate through the ResultSet and fetch all the test data
			try {
				while (testDataResultSet.next()) {
					// Add the Test Data Key and Value in HashMap in form of Key,Value pair.
					testDataMap.put(testDataResultSet.getString(2), testDataResultSet.getString(3));
				}
			} catch (SQLException e) {
				System.out.println("Exception while iterating through the ResultSet and fetch all the test data");
				e.printStackTrace();
			}
		} else
			System.out.println("Failed To Fetch Test Data");

		// Closing all the DB instances.
		try {
			testDataResultSet.close();
			callableStatement.close();
			mySQLConnection.close();
		} catch (SQLException e) {
			System.out.println("Exception while closing the call");
			e.printStackTrace();
		}

		return testDataMap;
	}

	/**
	 * Method to get the login credentials from FARM Database
	 * 
	 * @author Ambarish Khatua
	 * @since 06-09-2016
	 * @param int intScriptID - Script Id, int intFirmID - Firm Id, int intProductID
	 *        - Product Id, int intEnvID - Environment Id
	 * @return HashMap<String, String> - Key,Value pair of Username and Password
	 *         where key will "Username" and "Password" respectively
	 */
	@SuppressWarnings("unused")
	public static HashMap<String, String> getLoginCredential(int intScriptID, int intFirmID, int intProductID,
			int intEnvID, String strCryptoKey, String strEncryptionFlag) {
		/**
		 * Create the HashMap to store the Username and Password Example: Key will be
		 * "Username" and Value will be "test.account" Key will be "Password" and Value
		 * will be "P@ssw0rd"
		 */
		HashMap<String, String> loginDataMap = new HashMap<String, String>();

		// Firm Name
		String strFirm;

		// Environment Name
		String strEnvironment;

		// UserName
		String strPassword;

		// Password
		String strUserName;

		try {
			// Fetch the Firm & Environment details from Config file
			ConfigProperties config = new ConfigProperties("");
			strFirm = config.getFirm();
			strEnvironment = config.getEnvironment();

			CallableStatement callableStatement = null;
			ResultSet loginResultSet = null;
			if (strEncryptionFlag.equals("YES")) {
				// Create the CallableStatement to execute the Login Data Stored Procedure
				callableStatement = mySQLConnection
						.prepareCall("{call " + Util.LogInCredentialStoredProcedure + "(?,?,?,?,?)}");

				// Set the input data to execute the Stored Procedure
				callableStatement.setInt(1, intScriptID);
				callableStatement.setInt(2, intFirmID);
				callableStatement.setInt(3, intProductID);
				callableStatement.setInt(4, intEnvID);
				callableStatement.setString(5, strCryptoKey);
			} else {
				// Create the CallableStatement to execute the Login Data Stored Procedure
				callableStatement = mySQLConnection
						.prepareCall("{call " + Util.LogInCredentialStoredProcedure + "(?,?,?,?)}");

				// Set the input data to execute the Stored Procedure
				callableStatement.setInt(1, intScriptID);
				callableStatement.setInt(2, intFirmID);
				callableStatement.setInt(3, intProductID);
				callableStatement.setInt(4, intEnvID);
			}
			// Execute the Stored Procedure to get the ResultSet
			loginResultSet = callableStatement.executeQuery();

			// Validate if it is null
			if (loginResultSet != null) {
				System.out.println("Successfully Fetched LogIn Data");

				loginResultSet.next();

				// Add the Username form of Key,Value pair.
				strUserName = loginResultSet.getString(1);
				loginDataMap.put("Username", strUserName);

			
				strPassword = loginResultSet.getString(2);

				// Add the Password form of Key,Value pair.
				loginDataMap.put("Password", strPassword);
			} else
				System.out.println("Failed To Fetch LogIn Data");

			// Closing all the DB instances.
			loginResultSet.close();
			callableStatement.close();
			mySQLConnection.close();

			// Return login data
			return loginDataMap;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return loginDataMap;
		}
	}

	/**
	 * Method to get the object identification properties from FARM Database
	 * 
	 * @author Ambarish Khatua
	 * @since 06-23-2016
	 * @param int intPageID - Page Id, int intEnvID - Environment Id
	 * @return HashMap<String, HashMap<String, String>> - Page Objects where object
	 *         name as Key and value as another HashMap containing object
	 *         identification properties.
	 */
	public HashMap<String, HashMap<String, String>> getObjects(int intPageID, int intEnvID) {
		/**
		 * Create HashMap to store object name as Key and value as another HashMap
		 * containing object identification properties. Example: Key will be
		 * "AccountSearchButton" and Value will be {"ID","text-search-button"}
		 */
		HashMap<String, HashMap<String, String>> PageObjectsMap = new HashMap<String, HashMap<String, String>>();

		try {

			CallableStatement callableStatement = null;
			ResultSet testDataResultSet = null;

			// Create the CallableStatement to execute the Stored Procedure to fetch Page
			// Objects from FARM Database
			callableStatement = mySQLConnection.prepareCall("{call " + Util.PageObjectStoredProcedure + "(?,?)}");

			// Set the input data to execute the Stored Procedure
			callableStatement.setInt(1, intPageID);
			callableStatement.setInt(2, intEnvID);

			// Execute the Stored Procedure to get the ResultSet
			testDataResultSet = callableStatement.executeQuery();

			// Validate if it is null
			if (testDataResultSet != null) {
				System.out.println("Successfully Fetched Page Objects");

				// Iterate through the ResultSet and fetch all the Page Objects
				while (testDataResultSet.next()) {
					/**
					 * Create HashMap to store object identification properties. Example: Key will
					 * be "ID"/"XPATH" etc and Value will be ID/XPATh etc locator
					 */
					HashMap<String, String> pageObjects = new HashMap<String, String>();
					pageObjects.put("ID", testDataResultSet.getString(Util.intIDIndex));
					pageObjects.put("CSS", testDataResultSet.getString(Util.intCSSIndex));
					pageObjects.put("XPATH", testDataResultSet.getString(Util.intXPATHIndex));
					pageObjects.put("XCORD", testDataResultSet.getString(Util.intXCORDIndex));
					pageObjects.put("YCORD", testDataResultSet.getString(Util.intYCORDIndex));
					pageObjects.put("ANGULARREF", testDataResultSet.getString(Util.intAngularRefIndex));
					pageObjects.put("ANGULARLOCATORTYPE", testDataResultSet.getString(Util.intAngularLocatorTypeIndex));

					// Add the Page Object Name as Key and Locators as Value in HashMap
					PageObjectsMap.put(testDataResultSet.getString(1), pageObjects);
				}
			} else
				System.out.println("Failed To Fetch Page Objects");

			// Closing all the DB instances.
			testDataResultSet.close();
			callableStatement.close();
			mySQLConnection.close();

			return PageObjectsMap;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return PageObjectsMap;
		}
	}

	@Override
	public void getLoginCredential() {
		int intScriptID = 0;
		int intFirmID = 0;
		int intProductID = 0;
		int intEnvID = 0;
		String strCryptoKey = null;
		String strEncryptionFlag = null;
		getLoginCredential(intScriptID, intFirmID, intProductID, intEnvID, strCryptoKey, strEncryptionFlag);

	}

	@Override
	public void getObjects() {

		int intPageID = 0;
		int intEnvID = 0;
		getObjects(intPageID, intEnvID);
	}

}
