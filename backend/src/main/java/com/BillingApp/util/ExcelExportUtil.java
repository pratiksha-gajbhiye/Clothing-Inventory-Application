package com.BillingApp.util;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.BillingApp.DTO.StockReportDTO;
import com.BillingApp.model.Expense;
import com.BillingApp.model.Item;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelExportUtil {

    public static ByteArrayInputStream itemsToExcel(List<Item> items) throws IOException {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Items");

            // Header
            Row headerRow = sheet.createRow(0);
            String[] headers = { "ID", "Name", "Brand", "Category", "Size", "Color", "Price", "Stock" };
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Data rows
            int rowIdx = 1;
            for (Item item : items) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(item.getId());
                row.createCell(1).setCellValue(item.getName());
                row.createCell(2).setCellValue(item.getBrand().getName());
                row.createCell(3).setCellValue(item.getCategory().getName());
                row.createCell(4).setCellValue(item.getSize());
                row.createCell(5).setCellValue(item.getColor());
//                row.createCell(6).setCellValue(item.getPrice());
                row.createCell(7).setCellValue(item.getStock());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
        
        
        
    }
    
    public static ByteArrayInputStream exportStockReportToExcel(List<StockReportDTO> dataList) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Stock Report");
            Row headerRow = sheet.createRow(0);

            String[] headers = {"Item", "Qty", "Purchase Price", "Sale Price"};
            for (int i = 0; i < headers.length; i++) {
                headerRow.createCell(i).setCellValue(headers[i]);
            }

            int rowNum = 1;
            for (StockReportDTO dto : dataList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(dto.getItemName());
                row.createCell(1).setCellValue(dto.getQuantityAvailable());
                row.createCell(2).setCellValue(dto.getPurchasePrice().doubleValue());
                row.createCell(3).setCellValue(dto.getSalePrice().doubleValue());
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        } catch (IOException e) {
            throw new RuntimeException("Excel export error", e);
        }
    }
    
    public static ByteArrayInputStream exportExpensesToExcel(List<Expense> expenses) throws IOException {
        String[] columns = {"ID", "Description", "Amount", "Date", "Category"};
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Expenses");

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++)
            headerRow.createCell(i).setCellValue(columns[i]);

        int rowNum = 1;
        for (Expense exp : expenses) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(exp.getId());
            row.createCell(1).setCellValue(exp.getDescription());
            row.createCell(2).setCellValue(exp.getAmount().doubleValue());
            row.createCell(3).setCellValue(exp.getDate().toString());
            row.createCell(4).setCellValue(exp.getCategory().getName());
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();
        return new ByteArrayInputStream(out.toByteArray());
    }
}


