<template>
  <div class="box p-3">
    <!-- <b-input-group style="width: 240px" prepend="작성자">
      <b-form-input
        placeholder="작성자 입력..."
        v-model="userId"></b-form-input>
    </b-input-group> -->
    <b-input-group>
      <b-form-textarea
        placeholder="댓글 입력..."
        rows="3"
        max-rows="6"
        v-model="comment"
      ></b-form-textarea>
      <b-input-group-append>
        <b-button variant="secondary" @click="write">등록</b-button>
      </b-input-group-append>
    </b-input-group>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

const boardStore = "boardStore";
const userStore = "userStore";

export default {
  data() {
    return {
      userId: "",
      comment: "",
    };
  },
  methods: {
    ...mapActions(boardStore, ["createComment", "getComments"]),
    write() {
      const payload = {
        comment: {
          articleNo: this.article.articleNo,
          userId: this.loginUser.userId,
          comment: this.comment,
        },
        callback: () => {
          // 기존 입력한 내용 지우기
          this.userId = "";
          this.comment = "";

          // 서버에 입력한 내용이 포함된 댓글 목록 요청하기
          this.getComments(this.article.articleNo);

          // Bootstrap Vue를 이용하여 메시지 출력(Toast)
          this.$bvToast.toast("댓글이 등록되었습니다.", {
            title: "댓글 알림", // 토스트 창 제목
            variant: "success", // 토스트 창 색상 (primary, secondary ... 등등)
            toaster: "b-toaster-bottom-center", // 토스트 출력 위치 (공식문서 확인하기)
            autoHideDelay: 3000, // 3초 뒤 사라짐
            solid: true, // 댓글 창 불투명처리
          });
        },
      };

      this.createComment(payload);
    },
  },
  computed: {
    ...mapGetters(boardStore, ["article"]),
    ...mapGetters(userStore, ["loginUser"]),
  },
};
</script>

<style scoped>
.box {
  border: 1px solid #eee;
  background-color: #eee;
  border-radius: 0.3rem;
}
</style>
