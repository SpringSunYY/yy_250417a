package com.lz.manage.model.dto.goodsCardInfo;

import java.util.Map;
import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.BeanUtils;
import com.baomidou.mybatisplus.annotation.TableField;
import com.lz.manage.model.domain.GoodsCardInfo;
/**
 * 购物车Query对象 tb_goods_card_info
 *
 * @author YY
 * @date 2025-04-21
 */
@Data
public class GoodsCardInfoQuery implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long cardId;

    /** 商品 */
    private Long goodsId;

    /** 用户 */
    private Long userId;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /** 更新人 */
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    /** 请求参数 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Map<String, Object> params;

    /**
     * 对象转封装类
     *
     * @param goodsCardInfoQuery 查询对象
     * @return GoodsCardInfo
     */
    public static GoodsCardInfo queryToObj(GoodsCardInfoQuery goodsCardInfoQuery) {
        if (goodsCardInfoQuery == null) {
            return null;
        }
        GoodsCardInfo goodsCardInfo = new GoodsCardInfo();
        BeanUtils.copyProperties(goodsCardInfoQuery, goodsCardInfo);
        return goodsCardInfo;
    }
}
