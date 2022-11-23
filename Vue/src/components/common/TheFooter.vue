<template>
  <footer>
    <div class="foo_div">
      <p>주소 : 부산 강서구 녹산동 삼성전기 멀티캠퍼스</p>
    </div>
  </footer>
</template>

<script>
export default {};
</script>

<style scoped>
footer {
  background-color: #eee;
  width: 100%;
  height: 5rem;
  float: left;
  align-items: center;
  justify-content: space-around;
  display: flex !important;
  position: absolute;
  bottom: 0;
}

.foo_div {
  text-align: center;
}

.foo_div span {
  font-size: 15px;
  color: white;
}

.foo_div p {
  margin-top: 5px;
  color: #838383;
}
</style>
