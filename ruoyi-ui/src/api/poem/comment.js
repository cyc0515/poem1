import request from '@/utils/request'

// 查询诗词评论列表
export function listComment(query) {
  return request({
    url: '/poem/comment/list',
    method: 'get',
    params: query
  })
}

// 查询诗词评论详细
export function getComment(id) {
  return request({
    url: '/poem/comment/' + id,
    method: 'get'
  })
}

// 新增诗词评论
export function addComment(data) {
  return request({
    url: '/poem/comment',
    method: 'post',
    data: data
  })
}

// 修改诗词评论
export function updateComment(data) {
  return request({
    url: '/poem/comment',
    method: 'put',
    data: data
  })
}

// 删除诗词评论
export function delComment(id) {
  return request({
    url: '/poem/comment/' + id,
    method: 'delete'
  })
}

// 导出诗词评论
export function exportComment(query) {
  return request({
    url: '/poem/comment/export',
    method: 'get',
    params: query
  })
}