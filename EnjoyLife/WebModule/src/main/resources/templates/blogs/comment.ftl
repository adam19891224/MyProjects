<div id="articlt-comment-div" class="article-comment">
    <div class="comment-title">
        <i></i><span>交流区</span>
    </div>
    <ul class="comment-container">
        [#if result?? && result?size > 0]
            [#list result as comment]
                <li data-id="${comment.commentId}">
                    <div class="comment-left">
                        <div class="left-head"></div>
                    </div>
                    <div class="comment-right">
                        <div class="right-main">
                            <div class="mian-name">
                                ${comment.commentUser?html}
                            </div>
                            <div class="main-content">
                                ${comment.commentBody}
                            </div>
                            <div class="main-reply">
                                <span class="to-reply" data-l="0">回复</span><span class="to-show" data-l="0">展开</span>
                            </div>
                            <div class="main-line"></div>
                            <div class="main-reply-container">
                                <ul class="reply-container" load="0">
                                    [#--<li>--]
                                        [#--<h5>--]
                                            [#--<span>XXX</span> 回复 <span>XXX</span>--]
                                        [#--</h5>--]
                                        [#--<div class="reply-content">--]
                                            [#--afasf撒旦法师法算法我挖的爱上艾弗森awful阿文爱上法萨芬afasf撒旦法师法算法我挖的爱上艾弗森awful阿文爱上法萨芬afasf撒旦法师法算法我挖的爱上艾弗森awful阿文爱上法萨芬-->--]
                                        [#--</div>--]
                                    [#--</li>--]
                                </ul>
                            </div>
                        </div>
                    </div>
                </li>
            [/#list]
        [#else]
            <li class="comment-no-data">
                <span>暂无评论~~~~(>_<)~~~~</span>
            </li>
        [/#if]
    </ul>
    <div id="comment-pages" class="comment-pages page-div"></div>
    <div class="comment-editor">
        <textarea id="comment-main-editor" cols="1" rows="1" title="发表"></textarea>
    </div>
    <div class="comment-buttons">
        <span id="comment-error-msg" class="error-message"></span>
        <a id="comment-submit" class="submit-button">发表</a>
    </div>
</div>