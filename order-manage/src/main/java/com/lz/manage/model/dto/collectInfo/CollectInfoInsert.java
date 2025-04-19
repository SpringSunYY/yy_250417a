package com.lz.manage.model.dto.collectInfo;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import com.lz.manage.model.domain.CollectInfo;
/**
 * 商品收藏Vo对象 tb_collect_info
 *
 * @author YY
 * @date 2025-04-19
 */
@Data
public class CollectInfoInsert implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long collectId;

    /** 商品 */
    private Long goodsId;

    /** 用户 */
    private Long userId;

    /** 备注 */
    private String remark;

    /**
     * 对象转封装类
     *
     * @param collectInfoInsert 插入对象
     * @return CollectInfoInsert
     */
    public static CollectInfo insertToObj(CollectInfoInsert collectInfoInsert) {
        if (collectInfoInsert == null) {
            return null;
        }
        CollectInfo collectInfo = new CollectInfo();
        BeanUtils.copyProperties(collectInfoInsert, collectInfo);
        return collectInfo;
    }
}
