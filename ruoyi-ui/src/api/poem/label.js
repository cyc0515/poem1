import request from '@/utils/request'

// 查询诗词标签列表
export function listLabel(query) {
  return request({
    url: '/poem/label/list',
    method: 'get',
    params: query
  })
}

// 查询诗词标签详细
export function getLabel(id) {
  return request({
    url: '/poem/label/' + id,
    method: 'get'
  })
}

// 新增诗词标签
export function addLabel(data) {
  return request({
    url: '/poem/label',
    method: 'post',
    data: data
  })
}

// 修改诗词标签
export function updateLabel(data) {
  return request({
    url: '/poem/label',
    method: 'put',
    data: data
  })
}

// 删除诗词标签
export function delLabel(id) {
  return request({
    url: '/poem/label/' + id,
    method: 'delete'
  })
}

// 导出诗词标签
export function exportLabel(query) {
  return request({
    url: '/poem/label/export',
    method: 'get',
    params: query
  })
}