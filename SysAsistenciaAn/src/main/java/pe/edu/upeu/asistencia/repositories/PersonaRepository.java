package pe.edu.upeu.asistencia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.asistencia.models.Persona;


@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long>{
    
}
