package com.acciojob.Book_my_show_application.Repository;

import com.acciojob.Book_my_show_application.Models.Movie;
import com.acciojob.Book_my_show_application.Models.Show;
import com.acciojob.Book_my_show_application.Models.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ShowRepository extends JpaRepository<Show,Integer> {

    public Show findShowByShowDateAndShowTimeAndMovieAndTheater(LocalDate showDate,
                                                                        LocalTime showTime,
                                                                        Movie movie,
                                                                        Theater theater);
    @Query(nativeQuery = true,value = "select * from shows where theater_theater_id = :theaterId")
    public List<Show> getShowByTheater(Integer theaterId);
}
