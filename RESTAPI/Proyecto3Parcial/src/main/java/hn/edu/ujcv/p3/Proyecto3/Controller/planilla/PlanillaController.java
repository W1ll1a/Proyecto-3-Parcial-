package hn.edu.ujcv.p3.Proyecto3.Controller.planilla;


import hn.edu.ujcv.p3.Proyecto3.entity.planilla.Planilla;
import hn.edu.ujcv.p3.Proyecto3.exception.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exception.NotFoundException;
import hn.edu.ujcv.p3.Proyecto3.service.planilla.PlanillaService;
import hn.edu.ujcv.p3.Proyecto3.utils.planilla.ConstantsPlanillas;
import org.omg.CORBA.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/planillas")
public class PlanillaController {
    @Autowired
    private PlanillaService service;

    @PostMapping("/addPlanilla")
    public ResponseEntity<Any> addPlanilla(@RequestBody Planilla planilla) {
        try {
            service.savePlanilla(planilla);
            HttpHeaders responseHeader = new HttpHeaders();
            responseHeader.set("location", ConstantsPlanillas.URL_BASE_PLANILLAS + planilla.getId());
            return new ResponseEntity(planilla, responseHeader, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addPlanillas")
    public ResponseEntity<Any> addPlanillas(@RequestBody List<Planilla> planillas) {
        try {
            return new ResponseEntity(service.savePlanillas(planillas), HttpStatus.CREATED);//201
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);//501
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Planilla>> findAllPlanillas() {
        try {
            return new ResponseEntity(service.getPlanillas(), HttpStatus.OK);//200
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);//500
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Planilla> findById(@PathVariable long id) {
        try {
            return new ResponseEntity(service.getPlanillaById(id), HttpStatus.OK);//200
        } catch (BusinessException be) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);//501
        } catch (NotFoundException ne) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);//404
        }


    }
    @GetMapping("/nombre/{name}")
    public ResponseEntity<Planilla> findByName(@PathVariable String name ){
        try{
            return new ResponseEntity(service.getPlanillaByName(name), HttpStatus.OK );//200
        }catch (BusinessException be){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);//501
        }
        catch (NotFoundException ne){
            return new ResponseEntity(HttpStatus.NOT_FOUND);//404
        }
    }
    @PutMapping("")
    public ResponseEntity<Any> updatePlanilla (@RequestBody Planilla planilla){
        try{
            service.updatePlanilla(planilla);
            return new ResponseEntity(planilla,HttpStatus.OK);

        }catch (BusinessException be ){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        }catch (NotFoundException ne ){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<Any> deletePlanilla (@PathVariable long id){
        try{
            service.deletePlanilla(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (BusinessException be ){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException ne ){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }


}
