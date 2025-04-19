package com.lz.manage.service;

import java.util.List;
import com.lz.manage.model.domain.UserAddressInfo;
import com.lz.manage.model.vo.userAddressInfo.UserAddressInfoVo;
import com.lz.manage.model.dto.userAddressInfo.UserAddressInfoQuery;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
 * 用户地址Service接口
 * 
 * @author YY
 * @date 2025-04-19
 */
public interface IUserAddressInfoService extends IService<UserAddressInfo>
{
    //region mybatis代码
    /**
     * 查询用户地址
     * 
     * @param addressId 用户地址主键
     * @return 用户地址
     */
    public UserAddressInfo selectUserAddressInfoByAddressId(Long addressId);

    /**
     * 查询用户地址列表
     * 
     * @param userAddressInfo 用户地址
     * @return 用户地址集合
     */
    public List<UserAddressInfo> selectUserAddressInfoList(UserAddressInfo userAddressInfo);

    /**
     * 新增用户地址
     * 
     * @param userAddressInfo 用户地址
     * @return 结果
     */
    public int insertUserAddressInfo(UserAddressInfo userAddressInfo);

    /**
     * 修改用户地址
     * 
     * @param userAddressInfo 用户地址
     * @return 结果
     */
    public int updateUserAddressInfo(UserAddressInfo userAddressInfo);

    /**
     * 批量删除用户地址
     * 
     * @param addressIds 需要删除的用户地址主键集合
     * @return 结果
     */
    public int deleteUserAddressInfoByAddressIds(Long[] addressIds);

    /**
     * 删除用户地址信息
     * 
     * @param addressId 用户地址主键
     * @return 结果
     */
    public int deleteUserAddressInfoByAddressId(Long addressId);
    //endregion
    /**
     * 获取查询条件
     *
     * @param userAddressInfoQuery 查询条件对象
     * @return 查询条件
     */
    QueryWrapper<UserAddressInfo> getQueryWrapper(UserAddressInfoQuery userAddressInfoQuery);

    /**
     * 转换vo
     *
     * @param userAddressInfoList UserAddressInfo集合
     * @return UserAddressInfoVO集合
     */
    List<UserAddressInfoVo> convertVoList(List<UserAddressInfo> userAddressInfoList);
}
