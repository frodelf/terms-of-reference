package test.termsofreference.sevice.impl;

import org.springframework.stereotype.Service;
import test.termsofreference.db.Database;
import test.termsofreference.entity.ReceptionPoint;
import test.termsofreference.exception.EntityNotFoundException;
import test.termsofreference.sevice.ReceptionPointService;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class ReceptionPointServiceImpl implements ReceptionPointService {
    private static final Logger logger = Logger.getLogger(ReceptionPointServiceImpl.class.getName());
    private final Map<Long, ReceptionPoint> receptionPointCache = new HashMap<>();

    @Override
    public ReceptionPoint getReceptionPointServiceById(long id) {
        logger.info("ReceptionPointServiceImpl-getReceptionPointServiceById successful");
        if (receptionPointCache.containsKey(id)) {
            return receptionPointCache.get(id);
        }

        ReceptionPoint receptionPoint = Database.getInstance().getReceptionPoint(id);
        if (receptionPoint == null) {
            throw new EntityNotFoundException("Reception point with id = "+id+" not found");
        }
        receptionPointCache.put(id, receptionPoint);
        logger.info("ReceptionPointServiceImpl-getReceptionPointServiceById start");
        return receptionPoint;
    }
}
