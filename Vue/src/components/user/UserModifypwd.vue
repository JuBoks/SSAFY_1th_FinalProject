<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col class="text-center">
        <h3 class="underline">비밀번호 변경</h3>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-form>
          <b-form-group
            label-cols="12"
            label="새 비밀번호:"
            label-for="userPwd"
            description="새 비밀번호 입력"
          >
            <b-form-input
              id="userPwd"
              ref="userPwd"
              v-model="userPwd"
              type="password"
              required
              placeholder="새 비밀번호 입력..."
            />
          </b-form-group>
          <b-form-group
            label-cols="12"
            label="새 비밀번호확인:"
            label-for="userPwdConfirm"
            description="새 비밀번호확인 입력"
          >
            <b-form-input
              id="userPwdConfirm"
              ref="userPwdConfirm"
              v-model="userPwdConfirm"
              type="password"
              required
              placeholder="새 비밀번호확인 입력..."
            />
          </b-form-group>
          <b-row class="mb-1">
            <b-col class="text-right">
              <b-button variant="success" class="m-1" @click="validate">
                변경하기
              </b-button>
            </b-col>
          </b-row>
        </b-form>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { modifyPwd } from "@/api/user";

export default {
  data() {
    return {
      userId: "",
      userPwd: "",
      userPwdConfirm: "",
    };
  },
  created() {
    this.userId = this.$route.params.userId;
  },
  methods: {
    validate() {
      let isValid = true;
      let errMsg = "";

      !this.userPwd
        ? ((isValid = false),
          (errMsg = "새 비밀번호를 입력해주세요."),
          this.$refs.userPwd.focus())
        : !this.userPwdConfirm
        ? ((isValid = false),
          (errMsg = "새 비밀번호확인을 입력해주세요."),
          this.$refs.userPwdConfirm.focus())
        : this.userPwd != this.userPwdConfirm
        ? ((isValid = false), (errMsg = "비밀번호가 일치하지 않습니다."))
        : (isValid = true);

      if (!isValid) {
        alert(errMsg);
      } else {
        modifyPwd(this.userId, this.userPwd, () => {
          alert("비밀번호가 수정되었습니다.");
          this.$router.push({ name: "Home" });
        });
      }
    },
    moveList() {
      if (this.loginUser) {
        this.$router.push({ name: "AdminList" });
      } else {
        this.$router.push({ name: "Map" });
      }
    },
  },
};
</script>

<style scoped>
.form-group {
  margin-bottom: 0;
}
</style>
