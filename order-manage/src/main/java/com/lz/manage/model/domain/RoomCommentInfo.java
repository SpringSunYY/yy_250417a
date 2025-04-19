package com.lz.manage.model.domain;

import java.io.Serializable;
import java.util.Map;
import java.util.Date;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.lz.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
/**
 * 商品评价对象 tb_room_comment_info
 *
 * @author YY
 * @date 2025-04-19
 */
@TableName("tb_room_comment_info")
@Data
public class RoomCommentInfo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @Excel(name = "编号")
    @TableId(value = "comment_id", type = IdType.ASSIGN_ID)
    private Long commentId;

    /** 商品 */
    @Excel(name = "商品")
    private Long goodsId;

    /** 评论用户 */
    @Excel(name = "评论用户")
    private Long userId;

    /** 评分 */
    @Excel(name = "评分")
    private Long score;

    /** 评论内容 */
    @Excel(name = "评论内容")
    private String content;

    /** 评论图片 */
    @Excel(name = "评论图片")
    private String imageUrls;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateTime;

    /** 请求参数 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Map<String, Object> params;
}
