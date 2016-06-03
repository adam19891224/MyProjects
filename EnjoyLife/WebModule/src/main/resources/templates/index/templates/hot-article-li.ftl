<#--热门文章模板-->
<textarea id="hotsArticle" class="jTempleta-textarea" title="hotsArticle" rows="0" cols="0">
    {#foreach $T as entity}
        <li>
            <a href="/blogs/{$T.entity.articleSid}.html">{$T.entity.articleTitle}</a>
        </li>
    {#/for}
</textarea>