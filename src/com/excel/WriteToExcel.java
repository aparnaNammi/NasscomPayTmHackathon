package com.excel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import com.pojo.UserProfileDetails;

public class WriteToExcel {
   //We are making use of a single instance to prevent multiple write access to same file.
   private static final WriteToExcel INSTANCE = new WriteToExcel();

   public static WriteToExcel getInstance() {
       return INSTANCE;
   }
   
   public  void writeUserProfileToExcel(String filePath, UserProfileDetails userProfDetails){
	   FileInputStream file;
	try {
		file = new FileInputStream(new File(filePath));
	
		System.out.println(file);
		HSSFWorkbook workbook;
		workbook = new HSSFWorkbook(file);

       	// Using XSSF for xlsx format, for xls use HSSF
      	// Workbook workbook1 = new XSSFWorkbook();
       	
        HSSFSheet sheet = workbook.getSheetAt(0);
        int lastrow = sheet.getLastRowNum();
        int rowIndex = lastrow;
       
           Row row = sheet.createRow(rowIndex++);
           int cellIndex = 0;
           row.createCell(cellIndex++).setCellValue(userProfDetails.getProfileId());
           row.createCell(cellIndex++).setCellValue(userProfDetails.getMobileNum());
           row.createCell(cellIndex++).setCellValue(userProfDetails.getFirstName());
           row.createCell(cellIndex++).setCellValue(userProfDetails.getMiddleName());
           row.createCell(cellIndex++).setCellValue(userProfDetails.getLastName());
           row.createCell(cellIndex++).setCellValue(userProfDetails.getGender());
           row.createCell(cellIndex++).setCellValue(userProfDetails.getAge());
           row.createCell(cellIndex++).setCellValue(userProfDetails.getFatherName());
           row.createCell(cellIndex++).setCellValue(userProfDetails.getAadharNum());
           row.createCell(cellIndex++).setCellValue(userProfDetails.getCurrentAddress());
           row.createCell(cellIndex++).setCellValue(userProfDetails.getEmergencyContactName1());
           row.createCell(cellIndex++).setCellValue(userProfDetails.getEmergencyContactNum1());
           row.createCell(cellIndex++).setCellValue(userProfDetails.getEmergencyContactName2());
           row.createCell(cellIndex++).setCellValue(userProfDetails.getEmergencyContactNum2());
           row.createCell(cellIndex++).setCellValue(userProfDetails.getEmergencyContactName3());
           row.createCell(cellIndex++).setCellValue(userProfDetails.getEmergencyContactNum3());
           row.createCell(cellIndex++).setCellValue(userProfDetails.getFamilyDoctorName());
           row.createCell(cellIndex++).setCellValue(userProfDetails.getFamilyDocNum());
           row.createCell(cellIndex++).setCellValue(userProfDetails.getCriticalIllness());
           row.createCell(cellIndex++).setCellValue(userProfDetails.getHistoricHealthEvents());
           row.createCell(cellIndex++).setCellValue(userProfDetails.getFamilyMedicalBackground());
		
           FileOutputStream fos = new FileOutputStream(new File(filePath));
           workbook.write(fos);
           workbook.close();
           fos.close();
           System.out.println(filePath + " is successfully written");
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }


   }
}
