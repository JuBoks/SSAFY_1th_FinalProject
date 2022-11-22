<template>
  <b-navbar class="px-3 app-nav" toggleable="md" variant="primary">
    <b-navbar-brand>
      <img
        src="@/assets/img/logo_w.png"
        alt="logo"
        @click="moveMap"
        width="100px" />
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
        <template v-if="loginUser">
          <b-nav-item
            ><span>반갑습니다.{{ this.loginUser.userId }}님</span></b-nav-item
          >
          <b-nav-item v-if="this.loginUser.userAuth == 1"
            ><span @click="moveAdmin">회원관리</span></b-nav-item
          >
          <b-nav-item><span @click="moveInfo">내정보</span></b-nav-item>
          <b-nav-item
            ><span @click="showModalLogout">로그아웃</span></b-nav-item
          >
        </template>
        <template v-else>
          <b-nav-item><span @click="showModalLogin">로그인</span></b-nav-item>
          <b-nav-item><span @click="movePage">회원가입</span></b-nav-item>
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
            console.log("userId", this.input.userId);
            this.userLogout(this.loginUser.userId);
          }
        });
    },

    ...mapActions(userStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.input);
      let token = sessionStorage.getItem("access-token");
      // console.log("1. confirm() token >> " + token);
      if (this.isLogin) {
        await this.getUserInfo(token);
        const auth = this.loginUser.userAuth;
        console.log("이사람의 현재권한", auth);
        // console.log("4. confirm() userInfo :: ", this.userInfo);
        this.hideModalLogin();
      }
    },
    movePage() {
      this.$router.push({ name: "AdminJoin" });
    },
    moveAdmin() {
      this.$router.push({ name: "AdminList" });
    },
    moveMap() {
      this.$router.push({ name: "Map" });
    },
    moveInfo() {
      this.$router.push({ name: "MyInfo" });
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
