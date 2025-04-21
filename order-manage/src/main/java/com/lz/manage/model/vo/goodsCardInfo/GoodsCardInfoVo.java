package com.lz.manage.model.vo.goodsCardInfo;

import java.io.Serializable;
import java.util.Date;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.lz.common.annotation.Excel;
import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.manage.model.domain.GoodsCardInfo;
/**
 * 购物车Vo对象 tb_goods_card_info
 *
 * @author YY
 * @date 2025-04-21
 */
@Data
public class GoodsCardInfoVo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @Excel(name = "编号")
    private Long cardId;

    /** 商品 */
    @Excel(name = "商品")
    private String goodsName;
    private Long goodsId;

    /** 用户 */
    @Excel(name = "用户")
    private String userName;
    private Long userId;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createTime;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateTime;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;


     /**
     * 对象转封装类
     *
     * @param goodsCardInfo GoodsCardInfo实体对象
     * @return GoodsCardInfoVo
     */
    public static GoodsCardInfoVo objToVo(GoodsCardInfo goodsCardInfo) {
        if (goodsCardInfo == null) {
            return null;
        }
        GoodsCardInfoVo goodsCardInfoVo = new GoodsCardInfoVo();
        BeanUtils.copyProperties(goodsCardInfo, goodsCardInfoVo);
        return goodsCardInfoVo;
    }
}
