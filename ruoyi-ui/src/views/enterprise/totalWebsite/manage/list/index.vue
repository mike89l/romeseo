<template>

  <div class="container">



    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="企业名称" prop="enterpriseName">
        <el-input
          v-model="queryParams.enterpriseName"
          placeholder="请输入企业名称"
          clearable
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="企业网址" prop="enterpriseUrl">
        <el-input
          v-model="queryParams.enterpriseUrl"
          placeholder="请输入企业网址"
          clearable
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select
          v-model="queryParams.status"
          placeholder="企业状态"
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
          v-hasPermi="['enterprise:manage:add']"
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
          v-hasPermi="['enterprise:manage:edit']"
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
          v-hasPermi="['enterprise:manage:remove']"
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
      <right-toolbar :showSearch.sync="showSearch" @queryTable="enterpriseList" :columns="columns"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="enterprise" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="50" align="center" />
      <el-table-column label="企业ID" align="center" key="enterpriseId" prop="enterpriseId"  v-if="columns[0].visible"/>
      <el-table-column label="企业名称" align="center" key="enterpriseName" prop="enterpriseName"  v-if="columns[1].visible"/>
      <el-table-column label="企业网址" align="center" key="enterpriseUrl" prop="enterpriseUrl"  v-if="columns[2].visible"/>
      <el-table-column label="企业类型" align="center" key="enterpriseType" prop="enterpriseType"  v-if="columns[1].visible"/>
      <el-table-column label="关键词" align="center" key="keyword" prop="keyword"  v-if="columns[3].visible"/>
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
        <template slot-scope="scope" v-if="scope.row.enterpriseId !== 1">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['enterprise:manage:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['enterprise:manage:remove']"
          >删除</el-button>
        </template>


        <!-- 添加或修改用户配置对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
          <el-form ref="form" :model="form" :rules="rules" label-width="140px">
            <el-row>
              <el-col :span="12">
                <el-form-item label="企业名称" prop="enterpriseName">
                  <el-input v-model="form.enterpriseName" placeholder="请输入企业名称" maxlength="30" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="企业网站" prop="enterpriseUrl">
                  <el-input v-model="form.enterpriseUrl" placeholder="请输入企业网站" maxlength="30" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="企业类型">
                  <el-select v-model="form.enterpriseType" placeholder="企业类型">
                    <el-option
                      v-for="dict in enterpriseTypeOptions"
                      :key="dict.value"
                      :label="dict.label"
                      :value="dict.value"
                    ></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="企业状态">
                  <el-select v-model="form.status" placeholder="企业状态">
                    <el-option
                      v-for="dict in enterpriseStatusOptions"
                      :key="dict.value"
                      :label="dict.label"
                      :value="dict.value"
                    ></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="企业关键词" prop="enterpriseUrl">
                  <el-input v-model="form.keyword" placeholder="企业关键词" maxlength="30" />
                </el-form-item>
              </el-col>
            </el-row>
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
      @pagination="enterpriseList"
    />
  </div>





</template>

<script>
import {getEnterpriseList,addEnterprise,getEnterprise,updateEnterprise,changeEnterpriseStatus,deleteEnterprise} from "@/api/system/enterprise";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import Treeselect from "@riophae/vue-treeselect";
import {getToken} from "@/utils/auth";

export default {
  name: "User",
  dicts: ['sys_normal_disable', 'sys_user_sex'],
  components: {Treeselect},
  data() {
    return {
      // 文章列表数据
      enterprise: null,
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
        enterpriseId: '',
        enterpriseName: '',
        enterpriseUrl:'',
        enterpriseType:''
      },
      // 列信息
      columns: [
        { key: 0, label: `企业ID`, visible: true },
        { key: 1, label: `企业名称`, visible: true },
        { key: 2, label: `企业类型`, visible: true },
        { key: 3, label: `企业网址`, visible: true },
        { key: 4, label: `状态`, visible: true },
      ],
      enterpriseTypeOptions:[{
        value: '4',
        label: '普通企业'
      },{
        value: '5',
        label: '自营站'
      },{
        value: '6',
        label: '代理商'
      }],
      enterpriseStatusOptions:[{
        value: '0',
        label: '正常'
      },{
        value: '1',
        label: '停用'
      }],
      // 表单校验
      rules: {
        enterpriseName: [
          { required: true, message: "企业名称不能为空", trigger: "blur" }
        ],
        enterpriseUrl: [
          { required: true, message: "企业网站不能为空", trigger: "blur" }
        ],
        keyword: [
          { required: true, message: "企业关键词不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.enterpriseList();
  },
  methods: {
    enterpriseList(){
      this.loading = true;
      // 获取文章数据
      this.loading = true;
      getEnterpriseList(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
          this.enterprise = response.rows;
          this.total = response.total;
          this.loading = false;
        }
      );
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.enterpriseList();
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
      this.enterpriseList();
    },
    // 企业状态修改
    handleStatusChange(row) {
      let text = row.status === "0" ? "启用" : "停用";
      this.$modal.confirm('确认要"  ' + text + '' + '  "企业吗？').then(function() {
        return changeEnterpriseStatus(row.enterpriseId, row.status);
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
        enterpriseId: undefined,
        enterpriseName: undefined,
        enterpriseUrl: undefined,
        enterpriseType: undefined,
        status: undefined,
        delFlag:undefined
      };
      this.resetForm("form");
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加企业";
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
          if (this.form.enterpriseId != undefined) {
            // 修改企业
            updateEnterprise(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.enterpriseList();
            });
          } else {
            // 新增企业
            addEnterprise(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.enterpriseList();
            });
          }
        }
      });
    },
    handleSelectionChange(){},
    handleEdit(index, row) {
      // console.log(index, row);
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const enterpriseId = row.enterpriseId || this.ids;
      getEnterprise(enterpriseId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改企业";
        // this.form.password = "";
      });
    },

    /** 删除按钮操作 */
    handleDelete(row) {
      const enterpriseIds = row.enterpriseId || this.ids;
      this.$modal.confirm('是否确认删除用户编号为"' + enterpriseIds + '"的数据项？').then(function() {
        return deleteEnterprise(enterpriseIds);
      }).then(() => {
        this.enterpriseList();
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
