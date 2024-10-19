package com.serverpet.server.Models;

import java.util.Date;

import com.serverpet.server.Util.HistoriState;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"Histori\"")
public class HistoriEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fecha;

    private String motivo;

    private String prioridad;
    @Lob
    private String diagnostico;
    @Lob
    private String tratamiento;

    @Enumerated(EnumType.STRING)
    private HistoriState historistate;

    @ManyToOne
    @JoinColumn(name = "mascotentity_id")
    private MascotEntity mascotentity;

    @ManyToOne
    @JoinColumn(name = "worker_id")
    private WorkerEntity workerEntity;

    @OneToOne(mappedBy = "historieEntity")
    private FacturaEntity factura;


}
