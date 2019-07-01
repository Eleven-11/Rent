/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : example

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-07-01 14:21:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT '' COMMENT '文章内容',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_status` varchar(1) DEFAULT '1' COMMENT '是否有效  1.有效  2无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='发布号作者表';

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('5', '莎士比亚', '2017-10-25 09:08:45', '2017-10-30 17:59:41', '1');
INSERT INTO `article` VALUES ('6', '亚里士多德', '2017-10-26 10:49:28', '2017-11-18 09:54:15', '1');
INSERT INTO `article` VALUES ('10', '亚历山大', '2017-10-26 14:57:45', '2017-11-08 13:28:52', '1');
INSERT INTO `article` VALUES ('11', '李白', '2017-10-26 15:23:42', '2017-10-26 15:23:42', '1');
INSERT INTO `article` VALUES ('19', '文章test2', '2017-11-18 13:37:07', '2017-11-18 13:37:11', '1');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL DEFAULT '0' COMMENT '自定id,主要供前端展示权限列表分类排序使用.',
  `menu_code` varchar(255) DEFAULT '' COMMENT '归属菜单,前端判断并展示菜单使用,',
  `menu_name` varchar(255) DEFAULT '' COMMENT '菜单的中文释义',
  `permission_code` varchar(255) DEFAULT '' COMMENT '权限的代码/通配符,对应代码中@RequiresPermissions 的value',
  `permission_name` varchar(255) DEFAULT '' COMMENT '本权限的中文释义',
  `required_permission` tinyint(1) DEFAULT '2' COMMENT '是否本菜单必选权限, 1.必选 2非必选 通常是"列表"权限是必选',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='后台权限表';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('101', 'article', '文章管理', 'article:list', '列表', '1');
INSERT INTO `sys_permission` VALUES ('102', 'article', '文章管理', 'article:add', '新增', '2');
INSERT INTO `sys_permission` VALUES ('103', 'article', '文章管理', 'article:update', '修改', '2');
INSERT INTO `sys_permission` VALUES ('601', 'user', '用户', 'user:list', '列表', '1');
INSERT INTO `sys_permission` VALUES ('602', 'user', '用户', 'user:add', '新增', '2');
INSERT INTO `sys_permission` VALUES ('603', 'user', '用户', 'user:update', '修改', '2');
INSERT INTO `sys_permission` VALUES ('701', 'role', '角色权限', 'role:list', '列表', '1');
INSERT INTO `sys_permission` VALUES ('702', 'role', '角色权限', 'role:add', '新增', '2');
INSERT INTO `sys_permission` VALUES ('703', 'role', '角色权限', 'role:update', '修改', '2');
INSERT INTO `sys_permission` VALUES ('704', 'role', '角色权限', 'role:delete', '删除', '2');
INSERT INTO `sys_permission` VALUES ('801', 'wxUser', '微信用户', 'wxuser:list', '列表', '1');
INSERT INTO `sys_permission` VALUES ('802', 'wxUser', '微信用户', 'wxUser:updateCollectionList', '修改', '2');
INSERT INTO `sys_permission` VALUES ('803', 'wxUser', '微信用户', 'wxUser:CollectionList', '列表', '2');
INSERT INTO `sys_permission` VALUES ('804', 'wxUser', '微信用户', 'wxuser:followList', '列表', '2');
INSERT INTO `sys_permission` VALUES ('805', 'wxUser', '微信用户', 'wxuser:fansList', '列表', '2');
INSERT INTO `sys_permission` VALUES ('806', 'wxUser', '微信用户', 'wxUser:updateWxUserRes', '修改', '2');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) DEFAULT NULL COMMENT '角色名',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_status` varchar(1) DEFAULT '1' COMMENT '是否有效  1有效  2无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='后台角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', '2017-11-22 16:24:34', '2017-11-22 16:24:52', '1');
INSERT INTO `sys_role` VALUES ('2', '作家', '2017-11-22 16:24:34', '2017-11-22 16:24:52', '1');
INSERT INTO `sys_role` VALUES ('3', '程序员', '2017-11-22 16:28:47', '2017-11-22 16:28:47', '1');
INSERT INTO `sys_role` VALUES ('4', '微信小程序管理员', '2019-06-20 17:35:05', '2019-06-20 17:35:12', '1');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `permission_id` int(11) DEFAULT NULL COMMENT '权限id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_status` varchar(1) DEFAULT '1' COMMENT '是否有效 1有效     2无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='角色-权限关联表';

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '2', '101', '2017-11-22 16:26:21', '2017-11-22 16:26:32', '1');
INSERT INTO `sys_role_permission` VALUES ('2', '2', '102', '2017-11-22 16:26:21', '2017-11-22 16:26:32', '1');
INSERT INTO `sys_role_permission` VALUES ('5', '2', '602', '2017-11-22 16:28:28', '2017-11-22 16:28:28', '1');
INSERT INTO `sys_role_permission` VALUES ('6', '2', '601', '2017-11-22 16:28:28', '2017-11-22 16:28:28', '1');
INSERT INTO `sys_role_permission` VALUES ('7', '2', '603', '2017-11-22 16:28:28', '2017-11-22 16:28:28', '1');
INSERT INTO `sys_role_permission` VALUES ('8', '2', '703', '2017-11-22 16:28:28', '2017-11-22 16:28:28', '1');
INSERT INTO `sys_role_permission` VALUES ('9', '2', '701', '2017-11-22 16:28:28', '2019-06-20 18:26:50', '2');
INSERT INTO `sys_role_permission` VALUES ('10', '2', '702', '2017-11-22 16:28:28', '2019-06-20 18:26:50', '2');
INSERT INTO `sys_role_permission` VALUES ('11', '2', '704', '2017-11-22 16:28:31', '2019-06-13 16:12:25', '2');
INSERT INTO `sys_role_permission` VALUES ('12', '2', '103', '2017-11-22 16:28:31', '2017-11-22 16:28:31', '1');
INSERT INTO `sys_role_permission` VALUES ('13', '3', '601', '2017-11-22 16:28:47', '2017-11-22 16:28:47', '1');
INSERT INTO `sys_role_permission` VALUES ('14', '3', '701', '2017-11-22 16:28:47', '2017-11-22 16:28:47', '1');
INSERT INTO `sys_role_permission` VALUES ('15', '3', '702', '2017-11-22 16:35:01', '2017-11-22 16:35:01', '1');
INSERT INTO `sys_role_permission` VALUES ('16', '3', '704', '2017-11-22 16:35:01', '2017-11-22 16:35:01', '1');
INSERT INTO `sys_role_permission` VALUES ('17', '3', '102', '2017-11-22 16:35:01', '2017-11-22 16:35:01', '1');
INSERT INTO `sys_role_permission` VALUES ('18', '3', '101', '2017-11-22 16:35:01', '2017-11-22 16:35:01', '1');
INSERT INTO `sys_role_permission` VALUES ('19', '3', '603', '2017-11-22 16:35:01', '2017-11-22 16:35:01', '1');
INSERT INTO `sys_role_permission` VALUES ('20', '2', '704', '2019-06-13 16:12:49', '2019-06-13 16:12:49', '1');
INSERT INTO `sys_role_permission` VALUES ('21', '4', '801', '2019-06-20 17:37:36', '2019-06-20 17:37:36', '1');
INSERT INTO `sys_role_permission` VALUES ('22', '4', '802', '2019-06-20 17:37:36', '2019-06-20 17:37:36', '1');
INSERT INTO `sys_role_permission` VALUES ('23', '2', '801', '2019-06-20 18:27:16', '2019-06-20 18:27:16', '1');
INSERT INTO `sys_role_permission` VALUES ('24', '4', '101', '2019-06-20 18:29:59', '2019-06-20 18:29:59', '1');
INSERT INTO `sys_role_permission` VALUES ('25', '4', '102', '2019-06-20 18:29:59', '2019-06-20 18:29:59', '1');
INSERT INTO `sys_role_permission` VALUES ('26', '4', '103', '2019-06-20 18:29:59', '2019-06-20 18:29:59', '1');
INSERT INTO `sys_role_permission` VALUES ('27', '4', '601', '2019-06-20 18:29:59', '2019-06-20 18:29:59', '1');
INSERT INTO `sys_role_permission` VALUES ('28', '4', '602', '2019-06-20 18:29:59', '2019-06-20 18:29:59', '1');
INSERT INTO `sys_role_permission` VALUES ('29', '4', '603', '2019-06-20 18:29:59', '2019-06-20 18:29:59', '1');
INSERT INTO `sys_role_permission` VALUES ('30', '4', '701', '2019-06-20 18:29:59', '2019-06-20 18:29:59', '1');
INSERT INTO `sys_role_permission` VALUES ('31', '4', '702', '2019-06-20 18:29:59', '2019-06-20 18:29:59', '1');
INSERT INTO `sys_role_permission` VALUES ('32', '4', '703', '2019-06-20 18:29:59', '2019-06-20 18:29:59', '1');
INSERT INTO `sys_role_permission` VALUES ('33', '4', '704', '2019-06-20 18:29:59', '2019-06-20 18:29:59', '1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `role_id` int(11) DEFAULT '0' COMMENT '角色ID',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `delete_status` varchar(1) DEFAULT '1' COMMENT '是否有效  1有效  2无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10009 DEFAULT CHARSET=utf8 COMMENT='运营后台用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('10003', 'admin', '123456', '超级用户23', '1', '2017-10-30 11:52:38', '2017-11-17 23:51:40', '1');
INSERT INTO `sys_user` VALUES ('10004', 'user', '123456', '莎士比亚', '3', '2017-10-30 16:13:02', '2019-06-20 18:27:03', '1');
INSERT INTO `sys_user` VALUES ('10005', 'aaa', '123456', 'abba', '1', '2017-11-15 14:02:56', '2017-11-17 23:51:42', '1');
INSERT INTO `sys_user` VALUES ('10007', 'test', '123456', '就看看列表', '3', '2017-11-22 16:29:41', '2017-11-22 16:29:41', '1');
INSERT INTO `sys_user` VALUES ('10008', 'WXADMIN', '123456', 'LINGLING', '4', '2019-06-20 17:37:08', '2019-06-20 17:37:08', '1');

-- ----------------------------
-- Table structure for t_advert_banner
-- ----------------------------
DROP TABLE IF EXISTS `t_advert_banner`;
CREATE TABLE `t_advert_banner` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '广告栏ID',
  `ADVERT_TITLE` varchar(100) DEFAULT NULL COMMENT '广告标题，今后台查看',
  `IMG_URL` varchar(255) DEFAULT NULL COMMENT '图片URL',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `SORT_TIME` datetime DEFAULT NULL COMMENT '排序时间',
  `IS_DEL` tinyint(4) DEFAULT '0' COMMENT '是否删除，0正常，1删除',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_advert_banner
-- ----------------------------

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `POST_ID` int(11) NOT NULL COMMENT '帖子ID',
  `START_ID` varchar(128) NOT NULL COMMENT '发起评论人的ID',
  `RECEIVE_ID` varchar(128) NOT NULL COMMENT '接收人ID',
  `CONTENT` text NOT NULL COMMENT '文本内容',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `IS_DEL` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除，0正常，1删除',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_comment
-- ----------------------------

-- ----------------------------
-- Table structure for t_navigation
-- ----------------------------
DROP TABLE IF EXISTS `t_navigation`;
CREATE TABLE `t_navigation` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '导航栏ID',
  `TITLE` varchar(50) NOT NULL COMMENT '标签文本',
  `MAX_TOP_NUM` int(11) NOT NULL COMMENT '最大置顶数量',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `IS_DEL` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除，0正常，1删除',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_navigation
-- ----------------------------

-- ----------------------------
-- Table structure for t_navigation_top
-- ----------------------------
DROP TABLE IF EXISTS `t_navigation_top`;
CREATE TABLE `t_navigation_top` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '置顶表ID',
  `NAVIGATION_ID` int(11) NOT NULL COMMENT '导航栏ID',
  `POST_ID` int(11) NOT NULL COMMENT '帖子ID',
  `SORT_TIME` datetime NOT NULL COMMENT '排序时间，用来排序使用',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `IS_DEL` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除，0正常，1删除',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_navigation_top
-- ----------------------------

-- ----------------------------
-- Table structure for t_post_base
-- ----------------------------
DROP TABLE IF EXISTS `t_post_base`;
CREATE TABLE `t_post_base` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '帖子ID',
  `USER_ID` varchar(128) NOT NULL COMMENT '发帖人ID',
  `TYPE_ID` int(11) NOT NULL COMMENT '帖子类型ID',
  `CONTENT` text NOT NULL COMMENT '帖子文本',
  `MIN_PRICE` varchar(10) DEFAULT NULL COMMENT '最小价格',
  `MAX_PRICE` varchar(10) DEFAULT NULL COMMENT '最大价格',
  `PHONE` varchar(20) DEFAULT NULL COMMENT '电话',
  `ADDRESS` varchar(255) DEFAULT NULL COMMENT '地址文本',
  `LABELS` varchar(255) DEFAULT NULL COMMENT '标签集合',
  `TOTAL_BROWSE` int(11) NOT NULL COMMENT '真实浏览量',
  `TOTAL_RESONATE` int(11) NOT NULL COMMENT '真实点赞数量',
  `DEV_BROWSE` int(11) NOT NULL COMMENT '偏移的浏览量，后台可以修改的地方',
  `DEV_RESONATE` int(11) NOT NULL COMMENT '偏移的点赞量，后台可以修改的地方',
  `ACTIVE_TIME` datetime NOT NULL COMMENT '活跃时间，作为排序的依据',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `IS_LOWER_SHELF` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否下架，0上架，1下架',
  `IS_DEL` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除，0正常，1删除',
  `TOTAL_COLLECTION` int(11) NOT NULL DEFAULT '0',
  `TOTAL_COMMENTS` int(11) NOT NULL DEFAULT '0',
  `FEE` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=222334 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_post_base
-- ----------------------------
INSERT INTO `t_post_base` VALUES ('111222', '12325', '2', 'xzsfds', '52', '8888', '110', 'xiamen', 'dsf', '12', '85', '888', '55', '2019-06-25 20:03:05', '2019-06-25 20:03:08', '12', '0', '12', '88', '0');
INSERT INTO `t_post_base` VALUES ('111333', '12315', '2', 'jakdjflj', '52', '8888', '110', 'xiamen', 'dsf', '12', '85', '888', '55', '2019-06-25 20:03:05', '2019-06-25 20:03:08', '12', '0', '12', '88', '0');
INSERT INTO `t_post_base` VALUES ('222333', '12333', '2', 'abcdefg', '52', '8888', '110', 'xiamen', 'dsf', '12', '85', '888', '55', '2019-06-25 20:03:05', '2019-06-25 20:03:08', '12', '0', '12', '88', '0');

-- ----------------------------
-- Table structure for t_post_label
-- ----------------------------
DROP TABLE IF EXISTS `t_post_label`;
CREATE TABLE `t_post_label` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '标签表ID',
  `PARENT_ID` int(11) NOT NULL COMMENT '标签的父ID',
  `CONTENT` varchar(50) NOT NULL COMMENT '标签内容',
  `IS_DEL` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除，0正常，1删除',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_post_label
-- ----------------------------

-- ----------------------------
-- Table structure for t_post_picture
-- ----------------------------
DROP TABLE IF EXISTS `t_post_picture`;
CREATE TABLE `t_post_picture` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '图片ID',
  `POST_ID` int(11) NOT NULL COMMENT '帖子ID',
  `IMG_URL` varchar(255) NOT NULL COMMENT '图片URL',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_post_picture
-- ----------------------------
INSERT INTO `t_post_picture` VALUES ('1', '111222', 'tupian1', '2019-06-26 10:25:39');
INSERT INTO `t_post_picture` VALUES ('2', '111333', 'tupian2', '2019-06-26 10:26:47');
INSERT INTO `t_post_picture` VALUES ('3', '222333', 'tupian3', '2019-06-26 10:31:55');

-- ----------------------------
-- Table structure for t_post_type
-- ----------------------------
DROP TABLE IF EXISTS `t_post_type`;
CREATE TABLE `t_post_type` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '帖子类型ID',
  `TYPE_IMTG_URL` varchar(255) NOT NULL COMMENT '类型图片URL',
  `TYPE_NAME` varchar(50) NOT NULL COMMENT '类型名',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `IS_DEL` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除，0不删除，1删除',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_post_type
-- ----------------------------

-- ----------------------------
-- Table structure for t_report_record
-- ----------------------------
DROP TABLE IF EXISTS `t_report_record`;
CREATE TABLE `t_report_record` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '举报记录ID',
  `USER_ID` varchar(128) NOT NULL COMMENT '举报人ID',
  `POST_ID` int(11) NOT NULL COMMENT '举报的帖子ID',
  `CREATE_TIME` datetime NOT NULL COMMENT '举报时间',
  `IS_DEL` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除，0正常，1删除',
  `IS_IDLE` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否闲置，0闲置，1已处理',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_report_record
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_information
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_information`;
CREATE TABLE `t_sys_information` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '系统消息ID',
  `TARGET_ID` varchar(128) NOT NULL COMMENT '发送对象的ID，单发为用户ID，群发为system_info',
  `CONTENT` text NOT NULL COMMENT '消息文本内容',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_information
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_template
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_template`;
CREATE TABLE `t_sys_template` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '系统模版ID',
  `TITLE` varchar(255) NOT NULL COMMENT '模版标题',
  `CONTENT` text NOT NULL COMMENT '模版文本',
  `IS_NOT_GUIDE` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否不是引导标示，0不是，1是',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_template
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_browse
-- ----------------------------
DROP TABLE IF EXISTS `t_user_browse`;
CREATE TABLE `t_user_browse` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '浏览记录ID',
  `USER_ID` varchar(128) NOT NULL COMMENT '用户ID',
  `POST_ID` int(11) NOT NULL COMMENT '帖子ID',
  `CREATE_TIME` datetime NOT NULL COMMENT '浏览时间',
  `IS_DEL` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除，0正常，1删除',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_browse
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_collection
-- ----------------------------
DROP TABLE IF EXISTS `t_user_collection`;
CREATE TABLE `t_user_collection` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '收藏主键ID',
  `USER_ID` varchar(128) NOT NULL,
  `POST_ID` int(11) NOT NULL COMMENT '帖子ID',
  `IS_DEL` tinyint(4) NOT NULL DEFAULT '0' COMMENT '收藏状态，0启用，1删除',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `SORT_TIME` datetime NOT NULL COMMENT '排序时间 ',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_collection
-- ----------------------------
INSERT INTO `t_user_collection` VALUES ('1', '12315', '111222', '0', '2019-06-25 19:15:04', '2019-06-28 00:00:00');
INSERT INTO `t_user_collection` VALUES ('2', '12325', '111333', '0', '2019-06-25 19:15:35', '2019-06-05 00:00:00');
INSERT INTO `t_user_collection` VALUES ('3', '12333', '222333', '0', '2019-06-25 19:15:51', '2019-06-04 00:00:00');
INSERT INTO `t_user_collection` VALUES ('4', '12315', '111333', '0', '2019-06-26 19:09:00', '2019-06-05 00:00:00');
INSERT INTO `t_user_collection` VALUES ('5', '12315', '222333', '0', '2019-06-26 19:09:17', '2019-06-04 00:00:00');

-- ----------------------------
-- Table structure for t_user_follow
-- ----------------------------
DROP TABLE IF EXISTS `t_user_follow`;
CREATE TABLE `t_user_follow` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '关注ID',
  `FOLLOW_ID` varchar(128) NOT NULL COMMENT '关注的用户ID',
  `FAN_ID` varchar(128) NOT NULL COMMENT '粉丝ID',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `IS_DEL` tinyint(4) NOT NULL DEFAULT '0' COMMENT '关注状态，0启用，1解除',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_follow
-- ----------------------------
INSERT INTO `t_user_follow` VALUES ('1', '12315', '12325', '2019-06-21 09:34:06', '0');
INSERT INTO `t_user_follow` VALUES ('2', '12325', '12315', '2019-06-21 09:34:21', '0');
INSERT INTO `t_user_follow` VALUES ('3', '12333', '12315', '2019-06-21 09:34:34', '0');
INSERT INTO `t_user_follow` VALUES ('5', '12325', '12333', '2019-06-21 09:35:15', '0');

-- ----------------------------
-- Table structure for t_user_information
-- ----------------------------
DROP TABLE IF EXISTS `t_user_information`;
CREATE TABLE `t_user_information` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户消息ID',
  `START_ID` varchar(128) NOT NULL COMMENT '发送者ID',
  `RECEIVE_ID` varchar(128) NOT NULL COMMENT '接收者ID',
  `CONTENT` text NOT NULL COMMENT '消息文本',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `IS_DEL` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除，0正常，1删除',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_information
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_operation_restrict
-- ----------------------------
DROP TABLE IF EXISTS `t_user_operation_restrict`;
CREATE TABLE `t_user_operation_restrict` (
  `USER_ID` varchar(128) NOT NULL COMMENT '用户唯一标示',
  `REPORT_NUM` int(11) NOT NULL DEFAULT '3' COMMENT '日最大举报次数',
  `PUBLISH_NUM` int(11) NOT NULL DEFAULT '5' COMMENT '日最大发布次数',
  `RESTRICT_STATE` tinyint(4) NOT NULL DEFAULT '0' COMMENT '限制状态，0不限制，1限制',
  `RESTRICT_END_TIME` datetime NOT NULL COMMENT '限制结束时间',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_operation_restrict
-- ----------------------------
INSERT INTO `t_user_operation_restrict` VALUES ('12315', '3', '5', '1', '2019-06-20 14:56:49');
INSERT INTO `t_user_operation_restrict` VALUES ('12325', '3', '5', '0', '2019-06-28 15:37:08');
INSERT INTO `t_user_operation_restrict` VALUES ('12333', '3', '5', '1', '2019-06-28 15:37:19');
INSERT INTO `t_user_operation_restrict` VALUES ('12345', '3', '5', '1', '2019-06-28 15:36:40');
INSERT INTO `t_user_operation_restrict` VALUES ('32156', '3', '5', '0', '2019-06-28 15:36:59');

-- ----------------------------
-- Table structure for t_user_resonate
-- ----------------------------
DROP TABLE IF EXISTS `t_user_resonate`;
CREATE TABLE `t_user_resonate` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '点赞表主键',
  `TYPE` int(11) NOT NULL COMMENT '点赞类型',
  `TARGET_ID` varchar(128) NOT NULL COMMENT '点赞的目标id',
  `USER_ID` varchar(128) NOT NULL COMMENT '点赞用户的ID',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `IS_DEL` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态，0启用，1删除',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_resonate
-- ----------------------------

-- ----------------------------
-- Table structure for t_visitor
-- ----------------------------
DROP TABLE IF EXISTS `t_visitor`;
CREATE TABLE `t_visitor` (
  `USER_ID` varchar(255) NOT NULL COMMENT '系统用户唯一标示',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_visitor
-- ----------------------------

-- ----------------------------
-- Table structure for t_wx_user
-- ----------------------------
DROP TABLE IF EXISTS `t_wx_user`;
CREATE TABLE `t_wx_user` (
  `USER_ID` varchar(128) NOT NULL COMMENT '系统用户ID',
  `OPEN_ID` varchar(128) DEFAULT NULL COMMENT '微信openId',
  `UNION_ID` varchar(128) NOT NULL COMMENT '微信唯一标示',
  `WX_AVATAR_URL` varchar(255) NOT NULL COMMENT '微信用户头像',
  `WX_NICK_NAME` varchar(100) NOT NULL COMMENT '微信名称',
  `WX_GENDER` varchar(10) NOT NULL COMMENT '微信性别',
  `WX_CITY` varchar(100) DEFAULT NULL COMMENT '市',
  `WX_PROVINCE` varchar(100) DEFAULT NULL COMMENT '省',
  `WX_COUNTRY` varchar(100) DEFAULT NULL COMMENT '国家',
  `DEV_FANS` int(11) NOT NULL DEFAULT '0' COMMENT '粉丝偏移量',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime NOT NULL COMMENT '更新时间',
  `IS_DEL` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除，0正常，1删除',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_wx_user
-- ----------------------------
INSERT INTO `t_wx_user` VALUES ('12315', '4546', '476548', 'dsafdf', 'JACK', '1', 'xiamen', 'fujian', 'zhongguo', '0', '2019-06-20 18:40:13', '2019-06-20 18:40:16', '0');
INSERT INTO `t_wx_user` VALUES ('12325', '5552', '5659sjd', 'dsafdf', 'NEIL', '1', 'xiamen', 'fujian', 'zhongguo', '0', '2019-06-20 18:40:13', '2019-06-20 18:40:16', '0');
INSERT INTO `t_wx_user` VALUES ('12333', '655565', '48safldsjg', 'dsafdf', 'ROSE', '1', 'xiamen', 'fujian', 'zhongguo', '0', '2019-06-20 18:40:13', '2019-06-20 18:40:16', '0');
INSERT INTO `t_wx_user` VALUES ('12345', '9999995645', 'jh;olokj85', 'jlkjop', 'Dan', '2', 'zhangzhou', 'fujian', 'zhongguo ', '0', '2019-06-26 20:10:53', '2019-06-26 20:10:55', '0');
