<template>
  <div class="container">


    <el-form :model="article" :rules="rules" ref="article" label-width="100px" class="demo-article">
      <el-form-item label="文章标题" prop="title">
        <el-input v-model="article.artTitle"></el-input>
      </el-form-item>
      <el-form-item label="文章描述" prop="description">
        <el-input v-model="article.artDescription"></el-input>
      </el-form-item>
      <el-form-item label="文章内容" prop="content">
        <el-input type="textarea" v-model="article.artContent"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('article')">立即创建</el-button>
        <el-button @click="resetForm('article')">重置</el-button>
      </el-form-item>
    </el-form>




  </div>
</template>

<script>
import {addArticle} from "@/api/system/article";

export default {
  data() {
    return {
      article: {
        artTitle: '',
        artDescription: '',
        artContent: ''
      },
      rules: {
        artTitle: [
          { required: true, message: '请输入文章标题', trigger: 'blur' },
          { min: 3, max: 100, message: '长度在 3 到 100 个字符', trigger: 'blur' }
        ],
        artDescription: [
          { required: true, message: '请输入文章描述', trigger: 'change' }
        ],
        artContent: [
          { required: true, message: '请输入文章描述', trigger: 'change' }
        ],
      }
    };
  },
  methods: {
    submitForm(article) {
      this.$refs[article].validate((valid) => {
        if (valid) {
          addArticle(this.article).then(response => {
            this.$modal.msgSuccess("新增成功");
            this.open = false;
            this.getList();
          });
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(article) {
      this.$refs[article].resetFields();
    }
  }
}








</script>

<style lang="scss" scoped>
.container{
  transform: translate(-50%,-50%);
  position: absolute;
  top: 40%;
  left: 50%;
}
</style>
