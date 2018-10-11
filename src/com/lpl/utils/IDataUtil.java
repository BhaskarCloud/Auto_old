package com.lpl.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sparasha
 *
 */
public interface IDataUtil {

	public Map<String, String> getTestData(int intScriptID, int intEnvID);

	public void getLoginCredential();

	public void getObjects();

}
