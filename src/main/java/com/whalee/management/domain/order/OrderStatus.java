package com.whalee.management.domain.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {

    WAIT("ROLE_WAIT", "대기"),
    ACCEPT("ROLE_ACCEPT", "접수"),
    COMPLETE("ROLE_COMPLETE", "완료");

    private final String key;
    private final String title;
}
