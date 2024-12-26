package com.scenario1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.*;
import org.testng.annotations.DataProvider;

public class readDataFromExcel {

	public XSSFWorkbook wb;

	@DataProvider(name = "excelData")
	public Object[][] testData() {
		File f1 = new File(System.getProperty("user.dir") + "//TestData//Data.xlsx");
		FileInputStream fs;
		try {
			fs = new FileInputStream(f1);
			wb = new XSSFWorkbook(fs);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// number of rows
		int rows = wb.getSheet("userdata").getPhysicalNumberOfRows();
		// number of cells
		int cells = wb.getSheet("userdata").getRow(0).getPhysicalNumberOfCells();
		// array of size that of file
		Object data[][] = new Object[rows - 1][cells]; // reading only 5 rows
		// iterate array and store data from file and save it in array
		for (int i = 1; i < rows; i++) {
			for (int j = 0; j < cells; j++) {
				// storing the data in (0,0)
				data[i - 1][j] = wb.getSheet("userdata").getRow(i).getCell(j).getStringCellValue();
				System.out.print(data[i - 1][j] + " ");
			}
			System.out.println();
		}
		return data;
	}

}
