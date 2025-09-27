/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.foodlab.foodlab.repositories;

import com.foodlab.foodlab.models.Usuario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

/**
 *
 * @author BryanVanegas
 */
@Repository // Se utiliza para marcar la clase como parte de la clase persistencia , es decir , aquella que se encarga de acceder , guardar , modificar y eliminar 
                        // datos en la base de datos.
/**
 *
 * @author MATEO
 */
public class UsuarioRepository {

    // Simulamos una base de datos con un Map 
    private final Map<String, Usuario> baseDeDatos = new HashMap<>();

    // Guardar un usuario 
    public Usuario save(Usuario usuario) {
        baseDeDatos.put(usuario.getId(), usuario);
        return usuario;
    }

    // Encontrar usuario por ID 
    public Usuario findById(String id) {
        return baseDeDatos.get(id);
    }

    // Listar todos los usuarios 
    public List<Usuario> findAll() {
        return new ArrayList<>(baseDeDatos.values());
    }

    // Filtrar por nombre 
    public List<Usuario> findByNombreContaining(String nombre) {
        return baseDeDatos.values().stream()
                .filter(usuario
                        -> usuario.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .collect(Collectors.toList());
    }
    
     //Filtrar por email y contrasenia
    public Usuario findByEmailAndPassword(String email, String password) {
        Usuario persona = baseDeDatos.values().stream().filter(usuario -> usuario.getEmail().toLowerCase().equals(email.toLowerCase())).findFirst().orElse(null);
        if (persona != null && persona.getContrasenia().equals(password)) {
            return persona;
        }else{
            return null;
        }
    }

    // Eliminar un usuario 
    public void deleteById(String id) {
        baseDeDatos.remove(id);
    }

    // Actualizar un usuario 
    public Usuario update(Usuario usuario) {
        if (baseDeDatos.containsKey(usuario.getId())) {
            baseDeDatos.put(usuario.getId(), usuario);
            return usuario;
        }
        return null;
    }

}
