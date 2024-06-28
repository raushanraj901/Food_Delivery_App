package com.masai.repository.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.masai.model.Customer;
import com.masai.model.Order;
import com.masai.model.Restaurant;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class IntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void givenOrder_whenGetOrder_thenStatus200() throws Exception {
        Order order = new Order();
        order.setCustomer(new Customer());
        order.setRestaurant(new Restaurant());

        restTemplate.postForEntity("/api/orders", order, Order.class);

        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].customer").exists());
    }
}
