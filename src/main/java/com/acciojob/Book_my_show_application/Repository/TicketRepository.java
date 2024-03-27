package com.acciojob.Book_my_show_application.Repository;

import com.acciojob.Book_my_show_application.Models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,String> {


}
