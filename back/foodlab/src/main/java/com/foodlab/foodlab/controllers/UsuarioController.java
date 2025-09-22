/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.foodlab.foodlab.controllers;

import com.foodlab.foodlab.models.Usuario;
import com.foodlab.foodlab.services.UsuarioService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author BryanVanegas
 */
@RestController
@RequestMapping("/foodlab/usuarios")
/*
// URL:  localhost:8080/foodlab/usuarios 
-----Anotaciones @-----
@RestController - Significa que esta clase va a manejar peticiones HTTP y devolvera directamente los datos (En formato JSON), no vistas HTML.
@RequestMapping("/api/usuarios") - Significa que esta es la ruta raiz para todas las peticiones que maneja este controlador.
@Autowired - Esta anotación le dice a Spring que debe inyectar automáticamente una instancia.
@GetMapping - Es una anotación que indica que este método debe responder a peticiones GET en una ruta específica (Por defecto la ruta raiz).
@GetMapping("/buscar") - Es una anotación que indica que este método debe responder a una peticion GET por la ruta de consulta dependiendo el parametro de consulta.
@RequestParam - Permite vincular un parámetro de la solicitud HTTP con un argumento de un método en tu controlador.
@GetMapping("/cabecera") - Es una anotación que indica que este método debe responder a una peticion GET y devolvera la cabecera.
@RequestHeader - Vincula el valor de un header HTTP directamente a un parametro de un metodo en el controlador.
@GetMapping("/{id}") - Es una anotación que indica que este método debe responder a una peticion GET por un usuario en este caso dependiendo del id.
@PathVariable - Se utiliza para extraer valores directamente de la URL y pasarlos como argumentos a un metodo del controlador.
@PostMapping - Es una anotación que indica que este método debe responder a una peticion POST para crear un nuevo usuario.
@PutMapping - Es una notacion que indica que este metodo debe responder a una peticion PUT para actualizar un usuario existente (reemplazo completo).
@RequestBody - Esta anotación indica que el objeto usuario se construye a partir del cuerpo de la solicitud HTTP, generalmente en formato JSON.
@PatchMapping - Se utiliza para manejar peticiones HTTP PATCH , que estan diseñadas para realizar actualizaciones parciales sobre un curso existente.
@DeleteMapping - Se utiliza para manejar peticiones HTTP DELETE , que sirven para eliminar recursos.
*/
/**
 *
 * @author MATEO
 */
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.findAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<List<Usuario>> getUsuariosByQuery(@RequestParam String nombre) {
        List<Usuario> usuarios = usuarioService.findByNombre(nombre);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
    
    @GetMapping("/cabecera")
    public ResponseEntity<String> getAgentInfo(@RequestHeader("User-Agent") String userAgent) {
        String info = "Información del cliente (User-Agent): " + userAgent;
        return new ResponseEntity<>(info, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable String id) {
        Usuario usuario = usuarioService.findById(id);
        if (usuario != null) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        Usuario newUsuario = usuarioService.save(usuario);
        return new ResponseEntity<>(newUsuario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable String id,
            @RequestBody Usuario usuario) {
        Usuario existingUsuario = usuarioService.findById(id);
        if (existingUsuario != null) {
            usuario.setId(id);
            Usuario updatedUsuario = usuarioService.update(usuario);
            return new ResponseEntity<>(updatedUsuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> patchUpdateUsuario(@PathVariable String id,
            @RequestBody Map<String, Object> updates) {
        Usuario updatedUsuario = usuarioService.patch(id, updates);
        if (updatedUsuario != null) {
            return new ResponseEntity<>(updatedUsuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable String id) {
        Usuario existingUsuario = usuarioService.findById(id);
        if (existingUsuario != null) {
            usuarioService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

