package com.lz.manage.mapper;

import java.util.List;
import com.lz.manage.model.domain.SupplierInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 供应商申请Mapper接口
 * 
 * @author YY
 * @date 2025-04-19
 */
public interface SupplierInfoMapper extends BaseMapper<SupplierInfo>
{
    /**
     * 查询供应商申请
     * 
     * @param supplierId 供应商申请主键
     * @return 供应商申请
     */
    public SupplierInfo selectSupplierInfoBySupplierId(Long supplierId);

    /**
     * 查询供应商申请列表
     * 
     * @param supplierInfo 供应商申请
     * @return 供应商申请集合
     */
    public List<SupplierInfo> selectSupplierInfoList(SupplierInfo supplierInfo);

    /**
     * 新增供应商申请
     * 
     * @param supplierInfo 供应商申请
     * @return 结果
     */
    public int insertSupplierInfo(SupplierInfo supplierInfo);

    /**
     * 修改供应商申请
     * 
     * @param supplierInfo 供应商申请
     * @return 结果
     */
    public int updateSupplierInfo(SupplierInfo supplierInfo);

    /**
     * 删除供应商申请
     * 
     * @param supplierId 供应商申请主键
     * @return 结果
     */
    public int deleteSupplierInfoBySupplierId(Long supplierId);

    /**
     * 批量删除供应商申请
     * 
     * @param supplierIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSupplierInfoBySupplierIds(Long[] supplierIds);
}
