package com.lz.manage.service.impl;

import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import com.lz.common.utils.StringUtils;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.common.utils.DateUtils;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lz.manage.mapper.SupplierInfoMapper;
import com.lz.manage.model.domain.SupplierInfo;
import com.lz.manage.service.ISupplierInfoService;
import com.lz.manage.model.dto.supplierInfo.SupplierInfoQuery;
import com.lz.manage.model.vo.supplierInfo.SupplierInfoVo;

/**
 * 供应商申请Service业务层处理
 * 
 * @author YY
 * @date 2025-04-19
 */
@Service
public class SupplierInfoServiceImpl extends ServiceImpl<SupplierInfoMapper, SupplierInfo> implements ISupplierInfoService
{
    @Resource
    private SupplierInfoMapper supplierInfoMapper;

    //region mybatis代码
    /**
     * 查询供应商申请
     * 
     * @param supplierId 供应商申请主键
     * @return 供应商申请
     */
    @Override
    public SupplierInfo selectSupplierInfoBySupplierId(Long supplierId)
    {
        return supplierInfoMapper.selectSupplierInfoBySupplierId(supplierId);
    }

    /**
     * 查询供应商申请列表
     * 
     * @param supplierInfo 供应商申请
     * @return 供应商申请
     */
    @Override
    public List<SupplierInfo> selectSupplierInfoList(SupplierInfo supplierInfo)
    {
        return supplierInfoMapper.selectSupplierInfoList(supplierInfo);
    }

    /**
     * 新增供应商申请
     * 
     * @param supplierInfo 供应商申请
     * @return 结果
     */
    @Override
    public int insertSupplierInfo(SupplierInfo supplierInfo)
    {
        supplierInfo.setCreateTime(DateUtils.getNowDate());
        return supplierInfoMapper.insertSupplierInfo(supplierInfo);
    }

    /**
     * 修改供应商申请
     * 
     * @param supplierInfo 供应商申请
     * @return 结果
     */
    @Override
    public int updateSupplierInfo(SupplierInfo supplierInfo)
    {
        supplierInfo.setUpdateTime(DateUtils.getNowDate());
        return supplierInfoMapper.updateSupplierInfo(supplierInfo);
    }

    /**
     * 批量删除供应商申请
     * 
     * @param supplierIds 需要删除的供应商申请主键
     * @return 结果
     */
    @Override
    public int deleteSupplierInfoBySupplierIds(Long[] supplierIds)
    {
        return supplierInfoMapper.deleteSupplierInfoBySupplierIds(supplierIds);
    }

    /**
     * 删除供应商申请信息
     * 
     * @param supplierId 供应商申请主键
     * @return 结果
     */
    @Override
    public int deleteSupplierInfoBySupplierId(Long supplierId)
    {
        return supplierInfoMapper.deleteSupplierInfoBySupplierId(supplierId);
    }
    //endregion
    @Override
    public QueryWrapper<SupplierInfo> getQueryWrapper(SupplierInfoQuery supplierInfoQuery){
        QueryWrapper<SupplierInfo> queryWrapper = new QueryWrapper<>();
        //如果不使用params可以删除
        Map<String, Object> params = supplierInfoQuery.getParams();
        if (StringUtils.isNull(params)) {
            params = new HashMap<>();
        }
        Long supplierId = supplierInfoQuery.getSupplierId();
        queryWrapper.eq( StringUtils.isNotNull(supplierId),"supplier_id",supplierId);

        Long userId = supplierInfoQuery.getUserId();
        queryWrapper.eq( StringUtils.isNotNull(userId),"user_id",userId);

        Long status = supplierInfoQuery.getStatus();
        queryWrapper.eq( StringUtils.isNotNull(status),"status",status);

        Date createTime = supplierInfoQuery.getCreateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginCreateTime"))&&StringUtils.isNotNull(params.get("endCreateTime")),"create_time",params.get("beginCreateTime"),params.get("endCreateTime"));

        String updateBy = supplierInfoQuery.getUpdateBy();
        queryWrapper.like(StringUtils.isNotEmpty(updateBy) ,"update_by",updateBy);

        Date updateTime = supplierInfoQuery.getUpdateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginUpdateTime"))&&StringUtils.isNotNull(params.get("endUpdateTime")),"update_time",params.get("beginUpdateTime"),params.get("endUpdateTime"));

        return queryWrapper;
    }

    @Override
    public List<SupplierInfoVo> convertVoList(List<SupplierInfo> supplierInfoList) {
        if (StringUtils.isEmpty(supplierInfoList)) {
            return Collections.emptyList();
        }
        return supplierInfoList.stream().map(SupplierInfoVo::objToVo).collect(Collectors.toList());
    }

}
