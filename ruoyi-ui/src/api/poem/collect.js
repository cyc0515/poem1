import request from '@/utils/request'

// 查询诗词收藏列表
export function listCollect(query) {
  return request({
    url: '/poem/collect/list',
    method: 'get',
    params: query
  })
}

// 查询诗词收藏详细
export function getCollect(id) {
  return request({
    url: '/poem/collect/' + id,
    method: 'get'
  })
}

// 新增诗词收藏
export function addCollect(data) {
  return request({
    url: '/poem/collect',
    method: 'post',
    data: data
  })
}

// 修改诗词收藏
export function updateCollect(data) {
  return request({
    url: '/poem/collect',
    method: 'put',
    data: data
  })
}

// 删除诗词收藏
export function delCollect(id) {
  return request({
    url: '/poem/collect/' + id,
    method: 'delete'
  })
}

// 导出诗词收藏
export function exportCollect(query) {
  return request({
    url: '/poem/collect/export',
    method: 'get',
    params: query
  })
}