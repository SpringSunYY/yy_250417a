package com.lz.manage.service;

import java.util.List;
import com.lz.manage.model.domain.GoodsCardInfo;
import com.lz.manage.model.dto.goodsCardInfo.GoodsCardPay;
import com.lz.manage.model.vo.goodsCardInfo.GoodsCardInfoVo;
import com.lz.manage.model.dto.goodsCardInfo.GoodsCardInfoQuery;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
 * 购物车Service接口
 * 
 * @author YY
 * @date 2025-04-21
 */
public interface IGoodsCardInfoService extends IService<GoodsCardInfo>
{
    //region mybatis代码
    /**
     * 查询购物车
     * 
     * @param cardId 购物车主键
     * @return 购物车
     */
    public GoodsCardInfo selectGoodsCardInfoByCardId(Long cardId);

    /**
     * 查询购物车列表
     * 
     * @param goodsCardInfo 购物车
     * @return 购物车集合
     */
    public List<GoodsCardInfo> selectGoodsCardInfoList(GoodsCardInfo goodsCardInfo);

    /**
     * 新增购物车
     * 
     * @param goodsCardInfo 购物车
     * @return 结果
     */
    public int insertGoodsCardInfo(GoodsCardInfo goodsCardInfo);

    /**
     * 修改购物车
     * 
     * @param goodsCardInfo 购物车
     * @return 结果
     */
    public int updateGoodsCardInfo(GoodsCardInfo goodsCardInfo);

    /**
     * 批量删除购物车
     * 
     * @param cardIds 需要删除的购物车主键集合
     * @return 结果
     */
    public int deleteGoodsCardInfoByCardIds(Long[] cardIds);

    /**
     * 删除购物车信息
     * 
     * @param cardId 购物车主键
     * @return 结果
     */
    public int deleteGoodsCardInfoByCardId(Long cardId);
    //endregion
    /**
     * 获取查询条件
     *
     * @param goodsCardInfoQuery 查询条件对象
     * @return 查询条件
     */
    QueryWrapper<GoodsCardInfo> getQueryWrapper(GoodsCardInfoQuery goodsCardInfoQuery);

    /**
     * 转换vo
     *
     * @param goodsCardInfoList GoodsCardInfo集合
     * @return GoodsCardInfoVO集合
     */
    List<GoodsCardInfoVo> convertVoList(List<GoodsCardInfo> goodsCardInfoList);

    /**
     * 购物车下单
     *
     * @param goodsCardPay 支付订单对象
     * @return 结果
     */
    int payOrderCard(GoodsCardPay goodsCardPay);
}
