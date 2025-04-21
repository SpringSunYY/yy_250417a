package com.lz.manage.model.dto.orderInfo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import com.lz.manage.model.domain.OrderInfo;
/**
 * 订单信息Vo对象 tb_order_info
 *
 * @author YY
 * @date 2025-04-21
 */
@Data
public class OrderInfoInsert implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long orderId;

    /** 商品 */
    private Long goodsId;

    /** 用户 */
    private Long userId;

    /** 地址 */
    private Long addressId;

    /** 价格 */
    private BigDecimal totalPrice;

    /** 状态 */
    private Long historyStatus;

    /** 备注 */
    private String remark;

    /**
     * 对象转封装类
     *
     * @param orderInfoInsert 插入对象
     * @return OrderInfoInsert
     */
    public static OrderInfo insertToObj(OrderInfoInsert orderInfoInsert) {
        if (orderInfoInsert == null) {
            return null;
        }
        OrderInfo orderInfo = new OrderInfo();
        BeanUtils.copyProperties(orderInfoInsert, orderInfo);
        return orderInfo;
    }
}
