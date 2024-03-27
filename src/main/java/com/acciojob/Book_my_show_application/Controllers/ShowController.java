package com.acciojob.Book_my_show_application.Controllers;

import com.acciojob.Book_my_show_application.Exceptions.InvalidMovieNameException;
import com.acciojob.Book_my_show_application.Exceptions.InvalidTheaterIdException;
import com.acciojob.Book_my_show_application.Models.Show;
import com.acciojob.Book_my_show_application.Requests.AddShowRequest;
import com.acciojob.Book_my_show_application.Requests.AddShowSeatRequest;
import com.acciojob.Book_my_show_application.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("show")
public class ShowController {

    @Autowired
    private ShowService showService;
    @PostMapping("addShow")
    public String addShow(@RequestBody AddShowRequest addShowRequest){
        try {
            String response = showService.addShows(addShowRequest);
            return response;
            //InvalidmovieName and Invalid theaterId exceptions are collapsed with the Exception e2

        }//InvalidmovieName and Invalid theaterId exceptions are collapsed with the Exception e2
        //catch(InvalidMovieNameException e){
        //      return e.getMessage();
        //  }
        //catch(InvalidTheaterIdException e1){
            //      return e1.getMessage();
            //  }
        //
        //
        catch (Exception e2){
            return e2.getMessage();
        }

    }

    @PostMapping("addShowseats")
    public String addShowSeats(@RequestBody AddShowSeatRequest showSeatRequest){
        String response = showService.addShowSeats(showSeatRequest);
        return response;
    }
    @GetMapping("findShowsByMovie")
    public List<Integer> findShowsByMovie(@RequestParam("movieName")String movieName){

        List<Show> showList = showService.findShowsByMovie(movieName);
        List<Integer> showIds = new ArrayList<>();
        for(Show show:showList){
            showIds.add(show.getShowId());
        }
        return showIds;
    }
    @GetMapping("getShowByTheater")
    public List<Integer> getShowByTheater(@RequestParam("theaterId")Integer theaterId){
        List<Integer> showList = showService.getShowByTheater(theaterId);
        return showList;
    }
}
