<template>
  <b-navbar class="px-3 app-nav" toggleable="md" variant="primary">
    <b-navbar-brand href="/">
      <img src="@/assets/img/logo_w.png" alt="logo" width="100px" />
    </b-navbar-brand>

    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

    <b-collapse id="nav-collapse" is-nav>
      <b-navbar-nav>
        <!-- <b-nav-item><router-link to="/main">홈</router-link></b-nav-item> -->
        <b-nav-item><router-link to="/map">매물</router-link></b-nav-item>
        <!-- <b-nav-item><router-link to="/admin">관리자</router-link></b-nav-item> -->
        <b-nav-item><router-link to="/board">QnA</router-link></b-nav-item>
      </b-navbar-nav>
      <!-- right nav items -->
      <b-navbar-nav class="ml-auto">
        <template v-if="avail">
          <!-- <b-nav-item><span>내정보</span></b-nav-item> -->
          <b-nav-item
            ><span @click="showModalLogout">로그아웃</span></b-nav-item
          >
        </template>
        <template v-else>
          <b-nav-item><span @click="showModalLogin">로그인</span></b-nav-item>
          <!-- <b-nav-item><span>회원가입</span></b-nav-item> -->
        </template>
      </b-navbar-nav>
    </b-collapse>

    <!-- 로그인 모달 창 -->
    <b-modal
      ref="modalLogin"
      title="로그인"
      header-bg-variant="dark"
      header-text-variant="light"
      centered
      hide-footer>
      <!-- 수정 모달 창 body 작성 -->
      <div>
        <b-form-input
          v-model="input.userId"
          placeholder="아이디 입력 ..."></b-form-input>
        <b-form-input
          v-model="input.userPwd"
          type="password"
          placeholder="패스워드 입력 ..."></b-form-input>
      </div>
      <!-- 수정 모달 창 Footer 작성 -->
      <div class="text-right">
        <b-button variant="primary" @click="confirm">로그인</b-button>
      </div>
    </b-modal>
  </b-navbar>
</template>

<script>
import { mapActions, mapGetters, mapState } from "vuex";

const userStore = "userStore";
export default {
  data() {
    return {
      avail: "",
      input: {
        userId: "",
        userPwd: "",
      },
    };
  },
  computed: {
    ...mapGetters(userStore, ["loginUser"]),
    ...mapState(userStore, ["isLogin", "isLoginError", "userInfo"]),
  },

  methods: {
    ...mapActions(userStore, ["login", "userLogout"]),

    showModalLogin() {
      this.$refs["modalLogin"].show();
    },
    hideModalLogin() {
      this.$refs["modalLogin"].hide();
    },
    showModalLogout() {
      this.$bvModal
        .msgBoxConfirm("로그아웃 하시겠습니까?", {
          centered: true,
        })
        .then((value) => {
          if (value) {
            this.avail = "";
            console.log("userId", this.input.userId);
            this.userLogout(this.input.userId);
          }
        });
    },

    ...mapActions(userStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.input);
      let token = sessionStorage.getItem("access-token");
      console.log("1. confirm() token >> " + token);
      if (this.isLogin) {
        await this.getUserInfo(token);
        this.avail = "yes";
        console.log(this.avail);
        console.log("4. confirm() userInfo :: ", this.userInfo);
        this.hideModalLogin();
      }
    },
    movePage() {
      this.$router.push({ name: "join" });
    },
  },
};
</script>

<style scoped>
span,
a {
  color: azure;
}
</style>
