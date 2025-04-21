package com.lz.manage.service.impl;

import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import com.lz.common.core.domain.entity.SysUser;
import com.lz.common.utils.SecurityUtils;
import com.lz.common.utils.StringUtils;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.common.utils.DateUtils;

import javax.annotation.Resource;

import com.lz.manage.model.enums.AuditCommonStatus;
import com.lz.system.service.ISysUserService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lz.manage.mapper.GoodsInfoMapper;
import com.lz.manage.model.domain.GoodsInfo;
import com.lz.manage.service.IGoodsInfoService;
import com.lz.manage.model.dto.goodsInfo.GoodsInfoQuery;
import com.lz.manage.model.vo.goodsInfo.GoodsInfoVo;

/**
 * 商品信息Service业务层处理
 *
 * @author YY
 * @date 2025-04-19
 */
@Service
public class GoodsInfoServiceImpl extends ServiceImpl<GoodsInfoMapper, GoodsInfo> implements IGoodsInfoService {
    @Resource
    private GoodsInfoMapper goodsInfoMapper;

    @Resource
    private ISysUserService userService;

    //region mybatis代码

    /**
     * 查询商品信息
     *
     * @param goodsId 商品信息主键
     * @return 商品信息
     */
    @Override
    public GoodsInfo selectGoodsInfoByGoodsId(Long goodsId) {
        return goodsInfoMapper.selectGoodsInfoByGoodsId(goodsId);
    }

    /**
     * 查询商品信息列表
     *
     * @param goodsInfo 商品信息
     * @return 商品信息
     */
    @Override
    public List<GoodsInfo> selectGoodsInfoList(GoodsInfo goodsInfo) {
        List<GoodsInfo> goodsInfos = goodsInfoMapper.selectGoodsInfoList(goodsInfo);
        for (GoodsInfo info : goodsInfos) {
            SysUser user = userService.selectUserById(info.getUserId());
            if (StringUtils.isNotNull(user)) {
                info.setUserName(user.getUserName());
            }
        }
        return goodsInfos;
    }

    /**
     * 新增商品信息
     *
     * @param goodsInfo 商品信息
     * @return 结果
     */
    @Override
    public int insertGoodsInfo(GoodsInfo goodsInfo) {
        goodsInfo.setGoodsStatus(Long.parseLong(AuditCommonStatus.AUDIT_COMMON_STATUS_0.getValue()));
        goodsInfo.setPurchaseNum(0L);
        goodsInfo.setUserId(SecurityUtils.getUserId());
        goodsInfo.setCreateTime(DateUtils.getNowDate());
        return goodsInfoMapper.insertGoodsInfo(goodsInfo);
    }

    /**
     * 修改商品信息
     *
     * @param goodsInfo 商品信息
     * @return 结果
     */
    @Override
    public int updateGoodsInfo(GoodsInfo goodsInfo) {
        goodsInfo.setUpdateBy(SecurityUtils.getUsername());
        goodsInfo.setUpdateTime(DateUtils.getNowDate());
        return goodsInfoMapper.updateGoodsInfo(goodsInfo);
    }

    /**
     * 批量删除商品信息
     *
     * @param goodsIds 需要删除的商品信息主键
     * @return 结果
     */
    @Override
    public int deleteGoodsInfoByGoodsIds(Long[] goodsIds) {
        return goodsInfoMapper.deleteGoodsInfoByGoodsIds(goodsIds);
    }

    /**
     * 删除商品信息信息
     *
     * @param goodsId 商品信息主键
     * @return 结果
     */
    @Override
    public int deleteGoodsInfoByGoodsId(Long goodsId) {
        return goodsInfoMapper.deleteGoodsInfoByGoodsId(goodsId);
    }

    //endregion
    @Override
    public QueryWrapper<GoodsInfo> getQueryWrapper(GoodsInfoQuery goodsInfoQuery) {
        QueryWrapper<GoodsInfo> queryWrapper = new QueryWrapper<>();
        //如果不使用params可以删除
        Map<String, Object> params = goodsInfoQuery.getParams();
        if (StringUtils.isNull(params)) {
            params = new HashMap<>();
        }
        Long goodsId = goodsInfoQuery.getGoodsId();
        queryWrapper.eq(StringUtils.isNotNull(goodsId), "goods_id", goodsId);

        String goodsName = goodsInfoQuery.getGoodsName();
        queryWrapper.like(StringUtils.isNotEmpty(goodsName), "goods_name", goodsName);

        Long goodsStatus = goodsInfoQuery.getGoodsStatus();
        queryWrapper.eq(StringUtils.isNotNull(goodsStatus), "goods_status", goodsStatus);

        Long userId = goodsInfoQuery.getUserId();
        queryWrapper.eq(StringUtils.isNotNull(userId), "user_id", userId);

        Date createTime = goodsInfoQuery.getCreateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginCreateTime")) && StringUtils.isNotNull(params.get("endCreateTime")), "create_time", params.get("beginCreateTime"), params.get("endCreateTime"));

        String updateBy = goodsInfoQuery.getUpdateBy();
        queryWrapper.like(StringUtils.isNotEmpty(updateBy), "update_by", updateBy);

        Date updateTime = goodsInfoQuery.getUpdateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginUpdateTime")) && StringUtils.isNotNull(params.get("endUpdateTime")), "update_time", params.get("beginUpdateTime"), params.get("endUpdateTime"));

        return queryWrapper;
    }

    @Override
    public List<GoodsInfoVo> convertVoList(List<GoodsInfo> goodsInfoList) {
        if (StringUtils.isEmpty(goodsInfoList)) {
            return Collections.emptyList();
        }
        return goodsInfoList.stream().map(GoodsInfoVo::objToVo).collect(Collectors.toList());
    }

}
