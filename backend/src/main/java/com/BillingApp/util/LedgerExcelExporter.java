package com.BillingApp.util;

import com.BillingApp.DTO.LedgerEntryDTO;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class LedgerExcelExporter {

    public static ByteArrayInputStream exportToExcel(List<LedgerEntryDTO> ledger) throws IOException {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Ledger");

            // Header
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Date");
            headerRow.createCell(1).setCellValue("Description");
            headerRow.createCell(2).setCellValue("Debit");
            headerRow.createCell(3).setCellValue("Credit");
            headerRow.createCell(4).setCellValue("Balance");

            int rowIdx = 1;
            for (LedgerEntryDTO entry : ledger) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(entry.getDate().toString());
                row.createCell(2).setCellValue(entry.getDebit().doubleValue());
                row.createCell(3).setCellValue(entry.getCredit().doubleValue());
                row.createCell(4).setCellValue(entry.getBalance().doubleValue());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
