package utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * ExcelUtils
 * -----------
 * Utility class to read test data from Excel files for Data-Driven Testing (TestNG).
 *
 * Responsibilities:
 * - Open Excel (.xlsx) files and access sheets
 * - Read data row by row and column by column
 * - Convert all cell values to String to handle different cell types
 * - Return data as a 2D Object array suitable for TestNG @DataProvider
 * - Handle null cells by storing empty strings
 * - Close workbook and file streams to release resources
 */


public class ExcelUtils {
    
	/**
     * Reads data from Excel sheet and returns a 2D Object array
     * @param filePath  Path of the Excel file
     * @param sheetName Name of the sheet to read
     * @return Object[][] containing the sheet data
     * @throws IOException If file not found or cannot be read
     */
    public static Object[][] getExcelData(String filePath, String sheetName) throws IOException {

        FileInputStream file = new FileInputStream(filePath); // Open the Excel file as a FileInputStream
        XSSFWorkbook workbook = new XSSFWorkbook(file);  // Create a workbook instance for XLSX file
        XSSFSheet sheet = workbook.getSheet(sheetName); // Get the specific sheet by name

        int rowCount = sheet.getLastRowNum(); // Number of data rows
        int colCount = sheet.getRow(0).getLastCellNum(); // Number of columns

        Object[][] data = new Object[rowCount][colCount]; // Initialize a 2D array to store data: rows x columns
        DataFormatter formatter = new DataFormatter(); // DataFormatter converts any cell type (number, date, string) to String
        
        // Loop through rows starting from 1 (skip row 0 = header)
        for (int i = 1; i <= rowCount; i++) {
        	 // Loop through all columns in the current row
            for (int j = 0; j < colCount; j++) {
            	 // Check if row and cell are not null to avoid NullPointerException
                if (sheet.getRow(i) != null && sheet.getRow(i).getCell(j) != null) {
                	// Format the cell value as String and store in array
                    data[i - 1][j] = formatter.formatCellValue(sheet.getRow(i).getCell(j));
                } else {
                	// If cell is null, store empty string to keep array structure
                    data[i - 1][j] = "";   // to treat null as empty
                }
            }
        }

        workbook.close(); // Close workbook to release resources
        file.close(); // Close file input stream to release resources
        return data; // Return the populated data array
    }
}
