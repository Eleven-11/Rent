/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : example

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 18/08/2019 22:36:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_wx_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_wx_permission`;
CREATE TABLE `t_wx_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_type` int(11) NOT NULL DEFAULT '0',
  `permission_code` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_wx_permission
-- ----------------------------
BEGIN;
INSERT INTO `t_wx_permission` VALUES (1, 0, 'wx:follow:userList');
INSERT INTO `t_wx_permission` VALUES (2, 0, 'wx:follow:fansList');
INSERT INTO `t_wx_permission` VALUES (3, 0, 'wx:comment:del');
INSERT INTO `t_wx_permission` VALUES (4, 0, 'wx:comment:list');
INSERT INTO `t_wx_permission` VALUES (5, 0, 'wx:post:del');
INSERT INTO `t_wx_permission` VALUES (6, 0, 'wx:browse:postList');
INSERT INTO `t_wx_permission` VALUES (7, 0, 'wx:browse:userList');
INSERT INTO `t_wx_permission` VALUES (8, 0, 'wx:collection:list');
INSERT INTO `t_wx_permission` VALUES (9, 0, 'wx:user:update');
INSERT INTO `t_wx_permission` VALUES (10, 0, 'wx:follow:update');
INSERT INTO `t_wx_permission` VALUES (11, 0, 'wx:post:update');
INSERT INTO `t_wx_permission` VALUES (12, 0, 'wx:post:info');
INSERT INTO `t_wx_permission` VALUES (13, 0, 'wx:like:userList');
INSERT INTO `t_wx_permission` VALUES (14, 0, 'wx:like:postList');
INSERT INTO `t_wx_permission` VALUES (15, 0, 'wx:report:add');
INSERT INTO `t_wx_permission` VALUES (16, 1, 'wx:comment:add');
INSERT INTO `t_wx_permission` VALUES (17, 1, 'wx:post:add');
INSERT INTO `t_wx_permission` VALUES (18, 1, 'wx:collection:add');
INSERT INTO `t_wx_permission` VALUES (19, 1, 'wx:post:shelf');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
