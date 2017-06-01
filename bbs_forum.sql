/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50627
Source Host           : localhost:3306
Source Database       : bbs_forum

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2017-05-25 19:12:37
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
INSERT INTO `friends` VALUES ('0000', '2222');
INSERT INTO `friends` VALUES ('0000', '3333');
INSERT INTO `friends` VALUES ('0000', '4444');
INSERT INTO `friends` VALUES ('0000', '5555');
INSERT INTO `friends` VALUES ('0000', '6666');

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
) ENGINE=InnoDB AUTO_INCREMENT=163 DEFAULT CHARSET=utf8;

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

-- ----------------------------
-- Table structure for `news`
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `sender_mail` char(20) NOT NULL,
  `receiver_mail` char(20) NOT NULL,
  `send_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `content` varchar(255) NOT NULL DEFAULT 'CURRENT_TIMESTAMP',
  `type` char(5) DEFAULT 'n',
  `state` int(2) DEFAULT '0',
  PRIMARY KEY (`sender_mail`,`receiver_mail`),
  KEY `receiver_mail` (`receiver_mail`),
  CONSTRAINT `news_ibfk_1` FOREIGN KEY (`sender_mail`) REFERENCES `user` (`mail_address`),
  CONSTRAINT `news_ibfk_2` FOREIGN KEY (`receiver_mail`) REFERENCES `user` (`mail_address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news
-- ----------------------------

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
INSERT INTO `post` VALUES ('1', '13243', '242342', '1111', '2017-04-04 10:20:53', '4', '234');
INSERT INTO `post` VALUES ('2', 'test', 'testtesetsetset', '0000', '2017-05-01 20:04:08', '4', '12');
INSERT INTO `post` VALUES ('3', 'test1', 'wertwerwerwer', '0000', '2017-05-08 16:33:23', '5', '34');
INSERT INTO `post` VALUES ('4', 'test2', '啊哈哈哈哈哈', '0000', '2017-05-08 16:33:36', '5', '33');
INSERT INTO `post` VALUES ('5', 'test3', 'hello', '0000', '2017-05-08 16:33:55', '4', '234');
INSERT INTO `post` VALUES ('6', 'test4', 'hhhhhhh', '0000', '2017-05-08 16:34:12', '5', '44');
INSERT INTO `post` VALUES ('7', 'test5', 'what?', '0000', '2017-05-08 16:34:36', '4', '22');

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reply
-- ----------------------------

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
INSERT INTO `user` VALUES ('1111', 'Headbanger', '123456', '男', '2017-04-28 14:26:01', 'upload/headicon/default_icon.jpg', '1', 'NO PAIN,NO GAIN.', '0');
INSERT INTO `user` VALUES ('2222', 'user002', '1111', '男', '2017-05-11 09:29:41', 'upload/headicon/default_icon.jpg', '1', '还未设置个人签名。', '0');
INSERT INTO `user` VALUES ('3333', 'user003', '1111', '男', '2017-05-11 09:29:50', 'upload/headicon/default_icon.jpg', '1', '还未设置个人签名。', '0');
INSERT INTO `user` VALUES ('4444', 'user004', '1111', '男', '2017-05-11 09:29:59', 'upload/headicon/default_icon.jpg', '1', '还未设置个人签名。', '0');
INSERT INTO `user` VALUES ('5555', 'user005', '1111', '男', '2017-05-11 09:30:11', 'upload/headicon/default_icon.jpg', '1', '还未设置个人签名。', '0');
INSERT INTO `user` VALUES ('6666', 'user006', '1111', '男', '2017-05-11 09:30:19', 'upload/headicon/default_icon.jpg', '1', '还未设置个人签名。', '0');
