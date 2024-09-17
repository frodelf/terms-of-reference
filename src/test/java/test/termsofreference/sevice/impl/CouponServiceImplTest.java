package test.termsofreference.sevice.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import test.termsofreference.db.Database;
import test.termsofreference.entity.Coupon;
import test.termsofreference.exception.EntityNotFoundException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

class CouponServiceImplTest {

    @Spy
    @InjectMocks
    private CouponServiceImpl couponService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCouponById() {
        Coupon result = couponService.getCouponById(1);

        assertEquals(Database.getInstance().getCoupon(1), result);
    }

    @Test
    void getCouponByIdNotFound() {
        long couponId = 999L;

        Exception exception = assertThrows(EntityNotFoundException.class, () -> couponService.getCouponById(couponId));
        assertEquals("Coupon with id = 999 not found", exception.getMessage());
    }

    @Test
    void getTicketPriceWithDiscount() {
        BigDecimal discountedPrice = couponService.getTicketPriceWithDiscount(BigDecimal.valueOf(1000), 1);

        assertEquals(BigDecimal.valueOf(900.0), discountedPrice);
    }
}