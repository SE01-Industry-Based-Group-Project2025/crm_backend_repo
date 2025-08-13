package com.example.CRM.controller;

import com.example.CRM.model.Customer;
import com.example.CRM.service.CustomerService1;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;

@RestController
public class ReportController {

    private final CustomerService1 customerService;

    public ReportController(CustomerService1 customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/generate-report")
    public ResponseEntity<byte[]> generateReport(@RequestParam Long userId) {
        Customer customer = customerService.getCustomerById(userId);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }

        byte[] pdf = buildPdf(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=report_" + userId + ".pdf");

        return ResponseEntity.ok().headers(headers).body(pdf);
    }

    private byte[] buildPdf(Customer c) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document doc = new Document(pdfDoc);

        doc.add(new Paragraph("Customer Report").setBold());
        doc.add(new Paragraph("ID: " + c.getId()));
        doc.add(new Paragraph("First Name: " + c.getFirstName()));
        doc.add(new Paragraph("Last Name: " + c.getLastName()));
        doc.add(new Paragraph("Email: " + c.getEmail()));
        doc.add(new Paragraph("Phone: " + c.getPhone()));

        doc.close();
        return baos.toByteArray();
    }
}
