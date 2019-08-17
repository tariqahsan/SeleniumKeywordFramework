package org.isb.training.selenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import jxl.Sheet;
import jxl.read.biff.BiffException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.zia.training.selenium.keyword.ExcelSheetDriver;

public class ApachePOIExcelRead {

	private static final String FILE_NAME = "./Data/excelExportAndFileIO/TestDataFile.xlsx";

    public static void main(String[] args) throws BiffException, IOException {
    	ExcelSheetDriver excelSheetDriver = new ExcelSheetDriver();
    	Sheet testSuitesheet = excelSheetDriver.getWorksheet(FILE_NAME, "TestCase1");
//    	System.out.println(testSuitesheet);
//        try {
////        	String filePath = System.getProperty("user.dir")+"\\data\\excelExportAndFileIO";
////            FileInputStream excelFile = new FileInputStream(new File("./Data/excelExportAndFileIO/TestDataFile.xlsx"));
//            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
//            //fileINputStream does read all files line by line and make it binary format
////            FileOutputStream>>for reading purpose
//            Workbook workbook = new XSSFWorkbook(excelFile);
//            Sheet datatypeSheet = workbook.getSheetAt(0);
//            Iterator<Row> iterator = datatypeSheet.iterator();
//
//            while (iterator.hasNext()) {
//
//                Row currentRow = iterator.next();
//                Iterator<Cell> cellIterator = currentRow.iterator();
//
//                while (cellIterator.hasNext()) {
//
//                    Cell currentCell = cellIterator.next();
//                    //getCellTypeEnum shown as deprecated for version 3.15
//                    //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
//                    if (currentCell.getCellTypeEnum() == org.apache.poi.ss.usermodel.CellType.STRING) {
//                        System.out.print(currentCell.getStringCellValue() + "--");
//                    } else if (currentCell.getCellTypeEnum() == org.apache.poi.ss.usermodel.CellType.NUMERIC) {
//                        System.out.print(currentCell.getNumericCellValue() + "--");
//                    }
//
//                }
//                System.out.println();
//
//            }
////        } catch (FileNotFoundException e) {
////            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}