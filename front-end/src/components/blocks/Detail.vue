<template>
  <div class="e-container container-margin-top">
    <list-title :name="$t('blockDetail.nickname')"></list-title>
    <detail-title :name="$t('blockDetail.name')" :val="block.block_height"></detail-title>

    <!--区块时间和大小-->
    <detail-block-2 :name1="$t('blockDetail.BlockTime')" :val1="$HelperTools.getTransDate(block.block_time)" :rows1="'1.1'"
                    :name2="$t('blockDetail.BlockSize')" :val2="block.block_size + ' bytes'" :rows2="'1.1'">
    </detail-block-2>

    <detail-block :params="detailParams" :styleVal="'new'"></detail-block>

    <!--上一个区块及下一个区块-->
<!--     <detail-block-2 :name1="$t('blockDetail.PrevBlock')" :val1="block.block_height-1" :rows1="'2'"
                    :params1="['block', block.block_height-1]"
                    :name2="$t('blockDetail.NextBlock')" :val2="block.block_height+1" :rows2="'2'"
                    :params2="nextBlockUrl !== 'Null' ? ['block', block.block_height+1] : ['block', block.block_height+1]">
    </detail-block-2> -->

    <div class="row" v-if="block.tx_count !== 0">
      <div class="col">
        <div class="detail-col">
          {{ block.tx_count }}<span class="f-color"> {{ $t('blockDetail.txOnBlock') }}</span>
          <div class="table-responsive">
            <table class="table">
              <thead>
              <tr class="f-color">
                <th class="td-tx-head font-size18 font-Blod">{{ $t('all.hash') }}</th>
                <th class="td-tx-head font-size18 font-Blod">{{ $t('all.status') }}</th>
                <th class="td-tx-head font-size18 font-Blod">{{ $t('all.time') }}</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="tx in block.txs">
                <td class="font-size14 important_color font-Regular pointer" @click="toTransDetailPage(tx.tx_hash)">
                  {{tx.tx_hash.substr(0,4) + '...' + tx.tx_hash.substr(60)}}
                </td>
                <td class="font-size14 s-color font-Regular" v-if="tx.confirm_flag === 1">
                  Confirmed
                </td>
                <td class="font-size14 f-color font-Regular" v-else>
                  Failed
                </td>
                <td class="font-size14 normal_color font-Regular">
                  {{$HelperTools.getTransDate(tx.tx_time)}}
                </td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import {mapState} from 'vuex'

  export default {
    data() {
      return {
        block:{},
        nextFlag: false,
        nextCheck: false,
      }
    },
    mounted() {
      this.getBlock()
      document.body.scrollTop = document.documentElement.scrollTop = 0;
    },
    watch: {
      '$route': function(){
        this.getBlock()
      },
      'currentblock':function(){
        this.block = this.currentblock
        console.log("bingo")
      }
    },
    computed: {
      ...mapState({
        currentblock: state => state.Blocks.Detail,
      }),
      detailParams: function () {
        return [
          {name: this.$t('blockDetail.hash'), val: this.block.block_hash, rows: 2},
          {name: this.$t('blockDetail.keeper'), val: this.block.BookKeeper, rows: 2},
          {name: this.$t('blockDetail.merkle'), val: this.block.txs_root, rows: 2},
          {name: this.$t('blockDetail.Consensus'), val: this.block.consensus_data, rows: 2},
        ]
      },
      prevBlockUrl: function () {
        return typeof(this.block.PrevBlock) === 'undefined' ? 'Null' : this.block.PrevBlock.substr(0, 4) + '...' + this.block.PrevBlock.substr(60)
      },
      nextBlockUrl: function () {
        if (typeof(this.block.NextBlock) !== 'undefined') {
          if (this.block.NextBlock !== '') {
            return this.block.NextBlock.substr(0, 4) + '...' + this.block.NextBlock.substr(60)
          }
        }

        return 'Null'
      }
    },
    methods: {
      getBlock() {
        this.$store.dispatch('GetBlock', this.$route.params).then()
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
          this.$router.push({name: 'TransactionDetailTest', params: {tx_hash: $TxnId, net: 'testnet'}})
        } else {
          this.$router.push({name: 'TransactionDetail', params: {tx_hash: $TxnId}})
        }
      }
    }
  }
</script>

<style scoped>
</style>
