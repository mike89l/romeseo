import request from '@/utils/request'


// 提交链接入库
export function insertdata(data) {
  return request({
    url: '/system/submit/add',
    method: 'post',
    data: data
  })
}

// 查询链接列表
export function listData(query) {
  return request({
    url: '/system/subnit/list',
    method: 'get',
    params: query
  })
}


// 查询【请填写功能名称】列表
export function listStutas(query) {
    return request({
      url: '/system/stutas/list',
      method: 'get',
      params: query
    })
  }
  
  // 查询【请填写功能名称】详细
  export function getStutas(id) {
    return request({
      url: '/system/stutas/' + id,
      method: 'get'
    })
  }
  
  // 新增【请填写功能名称】
  export function addStutas(data) {
    return request({
      url: '/system/stutas',
      method: 'post',
      data: data
    })
  }
  
  // 修改【请填写功能名称】
  export function updateStutas(data) {
    return request({
      url: '/system/stutas',
      method: 'put',
      data: data
    })
  }
  
  // 删除【请填写功能名称】
  export function delStutas(id) {
    return request({
      url: '/system/stutas/' + id,
      method: 'delete'
    })
  }
  