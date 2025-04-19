package com.lz.manage.model.dto.goodsInfo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import com.lz.manage.model.domain.GoodsInfo;
/**
 * 商品信息Vo对象 tb_goods_info
 *
 * @author YY
 * @date 2025-04-19
 */
@Data
public class GoodsInfoEdit implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long goodsId;

    /** 商品名称 */
    private String goodsName;

    /** 商品价格 */
    private BigDecimal goodsPrice;

    /** 商品描述 */
    private String goodsDesc;

    /** 商品图片 */
    private String goodsImage;

    /** 商品状态 */
    private Long goodsStatus;

    /** 购买次数 */
    private Long purchaseNum;

    /** 供应商 */
    private Long userId;

    /** 备注 */
    private String remark;

    /**
     * 对象转封装类
     *
     * @param goodsInfoEdit 编辑对象
     * @return GoodsInfo
     */
    public static GoodsInfo editToObj(GoodsInfoEdit goodsInfoEdit) {
        if (goodsInfoEdit == null) {
            return null;
        }
        GoodsInfo goodsInfo = new GoodsInfo();
        BeanUtils.copyProperties(goodsInfoEdit, goodsInfo);
        return goodsInfo;
    }
}
