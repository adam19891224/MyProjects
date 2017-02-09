<!DOCTYPE html>
<html>
    <head>
        <title>月下竹影---Web开发交流频道</title>
        <meta name="keywords" content="Web技术，Web开发，Web交流，月下竹影，竹影，箫竹影，周禹宏" />
        <meta name="description" content="月下竹影，是一个作为Web技术交流平台的个人原创技术博客。期待大家的光临。" />
    </head>
    <body>
        <ul>
            [#list articles as article]
                <li>
                    <a href="http://localhost:8080/EnjoylifeView/view.html#/blog/${article.articleSid}">
                        <h2 title="${article.articleTitle}">${article.articleTitle}</h2>
                    </a>
                    <span>
                        ${article.articleDescription}
                    </span>
                </li>
            [/#list]

        </ul>
    </body>
</html>
