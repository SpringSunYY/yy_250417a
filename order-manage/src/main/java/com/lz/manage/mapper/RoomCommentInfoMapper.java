package com.lz.manage.mapper;

import java.util.List;
import com.lz.manage.model.domain.RoomCommentInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 商品评价Mapper接口
 * 
 * @author YY
 * @date 2025-04-19
 */
public interface RoomCommentInfoMapper extends BaseMapper<RoomCommentInfo>
{
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
     * 删除商品评价
     * 
     * @param commentId 商品评价主键
     * @return 结果
     */
    public int deleteRoomCommentInfoByCommentId(Long commentId);

    /**
     * 批量删除商品评价
     * 
     * @param commentIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRoomCommentInfoByCommentIds(Long[] commentIds);
}
