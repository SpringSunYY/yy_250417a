<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="编号" prop="orderId">
        <el-input
          v-model="queryParams.orderId"
          placeholder="请输入编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="historyStatus">
        <el-select v-model="queryParams.historyStatus" placeholder="请选择状态" clearable>
          <el-option
            v-for="dict in dict.type.order_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker
          v-model="daterangeCreateTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <!--      <el-form-item label="更新人" prop="updateBy">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.updateBy"-->
      <!--          placeholder="请输入更新人"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="更新时间">-->
      <!--        <el-date-picker-->
      <!--          v-model="daterangeUpdateTime"-->
      <!--          style="width: 240px"-->
      <!--          value-format="yyyy-MM-dd"-->
      <!--          type="daterange"-->
      <!--          range-separator="-"-->
      <!--          start-placeholder="开始日期"-->
      <!--          end-placeholder="结束日期"-->
      <!--        ></el-date-picker>-->
      <!--      </el-form-item>-->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <!--      <el-col :span="1.5">-->
      <!--        <el-button-->
      <!--          type="primary"-->
      <!--          plain-->
      <!--          icon="el-icon-plus"-->
      <!--          size="mini"-->
      <!--          @click="handleAdd"-->
      <!--          v-hasPermi="['manage:orderInfo:add']"-->
      <!--        >新增-->
      <!--        </el-button>-->
      <!--      </el-col>-->
      <!--      <el-col :span="1.5">-->
      <!--        <el-button-->
      <!--          type="success"-->
      <!--          plain-->
      <!--          icon="el-icon-edit"-->
      <!--          size="mini"-->
      <!--          :disabled="single"-->
      <!--          @click="handleUpdate"-->
      <!--          v-hasPermi="['manage:orderInfo:edit']"-->
      <!--        >修改-->
      <!--        </el-button>-->
      <!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-plus"
          size="mini"
          :disabled="multiple"
          @click="handlePay"
          v-hasPermi="['manage:orderInfo:add']"
        >支付
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['manage:orderInfo:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['manage:orderInfo:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="orderInfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="编号" align="center" v-if="columns[0].visible" prop="orderId"/>
      <el-table-column label="商品" :show-overflow-tooltip="true" align="center" v-if="columns[1].visible"
                       prop="goodsName"
      />
      <el-table-column label="用户" :show-overflow-tooltip="true" align="center" v-if="columns[2].visible"
                       prop="userName"
      />
      <el-table-column label="地址" :show-overflow-tooltip="true" align="center" v-if="columns[3].visible"
                       prop="addressName"
      />
      <el-table-column label="价格" :show-overflow-tooltip="true" align="center" v-if="columns[4].visible"
                       prop="totalPrice"
      />
      <el-table-column label="状态" align="center" v-if="columns[5].visible" prop="historyStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.order_status" :value="scope.row.historyStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="供应商" :show-overflow-tooltip="true" align="center" v-if="columns[6].visible"
                       prop="supplierName"
      />
      <el-table-column label="创建时间" align="center" v-if="columns[7].visible" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新人" :show-overflow-tooltip="true" align="center" v-if="columns[8].visible"
                       prop="updateBy"
      />
      <el-table-column label="更新时间" align="center" v-if="columns[9].visible" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" :show-overflow-tooltip="true" align="center" v-if="columns[10].visible"
                       prop="remark"
      />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            v-if="scope.row.historyStatus==='1'"
            @click="handleComment(scope.row)"
            v-hasPermi="['manage:roomCommentInfo:add']"
          >评价
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['manage:orderInfo:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['manage:orderInfo:remove']"
          >删除
          </el-button>
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

    <!-- 添加或修改订单信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <!--        <el-form-item label="商品" prop="goodsId">-->
        <!--          <el-input v-model="form.goodsId" placeholder="请输入商品"/>-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="用户" prop="userId">-->
        <!--          <el-input v-model="form.userId" placeholder="请输入用户"/>-->
        <!--        </el-form-item>-->
        <el-form-item label="地址" prop="addressId">
          <el-select
            style="width: 100%"
            v-model="form.addressId"
            filterable
            remote
            reserve-keyword
            placeholder="请输入手机号码"
            :remote-method="selectUserAddressList"
            :loading="userAddressLoading"
          >
            <el-option
              v-for="item in userAddressList"
              :key="item.addressId"
              :label="`${item.phone}-${item.province}-${item.city}-${item.county}-${item.address}`"
              :value="item.addressId"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <!--        <el-form-item label="价格" prop="totalPrice">-->
        <!--          <el-input v-model="form.totalPrice" placeholder="请输入价格"/>-->
        <!--        </el-form-item>-->
        <el-form-item label="状态" prop="historyStatus">
          <el-radio-group disabled v-model="form.historyStatus">
            <el-radio
              v-for="dict in dict.type.order_status"
              :key="dict.value"
              :label="dict.value"
            >{{ dict.label }}
            </el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="供应商" prop="supplierId">
          <el-input v-model="form.supplierId" placeholder="请输入供应商"/>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <el-dialog :title="title" :visible.sync="openPay" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="价格" prop="totalPrice">
          <el-input readonly v-model="totalPrice" placeholder="请输入价格"/>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFormPay">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加或修改商品评价对话框 -->
    <el-dialog :title="title" :visible.sync="openComment" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <!--        <el-form-item label="房间" prop="roomId">-->
        <!--          <el-input v-model="form.roomId" placeholder="请输入房间"/>-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="评论用户" prop="userId">-->
        <!--          <el-input v-model="form.userId" placeholder="请输入评论用户"/>-->
        <!--        </el-form-item>-->
        <el-form-item label="评分" prop="score">
          <el-input-number :min="0" :max="5" v-model="form.score" placeholder="请输入评分"/>
        </el-form-item>
        <el-form-item label="评论内容">
          <el-input type="textarea" placeholder="请输入评论内容" v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="评论图片" prop="imageUrls">
          <image-upload v-model="form.imageUrls"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitComment">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listOrderInfo,
  getOrderInfo,
  delOrderInfo,
  addOrderInfo,
  updateOrderInfo,
  payOrderInfo
} from '@/api/manage/orderInfo'
import { listUserAddressInfo } from '@/api/manage/userAddressInfo'
import { addRoomCommentInfo } from '@/api/manage/roomCommentInfo'

export default {
  name: 'OrderInfo',
  dicts: ['order_status'],
  data() {
    return {
      //评论
      openComment: false,
      userAddressList: [],
      userAddressLoading: false,
      userAddressQueryParams: {
        userName: '',
        pageNum: 1,
        pageSize: 100
      },
      openPay: false,
      //表格展示列
      columns: [
        { key: 0, label: '编号', visible: true },
        { key: 1, label: '商品', visible: true },
        { key: 2, label: '用户', visible: true },
        { key: 3, label: '地址', visible: true },
        { key: 4, label: '价格', visible: true },
        { key: 5, label: '状态', visible: true },
        { key: 6, label: '供应商', visible: true },
        { key: 7, label: '创建时间', visible: true },
        { key: 8, label: '更新人', visible: false },
        { key: 9, label: '更新时间', visible: false },
        { key: 10, label: '备注', visible: false }
      ],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      totalPrice: 0,
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 订单信息表格数据
      orderInfoList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 备注时间范围
      daterangeCreateTime: [],
      // 备注时间范围
      daterangeUpdateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderId: null,
        historyStatus: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        goodsId: [
          { required: true, message: '商品不能为空', trigger: 'blur' }
        ],
        userId: [
          { required: true, message: '用户不能为空', trigger: 'blur' }
        ],
        addressId: [
          { required: true, message: '地址不能为空', trigger: 'blur' }
        ],
        totalPrice: [
          { required: true, message: '价格不能为空', trigger: 'blur' }
        ],
        historyStatus: [
          { required: true, message: '状态不能为空', trigger: 'change' }
        ],
        supplierId: [
          { required: true, message: '供应商不能为空', trigger: 'blur' }
        ],
        createTime: [
          { required: true, message: '创建时间不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.getList()
    this.getUserAddressList()
  },
  methods: {
    handleComment(row) {
      this.reset()
      this.title = '添加评论'
      this.openComment = true
      this.form.userId = row.userId
      this.form.orderId = row.orderId
      this.form.goodsId = row.goodsId
    },
    submitComment() {
      addRoomCommentInfo(this.form).then(res => {
        this.$modal.msgSuccess('评论成功')
        this.openComment = false
        this.getList()
      })
    },
    /**
     * 获取用户地址列表推荐
     * @param query
     */
    selectUserAddressList(query) {
      if (query !== '') {
        this.userAddressLoading = true
        this.userAddressQueryParams.phone = query
        setTimeout(() => {
          this.getUserAddressList()
        }, 200)
      } else {
        this.userAddressList = []
      }
    },
    /**
     * 获取用户地址信息列表
     */
    getUserAddressList() {
      //添加查询参数
      if (this.form.userId != null) {
        this.userAddressQueryParams.userId = this.form.userId
      } else {
        this.userAddressQueryParams.userId = null
      }
      if (this.userAddressQueryParams.userName != null) {
        this.userAddressQueryParams.userId = null
      }
      listUserAddressInfo(this.userAddressQueryParams).then(res => {
        this.userAddressList = res.rows
        this.userAddressLoading = false
      })
    },
    submitFormPay() {
      console.log(this.form)
      payOrderInfo(this.ids).then(res => {
          this.openPay = false
          this.getList()
          this.$modal.msgSuccess('支付成功')
        }
      )
    },
    handlePay() {
      this.title = '支付订单'
      this.openPay = true
    },
    /** 查询订单信息列表 */
    getList() {
      this.loading = true
      this.queryParams.params = {}
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.params['beginCreateTime'] = this.daterangeCreateTime[0]
        this.queryParams.params['endCreateTime'] = this.daterangeCreateTime[1]
      }
      if (null != this.daterangeUpdateTime && '' != this.daterangeUpdateTime) {
        this.queryParams.params['beginUpdateTime'] = this.daterangeUpdateTime[0]
        this.queryParams.params['endUpdateTime'] = this.daterangeUpdateTime[1]
      }
      listOrderInfo(this.queryParams).then(response => {
        this.orderInfoList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.openPay = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        ids: [],
        orderId: null,
        goodsId: null,
        userId: null,
        addressId: null,
        totalPrice: null,
        historyStatus: null,
        supplierId: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      }
      this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeCreateTime = []
      this.daterangeUpdateTime = []
      this.resetForm('queryForm')
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.totalPrice = 0
      this.reset()
      this.ids = selection.map(item => item.orderId)
      this.totalPrice = selection.reduce((total, item) => {
        return total + item.totalPrice
      }, 0)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加订单信息'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const orderId = row.orderId || this.ids
      getOrderInfo(orderId).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改订单信息'
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.orderId != null) {
            updateOrderInfo(this.form).then(response => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addOrderInfo(this.form).then(response => {
              this.$modal.msgSuccess('新增成功')
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const orderIds = row.orderId || this.ids
      this.$modal.confirm('是否确认删除订单信息编号为"' + orderIds + '"的数据项？').then(function() {
        return delOrderInfo(orderIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('manage/orderInfo/export', {
        ...this.queryParams
      }, `orderInfo_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
