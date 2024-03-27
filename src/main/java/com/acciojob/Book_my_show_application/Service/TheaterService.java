package com.acciojob.Book_my_show_application.Service;

import com.acciojob.Book_my_show_application.Enums.SeatType;
import com.acciojob.Book_my_show_application.Models.Theater;
import com.acciojob.Book_my_show_application.Models.TheaterSeat;
import com.acciojob.Book_my_show_application.Repository.TheaterRepository;
import com.acciojob.Book_my_show_application.Repository.TheaterSeatRepository;
import com.acciojob.Book_my_show_application.Requests.AddTheaterRequest;
import com.acciojob.Book_my_show_application.Requests.AddTheaterSeatsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private TheaterSeatRepository theaterSeatRepository;

    public String addTheater(AddTheaterRequest addTheaterRequest){

        Theater theater = Theater.builder().address(addTheaterRequest.getAddress())
                .noOfScreens(addTheaterRequest.getNoOfScreens())
                .name(addTheaterRequest.getName())
                .build();
//
//        //Save the entity to the DB
//
        theater = theaterRepository.save(theater);
        return "The theater is svaed with a theaterId "+theater.getTheaterId();
    }

    public String addTheaterSeats(AddTheaterSeatsRequest addTheaterSeatsRequest){

        int noOfClassicSeats = addTheaterSeatsRequest.getNoOfClassicSeats();
        int noOfPremiumSeats = addTheaterSeatsRequest.getNoOfPremiumSeats();

        Integer theaterId = addTheaterSeatsRequest.getTheaterId();
        Theater theater = theaterRepository.findById(theaterId).get();

        int classicSeatCounter = 1;
        char ch='A';
        int rowNo = 1;

        List<TheaterSeat> theaterSeatList = new ArrayList<>();

        while(classicSeatCounter<=noOfClassicSeats){

            String seatNo = rowNo+""+ch;
            TheaterSeat theaterSeat = TheaterSeat.builder()
                    .seatNo(seatNo)
                    .seatType(SeatType.CLASSIC)
                    .theater(theater)
                    .build();

            theaterSeatList.add(theaterSeat);
            ch++;

            if(classicSeatCounter%5==0) {
                rowNo = rowNo+1;
                ch = 'A';
            }
            classicSeatCounter++;

        }

        int premiumSeatCounter = 1;

        ch='A';
        if(classicSeatCounter%5!=1)
          rowNo = rowNo+1;
        while(premiumSeatCounter<=noOfPremiumSeats){

            String seatNo = rowNo+""+ch;
            TheaterSeat theaterSeat = TheaterSeat.builder()
                    .seatNo(seatNo)
                    .theater(theater)
                    .seatType(SeatType.PREMIUM)
                    .build();

            theaterSeatList.add(theaterSeat);

            ch++;

            if(premiumSeatCounter%5==0){
                rowNo = rowNo+1;
                ch = 'A';
            }
            premiumSeatCounter++;

        }

        theater.setTheaterSeatList(theaterSeatList);
        theaterRepository.save(theater);

        //theaterSeatRepository.saveAll(theaterSeatList);
        //theater seats will automatically get saved due to bidirectional mapping
        return "Theater seats have been generated";

    }

    public List<TheaterSeat> listOfseatsForTheater(Integer theaterId){
        Theater theater = theaterRepository.findById(theaterId).get();
        List<TheaterSeat> theaterSeatList = theater.getTheaterSeatList();
        return theaterSeatList;

    }
}
