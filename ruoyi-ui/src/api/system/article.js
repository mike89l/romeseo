import request from "@/utils/request";



//文章列表
export function getarticleList() {
  return request({
    url: '/article/add/list',
    method: 'get'
  })
}


//新增文章
export function addArticle(data) {
  return request({
    url: '/article/add',
    method: 'post',
    data: data
  })
}
