[#--左边--]
<div id="main-left" class="main-left" data-type="${dataType?default("")}">
[#--列表--]
    <div class="left-body">
        <ul class="series-main">
        [#if series?? && series?size > 0]
            [#list series as seriesE]
                <li>
                    <a href="#">
                        <h2>${seriesE.seriesName}</h2>
                    </a>
                    <span>${seriesE.counts} 篇</span>
                </li>
            [/#list]
        [#else]
            <li>
                <h5>敬请期待</h5>
            </li>
        [/#if]
        </ul>
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