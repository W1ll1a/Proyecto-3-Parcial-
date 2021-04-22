package hn.edu.ujcv.p3.Proyecto3.service.ticket;

import hn.edu.ujcv.p3.Proyecto3.entity.ticket.Ticket;
import hn.edu.ujcv.p3.Proyecto3.exception.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exception.NotFoundException;

import java.util.List;

public interface ITicketService {
    Ticket  saveTicket (Ticket ticket )throws BusinessException;
    List<Ticket> getTickets () throws BusinessException;
    Ticket      getById (long id )throws BusinessException, NotFoundException;
    Ticket      getByCliente (String clietne )throws BusinessException, NotFoundException;
    void        deleteTicket (long id )throws BusinessException, NotFoundException;
    Ticket      updateTicket(Ticket ticket )throws BusinessException,NotFoundException;

}
