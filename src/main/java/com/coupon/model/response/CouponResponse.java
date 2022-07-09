package com.coupon.model.response;

import lombok.Data;

import java.util.Date;

@Data
public class CouponResponse {

    private String couponCode;
    private String couponDesc;
    private String discountType;
    private Float discountValue;
    private Float minPurchaseValue;
    private Date validUntil;

}
