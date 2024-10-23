package com.serverpet.server.Models;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import com.serverpet.server.DTO.FacturaDTO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


@Component
public class FacturaPDFGenerator {
    public byte[] generarFacturaPDF(FacturaDTO facturaDTO) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 750);
                contentStream.showText("Factura Veterinaria");
                contentStream.endText();

                contentStream.setFont(PDType1Font.HELVETICA, 12);
                escribirTexto(contentStream, "Nombre de la Mascota: " + facturaDTO.getNombreMascota(), 50, 700);
                escribirTexto(contentStream, "Dueño: " + facturaDTO.getNombrePropietario(), 50, 680);
                escribirTexto(contentStream, "Doctor: " + facturaDTO.getNombreDoctor(), 50, 660);
                escribirTexto(contentStream, "Motivo de la cita: " + facturaDTO.getMotivo(), 50, 640);
                escribirTexto(contentStream, "Diagnóstico: " + facturaDTO.getDiagnostico(), 50, 620);
                escribirTexto(contentStream, "Tratamiento: " + facturaDTO.getTratamiento(), 50, 600);
                escribirTexto(contentStream, "Fecha de la cita: " + facturaDTO.getFechaCita().toString(), 50, 580);
                escribirTexto(contentStream, "Monto total: $" + facturaDTO.getMontoTotal(), 50, 560);
            }
            try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                document.save(byteArrayOutputStream);
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    public byte[] generarReporteGeneral(List<FacturaDTO> facturaDTOs) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            try {

                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 750);
                contentStream.showText("Reporte General de Facturas");
                contentStream.endText();

                // Información de cada factura en la lista
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                float yPosition = 700;

                for (FacturaDTO factura : facturaDTOs) {
                    if (yPosition < 100) {
                        // Si la página está llena, cerrar el contentStream actual y agregar una nueva página
                        contentStream.close();
                        page = new PDPage(PDRectangle.A4);
                        document.addPage(page);
                        contentStream = new PDPageContentStream(document, page);  // Crear un nuevo contentStream para la nueva página
                        yPosition = 700;
                    }

                    // Escribir información de la factura
                    escribirTexto(contentStream, "Nombre de la Mascota: " + factura.getNombreMascota(), 50, yPosition);
                    escribirTexto(contentStream, "Dueño: " + factura.getNombrePropietario(), 50, yPosition - 20);
                    escribirTexto(contentStream, "Doctor: " + factura.getNombreDoctor(), 50, yPosition - 40);
                    escribirTexto(contentStream, "Motivo: " + factura.getMotivo(), 50, yPosition - 60);
                    escribirTexto(contentStream, "Diagnóstico: " + factura.getDiagnostico(), 50, yPosition - 80);
                    escribirTexto(contentStream, "Tratamiento: " + factura.getTratamiento(), 50, yPosition - 100);
                    escribirTexto(contentStream, "Monto: $" + factura.getMontoTotal(), 50, yPosition - 120);
                    escribirTexto(contentStream, "Fecha de la Cita: " + factura.getFechaCita().toString(), 50, yPosition - 140);

                    yPosition -= 160;  // Espacio entre facturas
                }

                contentStream.endText();
            } finally {
                contentStream.close();  // Cerrar el contentStream al final del bloque
            }

            // Convertir el documento a un array de bytes y devolverlo
            try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                document.save(byteArrayOutputStream);
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    // Método auxiliar para escribir texto en posiciones específicas
    private void escribirTexto(PDPageContentStream contentStream, String texto, float x, float y) throws IOException {
        contentStream.beginText();
        contentStream.newLineAtOffset(x, y);
        contentStream.showText(texto);
        contentStream.endText();
    }
}
