package pe.edu.upeu.asistencia.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upeu.asistencia.models.Facultad;
import pe.edu.upeu.asistencia.services.FacultadService;

@RestController
@RequestMapping("/asis/facultad")
public class FacultadController {
    @Autowired
    private FacultadService entidadService;    
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<Facultad>> listEntidad() {
        List<Facultad> actDto = entidadService.findAll();
        return ResponseEntity.ok().body(actDto);
        //return new ResponseEntity<>(actDto, HttpStatus.OK);
    }    
}
