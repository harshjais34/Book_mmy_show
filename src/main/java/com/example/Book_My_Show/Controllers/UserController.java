package com.example.Book_My_Show.Controllers;

import com.example.Book_My_Show.Dtos.UserRequestDto;
import com.example.Book_My_Show.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    private com.example.Book_My_Show.Dtos.UserRequestDto UserRequestDto;

    @PostMapping("/add")
    public String addUser(@RequestBody UserRequestDto userRequestDto){

        return userService.createUser(UserRequestDto);

    }
}
