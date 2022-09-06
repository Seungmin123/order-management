package com.whalee.management.web.dto;

import com.whalee.management.domain.order.OrderStatus;
import com.whalee.management.domain.order.Orders;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class OrdersListReponseDto {

    private Long id;

    private String title;

    private String contents;

    private OrderStatus orderStatus;

    private LocalDateTime modifiedDate;

    public OrdersListReponseDto(Orders entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.contents = entity.getContents();
        this.orderStatus = entity.getOrdersStatus();
        this.modifiedDate = entity.getModifiedDate();
    }
}
