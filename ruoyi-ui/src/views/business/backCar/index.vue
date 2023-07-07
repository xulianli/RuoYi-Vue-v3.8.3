<template>
  <div class="app-container">
    <el-card style="margin-bottom: 10px">
      <el-form :model="queryParams" :rules="rules" ref="queryForm" :inline="true" label-width="100px">
        <el-form-item label="出租单号" prop="rentSn">
          <el-input
            v-model="queryParams.rentSn"
            placeholder="请输入出租单号"
            clearable
            size="small"
            style="width: 300px"
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <div v-show="showBackCar">
      <el-card>
        <el-form ref="elForm" :model="form"  size="medium" label-width="100px">
          <el-row :gutter="10">
            <el-col :span="8">
              <el-form-item label="出租单号" prop="rentSn">
                <el-input v-model="form.rentSn" readonly placeholder="请输入出租单号" clearable :style="{width: '100%'}">
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="检查单号" prop="checkSn">
                <el-input v-model="form.checkSn" readonly placeholder="请输入检查单号" clearable :style="{width: '100%'}">
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="检查时间" prop="checkDate">
                <el-date-picker v-model="form.checkDate" placeholder="请选择检查时间"  clearable :style="{width: '100%'}">
                </el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="10">
            <el-col :span="8">
              <el-form-item label="操作人" prop="opername">
                <el-input v-model="form.opername" placeholder="请输入操作人" readonly clearable :style="{width: '100%'}">
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="存在问题" prop="problem">
                <el-input v-model="form.problem" placeholder="请输入存在问题" clearable :style="{width: '100%'}">
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="赔付金额" prop="payMoney">
                <el-input-number v-model="form.payMoney" placeholder="请输入赔付金额" clearable :style="{width: '100%'}">
                </el-input-number>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="10">
            <el-form-item label="问题描述" prop="checkDesc">
              <el-input v-model="form.checkDesc" type="textarea" placeholder="请输入问题描述"
                        :autosize="{minRows: 4, maxRows: 4}" :style="{width: '100%'}"></el-input>
            </el-form-item>
          </el-row>
          <el-form-item size="large" style="text-align: center">
            <el-button type="primary" @click="submitForm">提交</el-button>
            <el-button @click="resetForm">重置</el-button>
          </el-form-item>
        </el-form>
      </el-card>

      <el-card style="margin-top: 10px">
        <el-row :gutter="10">
          <el-col :span="8">
            <el-card>
              <div slot="header" class="clearfix">
                <span>客户数据</span>
              </div>
              <div>客户名称：{{customer.customerName}}</div>
              <div>身份证号：{{customer.identity}}</div>
              <div>客户性别：{{customer.sex==1?"男":"女"}}</div>
              <div>客户地址：{{customer.address}}</div>
              <div>客户电话：{{customer.phone}}</div>
              <div>所属区域：{{customer.deptName}}</div>
              <div>客户职位：{{customer.job}}</div>
            </el-card>
          </el-col>
          <el-col :span="8">
            <el-card>
              <div slot="header" class="clearfix">
                <span>车辆数据</span>
              </div>
              <div>车辆号牌：{{car.carNumber}}</div>
              <div>车架号：{{car.frameNumber}}</div>
              <div>车辆品牌：{{car.brand}}</div>
              <div>车辆颜色：{{car.color}}</div>
              <div>描述：{{car.description}}</div>
              <div>所属区域：{{car.deptName}}</div>
              <div>
                <el-carousel v-if="srcList.length>0" indicator-position="outside">
                  <el-carousel-item v-for="item in srcList" :key="item">
                    <div style="text-align: center"><img :src="item"></div>
                  </el-carousel-item>
                </el-carousel>
              </div>
            </el-card>
          </el-col>
          <el-col :span="8">
            <el-card>
              <div slot="header" class="clearfix">
                <span>出租单数据</span>
              </div>
              <div>出租单号：{{rent.rentSn}}</div>
              <div>开始时间：{{rent.beginDate}}</div>
              <div>还车时间：{{rent.returnDate}}</div>
              <div>出租价格：{{rent.rentprice}}</div>
              <div>操作员：{{rent.opername}}</div>
            </el-card>
          </el-col>
        </el-row>
      </el-card>
    </div>
  </div>
</template>

<script>
import {getRentDataByRentId} from "@/api/business/backCar";
// import {queryCarImagesByCarNumber} from "@/api/business/attachment";

export default {
  name: "backCar",
  data(){
    return{
      queryParams:{
        rentSn:undefined
      },
      //表单数据
      form:{
        rentSn: undefined,
        checkSn: undefined,
        checkDate: new Date(),
        problem: undefined,
        payMoney: 0,
        opername: undefined,
        checkDesc: undefined,
      },
      rules:{
        rentSn: [
          { required: true, message: "出租单号不能为空", trigger: "blur" }
        ],
      },
      //客户数据
      customer:{},
      //车辆数据
      car:{},
      //出租单数据
      rent:{},
      //车辆图片的显示
      srcList:[],
      //是否显示搜索框架下面的内容
      showBackCar:false
    }
  },
  methods:{
    handleQuery(){
      this.$refs.queryForm.validate(valid=>{
        if(valid){
          getRentDataByRentId(this.queryParams.rentSn).then(res=>{
            this.form=res.data.check;
            this.customer=res.data.customer
            this.car=res.data.car;
            this.rent=res.data.rent;
            //查询车辆图片
            // queryCarImagesByCarNumber(this.car.carNumber).then(res=>{
            //   if(res.data.length>0){
            //     //对路径进行分割
            //     this.srcList=res.data.split(",");
            //     //给每个路径前加前缀
            //     for (let i = 0; i <this.srcList.length; i++) {
            //       this.srcList[i]=process.env.VUE_APP_BASE_API+this.srcList[i];
            //     }
            //   }
            // })
            this.showBackCar=true;
          }).catch(error=>{
            this.showBackCar=false;
          })
        }
      })
    },
    //保存检查单信息
    submitForm(){
      this.$refs.dataForm.validate(valid=>{
        if (valid){
          addCheck(this.form).then(res=>{
            this.$modal.msgSuccess("还车成功");
            this.showBackCar=false;
          }).catch(error=>{
          })
        }
      })
    },
    //重置检查单信息
    resetForm(){
      this.form.checkDate=undefined;
      this.form.problem=undefined;
      this.form.payMony=undefined;
      this.form.checkDesc=undefined;
    }
  }
}
</script>

<style scoped>

</style>
