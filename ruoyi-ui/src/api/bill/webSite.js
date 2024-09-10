import request from '@/utils/request'

// 查询参数列表
export function subtWebSite() {
  return request({
    url: '/system/bill/onSubmit',
    method: 'post',
    params: query
  })
}

export function getWebSite(query) {
  console.log(query)
  return request({
    url: '/system/bill/getWebSite',
    method: 'get',
    params: query
  })
}
