<template>
  <div class="e-container margin-top-15 statistics-container">
    <list-title :name="$route.params.day + $t('statistics.day') + titName + $t('statistics.name')"></list-title>

    <div class="e-container">
      <div class="row">
        <div class="col">
          <div class="btn-group">
            <button type="button"
                    :disabled="typeof(this.$route.params.contractHash) === 'undefined'"
                    @click="toAllStatistics"
                    :class="typeof(this.$route.params.contractHash) === 'undefined' ? 'btn-current' : 'btn-choose'"
                    class="btn">All</button>
<!--             <div class="btn-group">
              <button type="button" class="btn btn-choose dropdown-toggle" data-toggle="dropdown">
                Contract
              </button>
              <div class="dropdown-menu">
                <a class="dropdown-item" href="#"
                   v-for="scl in scList"
                   @click="toScStatistics(scl.ContractHash)"
                >
                  {{ scl.Name }}
                </a>
              </div>
            </div> -->
          </div>
        </div>
        <div class="col text-right">
          <div class="btn-group">
            <button type="button"
                    :disabled="$route.params.day === '14'"
                    @click="toStatistics('14')"
                    :class="$route.params.day === '14' ? 'btn-current' : 'btn-choose'"
                    class="btn">{{ '14' + $t('statistics.day') }}</button>
            <!-- <button type="button"
                    :disabled="$route.params.day === '30'"
                    @click="toStatistics('30')"
                    :class="$route.params.day === '30' ? 'btn-current' : 'btn-choose'"
                    class="btn btn-left-0-border">{{ '30' + $t('statistics.day') }}</button> -->
          </div>
        </div>
      </div>

      <!--line-chart models-->
      <div class="row">
        <div class="col-xl-6 col-lg-6 col-md-12 col-sm-12" v-for="(item,index) in data.data" >
          <line-chart class="line-chart-style"
            :labels="data.labels"
            :label=" $route.params.day + $t('statistics.day') + $t('statistics.' + item.label)"
            :data="item.list"
            options="">
          </line-chart>
        </div>
      </div>
      <!--high-chart models-->
<!--       <div class="row">
        <div class="col-xl-6 col-lg-6 col-md-12 col-sm-12" v-for="(item,index) in data.data">
          <hi-chart class="line-chart-style"
            :labels="data.labels"
            :label=" $route.params.day + $t('statistics.day') + $t('statistics.' + item.label)"
            :data="item.list"
          ></hi-chart>
        </div>
      </div> -->

    </div>

  </div>
</template>

<script>
  import {mapState} from 'vuex'
  import HiChart from '../common/HiChart.vue'

  export default {
    name: "StatisticsTable",
    mounted() {
      this.getTableData();
      document.body.scrollTop = document.documentElement.scrollTop = 0;
     // this.getContractList()
    },
    watch: {
      '$route': ['getTableData'],
      'statistics.day':['getTableData'],
    },
    computed: {
      ...mapState({
        data: state => state.Statistics.StatisticsData,
        scList: state => state.Statistics.ScList
      }),
      titName: function () {
        for (let scIndex in this.scList) {
          if (this.scList[scIndex].ContractHash === this.$route.params.contractHash) {
            return this.scList[scIndex].Name + ' '
          }
        }

        return ''
      }
    },
    data() {
      return {
        chartid: ['chart0','chart1','chart2','chart3','chart4','chart5','chart6','chart7','chart8','chart9',],
        /* updateArgs: [true, true, {duration: 1000}], */
        chartOptions: {
          chart: {
            type: 'area'
          },
          title: {
            text: ''
          },
          credits: {
              enabled: false
          },
          xAxis: {
            categories: []
          },
          series: [{
            name:'',
            data: [],
            color: '#6fcd98'
          }]
        }
      }
    },
    methods: {
      toStatistics($days) {
        let name = 'Statistics';
        let params = {day: $days};

        if (typeof(this.$route.params.contractHash) !== "undefined") {
          params.contractHash = this.$route.params.contractHash;
          name = name + 'Contract'
        }

        if (this.$route.params.net === 'testnet') {
          params.net = "testnet";
          name = name + 'Test'
        }

        this.$router.push({name: name, params: params});

        location.reload()
      },
      toScStatistics($hash) {
        this.$route.params.contractHash = $hash;
        this.toStatistics(this.$route.params.day)
      },
      toAllStatistics() {
        delete(this.$route.params.contractHash);
        this.toStatistics(this.$route.params.day)
      },
      getTableData() {
        this.$store.dispatch('getStatisticsData', this.$route.params).then()
      },
      getContractList() {
        this.$store.dispatch('getContractList', this.$route.params).then()
      }
    },
    components: {
      HiChart
    }
  }
</script>

<style scoped>
  .statistics-container .dropdown-menu {
    border-radius: 0;
  }

  .btn-current,
  .btn-choose {
    border-radius: 0;
    min-width: 96px;
    height: 32px;
    line-height: 10px;
  }
  .btn-current {
    border: 1px solid #e4e4e4;
    color: #e4e4e4;
  }

  .btn-choose {
    border: 1px solid #4C4D66;
    color: #4C4D66;
  }

  .line-chart-style {
    width: 100%;
    height: 240px;
    margin: 30px 0;
    background-color: white;
  }
</style>
