package com.lz.manage.service;

import java.util.List;
import com.lz.manage.model.domain.RechargeHistoryInfo;
import com.lz.manage.model.vo.rechargeHistoryInfo.RechargeHistoryInfoVo;
import com.lz.manage.model.dto.rechargeHistoryInfo.RechargeHistoryInfoQuery;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
 * 充值记录Service接口
 * 
 * @author YY
 * @date 2025-04-19
 */
public interface IRechargeHistoryInfoService extends IService<RechargeHistoryInfo>
{
    //region mybatis代码
    /**
     * 查询充值记录
     * 
     * @param historyId 充值记录主键
     * @return 充值记录
     */
    public RechargeHistoryInfo selectRechargeHistoryInfoByHistoryId(Long historyId);

    /**
     * 查询充值记录列表
     * 
     * @param rechargeHistoryInfo 充值记录
     * @return 充值记录集合
     */
    public List<RechargeHistoryInfo> selectRechargeHistoryInfoList(RechargeHistoryInfo rechargeHistoryInfo);

    /**
     * 新增充值记录
     * 
     * @param rechargeHistoryInfo 充值记录
     * @return 结果
     */
    public int insertRechargeHistoryInfo(RechargeHistoryInfo rechargeHistoryInfo);

    /**
     * 修改充值记录
     * 
     * @param rechargeHistoryInfo 充值记录
     * @return 结果
     */
    public int updateRechargeHistoryInfo(RechargeHistoryInfo rechargeHistoryInfo);

    /**
     * 批量删除充值记录
     * 
     * @param historyIds 需要删除的充值记录主键集合
     * @return 结果
     */
    public int deleteRechargeHistoryInfoByHistoryIds(Long[] historyIds);

    /**
     * 删除充值记录信息
     * 
     * @param historyId 充值记录主键
     * @return 结果
     */
    public int deleteRechargeHistoryInfoByHistoryId(Long historyId);
    //endregion
    /**
     * 获取查询条件
     *
     * @param rechargeHistoryInfoQuery 查询条件对象
     * @return 查询条件
     */
    QueryWrapper<RechargeHistoryInfo> getQueryWrapper(RechargeHistoryInfoQuery rechargeHistoryInfoQuery);

    /**
     * 转换vo
     *
     * @param rechargeHistoryInfoList RechargeHistoryInfo集合
     * @return RechargeHistoryInfoVO集合
     */
    List<RechargeHistoryInfoVo> convertVoList(List<RechargeHistoryInfo> rechargeHistoryInfoList);
}
