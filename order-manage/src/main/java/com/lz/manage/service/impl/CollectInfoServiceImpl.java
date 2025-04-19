package com.lz.manage.service.impl;

import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import com.lz.common.utils.StringUtils;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.common.utils.DateUtils;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lz.manage.mapper.CollectInfoMapper;
import com.lz.manage.model.domain.CollectInfo;
import com.lz.manage.service.ICollectInfoService;
import com.lz.manage.model.dto.collectInfo.CollectInfoQuery;
import com.lz.manage.model.vo.collectInfo.CollectInfoVo;

/**
 * 商品收藏Service业务层处理
 * 
 * @author YY
 * @date 2025-04-19
 */
@Service
public class CollectInfoServiceImpl extends ServiceImpl<CollectInfoMapper, CollectInfo> implements ICollectInfoService
{
    @Resource
    private CollectInfoMapper collectInfoMapper;

    //region mybatis代码
    /**
     * 查询商品收藏
     * 
     * @param collectId 商品收藏主键
     * @return 商品收藏
     */
    @Override
    public CollectInfo selectCollectInfoByCollectId(Long collectId)
    {
        return collectInfoMapper.selectCollectInfoByCollectId(collectId);
    }

    /**
     * 查询商品收藏列表
     * 
     * @param collectInfo 商品收藏
     * @return 商品收藏
     */
    @Override
    public List<CollectInfo> selectCollectInfoList(CollectInfo collectInfo)
    {
        return collectInfoMapper.selectCollectInfoList(collectInfo);
    }

    /**
     * 新增商品收藏
     * 
     * @param collectInfo 商品收藏
     * @return 结果
     */
    @Override
    public int insertCollectInfo(CollectInfo collectInfo)
    {
        collectInfo.setCreateTime(DateUtils.getNowDate());
        return collectInfoMapper.insertCollectInfo(collectInfo);
    }

    /**
     * 修改商品收藏
     * 
     * @param collectInfo 商品收藏
     * @return 结果
     */
    @Override
    public int updateCollectInfo(CollectInfo collectInfo)
    {
        collectInfo.setUpdateTime(DateUtils.getNowDate());
        return collectInfoMapper.updateCollectInfo(collectInfo);
    }

    /**
     * 批量删除商品收藏
     * 
     * @param collectIds 需要删除的商品收藏主键
     * @return 结果
     */
    @Override
    public int deleteCollectInfoByCollectIds(Long[] collectIds)
    {
        return collectInfoMapper.deleteCollectInfoByCollectIds(collectIds);
    }

    /**
     * 删除商品收藏信息
     * 
     * @param collectId 商品收藏主键
     * @return 结果
     */
    @Override
    public int deleteCollectInfoByCollectId(Long collectId)
    {
        return collectInfoMapper.deleteCollectInfoByCollectId(collectId);
    }
    //endregion
    @Override
    public QueryWrapper<CollectInfo> getQueryWrapper(CollectInfoQuery collectInfoQuery){
        QueryWrapper<CollectInfo> queryWrapper = new QueryWrapper<>();
        //如果不使用params可以删除
        Map<String, Object> params = collectInfoQuery.getParams();
        if (StringUtils.isNull(params)) {
            params = new HashMap<>();
        }
        Long collectId = collectInfoQuery.getCollectId();
        queryWrapper.eq( StringUtils.isNotNull(collectId),"collect_id",collectId);

        Long goodsId = collectInfoQuery.getGoodsId();
        queryWrapper.eq( StringUtils.isNotNull(goodsId),"goods_id",goodsId);

        Long userId = collectInfoQuery.getUserId();
        queryWrapper.eq( StringUtils.isNotNull(userId),"user_id",userId);

        Date createTime = collectInfoQuery.getCreateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginCreateTime"))&&StringUtils.isNotNull(params.get("endCreateTime")),"create_time",params.get("beginCreateTime"),params.get("endCreateTime"));

        String updateBy = collectInfoQuery.getUpdateBy();
        queryWrapper.like(StringUtils.isNotEmpty(updateBy) ,"update_by",updateBy);

        Date updateTime = collectInfoQuery.getUpdateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginUpdateTime"))&&StringUtils.isNotNull(params.get("endUpdateTime")),"update_time",params.get("beginUpdateTime"),params.get("endUpdateTime"));

        return queryWrapper;
    }

    @Override
    public List<CollectInfoVo> convertVoList(List<CollectInfo> collectInfoList) {
        if (StringUtils.isEmpty(collectInfoList)) {
            return Collections.emptyList();
        }
        return collectInfoList.stream().map(CollectInfoVo::objToVo).collect(Collectors.toList());
    }

}
