package hn.edu.ujcv.p3.Proyecto3.Controller.Trabajadores;

import hn.edu.ujcv.p3.Proyecto3.entity.trabajadores.Trabajadores;
import hn.edu.ujcv.p3.Proyecto3.exception.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exception.NotFoundException;
import hn.edu.ujcv.p3.Proyecto3.service.trabajadores.TrabajadoresService;
import hn.edu.ujcv.p3.Proyecto3.utils.trabajadores.ConstantsTrabajadores;
import org.omg.CORBA.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trabajadores")
public class TrabajadoresController {

    @Autowired
    private TrabajadoresService service;

    @PostMapping("/addTrabajador")
    public ResponseEntity<Any> addTrabajador (@RequestBody Trabajadores trabajador){
        try{
            service.saveTrabajador(trabajador);
            HttpHeaders responseHeader = new HttpHeaders();
            responseHeader.set("location" , ConstantsTrabajadores.URL_BASE_TRABAJADORES + trabajador.getId());
            return new ResponseEntity(trabajador ,responseHeader, HttpStatus.CREATED);//201

        }catch (Exception e){
            return  new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);//501
        }
    }

    @PostMapping ("/addTrabajadores")
    public ResponseEntity<Any> addTrabajadores (@RequestBody List<Trabajadores> trabajadores ){
        try {
            return  new ResponseEntity(service.saveTrabajadores(trabajadores) , HttpStatus.CREATED);//201
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);//501
        }
    }
    @GetMapping("")
    public ResponseEntity<List<Trabajadores>> findAllTrabajadores(){
        try{
            return new ResponseEntity(service.getTrabajadores(), HttpStatus.OK);//200
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);//500
        }
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Trabajadores> findById(@PathVariable long id ){
        try{
            return new ResponseEntity(service.getTrabajadorById(id), HttpStatus.OK );//200
        }catch (BusinessException be){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);//501
        }
        catch (NotFoundException ne){
            return new ResponseEntity(HttpStatus.NOT_FOUND);//404
        }


    }
    @GetMapping("/telefono/{numeroTelefono}")
    public ResponseEntity<Trabajadores> findByNumeroTelefono(@PathVariable long numeroTelefono ){
        try{
            return new ResponseEntity(service.getTrabajadorByNumerotelefono(numeroTelefono), HttpStatus.OK );//200
        }catch (BusinessException be){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);//501
        }
        catch (NotFoundException ne){
            return new ResponseEntity(HttpStatus.NOT_FOUND);//404
        }


    }
    @PutMapping("")
    public ResponseEntity<Any> updateProduct (@RequestBody Trabajadores trabajador){
        try{
            service.updateTrabajador(trabajador);
            return new ResponseEntity(trabajador,HttpStatus.OK);

        }catch (BusinessException be ){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        }catch (NotFoundException ne ){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<Any> deleteTabajador (@PathVariable long id){
        try{
            service.deleteTrabajador(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (BusinessException be ){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException ne ){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
