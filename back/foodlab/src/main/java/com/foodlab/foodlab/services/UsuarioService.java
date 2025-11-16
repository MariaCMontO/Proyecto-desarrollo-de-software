/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.foodlab.foodlab.services;

import com.foodlab.foodlab.dto.LoginRequestDTO;
import com.foodlab.foodlab.dto.LoginResponseDTO;
import com.foodlab.foodlab.jwt.JwtService;
import com.foodlab.foodlab.models.MetodoPago;
import com.foodlab.foodlab.models.Preferencia;
import com.foodlab.foodlab.models.Usuario;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.foodlab.foodlab.repositories.MetodoPagoRepository;
import com.foodlab.foodlab.repositories.PreferenciaRepository;
import com.foodlab.foodlab.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final PasswordEncoder passwordEncoder;
    private final MetodoPagoRepository metodoPagoRepository;
    private final PreferenciaRepository preferenciaRepository;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired  //Motor de inyeccion de dependencias 
    public UsuarioService(UsuarioRepository usuarioRepository, MetodoPagoRepository metodoPagoRepository, PreferenciaRepository preferenciaRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.metodoPagoRepository = metodoPagoRepository;
        this.preferenciaRepository = preferenciaRepository;

//         Inicializamos algunos datos de ejemplo
//        initSampleData();
    }

    private void initSampleData() {
        Usuario user1 = new Usuario("Messi", "messi@eam.com", "123", "3285410320", "Armenia", "ADMIN");
        save(user1);

        save(new Usuario("Neymar", "neymar@eam.com", "789", "3368273083", "Cali", "CLIENTE"));

        Usuario cristiano = new Usuario("Cristiano", "cristiano@eam.com", "356", "3151982551", "Bogota", "CLIENTE");
        MetodoPago metP = new MetodoPago(1234123412341234L, "Debito", "Visa", 1234L);
        Preferencia pref = new Preferencia("cebolla, tomate", "Sin lechuga", "Recibir la comida caliente", "hamburguesas");

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

    public LoginResponseDTO login(LoginRequestDTO loginRequest) throws AuthenticationException {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        Usuario usuario = usuarioRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        String token = jwtService.generateToken(userDetails);

        return new LoginResponseDTO(token, usuario);
    }

    // Actualizar un usuario 
    public Usuario update(Integer id, Usuario usuarioData) {
        Usuario existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // datos simples
        existente.setNombre(usuarioData.getNombre());
        existente.setEmail(usuarioData.getEmail());
        existente.setCelular(usuarioData.getCelular());
        existente.setDireccion(usuarioData.getDireccion());

        MetodoPago mp = usuarioData.getMetodoPago();

        if (mp != null) {

            // 4. Está vacío → ignorar
            boolean estaVacio
                    = (mp.getCvv() == null)
                    && (mp.getFranquicia() == null || mp.getFranquicia().isBlank())
                    && (mp.getNumeroTarjeta() == null)
                    && (mp.getTipoTarjeta() == null || mp.getTipoTarjeta().isBlank());

            if (!estaVacio) {
                // 2. Tiene id → actualizar
                if (mp.getId() != null) {
                    MetodoPago existenteMP = metodoPagoRepository.findById(mp.getId())
                            .orElseThrow(() -> new RuntimeException("Método de pago no encontrado"));

                    existenteMP.setCvv(mp.getCvv());
                    existenteMP.setFranquicia(mp.getFranquicia());
                    existenteMP.setNumeroTarjeta(mp.getNumeroTarjeta());
                    existenteMP.setTipoTarjeta(mp.getTipoTarjeta());

                    existente.setMetodoPago(existenteMP);

                } else {
                    // 3. No tiene id pero sí datos → crear nuevo
                    MetodoPago nuevoMP = new MetodoPago();
                    nuevoMP.setCvv(mp.getCvv());
                    nuevoMP.setFranquicia(mp.getFranquicia());
                    nuevoMP.setNumeroTarjeta(mp.getNumeroTarjeta());
                    nuevoMP.setTipoTarjeta(mp.getTipoTarjeta());

                    MetodoPago guardado = metodoPagoRepository.save(nuevoMP);
                    existente.setMetodoPago(guardado);
                }
            }
        }
        Preferencia pref = usuarioData.getPreferencia();

        if (pref != null) {

            boolean estaVaciaPref
                    = (pref.getComidaFavorita() == null || pref.getComidaFavorita().isBlank())
                    && (pref.getExpectativas() == null || pref.getExpectativas().isBlank())
                    && (pref.getIngredientes() == null || pref.getIngredientes().isBlank())
                    && (pref.getRestricciones() == null || pref.getRestricciones().isBlank());

            if (!estaVaciaPref) {

                if (pref.getId() != null) {
                    Preferencia existentePref = preferenciaRepository.findById(pref.getId())
                            .orElseThrow(() -> new RuntimeException("Preferencia no encontrada"));

                    existentePref.setComidaFavorita(pref.getComidaFavorita());
                    existentePref.setExpectativas(pref.getExpectativas());
                    existentePref.setIngredientes(pref.getIngredientes());
                    existentePref.setRestricciones(pref.getRestricciones());

                    existente.setPreferencia(existentePref);

                } else {
                    // Crear nueva preferencia
                    Preferencia nueva = new Preferencia();
                    nueva.setComidaFavorita(pref.getComidaFavorita());
                    nueva.setExpectativas(pref.getExpectativas());
                    nueva.setIngredientes(pref.getIngredientes());
                    nueva.setRestricciones(pref.getRestricciones());

                    Preferencia guardada = preferenciaRepository.save(nueva);
                    existente.setPreferencia(guardada);
                }
            }
        }

        return usuarioRepository.save(existente);
    }

    // Actualización parcial 
    public Usuario patch(Integer id, Map<String, Object> updates) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        updates.forEach((key, value) -> {
            switch (key) {
                case "nombre" ->
                    usuario.setNombre((String) value);
                case "email" ->
                    usuario.setEmail((String) value);
                case "contrasenia" ->
                    usuario.setContrasenia((String) value);
                case "direccion" ->
                    usuario.setDireccion((String) value);
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
