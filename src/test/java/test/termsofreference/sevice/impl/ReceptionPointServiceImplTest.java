package test.termsofreference.sevice.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import test.termsofreference.db.Database;
import test.termsofreference.entity.ReceptionPoint;
import test.termsofreference.exception.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ReceptionPointServiceImplTest {
    @Spy
    @InjectMocks
    private ReceptionPointServiceImpl receptionPointService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getReceptionPointServiceById() {
        ReceptionPoint result = receptionPointService.getReceptionPointServiceById(1);

        assertEquals(Database.getInstance().getReceptionPoint(1), result);
    }

    @Test
    void getReceptionPointServiceByIdNotFound() {
        long receptionPointId = 999L;

        Exception exception = assertThrows(EntityNotFoundException.class, () -> receptionPointService.getReceptionPointServiceById(receptionPointId));
        assertEquals("Reception point with id = 999 not found", exception.getMessage());
    }
}