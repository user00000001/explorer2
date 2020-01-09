import $httpService from '../../common/utils'
import * as types from "../mutation-type"

export default {
  state: {
    BlockStatus: {
      info: ''
    },
    GenerateTime: {
      info: ''
    },
    NodeCount:{
      info: ''
    }
  },
  mutations: {
    [types.SET_RUN_STATUS](state, payload) {
      state.BlockStatus.info = payload.info
    },
    [types.SET_GENERATE_TIME](state, payload) {
      state.GenerateTime.info = payload.info
    },
    [types.SET_NODECOUNT_STATUS](state, payload) {
      state.NodeCount.info = payload.info
    }
  },
  actions: {
    getRunStatus({dispatch, commit}, $param) {
      return $httpService.get('/summary/blockchain/latest-info').then(response => {
        commit({
          type: types.SET_RUN_STATUS,
          info: response.result
        })
      }).catch(error => {
        console.log(error)
      })
    },
    getNodeCount({dispatch, commit}) {
      return $httpService.get('/nodes/count').then(response => {
        commit({
          type: types.SET_NODECOUNT_STATUS,
          info: response.result
        })
      }).catch(error => {
        console.log(error)
      })
    },
    generateTime({dispatch, commit}, $param) {
      return $httpService.get('/blocks/generate-time?count=' + $param.amount).then(response => {
        commit({
          type: types.SET_GENERATE_TIME,
          info: response.result
        })
      }).catch(error => {
        console.log(error)
      })
    }
  }
}
