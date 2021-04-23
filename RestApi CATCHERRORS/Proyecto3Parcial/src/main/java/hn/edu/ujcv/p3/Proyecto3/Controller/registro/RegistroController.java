package hn.edu.ujcv.p3.Proyecto3.Controller.registro;

import hn.edu.ujcv.p3.Proyecto3.entity.planilla.Planilla;
import hn.edu.ujcv.p3.Proyecto3.entity.registro.Registro;
import hn.edu.ujcv.p3.Proyecto3.exception.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exception.NotFoundException;
import hn.edu.ujcv.p3.Proyecto3.service.registro.RegistroService;
import hn.edu.ujcv.p3.Proyecto3.utils.RestApiError.RestApiError;
import hn.edu.ujcv.p3.Proyecto3.utils.planilla.ConstantsPlanillas;
import hn.edu.ujcv.p3.Proyecto3.utils.registro.ConstantsRegistro;
import org.aspectj.weaver.ast.Not;
import org.omg.CORBA.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registros")
public class RegistroController {
    @Autowired
    private RegistroService service ;

    @PostMapping("/addRegistro")
    public ResponseEntity<Object> addRegistro(@RequestBody Registro registro) {
        try {
            if (registro.getVehiculo().isEmpty()){
                throw new BusinessException("Tipo de Vehiculo vacio");
            }
            if (registro.getCantidadproducto()<15){
                throw new BusinessException("No pueden ir menos de 15 productos");
            }
            if (registro.getFechaentrada().isEmpty()){
                throw new BusinessException("Ocupa registrar Fechas");
            }
            if (registro.getFechamaximaentrega().isEmpty()){
                throw new BusinessException("Ocupa registrar Fechas");
            }
            if (registro.getFechasalida().isEmpty()){
                throw new BusinessException("Ocupa registrar Fechas");
            }

            service.saveRegistro(registro);
            HttpHeaders responseHeader = new HttpHeaders();
            responseHeader.set("location", ConstantsRegistro.URL_BASE_REGISTROS + registro.getId());
            return new ResponseEntity(registro, responseHeader, HttpStatus.CREATED);
        } catch (Exception e) {
            RestApiError apiError = new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Información Enviada no es Válida.",
                    e.getMessage());
            return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/addRegistros")
    public ResponseEntity<Any> addRegistros (@RequestBody List<Registro> registros){
        try{
            return new ResponseEntity(service.saveRegistros(registros),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("")
    public ResponseEntity <List<Registro>> findAllRegistros(){
        try{
            return new ResponseEntity(service.getRegistros(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Any>findRegistroById(@PathVariable long id){
        try{
            return new ResponseEntity(service.getRegistroById(id),HttpStatus.OK);
        }catch (BusinessException be){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException ne){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/vehiculo/{vehiculo}")
    public ResponseEntity<Registro> findByVehiculo(@PathVariable String vehiculo ){
        try{
            return new ResponseEntity(service.getRegistroByVehiculo(vehiculo), HttpStatus.OK );//200
        }catch (BusinessException be){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);//501
        }
        catch (NotFoundException ne){
            return new ResponseEntity(HttpStatus.NOT_FOUND);//404
        }
    }

    @PutMapping ("")
    public ResponseEntity<Any> updateRegistro (@RequestBody Registro registro){
        try{
            service.updateRegistro(registro);
            return new ResponseEntity(registro,HttpStatus.OK);
        }catch (BusinessException be ){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        }
        catch (NotFoundException ne) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Any> deleteRegistro (@PathVariable long id){
        try{
            service.deleteRegistro(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (BusinessException be){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException ne ){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
