import request from '@/utils/request'

// 校验客户的身份信息是否合理
export function checkCustomerIdentity(query) {
  return request({
    url: '/business/rentCar/checkCustomerIdentity',
    method: 'get',
    params: query
  })
}

// 根据车牌号查看这个车辆大图
export function getCarImagesToShow(query) {
  return request({
    url: '/business/rentCar/getCarImagesToShow',
    method: 'get',
    params: query
  })
}


export function createRentSn() {
  return request({
    url: '/business/rentCar/createRentSn',
    method: 'get'
  })
}

// ajax 发get ?  params   如果post请求 你想发送json
export function realDoRent(data) {
  return request({
    url: '/business/rentCar/realDoRent',
    method: 'post',
    data: data
  })
}
