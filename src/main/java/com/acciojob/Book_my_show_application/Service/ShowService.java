package com.acciojob.Book_my_show_application.Service;

import com.acciojob.Book_my_show_application.Enums.SeatType;
import com.acciojob.Book_my_show_application.Exceptions.InvalidMovieNameException;
import com.acciojob.Book_my_show_application.Exceptions.InvalidTheaterIdException;
import com.acciojob.Book_my_show_application.Models.*;
import com.acciojob.Book_my_show_application.Repository.MovieRepository;
import com.acciojob.Book_my_show_application.Repository.ShowRepository;
import com.acciojob.Book_my_show_application.Repository.ShowSeatRepository;
import com.acciojob.Book_my_show_application.Repository.TheaterRepository;
import com.acciojob.Book_my_show_application.Requests.AddShowRequest;
import com.acciojob.Book_my_show_application.Requests.AddShowSeatRequest;
import com.acciojob.Book_my_show_application.Requests.AddTheaterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;
    public String addShows(AddShowRequest showRequest) throws Exception{

        //I need to get movie entity and theater Entity

        Movie movie = movieRepository.findMovie(showRequest.getMovieName());
        if (movie == null){
            throw new InvalidMovieNameException("Enter correct movie name ");
        }

//        Theater theater = theaterRepository.findById(showRequest.getTheaterId()).get();
//       if (theater == null){
//           throw new InvalidTheaterIdException("Enter correct theaterId ");
//       }
        Optional<Theater> theaterOptional = theaterRepository.findById(showRequest.getTheaterId());
        if (!theaterOptional.isPresent()){
            throw new InvalidTheaterIdException("Enter correct theaterId");
        }
        Theater theater = theaterOptional.get();

        Show show = Show.builder()
                .showDate(showRequest.getShowDate())
                .showTime(showRequest.getShowTime())
                .movie(movie)
                .theater(theater)
                .build();

        show = showRepository.save(show);
        return "Show has been saved to the Db ";
    }
    public String addShowSeats(AddShowSeatRequest showSeatRequest){
        Integer showId = showSeatRequest.getShowId();
        Show show = showRepository.findById(showId).get();

        Theater theater = show.getTheater();
        List<TheaterSeat> theaterSeatList = theater.getTheaterSeatList();

        List<ShowSeat> showSeatList = new ArrayList<>();
        for (TheaterSeat theaterSeat:theaterSeatList) {
            ShowSeat showSeat = ShowSeat.builder()
                    .seatNo(theaterSeat.getSeatNo())
                    .seatType(theaterSeat.getSeatType())
                    .isAvailable(Boolean.TRUE)
                    .show(show)
                    .build();

            if (theaterSeat.getSeatType().equals(SeatType.CLASSIC)) {
                showSeat.setPrice(showSeatRequest.getPriceofClassicSeats());
            } else {
                showSeat.setPrice(showSeatRequest.getPriceofPremiumSeats());
            }

            showSeatList.add(showSeat);

        }
        showSeatRepository.saveAll(showSeatList);
        return "Show seats have been generated for given showId";
    }
    public List<Show> findShowsByMovie(String movieName){
        List<Show> showList = showRepository.findAll();
        List<Show> showsByMovie = new ArrayList<>();
        for (Show show:showList){
            if (show.getMovie().getMovieName().equals(movieName)){
                showsByMovie.add(show);
            }
        }
        return showsByMovie;
    }

    public List<Integer> getShowByTheater(Integer theaterId){
        List<Show> showList = showRepository.getShowByTheater(theaterId);
        List<Integer> shows = new ArrayList<>();
        for (Show show: showList){
            shows.add(show.getShowId());
        }
        return shows;
    }
}
