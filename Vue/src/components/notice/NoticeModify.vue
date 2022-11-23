<template>
  <div>
    <write-form type="modify"></write-form>
  </div>
</template>

<script>
export default {
  // Dynamic module loading을 통해 component를 불러옴
  components: {
    "write-form": () => import("@/components/notice/include/WriteForm.vue"),
  },
};
</script>
