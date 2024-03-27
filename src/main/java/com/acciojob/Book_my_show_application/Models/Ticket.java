package com.acciojob.Book_my_show_application.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "ticket")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String ticketId;

    private String movieName;

    private LocalDate showDate;

    private LocalTime showTime;

    private String theaterNameAndAddress;

    private Integer totalAmtPaid;

    @ManyToOne
    @JoinColumn
   private User user;

}
