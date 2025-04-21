package com.lz.manage.model.dto.goodsCardInfo;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import com.lz.manage.model.domain.GoodsCardInfo;
/**
 * 购物车Vo对象 tb_goods_card_info
 *
 * @author YY
 * @date 2025-04-21
 */
@Data
public class GoodsCardInfoInsert implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long cardId;

    /** 商品 */
    private Long goodsId;

    /** 用户 */
    private Long userId;

    /** 备注 */
    private String remark;

    /**
     * 对象转封装类
     *
     * @param goodsCardInfoInsert 插入对象
     * @return GoodsCardInfoInsert
     */
    public static GoodsCardInfo insertToObj(GoodsCardInfoInsert goodsCardInfoInsert) {
        if (goodsCardInfoInsert == null) {
            return null;
        }
        GoodsCardInfo goodsCardInfo = new GoodsCardInfo();
        BeanUtils.copyProperties(goodsCardInfoInsert, goodsCardInfo);
        return goodsCardInfo;
    }
}
