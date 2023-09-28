package pe.edu.upeu.asistencia.services;

import java.util.List;
import java.util.Map;
import pe.edu.upeu.asistencia.models.Persona;


public interface PersonaService {
    Persona save(Persona entidad);

    List<Persona> findAll();

    Map<String, Boolean> delete(Long id);

    Persona geEntidadById(Long id);

    Persona update(Persona entidad, Long id);     
}
