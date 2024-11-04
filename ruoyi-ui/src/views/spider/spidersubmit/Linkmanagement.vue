<template>
  <div class="page-container">
    <h1>链接管理</h1>
    <div class="filter-buttons">
      <button @click="fetchLinks">刷新数据</button>
    </div>

    <!-- 表格 -->
    <table class="link-table">
      <thead>
        <tr>
          <th>ID</th>
          <th>链接列表</th>
          <th>链接数量</th>
          <th>优化池</th>
          <th>是否强引</th>
          <th>添加时间</th>
          <th>状态</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="link in links" :key="link.id">
          <td>{{ link.id }}</td>
          <td>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="viewLink(link)"
              v-hasPermi="['article:manage:edit']"
            >查看</el-button>
          </td>
          <td>{{ link.urlnum }}</td>
          <td>{{ getSubmitTypeName(link.submitType) }}</td>
          <td>{{ link.forcedBootState === 0 ? '是' : '否' }}</td>
          <td>{{ link.addTime || new Date().toLocaleString() }}</td>
          <td>
            <span :class="{'orange-background': link.submitStutsa === 0, 'blue-background': link.submitStutsa === 1}">
              {{ link.submitStutsa === 0 ? '优化中' : '优化结束' }}
            </span>
          </td>
          <td>
            <button @click="openRemarkModal(link)">添加备注</button>
            <button @click="deleteLink(link)">删除</button>
          </td>
        </tr>
      </tbody>
    </table>

    <!-- 分页控件移到这里 -->
    <div class="pagination">
      <button @click="previousPage" :disabled="currentPage === 1">上一页</button>
      <span>{{ currentPage }} / {{ totalPages }}</span>
      <input type="number" v-model="currentPage" min="1" :max="totalPages" @change="goToPage" aria-label="跳转到第几页">
      <button @click="nextPage" :disabled="currentPage === totalPages">下一页</button>
    </div>

    <!-- 用于展示URL的弹窗 -->
<div v-if="showModal" class="modal-overlay">
  <div class="modal-content">
    <h2>链接详情</h2>
    <div v-for="(url, index) in formattedUrls" :key="index">
      <p>{{ url }}</p>
    </div>
    <button @click="closeModal">关闭</button>
  </div>
</div>


    <!-- 用于添加备注的弹窗 -->
    <div v-if="showRemarkModal" class="modal-overlay">
      <div class="modal-content">
        <h2>添加备注</h2>
        <input v-model="remark" placeholder="请输入备注内容" />
        <button @click="submitRemark">提交</button>
        <button @click="closeRemarkModal">关闭</button>
      </div>
    </div>
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
      showRemarkModal: false, // 控制备注弹窗显示与否
      selectedLink: null,
      linkUrl: '',
      formattedUrls: [], // 用于存储在弹窗中显示的格式化URL
      remark: '' // 储存输入的备注内容
    };
  },
  mounted() {
    this.fetchLinks();
    this.fetchUsers();
  },
  methods: {

    async fetchUsers() {
      try {
        const response = await info(); // 调用获取用户信息的接口
        console.log('API响应:', response); // 日志输出完整响应
        if (response && response.code === 200) {
      this.users = response.data;
      console.log('用户信息:', this.users);
    } else {
      throw new Error('获取用户信息失败: ' + (response ? response.message : '无响应'));
    }
  } catch (error) {
    console.error('获取用户信息时出错:', error);
  }
    },

    getSubmitTypeName(type) {
      const typeMap = {
        'baidu-x10': '百度',
        'google-x35': 'Google',
        // 可以继续添加其他映射
      };
      return typeMap[type] || type; // 返回对应的中文名称，如果没有映射则返回原值
    },

    async fetchLinks() {
      try {
        const response = await listStutas({ page: this.currentPage, size: this.pageSize });
        this.links = response.rows;
        console.log('链接数据:', response.rows);
        this.totalPages = Math.ceil(response.total / this.pageSize);
      } catch (error) {
        toast.error('获取链接数据失败');
        console.error(error);
      }
    },

    async viewLink(link) {
  this.selectedLink = link;
  try {
    console.log('选中链接:', link);
    const response = await getData(link.submitId);
    console.log('API 响应:', response);
    if (response && response.code === 200 && response.data && response.data.url) {
      // 按换行符或空格分割多个URL（如果返回多个URL）
      this.linkUrl = response.data.url; 
      this.formattedUrls = this.linkUrl.split(/\s+/); // 根据实际返回的URL格式调整此部分
      console.log('获取的URLs:', this.formattedUrls);
      this.showModal = true;
    } else {
      throw new Error('URL数据缺失');
    }
  } catch (error) {
    const errorMessage = (error && error.message) || '获取链接详情失败';
    toast.error(errorMessage);
    console.error('获取链接详情时出错:', error);
  }
},

    openRemarkModal(link) {
      this.selectedLink = link; // 存储当前链接
      this.remark = ''; // 清空之前的备注内容
      this.showRemarkModal = true; // 显示备注弹窗
    },

    async submitRemark() {
      if (!this.remark) {
        toast.error('备注内容不能为空');
        return;
      }

      try {
        const response = await updateStutas({ id: this.selectedLink.id, coment: this.remark });
        if (response && response.code === 200) {
          toast.success(`已添加备注: ${response.data.remark}`);
          this.showRemarkModal = false; // 关闭备注弹窗
          this.remark = ''; // 清空备注内容
          console.log('备注弹窗已关闭'); // 添加调试输出
        } else {
          throw new Error('添加备注失败');
        }
      } catch (error) {
        const errorMessage = (error && error.message) || '添加备注失败';
        toast.error(errorMessage);
        console.error('添加备注时出错:', error);
      }
    },

    closeModal() {
      this.showModal = false;
      this.selectedLink = null;
      this.linkUrl = '';
    },

    closeRemarkModal() {
      this.showRemarkModal = false;
      this.selectedLink = null;
      this.remark = ''; // 清空备注内容
    },

    addRemark(link) {
      toast.success(`添加备注: ${link.id}`);
    },

    async deleteLink(link) {
      const confirmed = confirm(`确定要删除ID为 ${link.id} 的链接吗？`);
      if (confirmed) {
        try {
          await delStutas(link.id);
          this.links = this.links.filter(l => l.id !== link.id);
          toast.success(`已删除链接: ${link.id}`);
        } catch (error) {
          toast.error(`删除链接失败: ${error.message}`);
        }
      }
    },

    previousPage() {
      if (this.currentPage > 1) {
        this.currentPage--
        this.fetchLinks();
      }
    },

    nextPage() {
      if (this.currentPage < this.totalPages) {
        this.currentPage++
        this.fetchLinks()
      }
    },

    goToPage() {
      this.currentPage = Math.max(1, Math.min(this.currentPage, this.totalPages));
      this.fetchLinks();
    },
  },
  watch: {
    showModal(newVal) {
      console.log('弹窗状态:', newVal);
    },
  }
};
</script>


<style scoped>
.page-container {
  width: 100%;
  margin: 0 auto;
}

.filter-buttons {
  display: flex;
  margin-bottom: 20px;
}

.filter-buttons button {
  margin-right: 10px;
  padding: 10px 20px;
  background-color: #f0f0f0;
  border: none;
  cursor: pointer;
}

.link-table {
  width: 100%;
  border-collapse: collapse;
  margin-bottom: 20px;
}

.link-table th, .link-table td {
  border: 1px solid #ddd;
  padding: 10px;
  text-align: center;
}

.pagination {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 20px;
}

.pagination button {
  padding: 5px 10px;
  cursor: pointer;
}

.pagination button:disabled {
  cursor: not-allowed;
  opacity: 0.5;
}

/* 弹窗样式 */
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 9999;
}

.modal-content {
  background: white;
  padding: 20px;
  border-radius: 8px;
  text-align: center;
}

/* 备注弹窗样式 */
.modal-content {
  background: white;
  padding: 20px;
  border-radius: 8px;
  text-align: center;
}

/* 背景颜色样式 */
.orange-background {
  background-color: orange; /* 橙色背景 */
  color: white; /* 文字颜色可以根据需要修改 */
  padding: 2px 5px; /* 背景颜色包裹文字 */
  border-radius: 4px; /* 圆角边框 */
}

.blue-background {
  background-color: lightblue; /* 蓝色背景 */
  color: black; /* 文字颜色可以根据需要修改 */
  padding: 2px 5px; /* 背景颜色包裹文字 */
  border-radius: 4px; /* 圆角边框 */
}
</style>
