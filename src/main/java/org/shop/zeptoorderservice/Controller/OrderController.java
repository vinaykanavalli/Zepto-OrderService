package org.shop.zeptoorderservice.Controller;


import org.shop.zeptoorderservice.Order.Request.OrderRequest;
import org.shop.zeptoorderservice.Order.Response.OrderResponse;
import org.shop.zeptoorderservice.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order/")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("place")
    public OrderResponse placeOrder(@RequestBody OrderRequest orderRequest) {
        System.out.println("OrderController.placeOrder():::::::::::::::::::::::::::: START");
        OrderResponse orderResponse = orderService.acceptOrder(orderRequest);
        System.out.println("OrderController.placeOrder():::::::::::::::::::::::::::: END");
        return orderResponse;
    }

}