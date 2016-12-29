<div id="main-left" class="main-left" data-type="${dataType?default("")}">
    <h1>
    ${article.articleTitle}
    </h1>
    <div class="article-intro">
        <span class="intro-type">
        [#if type??]
            <i></i># <span>${type.typeName?default("个人博文")}</span>
        [/#if]
        </span>
        <span class="intro-time">
            <i></i>${article.createDate?string("yyyy-MM-dd")}
        </span>
    </div>
    <div class="article-tags">
    [#if tags?? && tags?size > 0]
        [#list tags as tag]
            <span tid="${tag.tagId}">${tag.tagName}</span>
        [/#list]
    [/#if]
    </div>
    <article id="article-body" class="article-body" data-aid="${article.articleId}">
        ${article.articleBody}
    </article>
    <div class="article-line"></div>
    <div class="article-block"></div>
    [#--引入评论--]
    [#include "comment.ftl"/]
</div>
[#--右边--]
<div class="main-right">
    <div class="right-body">
    [#--引入介绍--]
        [#include "../base/common/intro.ftl"/]
    </div>
    <div class="right-body">
    [#--引入标签云--]
        [#include "../base/common/cloud.ftl"/]
    </div>
</div>

<div id="whole-div" class="whole-div">
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
<div id="loading-div" class="whole-div">
    <div class="submit-screen-div"></div>
    <div class="loading-div">
        <div class="loading-info"></div>
    </div>
</div>

<script src="/blogs/js/index.js"></script>
[#--分享--]
<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":["mshare","qzone","tsina","weixin","renren","kaixin001","tieba","sqq","youdao","ty","fbook","twi","linkedin","evernotecn"],"bdPic":"","bdStyle":"0","bdSize":"16"},"slide":{"type":"slide","bdImg":"2","bdPos":"right","bdTop":"139.5"}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
