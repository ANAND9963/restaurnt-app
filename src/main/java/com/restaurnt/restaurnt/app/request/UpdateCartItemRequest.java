package com.restaurnt.restaurnt.app.request;

import lombok.Data;

@Data
public class UpdateCartItemRequest {

    private Long cartItemId;

    private int quantity;
}
