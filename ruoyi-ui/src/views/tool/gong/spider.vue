<template>
  <div class="form-container">
    <h2>添加链接</h2>
    <form @submit.prevent="submitForm">
      <!-- URL输入框 -->
      <div class="form-group">
        <label for="url" class="form-label">URL:</label>
        <input type="text" v-model="url" placeholder="填写要提取URL的页面地址，http://" id="url" />
      </div>

      <!-- 描述文本域 -->
      <div class="form-group">
        <label for="notes" class="form-label">描述:</label>
        <textarea
          v-model="notes"
          id="notes"
          placeholder="请将URL放在此处&#10;想让哪个页面收录，提交哪个&#10;一行一条&#10;实际也可以提交10000条/次"
          @input="updateQuantity">
        </textarea>
      </div>

      <!-- 数量显示，自动计算 -->
      <div class="form-group">
        <label for="quantity" class="form-label">数量:</label>
        <input type="number" v-model="quantity" readonly />
      </div>

      <!-- 蜘蛛池下拉菜单 -->
      <div class="form-group">
        <label for="spider-pool" class="form-label">蜘蛛池:</label>
        <select v-model="spiderPool" id="spider-pool" @change="calculateDeduction">
          <option value="baidu-x10">百度 x10</option>
          <!-- 根据需要添加其他选项 -->
        </select>
      </div>

      <!-- 301跳转下拉菜单 -->
      <div class="form-group">
        <label for="redirect" class="form-label">301跳转:</label>
        <select v-model="redirect" id="redirect" @change="calculateDeduction">
          <option value="no">否</option>
          <option value="yes">是</option>
        </select>
      </div>

      <!-- 当前积分 -->
      <div class="form-group">
        <label class="form-label">当前积分:</label>
        <span>{{ points }}</span>
      </div>

      <!-- 扣除积分显示 -->
      <div class="form-group">
        <label class="form-label">扣除积分:</label>
        <span>{{ quantity }} * {{ spiderMultiplier }} * {{ redirectMultiplier }} = {{ deductedPoints }}</span>
      </div>

      <!-- 提交按钮 -->
      <button type="submit">提交</button>
    </form>
  </div>
</template>

<script>
import { insertdata } from '@/api/system/spider/url' // 确保导入正确的路径
import axios from 'axios'

export default {
  data() {
    return {
      url: '',
      notes: '',
      quantity: 0,  // 描述的行数
      spiderPool: 'baidu-x10', // 蜘蛛池默认选择百度 x10
      redirect: 'no',  // 301跳转默认选择否
      points: 600,  // 当前积分
      deductedPoints: 0,  // 扣除的积分
      spiderMultiplier: 10,  // 蜘蛛池倍数
      redirectMultiplier: 1  // 301跳转倍数
    };
  },
  methods: {
    // 每次输入描述时自动更新数量
    updateQuantity() {
      this.quantity = (this.notes.match(/http:\/\//g) || []).length;
      this.calculateDeduction();  // 每次更新行数后重新计算扣除积分
    },
    // 计算扣除积分
    calculateDeduction() {
      this.spiderMultiplier = parseInt(this.spiderPool.split('-x')[1]) || 1;
      this.redirectMultiplier = this.redirect === 'yes' ? 10 : 1;
      this.deductedPoints = this.quantity * this.spiderMultiplier * this.redirectMultiplier;
    },
    // 提交表单
    async submitForm() {
      const data = {
        url: this.url,
        notes: this.notes,
        quantity: this.quantity,
        spiderPool: this.spiderPool,
        redirect: this.redirect,
        deductedPoints: this.deductedPoints
      };
      
      try {
        // 发送GET请求到后台insert接口
        const response = await insertdata(data); // 调用你提供的 insertdata 函数
        console.log('Form Submitted Successfully:', response.data);
        // 可以在这里添加成功提交后的处理逻辑，比如重置表单或显示成功消息
      } catch (error) {
        console.error('Error submitting form:', error);
        // 处理提交错误，比如显示错误消息
      }
    },
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
