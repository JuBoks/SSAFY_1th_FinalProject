<template>
  <b-tr class="text-center">
    <b-td>{{ articleNo }}</b-td>
    <b-th>
      <router-link
        :to="{
          name: 'BoardDetail',
          params: { articleNo: articleNo },
        }"
        >{{ subject }}</router-link
      >
    </b-th>
    <b-td>{{ hit }}</b-td>
    <b-td>{{ registerTime }}</b-td>
  </b-tr>
</template>

<script>
export default {
  // 부모 component로 부터 전달받은 도서정보
  props: {
    articleNo: Number,
    hit: Number,
    registerTime: String,
    subject: String,
    userId: String,
  },
};
</script>

<style scope></style>
