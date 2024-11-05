<template>
  <div class="page-container">
    <h1>链接管理</h1>
    <div class="filter-buttons">
      <el-button @click="fetchLinks" size="medium" type="primary">刷新数据</el-button>
      <el-button @click="goToAddLinkPage" size="medium" type="success">添加链接</el-button>
    </div>

    <!-- 表格 -->
    <el-table :data="links" style="width: 100%" stripe>
      <el-table-column label="ID" prop="id" width="80"></el-table-column>
      <el-table-column label="链接列表">
        <template #default="{ row }">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="viewLink(row)"
            v-hasPermi="['spider:submit:list']"
          >查看</el-button>
        </template>
      </el-table-column>
      <el-table-column label="链接数量" prop="urlnum" width="100"></el-table-column>
      <el-table-column label="优化池" :formatter="getSubmitTypeName" width="100">
        <template #default="{ row }">
          {{ getSubmitTypeName(row.submitType) }}
        </template>
      </el-table-column>
      <el-table-column label="是否强引" width="120">
        <template #default="{ row }">
          {{ row.forcedBootState === "0" ? '是' : '否' }}
        </template>
      </el-table-column>
      <el-table-column label="添加时间" prop="ticktime"  width="180"></el-table-column>
      <el-table-column label="状态" width="120">
        <template #default="{ row }">
          <span :class="{'orange-background': row.submitStutsa === 0, 'blue-background': row.submitStutsa === 1}">
            {{ row.submitStutsa === 0 ? '优化中' : '优化结束' }}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="180">
        <template #default="{ row }">
          <el-button @click="openRemarkModal(row)" size="mini" type="primary">添加备注</el-button>
          <el-button @click="deleteLink(row)" size="mini" type="danger">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页控件 -->
    <el-pagination
      :current-page="currentPage"
      :page-size="pageSize"
      :total="totalPages * pageSize"
      @current-change="goToPage"
      layout="prev, pager, next"
      background
    ></el-pagination>

    <!-- 用于展示URL的弹窗 -->
    <el-dialog :visible.sync="showModal" title="链接详情">
      <div v-for="(url, index) in formattedUrls" :key="index">
        <p>{{ url }}</p>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeModal">关闭</el-button>
      </span>
    </el-dialog>

    <!-- 用于添加备注的弹窗 -->
    <el-dialog :visible.sync="showRemarkModal" title="添加备注">
      <el-input v-model="remark" placeholder="请输入备注内容" rows="4"></el-input>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeRemarkModal">关闭</el-button>
        <el-button type="primary" @click="submitRemark">提交</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { listStutas, getData, updateStutas, delStutas, info } from '@/api/system/spider/url'; // 确保正确引入API
import { toast } from 'vue-toastification'; // 提示通知库

export default {
  data() {
    return {
      users: [],
      links: [],
      currentPage: 1,
      totalPages: 0,
      pageSize: 10,
      showModal: false,
      showRemarkModal: false,
      selectedLink: null,
      linkUrl: '',
      formattedUrls: [],
      remark: ''
    };
  },
  mounted() {
    this.fetchLinks();
    this.fetchUsers();
  },
  methods: {
    // 导航到添加链接页面
    goToAddLinkPage() {
      this.$router.push('/spider');  // 路由跳转到添加链接页面
    },

    // 提交类型映射
    getSubmitTypeName(type) {
      const typeMap = {
        'baidu-x10': '百度',  // 映射 'baidu-x10' 为 '百度'
        'google-x35': 'Google' // 映射 'google-x35' 为 'Google'
      };
      return typeMap[type] || type; // 如果没有映射，返回原始值
    },

    // 获取用户信息
    async fetchUsers() {
      try {
        const response = await info();
        if (response && response.code === 200) {
          this.users = response.data;
        } else {
          throw new Error('获取用户信息失败');
        }
      } catch (error) {
        toast.error('获取用户信息失败');
      }
    },

    // 获取链接信息
    async fetchLinks() {
      try {
        const response = await listStutas({ page: this.currentPage, size: this.pageSize });
        this.links = response.rows;
        console.log('oioioio', this.links);
        this.totalPages = Math.ceil(response.total / this.pageSize);
      } catch (error) {
        toast.error('获取链接数据失败');
      }
    },

    // 查看链接详情
    async viewLink(link) {
      this.selectedLink = link;
      try {
        const response = await getData(link.submitId);
        if (response && response.code === 200 && response.data && response.data.url) {
          this.linkUrl = response.data.url;
          this.formattedUrls = this.linkUrl.split(/\s+/);
          this.showModal = true;
        } else {
          throw new Error('URL数据缺失');
        }
      } catch (error) {
        toast.error('获取链接详情失败');
      }
    },

    // 打开备注弹窗
    openRemarkModal(link) {
      this.selectedLink = link;
      this.remark = '';
      this.showRemarkModal = true;
    },

    // 提交备注
    async submitRemark() {
      if (!this.remark) {
        toast.error('备注内容不能为空');
        return;
      }

      try {
        const response = await updateStutas({ id: this.selectedLink.id, coment: this.remark });
        if (response && response.code === 200) {
          toast.success(`已添加备注: ${response.data.remark}`);
          this.showRemarkModal = false;
          this.remark = '';
        } else {
          throw new Error('添加备注失败');
        }
      } catch (error) {
        toast.error('添加备注失败');
      }
    },

    // 关闭URL弹窗
    closeModal() {
      this.showModal = false;
      this.selectedLink = null;
      this.linkUrl = '';
    },

    // 关闭备注弹窗
    closeRemarkModal() {
      this.showRemarkModal = false;
      this.selectedLink = null;
      this.remark = '';
    },

    // 删除链接
    async deleteLink(link) {
      const confirmed = confirm(`确定要删除ID为 ${link.id} 的链接吗？`);
      if (confirmed) {
        try {
          await delStutas(link.id);
          this.links = this.links.filter(l => l.id !== link.id);
          toast.success(`已删除链接: ${link.id}`);
        } catch (error) {
          toast.error('删除链接失败');
        }
      }
    },

    // 分页
    goToPage(page) {
      this.currentPage = page;
      this.fetchLinks();
    },

    // 格式化时间
  formatDate(row, column, cellValue) {
    if (cellValue) {
      // 将时间字符串转为 Date 对象
      const date = new Date(cellValue);
      // 格式化日期为 "yyyy-MM-dd HH:mm:ss" 格式
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, '0');
      const day = String(date.getDate()).padStart(2, '0');
      const hours = String(date.getHours()).padStart(2, '0');
      const minutes = String(date.getMinutes()).padStart(2, '0');
      const seconds = String(date.getSeconds()).padStart(2, '0');
      return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
    }
    return ''; // 如果没有日期，返回空
  }
  }
};
</script>


<style scoped>
/* 样式部分，根据需要调整 */
.orange-background {
  background-color: orange;
}

.blue-background {
  background-color: lightblue;
}
</style>
