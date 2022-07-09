package com.coupon.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "coupon")
@ApiModel(description = "Details about the coupon")
public class Coupon {

    @Id
    @ApiModelProperty(notes = "The unique code of the coupon")
    private String couponCode;

    @NotBlank(message = "Please enter the coupon type")
    @ApiModelProperty(notes = "The coupon type :(public/private/restricted)")
    private String couponType;


    @NotNull(message = "Please enter the coupon description")
    @ApiModelProperty(notes = "This the description of the coupon")
    private String couponDesc;

    @NotNull(message = "Please enter the launch date")
    @ApiModelProperty(notes = "The date of launch of the coupon")
    private Date launchDate;

    @NotNull(message = "Please enter the expiry date")
    @ApiModelProperty(notes = "The expiry date of the coupon")
    private Date validUntil;

    @NotNull(message = "Please enter the minimum purchase value")
    @ApiModelProperty(notes = "The minimum value required to apply/avail the coupon on a particular order")
    private Float minPurchaseValue;

    @NotNull(message = "Please enter the discount type")
    @ApiModelProperty(notes = "The type of discount coupon has i.e,:Amount/Percentage ")
    private String discountType;

    @NotNull(message = "Please enter the discount value")
    @ApiModelProperty(notes = "The discount value(Amount/percentage) being offered in the coupon")
    private Float discountValue;


    @NotNull(message = "Please enter if coupon is enabled or not(true/false)")
    @ApiModelProperty(notes = "Is coupon available/launched or not ")
    private Boolean isEnabled = false;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    @ApiModelProperty(notes = "Date and time of creation of the coupon")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "Updated_at", nullable = false, updatable = false)
    @ApiModelProperty(notes = "Date and time of update of the coupon")
    private Date updatedAt;


}

