package com.lpl.testdata;

import java.util.Map;

import com.lpl.utils.DBFactory;
import com.lpl.utils.IDataUtil;

/**
 * @author sparasha
 *
 */
public class TestData {
	String testDataType;

	public String getTestDataType() {
		return testDataType;
	}

	public void setTestDataType(String testDataType) {
		this.testDataType = testDataType;
	}


	public static Map<String, String> getTestDataFromDB(int intScriptID, int intEnvID) {
		/**
		 * Create the HashMap to store the test data key and test data value Example:
		 * Key will be "strAccountNo" and Value will be "1111-1111"
		 */

		IDataUtil dbutil = DBFactory.getWorker("mysql");
		return dbutil.getTestData(intScriptID, intEnvID);

	}

}
