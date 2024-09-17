package test.termsofreference.sevice;

import test.termsofreference.entity.Ticket;

public interface TicketService {
    boolean isTicketAvailable(long ticketId);
    Ticket getTicketById(long ticketId);
}