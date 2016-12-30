[#--左边--]
<div id="main-left" class="main-left" data-type="${dataType?default("")}">
    <div class="eyes-time">
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
    <div class="eyes-title">
        <div id="container-title" class="eyes-container-title">
        [#list result as blog]
            <h2 data-year="${blog.createDate?string("yyyy")}" data-month="${blog.createDate?string("MM")?eval}">
                <span class="eyes-title-time">
                    ${blog.createDate?string("MM-dd")}
                </span>
                <span class="eyes-title-content">
                    <a href="/blogs/${blog.articleSid}" class="link-head">
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
<script src="/eyes/js/index.js"></script>