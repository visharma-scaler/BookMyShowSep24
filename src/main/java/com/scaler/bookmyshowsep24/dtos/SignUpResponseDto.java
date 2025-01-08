package com.scaler.bookmyshowsep24.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignUpResponseDto {
    private ResponseStatus status;
    private Long userId;
    private String message;
}
