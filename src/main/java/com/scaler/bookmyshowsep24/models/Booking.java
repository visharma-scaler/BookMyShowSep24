package com.scaler.bookmyshowsep24.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel {

    @Enumerated(value = EnumType.STRING)   // "CONFIRMED"
//    @Enumerated(value = EnumType.ORDINAL)   // 0
    private BookingStatus status;

    @ManyToMany
    private List<ShowSeat> showSeats;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    private Date time;

    @ManyToOne
    private MovieShow movieShow;

    private double amount;

    @OneToMany
    private List<Payment> payments;
}
