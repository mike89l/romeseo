<template>
  <div class="container">
    <el-table v-loading="loading" :data="article" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="50" align="center" />
      <el-table-column label="文章ID" align="center" key="articleId" prop="articleId"  />
      <el-table-column label="文章标题" align="center" key="artTitle" prop="artTitle"  />
      <el-table-column label="文章描述" align="center" key="artDescription" prop="artDescription"  />
      <el-table-column label="文章内容" align="center" key="artContent" prop="artContent"  />
      <el-table-column label="状态" align="center" key="status" prop="status"  />
      <el-table-column label="创建时间" align="center" key="createTime" prop="createTime"  />
      <el-table-column label="修改时间" align="center" key="updateTime" prop="updateTime"  />
    </el-table>
  </div>
</template>

<script>
import {getarticleList} from "@/api/system/article";
export default {
  data() {
    return {
      article: null,
      // 遮罩层
      loading: true
    }
  },
  mounted() {
    this.articleList();
  },
  methods: {
    articleList(){
      // 获取文章数据
      getarticleList().then(response => {
        this.article = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    handleSelectionChange(){},
    handleEdit(index, row) {
      // console.log(index, row);
    },
    handleDelete(index, row) {
      // console.log(index, row);
    }
  }
}


</script>

<style lang="scss" scoped>
.container{

}
</style>
