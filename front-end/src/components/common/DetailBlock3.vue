<template>
  <div class="">
    <!-- PC Styles -->
<!-- Creator: -->
      <div class="row font-size14" >

        <div  class="sc-detail-divider-line"></div>
        <div class="col-2"><span class="normal_color">{{name1}}</span></div>
        <div class="col-10">
          <span class="f-color word-break d-block height-100 font-size14">
            <div class="height-100":class="classObject1" @click="doAction(params1)">{{val1}}</div>
          </span>
        </div>
      </div>
<!-- Created Time: -->
      <div class="row font-size14" >

        <div  class="sc-detail-divider-line"></div>
        <div class="col-sm-2 col-5"><span class="normal_color">{{name2}}</span></div>
        <div class="col-sm-10 col-7">
          <span class="f-color word-break d-block height-100 font-size14">
            <div class="height-100" :class="classObject2" @click="doAction(params2)">{{val2}}</div>
          </span>
        </div>
      </div>

    <!-- Mobile Styles -->
<!--     <div class="d-block d-sm-none col">
      <div class="detail-col font-size14">
        <div class="row">
          <div class="col normal_color" @click="doAction(params1)">
            <div class="font-blod">{{ name1 }}</div>
            <div :class="classObject1">{{ val1 }}</div>
          </div>
        </div>
      </div>

      <div class="detail-col font-size14">
        <div class="row">
          <div class="col">
            <div class="normal_color" @click="doAction(params2)">
              <div class="font-blod">{{ name2 }}</div>
              <div :class="classObject2">{{ val2 }}</div>
            </div>
          </div>
        </div>
      </div>
    </div> -->
  </div>
</template>

<script>
  //提示框
  export default {
    /**
     * 该全局子组件提供【Details】页面内详情的【单行双列】白色数据块。
     *
     * 一般承载3类数据：
     * 1. 详情标题和内容，在块内单行展示
     * 2. 详情标题和内容，在块内双行展示
     * 3. 详情标题和内容，在块内双行展示，其中第二行可以点击跳转
     *
     * rows:
     * 1    代表一行内字体默认；
     * 1.1  代表一行内字体颜色是划重点；#00AE1D 浅绿色；
     * 1.2  代表一行内可跳转，小字体
     * 1.3  代表一行内字体颜色是划重点；超算网络蓝；
     * 2    代表2行内容点击可跳转；14号字体；
     *
     * params：
     * 超算网络蓝字体，一般可跳转
     */
    name: "DetailBlock3",
    props: ['name1', 'val1', 'rows1', 'params1', 'name2', 'val2', 'rows2', 'params2', 'tip', 'tipTit'],
    mounted() {
      $(document).ready(function(){
        $('[data-toggle="tooltip"]').tooltip();
      });
    },
    computed: {
      /**
       * 是否换行，字体大小，颜色，是否可以点击跳转。
       */
      classObject1: function () {
        return {
          'd-block': this.rows1 === '2',
          'font-size14': this.rows1 === '2' || this.rows1 === '1.2',
          's_color': this.rows1 === '1.1',
          'important_color': this.params1 !== undefined ||  this.rows1 === '1.3',
          pointer: this.params1 !== undefined
        }
      },
      classObject2: function () {
        return {
          'd-block': this.rows2 === '2',
          'font-size14': this.rows2 === '2' || this.rows2 === '1.2',
          's_color': this.rows2 === '1.1',
          'important_color': this.params2 !== undefined ||  this.rows1 === '1.3',
          pointer: this.params2 !== undefined
        }
      }
    },
    methods: {
      /**
       * 通过传参判断执行什么函数和函数参数是什么。
       *
       * @param params
       * @return {boolean}
       */
      doAction(params) {
        if (params === undefined) return false;

        if (params[0] === 'block') {
          this.toBlockDetailPage(params[1])
        } else if (params[0] === 'tstId') {
          this.toTstIdDetailPage(params[1])
        } else if (params[0] === 'address') {
          this.goToAddressDetail(params[1])
        }
      },
      toBlockDetailPage($blockHeight) {
        if (this.$route.params.net === 'testnet') {
          this.$router.push({name: 'blockDetailTest', params: {param: $blockHeight, net: 'testnet'}})
        } else {
          this.$router.push({name: 'blockDetail', params: {param: $blockHeight}})
        }
      },
      toTransDetailPage($TxnId) {
        if (this.$route.params.net === 'testnet') {
          this.$router.push({name: 'TransactionDetailTest', params: {txnHash: $TxnId, net: 'testnet'}})
        } else {
          this.$router.push({name: 'TransactionDetail', params: {txnHash: $TxnId}})
        }
      },
      toTstIdDetailPage($TstId) {
        if (this.$route.params.net === 'testnet') {
          this.$router.push({name: 'TstIdDetailTest', params: {tstid: $TstId, net: "testnet"}})
        } else {
          this.$router.push({name: 'TstIdDetail', params: {tstid: $TstId}})
        }
      },
      goToAddressDetail(address) {
        if (this.$route.params.net === 'testnet') {
          this.$router.push({
            name: 'AddressDetailTest',
            params: {address: address, assetName:"ALL", pageSize: 20, pageNumber: 1, net: 'testnet'}
          })
        } else {
          this.$router.push({
            name: 'AddressDetail',
            params: {address: address, assetName:"ALL", pageSize: 20, pageNumber: 1}
          })
        }
      }
    }
  }
</script>

<style scoped>
  .common-tooltip-style {
    float: right;
    color: #AAB3B4;
    font-size: 16px;
    line-height: 32px;
  }

  .sc-detail-divider-line {
    background: #e5e4e4;
    height: 1px;
    margin: 10px;
    width: 100%;
  }
</style>
