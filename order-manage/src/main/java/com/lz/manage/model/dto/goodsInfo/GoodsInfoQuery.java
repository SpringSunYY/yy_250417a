package com.lz.manage.model.dto.goodsInfo;

import java.util.Map;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.BeanUtils;
import com.baomidou.mybatisplus.annotation.TableField;
import com.lz.manage.model.domain.GoodsInfo;
/**
 * 商品信息Query对象 tb_goods_info
 *
 * @author YY
 * @date 2025-04-19
 */
@Data
public class GoodsInfoQuery implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long goodsId;

    /** 商品名称 */
    private String goodsName;

    /** 商品状态 */
    private Long goodsStatus;

    /** 供应商 */
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
     * @param goodsInfoQuery 查询对象
     * @return GoodsInfo
     */
    public static GoodsInfo queryToObj(GoodsInfoQuery goodsInfoQuery) {
        if (goodsInfoQuery == null) {
            return null;
        }
        GoodsInfo goodsInfo = new GoodsInfo();
        BeanUtils.copyProperties(goodsInfoQuery, goodsInfo);
        return goodsInfo;
    }
}
