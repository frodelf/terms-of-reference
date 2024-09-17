package test.termsofreference.sevice.impl;

import org.springframework.stereotype.Service;
import test.termsofreference.db.Database;
import test.termsofreference.entity.Baggage;
import test.termsofreference.exception.EntityNotFoundException;
import test.termsofreference.sevice.BaggageService;
import test.termsofreference.sevice.ReceptionPointService;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class BaggageServiceImpl implements BaggageService {
    private static final Logger logger = Logger.getLogger(BaggageServiceImpl.class.getName());
    private final ReceptionPointService receptionPointService;

    private final Map<Long, Baggage> baggageCache = new HashMap<>();

    public BaggageServiceImpl(ReceptionPointService receptionPointService) {
        this.receptionPointService = receptionPointService;
    }

    @Override
    public void checkInBaggage(long destinationId, long baggageId) {
        logger.info("BaggageServiceImpl-checkInBaggage start");
        Baggage baggage = getBaggageById(baggageId);
        baggage.setReceptionPoint(receptionPointService.getReceptionPointServiceById(destinationId));
        save(baggage);
        baggageCache.put(baggage.getId(), baggage);
        logger.info("BaggageServiceImpl-checkInBaggage successful");
    }

    @Override
    public Baggage getBaggageById(long baggageId) {
        logger.info("BaggageServiceImpl-getBaggageById start");
        if (baggageCache.containsKey(baggageId)) {
            return baggageCache.get(baggageId);
        }

        Baggage baggage = Database.getInstance().getBaggage(baggageId);
        if (baggage == null) {
            throw new EntityNotFoundException("Baggage with id = " + baggageId + " not found");
        }
        baggageCache.put(baggageId, baggage);
        logger.info("BaggageServiceImpl-getBaggageById successful");
        return baggage;
    }

    @Override
    public Baggage save(Baggage baggage) {
        logger.info("BaggageServiceImpl-save start");
        Database.getInstance().setBaggage(baggage);
        baggageCache.put(baggage.getId(), baggage);
        logger.info("BaggageServiceImpl-save successful");
        return baggage;
    }
}