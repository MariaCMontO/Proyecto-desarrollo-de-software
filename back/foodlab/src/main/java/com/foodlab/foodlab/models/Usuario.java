/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.foodlab.foodlab.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

/**
 *
 * @author BryanVanegas
 */
@Entity
@Table(name = "usuarios")
public class Usuario {

    // Atributos:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String contrasenia;
    @Column
    private String celular;
    @Column
    private String direccion;
    @Column
    private String rol = "CLIENTE";

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "user", "factura", "products"})
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Order> ordenes;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "metodo_pago_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "usuario"})
    private MetodoPago metodoPago;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "preferencia_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "usuario"})
    private Preferencia preferencia;

    // Constructor:
    public Usuario() {
    }

    public Usuario(String nombre, String email, String contrasenia, String celular, String direccion, String rol) {
        this.nombre = nombre;
        this.email = email;
        this.contrasenia = contrasenia;
        this.celular = celular;
        this.direccion = direccion;
        this.rol = rol;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public List<Order> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(List<Order> ordenes) {
        this.ordenes = ordenes;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Preferencia getPreferencia() {
        return preferencia;
    }

    public void setPreferencia(Preferencia preferencia) {
        this.preferencia = preferencia;
    }
}
