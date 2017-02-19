package com.hi.uiautomation.testscripts;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import reporting.BddStepCreationControler;
import reporting.LogFile;
import reporting.ReportCreation;
import testDriver.Actions;
import Common.*;
import dataReader.*;



public class TestEngine {
	
	ResultSet TestORSet = null;
	public Actions objAction = null;
	private ReaderUtility objReader = null;
    public String StepAction;
    public LogFile objLog = new LogFile();
    public static ArrayList<String> TestCaseIDsForExecution = new ArrayList<String>();
    boolean IsWriteStep = false;
    BddStepCreationControler objController;
    public HashMap<String, String> getORData(String parent,String testObject) throws Exception{
		HashMap<String, String> objDef = new HashMap<String, String>();
		try{
			TestORSet.beforeFirst();
		while(TestORSet.next()){
			if(TestORSet.getString("parent").equals(parent) && TestORSet.getString("testObject").equals(testObject)){
				String how = TestORSet.getString("how");
				String what = TestORSet.getString("what");
				how  = Utility.ReplaceVariableInString(how);
				what = Utility.ReplaceVariableInString(what);
				
				objDef.put("parent", parent);
				objDef.put("testObject", testObject);
				objDef.put("how", how);
				objDef.put("what", what);
				
				break;
				
			}
		}
		
		return objDef;
		
		}
		catch(Exception e){
			throw e;
		}
	}
	
	
	
	
	public void ExecuteTestCase(String TestCaseID,boolean reusableFlag) throws Exception{
		try{
		 //objReader = new ReaderUtility(TestCaseID);
		objReader.setFileLocation(reusableFlag);
		TestORSet  = objReader.getORData();
		ResultSet TestCaseSet = objReader.getTestCaseData();
		ResultSet TestDataSet = objReader.getTestData();
		objAction = null;
		Boolean IsTestidFound = false;
		while(TestCaseSet.next()){
			String testid = TestCaseSet.getString("testcase_id");
			if(testid == null){testid = "";}
			if(testid.equalsIgnoreCase(TestCaseID)){
				IsTestidFound = true;
			}
			
			if(IsTestidFound){
							
				if(!testid.equals("") && !testid.equals(TestCaseID)){
					IsTestidFound = false;
					
					break;
				}
				else{
						Property.StepStatus = "";
						String BDD_Step = "";
					String parent = TestCaseSet.getString("parent");
					String testObject = TestCaseSet.getString("testObject");
					String DataContent = TestCaseSet.getString("data");
					String StepAction = TestCaseSet.getString("stepaction");
					String options = TestCaseSet.getString("options");
				    BDD_Step = TestCaseSet.getString("step");
					DataContent = Utility.ReplaceVariableInString(DataContent);
					parent = Utility.ReplaceVariableInString(parent);
					testObject = Utility.ReplaceVariableInString(testObject);
					options = Utility.ReplaceVariableInString(options);
					
						
					int rownum = 1;//Will use row num logic later.
						
						if(DataContent == null){
							try
							{
								//while(TestDataSet.next()){
								//	System.out.println(TestDataSet.getString("edtSearch"));
								//}
								//TestDataSet.findColumn(testObject);
								TestDataSet.absolute(rownum);
								DataContent =  TestDataSet.getString(testObject);
							    Utility.setVariable(testObject, DataContent);
							}
							catch(NullPointerException e){
								DataContent = null;
							}
							catch(SQLException e){
								DataContent = null;
								}
						
						
						}
						
					    
						
						//TODO : Will add code to parse data.
						Actions.objDataRow = getORData(parent, testObject);
						objAction = new Actions();
						BDD_Step = Utility.ReplaceVariableInString(BDD_Step);
						//if(!reusableFlag){
						//ReportCreation.WriteLogMessage("Stepction : '" + StepAction + "' \n");
						//ReportCreation.WriteLogMessage("Executed on '" + parent + "' :: '" + testObject + "' \n");
						//}
						
						
					   						
					    try{
					    	objAction.DO(StepAction,DataContent,parent,testObject,options);
					    }
					    catch(Exception e){
					    	  if(e.getMessage().equalsIgnoreCase("No Such Action")){
					    		
					    		  		//If datacontent would have contained {$var} then it should have replaced so checking for arguments only.		    		  
					    		 if(DataContent != null){
					    		  if(DataContent.contains("{") && DataContent.contains("}")){  
					    		   String[] arguments = DataContent.split(",");
					    		   for(int i=0;i<= arguments.length-1;i++){
					    				arguments[i] = arguments[i].replace("{", "");
					    				arguments[i] = arguments[i].replace("}", "");
					    			   Utility.setVariable("argument"+i, arguments[i]);
					    			  }
					    			 				    		  
					    		}
					    	  }
					    		  ExecuteTestCase(StepAction, true);
					    		  
					    		  //Need to loook if arguments need to be deleted.
					    		  IsWriteStep = false;
					    		  
					    	  }
					    }
					    //ReportCreation.WriteLogMessage("Remarks : " + Property.Remarks);
					    //ReportCreation.WriteLogMessage("Status" + Property.StepStatus);
					    objLog.logMessageConsole("Remarks : " + Property.Remarks);
					    objLog.logMessageConsole("Status" + Property.StepStatus);
					    objLog.writeStepLog(TestCaseID, StepAction, Property.StepStatus,Property.Remarks,BDD_Step,IsWriteStep);
					    BddStepCreationControler.objStepFormation.CreateContentRow(TestCaseID, StepAction, BDD_Step, Property.StepStatus,IsWriteStep);
					    IsWriteStep = true;
					    
				}
			}
			//System.out.println("Status of '" + StepAction +"' =" + Property.StepStatus);
			//System.out.println("Remarks : " + Property.Remarks);
			//Assert.assertEquals(Property.StepStatus, Property.PASS);
	}
		
		
		
		
		}
		catch(NullPointerException e){
			throw new NullPointerException("Execution has unexpectedly broken.");
		}
		catch(Exception e){
			throw e;
		}
		
		
	}

	@Before
	
	
	public void f(){
		try{
		/*	System.out.println("@before "  + System.getProperty("abc"));
		Properties prop =  System.getProperties();
		prop.getProperty("browsertype");
		System.out.println(prop.containsKey("browsertype"));
			//Read data from property file.
		System.out.println(prop.toString());
		*/
		
		Utility.collectKeyValuePair();
		
		Utility.populateGlobalMap();
		IsWriteStep = true;
		
		Property.TestSuite = Utility.getParameter("TestSuite");
		Property.BrowserName = Utility.getParameter("browserType");
		Property.SyncTimeOut = Utility.getParameter("MaximumTimeout");
		Property.IsRemoteExecution = Utility.getParameter("RemoteExecution");
		Property.IsSauceLabExecution = Utility.getParameter("SaucelabExecution");
		Property.ApplicationURL = Utility.getParameter("application.url");
		Property.RemoteURL = Utility.getParameter("RemoteUrl");
		Property.BDD_FileFormat = Utility.getParameter("BDDFileFormat");
		
		//Update key value pair passed as an arguments(update paramMAp).
		
		//Set All PRoperty variable using setParameter();
		
		//Use dataReader class to get the data for the framework.
		
		//Getting a controller object to record BDD Step.
		
		objController = new BddStepCreationControler(Property.BDD_FileFormat);
		
		objController.buildStepCreationObject();
		
		//Preparing the name of the file for BDD step recording.
		
		String BDDFileName = Property.TestSuite + "_" +  Utility.getCurrentTimeStamp();
		
		
		//Creating a File
		BddStepCreationControler.objStepFormation.createFile(BDDFileName);
		//Create a header.
		BddStepCreationControler.objStepFormation.CreateHeader();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	

	@Test
	public void Execution() throws Exception {
		
		System.out.println("@test" + System.getProperty("browsertype"));
		try{
		String TestSuite = Utility.getParameter("TestSuite");
		String TestCaseID = Utility.getParameter("TestCase");
		//String CurrentTestCaseID = TestCaseIDsForExecution.get(i);
		String LogFileName = TestSuite + "_" + Utility.getCurrentTimeStamp() +".txt";
		String LogFile = Property.LogFile.replace("{0}", LogFileName);
		objLog.prepareLogger(LogFile);
		objLog.prepareHeader();
		objReader = new ReaderUtility();
		objReader.setFileLocation(false);
		 
		if(!(TestSuite.equals(null)) && (TestCaseID == null || TestCaseID == "") )
		{
			ResultSet rs = objReader.getTestCaseData();
			
			while(rs.next()){
				
				String tid = rs.getString("testcase_id");
				if(!(tid == null)){
					TestCaseIDsForExecution.add(tid);
					
				}
			}
			
			
		}
		else
		{
			if(TestCaseID.contains(",")){
			String[] TCids = 	TestCaseID.split(",");
			for (String ID : TCids) {
				ID = ID.trim();
				TestCaseIDsForExecution.add(ID);
				}
			}
			else
			{
				TestCaseIDsForExecution.add(TestCaseID);
			}
			
		}
		
		for(int i=0;i <= (TestCaseIDsForExecution.size()-1);i++){
			String CurrentTestCaseID = TestCaseIDsForExecution.get(i);
		//	String LogFileName = CurrentTestCaseID + "_" + Utility.getCurrentTimeStamp() +".txt";
		//	String LogFile = Property.LogFile.replace("{0}", LogFileName);
		//	objLog.prepareLogger(LogFile);
			objLog.logMessageConsole("Execution start for " + CurrentTestCaseID);
			//System.out.println("Execution start for " + CurrentTestCaseID);
			ExecuteTestCase(CurrentTestCaseID,false);		
			try{objAction.DO("closeallbrowsers", "", "", "",null);} catch(Exception e){}
			objLog.logMessageConsole("Execution ends for " + CurrentTestCaseID);
			//System.out.println("Execution ends for " + CurrentTestCaseID);
		}
		
		}
		catch(Exception e){
			ReportCreation.WriteLogMessage(e.getMessage());
		}
		
	}

}
