<template>
  <div class="app-container">
    <el-form :model="queryParams" :rules="rules" ref="queryForm" :inline="true" label-width="100px">
      <el-form-item label="身份证号" prop="identity">
        <el-input
          v-model="queryParams.identity"
          placeholder="请输入身份证号"
          clearable
          size="small"
          style="width: 300px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-table v-loading="loading" v-show="showRentCar" :data="carList" border>
      <el-table-column label="车辆id" align="center" prop="id" />
      <el-table-column label="车牌号" align="center" prop="carNumber" />
      <el-table-column label="车架号" align="center" prop="frameNumber" />
      <el-table-column label="品牌" align="center" prop="brand" />
      <el-table-column label="类型" align="center" prop="type">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.car_type" :value="scope.row.type"/>
        </template>
      </el-table-column>
      <el-table-column label="颜色" align="center" prop="color" />
      <el-table-column label="出租价格" align="center" prop="rentprice" />
      <el-table-column label="出租押金" align="center" prop="deposit" />
      <el-table-column label="描述" align="center" prop="description" />
      <el-table-column label="出租状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.car_rent_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="门店名称" align="center" prop="deptName" />
      <el-table-column label="操作" align="center" width="200" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-printer"
            @click="handleRentCar(scope.row)"
            v-hasPermi="['business:car:remove']"
          >出租车辆</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleUpload(scope.row)"
            v-hasPermi="['business:car:remove']"
          >预览图片</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!--文件预览的弹出层-->
    <el-dialog :title="title" :visible.sync="openView" width="800px" append-to-body>
      <div>
        <el-carousel indicator-position="outside">
          <el-carousel-item v-for="item in srcList" :key="item">
            <div style="text-align: center"><img :src="item"></div>
          </el-carousel-item>
        </el-carousel>
      </div>
    </el-dialog>

    <!--汽车出租的弹出层-->
    <el-dialog :title="title" center :visible.sync="openRentCar" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="起租时间" prop="beginDate">
          <el-date-picker value-format="yyyy-MM-dd"  v-model="form.beginDate" placeholder="请选择起租时间" />
        </el-form-item>
        <el-form-item label="还车时间" prop="returnDate">
          <el-date-picker value-format="yyyy-MM-dd"   v-model="form.returnDate" placeholder="请选择还车时间" />
        </el-form-item>
        <el-form-item label="出租单号" prop="rentSn">
          <el-input v-model="form.rentSn" readonly placeholder="请输入出租单号" />
        </el-form-item>
        <el-form-item label="出租价格" prop="address">
          <el-input v-model="form.rentprice" placeholder="请输入地址" />
        </el-form-item>
        <el-form-item label="出租押金" prop="deposit">
          <el-input v-model="form.deposit" readonly placeholder="请输入出租押金" />
        </el-form-item>
        <el-form-item label="客户身份证号" prop="identity">
          <el-input v-model="form.identity" readonly placeholder="请输入客户身份证号" />
        </el-form-item>
        <el-form-item label="车牌号"  prop="carNumber">
          <el-input v-model="form.carNumber"  readonly placeholder="请输入车牌号" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCar} from "@/api/business/car";
import {listAllDept} from "@/api/business/dept";
// import {queryCarImagesByCarNumber} from "@/api/business/attachment";
import {getCustomerByIdentity} from "@/api/business/customer";
import {getRentSn,addRent} from "@/api/business/rent";
import {createRentSn,getCarImagesToShow,realDoRent} from "@/api/business/rentCar";


export default {
  name: "Car",
  dicts: ['car_type', 'car_rent_status'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 总条数
      total: 0,
      // 车辆表格数据
      carList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      //是否显示未出租的汽车列表
      showRentCar:false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        status:0,
        identity:null
      },
      // 表单参数
      form: {
        beginDate:new Date(),
        returnDate:null,
        rentSn:null,
        rentprice:null,
        deposit:null,
        identity:null,
        carNumber:null
      },
      // 表单校验
      rules: {
        identity: [
          { required: true, message: "身份证号不能为空", trigger: "blur" }
        ],
        rentDate: [
          { required: true, message: "还车时间不能为空", trigger: "blur" }
        ]
      },
      //存放部门数据
      deptList:[],
      //是否显示文件预览的弹出层
      openView:false,
      //图片列表
      srcList:[],
      //是否显示汽车出租的弹出层
      openRentCar:false
    };
  },
  created() {
    this.getList();
    this.getAllDept();

  },
  methods: {
    /** 查询车辆列表 */
    getList() {
      this.loading = true;
      listCar(this.queryParams).then(response => {
        this.carList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    //查询所有二级部门
    getAllDept(){
      listAllDept().then(res=>{
        this.deptList=res.data;
      })
    },
    // 取消按钮
    cancel() {
      this.open = false;
      // this.reset();
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.$refs["queryForm"].validate(valid =>{
        if(valid){
          //根据客户的身份证号查询客户数据
          getCustomerByIdentity(this.queryParams.identity).then(res=>{
            if(res.code==200){
              this.queryParams.pageNum = 1;
              this.showRentCar=true
              this.getList();
            }
          }).catch(error=>{
            this.showRentCar=false;
            this.total=0;
          })
        }
      })

    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.showRentCar=false;
      this.total=0
      // this.handleQuery();
    },
    //打开文件上传的弹出层
    handleUpload(row){
      this.title="【"+row.carNumber+"】车辆的图片"
      queryCarImagesByCarNumber(row.carNumber).then(res=>{
        console.log(res.data)
        if(res.data.length>0){
          //显示弹出层
          this.openView=true;
          //对路径进行分割
          this.srcList=res.data.split(",");
          //给每个路径前加前缀
          for (let i = 0; i <this.srcList.length; i++) {
            this.srcList[i]=process.env.VUE_APP_BASE_API+this.srcList[i];
          }
        }else{
          this.$modal.msgError("暂无图片预览");
        }
      })
    },
    //弹出租车的弹出层
    handleRentCar(row){
      this.openRentCar=true;
      this.title="出租车辆"
      this.form.rentprice=row.rentprice
      this.form.deposit=row.deposit
      this.form.identity=this.queryParams.identity
      this.form.carNumber=row.carNumber
      //请求后台接口，生成出租单号
      getRentSn().then(res=>{
        this.form.rentSn=res.data;
      })
    },
    //保存出租单数据
    submitForm(){
      this.$refs["form"].validate(valid => {
        if (valid) {
          addRent(this.form).then(res=>{
            this.$modal.msgSuccess("添加出租单成功，请到出租单管理里面查询详情");
            this.showRentCar=false;
            this.total=0
            this.openRentCar=false;//关闭弹出层
          }).catch(error=>{
            this.$modal.msgError("添加出租单失败，请核对数据后再操作");
          })
        }
      });
    }
  }
};
</script>
