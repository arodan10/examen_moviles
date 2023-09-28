package pe.edu.upeu.asistencia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.edu.upeu.asistencia.models.Usuario;
import pe.edu.upeu.asistencia.services.UsuarioService2;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/asis/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService2 usuarioService;

    @GetMapping("/list")
    public ResponseEntity<List<Usuario>> listUsuarios() {
        List<Usuario> usuarios = usuarioService.findAll();
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping("/crear")
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        Usuario data = usuarioService.save(usuario);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        Usuario usuario = usuarioService.geUsuarioById(id);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.delete(id));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetails) {
        Usuario updatedUsuario = usuarioService.update(usuarioDetails, id);
        return ResponseEntity.ok(updatedUsuario);
    }
}
