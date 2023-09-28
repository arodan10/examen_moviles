package pe.edu.upeu.asistencia.repositories;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.asistencia.models.Actividad;


@Repository
public interface ActividadRepository extends JpaRepository<Actividad, Long>{
    Optional<Actividad> findByNombreActividad(String nombreActidad);
}
