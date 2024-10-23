package com.serverpet.server.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MascotDTO {
    private String name;
    private Integer years_old;
    private String race;
    private String sex;
    private boolean isDeleted;
    private Long userentidadId;
}
