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
import com.lz.manage.mapper.RoomCommentInfoMapper;
import com.lz.manage.model.domain.RoomCommentInfo;
import com.lz.manage.service.IRoomCommentInfoService;
import com.lz.manage.model.dto.roomCommentInfo.RoomCommentInfoQuery;
import com.lz.manage.model.vo.roomCommentInfo.RoomCommentInfoVo;

/**
 * 商品评价Service业务层处理
 * 
 * @author YY
 * @date 2025-04-19
 */
@Service
public class RoomCommentInfoServiceImpl extends ServiceImpl<RoomCommentInfoMapper, RoomCommentInfo> implements IRoomCommentInfoService
{
    @Resource
    private RoomCommentInfoMapper roomCommentInfoMapper;

    //region mybatis代码
    /**
     * 查询商品评价
     * 
     * @param commentId 商品评价主键
     * @return 商品评价
     */
    @Override
    public RoomCommentInfo selectRoomCommentInfoByCommentId(Long commentId)
    {
        return roomCommentInfoMapper.selectRoomCommentInfoByCommentId(commentId);
    }

    /**
     * 查询商品评价列表
     * 
     * @param roomCommentInfo 商品评价
     * @return 商品评价
     */
    @Override
    public List<RoomCommentInfo> selectRoomCommentInfoList(RoomCommentInfo roomCommentInfo)
    {
        return roomCommentInfoMapper.selectRoomCommentInfoList(roomCommentInfo);
    }

    /**
     * 新增商品评价
     * 
     * @param roomCommentInfo 商品评价
     * @return 结果
     */
    @Override
    public int insertRoomCommentInfo(RoomCommentInfo roomCommentInfo)
    {
        roomCommentInfo.setCreateTime(DateUtils.getNowDate());
        return roomCommentInfoMapper.insertRoomCommentInfo(roomCommentInfo);
    }

    /**
     * 修改商品评价
     * 
     * @param roomCommentInfo 商品评价
     * @return 结果
     */
    @Override
    public int updateRoomCommentInfo(RoomCommentInfo roomCommentInfo)
    {
        roomCommentInfo.setUpdateTime(DateUtils.getNowDate());
        return roomCommentInfoMapper.updateRoomCommentInfo(roomCommentInfo);
    }

    /**
     * 批量删除商品评价
     * 
     * @param commentIds 需要删除的商品评价主键
     * @return 结果
     */
    @Override
    public int deleteRoomCommentInfoByCommentIds(Long[] commentIds)
    {
        return roomCommentInfoMapper.deleteRoomCommentInfoByCommentIds(commentIds);
    }

    /**
     * 删除商品评价信息
     * 
     * @param commentId 商品评价主键
     * @return 结果
     */
    @Override
    public int deleteRoomCommentInfoByCommentId(Long commentId)
    {
        return roomCommentInfoMapper.deleteRoomCommentInfoByCommentId(commentId);
    }
    //endregion
    @Override
    public QueryWrapper<RoomCommentInfo> getQueryWrapper(RoomCommentInfoQuery roomCommentInfoQuery){
        QueryWrapper<RoomCommentInfo> queryWrapper = new QueryWrapper<>();
        //如果不使用params可以删除
        Map<String, Object> params = roomCommentInfoQuery.getParams();
        if (StringUtils.isNull(params)) {
            params = new HashMap<>();
        }
        Long commentId = roomCommentInfoQuery.getCommentId();
        queryWrapper.eq( StringUtils.isNotNull(commentId),"comment_id",commentId);

        Long goodsId = roomCommentInfoQuery.getGoodsId();
        queryWrapper.eq( StringUtils.isNotNull(goodsId),"goods_id",goodsId);

        Long userId = roomCommentInfoQuery.getUserId();
        queryWrapper.eq( StringUtils.isNotNull(userId),"user_id",userId);

        Long score = roomCommentInfoQuery.getScore();
        queryWrapper.eq( StringUtils.isNotNull(score),"score",score);

        Date createTime = roomCommentInfoQuery.getCreateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginCreateTime"))&&StringUtils.isNotNull(params.get("endCreateTime")),"createTime",params.get("beginCreateTime"),params.get("endCreateTime"));

        Date updateTime = roomCommentInfoQuery.getUpdateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginUpdateTime"))&&StringUtils.isNotNull(params.get("endUpdateTime")),"updateTime",params.get("beginUpdateTime"),params.get("endUpdateTime"));

        return queryWrapper;
    }

    @Override
    public List<RoomCommentInfoVo> convertVoList(List<RoomCommentInfo> roomCommentInfoList) {
        if (StringUtils.isEmpty(roomCommentInfoList)) {
            return Collections.emptyList();
        }
        return roomCommentInfoList.stream().map(RoomCommentInfoVo::objToVo).collect(Collectors.toList());
    }

}
