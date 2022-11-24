<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col class="text-center">
        <h3 class="underline">{{ title }}</h3>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-form>
          <b-form-group
            v-if="isAdmin"
            label-cols="12"
            label="권한:"
            label-for="userAuth"
            description="사용자권한 선택"
          >
            <b-form-select
              id="userAuth"
              ref="userAuth"
              v-model="input.userAuth"
              :options="authOption"
              required
            />
          </b-form-group>
          <b-form-group
            v-if="type == 'create'"
            label-cols="12"
            label="아이디:"
            label-for="userName"
            description="아이디 입력"
          >
            <b-form-input
              id="userId"
              ref="userId"
              v-model="input.userId"
              type="text"
              required
              placeholder="아이디 입력..."
            />
          </b-form-group>
          <b-form-group
            label-cols="12"
            label="비밀번호:"
            label-for="userPwd"
            description="비밀번호 입력"
          >
            <b-form-input
              id="userPwd"
              ref="userPwd"
              v-model="input.userPwd"
              type="password"
              required
              placeholder="비밀번호 입력..."
            />
          </b-form-group>
          <b-form-group
            label-cols="12"
            label="이름:"
            label-for="userName"
            description="이름 변경"
          >
            <b-form-input
              id="userName"
              ref="userName"
              v-model="input.userName"
              type="text"
              required
              placeholder="이름 입력..."
            />
          </b-form-group>
          <b-form-group
            label-cols="12"
            label="이메일:"
            label-for="userAddr"
            description="이메일를 입력하세요."
          >
            <b-form-input
              id="userAddr"
              ref="userAddr"
              v-model="input.userAddr"
              type="text"
              required
              placeholder="이메일 입력..."
            />
          </b-form-group>
          <b-form-group
            label-cols="12"
            label="연락처:"
            label-for="userPhone"
            description="연락처를 입력하세요."
          >
            <b-form-input
              id="userPhone"
              ref="userPhone"
              v-model="input.userPhone"
              type="text"
              required
              placeholder="연락처 입력..."
            />
          </b-form-group>

          <b-row class="mb-1">
            <b-col class="text-right">
              <b-button variant="success" class="m-1" @click="validate">
                {{ btnText }}
              </b-button>
              <b-button
                v-if="isAdmin"
                variant="primary"
                class="m-1"
                @click="moveList"
                >목록</b-button
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
import { checkId } from "@/api/user";

const adminStore = "adminStore";
const userStore = "userStore";
export default {
  props: {
    type: { type: String },
    isAdmin: { type: Boolean },
    title: { type: String },
    btnText: { type: String },
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
          checkId(this.input.userId, ({ data }) => {
            if (!data) {
              alert("이미 존재하는 아이디입니다.");
              this.$refs.userId.focus();
              return;
            }
            this.createUser({
              userInfo: this.input,
              callback: () => {
                this.getUsers({ pgno: 1, key: "", word: "" });
              },
            });
            this.moveList();
          });
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
