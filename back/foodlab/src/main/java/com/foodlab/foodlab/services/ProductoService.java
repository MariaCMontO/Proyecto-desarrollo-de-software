/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.foodlab.foodlab.services;

import com.foodlab.foodlab.models.Producto;
import com.foodlab.foodlab.repositories.ProductoRepository;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author BryanVanegas
 */
@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
        initSampleData();
    }

    private void initSampleData() {
        save(new Producto("Hamburguesas", "Big Melt Supreme",
                "Carne jugosa, huevo frito, tocino crujiente y queso cheddar derretido.",
                30000.0, "/hamburguesas_01.jpg"));

        save(new Producto("Hamburguesas", "Crispy Rancher",
                "Pechuga empanizada, queso cheddar, tomate y lechuga fresca.",
                28500.0, "/hamburguesas_02.jpg"));

        save(new Producto("Hamburguesas", "BBQ Sunset",
                "Hamburguesa de pollo a la parrilla con queso suizo y mermelada de tocineta al BBQ.",
                30000.0, "/hamburguesas_03.jpg"));

        save(new Producto("Hamburguesas", "Green Garden Burger",
                "Carne premium con vegetales frescos y toque de queso suave.",
                31000.0, "/hamburguesas_04.jpg"));

        save(new Producto("Hamburguesas", "Cheese Lava Burger",
                "Carne jugosa bañada en queso derretido y vegetales frescos.",
                32000.0, "/hamburguesas_05.jpg"));

        save(new Producto("Hamburguesas", "Double Cheddar Blast",
                "Doble carne, doble queso cheddar y vegetales crujientes.",
                30000.0, "/hamburguesas_06.jpg"));

        save(new Producto("Hamburguesas", "Texas BBQ",
                "Pan brioche, carne de res, queso cheddar, cebolla caramelizada, tocineta y salsa BBQ.",
                27000.0, "/hamburguesas_07.jpg"));

        save(new Producto("Hamburguesas", "Mediterránea Burger",
                "Pan rústico, carne de res, queso feta, tomate seco, rúgula y mayonesa de ajo.",
                30000.0, "/hamburguesas_08.jpg"));

        save(new Producto("Hamburguesas", "Volcán de Queso",
                "Pan artesanal, doble carne de res, mezcla de quesos fundidos, pepinillos y salsa especial.",
                28500.0, "/hamburguesas_09.jpg"));

        save(new Producto("Perros", "Urban Dog",
                "Pan artesanal, salchicha americana, cebolla caramelizada, salsa tártara, mostaza Dijon y perejil fresco.",
                22000.0, "/perros_01.jpg"));

        save(new Producto("Perros", "Tex-Mex Bite",
                "Salchicha parrillera, guacamole cremoso, jalapeños frescos, pico de gallo y salsa cheddar.",
                23500.0, "/perros_02.jpg"));

        save(new Producto("Perros", "Crispy Onion Dog",
                "Salchicha ahumada, mayonesa de ajo, cebolla crujiente frita, lechuga fresca y salsa BBQ ligera.",
                24000.0, "/perros_03.jpg"));

        save(new Producto("Perros", "BBQ Smash Dog",
                "Salchicha tipo Frankfurt, panceta ahumada, queso cheddar fundido, cebolla morada y abundante salsa BBQ.",
                23000.0, "/perros_04.jpg"));

        save(new Producto("Perros", "Cheesy Lover Dog",
                "Salchicha alemana, doble queso fundido (cheddar y mozzarella), salsa de queso, paprika y cebollín.",
                23500.0, "/perros_05.jpg"));

        save(new Producto("Perros", "Sweet & Spicy Dog",
                "Salchicha parrillera, miel natural, mostaza picante, pepinillos encurtidos y cebolla morada.",
                24000.0, "/perros_06.jpg"));

        save(new Producto("Pizzas", "Mediterránea",
                "Tomate, mozzarella, aceitunas negras, pimientos, alcachofas y orégano.",
                25000.0, "/pizzas_01.jpg"));

        save(new Producto("Pizzas", "Campesina",
                "Salsa de tomate, queso mozzarella, maíz tierno, champiñones, tocineta y cebolla morada.",
                24500.0, "/pizzas_02.jpg"));

        save(new Producto("Pizzas", "Cuatro Quesos",
                "Mozzarella, gorgonzola, parmesano y queso crema.",
                24500.0, "/pizzas_03.jpg"));

        save(new Producto("Pizzas", "Boscaiola",
                "Tomate, mozzarella, champiñones, salchicha italiana y cebolla caramelizada.",
                26000.0, "/pizzas_04.jpg"));

        save(new Producto("Pizzas", "Ibérica",
                "Tomate, mozzarella, jamón serrano, rúgula y lascas de parmesano.",
                25500.0, "/pizzas_05.jpg"));

        save(new Producto("Pizzas", "Rústica",
                "Salsa de tomate, mozzarella, chorizo español, pimientos rojos y aceitunas verdes.",
                23000.0, "/pizzas_06.jpg"));

        save(new Producto("Pizzas", "Carbonara",
                "Salsa blanca, mozzarella, tocineta, cebolla y huevo.",
                23000.0, "/pizzas_07.jpg"));

        save(new Producto("Pizzas", "Suprema",
                "Tomate, mozzarella, pepperoni, champiñones, pimientos y aceitunas negras.",
                25000.0, "/pizzas_08.jpg"));

        save(new Producto("Pizzas", "Hawaiana Salada",
                "Tomate, mozzarella, jamón ahumado y piña asada sin azúcar.",
                24000.0, "/pizzas_09.jpg"));

        save(new Producto("Pizzas", "Napolitana",
                "Tomate, mozzarella, anchoas, aceitunas negras y alcaparras.",
                23500.0, "/pizzas_10.jpg"));

        save(new Producto("Pizzas", "Provenzal",
                "Tomate, mozzarella, calabacín, berenjena, ajo asado y orégano fresco.",
                22500.0, "/pizzas_11.jpg"));
    }

    // Crear un nuevo producto
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    // Obtener un usuario por IDProducto
    public Producto findById(String idProducto) {
        return productoRepository.findById(idProducto);
    }

    // Listar todos los productos
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    // Buscar por nombre
    public List<Producto> findByNombre(String nombre) {
        return productoRepository.findByNombreContaining(nombre);
    }

    // Actualizar un producto
    public Producto update(Producto producto) {
        return productoRepository.update(producto);
    }

    // Actualización parcial
    public Producto patch(String idProducto, Map<String, Object> updates) {
        Producto producto = productoRepository.findById(idProducto);
        if (producto != null) {
            updates.forEach((key, value) -> {
                switch (key) {
                    case "categoria":
                        producto.setCategoria((String) value);
                        break;
                    case "nombre":
                        producto.setNombre((String) value);
                        break;
                    case "descripcion":
                        producto.setDescripcion((String) value);
                        break;
                    case "precio":
                        producto.setPrecio((Double) value);
                        break;
                    case "imagen":
                        producto.setImagen((String) value);
                }
            });
            return productoRepository.update(producto);
        }
        return null;
    }

    // Eliminar un producto
    public void deleteById(String idProducto) {
        productoRepository.deleteById(idProducto);
    }
}
