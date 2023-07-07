import request from "@/utils/request";

export function getRentDataByRentId(query){
  return request({
    url: '/business/backCar/getRentDataByRentId/'+query,
    method: 'get',
    // params: query
  })
}
