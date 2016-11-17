function Applications() {

    var _this = this;

    this.castStr2Num = function(str){
        return parseInt(str);
    };

    this.isNotNull = function (str) {
        if(Object.prototype.toString.call(str) === "[object String]"){
            return (str != null && str != "" && str != undefined);
        }
        return (str != null && str != "" && str != undefined && str.length > 0);
    };

    this.replaceBlock = function (str) {
        return str.replace(/\s/g, '');
    }

}
var applications = new Applications();