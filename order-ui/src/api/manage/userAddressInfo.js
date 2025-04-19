import request from '@/utils/request'

// 查询用户地址列表
export function listUserAddressInfo(query) {
  return request({
    url: '/manage/userAddressInfo/list',
    method: 'get',
    params: query
  })
}

// 查询用户地址详细
export function getUserAddressInfo(addressId) {
  return request({
    url: '/manage/userAddressInfo/' + addressId,
    method: 'get'
  })
}

// 新增用户地址
export function addUserAddressInfo(data) {
  return request({
    url: '/manage/userAddressInfo',
    method: 'post',
    data: data
  })
}

// 修改用户地址
export function updateUserAddressInfo(data) {
  return request({
    url: '/manage/userAddressInfo',
    method: 'put',
    data: data
  })
}

// 删除用户地址
export function delUserAddressInfo(addressId) {
  return request({
    url: '/manage/userAddressInfo/' + addressId,
    method: 'delete'
  })
}
