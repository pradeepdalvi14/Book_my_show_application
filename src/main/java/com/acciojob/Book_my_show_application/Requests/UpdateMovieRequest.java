package com.acciojob.Book_my_show_application.Requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMovieRequest {
    private Integer movieId;
    private double rating;
    private double duration;
}
