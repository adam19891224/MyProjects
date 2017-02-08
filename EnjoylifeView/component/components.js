Vue.filter('ymd', function (value, formatString) {
    formatString = formatString || 'YYYY-MM-DD';
    return moment(value).format(formatString);
});
Vue.filter('md', function (value, formatString) {
    formatString = formatString || 'MM-DD';
    return moment(value).format(formatString);
});
Vue.filter('y', function (value, formatString) {
    formatString = formatString || 'YYYY';
    return moment(value).format(formatString);
});
Vue.filter('m', function (value, formatString) {
    formatString = formatString || 'MM';
    return moment(value).format(formatString);
});

// 定义首页组件
const IndexView = {
    template:
    `<main id="main" class="main">
        <div id="main-left" class="main-left">
            <div id="left-body" class="left-body">
                <div class="main-blog" v-for="blog in blogs">
                    <div class="blog-head">
                       <router-link tag="h2" :to="{ name: 'blog', params: { aid: blog.articleSid } }">
                            <a class="link-head">{{blog.articleTitle}}</a>
                       </router-link>
                    </div>
                    <div class="blog-intro">
                        <span>评论数：({{blog.comments}})</span>
                        <span>by：{{blog.createDate | ymd}}</span>
                    </div>
                    <div class="blog-line"></div>
                    <div class="blog-type"></div>
                    <div class="blog-body">
                        <router-link :to="{ name: 'blog', params: { aid: blog.articleSid } }">
                            <img src="../images/image-loading.gif" class="b-lazy" :data-src="blog.articleImg" :alt="blog.articleTitle"/>
                        </router-link>
                        <span>
                            {{blog.articleDescription}}
                        </span>
                    </div>
                </div>
            </div>
            <div class="blog-page">
                <div id="page-div" class="page-div" :data-pages="totalPages" :data-current="page"></div>
            </div>
        </div>
        <div class="main-right">
            <div class="right-body">
                <div class="head-image">
                    <img src="../images/default-head.jpg" />
                </div>
                <div class="msg-intro">
                    <div class="intro-blogs">
                        <span>文章</span>
                        <span>
                            {{totalArticles}}
                        </span>
                    </div>
                    <div class="intro-types">
                        <span>类别</span>
                        <span>
                            {{totalTypes}}
                        </span>
                    </div>
                </div>
            </div>
        </div>
    </main>`,
    //给模板绑定数据
    data () {
        return {
            blogs: null,
            totalPages: null,
            page: null,
            totalArticles: null,
            totalTypes: null
        }
    },
    //模板创建完毕后，获取数据
    created () {
        this.fetchData();
    },
    watch: {
        /*
          由于vue中定义，当多个路由指向同一个组件时，即/index/1, /index/2指向同一个组件时，vue会复用组件，所以这就导致了组件的生命周期钩子
          不会被调用，所以上面的created方法就不会被调用，那么造成的影响就是数据不会重新加载，所以需要监测变化，即调用wache方法，该方法内部用于
          监测$route对象的变化
        */
        '$route': 'fetchData' //$route发生变化后，执行fetchdata方法
    },
    methods: {
        fetchData () {
            var _this = this;
            var page = _this.$route.params.page;
            if(!page){
                page = 1;
            }
            // 组件创建完后获取数据，
            _this.$http.post('http://localhost:8888/index/' + page).then(response => {
                // get body data
                var result = response.body;
                if(result.code == "Y"){
                    var data = result.data;
                    _this.blogs = data.blogs;
                    _this.totalPages = data.totalPages;
                    _this.totalArticles = data.totalArticles;
                    _this.page = data.page;
                    _this.totalTypes = data.totalTypes;
                }else{
                    alert("加载数据错误");
                    console.log(result.message);
                }
            }, response => {
                console.log("异常")
            }).then(() => {
                index.init();
            });
        }
    }
};

// 定义专题组件
const SeriesView = {
    template:
    `<main id="main" class="main">
        <div id="main-left" class="main-left">
            <div class="left-body">
                <ul class="series-main">
                    <li v-for="serie in series">
                        <router-link to="/index">
                            <h2>{{serie.seriesName}}</h2>
                        </router-link>
                        <span>{{serie.counts}} 篇</span>
                    </li>
                </ul>
            </div>
        </div>
        <div class="main-right">
            <div class="right-body">
                <div class="head-image">
                    <img src="../images/default-head.jpg" />
                </div>
                <div class="msg-intro">
                    <div class="intro-blogs">
                        <span>文章</span>
                        <span>
                            {{totalArticles}}
                        </span>
                    </div>
                    <div class="intro-types">
                        <span>类别</span>
                        <span>
                            {{totalTypes}}
                        </span>
                    </div>
                </div>
            </div>
            <div class="right-body">
                <div id="type-body" class="cloud-div">
                    <router-link v-for="type in types" :to="{ name: 'genre', params: { typeName: type.typeName } }" :tn="type.typeName">{{type.typeName}}</router-link>
                </div>
            </div>
        </div>
    </main>`,
    //给模板绑定数据
    data () {
        return {
            series: null,
            totalArticles: null,
            totalTypes: null,
            types: null
        }
    },
    //模板创建完毕后，获取数据
    created () {
        this.fetchData();
    },
    watch: {
        /*
         由于vue中定义，当多个路由指向同一个组件时，即/index/1, /index/2指向同一个组件时，vue会复用组件，所以这就导致了组件的生命周期钩子
         不会被调用，所以上面的created方法就不会被调用，那么造成的影响就是数据不会重新加载，所以需要监测变化，即调用wache方法，该方法内部用于
         监测$route对象的变化
         */
        '$route': 'fetchData' //$route发生变化后，执行fetchdata方法
    },
    methods: {
        fetchData () {
            var _this = this;
            // 组件创建完后获取数据，
            _this.$http.post('http://localhost:8888/series').then(response => {
                // get body data
                var result = response.body;
                if(result.code == "Y"){
                    var data = result.data;
                    _this.series = data.series;
                    _this.totalArticles = data.totalArticles;
                    _this.totalTypes = data.totalTypes;
                    _this.types = data.types;
                }else{
                    alert("加载数据错误");
                    console.log(result.message);
                }
            }, response => {
                console.log("异常")
            }).then(() => {
                applications.startCloud();
            });
        }
    }
};

//定义一览组件
const EyesView = {
    template:
    `<main id="main" class="main">
        <div id="main-left" class="main-left">
            <div class="eyes-time">
                <div id="time-div" class="time-div">
                    <div class="time-year" v-for="(year, yindex) in times">
                        <span :time-year="year.date">
                            <em :class="[yindex == 0 ? 'now-year' : '']">{{year.date}}</em>
                            <i :class="[yindex == 0 ? 'yead-front-up' : '']"></i>
                        </span>
                        <ul :class="[yindex == 0 ? '' : 'display-ul']">
                            <li v-for="(month, mindex) in year.list" :class="[(yindex == 0 && mindex == 0) ? 'now-month' : '']" :t-m="month.date">
                                {{month.date}}月
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="eyes-title">
                <div id="container-title" class="eyes-container-title">
                    <h2 v-for="blog in blogs" :data-year="blog.createDate | y" :data-month="blog.createDate | m">
                        <span class="eyes-title-time">
                            {{blog.createDate | md}}
                        </span>
                        <span class="eyes-title-content">
                            <a href="/blogs/blog.articleSid" class="link-head">
                                {{blog.articleTitle}}
                            </a>
                        </span>
                    </h2>
                </div>
            </div>
        </div>
        <div class="main-right">
            <div class="right-body">
                <div class="head-image">
                    <img src="../images/default-head.jpg" />
                </div>
                <div class="msg-intro">
                    <div class="intro-blogs">
                        <span>文章</span>
                        <span>
                            {{totalArticles}}
                        </span>
                    </div>
                    <div class="intro-types">
                        <span>类别</span>
                        <span>
                            {{totalTypes}}
                        </span>
                    </div>
                </div>
            </div>
            <div class="right-body">
                <div id="type-body" class="cloud-div">
                    <router-link v-for="type in types" :to="{ name: 'genre', params: { typeName: type.typeName } }" :tn="type.typeName">{{type.typeName}}</router-link>
                </div>
            </div>
        </div>
    </main>`,
    //给模板绑定数据
    data () {
        return {
            blogs: null,
            times: null,
            totalArticles: null,
            totalTypes: null,
            types: null
        }
    },
    //模板创建完毕后，获取数据
    created () {
        this.fetchData();
    },
    watch: {
        /*
         由于vue中定义，当多个路由指向同一个组件时，即/index/1, /index/2指向同一个组件时，vue会复用组件，所以这就导致了组件的生命周期钩子
         不会被调用，所以上面的created方法就不会被调用，那么造成的影响就是数据不会重新加载，所以需要监测变化，即调用wache方法，该方法内部用于
         监测$route对象的变化
         */
        '$route': 'fetchData' //$route发生变化后，执行fetchdata方法
    },
    methods: {
        fetchData () {
            var _this = this;
            // 组件创建完后获取数据，
            _this.$http.post('http://localhost:8888/eyes').then(response => {
                // get body data
                var result = response.body;
                if(result.code == "Y"){
                    var data = result.data;
                    _this.blogs = data.blogs;
                    _this.times = data.times;
                    _this.totalArticles = data.totalArticles;
                    _this.totalTypes = data.totalTypes;
                    _this.types = data.types;
                }else{
                    alert("加载数据错误");
                    console.log(result.message);
                }
            }, response => {
                console.log("异常")
            }).then(() => {
                applications.startCloud();
                eyes.init();
            });
        }
    }
};
