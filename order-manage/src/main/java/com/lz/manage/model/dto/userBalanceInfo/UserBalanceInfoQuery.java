package com.lz.manage.model.dto.userBalanceInfo;

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
import com.lz.manage.model.domain.UserBalanceInfo;
/**
 * 用户余额Query对象 tb_user_balance_info
 *
 * @author YY
 * @date 2025-04-19
 */
@Data
public class UserBalanceInfoQuery implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long balanceId;

    /** 用户 */
    private Long userId;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /** 请求参数 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Map<String, Object> params;

    /**
     * 对象转封装类
     *
     * @param userBalanceInfoQuery 查询对象
     * @return UserBalanceInfo
     */
    public static UserBalanceInfo queryToObj(UserBalanceInfoQuery userBalanceInfoQuery) {
        if (userBalanceInfoQuery == null) {
            return null;
        }
        UserBalanceInfo userBalanceInfo = new UserBalanceInfo();
        BeanUtils.copyProperties(userBalanceInfoQuery, userBalanceInfo);
        return userBalanceInfo;
    }
}
