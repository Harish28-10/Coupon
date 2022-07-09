package com.coupon.controller;

import com.coupon.model.request.CouponRequest;
import com.coupon.model.response.CouponResponse;
import com.coupon.services.CouponService;
import com.coupon.entities.Coupon;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CouponController {

    @Autowired
    private CouponService couponService;


    @GetMapping("/coupons")
    @ApiOperation(value = "Shows all the coupons available",
            response = Coupon.class)
    public ResponseEntity<List<CouponResponse>> getCoupons() {
        return new ResponseEntity<List<CouponResponse>>(couponService.getCoupons(), HttpStatus.OK);
    }


    @GetMapping("/coupon")
    @ApiOperation(value = "Finds coupon by coupon code ",
            notes = "Provide a coupon code to look up specific coupon"
            , response = Coupon.class)
    public ResponseEntity<CouponResponse> getCouponById(@RequestParam String couponCode) {
        return new ResponseEntity<CouponResponse>(couponService.getCoupon(couponCode), HttpStatus.OK);
    }

    @PutMapping("/update")
    @ApiOperation(value = "Updates coupon by coupon code ",
            notes = "Provide a coupon code to update the desired coupon",
            response = Coupon.class)
    public ResponseEntity<CouponResponse> updateCoupon(@RequestParam String couponCode,
                                                       @RequestBody CouponRequest couponRequest) {
        couponRequest.setCouponCode(couponCode);
        return new ResponseEntity<CouponResponse>(couponService.updateCoupon(couponRequest),
                HttpStatus.OK);
    }

    @PostMapping("/save")
    @ApiOperation(value = "Creates a new coupon ",
            response = Coupon.class)
    public ResponseEntity<CouponResponse> createCoupon(
            @Valid @RequestBody CouponRequest couponRequest) {
        return new ResponseEntity<CouponResponse>(couponService.saveCoupon(couponRequest),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "Deletes coupon by coupon code "
            , notes = "Provide a couponCode to delete a specific coupon")
    public ResponseEntity<HttpStatus> deleteCoupon(@RequestParam String couponCode) {
        try {
            couponService.deleteCoupon(couponCode);
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/customcode")
    @ApiOperation(value = "creates a unique custom coupon code ",
            notes = "Provide desired set of characters for  coupon code ")
    public ResponseEntity<String> createCustomCode(@RequestParam String couponCode) {
        return new ResponseEntity<String>(couponService.generateCustomCode(couponCode),
                HttpStatus.CREATED);
    }

    @GetMapping("/randomcode")
    @ApiOperation(value = "Generates a unique coupon code ")
    public ResponseEntity<String> createRandomCouponCode() {
        return new ResponseEntity<String>(couponService.generateRandomCode(), HttpStatus.CREATED);
    }
}



