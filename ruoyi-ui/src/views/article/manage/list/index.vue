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
      <el-table-column
        label="操作"
        align="center"
        width="160"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope" v-if="scope.row.articleId !== 1">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:user:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:user:remove']"
          >删除</el-button>

        </template>
      </el-table-column>
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="article.pageNum"
        :limit.sync="article.pageSize"
        @pagination="articleList"
      />
    </el-table>
  </div>
</template>

<script>
import {getarticleList} from "@/api/system/article";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";


export default {
  data() {
    return {
      // 文章表格数据
      article: null,
      // 遮罩层
      loading: true,
      // 总条数
      total: 0,
      // 日期范围
      dateRange: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userName: undefined,
        phonenumber: undefined,
        status: undefined,
        deptId: undefined
      },
    }
  },
  mounted() {
    this.articleList();
  },
  methods: {
    articleList(){
      // 获取文章数据
      getarticleList(this.queryParams).then(response => {
        console.log('response',response)
        this.article = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    handleSelectionChange(){},
    handleEdit(index, row) {
      // console.log(index, row);
    },
    handleUpdate(index, row){
      console.log(index, row);
    },
    handleDelete(index, row) {

      console.log(index, row);
    }
  }
}


</script>

<style lang="scss" scoped>
.container{

}
</style>
