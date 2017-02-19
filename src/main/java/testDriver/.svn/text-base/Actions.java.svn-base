/**************************************
 testDriver.Actions.java : Kind of interface that prepare objects and data before execution goes to perform application's operations.
 **************************************/


package testDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.CommonDataSource;

import org.openqa.selenium.WebElement;

import Common.Property;
import Common.Utility;

import seleniumdriver.SeleniumImplementation;

public class Actions {
	SeleniumImplementation objSeleniumDriver = null; 
	public static HashMap<String, String> objDataRow = new HashMap<String, String>();
	public ArrayList<String> optionsList = new ArrayList<String>();
	public Actions(){
		
		objSeleniumDriver = new SeleniumImplementation(null);
		
	}
	
	public void DO(String stepAction,String Data,String parent,String child,String modifier) throws Exception{
		boolean verification = true;
		boolean enable = true;
		String stepStatus = "";
		String TestObject = child;
		Property.Remarks = "";
		
		//------Parse Options field.
		optionsList.clear();
		int stIndex;
		int endIndex;
		if(modifier != null){
			modifier = modifier.toLowerCase().trim();
			for(int v=0;;v++){
				if(modifier.contains("{")){
					stIndex = modifier.indexOf('{');
					modifier = modifier.replaceFirst("\\{", "");
					endIndex = modifier.indexOf('}');
					String KeyVariable = modifier.substring(stIndex, endIndex);
					optionsList.add(KeyVariable);
					modifier = modifier.replaceFirst("\\}", "");
					}
				else {
					break;
				}
			}
			
		}
		Common.Utility.driverOptionList = null;
		Common.Utility.driverOptionList = optionsList;
		
		//----Parse data----
		
		String[] DataContents = null;
		String dataContentFirst = Data;
		String dataContentSecond = "";
		String dataContentThird = "";
		if(Data != null){
		DataContents = Data.split(Property.SEPERATOR);
		switch (DataContents.length) {
		case 1:
			dataContentFirst = DataContents[0];
			dataContentFirst = dataContentFirst.trim();
			break;
		case 2:
			dataContentFirst = DataContents[0];
			dataContentSecond = DataContents[1];
			dataContentFirst = dataContentFirst.trim();
			dataContentSecond = dataContentSecond.trim();
			break;
		case 3:
			dataContentFirst = DataContents[0];
			dataContentSecond = DataContents[1];
			dataContentThird = DataContents[3];
			dataContentFirst = dataContentFirst.trim();
			dataContentSecond = dataContentSecond.trim();
			dataContentThird = dataContentThird.trim();

		default:
			break;
		}
		dataContentFirst = Common.Utility.ReplaceVariableInString(dataContentFirst);
		dataContentSecond = Common.Utility.ReplaceVariableInString(dataContentSecond);
		dataContentThird = Common.Utility.ReplaceVariableInString(dataContentThird);
		}
		
		//------
		
		
		
		try{
		objSeleniumDriver.setObjectDataRow(objDataRow);
		
		if(stepAction.toLowerCase().equals("openbrowser")){
			
			if(dataContentFirst == null || dataContentFirst == ""){
				dataContentFirst = Property.ApplicationURL;
			}
			//call init method of seleniumImplemention here.
			Property.StepDescription = "Open a new browser and navifate to URL '" + dataContentFirst + "'";
			objSeleniumDriver.openBrowser(dataContentFirst);
			//Maximize  browser window.
			objSeleniumDriver.ExecuteScript(null, "window.moveTo(0,0); window.resizeTo(screen.width-10,screen.height-10);");
					
		}
		else if(stepAction.equalsIgnoreCase("goback")){
			Property.StepDescription = "Go backward in browser";
			objSeleniumDriver.goBack();
		}
		else if(stepAction.equalsIgnoreCase("goforward")){
			Property.StepDescription = "Go forward in browser";
			objSeleniumDriver.goForward();
		}
		else if(stepAction.equalsIgnoreCase("navigateurl")){
			Property.StepDescription = "Navigate to Url '" + dataContentFirst + "' in currently opened browser";
			objSeleniumDriver.NavigateUrl(dataContentFirst);
		}
		else if(stepAction.equalsIgnoreCase("refresh")){
			Property.StepDescription = "Refresh browser";
			objSeleniumDriver.refreshBrowser();
		}
		else if(stepAction.equalsIgnoreCase("check")){
			Property.StepDescription = "Check '" + TestObject + "'";
			objSeleniumDriver.check();
		}
		else if(stepAction.equalsIgnoreCase("uncheck")){
			Property.StepDescription = "Uncheck '" + TestObject + "'";
			objSeleniumDriver.Uncheck();
		}
		else if(stepAction.toLowerCase().equals("closebrowser")){
			Property.StepDescription = "Close Browser";
			objSeleniumDriver.closeBrowser();
		}
		else if(stepAction.equalsIgnoreCase("closeallbrowsers")){
			objSeleniumDriver.closeAllBrowsers();
		}
		else if(stepAction.toLowerCase().equals("click")){
			Property.StepDescription = "Click on '" + TestObject + "'";
					
			objSeleniumDriver.click();
		}
		else if(stepAction.toLowerCase().equals("doubleclick")){
			Property.StepDescription = "Double click on '" + TestObject + "'";
			objSeleniumDriver.DoubleClick();
		}
		else if(stepAction.toLowerCase().equals("enterdata") || stepAction.toLowerCase().equals("type")){
			Property.StepDescription = "Enter Text '" + dataContentFirst + "' in '" + TestObject + "'";
			objSeleniumDriver.sendKeys(dataContentFirst);
			Utility.setVariable(TestObject, dataContentFirst);
			
		}
		else if(stepAction.equalsIgnoreCase("fireevent")){
			Property.StepDescription = "Fire '" + dataContentFirst + "' on '" + TestObject + "'";
			objSeleniumDriver.FireEvent(dataContentFirst);
			System.out.println("done");
		}
		else if(stepAction.equalsIgnoreCase("enteruniquedata")){
			
		}
		else if(stepAction.equalsIgnoreCase("keypress")){
			Property.StepDescription = "Press Keyboard key '" + dataContentFirst + "' on '" + TestObject + "'";
			objSeleniumDriver.KeyPress(dataContentFirst);
		}
		else if(stepAction.equalsIgnoreCase("wait")){
			
		}
		else if(stepAction.equalsIgnoreCase("selectitem")){
			Property.StepDescription = "Select '" + dataContentFirst + "' in '" + TestObject + "'";
			objSeleniumDriver.selectItem(dataContentFirst);
		}
		else if(stepAction.equalsIgnoreCase("selectitembyindex")){
			Property.StepDescription = "Select '" + dataContentFirst +"th' item from '"+TestObject;
			String optionValue = objSeleniumDriver.SelectElementByIndex(dataContentFirst);
			Utility.setVariable(TestObject, optionValue);
		}
		else if(stepAction.equalsIgnoreCase("getobjectproperty") || stepAction.equalsIgnoreCase("getattribute")){
			String property = dataContentFirst.trim();
			String propertyvariable = TestObject + "." + property;
			if(dataContentSecond != "" && dataContentSecond != null)
				propertyvariable = dataContentSecond.trim();
			Property.StepDescription = "Get Property of '" + TestObject + "' and set its value in '" + propertyvariable + "'";
			String propertyValue =  objSeleniumDriver.GetObjectProperty(property);
			Utility.setVariable(propertyvariable, propertyValue);
		}
		else if(stepAction.equalsIgnoreCase("setvariable")){
			Property.StepDescription = "Set the variable '" + dataContentFirst + "' with value '" + dataContentSecond + "'";
			Utility.setVariable(dataContentFirst, dataContentSecond);
		}
		
		else if(stepAction.equalsIgnoreCase("getpageproperty")){
			
		}
		else if(stepAction.equalsIgnoreCase("verifytextpresent")){
			Property.StepDescription = "Verify text present";
			
			if(objDataRow.size() == 0){
				verification = objSeleniumDriver.verifyTextPresentOnPage(dataContentFirst,optionsList);
			}
			else{
				verification = objSeleniumDriver.verifyObjectProperty("text", dataContentFirst,optionsList);
				}
		}
		else if(stepAction.equalsIgnoreCase("verifyobjectproperty")){
			Property.StepDescription = "Verify Object Property ' "+ dataContentFirst + "'";
			verification = objSeleniumDriver.verifyObjectProperty(dataContentFirst, dataContentSecond, optionsList);
		}
				
		else if(stepAction.equalsIgnoreCase("verifypageproperty")){
			verification = objSeleniumDriver.verifyPageProperty(dataContentFirst, dataContentSecond,optionsList);
					
		}
		else if(stepAction.equalsIgnoreCase("verifyobjectpresent")){
			Property.StepDescription = "Verify object '" + TestObject + "' present.";
			WebElement Element = objSeleniumDriver.waitAndGetElement();
			if(Element == null) { verification = false;}
			else {verification = true;}
		}
		else if(stepAction.equalsIgnoreCase("waitforelement")){
			objSeleniumDriver.waitAndGetElement();
		}
		
		else{
			throw new NoSuchMethodException("No Such Action");
		}
		
		
		if(verification){
			stepStatus = Property.PASS;
		}
		else{
			if(optionsList.contains("optional")) { stepStatus = Property.PASS;}
			else{stepStatus = Property.FAIL;}
		}
		
		
		}
		//catch (NoSuchMethodError e) {
		//	throw e;
		//}
		catch(NoSuchMethodException exception){
		  	  
		  stepAction = Property.FAIL;
		  throw exception;
		}
		catch(Exception e){
			Property.Remarks = e.getMessage();
			if(optionsList.contains("optional")){stepStatus = Property.PASS;} 
			else { stepStatus = Property.FAIL;}
		}
		
		Property.StepStatus = stepStatus;
				
	}
}
