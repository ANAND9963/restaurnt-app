package com.restaurnt.restaurnt.app.request;

import com.restaurnt.restaurnt.app.model.Category;
import com.restaurnt.restaurnt.app.model.IngredientsItem;
import lombok.Data;

import java.util.List;

@Data
public class CreateFoodRequest {

    private String name;

    private String description;

    private Long price;

    private Category category;

    private List<String> images;

    private Long restaurantId;

    private boolean vegetarian;

    private boolean seasonal;

    private List<IngredientsItem> ingredients;
}
