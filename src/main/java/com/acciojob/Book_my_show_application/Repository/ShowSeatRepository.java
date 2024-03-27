package com.acciojob.Book_my_show_application.Repository;

import com.acciojob.Book_my_show_application.Models.Show;
import com.acciojob.Book_my_show_application.Models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ShowSeatRepository extends JpaRepository<ShowSeat,Integer> {

    public List<ShowSeat> findAllByShow(Show show); //inbuilt method
    //custom jpa query
    @Query(nativeQuery = true,value = "select * from show_seats where show_show_id = :showId")
    public List<ShowSeat> findShowSeats(Integer showId);

    @Query(nativeQuery = true, value = "SELECT * FROM show_seats WHERE is_Available = true AND show_show_id = :showId")
    List<ShowSeat> showAvailableSeats(Integer showId);
}
