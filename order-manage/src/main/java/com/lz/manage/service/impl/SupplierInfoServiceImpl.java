package com.lz.manage.service.impl;

import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import com.lz.common.core.domain.entity.SysUser;
import com.lz.common.utils.SecurityUtils;
import com.lz.common.utils.StringUtils;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.common.utils.DateUtils;

import javax.annotation.Resource;

import com.lz.manage.model.enums.AuditCommonStatus;
import com.lz.system.service.ISysUserService;
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
public class SupplierInfoServiceImpl extends ServiceImpl<SupplierInfoMapper, SupplierInfo> implements ISupplierInfoService {
    @Resource
    private SupplierInfoMapper supplierInfoMapper;

    @Resource
    private ISysUserService userService;

    //region mybatis代码

    /**
     * 查询供应商申请
     *
     * @param supplierId 供应商申请主键
     * @return 供应商申请
     */
    @Override
    public SupplierInfo selectSupplierInfoBySupplierId(Long supplierId) {
        return supplierInfoMapper.selectSupplierInfoBySupplierId(supplierId);
    }

    /**
     * 查询供应商申请列表
     *
     * @param supplierInfo 供应商申请
     * @return 供应商申请
     */
    @Override
    public List<SupplierInfo> selectSupplierInfoList(SupplierInfo supplierInfo) {
        List<SupplierInfo> supplierInfos = supplierInfoMapper.selectSupplierInfoList(supplierInfo);
        for (SupplierInfo info : supplierInfos) {
            SysUser user = userService.selectUserById(info.getUserId());
            if (StringUtils.isNotNull(user)) {
                info.setUserName(user.getUserName());
            }
            SysUser user1 = userService.selectUserById(info.getAuditUserId());
            if (StringUtils.isNotNull(user1)) {
                info.setAuditUserName(user1.getUserName());
            }
        }
        return supplierInfos;
    }

    /**
     * 新增供应商申请
     *
     * @param supplierInfo 供应商申请
     * @return 结果
     */
    @Override
    public int insertSupplierInfo(SupplierInfo supplierInfo) {
        supplierInfo.setStatus(Long.parseLong(AuditCommonStatus.AUDIT_COMMON_STATUS_0.getValue()));
        supplierInfo.setUserId(SecurityUtils.getUserId());
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
    public int updateSupplierInfo(SupplierInfo supplierInfo) {
        //查询到旧数据
        SupplierInfo supplierInfoOld = supplierInfoMapper.selectSupplierInfoBySupplierId(supplierInfo.getSupplierId());
        //如果同意了
        if (supplierInfoOld.getStatus().equals(Long.parseLong(AuditCommonStatus.AUDIT_COMMON_STATUS_1.getValue()))) {
            throw new RuntimeException("审核通过后不能修改");
        }
        //如果两个状态不同
        if (!supplierInfoOld.getStatus().equals(supplierInfo.getStatus())) {
            supplierInfo.setAuditUserId(SecurityUtils.getUserId());
        }
        //如果传过来的是同意
        if (supplierInfo.getStatus().toString().equals(AuditCommonStatus.AUDIT_COMMON_STATUS_1.getValue())) {
            //为用户授权
            userService.insertUserAuth(supplierInfo.getUserId(), new Long[]{100L, 2L});
        }
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
    public int deleteSupplierInfoBySupplierIds(Long[] supplierIds) {
        return supplierInfoMapper.deleteSupplierInfoBySupplierIds(supplierIds);
    }

    /**
     * 删除供应商申请信息
     *
     * @param supplierId 供应商申请主键
     * @return 结果
     */
    @Override
    public int deleteSupplierInfoBySupplierId(Long supplierId) {
        return supplierInfoMapper.deleteSupplierInfoBySupplierId(supplierId);
    }

    //endregion
    @Override
    public QueryWrapper<SupplierInfo> getQueryWrapper(SupplierInfoQuery supplierInfoQuery) {
        QueryWrapper<SupplierInfo> queryWrapper = new QueryWrapper<>();
        //如果不使用params可以删除
        Map<String, Object> params = supplierInfoQuery.getParams();
        if (StringUtils.isNull(params)) {
            params = new HashMap<>();
        }
        Long supplierId = supplierInfoQuery.getSupplierId();
        queryWrapper.eq(StringUtils.isNotNull(supplierId), "supplier_id", supplierId);

        Long userId = supplierInfoQuery.getUserId();
        queryWrapper.eq(StringUtils.isNotNull(userId), "user_id", userId);

        Long status = supplierInfoQuery.getStatus();
        queryWrapper.eq(StringUtils.isNotNull(status), "status", status);

        Date createTime = supplierInfoQuery.getCreateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginCreateTime")) && StringUtils.isNotNull(params.get("endCreateTime")), "create_time", params.get("beginCreateTime"), params.get("endCreateTime"));

        String updateBy = supplierInfoQuery.getUpdateBy();
        queryWrapper.like(StringUtils.isNotEmpty(updateBy), "update_by", updateBy);

        Date updateTime = supplierInfoQuery.getUpdateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginUpdateTime")) && StringUtils.isNotNull(params.get("endUpdateTime")), "update_time", params.get("beginUpdateTime"), params.get("endUpdateTime"));

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
