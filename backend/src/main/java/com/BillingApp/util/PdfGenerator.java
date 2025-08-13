package com.BillingApp.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

import com.BillingApp.DTO.ProfitLossReportDTO;
import com.BillingApp.model.Expense;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfGenerator {

    public static ByteArrayInputStream generateProfitLossReport(ProfitLossReportDTO dto) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font headerFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("Profit & Loss Report", headerFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(80);

            table.addCell("Total Sales");
            table.addCell(dto.getTotalSales().toString());

            table.addCell("Total Purchase");
            table.addCell(dto.getTotalPurchase().toString());

            table.addCell("Total Expense");
            table.addCell(dto.getTotalExpense().toString());

            table.addCell("Net Profit/Loss");
            table.addCell(dto.getProfitOrLoss().toString());

            document.add(table);
            document.close();
        } catch (DocumentException e) {
            throw new RuntimeException("Error creating PDF", e);
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
    
    public static ByteArrayInputStream exportExpensesToPDF(List<Expense> expenses) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font header = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            Paragraph title = new Paragraph("Expense Report", header);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            Stream.of("ID", "Description", "Amount", "Date", "Category")
                    .forEach(col -> {
                        PdfPCell cell = new PdfPCell(new Phrase(col));
                        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        table.addCell(cell);
                    });

            for (Expense exp : expenses) {
                table.addCell(String.valueOf(exp.getId()));
                table.addCell(exp.getDescription());
                table.addCell(String.valueOf(exp.getAmount()));
                table.addCell(String.valueOf(exp.getDate()));
                table.addCell(exp.getCategory().getName());
            }

            document.add(table);
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
    

