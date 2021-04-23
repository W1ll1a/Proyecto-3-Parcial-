package hn.edu.ujcv.p3.Proyecto3.Controller.Sedes;


import hn.edu.ujcv.p3.Proyecto3.entity.sedes.Sedes;
import hn.edu.ujcv.p3.Proyecto3.exception.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exception.NotFoundException;
import hn.edu.ujcv.p3.Proyecto3.service.sedes.SedesService;
import hn.edu.ujcv.p3.Proyecto3.utils.RestApiError.RestApiError;
import hn.edu.ujcv.p3.Proyecto3.utils.sedes.ConstantsSedes;
import org.omg.CORBA.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sedes")
public class SedeController {
    @Autowired
    private SedesService service;

    @PostMapping("/addSede")
    public ResponseEntity<Object> addSede(@RequestBody Sedes sede) {
        try {
            if (sede.getCiudad().isEmpty()){
                throw new BusinessException("Ciudad no puede estar vacio");
            }
            if (sede.getDireccion().isEmpty()){
                throw new BusinessException("Direccion vacio");
            }
            if (sede.getNombredirector().isEmpty()){
                throw new BusinessException("Director no puede quedar vacio");
            }
            if (sede.getNombredesede().isEmpty()){
                throw new BusinessException("Nombre de sede no puede quedar vacio");
            }
            if (sede.getCantidaddevehiculosensede()<15){
                throw new BusinessException("Una sede no puede tener menos de 15 vehiculos");
            }
            service.saveSede(sede);
            HttpHeaders responseHeader = new HttpHeaders();
            responseHeader.set("location", ConstantsSedes.URL_BASE_SEDES + sede.getId());
            return new ResponseEntity(sede, responseHeader, HttpStatus.CREATED);//201

        } catch (Exception e) {
            RestApiError apiError = new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Información Enviada no es Válida.",
                    e.getMessage());
            return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PostMapping("/addSedes")
    public ResponseEntity<Any> addSede(@RequestBody List<Sedes> sedes) {
        try {
            return new ResponseEntity(service.saveSedes(sedes), HttpStatus.CREATED);//201
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);//501
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Sedes>> findAllSedes() {
        try {
            return new ResponseEntity(service.getSedes(), HttpStatus.OK);//200
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);//500
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Sedes> findById(@PathVariable long id) {
        try {
            return new ResponseEntity(service.getSedeById(id), HttpStatus.OK);//200
        } catch (BusinessException be) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);//501
        } catch (NotFoundException ne) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);//404
        }
    }
    @GetMapping("/nombreDeSede/{sede}")
    public ResponseEntity<Sedes> findByNombreDeSede(@PathVariable String sede) {
        try {
            return new ResponseEntity(service.getSedeBynombredesede(sede), HttpStatus.OK);//200
        } catch (BusinessException be) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);//501
        } catch (NotFoundException ne) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);//404
        }
    }
    @PutMapping("")
    public ResponseEntity<Any> updateSede (@RequestBody Sedes sede){
        try{
            service.updateSede(sede);
            return new ResponseEntity(sede,HttpStatus.OK);

        }catch (BusinessException be ){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        }catch (NotFoundException ne ){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<Any> deleteSede (@PathVariable long id){
        try{
            service.deleteSede(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (BusinessException be ){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException ne ){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}