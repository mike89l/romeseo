import request from "@/utils/request";



//文章列表
export function getarticleList(query) {
  return request({
    url: '/article/manage/list',
    method: 'get',
    params: query
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

//删除文章
export function deleteArticle(data) {
  return request({
    url: '/article/manage/delete',
    method: 'post',
    data: data
  })
}
