<template>
  <div class="token-submit">
    <div class="container">
      <div class="title-info">
        <div class="title">Submit Your Token Information</div>
      </div>
      <div class="common-info">
        <div class="item">
          <div class="item-title">Token hash*</div>
          <input v-model="tokenHash" />
        </div>
        <div class="item">
          <div class="item-title">Token Name*</div>
          <input v-model="tokenName" maxlength="255" />
        </div>
        <div class="item">
          <div class="item-title">Virtual Machine Category*</div>
          <el-radio v-model="radio" label="tesra">tesra</el-radio>
          <el-radio v-model="radio" label="wasmvm">wasmvm</el-radio>
        </div>
        <div class="item">
          <div class="item-title">Token Abi*</div>
          <textarea v-model="tokenAbi" />
        </div>
        <div class="item">
          <div class="item-title">Token Code*</div>
          <textarea v-model="tokenCode" />
        </div>
        <div class="item">
          <div class="item-title">Description*</div>
          <textarea v-model="tokenDescription" maxlength="800" />
        </div>
      </div>
      <div class="token-info">
        <el-tabs v-model="activeName" @tab-click="handleClick">
          <el-tab-pane label="OEP-4" name="first">OEP-4</el-tab-pane>
          <el-tab-pane label="OEP-5" name="second">OEP-5</el-tab-pane>
          <el-tab-pane label="OEP-8" name="third">OEP-8</el-tab-pane>
        </el-tabs>
        <div v-if="activeName === 'first'" id="oep4" class="content">
          <div class="item">
            <div class="item-title">Token Symbol*</div>
            <input v-model="tokenSymbol" maxlength="255" />
          </div>
          <div class="item">
            <div class="item-title">Token Decimals*</div>
            <input v-model="tokenDecimals" type="number" maxlength="11" />
          </div>
          <div class="item">
            <div class="item-title">Total Supply*</div>
            <input v-model="tokenTotalSupply" type="number" maxlength="15" />
          </div>
          <div class="item">
            <div class="item-title">Logo*</div>
            <el-upload
              :class="imageUrl?'avatar-uploader none':'avatar-uploader'"
              action="/"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload"
            >
              <img v-if="imageUrl" :src="imageUrl" class="avatar" id="logoImg" />
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
            <div class="desc">256px*256px in jpg or png, no rounded corners</div>
          </div>
        </div>
        <div v-if="activeName === 'second'" id="oep5" class="content">
          <div class="item">
            <div class="item-title">Token Symbol*</div>
            <input v-model="tokenSymbol" maxlength="255" />
          </div>
          <div class="item">
            <div class="item-title">Total Supply*</div>
            <input v-model="tokenTotalSupply" type="number" maxlength="15" />
          </div>
          <div class="item">
            <div class="item-title">Logo*</div>
            <el-upload
              :class="imageUrl?'avatar-uploader none':'avatar-uploader'"
              action="/"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload"
            >
              <img v-if="imageUrl" :src="imageUrl" class="avatar" id="logoImg" />
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
            <div class="desc">256px*256px in jpg or png, no rounded corners</div>
          </div>
        </div>
        <div v-if="activeName === 'third'" id="oep8" class="content">
          <div class="item">
            <el-table :data="tokens" style="width: 100%" empty-text="No token added">
              <el-table-column label="Token ID" width="120">
                <template slot-scope="scope">
                  <div class="table-text">{{scope.row.token_id}}</div>
                </template>
              </el-table-column>
              <el-table-column label="Token Name" width="120">
                <template slot-scope="scope">
                  <div class="table-text">{{scope.row.token_name}}</div>
                </template>
              </el-table-column>
              <el-table-column label="Total Supply" width="120">
                <template slot-scope="scope">
                  <div class="table-text">{{scope.row.total_supply}}</div>
                </template>
              </el-table-column>
              <el-table-column label="Token Symbol" width="120">
                <template slot-scope="scope">
                  <div class="table-text">{{scope.row.symbol}}</div>
                </template>
              </el-table-column>
              <el-table-column label="Operation">
                <template slot-scope="scope">
                  <!--                   <el-button
                    size="mini"
                    type="danger"
                    icon="el-icon-minus"
                    circle
                    @click="handleDelete(scope.$index, scope.row)"
                  ></el-button>-->
                  <i
                    style="width:14px;height:14px;color:red;cursor:pointer"
                    @click="handleDelete(scope.$index, scope.row)"
                    class="el-icon-remove-outline"
                  ></i>
                </template>
              </el-table-column>
            </el-table>
          </div>
          <div class="item">
            <div class="item-title">Oep8 Token ID*</div>
            <input v-model="oep8info.token_id" maxlength="255" />
          </div>
          <div class="item">
            <div class="item-title">Oep8 Token Name*</div>
            <input v-model="oep8info.token_name" maxlength="255" />
          </div>
          <div class="item">
            <div class="item-title">Oep8 Total Supply*</div>
            <input v-model="oep8info.total_supply" type="number" maxlength="15" />
          </div>
          <div class="item">
            <div class="item-title">Oep8 Token Symbol*</div>
            <input v-model="oep8info.symbol" maxlength="255" />
          </div>
          <div class="item">
            <div class="add-btn">
              <div class="text" @click="addOep8Token()">Add</div>
            </div>
          </div>
          <div class="item">
            <div class="item-title">Logo*</div>
            <el-upload
              :class="imageUrl?'avatar-uploader none':'avatar-uploader'"
              action="/"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload"
            >
              <img v-if="imageUrl" :src="imageUrl" class="avatar" id="logoImg" />
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
            <div class="desc">256px*256px in jpg or png, no rounded corners</div>
          </div>
        </div>
      </div>
      <div class="contact-info">
        <el-divider>Contact Information</el-divider>
        <div class="item">
          <div class="item-title">Website</div>
          <input v-model="tokenWebsite" placeholder="Optional" />
        </div>
        <div class="item">
          <div class="item-title">E-mail*</div>
          <input v-model="tokenEmail" />
        </div>
      </div>
      <div class="submit">
        <div class="btn">
          <div class="text" @click="submit()">Submit</div>
        </div>
      </div>
    </div>
    <el-dialog
      title="Success"
      :visible.sync="dialogVisible"
      width="480px"
      :before-close="handleClose"
    >
      <span>Token information is sucessfully submitted.</span>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false">OK</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { mapState } from "vuex";
import Clipboard from "clipboard";
import HelperTool from "./../../helpers/helper";

export default {
  name: "Token-Detail",
  mounted() {},
  watch: {
    submitresult: function() {
      let self = this;
      console.log("submitresult", this.submitresult);
      if (this.submitresult.list.code == 61003) {
        this.$message({
          message: this.$t("error.msg19"),
          type: "error"
        });
      } else {
        if (
          this.submitresult.list.code == 61011 ||
          this.submitresult.list.code == 61010
        ) {
          this.$message({
            message: this.$t("error.msg20"),
            type: "error"
          });
        } else {
          this.dialogVisible = true;
          self.activeName = "first";
          self.tokenType = "oep4s";
          self.radio = "";
          self.tokenHash = "";
          self.tokenName = "";
          self.tokenDescription = "";
          self.tokenAbi = "";
          self.tokenCode = "";
          self.dataURL = "";
          self.tokenEmail = "";
          self.tokenWebsite = "";
          self.tokenTotalSupply = "";
          self.tokenDecimals = "";
          self.tokenSymbol = "";
          self.vm_category = "";
          self.imageUrl = "";
          self.tokens = [];
        }
      }
    }
  },
  computed: {
    ...mapState({
      submitresult: state => state.Tokens.Res
    })
  },
  data() {
    return {
      dialogVisible: false,
      showCodeCopied: false,
      showABICopied: false,
      tokenData: {},
      tokenInfo: {},
      loadingFlag: false,
      activeName: "first",
      radio: "",
      tokenHash: "",
      tokenSymbol: "",
      tokenAbi: "",
      tokenCode: "",
      tokenDescription: "",
      tokenName: "",
      tokenDecimals: "",
      tokenTotalSupply: "",
      tokenEmail: "",
      tokenWebsite: "",
      imageUrl: "",
      dataURL: "",
      vm_category: "tesra",
      tokens: [],
      oep8info: {
        token_id: "",
        token_name: "",
        total_supply: "",
        symbol: ""
      },
      tokenType: "oep4s"
    };
  },
  methods: {
    submit() {
      var self = this;
      let params = self.$route.params;
      if (self.check(self.tokenType)) {
        params.tokenType = self.tokenType;
        params.tokenHash = self.tokenHash;
        params.tokenName = self.tokenName;
        params.tokenDescription = self.tokenDescription;
        params.tokenAbi = self.tokenAbi;
        params.tokenCode = self.tokenCode;
        params.dataURL = self.dataURL;
        params.contact_info = {
          email: self.tokenEmail,
          website: self.tokenWebsite
        };
        params.tokenTotalSupply = self.tokenTotalSupply;
        params.tokenDecimals = self.tokenDecimals;
        params.tokenSymbol = self.tokenSymbol;
        params.vm_category = self.radio;
        params.tokens = self.tokens;
        this.$store
          .dispatch("submitToken", params)
          .then(res => {})
          .catch(() => {
            self.$message({
              message: "failed",
              type: "error"
            });
          });
      }
    },
    check($type) {
      var self = this;
      let flag = true;
      if (self.tokenHash === "") {
        self.$message({
          message: self.$t("error.msg1"),
          type: "error"
        });
        flag = false;
        return flag;
      }
      if (self.tokenName === "") {
        self.$message({
          message: self.$t("error.msg2"),
          type: "error"
        });
        flag = false;
        return flag;
      }
      if (self.radio === "") {
        self.$message({
          message: self.$t("error.msg13"),
          type: "error"
        });
        flag = false;
        return flag;
      }
      if (self.tokenAbi === "") {
        self.$message({
          message: self.$t("error.msg3"),
          type: "error"
        });
        flag = false;
        return flag;
      }
      if (self.tokenCode === "") {
        self.$message({
          message: self.$t("error.msg4"),
          type: "error"
        });
        flag = false;
        return flag;
      }
      if (self.tokenDescription === "") {
        self.$message({
          message: self.$t("error.msg5"),
          type: "error"
        });
        flag = false;
        return flag;
      }
      if (self.dataURL === "") {
        self.$message({
          message: self.$t("error.msg9"),
          type: "error"
        });
        flag = false;
        return flag;
      }
      if ($type === "oep8s") {
        if (self.tokens.length == 0) {
          self.$message({
            message: self.$t("error.msg12"),
            type: "error"
          });
          flag = false;
          return flag;
        }
      }
      if ($type === "oep4s") {
        if (self.tokenSymbol === "") {
          self.$message({
            message: self.$t("error.msg6"),
            type: "error"
          });
          flag = false;
          return flag;
        }
        if (self.tokenDecimals === "") {
          self.$message({
            message: self.$t("error.msg7"),
            type: "error"
          });
          flag = false;
          return flag;
        }
        if (self.tokenTotalSupply === "") {
          self.$message({
            message: self.$t("error.msg8"),
            type: "error"
          });
          flag = false;
          return flag;
        }
        if (
          self.tokenTotalSupply > 999999999999999 ||
          self.tokenTotalSupply < 1
        ) {
          self.$message({
            message: self.$t("error.msg21"),
            type: "error"
          });
          flag = false;
          return flag;
        }
      }
      if ($type === "oep5s") {
        if (self.tokenSymbol === "") {
          self.$message({
            message: self.$t("error.msg6"),
            type: "error"
          });
          flag = false;
          return flag;
        }
        if (self.tokenTotalSupply === "") {
          self.$message({
            message: self.$t("error.msg8"),
            type: "error"
          });
          flag = false;
          return flag;
        }        
        if (
          self.tokenTotalSupply > 999999999999999 ||
          self.tokenTotalSupply < 1
        ) {
          self.$message({
            message: self.$t("error.msg21"),
            type: "error"
          });
          flag = false;
          return flag;
        }
      }
      if (self.tokenEmail === "") {
        self.$message({
          message: self.$t("error.msg11"),
          type: "error"
        });
        flag = false;
        return flag;
      } else {
        let email = /^([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
        if (!email.test(self.tokenEmail)) {
          self.$message({
            message: self.$t("error.msg10"),
            type: "error"
          });
          flag = false;
          return flag;
        }
      }
      return flag;
    },
    handleClose(done) {
      done();
    },
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleDelete(index, row) {
      this.tokens.splice(index, 1);
      console.log(this.tokens);
    },
    addOep8Token(index, row) {
      let self = this;
      if (this.oep8info.token_id === "") {
        self.$message({
          message: self.$t("error.msg14"),
          type: "error"
        });
        return false;
      }
      if (this.oep8info.token_name === "") {
        self.$message({
          message: self.$t("error.msg15"),
          type: "error"
        });
        return false;
      }
      if (this.oep8info.total_supply === "") {
        self.$message({
          message: self.$t("error.msg16"),
          type: "error"
        });
        return false;
      }
      if (
        self.oep8info.total_supply > 999999999999999 ||
        self.oep8info.total_supply < 1
      ) {
        self.$message({
          message: self.$t("error.msg21"),
          type: "error"
        });
        return false;
      }
      if (this.oep8info.symbol === "") {
        self.$message({
          message: self.$t("error.msg17"),
          type: "error"
        });
        return false;
      }
      let checkFlag = true;
      for (var i = 0; i < this.tokens.length; i++) {
        if (this.tokens[i].token_id === this.oep8info.token_id) {
          checkFlag = false;
        }
      }
      if (!checkFlag) {
        self.$message({
          message: self.$t("error.msg18"),
          type: "error"
        });
        return false;
      }
      let array = {
        token_id: this.oep8info.token_id,
        token_name: this.oep8info.token_name,
        total_supply: this.oep8info.total_supply,
        symbol: this.oep8info.symbol
      };
      this.tokens.push(array);
      console.log(this.tokens);
      this.oep8info.token_id = "";
      this.oep8info.token_name = "";
      this.oep8info.total_supply = "";
      this.oep8info.symbol = "";
    },
    handleAvatarSuccess(res, file) {
      /* this.imageUrl = URL.createObjectURL(file.raw); */
      this.imageUrl = this.dataURL;
      console.log(file.raw);
    },
    beforeAvatarUpload(file) {
      var self = this;
      const isJPG = file.type === "image/jpeg" || "image/png";

      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      } else {
        var reader = new FileReader();
        reader.readAsDataURL(file);
        var img = new Image();
        reader.onloadend = function() {
          img.src = reader.result;
          img.onload = function(event) {
            if (img.width !== 256 || img.height !== 256) {
              self.$message({
                message: "256px*256px in jpg or png, no rounded corners",
                type: "error"
              });
              return false;
            } else {
              self.dataURL = reader.result;
              self.imageUrl = reader.result;
            }
          };
        };
      }
      return false;
    },
    handleClick(tab, event) {
      console.log(tab, event);
      console.log(this.activeName);
      this.tokens = [];
      this.oep8info.token_id = "";
      this.oep8info.token_name = "";
      this.oep8info.total_supply = "";
      this.oep8info.symbol = "";
      this.imageUrl = "";
      this.tokenSymbol = "";
      this.tokenDecimals = "";
      this.tokenTotalSupply = "";
      if (this.activeName === "first") {
        this.tokenType = "oep4s";
      }
      if (this.activeName === "second") {
        this.tokenType = "oep5s";
      }
      if (this.activeName === "third") {
        this.tokenType = "oep8s";
      }
      console.log(this.tokenType);
    },
    getTokenData() {
      this.token.list = "";
      this.loadingFlag = false;
      this.$store.dispatch("GetToken", this.$route.params).then();
      this.$store.dispatch("GetContractTx", this.$route.params).then();
    },
    toTransDetailPage($TxnId) {
      if (this.$route.params.net === "testnet") {
        this.$router.push({
          name: "TransactionDetailTest",
          params: { tx_hash: $TxnId, net: "testnet" }
        });
      } else {
        this.$router.push({
          name: "TransactionDetail",
          params: { tx_hash: $TxnId }
        });
      }
    },
    copyDetailVal($id) {
      let clipboard = new Clipboard(".copy-success");

      clipboard.on("success", function(e) {
        e.clearSelection();
      });

      if ($id === "scCodeData") {
        this.showCodeCopied = true;
      } else {
        this.showABICopied = true;
      }
    },
    toJson(str) {
      return JSON.parse(str);
    }
  }
};
</script>

<style scoped lang="scss">
::-webkit-input-placeholder {
  /* Chrome/Opera/Safari */
  color: rgba(0, 0, 0, 0.1);
}
::-moz-placeholder {
  /* Firefox 19+ */
  color: rgba(0, 0, 0, 0.1);
}
:-ms-input-placeholder {
  /* IE 10+ */
  color: rgba(0, 0, 0, 0.1);
}
:-moz-placeholder {
  /* Firefox 18- */
  color: rgba(0, 0, 0, 0.1);
}
input:focus {
  outline: none;
}
textarea:focus {
  outline: none;
}
.avatar-uploader {
  width: 100%;
  height: 168px;
  background: rgba(255, 255, 255, 1);
  border-radius: 3px;
  border: 1px solid rgba(0, 0, 0, 0.1);
}
.avatar-uploader.none {
  /deep/ .el-upload {
    width: 138px;
    height: 138px;
    background: rgba(72, 163, 255, 0);
    border-radius: 10px;
    border: 1px solid rgba(0, 0, 0, 0);
    margin: 14px;
  }
}
.avatar-uploader {
  /deep/ .el-upload {
    width: 138px;
    height: 138px;
    background: rgba(72, 163, 255, 0.1);
    border-radius: 10px;
    border: 1px solid rgba(0, 0, 0, 0.1);
    margin: 14px;
  }
}
.avatar-uploader .el-upload:hover {
  border-color: #4C4D66;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #000;
  font-weight: 1000;
  width: 138px;
  height: 138px;
  line-height: 138px;
  text-align: center;
}
.avatar-uploader-icon:hover {
  color: rgba(0, 0, 0, 0.8);
}
.avatar {
  width: 138px;
  height: 138px;
  display: block;
}
/deep/ .el-table th {
  background: rgba(0, 0, 0, 0.4);
  height: 40px;
  padding: 0;
}
/deep/ .el-table th > .cell {
  font-size: 14px;
  font-family: SourceSansPro-Semibold, "Helvetica Neue", "Arial", sans-serif;
  font-weight: 600;
  color: rgba(255, 255, 255, 1);
  line-height: 19px;
}
/deep/ .el-radio.is-checked {
  color: #4C4D66;
  .el-radio__input.is-checked .el-radio__inner {
    border-color: #4C4D66;
    background: #4C4D66;
  }
  .el-radio__input.is-checked + .el-radio__label {
    color: #4C4D66;
  }
}
/deep/ .el-tabs__active-bar {
  background: #4C4D66;
  width: 88px !important;
  display: none;
}
/deep/ .el-dialog__headerbtn {
  display: none;
}
/deep/ .el-dialog__title {
  font-size: 16px;
  font-family: SourceSansPro-Semibold, "Helvetica Neue", "Arial", sans-serif;
  font-weight: 600;
  color: rgba(0, 0, 0, 1);
  line-height: 22px;
}
/deep/ .el-dialog__body {
  text-align: center;
}
/deep/ .el-dialog__footer {
  border-top: 1px solid rgba(0, 0, 0, 0.1);
}
/deep/ .el-button--primary {
  width: 100px;
  height: 32px;
  background: rgba(50, 164, 190, 1);
  border-radius: 4px;
  padding: 0;
}
/deep/ .el-tabs__item {
  padding: 0 16px !important;
  border-bottom: none;
}
/deep/ .el-tabs__item.is-active {
  border-bottom: 2px solid #4C4D66;
}
/deep/ .el-divider__text {
  font-size: 16px;
  font-family: SourceSansPro-Semibold, "Helvetica Neue", "Arial", sans-serif;
  font-weight: 600;
  color: rgba(0, 0, 0, 1);
  line-height: 22px;
}
.token-submit {
  background: rgba(245, 242, 242, 1);
  padding-top: 32px;
  padding-bottom: 32px;
  .container {
    width: 992px;
    background: rgba(255, 255, 255, 1);
    .title-info {
      padding-top: 59px;
      display: flex;
      justify-content: center;
      .title {
        font-size: 24px;
        font-family: SourceSansPro-Regular, "Helvetica Neue", "Arial", sans-serif;
        font-weight: 300;
        color: rgba(0, 0, 0, 1);
        line-height: 32px;
      }
    }
    .common-info {
      padding-top: 74px;
      display: flex;
      justify-content: start;
      flex-flow: column;
      width: 688px;
      margin-left: auto;
      margin-right: auto;
      .item {
        padding-bottom: 40px;
        .item-title {
          font-size: 14px;
          font-family: SourceSansPro-Semibold, "Helvetica Neue", "Arial", sans-serif;
          font-weight: 600;
          color: rgba(0, 0, 0, 1);
          line-height: 19px;
          margin-bottom: 8px;
        }
        input {
          width: 540px;
          height: 42px;
          background: rgba(255, 255, 255, 1);
          border-radius: 3px;
          border: 1px solid rgba(0, 0, 0, 0.1);
          padding-left: 14px;
        }
        textarea {
          width: 540px;
          height: 168px;
          background: rgba(255, 255, 255, 1);
          border-radius: 3px;
          border: 1px solid rgba(0, 0, 0, 0.1);
          resize: none;
          padding-left: 14px;
        }
      }
    }
    .token-info {
      padding-top: 32px;
      display: flex;
      justify-content: center;
      background: rgba(0, 0, 0, 0.02);
      border-radius: 6px;
      width: 688px;
      flex-flow: column;
      margin-left: auto;
      margin-right: auto;
      /deep/ .el-tabs__item {
        font-size: 16px;
        font-family: SourceSansPro-Regular, "Helvetica Neue", "Arial",
          sans-serif;
        font-weight: 400;
        color: rgba(0, 0, 0, 0.3);
        line-height: 41px;
        height: 41px;
      }
      /deep/ .is-active {
        color: rgba(0, 0, 0, 1) !important;
      }
      /deep/ .el-tabs__content {
        display: none;
      }
      /deep/ .el-tabs {
        display: flex;
        justify-content: center;
      }
      /deep/ .el-tabs__header {
        width: 265px;
      }
      /deep/ .el-tabs__nav-wrap::after {
        width: 240px;
      }
      .content {
        padding: 32px 48px 15px;
        .item {
          padding-bottom: 40px;
          .item-title {
            font-size: 14px;
            font-family: SourceSansPro-Semibold, "Helvetica Neue", "Arial",
              sans-serif;
            font-weight: 600;
            opacity: 0.65;
            color: rgba(0, 0, 0, 1);
            line-height: 19px;
            padding-bottom: 8px;
          }
          input {
            width: 320px;
            height: 42px;
            background: rgba(255, 255, 255, 1);
            border-radius: 3px;
            border: 1px solid rgba(0, 0, 0, 0.1);
            padding-left: 14px;
          }
          .img-wrapper {
            width: 100%;
            height: 168px;
            background: rgba(255, 255, 255, 1);
            border-radius: 3px;
            border: 1px solid rgba(170, 179, 180, 1);
          }
          .desc {
            font-size: 14px;
            font-family: SourceSansPro-Regular, "Helvetica Neue", "Arial",
              sans-serif;
            font-weight: 400;
            color: rgba(0, 0, 0, 0.5);
            line-height: 19px;
            margin-top: 11px;
          }
          .table-input {
            padding-left: 0px;
            font-size: 14px;
            font-family: SourceSansPro-Regular, "Helvetica Neue", "Arial",
              sans-serif;
            font-weight: 400;
            color: rgba(0, 0, 0, 1);
            line-height: 20px;
            border: none;
          }
          .table-text {
            font-size: 14px;
            font-family: SourceSansPro-Regular, "Helvetica Neue", "Arial",
              sans-serif;
            font-weight: 400;
            color: rgba(0, 0, 0, 1);
            line-height: 20px;
            opacity: 0.65;
            padding: 10px 0;
          }
          .add-btn {
            cursor: pointer;
            width: 100px;
            height: 32px;
            border-radius: 4px;
            border: 1px solid rgba(50, 164, 190, 1);
            .text {
              height: 30px;
              font-size: 16px;
              font-family: SourceSansPro-Regular, "Helvetica Neue", "Arial",
                sans-serif;
              font-weight: 400;
              color: rgba(50, 164, 190, 1);
              line-height: 30px;
              text-align: center;
            }
          }
          .add-btn:hover {
            background: rgba(72, 163, 255, 0.1);
            border-radius: 4px;
            border: 1px solid rgba(50, 164, 190, 1);
          }
        }
      }
    }
    .contact-info {
      width: 688px;
      margin-left: auto;
      margin-right: auto;
      padding-top: 54px;
      padding-bottom: 16px;
      display: flex;
      justify-content: start;
      flex-flow: column;
      /deep/ .el-divider--horizontal {
        margin-bottom: 64px;
      }
      .item {
        padding-bottom: 40px;
        .item-title {
          font-size: 14px;
          font-family: SourceSansPro-Regular, "Helvetica Neue", "Arial",
            sans-serif;
          font-weight: 600;
          color: rgba(0, 0, 0, 1);
          line-height: 19px;
          margin-bottom: 8px;
        }
        input {
          width: 320px;
          height: 42px;
          background: rgba(255, 255, 255, 1);
          border-radius: 3px;
          border: 1px solid rgba(0, 0, 0, 0.1);
          padding-left: 14px;
        }
      }
    }
    .submit {
      display: flex;
      justify-content: center;
      padding-bottom: 64px;
      .btn {
        width: 100px;
        height: 32px;
        background: rgba(50, 164, 190, 1);
        border-radius: 4px;
        cursor: pointer;
        .text {
          height: 22px;
          font-size: 16px;
          font-family: SourceSansPro-Regular, "Helvetica Neue", "Arial",
            sans-serif;
          font-weight: 400;
          color: rgba(255, 255, 255, 1);
          line-height: 18px;
        }
      }
      .btn:hover {
        background: rgba(50, 164, 190, 0.8);
        border-radius: 4px;
      }
    }
  }
}
</style>
