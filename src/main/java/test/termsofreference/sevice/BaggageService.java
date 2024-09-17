package test.termsofreference.sevice;

import test.termsofreference.entity.Baggage;

public interface BaggageService {
    void checkInBaggage(long destinationId, long baggageId);
    Baggage getBaggageById(long baggageId);
    Baggage save(Baggage baggage);
}