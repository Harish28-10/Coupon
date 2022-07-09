package com.coupon.services;

import com.coupon.entities.Coupon;
import com.coupon.model.request.CouponRequest;
import com.coupon.model.response.CouponResponse;
import com.coupon.repositories.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CouponServiceImpl implements CouponService {


    @Autowired
    private CouponRepository couponRepository;

    static final String s = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    static SecureRandom r = new SecureRandom();


    @Override
    public List<CouponResponse> getCoupons() {
        return ((List<Coupon>) couponRepository
                .findAll())
                .stream()
                .map(this::convertToCouponDTO)
                .collect(Collectors.toList());
    }


    @Override
    public CouponResponse getCoupon(String couponCode) {
        Optional<CouponResponse> responseDTO = couponRepository
                .findById(couponCode)
                .map(this::convertToCouponDTO);
        if (responseDTO.isPresent()) {
            return responseDTO.get();
        }
        throw new RuntimeException("Coupon not found with coupon code: " + couponCode);
    }


    @Override
    public CouponResponse updateCoupon(CouponRequest couponRequest) {
        Coupon coupon = convertToCoupon(couponRequest);
        return convertToCouponDTO(couponRepository.save(coupon));
    }

    @Override
    public CouponResponse saveCoupon(CouponRequest couponRequest) {
        Coupon coupon = convertToCoupon(couponRequest);
        return convertToCouponDTO(couponRepository.save(coupon));
    }

    @Override
    public void deleteCoupon(String couponCode) {
        couponRepository.deleteById(couponCode);
    }


    @Override
    public String generateCustomCode(String couponCode) {
        Optional<Coupon> c = couponRepository.findById(couponCode);
        if (c.isPresent()) {
            throw new RuntimeException(" Already exists.");
        } else {
            return "Generated";
        }
    }

    @Override
    public String generateRandomCode() {
        StringBuilder stringBuilder = new StringBuilder();
        Optional<Coupon> c;
        do {
            for (int i = 0; i < 10; i++) {
                stringBuilder.append(s.charAt(r.nextInt(s.length())));
            }
            c = couponRepository.findById(stringBuilder.toString());
        } while (c.isPresent());

        return stringBuilder.toString();
    }


    private Coupon convertToCoupon(CouponRequest couponRequest) {
        Coupon coupon = new Coupon();
        coupon.setCouponCode(couponRequest.getCouponCode());
        coupon.setCouponDesc(couponRequest.getCouponDesc());
        coupon.setCouponType(couponRequest.getCouponType());
        coupon.setDiscountType(couponRequest.getDiscountType());
        coupon.setDiscountValue(couponRequest.getDiscountValue());
        coupon.setMinPurchaseValue(couponRequest.getMinPurchaseValue());
        coupon.setLaunchDate(couponRequest.getLaunchDate());
        coupon.setIsEnabled(couponRequest.getIsEnabled());
        coupon.setValidUntil(couponRequest.getValidUntil());

        return coupon;
    }

    private CouponResponse convertToCouponDTO(Coupon coupon) {
        CouponResponse couponResponse = new CouponResponse();
        couponResponse.setCouponCode(coupon.getCouponCode());
        couponResponse.setCouponDesc(coupon.getCouponDesc());
        couponResponse.setDiscountType(coupon.getDiscountType());
        couponResponse.setDiscountValue(coupon.getDiscountValue());
        couponResponse.setMinPurchaseValue(coupon.getMinPurchaseValue());
        couponResponse.setValidUntil(coupon.getValidUntil());

        return couponResponse;
    }


}

