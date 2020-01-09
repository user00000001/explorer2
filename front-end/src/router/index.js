import Vue from 'vue'
import Router from 'vue-router'

import Home from '@/components/home/Index'
// import BlockListPage from '@/components/blocks/List'
// import BlockDetailPage from '@/components/blocks/Detail'
// import TstIdListPage from '@/components/tstId/List'
// import TstIdDetailPage from '@/components/tstId/Detail'
// import AddressDetailPage from '@/components/addresses/Detail'
// import AddressList from '@/components/addresses/List'
// import TransactionList from '@/components/transactions/List'
// import TransactionDetail from '@/components/transactions/Detail'
// import ClaimDetailPage from '@/components/claim/Detail'
// import ClaimVerifyPage from '@/components/claim/Verify'
// import NodeStakeAuthorization from '@/components/nodes/List'
// import NodeTeamDetail from '@/components/nodes/Detail'
// import ContractList from '@/components/contracts/List'
// import ContractDetail from '@/components/contracts/Detail'
// import TokenList from '@/components/tokens/List'
// import TokenDetail from '@/components/tokens/Detail'
// import StatisticsTable from '@/components/statistics/Table'
// import ContractForm from '@/components/form/ContractForm'

// const Home = () => import('@/components/home/Index')
const BlockListPage = () => import('@/components/blocks/List')
const BlockDetailPage = () => import('@/components/blocks/Detail')
const TstIdListPage = () => import('@/components/tstId/List')
const TstIdDetailPage = () => import('@/components/tstId/Detail')
const AddressDetailPage = () => import('@/components/addresses/Detail')
const AddressList = () => import('@/components/addresses/List')
const TransactionList = () => import('@/components/transactions/List')
const TransactionDetail = () => import('@/components/transactions/Detail')
const ClaimDetailPage = () => import('@/components/claim/Detail')
const ClaimVerifyPage = () => import('@/components/claim/Verify')
const NodeStakeAuthorization = () => import('@/components/nodes/List')
const NodeTeamDetail = () => import('@/components/nodes/Detail')
const ContractList = () => import('@/components/contracts/List')
const ContractDetail = () => import('@/components/contracts/Detail')
const TokenList = () => import('@/components/tokens/List')
const TokenDetail = () => import('@/components/tokens/Detail')
const TokenSubmit = () => import('@/components/tokens/submit')
const StatisticsTable = () => import('@/components/statistics/Table')
const ContractForm = () => import('@/components/form/ContractForm')

Vue.use(Router);

let routes = [
  {
    path: '/home',
    name: 'Home',
    component: Home
  },
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/blocks/:pageSize/:pageNumber',
    name: 'blockListDetail',
    component: BlockListPage
  },
  {
    path: '/block/:param',
    name: 'blockDetail',
    component: BlockDetailPage
  },
  {
    path: '/blocks/:pageSize/:pageNumber/:net',
    name: 'blockListDetailTest',
    component: BlockListPage
  },
  {
    path: '/block/:param/:net',
    name: 'blockDetailTest',
    component: BlockDetailPage
  },
  {
    path: '/tstidlist/:pageSize/:pageNumber',
    name: 'TstIdListDetail',
    component: TstIdListPage
  },
  {
    path: '/tstid/:tstid/:pageSize/:pageNumber',
    name: 'TstIdDetail',
    component: TstIdDetailPage
  },
  {
    path: '/tstid/:tstid',
    name: 'TstIdDetailWithoutPage',
    component: TstIdDetailPage
  },
  {
    path: '/address/:address/:assetName/:pageSize/:pageNumber',
    name: 'AddressDetail',
    component: AddressDetailPage
  },
  {
    path: '/address/:address/:pageSize/:pageNumber',
    name: 'AddressDetail',
    component: AddressDetailPage
  },
  {
    path: '/address/:address/',
    name: 'AddressDetailwithoutpage',
    component: AddressDetailPage
  },
  {
    path: '/addresses/:token/:pageSize/:pageNumber',
    name: 'addressList',
    component: AddressList
  },
  {
    path: '/addresses/:token/:pageSize/:pageNumber/:net',
    name: 'addressListTest',
    component: AddressList
  },
  {
    path: '/transactions/:pageSize/:pageNumber',
    name: 'TransactionList',
    component: TransactionList
  },
  {
    path: '/transactions/:pageSize/:pageNumber/:net',
    name: 'TransactionListTest',
    component: TransactionList
  },
  {
    path: '/transaction/:tx_hash',
    name: 'TransactionDetail',
    component: TransactionDetail
  },
  {
    path: '/transaction/:tx_hash/:net',
    name: 'TransactionDetailTest',
    component: TransactionDetail
  },
  {
    path: '/claimverify/:cardId/:ownerTstId',
    name: 'ClaimDetai',
    component: ClaimDetailPage
  },
  {
    path: '/claimverifyresult',
    name: 'ClaimVerify',
    component: ClaimVerifyPage
  },
  {
    path: '/:net',
    name: 'HomeTest',
    component: Home
  },
  {
    path: '/tstidlist/:pageSize/:pageNumber/:net',
    name: 'TstIdListDetailTest',
    component: TstIdListPage
  },
  {
    path: '/tstid/:tstid/:pageSize/:pageNumber/:net',
    name: 'TstIdDetailTest',
    component: TstIdDetailPage
  },
  {
    path: '/address/:address/:assetName/:pageSize/:pageNumber/:net',
    name: 'AddressDetailTest',
    component: AddressDetailPage
  },
  {
    path: '/address/:address/:pageSize/:pageNumber/:net',
    name: 'AddressDetailTest',
    component: AddressDetailPage
  },
  {
    path: '/address/:address/:net',
    name: 'AddressDetailTest',
    component: AddressDetailPage
  },
  {
    path: '/claimverify/:cardId/:ownerTstId/:net',
    name: 'ClaimDetaiTest',
    component: ClaimDetailPage
  },
  {
    path: '/claimverifyresult/:net',
    name: 'ClaimVerifyTest',
    component: ClaimVerifyPage
  },
  {
    path: '/nodes/list',
    name: 'NodeStakeList',
    component: NodeStakeAuthorization
  },
  {
    path: '/nodes/detail/:pk',
    name: 'NodeTeamDetail',
    component: NodeTeamDetail
  },
  {
    path: '/nodes/list/:net',
    name: 'NodeStakeListTestNet',
    component: NodeStakeAuthorization
  },
  {
    path: '/contract/list/:pageSize/:pageNumber',
    name: 'ContractList',
    component: ContractList
  },
  {
    path: '/contract/list/:pageSize/:pageNumber/:net',
    name: 'ContractListTest',
    component: ContractList
  },
  {
    path: '/contract/:contractType/:contractHash/:pageSize/:pageNumber',
    name: 'ContractDetail',
    component: ContractDetail
  },
  {
    path: '/contract/:contractType/:contractHash',
    name: 'ContractDetailwithoutpage',
    component: ContractDetail
  },
/*   {
    path: '/contract/:contractType/:contractHash/:pageSize',
    name: 'ContractDetailwithoutpageSize',
    component: ContractDetail
  }, */
  {
    path: '/contract/:contractHash/:pageSize/:pageNumber',
    name: 'ContractDetailwithoutType',
    component: ContractDetail
  },
  {
    path: '/contract/:contractHash',
    name: 'ContractDetailOnlyHash',
    component: ContractDetail
  },
  {
    path: '/contract/:contractType/:contractHash/:pageSize/:pageNumber/:net',
    name: 'ContractDetailTest',
    component: ContractDetail
  },
/*   {
    path: '/contract/RegistrationForm',
    name: 'ContractRegistrationForm',
    component: ContractRegistrationForm
  },
  {
    path: '/contract/RegistrationForm/:net',
    name: 'ContractRegistrationFormTest',
    component: ContractRegistrationForm
  }, */
  {
    path: '/token/list/:contractType/:pageSize/:pageNumber',
    name: 'TokenList',
    component: TokenList
  },
  {
    path: '/token/list/:contractType/:pageSize/:pageNumber/:net',
    name: 'TokenListTest',
    component: TokenList
  },
  {
    path: '/token/detail/:contractType/:contractHash/:tokenName/:pageSize/:pageNumber',
    name: 'TokenDetail',
    component: TokenDetail
  },
  {
    path: '/token/detail/:contractType/:contractHash/:tokenName/:pageSize/:pageNumber/:net',
    name: 'TokenDetailTest',
    component: TokenDetail
  },
  {
    path: '/token/submit',
    name: 'TokenSubmit',
    component: TokenSubmit
  },
  {
    path: '/token/submit/:net',
    name: 'TokenSubmitTest',
    component: TokenSubmit
  },
  {
    path: '/statistics/:day',
    name: 'Statistics',
    component: StatisticsTable
  },
  {
    path: '/statistics/:day/:net',
    name: 'StatisticsTest',
    component: StatisticsTable
  },
  {
    path: '/statistics/contract/:contractHash/:day',
    name: 'StatisticsContract',
    component: StatisticsTable
  },
  {
    path: '/statistics/contract/:contractHash/:day/:net',
    name: 'StatisticsContractTest',
    component: StatisticsTable
  },
  {
    path: '/form/contractform',
    name: 'ContractForm',
    component: ContractForm
  },
  {
    path: '/addresses/:pageSize/:pageNumber/:net',
    name: 'ContractFormTest',
    component: ContractForm
  },
];

const router = new Router({
  mode: 'history',
  routes: routes
});

export default router
