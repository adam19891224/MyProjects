[#--左边--]
<div id="main-left" class="main-left" data-type="${dataType?default("")}">
    <div class="left-body">
    [#if result?? && result?size > 0]
        [#list result as info]
            <div class="cate-info-container">
                <div class="cate-container-left">
                    <img src="/base/images/image-loading.gif" class="b-lazy" data-src="${info.articleImg}" alt="${info.articleTitle}" />
                </div>
                <div class="cate-container-right">
                    <div class="cate-right-title">
                        <a href="/blogs/${info.articleSid}" class="link-head">
                            ${info.articleTitle?default("")}
                        </a>
                    </div>
                    <div class="cate-right-desc">
                        ${info.articleDescription}
                    </div>
                    <div class="cate-right-tag">
                        <span>
                            <i class="show"></i> <em class="show-text">个人博客</em>
                        </span>
                        <span>
                            <i class="time"></i> <em class="time-text">${info.createDate?default("")?string("yyyy-MM-dd")}</em>
                        </span>
                    </div>
                </div>
            </div>
        [/#list]
    [#else]
        <div class="cate-info-container">
            <h2 class="not-data">
                没有找到数据。。。
            </h2>
        </div>
    [/#if]
    </div>
[#--分页--]
    <div class="cate-left-page">
        <div id="page-div" class="page-div" data-pages="${totalPages}" data-current="${page}"></div>
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
<script src="/category/js/index.js"></script>