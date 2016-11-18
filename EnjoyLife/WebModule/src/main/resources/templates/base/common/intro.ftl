<div class="head-image">
    <img src="/base/images/default-head.jpg" />
</div>
<div class="msg-intro">
    <div class="intro-blogs">
        <span>文章</span>
        <span>
            [#if totalCounts??]
                ${totalCounts}
            [#else]
                0
            [/#if]
        </span>
    </div>
    <div class="intro-types">
        <span>类别</span>
        <span>
            [#if allTypes??]
                ${allTypes}
            [#else]
                0
            [/#if]
        </span>
    </div>
</div>