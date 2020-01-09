import $httpService from '../../common/utils'
import * as types from "../mutation-type"

export default {
  state: {
    LatestTransactionList: {
      info: ''
    }
  },
  mutations: {
    [types.SET_TRANSACTION_LIST](state, payload) {
      state.LatestTransactionList.info = payload.info
    }
  },
  actions: {
    getTransactionList({dispatch, commit},$param) {

      return $httpService.get('/latest-transactions?count=10').then(response => {
        commit({
          type: types.SET_TRANSACTION_LIST,
          info: response.result
        })
      }).catch(error => {
        console.log(error)
      })
    },
  }
}
