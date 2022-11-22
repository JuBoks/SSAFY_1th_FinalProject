<template>
  <footer>
    <div class="foo_div">
      <!-- <img :img-src="require('@/assets/img/logo.png')" /> -->
    </div>
    <div class="foo_div1">
      <span>(주)samsung</span> <span> | </span> <span>대표 : 송주영</span>
      <span> | </span> <span>TEL : 010-5899-1114</span>
      <span> | </span>
      <p>주소 : 부산 강서구 녹산동 삼성전기 멀티캠퍼스</p>
    </div>
  </footer>
</template>

<script>
export default {};
</script>

<style>
footer {
  background-color: #3c3935;
}

footer img {
  width: 90px;
  height: 90px;
  border-radius: 50%;
  margin-top: 7px;
}

.foo_div {
  float: left;
}

.foo_div1 {
  text-align: center;
}

.foo_div1 span {
  font-size: 15px;
  color: white;
}

.foo_div1 p {
  margin-top: 5px;
  color: white;
}

.foo_div1 img {
  width: 25px;
  height: 25px;
  margin-top: 10px;
}
</style>
