/*
Navicat MySQL Data Transfer

Source Server         : localhost_2333
Source Server Version : 80012
Source Host           : localhost:2333
Source Database       : youjidatabase

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2019-01-02 20:22:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for collection
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection` (
  `collection_id` int(11) NOT NULL AUTO_INCREMENT,
  `collection_user_id` int(11) NOT NULL,
  `collection_dynamic_id` int(11) NOT NULL,
  PRIMARY KEY (`collection_id`,`collection_user_id`,`collection_dynamic_id`),
  KEY `collection_user_id` (`collection_user_id`),
  KEY `collection_dynamic_id` (`collection_dynamic_id`),
  CONSTRAINT `collection_ibfk_1` FOREIGN KEY (`collection_user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `collection_ibfk_2` FOREIGN KEY (`collection_dynamic_id`) REFERENCES `dynamic` (`dynamic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collection
-- ----------------------------

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_text` text,
  `comment_time` datetime DEFAULT NULL,
  `comment_dynamic_id` int(11) DEFAULT NULL,
  `comment_like_num` int(11) DEFAULT NULL,
  `comment_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `comment_dynamic_id` (`comment_dynamic_id`),
  KEY `comment_user_id` (`comment_user_id`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`comment_dynamic_id`) REFERENCES `dynamic` (`dynamic_id`),
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`comment_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('13', '有趣', '2018-12-30 06:38:09', '98', '0', '14');
INSERT INTO `comment` VALUES ('14', '测试评论', '2019-01-02 02:12:03', '99', '0', '14');
INSERT INTO `comment` VALUES ('15', '演示评论', '2019-01-02 07:01:36', '99', '0', '14');

-- ----------------------------
-- Table structure for dynamic
-- ----------------------------
DROP TABLE IF EXISTS `dynamic`;
CREATE TABLE `dynamic` (
  `dynamic_id` int(11) NOT NULL AUTO_INCREMENT,
  `dynamic_user_id` int(11) DEFAULT NULL,
  `dynamic_text` text,
  `dynamic_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `dynamic_collection_num` int(11) DEFAULT '0',
  `dynamic_like_num` int(11) DEFAULT '0',
  `dynamic_comment_num` int(11) DEFAULT '0',
  `dynamic_address` varchar(255) DEFAULT NULL,
  `dynamic_time` datetime DEFAULT NULL,
  `dynamic_partition_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`dynamic_id`),
  KEY `dynamic_user_id` (`dynamic_user_id`),
  CONSTRAINT `dynamic_ibfk_1` FOREIGN KEY (`dynamic_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dynamic
-- ----------------------------
INSERT INTO `dynamic` VALUES ('67', '14', 'ver0.99999 大概', 'dymaic_test.jpg', '0', '0', '0', ' 河北省辛集市', '2018-12-26 16:02:27', '1');
INSERT INTO `dynamic` VALUES ('96', '18', '一个人的旅行，在路上遇见最真实的自己', 'hshdy.jpg', '0', '0', '0', '河北省', '2018-12-14 14:24:41', '1');
INSERT INTO `dynamic` VALUES ('97', '14', '桂林旅行,七星公园', 'lhrdy.jpg', '0', '0', '0', '桂林市 ', '2018-08-16 14:26:58', '1');
INSERT INTO `dynamic` VALUES ('98', '16', '今天天气好晴朗，处处好风光', 'lqwdy.jpg', '0', '0', '1', '桂林市', '2018-12-05 14:28:53', '1');
INSERT INTO `dynamic` VALUES ('99', '17', '一辈子是场修行，短的是旅行，长的是人生。', 'ljxdy.jpg', '0', '1', '2', '河北省', '2018-12-05 14:30:46', '1');
INSERT INTO `dynamic` VALUES ('100', '14', '测试发表', '743.2276442436313.jpg', '0', '0', '0', '河北省辛集市', '2019-01-02 02:14:18', '1');
INSERT INTO `dynamic` VALUES ('101', '14', '演示发表', '165.79718787980102.jpg', '0', '0', '0', '河北省辛集市', '2019-01-02 07:03:05', '1');

-- ----------------------------
-- Table structure for follow
-- ----------------------------
DROP TABLE IF EXISTS `follow`;
CREATE TABLE `follow` (
  `follow_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `follow_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`follow_id`),
  KEY `user_id` (`user_id`),
  KEY `follow_user_id` (`follow_user_id`),
  CONSTRAINT `follow_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `follow_ibfk_2` FOREIGN KEY (`follow_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of follow
-- ----------------------------
INSERT INTO `follow` VALUES ('8', '14', '17');

-- ----------------------------
-- Table structure for likeup
-- ----------------------------
DROP TABLE IF EXISTS `likeup`;
CREATE TABLE `likeup` (
  `likeup_id` int(11) NOT NULL AUTO_INCREMENT,
  `likeup_user_id` int(11) NOT NULL,
  `likeup_dynamic_id` int(11) NOT NULL,
  PRIMARY KEY (`likeup_id`,`likeup_user_id`,`likeup_dynamic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of likeup
-- ----------------------------
INSERT INTO `likeup` VALUES ('12', '14', '99');

-- ----------------------------
-- Table structure for partition
-- ----------------------------
DROP TABLE IF EXISTS `partition`;
CREATE TABLE `partition` (
  `partition_id` int(11) NOT NULL AUTO_INCREMENT,
  `partition_text` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`partition_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of partition
-- ----------------------------
INSERT INTO `partition` VALUES ('1', '美食');
INSERT INTO `partition` VALUES ('2', '游玩');
INSERT INTO `partition` VALUES ('3', null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_phone` varchar(20) NOT NULL,
  `user_password` varchar(255) NOT NULL,
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_sex` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `user_birthday` datetime DEFAULT NULL,
  `user_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `user_funnum` int(11) DEFAULT '0',
  `user_collection_num` int(11) DEFAULT '0',
  `user_touxiang_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `user_background_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `user_introduction` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_phone` (`user_phone`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('14', '15227186315', '123456', '刘恒韧', '男', '1997-04-06 00:00:00', '河北省辛集市', '0', '0', '962.6319832320949.jpg', '', 'ver1.0大概添加');
INSERT INTO `user` VALUES ('16', '15226507611', '123456', '梁启文', '男', '1998-04-27 00:00:00', '东北', '0', '0', 'lqw.jpg', '', 'promising young');
INSERT INTO `user` VALUES ('17', '123456789', '123456789', '李俊霞', '女', '1997-10-25 00:00:00', '山西', '0', '0', 'ljx.png', '', '还没想好怎么介绍自己');
INSERT INTO `user` VALUES ('18', '18332158253', '123456', '郝帅虎', '男', '1998-10-09 00:00:00', '河北师大', '0', '0', 'hsh.jpg', '', 'Game to life.');
