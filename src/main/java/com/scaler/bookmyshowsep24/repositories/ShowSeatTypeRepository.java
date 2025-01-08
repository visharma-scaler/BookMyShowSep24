package com.scaler.bookmyshowsep24.repositories;

import com.scaler.bookmyshowsep24.models.MovieShow;
import com.scaler.bookmyshowsep24.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Long> {
    List<ShowSeatType> findAllByMovieShow(MovieShow movieShow);
}
