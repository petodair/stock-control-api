package br.com.stock_control_api.service.batch;

import br.com.stock_control_api.dto.batch.BatchResponseDTO;
import br.com.stock_control_api.entity.Batch;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Service
public class BatchPdfServiceImpl implements BatchPdfService {

    @Override
    public byte[] generatePdf(List<BatchResponseDTO> batches) {
        Document document = new Document();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("Relatório de Lotes", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" "));

            PdfPTable table = getPdfPTable(batches);

            document.add(table);
            document.close();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF", e);
        }

        return out.toByteArray();
    }

    private static @NonNull PdfPTable getPdfPTable(List<BatchResponseDTO> batches) {
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);

        table.addCell("Produto");
        table.addCell("Quantidade");
        table.addCell("Lote");
        table.addCell("Validade");
        table.addCell("Local");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        NumberFormat numberFormat = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);

        for (BatchResponseDTO batch : batches) {
            table.addCell(batch.productName());

            String quantity = batch.quantity() != null
                    ? numberFormat.format(batch.quantity())
                    : "0,00";

            table.addCell(quantity);

            table.addCell(batch.batchNumber());
            table.addCell(batch.expirationDate().format(formatter));
            table.addCell(batch.batchLocal().getDescricao());
        }
        return table;
    }
}
