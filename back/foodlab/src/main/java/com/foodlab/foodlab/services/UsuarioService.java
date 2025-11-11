/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.foodlab.foodlab.services;

import com.foodlab.foodlab.models.MetodoPago;
import com.foodlab.foodlab.models.Preferencia;
import com.foodlab.foodlab.models.Usuario;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.foodlab.foodlab.repositories.MetodoPagoRepository;
import com.foodlab.foodlab.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author BryanVanegas
 */
@Service
// Se utiliza para marcar la clase como un componente de servicio , para que Spring la detecte automaticamente y pueda
// inyectarla en otras partes del sistema.

/**
 *
 * @author MATEO
 */
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final MetodoPagoRepository metodoPagoRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired  //Motor de inyeccion de dependencias 
    public UsuarioService(UsuarioRepository usuarioRepository, MetodoPagoRepository metodoPagoRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.metodoPagoRepository = metodoPagoRepository;
        this.passwordEncoder = passwordEncoder;
        // Inicializamos algunos datos de ejemplo
        //initSampleData();
    }

    private void initSampleData() {
        Usuario user1 = new Usuario("Messi", "messi@eam.com", "123", "3285410320", "Armenia", "ADMIN");
        save(user1);

        save(new Usuario("Neymar", "neymar@eam.com", "789", "3368273083", "Cali", "CLIENTE"));

        Usuario cristiano = new Usuario("Cristiano", "cristiano@eam.com", "356", "3151982551", "Bogota", "CLIENTE");
        MetodoPago metP = new MetodoPago(1234123412341234L, "Debito", "Visa", 1234L);
        Preferencia pref = new Preferencia("cebolla, tomate", "Sin lechuga", "Recibir la comida caliente","hamburguesas");

        cristiano.setMetodoPago(metP);
        metP.setUsuario(cristiano);

        cristiano.setPreferencia(pref);
        pref.setUsuario(cristiano);

        save(cristiano);
    }

    // Crear un nuevo usuario 
    public Usuario save(Usuario usuario) {
        String passHashed = passwordEncoder.encode(usuario.getContrasenia());
        usuario.setContrasenia(passHashed);
        return usuarioRepository.save(usuario);
    }

    // Obtener un usuario por ID 
    public Optional<Usuario> findById(Integer id) {
        return usuarioRepository.findById(id);
    }

    // Listar todos los usuarios 
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    // Buscar por nombre 
    public List<Usuario> findByNombreContainingIgnoreCase(String nombre) {
        return usuarioRepository.findByNombreContainingIgnoreCase(nombre);
    }

    // Filtrar por email
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("No se encontró"));
    }

    public Usuario login(String email, String contrasenia) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

        if (usuario.isPresent()) {
            boolean coincidencia = passwordEncoder.matches(contrasenia, usuario.get().getContrasenia());

            if (!coincidencia) {
                return null;
            }
            return usuario.get();
        }
        return null;
    }

    // Actualizar un usuario 
    public Usuario update(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Actualización parcial 
    public Usuario patch(Integer id, Map<String, Object> updates) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        updates.forEach((key, value) -> {
            switch (key) {
                case "nombre" -> usuario.setNombre((String) value);
                case "email" -> usuario.setEmail((String) value);
                case "contrasenia" -> usuario.setContrasenia((String) value);
                case "direccion" -> usuario.setDireccion((String) value);
            }
        });

        return usuarioRepository.save(usuario);
    }


    // Eliminar un usuario
    public boolean deleteById(Integer id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
