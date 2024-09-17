package test.termsofreference.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import test.termsofreference.sevice.BaggageService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BaggageController.class)
class BaggageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BaggageService baggageService;

    @Test
    void checkIn() throws Exception {
        long destinationId = 1L;
        long baggageId = 2L;

        mockMvc.perform(put("/api/baggage/check-in")
                        .param("destinationId", String.valueOf(destinationId))
                        .param("baggageId", String.valueOf(baggageId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));

        verify(baggageService, times(1)).checkInBaggage(destinationId, baggageId);
    }
}
