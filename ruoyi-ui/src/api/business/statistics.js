import request from '@/utils/request'

// 查询客户地址分布
export function getCustomerAddress() {
  return request({
    url: '/business/statistics/getCustomerAddress',
    method: 'get'
  })
}
