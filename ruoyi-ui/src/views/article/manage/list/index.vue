<template>
  <div class="container">

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['article:manage:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:user:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:user:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-upload2"
          size="mini"
          @click="handleImport"
          v-hasPermi="['system:user:import']"
        >导入</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:user:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="articleList" :columns="columns"></right-toolbar>
    </el-row>



<!-- 添加或修改用户配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="文章标题" prop="artTitle">
              <el-input v-model="form.artTitle" placeholder="请输入文章标题" maxlength="30" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="文章描述" prop="artDescription">
              <el-input v-model="form.artDescription" placeholder="请输入文章描述" maxlength="30" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="文章内容" prop="artContent">
              <el-input v-model="form.artContent" placeholder="请输入文章内容" maxlength="32" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="文章状态" prop="status">
              <el-input v-model="form.status" placeholder="请输入文章状态" maxlength="10" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>






<!--文章数据-->
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
    </el-table>

<!--    分页-->
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="articleList"
    />




  </div>
</template>

<script>
import {addArticle, getarticleList} from "@/api/system/article";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import Treeselect from "@riophae/vue-treeselect";
import {getToken} from "@/utils/auth";


export default {
  components: {Treeselect},
  data() {
    return {
      // 文章表格数据
      article: null,
      // 遮罩层
      loading: true,
      // 总条数
      total: 0,
      // 显示搜索条件
      showSearch: true,
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 是否显示弹出层
      open: false,
      // 表单参数
      form: {},
      // 弹出层标题
      title: "",
      // 日期范围
      dateRange: [],
      // 用户导入参数
      upload: {
        // 是否显示弹出层（用户导入）
        open: false,
        // 弹出层标题（用户导入）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 是否更新已经存在的用户数据
        updateSupport: 0,
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/system/user/importData"
      },
      // 分页查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        articleId: '',
        artTitle: '',
        artDescription: '',
        artContent: '',
        status:''
      },
      // 列信息
      columns: [
        { key: 0, label: `文章ID`, visible: true },
        { key: 1, label: `文章标题`, visible: true },
        { key: 2, label: `文章描述`, visible: true },
        { key: 3, label: `文章内容`, visible: true },
        { key: 4, label: `文章状态`, visible: true },
        { key: 6, label: `创建时间`, visible: true }
      ],
      // 表单校验
      rules: {
        artTitle: [
          { required: true, message: "文章标题不能为空", trigger: "blur" }
        ],
        artDescription: [
          { required: true, message: "文章描述不能为空", trigger: "blur" }
        ],
        artContent: [
          { required: true, message: "文章内容不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "文章状态不能为空", trigger: "blur" }
        ],
        // userName: [
        //   { required: true, message: "用户名称不能为空", trigger: "blur" },
        //   { min: 2, max: 20, message: '用户名称长度必须介于 2 和 20 之间', trigger: 'blur' }
        // ],
        // password: [
        //   { required: true, message: "用户密码不能为空", trigger: "blur" },
        //   { min: 5, max: 20, message: '用户密码长度必须介于 5 和 20 之间', trigger: 'blur' },
        //   { pattern: /^[^<>"'|\\]+$/, message: "不能包含非法字符：< > \" ' \\\ |", trigger: "blur" }
        // ],
        // email: [
        //   {
        //     type: "email",
        //     message: "请输入正确的邮箱地址",
        //     trigger: ["blur", "change"]
        //   }
        // ],
        // phonenumber: [
        //   {
        //     pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
        //     message: "请输入正确的手机号码",
        //     trigger: "blur"
        //   }
        // ]
      }
    }
  },
  mounted() {
    this.articleList();
  },
  methods: {
    articleList(){
      // 获取文章数据
      this.loading = true;
      getarticleList(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
          this.article = response.rows;
          this.total = response.total;
          this.loading = false;
        }
      );
    },
    cancel() {
      this.open = false;
      this.reset();
    },
    /** 导入按钮操作 */
    handleImport() {
      this.upload.title = "用户导入";
      this.upload.open = true;
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/user/export', {
        ...this.queryParams
      }, `user_${new Date().getTime()}.xlsx`)
    },
    // 表单重置
    reset() {
      this.form = {
        articleId: undefined,
        artTitle: undefined,
        artDescription: undefined,
        artContent: undefined,
        status: undefined
      };
      this.resetForm("form");
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.artTitle != undefined) {
            addArticle(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.articleList();
            });
          } else {
            // addUser(this.form).then(response => {
            //   this.$modal.msgSuccess("新增成功");
            //   this.open = false;
            //   this.getList();
            // });
          }


          // if (this.form.articleId != undefined) {
          //   updateUser(this.form).then(response => {
          //     this.$modal.msgSuccess("修改成功");
          //     this.open = false;
          //     this.getList();
          //   });
          // } else {
          //   addUser(this.form).then(response => {
          //     this.$modal.msgSuccess("新增成功");
          //     this.open = false;
          //     this.getList();
          //   });
          // }
        }
      });
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加用户";
      // addArticle().then(response => {
      //   this.postOptions = response.posts;
      //   this.roleOptions = response.roles;
      //   this.open = true;
      //   this.title = "添加用户";
      //   this.form.password = this.initPassword;
      // });
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
  margin-top: 2%;
  margin-left: 1%;
}
</style>
