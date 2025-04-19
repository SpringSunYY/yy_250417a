package com.lz.manage.mapper;

import java.util.List;
import com.lz.manage.model.domain.CollectInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 商品收藏Mapper接口
 * 
 * @author YY
 * @date 2025-04-19
 */
public interface CollectInfoMapper extends BaseMapper<CollectInfo>
{
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
     * 删除商品收藏
     * 
     * @param collectId 商品收藏主键
     * @return 结果
     */
    public int deleteCollectInfoByCollectId(Long collectId);

    /**
     * 批量删除商品收藏
     * 
     * @param collectIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCollectInfoByCollectIds(Long[] collectIds);
}
