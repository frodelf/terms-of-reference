package test.termsofreference.sevice.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import test.termsofreference.db.Database;
import test.termsofreference.entity.Baggage;
import test.termsofreference.entity.ReceptionPoint;
import test.termsofreference.exception.EntityNotFoundException;
import test.termsofreference.sevice.ReceptionPointService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class BaggageServiceImplTest {

    @Mock
    private ReceptionPointService receptionPointService;

    @Spy
    @InjectMocks
    private BaggageServiceImpl baggageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void checkInBaggageSuccess() {
        long destinationId = 1L;
        long baggageId = 1L;

        ReceptionPoint receptionPoint = new ReceptionPoint();
        receptionPoint.setId(destinationId);
        Baggage baggage = new Baggage();
        baggage.setId(baggageId);

        when(receptionPointService.getReceptionPointServiceById(destinationId)).thenReturn(receptionPoint);
        doReturn(baggage).when(baggageService).getBaggageById(baggageId);

        baggageService.checkInBaggage(destinationId, baggageId);

        assertEquals(receptionPoint, baggage.getReceptionPoint());
        verify(receptionPointService).getReceptionPointServiceById(destinationId);
    }

    @Test
    void getBaggageByIdFromCache() {
        long baggageId = 1L;
        Baggage baggage = new Baggage();
        baggage.setId(baggageId);
        baggageService.save(baggage);

        Baggage result = baggageService.getBaggageById(baggageId);

        assertEquals(baggage, result);
    }

    @Test
    void getBaggageByIdFromDatabase() {
        Baggage result = baggageService.getBaggageById(1);

        assertEquals(Database.getInstance().getBaggage(1), result);
    }

    @Test
    void getBaggageByIdNotFound() {
        long baggageId = 999L;

        Exception exception = assertThrows(EntityNotFoundException.class, () -> baggageService.getBaggageById(baggageId));
        assertEquals("Baggage with id = 999 not found", exception.getMessage());
    }

    @Test
    void saveBaggage() {
        Baggage baggage = new Baggage();
        baggage.setId(1L);

        baggageService.save(baggage);

        assertEquals(baggage, baggageService.getBaggageById(1L));
    }
}