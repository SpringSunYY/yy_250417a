package com.lz.manage.model.dto.userBalanceInfo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import com.lz.manage.model.domain.UserBalanceInfo;
/**
 * 用户余额Vo对象 tb_user_balance_info
 *
 * @author YY
 * @date 2025-04-19
 */
@Data
public class UserBalanceInfoEdit implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long balanceId;

    /** 用户 */
    private Long userId;

    /** 当前余额 */
    private BigDecimal balance;

    /**
     * 对象转封装类
     *
     * @param userBalanceInfoEdit 编辑对象
     * @return UserBalanceInfo
     */
    public static UserBalanceInfo editToObj(UserBalanceInfoEdit userBalanceInfoEdit) {
        if (userBalanceInfoEdit == null) {
            return null;
        }
        UserBalanceInfo userBalanceInfo = new UserBalanceInfo();
        BeanUtils.copyProperties(userBalanceInfoEdit, userBalanceInfo);
        return userBalanceInfo;
    }
}
