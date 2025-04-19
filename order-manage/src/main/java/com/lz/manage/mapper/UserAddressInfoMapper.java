package com.lz.manage.mapper;

import java.util.List;
import com.lz.manage.model.domain.UserAddressInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 用户地址Mapper接口
 * 
 * @author YY
 * @date 2025-04-19
 */
public interface UserAddressInfoMapper extends BaseMapper<UserAddressInfo>
{
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
     * 删除用户地址
     * 
     * @param addressId 用户地址主键
     * @return 结果
     */
    public int deleteUserAddressInfoByAddressId(Long addressId);

    /**
     * 批量删除用户地址
     * 
     * @param addressIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserAddressInfoByAddressIds(Long[] addressIds);
}
