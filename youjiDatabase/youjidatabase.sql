/*
Navicat MySQL Data Transfer

Source Server         : localhost_2333
Source Server Version : 80012
Source Host           : localhost:2333
Source Database       : youjidatabase

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2018-12-15 14:58:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for collection
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection` (
  `collection_id` int(11) NOT NULL AUTO_INCREMENT,
  `collection_user_id` int(11) DEFAULT NULL,
  `collection_dynamic_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`collection_id`),
  KEY `collection_user_id` (`collection_user_id`),
  KEY `collection_dynamic_id` (`collection_dynamic_id`),
  CONSTRAINT `collection_ibfk_1` FOREIGN KEY (`collection_user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `collection_ibfk_2` FOREIGN KEY (`collection_dynamic_id`) REFERENCES `dynamic` (`dynamic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_text` text,
  `comment_dynamic_id` int(11) DEFAULT NULL,
  `comment_like_num` int(11) DEFAULT NULL,
  `comment_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `comment_dynamic_id` (`comment_dynamic_id`),
  KEY `comment_user_id` (`comment_user_id`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`comment_dynamic_id`) REFERENCES `dynamic` (`dynamic_id`),
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`comment_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dynamic
-- ----------------------------
DROP TABLE IF EXISTS `dynamic`;
CREATE TABLE `dynamic` (
  `dynamic_id` int(11) NOT NULL AUTO_INCREMENT,
  `dynamic_user_id` int(11) DEFAULT NULL,
  `dynamic_text` text,
  `dynamic_img` varchar(255) DEFAULT NULL,
  `dynamic_collection_num` int(11) DEFAULT NULL,
  `dynamic_like_num` int(11) DEFAULT NULL,
  `dynamic_comment_num` int(11) DEFAULT NULL,
  `dynamic_address` varchar(255) DEFAULT NULL,
  `dynamic_time` datetime DEFAULT NULL,
  `dynamic_partition_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`dynamic_id`),
  KEY `dynamic_user_id` (`dynamic_user_id`),
  CONSTRAINT `dynamic_ibfk_1` FOREIGN KEY (`dynamic_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for follow
-- ----------------------------
DROP TABLE IF EXISTS `follow`;
CREATE TABLE `follow` (
  `follow_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `follow_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`follow_id`),
  KEY `user_id` (`user_id`),
  KEY `follow_user_id` (`follow_user_id`),
  CONSTRAINT `follow_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `follow_ibfk_2` FOREIGN KEY (`follow_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for partition
-- ----------------------------
DROP TABLE IF EXISTS `partition`;
CREATE TABLE `partition` (
  `partition_id` int(11) NOT NULL AUTO_INCREMENT,
  `partition_text` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`partition_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_phone` varchar(20) NOT NULL,
  `user_password` varchar(255) NOT NULL,
  `user_name` varchar(30) DEFAULT NULL,
  `user_sex` varchar(4) DEFAULT NULL,
  `user_birthday` datetime DEFAULT NULL,
  `user_address` varchar(255) DEFAULT NULL,
  `user_funnum` int(11) DEFAULT NULL,
  `user_collection_num` int(11) DEFAULT NULL,
  `user_touxiang_url` varchar(255) DEFAULT NULL,
  `user_background_url` varchar(255) DEFAULT NULL,
  `user_introduction` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
