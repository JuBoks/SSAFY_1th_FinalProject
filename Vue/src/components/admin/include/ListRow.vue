<template>
  <b-tr class="text-center">
    <b-td>{{ user.userId }}</b-td>
    <b-th>{{ user.userName }}</b-th>
    <b-td>{{ user.userAddr }}</b-td>
    <b-td>{{ user.userPhone }}</b-td>
    <b-td
      ><b-button variant="info" size="sm" class="w-30" @click="moveModifyUser"
        >유저수정</b-button
      ></b-td
    >
    <b-td>
      <b-button variant="danger" size="sm" class="w-30" @click="deleteu"
        >유저삭제</b-button
      >
    </b-td>
  </b-tr>
</template>

<script>
import { mapActions } from "vuex";
const adminStore = "adminStore";
export default {
  // 부모 component로 부터 전달받은 도서정보
  props: {
    user: Object,
  },

  methods: {
    ...mapActions(adminStore, ["deleteUser", "modifyUser", "getUsers"]),
    deleteu() {
      this.deleteUser({
        userId: this.user.userId,
        callback: () => {
          this.getUsers({ pgno: 1, key: "", word: "" });
        },
      });
    },

    moveModifyUser() {
      this.$router.push({
        name: "AdminModify",
        params: {
          userId: this.user.userId,
        },
      });
    },
  },
};
</script>

<style scope></style>
