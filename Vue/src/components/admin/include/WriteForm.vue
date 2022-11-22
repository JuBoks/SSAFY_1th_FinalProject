<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col class="text-center">
        <h3 v-if="type == 'create'" class="underline">회원정보 등록</h3>
        <h3 v-else class="underline">회원정보 수정</h3>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-form>
          <b-form-group
            label-cols="12"
            label="권한:"
            label-for="userAuth"
            description="사용자권한 선택">
            <b-form-select
              id="userAuth"
              ref="userAuth"
              v-model="input.userAuth"
              :options="authOption"
              required />
          </b-form-group>
          <b-form-group
            v-if="type == 'create'"
            label-cols="12"
            label="아이디:"
            label-for="userName"
            description="아이디 입력">
            <b-form-input
              id="userId"
              ref="userId"
              v-model="input.userId"
              type="text"
              required
              placeholder="아이디 입력..." />
          </b-form-group>
          <b-form-group
            label-cols="12"
            label="비밀번호:"
            label-for="userPwd"
            description="비밀번호 입력">
            <b-form-input
              id="userPwd"
              ref="userPwd"
              v-model="input.userPwd"
              type="password"
              required
              placeholder="비밀번호 입력..." />
          </b-form-group>
          <b-form-group
            label-cols="12"
            label="이름:"
            label-for="userName"
            description="이름 변경">
            <b-form-input
              id="userName"
              ref="userName"
              v-model="input.userName"
              type="text"
              required
              placeholder="이름 변경..." />
          </b-form-group>
          <b-form-group
            label-cols="12"
            label="주소:"
            label-for="subject"
            description="주소를 입력하세요.">
            <b-form-input
              id="userAddr"
              ref="userAddr"
              v-model="input.userAddr"
              type="text"
              required
              placeholder="이름 변경..." />
          </b-form-group>
          <b-form-group
            label-cols="12"
            label="연락처:"
            label-for="userPhone"
            description="연락처를 입력하세요.">
            <b-form-input
              id="userPhone"
              ref="userPhone"
              v-model="input.userPhone"
              type="text"
              required
              placeholder="연락처 변경..." />
          </b-form-group>

          <b-button
            v-if="type == 'create'"
            variant="primary"
            class="m-1"
            @click="validate"
            >사용자 등록</b-button
          >
          <b-button v-else variant="success" class="m-1" @click="validate"
            >사용자 수정</b-button
          >
          <b-button variant="primary" class="m-1" @click="moveList"
            >목록</b-button
          >
        </b-form>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
const adminStore = "adminStore";
const userStore = "userStore";
export default {
  props: {
    // 부모 component로 부터 전달받은 type 정보를 받아옴
    type: { type: String },
  },
  data() {
    return {
      authOption: [
        { text: "관리자", value: 1 },
        { text: "일반사용자", value: 0 },
      ],
    };
  },
  methods: {
    ...mapActions(adminStore, [
      "createUser",
      "modifyUser",
      "getUser",
      "getUsers",
    ]),
    validate() {
      let isValid = true;
      let errMsg = "";

      !this.input.userName
        ? ((isValid = false),
          (errMsg = "이름을 입력해주세요."),
          this.$refs.userName.focus())
        : !this.input.userAddr
        ? ((isValid = false),
          (errMsg = "주소를 입력해주세요."),
          this.$refs.userAddr.focus())
        : (isValid = true);
      !this.input.userPhone
        ? ((isValid = false),
          (errMsg = "번호를 입력해주세요."),
          this.$refs.userPhone.focus())
        : (isValid = true);

      if (!isValid) {
        alert(errMsg);
      } else {
        if (this.type == "create") {
          this.createUser({
            userInfo: this.input,
            callback: () => {
              this.getUsers({ pgno: 1, key: "", word: "" });
            },
          });
          this.moveList();
        } else {
          this.modifyUser({
            userInfo: this.input,
            callback: () => {
              this.getUsers({ pgno: 1, key: "", word: "" });
            },
          });
          this.moveList();
        }
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
  computed: {
    ...mapGetters(userStore, ["loginUser"]),
    ...mapGetters(adminStore, ["user"]),
    input() {
      if (this.type == "modify") {
        // 브라우저 새로고침을 하게되면 state에 저장된 데이터가 사라짐
        // Vuex의 state.user 객체가 비어있다면  서버에 요청하기
        //this.getUser(this.$route.params.user);

        // 수정화면일 경우
        return { ...this.user }; // Vuex의 state.users 객체에 직접 접근하는걸 막기 위해 Deep Copy
      } else {
        // 등록화면일 경우
        return {
          userAuth: 0,
          userId: "",
          userPwd: "",
          userName: "",
          userAddr: "",
          userPhone: "",
        };
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
