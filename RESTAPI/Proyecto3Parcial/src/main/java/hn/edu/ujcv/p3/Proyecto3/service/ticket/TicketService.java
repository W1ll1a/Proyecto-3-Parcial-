package hn.edu.ujcv.p3.Proyecto3.service.ticket;

import hn.edu.ujcv.p3.Proyecto3.entity.sedes.Sedes;
import hn.edu.ujcv.p3.Proyecto3.entity.ticket.Ticket;
import hn.edu.ujcv.p3.Proyecto3.exception.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exception.NotFoundException;
import hn.edu.ujcv.p3.Proyecto3.repository.ticket.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService implements ITicketService{
    @Autowired
    private TicketRepository repository ;

    public Ticket saveTicket(Ticket ticket)throws BusinessException{
        try {
            return repository.save(ticket);

        }catch (Exception e ){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Ticket> getTickets() throws BusinessException {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }
    public Ticket getById(long id) throws BusinessException, NotFoundException {
        Optional<Ticket> opt = null;
        try {
            opt = repository.findById(id);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }

        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el ticket " + id);
        }
        return opt.get();
    }
    public Ticket getByCliente(String cliente) throws BusinessException, NotFoundException {
        Optional<Ticket> opt = null;
        try {
            opt = repository.findBycliente(cliente);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }

        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró este cliente " + cliente);
        }
        return opt.get();
    }
    public void deleteTicket(long id) throws BusinessException, NotFoundException {
        Optional<Ticket> opt = null;
        try {
            opt = repository.findById(id);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró Ticket " + id);
        } else {
            try {
                repository.deleteById(id);
            } catch (Exception e1) {
                throw new BusinessException(e1.getMessage());
            }
        }

    }
    public Ticket updateTicket(Ticket ticket)throws BusinessException,NotFoundException{
        Optional<Ticket> opt;
        try{
            opt = repository.findById(ticket.getId());
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el producto " + ticket.getId());
        }
        else{
            try {
                Ticket existingTicket=new Ticket();
                existingTicket.setId(ticket.getId());
                existingTicket.setCliente(ticket.getCliente());
                existingTicket.setTelefono(ticket.getTelefono());
                existingTicket.setFechaentrega(ticket.getFechaentrega());
                existingTicket.setProducto(ticket.getProducto());
                existingTicket.setCantidad(ticket.getCantidad());
                return repository.save(existingTicket);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }

        }
    }
}
