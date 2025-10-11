package com.admisionesYRegistro.Request.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "request")


public class RequestModel {
    // ID de solicitud
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSolicitud;

    // INFORMACIÓN DE IDENTIFICACIÓN


    private String tipoDoc;
    private String numeroDoc;
    private LocalDate fechaExpedicion;
    private String paisExpedicion;
    private String departamentoExpedicion;
    private String ciudadExpedicion;
    private String nombres;
    private String apellidos;
    private String genero;
    private String estadoCivil;
    private String paisNacimiento;
    private String departamentoNacimiento;
    private LocalDate fechaNacimiento;
    private String ciudadNacimiento;
    private String indicativoPais;
    private String numeroCelular;
    private String primerPrograma;
    private String segundoPrograma;

    public Long getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Long idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(String numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public LocalDate getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(LocalDate fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public String getPaisExpedicion() {
        return paisExpedicion;
    }

    public void setPaisExpedicion(String paisExpedicion) {
        this.paisExpedicion = paisExpedicion;
    }

    public String getDepartamentoExpedicion() {
        return departamentoExpedicion;
    }

    public void setDepartamentoExpedicion(String departamentoExpedicion) {
        this.departamentoExpedicion = departamentoExpedicion;
    }

    public String getCiudadExpedicion() {
        return ciudadExpedicion;
    }

    public void setCiudadExpedicion(String ciudadExpedicion) {
        this.ciudadExpedicion = ciudadExpedicion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getPaisNacimiento() {
        return paisNacimiento;
    }

    public void setPaisNacimiento(String paisNacimiento) {
        this.paisNacimiento = paisNacimiento;
    }

    public String getDepartamentoNacimiento() {
        return departamentoNacimiento;
    }

    public void setDepartamentoNacimiento(String departamentoNacimiento) {
        this.departamentoNacimiento = departamentoNacimiento;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCiudadNacimiento() {
        return ciudadNacimiento;
    }

    public void setCiudadNacimiento(String ciudadNacimiento) {
        this.ciudadNacimiento = ciudadNacimiento;
    }

    public String getIndicativoPais() {
        return indicativoPais;
    }

    public void setIndicativoPais(String indicativoPais) {
        this.indicativoPais = indicativoPais;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public String getPrimerPrograma() {
        return primerPrograma;
    }

    public void setPrimerPrograma(String primerPrograma) {
        this.primerPrograma = primerPrograma;
    }

    public String getSegundoPrograma() {
        return segundoPrograma;
    }

    public void setSegundoPrograma(String segundoPrograma) {
        this.segundoPrograma = segundoPrograma;
    }
}
