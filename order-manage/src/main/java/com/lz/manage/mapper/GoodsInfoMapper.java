package com.lz.manage.mapper;

import java.util.List;
import com.lz.manage.model.domain.GoodsInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 商品信息Mapper接口
 * 
 * @author YY
 * @date 2025-04-19
 */
public interface GoodsInfoMapper extends BaseMapper<GoodsInfo>
{
    /**
     * 查询商品信息
     * 
     * @param goodsId 商品信息主键
     * @return 商品信息
     */
    public GoodsInfo selectGoodsInfoByGoodsId(Long goodsId);

    /**
     * 查询商品信息列表
     * 
     * @param goodsInfo 商品信息
     * @return 商品信息集合
     */
    public List<GoodsInfo> selectGoodsInfoList(GoodsInfo goodsInfo);

    /**
     * 新增商品信息
     * 
     * @param goodsInfo 商品信息
     * @return 结果
     */
    public int insertGoodsInfo(GoodsInfo goodsInfo);

    /**
     * 修改商品信息
     * 
     * @param goodsInfo 商品信息
     * @return 结果
     */
    public int updateGoodsInfo(GoodsInfo goodsInfo);

    /**
     * 删除商品信息
     * 
     * @param goodsId 商品信息主键
     * @return 结果
     */
    public int deleteGoodsInfoByGoodsId(Long goodsId);

    /**
     * 批量删除商品信息
     * 
     * @param goodsIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGoodsInfoByGoodsIds(Long[] goodsIds);
}
