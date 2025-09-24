package modelo;

import java.util.UUID;
//BPO clase para movilizar datos

public class Producto {

    private String idProducto;
    private String categoria;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;

    public Producto() {
        this.idProducto = UUID.randomUUID().toString();
    }

    public Producto(String categoria, String nombre, String descripcion, double precio, int stock) {
        this.categoria = categoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setId(String id) {
        this.idProducto = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}
