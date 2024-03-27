package com.acciojob.Book_my_show_application.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "shows")
@Data //for getters and setters, toString , requiredargsConstruuctor
//noargs and allargs are not included in data we need to mention it explicitely
@Builder // when we add Builder no args will re be remove thats why we need to initialise it
@AllArgsConstructor
@NoArgsConstructor
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer showId;

    private LocalDate showDate; //"yyyy-mm-dd"

    private LocalTime showTime; //"hh:mm:ss"

    @ManyToOne
    @JoinColumn
    private Movie movie;

    @ManyToOne
    @JoinColumn
    private Theater theater;


}
