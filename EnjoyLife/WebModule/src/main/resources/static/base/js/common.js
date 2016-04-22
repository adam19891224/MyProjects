function Common(){
    this.searchButtonMouseListener = function(){

        $("#search").mouseover(function(){
            $(this).find("img").attr("src", "../base/images/mirror-hover.png");
        });

        $("#search").mouseout(function(){
            $(this).find("img").attr("src", "../base/images/mirror.png");
        });

    }
}
