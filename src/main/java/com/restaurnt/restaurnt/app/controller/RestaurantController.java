package com.restaurnt.restaurnt.app.controller;

import com.restaurnt.restaurnt.app.dto.RestaurntDTO;
import com.restaurnt.restaurnt.app.model.Restaurant;
import com.restaurnt.restaurnt.app.model.User;
import com.restaurnt.restaurnt.app.request.CreateRestaurantRequest;
import com.restaurnt.restaurnt.app.service.RestaurantService;
import com.restaurnt.restaurnt.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {


    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurant(
            @RequestHeader("Authorization") String jwt,
            @RequestParam String keyword
    ) throws Exception{

        User user = userService.findUserByJwtToken(jwt);

        List<Restaurant> restaurant = restaurantService.searchRestaurant(keyword);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurant(
            @RequestHeader("Authorization") String jwt
    ) throws Exception{

        User user = userService.findUserByJwtToken(jwt);

        List<Restaurant> restaurant = restaurantService.getAllRestaurant();

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findRestaurnatById(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception{

        User user = userService.findUserByJwtToken(jwt);

        Restaurant restaurant = restaurantService.findRestaurantById(id);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @PutMapping("/{id}/add-favorites")
    public ResponseEntity<RestaurntDTO> addToFavorites(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception{

        User user = userService.findUserByJwtToken(jwt);

        RestaurntDTO restaurant = restaurantService.addToFavorites(id , user);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }




}
