package com.acciojob.Book_my_show_application.Controllers;

import com.acciojob.Book_my_show_application.Models.Movie;
import com.acciojob.Book_my_show_application.Requests.UpdateMovieRequest;
import com.acciojob.Book_my_show_application.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/addMovie")
    public String addMovie(@RequestBody Movie movie){
        String response = movieService.addMovie(movie);
        return response;
    }

    @PutMapping("/updateMovieAttributes")
    public String updateMovieAttributes(@RequestBody UpdateMovieRequest movieRequest){
        //You have made sure that only relevant attributes
        //are expose to the client

        String result = movieService.updateMovieAttributes(movieRequest);
        return result;

    }

}
