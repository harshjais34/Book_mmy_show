package com.example.Book_My_Show.Service;

import com.example.Book_My_Show.Dtos.UserRequestDto;
import com.example.Book_My_Show.Model.UserEntity;
import com.example.Book_My_Show.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String createUser(UserRequestDto userRequestDto){

        // convert userequestDto into userEntity
        UserEntity userEntity=UserEntity.builder().name(userRequestDto.getName()).mobile(userRequestDto.getMobile()).build();

        try{
            userRepository.save(userEntity);
        }
        catch (Exception e){
            return "user couldn't be added";
        }
        return "User added Successfully";
    }
}