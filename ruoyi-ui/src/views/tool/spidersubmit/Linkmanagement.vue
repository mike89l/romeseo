<template>
  <div class="page-container">
    <!-- 顶部类型选择按钮 -->
    <div class="filter-buttons">
      <button v-for="(type, index) in types" 
              :key="index" 
              :class="{ active: selectedType === type }"
              @click="selectType(type)">
        {{ type }}
      </button>
    </div>

    <!-- 链接管理表格 -->
    <div class="table-container">
      <table class="link-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>链接</th>
            <th>链接数</th>
            <th>积分</th>
            <th>蜘蛛池</th>
            <th>301强引</th>
            <th>包月支付</th>
            <th>添加时间</th>
            <th>状态</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(link, index) in paginatedLinks" :key="index">
            <td>{{ link.id }}</td>
            <td><button @click="viewLink(link)">查看</button></td>
            <td>{{ link.linkCount }}</td>
            <td>{{ link.points }}</td>
            <td>{{ link.spiderPool }}</td>
            <td>{{ link.redirect }}</td>
            <td>{{ link.monthlyPayment }}</td>
            <td>{{ link.addTime }}</td>
            <td>{{ link.status }}</td>
            <td>
              <button @click="addRemark(link)">添加备注</button>
              <button @click="deleteLink(link)">删除</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- 分页控制 -->
    <div class="pagination">
      <button @click="previousPage" :disabled="currentPage === 1">上一页</button>
      <span>{{ currentPage }} / {{ totalPages }}</span>
      <button @click="nextPage" :disabled="currentPage === totalPages">下一页</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      types: ["全部", "百度", "谷歌", "搜狗", "神马", "Bing", "360"],
      selectedType: "全部",
      currentPage: 1,
      itemsPerPage: 10,
      links: [
        // 示例数据
        { id: 22209, linkCount: 1, points: 10, spiderPool: "谷歌", redirect: "否", monthlyPayment: "否", addTime: "2024-09-26 19:52:30", status: "运行中" },
        { id: 18743, linkCount: 24, points: 2160, spiderPool: "谷歌", redirect: "否", monthlyPayment: "否", addTime: "2024-05-29 22:40:40", status: "结束" },
        // ...更多数据
      ]
    };
  },
  computed: {
    totalPages() {
      return Math.ceil(this.filteredLinks.length / this.itemsPerPage);
    },
    paginatedLinks() {
      const start = (this.currentPage - 1) * this.itemsPerPage;
      const end = start + this.itemsPerPage;
      return this.filteredLinks.slice(start, end);
    },
    filteredLinks() {
      if (this.selectedType === "全部") {
        return this.links;
      }
      return this.links.filter(link => link.spiderPool.includes(this.selectedType));
    }
  },
  methods: {
    selectType(type) {
      this.selectedType = type;
      this.currentPage = 1; // 重置到第一页
    },
    viewLink(link) {
      alert(`查看链接: ${link.id}`);
    },
    addRemark(link) {
      alert(`添加备注: ${link.id}`);
    },
    deleteLink(link) {
      const confirmed = confirm(`确定要删除ID为 ${link.id} 的链接吗？`);
      if (confirmed) {
        this.links = this.links.filter(l => l.id !== link.id);
      }
    },
    nextPage() {
      if (this.currentPage < this.totalPages) {
        this.currentPage++;
      }
    },
    previousPage() {
      if (this.currentPage > 1) {
        this.currentPage--;
      }
    }
  }
};
</script>

<style scoped>
.page-container {
  width: 1000px;
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

.filter-buttons button.active {
  background-color: #007bff;
  color: white;
}

.table-container {
  width: 100%;
  margin-bottom: 20px;
}

.link-table {
  width: 100%;
  border-collapse: collapse;
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
}

.pagination button {
  padding: 5px 10px;
  cursor: pointer;
}

.pagination button:disabled {
  cursor: not-allowed;
  opacity: 0.5;
}
</style>
