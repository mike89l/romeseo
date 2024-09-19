<template>
  <div class="container">



    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="文章标题" prop="artTitle">
        <el-input
          v-model="queryParams.artTitle"
          placeholder="请输入文章标题"
          clearable
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select
          v-model="queryParams.status"
          placeholder="文章状态"
          clearable
          style="width: 240px"
        >
          <el-option
            v-for="dict in dict.type.sys_normal_disable"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker
          v-model="dateRange"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>


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
          v-hasPermi="['article:manage:edit']"
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
          v-hasPermi="['article:manage:remove']"
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

    <el-table v-loading="loading" :data="article" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="50" align="center" />
      <el-table-column label="文章ID" align="center" key="articleId" prop="articleId"  v-if="columns[0].visible"/>
      <el-table-column label="文章标题" align="center" key="artTitle" prop="artTitle"  v-if="columns[1].visible"/>
      <el-table-column label="文章描述" align="center" key="artDescription" prop="artDescription"  v-if="columns[2].visible"/>
      <el-table-column label="文章内容" align="center" key="artContent" prop="artContent"  v-if="columns[3].visible"/>
      <el-table-column label="状态" align="center" key="status" v-if="columns[4].visible">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.status"
            active-value="0"
            inactive-value="1"
            @change="handleStatusChange(scope.row)"
          ></el-switch>
        </template>
      </el-table-column>
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
            v-hasPermi="['article:manage:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['article:manage:remove']"
          >删除</el-button>
        </template>


        <!-- 添加或修改用户配置对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
          <el-form ref="form" :model="form" :rules="rules" label-width="140px">
            <el-row>
              <el-col :span="12">
                <el-form-item label="请输入文章标题" prop="artTitle">
                  <el-input v-model="form.artTitle" placeholder="请输入文章标题" maxlength="30" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="请输入文章描述" prop="artDescription">
                  <el-input v-model="form.artDescription" placeholder="请输入文章描述" maxlength="30" />
                </el-form-item>
              </el-col>
              <!--              <el-col :span="12">-->
              <!--                <el-form-item label="归属部门" prop="deptId">-->
              <!--                  <treeselect v-model="form.deptId" :options="deptOptions" :show-count="true" placeholder="请选择归属部门" />-->
              <!--                </el-form-item>-->
              <!--              </el-col>-->
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="请输入文章内容" prop="artContent">
                  <el-input v-model="form.artContent" placeholder="请输入文章内容" maxlength="30" />
                </el-form-item>
              </el-col>
<!--              <el-col :span="12">-->
<!--                <el-form-item label="文章状态">-->
<!--                  <el-radio-group v-model="form.status">-->
<!--                    <el-radio-->
<!--                      v-for="dict in dict.type.sys_normal_disable"-->
<!--                      :key="dict.value"-->
<!--                      :label="dict.value"-->
<!--                    >{{dict.label}}</el-radio>-->
<!--                  </el-radio-group>-->
<!--                </el-form-item>-->
<!--              </el-col>-->
              <!--              <el-col :span="12">-->
              <!--                <el-form-item label="归属部门" prop="deptId">-->
              <!--                  <treeselect v-model="form.deptId" :options="deptOptions" :show-count="true" placeholder="请选择归属部门" />-->
              <!--                </el-form-item>-->
              <!--              </el-col>-->
            </el-row>

            <!--            <el-row>-->
            <!--              <el-col :span="12">-->
            <!--                <el-form-item label="手机号码" prop="phonenumber">-->
            <!--                  <el-input v-model="form.phonenumber" placeholder="请输入手机号码" maxlength="11" />-->
            <!--                </el-form-item>-->
            <!--              </el-col>-->
            <!--              <el-col :span="12">-->
            <!--                <el-form-item label="邮箱" prop="email">-->
            <!--                  <el-input v-model="form.email" placeholder="请输入邮箱" maxlength="50" />-->
            <!--                </el-form-item>-->
            <!--              </el-col>-->
            <!--            </el-row>-->
            <!--            <el-row>-->
            <!--              <el-col :span="12">-->
            <!--                <el-form-item v-if="form.userId == undefined" label="用户名称" prop="userName">-->
            <!--                  <el-input v-model="form.userName" placeholder="请输入用户名称" maxlength="30" />-->
            <!--                </el-form-item>-->
            <!--              </el-col>-->
            <!--              <el-col :span="12">-->
            <!--                <el-form-item v-if="form.userId == undefined" label="用户密码" prop="password">-->
            <!--                  <el-input v-model="form.password" placeholder="请输入用户密码" type="password" maxlength="20" show-password/>-->
            <!--                </el-form-item>-->
            <!--              </el-col>-->
            <!--            </el-row>-->
            <!--            <el-row>-->
            <!--              <el-col :span="12">-->
            <!--                <el-form-item label="用户性别">-->
            <!--                  <el-select v-model="form.sex" placeholder="请选择性别">-->
            <!--                    <el-option-->
            <!--                      v-for="dict in dict.type.sys_user_sex"-->
            <!--                      :key="dict.value"-->
            <!--                      :label="dict.label"-->
            <!--                      :value="dict.value"-->
            <!--                    ></el-option>-->
            <!--                  </el-select>-->
            <!--                </el-form-item>-->
            <!--              </el-col>-->
            <!--              <el-col :span="12">-->
            <!--                <el-form-item label="状态">-->
            <!--                  <el-radio-group v-model="form.status">-->
            <!--                    <el-radio-->
            <!--                      v-for="dict in dict.type.sys_normal_disable"-->
            <!--                      :key="dict.value"-->
            <!--                      :label="dict.value"-->
            <!--                    >{{dict.label}}</el-radio>-->
            <!--                  </el-radio-group>-->
            <!--                </el-form-item>-->
            <!--              </el-col>-->
            <!--            </el-row>-->
            <!--            <el-row>-->
            <!--              <el-col :span="12">-->
            <!--                <el-form-item label="岗位">-->
            <!--                  <el-select v-model="form.postIds" multiple placeholder="请选择岗位">-->
            <!--                    <el-option-->
            <!--                      v-for="item in postOptions"-->
            <!--                      :key="item.postId"-->
            <!--                      :label="item.postName"-->
            <!--                      :value="item.postId"-->
            <!--                      :disabled="item.status == 1"-->
            <!--                    ></el-option>-->
            <!--                  </el-select>-->
            <!--                </el-form-item>-->
            <!--              </el-col>-->
            <!--              <el-col :span="12">-->
            <!--                <el-form-item label="角色">-->
            <!--                  <el-select v-model="form.roleIds" multiple placeholder="请选择角色">-->
            <!--                    <el-option-->
            <!--                      v-for="item in roleOptions"-->
            <!--                      :key="item.roleId"-->
            <!--                      :label="item.roleName"-->
            <!--                      :value="item.roleId"-->
            <!--                      :disabled="item.status == 1"-->
            <!--                    ></el-option>-->
            <!--                  </el-select>-->
            <!--                </el-form-item>-->
            <!--              </el-col>-->
            <!--            </el-row>-->
            <!--            <el-row>-->
            <!--              <el-col :span="24">-->
            <!--                <el-form-item label="备注">-->
            <!--                  <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"></el-input>-->
            <!--                </el-form-item>-->
            <!--              </el-col>-->
            <!--            </el-row>-->
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="submitForm">确 定</el-button>
            <el-button @click="cancel">取 消</el-button>
          </div>
        </el-dialog>



      </el-table-column>
    </el-table>

    <!--    分页处理-->
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
import {addArticle,updateArticle,deleteArticle,getarticleList,changeArticleStatus,getarticle} from "@/api/system/article";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import Treeselect from "@riophae/vue-treeselect";
import {getToken} from "@/utils/auth";
import {changeUserStatus} from "@/api/system/user";


export default {
  name: "User",
  dicts: ['sys_normal_disable', 'sys_user_sex'],
  components: {Treeselect},
  data() {
    return {
      // 文章列表数据
      article: null,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 弹出层标题
      title: "",
      // 部门树选项
      deptOptions: undefined,
      // 是否显示弹出层
      open: false,
      // 日期范围
      dateRange: [],
      // 查询参数
      // 岗位选项
      postOptions: [],
      // 角色选项
      roleOptions: [],
      // 表单参数
      form: {},
      defaultProps: {
        children: "children",
        label: "label"
      },
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
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        articleId: '',
        artTitle: ''
      },
      // 列信息
      columns: [
        { key: 0, label: `文章ID`, visible: true },
        { key: 1, label: `文章标题`, visible: true },
        { key: 2, label: `文章描述`, visible: true },
        { key: 3, label: `文章内容`, visible: true },
        { key: 4, label: `状态`, visible: true },
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
        ]
      }
      // 表单校验
      // rules: {
      //   userName: [
      //     { required: true, message: "用户名称不能为空", trigger: "blur" },
      //     { min: 2, max: 20, message: '用户名称长度必须介于 2 和 20 之间', trigger: 'blur' }
      //   ],
      //   nickName: [
      //     { required: true, message: "用户昵称不能为空", trigger: "blur" }
      //   ],
      //   password: [
      //     { required: true, message: "用户密码不能为空", trigger: "blur" },
      //     { min: 5, max: 20, message: '用户密码长度必须介于 5 和 20 之间', trigger: 'blur' },
      //     { pattern: /^[^<>"'|\\]+$/, message: "不能包含非法字符：< > \" ' \\\ |", trigger: "blur" }
      //   ],
      //   email: [
      //     {
      //       type: "email",
      //       message: "请输入正确的邮箱地址",
      //       trigger: ["blur", "change"]
      //     }
      //   ],
      //   phonenumber: [
      //     {
      //       pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
      //       message: "请输入正确的手机号码",
      //       trigger: "blur"
      //     }
      //   ]
      // }
    }
  },
  created() {
    this.articleList();
  },
  methods: {
    articleList(){
      this.loading = true;
      // 获取文章数据
      this.loading = true;
      getarticleList(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
          this.article = response.rows;
          this.total = response.total;
          this.loading = false;
        }
      );
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.articleList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.dateRange = [];
      this.resetForm("queryForm");
      this.$refs.tree.setCurrentKey(null);
      this.handleQuery();
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
    /** 下载模板操作 */
    importTemplate() {
      this.download('system/user/importTemplate', {
      }, `user_template_${new Date().getTime()}.xlsx`)
    },
    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.upload.open = false;
      this.upload.isUploading = false;
      this.$refs.upload.clearFiles();
      this.$alert("<div style='overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;'>" + response.msg + "</div>", "导入结果", { dangerouslyUseHTMLString: true });
      this.articleList();
    },
    // 用户状态修改
    handleStatusChange(row) {
      let text = row.status === "0" ? "启用" : "停用";
      this.$modal.confirm('确认要"  ' + text + '' + row.artTitle + '  "文章吗？').then(function() {
        return changeArticleStatus(row.articleId, row.status);
      }).then(() => {
        this.$modal.msgSuccess(text + "成功");
      }).catch(function() {
        row.status = row.status === "0" ? "1" : "0";
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        artTitle: undefined,
        artDescription: undefined,
        artContent: undefined,
        status: undefined,
        delFlag:undefined
      };
      this.resetForm("form");
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加文章";
      // getUser().then(response => {
      //   this.postOptions = response.posts;
      //   this.roleOptions = response.roles;
      //   this.open = true;
      //   this.title = "添加文章";
      // });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.articleId != undefined) {
            // 修改文章
            updateArticle(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.articleList();
            });
          } else {
            // 新增文章
            addArticle(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.articleList();
            });
          }
        }



        // if (valid) {
        //   if (valid) {
        //     addArticle(this.form).then(response => {
        //       this.$modal.msgSuccess("新增成功");
        //       this.open = false;
        //       this.articleList();
        //     });
        //   } else {
        //     console.log('error submit!!');
        //     return false;
        //   }
        // }
      });



      // this.$refs["form"].validate(valid => {
      //   if (valid) {
      //     if (this.form.userId != undefined) {
      //       updateUser(this.form).then(response => {
      //         this.$modal.msgSuccess("修改成功");
      //         this.open = false;
      //         this.getList();
      //       });
      //     } else {
      //       addUser(this.form).then(response => {
      //         this.$modal.msgSuccess("新增成功");
      //         this.open = false;
      //         this.getList();
      //       });
      //     }
      //   }
      // });
    },
    handleSelectionChange(){},
    handleEdit(index, row) {
      // console.log(index, row);
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const articleId = row.articleId || this.ids;
      getarticle(articleId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改文章";
        // this.form.password = "";
      });
    },

    /** 删除按钮操作 */
    handleDelete(row) {
      const articleIds = row.articleId || this.ids;
      this.$modal.confirm('是否确认删除用户编号为"' + articleIds + '"的数据项？').then(function() {
        return deleteArticle(articleIds);
      }).then(() => {
        this.articleList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    }
  }
}


</script>

<style lang="scss" scoped>
.container{
  margin-top: 1%;
  margin-left: 1%;
}
</style>
