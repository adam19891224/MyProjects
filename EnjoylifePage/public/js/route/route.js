// 定义路由
const routes = [
    { path: '/', redirect: '/index' },
    { path: '/index', components: {
        main: IndexView,
        info: InfoView,
        cloud: TarCloudView
    } },
    { path: '/index/:page', components: {
        main: IndexView,
        info: InfoView,
        cloud: TarCloudView
    }, name: "index" },
    { path: '/series', components: {
        main: SeriesView,
        info: InfoView,
        cloud: TarCloudView
    } },
    { path: '/eyes', components: {
        main: EyesView,
        info: InfoView,
        cloud: TarCloudView
    }, name: "eyes" },
    { path: '/profile', components: {
        main: ProfileView,
        info: InfoView,
        cloud: TarCloudView
    } },
    { path: '/friends', components: {
        main: FriendsView,
        info: InfoView,
        cloud: TarCloudView
    } },
    { path: '/genre/:typeName/:p', components: {
        main: CategoryView,
        info: InfoView,
        cloud: TarCloudView
    }, name: "genre"},
    { path: '/query/:searchName/:p', components: {
        main: QueryView,
        info: InfoView,
        cloud: TarCloudView
    }, name: "query"},
    { path: '/blogs/:aid', components: {
        main: BlogView,
        info: InfoView,
        cloud: TarCloudView
    }, name: "blog"},
    { path: '*', components: {
        main: ErrorView,
        info: InfoView,
        cloud: TarCloudView
    } }
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