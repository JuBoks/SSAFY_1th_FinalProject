<template>
  <b-list-group-item class="flex-column align-items-start">
    <div class="d-flex w-100 justify-content-between">
      <h5 class="mb-1">{{ comment.userName }}</h5>
      <small class="text-muted">{{ comment.commentTime }}</small>
    </div>

    <p :inner-html.prop="comment.comment | filterEnterToBr"></p>

    <div class="text-right">
      <b-button-group>
        <b-button variant="secondary" @click="showModalModify">수정</b-button>
        <b-button variant="outline-secondary" @click="showModalDelete"
          >삭제</b-button
        >
      </b-button-group>
    </div>

    <!-- 수정 모달 창 작성 -->
    <b-modal
      :ref="`comment-${comment.commentNo}`"
      title="댓글 수정"
      header-bg-variant="dark"
      header-text-variant="light"
      centered
      hide-footer>
      <!-- 수정 모달 창 Body 작성 -->
      <div>
        <b-input-group style="width: 240px" prepend="작성자">
          <b-form-input
            v-model="input.userName"
            placeholder="작성자 입력..."
            readonly></b-form-input>
        </b-input-group>
        <b-form-textarea
          v-model="input.comment"
          placeholder="댓글 입력..."
          rows="3"
          max-rows="6"></b-form-textarea>
      </div>

      <!-- 수정 모달 창 Footer 작성 -->
      <div class="text-right">
        <b-button-group>
          <b-button variant="outline-secondary">취소</b-button>
          <b-button variant="secondary" @click="modify">수정</b-button>
        </b-button-group>
      </div>
    </b-modal>
  </b-list-group-item>
</template>

<script>
import { mapActions } from "vuex";

const boardStore = "boardStore";

export default {
  props: {
    articleNo: Number,
    comment: Object,
  },
  methods: {
    ...mapActions(boardStore, [
      "modifyComment",
      "getComments",
      "deleteComment",
    ]),
    showModalModify() {
      this.$refs[`comment-${this.comment.commentNo}`].show();
    },
    hideModalModify() {
      this.$refs[`comment-${this.comment.commentNo}`].hide();
    },
    modify() {
      const payload = {
        comment: {
          commentNo: this.comment.commentNo,
          comment: this.input.comment,
        },
        callback: () => {
          // 수정한 내용 지우기
          this.input.userName = "";
          this.input.comment = "";

          // 댓글 목록 갱신하기
          console.log(this.articleNo, "돼라");
          this.getComments(this.articleNo);

          // 모달창 숨기기
          this.hideModalModify();

          // 수정 완료 Toast 출력
          this.$bvToast.toast("댓글이 수정되었습니다.", {
            title: "댓글 알림",
            variant: "success",
            toaster: "b-toaster-bottom-center",
            autoHideDelay: 3000,
            solid: true,
          });
        },
      };

      this.modifyComment(payload);
    },
    showModalDelete() {
      this.$bvModal
        .msgBoxConfirm("정말로 삭제 하시겠습니까?", {
          centered: true,
        })
        .then((value) => {
          console.log(value);
          if (value) {
            this.delete();
          }
        });
    },
    delete() {
      const payload = {
        commentNo: this.comment.commentNo,
        callback: () => {
          // 댓글 목록 갱신
          this.getComments(this.articleNo);

          // 삭제 완료 Toast 출력
          setTimeout(() => {
            this.$bvToast.toast("댓글이 삭제되었습니다.", {
              title: "댓글 알림",
              variant: "success",
              toaster: "b-toaster-bottom-center",
              autoHideDelay: 3000,
              solid: true,
            });
          }, 500);
        },
      };

      this.deleteComment(payload);
    },
  },
  computed: {
    input() {
      return { ...this.comment }; // Vuex의 state.comments 객체에 직접 접근하는걸 막기 위해 Deep Copy
    },
  },
};
</script>

<style></style>
