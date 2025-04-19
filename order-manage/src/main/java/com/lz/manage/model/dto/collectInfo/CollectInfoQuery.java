package com.lz.manage.model.dto.collectInfo;

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
import com.lz.manage.model.domain.CollectInfo;
/**
 * 商品收藏Query对象 tb_collect_info
 *
 * @author YY
 * @date 2025-04-19
 */
@Data
public class CollectInfoQuery implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long collectId;

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
     * @param collectInfoQuery 查询对象
     * @return CollectInfo
     */
    public static CollectInfo queryToObj(CollectInfoQuery collectInfoQuery) {
        if (collectInfoQuery == null) {
            return null;
        }
        CollectInfo collectInfo = new CollectInfo();
        BeanUtils.copyProperties(collectInfoQuery, collectInfo);
        return collectInfo;
    }
}
