package com.lz.manage.service;

import java.util.List;

import com.lz.manage.model.domain.OrderInfo;
import com.lz.manage.model.vo.orderInfo.OrderInfoVo;
import com.lz.manage.model.dto.orderInfo.OrderInfoQuery;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * 订单信息Service接口
 *
 * @author YY
 * @date 2025-04-21
 */
public interface IOrderInfoService extends IService<OrderInfo> {
    //region mybatis代码

    /**
     * 查询订单信息
     *
     * @param orderId 订单信息主键
     * @return 订单信息
     */
    public OrderInfo selectOrderInfoByOrderId(Long orderId);

    /**
     * 查询订单信息列表
     *
     * @param orderInfo 订单信息
     * @return 订单信息集合
     */
    public List<OrderInfo> selectOrderInfoList(OrderInfo orderInfo);

    /**
     * 新增订单信息
     *
     * @param orderInfo 订单信息
     * @return 结果
     */
    public int insertOrderInfo(OrderInfo orderInfo);

    /**
     * 修改订单信息
     *
     * @param orderInfo 订单信息
     * @return 结果
     */
    public int updateOrderInfo(OrderInfo orderInfo);

    /**
     * 批量删除订单信息
     *
     * @param orderIds 需要删除的订单信息主键集合
     * @return 结果
     */
    public int deleteOrderInfoByOrderIds(Long[] orderIds);

    /**
     * 删除订单信息信息
     *
     * @param orderId 订单信息主键
     * @return 结果
     */
    public int deleteOrderInfoByOrderId(Long orderId);
    //endregion

    /**
     * 获取查询条件
     *
     * @param orderInfoQuery 查询条件对象
     * @return 查询条件
     */
    QueryWrapper<OrderInfo> getQueryWrapper(OrderInfoQuery orderInfoQuery);

    /**
     * 转换vo
     *
     * @param orderInfoList OrderInfo集合
     * @return OrderInfoVO集合
     */
    List<OrderInfoVo> convertVoList(List<OrderInfo> orderInfoList);

    /**
     * 支付订单
     *
     * @param orderIds 订单编号
     * @return int
     * @author YY
     * @method payOrderInfo
     * @date 2025/4/21 14:49
     **/
    int payOrderInfo(String orderIds);
}
