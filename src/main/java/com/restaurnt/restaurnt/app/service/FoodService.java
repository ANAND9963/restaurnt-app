package com.restaurnt.restaurnt.app.service;

import com.restaurnt.restaurnt.app.model.Category;
import com.restaurnt.restaurnt.app.model.Food;
import com.restaurnt.restaurnt.app.model.Restaurant;
import com.restaurnt.restaurnt.app.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {

    public Food createFood(CreateFoodRequest req , Category category , Restaurant restaurant);

    public void  deleteFood(Long foodId) throws Exception;

    public List<Food> getRestaurantFood(Long restaurantId,boolean isVegetarian, boolean isNonveg, boolean isSeasonal,String foodCategory);

    public List<Food> searchFood(String keyword);

    public Food findFoodById(Long foodId) throws Exception;

    public  Food updateAvailibilityStatus(Long foodId) throws Exception;
}
