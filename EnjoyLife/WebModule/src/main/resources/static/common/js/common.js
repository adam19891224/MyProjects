function Application(){

    this.init = function(){
        navBarClickListener();
        searchButtonListener();
        checkOldBowser();
        showConsloeLog();
        windowScrollListener();
        rightBarClickListener();
    };

    var navBarClickListener = function(){
        $("#nav-bar").find("li").click(function(){
            window.location = $(this).attr("data-url");
        });
    };

    var searchButtonListener = function(){
        $("#search-button").click(function(){
            var text = $("#search-input").val();
            alert(text);
        });
    };

    this.navBarAnimation = function(){
        setTimeout("leftStart()", 1000);
    };

    var checkOldBowser = function(){
        if(!$.support.leadingWhitespace){
            $("#fixed-div-middle").empty().html("<span>您的浏览器版本过低，建议更换最新的浏览器！！！</span>").css({
                "color" : "red",
                "text-align" : "center"
            });
        }else{
            setInterval("fixedTextMarquee()", 500);
        }
    };

    var showConsloeLog = function(){
        if(window.console && console.log){
            console.log("欢迎访问小周的网站(*^__^*)");
        }
    };

    var windowScrollListener = function(){
        var rightBar = $("#right-bar");
        $(window).scroll(function(){
            //noinspection JSValidateTypes
            var nowTop = $(window).scrollTop();
            if(nowTop > 300){
                rightBar.fadeIn("slow");
            }else{
                rightBar.fadeOut("slow");
            }
        });
    };

    var rightBarClickListener = function(){
        $("#right-bar").on("click", "li", function(){
            var type = $(this).attr("type");
            if(type == "top"){
                gotoTop();
            }
        });
    };

    var gotoTop = function(){
        $("html,body").animate({scrollTop : 0}, 1000);
    }
}

var time = 0;

var animateId;

function navBarAnimateLeft(){
    $("#nav-bar").find("li").eq(time).find(".nav-underline").animate({"top" : "0"}, 300, function(){
        $(this).animate({"top" : "67px"}, 300);
    });
    time ++;
    if(time > 3){
        clearInterval(animateId);
        setTimeout("rightStart()", 1000);
    }
}

function navBarAnimateRight(){
    time --;
    $("#nav-bar").find("li").eq(time).find(".nav-underline").animate({"top" : "0"}, 300, function(){
        $(this).animate({"top" : "67px"}, 300);
    });
    if(time == 0){
        clearInterval(animateId);
        navBarMouseMoveListener();
    }
}

var navBarMouseMoveListener = function(){
    $("#nav-bar").find("li").mouseenter(function(){
        $(this).find(".nav-underline").animate({"top" : "0"}, 200);
    });
    $("#nav-bar").find("li").mouseleave(function(){
        $(this).find(".nav-underline").animate({"top" : "67px"}, 200);
    });
};

function leftStart(){
    animateId = setInterval("navBarAnimateLeft()", 100);
}

function rightStart(){
    animateId = setInterval("navBarAnimateRight()", 100);
}

function fixedTextMarquee(){
    var fixed = $("#fixed-div-middle");
    var text = fixed.text();
    var length = text.length;
    fixed.text(text.substring(1, length) + text.charAt(0));
}
