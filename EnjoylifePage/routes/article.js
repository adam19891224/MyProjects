const express = require('express');
const router = express.Router();
const request = require('request');
const validator = require('validator');
const config = require('./config');
const api = config.api;
const logger = require('../log').logger;

router.get('/:id', function(req, res, next) {

    let id = req.params.id;

    if(!validator.isNumeric(id)){
        res.json({"code": "N"});
    }

    let url = api + "/article/" + id;
    request.post({
        url: url
    }, function optionalCallback(err, httpResponse, body) {
        if (err) {
            return logger.error('请求标签云接口失败:', err);
        }
        res.json(JSON.parse(body));
    });

});

router.post('/save', function(req, res, next) {

    let data = req.body;

    let url = api + "/article/postComment";
    request.post({
        url: url,
        form: data
    }, function optionalCallback(err, httpResponse, body) {
        if (err) {
            return logger.error('请求标签云接口失败:', err);
        }
        res.send(body);
    });

});

router.post('/comments', function(req, res, next) {

    let data = req.body;

    let url = api + "/article/getComment";
    request.post({
        url: url,
        form: data
    }, function optionalCallback(err, httpResponse, body) {
        if (err) {
            return logger.error('请求标签云接口失败:', err);
        }
        res.json(JSON.parse(body));
    });

});

module.exports = router;