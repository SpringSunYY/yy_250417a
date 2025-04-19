package com.lz.manage.service;

import java.util.List;
import com.lz.manage.model.domain.UserBalanceInfo;
import com.lz.manage.model.vo.userBalanceInfo.UserBalanceInfoVo;
import com.lz.manage.model.dto.userBalanceInfo.UserBalanceInfoQuery;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
 * 用户余额Service接口
 * 
 * @author YY
 * @date 2025-04-19
 */
public interface IUserBalanceInfoService extends IService<UserBalanceInfo>
{
    //region mybatis代码
    /**
     * 查询用户余额
     * 
     * @param balanceId 用户余额主键
     * @return 用户余额
     */
    public UserBalanceInfo selectUserBalanceInfoByBalanceId(Long balanceId);

    /**
     * 查询用户余额列表
     * 
     * @param userBalanceInfo 用户余额
     * @return 用户余额集合
     */
    public List<UserBalanceInfo> selectUserBalanceInfoList(UserBalanceInfo userBalanceInfo);

    /**
     * 新增用户余额
     * 
     * @param userBalanceInfo 用户余额
     * @return 结果
     */
    public int insertUserBalanceInfo(UserBalanceInfo userBalanceInfo);

    /**
     * 修改用户余额
     * 
     * @param userBalanceInfo 用户余额
     * @return 结果
     */
    public int updateUserBalanceInfo(UserBalanceInfo userBalanceInfo);

    /**
     * 批量删除用户余额
     * 
     * @param balanceIds 需要删除的用户余额主键集合
     * @return 结果
     */
    public int deleteUserBalanceInfoByBalanceIds(Long[] balanceIds);

    /**
     * 删除用户余额信息
     * 
     * @param balanceId 用户余额主键
     * @return 结果
     */
    public int deleteUserBalanceInfoByBalanceId(Long balanceId);
    //endregion
    /**
     * 获取查询条件
     *
     * @param userBalanceInfoQuery 查询条件对象
     * @return 查询条件
     */
    QueryWrapper<UserBalanceInfo> getQueryWrapper(UserBalanceInfoQuery userBalanceInfoQuery);

    /**
     * 转换vo
     *
     * @param userBalanceInfoList UserBalanceInfo集合
     * @return UserBalanceInfoVO集合
     */
    List<UserBalanceInfoVo> convertVoList(List<UserBalanceInfo> userBalanceInfoList);
}
