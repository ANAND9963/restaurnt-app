package com.restaurnt.restaurnt.app.repository;

import com.restaurnt.restaurnt.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    public  User findByEmail(String username);

}
