package com.whalee.management.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
    // 주문의 경우 주문한 사용자의 ID 값을 갖고 있어야할 것 같아 만들었습니다.
    // 현 과제에서는 필요하지 않아 사용하지 않았지만 필요 시 컬럼을 추가하여 사용할 수 있습니다.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String name;
}
