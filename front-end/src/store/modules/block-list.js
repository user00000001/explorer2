import axios from 'axios'
import * as types from "../mutation-type"
import $httpService from '../../common/utils'

export default {
  state: {
    LatestBlockList: {
      info: ''
    }
  },
  mutations: {
    [types.SET_BLOCK_LIST](state, payload) {
      state.LatestBlockList.info = payload.info
    }
  },
  actions: {
    getBlockList({dispatch, commit},$param) {

      return $httpService.get('/latest-blocks?count=10').then(response => {
        if(response.code === 0) {
          commit({
            type: types.SET_BLOCK_LIST,
            info: response.result
          })
        }
      }).catch(error => {
        console.log(error)
      })
    },
  }
}
