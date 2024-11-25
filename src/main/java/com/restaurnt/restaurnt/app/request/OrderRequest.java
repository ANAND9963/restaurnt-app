package com.restaurnt.restaurnt.app.request;

import com.restaurnt.restaurnt.app.model.Address;
import lombok.Data;

@Data
public class OrderRequest {

    private Long restaurantId;
    private Address deliveryAddress;
}
