function Applications() {

    this.castStr2Num = function(str){
        return parseInt(str);
    };

    this.isNull = function (str) {
        if(Object.prototype.toString.call(str) === "[object String]"){
            return (str != null && str != "" && str != undefined);
        }
        return (str != null && str != "" && str != undefined && str.length > 0);
    }

}
var applications = new Applications();