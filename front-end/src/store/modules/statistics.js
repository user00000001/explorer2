import axios from 'axios'
import $httpService from '../../common/utils'
import Helper from '../../helpers/helper'

import * as types from "../mutation-type"

/**
 * 设置区块链全部统计数据
 *
 * @param list
 * @return {{labels: Array, data: {block: {label: string, list: Array}, txn: {label: string, list: Array}, newAddress: {label: string, list: Array}, activeAddress: {label: string, list: Array}, newTstId: {label: string, list: Array}, activeTstId: {label: string, list: Array}, sumAddress: {label: string, list: Array}, sumTstId: {label: string, list: Array}, tst: {label: string, list: Array}, tsg: {label: string, list: Array}}}}
 */
function setAllData(list) {
  // 表格数据格式构造
  let chartData = {
    labels: [],
    data: {
      block: {label: 'blockLbl', list: []},
      txn: {label: 'txnLbl', list: []},
      newAddress: {label: 'newAddressLbl', list: []},
      activeAddress: {label: 'activeAddressLbl', list: []},
      newTstId: {label: 'newTstIdLbl', list: []},
      activeTstId: {label: 'activeTstIdLbl', list: []},
      sumAddress: {label: 'sumAddressLbl', list: []},
      sumTstId: {label: 'sumTstIdLbl', list: []},
      tst: {label: 'tstLbl', list: []},
      tsg: {label: 'tsgLbl', list: []}
    }
  };

  // 表格数据填充
  for (let listKey in list) {
    let time = Helper.HelperTools.getStatisticsTime(list[listKey].time)
    chartData.labels.push(time.length > 5 ? time.substr(0,5) : time);
    chartData.data.block.list.push(list[listKey].block_count);
    chartData.data.txn.list.push(list[listKey].tx_count);
    chartData.data.newAddress.list.push(list[listKey].new_address_count);
    chartData.data.activeAddress.list.push(list[listKey].active_address_count);
    chartData.data.newTstId.list.push(list[listKey].new_tstid_count);
    chartData.data.activeTstId.list.push(list[listKey].active_tstid_count);
    chartData.data.sumAddress.list.push(list[listKey].address_total);
    chartData.data.sumTstId.list.push(list[listKey].tstid_total);
    chartData.data.tst.list.push(list[listKey].tst_sum);
    chartData.data.tsg.list.push(list[listKey].tsg_sum)
  }

  return chartData;
}

/**
 * 设置合约统计数据
 *
 * @param url
 * @return {{labels: Array, data: {newAddress: {label: string, list: Array}, activeAddress: {label: string, list: Array}, tst: {label: string, list: Array}, tsg: {label: string, list: Array}, txn: {label: string, list: Array}}}}
 */
function setContractData(list) {
  // 表格数据格式构造
  let chartData = {
    labels: [],
    data: {
      newAddress: {label: 'newAddressLbl', list: []},
      activeAddress: {label: 'activeAddressLbl', list: []},
      tst: {label: 'tstLbl', list: []},
      tsg: {label: 'tsgLbl', list: []},
      txn: {label: 'txnLbl', list: []}
    }
  };

  // 表格数据填充
  for (let listKey in list) {
    let sctime = Helper.HelperTools.getStatisticsTime(list[listKey].time)
    chartData.labels.push(sctime.length > 5 ? sctime.substr(0,5) : sctime);
    chartData.data.newAddress.list.push(list[listKey].new_address_count);
    chartData.data.activeAddress.list.push(list[listKey].active_address_count);
    chartData.data.tst.list.push(list[listKey].tst_sum);
    chartData.data.tsg.list.push(list[listKey].tsg_sum);
    chartData.data.txn.list.push(list[listKey].tx_count)
  }

  return chartData;
}



export default {
  state: {
    StatisticsData: {info: ''},
    ScList: {}
  },
  mutations: {
    [types.SET_STATISTICS_DATA](state, payload) {
      state.StatisticsData = payload.info
    },
    [types.SET_SC_LIST](state, payload) {
      state.ScList = payload.info
    }
  },
  actions: {
    /**
     * 获取统计数据
     *
     * @param dispatch
     * @param commit
     * @param $param
     * @return {Promise<AxiosResponse | never>}
     */
    getStatisticsData({dispatch, commit}, $param) {
      let timestamp = (new Date()).valueOf();
      timestamp = parseInt(timestamp / 1000); // to second
      let days = typeof($param.day) !== "undefined" ? $param.day : '14';
      let startTimestamp = days == '14' ? timestamp - (86400 * days) - 86400:timestamp - (86400 * days); // 14 days or 30 days ago

      let apiUrl = ($param.net === "testnet") ? process.env.TEST_API_URL : process.env.API_URL;
      // let url = apiUrl + '/summary/daily/' + startTimestamp + '/' + timestamp;
       let url = `/summary/blockchain/daily?start_time=${startTimestamp}&end_time=${timestamp}`
      //let url = `/summary/blockchain/daily?start_time=1558051200&end_time=1558569600`

      // 如果有hash，说明是合约信息
      if (typeof($param.contractHash) !== "undefined") {
        // url = apiUrl + '/summary/contract/' + $param.contractHash + '/daily/' + startTimestamp + '/' + timestamp;
         url = `/summary/contracts/${$param.contractHash}/daily?start_time=${startTimestamp}&end_time=${timestamp}`
        //url = `/summary/contracts/01f7166c22f7164651e57c1ebec02a01960500d0/daily?start_time=${startTimestamp}&end_time=${timestamp}`
      }

      return $httpService.get(url).then(response => {
        let list = response.result.records;
        let chartData = list;

        if (typeof($param.contractHash) !== "undefined") {
          chartData = setContractData(list)
        } else {
          chartData = setAllData(list)
        }

        commit({
          type: types.SET_STATISTICS_DATA,
          info: chartData
        })
      }).catch(error => {
        console.log(error)
      })
    },

    /**
     * 获取合约列表
     *
     * @param dispatch
     * @param commit
     * @param $param
     * @return {Promise<AxiosResponse | never>}
     */
    getContractList({dispatch, commit}, $param) {
      let url = '/contract/100/1';

      return $httpService.get(url).then(response => {
        commit({
          type: types.SET_SC_LIST,
          info: response.data.Result.ContractList
        })
      }).catch(error => {
        console.log(error)
      })
    }
  }
}
