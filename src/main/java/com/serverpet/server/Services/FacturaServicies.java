package com.serverpet.server.Services;

import com.serverpet.server.DTO.FacturaDTO;
import com.serverpet.server.Models.HistoriEntity;
import com.serverpet.server.Repositories.HistoriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serverpet.server.Models.FacturaEntity;
import com.serverpet.server.Models.FacturaPDFGenerator;
import com.serverpet.server.Repositories.FacturaRepository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class FacturaServicies {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private HistoriRepository historiRepository;

    @Autowired
    private FacturaPDFGenerator pdfGenerator;

    public FacturaEntity crearFactura(Long historiaId, Double monto) {
        HistoriEntity historia = historiRepository.findById(historiaId)
                .orElseThrow(() -> new RuntimeException("Historia no encontrada"));


        FacturaEntity factura = new FacturaEntity();
        factura.setHistorieEntity(historia);
        factura.setMonto_total(monto); 
        return facturaRepository.save(factura);
    }


    public List<FacturaDTO> listarFacturas() {
        List<FacturaEntity> facturas = facturaRepository.findAll();
        return facturas.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public FacturaEntity actualizarFactura(Long id, Double monto) {
        FacturaEntity factura = facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));
        factura.setMonto_total(monto);
        return facturaRepository.save(factura);
    }


    public byte[] generarPdfFactura(Long id) throws IOException {
        FacturaEntity factura = facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));
        return pdfGenerator.generarFacturaPDF(mapToDTO(factura));
    }


    public byte[] generarReporteGeneral(Long mascotaId) throws IOException {
        List<FacturaEntity> facturas = facturaRepository.findAllByMascotId(mascotaId);
        List<FacturaDTO> facturaDTOs = facturas.stream().map(this::mapToDTO).collect(Collectors.toList());
        return pdfGenerator.generarReporteGeneral(facturaDTOs);
    }


    private FacturaDTO mapToDTO(FacturaEntity factura) {
        return FacturaDTO.builder()
                .id(factura.getId())
                .historiaId(factura.getHistorieEntity().getId())
                .nombreMascota(factura.getHistorieEntity().getMascotentity().getName())
                .nombrePropietario(factura.getHistorieEntity().getMascotentity().getUserentidad().getUsername())
                .nombreDoctor(factura.getHistorieEntity().getWorkerEntity().getUsername())
                .motivo(factura.getHistorieEntity().getMotivo())
                .diagnostico(factura.getHistorieEntity().getDiagnostico())
                .tratamiento(factura.getHistorieEntity().getTratamiento())
                .montoTotal(factura.getMonto_total())
                .fechaCita(factura.getHistorieEntity().getFecha())
                .build();
    }

}
