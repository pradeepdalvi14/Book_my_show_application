package com.acciojob.Book_my_show_application.Models;

import com.acciojob.Book_my_show_application.Enums.SeatType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "theater_seats")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TheaterSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer theaterSeatId;


    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    @JoinColumn
    @ManyToOne
    private Theater theater;
}
