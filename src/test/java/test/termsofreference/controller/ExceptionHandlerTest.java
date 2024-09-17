package test.termsofreference.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import test.termsofreference.exception.EntityNotFoundException;
import test.termsofreference.sevice.BaggageService;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BaggageController.class)
class ExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BaggageService baggageService;
    @Test
    void handleEntityNotFoundException() throws Exception {
        long destinationId = 1L;
        long baggageId = 2L;

        doThrow(new EntityNotFoundException("Baggage not found")).when(baggageService).checkInBaggage(destinationId, baggageId);

        mockMvc.perform(put("/api/baggage/check-in")
                        .param("destinationId", String.valueOf(destinationId))
                        .param("baggageId", String.valueOf(baggageId)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Baggage not found"));
    }
}