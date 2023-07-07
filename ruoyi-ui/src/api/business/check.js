import request from '@/utils/request'

// 查询检查单列表
export function listCheck(query) {
  return request({
    url: '/business/check/list',
    method: 'get',
    params: query
  })
}

// 查询检查单详细
export function getCheck(id) {
  return request({
    url: '/business/check/' + id,
    method: 'get'
  })
}

// 新增检查单
export function addCheck(data) {
  return request({
    url: '/business/check',
    method: 'post',
    data: data
  })
}

// 修改检查单
export function updateCheck(data) {
  return request({
    url: '/business/check',
    method: 'put',
    data: data
  })
}

// 删除检查单
export function delCheck(id) {
  return request({
    url: '/business/check/' + id,
    method: 'delete'
  })
}
