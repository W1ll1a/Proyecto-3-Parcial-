package hn.edu.ujcv.p3.Proyecto3.Controller.ticket;

import hn.edu.ujcv.p3.Proyecto3.entity.sedes.Sedes;
import hn.edu.ujcv.p3.Proyecto3.entity.ticket.Ticket;
import hn.edu.ujcv.p3.Proyecto3.exception.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exception.NotFoundException;
import hn.edu.ujcv.p3.Proyecto3.service.ticket.TicketService;
import hn.edu.ujcv.p3.Proyecto3.utils.sedes.ConstantsSedes;
import org.omg.CORBA.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {
    @Autowired
    private TicketService service ;

    @PostMapping ("/addTicket")
    public ResponseEntity<Any> addTicket (@RequestBody Ticket ticket){
        try {
            service.saveTicket(ticket);
            HttpHeaders responseHeader = new HttpHeaders();
            responseHeader.set("location", ConstantsSedes.URL_BASE_SEDES + ticket.getId());
            return new ResponseEntity(ticket, responseHeader, HttpStatus.CREATED);//201

        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);//501
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Ticket>> findAllTicket() {
        try {
            return new ResponseEntity(service.getTickets(), HttpStatus.OK);//200
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);//500
        }
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Ticket> findById(@PathVariable long id) {
        try {
            return new ResponseEntity(service.getById(id), HttpStatus.OK);//200
        } catch (BusinessException be) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);//501
        } catch (NotFoundException ne) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);//404
        }
    }
    @GetMapping("/cliente/{cliente}")
    public ResponseEntity<Sedes> findBycliente(@PathVariable String cliente) {
        try {
            return new ResponseEntity(service.getByCliente(cliente), HttpStatus.OK);//200
        } catch (BusinessException be) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);//501
        } catch (NotFoundException ne) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);//404
        }
    }

    @PutMapping("")
    public ResponseEntity<Any> updateTicket (@RequestBody Ticket ticket){
        try{
            service.updateTicket(ticket);
            return new ResponseEntity(ticket,HttpStatus.OK);

        }catch (BusinessException be ){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        }catch (NotFoundException ne ){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<Any> deleteTicket (@PathVariable long id){
        try{
            service.deleteTicket(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (BusinessException be ){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException ne ){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
