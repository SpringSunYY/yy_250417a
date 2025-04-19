import request from '@/utils/request'

// 查询商品评价列表
export function listRoomCommentInfo(query) {
  return request({
    url: '/manage/roomCommentInfo/list',
    method: 'get',
    params: query
  })
}

// 查询商品评价详细
export function getRoomCommentInfo(commentId) {
  return request({
    url: '/manage/roomCommentInfo/' + commentId,
    method: 'get'
  })
}

// 新增商品评价
export function addRoomCommentInfo(data) {
  return request({
    url: '/manage/roomCommentInfo',
    method: 'post',
    data: data
  })
}

// 修改商品评价
export function updateRoomCommentInfo(data) {
  return request({
    url: '/manage/roomCommentInfo',
    method: 'put',
    data: data
  })
}

// 删除商品评价
export function delRoomCommentInfo(commentId) {
  return request({
    url: '/manage/roomCommentInfo/' + commentId,
    method: 'delete'
  })
}
