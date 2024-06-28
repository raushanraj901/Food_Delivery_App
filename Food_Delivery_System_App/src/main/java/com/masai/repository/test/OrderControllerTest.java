package com.masai.repository.test;

import static org.hamcrest.CoreMatchers.is;;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.masai.model.Order;
import com.masai.services.OrderService;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    public void whenGetOrders_thenReturnOrders() throws Exception {
        Order order = new Order();
        order.setId(1L);

        List<Order> allOrders = Collections.singletonList(order);
        Mockito.when(orderService.getOrders(PageRequest.of(0, 10))).thenReturn(new PageImpl<>(allOrders));

        mockMvc.perform(get("/api/orders").param("page", "0").param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(order.getId().intValue())));
    }
}

