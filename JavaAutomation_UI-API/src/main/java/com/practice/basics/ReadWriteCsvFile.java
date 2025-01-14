package com.practice.basics;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class ReadWriteCsvFile {
    public static void main(String[] args) {
        String excelPath = System.getProperty("user.dir") + "/output/" + "excelfile.xls";
        String[] siteHeaders={"user_ref_id","partner_type","location"};
        //get current directory path
        File rootDir = new File(".").getAbsoluteFile().getParentFile();
        String sitesFile = rootDir+"/output/"+"NEW_CSV.csv";

        //CREATE CSV file with headers
        try(FileWriter writer=new FileWriter(sitesFile);
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.builder().setHeader(siteHeaders).build())){
        }catch(IOException e){
            e.printStackTrace();
        }

        //READ CSV
        try(
                FileReader reader=new FileReader(rootDir+"/output/"+"CSVReadSample.csv");
                CSVParser csvParser = new CSVParser(reader,CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).build())
                ){
            for(CSVRecord csvRecord:csvParser) {
                //CSV has columns "user_ref_id","partner_type"
                String user_ref_id = csvRecord.get("user_ref_id");
                String partner_type = csvRecord.get("partner_type");
                System.out.println("Read csv output");
                System.out.println("user_ref_id and partner_type "+user_ref_id+" "+partner_type);
            }

        }catch (IOException e){
            e.printStackTrace();
        }

        //WRITE OR APPEND TO CSV --- here true means Append Mode
        try(FileWriter writer=new FileWriter(sitesFile,true);
            CSVPrinter csvPrinter = new CSVPrinter(writer,CSVFormat.DEFAULT)){
            List<List> sites = Arrays.asList(Arrays.asList("ABC","EFG"),Arrays.asList("HIJ","LMN"));
            for(List i: sites){
                csvPrinter.printRecord(i);
            }
            csvPrinter.flush();

        }catch (IOException e){
            e.printStackTrace();
        }


    }
}
