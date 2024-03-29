package com.example.Book_My_Show.Model;
import jakarta.persistence.*;

import com.example.Book_My_Show.Enums.SeatType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "show_seats")
@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor

public class ShowSeatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String seatNo;


    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;




    private boolean booked;

    private Date bookedAt;

    @ManyToOne
    @JoinColumn
    private ShowEntity show;

    @ManyToOne
    @JoinColumn
    private TicketEntity ticket;
}