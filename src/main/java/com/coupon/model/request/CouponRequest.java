package com.coupon.model.request;


import lombok.Data;
import java.util.Date;

@Data
public class CouponRequest {

    private String couponCode;
    private String couponType;
    private String couponDesc;
    private String discountType;
    private Float discountValue;
    private Float minPurchaseValue;
    private Date launchDate;
    private Boolean isEnabled;
    private Date validUntil;
}

