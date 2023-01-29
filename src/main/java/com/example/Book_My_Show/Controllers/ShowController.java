package com.example.Book_My_Show.Controllers;

import com.example.Book_My_Show.Dtos.ShowRequestDto;
import com.example.Book_My_Show.Service.ShowService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    ShowService showService;
    @PostMapping("/add")
    public String addShow(@RequestBody ShowRequestDto showRequestDto){
        return  showService.addShow(showRequestDto);

    }

}