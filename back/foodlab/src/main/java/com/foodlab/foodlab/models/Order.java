   /*
    * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
    * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
    */
   package com.foodlab.foodlab.models;

   import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
   import com.fasterxml.jackson.annotation.JsonProperty;
   import jakarta.persistence.*;

   import java.time.LocalDateTime;
   import java.util.ArrayList;
   import java.util.List;

   /**
    *
    * @author BryanVanegas
    */
   @Entity
   @Table(name = "ordenes")
   public class Order {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Integer idOrder;
       @Column(nullable = false)
       private LocalDateTime date = LocalDateTime.now();
       @Column()
       private String status = "PENDIENTE";
       @Column
       private double total;

       @ManyToOne(fetch = FetchType.LAZY)
       @JoinColumn(name = "usuario_id", nullable = false)
       @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "ordenes", "metodoPago"})
       private Usuario user;

       @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
       @JoinColumn(name = "factura_id")
       @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "order"})
       private Receipt factura;

       @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
       @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "order"})
       private List<OrdenProducto> products;

       public Order() {
       }

       public Integer getIdOrder() {
           return idOrder;
       }

       public void setIdOrder(Integer idOrder) {
           this.idOrder = idOrder;
       }

       public LocalDateTime getDate() {
           return date;
       }

       public void setDate(LocalDateTime date) {
           this.date = date;
       }

       public double getTotal() {
           return total;
       }

       public void setTotal(double total) {
           this.total = total;
       }

       public Usuario getUser() {
           return user;
       }

       public void setUser(Usuario user) {
           this.user = user;
       }

       public List<OrdenProducto> getProducts() {
           return products;
       }

       public void setProducts(List<OrdenProducto> products) {
           this.products = products;
       }

       public Receipt getFactura() {
           return factura;
       }

       public void setFactura(Receipt factura) {
           this.factura = factura;
       }

       public String getStatus() {
           return status;
       }

       public void setStatus(String status) {
           this.status = status;
       }

   }
