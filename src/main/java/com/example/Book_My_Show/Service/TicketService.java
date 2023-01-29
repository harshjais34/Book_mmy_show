package com.example.Book_My_Show.Service;

import com.example.Book_My_Show.Dtos.BookTicketRequestDto;
import com.example.Book_My_Show.Model.ShowEntity;
import com.example.Book_My_Show.Model.ShowSeatEntity;
import com.example.Book_My_Show.Model.TicketEntity;
import com.example.Book_My_Show.Model.UserEntity;
import com.example.Book_My_Show.Repository.ShowRepository;
import com.example.Book_My_Show.Repository.TicketRepository;
import com.example.Book_My_Show.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    ShowRepository showRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    TicketRepository ticketRepository;
    public String bookTicket(BookTicketRequestDto bookTicketRequestDto) throws Exception{
        // Get the requested seats;
        List<String> requestedSeats= bookTicketRequestDto.getRequestSeats();
        ShowEntity showEntity=showRepository.findById(bookTicketRequestDto.getShowId()).get();
        UserEntity userEntity= userRepository.findById(bookTicketRequestDto.getUserId()).get();

        // get the show seats from showentity
        List<ShowSeatEntity> showSeats=showEntity.getListOfSeats();

        // validate if i can allot these seats requested by user;
        List<ShowSeatEntity> bookedSeats= new ArrayList<>();

        for(ShowSeatEntity showSeat: showSeats){
            String seatNo= showSeat.getSeatNo();

            if(showSeat.isBooked()== false && requestedSeats.contains(seatNo)){
                bookedSeats.add(showSeat);
            }
        }

        if(bookedSeats.size()!=requestedSeats.size()){
            // some seats the userRequested are not available
            throw new  Exception("Requested seats are not available");
        }
        // This means the BookedSeats actually contain the bookedseats

        TicketEntity ticketEntity= new TicketEntity();

        //Calculating amount,setting BookStatus
        double totalAmount=0;
        double multiplier=showEntity.getMultiplier();
        String allotedSeats="";
        int rate =0;

        for(ShowSeatEntity bookedSeat:bookedSeats){
            bookedSeat.setBooked(true);
            bookedSeat.setBookedAt(new Date());
            bookedSeat.setTicket(ticketEntity);
            bookedSeat.setShow(showEntity);
            String seatNo=bookedSeat.getSeatNo();

            allotedSeats=allotedSeats +seatNo+",";

            if(seatNo.charAt(0)=='1'){
                rate =100;
            }
            else{
                rate=200;
            }
            totalAmount = totalAmount + multiplier*rate;

        }
        ticketEntity.setBooked_at(new Date());
        ticketEntity.setAmount((int)totalAmount);
        ticketEntity.setShow(showEntity);
        ticketEntity.setUser(userEntity);
        ticketEntity.setBookedSeats(bookedSeats);
        ticketEntity.setAlloted_seats(allotedSeats);

        // bidiretional mapping part is pending
        ticketRepository.save(ticketEntity);

        return " successfully  created a ticket";
    }
}