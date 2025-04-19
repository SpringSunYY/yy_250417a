package com.lz.manage.model.dto.userAddressInfo;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import com.lz.manage.model.domain.UserAddressInfo;
/**
 * 用户地址Vo对象 tb_user_address_info
 *
 * @author YY
 * @date 2025-04-19
 */
@Data
public class UserAddressInfoEdit implements Serializable
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

    /** 详细地址 */
    private String address;

    /** 备注 */
    private String remark;

    /**
     * 对象转封装类
     *
     * @param userAddressInfoEdit 编辑对象
     * @return UserAddressInfo
     */
    public static UserAddressInfo editToObj(UserAddressInfoEdit userAddressInfoEdit) {
        if (userAddressInfoEdit == null) {
            return null;
        }
        UserAddressInfo userAddressInfo = new UserAddressInfo();
        BeanUtils.copyProperties(userAddressInfoEdit, userAddressInfo);
        return userAddressInfo;
    }
}
