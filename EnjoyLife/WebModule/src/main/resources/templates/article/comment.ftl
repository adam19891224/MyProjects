<div id="article-comment">
    <h4>留言 <span id="article-comment-counts"></span> 条</h4>
    <ul id="article-comment-ul">
    </ul>
    <li id="article-comment-li-display" class="dislpay-none">
        <div class="comment-title">
            <div class="title-left">
                <span></span>&nbsp;&nbsp;说：
            </div>
            <div class="title-right">
                <a href="javascript:">引用</a>
            </div>
        </div>
        <div class="comment-body"></div>
    </li>
    <div class="comment-page">
    [#include "../layout/page-bar.ftl" /]
    </div>
</div>
<div id="article-comment-editor">
    <h3>您的意见：</h3>
[#--引用div--]
    <div id="other-thinking-div">
        <h5>您正在引用 『<span></span>』 的意见：<strong>取消引用</strong></h5>
        <div></div>
    </div>
    <textarea id="article-comment-editor-text" placeholder="回复内容"></textarea>
    <ul class="editor-div">
        <li>
            <span class="editor-div-color-red">
                您的网名：
            </span>
            &nbsp;
            <input type="text" id="subimt-username" placeholder="必填" title="您的网名"/>
        </li>
        <li>
            <span>
                您的邮箱：
            </span>
            &nbsp;
            <input type="text" id="subimt-useremail" placeholder="选填" title="您的邮箱"/>
        </li>
        <li>
            <span>
                您的网站：
            </span>
            &nbsp;
            <input type="text" id="subimt-userwebsite" placeholder="选填" title="您的网站"/>
        </li>
    </ul>
    <div id="editor-submit" type="go">
        提交留言
    </div>
</div>