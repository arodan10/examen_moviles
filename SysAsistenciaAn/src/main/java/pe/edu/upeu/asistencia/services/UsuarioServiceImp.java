package pe.edu.upeu.asistencia.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.asistencia.exceptions.ResourceNotFoundException;
import pe.edu.upeu.asistencia.models.Rol;
import pe.edu.upeu.asistencia.models.Usuario;
import pe.edu.upeu.asistencia.repositories.RolRepository;
import pe.edu.upeu.asistencia.repositories.UsuarioRepository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class UsuarioServiceImp implements UsuarioService2 {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private final RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
public Usuario save(Usuario usuario) {
    String encodedPassword = passwordEncoder.encode(usuario.getPassword());
    usuario.setPassword(encodedPassword);
    
    Set<Rol> roles = new HashSet<>();
    for (Rol rol : usuario.getRoles()) {
        roles.add(rolRepository.findById(rol.getId())
        .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado con ID: " + rol.getId())));
    }
    usuario.setRoles(roles);
    
    return usuarioRepository.save(usuario);
}

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Map<String, Boolean> delete(Long id) {
        Map<String, Boolean> response = new HashMap<>();
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuarioRepository.delete(usuario);
            response.put("deleted", Boolean.TRUE);
        } else {
            response.put("deleted", Boolean.FALSE);
        }

        return response;
    }

    @Override
    public Usuario geUsuarioById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
    }

    @Override
public Usuario update(Usuario usuario, Long id) {
    Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

    if (optionalUsuario.isPresent()) {
        Usuario existingUsuario = optionalUsuario.get();
        existingUsuario.setNombres(usuario.getNombres());
        existingUsuario.setApellidos(usuario.getApellidos());
        existingUsuario.setCorreo(usuario.getCorreo());

        // Codificar la contraseña si se proporciona una nueva contraseña
        if (!usuario.getPassword().equals(existingUsuario.getPassword())) {
            String encodedPassword = passwordEncoder.encode(usuario.getPassword());
            existingUsuario.setPassword(encodedPassword);
        }

        existingUsuario.setDni(usuario.getDni());
        existingUsuario.setPerfilPrin(usuario.getPerfilPrin());
        existingUsuario.setEstado(usuario.getEstado());
        existingUsuario.setOfflinex(usuario.getOfflinex());

        // Fetch roles from the database based on the provided role IDs
        Set<Rol> updatedRoles = usuario.getRoles().stream()
                .map(rol -> rolRepository.findById(rol.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Rol not found with ID: " + rol.getId())))
                .collect(Collectors.toSet());

        existingUsuario.setRoles(updatedRoles);

        return usuarioRepository.save(existingUsuario);
    } else {
        throw new ResourceNotFoundException("Usuario no encontrado con ID: " + id);
    }
}


}
