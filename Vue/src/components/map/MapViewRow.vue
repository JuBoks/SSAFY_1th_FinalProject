<template>
  <b-list-group-item href="#" class="d-flex justify-content-between">
    <span class="col-apt text-truncate"
      >{{ info.dong }} {{ info.apartmentName }}</span
    >
    <span class="col-build-year">{{ info.buildYear }}</span>
  </b-list-group-item>
</template>

<script>
export default {
  props: {
    info: Object,
  },
  updated() {
    console.log("info", this.info);
  },
};
</script>

<style scoped>
</style>