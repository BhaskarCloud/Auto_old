package com.lpl.utils;

import com.lpl.utils.impl.MySqlImpl;

/**
 * @author sparasha
 *
 */
public class DBFactory {

	public static IDataUtil getWorker(String worker) {
		if ("mysql".equalsIgnoreCase(worker))
			return new MySqlImpl();
		return null;
	}

}
