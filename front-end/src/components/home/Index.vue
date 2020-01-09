<template>
  <div>
    <div class="container-top">
      <nav-bar-1 class="nav-pc-display"></nav-bar-1>
      <nav-bar  class="nav-mobile-display"></nav-bar>

      <search-input></search-input>
    </div>

    <run-status></run-status>

    <div class="e-container">
      <div class="row list-wrapper">
        <div class="list-item">
          <block-list></block-list>
        </div>
        <div class="list-item">
          <transaction-list></transaction-list>
        </div>
        <!-- <div class="col-sm-12 col-md-6 col-lg-4 col-xl-4 list-item">
          <TstIdList></TstIdList>
        </div> -->
      </div>
    </div>
  </div>
</template>

<script>
  import SearchInput from './../home/SearchInput'
  import RunStatus from "./RunStatus";
  import BlockList from "./BlockList";
  import TransactionList from "./TransactionList";
  import TstIdList from "./TstIdList";

  export default {
    name: 'Home',
    created() {
      console.log(this.$route)
    },
    components: {
      SearchInput,
      RunStatus,
      BlockList,
      TransactionList,
      TstIdList
    }
  }
</script>

<style>
  .container-top {
    height: 360px;
    background-size: 100% 100%;
    background: url('../../assets/navbar/banner.png') no-repeat;
    background-position: center center;
    margin-bottom: 120px;
    /* background-image: -ms-linear-gradient(bottom, #2C92A5 0%, #37B6D3 100%);
    background-image: -moz-linear-gradient(bottom, #2C92A5 0%, #37B6D3 100%);
    background-image: -o-linear-gradient(bottom, #2C92A5 0%, #37B6D3 100%);
    background-image: -webkit-gradient(linear, left bottom, left top, color-stop(0, #2C92A5), color-stop(100, #37B6D3));
    background-image: -webkit-linear-gradient(bottom, #2C92A5 0%, #37B6D3 100%);
    background-image: linear-gradient(to top, #2C92A5 0%, #37B6D3 100%); */
  }
  .list-wrapper{
    padding: 0 45px;
    justify-content: space-between;
  }
  .list-item{
    padding:0;
    width: 540px;
  }
</style>
