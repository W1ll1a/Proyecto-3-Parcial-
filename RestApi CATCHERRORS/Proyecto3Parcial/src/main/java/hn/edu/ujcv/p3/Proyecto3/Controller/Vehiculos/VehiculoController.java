package hn.edu.ujcv.p3.Proyecto3.Controller.Vehiculos;

import hn.edu.ujcv.p3.Proyecto3.entity.vehiculos.Vehiculos;
import hn.edu.ujcv.p3.Proyecto3.exception.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exception.NotFoundException;
import hn.edu.ujcv.p3.Proyecto3.repository.vehiculos.VehiculosRepositorio;
import hn.edu.ujcv.p3.Proyecto3.service.vehiculo.VehiculoService;
import hn.edu.ujcv.p3.Proyecto3.utils.RestApiError.RestApiError;
import hn.edu.ujcv.p3.Proyecto3.utils.vehiculo.ConstantsVehiculos;
import org.omg.CORBA.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehiculos")
public class VehiculoController {
    @Autowired
    private VehiculoService service;

    @PostMapping("/addVehiculo")
    public ResponseEntity<Object> addVehiculo (@RequestBody Vehiculos vehiculo){
        try{
            if(vehiculo.getColor().isEmpty()){
                throw new BusinessException("Introduzca el color del vehiculo");
            }
            if(vehiculo.getEstado().isEmpty()){
                throw new BusinessException("Introduzca el estado del vehiculo");
            }
            if (vehiculo.getPlaca().isEmpty()){
                throw new BusinessException("Introduzca placa del vehiculo");
            }
            if (vehiculo.getTipodevehiculo().isEmpty()){
                throw new BusinessException("Introduzca el tipo de vehiculo que es");
            }
            service.saveVehiculo(vehiculo);
            HttpHeaders responseHeader = new HttpHeaders();
            responseHeader.set("location" , ConstantsVehiculos.URL_BASE_VEHICULOS + vehiculo.getId());
            return new ResponseEntity(vehiculo ,responseHeader, HttpStatus.CREATED);//201

        }catch (Exception e){
            RestApiError apiError = new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Información Enviada no es Válida.",
                    e.getMessage());
            return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping ("/addVehiculos")
    public ResponseEntity<Any> addVehiculos (@RequestBody List<Vehiculos> vehiculos ){
        try {
            return  new ResponseEntity(service.saveVehiculos(vehiculos) , HttpStatus.CREATED);//201
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);//501
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Vehiculos>> findAllVehiculos(){
        try{
            return new ResponseEntity(service.getVehiculos(), HttpStatus.OK);//200
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);//500
        }
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Vehiculos> findById(@PathVariable long id ){
        try{
            return new ResponseEntity(service.getVehiculoById(id), HttpStatus.OK );//200
        }catch (BusinessException be){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);//501
        }
        catch (NotFoundException ne){
            return new ResponseEntity(HttpStatus.NOT_FOUND);//404
        }


    }
    @GetMapping("/placa/{placa}")
    public ResponseEntity<Vehiculos> findByPlaca(@PathVariable String placa ){
        try{
            return new ResponseEntity(service.getVehiculoByPlaca(placa), HttpStatus.OK );//200
        }catch (BusinessException be){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);//501
        }
        catch (NotFoundException ne){
            return new ResponseEntity(HttpStatus.NOT_FOUND);//404
        }

    }

    @PutMapping("")
    public ResponseEntity<Any> updateVehiculo (@RequestBody Vehiculos vehiculo){
        try{
            service.updateVehiculo(vehiculo);
            return new ResponseEntity(vehiculo,HttpStatus.OK);

        }catch (BusinessException be ){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        }catch (NotFoundException ne ){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<Any> deleteVehiculo (@PathVariable long id){
        try{
            service.deleteVehiculo(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (BusinessException be ){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException ne ){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }


}
