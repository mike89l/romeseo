import request from "@/utils/request";
import { parseStrEmpty } from "@/utils/ruoyi";

//文章列表
export function getEnterpriseList(query) {
  return request({
    url: '/enterprise/manage/list',
    method: 'get',
    params: query
  })
}
