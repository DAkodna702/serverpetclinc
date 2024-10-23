package com.serverpet.server.Controllers;

import com.serverpet.server.DTO.FacturaDTO;
import com.serverpet.server.Models.FacturaEntity;
import com.serverpet.server.Services.FacturaServicies;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/facturas")
public class AuthFactura {

    @Autowired
    private FacturaServicies facturaServices;


    @PostMapping("/crear")
    public ResponseEntity<FacturaEntity> crearFactura(@RequestBody FacturaDTO facturaDTO) {
        FacturaEntity factura = facturaServices.crearFactura(facturaDTO.getHistoriaId(), facturaDTO.getMontoTotal());
        return new ResponseEntity<>(factura, HttpStatus.CREATED);
    }

    // Listar todas las facturas
    @GetMapping("/list")
    public ResponseEntity<List<FacturaDTO>> listarFacturas() {
        List<FacturaDTO> facturas = facturaServices.listarFacturas();
        return new ResponseEntity<>(facturas, HttpStatus.OK);
    }


    @PutMapping("/actualizar/{id}")
    public ResponseEntity<FacturaEntity> actualizarFactura(@PathVariable Long id, @RequestBody FacturaDTO facturaDTO) {
        FacturaEntity factura = facturaServices.actualizarFactura(id, facturaDTO.getMontoTotal());
        return new ResponseEntity<>(factura, HttpStatus.OK);
    }


    @GetMapping("/pdf/{id}")
    public ResponseEntity<byte[]> generarPdfFactura(@PathVariable Long id)  throws IOException{
        byte[] pdfBytes = facturaServices.generarPdfFactura(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "factura_" + id + ".pdf");
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

   
    @GetMapping("/reporte/{mascotaId}")
    public ResponseEntity<byte[]> generarReporteGeneral(@PathVariable Long mascotaId) throws IOException {
        byte[] pdfBytes = facturaServices.generarReporteGeneral(mascotaId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "reporte_mascota_" + mascotaId + ".pdf");
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}

