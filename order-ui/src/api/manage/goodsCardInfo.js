import request from '@/utils/request'

// 查询购物车列表
export function listGoodsCardInfo(query) {
  return request({
    url: '/manage/goodsCardInfo/list',
    method: 'get',
    params: query
  })
}

// 查询购物车详细
export function getGoodsCardInfo(cardId) {
  return request({
    url: '/manage/goodsCardInfo/' + cardId,
    method: 'get'
  })
}

// 新增购物车
export function addGoodsCardInfo(data) {
  return request({
    url: '/manage/goodsCardInfo',
    method: 'post',
    data: data
  })
}

// 修改购物车
export function updateGoodsCardInfo(data) {
  return request({
    url: '/manage/goodsCardInfo',
    method: 'put',
    data: data
  })
}

// 删除购物车
export function delGoodsCardInfo(cardId) {
  return request({
    url: '/manage/goodsCardInfo/' + cardId,
    method: 'delete'
  })
}
