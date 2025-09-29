package com.example.demo;

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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/foodlab/usuarios")
@Tag(name = "Usuarios", description = "API para la gestion de usuarios")
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
    @Operation(summary = "Obtiene todos los usuarios registrados", description = "Devuelve en una lista todos los usuarios registrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida con exito"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.findAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar usuarios por nombre", description = "Buscar todos los usuarios por ese nombre completo o parcial")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuarios encontrados con exito"),
        @ApiResponse(responseCode = "404", description = "Usuario NO encontrado con ese nombre")
    })
    public ResponseEntity<List<Usuario>> getUsuariosByQuery(
            @Parameter(description = "Nombre del usuario a buscar") @RequestParam String nombre) {
        List<Usuario> usuarios = usuarioService.findByNombre(nombre);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{email}")
    @Operation(summary = "Buscar usuario por email y contraseña", description = "Buscar un usuario en especifico por su email y contraseña")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado con exito"),
        @ApiResponse(responseCode = "404", description = "Usuario NO encontrado")
    })
    public ResponseEntity<Usuario> getUsuarioByEmailAndPassword(
            @Parameter(description = "Email del usuario a buscar") @RequestParam String email,
            @Parameter(description = "Contraseña del usuario a buscar") @RequestParam String password) {
        Usuario usuario = usuarioService.findByEmailAndPassword(email, password);
        if (usuario != null) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/cabecera")
    @Operation(summary = "Obtiene informacion del cliente desde el header User-Agent", description = "Obtiene informacion del cliente que esta en la cabecera")
    @ApiResponse(responseCode = "200", description = "Informacion obtenida con exito")
    public ResponseEntity<String> getAgentInfo(
            @Parameter(description = "Header User-Agent del cliente") @RequestHeader("User-Agent") String userAgent) {
        String info = "Información del cliente (User-Agent): " + userAgent;
        return new ResponseEntity<>(info, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuario por ID", description = "Buscar un usuario en especifico por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado con exito"),
        @ApiResponse(responseCode = "404", description = "Usuario NO encontrado con exito")
    })
    public ResponseEntity<Usuario> getUsuarioById(
            @Parameter(description = "ID del usuario a buscar") @PathVariable String id) {
        Usuario usuario = usuarioService.findById(id);
        if (usuario != null) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Registrar un nuevo usuario", description = "Crear un nuevo usuario para guadarlo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario registrado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos")
    })
    public ResponseEntity<Usuario> createUsuario(
            @Parameter(description = "Datos del nuevo usuario") @RequestBody Usuario usuario) {
        Usuario newUsuario = usuarioService.save(usuario);
        return new ResponseEntity<>(newUsuario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar los datos de un usuario", description = "Actualizar todos los datos de un usuario")
    @ApiResponses(value={
        @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Usuario NO encontrado")
    })
    public ResponseEntity<Usuario> updateUsuario(
            @Parameter(description = "ID del usuario a actualizar") @PathVariable String id,
            @Parameter(description = "Datos actualizados del usuario") @RequestBody Usuario usuario) {
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
    @Operation(summary = "Actualizar los datos de un usuario", description = "Actualizar parcialmente algunos datos del usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Datos actualizados parcialmente exitosamente"),
        @ApiResponse(responseCode = "404", description = "Usuario NO encontrado")
    })
    public ResponseEntity<Usuario> patchUpdateUsuario(
            @Parameter(description = "ID del usuario a actualizar") @PathVariable String id,
            @Parameter(description = "Datos actualizados del usuario") @RequestBody Map<String, Object> updates) {
        Usuario updatedUsuario = usuarioService.patch(id, updates);
        if (updatedUsuario != null) {
            return new ResponseEntity<>(updatedUsuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un usuario", description = "Eliminar un usuario en especifico por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Usuario NO encontrado")
    })
    public ResponseEntity<Void> deleteUsuario(
            @Parameter(description = "ID del usuario a eliminar") @PathVariable String id) {
        Usuario existingUsuario = usuarioService.findById(id);
        if (existingUsuario != null) {
            usuarioService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
