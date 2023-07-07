import request from '@/utils/request'

// 查询客户增删改查列表
export function listCustomer(query) {
  return request({
    url: '/business/customer/list',
    method: 'get',
    params: query
  })
}

// 查询客户增删改查详细
export function getCustomer(id) {
  return request({
    url: '/business/customer/' + id,
    method: 'get'
  })
}

// 新增客户增删改查
export function addCustomer(data) {
  return request({
    url: '/business/customer',
    method: 'post',
    data: data
  })
}

// 修改客户增删改查
export function updateCustomer(data) {
  return request({
    url: '/business/customer',
    method: 'put',
    data: data
  })
}

// 删除客户增删改查
export function delCustomer(id) {
  return request({
    url: '/business/customer/' + id,
    method: 'delete'
  })
}

export function getCustomerByIdentity(identity){
  return request({
    url:'/business/customer/getCustomerByIdentity/'+identity,
    method:'get'
  })
}
