package com.lz.manage.mapper;

import java.util.List;
import com.lz.manage.model.domain.RechargeHistoryInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 充值记录Mapper接口
 * 
 * @author YY
 * @date 2025-04-19
 */
public interface RechargeHistoryInfoMapper extends BaseMapper<RechargeHistoryInfo>
{
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
     * 删除充值记录
     * 
     * @param historyId 充值记录主键
     * @return 结果
     */
    public int deleteRechargeHistoryInfoByHistoryId(Long historyId);

    /**
     * 批量删除充值记录
     * 
     * @param historyIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRechargeHistoryInfoByHistoryIds(Long[] historyIds);
}
