var categorys = new CategoryUtils();

$(function () {
    categorys.init($("#type-body"));

    //渲染评论编辑器
    CKEDITOR.replace("comment-main-editor", { height: "160px"});

    blogs.commentReplyButtonClick();

});

function Blogs(){

    var replyEidtorDiv = "<div id=\"reply-editor-div\" class=\"reply-editor\"><textarea id=\"reply-main-editor\" cols=\"1\" rows=\"1\" title=\"回复\"></textarea></div>";
    var replyButtonDiv = "<div id=\"reply-buttons-div\" class=\"reply-buttons\"><a class=\"submit-reply-button\">回复</a></div>";

    this.commentReplyButtonClick = function () {
        var _this, _thisL;
        $("#articlt-comment-div").on("click", ".to-reply", function () {
            _this = $(this);
            _thisL = _this.parents("li");
            if(_thisL.find("#reply-editor-div").length == 0 &&
                _thisL.find("#reply-buttons-div").length == 0){
                //移除其他地方的编辑器和按钮层
                $("#reply-editor-div").remove();
                $("#reply-buttons-div").remove();
                //移除蓝色颜色字体
                $(".reply-color").removeClass("reply-color");
                _this.addClass("reply-color");
                //在当前点击的评论中添加编辑器
                _thisL.find(".main-reply-container").append(replyEidtorDiv).append(replyButtonDiv);
                //创建编辑器
                CKEDITOR.replace("reply-main-editor", { height: "100px"});
            }
        });
    };

}

var blogs = new Blogs();