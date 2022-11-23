<template>
  <b-container class="bv-example-row">
    <b-row>
      <b-col class="m-5">
        <b-card
          title="당신에게 딱 맞는 집!"
          :img-src="require('@/assets/img/Home.jpg')"
          img-alt="Image"
          img-top
          tag="article"
          style="max-width: 20rem"
          class="mb-6">
          <b-button @click="movePage" variant="primary">바로 검색</b-button>
        </b-card>
      </b-col>

      <b-col>
        <li v-for="(Newsone, index) in this.news" :key="index">
          <b-card no-body class="overflow-hidden" style="max-width: 1000px">
            <b-row no-gutters>
              <b-col md="6">
                <b-card-img :src="Newsone.img" class="rounded-0"></b-card-img>
              </b-col>
              <b-col md="6">
                <b-card-body :title="Newsone.subject">
                  <b-card-text>
                    <a :href="Newsone.url" target="_blank">{{
                      Newsone.contents
                    }}</a>
                  </b-card-text>
                </b-card-body>
              </b-col>
            </b-row>
          </b-card>
        </li>
      </b-col>
      <!-- <img class="i" src="@/assets/img/LOCAL.jpg" /> -->
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
    console.log("화면띄우고");
    this.getAllNews();
    console.log(this.news);
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
