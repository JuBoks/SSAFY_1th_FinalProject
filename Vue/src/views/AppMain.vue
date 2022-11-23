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
        <div>
          <img class="i" src="@/assets/img/LOCAL.jpg" />
        </div>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  methods: {
    movePage() {
      this.$router.push({ name: "Home" });
    },
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
