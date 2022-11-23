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
      title: "회원가입",
      btnText: "회원가입",
    };
  },
  components: {
    "user-form": () => import("@/components/common/UserForm.vue"),
  },
};
</script>
