[#include "../layout/html.ftl" /]
[@html title="小周的修行之路" keywords="小周的修行之路" description="路漫漫其修远兮，吾将上下而求索！" mainJs="/home/js/main.js"]
    <link rel="stylesheet" href="/home/css/home.css?t=[@time /]">
    <div class="context">
        <div class="context-left">
            <ul id="article-pages">

            </ul>
            [#include "../layout/page-bar.ftl" /]
        </div>
        <div class="context-right">
            <ul>
                <li id="play-music-li">
                    <iframe frameborder="no" border="0" marginwidth="0" marginheight="0" width=280 height=110 src="http://music.163.com/outchain/player?type=0&id=96413803&auto=0&height=90"></iframe>
                </li>
            </ul>
        </div>
    </div>
    [#include "article-li.ftl" /]
[/@html]