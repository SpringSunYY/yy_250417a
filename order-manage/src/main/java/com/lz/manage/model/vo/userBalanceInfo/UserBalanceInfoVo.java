package com.lz.manage.model.vo.userBalanceInfo;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.lz.common.annotation.Excel;
import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.manage.model.domain.UserBalanceInfo;

/**
 * 用户余额Vo对象 tb_user_balance_info
 *
 * @author YY
 * @date 2025-04-19
 */
@Data
public class UserBalanceInfoVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @Excel(name = "编号")
    private Long balanceId;

    /**
     * 用户
     */
    @Excel(name = "用户")
    private String userName;
    private Long userId;

    /**
     * 当前余额
     */
    @Excel(name = "当前余额")
    private BigDecimal balance;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateTime;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createTime;


    /**
     * 对象转封装类
     *
     * @param userBalanceInfo UserBalanceInfo实体对象
     * @return UserBalanceInfoVo
     */
    public static UserBalanceInfoVo objToVo(UserBalanceInfo userBalanceInfo) {
        if (userBalanceInfo == null) {
            return null;
        }
        UserBalanceInfoVo userBalanceInfoVo = new UserBalanceInfoVo();
        BeanUtils.copyProperties(userBalanceInfo, userBalanceInfoVo);
        return userBalanceInfoVo;
    }
}
