package com.lpl.utils.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.lpl.utils.IDataUtil;
import com.opencsv.CSVReader;

/**
 * @author sparasha
 *
 */
public class FlatFileImpl implements IDataUtil {
	final HashMap<String, HashMap<String, HashMap<String, String>>> totalMap = new HashMap<>();
	HashMap<String, HashMap<String, String>> individualMap = new HashMap<>();
	HashMap<String, Integer> headerMap = new HashMap<>();
	HashMap<String, String> configMap = new HashMap<>();
	HashMap<String, String> credentialMap = new HashMap<>();
	HashMap<String, String> appFeatureMap = new HashMap<>();
	HashMap<String, String> appScenarioMap = new HashMap<>();
	HashMap<String, String> testDataMap = new HashMap<>();

	@Override
	public Map<String, String> getTestData(int intScriptID, int intEnvID) {
		Path fileName = Paths.get("C:\\Softwares\\TestData_Template.csv");

		try {
			Reader reader;
			reader = Files.newBufferedReader(fileName);
			CSVReader csvReader = new CSVReader(reader);
			String[] headerLines = csvReader.readNext();
			int i = 0;
			for (String headerLine : headerLines) {
				headerMap.put(headerLine, i++);
			}
			String[] nextRecord;
			int counter = 0;
			while ((nextRecord = csvReader.readNext()) != null) {
				String countertest = "counter" + counter;
				individualMap = new HashMap<>();
				configMap = new HashMap<>();
				credentialMap = new HashMap<>();
				appFeatureMap = new HashMap<>();
				appScenarioMap = new HashMap<>();
				testDataMap = new HashMap<>();
				for (Entry<String, Integer> headerEntrySet : headerMap.entrySet()) {

					String key = headerEntrySet.getKey();
					if (key.startsWith("Config")) {
						configMap.put(key.replaceFirst("Config:", ""), nextRecord[headerMap.get(key)]);
					} else if (key.startsWith("AppFeature")) {
						appFeatureMap.put(key.replaceFirst("AppFeature:", ""), nextRecord[headerMap.get(key)]);
					} else if (key.startsWith("Credential")) {
						credentialMap.put(key.replaceFirst("Credential:", ""), nextRecord[headerMap.get(key)]);
					} else if (key.startsWith("AppSenario")) {
						appScenarioMap.put(key.replaceFirst("AppSenario:", ""), nextRecord[headerMap.get(key)]);
					} else if (key.startsWith("TestData")) {
						testDataMap.put(key.replaceFirst("TestData:", ""), nextRecord[headerMap.get(key)]);
					}
				}
				individualMap.put("configMap", configMap);
				individualMap.put("appFeatureMap", appFeatureMap);
				individualMap.put("credentialMap", credentialMap);
				individualMap.put("appScenarioMap", appScenarioMap);
				individualMap.put("testDataMap", testDataMap);
				setTotalMap(countertest, individualMap);
				counter++;
			}
			csvReader.close();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();

		} 

		return null;
	}

	public void setIndividualMap(String mapName, Map<String, String> map) {
		individualMap.put(mapName, (HashMap<String, String>) map);

	}

	public void setTotalMap(String countertest, Map<String, HashMap<String, String>> atm) {
		totalMap.put(countertest, (HashMap<String, HashMap<String, String>>) atm);
	}

	@Override
	public void getLoginCredential() {
		// ToDO implemented later.
	}

	@Override
	public void getObjects() {

		for (Entry<String, HashMap<String, HashMap<String, String>>> totalEntrySet : totalMap.entrySet()) {
			String count = totalEntrySet.getKey();
			System.out.println("count"+count);
			HashMap<String, HashMap<String, String>> individualTestMap;
			individualTestMap = totalMap.get(count);
			System.out.println("individualTestMap"+individualTestMap.toString());
			for (Entry<String, HashMap<String, String>> mapNameEntrySet : individualTestMap.entrySet()) {
				String mapName = mapNameEntrySet.getKey();
				HashMap<String, String> mapforEach;
				mapforEach = individualTestMap.get(mapName);
				for (Entry<String, String> individualEntrySet : mapforEach.entrySet()) {
					String key = individualEntrySet.getKey();
					String value = mapforEach.get(key);
					//System.out.println("key"+key+"value"+value);
				}
			}
		}

	}

}
