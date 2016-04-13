[#macro html title keywords description mainJs lang="zh-CN" charset="UTF-8"]
<!DOCTYPE>
<html lang=${lang}>
    <head>
        <title>${title}</title>
        [#include "setting.ftl" /]
    </head>
    <body>

        [#--引入顶部--]
        [#include "header.ftl" /]

        <div id="main-body">
            [#--此处表示具体其他模板放的位置--]
            [#nested]
        </div>

        [#--引入尾部--]
        [#include "footer.ftl" /]

        [#--引入浮动--]
        [#include "fixed.ftl" /]
        [#include "right-bar.ftl" /]
    </body>
</html>
[/#macro]