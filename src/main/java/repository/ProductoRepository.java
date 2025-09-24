package repository;

import modelo.Producto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository

public class ProductoRepository {

    // Simulamos una base de datos con un Map
    private final Map<String, Producto> baseDeDatos = new HashMap<>();

    // Guardar un producto
    public Producto save(Producto producto) {
        baseDeDatos.put(producto.getIdProducto(), producto);
        return producto;
    }

    // Encontrar producto por ID
    public Producto findById(String idProducto) {
        return baseDeDatos.get(idProducto);
    }

    // Listar todos los productos
    public List<Producto> findAll() {
        return new ArrayList<>(baseDeDatos.values());
    }

    // Filtrar por nombre
    public List<Producto> findByNombreContaining(String nombre) {
        return baseDeDatos.values().stream()
                .filter(producto -> producto.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Eliminar un producto
    public void deleteById(String idProducto) {
        baseDeDatos.remove(idProducto);
    }

    // Actualizar un producto
    public Producto update(Producto producto) {
        if (baseDeDatos.containsKey(producto.getIdProducto())) {
            baseDeDatos.put(producto.getIdProducto(), producto);
            return producto;
        }
        return null;
    }
}
