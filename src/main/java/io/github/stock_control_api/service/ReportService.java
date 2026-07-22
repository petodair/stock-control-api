package io.github.stock_control_api.service;


import io.github.stock_control_api.repository.BatchRepository;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final DataSource dataSource;

    @Value("classpath:reports/batches.jrxml")
    Resource batchReportResource;

    @Value("classpath:reports/product.jrxml")
    Resource productReportResource;

    public byte[] generateBatchReport(
    ) throws Exception {
        try (InputStream input = batchReportResource.getInputStream()) {
            JasperReport jasperReport = JasperCompileManager.compileReport(input);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("ORDER_BY", "validity DESC");
            parameters.put(
                    "LOGO_TEXT",
                    getClass().getResourceAsStream("/images/petercode-text.png")
            );
            parameters.put(
                    "LOGO",
                    getClass().getResourceAsStream("/images/petercode-logo.png")
            );
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    parameters,
                    dataSource.getConnection()
            );
            return JasperExportManager.exportReportToPdf(jasperPrint);
        }
    }

    public byte[] generateProductReport(
    ) throws Exception {
        try (InputStream input = productReportResource.getInputStream()) {
            JasperReport jasperReport = JasperCompileManager.compileReport(input);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put(
                    "LOGO_TEXT",
                    getClass().getResourceAsStream("/images/petercode-text.png")
            );
            parameters.put(
                    "LOGO",
                    getClass().getResourceAsStream("/images/petercode-logo.png")
            );
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    parameters,
                    dataSource.getConnection()
            );
            return JasperExportManager.exportReportToPdf(jasperPrint);
        }
    }
}
