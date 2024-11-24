package com.restaurnt.restaurnt.app.response;

import com.restaurnt.restaurnt.app.model.USER_ROLE;
import com.restaurnt.restaurnt.app.model.User;
import lombok.Data;

@Data
public class AuthResponse {

    private String jwt;

    private String message;

    private USER_ROLE role;
}
