package com.lz.manage.service.impl;

import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import com.lz.common.utils.StringUtils;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.common.utils.DateUtils;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lz.manage.mapper.RechargeHistoryInfoMapper;
import com.lz.manage.model.domain.RechargeHistoryInfo;
import com.lz.manage.service.IRechargeHistoryInfoService;
import com.lz.manage.model.dto.rechargeHistoryInfo.RechargeHistoryInfoQuery;
import com.lz.manage.model.vo.rechargeHistoryInfo.RechargeHistoryInfoVo;

/**
 * 充值记录Service业务层处理
 * 
 * @author YY
 * @date 2025-04-19
 */
@Service
public class RechargeHistoryInfoServiceImpl extends ServiceImpl<RechargeHistoryInfoMapper, RechargeHistoryInfo> implements IRechargeHistoryInfoService
{
    @Resource
    private RechargeHistoryInfoMapper rechargeHistoryInfoMapper;

    //region mybatis代码
    /**
     * 查询充值记录
     * 
     * @param historyId 充值记录主键
     * @return 充值记录
     */
    @Override
    public RechargeHistoryInfo selectRechargeHistoryInfoByHistoryId(Long historyId)
    {
        return rechargeHistoryInfoMapper.selectRechargeHistoryInfoByHistoryId(historyId);
    }

    /**
     * 查询充值记录列表
     * 
     * @param rechargeHistoryInfo 充值记录
     * @return 充值记录
     */
    @Override
    public List<RechargeHistoryInfo> selectRechargeHistoryInfoList(RechargeHistoryInfo rechargeHistoryInfo)
    {
        return rechargeHistoryInfoMapper.selectRechargeHistoryInfoList(rechargeHistoryInfo);
    }

    /**
     * 新增充值记录
     * 
     * @param rechargeHistoryInfo 充值记录
     * @return 结果
     */
    @Override
    public int insertRechargeHistoryInfo(RechargeHistoryInfo rechargeHistoryInfo)
    {
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
    public int updateRechargeHistoryInfo(RechargeHistoryInfo rechargeHistoryInfo)
    {
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
    public int deleteRechargeHistoryInfoByHistoryIds(Long[] historyIds)
    {
        return rechargeHistoryInfoMapper.deleteRechargeHistoryInfoByHistoryIds(historyIds);
    }

    /**
     * 删除充值记录信息
     * 
     * @param historyId 充值记录主键
     * @return 结果
     */
    @Override
    public int deleteRechargeHistoryInfoByHistoryId(Long historyId)
    {
        return rechargeHistoryInfoMapper.deleteRechargeHistoryInfoByHistoryId(historyId);
    }
    //endregion
    @Override
    public QueryWrapper<RechargeHistoryInfo> getQueryWrapper(RechargeHistoryInfoQuery rechargeHistoryInfoQuery){
        QueryWrapper<RechargeHistoryInfo> queryWrapper = new QueryWrapper<>();
        //如果不使用params可以删除
        Map<String, Object> params = rechargeHistoryInfoQuery.getParams();
        if (StringUtils.isNull(params)) {
            params = new HashMap<>();
        }
        Long historyId = rechargeHistoryInfoQuery.getHistoryId();
        queryWrapper.eq( StringUtils.isNotNull(historyId),"history_id",historyId);

        Long userId = rechargeHistoryInfoQuery.getUserId();
        queryWrapper.eq( StringUtils.isNotNull(userId),"user_id",userId);

        Long auditStatus = rechargeHistoryInfoQuery.getAuditStatus();
        queryWrapper.eq( StringUtils.isNotNull(auditStatus),"audit_status",auditStatus);

        Date createTime = rechargeHistoryInfoQuery.getCreateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginCreateTime"))&&StringUtils.isNotNull(params.get("endCreateTime")),"create_time",params.get("beginCreateTime"),params.get("endCreateTime"));

        String updateBy = rechargeHistoryInfoQuery.getUpdateBy();
        queryWrapper.like(StringUtils.isNotEmpty(updateBy) ,"update_by",updateBy);

        Date updateTime = rechargeHistoryInfoQuery.getUpdateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginUpdateTime"))&&StringUtils.isNotNull(params.get("endUpdateTime")),"update_time",params.get("beginUpdateTime"),params.get("endUpdateTime"));

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
