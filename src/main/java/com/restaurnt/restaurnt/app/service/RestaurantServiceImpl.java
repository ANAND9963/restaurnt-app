package com.restaurnt.restaurnt.app.service;

import com.restaurnt.restaurnt.app.dto.RestaurntDTO;
import com.restaurnt.restaurnt.app.model.Address;
import com.restaurnt.restaurnt.app.model.Restaurant;
import com.restaurnt.restaurnt.app.model.User;
import com.restaurnt.restaurnt.app.repository.AddressRepository;
import com.restaurnt.restaurnt.app.repository.RestaurantRepository;
import com.restaurnt.restaurnt.app.repository.UserRepository;
import com.restaurnt.restaurnt.app.request.CreateRestaurantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {

        Address address= addressRepository.save(req.getAddress());
        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setContactInformation(req.getContactInformation());
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setImages(req.getImages());
        restaurant.setName(req.getName());
        restaurant.setOpeningHours(req.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);

        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception {


        Restaurant restaurant = findRestaurantById(restaurantId);

        if(restaurant.getCuisineType() != null){
            restaurant.setCuisineType(updatedRestaurant.getCuisineType());
        }
        if(restaurant.getDescription() != null){
            restaurant.setDescription(updatedRestaurant.getDescription());

        }
        if(restaurant.getName() != null){
            restaurant.setName((updatedRestaurant.getName()));
        }
        if(restaurant.getContactInformation() != null){
            restaurant.setContactInformation(updatedRestaurant.getContactInformation());
        }
        if(restaurant.getOpeningHours()!= null){
            restaurant.setOpeningHours(updatedRestaurant.getOpeningHours());
        }
        if(restaurant.getImages() != null){
            restaurant.setImages(updatedRestaurant.getImages());
        }
        if(restaurant.getAddress() != null){
            restaurant.setAddress(updatedRestaurant.getAddress());
        }
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {

        Restaurant restaurant= findRestaurantById(restaurantId);

        restaurantRepository.delete(restaurant);

    }

    @Override
    public List<Restaurant> getAllRestaurant() {


        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {

        Optional<Restaurant> opt = restaurantRepository.findById(id);

        if(opt.isEmpty()){
            throw new Exception("Restaurant is not found with this id : " + id);
        }
        return opt.get();
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {

        Restaurant restaurant = restaurantRepository.findByOwnerId(userId);
        if(restaurant == null){
            throw new Exception("Restaurant is not found with this owner Id : "+ userId );
        }
        return restaurant;
    }

    @Override
    public RestaurntDTO addToFavorites(Long restaurantId, User user) throws Exception {

        Restaurant restaurant= findRestaurantById(restaurantId);

        RestaurntDTO dto = new RestaurntDTO();
        dto.setDescription(restaurant.getDescription());
        dto.setImages(restaurant.getImages());
        dto.setTitle(restaurant.getName());
        dto.setId(restaurantId);

       boolean isFavorited = false;

       List<RestaurntDTO> favorites = user.getFavorites();
       for(RestaurntDTO favorite : favorites){
           if(favorite.getId().equals(restaurantId)){
               isFavorited = true;
               break;
           }
       }
       if(isFavorited){
           favorites.removeIf(favorite -> favorite.getId().equals(restaurantId));
       }else{
           favorites.add(dto);
       }


        userRepository.save(user);

        return dto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {

        Restaurant restaurant = findRestaurantById(id);
        restaurant.setOpen(!restaurant.isOpen());

        return restaurantRepository.save(restaurant);
    }
}
