package test.testDataProvider;

import org.testng.annotations.DataProvider;

import main.java.amazonfilterapplicatione2e.fileReader.ExcelReader;

public class TestDataProvider {

	 @DataProvider(name = "ExcelData",indices = {0})
	    public Object[][] excelData() {
	        return ExcelReader.readSingleColumn("data/Products.xlsx", "Products");
	    }
	 
}
