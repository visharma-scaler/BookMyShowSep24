package com.scaler.bookmyshowsep24.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Payment extends BaseModel {

    @Enumerated(value = EnumType.STRING)
    private PaymentProvider paymentProvider;
    private String referenceNumber;

    @Enumerated(value = EnumType.STRING)
    private PaymentStatus status;
    private double amount;
}
