/**
 * 
 */
package reporting;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.poi.hssf.model.Workbook;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import Common.Property;


/**
 * @author Nayan  :  Create BDD Step in Excel Format.
 *
 */
public class BDDStepTrackInExcel implements IBddStepFormation {

	private HSSFWorkbook wb = new HSSFWorkbook();
	private HSSFSheet sheet;
	private String Workbook = "";
	private static int rowNumber = 0;
	public void createFile(String Filename){
		
		Workbook = Property.BDDFile_Location + Filename;
		CreateWorkBook(Workbook);
		CreateSheet();
	}
	
	public void CreateHeader(){
	    // Create a row and put some cells in it. Rows are 0 based.
	    HSSFRow row = sheet.createRow(rowNumber);
	    rowNumber ++ ;
	    // Create a cell and put a value in it.
	    HSSFCell cellTestCaseID = row.createCell(0);
	    HSSFCell cellTestCaseStep = row.createCell(1);
	    HSSFCell cellBDDStep = row.createCell(2);
	    HSSFCell cellStatus = row.createCell(3);
	    HSSFCellStyle style =   wb.createCellStyle();
	    
	    
	    HSSFRichTextString TestCaseId = BoldContent("TestCaseID");
	    HSSFRichTextString TestStep = BoldContent("TestStep");
	    HSSFRichTextString BDDStepName = BoldContent("BDDStepName");
	    HSSFRichTextString Status = BoldContent("Status");
	    
	    cellTestCaseID.setCellValue(TestCaseId);
	    
	    cellTestCaseStep.setCellValue(TestStep);
	    cellBDDStep.setCellValue(BDDStepName);
	    cellStatus.setCellValue(Status);
	    SaveWorkBook();
	    }
	
	
	private HSSFRichTextString BoldContent(String content){
		HSSFFont font = wb.createFont();
		font.setBoldweight(font.BOLDWEIGHT_BOLD);
		
		HSSFRichTextString string = new HSSFRichTextString(content);
		string.applyFont(font);
		return string;
	}
	
	private HSSFRichTextString setGreen(String content){
		HSSFFont font = wb.createFont();
		
		font.setColor(HSSFColor.DARK_GREEN.index);
		HSSFRichTextString string = new HSSFRichTextString(content);
		string.applyFont(font);
		return string;
		
	}
	
	private HSSFRichTextString setRed(String content){
		HSSFFont font = wb.createFont();
		
		font.setColor(HSSFColor.DARK_RED.index);
		HSSFRichTextString string = new HSSFRichTextString(content);
		string.applyFont(font);
		return string;
		
	}	
	
	
	
	public void CreateContentRow(String TestCaseID,String TestStep,String BDDStep,String Status,boolean ISWrite)
	{
		if(ISWrite)
		{
			HSSFRow row = sheet.createRow(rowNumber);
			rowNumber ++ ;
			
			HSSFRichTextString status = null;
			if(Status.toLowerCase().equals(Property.PASS)) {
				status = setGreen(Status);
			}
			else if(Status.toLowerCase().equals(Property.FAIL)) {
				status = setRed(Status);
			}
			
			// Create a cell and put a value in it.
			HSSFCell cellTestCaseID = row.createCell(0);
			HSSFCell cellTestCaseStep = row.createCell(1);
			HSSFCell cellBDDStep = row.createCell(2);
			HSSFCell cellStatus = row.createCell(3);
			cellTestCaseID.setCellValue(TestCaseID);
			cellTestCaseStep.setCellValue(TestStep);
			cellBDDStep.setCellValue(BDDStep);
			cellStatus.setCellValue(status);
			SaveWorkBook();
	    }
	    
		
	}
	private void CreateWorkBook(String WorkbookName){
		try {	
			
			
			FileOutputStream out = new FileOutputStream(WorkbookName + ".xls");
			wb.write(out);
			out.close();
			} 
		catch (Exception e) {
			
		}
	}
	private void SaveWorkBook(){
		try{
		FileOutputStream out = new FileOutputStream(Workbook + ".xls");
		wb.write(out);
		out.close();
		}
		catch(Exception e){
			
		}
	}
	
	private void CreateSheet(){
	
		sheet =  wb.createSheet("Steps");
		SaveWorkBook();
	}
	
	
	

}
