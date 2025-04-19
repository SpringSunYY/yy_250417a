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
public class UserBalanceInfoInsert implements Serializable
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
     * @param userBalanceInfoInsert 插入对象
     * @return UserBalanceInfoInsert
     */
    public static UserBalanceInfo insertToObj(UserBalanceInfoInsert userBalanceInfoInsert) {
        if (userBalanceInfoInsert == null) {
            return null;
        }
        UserBalanceInfo userBalanceInfo = new UserBalanceInfo();
        BeanUtils.copyProperties(userBalanceInfoInsert, userBalanceInfo);
        return userBalanceInfo;
    }
}
