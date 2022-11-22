<template>
  <b-container class="bv-example-row">
    <b-row>
      <b-col class="m-2">
        <b-card
          border-variant="primary"
          header="메뉴"
          header-bg-variant="primary"
          header-text-variant="white"
          align="center">
          <b-card
            title="집 보러 가자!"
            :img-src="require('@/assets/img/Home.jpg')"
            img-alt="Image"
            img-top>
          </b-card>
        </b-card>
      </b-col>
      <b-col style="overflow: hidden"> 오른쪽 </b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {};
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
</style>
