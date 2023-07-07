import request from '@/utils/request'

// 查询二级部门列表
export function listAllDept() {
  return request({
    url: '/business/dept/list',
    method: 'get'
  })
}
