/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50627
Source Host           : localhost:3306
Source Database       : bbs_forum

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2017-04-06 21:53:05
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

-- ----------------------------
-- Table structure for `post`
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `id` char(50) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` varchar(255) NOT NULL,
  `publisher_mail` char(20) NOT NULL,
  `publish_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `childboard_id` int(11) NOT NULL,
  `page_view` int(11) NOT NULL,
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
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '注册时间',
  `photo_url` varchar(40) DEFAULT 'upload/headicon/default_icon.jpg' COMMENT '用于存放用户头像的本地文件地址，需要在注册时指定为默认的头像地址',
  `user_type` int(11) DEFAULT '0' COMMENT '为int类型，0代表普通用户，1代表被赋予了管理员权限',
  `level` int(11) DEFAULT '1',
  `type` int(1) DEFAULT '0' COMMENT '用户类型，0代表一般用户，1代表被管理员',
  `signature` varchar(255) DEFAULT '还未设置个人签名。' COMMENT '个人签名',
  PRIMARY KEY (`mail_address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1111', 'Headbanger', '123456', '男', '2017-04-04 16:48:45', 'upload/headicon/default_icon.jpg', '0', '1', '0', 'NO PAIN,NO GAIN.');
