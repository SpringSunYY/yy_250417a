package com.lz.manage.model.dto.goodsCardInfo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Project: order
 * Package: com.lz.manage.model.dto.goodsCardInfo
 * Author: YY
 * CreateTime: 2025-04-21  16:54
 * Description: GoodsCardPay
 * Version: 1.0
 */
@Data
public class GoodsCardPay {
    @NotEmpty(message = "cardIds不能为空")
    private String cardIds;
    @NotNull(message = "必须选择地址")
    private Long addressId;
}
