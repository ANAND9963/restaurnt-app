package com.restaurnt.restaurnt.app.service;

import com.restaurnt.restaurnt.app.model.User;

public interface UserService {

    public User findUserByJwtToken(String jwt) throws Exception;

    public User findUserByEmail(String email) throws Exception;
}
