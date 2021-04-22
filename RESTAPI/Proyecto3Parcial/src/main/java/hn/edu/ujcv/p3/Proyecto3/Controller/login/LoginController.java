package hn.edu.ujcv.p3.Proyecto3.Controller.login;

import hn.edu.ujcv.p3.Proyecto3.entity.login.Login;
import hn.edu.ujcv.p3.Proyecto3.entity.planilla.Planilla;
import hn.edu.ujcv.p3.Proyecto3.exception.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exception.NotFoundException;
import hn.edu.ujcv.p3.Proyecto3.service.login.LoginService;
import hn.edu.ujcv.p3.Proyecto3.utils.login.ConstantLogin;
import hn.edu.ujcv.p3.Proyecto3.utils.planilla.ConstantsPlanillas;
import org.omg.CORBA.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/logins")
public class LoginController {
    @Autowired
    private LoginService service;

    @PostMapping("/addLogin")
    public ResponseEntity<Any> addLog(@RequestBody Login login) {
        try {
            service.saveLog(login);
            HttpHeaders responseHeader = new HttpHeaders();
            responseHeader.set("location", ConstantLogin.URL_BASE_LOGINS + login.getId());
            return new ResponseEntity(login, responseHeader, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addLogs")
    public ResponseEntity<Any> addLogs(@RequestBody List<Login> users) {
        try {
            return new ResponseEntity(service.savelogs(users), HttpStatus.CREATED);//201
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);//501
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Planilla>> findAllUsuarios() {
        try {
            return new ResponseEntity(service.getUsuarios(), HttpStatus.OK);//200
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);//500
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Login> findById(@PathVariable long id) {
        try {
            return new ResponseEntity(service.getUsuarioById(id), HttpStatus.OK);//200
        } catch (BusinessException be) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);//501
        } catch (NotFoundException ne) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);//404
        }


    }
    @GetMapping("/user/{usuario}")
    public ResponseEntity<Planilla> findByUsuario(@PathVariable String usuario ){
        try{
            return new ResponseEntity(service.getUsuarioByUsuario(usuario), HttpStatus.OK );//200
        }catch (BusinessException be){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);//501
        }
        catch (NotFoundException ne){
            return new ResponseEntity(HttpStatus.NOT_FOUND);//404
        }
    }
    @PutMapping("")
    public ResponseEntity<Any> updateCuenta (@RequestBody Login cuenta){
        try{
            service.updateUsuario(cuenta);
            return new ResponseEntity(cuenta,HttpStatus.OK);

        }catch (BusinessException be ){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        }catch (NotFoundException ne ){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<Any> deleteUsuario (@PathVariable long id){
        try{
            service.deleteUsuario(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (BusinessException be ){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException ne ){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
