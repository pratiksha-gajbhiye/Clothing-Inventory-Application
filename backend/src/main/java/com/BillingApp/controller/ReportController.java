package com.BillingApp.controller;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BillingApp.DTO.ExpenseDTO;
import com.BillingApp.DTO.ItemSalesReportDTO;
import com.BillingApp.DTO.ProfitLossReportDTO;
import com.BillingApp.DTO.StockReportDTO;
import com.BillingApp.services.ReportService;
import com.BillingApp.util.ExcelExportUtil;
import com.BillingApp.util.PdfGenerator;
import org.springframework.http.MediaType;


@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired private ReportService reportService;

    @GetMapping("/profit-loss")
    public ResponseEntity<ProfitLossReportDTO> getProfitLoss(
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return ResponseEntity.ok(reportService.getProfitLossReport(from, to));
    }

    @GetMapping("/item-sales")
    public ResponseEntity<List<ItemSalesReportDTO>> getItemWiseSales(
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return ResponseEntity.ok(reportService.getItemWiseSales(from, to));
    }

    @GetMapping("/item-purchase")
    public ResponseEntity<List<ItemSalesReportDTO>> getItemWisePurchase(
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return ResponseEntity.ok(reportService.getItemWisePurchase(from, to));
    }

    @GetMapping("/stock")
    public ResponseEntity<List<StockReportDTO>> getStockReport() {
        return ResponseEntity.ok(reportService.getStockReport());
    }

    @GetMapping("/expenses")
    public ResponseEntity<List<ExpenseDTO>> getExpenseReport(
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return ResponseEntity.ok(reportService.getExpenseReport(from, to));
    }
    
    @GetMapping("/export/profit-loss/pdf")
    public ResponseEntity<InputStreamResource> exportProfitLossPdf(
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {

        ProfitLossReportDTO report = reportService.getProfitLossReport(from, to);
        ByteArrayInputStream pdfStream = PdfGenerator.generateProfitLossReport(report);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=profit_loss_report.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdfStream));
    }
    
    @GetMapping("/export/stock-report/excel")
    public ResponseEntity<InputStreamResource> exportStockExcel() {
        List<StockReportDTO> list = reportService.getStockReport();
        ByteArrayInputStream in = ExcelExportUtil.exportStockReportToExcel(list);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=stock_report.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(in));
    }


}
