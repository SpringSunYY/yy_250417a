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
public class RoomCommentInfoEdit implements Serializable
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
     * @param roomCommentInfoEdit 编辑对象
     * @return RoomCommentInfo
     */
    public static RoomCommentInfo editToObj(RoomCommentInfoEdit roomCommentInfoEdit) {
        if (roomCommentInfoEdit == null) {
            return null;
        }
        RoomCommentInfo roomCommentInfo = new RoomCommentInfo();
        BeanUtils.copyProperties(roomCommentInfoEdit, roomCommentInfo);
        return roomCommentInfo;
    }
}
