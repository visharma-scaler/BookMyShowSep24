package com.scaler.bookmyshowsep24.controllers;

import com.scaler.bookmyshowsep24.dtos.BookTicketRequestDto;
import com.scaler.bookmyshowsep24.dtos.BookTicketResponseDto;
import com.scaler.bookmyshowsep24.dtos.ResponseStatus;
import com.scaler.bookmyshowsep24.models.Booking;
import com.scaler.bookmyshowsep24.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public BookTicketResponseDto bookTicket(BookTicketRequestDto requestDto) {
        BookTicketResponseDto responseDto = new BookTicketResponseDto();
        try {
            Booking booking = bookingService.bookMovie(requestDto.getShowSeatIds(), requestDto.getShowId(), requestDto.getUserId());
            responseDto.setStatus(ResponseStatus.SUCCESS);
            responseDto.setBookingId(booking.getId());
            responseDto.setAmount(booking.getAmount());
        } catch (Exception e) {
            responseDto.setStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
