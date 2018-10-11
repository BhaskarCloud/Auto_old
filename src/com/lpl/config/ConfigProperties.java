package com.lpl.config;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author sparasha
 *
 */
public class ConfigProperties {

	private String application;
	private String firm;
	private String environment;
	private String uRL;
	private String VIP;
	private String executionLocation;
	private String browserType;
	private String browserVersion;
	private String oS;
	private String device;
	private String testFrameworkType;
	private String user;
	private String pwd;
	private String dynatraceFlag;
	private int firmId;
	private int productId;
	private int environmentId;
	private int scriptId;
	private int jobID;
	private int labID;
	private int labGroupID;
	

	public int getFirmId() {
		return firmId;
	}

	public void setFirmId(int firmId) {
		this.firmId = firmId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getEnvironmentId() {
		return environmentId;
	}

	public void setEnvironmentId(int environmentId) {
		this.environmentId = environmentId;
	}

	public int getScriptId() {
		return scriptId;
	}

	public void setScriptId(int scriptId) {
		this.scriptId = scriptId;
	}

	public int getJobID() {
		return jobID;
	}

	public void setJobID(int jobID) {
		this.jobID = jobID;
	}

	public int getLabID() {
		return labID;
	}

	public void setLabID(int labID) {
		this.labID = labID;
	}

	public int getLabGroupID() {
		return labGroupID;
	}

	public void setLabGroupID(int labGroupID) {
		this.labGroupID = labGroupID;
	}

	public String getUserDirectory() {
		return userDirectory;
	}

	public void setUserDirectory(String user_dir) {
		this.userDirectory = user_dir;
	}



	public String getURL() {
		return uRL;
	}

	public void setURL(String uRL) {
		this.uRL = uRL;
	}

	public String getVIP() {
		return VIP;
	}

	public void setVIP(String vIP) {
		this.VIP = vIP;
	}

	public String getExecutionLocation() {
		return executionLocation;
	}

	public void setExecutionLocation(String executionLocation) {
		this.executionLocation = executionLocation;
	}

	public String getBrowserType() {
		return browserType;
	}

	public void setBrowserType(String browserType) {
		this.browserType = browserType;
	}

	public String getBrowserVersion() {
		return browserVersion;
	}

	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}

	public String getOS() {
		return oS;
	}

	public void setOS(String oS) {
		this.oS = oS;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public Properties getProp() {
		return prop;
	}

	public void setProp(Properties prop) {
		this.prop = prop;
	}

	protected String userDirectory = System.getProperty("user.dir");
	protected Properties prop = new Properties();

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getFirm() {
		return firm;
	}

	public void setFirm(String firm) {
		this.firm = firm;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getDynatraceFlag() {
		return dynatraceFlag;
	}

	public void setDynatraceFlag(String dynatraceFlag) {
		this.dynatraceFlag = dynatraceFlag;
	}

	public String getTestFrameworkType() {
		return testFrameworkType;
	}

	public void setTestFrameworkType(String testFrameworkType) {
		this.testFrameworkType = testFrameworkType;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public ConfigProperties(String fileName) {
		
		try {
			String configPath = "./config/".concat(fileName);
			FileInputStream configFile = new FileInputStream(configPath);
			if (configFile != null) {
				prop.load(configFile);
				configFile.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error Loading Configuration properites: File system Error " + e.getStackTrace());
			System.exit(1);
		}

		application = prop.getProperty("Application");
		firm = prop.getProperty("Frim");
		environment = prop.getProperty("Environment");
		uRL = prop.getProperty("URL");
		VIP = prop.getProperty("VIP");
		executionLocation = prop.getProperty("ExecutionLocation");
		browserType = prop.getProperty("BrowserType");
		browserVersion = prop.getProperty("BrowserVersion");
		testFrameworkType = prop.getProperty("TESTFRAMEWORKTYPE");
		oS = prop.getProperty("OS");
		device = prop.getProperty("Device");
		dynatraceFlag = prop.getProperty("DYNATRACE");
		firmId = Integer.parseInt(prop.getProperty("FIRMID"));
		productId = Integer.parseInt(prop.getProperty("PRODUCTID"));
		environmentId = Integer.parseInt(prop.getProperty("ENVIRONMENTID"));
		scriptId = Integer.parseInt(prop.getProperty("SCRIPTID"));

		if (prop.getProperty("JOBID") == null)
			jobID = 0;
		else
			jobID = Integer.parseInt(prop.getProperty("JOBID"));

		if (prop.getProperty("LABID") == null)
			labID = 0;
		else
			labID = Integer.parseInt(prop.getProperty("LABID"));

		if (prop.getProperty("LABGROUPID") == null)
			labGroupID = 0;
		else
			labGroupID = Integer.parseInt(prop.getProperty("LABGROUPID"));

		// Temp Properties
		setUser(prop.getProperty("User"));
		setPwd(prop.getProperty("Pwd"));
	}
}
