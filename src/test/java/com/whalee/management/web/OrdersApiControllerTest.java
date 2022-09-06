package com.whalee.management.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whalee.management.domain.order.Orders;
import com.whalee.management.domain.order.OrdersRepository;
import com.whalee.management.domain.order.OrderStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrdersApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @After
    public void tearDown() throws Exception{
        ordersRepository.deleteAll();
    }

    @Test
    public void order_change_status_accept() throws Exception{
        String title = "title";
        String contents = "contents";

        Orders orders = ordersRepository.save(Orders.builder()
                .title(title)
                .contents(contents)
                .build());

        String url = "http://localhost:" + port + "/api/orders/"+ orders.getId()+"/accept";

        mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(orders)))
                .andExpect(status().isOk());

        List<Orders> all = ordersRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContents()).isEqualTo(contents);
        assertThat(all.get(0).getId()).isEqualTo(orders.getId());
        assertThat(all.get(0).getOrdersStatus().getTitle()).isEqualTo(OrderStatus.ACCEPT.getTitle());
    }

    @Test
    public void order_change_status_complete() throws Exception{
        String title = "title";
        String contents = "contents";

        Orders orders = ordersRepository.save(Orders.builder()
                .title(title)
                .contents(contents)
                .build());

        String url = "http://localhost:" + port + "/api/orders/"+ orders.getId()+"/complete";

        mvc.perform(put(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsString(orders)))
                .andExpect(status().isOk());

        List<Orders> all = ordersRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContents()).isEqualTo(contents);
        assertThat(all.get(0).getId()).isEqualTo(orders.getId());
        assertThat(all.get(0).getOrdersStatus().getTitle()).isEqualTo(OrderStatus.COMPLETE.getTitle());
    }

    @Test
    public void order_get_single_order() throws Exception{
        String title = "title";
        String contents = "contents";

        Orders orders = ordersRepository.save(Orders.builder()
                .title(title)
                .contents(contents)
                .build());

        String url = "http://localhost:" + port + "/api/orders/"+ orders.getId();

        mvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsString(orders)))
                .andExpect(status().isOk());

        List<Orders> all = ordersRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContents()).isEqualTo(contents);
        assertThat(all.get(0).getId()).isEqualTo(orders.getId());
    }

    @Test
    public void order_get_order_list() throws Exception{
        String title = "title";
        String contents = "contents";

        List<Orders> ordersList = new ArrayList<Orders>();

        for(int i = 0; i < 10; i++){
            Orders orders = ordersRepository.save(Orders.builder()
                    .title(title + i)
                    .contents(contents + i)
                    .build());

            ordersList.add(orders);
        }

        String url = "http://localhost:" + port + "/api/orders";

        mvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsString(ordersList)))
                .andExpect(status().isOk());

        List<Orders> all = ordersRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title+"0");
        assertThat(all.get(0).getContents()).isEqualTo(contents+"0");
        assertThat(all.size()).isEqualTo(ordersList.size());
    }
}
