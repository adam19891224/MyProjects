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
    formatString = formatString || 'M';
    return moment(value).format(formatString);
});

const path = "http://localhost:36666";

const InfoView = {
    template:
    `<div class="right-body">
        <div id="left-loading" class="main-left-loading" v-if="!isLoading">
            <img src="/images/page-loading.gif">
        </div>
        <div class="head-image" v-if="isLoading">
            <img src="/images/default-head.jpg" />
        </div>
        <div class="msg-intro" v-if="isLoading">
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
    </div>`,
    //给模板绑定数据
    data () {
        return {
            isLoading: false,
            totalArticles: null,
            totalTypes: null
        }
    },
    //模板创建完毕后，获取数据
    created () {
        this.fetchData();
    },
    methods: {
        fetchData () {
            let _this = this;
            // 组件创建完后获取数据，
            _this.$http.get('/infomations/introduce').then(response => {
                // get body data
                let result = response.body;
                if(result.code == "Y"){
                    let data = result.data;
                    _this.totalArticles = data.totalArticles;
                    _this.totalTypes = data.totalTypes;
                }else{
                    alert("加载数据错误");
                    console.log(result.message);
                }
                _this.isLoading = true;
            }, response => {
                console.log("异常");
            });
        }
    }
};

const TarCloudView = {
    template:
    `<div class="right-body">
        <div id="type-body" class="cloud-div">
            <router-link v-if="types.length > 0" v-for="type in types" :to="{ name: 'genre', params: { typeName: type.typeName, p: 1 } }" :tn="type.typeName">{{type.typeName}}</router-link>
        </div>
    </div>`,
    //给模板绑定数据
    data () {
        return {
            types: null
        }
    },
    //模板创建完毕后，获取数据
    created () {
        this.fetchData();
    },
    methods: {
        fetchData () {
            let _this = this;
            // 组件创建完后获取数据，
            _this.$http.get('/infomations/tagcloud').then(response => {
                // get body data
                let result = response.body;
                if(result.code == "Y"){
                    let data = result.data;
                    _this.types = data.types;
                }else{
                    alert("加载数据错误");
                    console.log(result.message);
                }
            }, response => {
                console.log("异常")
            });
        }
    },
    updated(){
        applications.startCloud();
    }
};


// 定义首页组件
const ArticlesView = {
    template:
    `<section>
        <div id="left-loading" class="main-left-loading" v-if="!isLoading">
            <img src="/images/page-loading.gif">
        </div>
        <div id="left-body" class="left-body" v-if="isLoading">
            <div class="main-blog" v-for="blog in blogs">
                <div class="blog-head">
                   <router-link tag="h2" :to="{ name: 'article', params: { aid: blog.articleSid } }">
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
                    <router-link :to="{ name: 'article', params: { aid: blog.articleSid } }">
                        <img src="../images/image-loading.gif" class="b-lazy" :data-src="blog.articleImg" :alt="blog.articleTitle"/>
                    </router-link>
                    <span>
                        {{blog.articleDescription}}
                    </span>
                </div>
            </div>
        </div>
        <div class="blog-page" v-if="isLoading">
            <div id="page-div" class="page-div" :data-pages="totalPages" :data-current="page"></div>
        </div>
    </section>`,
    //给模板绑定数据
    data () {
        return {
            isLoading: false,
            blogs: null,
            totalPages: null,
            page: null
        }
    },
    //模板创建完毕后，获取数据
    created () {
        this.fetchData();
    },
    watch: {
        '$route': 'fetchData'
    },
    methods: {
        fetchData () {
            let _this = this;
            _this.isLoading = false;
            let page = _this.$route.params.page;
            if(!page){
                page = 1;
            }
            // 组件创建完后获取数据，
            _this.$http.get('/articles/' + page).then(response => {
                // get body data
                let result = response.body;
                if(result.code == "Y"){
                    let data = result.data;
                    _this.blogs = data.blogs;
                    _this.totalPages = data.totalPages;
                    _this.page = data.page;
                }else{
                    alert("加载数据错误");
                    console.log(result.message);
                }
                _this.isLoading = true;
            }, response => {
                console.log("异常");
            });
        }
    },
    updated(){
        articles.init();
    }
};

// 定义专题组件
const SeriesView = {
    template:
    `<section>
        <div id="left-loading" class="main-left-loading" v-if="!isLoading">
            <img src="/images/page-loading.gif">
        </div>
        <div class="left-body" v-if="isLoading">
            <ul class="series-main">
                <li v-if="series.length > 0" v-for="serie in series">
                    <router-link to="/index" v-if="serie.seriesName != null">
                        <h2>{{serie.seriesName}}</h2>
                    </router-link>
                    <span v-if="serie.counts != null">{{serie.counts}} 篇</span>
                    <h5 v-if="serie.seriesName == null">敬请期待</h5>
                </li>
            </ul>
        </div>
    </section>`,
    //给模板绑定数据
    data () {
        return {
            isLoading: false,
            series: null
        }
    },
    //模板创建完毕后，获取数据
    created () {
        this.fetchData();
    },
    methods: {
        fetchData () {
            let _this = this;
            // 组件创建完后获取数据，
            _this.$http.get('/series').then(response => {
                // get body data
                let result = response.body;
                if(result.code == "Y"){
                    let data = result.data;
                    if(data.series.length > 0){
                        _this.series = data.series;
                    }else{
                        let series = [];
                        series.push({});
                        _this.series = series;
                    }
                }else{
                    alert("加载数据错误");
                    console.log(result.message);
                }
                _this.isLoading = true;
            }, response => {
                console.log("异常")
            });
        }
    }
};

//定义一览组件
const EyesView = {
    template:
    `<section>
        <div id="left-loading" class="main-left-loading" v-if="!isLoading">
            <img src="/images/page-loading.gif">
        </div>
        <div class="eyes-time" v-if="isLoading">
            <div id="time-div" class="time-div" :n-y="nowY" :n-m="nowM">
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
        <div class="eyes-title" v-if="isLoading">
            <div id="container-title" class="eyes-container-title">
                <h2 v-for="blog in blogs" :data-year="blog.createDate | y" :data-month="blog.createDate | m">
                    <span class="eyes-title-time">
                        {{blog.createDate | md}}
                    </span>
                    <span class="eyes-title-content">
                        <router-link :to="{ name: 'article', params: { aid: blog.articleSid } }" class="link-head">
                            {{blog.articleTitle}}
                        </router-link>
                    </span>
                </h2>
            </div>
        </div>
    </section>`,
    //给模板绑定数据
    data () {
        return {
            isLoading: false,
            blogs: null,
            times: null,
            nowY: null,
            nowM: null
        }
    },
    //模板创建完毕后，获取数据
    created () {
        this.fetchData();
    },
    methods: {
        fetchData () {
            let _this = this;
            // 组件创建完后获取数据，
            _this.$http.get('/eyes').then(response => {
                // get body data
                let result = response.body;
                if(result.code == "Y"){
                    let data = result.data;
                    _this.blogs = data.blogs;
                    _this.times = data.times;
                    _this.nowY = data.nowY;
                    _this.nowM = data.nowM;
                }else{
                    alert("加载数据错误");
                    console.log(result.message);
                }
                _this.isLoading = true;
            }, response => {
                console.log("异常")
            });
        }
    },
    updated(){
        eyes.init();
    }
};

//定义关于组件
const ProfileView = {
    template:
    `<section class="mian-profile">
        <div id="left-loading" class="main-left-loading" v-if="!isLoading">
            <img src="/images/page-loading.gif">
        </div>
        <h2 v-if="isLoading">个人简介</h2>
        <ul v-if="isLoading">
            <li>
                姓名：<span>周禹宏（Adam）</span>
            </li>
            <li>
                生日：<span>1989-12-24</span>
            </li>
            <li>
                性别：<span>男</span>
            </li>
            <li>
                籍贯：<span>重庆</span>
            </li>
            <li>
                爱好：<span>足球，阅读</span>
            </li>
            <li>
                QQ：<span>273961736</span>
            </li>
        </ul>
    </section>`,
    data () {
        return {
            isLoading: true
        }
    },
};

//定义友链组件
const FriendsView = {
    template:
    `<section>
        <div id="left-loading" class="main-left-loading" v-if="!isLoading">
            <img src="/images/page-loading.gif">
        </div>
        <div id="friedns-screen" class="friedns-screen" v-if="isLoading">
            <h2>友情链接</h2>
            <ul>
                <li v-if="friends.length > 0" v-for="friend in friends">
                    <span>
                        <a v-if="friend.friendValue != null && friend.friendName != null" :href="friend.friendValue" target="_blank">{{friend.friendName}}</a>
                    </span>
                    <span v-if="friend.friendTips != null">({{friend.friendTips}})</span>
                    <h5 v-if="friend.friendValue == null">目前还没有哦。请联系博主吧</h5>
                </li>
            </ul>
        </div>
    </section>`,
    //给模板绑定数据
    data () {
        return {
            isLoading: false,
            friends: null
        }
    },
    //模板创建完毕后，获取数据
    created () {
        this.fetchData();
    },
    methods: {
        fetchData () {
            let _this = this;
            // 组件创建完后获取数据，
            _this.$http.get('/friends').then(response => {
                // get body data
                let result = response.body;
                if(result.code == "Y"){
                    let data = result.data;
                    if(data.friends.length > 0){
                        _this.friends = data.friends;
                    }else{
                        let friends = [];
                        friends.push({});
                        _this.friends = friends;
                    }
                }else{
                    alert("加载数据错误");
                    console.log(result.message);
                }
                _this.isLoading = true;
            }, response => {
                console.log("异常")
            });
        }
    }
};

//定义类别组件
const CategoryView = {
    template:
    `<section>
        <div id="left-loading" class="main-left-loading" v-if="!isLoading">
            <img src="/images/page-loading.gif">
        </div>
        <div class="left-body" v-if="isLoading">
            <div class="cate-info-container" v-for="info in blogs" v-if="blogs.length > 0">
                <div class="cate-container-left" v-if="info.articleImg != null">
                    <img src="/images/image-loading.gif" class="b-lazy" :data-src="info.articleImg" :alt="info.articleTitle" />
                </div>
                <div class="cate-container-right" v-if="info.articleSid != null && info.articleTitle != null && info.articleDescription != null">
                    <div class="cate-right-title">
                        <router-link :to="{ name: 'article', params: { aid: info.articleSid } }">
                            {{info.articleTitle}}
                        </router-link>
                    </div>
                    <div class="cate-right-desc">
                        {{info.articleDescription}}
                    </div>
                    <div class="cate-right-tag">
                        <span>
                            <i class="show"></i> <em class="show-text">个人博客</em>
                        </span>
                        <span>
                            <i class="time"></i> <em class="time-text">{{info.createDate | ymd}}</em>
                        </span>
                    </div>
                </div>
                <h2 class="not-data" v-if="info.articleSid == null">
                    没有找到数据。。。
                </h2>
            </div>
        </div>
        <div class="cate-left-page" v-if="blogs != null && isLoading">
            <div id="cate-page-div" class="page-div" :data-pages="totalPages" :data-current="page"></div>
        </div>
    </section>`,
    //给模板绑定数据
    data () {
        return {
            isLoading: false,
            blogs: null,
            totalPages: null,
            page: null
        }
    },
    //模板创建完毕后，获取数据
    created () {
        this.fetchData();
    },
    watch: {
        '$route': 'fetchData'
    },
    methods: {
        fetchData () {
            let _this = this;
            _this.isLoading = false;
            let page = _this.$route.params.p;
            let name = _this.$route.params.typeName;
            if(!page){
                page = 1;
            }
            // 组件创建完后获取数据，
            _this.$http.get('/genre/' + name + "/" + page).then(response => {
                // get body data
                let result = response.body;
                if(result.code == "Y"){
                    let data = result.data;
                    let blogs = data.blogs;
                    if(blogs != null && blogs.length > 0){
                        _this.blogs = data.blogs;
                    }else{
                        let blogs = [];
                        blogs.push({});
                        this.blogs = blogs;
                    }
                    _this.totalPages = data.totalPages;
                    _this.page = data.page;
                }else{
                    alert("加载数据错误");
                    console.log(result.message);
                }
                _this.isLoading = true;
            }, response => {
                console.log("异常")
            });
        }
    },
    updated(){
        cate.init();
    }
};

//定义搜索组件
const QueryView = {
    template:
    `<section>
        <div id="left-loading" class="main-left-loading" v-if="!isLoading">
            <img src="/images/page-loading.gif">
        </div>
        <div class="left-body" v-if="isLoading">
            <div class="sea-info-container" v-for="info in blogs">
                <h2 v-if="info.articleSid != null && info.articleTitle != null">
                    <router-link :to="{ name: 'article', params: { aid: info.articleSid } }" v-html="info.articleTitle"></router-link>
                </h2>
                <h2 class="not-data" v-if="info.articleSid == null">
                    没有找到数据。。。
                </h2>
                <section v-if="info.createDate != null && info.articleDescription != null">
                    <span class="info-date">{{info.createDate | ymd}}</span> - <span v-html="info.articleDescription"></span>
                </section>
            </div>
        </div>
        <div class="sea-left-page" v-if="blogs != null && isLoading">
            <div id="search-page-div" class="page-div" :data-pages="totalPages" :data-current="page"></div>
        </div>
    </section>`,
    //给模板绑定数据
    data () {
        return {
            isLoading: false,
            blogs: null,
            totalPages: null,
            page: null
        }
    },
    //模板创建完毕后，获取数据
    created () {
        this.fetchData();
    },
    watch: {
        '$route': 'fetchData'
    },
    methods: {
        fetchData () {
            let _this = this;
            _this.isLoading = false;
            let page = _this.$route.params.p;
            let name = _this.$route.params.searchName;
            if(!page){
                page = 1;
            }
            // 组件创建完后获取数据，
            _this.$http.get('/query/' + name + "/" + page).then(response => {
                // get body data
                let result = response.body;
                if(result.code == "Y"){
                    let data = result.data;
                    let blogs = data.blogs;
                    if(blogs != null && blogs.length > 0){
                        _this.blogs = data.blogs;
                    }else{
                        let blogs = [];
                        blogs.push({});
                        this.blogs = blogs;
                    }
                    _this.totalPages = data.totalPages;
                    _this.page = data.page;
                }else{
                    alert("加载数据错误");
                    console.log(result.message);
                }
                _this.isLoading = true;
            }, response => {
                console.log("异常")
            });
        }
    },
    updated(){
        search.init();
    }
};

//定义详情组件
const ArticleView = {
    template:
    `<section>
        <div id="left-loading" class="main-left-loading" v-if="!isLoading">
            <img src="/images/page-loading.gif">
        </div>
        <h1 v-if="article != null && isLoading">
            {{article.articleTitle}}
        </h1>
        <div class="article-intro" v-if="article != null && isLoading">
            <span class="intro-type">
                <i></i># <span v-if="type != null"><router-link :to="{ name: 'genre', params: { typeName: type.typeName, p: 1 } }">{{type.typeName}}</router-link></span><span v-if="type == null"><a href="javascript:">个人博客</a></span>
            </span>
            <span class="intro-time">
                <i></i>{{article.createDate | ymd}}
            </span>
        </div>
        <div class="article-tags" v-if="article != null && isLoading">
            <span v-for="tag in tags" :tid="tag.tagId">{{tag.tagName}}</span>
        </div>
        <article id="article-body" v-if="article != null && isLoading" class="article-body" :data-aid="article.articleId" :data-asid="article.articleSid" v-html="article.articleBody"></article>
        <div class="article-line" v-if="isLoading"></div>
        <div class="article-block" v-if="isLoading"></div>
        <div id="articlt-comment-div" class="article-comment" :ck="ck" v-if="isLoading">
            <div class="comment-title">
                <i></i><span>交流区</span>
            </div>
            <ul class="comment-container"></ul>
            <div id="comment-pages" class="comment-pages"></div>
            <div class="comment-editor">
                <textarea id="comment-main-editor" cols="0" rows="0" class="comment-main-editor" title="发表"></textarea>
            </div>
            <div class="comment-buttons">
                <span id="comment-error-msg" class="error-message"></span>
                <a id="comment-submit" class="submit-button">发表</a>
            </div>
        </div>
        
        <div id="whole-div" class="whole-div" v-if="isLoading">
            <div class="submit-screen-div"></div>
            <div class="submit-info-div">
                <ul class="info-main">
                    <li>
                        <input type="text" id="friend-name" title="名称" placeholder="请输入昵称(必填)" />
                    </li>
                    <li>
                        <input type="text" id="friend-email" title="邮箱" placeholder="请输入邮箱(必填)" />
                    </li>
                    <li>
                        <select id="friend-sitetype" title="类型">
                            <option value="http">http://</option>
                            <option value="https">https://</option>
                        </select>
                        <input type="text" id="friend-website" title="网站" placeholder="请输入网站" />
                    </li>
                </ul>
                <div class="info-buttons">
                    <span id="to-submit">提交</span>
                    <span id="to-cancle">取消</span>
                </div>
            </div>
        </div>
        <div id="loading-div" class="whole-div" v-if="isLoading">
            <div class="submit-screen-div"></div>
            <div class="loading-div">
                <div class="loading-info"></div>
            </div>
        </div>
    </section>`,
    //给模板绑定数据
    data () {
        return {
            isLoading: false,
            article: null,
            tags: null,
            type: null,
            ck: null
        }
    },
    //模板创建完毕后，获取数据
    created () {
        this.fetchData();
    },
    /*
     由于vue中定义，当多个路由指向同一个组件时，即/index/1, /index/2指向同一个组件时，vue会复用组件，所以这就导致了组件的生命周期钩子
     不会被调用，所以上面的created方法就不会被调用，那么造成的影响就是数据不会重新加载，所以需要监测变化，即调用wache方法，该方法内部用于
     监测$route对象的变化
     */
    watch: {
        '$route': 'fetchData'
    },
    methods: {
        fetchData () {
            let _this = this;
            let aid = _this.$route.params.aid;
            if(!aid){
                alert("博客编号为空");
                console.log("博客编号为空");
                router.push({name: "index", params: { page: 1 }});
                return;
            }
            // 组件创建完后获取数据，
            _this.$http.get('/article/' + aid).then(response => {
                // get body data
                let result = response.body;
                if(result.code == "Y"){
                    let data = result.data;
                    _this.ck = data.ck;
                    if(data.article != null){
                        _this.article = data.article;
                    }
                    if(data.tags.length > 0){
                        _this.tags = data.tags;
                    }
                    if(data.type != null){
                        _this.type = data.type;
                    }
                }else{
                    alert("加载数据错误");
                    console.log(result.message);
                }
                _this.isLoading = true;
            }, response => {
                console.log("异常")
            });
        }
    },
    updated(){
        article.init();
    }
};

// 定义专题组件
const ErrorView = {
    template:
    `<section>
        <div class="not-container">
            <h3>404</h3>
            <router-link to="/articles">回到首页</router-link>
        </div>
    </section>`
};