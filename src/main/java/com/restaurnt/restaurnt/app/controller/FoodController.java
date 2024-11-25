package com.restaurnt.restaurnt.app.controller;

import com.restaurnt.restaurnt.app.model.Food;
import com.restaurnt.restaurnt.app.model.Restaurant;
import com.restaurnt.restaurnt.app.model.User;
import com.restaurnt.restaurnt.app.request.CreateFoodRequest;
import com.restaurnt.restaurnt.app.response.MessageResponse;
import com.restaurnt.restaurnt.app.service.FoodService;
import com.restaurnt.restaurnt.app.service.RestaurantService;
import com.restaurnt.restaurnt.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;


    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String name,
                                           @RequestHeader("Authorization") String jwt) throws Exception{

        User user = userService.findUserByJwtToken(jwt);


        List<Food> foods = foodService.searchFood(name);

        return new ResponseEntity<>(foods , HttpStatus.OK);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(@RequestParam boolean vegitarian,
                                                        @RequestParam boolean seasonal,
                                                        @RequestParam boolean nonVeg,
                                                        @RequestParam(required = false)String food_category,
                                                 @PathVariable Long restaurantId,

                                                 @RequestHeader("Authorization") String jwt) throws Exception{

        User user = userService.findUserByJwtToken(jwt);


        List<Food> foods = foodService.getRestaurantFood(restaurantId,vegitarian,nonVeg,seasonal,food_category);

        return new ResponseEntity<>(foods , HttpStatus.OK);
    }


}
