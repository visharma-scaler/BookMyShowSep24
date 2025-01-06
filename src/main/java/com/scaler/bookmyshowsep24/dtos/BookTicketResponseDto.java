package com.scaler.bookmyshowsep24.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookTicketResponseDto {
    private ResponseStatus status;
    private Long bookingId;
    private double amount;
}
