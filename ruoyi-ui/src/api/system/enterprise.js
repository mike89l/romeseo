import request from "@/utils/request";
import { parseStrEmpty } from "@/utils/ruoyi";

//企业列表
export function getEnterpriseList(query) {
  return request({
    url: '/enterprise/manage/list',
    method: 'get',
    params: query
  })
}

// 新增企业
export function addEnterprise(data) {
  return request({
    url: '/enterprise/manage/add',
    method: 'post',
    data: data
  })
}

// 查询企业详细
export function getEnterprise(enterpriseId) {
  return request({
    url: '/enterprise/manage/' + parseStrEmpty(enterpriseId),
    method: 'get'
  })
}

// 修改企业信息
export function updateEnterprise(data) {
  return request({
    url: '/enterprise/manage',
    method: 'put',
    data: data
  })
}

// 企业状态修改
export function changeEnterpriseStatus(enterpriseId, status) {
  const data = {
    enterpriseId,
    status
  }
  return request({
    url: '/enterprise/manage/changeStatus',
    method: 'put',
    data: data
  })
}


//删除企业
export function deleteEnterprise(enterpriseId) {
  return request({
    url: '/enterprise/manage/' + enterpriseId,
    method: 'delete',
  })
}







