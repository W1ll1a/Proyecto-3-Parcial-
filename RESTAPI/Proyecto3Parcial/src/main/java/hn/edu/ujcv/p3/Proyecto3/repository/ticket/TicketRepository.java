package hn.edu.ujcv.p3.Proyecto3.repository.ticket;

import hn.edu.ujcv.p3.Proyecto3.entity.ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
     Optional<Ticket> findBycliente(String Ticket);
}
