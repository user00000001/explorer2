import $httpService from '../../common/utils'
import * as types from "../mutation-type"

export default {
  state: {
    LatestTstIdList: {
      info: ''
    }
  },
  mutations: {
    [types.SET_TST_ID_LIST](state, payload) {
      state.LatestTstIdList.info = payload.info
    }
  },
  actions: {
    getTstIdList({dispatch, commit}, $param) {
      return $httpService.get('/latest-tstids?count=5').then(response => {
        commit({
          type: types.SET_TST_ID_LIST,
          info: response.result
        })
      }).catch(error => {
        console.log(error)
      })
    },
  }
}
