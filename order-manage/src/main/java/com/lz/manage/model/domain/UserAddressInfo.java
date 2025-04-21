package com.lz.manage.model.domain;

import java.io.Serializable;
import java.util.Map;
import java.util.Date;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.lz.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
/**
 * 用户地址对象 tb_user_address_info
 *
 * @author YY
 * @date 2025-04-19
 */
@TableName("tb_user_address_info")
@Data
public class UserAddressInfo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @Excel(name = "编号")
    @TableId(value = "address_id", type = IdType.ASSIGN_ID)
    private Long addressId;

    /** 用户 */
    @Excel(name = "用户")
    @TableField(exist = false)
    private String userName;
    private Long userId;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phone;

    /** 省份 */
    @Excel(name = "省份")
    private String province;

    /** 市区 */
    @Excel(name = "市区")
    private String city;

    /** 区县 */
    @Excel(name = "区县")
    private String county;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String address;

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

    /** 请求参数 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Map<String, Object> params;
}
