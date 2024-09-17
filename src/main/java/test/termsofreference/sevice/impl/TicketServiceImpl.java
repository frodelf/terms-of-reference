package test.termsofreference.sevice.impl;

import org.springframework.stereotype.Service;
import test.termsofreference.db.Database;
import test.termsofreference.entity.Ticket;
import test.termsofreference.exception.EntityNotFoundException;
import test.termsofreference.sevice.TicketService;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class TicketServiceImpl implements TicketService {
    private static final Logger logger = Logger.getLogger(TicketServiceImpl.class.getName());
    private final Map<Long, Ticket> ticketCache = new HashMap<>();

    @Override
    public boolean isTicketAvailable(long ticketId) {
        logger.info("TicketServiceImpl-isTicketAvailable start");
        boolean result = getTicketById(ticketId).getCustomer()==null;
        logger.info("TicketServiceImpl-isTicketAvailable successful");
        return result;
    }
    @Override
    public Ticket getTicketById(long ticketId) {
        logger.info("TicketServiceImpl-getTicketById start");
        if (ticketCache.containsKey(ticketId)) {
            return ticketCache.get(ticketId);
        }

        Ticket ticket = Database.getInstance().getTicket(ticketId);
        if (ticket == null) {
            throw new EntityNotFoundException("Ticket with id = "+ticketId+" not found");
        }
        ticketCache.put(ticketId, ticket);
        logger.info("TicketServiceImpl-getTicketById successful");
        return ticket;
    }
}