package br.com.stock_control_api.service.batch;

import br.com.stock_control_api.dto.batch.BatchResponseDTO;
import br.com.stock_control_api.entity.Batch;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Service
public class BatchPdfServiceImpl implements BatchPdfService {

    @Override
    public byte[] generatePdf(List<BatchResponseDTO> batches) {
        Document document = new Document(PageSize.A4, 30, 30, 30, 30);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            PdfPTable headerTable = getHeaderTable();
            document.add(headerTable);

            document.add(new Paragraph(" "));

            PdfPTable table = getPdfPTable(batches);
            document.add(table);

            document.close();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF", e);
        }

        return out.toByteArray();
    }

    private PdfPTable getHeaderTable() {
        PdfPTable headerTable = new PdfPTable(2);
        headerTable.setWidthPercentage(100);

        try {
            headerTable.setWidths(new float[]{1.2f, 4f});
        } catch (DocumentException e) {
            throw new RuntimeException("Erro ao definir larguras do cabeçalho do PDF", e);
        }

        PdfPCell logoCell = new PdfPCell();
        logoCell.setBorder(Rectangle.NO_BORDER);
        logoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        logoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        logoCell.setPadding(5);

        try {
            Image logo = Image.getInstance(
                    Objects.requireNonNull(
                            getClass().getClassLoader().getResource("images/petercode-logo.png")
                    )
            );
            logo.scaleToFit(55, 55);
            logoCell.addElement(logo);
        } catch (Exception e) {
            Paragraph fallback = new Paragraph("P", new Font(Font.HELVETICA, 24, Font.BOLD));
            logoCell.addElement(fallback);
        }

        PdfPCell textCell = new PdfPCell();
        textCell.setBorder(Rectangle.NO_BORDER);
        textCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        textCell.setPaddingLeft(10);

        Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD, new Color(31, 41, 55));
        Font subtitleFont = new Font(Font.HELVETICA, 10, Font.NORMAL, Color.DARK_GRAY);

        Paragraph title = new Paragraph("Relatório de Lotes", titleFont);
        title.setSpacingAfter(4f);

        Paragraph subtitle = new Paragraph(
                "Emitido em: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                subtitleFont
        );

        textCell.addElement(title);
        textCell.addElement(subtitle);

        headerTable.addCell(logoCell);
        headerTable.addCell(textCell);

        return headerTable;
    }

    private static @NonNull PdfPTable getPdfPTable(List<BatchResponseDTO> batches) {
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);

        try {
            table.setWidths(new float[]{3.2f, 1.8f, 1.8f, 1.8f, 2.2f});
        } catch (DocumentException e) {
            throw new RuntimeException("Erro ao definir larguras da tabela do PDF", e);
        }

        table.addCell(createHeaderCell("Produto"));
        table.addCell(createHeaderCell("Quantidade"));
        table.addCell(createHeaderCell("Lote"));
        table.addCell(createHeaderCell("Validade"));
        table.addCell(createHeaderCell("Local"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        NumberFormat numberFormat = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);

        for (BatchResponseDTO batch : batches) {
            String productName = batch.productName() != null ? batch.productName() : "N/A";

            String quantity = batch.quantity() != null
                    ? numberFormat.format(batch.quantity())
                    : "0,00";

            String batchNumber = batch.batchNumber() != null ? batch.batchNumber() : "N/A";

            String expirationDate = batch.expirationDate() != null
                    ? batch.expirationDate().format(formatter)
                    : "N/A";

            String batchLocal = batch.batchLocal() != null
                    ? batch.batchLocal().getDescricao()
                    : "N/A";

            table.addCell(createBodyCell(productName, Element.ALIGN_LEFT));
            table.addCell(createBodyCell(quantity, Element.ALIGN_CENTER));
            table.addCell(createBodyCell(batchNumber, Element.ALIGN_CENTER));
            table.addCell(createBodyCell(expirationDate, Element.ALIGN_CENTER));
            table.addCell(createBodyCell(batchLocal, Element.ALIGN_CENTER));
        }

        return table;
    }

    private static PdfPCell createHeaderCell(String text) {
        Font font = new Font(Font.HELVETICA, 11, Font.BOLD, Color.WHITE);

        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(new Color(31, 41, 55));
        cell.setPadding(8f);

        return cell;
    }

    private static PdfPCell createBodyCell(String text, int horizontalAlignment) {
        Font font = new Font(Font.HELVETICA, 10, Font.NORMAL, Color.BLACK);

        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setHorizontalAlignment(horizontalAlignment);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(6f);

        return cell;
    }
}
