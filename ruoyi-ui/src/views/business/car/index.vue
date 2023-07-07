<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="车牌号" prop="carNumber">
        <el-input
          v-model="queryParams.carNumber"
          placeholder="请输入车牌号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车架号" prop="frameNumber">
        <el-input
          v-model="queryParams.frameNumber"
          placeholder="请输入车架号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="品牌" prop="brand">
        <el-input
          v-model="queryParams.brand"
          placeholder="请输入品牌"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择类型" clearable size="small">
          <el-option
            v-for="dict in dict.type.car_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="颜色" prop="color">
        <el-input
          v-model="queryParams.color"
          placeholder="请输入颜色"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>


      <el-form-item label="出租价格" prop="rentprice" >
        <el-input
          v-model="queryParams.minRentPrice"
          placeholder="请输入最低价格"
          style="width: 200px;"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        /> --
        <el-input
          v-model="queryParams.maxRentPrice"
          placeholder="请输入最高价格"
          clearable
          style="width: 200px;"
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>




      <el-form-item label="出租状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择出租状态" clearable size="small">
          <el-option
            v-for="dict in dict.type.car_rent_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="车辆所属门店" prop="deptId">
        <el-select v-model="queryParams.deptId" placeholder="请选择门店" clearable size="small">
          <el-option
            v-for="dept in depts"
            :key="dept.deptId"
            :label="dept.deptName"
            :value="dept.deptId"
          />
        </el-select>
      </el-form-item>


      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['business:car:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['business:car:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['business:car:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['business:car:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="carList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
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
      <el-table-column label="购买价格" align="center" prop="price" />
      <el-table-column label="出租价格" align="center" prop="rentprice" />
      <el-table-column label="出租押金" align="center" prop="deposit" />
      <el-table-column label="描述" align="center" prop="description" />
      <el-table-column label="出租状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.car_rent_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="门店名称" align="center" prop="deptName" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['business:car:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['business:car:remove']"
          >删除</el-button>
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

    <!-- 添加或修改车辆管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="车牌图片" prop="carImgs">
          <imageUpload v-model="form.carImgs"/>
        </el-form-item>

        <el-form-item label="车牌号" prop="carNumber">
          <el-input v-model="form.carNumber" placeholder="请输入车牌号" />
        </el-form-item>
        <el-form-item label="车架号" prop="frameNumber">
          <el-input v-model="form.frameNumber" placeholder="请输入车架号" />
        </el-form-item>
        <el-form-item label="品牌" prop="brand">
          <el-input v-model="form.brand" placeholder="请输入品牌" />
        </el-form-item>
        <el-form-item label="类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择类型">
            <el-option
              v-for="dict in dict.type.car_type"
              :key="dict.value"
              :label="dict.label"
:value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="颜色" prop="color">
          <el-input v-model="form.color" placeholder="请输入颜色" />
        </el-form-item>
        <el-form-item label="购买价格" prop="price">
          <el-input v-model="form.price" placeholder="请输入购买价格" />
        </el-form-item>
        <el-form-item label="出租价格" prop="rentprice">
          <el-input v-model="form.rentprice" placeholder="请输入出租价格" />
        </el-form-item>
        <el-form-item label="出租押金" prop="deposit">
          <el-input v-model="form.deposit" placeholder="请输入出租押金" />
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="form.description" placeholder="请输入描述" />
        </el-form-item>
        <el-form-item label="出租状态">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in dict.type.car_rent_status"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="车辆所属门店" prop="deptId">
          <el-select v-model="form.deptId" placeholder="请选择门店" clearable size="small">
            <el-option
              v-for="dept in depts"
              :key="dept.deptId"
              :label="dept.deptName"
              :value="dept.deptId"
            />
          </el-select>
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
import { listCar, getCar, delCar, addCar, updateCar } from "@/api/business/car";
import {listAllDept} from "@/api/business/dept"

export default {
  name: "Car",
  dicts: ['car_type', 'car_rent_status'],
  data() {
    return {
      depts: [],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 车辆管理表格数据
      carList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        carNumber: null,
        frameNumber: null,
        brand: null,
        type: null,
        color: null,
        minRentPrice: null,
        maxRentPrice: null,
        status: null,
        deptId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        carNumber: [
          { required: true, message: "车牌号不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.listAllDept();
  },
  methods: {
    // 一开始就去查询门店
    listAllDept() {
      listAllDept().then(res => {
        this.depts = res.data;
      })
    },
    /** 查询车辆管理列表 */
    getList() {
      this.loading = true;
      listCar(this.queryParams).then(response => {
        this.carList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        carNumber: null,
        frameNumber: null,
        brand: null,
        type: null,
        color: null,
        price: null,
        rentprice: null,
        deposit: null,
        description: null,
        status: 0,
        deptName: null,
        deptId: null,
        delFlag: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.queryParams.minRentPrice = null;
      this.queryParams.maxRentPrice = null;
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加车辆管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCar(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改车辆管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCar(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCar(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除车辆管理编号为"' + ids + '"的数据项？').then(function() {
        return delCar(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('business/car/export', {
        ...this.queryParams
      }, `car_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
