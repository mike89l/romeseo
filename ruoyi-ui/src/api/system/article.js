import request from "@/utils/request";

//新增文章
export function addArticle(data) {
  return request({
    url: '/article/add',
    method: 'post',
    data: data
  })
}
