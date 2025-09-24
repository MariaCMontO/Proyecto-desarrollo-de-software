/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.foodlab.foodlab.models;

/**
 *
 * @author BryanVanegas
 */
public class MetodoPago {
    
    // Atributos:
    private String metodo;
    private Long numero;
    private String nombreTarjeta;
    private Long cvv;
    
    // Constructor:
    public MetodoPago(String metodo, Long numero, String nombreTarjeta, Long cvv) {
        this.metodo = metodo;
        this.numero = numero;
        this.nombreTarjeta = nombreTarjeta;
        this.cvv = cvv;
    }
    
    // Getter y Setter:
    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getNombreTarjeta() {
        return nombreTarjeta;
    }

    public void setNombreTarjeta(String nombreTarjeta) {
        this.nombreTarjeta = nombreTarjeta;
    }

    public Long getCvv() {
        return cvv;
    }

    public void setCvv(Long cvv) {
        this.cvv = cvv;
    }

    @Override
    public String toString() {
        return "MetodoPago{" + "metodo=" + metodo + ", numero=" + numero + ", nombreTarjeta=" + nombreTarjeta + ", cvv=" + cvv + '}';
    }
    
}

