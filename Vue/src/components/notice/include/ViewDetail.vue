<template>
  <b-row class="mb-1">
    <b-col>
      <b-card class="mb-2" border-variant="dark" no-body>
        <b-card-header class="text-left">
          <h3 class="mb-3">{{ article.subject }}(제목)</h3>
          <div style="color: #666">
            <h6 class="mb-1">{{ article.userId }}</h6>
            <h6>
              등록일 {{ article.registerTime }} | 조회수 {{ article.hit }}
            </h6>
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
