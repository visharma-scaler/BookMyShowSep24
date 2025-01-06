package com.scaler.bookmyshowsep24.repositories;

import com.scaler.bookmyshowsep24.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
