/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : webserver

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-11-10 10:39:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_article
-- ----------------------------
DROP TABLE IF EXISTS `tb_article`;
CREATE TABLE `tb_article` (
  `ARTICLE_SID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `ARTICLE_ID` varchar(64) NOT NULL COMMENT '文章id（uuid唯一编码）',
  `CREATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `ARTICLE_BODY` longtext COMMENT '文章内容',
  `ARTICLE_TITLE` varchar(255) DEFAULT NULL COMMENT '文章题目',
  `ARTICLE_DESCRIPTION` text COMMENT '文章摘要',
  `ARTICLE_BACKUP1` varchar(255) DEFAULT NULL COMMENT '文章预留字段1',
  `ARTICLE_BACKUP2` varchar(255) DEFAULT NULL COMMENT '文章预留字段2',
  `ARTICLE_BACKUP3` varchar(255) DEFAULT NULL COMMENT '文章预留字段3',
  `ARTICLE_IMG` varchar(255) DEFAULT NULL COMMENT '文章列表配图',
  PRIMARY KEY (`ARTICLE_SID`),
  KEY `tb_article_id` (`ARTICLE_ID`) USING BTREE,
  FULLTEXT KEY `tb_article_title` (`ARTICLE_TITLE`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_article
-- ----------------------------
INSERT INTO `tb_article` VALUES ('7', '026d007d-bf29-4f25-9597-bb7cea81036f', '2015-12-31 17:36:34', '2015-12-31 17:36:34', '<p>完整测试博文完整测试博文完整测试博文完整测试博文</p>\n\n<p>插入一段代码：</p>\n\n<pre>\n<code class=\"lang-java\">public static CloseableHttpResponse uploadFile(String url, HttpEntity entity){\n\n        HttpPost httpPost = new HttpPost(url);\n\n        httpPost.setEntity(entity);\n        //创建httpclient对象\n        CloseableHttpClient httpclient = HttpClients.createDefault();\n        try {\n            return httpclient.execute(httpPost);\n        } catch (IOException e) {\n            e.printStackTrace();\n        }\n        return null;\n    }</code></pre>\n\n<p><font face=\"monospace\">以上就是代码片段</font></p>', '完整测试博文', '完整测试博文完整测试博文完整测试博文完整测试博文', null, null, null, 'http://img.zcool.cn/community/01234f5721afff6ac725381232d2a0.jpg@900w_1l_2o');
INSERT INTO `tb_article` VALUES ('8', '9d4721f5-a1bd-4fc6-80bd-8243776e5831', '2016-01-12 09:17:08', '2016-01-12 09:17:08', '<p>&nbsp; 这是第二篇测试，我们来看一下代码</p>\n\n<pre>\n<code class=\"lang-java\">public class Test{\n    \n    public void say(){\n        System.out.pringln(&quot;111&quot;);\n    }\n\n}</code></pre>\n\n<p>&nbsp;</p>\n\n<p>&nbsp;</p>\n\n<p><font face=\"monospace\">代码测试完毕</font></p>\n\n<p>&nbsp; 还有其他的什么吗，没有</p>\n\n<p>&nbsp;</p>', '二次测试', '大多数 jQuery 的遍历方法会操作一个 jQuery 对象实例，并生成一个匹配不同 DOM 元素集的新对象。当发生这种情况时，应该会把新的元素集推入维持在对象中的堆栈内。每次成功的筛选方法调用都会把新元素推入堆栈中。如果我们需要老的元素集，可以使用 end() 从堆栈中弹出新集合。', null, null, null, 'http://image.adamzhou.cn/getMyShowTarg/2016/1/d2ccb681-8633-4379-8fcb-6d65bcadc908.png');
INSERT INTO `tb_article` VALUES ('11', 'edc3bd8f-5647-4444-8070-07bfac0407e8', '2016-01-21 13:39:41', '2016-01-21 13:39:41', '<p>asdasdasdasdasdasdasd</p>\r\n\r\n<p>asdasdasdasd</p>\r\n\r\n<p>asdasdasd</p>\r\n\r\n<p>asdasdasd</p>', 'dasdasdads', 'asdasdasdsad', null, null, null, 'http://localhost:9999/getMyShowTarg/2016/1/b9feae86-9167-4f8f-a37d-ee79df78dcbb.png');
INSERT INTO `tb_article` VALUES ('12', 'e70600b6-a867-41fb-884b-55d7603977f7', '2016-01-21 13:41:38', '2016-01-21 13:41:38', '<p>测试代码插入</p>\r\n\r\n<pre>\r\n<code class=\"lang-java\">public static void main(){\r\n    //ssss\r\n    test();\r\n\r\n}</code></pre>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><font face=\"monospace\">测试图片插入</font></p>\r\n\r\n<p><font face=\"monospace\"><img alt=\"\" src=\"http://localhost:9999/getMyShowTarg/2016/1/776a6ce1-1e8f-4632-be87-4f12802391ab.png\" style=\"height:100px; width:166px\" />事实上事实上的撒打算的按时打算打算打算打算。</font></p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><font face=\"monospace\">测试完毕 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;</font></p>', '测试', '这是表述测试', null, null, null, 'http://localhost:9999/getMyShowTarg/2016/1/5a8f6ffd-8c27-453c-a876-16c26aab803d.png');
INSERT INTO `tb_article` VALUES ('13', 'eba93722-aef0-4986-89ae-a9541f3eab8c', '2016-01-21 14:07:55', '2016-01-21 14:07:55', '<p>adsadasdas</p>\r\n\r\n<p>asdasdasdas</p>\r\n\r\n<p><img alt=\"\" src=\"http://localhost:9999/getMyShowTarg/2016/1/d4b83180-788c-4a95-bdc7-dcea26b83f24.png\" style=\"height:150px; width:249px\" />asdasd asdasdasdasd</p>\r\n\r\n<p>asdasdasd</p>\r\n\r\n<pre>\r\n<code class=\"lang-java\">public static void main(){\r\n    //ssssss\r\n    asdasdasd\r\n}</code></pre>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>', 'asdasdasd', 'weqweqweqwe', null, null, null, 'http://localhost:9999/getMyShowTarg/2016/1/36335c17-6c43-4c45-83f4-a89d7fbb19d5.png');
INSERT INTO `tb_article` VALUES ('14', '980829d0-d7a3-4a4e-9b86-d772bdfc2e0a', '2016-01-21 16:32:35', '2016-01-21 16:32:35', '<p>按时打算发大水发</p>\r\n\r\n<p>打谁爱上 saaaaaaaaaaaaaaaaaaaaaaaaaa法 &nbsp; &nbsp; &nbsp; &nbsp;大师的撒啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊</p>\r\n\r\n<p>发撒的发生反反复复反复反复反复反复反复反复反复</p>\r\n\r\n<p><img alt=\"\" src=\"http://localhost:9999/getMyShowTarg/2016/1/51bc7409-6024-4f21-b19b-d5e3637ec130.png\" style=\"height:400px; width:427px\" />阿斯达锁定</p>\r\n\r\n<p>撒旦法师法</p>\r\n\r\n<pre>\r\n<code class=\"lang-javascript\">function test(){\r\n    alert(&quot;1&quot;);\r\n}</code></pre>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>dsadasd&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>', '测试', '阿斯达锁定', null, null, null, 'http://localhost:9999/getMyShowTarg/2016/1/152733d1-f85d-4329-a48b-d7d5ccc8273d.png');
INSERT INTO `tb_article` VALUES ('15', 'b0b3698b-8192-4d82-be9e-1c72f6fe2cc6', '2016-02-04 14:22:18', '2016-02-04 14:22:18', '<p>测试</p>\r\n\r\n<p>哈哈</p>\r\n\r\n<p>呵呵</p>\r\n\r\n<p><img alt=\"\" src=\"http://image.adamzhou.cn/getMyShowTarg/2016/2/8a000226-086b-45bd-8bb4-52f17a53ea03.png\" style=\"height:500px; width:800px\" /></p>\r\n\r\n<p>哈哈</p>\r\n\r\n<p>&nbsp;</p>', '测试', '不错测试完整', null, null, null, 'http://image.adamzhou.cn/getMyShowTarg/2016/2/f40e5313-559b-4cf7-8be7-b167833fcecc.png');
INSERT INTO `tb_article` VALUES ('16', '4c2c4d79-b298-4394-a00d-cefb85748ea1', '2016-02-04 14:26:16', '2016-02-04 14:26:16', '<p><img alt=\"\" src=\"http://image.adamzhou.cn/getMyShowTarg/2016/2/d3d6291f-c4ff-44f6-bcc4-0000a5b116d3.png\" /></p>', '图片', '法法师法', null, null, null, 'http://image.adamzhou.cn/getMyShowTarg/2016/2/24689801-8754-4b66-8822-f9df0e123fe1.png');

-- ----------------------------
-- Table structure for tb_article_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_article_comment`;
CREATE TABLE `tb_article_comment` (
  `ARTICLE_COMMENT_ID` varchar(64) NOT NULL COMMENT '表主键id',
  `ARTICLE_ID` varchar(128) DEFAULT NULL COMMENT '文章id',
  `COMMENT_ID` varchar(128) DEFAULT NULL COMMENT '评论Id',
  PRIMARY KEY (`ARTICLE_COMMENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_article_comment
-- ----------------------------
INSERT INTO `tb_article_comment` VALUES ('1aabc53f-c47d-43a7-a9ca-3dcd52c7edb8', '980829d0-d7a3-4a4e-9b86-d772bdfc2e0a', '399ac0c9-6a04-40bf-9602-0fb17b65f3e3');
INSERT INTO `tb_article_comment` VALUES ('1e45654e-aa77-4160-a869-1443a5747f4b', '4c2c4d79-b298-4394-a00d-cefb85748ea1', 'ee86a65c-f5c4-4f8c-aaa7-0a4c1181ece7');
INSERT INTO `tb_article_comment` VALUES ('2628ce44-d373-4be4-957a-3ddf67bac525', '980829d0-d7a3-4a4e-9b86-d772bdfc2e0a', '8bd64769-13c0-43a2-9124-fc0331936bea');
INSERT INTO `tb_article_comment` VALUES ('2931e6f1-b01c-4f4e-b646-078ca58e49f4', '980829d0-d7a3-4a4e-9b86-d772bdfc2e0a', '13f838e4-c399-45a6-97e9-208dd90d057a');
INSERT INTO `tb_article_comment` VALUES ('30210a50-fcae-4016-a28a-b2407a913d3d', '980829d0-d7a3-4a4e-9b86-d772bdfc2e0a', '93bf466c-4fad-4cf6-b787-b3a9a6bc1a3e');
INSERT INTO `tb_article_comment` VALUES ('30ac53d7-9045-44cc-8c83-8eae79688505', '980829d0-d7a3-4a4e-9b86-d772bdfc2e0a', 'cc77cf41-d2a5-4727-8b06-42309ee253ee');
INSERT INTO `tb_article_comment` VALUES ('37f84b02-3933-4a88-a7cb-f36b130fe4cf', '980829d0-d7a3-4a4e-9b86-d772bdfc2e0a', 'ef5a13d8-f8b4-4f24-bcfa-701b73cc2031');
INSERT INTO `tb_article_comment` VALUES ('3806c109-ee3e-45f8-9adc-aedd6caa1f56', '980829d0-d7a3-4a4e-9b86-d772bdfc2e0a', '9ce25fa7-455b-48a1-94c6-05c9734b2da6');
INSERT INTO `tb_article_comment` VALUES ('3f14a317-4607-4ea2-a7ac-82e88d0ad309', '980829d0-d7a3-4a4e-9b86-d772bdfc2e0a', '1cca400a-e6b0-45df-9a48-be907e4cf0af');
INSERT INTO `tb_article_comment` VALUES ('49894604-9882-4f92-9938-26fc8a5542df', '4c2c4d79-b298-4394-a00d-cefb85748ea1', '09e9eb1f-1fa8-46af-b680-a3ba1116740b');
INSERT INTO `tb_article_comment` VALUES ('4c328683-43b9-4c01-a7ec-6c2b7da6c92f', '980829d0-d7a3-4a4e-9b86-d772bdfc2e0a', '63cdd0bb-5ed9-436b-bee9-51e55b31b1e2');
INSERT INTO `tb_article_comment` VALUES ('4d31e789-d482-4a3f-a8e0-1e8b2f37f1cb', '980829d0-d7a3-4a4e-9b86-d772bdfc2e0a', '0cf823c5-5e2f-479f-967a-f7ef28b1c0b3');
INSERT INTO `tb_article_comment` VALUES ('6a9e05c2-03cb-4c02-af09-a1f1c7bbb7ae', '4c2c4d79-b298-4394-a00d-cefb85748ea1', '0b72a8aa-a524-4f3e-92c7-641f1911af44');
INSERT INTO `tb_article_comment` VALUES ('726cdf42-7b67-4655-9431-ffd6543a53c7', '980829d0-d7a3-4a4e-9b86-d772bdfc2e0a', '862788c8-3a21-42d8-bf19-daa6ba5ad8c4');
INSERT INTO `tb_article_comment` VALUES ('7ccf98b3-c5d8-4cfc-87fe-e1ddb4e60ef2', '980829d0-d7a3-4a4e-9b86-d772bdfc2e0a', '113d86e6-37f6-436c-9445-b58a2dbbe718');
INSERT INTO `tb_article_comment` VALUES ('7f3d8dca-232f-4ce4-89a8-d0e6dafa2302', '4c2c4d79-b298-4394-a00d-cefb85748ea1', 'a0d9b182-24a1-4547-bf82-dfdbf1dcc0fc');
INSERT INTO `tb_article_comment` VALUES ('93d639eb-a4f5-4efd-a063-aac4d633ac7e', '980829d0-d7a3-4a4e-9b86-d772bdfc2e0a', 'e82ea2ca-5d7c-4f83-bac9-8056cb8a4c61');
INSERT INTO `tb_article_comment` VALUES ('9e71beb2-423b-47c7-b265-dbea13c4dd5d', '980829d0-d7a3-4a4e-9b86-d772bdfc2e0a', '71484aba-659c-4970-abba-7c6f463c2b07');
INSERT INTO `tb_article_comment` VALUES ('b124a810-5294-4821-97f2-46a18447c529', '980829d0-d7a3-4a4e-9b86-d772bdfc2e0a', 'b355a604-804a-4682-ad37-f84d7a5e02f4');
INSERT INTO `tb_article_comment` VALUES ('b6aef8cd-8687-4e0f-b600-707784fe49fb', '980829d0-d7a3-4a4e-9b86-d772bdfc2e0a', 'fe13fc3c-5214-4954-a2ab-9d67cc7a5da0');
INSERT INTO `tb_article_comment` VALUES ('b9465493-dcea-4532-9914-6a0dfc73aea8', '980829d0-d7a3-4a4e-9b86-d772bdfc2e0a', '1253591a-8165-4313-9e4d-dd23de58dcfb');
INSERT INTO `tb_article_comment` VALUES ('b9a39fa7-f5f8-4887-86b4-e09f306e9e60', '980829d0-d7a3-4a4e-9b86-d772bdfc2e0a', '042369fe-7b36-4e27-8ac8-33f5544f3f08');
INSERT INTO `tb_article_comment` VALUES ('ba258bce-692b-4b94-a5c9-4246251aa9cd', '980829d0-d7a3-4a4e-9b86-d772bdfc2e0a', '6b6fb430-e4ff-4722-9985-9a3c85d0a55b');
INSERT INTO `tb_article_comment` VALUES ('c1d67056-9d6f-47eb-b596-e555ac83749e', '980829d0-d7a3-4a4e-9b86-d772bdfc2e0a', '02ca57d5-f251-4f30-aceb-0ef151b39857');
INSERT INTO `tb_article_comment` VALUES ('d2527bf1-2b4e-4859-9f47-d84bb09a67d1', '980829d0-d7a3-4a4e-9b86-d772bdfc2e0a', '841a79e7-6567-4f9b-bac4-87d42920d161');
INSERT INTO `tb_article_comment` VALUES ('d7f842a4-f5a4-4a50-81c0-53589ade9523', '980829d0-d7a3-4a4e-9b86-d772bdfc2e0a', 'ad5f013a-527d-4a39-8c5c-510fa88c4557');
INSERT INTO `tb_article_comment` VALUES ('dfe3b4b4-a697-4e6f-903e-63d5f10a119e', '980829d0-d7a3-4a4e-9b86-d772bdfc2e0a', 'ac162ce3-0cb5-4ba0-9799-12bce4fd72a1');
INSERT INTO `tb_article_comment` VALUES ('e8db6186-4326-482d-88df-280927811cff', '980829d0-d7a3-4a4e-9b86-d772bdfc2e0a', 'e0abdcfb-435b-4598-992b-afe7a02f7fe7');

-- ----------------------------
-- Table structure for tb_article_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_article_type`;
CREATE TABLE `tb_article_type` (
  `ARTICLE_TYPE_ID` varchar(64) NOT NULL COMMENT '主键id',
  `ARTICLE_ID` varchar(64) DEFAULT NULL COMMENT '文章id',
  `TYPE_ID` varchar(64) DEFAULT NULL COMMENT '类型id',
  PRIMARY KEY (`ARTICLE_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_article_type
-- ----------------------------
INSERT INTO `tb_article_type` VALUES ('30090553-899b-4177-8ec9-f5e129416101', '9d4721f5-a1bd-4fc6-80bd-8243776e5831', '3f0e2c10-123f-4a00-9a86-217abde3caeb');
INSERT INTO `tb_article_type` VALUES ('4644a6e6-836c-4671-939f-7f2f4c53c434', '026d007d-bf29-4f25-9597-bb7cea81036f', '63be83aa-27eb-4f14-a34c-71fb0bee5f5d');
INSERT INTO `tb_article_type` VALUES ('61022a0b-b106-44b8-84ad-ffea783f7820', 'b0b3698b-8192-4d82-be9e-1c72f6fe2cc6', '3f0e2c10-123f-4a00-9a86-217abde3caeb');
INSERT INTO `tb_article_type` VALUES ('6f72279c-2d34-40fd-82ec-7e401dc1d964', '980829d0-d7a3-4a4e-9b86-d772bdfc2e0a', '3f0e2c10-123f-4a00-9a86-217abde3caeb');
INSERT INTO `tb_article_type` VALUES ('7994f801-2fb4-4251-8794-a90306ec63cb', 'e70600b6-a867-41fb-884b-55d7603977f7', '63be83aa-27eb-4f14-a34c-71fb0bee5f5d');
INSERT INTO `tb_article_type` VALUES ('8f93d4d5-097f-423c-aecc-96560aaf1ca9', 'eba93722-aef0-4986-89ae-a9541f3eab8c', '3f0e2c10-123f-4a00-9a86-217abde3caeb');
INSERT INTO `tb_article_type` VALUES ('f5186444-f2f7-4fc5-a454-393e52ea2d31', '4c2c4d79-b298-4394-a00d-cefb85748ea1', '63be83aa-27eb-4f14-a34c-71fb0bee5f5d');
INSERT INTO `tb_article_type` VALUES ('f69cf62f-bbdf-4383-bfc0-89ac0f3d4729', 'edc3bd8f-5647-4444-8070-07bfac0407e8', '3f0e2c10-123f-4a00-9a86-217abde3caeb');

-- ----------------------------
-- Table structure for tb_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment` (
  `COMMENT_SID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `COMMENT_ID` varchar(64) NOT NULL COMMENT '评论id',
  `COMMENT_USER` varchar(255) DEFAULT NULL COMMENT '评论人',
  `COMMENT_USER_WEBSITE` varchar(255) DEFAULT NULL COMMENT '评论人网站',
  `COMMENT_EMAIL` varchar(255) DEFAULT NULL COMMENT '评论人邮箱',
  `COMMENT_BODY` longtext COMMENT '评论内容',
  `COMMENT_POINT` int(2) DEFAULT NULL COMMENT '评分 最高5分',
  `COMMENT_IS_REPLY` tinyint(4) DEFAULT '0' COMMENT '是否是回复评论',
  `COMMENT_REPLY_USER` varchar(255) DEFAULT NULL COMMENT '被回复的评论人',
  `COMMENT_REPLY_BODY` varchar(255) DEFAULT NULL COMMENT '被回复的评论内容',
  `CREATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `COMMENT_BACKUP1` varchar(255) DEFAULT NULL COMMENT '评论预留字段1',
  `COMMENT_BACKUP2` varchar(255) DEFAULT NULL COMMENT '评论预留字段2',
  `COMMENT_BACKUP3` varchar(255) DEFAULT NULL COMMENT '评论预留字段3',
  PRIMARY KEY (`COMMENT_SID`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_comment
-- ----------------------------
INSERT INTO `tb_comment` VALUES ('36', '93bf466c-4fad-4cf6-b787-b3a9a6bc1a3e', 'test', '', '', '&lt;script&gt;<br/> alert(&quot;1&quot;);<br/>&lt;/script&gt;', null, '0', null, null, '2016-02-03 15:13:56', '2016-02-03 15:13:56', null, null, null);
INSERT INTO `tb_comment` VALUES ('37', 'e0abdcfb-435b-4598-992b-afe7a02f7fe7', 'ddd', '', '', '&lt;script&gt;<br/> alert(&quot;2&quot;);<br/>&lt;/script&gt;', null, '1', 'test', '&lt;script&gt;<br/> alert(&quot;1&quot;);<br/>&lt;/script&gt;', '2016-02-03 15:14:22', '2016-02-03 15:14:22', null, null, null);
INSERT INTO `tb_comment` VALUES ('38', 'cc77cf41-d2a5-4727-8b06-42309ee253ee', '搜索', 'www.baidu.com', '', '呵呵<br/>哈哈', null, '1', 'ddd', '&lt;script&gt;<br/> alert(&quot;2&quot;);<br/>&lt;/script&gt;', '2016-02-03 15:35:07', '2016-02-03 15:35:07', null, null, null);
INSERT INTO `tb_comment` VALUES ('39', '1253591a-8165-4313-9e4d-dd23de58dcfb', 'dd', '', '', 'public void say(){<br/>  sysout(&quot;1&quot;);<br/>}', null, '0', null, null, '2016-02-03 15:37:41', '2016-02-03 15:37:41', null, null, null);
INSERT INTO `tb_comment` VALUES ('40', '8bd64769-13c0-43a2-9124-fc0331936bea', 'dd', '', '', '&amp;lt;script&amp;gt;&lt;br/&gt; alert(&amp;quot;1&amp;quot;);&lt;br/&gt;&amp;lt;/script&amp;gt;', null, '0', null, null, '2016-02-03 15:38:58', '2016-02-03 15:38:58', null, null, null);
INSERT INTO `tb_comment` VALUES ('41', '1cca400a-e6b0-45df-9a48-be907e4cf0af', '方法', 'http://www.baidu.com', '', '大师大师的', null, '0', null, null, '2016-02-03 16:02:31', '2016-02-03 16:02:31', null, null, null);
INSERT INTO `tb_comment` VALUES ('42', '63cdd0bb-5ed9-436b-bee9-51e55b31b1e2', 'adam', 'http://www.adamzhou.cn', '', 'sadf', null, '0', null, null, '2016-02-03 16:30:06', '2016-02-03 16:30:06', null, null, null);
INSERT INTO `tb_comment` VALUES ('43', 'fe13fc3c-5214-4954-a2ab-9d67cc7a5da0', '案说法', '', '', '就是<br/>达到', null, '1', '搜索', '呵呵<br/>哈哈', '2016-02-04 09:04:17', '2016-02-04 09:04:17', null, null, null);
INSERT INTO `tb_comment` VALUES ('44', 'ef5a13d8-f8b4-4f24-bcfa-701b73cc2031', '111', '', '', '撒旦', null, '0', null, null, '2016-02-04 11:00:18', '2016-02-04 11:00:18', null, null, null);
INSERT INTO `tb_comment` VALUES ('45', '399ac0c9-6a04-40bf-9602-0fb17b65f3e3', '飞', '', '', '埃索达', null, '0', null, null, '2016-02-04 11:00:56', '2016-02-04 11:00:56', null, null, null);
INSERT INTO `tb_comment` VALUES ('46', 'ac162ce3-0cb5-4ba0-9799-12bce4fd72a1', '飞', '', '', '埃索达', null, '0', null, null, '2016-02-04 11:02:13', '2016-02-04 11:02:13', null, null, null);
INSERT INTO `tb_comment` VALUES ('47', '042369fe-7b36-4e27-8ac8-33f5544f3f08', '飞', '', '', '奥法', null, '0', null, null, '2016-02-04 11:03:57', '2016-02-04 11:03:57', null, null, null);
INSERT INTO `tb_comment` VALUES ('48', '02ca57d5-f251-4f30-aceb-0ef151b39857', '想', '', '', '奥法', null, '0', null, null, '2016-02-04 11:04:56', '2016-02-04 11:04:56', null, null, null);
INSERT INTO `tb_comment` VALUES ('49', '841a79e7-6567-4f9b-bac4-87d42920d161', '方法', '', '', '不错', null, '1', 'dd', 'public void say(){<br/>  sysout(&quot;1&quot;);<br/>}', '2016-02-04 11:05:17', '2016-02-04 11:05:17', null, null, null);
INSERT INTO `tb_comment` VALUES ('50', 'b355a604-804a-4682-ad37-f84d7a5e02f4', '啊', '', '', '呵呵哈哈', null, '0', null, null, '2016-02-04 11:06:52', '2016-02-04 11:06:52', null, null, null);
INSERT INTO `tb_comment` VALUES ('51', 'ad5f013a-527d-4a39-8c5c-510fa88c4557', '飞', '', '', '埃索达', null, '1', '啊', '呵呵哈哈', '2016-02-04 11:07:52', '2016-02-04 11:07:52', null, null, null);
INSERT INTO `tb_comment` VALUES ('52', '6b6fb430-e4ff-4722-9985-9a3c85d0a55b', '请问', '', '', '奥法', null, '0', null, null, '2016-02-04 11:11:04', '2016-02-04 11:11:04', null, null, null);
INSERT INTO `tb_comment` VALUES ('53', 'e82ea2ca-5d7c-4f83-bac9-8056cb8a4c61', '飞', 'http://www.163.com', '', '奥法', null, '0', null, null, '2016-02-04 11:12:01', '2016-02-04 11:12:01', null, null, null);
INSERT INTO `tb_comment` VALUES ('54', '0cf823c5-5e2f-479f-967a-f7ef28b1c0b3', 'asf', '', '', 'asfa', null, '1', '飞', '奥法', '2016-02-04 11:32:11', '2016-02-04 11:32:11', null, null, null);
INSERT INTO `tb_comment` VALUES ('55', '113d86e6-37f6-436c-9445-b58a2dbbe718', '飞', '', '', '不错', null, '1', 'test', '&lt;script&gt;<br/> alert(&quot;1&quot;);<br/>&lt;/script&gt;', '2016-02-04 11:32:30', '2016-02-04 11:32:30', null, null, null);
INSERT INTO `tb_comment` VALUES ('56', '862788c8-3a21-42d8-bf19-daa6ba5ad8c4', 'addsd', '', '', '啊沙发沙发', null, '0', null, null, '2016-02-04 14:08:54', '2016-02-04 14:08:54', null, null, null);
INSERT INTO `tb_comment` VALUES ('57', '13f838e4-c399-45a6-97e9-208dd90d057a', 'sad', '', '', 'asfasf', null, '0', null, null, '2016-02-04 14:11:21', '2016-02-04 14:11:21', null, null, null);
INSERT INTO `tb_comment` VALUES ('58', '71484aba-659c-4970-abba-7c6f463c2b07', '发哈', '', '', '恩', null, '1', '案说法', '就是<br/>达到', '2016-02-04 14:11:39', '2016-02-04 14:11:39', null, null, null);
INSERT INTO `tb_comment` VALUES ('59', '9ce25fa7-455b-48a1-94c6-05c9734b2da6', '啊发顺丰', 'http://www.baidu.com', '', '啊沙发沙发', null, '0', null, null, '2016-02-04 14:13:28', '2016-02-04 14:13:28', null, null, null);
INSERT INTO `tb_comment` VALUES ('60', '0b72a8aa-a524-4f3e-92c7-641f1911af44', '呵呵', '', '', '不错哈哈<br/>&lt;script&gt;<br/> alert(&quot;1&quot;)<br/>&lt;/script&gt;', null, '0', null, null, '2016-02-04 14:27:27', '2016-02-04 14:27:27', null, null, null);
INSERT INTO `tb_comment` VALUES ('61', 'a0d9b182-24a1-4547-bf82-dfdbf1dcc0fc', '大哥', 'http://www.baidu.com', '', '恩恩', null, '1', '呵呵', '不错哈哈<br/>&lt;script&gt;<br/> alert(&quot;1&quot;)<br/>&lt;/script&gt;', '2016-02-04 14:37:16', '2016-02-04 14:37:16', null, null, null);
INSERT INTO `tb_comment` VALUES ('62', 'ee86a65c-f5c4-4f8c-aaa7-0a4c1181ece7', '小张', '', '', '不错', null, '0', null, null, '2016-03-16 10:33:43', '2016-03-16 10:33:43', null, null, null);
INSERT INTO `tb_comment` VALUES ('63', '09e9eb1f-1fa8-46af-b680-a3ba1116740b', '萨达', '', 'sad@qq.com', '<p><img alt=\"angry\" height=\"23\" src=\"http://localhost:8888/blogs/commentEditor/plugins/smiley/images/angry_smile.png\" title=\"angry\" width=\"23\" /></p>', null, '0', null, null, '2016-07-04 09:29:00', '2016-07-04 09:29:00', null, null, null);

-- ----------------------------
-- Table structure for tb_series
-- ----------------------------
DROP TABLE IF EXISTS `tb_series`;
CREATE TABLE `tb_series` (
  `SERIES_SID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `SERIES_NAME` varchar(128) DEFAULT NULL COMMENT '专题名称',
  `SERIES_ID` varchar(128) DEFAULT NULL COMMENT '专题id',
  `CREATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `UPDATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`SERIES_SID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_series
-- ----------------------------

-- ----------------------------
-- Table structure for tb_series_article
-- ----------------------------
DROP TABLE IF EXISTS `tb_series_article`;
CREATE TABLE `tb_series_article` (
  `ARTICLE_SERIES_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `SERIES_ID` varchar(128) DEFAULT NULL COMMENT '专题id',
  `ARTICLE_ID` varchar(128) DEFAULT NULL COMMENT '文章Id',
  PRIMARY KEY (`ARTICLE_SERIES_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_series_article
-- ----------------------------

-- ----------------------------
-- Table structure for tb_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_type`;
CREATE TABLE `tb_type` (
  `TYPE_SID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `TYPE_ID` varchar(64) NOT NULL COMMENT '类型id',
  `TYPE_NAME` varchar(32) DEFAULT NULL COMMENT '类型名称',
  `TYPE_PARENT_ID` varchar(64) DEFAULT NULL COMMENT '父元素id',
  PRIMARY KEY (`TYPE_SID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_type
-- ----------------------------
INSERT INTO `tb_type` VALUES ('15', '4666a3ac-7609-4a84-932e-5a22960a8612', '数据库', null);
INSERT INTO `tb_type` VALUES ('16', '3f0e2c10-123f-4a00-9a86-217abde3caeb', '网页', null);
INSERT INTO `tb_type` VALUES ('17', '63be83aa-27eb-4f14-a34c-71fb0bee5f5d', 'mysql', '4666a3ac-7609-4a84-932e-5a22960a8612');
INSERT INTO `tb_type` VALUES ('18', '7a724051-07d8-4293-a816-bc3908e14caf', '个人心得', null);
