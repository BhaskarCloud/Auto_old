package com.lpl.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * @author sparasha
 *
 */
public class Util {
	/* Config file Path */
	public static String CONFIG_FILE_PATH;

	/*----------------------------------------DRIVER---------------------------------------------------------------------------------------------------*/
	/* IE Driver Path */
	public String WIN_IE_DRIVER32_PATH;
	public String WIN_IE_DRIVER64_PATH;

	/* CHROME Driver Path */
	public String WIN_CHROME_DRIVER_PATH;
	public String MAC_CHROME_DRIVER_PATH;

	/* PHANTOMJS Driver Path */
	public String WIN_PHANTOMJS_DRIVER32_PATH;

	/* FireFox Driver path */
	public String WIN_GECKO_DRIVER_PATH;

	/* ExtentReportPath */
	public String EXTENT_REPORTS_PATH;

	/* Navigation RC Menu XML file path */
	public String RCMENUNAVIGATION_XML_PATH;

	/*----------------------------------------Timeout Values---------------------------------------------------------------------------------------------------*/
	// Different Timeout values are list here sorted descending from the highest
	// wait to lowest wait. These many are required for various Implicit wait
	// purpose
	public int HIGHEST;
	public int VERYHIGH;
	public int HIGH;
	public int MEDIUM;
	public int FAIR;
	public int LOW;
	public int BASE;
	public int LOWEST;
	public int UNIT;
	public int HIGHINMILLISEC;
	public int FAIRINMILLISEC;
	public int BaseInMiliSec;
	public int MediumInMiliSec;
	public int LOWESTInMiliSec;
	public int UNITINMILLISEC;

	/*----------------------------------------Database Connection Details---------------------------------------------------------------------------------------------------*/
	public static String FARMDBConnectionString;
	public static String MySQLDriver;
	public static String LogInCredentialStoredProcedure;
	public String TestDataStoredProcedure;
	public static String PageObjectStoredProcedure;
	public String strCryptoKey;
	public String strEncryptionFlag;
	public static int intIDIndex;
	public static int intCSSIndex;
	public static int intXPATHIndex;
	public static int intXCORDIndex;
	public static int intYCORDIndex;
	public static int intAngularRefIndex;
	public static int intAngularLocatorTypeIndex;

	/*----------------------------------Report logger file -----------------------------------------------------------*/

	public String REPORT_LOG_FILE_NAME;
	public String REPORT_LOG_FILE_PATH;

	/*-------------------------------- ScreenShort Path ------------------------------------------------------------*/
	public String SCREEN_SHOT_PATH;
	public String GLOBAL_SCREEN_SHOT_PATH;

	/*-------------------------------- HTML Report Path ------------------------------------------------------------*/
	public String htmlReportPath;
	public String CSSPath;
	public String QA2000ServerHTMLReportPath;
	/*---------------------------------- QA Environment Server Details -----------------------------------------------*/
	public String LPL_QAF1TX;
	public String LPL_QAF2TX;
	public String LPL_QAF3TX;
	public String LPL_QAF4TX;
	public String LPL_DevF1TX;
	public String AXA_QAF1TX;
	public String AXA_QAF2TX;
	public String AXA_QAF3TX;
	public String AXA_QAF4TX;
	public String AXA_DevF1TX;

	/*------------------------------- Database Connection Details To Fetch Password In QA Environment -------------------*/
	public String strQAPasswordConnectionString;
	public String strQAUtilUserName;
	public String strQAUtilPassword;
	public String strQAPasswordQueryString;

	/*------------------------------- Certificate Selection Command -------------------*/
	public String strCertCommand;
	public String strCompViewCommand;
	public String strIEDownloadCommand;
	public String strWinAuthCommand;
	public String strWinUploadHandleCommand;
	public String strThirdPartyDownloadCommand;
	public String DefaultDownloadFolder;
	public String CancelIEPwdPopUPCommand;
	public String strFirefoxExceptionHandlerCommand;
	public String strIEOpenSaveDownloadCommand;
	public String strCancelUploadCommand;

	public Util() {
		try {
			// Loading the file from the given path
			FileInputStream xmlFile = new FileInputStream("C:\\FarmClient\\TestDir\\ProjectConfig\\CoreConstents.xml");

			// Parsing the XML file into a document
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile);

			// Retrieve data from XML
			CONFIG_FILE_PATH = document.getElementsByTagName("CONFIG_FILE_PATH").item(0).getTextContent();
			RCMENUNAVIGATION_XML_PATH = document.getElementsByTagName("RCMENUNAVIGATION_XML_PATH").item(0)
					.getTextContent();
			WIN_IE_DRIVER32_PATH = document.getElementsByTagName("WIN_IE_DRIVER32_PATH").item(0).getTextContent();
			WIN_IE_DRIVER64_PATH = document.getElementsByTagName("WIN_IE_DRIVER64_PATH").item(0).getTextContent();
			WIN_PHANTOMJS_DRIVER32_PATH = document.getElementsByTagName("WIN_PHANTOMJS_DRIVER32_PATH").item(0)
					.getTextContent();
			WIN_CHROME_DRIVER_PATH = document.getElementsByTagName("WIN_CHROME_DRIVER_PATH").item(0).getTextContent();
			MAC_CHROME_DRIVER_PATH = document.getElementsByTagName("MAC_CHROME_DRIVER_PATH").item(0).getTextContent();
			WIN_GECKO_DRIVER_PATH = document.getElementsByTagName("WIN_GECKO_DRIVER_PATH").item(0).getTextContent();
			EXTENT_REPORTS_PATH = document.getElementsByTagName("CONFIG_FILE_PATH").item(0).getTextContent();
			HIGHEST = Integer.parseInt(document.getElementsByTagName("HIGHEST").item(0).getTextContent());
			VERYHIGH = Integer.parseInt(document.getElementsByTagName("VERYHIGH").item(0).getTextContent());
			HIGH = Integer.parseInt(document.getElementsByTagName("HIGH").item(0).getTextContent());
			MEDIUM = Integer.parseInt(document.getElementsByTagName("MEDIUM").item(0).getTextContent());
			FAIR = Integer.parseInt(document.getElementsByTagName("FAIR").item(0).getTextContent());
			LOW = Integer.parseInt(document.getElementsByTagName("LOW").item(0).getTextContent());
			BASE = Integer.parseInt(document.getElementsByTagName("BASE").item(0).getTextContent());
			LOWEST = Integer.parseInt(document.getElementsByTagName("LOWEST").item(0).getTextContent());
			UNIT = Integer.parseInt(document.getElementsByTagName("UNIT").item(0).getTextContent());
			BaseInMiliSec = Integer.parseInt(document.getElementsByTagName("BASEINMILISEC").item(0).getTextContent());
			FAIRINMILLISEC = Integer.parseInt(document.getElementsByTagName("FAIRINMILLISEC").item(0).getTextContent());
			MediumInMiliSec = Integer
					.parseInt(document.getElementsByTagName("MEDIUMINMILISEC").item(0).getTextContent());
			LOWESTInMiliSec = Integer
					.parseInt(document.getElementsByTagName("LOWESTINMILISEC").item(0).getTextContent());
			FARMDBConnectionString = document.getElementsByTagName("FARMDBCONNECTIONSTRING").item(0).getTextContent();
			HIGHINMILLISEC = Integer.parseInt(document.getElementsByTagName("HIGHINMILLISEC").item(0).getTextContent());
			UNITINMILLISEC = Integer.parseInt(document.getElementsByTagName("UNITINMILLISEC").item(0).getTextContent());

			MySQLDriver = document.getElementsByTagName("MYSQLDRIVER").item(0).getTextContent();
			LogInCredentialStoredProcedure = document.getElementsByTagName("LOGINCREDENTIALSTOREDPROCEDURE").item(0)
					.getTextContent();
			TestDataStoredProcedure = document.getElementsByTagName("TESTDATASTOREDPROCEDURE").item(0).getTextContent();
			PageObjectStoredProcedure = document.getElementsByTagName("PAGEOBJECTSTOREDPROCEDURE").item(0)
					.getTextContent();

			strCryptoKey = document.getElementsByTagName("STRCRYPTOKEY").item(0).getTextContent();
			strEncryptionFlag = document.getElementsByTagName("STRENCRYPTIONFLAG").item(0).getTextContent();

			intIDIndex = Integer.parseInt(document.getElementsByTagName("INTIDINDEX").item(0).getTextContent());
			intCSSIndex = Integer.parseInt(document.getElementsByTagName("INTCSSINDEX").item(0).getTextContent());
			intXPATHIndex = Integer.parseInt(document.getElementsByTagName("INTXPATHINDEX").item(0).getTextContent());
			intXCORDIndex = Integer.parseInt(document.getElementsByTagName("INTXCORDINDEX").item(0).getTextContent());
			intYCORDIndex = Integer.parseInt(document.getElementsByTagName("INTYCORDINDEX").item(0).getTextContent());
			intAngularRefIndex = Integer
					.parseInt(document.getElementsByTagName("INTANGULARREFINDEX").item(0).getTextContent());
			intAngularLocatorTypeIndex = Integer
					.parseInt(document.getElementsByTagName("INTANGULARLOCATORTYPEINDEX").item(0).getTextContent());
			REPORT_LOG_FILE_NAME = document.getElementsByTagName("REPORT_LOG_FILE_NAME").item(0).getTextContent();
			REPORT_LOG_FILE_PATH = document.getElementsByTagName("REPORT_LOG_FILE_PATH").item(0).getTextContent();
			SCREEN_SHOT_PATH = document.getElementsByTagName("SCREEN_SHOT_PATH").item(0).getTextContent();
			GLOBAL_SCREEN_SHOT_PATH = document.getElementsByTagName("GLOBAL_SCREEN_SHOT_PATH").item(0).getTextContent();

			/*---------------------------------- QA Environment Server Details -----------------------------------------------*/
			// For LPL Firm
			Element LPL = (Element) document.getElementsByTagName("LPL").item(0);
			LPL_QAF1TX = LPL.getElementsByTagName("QA-F1TX").item(0).getTextContent();
			LPL_QAF2TX = LPL.getElementsByTagName("QA-F2TX").item(0).getTextContent();
			LPL_QAF3TX = LPL.getElementsByTagName("QA-F3TX").item(0).getTextContent();
			LPL_QAF4TX = LPL.getElementsByTagName("QA-F4TX").item(0).getTextContent();
			LPL_DevF1TX = LPL.getElementsByTagName("DEV-F1TX").item(0).getTextContent();

			// For AXA Firm
			Element AXA = (Element) document.getElementsByTagName("AXA").item(0);
			AXA_QAF1TX = AXA.getElementsByTagName("QA-F1TX").item(0).getTextContent();
			AXA_QAF2TX = AXA.getElementsByTagName("QA-F2TX").item(0).getTextContent();
			AXA_QAF3TX = AXA.getElementsByTagName("QA-F3TX").item(0).getTextContent();
			AXA_QAF4TX = AXA.getElementsByTagName("QA-F4TX").item(0).getTextContent();
			AXA_DevF1TX = AXA.getElementsByTagName("DEV-F1TX").item(0).getTextContent();

			/*------------------------------- Database Connection Details To Fetch Password In QA Environment -------------------*/
			strQAPasswordConnectionString = document.getElementsByTagName("QAPASSWORDCONNECTIONSTRING").item(0)
					.getTextContent();
			strQAUtilUserName = document.getElementsByTagName("QAUTILUSERNAME").item(0).getTextContent();
			strQAUtilPassword = document.getElementsByTagName("QAUTILPASSWORD").item(0).getTextContent();
			strQAPasswordQueryString = document.getElementsByTagName("QAPASSWORDQUERYSTRING").item(0).getTextContent();

			/*------------------------------- Exe Call Commands -------------------*/
			strCertCommand = document.getElementsByTagName("CERTCOMMAND").item(0).getTextContent();
			strCompViewCommand = document.getElementsByTagName("COMPVIEWCOMMAND").item(0).getTextContent();
			strIEDownloadCommand = document.getElementsByTagName("IEDOWNLOADCOMMAND").item(0).getTextContent();
			strWinAuthCommand = document.getElementsByTagName("WINAUTHCOMMAND").item(0).getTextContent();
			strWinUploadHandleCommand = document.getElementsByTagName("FILEUPLOADCOMMAND").item(0).getTextContent();
			strThirdPartyDownloadCommand = document.getElementsByTagName("IETPDCOMMAND").item(0).getTextContent();
			CancelIEPwdPopUPCommand = document.getElementsByTagName("CANCELIEPWDPOPUPCOMMAND").item(0).getTextContent();
			strFirefoxExceptionHandlerCommand = document.getElementsByTagName("FIREFOXEXCEPTIONHANDLERCOMMAND").item(0)
					.getTextContent();
			strIEOpenSaveDownloadCommand = document.getElementsByTagName("IEOPENSAVEDOWNLOADCOMMAND").item(0)
					.getTextContent();
			strCancelUploadCommand = document.getElementsByTagName("CANCELUPLOADCOMMAND").item(0).getTextContent();

			/*-------------------------------------Default Download Folder path ---------------------------*/
			DefaultDownloadFolder = document.getElementsByTagName("DEFAULTDOWNLOADFOLDER").item(0).getTextContent();

			/*-------------------------------- HTML Report Path ------------------------------------------------------------*/
			htmlReportPath = document.getElementsByTagName("HTMLReportPath").item(0).getTextContent();
			CSSPath = document.getElementsByTagName("CSSPath").item(0).getTextContent();
			QA2000ServerHTMLReportPath = document.getElementsByTagName("QA2000ServerHTMLReportPath").item(0)
					.getTextContent();

		} catch (NumberFormatException |DOMException |SAXException |IOException |ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
}
