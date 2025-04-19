package com.lz.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lz.common.annotation.DataScope;
import com.lz.common.core.domain.entity.SysUser;
import com.lz.common.exception.ServiceException;
import com.lz.common.utils.DateUtils;
import com.lz.common.utils.SecurityUtils;
import com.lz.common.utils.StringUtils;
import com.lz.manage.mapper.RechargeHistoryInfoMapper;
import com.lz.manage.model.domain.RechargeHistoryInfo;
import com.lz.manage.model.domain.UserBalanceInfo;
import com.lz.manage.model.dto.rechargeHistoryInfo.RechargeHistoryInfoQuery;
import com.lz.manage.model.enums.AuditCommonStatus;
import com.lz.manage.model.vo.rechargeHistoryInfo.RechargeHistoryInfoVo;
import com.lz.manage.service.IRechargeHistoryInfoService;
import com.lz.manage.service.IUserBalanceInfoService;
import com.lz.system.service.ISysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 充值记录Service业务层处理
 *
 * @author YY
 * @date 2025-04-19
 */
@Service
public class RechargeHistoryInfoServiceImpl extends ServiceImpl<RechargeHistoryInfoMapper, RechargeHistoryInfo> implements IRechargeHistoryInfoService {
    @Resource
    private RechargeHistoryInfoMapper rechargeHistoryInfoMapper;

    @Resource
    private IUserBalanceInfoService userBalanceInfoService;

    @Resource
    private ISysUserService userService;

    //region mybatis代码

    /**
     * 查询充值记录
     *
     * @param historyId 充值记录主键
     * @return 充值记录
     */
    @Override
    public RechargeHistoryInfo selectRechargeHistoryInfoByHistoryId(Long historyId) {
        return rechargeHistoryInfoMapper.selectRechargeHistoryInfoByHistoryId(historyId);
    }

    /**
     * 查询充值记录列表
     *
     * @param rechargeHistoryInfo 充值记录
     * @return 充值记录
     */
    @Override
    @DataScope(deptAlias = "tb_recharge_history_info", userAlias = "tb_recharge_history_info")
    public List<RechargeHistoryInfo> selectRechargeHistoryInfoList(RechargeHistoryInfo rechargeHistoryInfo) {
        List<RechargeHistoryInfo> infos = rechargeHistoryInfoMapper.selectRechargeHistoryInfoList(rechargeHistoryInfo);
        for (RechargeHistoryInfo info : infos) {
            SysUser user = userService.selectUserById(info.getUserId());
            if (StringUtils.isNotNull(user)) {
                info.setUserName(user.getUserName());
            }
        }
        return infos;
    }

    /**
     * 新增充值记录
     *
     * @param rechargeHistoryInfo 充值记录
     * @return 结果
     */
    @Override
    public int insertRechargeHistoryInfo(RechargeHistoryInfo rechargeHistoryInfo) {
        //判断用户是否有审核权限且传过来用户id
        if (StringUtils.isNotNull(rechargeHistoryInfo.getUserId())
                && !SecurityUtils.hasPermi("manage:rechargeHistoryInfo:audit")) {
            throw new ServiceException("没有权限");
        }
        //如果没有传过来用户ID，就是登录用户
        if (StringUtils.isNull(rechargeHistoryInfo.getUserId())) {
            rechargeHistoryInfo.setUserId(SecurityUtils.getUserId());
            rechargeHistoryInfo.setAuditStatus(Long.parseLong(AuditCommonStatus.AUDIT_COMMON_STATUS_0.getValue()));
        }
        //如果传过来的是同意
        if (rechargeHistoryInfo.getAuditStatus().toString().equals(AuditCommonStatus.AUDIT_COMMON_STATUS_1.getValue())) {
            //为用户新增余额
            UserBalanceInfo userBalanceInfo = new UserBalanceInfo();
            userBalanceInfo.setUserId(rechargeHistoryInfo.getUserId());
            userBalanceInfo.setBalance(rechargeHistoryInfo.getRechargePrice());
            userBalanceInfoService.addUserBalanceInfo(userBalanceInfo);
        }
        rechargeHistoryInfo.setCreateTime(DateUtils.getNowDate());
        return rechargeHistoryInfoMapper.insertRechargeHistoryInfo(rechargeHistoryInfo);
    }

    /**
     * 修改充值记录
     *
     * @param rechargeHistoryInfo 充值记录
     * @return 结果
     */
    @Override
    public int updateRechargeHistoryInfo(RechargeHistoryInfo rechargeHistoryInfo) {
        //如果数据库内容是同意
        RechargeHistoryInfo historyInfoDb = this.selectRechargeHistoryInfoByHistoryId(rechargeHistoryInfo.getHistoryId());
        if (StringUtils.isNull(historyInfoDb)) {
            throw new ServiceException("充值记录不存在");
        }
        if (historyInfoDb.getAuditStatus().toString().equals(AuditCommonStatus.AUDIT_COMMON_STATUS_1.getValue())) {
            throw new ServiceException("充值记录已审核通过，不能修改");
        }
        //如果传过来的是同意
        if (rechargeHistoryInfo.getAuditStatus().toString().equals(AuditCommonStatus.AUDIT_COMMON_STATUS_1.getValue())) {
            //为用户新增余额
            UserBalanceInfo userBalanceInfo = new UserBalanceInfo();
            userBalanceInfo.setUserId(rechargeHistoryInfo.getUserId());
            userBalanceInfo.setBalance(rechargeHistoryInfo.getRechargePrice());
            userBalanceInfoService.addUserBalanceInfo(userBalanceInfo);
        }
        rechargeHistoryInfo.setUpdateBy(SecurityUtils.getUsername());
        rechargeHistoryInfo.setUpdateTime(DateUtils.getNowDate());
        return rechargeHistoryInfoMapper.updateRechargeHistoryInfo(rechargeHistoryInfo);
    }

    /**
     * 批量删除充值记录
     *
     * @param historyIds 需要删除的充值记录主键
     * @return 结果
     */
    @Override
    public int deleteRechargeHistoryInfoByHistoryIds(Long[] historyIds) {
        return rechargeHistoryInfoMapper.deleteRechargeHistoryInfoByHistoryIds(historyIds);
    }

    /**
     * 删除充值记录信息
     *
     * @param historyId 充值记录主键
     * @return 结果
     */
    @Override
    public int deleteRechargeHistoryInfoByHistoryId(Long historyId) {
        return rechargeHistoryInfoMapper.deleteRechargeHistoryInfoByHistoryId(historyId);
    }

    //endregion
    @Override
    public QueryWrapper<RechargeHistoryInfo> getQueryWrapper(RechargeHistoryInfoQuery rechargeHistoryInfoQuery) {
        QueryWrapper<RechargeHistoryInfo> queryWrapper = new QueryWrapper<>();
        //如果不使用params可以删除
        Map<String, Object> params = rechargeHistoryInfoQuery.getParams();
        if (StringUtils.isNull(params)) {
            params = new HashMap<>();
        }
        Long historyId = rechargeHistoryInfoQuery.getHistoryId();
        queryWrapper.eq(StringUtils.isNotNull(historyId), "history_id", historyId);

        Long userId = rechargeHistoryInfoQuery.getUserId();
        queryWrapper.eq(StringUtils.isNotNull(userId), "user_id", userId);

        Long auditStatus = rechargeHistoryInfoQuery.getAuditStatus();
        queryWrapper.eq(StringUtils.isNotNull(auditStatus), "audit_status", auditStatus);

        Date createTime = rechargeHistoryInfoQuery.getCreateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginCreateTime")) && StringUtils.isNotNull(params.get("endCreateTime")), "create_time", params.get("beginCreateTime"), params.get("endCreateTime"));

        String updateBy = rechargeHistoryInfoQuery.getUpdateBy();
        queryWrapper.like(StringUtils.isNotEmpty(updateBy), "update_by", updateBy);

        Date updateTime = rechargeHistoryInfoQuery.getUpdateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginUpdateTime")) && StringUtils.isNotNull(params.get("endUpdateTime")), "update_time", params.get("beginUpdateTime"), params.get("endUpdateTime"));

        return queryWrapper;
    }

    @Override
    public List<RechargeHistoryInfoVo> convertVoList(List<RechargeHistoryInfo> rechargeHistoryInfoList) {
        if (StringUtils.isEmpty(rechargeHistoryInfoList)) {
            return Collections.emptyList();
        }
        return rechargeHistoryInfoList.stream().map(RechargeHistoryInfoVo::objToVo).collect(Collectors.toList());
    }
}
