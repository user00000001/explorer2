<template>
  <div class="footer-background">

    <div class="e-container footer-container mobile-container">

      <div class="footer-logo-title">
        <img class="footer-logo-img" src="../../assets/logos/tesra-logo-2.png">
        <label class="footer-logo-text">{{ $t('footer.Explorer') }}</label>
      </div>

      <div class="footer-content-info">
        <div class="footer-content">
          <div class="mobile-foot-line-developer"></div>
          <div class="footer-content-title">{{ $t('footer.Development') }}</div>
<!--           <div class="footer-content-div"><a class="footer-content-text" href="https://TesraSupernet.github.io/documentation/tutorial_for_developer_en.html">{{ $t('footer.Tutorials') }}</a></div> -->
          <div class="footer-content-div"><a class="footer-content-text" href="https://developer.tesra.me/">{{ $t('footer.DeveloperCenter') }}</a></div>
          <div class="footer-content-div"><a class="footer-content-text" href="https://dev-docs.tesra.me/#/">{{ $t('footer.Documentation') }}</a></div>
          <div class="footer-content-div"><a class="footer-content-text" href="https://bounty.tesra.me">{{ $t('footer.Bounty') }}</a></div>
          <div class="footer-content-div"><a class="footer-content-text" href="https://smartx.tesra.me">{{ $t('footer.SmartX') }}</a></div>
          <div class="footer-content-div"><a class="footer-content-text" href="https://github.com/TesraSupernet">{{ $t('footer.Github') }}</a></div>
        </div>

<!--         <div class="footer-content">
          <div class="mobile-foot-line"></div>
          <div class="footer-content-title">{{ $t('footer.Cooperation') }}</div>
          <div class="footer-content-div"><a class="footer-content-text" href="https://info.tesra.me/cooperation/en">{{ $t('footer.CooperateWithTesraSupernet') }}</a></div>
          <div class="footer-content-div"><a class="footer-content-text" href="https://info.tesra.me/listtriones/en">{{ $t('footer.Nodes') }}</a></div>
        </div> -->

        <div class="footer-content">
          <div class="mobile-foot-line"></div>
          <div class="footer-content-title">{{ $t('footer.About') }}</div>
<!--           <div class="footer-content-div"><a class="footer-content-text" href="https://info.tesra.me/news/en">{{ $t('footer.Press') }}</a></div>
          <div class="footer-content-div" @click="toTechNews()" ><a class="footer-content-text" >{{ $t('footer.TechNews') }}</a></div>
          <div class="footer-content-div"><a class="footer-content-text" href="https://info.tesra.me/team/en">{{ $t('footer.Team') }}</a></div> -->
          <div class="footer-content-div"><a class="footer-content-text" href="https://tesra.me/#/aboutus">{{ $t('footer.AboutTesraSupernet') }}</a></div>
          <div class="footer-content-div"><a class="footer-content-text" href="https://TesraSupernet.github.io/documentation/wp_download_en.html">{{ $t('footer.WhitePaper') }}</a></div>
<!--           <div class="footer-content-div"><a class="footer-content-text" href="https://explorer.tesra.me">{{ $t('footer.Explorer') }}</a></div> -->
        </div>

        <div class="footer-content">
          <div class="mobile-foot-line"></div>
          <div class="footer-content-title">{{ $t('footer.dApps') }}</div>
          <div class="footer-content-div"><a class="footer-content-text" href="https://oodapp.io"></a></div>
          <div class="footer-content-div"><a class="footer-content-text" href="https://oodapp.io/">{{ $t('footer.dApp') }} <img class="wailian-img" src="../../assets/wailian.svg"/></a></div>
          <div class="footer-content-div"><a class="footer-content-text" href="https://submit.oodapp.io">{{ $t('footer.SubmitAdApp') }} <img class="wailian-img" src="../../assets/wailian.svg"/></a></div>
        </div>
      </div>

      <div class="footer-language">
        <div :class="$t('footer.flag') == 'en' ? 'footer-content-text-active footer-language-content':'footer-language-content footer-content-text' " @click="changeLanguage('en')">English</div>
        <div :class="$t('footer.flag') == 'zh' ? 'footer-content-text-active footer-language-content':'footer-language-content footer-content-text' " style="margin-left: 40px;" @click="changeLanguage('zh')">中文</div>
      </div>

      <div class="footer-line"></div>

      <div class="footer-link">
        <div style="display: inline-block">
          <a href="https://t.me/TesraSupernet"><img class="footer-link-telegram footer-link-img" src="../../assets/footer/telegram@2x.png"></a>
          <a href="https://discord.gg/4TQujHj"><img class="footer-link-discord footer-link-img" src="../../assets/footer/discord@2x.png"></a>
          <a href="https://twitter.com/TesraSupernet"><img class="footer-link-twitter footer-link-img" src="../../assets/footer/twitter@2x.png"></a>
        </div>
        <div style="display: inline-block">
          <a href="https://www.facebook.com/TesraSupernet-468098413590227/"><img class="footer-link-facebook footer-link-img" src="../../assets/footer/facebook@2x.png"></a>
          <a href="https://old.reddit.com/r/TesraSupernet/"><img class="footer-link-reddit footer-link-img" src="../../assets/footer/reddit@2x.png"></a>
          <a href="https://medium.com/tesrasupernet"><img class="footer-link-medium footer-link-img" src="../../assets/footer/medium@2x.png"></a>
        </div>
        <div style="display: inline-block">
          <a href="mailto:contact@tesra.me"><img class="footer-link-email footer-link-img" src="../../assets/footer/email@2x.png"></a>
          <a href="https://github.com/TesraSupernet"><img class="footer-link-github footer-link-img" src="../../assets/footer/github@2x.png"></a>
          <a href="https://www.linkedin.com/company/tesrasupernet-official/"><img class="footer-link-linkedin footer-link-img" src="../../assets/footer/linkedin@2x.png"></a>
        </div>
      </div>
      <div class="footer-copyright">Copyright © 2019 The TesraSupernet Team</div>


    </div>

  </div>
</template>

<script>
  import LangStorage from './../../helpers/lang'
export default {
  name: 'Footer',
  props: {

  },
  methods:{
    changeLanguage($lang){
      this.$i18n.locale = $lang
      LangStorage.setLang(this.$i18n.locale)
    },
    toTechNews(){
      let lang = LangStorage.getLang('en')
      let url
      switch (lang) {
        case 'en':
          url = 'https://medium.com/tesraSupernet/tagged/tech'
          break
        case 'zh':
          url = 'https://medium.com/tesraSupernet/tagged/tech'
          break
        case 'jp':
          url = 'https://medium.com/tesraSupernet/tagged/tech'
          break
        case 'ko':
          url = 'https://medium.com/tesraSupernet/tagged/tech'
          break
        default:
          url = 'https://medium.com/tesraSupernet/tagged/tech'
          break;
      }
      window.location.href = url
    },
  }
}
</script>

<style scoped>
  .footer-copyright{
    margin-top: 20px;
  }
  .footer-background{
    background:#fff;
    padding-top: 40px;
    margin-top:64px;
  }

  .footer-width{
    width: 944px;
  }
  .footer-logo-title{
    margin-bottom: 25px;
    display: flex;
    align-items: center;
  }
  .footer-logo-img{
    height: 20px;
    margin-right: 10px;
  }
  .footer-logo-text{
    font-size:18px;
    font-family:SourceSansPro-Regular;
    font-weight:400;
    color:#595757;
    line-height:23px;
    margin-bottom: 0;
  }
  .footer-logo-label{
    opacity:0.2;
    color: black;
    margin-left: 16px;
    margin-right: 16px;
  }

  .footer-content{
/*     width: 224px; */
    width: 24%;
    display: inline-block;
    vertical-align: top;
  }
  .footer-content-title{
    font-size:16px;
    font-family:SourceSansPro-Bold;
    font-weight:600;
    color:#595757;
    line-height:20px;
  }
  .footer-content-div{
    margin-top: 14px;
  }
  .footer-content-text{
    font-size:14px;
    font-family:SourceSansPro-Regular;
    font-weight:400;
    color:#AFACAC !important;
    line-height:18px;
    cursor: pointer;
  }
  .footer-content-text-active{
    font-size:14px;
    font-family:SourceSansPro-Regular;
    font-weight:400;
    color:#595757 !important;
    line-height:18px;
    cursor: pointer;
  }
  .footer-content-text:hover{
    color:#4C4D66 !important;;
    text-decoration:underline !important;
  }
  .footer-content-text:active{
    color:rgba(50,164,190,1) !important;;
    text-decoration:underline !important;
  }
  .footer-language{
    margin-top: 34px;
    text-align: center;
  }
  .footer-language-content{
    display: inline-block;
    vertical-align: top;
  }
  .footer-line{
    height:1px;
    border:1px solid #AFACAC;
    margin-top: 25px;
  }
  .footer-link{
    text-align: center;
    margin-bottom: 24px;
  }
  .footer-link-telegram{
    width: 32px;
    cursor: pointer;
  }
  .footer-link-discord{
    width: 27px;
  }
  .footer-link-twitter{
    width: 32px;
  }
  .footer-link-facebook{
    width: 26px;
  }
  .footer-link-reddit{
    width: 32px;
  }
  .footer-link-medium{
    width: 26px;
  }
  .footer-link-email{
    width: 30px;
  }
  .footer-link-github{
    width: 32px;
  }
  .footer-link-linkedin{
    width: 26px;
  }
  .footer-link-img{
    opacity: 0.4;
    cursor: pointer;
    margin-top: 23px;
    margin-left:22px;
    margin-right: 22px;
  }
  .footer-link-img:hover,
  .footer-link-img:active{
    opacity: 1;
  }
  .footer-copyright{
    font-size:14px;
    font-family:SourceSansPro-Regular;
    font-weight:400;
    color:rgba(170,179,180,1);
    line-height:18px;
    text-align: center;
    padding-bottom: 40px;
  }
  /*手机*/
  @media screen and (max-width:576px) {
    .footer-content{
      display: unset;
    }
    .mobile-foot-line-developer{
      height: 1px;
      opacity: 0.2;
      border: 1px solid #ffffff;
      margin-top: -17px;
      margin-bottom: 8px;
    }
    .mobile-foot-line {
      height: 1px;
      opacity: 0.2;
      border: 1px solid #ffffff;
      margin-top: 16px;
      margin-bottom: 8px;
    }
    .mobile-container{
      padding-left: 24px;
      padding-right: 24px;
    }
    .footer-background{
      background:#ffffff;
      padding-top: 22px
    }
    .footer-link-img{
      opacity: 0.4;
      cursor: pointer;
      margin-top: 23px;
      margin-left:37px;
      margin-right: 37px;
    }
  }
  @media screen and (min-width:576px) and (max-width:768px) {
    .footer-content{
      width: 110px;
    }

  }
  @media screen and (min-width:769px) and (max-width:992px)  {
    .footer-content{
      width: 160px;
    }
  }
.wailian-img{
  width: 11px;
  margin-left: 2px;
  margin-bottom:2px;
  opacity: 0.5;
}
</style>
