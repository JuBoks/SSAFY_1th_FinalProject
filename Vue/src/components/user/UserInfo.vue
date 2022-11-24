<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col class="text-center">
        <h3 class="underline">내 정보</h3>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-form>
          <b-form-group
            v-if="loginUser.userAuth == 1"
            label-cols="12"
            label="권한:"
            label-for="userAuth"
          >
            <b-form-input
              disabled
              v-model="input.userAuth"
              type="text"
              required
            />
          </b-form-group>
          <b-form-group label-cols="12" label="아이디:" label-for="userName">
            <b-form-input
              disabled
              v-model="input.userId"
              type="text"
              required
            />
          </b-form-group>
          <b-form-group label-cols="12" label="비밀번호:">
            <b-form-input
              v-model="input.userPwd"
              type="password"
              required
              placeholder="비밀번호 입력..."
            />
          </b-form-group>
          <b-form-group label-cols="12" label="이름:" label-for="userName">
            <b-form-input
              v-model="input.userName"
              type="text"
              required
              placeholder="이름 변경..."
            />
          </b-form-group>
          <b-form-group label-cols="12" label="이메일:">
            <b-form-input
              v-model="input.userAddr"
              type="text"
              required
              placeholder="이메일 변경..."
            />
          </b-form-group>
          <b-form-group label-cols="12" label="연락처:">
            <b-form-input
              v-model="input.userPhone"
              type="text"
              required
              placeholder="연락처 변경..."
            />
          </b-form-group>

          <b-row class="mb-1">
            <b-col class="text-right">
              <b-button variant="danger" class="m-1" @click="withdraw"
                >탈퇴</b-button
              >
              <b-button variant="success" class="m-1" @click="Modify"
                >수정</b-button
              >
              <b-button variant="" class="m-1" @click="moveMap"
                >나가기</b-button
              >
            </b-col>
          </b-row>
        </b-form>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
const userStore = "userStore";
const adminStore = "adminStore";

export default {
  methods: {
    ...mapActions(adminStore, ["modifyUser", "getUser"]),
    ...mapActions(userStore, ["getUserInfo", "deleteUser"]),
    moveMap() {
      this.$router.push({ name: "Map" });
    },
    Modify() {
      this.modifyUser({
        userInfo: { ...this.input },
        callback: () => {
          // let token = sessionStorage.getItem("access-token");
          // this.getUserInfo(token);
          this.$router.push({ name: "Map" });
        },
      });
    },
    async withdraw() {
      this.deleteUser({
        userId: this.input.userId,
        callback: () => {
          alert("탈퇴되었습니다.");
          this.$router.push({ name: "Home" });
        },
      });
    },
  },
  async created() {
    let token = sessionStorage.getItem("access-token");
    await this.getUserInfo(token);
  },
  computed: {
    ...mapGetters(userStore, ["loginUser"]),
    ...mapGetters(adminStore, ["user"]),
    input() {
      return { ...this.loginUser };
    },
  },
};
</script>

<style scoped></style>
