package com.foodlab.foodlab.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "preferencias")
public class Preferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column()
    private String ingredientes;
    @Column
    private String restricciones;
    @Column
    private String expectativas;
    @Column
    private String comidaFavorita;

    @OneToOne(mappedBy = "preferencia")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "preferencia", "metodoPago", "ordenes"})
    private Usuario usuario;

    public Preferencia() {
    }

    public Preferencia(String ingredientes, String restricciones, String expectativas, String comidaFavorita) {
        this.ingredientes = ingredientes;
        this.restricciones = restricciones;
        this.expectativas = expectativas;
        this.comidaFavorita = comidaFavorita;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getRestricciones() {
        return restricciones;
    }

    public void setRestricciones(String restricciones) {
        this.restricciones = restricciones;
    }

    public String getExpectativas() {
        return expectativas;
    }

    public void setExpectativas(String expectativas) {
        this.expectativas = expectativas;
    }

    public String getComidaFavorita() {
        return comidaFavorita;
    }

    public void setComidaFavorita(String comidaFavorita) {
        this.comidaFavorita = comidaFavorita;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
