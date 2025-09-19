package com.example.demo;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // Se utiliza para marcar la clase como un componente de servicio , para que Spring la detecte automaticamente y pueda 
// inyectarla en otras partes del sistema.

/**
 *
 * @author MATEO
 */
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired  //Motor de inyeccion de dependencias 
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        // Inicializamos algunos datos de ejemplo 
        initSampleData();
    }

    private void initSampleData() {
        save(new Usuario("1", "Messi", "messi@eam.com", "123", "3285410320", "Armenia"));
        save(new Usuario("2", "Cristiano", "cristiano@eam.com", "356", "3151982551", "Bogota"));
        save(new Usuario("3", "Neymar", "neymar@eam.com", "789", "3368273083", "Cali"));
    }

    // Crear un nuevo usuario 
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Obtener un usuario por ID 
    public Usuario findById(String id) {
        return usuarioRepository.findById(id);
    }

    // Listar todos los usuarios 
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    // Buscar por nombre 
    public List<Usuario> findByNombre(String nombre) {
        return usuarioRepository.findByNombreContaining(nombre);
    }

    // Actualizar un usuario 
    public Usuario update(Usuario usuario) {
        return usuarioRepository.update(usuario);
    }

    // Actualización parcial 
    public Usuario patch(String id, Map<String, Object> updates) {
        Usuario usuario = usuarioRepository.findById(id);
        if (usuario != null) {
            updates.forEach((key, value) -> {
                switch (key) {
                    case "nombre":
                        usuario.setNombre((String) value);
                        break;
                    case "email":
                        usuario.setEmail((String) value);
                        break;
                    case "contrasenia":
                        usuario.setContrasenia((String) value);
                        break;
                    case "celular":
                        usuario.setCelular((String) value);
                    case "direccion":
                        usuario.setDireccion((String) value);
                    case "tipo":
                        usuario.setTipo((String) value);
                    case "ingredientes":
                        usuario.setIngredientes((String) value);
                    case "restricciones":
                        usuario.setRestricciones((String) value);
                    case "expectativas":
                        usuario.setExpectativas((String) value);
                    case "comidaFavorita":
                        usuario.setComidaFavorita((String) value);
                    case "pago":
                        usuario.setPago((Pago) value);
                }
            });
            return usuarioRepository.update(usuario);
        }
        return null;
    }

    // Eliminar un usuario 
    public void deleteById(String id) {
        usuarioRepository.deleteById(id);
    }

}
