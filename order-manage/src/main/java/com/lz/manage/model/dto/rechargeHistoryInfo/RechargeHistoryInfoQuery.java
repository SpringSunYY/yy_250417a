package com.lz.manage.model.dto.rechargeHistoryInfo;

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
import com.lz.manage.model.domain.RechargeHistoryInfo;
/**
 * 充值记录Query对象 tb_recharge_history_info
 *
 * @author YY
 * @date 2025-04-19
 */
@Data
public class RechargeHistoryInfoQuery implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long historyId;

    /** 用户 */
    private Long userId;

    /** 审核状态 */
    private Long auditStatus;

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
     * @param rechargeHistoryInfoQuery 查询对象
     * @return RechargeHistoryInfo
     */
    public static RechargeHistoryInfo queryToObj(RechargeHistoryInfoQuery rechargeHistoryInfoQuery) {
        if (rechargeHistoryInfoQuery == null) {
            return null;
        }
        RechargeHistoryInfo rechargeHistoryInfo = new RechargeHistoryInfo();
        BeanUtils.copyProperties(rechargeHistoryInfoQuery, rechargeHistoryInfo);
        return rechargeHistoryInfo;
    }
}
