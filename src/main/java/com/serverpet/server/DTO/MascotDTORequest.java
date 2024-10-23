package com.serverpet.server.DTO;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MascotDTORequest {
    private Long id;
    private String name;
    private  Integer years_old;
    private String race;
    private String sex;
}
