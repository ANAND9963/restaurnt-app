package com.restaurnt.restaurnt.app.repository;

import com.restaurnt.restaurnt.app.model.IngredientCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory , Long> {

    List<IngredientCategory> findByRestaurantId(Long id);


}
