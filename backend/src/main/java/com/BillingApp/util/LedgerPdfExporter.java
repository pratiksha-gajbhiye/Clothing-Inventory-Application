package com.BillingApp.util;


import com.BillingApp.DTO.LedgerEntryDTO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

public class LedgerPdfExporter {

    public static ByteArrayInputStream exportToPdf(List<LedgerEntryDTO> ledger) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Paragraph title = new Paragraph("Ledger Report", font);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{2, 2, 2, 3, 2, 2, 2});

            Stream.of("Date", "Type", "Mode", "Note", "Credit", "Debit", "Balance")
                    .forEach(header -> {
                        PdfPCell cell = new PdfPCell(new Phrase(header));
                        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                    });

            for (LedgerEntryDTO dto : ledger) {
                table.addCell(dto.getDate().toString());
                table.addCell(dto.getType());
                table.addCell(dto.getPaymentMode());
                table.addCell(dto.getNote());
                table.addCell(dto.getCredit().toString());
                table.addCell(dto.getDebit().toString());
                table.addCell(dto.getBalance().toString());
            }

            document.add(table);
            document.close();
        } catch (DocumentException e) {
            throw new RuntimeException("PDF generation failed", e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
