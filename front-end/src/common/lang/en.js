module.exports = {
  language: {
    zhName: '简体中文',
    enName: 'English',
    zh: 'ZH',
    en: 'EN',
  },
  navbar: {
    flag:"en",
    blockHeight: 'block height',
    wallet: 'wallet',
    blocks: 'Blocks',
    addrs: 'Addresses',
    tarns: 'Transactions',
    tstIds: 'TST ID List',
    top: {
      blockchain: 'Blockchain',
      contracts: 'Contract',
      tokens: 'Tokens',
      tstId: 'TST ID',
      apis: 'APIs',
      mainNet: 'Main Chain',
      testNet: 'Computing Chain',
      nodes: 'Node',
      stake: 'Stake Authorizations',
      nodeMap: 'Node Map',
      blocks: 'Block',
      txns: 'Transaction',
      accounts: 'Accounts',
      oep4: 'OEP-4 Overview',
      oep5: 'OEP-5 Overview',
      oep8: 'OEP-8 Overview',
      submit: 'Submit',
      statistics: 'Statistic',
      tool: 'Tool',
      apiDocUrl:"http://docs.tesra.me"
    }
  },
  all: {
    more: '>>',
    return: '  Back',
    block: 'Block',
    hash: 'Hash',
    status: 'Status',
    time: 'Time',
    height: 'Height',
    byte: 'byte',
    num: 'Number',
    fee: 'Fee',
    amount: 'Amount',
    address: 'Address',
    tstId: 'TST ID',
    content: 'Content',
    confirmed: 'Confirmed',
    failed: 'Failed',
    description: 'Description',
    token: 'Token',
    txn: 'Transaction',
    txns: 'Transactions',
    pk:"Public Key"
  },
  tstPagination: {
    total: 'A total of ',
    data: ' data'
  },
  searchInput: {
    search: 'SEARCH',
    placeholder: 'Block Height, TX Hash, Contract Hash, Address',
    slogan: 'Blockchain Explorer'
  },
  runStatus: {
    name: 'The whole network operation status',
    RunningTime: 'Whole Network Run Time:',
    CurrentHeight: 'Block Height',
    BlockInterval: 'Average Block Time',
    TxnCount: 'Total Transactions',
    NodeCount: 'Consensus Nodes',
    addressCount: 'Total addresses',
    second: 's',
    address: "addresses",
    tstid: "Total TST ID"
  },
  tstIdList: {
    name: 'TST ID EVENTS'
  },
  tstIdDetail: {
    nickname: 'TST ID DETAILS',
    name: 'TST ID: ',
    msgName: 'TST ID Event Log',
    TxnId: 'Transaction ID',
    Height: 'Block height',
    TxnType: 'Transaction Type',
    TxnTime: 'Block Time',
    Description: 'Description',
    events: 'Events on this TST ID',
    owner: 'Owner',
    failed: 'FAIL, not found.'
  },
  blockList: {
    name: 'BLOCKS',
    Height: 'Height',
    TxnNum: 'Txns',
    bookkeeper: 'Bookkeeper',
    BlockSize: 'Size',
    BlockTime: 'Time',
    bookkeeperCount: 'Bookkeeper'
  },
  addressList: {
    rank: 'Rank',
    name: 'Address',
    nickname: 'ADDRESSES - Position Ranking',
    balance: ' Balance',
    percent: 'Percent'
  },
  addressDetail: {
    name: 'ADDRESS DETAILS',
    address: 'Address: ',
    tstBalance: 'TST Balance: ',
    tsgBalance: 'TSG Balance: ',
    claimable: 'Claimable TSG: ',
    unbound: 'Unbound TSG: ',
    txOnAddr: 'Transactions on this Address:',
    txns: 'Transactions:',
    oep8Assets: 'OEP-8 Assets:',
    oep4Assets: 'OEP-4 Assets:',
    oep5Assets: 'OEP-5 Assets:',
    oepOtherAssets: 'Other OEP Assets:'
  },
  assetName: {
    tst: 'TST',
    tsg: 'TSG',
    pumpkin01: 'Red Pumpkin',
    pumpkin02: 'Orange Pumpkin',
    pumpkin03: 'Yellow Pumpkin',
    pumpkin04: 'Green Pumpkin',
    pumpkin05: 'Indigo Pumpkin',
    pumpkin06: 'Blue Pumpkin',
    pumpkin07: 'Purple Pumpkin',
    pumpkin08: 'Golden Pumpkin'
  },
  blockDetail: {
    nickname: 'BLOCK DETAILS',
    name: 'Height:',
    BlockSize: 'Size:',
    BlockTime: 'Time:',
    keeper: 'Bookkeeper:',
    hash: 'Hash:',
    TxnNum: 'Number Of Transactions In The Block:',
    Hash: 'The Block Hash:',
    PrevBlock: 'Previous Block:',
    LastBlock: 'Last Block:',
    NextBlock: 'Next Block:',
    merkle: 'Merkle Root:',
    Consensus: 'Consensus Data:',
    txOnBlock: 'Transactions on this block'
  },
  transList: {
    name: 'TRANSACTIONS'
  },
  txDetail: {
    name: 'TRANSACTION DETAILS',
    txHash: 'Hash:',
    time: 'Time: ',
    type: 'Type: ',
    deploySC: 'Deploy Smart Contract',
    sc: 'Invoke Smart Contract',
    wasmsc: 'Invoke wasmvm Contract',
    height: 'Height: ',
    status: 'Status: ',
    fee: 'Fee: ',
    issuer: 'Issuer TstId',
    detial:'Event: ',
    open:'Detail',
    close:'Close'
  },
  claimDetail: {
    name: 'CLAIM DETAILS'
  },
  addressMsg: {
    name: 'Address message',
    address: 'Address',
    latestDealTime: 'Latest Deal Time',
    dealNumber: 'Deal Number',
    addressGenerationTime: 'Address Generation Time',
    balance: 'Balance'
  },
  page: {
    First: "<<",
    PreviousPage: " < ",
    NextPage: " > ",
    Last: ">>"
  },
  nodes: {
    stakeListTit: 'STAKE AUTHORIZATIONS',
    rank: 'Rank',
    name: 'Name',
    proportion: 'Reward proportion in next round Node / User',
    reward: 'Reward',
    stake: 'Stake',
    process: 'Process',
    toNextRound: 'To next round: ',
    blocks: 'blocks',
    detailTit: 'NODE DETAILS',
    nodeName: 'Node Name: ',
    tstId: 'TST ID:',
    address: 'Address:',
    rewardRate: 'Reward Rate:',
    rank2: 'Rank:',
    stake2: 'Stake:',
    stakeTWallet: 'Stake with TWallet →',
    process2: 'Process:',
    ip: 'IP:',
    email: 'Email:',
    intro: 'Introduction:',
    vision: 'Vision:',
    website: 'Website:',
    guideTxt: 'To participate stake authorization, find the step-by-step guide ',
    here: 'here.',
    tooltipTit: 'This is the portion of rewards received the node has chosen to share with users who have authorized stakes with them in the next consensus round.',
    processTip: 'Calculating real-time data. Please wait: '
  },
  contracts: {
    list: {
      name: 'Contracts',
      tit: {
        currently: 'There are currently',
        contracts: 'contracts',
        checkIn: 'Join'
      },
      tab: {
        hash: 'Hash',
        name: 'Name',
        time: 'Created Time',
        txns: 'Transactions',
        desc: 'Description',
        logo: 'Logo'
      }
    },
    detail: {
      name: 'Contract Detail',
      hash: 'HASH:',
      overview: 'Contract Overview',
      desc: 'Description',
      txOn: 'Number Of Transactions In The Contract:',
      creator: 'Creator: ',
      createdTime: 'Created Time: ',
      contractInfo: 'Info',
      logo: 'Logo',
      tstFlow: 'TST Flow: ',
      tsgFlow: 'TSG Flow: ',
      amount: 'Total Flow: ',
    }
  },
  tokens: {
    list: {
      name: 'Token List',
      tab: {
        hash: 'Hash',
        name: 'Name',
        time: 'Created Time',
        txns: 'Transactions',
        desc: 'Description',
        logo: 'Logo',
        symbol: 'Symbol',
        creator: 'Creator',
        totalSupply: 'Total Supply',
        decimals: 'Decimals',
        addressCount: 'Addresses'
      }
    },
    detail: {
      name: 'Token Detail',
      hash: 'HASH:',
      overview: 'Contract Overview',
      desc: 'Description',
      txOn: 'Number Of Transactions In The Contract:',
      creator: 'Creator: ',
      createdTime: 'Created Time: ',
      contractInfo: 'Info',
      logo: 'Logo',
      tstFlow: 'TST Flow: ',
      tsgFlow: 'TSG Flow: ',
      amount: 'Total Flow: ',
      txn: 'Transactions',
      volume: 'Volume',
      code: 'ByteCode',
      abi: 'ABI',
      totalSupply: 'Total Supply: ',
      decimals: 'Decimals: ',
      statistic: 'Statistics',
      tokenLbl: 'Token Label',
      tokenImg: 'Token Image',
      tip: 'Please note: Here is daily cumulative statistics, there is up to 10 minutes statistical difference.'
    }
  },
  statistics: {
    name: 'History Statistics',
    day: ' Day ',
    newAddressLbl: 'New Address',
    activeAddressLbl: 'Active Address',
    blockLbl: 'New Block',
    newTstIdLbl: 'New TST-ID',
    activeTstIdLbl: 'Active TST-ID',
    sumAddressLbl: 'Address Growth',
    sumTstIdLbl: 'TST-ID Growth',
    txnLbl: 'Transaction History',
    tstLbl: 'TST Amount',
    tsgLbl: 'TSG Amount'
  },
  footer: {
    flag:"en",
    Development: 'Development',
    DeveloperCenter: 'Developer Center',
    dApp:"dApps List",
    Tutorials: 'Tutorials',
    Documentation: 'Documentation',
    Bounty: 'Bounty',
    SmartX: 'SmartX',
    Github: 'Github',
    Cooperation: 'Cooperation',
    CooperateWithTesraSupernet: 'Cooperate with TesraSupernet',
    TrustEcosystem: 'Trust Ecosystem',
    Nodes: 'Nodes',
    About: 'About',
    Press: 'Press',
    TechNews: 'Tech News',
    Team: 'Team',
    AboutTesraSupernet: 'About TesraSupernet',
    WhitePaper: 'White Paper',
    Explorer: 'Explorer',
    dApps: 'dApps',
    dAppsList: 'dApps List',
    SubmitAdApp: 'Submit a dApp',

  },
  form:{
    contractForm:"CONTRACT REGISTRATION FORM",
    name:"Project name",
    logo:"Project logo",
    description:"Project description",
    webLink:"Project official website and social media links",
    gitHubURL:"Project GitHub URL",
    TSTID:"TST ID",
    contractHash:"Contract hash ",
    contractABI:"Contract ABI ",
    contractByteCode:"Contract ByteCode ",
    add:"Add",
    delete:"Delete",
    addFile:"Add File",
    required:"Required",
    requiredtext:"* Required",
    requiredstar:"*",
    
  },
  error:{
    format:"Sorry, the search string you entered could not be found.",
    copied:"Copied!",
    pagesize:"pageSize limit 20",
    searching:"Querying",
    msg1:"Please enter Token hash",
    msg2:"Please enter the token name",
    msg3:"Please enter Token Abi",
    msg4:"Please enter Token Code",
    msg5:"Please enter Token Description",
    msg6:"Please enter Token Symbol",
    msg7:"Please enter Token Decimals",
    msg8:"Please enter Token Total Supply",
    msg9:"Please upload Token Logo",
    msg10:"Please enter the email in the correct format",
    msg11:"Please enter the contact email address",
    msg12:"Please enter information for at least one token",
    msg13:"Please select a virtual machine category",
    msg14:"Please enter OEP8 Token ID",
    msg15:"Please enter OEP8 Token Name",
    msg16:"Please enter OEP8 Token Total Supply",
    msg17:"Please enter OEP8 Token Symbol",
    msg18:"OEP8 Token ID is duplicated",
    msg19:"The token hash you submitted could not be found",
    msg20:"The token has been submitted",
    msg21:"Token total supply range 1-999999999999999"
  }
};
