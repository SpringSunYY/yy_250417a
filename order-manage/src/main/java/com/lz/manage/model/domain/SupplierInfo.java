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
 * 供应商申请对象 tb_supplier_info
 *
 * @author YY
 * @date 2025-04-19
 */
@TableName("tb_supplier_info")
@Data
public class SupplierInfo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @Excel(name = "编号")
    @TableId(value = "supplier_id", type = IdType.ASSIGN_ID)
    private Long supplierId;

    /** 用户 */
    @Excel(name = "用户")
    @TableField(exist = false)
    private String userName;
    private Long userId;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

    /** 审核人 */
    @Excel(name = "审核人")
    @TableField(exist = false)
    private String auditUserName;
    private Long auditUserId;

    /** 审核描述 */
    @Excel(name = "审核描述")
    private String auditDesc;

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
