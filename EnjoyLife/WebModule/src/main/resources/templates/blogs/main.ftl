<!DOCTYPE html>
<html>
    <head>
        <title>${article.articleTitle}---月下竹影---Web开发交流频道</title>
        <meta name="keywords" content="Web技术，Web开发，Web交流，月下竹影，竹影，箫竹影，周禹宏" />
        <meta name="description" content="${article.articleDescription}" />
        [#include "../layout/head-setting.ftl"]
        <link rel="stylesheet" href="../blogs/css/blog.css?t=[@time /]">
        <link rel="stylesheet" href="../blogs/css/prism.css?t=[@time /]">
        <script src="../blogs/js/blogs.js?t=[@time /]"></script>
        <script src="../blogs/js/prism.js"></script>
    </head>
    <body>
        [#--顶部--]
        [#include "../layout/header.ftl"]

        [#--中间--]
        <main id="main" class="main">
            <div class="main-div">
                <div class="div-left">

                    <article id="article" aid="${article.articleId}" class="article-main" allow="Y">
                        <h1>
                            ${article.articleTitle}
                        </h1>
                        <div class="article-show">
                            <div class="show-left">
                                <div class="bdsharebuttonbox"><a href="#" class="bds_more" data-cmd="more"></a><a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a><a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a><a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a><a href="#" class="bds_tieba" data-cmd="tieba" title="分享到百度贴吧"></a><a href="#" class="bds_twi" data-cmd="twi" title="分享到Twitter"></a></div>
                            </div>
                            <div class="show-right">
                                <img src="../blogs/images/time.png">
                                <span>${article.createDate?string("yyyy-MM-dd")}</span>
                            </div>
                        </div>
                        <div class="article-body">
                            ${article.articleBody}
                        </div>
                    </article>

                    [#--评论展示部分--]
                    [#include "comment-body.ftl"]

                </div>
                <div class="div-right">
                    <div class="hot-div">
                        <h3>
                            <i class="tips"></i>
                            热门搜索
                        </h3>
                        <div class="line"></div>
                        <ul>
                            <li>
                                <a href="#">热点11111</a>
                            </li>
                            <li>
                                <a href="#">热点11111</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </main>

        [#--提交评论确认信息框--]
        [#include "customer-info.ftl"]

        [#--提交完成展示div--]
        [#include "comment-subimt-success.ftl"]

        [#--底部--]
        [#include "../layout/footer.ftl"]

    </body>
    [#--百度分享--]
    <script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"share":{},"image":{"viewList":["qzone","tsina","weixin","tieba","twi"],"viewText":"分享到：","viewSize":"16"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["qzone","tsina","weixin","tieba","twi"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
</html>