[#include "../layout/html.ftl" /]
[@html title="${article.articleTitle}" keywords="${article.articleTitle}" description="${article.articleDescription}" mainJs="/article/js/main.js"]
<link rel="stylesheet" href="/article/css/article.css?t=[@time /]" type="text/css"/>
<link rel="stylesheet" href="/article/highlight/css/monokai_sublime.css" type="text/css"/>
<!--[if lte IE 8]>
    <script src="/common/js/ieBetter.js"></script>
<![endif]-->
<div class="context">
    <div class="context-left">
        <h1>${article.articleTitle}</h1>
        <h4>
            ${article.createDate?string("yyyy-MM-dd")}
        </h4>
        <article>
            <div id="article-main" data-num="${article.articleId}">
            ${article.articleBody}
            </div>
        </article>
        [#--引入评论模板--]
        [#include "comment.ftl" /]
    </div>
    <div class="context-right">
    </div>
</div>
[/@html]