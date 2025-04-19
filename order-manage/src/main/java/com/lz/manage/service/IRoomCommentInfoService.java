package com.lz.manage.service;

import java.util.List;
import com.lz.manage.model.domain.RoomCommentInfo;
import com.lz.manage.model.vo.roomCommentInfo.RoomCommentInfoVo;
import com.lz.manage.model.dto.roomCommentInfo.RoomCommentInfoQuery;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
 * 商品评价Service接口
 * 
 * @author YY
 * @date 2025-04-19
 */
public interface IRoomCommentInfoService extends IService<RoomCommentInfo>
{
    //region mybatis代码
    /**
     * 查询商品评价
     * 
     * @param commentId 商品评价主键
     * @return 商品评价
     */
    public RoomCommentInfo selectRoomCommentInfoByCommentId(Long commentId);

    /**
     * 查询商品评价列表
     * 
     * @param roomCommentInfo 商品评价
     * @return 商品评价集合
     */
    public List<RoomCommentInfo> selectRoomCommentInfoList(RoomCommentInfo roomCommentInfo);

    /**
     * 新增商品评价
     * 
     * @param roomCommentInfo 商品评价
     * @return 结果
     */
    public int insertRoomCommentInfo(RoomCommentInfo roomCommentInfo);

    /**
     * 修改商品评价
     * 
     * @param roomCommentInfo 商品评价
     * @return 结果
     */
    public int updateRoomCommentInfo(RoomCommentInfo roomCommentInfo);

    /**
     * 批量删除商品评价
     * 
     * @param commentIds 需要删除的商品评价主键集合
     * @return 结果
     */
    public int deleteRoomCommentInfoByCommentIds(Long[] commentIds);

    /**
     * 删除商品评价信息
     * 
     * @param commentId 商品评价主键
     * @return 结果
     */
    public int deleteRoomCommentInfoByCommentId(Long commentId);
    //endregion
    /**
     * 获取查询条件
     *
     * @param roomCommentInfoQuery 查询条件对象
     * @return 查询条件
     */
    QueryWrapper<RoomCommentInfo> getQueryWrapper(RoomCommentInfoQuery roomCommentInfoQuery);

    /**
     * 转换vo
     *
     * @param roomCommentInfoList RoomCommentInfo集合
     * @return RoomCommentInfoVO集合
     */
    List<RoomCommentInfoVo> convertVoList(List<RoomCommentInfo> roomCommentInfoList);
}
