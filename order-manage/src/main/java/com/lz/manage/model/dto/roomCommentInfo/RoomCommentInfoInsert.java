package com.lz.manage.model.dto.roomCommentInfo;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import com.lz.manage.model.domain.RoomCommentInfo;
/**
 * 商品评价Vo对象 tb_room_comment_info
 *
 * @author YY
 * @date 2025-04-19
 */
@Data
public class RoomCommentInfoInsert implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long commentId;

    /** 商品 */
    private Long goodsId;

    /** 评论用户 */
    private Long userId;

    /** 评分 */
    private Long score;

    /** 评论内容 */
    private String content;

    /** 评论图片 */
    private String imageUrls;

    /**
     * 对象转封装类
     *
     * @param roomCommentInfoInsert 插入对象
     * @return RoomCommentInfoInsert
     */
    public static RoomCommentInfo insertToObj(RoomCommentInfoInsert roomCommentInfoInsert) {
        if (roomCommentInfoInsert == null) {
            return null;
        }
        RoomCommentInfo roomCommentInfo = new RoomCommentInfo();
        BeanUtils.copyProperties(roomCommentInfoInsert, roomCommentInfo);
        return roomCommentInfo;
    }
}
