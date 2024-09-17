package test.termsofreference.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import test.termsofreference.sevice.TicketService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TicketController.class)
class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;

    @Test
    void checkTicketAvailability() throws Exception {
        int ticketId = 1;
        boolean isAvailable = true;

        when(ticketService.isTicketAvailable(ticketId)).thenReturn(isAvailable);

        mockMvc.perform(get("/api/ticket/check-availability/{id}", ticketId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(isAvailable));

        verify(ticketService, times(1)).isTicketAvailable(ticketId);
    }
}
