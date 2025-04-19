package com.lz.manage.model.vo.rechargeHistoryInfo;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.lz.common.annotation.Excel;
import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.manage.model.domain.RechargeHistoryInfo;
/**
 * 充值记录Vo对象 tb_recharge_history_info
 *
 * @author YY
 * @date 2025-04-19
 */
@Data
public class RechargeHistoryInfoVo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @Excel(name = "编号")
    private Long historyId;

    /** 用户 */
    @Excel(name = "用户")
    private String userName;
    private Long userId;

    /** 充值价格 */
    @Excel(name = "充值价格")
    private BigDecimal rechargePrice;

    /** 充值凭证 */
    @Excel(name = "充值凭证")
    private String rechargeVoucher;

    /** 审核状态 */
    @Excel(name = "审核状态")
    private Long auditStatus;

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
     * @param rechargeHistoryInfo RechargeHistoryInfo实体对象
     * @return RechargeHistoryInfoVo
     */
    public static RechargeHistoryInfoVo objToVo(RechargeHistoryInfo rechargeHistoryInfo) {
        if (rechargeHistoryInfo == null) {
            return null;
        }
        RechargeHistoryInfoVo rechargeHistoryInfoVo = new RechargeHistoryInfoVo();
        BeanUtils.copyProperties(rechargeHistoryInfo, rechargeHistoryInfoVo);
        return rechargeHistoryInfoVo;
    }
}
