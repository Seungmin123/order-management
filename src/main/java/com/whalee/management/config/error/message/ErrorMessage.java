package com.whalee.management.config.error.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {

    ORDER_NOT_EXISTS("0000", "해당 주문이 없습니다."),
    ORDER_SEARCH_NOT_EXISTS("0001", "검색되는 주문이 없습니다.");

    private final String key;
    private final String title;
}
