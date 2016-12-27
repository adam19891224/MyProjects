<main id="main" class="main">
[#--左边--]
    <div class="main-left">
    [#--列表--]
        <div id="left-body" class="left-body">
        [#list result as blog]
            <div class="main-blog">
                <div class="blog-head">
                    <h2>
                        <a href="/blogs/${blog.articleSid}" class="link-a">${blog.articleTitle}</a>
                    </h2>
                </div>
                <div class="blog-intro">
                    <span>评论数：(${blog.comments})</span>
                    <span>by：${blog.createDate?string("yyyy-MM-dd")}</span>
                </div>
                <div class="blog-line"></div>
                <div class="blog-type">
                    [#list blog.types as type]
                        <div class="type-body">${type}</div>
                    [/#list]
                </div>
                <div class="blog-body">
                    <a href="/blogs/${blog.articleSid}" class="link-a">
                        <img src="/base/images/image-loading.gif" class="b-lazy" data-src="${blog.articleImg}" alt="${blog.articleTitle}"/>
                    </a>
                    <span>
                    ${blog.articleDescription}
                            </span>
                </div>
            </div>
        [/#list]
        </div>
    [#--分页--]
        <div class="left-page">
            <div id="page-div" class="page-div" data-pages="${totalPages}" data-current="${page}"></div>
        </div>
    </div>
[#--右边--]
    <div class="main-right">
        <div class="right-body">
        [#--引入介绍--]
            [#include "../base/common/intro.ftl"/]
        </div>
    </div>
</main>
<script src="/index/js/index.js?t=[@time /]"></script>