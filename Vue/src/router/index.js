import Vue from "vue";
import VueRouter from "vue-router";
import store from "@/store";

Vue.use(VueRouter);

const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["userStore/checkUserInfo"];
  const checkToken = store.getters["userStore/checkToken"];
  let token = sessionStorage.getItem("access-token");
  console.log("로그인 처리 전", checkUserInfo, token);

  if (checkUserInfo != null && token) {
    console.log("토큰 유효성 체크하러 가자!!!!");
    await store.dispatch("userStore/getUserInfo", token);
  }
  if (!checkToken || checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    
    // next({ name: "login" });
    router.push({ name: "login" });
  } else {
    console.log("로그인 했다!!!!!!!!!!!!!.");
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "Home",
    redirect: { name: "Map" },
  },
  {
    path: "/MyInfo",
    name: "MyInfo",
    beforeEnter: onlyAuthUser,
    component: () => import("@/components/common/MyInfo"),
  },
  {
    path: "/map",
    name: "Map",
    component: () => import("@/views/AppMap"),
    children: [
      {
        path: "/map/list",
        name: "MapList",
        component: () => import("@/components/map/MapViewSidebarList"),
      },
      {
        path: "/map/info",
        name: "MapInfo",
        component: () => import("@/components/map/MapViewSidebarInfo"),
      },
    ],
  },
  {
    path: "/main",
    name: "Main",
    component: () => import("@/views/AppMain"),
  },

  {
    path: "/admin",
    name: "admin",
    component: () => import("@/views/AppAdmin"),
    redirect: "/admin/list",
    children: [
      {
        path: "list",
        name: "AdminList",
        component: () => import("@/components/admin/AdminList.vue"),
      },
      {
        path: "join",
        name: "AdminJoin",
        component: () => import("@/components/admin/AdminJoin.vue"),
      },
      {
        path: "modify/:userId",
        name: "AdminModify",
        component: () => import("@/components/admin/AdminModify.vue"),
      },
    ],
  },
  {
    path: "/board",
    name: "board",
    component: () => import("@/views/AppBoard"),
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "BoardList",
        component: () => import("@/components/board/BoardList.vue"),
      },
      {
        path: "write",
        name: "BoardCreate",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardCreate.vue"),
      },
      {
        path: "detail/:articleNo",
        name: "BoardDetail",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardDetail.vue"),
      },
      {
        path: "modify/:articleNo",
        name: "BoardModify",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardModify.vue"),
      },
      {
        path: "delete/:articleNo",
        name: "BoardDelete",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardDelete.vue"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
