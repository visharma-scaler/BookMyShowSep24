package com.scaler.bookmyshowsep24;

import com.scaler.bookmyshowsep24.controllers.UserController;
import com.scaler.bookmyshowsep24.dtos.LoginRequestDto;
import com.scaler.bookmyshowsep24.dtos.LoginResponseDto;
import com.scaler.bookmyshowsep24.dtos.SignUpRequestDto;
import com.scaler.bookmyshowsep24.dtos.SignUpResponseDto;
import com.scaler.bookmyshowsep24.models.BaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BookMyShowSep24Application implements CommandLineRunner {

    private final UserController userController;

    @Autowired
    public BookMyShowSep24Application(UserController userController) {
        this.userController = userController;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookMyShowSep24Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        SignUpRequestDto signUpRequestDto = new SignUpRequestDto();
//        signUpRequestDto.setEmail("uttamjeet@scaler.com");
//        signUpRequestDto.setPassword("password");
//
//        SignUpResponseDto responseDto = userController.signUp(signUpRequestDto);
//
//        System.out.println(responseDto);


        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setEmail("bathula@scaler.com");
        loginRequestDto.setPassword("password");

        LoginResponseDto loginResponseDto = userController.login(loginRequestDto);
        System.out.println(loginResponseDto);

    }
}


/*

    ORM -> Object Relational Mapping



*/