const express = require('express');
const router = express.Router();
const request = require('request');
const config = require('./config');
const api = config.api;
const logger = require('../log').logger;

router.get('/', function(req, res, next) {

    let url = api + "/friends";

    request.post({
        url: url
    }, function optionalCallback(err, httpResponse, body) {
        if (err) {
            return logger.error('请求标签云接口失败:', err);
        }
        res.json(JSON.parse(body));
    });

});

module.exports = router;