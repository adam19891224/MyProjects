<!DOCTYPE html>
<html>
<head>
    <title>月下竹影---Web开发交流频道</title>
    <meta name="keywords" content="Web技术，Web开发，Web交流，月下竹影，竹影，箫竹影，周禹宏"/>
    <meta name="description" content="月下竹影，是一个作为Web技术交流平台的个人原创技术博客。期待大家的光临。"/>
    [#include "../layout/head-setting.ftl"]
    <link rel="stylesheet" href="/base/css/page.css?t=[@time /]">
    <link rel="stylesheet" href="/search/css/search.css?t=[@time /]">
    <script src="/search/js/search.js?t=[@time /]"></script>
</head>
<body>
[#--顶部--]
[#include "../layout/header.ftl"]

[#--中间--]
<main id="main" class="main">
    <div class="main-div">
        <div class="div-left">
            <h3>
                <i class="tips"></i>
                ${key}
            </h3>
            <div class="line"></div>
            <ul id="search-main" class="search-main">
                [#if all.resultList?? && all.resultList?size > 0]
                    [#list all.resultList as entity]
                        <li data-time-Y="${entity.createDate?string("yyyy")}" data-time-M="${entity.createDate?string("MM")}" data-time-YM="${entity.createDate?string("yyyy-MM")}">
                            <div class="search-top">
                                ${entity.createDate?string("yyyy-MM-dd")}
                            </div>
                            <h2 class="search-title">
                                <a href="/blogs/${entity.articleSid}.html">
                                    [#if entity.highLightTitle??]
                                        ${entity.highLightTitle}
                                    [#else]
                                        ${entity.articleTitle}
                                    [/#if]
                                </a>
                            </h2>
                            <a href="/blogs/${entity.articleSid}.html" class="li-a">
                                <img src="/base/images/image-loading.gif" class="lazy" data-original="${entity.articleImg}" alt="${entity.articleTitle}">
                            </a>
                            <span>
                                ${entity.articleDescription}
                            </span>
                        </li>
                    [/#list]
                [#else]
                    <li style="min-height: 70px; height: 70px;">
                        <h3 style="text-align: center;">敬请期待</h3>
                    </li>
                [/#if]
            </ul>
            <div id="kkpager" class="div-page" total="${all.totalPages}" current="${all.page}" key="${key}"></div>
        </div>
        <div class="div-right">
            [#--热门搜索--]
            [#include "../common/hot-div.ftl" ]

            [#--标签--]
            [#include "../common/cloud-div.ftl" ]
        </div>
    </div>
</main>

[#--热门文章模板--]
[#include "../index/templates/hot-article-li.ftl"]

[#--底部--]
[#include "../layout/footer.ftl"]

</body>
</html>