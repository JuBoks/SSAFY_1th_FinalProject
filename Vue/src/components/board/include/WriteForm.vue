<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col class="text-center">
        <h3 v-if="type == 'create'" class="underline">QnA 등록</h3>
        <h3 v-else class="underline">QnA 수정</h3>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-form>
          <b-form-group
            label-cols="12"
            id="subject-group"
            label="제목:"
            label-for="subject"
            description="제목을 입력하세요.">
            <b-form-input
              id="subject"
              ref="subject"
              v-model="input.subject"
              type="text"
              required
              placeholder="제목 입력..." />
          </b-form-group>
          <b-form-group
            label-cols="12"
            id="content-group"
            label="내용:"
            label-for="content"
            ref="content">
            <b-form-textarea
              id="content"
              v-model="input.content"
              placeholder="내용 입력..."
              rows="10"
              max-rows="15"></b-form-textarea>
          </b-form-group>

          <b-button
            v-if="type == 'create'"
            variant="primary"
            class="m-1"
            @click="validate"
            >글 등록</b-button
          >
          <b-button v-else variant="success" class="m-1" @click="validate"
            >글 수정</b-button
          >
          <b-button variant="primary" class="m-1" @click="moveList"
            >목록</b-button
          >
        </b-form>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
const boardStore = "boardStore";

export default {
  props: {
    // 부모 component로 부터 전달받은 type 정보를 받아옴
    type: { type: String },
  },
  methods: {
    ...mapActions(boardStore, ["createArticle", "modifyArticle", "getArticle"]),
    validate() {
      let isValid = true;
      let errMsg = "";

      !this.input.subject
        ? ((isValid = false),
          (errMsg = "글제목을 입력해주세요."),
          this.$refs.subject.focus())
        : !this.input.content
        ? ((isValid = false),
          (errMsg = "글 내용을 입력해주세요."),
          this.$refs.content.focus())
        : (isValid = true);

      if (!isValid) {
        alert(errMsg);
      } else {
        const payload = {
          article: this.input,
          callback: () => {
            this.moveList();
          },
        };
        if (this.type == "create") {
          this.createArticle(payload);
        } else {
          this.modifyArticle(payload);
        }
      }
    },
    moveList() {
      this.$router.push({ name: "BoardList" });
    },
  },
  computed: {
    ...mapGetters(boardStore, ["article"]),
    input() {
      if (this.type == "modify") {
        // 브라우저 새로고침을 하게되면 state에 저장된 데이터가 사라짐
        // Vuex의 state.article 객체가 비어있다면 서버에 요청하기
        //this.getArticle(this.$route.params.article);

        // 수정화면일 경우
        return { ...this.article }; // Vuex의 state.articles 객체에 직접 접근하는걸 막기 위해 Deep Copy
      } else {
        // 등록화면일 경우
        return {
          content: "",
          // hit: 0,
          // registerTime: "",
          subject: "",
          // userId: "",
        };
      }
    },
  },
};
</script>

<style scoped></style>
