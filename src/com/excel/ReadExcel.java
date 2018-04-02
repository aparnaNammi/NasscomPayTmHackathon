package com.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

import com.pojo.UserProfileDetails;

public class ReadExcel {
	
	
 //private String filepath ="UserProfileBank.xls";
	public UserProfileDetails readExcelData(String mobileNumber, String filePath) throws IOException {

		try {
			//ClassLoader loader = this.getClass().getResource("UserProfileBank.xls");
			//FileInputStream file = loader.getResourceAsStream("UserProfileBank.xls");

			FileInputStream file = new FileInputStream(new File(filePath));
			
			System.out.println(file);

			// Get the workbook instance for XLS file
			HSSFWorkbook workbook = new HSSFWorkbook(file);

			// Get first sheet from the workbook
			HSSFSheet sheet = workbook.getSheetAt(0);

			UserProfileDetails profDetails = new UserProfileDetails();
			//String inputMobNum = "9490167336";
			int rowNum = 0;
			// Iterate through each rows from first sheet
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);

				// For each row, iterate through each columns
				for (int j = 0; j <= row.getLastCellNum(); j++) {

					Cell cell = row.getCell(1);
					if (cell != null) {
						cell.setCellType(CellType.STRING);
						switch (cell.getCellTypeEnum()) {
						case STRING:
							if (mobileNumber.equals(cell.getStringCellValue())) {
								rowNum = i;
								
								break;
							}
							break;
						}

					}

				}
			}
			Row rowReq = sheet.getRow(rowNum);
			if (rowReq != null) {
				for (int j = 0; j <= rowReq.getLastCellNum(); j++) {
					Cell cell = rowReq.getCell(j);
					if (cell != null) {
						cell.setCellType(CellType.STRING);
						switch (cell.getCellTypeEnum()) {
						case STRING:
							if (j == 0) {
								profDetails.setProfileId(cell
										.getStringCellValue() == null ? ""
										: cell.getStringCellValue());
							} else if (j == 1) {
								profDetails.setMobileNum(cell
										.getStringCellValue() == null ? ""
										: cell.getStringCellValue());
							} else if (j == 2) {
								profDetails.setFirstName(cell
										.getStringCellValue() == null ? ""
										: cell.getStringCellValue());
							} else if (j == 3) {
								profDetails.setMiddleName(cell
										.getStringCellValue() == null ? ""
										: cell.getStringCellValue());
							} else if (j == 4) {
								profDetails.setLastName(cell
										.getStringCellValue() == null ? ""
										: cell.getStringCellValue());
							} else if (j == 5) {
								profDetails
										.setGender(cell
												.getStringCellValue() == null ? ""
														: cell.getStringCellValue());
							} else if (j == 6) {
								DataFormatter dataFormatter = new DataFormatter();
								String cellStringValue = dataFormatter.formatCellValue(cell);

								profDetails.setAge(cellStringValue);
							} else if (j == 7) {
								profDetails.setFatherName(cell
										.getStringCellValue() == null ? ""
												: cell.getStringCellValue());
							} else if (j == 8) {
								profDetails.setAadharNum(cell
										.getStringCellValue() == null ? ""
												: cell.getStringCellValue());
							} else if (j == 9) {
								profDetails.setCurrentAddress(cell
										.getStringCellValue() == null ? ""
												: cell.getStringCellValue());
							} else if (j == 10) {
								profDetails.setEmergencyContactName1(cell
										.getStringCellValue() == null ? ""
												: cell.getStringCellValue());
							} else if (j == 11) {
								profDetails.setEmergencyContactNum1(cell
										.getStringCellValue() == null ? ""
												: cell.getStringCellValue());
							} else if (j == 12) {
								profDetails.setEmergencyContactName2(cell
										.getStringCellValue() == null ? ""
												: cell.getStringCellValue());
							} else if (j == 13) {
								profDetails.setEmergencyContactNum2(cell
										.getStringCellValue() == null ? ""
												: cell.getStringCellValue());
							} else if (j == 14) {
								profDetails.setEmergencyContactName3(cell
										.getStringCellValue() == null ? ""
												: cell.getStringCellValue());
							} else if (j == 15) {
								profDetails.setEmergencyContactNum3(cell
										.getStringCellValue() == null ? ""
												: cell.getStringCellValue());
							} else if (j == 16) {
								profDetails.setFamilyDoctorName(cell
										.getStringCellValue() == null ? ""
												: cell.getStringCellValue());
							} else if (j == 17) {
								profDetails.setFamilyDocNum(cell
										.getStringCellValue() == null ? ""
												: cell.getStringCellValue());
							} else if (j == 18) {
								profDetails.setCriticalIllness(cell
										.getStringCellValue() == null ? ""
												: cell.getStringCellValue());
							} else if (j == 19) {
								profDetails.setHistoricHealthEvents(cell
										.getStringCellValue() == null ? ""
												: cell.getStringCellValue());
							} else if (j == 20) {
								profDetails.setFamilyMedicalBackground(cell
										.getStringCellValue() == null ? ""
												: cell.getStringCellValue());
							}
							break;
						}
					}
				}
			}
			file.close();
			
			return profDetails;
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}

	}
}
