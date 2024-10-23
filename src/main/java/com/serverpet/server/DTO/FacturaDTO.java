package com.serverpet.server.DTO;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FacturaDTO {
    private Long id; 
    private Long historiaId;  
    private String nombreMascota;     
    private String nombrePropietario;       
    private String nombreDoctor;      
    private String motivo;            
    private String diagnostico;       
    private String tratamiento;       
    private Double montoTotal;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") 
    private Date fechaCita;
}
