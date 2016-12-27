<header id="header" class="header-main">
    <div class="header-inner">
        <div class="header-left">
            <a href="/index">
                <img src="/base/images/logo.png">
            </a>
            <h2>月下竹影</h2>
        </div>
        <div class="header-mid">
            <input type="text" title="搜索" value="${keyword?default("")}"/>
            <i id="search-button"></i>
        </div>
        <div class="header-nav">
            <ul>
                <li [#if isIndex?? && isIndex == "Y"]class="header-underline"[/#if]>
                    <a href="/index" class="link-head">
                        首页
                    </a>
                </li>
                <li [#if isComplain?? && isComplain == "Y"]class="header-underline"[/#if]>
                    <a href="#" class="link-head">
                        吐槽
                    </a>
                </li>
                <li [#if isSeries?? && isSeries == "Y"]class="header-underline"[/#if]>
                    <a href="/series" class="link-head">
                        专题
                    </a>
                </li>
                <li [#if isEyes?? && isEyes == "Y"]class="header-underline"[/#if]>
                    <a href="/eyes" class="link-head">
                        一览
                    </a>
                </li>
                <li [#if isProfile?? && isProfile == "Y"]class="header-underline"[/#if]>
                    <a href="/profile" class="link-head">
                        关于
                    </a>
                </li>
            </ul>
        </div>
        <div class="header-right">

        </div>
    </div>
</header>