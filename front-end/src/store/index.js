import Vue from 'vue'
import Vuex from 'vuex'

import RunStatus from './modules/run-status'
import BlockList from './modules/block-list'
import TransactionList from './modules/transaction-list'
import TstIdList from './modules/tst-id-list'
import Blocks from './modules/blocks'
import Transactions from './modules/transactions'
import TstIDs from './modules/tst-ids'
import Addresses from './modules/addresses'
import ClaimDetailPage from './modules/claim-verify-page'
import NodeAuthorization from './modules/node-authorization'
import Contracts from './modules/contracts'
import Tokens from './modules/tokens'
import Statistics from './modules/statistics'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    RunStatus,
    TstIdList,
    TstIDs,
    BlockList,
    Blocks,
    TransactionList,
    Transactions,
    Addresses,
    ClaimDetailPage,
    NodeAuthorization,
    Contracts,
    Tokens,
    Statistics,
  }
})
