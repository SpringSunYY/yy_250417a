package com.lz.manage.service.impl;

import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import com.lz.common.core.domain.entity.SysUser;
import com.lz.common.exception.ServiceException;
import com.lz.common.utils.SecurityUtils;
import com.lz.common.utils.StringUtils;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.common.utils.DateUtils;

import javax.annotation.Resource;

import com.lz.manage.model.domain.GoodsInfo;
import com.lz.manage.model.domain.UserAddressInfo;
import com.lz.manage.model.enums.AuditCommonStatus;
import com.lz.manage.service.IGoodsInfoService;
import com.lz.manage.service.IUserAddressInfoService;
import com.lz.system.service.ISysUserService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lz.manage.mapper.OrderInfoMapper;
import com.lz.manage.model.domain.OrderInfo;
import com.lz.manage.service.IOrderInfoService;
import com.lz.manage.model.dto.orderInfo.OrderInfoQuery;
import com.lz.manage.model.vo.orderInfo.OrderInfoVo;

/**
 * 订单信息Service业务层处理
 *
 * @author YY
 * @date 2025-04-21
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements IOrderInfoService {
    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Resource
    private ISysUserService userService;

    @Resource
    private IGoodsInfoService goodsInfoService;

    @Resource
    private IUserAddressInfoService userAddressInfoService;

    //region mybatis代码

    /**
     * 查询订单信息
     *
     * @param orderId 订单信息主键
     * @return 订单信息
     */
    @Override
    public OrderInfo selectOrderInfoByOrderId(Long orderId) {
        return orderInfoMapper.selectOrderInfoByOrderId(orderId);
    }

    /**
     * 查询订单信息列表
     *
     * @param orderInfo 订单信息
     * @return 订单信息
     */
    @Override
    public List<OrderInfo> selectOrderInfoList(OrderInfo orderInfo) {
        List<OrderInfo> orderInfos = orderInfoMapper.selectOrderInfoList(orderInfo);
        for (OrderInfo info : orderInfos) {
            SysUser user = userService.selectUserById(info.getUserId());
            if (StringUtils.isNotNull(user)) {
                info.setUserName(user.getUserName());
            }
            UserAddressInfo userAddressInfo = userAddressInfoService.selectUserAddressInfoByAddressId(info.getUserId());
            if (StringUtils.isNotNull(userAddressInfo)) {
                info.setAddressName(StringUtils.format("{}-{}-{}-{}-{}",
                        userAddressInfo.getPhone(),
                        userAddressInfo.getProvince(),
                        userAddressInfo.getCity(),
                        userAddressInfo.getCounty(),
                        userAddressInfo.getAddress()));
            }
            GoodsInfo goodsInfo = goodsInfoService.selectGoodsInfoByGoodsId(info.getGoodsId());
            if (StringUtils.isNotNull(goodsInfo)) {
                info.setGoodsName(goodsInfo.getGoodsName());
            }
            SysUser supplier = userService.selectUserById(info.getSupplierId());
            if (StringUtils.isNotNull(supplier)) {
                info.setSupplierName(supplier.getUserName());
            }
        }
        return orderInfos;
    }

    /**
     * 新增订单信息
     *
     * @param orderInfo 订单信息
     * @return 结果
     */
    @Override
    public int insertOrderInfo(OrderInfo orderInfo) {
        //查询商品状态
        GoodsInfo goodsInfo = goodsInfoService.selectGoodsInfoByGoodsId(orderInfo.getGoodsId());
        if (StringUtils.isNull(goodsInfo)) {
            throw new ServiceException("商品不存在");
        }
        if (!goodsInfo.getGoodsStatus().toString().equals(AuditCommonStatus.AUDIT_COMMON_STATUS_1.getValue())) {
            throw new ServiceException("商品状态异常");
        }
        orderInfo.setSupplierId(goodsInfo.getUserId());
        orderInfo.setTotalPrice(goodsInfo.getGoodsPrice());
        orderInfo.setUserId(SecurityUtils.getUserId());
        orderInfo.setCreateTime(DateUtils.getNowDate());
        return orderInfoMapper.insertOrderInfo(orderInfo);
    }

    /**
     * 修改订单信息
     *
     * @param orderInfo 订单信息
     * @return 结果
     */
    @Override
    public int updateOrderInfo(OrderInfo orderInfo) {
        orderInfo.setUpdateTime(DateUtils.getNowDate());
        return orderInfoMapper.updateOrderInfo(orderInfo);
    }

    /**
     * 批量删除订单信息
     *
     * @param orderIds 需要删除的订单信息主键
     * @return 结果
     */
    @Override
    public int deleteOrderInfoByOrderIds(Long[] orderIds) {
        return orderInfoMapper.deleteOrderInfoByOrderIds(orderIds);
    }

    /**
     * 删除订单信息信息
     *
     * @param orderId 订单信息主键
     * @return 结果
     */
    @Override
    public int deleteOrderInfoByOrderId(Long orderId) {
        return orderInfoMapper.deleteOrderInfoByOrderId(orderId);
    }

    //endregion
    @Override
    public QueryWrapper<OrderInfo> getQueryWrapper(OrderInfoQuery orderInfoQuery) {
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        //如果不使用params可以删除
        Map<String, Object> params = orderInfoQuery.getParams();
        if (StringUtils.isNull(params)) {
            params = new HashMap<>();
        }
        Long orderId = orderInfoQuery.getOrderId();
        queryWrapper.eq(StringUtils.isNotNull(orderId), "order_id", orderId);

        Long goodsId = orderInfoQuery.getGoodsId();
        queryWrapper.eq(StringUtils.isNotNull(goodsId), "goods_id", goodsId);

        Long userId = orderInfoQuery.getUserId();
        queryWrapper.eq(StringUtils.isNotNull(userId), "user_id", userId);

        Long historyStatus = orderInfoQuery.getHistoryStatus();
        queryWrapper.eq(StringUtils.isNotNull(historyStatus), "history_status", historyStatus);

        Date createTime = orderInfoQuery.getCreateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginCreateTime")) && StringUtils.isNotNull(params.get("endCreateTime")), "create_time", params.get("beginCreateTime"), params.get("endCreateTime"));

        String updateBy = orderInfoQuery.getUpdateBy();
        queryWrapper.like(StringUtils.isNotEmpty(updateBy), "update_by", updateBy);

        Date updateTime = orderInfoQuery.getUpdateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginUpdateTime")) && StringUtils.isNotNull(params.get("endUpdateTime")), "update_time", params.get("beginUpdateTime"), params.get("endUpdateTime"));

        return queryWrapper;
    }

    @Override
    public List<OrderInfoVo> convertVoList(List<OrderInfo> orderInfoList) {
        if (StringUtils.isEmpty(orderInfoList)) {
            return Collections.emptyList();
        }
        return orderInfoList.stream().map(OrderInfoVo::objToVo).collect(Collectors.toList());
    }

}
