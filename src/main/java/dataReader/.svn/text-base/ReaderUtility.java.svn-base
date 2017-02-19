/**********************************************************
 dataReader.ReaderUtility.java : Contains all utility methods used in accessing external files.
 *********************************************************/


package dataReader;

import java.sql.*;
import Common.*;

public class ReaderUtility {
	
	private String TestCaseFileLocation;
	private String TestDataFileLocation;
	private String ObjectRepositoryFileLocation;
	private String CurrentTestSuiteID;
	//Constructor
	public ReaderUtility(){
		CurrentTestSuiteID = Property.TestSuite;
	}
	//Set All the file location basis on testcaseID or reusable test case id.
	public void setFileLocation(boolean reusableFlag){
		TestCaseFileLocation = "src" + Property.FileSeperator + "main"+ Property.FileSeperator + "resources" + Property.FileSeperator + "TestCase"+ Property.FileSeperator + CurrentTestSuiteID + ".xls";
		if(reusableFlag){
			TestCaseFileLocation = Property.TESTCASE_LOC + Property.REUABLE_FILE_NAME + Property.FILE_EXTENSION;
		}
		TestDataFileLocation = TestCaseFileLocation;	
		ObjectRepositoryFileLocation = Property.ObjectRepositoryFileLocation;
	}
	
	/*
	 getRequiredRows : Get the rows of data from external file basis on the query given.
	 */
	public ResultSet getRequiredRows(String FilePath,String ConnectionString,String Query) throws Exception{
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			conn = DriverManager.getConnection(ConnectionString);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(Query);
			
		}
		catch(Exception e){
			throw e;
		}
		return rs;
		
	}
	/*
	 getORData : Get the data from Object Repository.
	 */
	public ResultSet getORData() throws Exception{
		try{
		String connectString = Property.JDBCConnectionStringObjectRepository.replace("{0}", ObjectRepositoryFileLocation);
		ResultSet rs = getRequiredRows(ObjectRepositoryFileLocation, connectString, Property.ObjectRepositoryQuery);
		return rs;
		}
		catch(Exception e){
			throw e;
		}
		
	}
	/*
	 getTestCaseData : Get the data from testcase repository.
	 */
	public ResultSet getTestCaseData() throws Exception{
		try{
			String connectString = Property.JDBCConnectionStringTestCase.replace("{0}", TestCaseFileLocation);
			ResultSet rs = getRequiredRows(TestCaseFileLocation, connectString, Property.TestCaseQuery);
			return rs;
					
			
		}
		catch(Exception e){
			throw e;
		}
	}
	
	/*
	 getTestData : Get the data from test data repository file.
	 */
	public ResultSet getTestData() throws Exception{
		try{
			String connectString = Property.JDBCConnectionStringTestData.replace("{0}", TestDataFileLocation);
			ResultSet rs = getRequiredRows(TestDataFileLocation, connectString, Property.TestDataQuery);
			return rs;
			
		}
		catch(Exception e){
			throw e;
		}
	}
	
	
	

}
