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
export default {
  data() {
    return {
      type: "create",
      isAdmin: false,
      title: "회원정보 등록",
      btnText: "등록하기",
    };
  },
  components: {
    "user-form": () => import("@/components/common/UserForm.vue"),
  },
  created() {
    this.isAdmin = this.loginUser && this.loginUser.userAuth == 1;
  },
};
</script>
