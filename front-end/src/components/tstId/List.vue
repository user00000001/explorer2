<template>
  <div class="e-container container-margin-top">
    <list-title :name="$t('tstIdList.name')"></list-title>

    <tst-pagination :total="tstIdList.total"></tst-pagination>

    <div class="row justify-content-center">
      <div class="col">
        <o-load :type="listType" v-if="!(tstIdList.list && loadingFlag)" ></o-load>
        <div v-else class="table-responsive">
          <table class="table table-hover">
            <thead>
            <tr>
              <th class="font-size18" scope="col">{{ $t('all.hash') }}</th>
              <th class="font-size18" scope="col">{{ $t('all.tstId') }}</th>
              <th class="font-size18" scope="col">{{ $t('all.content') }}</th>
              <th class="font-size18" scope="col">{{ $t('all.height') }}</th>
              <th class="font-size18" scope="col">{{ $t('all.fee') }}</th>
              <th class="font-size18" scope="col">{{ $t('all.time') }}</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="TstId in tstIdList.list">
              <td class="font-size14 font-Regular important_color pointer" @click="toTransactionDetailPage(TstId.tx_hash)">{{TstId.tx_hash.substr(0,8) + '...' + TstId.tx_hash.substr(56)}}</td>
              <td class="font-size14 font-Regular important_color pointer" @click="toTstIdDetailPage(TstId.tstid)">{{TstId.tstid.substr(0,10)}}...{{TstId.tstid.substr(35,46)}}</td>
              <td class="font-size14 font-Regular normal_color">{{getTstIDEvent(TstId.description)}}</td>
              <td class="font-size14 font-Regular normal_color">{{TstId.block_height}}</td>
              <td class="font-size14 font-Regular normal_color">{{Number(TstId.fee)}}</td>
              <td class="font-size14 font-Regular normal_color">{{$HelperTools.getTransDate(TstId.tx_time)}}</td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>

    <tst-pagination :total="tstIdList.total"></tst-pagination>
  </div>
</template>

<script>
  import {mapState} from 'vuex'
  import GetTransactionType from './../../common/TstMsg/GetTransactionType.js'

  export default {
    data() {
      return {
        listType:"list",
        loadingFlag:false,
      }
    },
    mounted() {
      this.getTstIdList()
      document.body.scrollTop = document.documentElement.scrollTop = 0;
    },
    watch: {
      '$route': function(){
        this.getTstIdList()
        document.body.scrollTop = document.documentElement.scrollTop = 0;
      },
      'tstIdList':function(){
        this.loadingFlag = true
        console.log(this.tstIdList)
      }
    },
    computed: {
      ...mapState({
        tstIdList: state => state.TstIDs.List,
      })
    },
    methods: {
      getTstIdList() {
        this.loadingFlag = false
        this.testNetPageSizeCheck()
        this.$store.dispatch('GetTstIdList', this.$route.params).then()
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
      getTransactionType($case) {
        return GetTransactionType.getTransactionType($case)
      },
      toTransactionDetailPage($TxnId) {
        if (this.$route.params.net == undefined) {
          this.$router.push({name: 'TransactionDetail', params: {tx_hash: $TxnId}})
        } else {
          this.$router.push({name: 'TransactionDetailTest', params: {tx_hash: $TxnId, net: "testnet"}})
        }
      },
      toTstIdDetailPage($TstId) {
        if (this.$route.params.net == undefined) {
          this.$router.push({name: 'TstIdDetail', params: {tstid: $TstId,pageSize:10,pageNumber:1}})
        } else {
          this.$router.push({name: 'TstIdDetailTest', params: {tstid: $TstId,pageSize:10,pageNumber:1, net: "testnet"}})
        }
      },
      getTstIDEvent: function ($event) {
        switch ($event.substr(0, 12)) {
          case "register Tst":
            return "Register TST ID"
          case "add publicKe":
            return "Add publickey"
          case "remove publi":
            return "Remove publickey"
          case "add attribut":
            return "Add identity attribute"
          case "update attri":
            return "Update identity attribute"
          case "delete attri":
            return "Delete identity attribute"
          case "change recov":
            return "Change recovery"
          case "add recovery":
            return "Add recovery"
          case "remove attri":
            return "Remove attribute"
          case "create new c":
            return "Create new claim"
          case "revoke claim":
            return "revoke claim"
        }
      }
    }
  }
</script>

<style scoped>

</style>
