package com.practice.basics;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReadWriteExcelFile {
    public static void main(String[] args) {
        String excelPath = System.getProperty("user.dir") + "/output/" + "excelfile.xls";
        /*
        CREATE NEW EXCEL FILE & WRITE COUPLE OF ROWS TO IT
         */
        try (Workbook workbook = new HSSFWorkbook(); //HSSFWorkbook for .xls AND XSSFWorkbook for .xlsx
             FileOutputStream fileOutputStream = new FileOutputStream(excelPath)) {
            Sheet sheet = workbook.createSheet("Data"); //for reading getSheetAt(0) OR getSheet("sheetName")
            Row headerRow = sheet.createRow(0); //for reading sheet.getRow(0)
            String[] headers = {"ID", "Name", "Age"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i); //for reading - getCell(columnIndex)
                cell.setCellValue(headers[i]); //for reading - getNumericCellValue, getStringCellValue
            }
            Object[][] data = {
                    {1, "Alice", 30},
                    {2, "Bob", 25}
            };
            for (int i = 0; i < data.length; i++) {
                Row row = sheet.createRow(i + 1);
                for (int j = 0; j < data[i].length; j++) {
                    Cell cell = row.createCell(j);
                    if (data[i][j] instanceof String) {
                        cell.setCellValue((String) data[i][j]);
                    } else if (data[i][j] instanceof Integer) {
                        cell.setCellValue((Integer) data[i][j]);
                    }
                }
            }
            workbook.write(fileOutputStream);
            System.out.println("New excel created with 2 rows");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /*
        READ EXCEL FILE
         */
        try (FileInputStream fileInputStream = new FileInputStream(excelPath);
             Workbook workbook = new HSSFWorkbook(fileInputStream);
        ) {
            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            int idCol = -1;
            int nameCol = -1;
            int ageCol = -1;
            for (Cell cell : headerRow) {
                if ("ID".equals(cell.getStringCellValue())) {
                    idCol = cell.getColumnIndex();
                } else if ("Name".equals(cell.getStringCellValue())) {
                    nameCol = cell.getColumnIndex();
                } else if ("Age".equals(cell.getStringCellValue())) {
                    ageCol = cell.getColumnIndex();
                }
            }

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                System.out.println(sheet.getRow(i).getCell(idCol).getNumericCellValue() + " -- " +
                        sheet.getRow(i).getCell(nameCol).getStringCellValue() + " -- " +
                        sheet.getRow(i).getCell(ageCol).getNumericCellValue());
            }

            /*
            ADDING NEW COLUMN TO THE EXCEL AND WRITING BACK TO THE SAME EXCEL
            */
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;
                Cell newCell = row.createCell(3); // New column index
                if (i == 0) {
                    newCell.setCellValue("Salary"); // Header for new column
                } else {
                    newCell.setCellValue(Math.random() * 1000); // Random value for new column
                }
            }
            try (FileOutputStream fileOut = new FileOutputStream(excelPath)) {
                workbook.write(fileOut);
                System.out.println("Updated Excel file successfully!");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
