[#--左边--]
<div id="main-left" class="main-left" data-type="${dataType?default("")}">
    <div class="left-body">
    [#if result?? && result?size > 0]
        [#list result as info]
            <div class="sea-info-container">
                <h2>
                    <a href="/blogs/${info.articleSid}" class="link-head">
                        ${info.articleTitle?default("")}
                    </a>
                </h2>
                <section>
                    <span class="info-date">${info.createDate?default("")?string("yyyy-MM-dd")}</span> - ${info.articleDescription?default("")}
                </section>
            </div>
        [/#list]
    [#else]
        <div class="sea-info-container">
            <h2 class="not-data">
                没有找到数据。。。
            </h2>
        </div>
    [/#if]
    </div>
[#--分页--]
    <div class="sea-left-page">
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
<script src="/search/js/index.js"></script>