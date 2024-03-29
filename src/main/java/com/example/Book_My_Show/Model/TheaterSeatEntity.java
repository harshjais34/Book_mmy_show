package com.example.Book_My_Show.Model;


import com.example.Book_My_Show.Enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "theater_seats")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheaterSeatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    //  @Column(unique = true,columnDefinition = "seat_no",nullable = false)
    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;
    private int rate;

    @JoinColumn
    @ManyToOne
    private TheaterEntity theater;

    public TheaterSeatEntity(String seatNo,SeatType seatType,int rate){
        this.seatNo=seatNo;
        this.seatType=seatType;
        this.rate= rate;
    }


}