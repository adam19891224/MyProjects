/**
 * 类别标签特效
 * 感谢 次碳酸钴 -- https://www.web-tinker.com
 * @constructor
 */
function CategoryUtils(){

    var pi = Math.PI, sin = Math.sin, cos = Math.cos, tan = Math.tan, acos = Math.acos, abs = Math.abs;

    var $window = $(window);

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

    //初始化函数
    this.init = function (index, element) {
        //取元素列表
        var items = element.getElementsByTagName("a");
        if(items.length <= 0){
            return false;
        }

        var $element = $(element);
        //若其当前不可见则待其可见后方可执行初始化
        if ($element.is(":hidden"))return $window.one("resize", $.proxy(init, null, index, element));
        //取其尺寸
        var oWidth = element.offsetWidth;
        var oHeight = element.offsetHeight;
        //取其位置
        var offset;
        var onresize = function () {
            offset = $element.offset();
        };
        $window.resize(onresize);
        onresize();
        //取其缩放
        var scale = window.devicePixelRatio | 0 || 1;
        //计算像素尺寸
        var width = oWidth * scale;
        var height = oHeight * scale;

        //现代浏览器使用canvas渲染
        var g, canvas;
        canvas = document.createElement("canvas");
        //实际绘图区域尺寸
        canvas.width = width;
        canvas.height = height;
        //显示尺寸
        canvas.style.width = oWidth + "px";
        canvas.style.height = oHeight + "px";
        //放入文档
        element.appendChild(canvas);
        //初始化绘图对象
        g = canvas.getContext("2d");
        g.translate(width / 2 + 0.5, height / 2 + 0.5);
        g.textBaseline = "middle";
        g.textAlign = "center";
        new Promise(function (resolve) {
            //初始化所需的参数，异步处理
            var c = 8;
            var count = items.length;
            var index = 0;
            var n, m, w, h;
            void function callee() {
                n = 2 * (index + 1) / (count + 2) - 1;
                m = sin(acos(n));
                var item = items[index], $item = $(item);
                item.data = {
                    x: sin(c * pi * n) * m,
                    y: n,
                    z: cos(c * pi * n) * m,
                    // 触碰面积，考虑 retina 屏
                    width: w = item.offsetWidth * scale,
                    height: h = item.offsetHeight * scale,
                    text: $item.text(),
                    font: // 考虑 retina 屏
                    $item.css("font-size").replace(/\d+/, function ($0) {
                        return $0 * scale;
                    }) + " " + $item.css("font-family") + " ",
                    color: randomColor(),
                    href: $item.attr("href")
                };
                g ? $item.hide() : $item.show().css({
                    visibility: "visible",
                    marginLeft: -w / 2,
                    marginTop: -h / 2,
                    opacity: 0,
                    borderBottomColor: $item.css("color")
                });
                ++index < count ? setTimeout(callee) : resolve();
            }();
        }).then(function () {
            //渲染参数
            var A = 1 / tan(37 * pi / 360), Z = 6.4;
            var iterator = 0;
            //绘制下一帧
            var nextFrame = function () {
                if (--iterator < -628)iterator = 0;
                draw();
            };
            //绘制
            var draw = function () {
                var i, o, x, y, z, d, s, c;
                s = sin(iterator / 100);
                c = cos(iterator / 100);
                if (g)
                    g.clearRect(-width / 2, -height / 2, width, height);

                for (i = 0; o = items[i]; i++) {
                    d = o.data;
                    //计算旋转
                    x = d.x * c + d.z * s;
                    z = d.z * c - d.x * s; //旋转
                    y = d.y;
                    z += 1 + Z; //平移
                    //投射变换
                    d.px = height * A * x / z | 0;
                    d.py = height * A * y / z | 0;
                    d.pz = 1 - (z - Z) * 0.35; //(1-(z-Z)/2)*0.7+0.3;
                    //绘制到屏幕
                    if (g) {
                        g.fillStyle = "rgba(" + d.color + "," + d.pz + ")";
                        g.font = d.font;
                        g.fillText(d.text, d.px, d.py);
                    } else {
                        o.style.left = d.px + width / 2 + "px";
                        o.style.top = d.py + height / 2 + "px";
                        o.style.zIndex = o.filters[0].opacity = d.pz * 100 | 0;
                    }
                }
            };
            return Promise.resolve({nextFrame: nextFrame, draw: draw});
        }).then(function (animation) { //初始化完成
            //绑定Hover控制播放停止
            var handle;
            function play() {
                animation.nextFrame();
                //开始css3动画 由于requestAnimationFrame只是一次性的，所以为了实现动画，则要反复调用
                handle = requestAnimationFrame(play);
            }

            $element.hover(function () {
                cancelAnimationFrame(handle);
                handle = void 0;
            }, function () {
                if (!handle)play();
            }).mouseleave();

            return Promise.resolve(animation);

        }).then(function (animation) {
            //Canvas上的鼠标操作
            if (!canvas)return;
            var active;
            $(canvas).on({
                click: function () {
                    if (active)open(active.href)
                },
                mousemove: function (e) { //hover事件，修改标签样式
                    var x = (e.pageX - offset.left - oWidth / 2) * scale;
                    var y = (e.pageY - offset.top - oHeight / 2) * scale;
                    var item, i;
                    for (active = null, i = 0; item = items[i]; i++) {
                        item = item.data;
                        //碰撞检测
                        if (
                            abs(item.px - x) * 2 < item.width && //x方向
                            abs(item.py - y) * 2 < item.height && //y方向
                            (active ? item.z < active.z : 1) //z方向
                        )active = item;
                    }

                    animation.draw();
                    $(canvas).css("cursor", active ? "pointer" : "");
                    if (!active)return;
                    //绘制hover时的下划线
                    g.fillStyle = "rgba(" + active.color + "," + active.pz + ")";
                    g.fillRect(active.px - active.width / 2, active.py + active.height / 2, active.width, 2);
                }
            });
        })["catch"](function (e) {
            console.log(e);
        });
    }
}