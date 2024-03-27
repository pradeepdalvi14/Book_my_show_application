package com.acciojob.Book_my_show_application.Requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddTheaterSeatsRequest {
    private int noOfClassicSeats;
    private int noOfPremiumSeats;
    private int theaterId;
}
