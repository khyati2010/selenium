package reporting;

import org.apache.log4j.Logger;

public class ReportCreation {
	
	private static Logger logger = Logger.getLogger(ReportCreation.class);
	
	public static void WriteLogMessage(String message){
		try{
		System.out.println(message);
		}
		catch(Exception e){
			System.out.println("Exception in logging");
		}
	}
	
	
	
	

}
