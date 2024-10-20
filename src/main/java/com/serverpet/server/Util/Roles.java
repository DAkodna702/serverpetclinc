package com.serverpet.server.Util;


import java.util.*;

import java.util.Arrays;

public enum Roles {
    Administrador(Arrays.asList(Permisos.delete_users,Permisos.read_citations,Permisos.delete_citations,Permisos.update_information_all,Permisos.create_empledos,Permisos.create_citations,Permisos.delete_citations,Permisos.postpone_appointments,Permisos.register_mascot,Permisos.delete_mascot,Permisos.update_information_p,Permisos.update_information_mascot_p)),
    Doctor(Arrays.asList(Permisos.read_citations,Permisos.update_information_p)),
    Enfermera(Arrays.asList(Permisos.read_citations,Permisos.update_information_p)), 
    Estilista(Arrays.asList(Permisos.read_citations,Permisos.update_information_p)),
    Recepcionista(Arrays.asList(Permisos.read_citations,Permisos.update_information_p,Permisos.create_citations,Permisos.delete_citations,Permisos.postpone_appointments)),
    Custumer(Arrays.asList(Permisos.create_citations,Permisos.delete_citations,Permisos.postpone_appointments,Permisos.register_mascot,Permisos.delete_mascot,Permisos.update_information_p,Permisos.update_information_mascot_p));                                      
    private List<Permisos>permisos;

    Roles(List<Permisos> permisos) {
        this.permisos = permisos;
    }

    public List<Permisos> getPermisos() {
        return permisos;
    }
    public void setPermisos(List<Permisos> permisos) {
        this.permisos= permisos;
    }

}
