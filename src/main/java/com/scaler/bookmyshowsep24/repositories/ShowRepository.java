package com.scaler.bookmyshowsep24.repositories;

import com.scaler.bookmyshowsep24.models.MovieShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<MovieShow, Long> {
}
