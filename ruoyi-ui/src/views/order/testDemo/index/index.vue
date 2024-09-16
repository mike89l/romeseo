<template>
  <div class="container">

<!--    <el-button type="text" @click="dialogVisible = true">点击打开 Dialog</el-button>-->

    <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="30%">
      <el-form class="form_define" ref="form" label-position="top" :model="form" :rules="rules" label-width="80px">
        <el-row :gutter="20">
          <el-col :span="6">
            <el-form-item label="客户公司名" prop="nickName">
              <el-input v-model="form.nickName" placeholder="请输入用户昵称" maxlength="30" />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="客户qq号" prop="nickName">
              <el-input v-model="form.qq" placeholder="请输入用户昵称" maxlength="30" />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="用户昵称" prop="nickName">
              <el-input v-model="form.nc" placeholder="请输入用户昵称" maxlength="30" />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="合同PDF上传" prop="nickName">
              <el-upload
                :fileList="form.fileList"
                class="avatar-uploader"
                action="https://jsonplaceholder.typicode.com/posts/"
                :show-file-list="false"
                :on-success="handleAvatarSuccess"
                :before-upload="beforeAvatarUpload">
                <img v-if="imageUrl" :src="imageUrl" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
              </el-upload>
            </el-form-item>
          </el-col>
        </el-row>
        <!--      <el-row>-->
        <!--        <el-col :span="12">-->
        <!--          <el-form-item label="手机号码" prop="phonenumber">-->
        <!--            <el-input v-model="form.phonenumber" placeholder="请输入手机号码" maxlength="11" />-->
        <!--          </el-form-item>-->
        <!--        </el-col>-->
        <!--        <el-col :span="12">-->
        <!--          <el-form-item label="邮箱" prop="email">-->
        <!--            <el-input v-model="form.email" placeholder="请输入邮箱" maxlength="50" />-->
        <!--          </el-form-item>-->
        <!--        </el-col>-->
        <!--      </el-row>-->
        <!--      <el-row>-->
        <!--        <el-col :span="12">-->
        <!--          <el-form-item v-if="form.userId == undefined" label="用户名称" prop="userName">-->
        <!--            <el-input v-model="form.userName" placeholder="请输入用户名称" maxlength="30" />-->
        <!--          </el-form-item>-->
        <!--        </el-col>-->
        <!--        <el-col :span="12">-->
        <!--          <el-form-item v-if="form.userId == undefined" label="用户密码" prop="password">-->
        <!--            <el-input v-model="form.password" placeholder="请输入用户密码" type="password" maxlength="20" show-password/>-->
        <!--          </el-form-item>-->
        <!--        </el-col>-->
        <!--      </el-row>-->
        <!--      <el-row>-->
        <!--        <el-col :span="12">-->
        <!--          <el-form-item label="用户性别">-->
        <!--            <el-select v-model="form.sex" placeholder="请选择性别">-->
        <!--              <el-option-->
        <!--                v-for="dict in dict.type.sys_user_sex"-->
        <!--                :key="dict.value"-->
        <!--                :label="dict.label"-->
        <!--                :value="dict.value"-->
        <!--              ></el-option>-->
        <!--            </el-select>-->
        <!--          </el-form-item>-->
        <!--        </el-col>-->
        <!--        <el-col :span="12">-->
        <!--          <el-form-item label="状态">-->
        <!--            <el-radio-group v-model="form.status">-->
        <!--              <el-radio-->
        <!--                v-for="dict in dict.type.sys_normal_disable"-->
        <!--                :key="dict.value"-->
        <!--                :label="dict.value"-->
        <!--              >{{dict.label}}</el-radio>-->
        <!--            </el-radio-group>-->
        <!--          </el-form-item>-->
        <!--        </el-col>-->
        <!--      </el-row>-->
        <!--      <el-row>-->
        <!--        <el-col :span="12">-->
        <!--          <el-form-item label="岗位">-->
        <!--            <el-select v-model="form.postIds" multiple placeholder="请选择岗位">-->
        <!--              <el-option-->
        <!--                v-for="item in postOptions"-->
        <!--                :key="item.postId"-->
        <!--                :label="item.postName"-->
        <!--                :value="item.postId"-->
        <!--                :disabled="item.status == 1"-->
        <!--              ></el-option>-->
        <!--            </el-select>-->
        <!--          </el-form-item>-->
        <!--        </el-col>-->
        <!--        <el-col :span="12">-->
        <!--          <el-form-item label="角色">-->
        <!--            <el-select v-model="form.roleIds" multiple placeholder="请选择角色">-->
        <!--              <el-option-->
        <!--                v-for="item in roleOptions"-->
        <!--                :key="item.roleId"-->
        <!--                :label="item.roleName"-->
        <!--                :value="item.roleId"-->
        <!--                :disabled="item.status == 1"-->
        <!--              ></el-option>-->
        <!--            </el-select>-->
        <!--          </el-form-item>-->
        <!--        </el-col>-->
        <!--      </el-row>-->
        <!--      <el-row>-->
        <!--        <el-col :span="24">-->
        <!--          <el-form-item label="备注">-->
        <!--            <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"></el-input>-->
        <!--          </el-form-item>-->
        <!--        </el-col>-->
        <!--      </el-row>-->
      </el-form>

      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
  </span>
    </el-dialog>
    <el-table :data="userList" class="table_define">
    <!--    <el-table-column type="selection" width="50" align="center" />-->
    <el-table-column label="用户编号" align="center" key="userId" prop="userId" v-if="columns[0].visible" />
    <el-table-column label="用户名称" align="center" key="userName" prop="userName" v-if="columns[1].visible" :show-overflow-tooltip="true" />
    <el-table-column label="用户昵称" align="center" key="nickName" prop="nickName" v-if="columns[2].visible" :show-overflow-tooltip="true" />
    <el-table-column label="部门" align="center" key="deptName" prop="dept.deptName" v-if="columns[3].visible" :show-overflow-tooltip="true" />
    <el-table-column label="手机号码" align="center" key="phonenumber" prop="phonenumber" v-if="columns[4].visible" width="120" />

    <el-table-column
      label="操作"
      align="center"
      width="160"
      class-name="small-padding fixed-width"
    >
      <template slot-scope="scope" v-if="scope.row.userId !== 1">
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
        <!--        <el-dropdown size="mini" @command="(command) => handleCommand(command, scope.row)" v-hasPermi="['system:user:resetPwd', 'system:user:edit']">-->
        <!--          <el-button size="mini" type="text" icon="el-icon-d-arrow-right">更多</el-button>-->
        <!--          <el-dropdown-menu slot="dropdown">-->
        <!--            <el-dropdown-item command="handleResetPwd" icon="el-icon-key"-->
        <!--                              v-hasPermi="['system:user:resetPwd']">重置密码</el-dropdown-item>-->
        <!--            <el-dropdown-item command="handleAuthRole" icon="el-icon-circle-check"-->
        <!--                              v-hasPermi="['system:user:edit']">分配角色</el-dropdown-item>-->
        <!--          </el-dropdown-menu>-->
        <!--        </el-dropdown>-->
      </template>
    </el-table-column>
  </el-table>


</div>
</template>

<script>
import {delUser, getUser, listUser} from "@/api/system/user";
import Treeselect from "@riophae/vue-treeselect";

export default {
  components: {Treeselect},
  data() {
    return {
      userList: [],
      dialogVisible: false,
      // 列信息
      columns: [
        { key: 0, label: `用户编号`, visible: true },
        { key: 1, label: `用户名称`, visible: true },
        { key: 2, label: `用户昵称`, visible: true },
        { key: 3, label: `部门`, visible: true },
        { key: 4, label: `手机号码`, visible: true },
        { key: 5, label: `状态`, visible: true },
        { key: 6, label: `创建时间`, visible: true }
      ],
      form: {
        nickName: '1',
        nc: 2,
        qq: 10086,
        fileList: []
      },
      rules: [],
    }

  },
  created() {
    this.getList()
  },
  methods: {

    /** 查询用户列表 */
    getList() {
      listUser().then(response => {
          this.userList = response.rows;
        }
      );
    },
    // 多选框选中数据
    // handleSelectionChange(selection) {
    //   this.ids = selection.map(item => item.userId);
    //   this.single = selection.length != 1;
    //   this.multiple = !selection.length;
    // },

    /** 修改按钮操作 */
    handleUpdate(row) {
      console.log('点击了修改')
      this.dialogVisible = true;
    },

    /** 删除按钮操作 */
    handleDelete(row) {
      const userIds = row.userId || this.ids;
      this.$modal.confirm('是否确认删除用户编号为"' + userIds + '"的数据项？').then(function() {
        return delUser(userIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
  }
}
</script>
<style scoped>
.container {
  padding: 20px;
}

.form_define {
  background: red;
}
</style>
