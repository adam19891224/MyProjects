<!DOCTYPE html>
<html>
<head>
    <title>月下竹影---Web开发交流频道</title>
    <meta name="keywords" content="Web技术，Web开发，Web交流，月下竹影，竹影，箫竹影，周禹宏" />
    <meta name="description" content="月下竹影，是一个作为Web技术交流平台的个人原创技术博客。期待大家的光临。" />
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link href="/base/images/ico.ico" rel="shortcut icon" type="image/x-icon">
    <link href="/base/css/common.css" rel="stylesheet" type="text/css" media="all">
    <link href="/base/css/intro.css" rel="stylesheet" type="text/css" media="all">
    <link href="/base/css/cloud.css" rel="stylesheet" type="text/css" media="all">
    <link href="/blogs/css/page.css?t=[@time /]" rel="stylesheet" type="text/css" media="all">
    <link href="/blogs/css/prism.css?t=[@time /]" rel="stylesheet" type="text/css" media="all">
    <link href="/blogs/css/index.css?t=[@time /]" rel="stylesheet" type="text/css" media="all">
    <script src="/base/js/jquery-2.2.3.min.js"></script>
    <script src="/ckeditor/ckeditor.js"></script>
    <script src="/base/js/tar-cloud.min.js"></script>
    <script src="/base/js/app.js"></script>
    <script src="/base/js/page.js"></script>
    <script src="/blogs/js/prism.js"></script>
</head>
<body>
[#--顶部--]
[#include "../base/layout/header.ftl"]

[#--中间--]
<main id="main" class="main">
    <div class="main-left">
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
</main>

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


[#--底部--]
[#include "../base/layout/footer.ftl"]
</body>
<script src="/blogs/js/index.js?t=[@time /]"></script>

</html>