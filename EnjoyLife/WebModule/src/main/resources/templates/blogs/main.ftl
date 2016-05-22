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
                <article id="article" aid="${article.articleId}" class="article-main">
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
                <div class="article-comment">
                    <h5>
                        <i></i>发表意见
                    </h5>
                    <div class="comment-line"></div>
                    <div class="comment-loading">
                        <img src="../base/images/loading1.gif">
                    </div>
                    <ul class="comment-main">
                        <li comment="fcba3876-1a66-4981-be24-d211754c9ce8">
                            <h6>
                                <a>XXXXX</a>&nbsp;说：<span>2016-05-11</span>
                            </h6>
                            <p>
                                asdasdasd法法师法是法法师法玩法啊发发发。
                            </p>
                            <h6 class="comment-helper">
                                <a class="reply">回复</a>
                                <a class="open">展开</a>
                            </h6>
                            <div class="comment-reply-bar"></div>
                            <ul class="comment-reply-ul">
                                <li>
                                    <h6>
                                        <span>SSSS</span><span>回复</span><span>XXXX</span>
                                    </h6>
                                    <p>
                                        按时大大大哇的
                                    </p>
                                </li>
                                <li>
                                    <h6>
                                        <span>SSSS</span><span>回复</span><span>XXXX</span>
                                    </h6>
                                    <p>
                                        按时大大大哇的
                                    </p>
                                </li>
                            </ul>
                            <div id="comment-reply-div" class="comment-reply-div">
                                <div class="comment-reply-editor">
                                    <textarea name="comment-reply-editor" title="回复文章"></textarea>
                                </div>
                                <div class="comment-reply-submit">
                                    <a class="submit-comment-reply">
                                        提交
                                    </a>
                                </div>
                            </div>
                        </li>
                        <li comment="fcba3876-1a66-4981-be24-d211754c9ce8">
                            <h6>
                                <a>XXXXX</a>&nbsp;说：<span>2016-05-11</span>
                            </h6>
                            <p>
                                asdasdasd法法师法是法法师法玩法啊发发发。
                            </p>
                            <h6 class="comment-helper">
                                <a class="reply">回复</a>
                                <a class="open">展开</a>
                            </h6>
                            <div class="comment-reply-bar"></div>
                        </li>
                    </ul>
                    <div class="comment-paginatior">
                        <a href="javascript:" class="next" forward="n">
                            下一页
                        </a>
                        <a href="javascript:" class="pre" forward="p">
                            上一页
                        </a>
                    </div>
                    <div class="comment-editor">
                        <textarea name="comment-editor" title="回复文章"></textarea>
                    </div>
                    <div class="comment-submit">
                        <a id="submit-comment">
                            提交
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
    <div id="get-customer-info">
        <div class="get-customer-info-whole"></div>
        <div class="get-customer-info-div">
            <div class="title">
                <span>感想您的意见</span>
            </div>
            <h3>
                称呼：
            </h3>
            <input type="text" id="username" title="填写称呼" placeholder="请填写称呼"/>
            <h3>
                邮箱：
            </h3>
            <input type="text" id="useremail" title="填写邮箱" placeholder="请填写邮箱"/>
            <h3>
                网站：
            </h3>
            <input type="text" id="useremail" title="填写网站" placeholder="请填写您的网站"/>
            <a id="cancle-info">取消</a>
            <a id="submit-info">确定</a>
        </div>
    </div>
    [#--底部--]
    [#include "../layout/footer.ftl"]
    </body>
    [#--百度分享--]
    <script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"share":{},"image":{"viewList":["qzone","tsina","weixin","tieba","twi"],"viewText":"分享到：","viewSize":"16"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["qzone","tsina","weixin","tieba","twi"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
</html>