<!DOCTYPE html>
<html>
<head>
    <title>月下竹影---Web开发交流频道</title>
    <meta name="keywords" content="Web技术，Web开发，Web交流，月下竹影，竹影，箫竹影，周禹宏" />
    <meta name="description" content="月下竹影，是一个作为Web技术交流平台的个人原创技术博客。期待大家的光临。" />
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="shortcut icon" href="/base/images/ico.ico" type="image/x-icon">
    <link rel="stylesheet" href="/base/css/common.css" type="text/css" media="all">
    <link rel="stylesheet" href="/base/css/intro.css" type="text/css" media="all">
    <link rel="stylesheet" href="/base/css/cloud.css" type="text/css" media="all">
    <link rel="stylesheet" href="/eyes/css/index.css?t=[@time /]" type="text/css" media="all">
    <script src="/base/js/jquery-2.2.3.min.js"></script>
    <script src="/base/js/tar-cloud.min.js"></script>
</head>
<body>
[#--顶部--]
[#include "../base/layout/header.ftl"]

[#--中间--]
<main id="main" class="main">
    [#--左边--]
    <div class="main-left">
        <div class="left-time">
            <div id="time-div" class="time-div" n-y="${times?first.date?c}" n-m="${times?first.list?first.date?c}">
                [#list times as year]
                [#--第一个元素--]
                    [#if year_index == 0]
                        <div class="time-year">
                                <span class="${year.date?c}">
                                    <em class="now-year">${year.date?c}</em>
                                    <i class="yead-front-up"></i>
                                </span>
                            <ul>
                                [#list year.list as month]
                                    [#if month_index == 0]
                                        <li class="now-month" t-m="${month.date?c}">
                                            ${month.date?c}月
                                        </li>
                                    [#else]
                                        <li t-m="${month.date?c}">
                                            ${month.date?c}月
                                        </li>
                                    [/#if]
                                [/#list]
                            </ul>
                        </div>
                    [#else]
                        <div class="time-year">
                                <span class="${year.date?c}">
                                    <em>${year.date?c}</em>
                                    <i></i>
                                </span>
                            <ul class="display-ul">
                                [#list year.list as month]
                                    <li t-m="${month.date?c}">
                                        ${month.date?c}月
                                    </li>
                                [/#list]
                            </ul>
                        </div>
                    [/#if]
                [/#list]
            </div>
        </div>
        <div class="left-title">
            <div id="container-title" class="container-title">
                [#list result as blog]
                    <h2 data-year="${blog.createDate?string("yyyy")}" data-month="${blog.createDate?string("MM")?eval}">
                        <span class="title-time">
                            ${blog.createDate?string("MM-dd")}
                        </span>
                        <span class="title-content">
                            <a href="/blogs/${blog.articleSid}.html">
                                ${blog.articleTitle}
                            </a>
                        </span>
                    </h2>
                [/#list]
            </div>
        </div>
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

[#--底部--]
[#include "../base/layout/footer.ftl"]
</body>
<script src="/eyes/js/index.js?t=[@time /]"></script>
</html>