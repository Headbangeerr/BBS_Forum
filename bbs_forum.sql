/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : bbs_forum

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2017-06-01 22:29:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `board`
-- ----------------------------
DROP TABLE IF EXISTS `board`;
CREATE TABLE `board` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(10) NOT NULL,
  `info` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of board
-- ----------------------------
INSERT INTO `board` VALUES ('1', '移动开发', '');
INSERT INTO `board` VALUES ('2', 'WEB开发', '');
INSERT INTO `board` VALUES ('3', '游戏开发', '');

-- ----------------------------
-- Table structure for `childboard`
-- ----------------------------
DROP TABLE IF EXISTS `childboard`;
CREATE TABLE `childboard` (
  `id` int(11) NOT NULL,
  `name` char(10) NOT NULL,
  `parent_board` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `parent_board` (`parent_board`),
  CONSTRAINT `childboard_ibfk_1` FOREIGN KEY (`parent_board`) REFERENCES `board` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of childboard
-- ----------------------------
INSERT INTO `childboard` VALUES ('4', 'ASP开发', '2');
INSERT INTO `childboard` VALUES ('5', 'JSP开发', '2');
INSERT INTO `childboard` VALUES ('6', 'IOS开发', '1');
INSERT INTO `childboard` VALUES ('7', 'Android开发', '1');
INSERT INTO `childboard` VALUES ('8', 'Construct2', '3');
INSERT INTO `childboard` VALUES ('9', 'GameMaker', '3');
INSERT INTO `childboard` VALUES ('10', 'Styncyl', '3');
INSERT INTO `childboard` VALUES ('11', 'GameSalad', '3');
INSERT INTO `childboard` VALUES ('12', 'LiveCode', '3');

-- ----------------------------
-- Table structure for `friends`
-- ----------------------------
DROP TABLE IF EXISTS `friends`;
CREATE TABLE `friends` (
  `user_mail` char(20) NOT NULL,
  `friend_mail` char(20) NOT NULL,
  PRIMARY KEY (`user_mail`,`friend_mail`),
  KEY `friend_mail` (`friend_mail`),
  CONSTRAINT `friends_ibfk_1` FOREIGN KEY (`user_mail`) REFERENCES `user` (`mail_address`),
  CONSTRAINT `friends_ibfk_2` FOREIGN KEY (`friend_mail`) REFERENCES `user` (`mail_address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friends
-- ----------------------------
INSERT INTO `friends` VALUES ('2222', '3333');
INSERT INTO `friends` VALUES ('0000', '4444');
INSERT INTO `friends` VALUES ('2222', '4444');
INSERT INTO `friends` VALUES ('0000', '5555');
INSERT INTO `friends` VALUES ('2222', '5555');
INSERT INTO `friends` VALUES ('0000', '6666');
INSERT INTO `friends` VALUES ('2222', '6666');
INSERT INTO `friends` VALUES ('2222', '7777');

-- ----------------------------
-- Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `publisher_mail` char(20) NOT NULL,
  `receiver_mail` char(20) NOT NULL,
  `content` varchar(255) NOT NULL,
  `publish_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `publisher_mail` (`publisher_mail`),
  KEY `reciver_mail` (`receiver_mail`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`publisher_mail`) REFERENCES `user` (`mail_address`),
  CONSTRAINT `message_ibfk_2` FOREIGN KEY (`receiver_mail`) REFERENCES `user` (`mail_address`)
) ENGINE=InnoDB AUTO_INCREMENT=164 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('141', '1111', '0000', '22222', '2017-05-07 20:43:35');
INSERT INTO `message` VALUES ('142', '1111', '0000', '33333', '2017-05-07 20:43:41');
INSERT INTO `message` VALUES ('143', '1111', '0000', '4444444', '2017-05-07 20:43:44');
INSERT INTO `message` VALUES ('144', '1111', '0000', '5555555', '2017-05-07 20:43:48');
INSERT INTO `message` VALUES ('145', '1111', '0000', '66666', '2017-05-07 20:43:50');
INSERT INTO `message` VALUES ('146', '1111', '0000', '777777', '2017-05-08 14:48:43');
INSERT INTO `message` VALUES ('147', '1111', '0000', '888888', '2017-05-08 16:14:01');
INSERT INTO `message` VALUES ('148', '1111', '0000', '9999999', '2017-05-08 17:10:57');
INSERT INTO `message` VALUES ('150', '1111', '0000', '34534534534', '2017-05-14 17:13:27');
INSERT INTO `message` VALUES ('151', '1111', '0000', '34534534534', '2017-05-14 17:13:32');
INSERT INTO `message` VALUES ('152', '1111', '0000', '34534534534', '2017-05-14 17:13:35');
INSERT INTO `message` VALUES ('153', '1111', '0000', '34534534534', '2017-05-14 17:13:35');
INSERT INTO `message` VALUES ('154', '1111', '0000', '34534534534', '2017-05-14 17:13:35');
INSERT INTO `message` VALUES ('155', '1111', '0000', '34534534534', '2017-05-14 17:13:35');
INSERT INTO `message` VALUES ('157', '1111', '0000', '2', '2017-05-14 17:17:26');
INSERT INTO `message` VALUES ('159', '1111', '0000', '234234324', '2017-05-14 17:23:14');
INSERT INTO `message` VALUES ('160', '1111', '0000', '234234324', '2017-05-14 17:24:28');
INSERT INTO `message` VALUES ('161', '1111', '0000', '77777', '2017-05-14 17:27:00');
INSERT INTO `message` VALUES ('162', '1111', '0000', '77777', '2017-05-19 15:23:39');
INSERT INTO `message` VALUES ('163', '0000', '1111', '他问他认为', '2017-05-27 17:56:32');

-- ----------------------------
-- Table structure for `news`
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` char(40) NOT NULL,
  `sender_mail` char(20) NOT NULL,
  `receiver_mail` char(20) NOT NULL,
  `send_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `content` varchar(255) NOT NULL DEFAULT 'CURRENT_TIMESTAMP',
  `type` char(5) DEFAULT 'n',
  `state` int(2) DEFAULT '0',
  PRIMARY KEY (`sender_mail`,`receiver_mail`,`id`),
  KEY `receiver_mail` (`receiver_mail`),
  CONSTRAINT `news_ibfk_1` FOREIGN KEY (`sender_mail`) REFERENCES `user` (`mail_address`),
  CONSTRAINT `news_ibfk_2` FOREIGN KEY (`receiver_mail`) REFERENCES `user` (`mail_address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES ('b17', '0000', '1111', '2017-06-01 19:13:49', '申请置顶，帖子id为：3', '0', '0');
INSERT INTO `news` VALUES ('b2', '0000', '1111', '2017-06-01 19:12:56', '申请置顶，帖子id为：2', '0', '0');
INSERT INTO `news` VALUES ('b22', '0000', '1111', '2017-06-01 19:15:34', '申请置顶，帖子id为：5', '0', '0');
INSERT INTO `news` VALUES ('b98', '0000', '1111', '2017-06-01 19:16:00', '申请置顶，帖子id为：6', '0', '0');
INSERT INTO `news` VALUES ('b22', '0000', '2222', '2017-06-01 19:15:34', '申请置顶，帖子id为：5', '0', '0');
INSERT INTO `news` VALUES ('b98', '0000', '2222', '2017-06-01 19:16:00', '申请置顶，帖子id为：6', '0', '0');
INSERT INTO `news` VALUES ('00001496317342974', '0000', '3333', null, '向您发送了好友请求', '1', '0');
INSERT INTO `news` VALUES ('b33', '2222', '1111', '2017-06-01 17:31:49', '申请置顶，帖子id为：a86', '0', '0');
INSERT INTO `news` VALUES ('b47', '2222', '1111', '2017-06-01 22:27:26', '申请置顶，帖子id为：a67', '0', '0');
INSERT INTO `news` VALUES ('b61', '2222', '1111', '2017-06-01 21:25:26', '申请置顶，帖子id为：a79', '0', '0');
INSERT INTO `news` VALUES ('b47', '2222', '2222', '2017-06-01 22:27:26', '申请置顶，帖子id为：a67', '0', '0');
INSERT INTO `news` VALUES ('b61', '2222', '2222', '2017-06-01 21:25:26', '申请置顶，帖子id为：a79', '0', '0');

-- ----------------------------
-- Table structure for `post`
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `id` char(50) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` varchar(255) NOT NULL,
  `publisher_mail` char(20) NOT NULL,
  `publish_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `childboard_id` int(11) NOT NULL,
  `page_view` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `publisher_mail` (`publisher_mail`),
  KEY `childboardId` (`childboard_id`),
  CONSTRAINT `post_ibfk_1` FOREIGN KEY (`publisher_mail`) REFERENCES `user` (`mail_address`),
  CONSTRAINT `post_ibfk_2` FOREIGN KEY (`childboard_id`) REFERENCES `childboard` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES ('1a', '士大夫', '阿房宫确认', '1111', '2017-05-10 08:39:03', '5', '0');
INSERT INTO `post` VALUES ('2', 'test', 'testtesetsetset', '0000', '2017-05-01 20:04:08', '4', '15');
INSERT INTO `post` VALUES ('3', 'test1', 'wertwerwerwer', '0000', '2017-05-08 16:33:23', '5', '34');
INSERT INTO `post` VALUES ('4', 'test2', '啊哈哈哈哈哈', '0000', '2017-05-08 16:33:36', '5', '33');
INSERT INTO `post` VALUES ('5', 'test3', 'hello', '0000', '2017-05-08 16:33:55', '4', '235');
INSERT INTO `post` VALUES ('6', 'test4', 'hhhhhhh', '0000', '2017-05-08 16:34:12', '5', '46');
INSERT INTO `post` VALUES ('7', 'test5', 'what?', '0000', '2017-05-08 16:34:36', '4', '22');
INSERT INTO `post` VALUES ('a2', '很八卦', 'JAIJROI0EWJREWR', '3333', '2017-05-29 10:00:33', '6', '3');
INSERT INTO `post` VALUES ('a30', 'rqwrwer', 'wqrwerwr', '3333', '2017-06-01 13:01:20', '7', '1');
INSERT INTO `post` VALUES ('a34', '太尴尬是否', '的风范古典风格vdfg', '0000', '2017-06-01 19:39:49', '7', '5');
INSERT INTO `post` VALUES ('a38', '是吗', '未全额提取我惹我', '3333', '2017-05-29 22:12:15', '4', '3');
INSERT INTO `post` VALUES ('a51', 'hugh', 'vfhfufu', '3333', '2017-05-29 09:56:55', '11', '0');
INSERT INTO `post` VALUES ('a55', '是吗', '未全额提取我惹我', '3333', '2017-05-29 22:10:35', '8', '7');
INSERT INTO `post` VALUES ('a65', '312312', '2342341341', '2222', '2017-05-26 09:47:52', '8', '0');
INSERT INTO `post` VALUES ('a67', '的身高大概', '各个', '2222', '2017-05-26 09:47:43', '4', '0');
INSERT INTO `post` VALUES ('a77', '价格和地方都是', '额外人特委托', '0000', '2017-06-01 19:40:08', '7', '12');
INSERT INTO `post` VALUES ('a81', '温热文人企鹅去', '表现出vjhtyjiyffhfgjfyurytyuyutgfudf', '3333', '2017-05-29 22:22:34', '11', '1');
INSERT INTO `post` VALUES ('a86', '很过分的话', '发的好女孩', '2222', '2017-05-12 09:46:00', '7', '0');
INSERT INTO `post` VALUES ('a88', '盛大公司发撒哥仨', '风格vdsfsfs', '2222', '2017-05-26 09:48:08', '9', '0');
INSERT INTO `post` VALUES ('a89', 'ewrtew', '额我去泰国拳王', '0000', '2017-05-29 15:29:58', '5', '4');
INSERT INTO `post` VALUES ('a90', 'we\'e\'w\'r', 'weewr温热微软', '3333', '2017-06-01 13:02:49', '8', '3');
INSERT INTO `post` VALUES ('a91', 'ewrtew', '去温热微软', '0000', '2017-05-29 15:30:39', '4', '4');
INSERT INTO `post` VALUES ('a96', 'fshdfas', 'fsfasfsf', '0000', '2017-06-01 14:47:30', '4', '8');

-- ----------------------------
-- Table structure for `reply`
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sender_mail` char(20) NOT NULL,
  `sendtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `content` varchar(255) NOT NULL,
  `postId` char(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `sender_mail` (`sender_mail`),
  CONSTRAINT `reply_ibfk_1` FOREIGN KEY (`sender_mail`) REFERENCES `user` (`mail_address`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reply
-- ----------------------------
INSERT INTO `reply` VALUES ('1', '0000', '2017-05-17 17:46:58', '我却认为人', '6');
INSERT INTO `reply` VALUES ('3', '0000', '2017-05-27 22:29:40', '很不错', 'a88');
INSERT INTO `reply` VALUES ('4', '0000', '2017-05-27 23:44:55', '游戏很好玩', 'a88');
INSERT INTO `reply` VALUES ('7', '3333', '2017-05-27 23:46:10', '厉害的', 'a88');
INSERT INTO `reply` VALUES ('8', '4444', '2017-05-27 23:46:38', '你吃了吗\r\n', 'a88');
INSERT INTO `reply` VALUES ('9', '4444', '2017-05-27 23:59:16', 'wertrewttewr', '1a');
INSERT INTO `reply` VALUES ('10', '4444', '2017-05-28 00:01:04', '很棒啊\r\n', 'a88');
INSERT INTO `reply` VALUES ('11', '4444', '2017-05-28 00:01:15', '啊撒飞洒地方', 'a88');
INSERT INTO `reply` VALUES ('12', '2222', '2017-05-28 16:01:13', 'twertert', 'a88');
INSERT INTO `reply` VALUES ('13', '2222', '2017-05-28 17:48:15', '3123124', 'a88');
INSERT INTO `reply` VALUES ('14', '2222', '2017-05-28 23:50:13', 'qwerqwe', 'a88');
INSERT INTO `reply` VALUES ('15', '2222', '2017-05-28 23:50:17', 'rqwer', '1');
INSERT INTO `reply` VALUES ('16', '3333', '2017-05-29 11:55:49', '复旦皇冠高分的回复', 'a88');
INSERT INTO `reply` VALUES ('17', '3333', '2017-05-29 11:57:30', '的促销幅度萨芬士大夫', 'a88');
INSERT INTO `reply` VALUES ('18', '3333', '2017-05-29 11:58:37', '地方活动官方回复的', 'a88');
INSERT INTO `reply` VALUES ('19', '3333', '2017-05-29 12:02:17', '的防火防盗更换', 'a88');
INSERT INTO `reply` VALUES ('20', '3333', '2017-05-29 12:02:41', '大话官方的 ', 'a88');
INSERT INTO `reply` VALUES ('21', '3333', '2017-05-29 12:02:51', '地方好地方好地方', 'a88');
INSERT INTO `reply` VALUES ('22', '3333', '2017-05-29 12:03:44', '岁的法国', 'a88');
INSERT INTO `reply` VALUES ('23', '3333', '2017-05-29 12:04:04', '十多个', 'a88');
INSERT INTO `reply` VALUES ('24', '3333', '2017-05-29 12:04:15', '说的话都是梵高', 'a88');
INSERT INTO `reply` VALUES ('25', '3333', '2017-05-29 12:04:19', '收到贵司的', 'a88');
INSERT INTO `reply` VALUES ('26', '3333', '2017-05-29 12:04:21', '速度跟得上时代', 'a88');
INSERT INTO `reply` VALUES ('27', '3333', '2017-05-29 12:04:24', '说的话都是对方', 'a88');
INSERT INTO `reply` VALUES ('28', '3333', '2017-05-29 12:04:35', '电视大赛决赛的', 'a88');
INSERT INTO `reply` VALUES ('29', '3333', '2017-05-29 12:04:39', '死的活该单身但是', 'a88');
INSERT INTO `reply` VALUES ('30', '0000', '2017-05-29 15:12:49', 'dsafadsf', '1');
INSERT INTO `reply` VALUES ('31', '0000', '2017-05-29 15:13:04', '撒旦发射点发射点', '1');
INSERT INTO `reply` VALUES ('32', '0000', '2017-05-29 15:14:05', '你好啊吃饭了吗', '1');
INSERT INTO `reply` VALUES ('33', '0000', '2017-05-29 15:19:06', '你好啊吃饭了吗', '1');
INSERT INTO `reply` VALUES ('34', '0000', '2017-05-29 15:22:56', '航天事业', 'a2');
INSERT INTO `reply` VALUES ('35', '0000', '2017-05-29 15:22:56', '热舞图文', 'a2');
INSERT INTO `reply` VALUES ('36', '0000', '2017-05-29 15:25:25', '啊的说法', 'a2');
INSERT INTO `reply` VALUES ('37', '0000', '2017-05-29 15:25:25', '鄂我热我热', 'a2');
INSERT INTO `reply` VALUES ('39', '2222', '2017-05-09 15:29:13', '感到反感', '1');
INSERT INTO `reply` VALUES ('40', '0000', '2017-05-29 15:37:06', '请输入回帖内容', 'a91');
INSERT INTO `reply` VALUES ('41', '0000', '2017-05-29 15:51:44', 'rqwerwqer', 'a89');
INSERT INTO `reply` VALUES ('42', '0000', '2017-05-29 15:52:21', '很棒', 'a89');
INSERT INTO `reply` VALUES ('43', '0000', '2017-05-29 15:54:19', '太好了哈哈', 'a89');
INSERT INTO `reply` VALUES ('44', '0000', '2017-05-29 15:56:49', '很不错啊', 'a89');
INSERT INTO `reply` VALUES ('45', '0000', '2017-05-29 15:57:39', '多少个发；路口片刻迫人破ip哦porkiewporkiwepowekrpo人为', 'a89');
INSERT INTO `reply` VALUES ('46', '0000', '2017-05-29 15:57:48', '恨不得', 'a89');
INSERT INTO `reply` VALUES ('50', '2222', '2017-05-30 15:18:12', '人权问题更大', '1');
INSERT INTO `reply` VALUES ('55', '0000', '2017-06-01 19:38:37', 'gafsafsdfas', 'a55');
INSERT INTO `reply` VALUES ('56', '0000', '2017-06-01 19:38:52', '二分五分而我特瑞特热他格瑞特人', 'a55');
INSERT INTO `reply` VALUES ('57', '0000', '2017-06-01 19:38:57', '的撒发射点法', 'a55');
INSERT INTO `reply` VALUES ('58', '0000', '2017-06-01 19:39:02', '萨芬士大夫', 'a55');
INSERT INTO `reply` VALUES ('59', '2222', '2017-06-01 21:17:10', '123123', 'a77');
INSERT INTO `reply` VALUES ('60', '2222', '2017-06-01 21:17:11', '123123', 'a77');
INSERT INTO `reply` VALUES ('61', '2222', '2017-06-01 21:17:12', '123123', 'a77');
INSERT INTO `reply` VALUES ('62', '2222', '2017-06-01 21:17:12', '123123', 'a77');
INSERT INTO `reply` VALUES ('63', '2222', '2017-06-01 21:17:13', '123123', 'a77');
INSERT INTO `reply` VALUES ('64', '2222', '2017-06-01 21:17:14', '123123', 'a77');
INSERT INTO `reply` VALUES ('65', '2222', '2017-06-01 21:17:20', '123123', 'a77');
INSERT INTO `reply` VALUES ('66', '2222', '2017-06-01 21:18:00', 'EW', 'a96');
INSERT INTO `reply` VALUES ('67', '2222', '2017-06-01 22:28:12', 'eqrqweqwe', 'a77');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `mail_address` char(20) NOT NULL COMMENT '用户邮箱地址，用于用户注册登录',
  `username` varchar(50) NOT NULL,
  `password` char(20) NOT NULL,
  `sex` char(2) NOT NULL DEFAULT '男',
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `photo_url` varchar(40) DEFAULT 'upload/headicon/default_icon.jpg' COMMENT '用于存放用户头像的本地文件地址，需要在注册时指定为默认的头像地址',
  `level` int(11) DEFAULT '1',
  `signature` varchar(255) DEFAULT '还未设置个人签名。' COMMENT '个人签名',
  `type` int(1) DEFAULT '0',
  PRIMARY KEY (`mail_address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('0000', 'user001', '1111', '女', '2017-05-01 16:04:09', 'upload/headicon/default_icon.jpg', '1', '还未设置个人签名。', '0');
INSERT INTO `user` VALUES ('1111', 'Headbanger', '123456', '男', '2017-04-28 14:26:01', 'upload/headicon/default_icon.jpg', '1', 'NO PAIN,NO GAIN.', '1');
INSERT INTO `user` VALUES ('2222', 'user002', '1111', '男', '2017-05-11 09:29:41', 'upload/headicon/default_icon.jpg', '1', '还未设置个人签名。', '1');
INSERT INTO `user` VALUES ('3333', 'user003', '1111', '男', '2017-05-11 09:29:50', 'upload/headicon/default_icon.jpg', '1', '还未设置个人签名。', '0');
INSERT INTO `user` VALUES ('4444', 'user004', '1111', '男', '2017-05-11 09:29:59', 'upload/headicon/default_icon.jpg', '2', '还未设置个人签名。', '0');
INSERT INTO `user` VALUES ('5555', 'user005', '1111', '男', '2017-05-11 09:30:11', 'upload/headicon/default_icon.jpg', '2', '还未设置个人签名。', '0');
INSERT INTO `user` VALUES ('6666', 'user006', '1111', '男', '2017-05-11 09:30:19', 'upload/headicon/default_icon.jpg', '1', '还未设置个人签名。', '0');
INSERT INTO `user` VALUES ('7777', 'muyizifan', '1111', '男', '2017-05-07 23:09:07', 'upload/headicon/default_icon.jpg', '3', 'Focus', '2');
