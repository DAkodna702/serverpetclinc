package com.serverpet.server.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MascotDTORseponse {
    private Long id;
    private String name;
    private Integer years_old;
    private String race;
    private String sex;
    private boolean isDeleted;
    private String username;
}
