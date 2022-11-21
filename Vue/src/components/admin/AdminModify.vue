<template>
  <div>
    <write-form type="modify"></write-form>
  </div>
</template>

<script>
import { mapActions } from "vuex";
const adminStore = "adminStore";
export default {
  // Dynamic module loading을 통해 component를 불러옴

  components: {
    "write-form": () => import("@/components/admin/include/WriteForm.vue"),
  },
  data() {
    return {
      userId: "",
    };
  },
  methods: {
    ...mapActions(adminStore, ["getUser"]),
    getinfo() {
      this.getUser(this.userId);
    },
  },
  created() {
    this.userId = this.$route.params.userId;
    this.getinfo();
  },
};
</script>
