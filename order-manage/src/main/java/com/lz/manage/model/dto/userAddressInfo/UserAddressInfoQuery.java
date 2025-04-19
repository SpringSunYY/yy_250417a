package com.lz.manage.model.dto.userAddressInfo;

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
import com.lz.manage.model.domain.UserAddressInfo;
/**
 * 用户地址Query对象 tb_user_address_info
 *
 * @author YY
 * @date 2025-04-19
 */
@Data
public class UserAddressInfoQuery implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long addressId;

    /** 用户 */
    private Long userId;

    /** 手机号码 */
    private String phone;

    /** 省份 */
    private String province;

    /** 市区 */
    private String city;

    /** 区县 */
    private String county;

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
     * @param userAddressInfoQuery 查询对象
     * @return UserAddressInfo
     */
    public static UserAddressInfo queryToObj(UserAddressInfoQuery userAddressInfoQuery) {
        if (userAddressInfoQuery == null) {
            return null;
        }
        UserAddressInfo userAddressInfo = new UserAddressInfo();
        BeanUtils.copyProperties(userAddressInfoQuery, userAddressInfo);
        return userAddressInfo;
    }
}
