import axios from 'axios'
import * as types from "../mutation-type"
import $httpService from '../../common/utils'

export default {
  state: {
    List: {},
    Detail: {},
    SCDetail: {}
  },
  mutations: {
    [types.SET_TRANSACTION_LIST_PAGE](state, payload) {
      state.List = payload.info
    },
    [types.SET_TRANSACTION_DETAIL_PAGE](state, payload) {
      state.Detail = payload.info
    },
    [types.SET_TRANSACTIONSC_DETAIL_PAGE](state, payload) {
      state.SCDetail = payload.info
    }
  },
  actions: {
    GetTransactions({dispatch, commit}, $param) {
      let apiUrl = ($param.net === "testnet") ? process.env.TEST_API_URL : process.env.API_URL;

      return $httpService.get('/transactions',{        
        params: {
          page_size: $param.pageSize,
          page_number: $param.pageNumber
        }
      }).then(response => {
        commit({
          type: types.SET_TRANSACTION_LIST_PAGE,
          info: {
            list: response.result.records,
            total: response.result.total
          }
        })
      }).catch(error => {
        console.log(error)
      })
    },
    GetTransaction({dispatch, commit}, $param) {
      
      return $httpService.get('/transactions/' + $param.tx_hash).then(res => {
        commit({
          type: types.SET_TRANSACTION_DETAIL_PAGE,
          info: res.result,
        })
      }).catch(err => {
        console.log(err)
      })
    },
    GetTransactionDetail({dispatch, commit}, $param) {
      let apiUrl = ($param.net === "testnet") ? process.env.TEST_DAPP_NODE_URL : process.env.DAPP_NODE_URL;

      return axios.get( apiUrl+'/api/v1/smartcode/event/txhash/'+$param.tx_hash).then(response => {
        var msg = JSON.parse(response.request.response)
        commit({
          type: types.SET_TRANSACTIONSC_DETAIL_PAGE,
          info: msg.Result
        })
      }).catch(error => {
        console.log(error)
      })
    },
  }
}
