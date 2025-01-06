package com.scaler.bookmyshowsep24.services;

import com.scaler.bookmyshowsep24.models.Booking;
import com.scaler.bookmyshowsep24.models.User;
import com.scaler.bookmyshowsep24.repositories.BookingRepository;
import com.scaler.bookmyshowsep24.repositories.ShowRepository;
import com.scaler.bookmyshowsep24.repositories.ShowSeatRepository;
import com.scaler.bookmyshowsep24.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final UserRepository userRepository;
    private final ShowRepository showRepository;
    private final ShowSeatRepository showSeatRepository;
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(UserRepository userRepository, ShowRepository showRepository, ShowSeatRepository showSeatRepository, BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.bookingRepository = bookingRepository;
    }


    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(List<Long> showSeatIds, Long showId, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return null;
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
