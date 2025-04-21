<template>
  <div class="goods-detail">
    <!-- 房间轮播图 -->
    <el-carousel :interval="3000" type="card" height="400px" v-if="goodsImages.length">
      <el-carousel-item v-for="(img, index) in goodsImages" :key="index">
        <el-image :src="img" fit="cover" style="width: 100%; height: 100%"/>
      </el-carousel-item>
    </el-carousel>

    <!-- 房间信息 -->
    <el-card class="goods-info-card" shadow="hover">
      <h2>{{ goodsInfo.goodsName }}</h2>
      <p><strong>价格：</strong> ¥{{ goodsInfo.goodsPrice }}</p>
      <p><strong>描述：</strong> {{ goodsInfo.goodsDesc }}</p>
      <p><strong>销量：</strong> {{ goodsInfo.purchaseNum }}</p>
      <p>
        <strong>
          <el-button type="text" @click="handlePurchase">立即购买</el-button>
          <el-button type="text" @click="handleCollect">收藏商品</el-button>
        </strong>
      </p>
    </el-card>
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
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitPurchaseForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 房间评论 -->
    <div class="comment-section">
      <h3>用户评论</h3>
      <el-empty description="暂无评论" v-if="comments.length === 0"/>
      <el-card class="comment-card" v-for="comment in comments" :key="comment.id" shadow="never">
        <div class="comment-header">
          <el-rate :value="Number(comment.score)" disabled/>
          <span class="comment-time">{{ comment.createTime }}</span>
        </div>
        <div class="comment-content">{{ comment.content }}</div>
        <div class="comment-images" v-if="comment.imageUrls">
          <el-image
            v-for="(img, index) in comment.imageUrls.split(',')"
            :key="index"
            :src="baseUrl+img"
            :initial-index="index"
            fit="cover"
            style="width: 100px; height: 100px; margin-right: 10px"
          />
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>


import { listRoomCommentInfo } from '@/api/manage/roomCommentInfo'
import { getGoodsInfo } from '@/api/manage/goodsInfo'
import { addOrderInfo } from '@/api/manage/orderInfo'
import { listUserAddressInfo } from '@/api/manage/userAddressInfo'
import { addCollectInfo } from '@/api/manage/collectInfo'

export default {
  name: 'RoomDetail',
  data() {
    return {
      userAddressList: [],
      userAddressLoading: false,
      userAddressQueryParams: {
        userName: '',
        pageNum: 1,
        pageSize: 100
      },
      title: '购买商品',
      openPurchase: false,
      form: {
        goodsId: '',
        userId: '',
        reserveTime: '',
        payTime: '',
        payStatus: '',
        payType: '',
        payAmount: '',
        remark: ''
      },
      baseUrl: process.env.VUE_APP_BASE_API,
      goodsId: 1, // 实际使用中通过路由或父组件传入
      goodsInfo: {
        goodsName: '',
        goodsPrice: 0,
        goodsDesc: '',
        goodsImage: '',
        goodsStatus: 0,
        reserveNum: 0,
        remark: ''
      },
      goodsImages: [],
      comments: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: null,
        goodsId: null,
        userId: null,
        score: null,
        content: null,
        imageUrls: null,
        createTime: null,
        updateTime: null
      },
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
        createTime: [
          { required: true, message: '创建时间不能为空', trigger: 'blur' }
        ]
      }
    }
  },

  created() {
    const goodsId = this.$route.params && this.$route.params.goodsId
    this.form.goodsId = goodsId
    this.getGoodsInfo(goodsId)
    this.getList(goodsId)
    this.getUserAddressList()
  },
  methods: {
    handleCollect() {
      const goodsIds = this.form.goodsId
      this.$modal.confirm('是否确认收藏商品？').then(function() {
        return addCollectInfo({ goodsId: goodsIds })
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('收藏成功')
      }).catch(() => {
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
    /** 查询房间评价列表 */
    getList(id) {
      this.loading = true
      this.queryParams.goodsId = id
      listRoomCommentInfo(this.queryParams).then(response => {
        this.comments = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    //购买
    handlePurchase() {
      this.title = '购买--' + this.goodsInfo.goodsName
      this.openPurchase = true
    },
    //提交预定房间
    submitPurchaseForm() {
      addOrderInfo(this.form).then(res => {
        this.$modal.msgSuccess('购买成功,请在十五分钟内立即支付')
        this.openPurchase = false
        this.getGoodsInfo(this.form.goodsId)
      })
    },
    // 取消按钮
    cancel() {
      this.openPurchase = false
    },
    getGoodsInfo(id) {
      getGoodsInfo(id).then(response => {
        this.goodsInfo = response.data
        this.goodsImages = this.goodsInfo.goodsImage.split(',').map(url => {
          console.log(this.baseUrl + url)
          return this.baseUrl + url
        })
      })
    }
  }
}
</script>

<style scoped>
.goods-detail {
  max-width: 1000px;
  margin: 0 auto;
  padding: 20px;
}

.goods-info-card {
  margin-top: 20px;
}

.comment-section {
  margin-top: 40px;
}

.comment-card {
  margin-bottom: 20px;
}

.comment-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 8px;
}

.comment-content {
  margin-bottom: 10px;
}

.comment-images {
  display: flex;
  flex-wrap: wrap;
}
</style>
