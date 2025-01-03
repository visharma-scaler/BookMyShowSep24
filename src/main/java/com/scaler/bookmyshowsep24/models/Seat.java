package com.scaler.bookmyshowsep24.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel {
    private String seatNumber;

    @ManyToOne
    private SeatType seatType;

    private int seatRow;
    private int seatCol;
}

//create table seat (id bigint not null auto_increment, created_at datetime(6),
//updated_at datetime(6), col_number integer not null, row_number integer not null,
//seat_number varchar(255), seat_type_id bigint, primary key (id))