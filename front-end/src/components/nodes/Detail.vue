<template>
  <div class="e-container container-margin-top">
    <list-title :name="$t('nodes.detailTit')"></list-title>
    <detail-title-2 :name1="$t('nodes.nodeName')" :val="nodeDetailInfo.name"
                    :name2="$t('nodes.stakeTWallet')" url="https://github.com/TesraSupernet/TWallet/releases">
    </detail-title-2>

    <!--The TST-ID and Reward-rate-->
    <detail-block-2 :name1="$t('nodes.address')" :val1="nodeDetailInfo.address" :rows1="'1.2'" :params1="['address', nodeDetailInfo.address]"
                    :name2="$t('nodes.rewardRate')" :val2="nodeDetailInfo.node_proportion" :rows2="'1.1'" :tip="'true'" :tipTit="$t('nodes.tooltipTit')">
    </detail-block-2>

    <!--The Rank, Stake and Process.-->
    <div class="row">
      <div class="col">
        <div class="detail-col detail-col-left">
          <span class="f-color">{{ $t('nodes.rank2') }}</span>
          <span class="pointer important_color">{{nodeDetailInfo.node_rank}}</span>
        </div>
      </div>
      <div class="col">
        <div class="detail-col detail-col-middle">
          <span class="f-color">{{ $t('nodes.stake2') }}</span>
          <span class="pointer normal_color">{{nodeDetailInfo.current_stake}}</span>
        </div>
      </div>
      <div class="col">
        <div class="detail-col detail-col-right">
          <span class="f-color">{{ $t('nodes.process2') }}</span>
          <span class="pointer normal_color">{{nodeDetailInfo.progress}}</span>
        </div>
      </div>
    </div>

    <!--The TST-ID and Reward-rate-->
    <detail-block-2  v-if="" :name1="$t('nodes.ip')" val1="Undisclosed"
                    :name2="$t('nodes.website')" :val2="nodedetail.website?nodedetail.website:'Undisclosed'" :params2="nodedetail.website?nodedetail.website:'Undisclosed'">
    </detail-block-2>

    <!--The Intro, Vision and Website.-->
    <detail-block :params="detailParams"></detail-block>
  </div>
</template>

<script>
  import {mapState} from 'vuex'

	export default {
    name: "node-team-detail",
    data() {
      return {
        nodeDetailInfo: {}
      }
    },
    created() {
      window.open("http://node.tesra.me/",'_self')
    },
    mounted() {
      this.getNodeList()
      this.getNodeDetail()
      document.body.scrollTop = document.documentElement.scrollTop = 0;
    },
    watch: {
      'nodelist': function () {
        for (let i in this.nodelist) {
          if (this.nodelist[i].public_key === this.$route.params.pk) {
            this.nodeDetailInfo = this.nodelist[i];
            break
          }
        }
      },
      'nodedetail':function(){
        console.log(this.nodedetail.website)
        this.nodeDetailInfo.Website = this.nodedetail.website
        console.log(this.nodeDetailInfo)
      }
    },
    computed: {
      ...mapState({
        nodelist: state => state.NodeAuthorization.nodelist,
        nodedetail: state => state.NodeAuthorization.nodedetail
      }),
      detailParams: function () {
        return [
          {name: this.$t('nodes.intro'), val: this.nodedetail.introduction ? this.nodedetail.introduction:'Undisclosed' , rows: 2},
/*           {name: this.$t('nodes.vision'), val: this.nodeDetailInfo.Vision ? this.nodeDetailInfo.Vision :'', rows: 2} */
        ]
      }
    },
    methods: {
      getNodeList(){
        this.$store.dispatch('getNodelist', this.$route.params).then();
      },
      getNodeDetail(){
        this.$store.dispatch('getNodeDetail', this.$route.params).then();
      },
    }
  }
</script>

<style scoped>

</style>
