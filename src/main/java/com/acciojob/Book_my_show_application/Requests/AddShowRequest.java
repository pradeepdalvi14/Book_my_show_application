package com.acciojob.Book_my_show_application.Requests;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AddShowRequest {

    private LocalDate showDate; //"yyyy-mm-dd"

    private LocalTime showTime; //"HH:MM:SS"

    private String movieName;

    private Integer theaterId;
}
