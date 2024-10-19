<template>
  <div class="form-container">
    <h2>添加链接</h2>
    <form @submit.prevent="submitForm">
      <div class="form-group">
        <label for="url" class="form-label">URL:</label>
        <textarea
          v-model="notes"
          id="url"
          placeholder="请将URL放在此处&#10;想让哪个页面收录，提交哪个&#10;一行一条&#10;实际也可以提交10000条/次"
          @input="updateQuantity"
        ></textarea>
      </div>

      <div class="form-group">
        <label for="quantity" class="form-label">数量:</label>
        <input type="number" v-model="quantity" readonly />
      </div>

      <div class="form-group">
        <label for="spider-pool" class="form-label">蜘蛛池:</label>
        <select v-model="spiderPool" id="spider-pool">
          <option value="baidu-x10">百度 x10</option>
          <option value="google-x35">Google x35</option>
        </select>
      </div>

      <div class="form-group">
        <label for="redirect" class="form-label">301跳转:</label>
        <select v-model="redirect" id="redirect">
          <option value="no">否</option>
          <option value="yes">是</option>
        </select>
      </div>

      <div class="form-group">
        <label class="form-label">当前积分:</label>
        <span>{{ points }}</span>
      </div>

      <div class="form-group">
        <label class="form-label">扣除积分:</label>
        <span>{{ deductedPoints }}</span>
      </div>

      <button type="submit" :disabled="isSubmitting">提交</button>

      <!-- 提交状态反馈 -->
      <div v-if="submissionStatus" class="submission-status">{{ submissionStatus }}</div>
    </form>

    <ul>
      <li v-for="item in linkList" :key="item.id">{{ item.url }}</li>
    </ul>
  </div>
</template>

<script>
import { insertdata, listData, addStutas } from '@/api/system/spider/url';

export default {
  data() {
    return {
      notes: '',
      spiderPool: 'baidu-x10',
      redirect: 'no',
      points: 600,
      submissionStatus: '',
      isSubmitting: false,
      linkList: [] // 用于存储链接列表
    };
  },
  computed: {
    quantity() {
      return (this.notes.match(/https?:\/\/[^\s]+/g) || []).length;
    },
    spiderMultiplier() {
      return parseInt(this.spiderPool.split('-x')[1]) || 1;
    },
    redirectMultiplier() {
      return this.redirect === 'yes' ? 10 : 1;
    },
    deductedPoints() {
      return this.quantity * this.spiderMultiplier * this.redirectMultiplier;
    }
  },
  methods: {
    async submitForm() {
  // 验证数量
  if (this.quantity === 0) {
    this.submissionStatus = '描述中没有有效链接';
    return;
  }

  const data = {
    urlnum: this.quantity,
    url: this.notes,
    pool: this.spiderPool,
    forcedBootState: this.redirect === 'yes' ? 1 : 0,
    integral: this.deductedPoints
  };

  this.isSubmitting = true; // 开始提交状态
  try {
    // 1. 调用 insertdata 接口
    const insertResponse = await insertdata(data);
    console.log('表单提交成功:', insertResponse.data);
    this.submissionStatus = '提交成功';

    // 2. 调用 listData 接口获取链接列表
    const listResponse = await listData({});
    
    // 确保 listResponse.data 为一个数组
    this.linkList = Array.isArray(listResponse.rows) ? listResponse.rows : [];

    console.log('查询数据', listResponse.rows);
    
    
    // 确保列表中至少有一条链接
    if (this.linkList.length === 0) {
      this.submissionStatus = '没有可用链接';
      return;
    }

    // 3. 取最新一条链接
    const latestLink = this.linkList[this.linkList.length - 1]; // 获取最新链接
      console.log('-=-=-=-=-', latestLink);
      
    // 构造 addStutas 的请求参数
    const stutasData = {
      submitId: latestLink.id, // 使用 insertResponse 返回的 ID
      submitType: this.spiderPool, // 使用蜘蛛池作为类型
      submitStutsa: 0, // 默认值
      coment: '', // 这里可以添加备注信息，如果需要的话
      urlnum: this.quantity,
      forcedBootState: latestLink.forcedBootState,
      ticktime: new Date().toISOString()
    };
    console.log('asd000000',stutasData);
    
    // 调用 addStutas 接口
    await addStutas(stutasData);
    console.log('状态添加成功');

    // 重置表单
    this.resetForm();
  } catch (error) {
    console.error('提交表单时出错:', error);
    this.submissionStatus = '提交失败，请重试';
  } finally {
    this.isSubmitting = false; // 重置提交状态
  }
},

    resetForm() {
      this.notes = '';
      this.spiderPool = 'baidu-x10';
      this.redirect = 'no';
      this.submissionStatus = '';
      this.linkList = []; // 清空链接列表
    }
  },
};
</script>


<style scoped>
.form-container {
  width: 600px;
  margin: 0 auto;
}

.form-group {
  display: flex;
  align-items: center;
  margin-bottom: 15px;
}

.form-label {
  width: 100px; /* 调整标签的宽度以适应布局 */
  text-align: right;
  margin-right: 10px;
  color: #888; /* 字体颜色淡化 */
}

input,
textarea,
select {
  flex: 1; /* 让输入框和下拉菜单占据剩余的空间 */
  padding: 8px;
  margin-top: 5px;
  border-radius: 25px; /* 让输入框和下拉菜单呈现半圆角 */
  border: 1px solid #ccc;
}

textarea {
  height: 100px; /* 可以调整文本域的高度 */
}

button {
  padding: 10px 15px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 25px; /* 提交按钮也设为半圆角 */
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}
</style>
