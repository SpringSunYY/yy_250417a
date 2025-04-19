package com.lz.manage.model.vo.supplierInfo;

import java.io.Serializable;
import java.util.Date;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.lz.common.annotation.Excel;
import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.manage.model.domain.SupplierInfo;
/**
 * 供应商申请Vo对象 tb_supplier_info
 *
 * @author YY
 * @date 2025-04-19
 */
@Data
public class SupplierInfoVo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @Excel(name = "编号")
    private Long supplierId;

    /** 用户 */
    @Excel(name = "用户")
    private Long userId;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

    /** 审核人 */
    @Excel(name = "审核人")
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


     /**
     * 对象转封装类
     *
     * @param supplierInfo SupplierInfo实体对象
     * @return SupplierInfoVo
     */
    public static SupplierInfoVo objToVo(SupplierInfo supplierInfo) {
        if (supplierInfo == null) {
            return null;
        }
        SupplierInfoVo supplierInfoVo = new SupplierInfoVo();
        BeanUtils.copyProperties(supplierInfo, supplierInfoVo);
        return supplierInfoVo;
    }
}
