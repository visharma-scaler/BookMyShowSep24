package com.scaler.bookmyshowsep24.controllers;

import com.scaler.bookmyshowsep24.dtos.*;
import com.scaler.bookmyshowsep24.models.User;
import com.scaler.bookmyshowsep24.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) {
        SignUpResponseDto responseDto = new SignUpResponseDto();
        try {
            User user = userService.signUp(signUpRequestDto.getEmail(), signUpRequestDto.getPassword());
            responseDto.setStatus(ResponseStatus.SUCCESS);
            responseDto.setUserId(user.getId());
            responseDto.setMessage("SignUp Successful!");
        } catch (Exception e) {
            responseDto.setStatus(ResponseStatus.FAILURE);
            responseDto.setMessage(e.getMessage());
        }
        return responseDto;
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        LoginResponseDto responseDto = new LoginResponseDto();
        try {
            User user = userService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
            responseDto.setStatus(ResponseStatus.SUCCESS);
            responseDto.setUserId(user.getId());
            responseDto.setMessage("Login Successful!");
        } catch (Exception e) {
            responseDto.setStatus(ResponseStatus.FAILURE);
            responseDto.setMessage(e.getMessage());
        }
        return responseDto;
    }
}
