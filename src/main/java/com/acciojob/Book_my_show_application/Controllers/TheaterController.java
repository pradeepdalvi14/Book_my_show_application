package com.acciojob.Book_my_show_application.Controllers;

import com.acciojob.Book_my_show_application.Models.TheaterSeat;
import com.acciojob.Book_my_show_application.Requests.AddTheaterRequest;
import com.acciojob.Book_my_show_application.Requests.AddTheaterSeatsRequest;
import com.acciojob.Book_my_show_application.Service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("theater")
public class TheaterController {

    @Autowired
    private TheaterService theaterService;

    @PostMapping("addTheater")
    public String addTheater(@RequestBody AddTheaterRequest addTheaterRequest){

        String result = theaterService.addTheater(addTheaterRequest);
        return result;

    }

    @PostMapping("addTheaterSeats")
    public String addTheaterSeats(@RequestBody AddTheaterSeatsRequest addTheaterSeatsRequest){
        String response = theaterService.addTheaterSeats(addTheaterSeatsRequest);
    return response;
    }

    @GetMapping("listOfseatsForTheater")
    public List<String> listOfseatsForTheater(@RequestParam("theaterId")Integer theaterId){
        List<TheaterSeat> theaterSeatList = theaterService.listOfseatsForTheater(theaterId);
        List<String> seats = new ArrayList<>();
        for (TheaterSeat theaterSeat:theaterSeatList){
            String seatNo = theaterSeat.getSeatNo();
            seats.add(seatNo);
        }
        return seats;
    }
}
