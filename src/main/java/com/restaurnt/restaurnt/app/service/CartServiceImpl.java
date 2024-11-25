package com.restaurnt.restaurnt.app.service;

import com.restaurnt.restaurnt.app.model.Cart;
import com.restaurnt.restaurnt.app.model.CartItem;
import com.restaurnt.restaurnt.app.model.Food;
import com.restaurnt.restaurnt.app.model.User;
import com.restaurnt.restaurnt.app.repository.CartItemRepository;
import com.restaurnt.restaurnt.app.repository.CartRepository;
import com.restaurnt.restaurnt.app.repository.FoodRepository;
import com.restaurnt.restaurnt.app.request.AddCartItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{


    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private FoodService foodService;


    @Override
    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        Food food = foodService.findFoodById(req.getFoodId());

        Cart cart = cartRepository.findByCustomerId(user.getId());

        for(CartItem cartItem:cart.getItem()){
            if(cartItem.getFood().equals(food)){
                int newQuantity = cartItem.getQuantity()+ req.getQuantity();
                return updateCartItemQuantity(cartItem.getId(),newQuantity);
            }
        }

        CartItem newcartItem = new CartItem();
        newcartItem.setFood(food);
        newcartItem.setCart(cart);
        newcartItem.setQuantity(req.getQuantity());
        newcartItem.setIngredients(req.getIngredients());
        newcartItem.setTotalPrice(req.getQuantity()*food.getPrice());

        CartItem savedCartItem = cartItemRepository.save(newcartItem);
        cart.getItem().add(savedCartItem);

        return savedCartItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {

        Optional<CartItem> cartItemOptional= cartItemRepository.findById(cartItemId);
        if(cartItemOptional.isEmpty()){
            throw new Exception("Cart item not found");
        }
        CartItem item = cartItemOptional.get();
        item.setQuantity(quantity);

        item.setTotalPrice(item.getFood().getPrice()*quantity);


        return cartItemRepository.save(item);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        Cart cart = cartRepository.findByCustomerId(user.getId());

        Optional<CartItem> cartItemOptional= cartItemRepository.findById(cartItemId);
        if(cartItemOptional.isEmpty()){
            throw new Exception("Cart item not found");
        }
        CartItem item= cartItemOptional.get();

        cart.getItem().remove(item);

        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotals(Cart cart) throws Exception {

        Long total =0L;

        for(CartItem cartItem:cart.getItem()){

            total+=cartItem.getFood().getPrice()*cartItem.getQuantity();
        }

        return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {

        Optional<Cart> optionalCart = cartRepository.findById(id);
        if(optionalCart.isEmpty()){
            throw new Exception("Cart not found with id : "+ id);
        }
        return optionalCart.get();
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {

//        User user = userService.findUserByJwtToken(userId);

        Cart cart = cartRepository.findByCustomerId(userId);

        cart.setTotal(calculateCartTotals(cart));

        return cart;
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {

//        User user=userService.findUserByJwtToken(userId);

        Cart cart= findCartByUserId(userId);

        cart.getItem().clear();

        return cartRepository.save(cart);
    }
}
