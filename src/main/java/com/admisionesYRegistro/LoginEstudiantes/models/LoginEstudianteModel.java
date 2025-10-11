package com.admisionesYRegistro.LoginEstudiantes.models;

import jakarta.persistence.*;

@Entity
@Table(name = "loginEstudiante")
public class LoginEstudianteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_solicitud;

    private String email;
    private String contrasena;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Long getId_solicitud() {
        return id_solicitud;
    }

    public void setId_solicitud(Long id_solicitud) {
        this.id_solicitud = id_solicitud;
    }
}
