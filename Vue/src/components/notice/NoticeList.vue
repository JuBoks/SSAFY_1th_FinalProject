<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col class="text-center">
        <h3 class="underline">공지사항</h3>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-form inline>
          <b-form-select
            id="inline-form-custom-select-pref"
            class="mb-2 mr-sm-1 mb-sm-0"
            :options="searchOption"
            v-model="selected"></b-form-select>
          <b-form-input
            v-model="word"
            class="mr-sm-1"
            placeholder="검색어..."
            @keydown.native="search"></b-form-input>
          <b-button variant="primary" @click="doSearch">검색</b-button>
        </b-form>
      </b-col>
      <b-col class="text-right">
        <b-button variant="outline-primary" @click="movePage">등록</b-button>
      </b-col>
    </b-row>
    <b-row>
      <b-col v-if="articles">
        <b-table-simple hover responsive>
          <b-thead head-variant="dark">
            <b-tr class="text-center">
              <b-th>글번호</b-th>
              <b-th>제목</b-th>
              <b-th>조회수</b-th>
              <b-th>작성일</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <list-row
              v-for="(article, index) in articles"
              :key="index"
              :articleNo="article.articleNo"
              :content="article.content"
              :hit="article.hit"
              :registerTime="article.registerTime"
              :subject="article.subject"
              :userId="article.userId"></list-row>
          </tbody>
        </b-table-simple>
      </b-col>
      <b-col v-else class="text-center">글 목록이 없습니다.</b-col>
    </b-row>
    <!-- Page navigation -->

    <!--
      total-rows: 총 게시글의 수
      per-page: 1페이지당 몇개의 게시글을 보여줄 것인지
     -->
    <b-pagination
      align="center"
      v-model="currentPage"
      :total-rows="totalArticles"
      :per-page="articlesPerPage"
      @page-click="pageClick"
      first-number
      last-number></b-pagination>
  </b-container>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

const boardStore = "boardStore";

export default {
  data() {
    return {
      selected: null,
      word: "",
      currentPage: 1, // 현재 조회중인 페이지 번호
      searchOption: [
        { text: "검색조건", value: null },
        { text: "제목", value: "subject" },
        { text: "작성자", value: "userid" },
        { text: "정확도", value: "precise" },
      ],
    };
  },
  computed: {
    ...mapGetters(boardStore, ["articles", "articlesPerPage", "totalArticles"]),
  },
  // Dynamic module loading을 통해 component를 불러옴
  components: {
    "list-row": () => import("@/components/board/include/ListRow.vue"),
  },
  methods: {
    ...mapActions(boardStore, ["getArticles", "getArticleCount"]),
    movePage: function () {
      this.$router.push({ name: "NoticeCreate" });
    },
    linkGen(pageNum) {
      // key, word 처리하기
      return pageNum === 1
        ? "?"
        : `?pgno=${pageNum}&key=${this.selected}&word=${this.word}`;
    },
    pageClick(bvEvent, page) {
      this.getArticles({
        pgno: page,
        key: this.selected,
        word: this.word,
      });
    },
    doSearch() {
      // 조건에 맞는 게시글 검색
      this.getArticles({
        pgno: 1,
        key: this.selected,
        word: this.word,
      });
      // 조건에 맞는 게시글의 개수를 가져오기
      this.getArticleCount({
        key: this.selected,
        word: this.word,
      });
    },
    search(e) {
      if (e.which === 13) {
        // Enter
        e.preventDefault();
        // 조건에 맞는 게시글 검색
        this.doSearch();
      }
    },
  },
  created() {
    // 개시글 총 개수 가져오기
    this.getArticleCount({ key: null, word: null });

    // 첫 페이지 가져오기
    this.getArticles({
      pgno: this.currentPage,
      key: this.selected,
      word: this.word,
    });
  },
};
</script>
