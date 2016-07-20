<!DOCTYPE html>
<html>
    <head>
        <title>月下竹影---Web开发交流频道</title>
        <meta name="keywords" content="Web技术，Web开发，Web交流，月下竹影，竹影，箫竹影，周禹宏" />
        <meta name="description" content="月下竹影，是一个作为Web技术交流平台的个人原创技术博客。期待大家的光临。" />
        [#include "../layout/head-setting.ftl"]
        <link rel="stylesheet" href="../index/css/index.css?t=[@time /]">
        <script src="../index/js/index.js?t=[@time /]"></script>
    </head>
    <body>
        [#--顶部--]
        [#include "../layout/header.ftl"]

        [#--中间--]
        <main id="main" class="main">
            <div class="main-top">
                <ul>
                    <li>
                        <span>用最多的梦想面对未来！</span>
                    </li>
                    <li>
                        <span>
                            当你能飞的时候不要放弃飞！
                        </span>
                        <span>
                            当你能梦的时候不要放弃梦！
                        </span>
                        <span>
                            当你能爱的时候不要放弃爱！
                        </span>
                    </li>
                    <li>
                        <span>你不能左右天气，但你能转变你的心情！</span>
                    </li>
                </ul>
            </div>
            <div class="main-div">
                <div class="div-left">
                    <div class="left-main">
                        <h3>
                            <i class="tips"></i>
                            技术交流
                        </h3>
                        <div class="line"></div>
                        <ul id="blog-main"></ul>
                        <div class="loading-div">
                            <div id="loading-div" class="loading-bar hidden">
                                <span>加载中。。。。。。</span>
                                <img src="../base/images/loading1.gif">
                            </div>
                        </div>
                        <div id="pagination-div" class="pagination-div">
                            <a href="javascript:" forward="p" class="none">
                                上一页
                            </a>
                            <a href="javascript:" forward="n">
                                下一页
                            </a>
                        </div>
                    </div>
                </div>
                <div class="div-right">
                    <div class="hot-div">
                        <h3>
                            <i class="tips"></i>
                            热门搜索
                        </h3>
                        <div class="line"></div>
                        <ul id="hots-article-ul">
                            <li>
                                <img src="../base/images/hot-loading.gif">
                            </li>
                        </ul>
                    </div>
                    <div class="music-div">
                        <iframe frameborder="no" border="0" marginwidth="0" marginheight="0" width=100% height=60 src="http://music.163.com/outchain/player?type=2&id=30953009&auto=0&height=32"></iframe>
                    </div>
                </div>
            </div>
        </main>

        [#--热门文章模板--]
        [#include "templates/hot-article-li.ftl"]

        [#--底部--]
        [#include "../layout/footer.ftl"]

    </body>
</html>