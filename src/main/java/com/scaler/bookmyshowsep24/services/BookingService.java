package com.scaler.bookmyshowsep24.services;

import com.scaler.bookmyshowsep24.models.*;
import com.scaler.bookmyshowsep24.repositories.BookingRepository;
import com.scaler.bookmyshowsep24.repositories.ShowRepository;
import com.scaler.bookmyshowsep24.repositories.ShowSeatRepository;
import com.scaler.bookmyshowsep24.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BookingService {

    private final UserRepository userRepository;
    private final ShowRepository showRepository;
    private final ShowSeatRepository showSeatRepository;
    private final BookingRepository bookingRepository;
    private final PriceCalculatorService priceCalculatorService;

    @Autowired
    public BookingService(UserRepository userRepository, ShowRepository showRepository,
                          ShowSeatRepository showSeatRepository, BookingRepository bookingRepository,
                          PriceCalculatorService priceCalculatorService) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.bookingRepository = bookingRepository;
        this.priceCalculatorService = priceCalculatorService;
    }


    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(List<Long> showSeatIds, Long showId, Long userId) {
        User user = getUser(userId);
        MovieShow movieShow = getMovieShow(showId);

        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);

        boolean allAvailable = showSeats.stream().allMatch(this::isShowSeatAvailable);

        if (!allAvailable) {
            throw new RuntimeException("Selected seats are not available");
        }

        showSeats.forEach(showSeat -> {
            showSeat.setShowSeatStatus(ShowSeatStatus.LOCKED);
            showSeat.setLockedAt(new Date());
        });

        List<ShowSeat> savedShowSeats = showSeatRepository.saveAll(showSeats);// Insert + Update -> Upsert operations

        Booking booking = Booking.builder()
                .movieShow(movieShow)
                .user(user)
                .showSeats(savedShowSeats)
                .status(BookingStatus.PENDING)
                .time(new Date())
                .amount(priceCalculatorService.calculate(movieShow, savedShowSeats))
                .payments(new ArrayList<>())
                .build();

        return bookingRepository.save(booking);
    }

    private MovieShow getMovieShow(Long showId) {
        return showRepository.findById(showId)
                .orElseThrow(() -> new RuntimeException("Show not found!"));
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"));
    }

    private boolean isShowSeatAvailable(ShowSeat showSeat) {
        return showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE) ||
                (showSeat.getShowSeatStatus().equals(ShowSeatStatus.LOCKED) &&
                        Duration.between(new Date().toInstant(), showSeat.getLockedAt().toInstant()).toMinutes() > 15);
    }
}


        /*
                ----------------- For today: Take Lock --------------------

                1. Get the user from userId
                2. Get the show from showId

                ----------------- Take Lock --------------------

                3. Get ShowSeats from showSeatIds
                4. Check all the seats are available or not
                5. If not, throw error
                6. If yes, mark the status for these seats as LOCKED
                7. Save the updated information in the database

                ----------------- Release Lock --------------------

                8. Create the corresponding booking object
                9. Return the booking object

                ----------------- For today: Release Lock --------------------
        */
