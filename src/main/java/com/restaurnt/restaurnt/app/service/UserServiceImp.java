package com.restaurnt.restaurnt.app.service;

import com.restaurnt.restaurnt.app.config.JwtProvider;
import com.restaurnt.restaurnt.app.model.User;
import com.restaurnt.restaurnt.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserByJwtToken(String jwt) throws Exception {

       String email = jwtProvider.getEmailFromJwtToken(jwt);

       User user = findUserByEmail(email);

        return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {

        User user = userRepository.findByEmail(email);

        if(user == null){
            throw new Exception("User not found.");
        }
        return user;
    }
}
