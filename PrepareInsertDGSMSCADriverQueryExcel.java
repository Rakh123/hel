package com.dvnr.excel.demo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PrepareInsertDGSMSCADriverQueryExcel {
	public static void main(String[] args) {
		String serverQuery = "INSERT INTO `mobile_user_details` (`first_name`, `last_name`, `username`, `password`, `email_id`, `phone_number`, `address`, `gst_hst_number`, `image_prefix`, `logo`, `user_type`, `company_name`, `app_name`, `safety_officer_mail_id`, `approval_status`, `mobile_type`, `license_period`, `user_creation_source`, `user_active_status_cart`, `user_order_id_cart`, `coupon_code`, `country_name`, `province_name`, `managername`, `drivertype`, `terminalname`) VALUES (?, ?, ?, ?, ?, ?, '', '', '', '', 'C', 'dayandross', 'dayandross', '', '1', '1', '365', '1', '1', '', '', 'Canada', ?,  ?, '', ?)";
		String clientQuery = "INSERT INTO `mobile_users` (`first_name`, `last_name`, `username`, `email_id`, `phone_number`, `mobile_type`, `active_status`, `managername`, `drivertype`, `terminalname`) VALUES (?, ?, ?, ?, ?, '0', '1', ?, '', ?);	";
		try (FileInputStream file = new FileInputStream(new File("howtodoinjava_demo.xlsx"));
				Connection con = DatabaseConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(serverQuery);
				PreparedStatement ps1 = con.prepareStatement(clientQuery);
				BufferedWriter writer = Files
						.newBufferedWriter(Paths.get("/home/nageswararao/Documents/Queries/causerinsertqueries.txt"))) {

			StringBuilder sb = new StringBuilder();
			sb.append("------------------ Server Queries ------------------");
			sb.append("\n");
			StringBuilder sb1 = new StringBuilder();
			sb1.append("------------------ Client Queries ------------------");
			sb1.append("\n");

//			con.setAutoCommit(false);

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				int rowNum = row.getRowNum();
				System.out.println("Row No : " + rowNum);
				// Skip head row
				if (rowNum != 0) {
					// For each row, iterate through all the columns
					Iterator<Cell> cellIterator = row.cellIterator();
					String fName = "";
					String lname = "";
					String userName = "";
					String password = "";
					String email = "";
					String phNum = "";
					String mName = "";
					String terminal = "";
					String provins = "";
					int count = 0;
					while (cellIterator.hasNext()) {
						count++;
						Cell cell = cellIterator.next();
						int conIdx = cell.getColumnIndex();
						System.out.print("col " + conIdx + " -> ");
						// Check the cell type and format accordingly
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							if (conIdx == 0) {
								fName = String.valueOf(cell.getNumericCellValue()).trim();
								System.out.print(fName + "\t");
							} else if (conIdx == 1) {
								lname =  row.getCell(1).toString();
								System.out.print(lname + "\t");
							} else if (conIdx == 2) {
								phNum = row.getCell(2).toString();
								
								System.out.print(phNum + "\t");
							} else if (conIdx == 3) {
								email = String.valueOf(cell.getNumericCellValue()).trim();
								System.out.print(email + "\t");
							} else if (conIdx == 4) {
								terminal = String.valueOf(cell.getNumericCellValue()).trim();
								System.out.print(terminal + "\t");
							} else if (conIdx == 5) {
								provins = String.valueOf(cell.getNumericCellValue()).trim();
								System.out.print(provins + "\t");
							} else if (conIdx == 6) {
								mName = String.valueOf(cell.getNumericCellValue()).trim();
								System.out.print(mName + "\t");
							}
							break;
						case Cell.CELL_TYPE_STRING:
							if (conIdx == 0) {
								fName = cell.getStringCellValue().trim();
								System.out.print(fName + "\t");
							} else if (conIdx == 1) {
								lname = cell.getStringCellValue().trim();
								System.out.print(lname + "\t");
							} else if (conIdx == 2) {
								phNum = cell.getStringCellValue().trim();
								System.out.print(phNum + "\t");
							} else if (conIdx == 3) {
								email = cell.getStringCellValue().trim();
								System.out.print(email + "\t");
							} else if (conIdx == 4) {
								terminal = cell.getStringCellValue().trim();
								System.out.print(terminal + "\t");
							} else if (conIdx == 5) {
								provins = cell.getStringCellValue().trim();
								System.out.print(provins + "\t");
							} else if (conIdx == 6) {
								mName = cell.getStringCellValue().trim();
								System.out.print(mName + "\t");
							}
							break;
						case Cell.CELL_TYPE_BLANK:
							System.out.print(cell + "\t");
							break;
						}
					}
					System.out.println();
					System.out.println("no.of cells : " + count);
					// create user name and passed
					userName = fName;
					userName += RandomStringUtils.random(3, false, true);
					System.out.println("uName : " + userName);
					password = PasswordEncryptionDecryption.encrypt(userName);
					System.out.println("password : " + password);
					ps.setString(1, fName);
					ps.setString(2, lname);
					ps.setString(3, userName);
					ps.setString(4, password);
					ps.setString(5, email);
					ps.setString(6, phNum);
					ps.setString(7, provins);
					ps.setString(8, mName);
					ps.setString(9, terminal);
					System.out.println(ps.toString().split(":")[1]);
					sb.append(ps.toString().split(":")[1]);
					sb.append(";");
					sb.append("\n");
					ps1.setString(1, fName);
					ps1.setString(2, lname);
					ps1.setString(3, userName);
					ps1.setString(4, email);
					ps1.setString(5, phNum);
					ps1.setString(6, mName);
					ps1.setString(7, terminal);
					System.out.println(ps1.toString().split(":")[1]);
					sb1.append(ps1.toString().split(":")[1]);
					sb1.append(";");
					sb1.append("\n");
//					int cnt = ps.executeUpdate();
//					respList.add(cnt);
					// ps.addBatch();
				} // if
			} //

//			System.out.println("final : "+ps.toString());
			// long[] exeCount = ps.executeLargeBatch();
//			con.commit();
//			System.out.println("exeCount : "+exeCount.length);
//			System.out.println("exeCount : "+respList.size());
//			writer.write(sb.toString());
//			writer.write(sb1.toString());
		} catch (Exception e) {
			// con.rollback();
			e.printStackTrace();
		}
	}
}
