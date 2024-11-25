package com.restaurnt.restaurnt.app.controller;

import com.restaurnt.restaurnt.app.model.IngredientCategory;
import com.restaurnt.restaurnt.app.model.IngredientsItem;
import com.restaurnt.restaurnt.app.request.IngredientCategoryRequest;
import com.restaurnt.restaurnt.app.request.IngredientItemRequest;
import com.restaurnt.restaurnt.app.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @PostMapping("/category")
    public ResponseEntity<IngredientCategory> createIngredientCategory(@RequestBody IngredientCategoryRequest req) throws Exception {

        IngredientCategory item = ingredientService.createIngredientCategory(req.getName(), req.getRestaurantId());
        return new ResponseEntity<>(item , HttpStatus.CREATED);

    }

    @PostMapping
    public ResponseEntity<IngredientsItem> createIngredientItem(@RequestBody IngredientItemRequest req) throws Exception {

        IngredientsItem item = ingredientService.createIngredientItem(req.getRestaurantId(), req.getName(), req.getCategoryId());
        return new ResponseEntity<>(item , HttpStatus.CREATED);

    }

    @PutMapping("/{id}/stoke")
    public ResponseEntity<IngredientsItem> updateIngredientStock(@PathVariable Long id) throws Exception {

        IngredientsItem item = ingredientService.updateStock(id);
        return new ResponseEntity<>(item , HttpStatus.OK);

    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientsItem>> getRestaurantIngredient(@PathVariable Long id) throws Exception {

        List<IngredientsItem> item = ingredientService.findRestaurantsIngredients(id);
        return new ResponseEntity<>(item , HttpStatus.OK);

    }

    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientCategory>> getRestaurantIngredientCategory(@PathVariable Long id) throws Exception {

        List<IngredientCategory> item = ingredientService.findIngredientCategoryByRestaurantId(id);
        return new ResponseEntity<>(item , HttpStatus.OK);

    }


}
