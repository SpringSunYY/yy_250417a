package com.lz.manage.service;

import java.util.List;
import com.lz.manage.model.domain.GoodsInfo;
import com.lz.manage.model.vo.goodsInfo.GoodsInfoVo;
import com.lz.manage.model.dto.goodsInfo.GoodsInfoQuery;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
 * 商品信息Service接口
 * 
 * @author YY
 * @date 2025-04-19
 */
public interface IGoodsInfoService extends IService<GoodsInfo>
{
    //region mybatis代码
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
     * 批量删除商品信息
     * 
     * @param goodsIds 需要删除的商品信息主键集合
     * @return 结果
     */
    public int deleteGoodsInfoByGoodsIds(Long[] goodsIds);

    /**
     * 删除商品信息信息
     * 
     * @param goodsId 商品信息主键
     * @return 结果
     */
    public int deleteGoodsInfoByGoodsId(Long goodsId);
    //endregion
    /**
     * 获取查询条件
     *
     * @param goodsInfoQuery 查询条件对象
     * @return 查询条件
     */
    QueryWrapper<GoodsInfo> getQueryWrapper(GoodsInfoQuery goodsInfoQuery);

    /**
     * 转换vo
     *
     * @param goodsInfoList GoodsInfo集合
     * @return GoodsInfoVO集合
     */
    List<GoodsInfoVo> convertVoList(List<GoodsInfo> goodsInfoList);
}
