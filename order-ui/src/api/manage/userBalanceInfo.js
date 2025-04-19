import request from '@/utils/request'

// 查询用户余额列表
export function listUserBalanceInfo(query) {
  return request({
    url: '/manage/userBalanceInfo/list',
    method: 'get',
    params: query
  })
}

// 查询用户余额详细
export function getUserBalanceInfo(balanceId) {
  return request({
    url: '/manage/userBalanceInfo/' + balanceId,
    method: 'get'
  })
}

// 新增用户余额
export function addUserBalanceInfo(data) {
  return request({
    url: '/manage/userBalanceInfo',
    method: 'post',
    data: data
  })
}

// 修改用户余额
export function updateUserBalanceInfo(data) {
  return request({
    url: '/manage/userBalanceInfo',
    method: 'put',
    data: data
  })
}

// 删除用户余额
export function delUserBalanceInfo(balanceId) {
  return request({
    url: '/manage/userBalanceInfo/' + balanceId,
    method: 'delete'
  })
}
