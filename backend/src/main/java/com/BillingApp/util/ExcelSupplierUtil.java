package com.BillingApp.util;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.BillingApp.DTO.SupplierDTO;

import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelSupplierUtil {

    // Import
    public static List<SupplierDTO> parseExcelFile(InputStream is) {
        List<SupplierDTO> supplierList = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header

                SupplierDTO dto = new SupplierDTO();
                dto.setName(row.getCell(0).getStringCellValue());
                dto.setMobile(row.getCell(1).getStringCellValue());
                dto.setEmail(row.getCell(2).getStringCellValue());
                dto.setPhone(row.getCell(3).getStringCellValue());
                dto.setGstNumber(row.getCell(4).getStringCellValue());
                dto.setTaxNumber(row.getCell(5).getStringCellValue());
                dto.setCountry(row.getCell(6).getStringCellValue());
                dto.setState(row.getCell(7).getStringCellValue());
                dto.setCity(row.getCell(8).getStringCellValue());
                dto.setPostcode(row.getCell(9).getStringCellValue());
                dto.setAddress(row.getCell(10).getStringCellValue());
                dto.setOpeningBalance(row.getCell(11).getNumericCellValue() == 0 ? null : 
                        new java.math.BigDecimal(row.getCell(11).getNumericCellValue()));

                supplierList.add(dto);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Excel: " + e.getMessage());
        }
        return supplierList;
    }

    // Export
    public static ByteArrayInputStream exportToExcel(List<SupplierDTO> suppliers) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Suppliers");
            Row header = sheet.createRow(0);
            String[] columns = {"Name", "Mobile", "Email", "Phone", "GST", "TAX", "Country", "State", "City", "Postcode", "Address", "Opening Balance"};
            for (int i = 0; i < columns.length; i++) {
                header.createCell(i).setCellValue(columns[i]);
            }

            int rowIdx = 1;
            for (SupplierDTO dto : suppliers) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(dto.getName());
                row.createCell(1).setCellValue(dto.getMobile());
                row.createCell(2).setCellValue(dto.getEmail());
                row.createCell(3).setCellValue(dto.getPhone());
                row.createCell(4).setCellValue(dto.getGstNumber());
                row.createCell(5).setCellValue(dto.getTaxNumber());
                row.createCell(6).setCellValue(dto.getCountry());
                row.createCell(7).setCellValue(dto.getState());
                row.createCell(8).setCellValue(dto.getCity());
                row.createCell(9).setCellValue(dto.getPostcode());
                row.createCell(10).setCellValue(dto.getAddress());
                row.createCell(11).setCellValue(dto.getOpeningBalance() != null ? dto.getOpeningBalance().doubleValue() : 0.0);
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        } catch (Exception e) {
            throw new RuntimeException("Failed to export Excel: " + e.getMessage());
        }
    }
}
