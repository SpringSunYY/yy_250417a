import request from '@/utils/request'

// 查询商品信息列表
export function listGoodsInfo(query) {
  return request({
    url: '/manage/goodsInfo/list',
    method: 'get',
    params: query
  })
}

// 查询商品信息详细
export function getGoodsInfo(goodsId) {
  return request({
    url: '/manage/goodsInfo/' + goodsId,
    method: 'get'
  })
}

// 新增商品信息
export function addGoodsInfo(data) {
  return request({
    url: '/manage/goodsInfo',
    method: 'post',
    data: data
  })
}

// 修改商品信息
export function updateGoodsInfo(data) {
  return request({
    url: '/manage/goodsInfo',
    method: 'put',
    data: data
  })
}

// 删除商品信息
export function delGoodsInfo(goodsId) {
  return request({
    url: '/manage/goodsInfo/' + goodsId,
    method: 'delete'
  })
}
