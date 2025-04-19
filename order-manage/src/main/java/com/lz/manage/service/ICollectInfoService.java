package com.lz.manage.service;

import java.util.List;
import com.lz.manage.model.domain.CollectInfo;
import com.lz.manage.model.vo.collectInfo.CollectInfoVo;
import com.lz.manage.model.dto.collectInfo.CollectInfoQuery;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
 * 商品收藏Service接口
 * 
 * @author YY
 * @date 2025-04-19
 */
public interface ICollectInfoService extends IService<CollectInfo>
{
    //region mybatis代码
    /**
     * 查询商品收藏
     * 
     * @param collectId 商品收藏主键
     * @return 商品收藏
     */
    public CollectInfo selectCollectInfoByCollectId(Long collectId);

    /**
     * 查询商品收藏列表
     * 
     * @param collectInfo 商品收藏
     * @return 商品收藏集合
     */
    public List<CollectInfo> selectCollectInfoList(CollectInfo collectInfo);

    /**
     * 新增商品收藏
     * 
     * @param collectInfo 商品收藏
     * @return 结果
     */
    public int insertCollectInfo(CollectInfo collectInfo);

    /**
     * 修改商品收藏
     * 
     * @param collectInfo 商品收藏
     * @return 结果
     */
    public int updateCollectInfo(CollectInfo collectInfo);

    /**
     * 批量删除商品收藏
     * 
     * @param collectIds 需要删除的商品收藏主键集合
     * @return 结果
     */
    public int deleteCollectInfoByCollectIds(Long[] collectIds);

    /**
     * 删除商品收藏信息
     * 
     * @param collectId 商品收藏主键
     * @return 结果
     */
    public int deleteCollectInfoByCollectId(Long collectId);
    //endregion
    /**
     * 获取查询条件
     *
     * @param collectInfoQuery 查询条件对象
     * @return 查询条件
     */
    QueryWrapper<CollectInfo> getQueryWrapper(CollectInfoQuery collectInfoQuery);

    /**
     * 转换vo
     *
     * @param collectInfoList CollectInfo集合
     * @return CollectInfoVO集合
     */
    List<CollectInfoVo> convertVoList(List<CollectInfo> collectInfoList);
}
