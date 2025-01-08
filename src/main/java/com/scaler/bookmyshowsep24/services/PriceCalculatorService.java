package com.scaler.bookmyshowsep24.services;

import com.scaler.bookmyshowsep24.models.MovieShow;
import com.scaler.bookmyshowsep24.models.ShowSeat;
import com.scaler.bookmyshowsep24.models.ShowSeatType;
import com.scaler.bookmyshowsep24.repositories.ShowSeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculatorService {

    private final ShowSeatTypeRepository showSeatTypeRepository;

    @Autowired
    public PriceCalculatorService(ShowSeatTypeRepository showSeatTypeRepository) {
        this.showSeatTypeRepository = showSeatTypeRepository;
    }

    public double calculate(MovieShow movieShow, List<ShowSeat> savedShowSeats) {
        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByMovieShow(movieShow);

        double amount = 0;

        for (ShowSeat showSeat : savedShowSeats) {
            for (ShowSeatType showSeatType : showSeatTypes) {
                if (showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())) {
                    amount += showSeatType.getPrice();
                    break;
                }
            }
        }
        return amount;
    }
}
