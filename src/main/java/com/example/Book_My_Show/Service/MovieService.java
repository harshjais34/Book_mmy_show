package com.example.Book_My_Show.Service;


import com.example.Book_My_Show.Controllers.MovieController;
import com.example.Book_My_Show.Dtos.MovieRequestDto;
import com.example.Book_My_Show.Model.MovieEntity;
import com.example.Book_My_Show.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;
    public String addMovie(MovieRequestDto movieRequestDto){
        //Convert Dto to Entity layer for saving it into in database;
        MovieEntity movie=MovieEntity.builder().movieName(movieRequestDto.getName()).duration(movieRequestDto.getDuration()).releaseDate(movieRequestDto.getReleaseDate()).build();

        movieRepository.save(movie);
        return "movie added succesfully";
    }
}
