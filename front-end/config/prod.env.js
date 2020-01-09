'use strict';

module.exports = {
  NODE_ENV: '"production"',

  // 单独提供给addresses页面的接口，golang实现的
  // EXPLORE_URL: '"http://localhost:8585/v2/"',
  // TEST_EXPLORE_URL: '"http://localhost:8585/v2/"',
  EXPLORE_URL: '"http://explorer.tesra.me:19998/"',
  TEST_EXPLORE_URL: '"http://explorer.tesra.me:3000/v2/"',

  // 基础API接口
  // API_URL: '"http://localhost:8585/v2"',
  // TEST_API_URL: '"http://localhost:8585/v2"',
  API_URL: '"http://explorer.tesra.me:3000/v2"',
  TEST_API_URL: '"http://explorer.tesra.me:3000/v2"',

  // 提供节点统计信息计算查询的接口：
  // DAPP_NODE_URL: '"http://localhost:25770"',
  // TEST_DAPP_NODE_URL: '"http://localhost:25770"',
  DAPP_NODE_URL: '"http://dapp2.tesra.me:25770"',
  TEST_DAPP_NODE_URL: '"http://dapp2.tesra.me:25770"',

  NET:true
};
