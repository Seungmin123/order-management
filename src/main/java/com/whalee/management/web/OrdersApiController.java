package com.whalee.management.web;

import com.whalee.management.domain.order.Orders;
import com.whalee.management.domain.order.OrderStatus;
import com.whalee.management.service.OrdersService;
import com.whalee.management.web.dto.OrdersListReponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrdersApiController {

    private final OrdersService ordersService;

    // 주문 접수처리
    @PutMapping("/api/orders/{id}/accept")
    public Long orderAccecpt(@PathVariable Long id){
        return ordersService.orderStatusChange(id, OrderStatus.ACCEPT);
    }

    // 주문 완료처리
    @PutMapping("/api/orders/{id}/complete")
    public Long orderComplete(@PathVariable Long id){
        return ordersService.orderStatusChange(id, OrderStatus.COMPLETE);
    }

    // 단일 주문조회
    @GetMapping("/api/orders/{id}")
    public Orders findSingleOrder(@PathVariable Long id){
        return ordersService.findSingleOrder(id);
    }

    // 주문 목록조회
    @GetMapping("/api/orders")
    public List<OrdersListReponseDto> getOrderList(){
        return ordersService.getOrderList();
    }
}
