package com.example.demo.model.requestbody;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgotPassword {
    private String email;

    private String newPassword;

    private String repeatNewPassword;
}
