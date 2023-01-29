package com.example.Book_My_Show.Service;


import com.example.Book_My_Show.Dtos.ShowRequestDto;
import com.example.Book_My_Show.Model.*;
import com.example.Book_My_Show.Repository.MovieRepository;
import com.example.Book_My_Show.Repository.ShowRepository;
import com.example.Book_My_Show.Repository.ShowSeatRepository;
import com.example.Book_My_Show.Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    ShowSeatRepository showSeatRepository;
    @Autowired
    ShowRepository showRepository;
    public String addShow(ShowRequestDto showRequestDto){


        // Show Entity
        ShowEntity showEntity= ShowEntity.builder().showDate(showRequestDto.getShowDate()).showTime(showRequestDto.getShowTime()).multiplier(showRequestDto.getMultiplier()).build();

        // you need to get MovieRepo
        MovieEntity movieEntity=movieRepository.findByMovie(showRequestDto.getMovieName());


        // you need to get theaterRepository
        TheaterEntity theaterEntity= theaterRepository.findById(showRequestDto.getTheaterId()).get();

        showEntity.setTheater(theaterEntity);
        showEntity.setMovie(movieEntity);


        // because we are doing bidirectional mapping
        // optional
        //  List<ShowEntity> currentListOfShow= movieEntity.getListOfShows();
        //  currentListOfShow.add(showEntity);
        //  movieEntity.setListOfShows(currentListOfShow);
        movieEntity.getListOfShows().add(showEntity);
        theaterEntity.getListOfShows().add(showEntity);
        // theaterRepository.save(theaterEntity);     // optional

        List<ShowSeatEntity> seatEntityList=createShowSeats(theaterEntity.getTheaterSeatEntityList());

        showEntity.setListOfSeats(seatEntityList);

        // for each ShowSeat: we need to mark that to which show belongs(foreign key will be filled)
        for(ShowSeatEntity showSeat:seatEntityList){
            showSeat.setShow(showEntity);

        }
        theaterRepository.save(theaterEntity);
        movieRepository.save(movieEntity); //  if not work coment 63,62 line and uncomment line 64
        //  showRepository.save(showEntity); this doesnot needed be called bcz parent save function is being called;
        return "Show added successfully";
    }

    public List<ShowSeatEntity> createShowSeats(List<TheaterSeatEntity> theaterSeatEntityList){
        List<ShowSeatEntity> seats= new ArrayList<>();

        for(TheaterSeatEntity theaterSeat: theaterSeatEntityList){
            ShowSeatEntity showSeat= ShowSeatEntity.builder().seatNo(theaterSeat.getSeatNo()).seatType(theaterSeat.getSeatType()).build();
            seats.add(showSeat);
        }

        showSeatRepository.saveAll(seats);

        return seats;
    }
}