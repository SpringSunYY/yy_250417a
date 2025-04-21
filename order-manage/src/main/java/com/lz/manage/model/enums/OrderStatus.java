package com.lz.manage.model.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 订单状态
 */
@Getter
public enum OrderStatus {
    ORDER_STATUS_0("0", "待支付"),
    ORDER_STATUS_1("1", "已支付");

    private final String value;
    private final String label;

    private static final Map<String, OrderStatus> VALUE_MAP = new HashMap<>();

    static {
        for (OrderStatus status : values()) {
            VALUE_MAP.put(status.value, status);
        }
    }

    OrderStatus(String value, String label) {
        this.value = value;
        this.label = label;
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value 枚举值
     * @return 对应的枚举项（Optional 包装）
     */
    public static Optional<OrderStatus> fromValue(String value) {
        return Optional.ofNullable(VALUE_MAP.get(value));
    }
}

