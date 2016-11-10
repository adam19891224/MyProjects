<#--首页文章-->
<textarea id="indexArticle" class="jTempleta-textarea" title="indexArticle" rows="0" cols="0">
    {#foreach $T as entity}
        <li>
            <div class="blog-tips">
                <span>{$T.entity.createDateStr}</span><br>
            </div>
            <h2 title="图片">
                <a href="/blogs/{$T.entity.articleSid}.html">{$T.entity.articleTitle}</a>
            </h2>
            <a href="/blogs/{$T.entity.articleSid}.html" class="li-a">
                <img src="/base/images/image-loading.gif" class="lazy" data-original="{$T.entity.articleImg}" alt="{$T.entity.articleTitle}">
            </a>
            <span>{$T.entity.articleDescription}</span>
        </li>
    {#/for}
</textarea>