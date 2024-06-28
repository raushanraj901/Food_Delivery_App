package com.masai.repository.test;

import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.masai.model.Customer;
import com.masai.model.Order;
import com.masai.model.Order.OrderStatus;
import com.masai.model.Restaurant;
import com.masai.repo.CustomerRepository;
import com.masai.repo.OrderRepository;
import com.masai.repo.RestaurantRepository;
import com.masai.services.OrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private RestaurantRepository restaurantRepository;

    @Test
    public void whenPlaceOrder_thenOrderShouldBePlaced() {
        Customer customer = new Customer();
        customer.setId(1L);

        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);

        Order order = new Order();
        order.setCustomer(customer);
        order.setRestaurant(restaurant);
        order.setOrderStatus(OrderStatus.PLACED);

        Mockito.when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        Mockito.when(restaurantRepository.findById(restaurant.getId())).thenReturn(Optional.of(restaurant));
        Mockito.when(orderRepository.save(order)).thenReturn(order);

        Order placedOrder = orderService.placeOrder(order);
        assertThat(placedOrder.getOrderStatus()).isEqualTo(OrderStatus.PLACED);
    }
}

