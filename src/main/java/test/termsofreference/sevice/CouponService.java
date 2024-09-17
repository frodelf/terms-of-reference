package test.termsofreference.sevice;

import test.termsofreference.entity.Coupon;

import java.math.BigDecimal;

public interface CouponService {
    Coupon getCouponById(long id);
    BigDecimal getTicketPriceWithDiscount(BigDecimal price, long couponId);
}