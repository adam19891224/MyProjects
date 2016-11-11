<div id="type-body" class="cloud-div">
    [#if types?? && types?size > 0]
        [#list types as type]
            <a href="/search/category/${type.typeName}/1.html">${type.typeName}</a>
        [/#list]
    [#else]
        <h2>敬请期待</h2>
    [/#if]
</div>