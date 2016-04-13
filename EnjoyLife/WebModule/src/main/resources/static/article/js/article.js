function Article(){

    this.init = function(){
        loadingData();
        loadingBarClickListener();
        editorSubmitButtonClickListener();
        drawLinkClickListener();
        replyDivCancleButtonListener();
        commentUserWebSiteHrefListener();
        websiteInputFocusListener();
        submitButtonListener();
        $("#article-comment").data("pageN", 1);
    };

    var loadingBarClickListener = function(){
        $("#loading-page-bar").click(function(){
            if(!checkIsLoad()){
                return false;
            }
            pageLoading();
            loadingData();
        });
    };

    var drawLinkClickListener = function(){
        $("#article-comment-ul").on("click", "a", function(){
            var li = $(this).parents("li");
            var text = li.find(".comment-body").html();
            var user = li.find("span").text();
            //过滤出blockquote之内的文本
            var reg = /<blockquote>[\s\S]*?<\/blockquote>/;
            text = text.replace(reg, "");
            var div = $("#article-comment-editor-text");
            var height = parseInt(div.offset().top) - (parseInt(window.screen.height) / 2);
            $("#other-thinking-div").show().find("span").text(user).end().find("div").html(text);
            //然后让滚动条滚动到这个div的位置
            window.scrollTo(0, parseInt(height));
        });
    };

    var checkWebSite = function(str){
        var strRegex="^((https|http|ftp|rtsp|mms)?://)"
        + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" // ftp的user@
        + "(([0-9]{1,3}\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
        + "|" // 允许IP和DOMAIN（域名）
        + "([0-9a-z_!~*'()-]+\.)*" // 域名- www.
        + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\." // 二级域名
        + "[a-z]{2,6})" // first level domain- .com or .museum
        + "(:[0-9]{1,4})?" // 端口- :80
        + "((/?)|" // a slash isn't required if there is no file name
        + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
        var re = new RegExp(strRegex);
        return re.test(str);
    };

    var websiteInputFocusListener = function(){
        $("#subimt-userwebsite").focus(function(){
            $(this).val("http://");
        });
    };

    var editorSubmitButtonClickListener = function(){
        $("#editor-submit").click(function(){
            var type = $(this).attr("type");
            if(type != "go"){
                return false;
            }
            var comment = $("#article-comment-editor-text").val();
            if(comment == ""){
                alert("请填写留言内容");
                return false;
            }

            var username = $("#subimt-username").val();
            if(username == ""){
                alert("请填写用户名!");
                return false;
            }

            var website = $("#subimt-userwebsite").val();
            if(website != ""){
                if(!checkWebSite(website)){
                    alert("请输入正确网址");
                    return false;
                }
            }

            $(this).attr("type", "back");

            var replyDiv = $("#other-thinking-div");

            var entity = {
                "commentBody" : comment,
                "commentUser" : username,
                "commentEmail" : $("#subimt-useremail").val(),
                "commentUserWebsite" : website,
                "articleId" : $("#article-main").attr("data-num"),
                "commentReplyUser" : replyDiv.find("span").text(),
                "commentReplyBody" : replyDiv.find("div").html()
            };

            $.ajax({
                url : "/articles/saveComment.html",
                type : "post",
                data : entity,
                success : function(data){
                    //data = $.parseJSON(data);
                    if(data == "success"){
                        //提交成功，则页面滚动到评论起始位置
                        //然后重新加载评论
                        //重置当前页码为1
                        $("#article-comment").data("pageN", "1");
                        //清空当前评论栏
                        $("#article-comment-ul").empty();
                        //回到评论位置
                        gotoPosition();
                        pageLoading();
                        loadingData();
                        $("#editor-submit").attr("type", "go");
                        //去掉引用匡
                        $("#other-thinking-div").find("strong").click();
                        //清空回复框
                        clearCommentDiv();
                    }else{
                        alert("添加失败！");
                        return false;
                    }
                }
            });
        });
    };

    var replyDivCancleButtonListener = function(){
        $("#other-thinking-div").on("click", "strong", function(){
            $("#other-thinking-div").hide().find("span").text("").end().find("div").html("");
        });
    };

    var commentUserWebSiteHrefListener = function(){
        $("#article-comment-ul").on("click", "span", function(){
            var href = $(this).attr("href");
            if(href){
                var hasHttp = href.indexOf("http");
                if(hasHttp != "-1"){
                    window.open(href);
                }else{
                    window.open("http://" + href);
                }
            }
        });
    };

    var loadingData = function(){

        var page = {
            "page" : $("#article-comment").data("pageN"),
            "articleId" : $("#article-main").attr("data-num")
        };

        $.ajax({
            url : "/articles/getComment.html",
            type : "post",
            data : page,
            timeout : 5000,
            success : function(data){
                data = $.parseJSON(data);
                $("#article-comment").data("pageN", data.page);
                appendingData(data.resultList);
                //设置总页码
                $("#article-comment-counts").text(data.totalCounts);
            },
            complete : function(XMLHttpRequest, status){ //请求完成后最终执行参数
                if(status == 'timeout'){//超时,status还有success,error等值的情况
                    pageTimeout();
                }
            }
        });
    };

    var appendingData = function (list){
        if(list.length > 0){
            var lis = "";
            for(var a = 0, len = list.length; a < len; a++){
                var obj = list[a];
                var li = $("#article-comment-li-display").clone();
                var html = "";
                if(obj.commentIsReply == "1"){
                    html += "<blockquote><h5>";
                    html += obj.commentReplyUser;
                    html += "：</h5>";
                    html += obj.commentReplyBody;
                    html += "</blockquote>";
                }
                html += obj.commentBody;
                if(obj.commentUserWebsite != null && obj.commentUserWebsite != ""){
                    li.removeClass("dislpay-none").find(".comment-body").html(html).end()
                        .find("span").text(obj.commentUser).attr("href", obj.commentUserWebsite).css({"text-decoration" : "underline", "cursor" : "pointer"});
                }else{
                    li.removeClass("dislpay-none").find(".comment-body").html(html).end()
                        .find("span").text(obj.commentUser);
                }
                lis += "<li>" + li.html() + "</li>";
            }
            $("#article-comment-ul").append(lis);
            pageOK();
        }else {
            pageNoData();
        }
    };

    var pageLoading = function(){
        changeLoadingBar("notR", "正在加载。。。", "/common/image/page-loading.gif");
    };

    var pageNoData = function(){
        changeLoadingBar("notR", "没有数据。。。", "/common/image/end.png");
    };

    var pageOK = function(){
        changeLoadingBar("R", "点击加载更多", "/common/image/front.png");
    };

    var pageTimeout = function(){
        changeLoadingBar("R", "超时，请重新点击加载", "/common/image/front.png");
    };

    var changeLoadingBar = function(follow, spanText, imgUrl){
        $("#loading-page-bar").attr("follow", follow)
            .find("span").text(spanText).end()
            .find("img").attr("src", imgUrl);
    };

    var checkIsLoad = function(){
        var isPage = $("#loading-page-bar").attr("follow");
        if(isPage == "R"){
            return true;
        }
    };

    var gotoPosition = function(){
        var top = $("#article-comment").offset().top;
        //noinspection JSValidateTypes
        $("html,body").scrollTop(top);
    };

    var clearCommentDiv = function(){
        $("#article-comment-editor").find("textarea").val("").end().find("input").val("");
    };

    var submitButtonListener = function(){
        $("#article-comment-editor").find("input").keydown(function(e){
            if (e.ctrlKey && e.keyCode == 13) {
                $("#editor-submit").click();
            }
        });
    };
}