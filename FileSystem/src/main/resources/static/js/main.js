function AudioHelper(audioName, mp3){

    var audio = document.querySelector("#" + audioName);
    var mp3Arrays = mp3;
    var status = 0;
    var type = 1;

    this.init = function(){
        ah.play();
        audio.addEventListener('ended', function (e) {
            setTimeout(ah.play, 2000);
        }, false);
    };

    this.play = function(){
        audio.src = mp3Arrays[status];
        audio.play();
        status = type++ % 2;
    }

}

var ah = new AudioHelper("myAudio", ["/audios/1.mp3", "/audios/2.mp3"]);

(function main(){

    setTimeout(ah.init, 2000);

})();