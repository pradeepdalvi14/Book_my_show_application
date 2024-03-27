package com.acciojob.Book_my_show_application.Repository;

import com.acciojob.Book_my_show_application.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieRepository extends JpaRepository<Movie,Integer> {

    Movie findMovieByMovieName(String movieName);//jpaQuery

    //sql query
    @Query(value = "select * from movies where movie_name = :movieName ", nativeQuery = true )
    Movie findMovie(String movieName);
}
