package com.lz.manage.service;

import java.util.List;
import com.lz.manage.model.domain.SupplierInfo;
import com.lz.manage.model.vo.supplierInfo.SupplierInfoVo;
import com.lz.manage.model.dto.supplierInfo.SupplierInfoQuery;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
 * 供应商申请Service接口
 * 
 * @author YY
 * @date 2025-04-19
 */
public interface ISupplierInfoService extends IService<SupplierInfo>
{
    //region mybatis代码
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
     * 批量删除供应商申请
     * 
     * @param supplierIds 需要删除的供应商申请主键集合
     * @return 结果
     */
    public int deleteSupplierInfoBySupplierIds(Long[] supplierIds);

    /**
     * 删除供应商申请信息
     * 
     * @param supplierId 供应商申请主键
     * @return 结果
     */
    public int deleteSupplierInfoBySupplierId(Long supplierId);
    //endregion
    /**
     * 获取查询条件
     *
     * @param supplierInfoQuery 查询条件对象
     * @return 查询条件
     */
    QueryWrapper<SupplierInfo> getQueryWrapper(SupplierInfoQuery supplierInfoQuery);

    /**
     * 转换vo
     *
     * @param supplierInfoList SupplierInfo集合
     * @return SupplierInfoVO集合
     */
    List<SupplierInfoVo> convertVoList(List<SupplierInfo> supplierInfoList);
}
