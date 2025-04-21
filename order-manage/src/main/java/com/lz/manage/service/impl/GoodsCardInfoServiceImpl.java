package com.lz.manage.service.impl;

import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lz.common.core.domain.entity.SysUser;
import com.lz.common.exception.ServiceException;
import com.lz.common.utils.SecurityUtils;
import com.lz.common.utils.StringUtils;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.common.utils.DateUtils;

import javax.annotation.Resource;

import com.lz.manage.model.domain.CollectInfo;
import com.lz.manage.model.domain.GoodsInfo;
import com.lz.manage.service.IGoodsInfoService;
import com.lz.system.service.ISysUserService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lz.manage.mapper.GoodsCardInfoMapper;
import com.lz.manage.model.domain.GoodsCardInfo;
import com.lz.manage.service.IGoodsCardInfoService;
import com.lz.manage.model.dto.goodsCardInfo.GoodsCardInfoQuery;
import com.lz.manage.model.vo.goodsCardInfo.GoodsCardInfoVo;

/**
 * 购物车Service业务层处理
 *
 * @author YY
 * @date 2025-04-21
 */
@Service
public class GoodsCardInfoServiceImpl extends ServiceImpl<GoodsCardInfoMapper, GoodsCardInfo> implements IGoodsCardInfoService {
    @Resource
    private GoodsCardInfoMapper goodsCardInfoMapper;

    @Resource
    private ISysUserService userService;

    @Resource
    private IGoodsInfoService goodsInfoService;

    //region mybatis代码

    /**
     * 查询购物车
     *
     * @param cardId 购物车主键
     * @return 购物车
     */
    @Override
    public GoodsCardInfo selectGoodsCardInfoByCardId(Long cardId) {
        return goodsCardInfoMapper.selectGoodsCardInfoByCardId(cardId);
    }

    /**
     * 查询购物车列表
     *
     * @param goodsCardInfo 购物车
     * @return 购物车
     */
    @Override
    public List<GoodsCardInfo> selectGoodsCardInfoList(GoodsCardInfo goodsCardInfo) {
        List<GoodsCardInfo> goodsCardInfos = goodsCardInfoMapper.selectGoodsCardInfoList(goodsCardInfo);
        for (GoodsCardInfo info : goodsCardInfos) {
            SysUser user = userService.selectUserById(info.getUserId());
            if (StringUtils.isNotNull(user)) {
                info.setUserName(user.getUserName());
            }

            GoodsInfo goodsInfo = goodsInfoService.selectGoodsInfoByGoodsId(info.getGoodsId());
            if (StringUtils.isNotNull(goodsInfo)) {
                info.setGoodsName(goodsInfo.getGoodsName());
            }
        }
        return goodsCardInfos;
    }

    /**
     * 新增购物车
     *
     * @param goodsCardInfo 购物车
     * @return 结果
     */
    @Override
    public int insertGoodsCardInfo(GoodsCardInfo goodsCardInfo) {
        //查询商品是否存在
        GoodsInfo goodsInfo = goodsInfoService.selectGoodsInfoByGoodsId(goodsCardInfo.getGoodsId());
        if (StringUtils.isNull(goodsInfo)) {
            throw new ServiceException("商品不存在");
        }
        //根据用户ID和商品ID查询是否已经收藏过
        GoodsCardInfo cardInfoDb = this.getOne(new LambdaQueryWrapper<GoodsCardInfo>()
                .eq(GoodsCardInfo::getGoodsId, goodsInfo.getGoodsId())
                .eq(GoodsCardInfo::getUserId, SecurityUtils.getUserId()));
        if (StringUtils.isNotNull(cardInfoDb)) {
            throw new ServiceException("商品已经加入购物车");
        }
        goodsCardInfo.setUserId(SecurityUtils.getUserId());
        goodsCardInfo.setCreateTime(DateUtils.getNowDate());
        return goodsCardInfoMapper.insertGoodsCardInfo(goodsCardInfo);
    }

    /**
     * 修改购物车
     *
     * @param goodsCardInfo 购物车
     * @return 结果
     */
    @Override
    public int updateGoodsCardInfo(GoodsCardInfo goodsCardInfo) {
        goodsCardInfo.setUpdateBy(SecurityUtils.getUsername());
        goodsCardInfo.setUpdateTime(DateUtils.getNowDate());
        return goodsCardInfoMapper.updateGoodsCardInfo(goodsCardInfo);
    }

    /**
     * 批量删除购物车
     *
     * @param cardIds 需要删除的购物车主键
     * @return 结果
     */
    @Override
    public int deleteGoodsCardInfoByCardIds(Long[] cardIds) {
        return goodsCardInfoMapper.deleteGoodsCardInfoByCardIds(cardIds);
    }

    /**
     * 删除购物车信息
     *
     * @param cardId 购物车主键
     * @return 结果
     */
    @Override
    public int deleteGoodsCardInfoByCardId(Long cardId) {
        return goodsCardInfoMapper.deleteGoodsCardInfoByCardId(cardId);
    }

    //endregion
    @Override
    public QueryWrapper<GoodsCardInfo> getQueryWrapper(GoodsCardInfoQuery goodsCardInfoQuery) {
        QueryWrapper<GoodsCardInfo> queryWrapper = new QueryWrapper<>();
        //如果不使用params可以删除
        Map<String, Object> params = goodsCardInfoQuery.getParams();
        if (StringUtils.isNull(params)) {
            params = new HashMap<>();
        }
        Long cardId = goodsCardInfoQuery.getCardId();
        queryWrapper.eq(StringUtils.isNotNull(cardId), "card_id", cardId);

        Long goodsId = goodsCardInfoQuery.getGoodsId();
        queryWrapper.eq(StringUtils.isNotNull(goodsId), "goods_id", goodsId);

        Long userId = goodsCardInfoQuery.getUserId();
        queryWrapper.eq(StringUtils.isNotNull(userId), "user_id", userId);

        Date createTime = goodsCardInfoQuery.getCreateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginCreateTime")) && StringUtils.isNotNull(params.get("endCreateTime")), "create_time", params.get("beginCreateTime"), params.get("endCreateTime"));

        String updateBy = goodsCardInfoQuery.getUpdateBy();
        queryWrapper.like(StringUtils.isNotEmpty(updateBy), "update_by", updateBy);

        Date updateTime = goodsCardInfoQuery.getUpdateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginUpdateTime")) && StringUtils.isNotNull(params.get("endUpdateTime")), "update_time", params.get("beginUpdateTime"), params.get("endUpdateTime"));

        return queryWrapper;
    }

    @Override
    public List<GoodsCardInfoVo> convertVoList(List<GoodsCardInfo> goodsCardInfoList) {
        if (StringUtils.isEmpty(goodsCardInfoList)) {
            return Collections.emptyList();
        }
        return goodsCardInfoList.stream().map(GoodsCardInfoVo::objToVo).collect(Collectors.toList());
    }

}
