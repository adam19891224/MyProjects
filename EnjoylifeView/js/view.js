const Bar = { template: '<div>bar</div>' };

// 定义路由
const routes = [
    { path: '/', redirect: '/index' },
    { path: '/index', component: IndexView },
    { path: '/index/:page', component: IndexView, name: "index" },
    { path: '/series', component: SeriesView },
    { path: '/eyes', component: EyesView },
    { path: '/profile', component: Bar },
    { path: '/friends', component: Bar },
    { path: '/blog/:aid', component: Bar, name: "blog"},
    { path: '/genre/:typeName/1', component: Bar, name: "genre"}
];

// 创建 router 实例，然后传 `routes` 配置
const router = new VueRouter({
    routes: routes
});

// 创建和挂载根实例。
// 记得要通过 router 配置参数注入路由，
// 从而让整个应用都有路由功能
const app = new Vue({
    router
}).$mount('#application');