package com.acciojob.Book_my_show_application.Requests;

import lombok.Data;

@Data
public class AddShowSeatRequest {

    private Integer showId;

    private Integer priceofClassicSeats;

    private Integer priceofPremiumSeats;


}
