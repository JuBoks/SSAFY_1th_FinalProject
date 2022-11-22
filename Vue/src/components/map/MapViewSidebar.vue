<template>
  <div :class="{ 'nav-open': panelFlag }" id="mySidebar" class="sidebar">
    <div class="sidebar-header">
      <a href="javascript:void(0)" @click="goback">
        <span class="sidebar-header-btn">←</span>
      </a>
      <span class="sidebar-header-name">{{
        this.aptSelected.apartmentName
      }}</span>
      <a href="javascript:void(0)" class="closebtn" @click="closePanel">
        <span class="sidebar-header-btn">×</span>
      </a>
    </div>
    <router-view></router-view>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

const mapStore = "mapStore";

export default {
  props: ["panelFlag"],
  computed: {
    ...mapGetters(mapStore, ["aptSelected"]),
  },

  methods: {
    ...mapActions(mapStore, ["setPanelOpen"]),

    closePanel() {
      this.$parent.$emit("onPanelClose");
    },
    goback() {
      this.$router.go(-1);
    },
  },
};
</script>

<style scoped>
@media screen and (max-height: 450px) {
  .sidebar {
    padding-top: 15px;
  }
  .sidebar a {
    font-size: 18px;
  }
}
a {
  text-decoration: none;
}
.sidebar-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0.5rem 1rem;
}
.sidebar-header-name {
  font-size: 24px;
}
.sidebar-header-btn {
  font-size: 36px;
}
.sidebar {
  position: absolute;
  height: 100%;
  width: 0;
  z-index: 3;
  top: 0;
  left: 0;
  background-color: white;
  overflow-x: hidden;
  transition: 0.5s;
}
/* .sidebar .closebtn {
  position: absolute;
  top: 0;
  right: 25px;
  font-size: 36px;
  margin-left: 50px;
} */

/* sidebar 클래스 */
.nav-open {
  width: 390px;
}
</style>
