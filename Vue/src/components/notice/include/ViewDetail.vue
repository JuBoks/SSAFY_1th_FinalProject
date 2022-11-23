<template>
  <b-row class="mb-1">
    <b-col>
      <b-card class="mb-2" border-variant="dark" no-body>
        <b-card-header class="text-center">
          <h3>{{ article.subject }}(제목)</h3>
          <h5>({{ article.articleNo }}번째 글)</h5>
          <div>
            <h6>{{ article.userId }}작성자</h6>
          </div>
          <div>
            <h6>{{ article.registerTime }}</h6>
          </div>
        </b-card-header>
        <b-card-body class="text-left">
          <div :inner-html.prop="article.content | filterEnterToBr"></div>
        </b-card-body>
      </b-card>
    </b-col>
  </b-row>
</template>

<script>
export default {
  // 부모 component로 부터 전달받은 도서정보
  props: {
    article: Object,
  },
};
</script>

<style scoped></style>
