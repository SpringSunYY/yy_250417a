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

import com.lz.system.service.ISysUserService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lz.manage.mapper.UserAddressInfoMapper;
import com.lz.manage.model.domain.UserAddressInfo;
import com.lz.manage.service.IUserAddressInfoService;
import com.lz.manage.model.dto.userAddressInfo.UserAddressInfoQuery;
import com.lz.manage.model.vo.userAddressInfo.UserAddressInfoVo;

/**
 * 用户地址Service业务层处理
 *
 * @author YY
 * @date 2025-04-19
 */
@Service
public class UserAddressInfoServiceImpl extends ServiceImpl<UserAddressInfoMapper, UserAddressInfo> implements IUserAddressInfoService {
    @Resource
    private UserAddressInfoMapper userAddressInfoMapper;

    @Resource
    private ISysUserService userService;

    //region mybatis代码

    /**
     * 查询用户地址
     *
     * @param addressId 用户地址主键
     * @return 用户地址
     */
    @Override
    public UserAddressInfo selectUserAddressInfoByAddressId(Long addressId) {
        return userAddressInfoMapper.selectUserAddressInfoByAddressId(addressId);
    }

    /**
     * 查询用户地址列表
     *
     * @param userAddressInfo 用户地址
     * @return 用户地址
     */
    @Override
    public List<UserAddressInfo> selectUserAddressInfoList(UserAddressInfo userAddressInfo) {
        List<UserAddressInfo> userAddressInfos = userAddressInfoMapper.selectUserAddressInfoList(userAddressInfo);
        for (UserAddressInfo info : userAddressInfos) {
            SysUser user = userService.selectUserById(info.getUserId());
            if (StringUtils.isNotNull(user)) {
                info.setUserName(user.getUserName());
            }
        }
        return userAddressInfos;
    }

    /**
     * 新增用户地址
     *
     * @param userAddressInfo 用户地址
     * @return 结果
     */
    @Override
    public int insertUserAddressInfo(UserAddressInfo userAddressInfo) {
        userAddressInfo.setUserId(SecurityUtils.getUserId());
        userAddressInfo.setCreateTime(DateUtils.getNowDate());
        return userAddressInfoMapper.insertUserAddressInfo(userAddressInfo);
    }

    /**
     * 修改用户地址
     *
     * @param userAddressInfo 用户地址
     * @return 结果
     */
    @Override
    public int updateUserAddressInfo(UserAddressInfo userAddressInfo) {
        userAddressInfo.setUpdateTime(DateUtils.getNowDate());
        return userAddressInfoMapper.updateUserAddressInfo(userAddressInfo);
    }

    /**
     * 批量删除用户地址
     *
     * @param addressIds 需要删除的用户地址主键
     * @return 结果
     */
    @Override
    public int deleteUserAddressInfoByAddressIds(Long[] addressIds) {
        return userAddressInfoMapper.deleteUserAddressInfoByAddressIds(addressIds);
    }

    /**
     * 删除用户地址信息
     *
     * @param addressId 用户地址主键
     * @return 结果
     */
    @Override
    public int deleteUserAddressInfoByAddressId(Long addressId) {
        return userAddressInfoMapper.deleteUserAddressInfoByAddressId(addressId);
    }

    //endregion
    @Override
    public QueryWrapper<UserAddressInfo> getQueryWrapper(UserAddressInfoQuery userAddressInfoQuery) {
        QueryWrapper<UserAddressInfo> queryWrapper = new QueryWrapper<>();
        //如果不使用params可以删除
        Map<String, Object> params = userAddressInfoQuery.getParams();
        if (StringUtils.isNull(params)) {
            params = new HashMap<>();
        }
        Long addressId = userAddressInfoQuery.getAddressId();
        queryWrapper.eq(StringUtils.isNotNull(addressId), "address_id", addressId);

        Long userId = userAddressInfoQuery.getUserId();
        queryWrapper.eq(StringUtils.isNotNull(userId), "user_id", userId);

        String phone = userAddressInfoQuery.getPhone();
        queryWrapper.eq(StringUtils.isNotEmpty(phone), "phone", phone);

        String province = userAddressInfoQuery.getProvince();
        queryWrapper.eq(StringUtils.isNotEmpty(province), "province", province);

        String city = userAddressInfoQuery.getCity();
        queryWrapper.eq(StringUtils.isNotEmpty(city), "city", city);

        String county = userAddressInfoQuery.getCounty();
        queryWrapper.eq(StringUtils.isNotEmpty(county), "county", county);

        Date createTime = userAddressInfoQuery.getCreateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginCreateTime")) && StringUtils.isNotNull(params.get("endCreateTime")), "create_time", params.get("beginCreateTime"), params.get("endCreateTime"));

        String updateBy = userAddressInfoQuery.getUpdateBy();
        queryWrapper.like(StringUtils.isNotEmpty(updateBy), "update_by", updateBy);

        Date updateTime = userAddressInfoQuery.getUpdateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginUpdateTime")) && StringUtils.isNotNull(params.get("endUpdateTime")), "update_time", params.get("beginUpdateTime"), params.get("endUpdateTime"));

        return queryWrapper;
    }

    @Override
    public List<UserAddressInfoVo> convertVoList(List<UserAddressInfo> userAddressInfoList) {
        if (StringUtils.isEmpty(userAddressInfoList)) {
            return Collections.emptyList();
        }
        return userAddressInfoList.stream().map(UserAddressInfoVo::objToVo).collect(Collectors.toList());
    }

}
