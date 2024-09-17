package test.termsofreference.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.termsofreference.sevice.CouponService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {
    private final CouponService couponService;
    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @GetMapping("/get-ticket-price-with-discount")
    public ResponseEntity<BigDecimal> getCoupons(@RequestParam double price, @RequestParam long couponId) {
        return ResponseEntity.ok(couponService.getTicketPriceWithDiscount(BigDecimal.valueOf(price), couponId));
    }
}