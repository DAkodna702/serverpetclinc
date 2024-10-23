package com.serverpet.server.DTO;

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
public class HistoriDTORequest {
    private Date fecha;
    private String motivo;
    private String prioridad;
    private String diagnostico;
    private String tratamiento;
    private HistoriState historistate;

    private Long mascotId;
    private Long workerId;
}
