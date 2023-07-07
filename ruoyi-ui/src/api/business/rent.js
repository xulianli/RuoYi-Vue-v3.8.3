import request from '@/utils/request'

export function getRentSn(){
  return request({
    url:'/business/rent/getRentSn',
    method:'get'
  })
}

// 查询出租单管理列表
export function listRent(query) {
  return request({
    url: '/business/rent/list',
    method: 'get',
    params: query
  })
}

// 查询出租单管理详细
export function getRent(id) {
  return request({
    url: '/business/rent/' + id,
    method: 'get'
  })
}

// 新增出租单管理
export function addRent(data) {
  return request({
    url: '/business/rent/addRent',
    method: 'post',
    data: data
  })
}

// 修改出租单管理
export function updateRent(data) {
  return request({
    url: '/business/rent',
    method: 'put',
    data: data
  })
}

// 删除出租单管理
export function delRent(id) {
  return request({
    url: '/business/rent/' + id,
    method: 'delete'
  })
}
