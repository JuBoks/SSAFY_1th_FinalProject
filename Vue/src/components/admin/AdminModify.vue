<template>
  <div>
    <user-form
      :type="type"
      :isAdmin="isAdmin"
      :title="title"
      :btnText="btnText"></user-form>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
const adminStore = "adminStore";
const userStore = "userStore";
export default {
  components: {
    "user-form": () => import("@/components/common/UserForm.vue"),
  },
  data() {
    return {
      userId: "",
      type: "modify",
      isAdmin: false,
      title: "회원정보 수정",
      btnText: "수정하기",
    };
  },
  computed: {
    ...mapGetters(userStore, ["loginUser"]),
  },
  methods: {
    ...mapActions(adminStore, ["getUser"]),
    getinfo() {
      this.getUser(this.userId);
    },
  },
  created() {
    this.userId = this.$route.params.userId;
    this.isAdmin = this.loginUser && this.loginUser.userAuth == 1;
    this.getinfo();
  },
};
</script>
