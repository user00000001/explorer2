import $httpService from '../../common/utils'
import * as types from "../mutation-type"

export default {
  state: {
    List: {},
    Detail: {}
  },
  mutations: {
    [types.SET_BLOCK_LIST_PAGE](state, payload) {
      state.List = payload.info
    },
    [types.SET_BLOCK_DETAIL_PAGE](state, payload) {
      state.Detail = payload.info;
    }
  },
  actions: {
    GetBlocks({dispatch, commit}, $param) {

      return $httpService.get('/blocks', {
        params: {
          page_size: $param.pageSize,
          page_number: $param.pageNumber
        }
      }).then(response => {
        let list = response.result.records

        // 将bookkeeper拆成数组
        for (let index in list) {
          list[index].BookKeeper = list[index].bookkeepers.split('&')
        }

        commit({
          type: types.SET_BLOCK_LIST_PAGE,
          info: {
            info: list,
            total: response.result.total
          }
        })
      }).catch(error => {
        console.log(error)
      })
    },
    GetBlock({dispatch, commit}, $param) {

      return $httpService.get('/blocks/'+$param.param).then(response => {
        let msg = response;
        let blockData = msg.result;

        // 将bookkeeper拆成数组
        blockData.BookKeeper = blockData.bookkeepers.split('&');

        commit({
          type: types.SET_BLOCK_DETAIL_PAGE,
          info: blockData
        })
      }).catch(error => {
        console.log(error)
      })
    }
  }
}
