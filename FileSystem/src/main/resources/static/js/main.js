/**
 *  by adam   ---   273961736@qq.com
 *  2016.4.20
 */
var ah = new AudioHelper("myAudio", ["/audios/1.mp3", "/audios/2.mp3"]);

function checkHtml5() {
    return typeof(Worker) !== "undefined";
}

(function main() {
    if(checkHtml5())
        setTimeout(ah.init, 2000);
    else
        console.log("no html5");
})();

