<template>
    <div style="margin: 2%;">

      <el-form :label-position="labelPosition" 	label-width="100px" inline="true"  :model="formLabelAlign">
        <el-col span="6">
        <el-form-item label="客户名词">
          <el-input v-model="formLabelAlign.name" class="lab-width"></el-input>
        </el-form-item>
        </el-col>
        <el-col span="6">
        <el-form-item label="客户类型">
          <el-select v-model="formInline.region" placeholder="客户类型" class="lab-width">
            <el-option label="代理" value="shanghai"></el-option>
            <el-option label="合作方" value="beijing"></el-option>
            <el-option label="直营" value="beijing"></el-option>
          </el-select>
        </el-form-item>
        </el-col>
        <el-col span="6">
        <el-form-item label="客户联系方式">
          <el-input v-model="formLabelAlign.type" class="lab-width"></el-input>
        </el-form-item>
        </el-col>
        <el-col span="6">
        <el-form-item label="客服负责人">
          <el-input v-model="formLabelAlign.type" class="lab-width"></el-input>
        </el-form-item>
        </el-col>
        <el-col span="6">
          <el-form-item label="客服负责人详情">
            <el-input v-model="formLabelAlign.type" class="lab-width"></el-input>
          </el-form-item>
        </el-col>
        <el-col span="6">
          <el-form-item label="客服负责人联系方式">
            <el-input v-model="formLabelAlign.type" class="lab-width"></el-input>
          </el-form-item>
        </el-col>
        <el-col span="6">
          <el-form-item label="方案天数">
            <el-input v-model="formLabelAlign.type" class="lab-width"></el-input>
          </el-form-item>
        </el-col>
        <el-col span="6">
          <el-form-item label="订单类型">
            <el-select v-model="formInline.region" placeholder="订单类型" class="lab-width">
              <el-option label="代理" value="shanghai"></el-option>
              <el-option label="合作方" value="beijing"></el-option>
              <el-option label="直营" value="beijing"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col span="6">
          <el-form-item label="方案选择">
            <el-select v-model="formInline.region" placeholder="订单类型" class="lab-width">
              <el-option label="基础班" value="shanghai"></el-option>
              <el-option label="商务版" value="beijing"></el-option>
              <el-option label="豪华版" value="beijing"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col span="6">
          <el-form-item label="是否自建站">
            <el-select v-model="formInline.region" placeholder="建站类型" class="lab-width">
              <el-option label="是" value="shanghai"></el-option>
              <el-option label="否" value="beijing"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col span="6">
          <el-form-item label="业务员姓名">
            <el-input v-model="formLabelAlign.type" class="lab-width"></el-input>
          </el-form-item>
        </el-col>
        <el-col span="6">
          <el-form-item label="增值服务">
            <el-select  placeholder="增值服务" class="lab-width">
              <el-option label="快排" value="shanghai"></el-option>
              <el-option label="水军" value="beijing"></el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col span="24">
          <el-form-item label="增值服务备备注" >
            <el-input  style="width: 1000px"></el-input>
          </el-form-item>
        </el-col>

        <el-col span="6">
          <el-form-item label="方案总金额">
            <el-input v-model="formLabelAlign.type" class="lab-width"></el-input>
          </el-form-item>
        </el-col>

        <el-col span="6">
          <el-form-item label="支付类型">
            <el-select  placeholder="增值服务" class="lab-width">
              <el-option label="微信" value="shanghai"></el-option>
              <el-option label="银行卡" value="beijing"></el-option>
              <el-option label="USDT" value="beijing"></el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col span="24">
          <el-form-item label="合同上传">
            <el-input v-model="formLabelAlign.type" class="lab-width"></el-input>
          </el-form-item>
        </el-col>



      </el-form>


  </div>
</template>

<script>
  export default {
    data() {
      return {
        labelPosition: 'top',
        formLabelAlign: {
          name: '',
          region: '',
          type: ''
        },
        formInline: {
          user: '',
          region: ''
        }
      };
    }
  }
</script>
<style scoped lang="scss">
  .lab-width{
    //width: 100%;
  }

</style>
