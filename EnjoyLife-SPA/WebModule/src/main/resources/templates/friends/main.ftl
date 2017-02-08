[#--左边--]
<div id="main-left" class="main-left" data-type="${dataType?default("")}">
    <div id="friedns-screen" class="friedns-screen">
        <h2>友情链接</h2>
        <ul>
            [#if friends?? && friends?size > 0]
                [#list friends as friend]
                    <li>
                        <span>
                            <a href="${friend.friendValue}" target="_blank">${friend.friendName}</a>
                        </span>
                        <span>(${friend.friendTips})</span>
                    </li>
                [/#list]
            [#else]
                <li>
                    <h5>目前还没有哦。请联系博主吧</h5>
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