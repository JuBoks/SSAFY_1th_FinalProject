<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col class="text-center">
        <h3 class="underline">사용자 관리</h3>
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
        <b-button variant="outline-primary" @click="movePage"
          >사용자 등록</b-button
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col v-if="users">
        <b-table-simple hover responsive>
          <b-thead head-variant="dark">
            <b-tr class="text-center">
              <b-th>아이디</b-th>
              <b-th>이름</b-th>
              <b-th>주소</b-th>
              <b-th>연락처</b-th>
              <b-th>수정하기</b-th>
              <b-th>삭제하기</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <list-row
              v-for="(user, index) in users"
              :key="index"
              :user="user"></list-row>
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
      :total-rows="totalUsers"
      :per-page="usersPerPage"
      @page-click="pageClick"
      first-number
      last-number></b-pagination>
  </b-container>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
const adminStore = "adminStore";
const userStore = "userStore";

export default {
  data() {
    return {
      selected: null,
      word: "",
      currentPage: 1, // 현재 조회중인 페이지 번호
      searchOption: [
        { text: "검색조건", value: null },
        { text: "아이디", value: "userId" },
        { text: "핸드폰번호", value: "userPhone" },
      ],
    };
  },
  computed: {
    ...mapGetters(adminStore, ["users", "usersPerPage", "totalUsers"]),
    ...mapGetters(userStore, ["loginUser"]),
  },
  // Dynamic module loading을 통해 component를 불러옴
  components: {
    "list-row": () => import("@/components/admin/include/ListRow.vue"),
  },
  methods: {
    ...mapActions(adminStore, ["getUsers", "getUserCount"]), //"getUserCount"넣어줘야함 나중에
    movePage: function () {
      this.$router.push({ name: "AdminJoin" });
    },
    linkGen(pageNum) {
      // key, word 처리하기
      return pageNum === 1
        ? "?"
        : `?pgno=${pageNum}&key=${this.selected}&word=${this.word}`;
    },
    pageClick(bvEvent, page) {
      this.getUsers({
        pgno: page,
        key: this.selected,
        word: this.word,
      });
    },
    doSearch() {
      // 조건에 맞는 게시글 검색
      this.getUsers({
        pgno: 1,
        key: this.selected,
        word: this.word,
      });
      // 조건에 맞는 게시글의 개수를 가져오기
      this.getUserCount({
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
    this.getUserCount({ key: null, word: null });

    // 첫 페이지 가져오기
    this.getUsers({
      pgno: this.currentPage,
      key: this.selected,
      word: this.word,
    });
  },
};
</script>

<style></style>
