Vue.filter('moment', function (value, formatString) {
    formatString = formatString || 'YYYY-MM-DD';
    return moment(value).format(formatString);
});


// 定义模板
const index_t = {
    template:
        `<main id="main" class="main">
            <div id="main-left" class="main-left">
            <div id="left-body" class="left-body">
                <div class="main-blog" v-for="blog in list">
                    <div class="blog-head">
                       <router-link tag="h2" :to="{ name: 'blog', params: { aid: blog.articleSid } }">
                            <a class="link-head">{{blog.articleTitle}}</a>
                        </router-link>
                    </div>
                    <div class="blog-intro">
                        <span>评论数：({{blog.comments}})</span>
                        <span>by：{{blog.createDate | moment}}</span>
                    </div>
                    <div class="blog-line"></div>
                    <div class="blog-type"></div>
                    <div class="blog-body">
                        <router-link to="/blogs/blog.articleSid">
                            <img src="../images/image-loading.gif" class="b-lazy" :data-src="blog.articleImg" :alt="blog.articleTitle"/>
                        </router-link>
                        <span>
                            {{blog.articleDescription}}
                        </span>
                    </div>
                </div>
            </div>
            <div class="blog-page">
                <div id="page-div" class="page-div" data-pages="" data-current=""></div>
            </div>
        </div>
        <div class="main-right">
            <div class="right-body"></div>
        </div>
    </main>`,
    data () {
        return {
            list: ""
        }
    },
    created () {
        var _this = this;
        // 组件创建完后获取数据，
        // 此时 data 已经被 observed 了
        _this.$http.post('http://localhost:8888/index').then(response => {
            // get body data
            var result = response.body;
            var data = result.data;
            _this.list = data.result;
        }, response => {
            // error callback
        });
    },
    ready: function() {
        var _this = this;
        //在编译后即调用API接口取得服务器端数据
        _this.$http.post('http://localhost:8888/index').then(response => {
            // get body data
            var result = response.body;
            var data = result.data;
            _this.list = data.result;
        }, response => {
            // error callback
        });
    }
};
