import request from '@/utils/request'

// 查询充值记录列表
export function listRechargeHistoryInfo(query) {
  return request({
    url: '/manage/rechargeHistoryInfo/list',
    method: 'get',
    params: query
  })
}

// 查询充值记录详细
export function getRechargeHistoryInfo(historyId) {
  return request({
    url: '/manage/rechargeHistoryInfo/' + historyId,
    method: 'get'
  })
}

// 新增充值记录
export function addRechargeHistoryInfo(data) {
  return request({
    url: '/manage/rechargeHistoryInfo',
    method: 'post',
    data: data
  })
}

// 修改充值记录
export function updateRechargeHistoryInfo(data) {
  return request({
    url: '/manage/rechargeHistoryInfo',
    method: 'put',
    data: data
  })
}

// 删除充值记录
export function delRechargeHistoryInfo(historyId) {
  return request({
    url: '/manage/rechargeHistoryInfo/' + historyId,
    method: 'delete'
  })
}
