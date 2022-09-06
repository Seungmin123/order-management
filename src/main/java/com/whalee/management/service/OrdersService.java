package com.whalee.management.service;

import com.whalee.management.config.error.message.ErrorMessage;
import com.whalee.management.domain.order.Orders;
import com.whalee.management.domain.order.OrdersRepository;
import com.whalee.management.domain.order.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<Orders> getOrderList(){
        // 약식으로 Order Class 를 만들어 리스트 조회 시 필요한 객체만 선언한 Dto를 만들지 않았습니다.
        return ordersRepository.findAll();
        // Order Class 를 정식으로 만들고 OrderResponseDto를 생성할 경우 아래 코드로 대체합니다.
//        return orderRepository.findAll().stream()
//                .map(OrderResponseDto::new)
//                .collect(Collectors.toList());
    }


    private Orders findByIdOrMsg(Long id, String message){
        Orders entity = ordersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(message));

        return entity;
    }

}
