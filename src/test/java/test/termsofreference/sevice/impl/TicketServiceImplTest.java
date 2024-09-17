package test.termsofreference.sevice.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import test.termsofreference.db.Database;
import test.termsofreference.entity.Customer;
import test.termsofreference.entity.Ticket;
import test.termsofreference.exception.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TicketServiceImplTest {
    @Spy
    @InjectMocks
    private TicketServiceImpl ticketService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void isTicketAvailable() {
        boolean result = ticketService.isTicketAvailable(3);

        assertTrue(result);
    }

    @Test
    void isTicketAvailableNotAvailable() {
        boolean result = ticketService.isTicketAvailable(1);

        assertFalse(result);
    }

    @Test
    void getTicketById() {
        Ticket result = ticketService.getTicketById(1L);

        assertEquals(Database.getInstance().getTicket(1), result);
    }

    @Test
    void getTicketByIdNotFound() {
        long ticketId = 999L;

        Exception exception = assertThrows(EntityNotFoundException.class, () -> ticketService.getTicketById(ticketId));
        assertEquals("Ticket with id = 999 not found", exception.getMessage());
    }
}