package com.restaurnt.restaurnt.app.request;

import com.restaurnt.restaurnt.app.model.Address;
import com.restaurnt.restaurnt.app.model.ContactInformation;
import lombok.Data;

import java.util.List;

@Data
public class CreateRestaurantRequest {

    private Long id;
    private String name;
    private String description;
    private String cuisineType;
    private Address address;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<String> images;

}
