package test.termsofreference.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import test.termsofreference.sevice.CouponService;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CouponController.class)
class CouponControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CouponService couponService;

    @Test
    void getCoupons() throws Exception {
        double price = 1000.0;
        long couponId = 1L;
        BigDecimal discountedPrice = BigDecimal.valueOf(900.0);

        when(couponService.getTicketPriceWithDiscount(BigDecimal.valueOf(price), couponId))
                .thenReturn(discountedPrice);

        mockMvc.perform(get("/api/coupon/get-ticket-price-with-discount")
                        .param("price", String.valueOf(price))
                        .param("couponId", String.valueOf(couponId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(discountedPrice));

        verify(couponService, times(1))
                .getTicketPriceWithDiscount(BigDecimal.valueOf(price), couponId);
    }
}
