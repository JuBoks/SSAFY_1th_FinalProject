import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    redirect: { name: "Map" },
  },
  {
    path: "/map",
    name: "Map",
    component: () => import("@/views/AppMap"),
  },
  {
    path: "/main",
    name: "Main",
    component: () => import("@/views/AppMain"),
  },
  {
    path: "/admin",
    name: "Admin",
    component: () => import("@/views/AppAdmin"),
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
        component: () => import("@/components/board/BoardCreate.vue"),
      },
      {
        path: "detail/:articleNo",
        name: "BoardDetail",
        component: () => import("@/components/board/BoardDetail.vue"),
      },
      {
        path: "modify/:articleNo",
        name: "BoardModify",
        component: () => import("@/components/board/BoardModify.vue"),
      },
      {
        path: "delete/:articleNo",
        name: "BoardDelete",
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
