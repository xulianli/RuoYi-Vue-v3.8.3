<!--<template>-->
<!--  <div class="app-container">-->
<!--    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">-->
<!--      <el-form-item label="出租单号" prop="rentSn">-->
<!--        <el-input-->
<!--          v-model="queryParams.rentSn"-->
<!--          placeholder="请输入出租单号"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->

<!--      <el-form-item label="起租时间" prop="beginDate">-->
<!--        <el-date-picker clearable size="small"-->
<!--          v-model="queryParams.beginDate"-->
<!--          type="datetime"-->
<!--          value-format="yyyy-MM-dd HH:mm:ss"-->
<!--          placeholder="选择起租时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="还车时间" prop="returnDate">-->
<!--        <el-date-picker clearable size="small"-->
<!--          v-model="queryParams.returnDate"-->
<!--          type="datetime"-->
<!--          value-format="yyyy-MM-dd HH:mm:ss"-->
<!--          placeholder="选择还车时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="出租单状态" prop="rentStatus">-->
<!--        <el-select v-model="queryParams.rentStatus" placeholder="请选择出租单状态" clearable size="small">-->
<!--          <el-option-->
<!--            v-for="dict in dict.type.car_back_status"-->
<!--            :key="dict.value"-->
<!--            :label="dict.label"-->
<!--            :value="dict.value"-->
<!--          />-->
<!--        </el-select>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="客户身份证号" prop="identity">-->
<!--        <el-input-->
<!--          v-model="queryParams.identity"-->
<!--          placeholder="请输入客户身份证号"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="车牌号" prop="carNumber">-->
<!--        <el-input-->
<!--          v-model="queryParams.carNumber"-->
<!--          placeholder="请输入车牌号"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item>-->
<!--        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>-->
<!--        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>-->
<!--      </el-form-item>-->
<!--    </el-form>-->


<!--    <el-table v-loading="loading" :data="rentList" @selection-change="handleSelectionChange">-->
<!--      <el-table-column type="selection" width="55" align="center" />-->
<!--      <el-table-column label="出租单id" align="center" prop="id" />-->
<!--      <el-table-column label="出租单号" align="center" prop="rentSn" />-->
<!--      <el-table-column label="出租价格" align="center" prop="rentprice" />-->
<!--      <el-table-column label="起租时间" align="center" prop="beginDate" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.beginDate, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="还车时间" align="center" prop="returnDate" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.returnDate, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="出租单状态" align="center" prop="rentStatus">-->
<!--        <template slot-scope="scope">-->
<!--          <dict-tag :options="dict.type.car_back_status" :value="scope.row.rentStatus"/>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="客户身份证号" align="center" prop="identity" />-->
<!--      <el-table-column label="车牌号" align="center" prop="carNumber" />-->
<!--      <el-table-column label="操作人" align="center" prop="opername" />-->
<!--    </el-table>-->

<!--    <pagination-->
<!--      v-show="total>0"-->
<!--      :total="total"-->
<!--      :page.sync="queryParams.pageNum"-->
<!--      :limit.sync="queryParams.pageSize"-->
<!--      @pagination="getList"-->
<!--    />-->

<!--    &lt;!&ndash; 添加或修改出租单管理对话框 &ndash;&gt;-->
<!--    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>-->
<!--      <el-form ref="form" :model="form" :rules="rules" label-width="80px">-->
<!--      </el-form>-->
<!--      <div slot="footer" class="dialog-footer">-->
<!--        <el-button type="primary" @click="submitForm">确 定</el-button>-->
<!--        <el-button @click="cancel">取 消</el-button>-->
<!--      </div>-->
<!--    </el-dialog>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--import { listRent, getRent, delRent, addRent, updateRent } from "@/api/business/rent";-->
<!--import {getCustomerByIdentity} from "@/api/business/customer";-->

<!--export default {-->
<!--  name: "Rent",-->
<!--  dicts: ['car_back_status'],-->
<!--  data() {-->
<!--    return {-->
<!--      // 遮罩层-->
<!--      loading: true,-->
<!--      // 选中数组-->
<!--      ids: [],-->
<!--      // 非单个禁用-->
<!--      single: true,-->
<!--      // 非多个禁用-->
<!--      multiple: true,-->
<!--      // 显示搜索条件-->
<!--      showSearch: true,-->
<!--      // 总条数-->
<!--      total: 0,-->
<!--      // 出租单管理表格数据-->
<!--      rentList: [],-->
<!--      // 弹出层标题-->
<!--      title: "",-->
<!--      // 是否显示弹出层-->
<!--      open: false,-->
<!--      // 查询参数-->
<!--      queryParams: {-->
<!--        pageNum: 1,-->
<!--        pageSize: 10,-->
<!--        rentSn: null,-->
<!--        rentprice: null,-->
<!--        beginDate: null,-->
<!--        returnDate: null,-->
<!--        rentStatus: null,-->
<!--        identity: null,-->
<!--        carNumber: null,-->
<!--      },-->
<!--      // 表单参数-->
<!--      form: {},-->
<!--      // 表单校验-->
<!--      rules: {-->
<!--      }-->
<!--    };-->
<!--  },-->
<!--  created() {-->
<!--    this.getList();-->
<!--  },-->
<!--  methods: {-->
<!--    /** 查询出租单管理列表 */-->
<!--    getList() {-->
<!--      this.loading = true;-->
<!--      listRent(this.queryParams).then(response => {-->
<!--        this.rentList = response.rows;-->
<!--        this.total = response.total;-->
<!--        this.loading = false;-->
<!--      });-->
<!--    },-->
<!--    // 取消按钮-->
<!--    cancel() {-->
<!--      this.open = false;-->
<!--      this.reset();-->
<!--    },-->
<!--    // 表单重置-->
<!--    reset() {-->
<!--      this.form = {-->
<!--        id: null,-->
<!--        rentSn: null,-->
<!--        rentprice: null,-->
<!--        beginDate: null,-->
<!--        returnDate: null,-->
<!--        rentStatus: 0,-->
<!--        identity: null,-->
<!--        carNumber: null,-->
<!--        opername: null,-->
<!--        delFlag: null,-->
<!--        createBy: null,-->
<!--        createTime: null,-->
<!--        updateBy: null,-->
<!--        updateTime: null,-->
<!--        deptId: null-->
<!--      };-->
<!--      this.resetForm("form");-->
<!--    },-->
<!--    /** 搜索按钮操作 */-->
<!--    handleQuery() {-->
<!--      this.queryParams.pageNum = 1;-->
<!--      this.getList();-->
<!--    },-->
<!--    /** 重置按钮操作 */-->
<!--    resetQuery() {-->
<!--      this.resetForm("queryForm");-->
<!--      this.handleQuery();-->
<!--    },-->
<!--    // 多选框选中数据-->
<!--    handleSelectionChange(selection) {-->
<!--      this.ids = selection.map(item => item.id)-->
<!--      this.single = selection.length!==1-->
<!--      this.multiple = !selection.length-->
<!--    },-->
<!--    /** 新增按钮操作 */-->
<!--    handleAdd() {-->
<!--      this.reset();-->
<!--      this.open = true;-->
<!--      this.title = "添加出租单管理";-->
<!--    },-->
<!--    /** 修改按钮操作 */-->
<!--    handleUpdate(row) {-->
<!--      this.reset();-->
<!--      const id = row.id || this.ids-->
<!--      getRent(id).then(response => {-->
<!--        this.form = response.data;-->
<!--        this.open = true;-->
<!--        this.title = "修改出租单管理";-->
<!--      });-->
<!--    },-->
<!--    /** 提交按钮 */-->
<!--    submitForm() {-->
<!--      this.$refs["form"].validate(valid => {-->
<!--        if (valid) {-->
<!--          if (this.form.id != null) {-->
<!--            updateRent(this.form).then(response => {-->
<!--              this.$modal.msgSuccess("修改成功");-->
<!--              this.open = false;-->
<!--              this.getList();-->
<!--            });-->
<!--          } else {-->
<!--            addRent(this.form).then(response => {-->
<!--              this.$modal.msgSuccess("新增成功");-->
<!--              this.open = false;-->
<!--              this.getList();-->
<!--            });-->
<!--          }-->
<!--        }-->
<!--      });-->
<!--    },-->
<!--    /** 删除按钮操作 */-->
<!--    handleDelete(row) {-->
<!--      const ids = row.id || this.ids;-->
<!--      this.$modal.confirm('是否确认删除出租单管理编号为"' + ids + '"的数据项？').then(function() {-->
<!--        return delRent(ids);-->
<!--      }).then(() => {-->
<!--        this.getList();-->
<!--        this.$modal.msgSuccess("删除成功");-->
<!--      }).catch(() => {});-->
<!--    },-->
<!--    /** 导出按钮操作 */-->
<!--    handleExport() {-->
<!--      this.download('business/rent/export', {-->
<!--        ...this.queryParams-->
<!--      }, `rent_${new Date().getTime()}.xlsx`)-->
<!--    }-->
<!--  }-->
<!--};-->
<!--</script>-->

<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="开始时间" prop="startDate">
        <el-date-picker clearable size="small"
                        v-model="queryParams.startDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择起租时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="endDate">
        <el-date-picker clearable size="small"
                        v-model="queryParams.endDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择还车时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="出租单状态" prop="rentStatus">
        <el-select v-model="queryParams.rentStatus" placeholder="请选择出租单状态" clearable size="small">
          <el-option
            v-for="dict in dict.type.car_rent_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="客户身份证号" prop="identity">
        <el-input
          v-model="queryParams.identity"
          placeholder="请输入客户身份证号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车牌号" prop="carNumber">
        <el-input
          v-model="queryParams.carNumber"
          placeholder="请输入车牌号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="操作人" prop="opername">
        <el-input
          v-model="queryParams.opername"
          placeholder="请输入操作人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['business:rent:edit']"
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
          v-hasPermi="['business:rent:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['business:rent:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" border :data="rentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="出租单id" align="center" prop="id" />
      <el-table-column label="出租单号" align="center" prop="rentSn" />
      <el-table-column label="出租价格" align="center" prop="rentprice" />
      <el-table-column label="起租时间" align="center" prop="beginDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.beginDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="还车时间" align="center" prop="returnDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.returnDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="出租单状态" align="center" prop="rentStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.car_rent_status" :value="scope.row.rentStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="客户身份证号" align="center" prop="identity" />
      <el-table-column label="车牌号" align="center" prop="carNumber" />
      <el-table-column label="操作人" align="center" prop="opername" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['business:rent:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['business:rent:remove']"
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

    <!-- 添加或修改出租单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="出租单号" prop="rentSn">
          <el-input v-model="form.rentSn" readonly placeholder="请输入出租单号" />
        </el-form-item>
        <el-form-item label="出租价格" prop="rentprice">
          <el-input v-model="form.rentprice" placeholder="请输入出租价格" />
        </el-form-item>
        <el-form-item label="起租时间" prop="beginDate">
          <el-date-picker clearable size="small"
                          v-model="form.beginDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择起租时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="还车时间" prop="returnDate">
          <el-date-picker clearable size="small"
                          v-model="form.returnDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择还车时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="客户身份证号" prop="identity">
          <el-input v-model="form.identity" readonly placeholder="请输入客户身份证号" />
        </el-form-item>
        <el-form-item label="车牌号" prop="carNumber">
          <el-input v-model="form.carNumber" readonly placeholder="请输入车牌号" />
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
import { listRent, getRent, delRent, addRent, updateRent } from "@/api/business/rent";

export default {
  name: "Rent",
  dicts: ['car_rent_status'],
  data() {
    return {
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
      // 出租单表格数据
      rentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        rentSn: null,
        rentprice: null,
        startDate: null,
        endDate: null,
        rentStatus: null,
        identity: null,
        carNumber: null,
        opername: null,
        deptId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        rentSn: [
          { required: true, message: "出租单号不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询出租单列表 */
    getList() {
      this.loading = true;
      listRent(this.queryParams).then(response => {
        console.log(response)
        this.rentList = response.rows;
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
        rentSn: null,
        rentprice: null,
        beginDate: null,
        returnDate: null,
        rentStatus: null,
        identity: null,
        carNumber: null,
        opername: null,
        delFlag: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        deptId: null
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
      this.title = "添加出租单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getRent(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改出租单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateRent(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRent(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除出租单编号为"' + ids + '"的数据项？').then(function() {
        return delRent(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('business/rent/export', {
        ...this.queryParams
      }, `rent_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
