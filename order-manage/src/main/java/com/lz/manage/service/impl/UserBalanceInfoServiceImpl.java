package com.lz.manage.service.impl;

import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lz.common.annotation.DataScope;
import com.lz.common.core.domain.entity.SysUser;
import com.lz.common.utils.StringUtils;

import java.math.BigDecimal;
import java.util.Date;

import com.lz.common.utils.DateUtils;

import javax.annotation.Resource;

import com.lz.system.service.ISysUserService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lz.manage.mapper.UserBalanceInfoMapper;
import com.lz.manage.model.domain.UserBalanceInfo;
import com.lz.manage.service.IUserBalanceInfoService;
import com.lz.manage.model.dto.userBalanceInfo.UserBalanceInfoQuery;
import com.lz.manage.model.vo.userBalanceInfo.UserBalanceInfoVo;

/**
 * 用户余额Service业务层处理
 *
 * @author YY
 * @date 2025-04-19
 */
@Service
public class UserBalanceInfoServiceImpl extends ServiceImpl<UserBalanceInfoMapper, UserBalanceInfo> implements IUserBalanceInfoService {
    @Resource
    private UserBalanceInfoMapper userBalanceInfoMapper;

    @Resource
    private ISysUserService userService;

    //region mybatis代码

    /**
     * 查询用户余额
     *
     * @param id 用户余额主键
     * @return 用户余额
     */
    @Override
    public UserBalanceInfo selectUserBalanceInfoByBalanceId(Long id) {
        return userBalanceInfoMapper.selectUserBalanceInfoByBalanceId(id);
    }

    /**
     * 查询用户余额列表
     *
     * @param userBalanceInfo 用户余额
     * @return 用户余额
     */
    @Override
    @DataScope(deptAlias = "tb_user_balance_info", userAlias = "tb_user_balance_info")
    public List<UserBalanceInfo> selectUserBalanceInfoList(UserBalanceInfo userBalanceInfo) {
        List<UserBalanceInfo> infos = userBalanceInfoMapper.selectUserBalanceInfoList(userBalanceInfo);
        for (UserBalanceInfo info : infos) {
            SysUser user = userService.selectUserById(info.getUserId());
            if (StringUtils.isNotNull(user)) {
                info.setUserName(user.getUserName());
            }
        }
        return infos;
    }

    /**
     * 新增用户余额
     *
     * @param userBalanceInfo 用户余额
     * @return 结果
     */
    @Override
    public int insertUserBalanceInfo(UserBalanceInfo userBalanceInfo) {
        userBalanceInfo.setCreateTime(DateUtils.getNowDate());
        return userBalanceInfoMapper.insertUserBalanceInfo(userBalanceInfo);
    }

    /**
     * 修改用户余额
     *
     * @param userBalanceInfo 用户余额
     * @return 结果
     */
    @Override
    public int updateUserBalanceInfo(UserBalanceInfo userBalanceInfo) {
        userBalanceInfo.setUpdateTime(DateUtils.getNowDate());
        return userBalanceInfoMapper.updateUserBalanceInfo(userBalanceInfo);
    }

    /**
     * 批量删除用户余额
     *
     * @param ids 需要删除的用户余额主键
     * @return 结果
     */
    @Override
    public int deleteUserBalanceInfoByBalanceIds(Long[] ids) {
        return userBalanceInfoMapper.deleteUserBalanceInfoByBalanceIds(ids);
    }

    /**
     * 删除用户余额信息
     *
     * @param id 用户余额主键
     * @return 结果
     */
    @Override
    public int deleteUserBalanceInfoByBalanceId(Long id) {
        return userBalanceInfoMapper.deleteUserBalanceInfoByBalanceId(id);
    }

    //endregion
    @Override
    public QueryWrapper<UserBalanceInfo> getQueryWrapper(UserBalanceInfoQuery userBalanceInfoQuery) {
        QueryWrapper<UserBalanceInfo> queryWrapper = new QueryWrapper<>();
        //如果不使用params可以删除
        Map<String, Object> params = userBalanceInfoQuery.getParams();
        if (StringUtils.isNull(params)) {
            params = new HashMap<>();
        }
        Long id = userBalanceInfoQuery.getBalanceId();
        queryWrapper.eq(StringUtils.isNotNull(id), "id", id);

        Long userId = userBalanceInfoQuery.getUserId();
        queryWrapper.eq(StringUtils.isNotNull(userId), "user_id", userId);

        Date updateTime = userBalanceInfoQuery.getUpdateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginUpdateTime")) && StringUtils.isNotNull(params.get("endUpdateTime")), "update_time", params.get("beginUpdateTime"), params.get("endUpdateTime"));

        Date createTime = userBalanceInfoQuery.getCreateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginCreateTime")) && StringUtils.isNotNull(params.get("endCreateTime")), "create_time", params.get("beginCreateTime"), params.get("endCreateTime"));

        return queryWrapper;
    }

    @Override
    public List<UserBalanceInfoVo> convertVoList(List<UserBalanceInfo> userBalanceInfoList) {
        if (StringUtils.isEmpty(userBalanceInfoList)) {
            return Collections.emptyList();
        }
        return userBalanceInfoList.stream().map(UserBalanceInfoVo::objToVo).collect(Collectors.toList());
    }

    @Override
    public UserBalanceInfo addUserBalanceInfo(UserBalanceInfo userBalanceInfo) {
        //判断此用户是否已经有余额
        UserBalanceInfo balanceInfo = this.getOne(new LambdaQueryWrapper<UserBalanceInfo>().eq(UserBalanceInfo::getUserId, userBalanceInfo.getUserId()));
        if (StringUtils.isNull(balanceInfo)) {
            balanceInfo = new UserBalanceInfo();
            balanceInfo.setBalance(userBalanceInfo.getBalance());
            balanceInfo.setUserId(userBalanceInfo.getUserId());
            balanceInfo.setCreateTime(DateUtils.getNowDate());
        } else {
            balanceInfo.setBalance(balanceInfo.getBalance().add(userBalanceInfo.getBalance()));
            balanceInfo.setUpdateTime(new Date());
        }
        return this.saveOrUpdate(balanceInfo) ? balanceInfo : null;
    }

    @Override
    public UserBalanceInfo selectUserBalanceInfoByUserId(Long userId) {
        return this.getOne(new LambdaQueryWrapper<UserBalanceInfo>().eq(UserBalanceInfo::getUserId, userId));
    }
}
