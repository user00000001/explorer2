// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './components/App'
//import "./theme/index.css"; // element-ui css
import router from './router'
import store from './store/index'
import VeeValidate from 'vee-validate';
import i18n from "./common/lang"; // Internationalization
import './components/Toast/toast.css';

Vue.use(VeeValidate);
import Toast from './components/Toast/index';
Vue.use(Toast);

import Helper from './helpers/helper'
Vue.use(Helper);

import {
  Pagination,
  Select,
  Option,
  Dialog,
  Tabs,
  TabPane,
  Upload,
  Table,
  Button,
  Radio,
  popover,
  Icon,
  TableColumn,
  Divider,
  Message,
} from "element-ui";
Vue.component(Pagination.name, Pagination);
Vue.prototype.$message = Message;
Vue.use(Select)
Vue.use(Option)
Vue.use(Dialog)
Vue.use(Tabs)
Vue.use(Table)
Vue.use(TableColumn)
Vue.use(TabPane)
Vue.use(popover)
Vue.use(Radio)
Vue.use(Button)
Vue.use(Icon)
Vue.use(Divider)
Vue.use(Upload)
/* import Highcharts from 'highcharts/highstock';
Vue.use(Highcharts);
import HighchartsVue from 'highcharts-vue';
Vue.use(HighchartsVue) */

// 提取加载css文件，构建时让webpack打包压缩优化。
import '../static/css/detail-block.css'
import '../static/css/detail-tab.css'
import '../static/css/loading.css'
import '../static/css/main.css'
import '../static/css/tst-element.css'

import service from './common/utils'
Vue.prototype.$httpService = service;
/**
 * 全局组件注册
 */
import NavBar from './components/common/NavBar'
import NavBar1 from './components/common/NavBar1'
import ListTitle from './components/common/ListTitle'
import ListTitle1 from './components/common/ListTitle1'
import TstPagination from './components/common/TstPagination'
import DetailTitle from './components/common/DetailTitle'
import DetailTitle1 from './components/common/DetailTitle1'
import DetailTitle2 from './components/common/DetailTitle2'
import DetailBlock from './components/common/DetailBlock'
import DetailBlock2 from './components/common/DetailBlock2'
import DetailBlock3 from './components/common/DetailBlock3'
import LineChart from './components/common/LineChart'
import HiChart from './components/common/HiChart'
import Loading from './components/common/Loading'

Vue.component('nav-bar', NavBar);
Vue.component('nav-bar-1', NavBar1);
Vue.component('list-title', ListTitle);
Vue.component('list-title-1', ListTitle1);
Vue.component('tst-pagination', TstPagination);
Vue.component('detail-title', DetailTitle);
Vue.component('detail-title-1', DetailTitle1);
Vue.component('detail-title-2', DetailTitle2);
Vue.component('detail-block', DetailBlock);
Vue.component('detail-block-2', DetailBlock2);
Vue.component('detail-block-3', DetailBlock3);
Vue.component('line-chart', LineChart);
Vue.component('hi-chart', HiChart);
Vue.component('o-load', Loading);

/**
 * Fixed compatibility issues with low version IE. lyx.
 */
import promise from 'es6-promise'
promise.polyfill();


Vue.config.productionTip = false;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  i18n,
  components: {App, ListTitle},
  template: '<App/>',
});
