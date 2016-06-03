<#--主评论模板-->
<textarea id="mainComment" class="jTempleta-textarea" title="mainComment" rows="0" cols="0">
    {#foreach $T as entity}
        <li>
            <h6>
                <a href="{$T.entity.href}" class="{$T.entity.clazz}" target="_blank">{$T.entity.userName}</a>&nbsp;说：<span>{$T.entity.time}</span>
            </h6>
            <section class="comment-section">
                {$T.entity.comment}
            </section>
            <h6 class="comment-helper" comment="{$T.entity.commentSid}" commentId="{$T.entity.commentId}">
                <a class="reply" show="N">回复</a>
                <a class="open" show="N" load="Y">展开</a>
            </h6>
            <div class="comment-reply-bar"></div>
            <ul class="comment-reply-ul"></ul>
            <div class="comment-reply-div">
                <div class="comment-reply-editor">
                    &lt;textarea name="comment-reply-editor" title="回复文章"&gt;&lt;/textarea&gt;
                </div>
                <div class="comment-reply-submit">
                    <span>对不起，文本内容不能为空！</span>
                    <a class="submit-comment-reply" owner="{$T.entity.userName}">
                        提交
                    </a>
                </div>
            </div>
        </li>
    {#/for}
</textarea>
<#--回复评论模板-->
<textarea id="replyComment" class="jTempleta-textarea" title="replyComment" rows="0" cols="0">
    {#foreach $T as entity}
        <li>
            <h6>
                <span>
                    <a href="{$T.entity.href}" class="{$T.entity.clazz}" target="_blank">{$T.entity.userName}</a>
                </span>
                <span>回复</span>
                <span>{$T.entity.replyUser}</span>
            </h6>
            <section class="comment-reply-section">
                {$T.entity.comment}
            </section>
        </li>
    {#/for}
</textarea>