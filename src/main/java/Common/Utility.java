/*************************************************
 Common.Utility.java : Contains all utility methods used in framework.
   
 ***************************************************/

package Common;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;

public class Utility {
	public static Properties prop = new Properties();
	public static ArrayList<String> driverOptionList = new ArrayList<String>();

	/*
	 * collectKeyValuePair : Load project property file to read its content.
	 */
	public static void collectKeyValuePair() throws Exception {
		try {
			// Read data from property file and store it in global paramMap.
			prop.load(new FileInputStream(Property.PropertyFileLocation));

		} catch (Exception e) {
			throw e;
		}
	}

	public static String getCurrentTimeStamp() {
		Date currentDate = new Date();
		return currentDate.toString().replace(":", "");
	}

	/*
	 * populateGlobalMap : Populate All Key value pairs given in property file
	 * to variable hash map.
	 */
	public static void populateGlobalMap() throws Exception {
		try {
			Enumeration em = prop.keys();
			Set keySet = prop.keySet();
			Object[] keys = keySet.toArray();
			int i = 0;
			while (em.hasMoreElements()) {
				String element = (String) em.nextElement();
				// System.out.println("KEY : " + keys[i].toString() + "VALUE : "
				// + prop.getProperty(element));
				Property.globalVarMap.put(keys[i].toString().toLowerCase(),
						prop.getProperty(element));
				i++;
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * getParameter : Get the value of specified key from property file.
	 */
	public static String getParameter(String key) {
		try {
			return prop.getProperty(key);
		} catch (Exception e) {
			return "";
		}
	}

	/*
	 * setParameter : Set the key value pair to property file.
	 */
	public static void setParameter(String Key, String Value) {
		try {
			prop.setProperty(Key, Value);
		}

		catch (Exception e) {

		}

	}

	/*
	 * setVariable : Set the key/value pair to runtime global hash map.
	 */
	public static void setVariable(String Key, String Value) throws Exception {
		try {
			Property.globalVarMap.put(Key, Value);
		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * getVariable : Get the value of key from run time hash map.
	 */
	public static String getVariable(String Key) throws Exception {
		try {
			return Property.globalVarMap.get(Key);
		} catch (Exception e) {
			return Key;
		}
	}

	/*
	 * doMatchBasedOnOptions : Matches the Expected and Actual values based on
	 * options specified.
	 */
	public static boolean doMatchBasedOnOptions(String ExpectedValue,
			String ActualValue) {
		if (driverOptionList.contains("ignorespace")) {
			ExpectedValue = ExpectedValue.replace(" ", "");
			ActualValue = ActualValue.replace(" ", "");
		}
		if (driverOptionList.contains("ignorecase")) {
			ExpectedValue = ExpectedValue.toLowerCase();
			ActualValue = ActualValue.toLowerCase();
		}

		if (!driverOptionList.contains("partialmatch")
				|| driverOptionList.contains("exactmatch")) {
			return ActualValue.equals(ExpectedValue);
		} else {
			return ActualValue.contains(ExpectedValue);
		}

	}

	/*
	 * ReplaceVariableInString : Replace all variables in format {$VAR} to its
	 * value that is stored in global hash map. Return as it is if couldn't find
	 * a value.
	 */
	/*
	 *the function basically replace the string like "{$Key}"
 from its value that is stored in the Hashmap so for this
 what we have done is we are checkng if that format exist
 if it is then get the start index and end index
 so that we have the key once we have key
 then replacing it with its value  and if the value for a key is not present 
  also note that we are deletng the {$ and } during process otherwise it would stuck into 
	  */
	public static String ReplaceVariableInString(String dataValue) {
		try {

			for (int v = 0;; v++) {

				if (dataValue.contains("{$")) {
					int stindex = dataValue.indexOf("{$");
					int endindex = dataValue.indexOf('}');
					if (stindex < 0 || endindex < 0) {
						break;
					}
					String keyVariable = dataValue.substring((stindex + 2),
							endindex);
					String value = getVariable(keyVariable);
					if (!value.equalsIgnoreCase(keyVariable)) {
						dataValue = dataValue.replace("{$" + keyVariable + "}",
								value);
					}

				} else {
					break;
				}
			}

		} catch (Exception e) {
			// Nothing to throw.
		}

		return dataValue;

	}
}