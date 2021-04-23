import request from '@/utils/request'

// 查询诗词信息列表
export function listInfo(query) {
  return request({
    url: '/poem/info/list',
    method: 'get',
    params: query
  })
}

// 查询诗词信息详细
export function getInfo(id) {
  return request({
    url: '/poem/info/' + id,
    method: 'get'
  })
}

// 新增诗词信息
export function addInfo(data) {
  return request({
    url: '/poem/info',
    method: 'post',
    data: data
  })
}

// 修改诗词信息
export function updateInfo(data) {
  return request({
    url: '/poem/info',
    method: 'put',
    data: data
  })
}

// 上传诗词信息
export function auditInfo(id) {
  return request({
    url: '/poem/info/audit/' + id,
    method: 'delete'
  })
}

// 撤回诗词信息
export function undoInfo(id) {
  return request({
    url: '/poem/info/undo/' + id,
    method: 'delete'
  })
}

// 删除诗词信息
export function delInfo(id) {
  return request({
    url: '/poem/info/' + id,
    method: 'delete'
  })
}

// 导出诗词信息
export function exportInfo(query) {
  return request({
    url: '/poem/info/export',
    method: 'get',
    params: query
  })
}