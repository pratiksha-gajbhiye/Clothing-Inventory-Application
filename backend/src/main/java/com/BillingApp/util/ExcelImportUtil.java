package com.BillingApp.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.BillingApp.model.Brand;
import com.BillingApp.model.Category;
import com.BillingApp.model.Item;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelImportUtil {

    public static List<Item> parseExcelFile(InputStream is, Brand brand, Category category) throws Exception {
        List<Item> itemList = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) { // skip header
            Row row = sheet.getRow(i);
            if (row == null || row.getCell(0) == null) continue;

            Item item = new Item();
            item.setName(row.getCell(0).getStringCellValue());
            item.setSize(row.getCell(1).getStringCellValue());
            item.setColor(row.getCell(2).getStringCellValue());
//            item.setPrice(row.getCell(3).getNumericCellValue());
            item.setStock((int) row.getCell(4).getNumericCellValue());
            item.setBrand(brand);
            item.setCategory(category);

            itemList.add(item);
        }

        workbook.close();
        return itemList;
    }
}
