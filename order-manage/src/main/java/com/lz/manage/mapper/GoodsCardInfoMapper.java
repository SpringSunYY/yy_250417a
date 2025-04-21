package com.lz.manage.mapper;

import java.util.List;
import com.lz.manage.model.domain.GoodsCardInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 购物车Mapper接口
 * 
 * @author YY
 * @date 2025-04-21
 */
public interface GoodsCardInfoMapper extends BaseMapper<GoodsCardInfo>
{
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
     * 删除购物车
     * 
     * @param cardId 购物车主键
     * @return 结果
     */
    public int deleteGoodsCardInfoByCardId(Long cardId);

    /**
     * 批量删除购物车
     * 
     * @param cardIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGoodsCardInfoByCardIds(Long[] cardIds);
}
