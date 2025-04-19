package com.lz.manage.model.vo.collectInfo;

import java.io.Serializable;
import java.util.Date;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.lz.common.annotation.Excel;
import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.manage.model.domain.CollectInfo;
/**
 * 商品收藏Vo对象 tb_collect_info
 *
 * @author YY
 * @date 2025-04-19
 */
@Data
public class CollectInfoVo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @Excel(name = "编号")
    private Long collectId;

    /** 商品 */
    @Excel(name = "商品")
    private Long goodsId;

    /** 用户 */
    @Excel(name = "用户")
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
     * @param collectInfo CollectInfo实体对象
     * @return CollectInfoVo
     */
    public static CollectInfoVo objToVo(CollectInfo collectInfo) {
        if (collectInfo == null) {
            return null;
        }
        CollectInfoVo collectInfoVo = new CollectInfoVo();
        BeanUtils.copyProperties(collectInfo, collectInfoVo);
        return collectInfoVo;
    }
}
