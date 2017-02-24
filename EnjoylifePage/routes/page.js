const express = require('express');
const router = express.Router();
const config = require('./config');
const logger = require('../log').logger;

/* GET home page. */
router.get('/', function(req, res, next) {
    logger.info("访问网站。。。");
    res.render('page');
});

module.exports = router;