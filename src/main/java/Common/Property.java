/**************************************************
 UIAutomation.
 Common.Property.java : Class containing all static variables used in the framework.
 */

package Common;

import java.util.HashMap;

public class Property {

	//TODO : get system properties and check for seperator using "file.seperator" for all the file path used .
	
	public static String FileSeperator = System.getProperty("file.separator");
	
	public static String ObjectRepositoryQuery = "select * from [object_defination$]";  //Query for getting data from Object Repository.
	
	public static String TestCaseQuery = "select * from [test_flow$]";  //Query for getting the data from Testcase.
	
	public static String TestDataSheetName = "test_data";
	
	public static String TestDataQuery = "select * from ["+ Property.TestDataSheetName + "$]";  
	
	//Dynamic JDBC Connection strings declarations.
	
	public static String JDBCConnectionStringObjectRepository = "jdbc:odbc:Driver={Microsoft Excel Driver (*.xls, *.xlsx, *.xlsm, *.xlsb)};DBQ={0};READONLY=TRUE";
	
	public static String JDBCConnectionStringTestCase = "jdbc:odbc:Driver={Microsoft Excel Driver (*.xls, *.xlsx, *.xlsm, *.xlsb)};DBQ=" + "{0}" + ";READONLY=TRUE";
	
	public static String JDBCConnectionStringTestData = "jdbc:odbc:Driver={Microsoft Excel Driver (*.xls, *.xlsx, *.xlsm, *.xlsb)};DBQ={0};READONLY=TRUE";
	
	public static String IsSauceLabExecution = "false";
	
	public static String IsRemoteExecution = "false";
	
	public static String RemoteURL = "";
	
	public static HashMap<String, String> paramMap = new HashMap<String, String>();
	
	public static String PropertyFileLocation = "src"+ FileSeperator +"main"+ FileSeperator +"resources" + FileSeperator + "uiautomation.properties";
	
	public static String ObjectRepositoryFileLocation = "src" + FileSeperator +"main"+ FileSeperator +"resources"+ FileSeperator +"ObjectRepository"+ FileSeperator + "ObjectRepository.xls";
	
	public static String BrowserName = "firefox";
	
	public static String TestSuite = "";
	
	public static HashMap<String, String> globalVarMap = new HashMap<String, String>();
	
	public static String REUSABLE_INVOKE_KEYWORD = "runreusabletestcase";
	
	public static String REUABLE_FILE_NAME = "GlobalActionFile";
	
	public static String FILE_EXTENSION = ".xls";
	
	public static String TESTCASE_LOC = "src" + FileSeperator+ "main" + FileSeperator +"resources"+ FileSeperator+"TestCase"+FileSeperator;
	
	public static String Remarks = "";
	
	public static final String PASS = "pass";
	
	public static final String FAIL = "fail";
	
	public static String StepStatus = "";
	
	public static String ApplicationURL = "";
	
	public static String SEPERATOR = "#";
	
	public static String StepDescription = "";
	
	public static String SyncTimeOut = "";
	
	public static String LogFile = "src" + FileSeperator+ "main" + FileSeperator +"resources"+ FileSeperator + "Execution_Log" + FileSeperator + "{0}";
	
	public static String BDD_StepName = "";
	
	public static String BDD_FileFormat = "";
	
	public static String BDDFile_Location = "src" + FileSeperator+ "main" + FileSeperator +"resources"+ FileSeperator + "BDDStepRecord"+ FileSeperator;
	
	
	
	
}

