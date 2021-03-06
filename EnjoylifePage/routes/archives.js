const express = require('express');
const router = express.Router();
const request = require('request');
const validator = require('validator');
const config = require('./config');
const api = config.api;
const logger = require('../log').logger;

router.get('/:page', function(req, res, next) {

    let page = req.params.page;
    if(!validator.isNumeric(page)){
        res.json({"code": "N"});
    }
    let url = api + "/archives/" + page;

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