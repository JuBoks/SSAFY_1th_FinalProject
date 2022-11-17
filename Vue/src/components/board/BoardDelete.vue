<template>
  <div>
    <h1>QnA 글 삭제</h1>
    <div>삭제중...</div>
  </div>
</template>

<script>
import { mapActions } from "vuex";
export default {
  methods: {
    ...mapActions(["deleteArticle"]),
  },
  created() {
    if (confirm("삭제하시겠습니까?")) {
      const articleNo = this.$route.params.articleNo;
      const payload = {
        articleNo: articleNo,
        callback: () => {
          this.$router.push({ name: "BoardList" });
        },
      };

      this.deleteArticle(payload);
    }
  },
};
</script>
