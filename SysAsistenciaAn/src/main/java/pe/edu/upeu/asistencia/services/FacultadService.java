
package pe.edu.upeu.asistencia.services;

import java.util.List;
import java.util.Map;
import pe.edu.upeu.asistencia.models.Facultad;


public interface FacultadService {
    Facultad save(Facultad entidad);

    List<Facultad> findAll();

    Map<String, Boolean> delete(Long id);

    Facultad geEntidadById(Long id);

    Facultad update(Facultad entidad, Long id); 
}
