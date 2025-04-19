package com.lz.manage.model.dto.rechargeHistoryInfo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import com.lz.manage.model.domain.RechargeHistoryInfo;
/**
 * 充值记录Vo对象 tb_recharge_history_info
 *
 * @author YY
 * @date 2025-04-19
 */
@Data
public class RechargeHistoryInfoEdit implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long historyId;

    /** 用户 */
    private Long userId;

    /** 充值价格 */
    private BigDecimal rechargePrice;

    /** 充值凭证 */
    private String rechargeVoucher;

    /** 审核状态 */
    private Long auditStatus;

    /** 备注 */
    private String remark;

    /**
     * 对象转封装类
     *
     * @param rechargeHistoryInfoEdit 编辑对象
     * @return RechargeHistoryInfo
     */
    public static RechargeHistoryInfo editToObj(RechargeHistoryInfoEdit rechargeHistoryInfoEdit) {
        if (rechargeHistoryInfoEdit == null) {
            return null;
        }
        RechargeHistoryInfo rechargeHistoryInfo = new RechargeHistoryInfo();
        BeanUtils.copyProperties(rechargeHistoryInfoEdit, rechargeHistoryInfo);
        return rechargeHistoryInfo;
    }
}
