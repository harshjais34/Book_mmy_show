package com.example.Book_My_Show.Controllers;

import com.example.Book_My_Show.Dtos.BookTicketRequestDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {


    @Autowired
    TicketController ticketService;
    @PostMapping("/book")
    public String bookTicket(@RequestBody BookTicketRequestDto bookTicketRequestDto){
        try{
            return ticketService.bookTicket(bookTicketRequestDto);
        }
        catch (Exception e){
            return "Requested seats are not available";
        }

    }
}