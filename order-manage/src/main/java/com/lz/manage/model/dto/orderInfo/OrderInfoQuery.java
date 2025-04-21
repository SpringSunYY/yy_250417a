package com.lz.manage.model.dto.orderInfo;

import java.util.Map;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.BeanUtils;
import com.baomidou.mybatisplus.annotation.TableField;
import com.lz.manage.model.domain.OrderInfo;
/**
 * 订单信息Query对象 tb_order_info
 *
 * @author YY
 * @date 2025-04-21
 */
@Data
public class OrderInfoQuery implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long orderId;

    /** 商品 */
    private Long goodsId;

    /** 用户 */
    private Long userId;

    /** 供应商 */
    private Long supplierId;

    /** 状态 */
    private Long historyStatus;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /** 更新人 */
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    /** 请求参数 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Map<String, Object> params;

    /**
     * 对象转封装类
     *
     * @param orderInfoQuery 查询对象
     * @return OrderInfo
     */
    public static OrderInfo queryToObj(OrderInfoQuery orderInfoQuery) {
        if (orderInfoQuery == null) {
            return null;
        }
        OrderInfo orderInfo = new OrderInfo();
        BeanUtils.copyProperties(orderInfoQuery, orderInfo);
        return orderInfo;
    }
}
