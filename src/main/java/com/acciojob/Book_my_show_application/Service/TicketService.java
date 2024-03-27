package com.acciojob.Book_my_show_application.Service;

import com.acciojob.Book_my_show_application.Exceptions.SeatNotAvailableException;
import com.acciojob.Book_my_show_application.Models.*;
import com.acciojob.Book_my_show_application.Repository.*;
import com.acciojob.Book_my_show_application.Requests.BookTicketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private UserRepository userRepository;
    public Ticket bookTicket(BookTicketRequest bookTicketRequest) throws Exception{

        //1. Calculate the total cost of the tickets
        Movie movie = movieRepository.findMovieByMovieName(bookTicketRequest.getMovieName());

        Theater theater = theaterRepository.findById(bookTicketRequest.getTheaterId()).get();


        //1.1 find the show entity with this date and time
        Show show = showRepository.findShowByShowDateAndShowTimeAndMovieAndTheater(bookTicketRequest.getShowDate(),bookTicketRequest.getShowTime()
                ,movie,theater);

        Integer showId = show.getShowId();

        List<ShowSeat> showSeatList = showSeatRepository.findShowSeats(showId);

        //Calculate the total amt and if all seats mentioned are available or not
        int totalAmount = 0;
        Boolean areAllSeatsAvailable = Boolean.TRUE;
        for(String seatNo:bookTicketRequest.getRequestedSeats()){
            for(ShowSeat showSeat:showSeatList){
                if (showSeat.getSeatNo().equals(seatNo)){
                    totalAmount = totalAmount+showSeat.getPrice();
                    if(showSeat.getIsAvailable()==Boolean.FALSE){
                        areAllSeatsAvailable = Boolean.FALSE;
                    }
                }
            }
        }
        if (areAllSeatsAvailable == Boolean.FALSE){
            throw new SeatNotAvailableException("The requested seats are unavailable");
        }
        //2. Make the seats booked : (Only if seats are available)

        for(String seatNo:bookTicketRequest.getRequestedSeats()){
            for(ShowSeat showSeat:showSeatList){
                if (showSeat.getSeatNo().equals(seatNo)){
                    showSeat.setIsAvailable(Boolean.FALSE);
                }
            }
        }

        User user = userRepository.findUserByMobNo(bookTicketRequest.getMobNo());

        //3. Save the ticket entity
        Ticket ticket = Ticket.builder().user(user)
                .movieName(bookTicketRequest.getMovieName())
                .showDate(bookTicketRequest.getShowDate())
                .theaterNameAndAddress(theater.getName()+" "+theater.getAddress())
                .showTime(bookTicketRequest.getShowTime())
                .totalAmtPaid(totalAmount)
                .build();

        ticket = ticketRepository.save(ticket);
        //4. Generate and return back the actual ticket response

        return ticket;

    }

}

