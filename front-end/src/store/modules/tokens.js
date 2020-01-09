import axios from 'axios'
import * as types from "../mutation-type"
import HelperTool from "./../../helpers/helper"
import $httpService from '../../common/utils'

export default {
  state: {
    List: {},
    Detail: {},
    Res: {}
  },
  mutations: {
    [types.SET_TOKENS_DATA](state, payload) {
      state.List = payload.info
    },
    [types.SET_TOKEN_DATA](state, payload) {
      state.Detail = payload.info
    },
    [types.SUBMIT_TOKEN_DATA](state, payload) {
      state.Res = payload.info
    }
  },
  actions: {

    GetTokens({dispatch, commit}, $param) {

      return $httpService.get('/tokens/'+$param.contractType,{
        params: {
          page_size: $param.pageSize,
          page_number: $param.pageNumber
        }
      }).then(response => {
        commit({
          type: types.SET_TOKENS_DATA,
          info: {
            list: response.result.records,
            total: response.result.total
          }
        })
      }).catch(error => {
        console.log(error)
      })
    },

    GetToken({dispatch, commit}, $param) {

      return $httpService.get('/tokens/'+$param.contractType+'/'+$param.contractHash).then(response => {
        commit({
          type: types.SET_TOKEN_DATA,
          info: {
            list: response.result
          }
        })
      }).catch(error => {
        console.log(error)
      })
    },

    submitToken({dispatch, commit}, $param) {
      let data = {}
      data.contract_hash = $param.tokenHash
      data.name = $param.tokenName
      data.description = $param.tokenDescription
      data.abi = $param.tokenAbi
      data.code = $param.tokenCode
      data.contact_info = $param.contact_info
      data.logo = $param.dataURL
      if($param.tokenType === "oep4s") {
        data.total_supply = $param.tokenTotalSupply
        data.decimals = $param.tokenDecimals
        data.symbol = $param.tokenSymbol
        data.vm_category = $param.vm_category
      }
      if($param.tokenType === "oep5s") {
        data.total_supply = $param.tokenTotalSupply
        data.decimals = $param.tokenDecimals
        data.symbol = $param.tokenSymbol
        data.vm_category = $param.vm_category
      }
      if($param.tokenType === "oep8s") {
        data.tokens = $param.tokens
        data.vm_category = $param.vm_category
      }
      return $httpService.post('/tokens/'+$param.tokenType+'/submit',data).then(response => {
        commit({
          type: types.SUBMIT_TOKEN_DATA,
          info: {
            list: response.result
          }
        })
      }).catch(error => {
        console.log(error.msg)
        commit({
          type: types.SUBMIT_TOKEN_DATA,
          info: {
            list: error
          }
        })
      })
    }
  }
}
