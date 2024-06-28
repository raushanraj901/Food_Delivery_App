package com.masai.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.masai.model.Order;
import com.masai.repo.OrderRepository;
import com.masai.repo.CustomerRepository;
import com.masai.repo.RestaurantRepository;
import com.masai.exception.NotFoundException;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Transactional
    public Order placeOrder(Order order) {
        customerRepository.findById(order.getCustomerId())
                .orElseThrow(() -> new NotFoundException("Customer not found with ID: " + order.getCustomerId()));

        restaurantRepository.findById(order.getRestaurantId())
                .orElseThrow(() -> new NotFoundException("Restaurant not found with ID: " + order.getRestaurantId()));

        order.setOrderStatus(Order.OrderStatus.PLACED);
        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order not found with ID: " + id));
    }
}

