<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col class="text-center">
        <h3 class="underline"></h3>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" class="w-30" @click="listNotice"
          >목록</b-button
        >
      </b-col>
      <b-col class="text-right">
        <b-button
          variant="outline-info"
          size="sm"
          class="w-30 mr-2"
          @click="moveModifyArticle"
          >글수정</b-button
        >
        <b-button
          variant="outline-danger"
          size="sm"
          class="w-30"
          @click="deleteArticle"
          >글삭제</b-button
        >
      </b-col>
    </b-row>
    <view-detail :article="article"></view-detail>
    <comment-write></comment-write>

    <b-list-group>
      <comment-row
        v-for="comment in comments"
        :key="comment.commentNo"
        :articleNo="article.articleNo"
        :comment="comment"></comment-row>
    </b-list-group>
  </b-container>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
const noticeStore = "noticeStore";

export default {
  // Dynamic module loading을 통해 component를 불러옴
  components: {
    "view-detail": () => import("@/components/notice/include/ViewDetail.vue"),
  },
  // 서버로부터 isbn 번호 받기 전에는 0으로 설정
  methods: {
    ...mapActions(noticeStore, ["getArticle", "getComments"]),
    listNotice() {
      this.$router.push({ name: "NoticeList" });
    },
    moveModifyArticle() {
      this.$router.push({
        name: "NoticeModify",
        params: this.article.articleNo ? this.article.articleNo : 0,
      });
    },
    deleteArticle() {
      this.$router.push({
        name: "NoticeDelete",
        params: this.article.articleNo ? this.article.articleNo : 0,
      });
    },
  },
  computed: {
    ...mapGetters(noticeStore, ["article", "comments"]),
  },
  created() {
    const articleNo = this.$route.params.articleNo;
    // action 함수 호출
    this.getArticle(articleNo);
    this.getComments(articleNo);
  },
};
</script>
