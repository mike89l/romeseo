<template>
  <div class="page-container">
    <h1>链接管理</h1>
<!--    <div class="filter-buttons">-->
<!--      <el-button @click="fetchLinks" size="medium" type="primary">刷新数据</el-button>-->
<!--      <el-button @click="goToAddLinkPage" size="medium" type="success">添加链接</el-button>-->
<!--      <el-button @click="goToAddLinkPage" size="medium" type="success">导出</el-button>-->
<!--    </div>-->


    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="goToAddLinkPage"
          v-hasPermi="['enterprise:manage:add']"
        >新增</el-button>
      </el-col>

      <right-toolbar :showSearch.sync="showSearch" @queryTable="enterpriseList" :columns="columns"></right-toolbar>
    </el-row>


    <!-- 表格 -->
    <el-table :data="links" style="width: 100%" stripe>
      <el-table-column label="ID"  prop="id" width="120" align="center"></el-table-column>
      <el-table-column label="链接列表" align="center">
        <template #default="{ row }">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="viewLink(row)"
            v-hasPermi="['spider:submit:query']"
          >查看</el-button>
        </template>
      </el-table-column>
      <el-table-column label="链接数量" prop="urlnum" width="120" align="center"></el-table-column>
      <el-table-column label="优化池" :formatter="getSubmitTypeName" align="center" width="120">
        <template #default="{ row }">
          {{ getSubmitTypeName(row.submitType) }}
        </template>
      </el-table-column>
      <el-table-column label="是否强引" width="120">
        <template #default="{ row }">
          {{ row.forcedBootState === "1" ? '是' : '否' }}
        </template>
      </el-table-column>
      <el-table-column label="添加时间" prop="ticktime"  width="180" align="center"></el-table-column>
      <el-table-column label="状态" width="120" align="center">
        <template #default="{ row }">
          <span :class="{'orange-background': row.submitStutsa === 0, 'blue-background': row.submitStutsa === 1}">
            {{ row.submitStutsa === 0 ? '优化中' : '优化结束' }}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="180" align="center">
        <template #default="{ row }">
<!--          <el-button @click="openRemarkModal(row)" size="mini" type="primary">添加备注</el-button>-->
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

    async handleExport() {
  try {
    const allLinks = []; // 用于存储所有链接的数据

    // 1. 提取所有链接的 ID
    const linkIds = this.links.map(link => link.submitId);
    console.log('Extracted link IDs:', linkIds);

    // 2. 根据 ID 获取链接的详细信息
    for (const id of linkIds) {
      const response = await getData(id); // 调用查看链接的接口
      console.log('response', response);
      
      if (response && response.code === 200 && response.data && response.data.url) {
        const urls = response.data.url.split(/\s+/); // 假设返回的链接可能是以空格或换行分隔的多个 URL

        // 对每个 URL 创建一个链接对象，并重复 ID 和其他信息
        urls.forEach(url => {
          allLinks.push({
            // id: urls.length > 1 ? '' : response.data.id, // 如果有多个 URL，ID 只显示在第一个
            url: url,
            status: '优化中',
            pool: response.data.pool === 'baidu-x10' ? '百度' : '谷歌',
            // time: this.formatDate(null, null, response.data.ticktime)
          });
        });
      } else {
        console.error(`Link data for ID ${id} is missing or invalid.`);
      }
    }

    console.log('All link data for export:', allLinks);

    // 3. 转换数据为 CSV 格式
    const csvContent = this.convertToCSV(allLinks);
    console.log('CSV content to be exported:', csvContent);

    // 4. 导出 CSV
    const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });
    saveAs(blob, '链接数据.csv');
    toast.success('导出成功');
  } catch (error) {
    console.error('Error during export:', error);
    toast.error('导出失败');
  }
},



convertToCSV(data) {
  const header = ['链接列表', '状态','优化池'];
  const rows = data.map(item => [
    // item.id || '',  // 如果没有 ID，使用空字符串
    item.url || '',  // 如果没有 URL，使用空字符串
    item.status || '',  // 如果没有状态，使用空字符串
    // item.time || '',  // 如果没有时间，使用空字符串
    item.pool || ''
  ]);

  return [header, ...rows].map(row => row.join(',')).join('\n');
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
