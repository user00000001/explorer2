<template>
  <div  class="row tst-pagination-top">
    <div class="col col-sm-4 total-data-lh">
      <span class="f-color"> {{ $t('tstPagination.total') }}</span>
      <span class="tst-blue">{{ total }}</span>
      <span class="f-color"> {{ $t('tstPagination.data') }}</span>
    </div>
    <div class="col col-sm-8">
      <div class="row justify-content-end">
        <el-select class="tst-oep-el-select pc-display" v-if="$route.name == 'AddressDetail' || $route.name == 'AddressDetailTest'" v-model="oepValue" :placeholder="oepValue">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
        <el-pagination
          @current-change="handleCurrentChange"
          @size-change="handleSizeChange"
          background
          layout="sizes, prev, pager, next"
          :current-page="currentPage"
          :page-size="pageSize"
          :pageSizes="pageSizes"
          :total="total">
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
	export default {
    /**
     * 绘制页数导航
     *
     * total： 总页数
     * paramVal： 可选；特殊参数的值
     */
    name: "TurnThePage",
    props: ['total', 'paramVal'],
    data() {
      return {
        pageSize: 0,
        currentPage: 0,
        pageSizesTest:[10,20],
        pageSizesMain:[10,20],
        pageSizes:[10,20,30,50,100],
        options: [{
          value: 'ALL',
          label: 'ALL'
        },{
          value: 'TST',
          label: 'TST'
        }, {
          value: 'TSG',
          label: 'TSG'
        }, {
          value: 'PAX',
          label: 'PAX'
        }],
        oepValue:this.$route.params.assetName?this.$route.params.assetName:"ALL"
      }
    },
    watch: {
      '$route': 'setParams',
      'total': 'setParams',
      'oepValue':'setAsset'
    },
    mounted(){
      this.setParams()
      console.log(this.$route.name)
    },
    methods: {
      setAsset(){
        /* sessionStorage.setItem("asset_name", this.oepValue); */
        this.toFirstPage()
      },
      setParams() {
        this.currentPage = Number(this.$route.params.pageNumber);
        this.pageSize = Number(this.$route.params.pageSize)
        if(this.$route.params.net === 'testnet' ){
          this.pageSizes = this.pageSizesTest
        }else{
          this.pageSizes = this.pageSizesMain
        }
      },
      toFirstPage() {
        let params = {assetName:this.oepValue,pageSize: 20, pageNumber: 1};
        this.routerPush(params)
      },
      handleCurrentChange(val) {
        let params = {pageSize: this.$route.params.pageSize, pageNumber: val};
        this.routerPush(params)
      },
      handleSizeChange(val) {
        let params = {pageSize: val, pageNumber: '1'};
        this.routerPush(params)
      },
      routerPush(params) {
        // 判断网络
        if (this.$route.params.net === 'testnet') {
          params['net'] = 'testnet'
        }

        // 判断是否有其他参数
        if (typeof(this.paramVal) !== 'undefined') {
          params[this.$route.name] = this.paramVal;
        }

        // 注意在view组件中需要用watch触发数据刷新！！
        this.$router.push({name: this.$route.name, params: params})
      }
    }
  }
</script>

<style scoped>
  .tst-pagination-top {
    margin: 15px -15px;
  }

  .total-data-lh {
    line-height: 32px;
  }
</style>
