import request from '@/utils/request'

// 查询诗词类型列表
export function listType(query) {
  return request({
    url: '/poem/type/list',
    method: 'get',
    params: query
  })
}

// 查询诗词类型详细
export function getType(dictCode) {
  return request({
    url: '/poem/type/' + dictCode,
    method: 'get'
  })
}

// 新增诗词类型
export function addType(data) {
  return request({
    url: '/poem/type',
    method: 'post',
    data: data
  })
}

// 修改诗词类型
export function updateType(data) {
  return request({
    url: '/poem/type',
    method: 'put',
    data: data
  })
}

// 删除诗词类型
export function delType(dictCode) {
  return request({
    url: '/poem/type/' + dictCode,
    method: 'delete'
  })
}

// 导出诗词类型
export function exportType(query) {
  return request({
    url: '/poem/type/export',
    method: 'get',
    params: query
  })
}