const log4js = require('log4js');
log4js.configure({
    appenders: [
        {
            type: "console"
        }, //控制台输出
        {
            type: "dateFile",
            filename: "E:/logs/enjoylifepage.log",
            pattern: "_yyyy-MM-dd",
            alwaysIncludePattern: false
        } //日期文件格式
    ],
    replaceConsole: true,
    levels:{
        dateFileLog: "INFO"
    }
});

const dateFileLog = log4js.getLogger('dateFileLog');

exports.logger = dateFileLog;

exports.use = function(app) {
    //页面请求日志,用auto的话,默认级别是WARN
    app.use(log4js.connectLogger(dateFileLog, {level: 'debug', format: ':method :url'}));
};