import request from '@/utils/request'

// 查询订单信息列表
export function listOrderInfo(query) {
  return request({
    url: '/manage/orderInfo/list',
    method: 'get',
    params: query
  })
}

// 查询订单信息详细
export function getOrderInfo(orderId) {
  return request({
    url: '/manage/orderInfo/' + orderId,
    method: 'get'
  })
}

// 新增订单信息
export function addOrderInfo(data) {
  return request({
    url: '/manage/orderInfo',
    method: 'post',
    data: data
  })
}

// 修改订单信息
export function updateOrderInfo(data) {
  return request({
    url: '/manage/orderInfo',
    method: 'put',
    data: data
  })
}

// 删除订单信息
export function delOrderInfo(orderId) {
  return request({
    url: '/manage/orderInfo/' + orderId,
    method: 'delete'
  })
}// 删除订单信息
export function payOrderInfo(orderId) {
  return request({
    url: '/manage/orderInfo/pay/' + orderId,
    method: 'get'
  })
}
