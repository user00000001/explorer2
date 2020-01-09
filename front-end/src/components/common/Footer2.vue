<template>
  <div class="footer-pure">
    <div class="footer-wrap">
      <img src="../../assets/footer/footer_pure_ico.png">
      <p>Copyright © 2019 TESRA SUPERNET</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Footer',
  props: {

  },
  methods:{
    
  }
}
</script>

<style scoped>
.footer-pure{
  width: 100%;
  margin-top: 80px;
  height: 200px;
  background: #2E2F3E;
}
.footer-pure .footer-wrap{
  position: relative;
  width: 1110px;
  margin: 0 auto;
  overflow: hidden;
}
.footer-pure .footer-wrap img{
  position: relative;
  display: block;
  width: 320px;
  height: 64px;
  margin: 68px auto;
}
.footer-pure .footer-wrap p {
  position: absolute;
  bottom: 14px;
  width: 320px;
  text-align: center;
  line-height: 1;
  left: 395px;
  color: #808298;
}
</style>
