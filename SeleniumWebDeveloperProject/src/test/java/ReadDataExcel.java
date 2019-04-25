import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ReadDataExcel {


	public static Object[][] testReadExcel(String sheetname) {

		// File name = new
		// File("C:\\Users\\Training_b6b.00.03\\eclipse-workspace\\SeleniumWebDeveloperProject\\src\\test\\resources\\datadrivenExcel.xlsx");
		// System.out.println(name.exists());
		// relative path
		File file = new File("src\\test\\resources\\datadrivenExcel.xlsx");
		
		Object[][] obj=null;
		
		try {
			InputStream is = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(is);
			XSSFSheet sheet1 = workbook.getSheet(sheetname);
			
			obj=new Object[sheet1.getLastRowNum()][];

			for (int i = 1; i <= sheet1.getLastRowNum(); i++) {
				
                 obj[i-1]=new Object[sheet1.getRow(i).getPhysicalNumberOfCells()];
                 
				for (int j = 0; j < sheet1.getRow(i).getPhysicalNumberOfCells(); j++) {

					//System.out.print(sheet1.getRow(i).getCell(j).getStringCellValue());
					
					obj[i-1][j]=sheet1.getRow(i).getCell(j).getStringCellValue();

				}
				// write a values into excel sheet
				//sheet1.getRow(i).createCell(2).setCellValue("vaild user");
				
				//System.out.println("");

			}
			is.close();
			//outputSTream
			/*OutputStream os = new FileOutputStream(file);
			workbook.write(os);
			os.close();*/

			workbook.close();

			// System.out.println(sheet1.getRow(1).getCell(0).getStringCellValue());

			// System.out.println(sheet1.getRow(1).getCell(1).getStringCellValue());

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;

	}
      

}
