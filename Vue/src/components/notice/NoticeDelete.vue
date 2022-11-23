<template>
  <div>
    <h1>QnA 글 삭제</h1>
    <div>삭제중...</div>
  </div>
</template>

<script>
import { mapActions } from "vuex";

const noticeStore = "noticeStore";

export default {
  methods: {
    ...mapActions(noticeStore, ["deleteArticle"]),
  },
  created() {
    if (confirm("삭제하시겠습니까?")) {
      const articleNo = this.$route.params.articleNo;
      const payload = {
        articleNo: articleNo,
        callback: () => {
          this.$router.push({ name: "NoticeList" });
        },
      };

      this.deleteArticle(payload);
    }
  },
};
</script>
