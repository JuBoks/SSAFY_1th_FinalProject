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
        <template v-if="loginUser">
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
        <b-button variant="primary" @click="doLogin">로그인</b-button>
      </div>
    </b-modal>
  </b-navbar>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

const userStore = "userStore";

export default {
  data() {
    return {
      input: {
        userId: "admin",
        userPwd: "1234",
      },
    };
  },
  computed: {
    ...mapGetters(userStore, ["loginUser"]),
  },
  methods: {
    ...mapActions(userStore, ["login", "logout"]),

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
            this.logout(() => {
              this.$router.push({ name: "Home" }).catch(() => {});
            });
          }
        });
    },
    async doLogin() {
      const payload = {
        input: this.input,
        callback: () => {
          this.hideModalLogin();
        },
      };
      this.login(payload);
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
