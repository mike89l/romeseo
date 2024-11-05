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
    url: '/system/submit/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getData(id) {
    return request({
      url: '/system/submit/' + id,
      method: 'get'
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
      url: '/system/stutas/edit',
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

  // 删除【请填写功能名称】
  export function info(id) {
    return request({
      url: '/system/stutas/info',
      method: 'get'
    })
  }

  export function login(username, password) {
    return request({
      url: '/login',
      method: 'post',
      data: {
        username: username,
        password: password
      }
    });
  }
