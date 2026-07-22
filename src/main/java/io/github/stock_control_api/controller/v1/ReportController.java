package io.github.stock_control_api.controller.v1;

import io.github.stock_control_api.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/batch/pdf")
    public ResponseEntity<byte[]> getBatchReport(){
        try{
            byte[] pdfBytes = reportService.generateBatchReport();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=relatorio_de_estoque.pdf");
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/product/pdf")
    public ResponseEntity<byte[]> getProductReport(){
        try{
            byte[] pdfBytes = reportService.generateProductReport();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=relatorio_de_estoque.pdf");
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
