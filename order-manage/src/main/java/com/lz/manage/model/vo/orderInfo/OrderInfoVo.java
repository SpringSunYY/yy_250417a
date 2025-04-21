package com.lz.manage.model.vo.orderInfo;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.lz.common.annotation.Excel;
import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.manage.model.domain.OrderInfo;

/**
 * 订单信息Vo对象 tb_order_info
 *
 * @author YY
 * @date 2025-04-21
 */
@Data
public class OrderInfoVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @Excel(name = "编号")
    private Long orderId;

    /**
     * 商品
     */
    @Excel(name = "商品")
    private String goodsName;
    private Long goodsId;

    /**
     * 用户
     */
    @Excel(name = "用户")
    private String userName;
    private Long userId;

    /**
     * 地址
     */
    @Excel(name = "地址")
    private String addressName;
    private Long addressId;

    /**
     * 价格
     */
    @Excel(name = "价格")
    private BigDecimal totalPrice;

    /**
     * 状态
     */
    @Excel(name = "状态")
    private Long historyStatus;

    /**
     * 供应商
     */
    @Excel(name = "供应商")
    private Long supplierId;
    private String supplierName;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createTime;

    /**
     * 更新人
     */
    @Excel(name = "更新人")
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateTime;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remark;


    /**
     * 对象转封装类
     *
     * @param orderInfo OrderInfo实体对象
     * @return OrderInfoVo
     */
    public static OrderInfoVo objToVo(OrderInfo orderInfo) {
        if (orderInfo == null) {
            return null;
        }
        OrderInfoVo orderInfoVo = new OrderInfoVo();
        BeanUtils.copyProperties(orderInfo, orderInfoVo);
        return orderInfoVo;
    }
}
