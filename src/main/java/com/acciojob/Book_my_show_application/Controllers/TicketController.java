package com.acciojob.Book_my_show_application.Controllers;

import com.acciojob.Book_my_show_application.Models.Ticket;
import com.acciojob.Book_my_show_application.Requests.BookTicketRequest;
import com.acciojob.Book_my_show_application.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;
    @PostMapping("bookTicket")
    public ResponseEntity bookTicket(@RequestBody BookTicketRequest bookTicketRequest){
        try{
            Ticket ticket = ticketService.bookTicket(bookTicketRequest);
            return new ResponseEntity(ticket,HttpStatus.OK);
        }catch (Exception e){
           String errMsg = "Error while booking you tickets: "+e.getMessage();
           return new ResponseEntity(errMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
