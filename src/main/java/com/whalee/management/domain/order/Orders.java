package com.whalee.management.domain.order;

import com.whalee.management.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Orders extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 주문 상태 accept 인지 complete 인지 등 OrderStatus Enum 에 선언
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus ordersStatus;

    // 주문 타이틀
    @Column(nullable = false)
    private String title;

    // 주문 내용
    @Column(nullable = false)
    private String contents;

    // 주문자 id
//    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name="user_id")
//    private User user;

    @Builder
    public Orders(String title, String contents){
        this.title = title;
        this.contents = contents;
        this.ordersStatus = OrderStatus.WAIT; // Builder 를 통한 생성 시 무조건 주문 상태 '대기'로 고정.
    }

    public void statusUpdate(OrderStatus ordersStatus){
        this.ordersStatus = ordersStatus;
    }
}
