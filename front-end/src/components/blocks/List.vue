<template>
  <div class="e-container container-margin-top">
    <list-title :name="$t('blockList.name')"></list-title>

    <tst-pagination :total="blocks.total"></tst-pagination>

    <div class="row justify-content-center">
      <div class="col">
        <o-load :type="listType" v-if="!(blocks.info && loadingFlag)" ></o-load>
        <div v-else class="table-responsive">
          <table class="table table-hover">
            <thead>
            <tr>
              <th class="font-size18" scope="col">{{ $t('all.height') }}</th>
              <th class="font-size18" scope="col">{{ $t('blockList.TxnNum') }}</th>
              <th class="font-size18" scope="col">{{ $t('blockList.bookkeeperCount') }}</th>
              <th class="font-size18" scope="col">{{ $t('blockList.BlockSize') }}( {{ $t('all.byte') }} )</th>
              <th class="font-size18" scope="col">{{ $t('all.time') }}</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="block in blocks.info">
              <td class="font-size14 font-Regular important_color pointer" @click="toBlockDetailPage(block.block_height)">{{block.block_height}}</td>
              <td class="font-size14 font-Regular normal_color">{{block.tx_count}}</td>
              <td class="font-size14 font-Regular normal_color">{{block.BookKeeper.length}}</td>
              <td class="font-size14 font-Regular normal_color">{{block.block_size}}</td>
              <td class="font-size14 font-Regular normal_color">{{$HelperTools.getTransDate(block.block_time)}}</td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>

    <tst-pagination :total="blocks.total"></tst-pagination>
  </div>
</template>

<script>
  import {mapState} from 'vuex'

  export default {
    mounted() {
      this.getBlocks()
      document.body.scrollTop = document.documentElement.scrollTop = 0;
    },
    watch: {
      '$route':function(){
        this.getBlocks()
        document.body.scrollTop = document.documentElement.scrollTop = 0;
      },
      'blocks':function(){
        this.loadingFlag = true
      }
    },
    computed: {
      ...mapState({
        blocks: state => state.Blocks.List,
      })
    },
    data() {
      return {
        loadingFlag:false,
        listType:'list'
      }
    },
    methods: {
      getBlocks() {
        this.loadingFlag = false
        this.testNetPageSizeCheck()
        this.$store.dispatch('GetBlocks', this.$route.params).then()
      },
      testNetPageSizeCheck(){
        if(this.$route.params.net == "testnet"){
          if(this.$route.params.pageSize > 30){
            this.$message({message: this.$t('error.pagesize')});
            this.$route.params.pageSize = 30
            this.$router.push({name: this.$route.name, params: this.$route.params})
            return
          }
        }
      },
      toBlockDetailPage($blockHeight) {
        if (this.$route.params.net == undefined) {
          this.$router.push({name: 'blockDetail', params: {param: $blockHeight}})
        } else {
          this.$router.push({name: 'blockDetailTest', params: {param: $blockHeight, net: 'testnet'}})
        }
      }
    }
  }
</script>

<style scoped>
</style>
