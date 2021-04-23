import request from '@/utils/request'

// 查询诗词审核列表
export function listAudit(query) {
  return request({
    url: '/poem/audit/list',
    method: 'get',
    params: query
  })
}

// 查询诗词审核详细
export function getAudit(id) {
  return request({
    url: '/poem/audit/' + id,
    method: 'get'
  })
}

// 新增诗词审核
export function addAudit(data) {
  return request({
    url: '/poem/audit',
    method: 'post',
    data: data
  })
}

// 修改诗词审核
export function updateAudit(data) {
  return request({
    url: '/poem/audit',
    method: 'put',
    data: data
  })
}
// 诗词审核通过
export function audit(id) {
  return request({
    url: '/poem/audit/audit/' + id,
    method: 'delete'
  })
}
// 诗词审核驳回
export function reaudit(id) {
  return request({
    url: '/poem/audit/reaudit/' + id,
    method: 'delete'
  })
}

// 删除诗词审核
export function delAudit(id) {
  return request({
    url: '/poem/audit/' + id,
    method: 'delete'
  })
}

// 导出诗词审核
export function exportAudit(query) {
  return request({
    url: '/poem/audit/export',
    method: 'get',
    params: query
  })
}