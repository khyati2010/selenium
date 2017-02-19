/**
 * 
 */
package reporting;

/**
 * @author Nayan : Manage the BDD Step Creation.
 *
 */
public class BddStepCreationControler {

	private String fileformat;
	
	public static IBddStepFormation objStepFormation = null;
	
	public BddStepCreationControler(String fileformat){
		this.fileformat = fileformat.toUpperCase();
	}
	
	public void buildStepCreationObject(){
		
		if(this.fileformat.equals("EXCEL")){
			objStepFormation = new BDDStepTrackInExcel();
		}
		//Will support more formats if required.
		
	}
	
	
	
}
