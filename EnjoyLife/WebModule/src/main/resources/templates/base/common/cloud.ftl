<div id="type-body" class="cloud-div">
    [#if types?? && types?size > 0]
        [#list types as type]
            <a href="/genre/${type.typeName}/1" class="link-head">${type.typeName}</a>
        [/#list]
    [#else]
        <h2>敬请期待</h2>
    [/#if]
</div>