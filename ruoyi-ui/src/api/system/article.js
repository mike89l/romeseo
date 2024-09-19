import request from "@/utils/request";
import { parseStrEmpty } from "@/utils/ruoyi";


//文章列表
export function getarticleList(query) {
  return request({
    url: '/article/manage/list',
    method: 'get',
    params: query
  })
}

// 查询文章详细
export function getarticle(articleId) {
  return request({
    url: '/article/manage/' + parseStrEmpty(articleId),
    method: 'get'
  })
}

//新增文章
export function addArticle(data) {
  return request({
    url: '/article/manage/add',
    method: 'post',
    data: data
  })
}

// 修改文章
export function updateArticle(data) {
  return request({
    url: '/article/manage',
    method: 'put',
    data: data
  })
}

// 文章状态修改
export function changeArticleStatus(articleId, status) {
  const data = {
    articleId,
    status
  }
  return request({
    url: '/article/manage/changeStatus',
    method: 'put',
    data: data
  })
}

//删除文章
export function deleteArticle(articleId) {
  return request({
    url: '/article/manage/' + articleId,
    method: 'delete',
  })
}
