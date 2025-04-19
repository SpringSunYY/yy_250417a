package com.lz.manage.model.vo.goodsInfo;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.lz.common.annotation.Excel;
import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.manage.model.domain.GoodsInfo;
/**
 * 商品信息Vo对象 tb_goods_info
 *
 * @author YY
 * @date 2025-04-19
 */
@Data
public class GoodsInfoVo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @Excel(name = "编号")
    private Long goodsId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 商品价格 */
    @Excel(name = "商品价格")
    private BigDecimal goodsPrice;

    /** 商品描述 */
    @Excel(name = "商品描述")
    private String goodsDesc;

    /** 商品图片 */
    @Excel(name = "商品图片")
    private String goodsImage;

    /** 商品状态 */
    @Excel(name = "商品状态")
    private Long goodsStatus;

    /** 购买次数 */
    @Excel(name = "购买次数")
    private Long purchaseNum;

    /** 供应商 */
    @Excel(name = "供应商")
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
     * @param goodsInfo GoodsInfo实体对象
     * @return GoodsInfoVo
     */
    public static GoodsInfoVo objToVo(GoodsInfo goodsInfo) {
        if (goodsInfo == null) {
            return null;
        }
        GoodsInfoVo goodsInfoVo = new GoodsInfoVo();
        BeanUtils.copyProperties(goodsInfo, goodsInfoVo);
        return goodsInfoVo;
    }
}
