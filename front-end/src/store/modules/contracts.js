import axios from 'axios'
import * as types from "../mutation-type"
import HelperTool from "./../../helpers/helper"
import $httpService from '../../common/utils'

export default {
  state: {
    List: {},
    Detail: {},
    TxList: {}
  },
  mutations: {
    [types.SET_CONTRACTS_DATA](state, payload) {
      state.List = payload.info
    },
    [types.SET_CONTRACT_DATA](state, payload) {
      state.Detail = payload.info
    },
    [types.SET_CONTRACT_TX_DATA](state, payload) {
      state.TxList = payload.info
    }
  },
  actions: {
    /**
     * Get Contract List Data
     *
     * @param dispatch
     * @param commit
     * @param $param
     * @return {Promise<AxiosResponse | never>}
     */
    GetContracts({dispatch, commit}, $param) {

      return $httpService.get('/contracts',{
        params: {
          page_size: $param.pageSize,
          page_number: $param.pageNumber
        }
      }).then(response => {
        commit({
          type: types.SET_CONTRACTS_DATA,
          info: {
            list: response.result.records,
            total: response.result.total
          }
        })
      }).catch(error => {
        console.log(error)
      })
    },
    /**
     * Get Contract Detail Data
     *
     * @param dispatch
     * @param commit
     * @param $param
     * @return {Promise<AxiosResponse | never>}
     */
    GetContract({dispatch, commit}, $param) {
      return $httpService.get('/contracts/'+$param.contractHash).then(response => {
        let list = response.result;

        // string to json
        list.contact_info = HelperTool.HelperTools.strToJson(list.contact_info);

        commit({
          type: types.SET_CONTRACT_DATA,
          info: {
            list: list
          }
        })
      }).catch(error => {
        console.log(error)
      })
    },
    GetContractTx({dispatch, commit}, $param) {
      var url = ""
      /* 存在直接从搜索框进入的情况，此时无法得知合约的type */
      if($param.contractType == "all"){
        url = '/contracts/'+$param.contractHash+'/transactions'
      }else{
        url = '/contracts/'+$param.contractType +'/'+$param.contractHash+'/transactions'
      }
      return $httpService.get(url,{
        params: {
          page_size: $param.pageSize,
          page_number: $param.pageNumber
        }
      }).then(response => {
        // string to json

        commit({
          type: types.SET_CONTRACT_TX_DATA,
          info: {
            list: response.result.records,
            total: response.result.total
          }
        })
      }).catch(error => {
        console.log(error)
      })
    },
  }
}
