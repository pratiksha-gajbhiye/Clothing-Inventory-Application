package com.BillingApp.controller;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.BillingApp.DTO.SupplierDTO;
import com.BillingApp.model.Supplier;
import com.BillingApp.services.SupplierService;
import com.BillingApp.util.ExcelSupplierUtil;
import com.BillingApp.util.FileUploadUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Sheet; // ✅

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@CrossOrigin(origins = "http://localhost:3000") // 👈 allow React dev origin

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping
    public Supplier createSupplier(@RequestBody SupplierDTO dto) {
        return supplierService.createSupplier(dto);
    }

    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @GetMapping("/{id}")
    public Supplier getSupplier(@PathVariable Long id) {
        return supplierService.getSupplierById(id);
    }

    @PostMapping("/import-excel")
    public String importExcel(@RequestParam("file") MultipartFile file) {
        try {
            List<SupplierDTO> suppliers = ExcelSupplierUtil.parseExcelFile(file.getInputStream());
            supplierService.importSuppliers(suppliers);
            return "Import successful!";
        } catch (Exception e) {
            return "Import failed: " + e.getMessage();
        }
    }@GetMapping("/export-excel")
    public ResponseEntity<byte[]> exportSuppliers() {
        List<SupplierDTO> dtos = supplierService.getAllSuppliers()
            .stream()
            .map(s -> {
                SupplierDTO dto = new SupplierDTO();
                dto.setName(s.getName());
                dto.setMobile(s.getMobile());
                dto.setEmail(s.getEmail());
                dto.setPhone(s.getPhone());
                dto.setGstNumber(s.getGstNumber());
                dto.setTaxNumber(s.getTaxNumber());
                dto.setCountry(s.getCountry());
                dto.setState(s.getState());
                dto.setCity(s.getCity());
                dto.setPostcode(s.getPostcode());
                dto.setAddress(s.getAddress());
                dto.setOpeningBalance(s.getOpeningBalance());
                return dto;
            })
            .toList();

        byte[] excelBytes = ExcelSupplierUtil.exportToExcel(dtos).readAllBytes();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=suppliers.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excelBytes);
    }

    
    @GetMapping("/supplier-template")
    public ResponseEntity<byte[]> downloadSupplierTemplate() {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Suppliers");

        Row header = sheet.createRow(0);
        String[] columns = {
            "Name", "Mobile", "Email", "Phone", "GST", "TAX", "Country",
            "State", "City", "Postcode", "Address", "Opening Balance"
        };
        for (int i = 0; i < columns.length; i++) {
            header.createCell(i).setCellValue(columns[i]);
        }

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            workbook.write(out);
            workbook.close();

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=supplier_template.xlsx")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Error generating template", e);
        }
    }
 
  

    @PostMapping("/upload-photo/{supplierId}")
    public ResponseEntity<String> uploadPhoto(@PathVariable Long supplierId,
                                              @RequestParam("file") MultipartFile file) {
        Supplier supplier = supplierService.findById(supplierId);
        if (supplier == null) return ResponseEntity.notFound().build();

        try {
            String uploadPath = "uploads/suppliers/";
            String savedPath = FileUploadUtil.saveFile(uploadPath, file.getOriginalFilename(), file);
            supplier.setPhotoPath(savedPath);
            supplierService.save(supplier);

            return ResponseEntity.ok("Photo uploaded");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to upload: " + e.getMessage());
        }
    }


    @GetMapping("/due-payments")
    public List<Supplier> getSuppliersWithDuePayments() {
        return supplierService.getSuppliersWithDuePayments();
    }
}
