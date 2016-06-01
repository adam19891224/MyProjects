function HotsUtils(){

    this.getHots = function(){
        $.ajax({
            url : "/blogs/getHotsBlogs.html",
            type : "post",
            timeout : 5000,
            success : function(data){
                data = eval("(" + data + ")");
                $("#hots-article-ul").empty();
                if(data.length > 0){
                    $("#hots-article-ul").setTemplateElement("hotsArticle").processTemplate(data);
                }else{
                    $("#hots-article-ul").append("<li><a href='javascript:'>敬请期待</a></li>");
                }
            },
            error : function(err){
                $("#hots-article-ul").empty();
                $("#hots-article-ul").append("<li><a href='javascript:'>敬请期待</a></li>");
                console.log(err);
            },
            complete : function(XMLHttpRequest,status){
                if(status=='timeout'){
                    $("#hots-article-ul").empty();
                    $("#hots-article-ul").append("<li><a href='javascript:'>敬请期待</a></li>");
                }
            }
        })
    };

}