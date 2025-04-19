import request from '@/utils/request'

// 查询商品收藏列表
export function listCollectInfo(query) {
  return request({
    url: '/manage/collectInfo/list',
    method: 'get',
    params: query
  })
}

// 查询商品收藏详细
export function getCollectInfo(collectId) {
  return request({
    url: '/manage/collectInfo/' + collectId,
    method: 'get'
  })
}

// 新增商品收藏
export function addCollectInfo(data) {
  return request({
    url: '/manage/collectInfo',
    method: 'post',
    data: data
  })
}

// 修改商品收藏
export function updateCollectInfo(data) {
  return request({
    url: '/manage/collectInfo',
    method: 'put',
    data: data
  })
}

// 删除商品收藏
export function delCollectInfo(collectId) {
  return request({
    url: '/manage/collectInfo/' + collectId,
    method: 'delete'
  })
}
