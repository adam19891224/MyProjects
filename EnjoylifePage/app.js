const express = require('express');
const path = require('path');
const favicon = require('serve-favicon');
const cookieParser = require('cookie-parser');
const bodyParser = require('body-parser');
const log = require('./log');

const app = express();

app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');
log.use(app);
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));


//创建路由
const page = require("./routes/page");
const index = require("./routes/index");
const infomations = require('./routes/infomations');
const series = require("./routes/series");
const eyes = require("./routes/eyes");
const friends = require("./routes/friends");
const genre = require("./routes/genre");
const query = require("./routes/query");
const blogs = require("./routes/blogs");

//注册路由
app.use('/', page);
app.use('/index', index);
app.use('/infomations', infomations);
app.use('/series', series);
app.use('/eyes', eyes);
app.use('/friends', friends);
app.use('/genre', genre);
app.use('/query', query);
app.use('/blogs', blogs);


app.use(function(req, res, next) {
  const err = new Error('Not Found');
  err.status = 404;
  next(err);
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});


module.exports = app;
console.log("启动成功，请点击： http://localhost:36666");