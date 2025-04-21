<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="编号" prop="cardId">
        <el-input
          v-model="queryParams.cardId"
          placeholder="请输入编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!--      <el-form-item label="商品" prop="goodsId">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.goodsId"-->
      <!--          placeholder="请输入商品"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="用户" prop="userId">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.userId"-->
      <!--          placeholder="请输入用户"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
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
      <!--          v-hasPermi="['manage:goodsCardInfo:add']"-->
      <!--        >新增</el-button>-->
      <!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-plus"
          size="mini"
          :disabled="multiple"
          @click="handlePurchase"
          v-hasPermi="['manage:orderInfo:add']"
        >购买
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['manage:goodsCardInfo:edit']"
        >修改
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
          v-hasPermi="['manage:goodsCardInfo:remove']"
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
          v-hasPermi="['manage:goodsCardInfo:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="goodsCardInfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="编号" align="center" v-if="columns[0].visible" prop="cardId"/>
      <el-table-column label="商品" :show-overflow-tooltip="true" align="center" v-if="columns[1].visible"
                       prop="goodsName"
      />
      <el-table-column label="用户" :show-overflow-tooltip="true" align="center" v-if="columns[2].visible"
                       prop="userName"
      />
      <el-table-column label="创建时间" align="center" v-if="columns[3].visible" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新人" :show-overflow-tooltip="true" align="center" v-if="columns[4].visible"
                       prop="updateBy"
      />
      <el-table-column label="更新时间" align="center" v-if="columns[5].visible" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" :show-overflow-tooltip="true" align="center" v-if="columns[6].visible"
                       prop="remark"
      />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['manage:goodsCardInfo:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['manage:goodsCardInfo:remove']"
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

    <!-- 添加或修改购物车对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <!--        <el-form-item label="商品" prop="goodsId">-->
        <!--          <el-input v-model="form.goodsId" placeholder="请输入商品"/>-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="用户" prop="userId">-->
        <!--          <el-input v-model="form.userId" placeholder="请输入用户"/>-->
        <!--        </el-form-item>-->
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加或修改订房记录对话框 -->
    <el-dialog :title="title" :visible.sync="openPurchase" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="收货地址" prop="addressId">
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
        <!--        <el-form-item label="备注" prop="remark">-->
        <!--          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"/>-->
        <!--        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitPurchaseForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  addGoodsCardInfo,
  delGoodsCardInfo,
  getGoodsCardInfo,
  listGoodsCardInfo, payOrderCard,
  updateGoodsCardInfo
} from '@/api/manage/goodsCardInfo'
import { addOrderInfo } from '@/api/manage/orderInfo'
import { listUserAddressInfo } from '@/api/manage/userAddressInfo'

export default {
  name: 'GoodsCardInfo',
  data() {
    return {
      userAddressList: [],
      userAddressLoading: false,
      userAddressQueryParams: {
        userName: '',
        pageNum: 1,
        pageSize: 100
      },
      openPurchase: false,
      //表格展示列
      columns: [
        { key: 0, label: '编号', visible: false },
        { key: 1, label: '商品', visible: true },
        { key: 2, label: '用户', visible: true },
        { key: 3, label: '创建时间', visible: true },
        { key: 4, label: '更新人', visible: false },
        { key: 5, label: '更新时间', visible: false },
        { key: 6, label: '备注', visible: false }
      ],
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
      // 购物车表格数据
      goodsCardInfoList: [],
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
        cardId: null,
        goodsId: null,
        userId: null,
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
    //购买
    handlePurchase() {
      this.title = '下单'
      this.openPurchase = true
    },
    //提交购买商品
    submitPurchaseForm() {
      this.form.cardIds = this.ids.join(',')
      payOrderCard(this.form).then(res => {
        this.$modal.msgSuccess('购买成功,请在十五分钟内立即支付')
        this.openPurchase = false
        this.getList()
      })
    },
    /** 查询购物车列表 */
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
      listGoodsCardInfo(this.queryParams).then(response => {
        this.goodsCardInfoList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        cardId: null,
        goodsId: null,
        userId: null,
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
      this.ids = selection.map(item => item.cardId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.openPurchase = false
      this.title = '添加购物车'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const cardId = row.cardId || this.ids
      getGoodsCardInfo(cardId).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改购物车'
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.cardId != null) {
            updateGoodsCardInfo(this.form).then(response => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addGoodsCardInfo(this.form).then(response => {
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
      const cardIds = row.cardId || this.ids
      this.$modal.confirm('是否确认删除购物车编号为"' + cardIds + '"的数据项？').then(function() {
        return delGoodsCardInfo(cardIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('manage/goodsCardInfo/export', {
        ...this.queryParams
      }, `goodsCardInfo_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
