package pe.edu.upeu.asistencia.services;

import java.util.List;
import java.util.Map;
import pe.edu.upeu.asistencia.models.Usuario;


public interface UsuarioService2 {  
    Usuario save(Usuario usuario);
    
    List<Usuario> findAll();

    Map<String, Boolean> delete(Long id);

    Usuario geUsuarioById(Long id);

    Usuario update(Usuario usuario, Long id);    
}
