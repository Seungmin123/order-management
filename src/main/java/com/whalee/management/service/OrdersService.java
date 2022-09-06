package com.whalee.management.service;

import com.whalee.management.config.error.message.ErrorMessage;
import com.whalee.management.domain.order.Orders;
import com.whalee.management.domain.order.OrdersRepository;
import com.whalee.management.domain.order.OrderStatus;
import com.whalee.management.web.dto.OrdersListReponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;

    @Transactional
    public Long orderStatusChange(Long id, OrderStatus orderStatus){
        Orders orders = findByIdOrMsg(id, ErrorMessage.ORDER_NOT_EXISTS.getTitle());

        orders.statusUpdate(orderStatus);

        return id;
    }

    public Orders findSingleOrder(Long id){
        Orders orders = findByIdOrMsg(id, ErrorMessage.ORDER_SEARCH_NOT_EXISTS.getTitle());

        return orders;
    }

    @Transactional(readOnly = true)
    public List<OrdersListReponseDto> getOrderList(){
        return ordersRepository.findAll().stream()
                .map(OrdersListReponseDto::new)
                .collect(Collectors.toList());
    }


    private Orders findByIdOrMsg(Long id, String message){
        Orders entity = ordersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(message));

        return entity;
    }

}
