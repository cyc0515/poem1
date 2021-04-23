import request from '@/utils/request'

// 查询诗词分享列表
export function listShare(query) {
  return request({
    url: '/poem/share/list',
    method: 'get',
    params: query
  })
}

// 查询诗词分享详细
export function getShare(id) {
  return request({
    url: '/poem/share/' + id,
    method: 'get'
  })
}

// 新增诗词分享
export function addShare(data) {
  return request({
    url: '/poem/share',
    method: 'post',
    data: data
  })
}

// 修改诗词分享
export function updateShare(data) {
  return request({
    url: '/poem/share',
    method: 'put',
    data: data
  })
}

// 删除诗词分享
export function delShare(id) {
  return request({
    url: '/poem/share/' + id,
    method: 'delete'
  })
}

// 导出诗词分享
export function exportShare(query) {
  return request({
    url: '/poem/share/export',
    method: 'get',
    params: query
  })
}