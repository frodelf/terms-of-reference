package test.termsofreference.sevice.impl;

import org.springframework.stereotype.Service;
import test.termsofreference.db.Database;
import test.termsofreference.entity.Coupon;
import test.termsofreference.exception.EntityNotFoundException;
import test.termsofreference.sevice.CouponService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class CouponServiceImpl implements CouponService {
    private static final Logger logger = Logger.getLogger(CouponServiceImpl.class.getName());
    private final Map<Long, Coupon> couponCache = new HashMap<>();
    @Override
    public Coupon getCouponById(long id) {
        logger.info("CouponServiceImpl-getCouponById start");
        if (couponCache.containsKey(id)) {
            return couponCache.get(id);
        }

        Coupon coupon = Database.getInstance().getCoupon(id);
        if (coupon== null) {
            throw new EntityNotFoundException("Coupon with id = "+id+" not found");
        }
        couponCache.put(id, coupon);
        logger.info("CouponServiceImpl-getCouponById successful");
        return coupon;
    }
    @Override
    public BigDecimal getTicketPriceWithDiscount(BigDecimal price, long couponId) {
        logger.info("CouponServiceImpl-getTicketPriceWithDiscount start");
        BigDecimal result = price.divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(100).subtract(BigDecimal.valueOf(getCouponById(couponId).getDiscountPercentage())));
        logger.info("CouponServiceImpl-getTicketPriceWithDiscount successful");
        return result;
    }
}
