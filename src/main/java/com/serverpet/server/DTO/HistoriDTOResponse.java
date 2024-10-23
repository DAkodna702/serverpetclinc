package com.serverpet.server.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.serverpet.server.Util.HistoriState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HistoriDTOResponse {
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fecha;
    private String motivo;
    private String prioridad;
    private String diagnostico;
    private String tratamiento;
    private HistoriState historistate;

    private String mascotaNombre;
    private String trabajadorNombre;
    private String trabajadorRol;
}