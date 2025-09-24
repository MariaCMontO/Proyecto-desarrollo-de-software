package service;

import repository.ProductoRepository;
import modelo.Producto;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
        initSampleData();
    }

    private void initSampleData() {
        save(new Producto("Hamburguesa", "Big Melte Supreme", "Carne jugosa, huevo frito, tocino crujiente y queso cheddar derretido", 30.000, 30));
        save(new Producto("Pizza", "Mediterránea", "Tomate, mozzarella, aceitunas negras, pimientos, alcachofas y orégano.", 25.000, 50));
        save(new Producto("Perro", "Urban Dog", "Pan artesanal, salchicha americana, cebolla caramelizada, salsa tártara, mostaza Dijon y perejil fresco.", 22.000, 30));
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
                    case "stock":
                        producto.setStock((Integer) value);
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
