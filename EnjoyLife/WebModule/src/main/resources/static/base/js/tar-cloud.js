/**
 * 类别标签特效
 * 感谢 次碳酸钴 -- https://www.web-tinker.com
 * @constructor
 */
function CategoryUtils(){

    var pi = Math.PI, sin = Math.sin, cos = Math.cos, tan = Math.tan, acos = Math.acos, abs = Math.abs;

    var $window = $(window);

    var _this = this;

    /**
     * 随机生成颜色
     */
    var randomColor = function() {
        var colorStr = Math.floor(Math.random() * 0xFFFFFF).toString(16).toUpperCase();

        /**
         * 十六进制颜色转换为RGB颜色
         */
        function colorHexToRGB(color) {
            color = color.toUpperCase();
            var regexpHex = /^#[0-9a-fA-F]{3,6}$/;//Hex

            if (regexpHex.test(color)) {
                var hexArray = [];
                var count = 1;

                for (var i = 1; i <= 3; i++) {
                    if (color.length - 2 * i > 3 - i) {
                        hexArray.push(Number("0x" + color.substring(count, count + 2)));
                        count += 2;
                    } else {
                        hexArray.push(Number("0x" + color.charAt(count) + color.charAt(count)));
                        count += 1;
                    }
                }
                return hexArray.join(",");
            } else {
                return color;
            }
        }
        return colorHexToRGB("#" + "000000".substring(0, 6 - colorStr) + colorStr);
    };

    /**
     * @param $element 标签云所在的父类容器
     * @returns {boolean}
     */
    this.init = function ($element) {

        //取元素列表
        var items = $element.find("a");

        //如果容器内没有找到a标签，则返回
        if(items.length <= 0){
            return false;
        }
        //如果当前元素是隐藏的，则绑定resize方法，当该元素可见的时候，才执行Init方法
        if ($element.is(":hidden")){
            return $window.one("resize", $.proxy(_this.init, null, $element));
        }

        //获取传入的容器的宽度和高度
        var oWidth = $element.width();
        var oHeight = $element.height();

        //取其位置
        var offset = $element.offset();

        //当浏览器调整窗口大小时，重新获取位置
        $window.resize(function () {
            offset = $element.offset();
        });

        //取其缩放
        var scale = window.devicePixelRatio | 0 || 1;

        //计算像素尺寸
        var width = oWidth * scale;
        var height = oHeight * scale;

        //现代浏览器使用canvas渲染, 声明两个变量，一个是画布canvas, 另一个是绘画环境（通过canvas的getContext方法获得）
        //canvas本身没有api, 都是通过它的绘画环境来进行绘画，最后放进画布canvas中
        var drawEvn, canvas;

        //创建canvas对象
        canvas = document.createElement("canvas");

        //实际绘图区域尺寸, 等于容器的高和宽乘以缩放比例
        canvas.width = width;
        canvas.height = height;

        //显示尺寸, 就等于容器的高和宽
        canvas.style.width = oWidth + "px";
        canvas.style.height = oHeight + "px";

        //放入文档
        $element.append(canvas);

        //初始化绘图环境, 目前canvas仅支持2d图像，所以只能传入2d参数
        drawEvn = canvas.getContext("2d");

        //转换
        drawEvn.translate(width / 2 + 0.5, height / 2 + 0.5);

        //设置绘画内部文字的基线
        drawEvn.textBaseline = "middle";
        //设置绘画内部文字的水平对齐方式
        drawEvn.textAlign = "center";

        //采用异步对象
        new Promise(function (resolve) {
            //初始化所需的参数，异步处理
            var c = 8;
            var count = items.length;
            var index = 0;
            var n, m, w, h;

            //创建内部方法，作用是对容器的a标签进行绘画元素的属性构建
            function addGraphics() {
                var item;
                //遍历容器中的a标签，进行绘画
                items.each(function () {
                    item = $(this);
                    //准备各个基数
                    n = 2 * (index + 1) / (count + 2) - 1;
                    m = sin(acos(n));

                    //把当前标签的绘画元素相关属性放进对应的Item中
                    item.data("graphic", {
                        x: sin(c * pi * n) * m,
                        y: n,
                        z: cos(c * pi * n) * m,
                        // 触碰面积
                        width: w = item.outerWidth() * scale,
                        height: h = item.outerHeight() * scale,
                        text: item.text(),
                        font: item.css("font-size").replace(/\d+/, function ($0) {
                                                                        return $0 * scale;
                                                                    }) + " " + item.css("font-family") + " ",
                        color: randomColor(),
                        href: item.attr("href")
                    });
                    //如果绘画环境存在，则让当前元素隐藏
                    drawEvn ? item.hide() : item.show().css({
                        visibility: "visible",
                        marginLeft: -w / 2,
                        marginTop: -h / 2,
                        opacity: 0,
                        borderBottomColor: item.css("color")
                    });
                    index ++;
                });
            }
            //调用
            addGraphics();
            //通知异步对象成功
            resolve();
        }).then(function () {
            //渲染参数
            var A = 1 / tan(37 * pi / 360), Z = 6.4;
            var iterator = 0;
            //绘制下一帧
            var nextFrame = function () {
                if (--iterator < -628){
                    iterator = 0;
                }
                draw();
            };
            //绘制
            var draw = function () {
                var o, x, y, z, s, c;
                s = sin(iterator / 100);
                c = cos(iterator / 100);
                if (drawEvn){
                    drawEvn.clearRect(-width / 2, -height / 2, width, height);
                }

                var itemData;

                items.each(function () {
                    //获取元素的绘画属性
                    itemData = $(this).data("graphic");
                    //计算旋转
                    x = itemData.x * c + itemData.z * s;
                    z = itemData.z * c - itemData.x * s; //旋转
                    y = itemData.y;
                    z += 1 + Z; //平移
                    //投射变换
                    itemData.px = height * A * x / z | 0;
                    itemData.py = height * A * y / z | 0;
                    itemData.pz = 1 - (z - Z) * 0.35; //(1-(z-Z)/2)*0.7+0.3;
                    //绘制到屏幕
                    if (drawEvn) {
                        drawEvn.fillStyle = "rgba(" + itemData.color + "," + itemData.pz + ")";
                        drawEvn.font = itemData.font;
                        drawEvn.fillText(itemData.text, itemData.px, itemData.py);
                    } else {
                        o.style.left = itemData.px + width / 2 + "px";
                        o.style.top = itemData.py + height / 2 + "px";
                        o.style.zIndex = o.filters[0].opacity = itemData.pz * 100 | 0;
                    }
                });
            };

            //将nextFrame和draw方法放入一个代理对象中，传递给下一个them方法
            var proxy = {
                nextFrame: nextFrame,
                draw: draw
            };
            return Promise.resolve(proxy);
        }).then(function (proxy) {

            var handle = 0;
            //声明一个内部play方法
            function play() {
                //调用代理对象的下一帧方法
                proxy.nextFrame();
                //开始css3动画 由于requestAnimationFrame只是一次性的，所以为了实现动画，则要反复调用
                handle = requestAnimationFrame(play);
            }

            //绑定div容器的hover方法，同时调用该容器的mouseleave方法触发hover的移除方法
            $element.hover(function () {
                //鼠标进入方法，移除动画效果
                cancelAnimationFrame(handle);
                //将handle设置为0
                handle = 0;
            }, function () {
                //鼠标移除方法，首先判断handle是否为0，如果是，则继续播放
                if (handle == 0){
                    play();
                }
            }).mouseleave();

            return Promise.resolve(proxy);

        }).then(function (proxy) {
            if (!canvas){
                return;
            }
            var active;
            //给canvas绑定点击事件和鼠标划入事件
            $(canvas).on({
                click: function () {
                    if (active){
                        window.open(active.attr("href"));
                    }
                },
                mousemove: function (e) { //hover事件，修改标签样式
                    var x = (e.pageX - offset.left - oWidth / 2) * scale;
                    var y = (e.pageY - offset.top - oHeight / 2) * scale;
                    var item, itemData;
                    active = null;
                    //遍历容器内的a标签
                    items.each(function () {
                        item = $(this);
                        //获取元素的绘画属性
                        itemData = item.data("graphic");
                        //碰撞检测
                        if (abs(itemData.px - x) * 2 < itemData.width && //x方向
                            abs(itemData.py - y) * 2 < itemData.height && //y方向
                            (active ? itemData.z < active.z : 1) //z方向
                        ){
                            active = item;
                        }
                    });
                    proxy.draw();
                    $(canvas).css("cursor", active ? "pointer" : "");
                    if (!active){
                        return;
                    }
                    var nowData = active.data("graphic");
                    //绘制hover时的下划线
                    drawEvn.fillStyle = "rgba(" + nowData.color + "," + nowData.pz + ")";
                    drawEvn.fillRect(nowData.px - active.width() / 2, nowData.py + active.height() / 2, active.width(), 2);
                }
            });
        })["catch"](function (e) {
            console.log(e);
        });
    }
}