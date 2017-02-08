<!DOCTYPE html>
<html>
<head>
    [#include "../base/layout/document.ftl"]
</head>
<body>
    [#--顶部--]
    [#include "../base/layout/header.ftl"]

    [#--中间--]
    <main id="main" class="main">
        [#include "main.ftl"]
    </main>

    [#--底部--]
    [#include "../base/layout/footer.ftl"]
</body>
</html>