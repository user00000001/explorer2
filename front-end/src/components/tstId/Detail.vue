<template>
  <div class="e-container container-margin-top">
    <list-title :name="$t('tstIdDetail.nickname')"></list-title>
    <detail-title :name="$t('tstIdDetail.name')" :val="$route.params.tstid"></detail-title>

    <!-- Owners info -->
    <div class="row" v-if="haveData && Ddo.Owners">
      <div class="col">
        <div class="detail-col">
          <p class="font-blod">{{ $t('tstIdDetail.owner') }}</p>
          <div class="row" v-for="owner in Ddo.Owners">
            <div class="col">
              <div class="font-size14 font-Regular normal_color txt-overflow">
                <p><span class="font-blod">Type: </span>{{owner.Type}}</p>
              </div>
              <div class="font-size14 font-Regular normal_color txt-overflow">
                <p><span class="font-blod">Curve: </span>{{owner.Curve}}</p>
              </div>
              <div class="font-size14 font-Regular normal_color txt-overflow">
                <p><span class="font-blod">Value: </span>{{owner.Value}}</p>
              </div>
              <div class="font-size14 font-Regular normal_color txt-overflow">
                <p><span class="font-blod">PublicKeyId: </span>{{owner.PubKeyId}}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="row" v-if="haveData && JSON.stringify(Ddo.Attributes) !== '[]' && $route.params.net =='testnet' && workFlag">
      <div class="col">
        <div class="detail-col">
          <p>Claim Metadata</p>
          <div class="row" v-for="(claim, key) in Ddo.Attributes">
            <div class="col" v-if="claim.Claim">
              <div class="font-size14 font-Regular normal_color"><p>Claim Hash: {{claim.Claim.ClaimId}}</p></div>
              <div class="font-size14 font-Regular normal_color"><p>Claim Context: {{claim.Claim.ClaimContext}}</p></div>
              <div class="font-size14 font-Regular normal_color"><p>Context Desc: {{claim.Claim.ContextDesc}}</p></div>
              <div class="font-size14 font-Regular normal_color" @click="toTstIdDetailPage(claim.Claim.IssuerTstId)">
                <p>Issuer: <span class="important_color pointer">{{claim.Claim.IssuerTstId}}</span></p>
              </div>
<!--               <div v-if="claim.SelfDefined">
                <p class="font-size14 font-Regular normal_color">Claim SelfDefined: {{claim.SelfDefined}}</p>
              </div> -->
            </div>

          </div>
          <div class=" special-ddo-wrapper"   v-if="workFlag">
            <div class="work-group-wrapper">
              <span class="work-group-font">Group: </span>
              <span class="work-group-font">{{work.group}} </span>
            </div>
            <div class="work-name-wrapper">
              <span class="work-name-font">{{work.work_name}} </span>
            </div>
<!--             <div class="work-awards-wrapper" :class="work.awards.length > 3 ?'work-awards-wrapper-small':''">
              <span class="work-awards-font" :class="work.awards.length > 3 ?'work-awards-font-small':''">{{work.awards}} </span>
            </div> -->
            <div class="work-trustAnchor-wrapper">
              <span class="work-group-font">Trust Anchor: </span>
              <span class="work-group-font">{{work.trust_anchor}} </span>
            </div>
            <div class="work-description-wrapper">
              <!-- <p class="work-normal-font"><span class="work-normal-font">Description: </span>{{work.description.length > 120 ? work.description.substr(0,120)+'...':work.description}} </p> -->
              <p class="work-p-normal-font p-work-normal-font"><span class="work-normal-font">Description: </span>{{work.description}} </p>
            </div>
            <div class="work-cryptoFunction-wrapper">
              <span class="work-normal-font">crypto_function </span>
            </div>
            <div class="work-cryptoFunction-wrapper1">
              <span class="work-normal-font">{{work.crypto_function}} </span>
            </div>
            <div class="work-uploadTime-wrapper">
              <span class="work-normal-font">uploadTime </span>
            </div>
            <div class="work-uploadTime-wrapper1">
              <span class="work-normal-font">{{work.uploadTime}} </span>
            </div>
            <div class="work-logo-wrapper">
              <img src="../../assets/tstid/logoEN.png" class="work-logo-img" />
            </div>
            <div class="work-hash-wrapper">
              <span class="work-hash-font">Work HASH: </span>
              <span class="work-hash-font">{{work.work_HASH}} </span>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="row" v-show="!haveData">
      <div class="col">
        <div class="detail-col">{{ $t('tstIdDetail.failed') }}</div>
      </div>
    </div>

    <!-- Events on this TST ID -->
    <div class="row" v-if="TxnTotal">
      <div class="col">
        <div class="detail-col">
          <span class="font-blod">{{ $t('tstIdDetail.events') }}</span>
          <div class="table-responsive">
            <table class="table ">
              <thead>
              <tr>
                <th class="td-tx-head font-size18 normal_color font-Blod">{{ $t('all.hash') }}</th>
                <th class="td-tx-head font-size18 normal_color font-Blod">{{ $t('all.content') }}</th>
                <th class="td-tx-head font-size18 normal_color font-Blod">{{ $t('all.fee') }}</th>
                <th class="td-tx-head font-size18 normal_color font-Blod">{{ $t('all.time') }}</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="tx in TxnList">
                <td class="font-size14 important_color font-Regular pointer" @click="toTransactionDetailPage(tx.tx_hash)">
                  {{tx.tx_hash.substr(0,4) + '...' + tx.tx_hash.substr(60)}}
                </td>
                <td class="font-size14 font-Regular normal_color ">
                  {{getTstIDEvent(tx.description)}}
                </td>
                <td class="font-size14 font-Regular normal_color">
                  {{tx.fee}}
                </td>
                <td class="font-size14 font-Regular normal_color">
                  {{$HelperTools.getTransDate(tx.tx_time)}}
                </td>
              </tr>
              </tbody>
            </table>
          </div>
          <tst-pagination :total="TxnTotal"></tst-pagination>
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
        Ddo: {},
        claimflag: true,
        TxnList: {},
        TxnTotal: '',
        haveData: true,
        work:{
          awards:'',
          group:'',
          description:'',
          owner_id:'',
          work_name:'',
          trust_anchor:'',
          crypto_function:'',
          uploadTime:'',
          work_HASH:'',
        },
        workFlag:false
      }
    },
    mounted() {
      if (this.$route.params.pageSize == undefined || this.$route.params.pageNumber == undefined) {
        this.toTstIdDetailPageReload(this.$route.params.tstid)
      }else{
        this.getTstIdDetail()
      }
      document.body.scrollTop = document.documentElement.scrollTop = 0;
    },
    watch: {
      '$route': 'getTstIdDetail',
      'TstIdDetail': function () {
        console.log(this.TstIdDetail)

        if (this.TstIdDetail.info === false) {
          this.haveData = false
        } else {
          this.Ddo = this.TstIdDetail.Ddo
          this.TxnList = this.TstIdDetail.TxnList
          this.TxnTotal = this.TstIdDetail.TxnTotal
        }
        if(this.Ddo.Attributes[5].SelfDefined['trust anchor'] == 'GGCA'){
          this.workFlag = true
          this.work.awards = this.Ddo.Attributes[0].SelfDefined['awards']
          this.work.group = this.Ddo.Attributes[1].SelfDefined['group']
          this.work.description = this.Ddo.Attributes[2].SelfDefined['description']
          this.work.owner_id = this.Ddo.Attributes[3].SelfDefined['owner_id']
          this.work.work_name = this.Ddo.Attributes[4].SelfDefined['work name']
          this.work.trust_anchor = this.Ddo.Attributes[5].SelfDefined['trust anchor']
          this.work.crypto_function = this.Ddo.Attributes[6].SelfDefined['crypto_function']
          this.work.uploadTime = this.Ddo.Attributes[7].SelfDefined['uploadTime']
          this.work.work_HASH = this.Ddo.Attributes[8].SelfDefined['work HASH']
          /* console.log(this.work) */
        }
      },
      'TstIdDdo':function(){
        console.log(this.TstIdDdo)
        this.Ddo = this.TstIdDdo
      },
      'TstIdTxnList':function(){
        console.log(this.TstIdTxnList)
        this.TxnList = this.TstIdTxnList.records
        this.TxnTotal = this.TstIdTxnList.total
      }
    },
    computed: {
      ...mapState({
        TstIdDdo: state => state.TstIDs.Ddo,
        TstIdTxnList: state => state.TstIDs.Tx,
      })
    },
    methods: {
      toTstIdDetailPageReload($TstId) {
        if (this.$route.params.net == undefined) {
          this.$router.push({name: 'TstIdDetail', params: {tstid: $TstId,pageSize:10,pageNumber:1}})
        } else {
          this.$router.push({name: 'TstIdDetailTest', params: {tstid: $TstId,pageSize:10,pageNumber:1, net: "testnet"}})
        }
      },
      getTstIdDetail() {
        this.$store.dispatch('GetTstIdDdoDetail', this.$route.params).then()
        this.$store.dispatch('GetTstIdTxDetail', this.$route.params).then()
      },
      toReturn() {
        this.$router.go(-1)
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
          this.$router.push({name: 'TstIdDetail', params: {tstid: $TstId}})
        } else {
          this.$router.push({name: 'TstIdDetailTest', params: {tstid: $TstId, net: "testnet"}})
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
            return "create new claim"
        }
      }
    }
  }
</script>

<style scoped>
.special-ddo-wrapper{
    background-image: url(../../assets/tstid/dianqingback.png);
    background-repeat: no-repeat;
    background-size: 950px 699px;
    height: 699px;
    min-width: 1122px;
    background-position: center;
    margin-bottom: 32px;
}
.p-work-normal-font{
    position:relative;
    /* 3 times the line-height to show 3 lines */
    height:72px;
    overflow:hidden;
}
.p-work-normal-font::after {
    content:"...";
    font-weight:bold;
    position:absolute;
    bottom:0;
    right:0;
    padding:0 20px 1px 45px;
    background:url(http://newimg88.b0.upaiyun.com/newimg88/2014/09/ellipsis_bg.png) repeat-y;
    opacity: 1;
}
.work-normal-font{
    font-size:16px;
    font-family:SourceSansPro-Regular;
    font-weight:400;
    color:rgba(89,87,87,1);
    line-height:20px;
}
.work-name-font{
    font-size:30px;
    font-family:SourceSansPro-Regular;
    font-weight:blod;
    color:rgba(181,148,114,1);
    line-height:46px;
}
.work-p-normal-font{
    font-size:16px;
    font-family:SourceSansPro-Regular;
    font-weight:400;
    color:rgba(89,87,87,1);
    line-height:24px;
}
.work-group-font{
    font-size:20px;
    font-family:SourceSansPro-Regular;
    font-weight:400;
    color:rgba(89,87,87,1);
    line-height:24px;
}
.work-awards-font{
    font-size:34px;
    font-family:SourceSansPro-bold;
    font-weight:600;
    color:rgba(50,164,190,1);
    line-height:48px;
}
.work-awards-font-small{
    font-size:25px !important;
    font-family:SourceSansPro-bold;
    font-weight:600;
    color:rgba(50,164,190,1);
    line-height:48px;  
}
.work-hash-font{
    font-size:16px;
    font-family:SourceSansPro-Regular;
    font-weight:400;
    color:rgba(89,87,87,1);
    line-height:24px;
}
.work-name-wrapper{
    position: absolute;
    left: 600px;
    transform: translate(-50%,0);
    top: 205px;
    width: 520px;
    text-align: center;
}
.work-group-wrapper{
    position: absolute;
    left: 561px;
    transform: translate(-18%,0);
    top: 143px;
    min-width: 130px;
}
.work-awards-wrapper{
    position: absolute;
    left: 600px;
    transform: translate(-50%,0);
    top: 163px;
    min-width: 103px;
}
.work-awards-wrapper-small{
    position: absolute;
    left: 600px;
    transform: translate(-50%,0);
    top: 163px;
    min-width: 103px;
}
.work-trustAnchor-wrapper{
    position: absolute;
    left: 600px;
    transform: translate(-50%,0);
    top: 332px;
    min-width: 170px;
}
.work-description-wrapper{
    position: absolute;
    left: 600px;
    transform: translate(-50%,0);
    top: 407px;    
    width: 610px;
    height: 72px;
    overflow: hidden;
}
.work-cryptoFunction-wrapper{
    position: absolute;
    left: 369px;
    transform: translate(-50%,0);
    top: 580px;    
    /* border-top: 2px solid #4C4D66; */
    min-width: 146px;
    text-align: center;
}
.work-cryptoFunction-wrapper1{
    position: absolute;
    left: 369px;
    transform: translate(-50%,0);
    top: 624px;    
    /* border-top: 2px solid #4C4D66; */
    min-width: 146px;
    text-align: center;
}
.work-uploadTime-wrapper{
    position: absolute;
    left: 832px;
    transform: translate(-50%,0);
    top: 580px;
    /* border-top: 2px solid #4C4D66; */
    min-width: 146px;
    text-align: center;
}
.work-uploadTime-wrapper1{
    position: absolute;
    left: 832px;
    transform: translate(-50%,0);
    top: 624px;
    /* border-top: 2px solid #4C4D66; */
    min-width: 146px;
    text-align: center;
}
.work-logo-wrapper{
    position: absolute;
    left: 600px;
    transform: translate(-50%,0);
    top: 564px;
}
.work-logo-img{
    width: 155px;
    height: 74px;
}
.work-hash-wrapper{
    position: absolute;
    left: 600px;
    transform: translate(-50%,0);
    top: 686px;
    min-width: 590px;
}
</style>
