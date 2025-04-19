package com.lz.manage.model.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 审核通用状态 枚举
 */
@Getter
public enum AuditCommonStatus {
    AUDIT_COMMON_STATUS_0("0", "待审核"),
    AUDIT_COMMON_STATUS_1("1", "同意"),
    AUDIT_COMMON_STATUS_2("2", "拒绝");

    private static final Map<String, AuditCommonStatus> VALUE_TO_ENUM = new HashMap<>();

    static {
        for (AuditCommonStatus item : values()) {
            VALUE_TO_ENUM.put(item.value, item);
        }
    }

    private final String value;
    private final String label;

    AuditCommonStatus(String value, String label) {
        this.value = value;
        this.label = label;
    }

    /**
     * 根据 value 获取对应的枚举
     *
     * @param value 枚举值
     * @return 对应的枚举对象，若无对应项则返回 Optional.empty()
     */
    public static Optional<AuditCommonStatus> getEnumByValue(String value) {
        if (value == null || value.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(VALUE_TO_ENUM.get(value));
    }
}
