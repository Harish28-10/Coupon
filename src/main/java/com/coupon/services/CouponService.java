package com.coupon.services;

import com.coupon.model.request.CouponRequest;
import com.coupon.model.response.CouponResponse;
import java.util.List;

public interface CouponService {

    List<CouponResponse> getCoupons();

    CouponResponse getCoupon(String couponCode);

    CouponResponse updateCoupon(CouponRequest couponRequest);

    CouponResponse saveCoupon(CouponRequest couponRequest);

    void deleteCoupon(String couponCode);

    String generateRandomCode();

    String generateCustomCode(String couponCode);
}
