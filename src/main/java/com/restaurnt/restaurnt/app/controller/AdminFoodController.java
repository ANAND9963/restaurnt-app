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

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;


    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req,
                                           @RequestHeader("Authorization") String jwt) throws Exception{

        User user = userService.findUserByJwtToken(jwt);

        Restaurant restaurant = restaurantService.findRestaurantById(req.getRestaurantId());

        Food food = foodService.createFood(req , req.getCategory() ,restaurant);

        return new ResponseEntity<>(food , HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> createFood(@PathVariable Long id,
                                           @RequestHeader("Authorization") String jwt) throws Exception{

        User user = userService.findUserByJwtToken(jwt);
        foodService.deleteFood(id);

        MessageResponse res = new MessageResponse();
        res.setMessage("food deleted successfully");

        return new ResponseEntity<>(res , HttpStatus.OK);


    }


    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFoodAvailabilityStatus(@PathVariable Long id,
                                           @RequestHeader("Authorization") String jwt) throws Exception{

        User user = userService.findUserByJwtToken(jwt);



        Food food = foodService.updateAvailibilityStatus(id);

        return new ResponseEntity<>(food , HttpStatus.CREATED);
    }


}
