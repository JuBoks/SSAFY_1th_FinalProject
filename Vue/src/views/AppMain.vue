<template>
  <b-container class="bv-home-row mt-5">
    <b-row>
      <b-col>
        <b-card
          title="매물을 검색해보세요!"
          :img-src="require('@/assets/img/Home.jpg')"
          img-alt="Image"
          img-top
          tag="article"
          style="max-width: 20rem"
          class="mb-6">
          <b-button @click="movePage" variant="primary">탐험하기</b-button>
        </b-card>
      </b-col>

      <b-col cols="8">
        <div v-for="(Newsone, index) in this.news" :key="index">
          <b-card
            no-body
            class="overflow-hidden mb-3"
            style="max-width: 1000px">
            <b-row no-gutters>
              <b-col cols="4">
                <b-card-img
                  width="100%"
                  height="100%"
                  :src="Newsone.img"
                  class="rounded-0"></b-card-img>
              </b-col>
              <b-col cols="8">
                <b-card-body>
                  <b-card-title title-tag="h5"
                    ><a :href="Newsone.url" target="_blank">{{
                      Newsone.subject
                    }}</a></b-card-title
                  >
                  <b-card-text>{{ Newsone.contents }}</b-card-text>
                </b-card-body>
              </b-col>
            </b-row>
          </b-card>
        </div>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
const newsStore = "newsStore";
export default {
  computed: {
    ...mapGetters(newsStore, ["news"]),
  },
  methods: {
    ...mapActions(newsStore, ["getAllNews"]),
    movePage() {
      this.$router.push({ name: "Home" });
    },
  },

  created() {
    this.getAllNews();
  },
};
</script>

<style>
video {
  min-width: 100%;
  min-height: 100%;
  width: auto;
  height: auto;
  z-index: -100;
  background-size: cover;
  overflow: hidden;
}
.i {
  margin: 10px;
  float: left;
  width: 500px;
  height: 500px;
  object-fit: cover;
}
</style>
