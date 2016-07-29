<!DOCTYPE html>
<html>
<head>
    <title>月下竹影---Web开发交流频道</title>
    <meta name="keywords" content="Web技术，Web开发，Web交流，月下竹影，竹影，箫竹影，周禹宏"/>
    <meta name="description" content="月下竹影，是一个作为Web技术交流平台的个人原创技术博客。期待大家的光临。"/>
    [#include "../layout/head-setting.ftl"]
    <link rel="stylesheet" href="../eyes/css/eyes.css?t=[@time /]">
    <script src="../eyes/js/eyes.js?t=[@time /]"></script>
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
                文章一览
            </h3>
            <div class="line"></div>
            <ul class="series-main">

            </ul>
        </div>
        <div class="div-right">
            <div class="hot-div">
                <h3>
                    <i class="tips"></i>
                    热门搜索
                </h3>
                <div class="line"></div>
                <ul id="hots-article-ul">
                    <li>
                        <img src="../base/images/hot-loading.gif">
                    </li>
                </ul>
            </div>
            <div class="type-div">
                <h3>
                    <i class="tips"></i>
                    分类查看
                </h3>
                <div class="line"></div>
                <div id="type-body" class="type-body">
                    [#if types?? && types?size > 0]
                        [#list types as type]
                            <a href="${type.typeId}" target="_blank">${type.typeName}</a>
                        [/#list]
                    [#else]
                        <h2>敬请期待</h2>
                    [/#if]
                </div>
            </div>
        </div>
    </div>
</main>

[#--热门文章模板--]
[#include "../index/templates/hot-article-li.ftl"]

[#--底部--]
[#include "../layout/footer.ftl"]

</body>
</html>