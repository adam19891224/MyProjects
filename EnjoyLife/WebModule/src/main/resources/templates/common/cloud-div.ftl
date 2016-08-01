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