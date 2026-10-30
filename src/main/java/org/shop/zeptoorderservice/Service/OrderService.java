package org.shop.zeptoorderservice.Service;

import java.util.Random;

import org.shop.zeptoorderservice.Entity.OrderEntity;
import org.shop.zeptoorderservice.Entity.PaymentEntity;
import org.shop.zeptoorderservice.Order.Request.OrderRequest;
import org.shop.zeptoorderservice.Order.Response.OrderResponse;
import org.shop.zeptoorderservice.PaymentRepository.PaymentRepository;
import org.shop.zeptoorderservice.Repository.OrderRepository;
import org.shop.zeptoorderservice.Service.kafka.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tools.jackson.databind.ObjectMapper;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    KafkaService kafkaService;

    @Transactional
    public OrderResponse acceptOrder(OrderRequest request) {
        // Generate amount first to use for price
        int amount = 1000 + new Random().nextInt(9000);

        // 1. Save Order
        OrderEntity order = new OrderEntity();
        order.setCustomerId(request.getCustomerId());
        order.setPaymentMethod(request.getPaymentMethod());
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());
        order.setShippingAddress(request.getShippingAddress());
        order.setOrderId(10000 + new Random().nextInt(90000));
        order.setPrice(amount);             // Fixed: Satisfies database 'price' requirement
        order.setWarranty_months(12);       // Fixed: Satisfies database 'warranty_months' requirement

        OrderEntity savedOrder = orderRepository.save(order);

        // 2. Save Payment
        PaymentEntity payment = new PaymentEntity();
        payment.setAmount(amount);
        payment.setPaymentRef("REF" + (100 + new Random().nextInt(900)));
        payment.setStatus("PAID");

        PaymentEntity savedPayment = paymentRepository.save(payment);

        // 3. Build Response
        OrderResponse response = new OrderResponse();
        response.setOrderId(savedOrder.getOrderId());
        response.setCustomerId(savedOrder.getCustomerId());
        response.setTotalAmount(amount);
        response.setPaymentStatus("SUCCESS");
        response.setOrderStatus("PLACED");

        // 4. Send to Kafka if Paid
        if ("PAID".equalsIgnoreCase(savedPayment.getStatus())) {
            String json = new ObjectMapper().writeValueAsString(response);
            for (int i = 0; i < 1000; i++) {
                kafkaService.sendMessage("order-all-status", json + " message #" + i);
            }
        }

        return response;
    }
}