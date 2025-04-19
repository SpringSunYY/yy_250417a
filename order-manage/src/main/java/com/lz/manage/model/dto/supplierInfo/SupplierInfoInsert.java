package com.lz.manage.model.dto.supplierInfo;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import com.lz.manage.model.domain.SupplierInfo;
/**
 * 供应商申请Vo对象 tb_supplier_info
 *
 * @author YY
 * @date 2025-04-19
 */
@Data
public class SupplierInfoInsert implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long supplierId;

    /** 用户 */
    private Long userId;

    /** 状态 */
    private Long status;

    /** 审核人 */
    private Long auditUserId;

    /** 审核描述 */
    private String auditDesc;

    /** 备注 */
    private String remark;

    /**
     * 对象转封装类
     *
     * @param supplierInfoInsert 插入对象
     * @return SupplierInfoInsert
     */
    public static SupplierInfo insertToObj(SupplierInfoInsert supplierInfoInsert) {
        if (supplierInfoInsert == null) {
            return null;
        }
        SupplierInfo supplierInfo = new SupplierInfo();
        BeanUtils.copyProperties(supplierInfoInsert, supplierInfo);
        return supplierInfo;
    }
}
