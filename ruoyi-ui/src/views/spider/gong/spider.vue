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
        <select v-model="spiderPool" id="spider-pool" @change="calculateDeduction">
          <option value="baidu-x10">百度 x10</option>
          <option value="google-x35">Google x35</option>
        </select>
      </div>

      <div class="form-group">
        <label for="redirect" class="form-label">301跳转:</label>
        <select v-model="redirect" id="redirect" @change="calculateDeduction">
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
        <span>{{ quantity }} * {{ spiderMultiplier }} * {{ redirectMultiplier }} = {{ deductedPoints }}</span>
      </div>

      <button type="submit">提交</button>

      <!-- 提交状态反馈 -->
      <div v-if="submissionStatus" class="submission-status">{{ submissionStatus }}</div>
    </form>
  </div>
</template>

<script>
import { insertdata } from '@/api/system/spider/url';

export default {
  data() {
    return {
      notes: '',
      quantity: 0,
      spiderPool: 'baidu-x10',
      redirect: 'no',
      points: 600,
      deductedPoints: 0,
      spiderMultiplier: 10,
      redirectMultiplier: 1,
      submissionStatus: '' // 用于反馈提交状态
    };
  },
  methods: {
    updateQuantity() {
      // 统计输入中的 URL 数量
      this.quantity = (this.notes.match(/https?:\/\//g) || []).length;
      this.calculateDeduction();
    },
    calculateDeduction() {
      // 计算扣除积分
      this.spiderMultiplier = parseInt(this.spiderPool.split('-x')[1]) || 1;
      this.redirectMultiplier = this.redirect === 'yes' ? 10 : 1;
      this.deductedPoints = this.quantity * this.spiderMultiplier * this.redirectMultiplier;
    },
    async submitForm() {
      // 提交表单数据
      if (this.quantity === 0) {
        this.submissionStatus = '描述中没有有效链接';
        return;
      }

      const data = {
        id: 0, // 假设ID由服务器生成
        urlnum: this.quantity,
        url: this.notes,
        pool: this.spiderPool,
        forcedBootState: this.redirect === 'yes' ? 1 : 0, // "yes"为1，"no"为0
        integral: this.deductedPoints
      };

      try {
        const response = await insertdata(data);
        console.log('表单提交成功:', response.data);
        this.submissionStatus = '提交成功'; // 更新状态反馈
        // 重置表单
        this.resetForm();
      } catch (error) {
        console.error('提交表单时出错:', error);
        this.submissionStatus = '提交失败，请重试'; // 更新状态反馈
      }
    },
    resetForm() {
      // 重置表单字段
      this.notes = '';
      this.quantity = 0;
      this.spiderPool = 'baidu-x10';
      this.redirect = 'no';
      this.submissionStatus = '';
      this.deductedPoints = 0;
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
