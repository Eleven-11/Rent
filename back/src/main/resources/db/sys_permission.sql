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

 Date: 19/08/2019 00:37:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
BEGIN;
INSERT INTO `sys_permission` VALUES (11, 'wxUser', '微信用户', 'wxUser:list', '列表', 1);
INSERT INTO `sys_permission` VALUES (12, 'wxUser', '微信用户', 'wxUser:update', '修改', 2);
INSERT INTO `sys_permission` VALUES (13, 'wxUser', '微信用户', 'wxUser:astrict', '限制', 2);
INSERT INTO `sys_permission` VALUES (14, 'wxUser', '微信用户', 'wxUser:follow', '关注&粉丝', 2);
INSERT INTO `sys_permission` VALUES (15, 'wxUser', '微信用户', 'wxUser:collection', '收藏', 2);
INSERT INTO `sys_permission` VALUES (21, 'notice', '通知推送', 'notice:list', '列表', 1);
INSERT INTO `sys_permission` VALUES (22, 'notice', '通知推送', 'notice:send', '发送消息', 2);
INSERT INTO `sys_permission` VALUES (23, 'notice', '通知推送', 'notice:del', '删除', 2);
INSERT INTO `sys_permission` VALUES (31, 'banner', '滚动栏', 'banner:list', '列表', 1);
INSERT INTO `sys_permission` VALUES (32, 'banner', '滚动栏', 'banner:add', '新增', 2);
INSERT INTO `sys_permission` VALUES (33, 'banner', '滚动栏', 'banner:update', '修改', 2);
INSERT INTO `sys_permission` VALUES (34, 'banner', '滚动栏', 'banner:sort', '排序', 2);
INSERT INTO `sys_permission` VALUES (35, 'banner', '滚动栏', 'banner:del', '删除', 2);
INSERT INTO `sys_permission` VALUES (41, 'postType', '帖子类别', 'postType:list', '列表', 1);
INSERT INTO `sys_permission` VALUES (42, 'postType', '帖子类别', 'postType:add', '新增', 2);
INSERT INTO `sys_permission` VALUES (43, 'postType', '帖子类别', 'postType:update', '修改', 2);
INSERT INTO `sys_permission` VALUES (44, 'postType', '帖子类别', 'postType:sort', '排序', 2);
INSERT INTO `sys_permission` VALUES (45, 'postType', '帖子类别', 'postType:del', '删除', 2);
INSERT INTO `sys_permission` VALUES (51, 'postLabel', '帖子标签', 'postLabel:list', '列表', 1);
INSERT INTO `sys_permission` VALUES (52, 'postLabel', '帖子标签', 'postType:add:list', '新增', 2);
INSERT INTO `sys_permission` VALUES (53, 'postLabel', '帖子标签', 'postLabel:update', '修改', 2);
INSERT INTO `sys_permission` VALUES (54, 'postLabel', '帖子标签', 'postLabel:del', '删除', 2);
INSERT INTO `sys_permission` VALUES (61, 'noticeTemplate', '通知模版', 'noticeTemplate:list', '列表', 1);
INSERT INTO `sys_permission` VALUES (62, 'noticeTemplate', '通知模版', 'noticeTemplate:add', '新增', 2);
INSERT INTO `sys_permission` VALUES (63, 'noticeTemplate', '通知模版', 'noticeTemplate:update', '修改', 2);
INSERT INTO `sys_permission` VALUES (64, 'noticeTemplate', '通知模版', 'noticeTemplate:del', '删除', 2);
INSERT INTO `sys_permission` VALUES (71, 'top', '置顶管理', 'top:list', '列表', 1);
INSERT INTO `sys_permission` VALUES (72, 'top', '置顶管理', 'top:update', '修改', 2);
INSERT INTO `sys_permission` VALUES (73, 'top', '置顶管理', 'top:sort', '排序', 2);
INSERT INTO `sys_permission` VALUES (74, 'top', '置顶管理', 'top:del', '删除', 2);
INSERT INTO `sys_permission` VALUES (81, 'post', '帖子管理', 'post:list', '列表', 1);
INSERT INTO `sys_permission` VALUES (82, 'post', '帖子管理', 'post:update', '修改', 2);
INSERT INTO `sys_permission` VALUES (83, 'post', '帖子管理', 'post:top', '置顶', 2);
INSERT INTO `sys_permission` VALUES (84, 'post', '帖子管理', 'post:shelf', '上/下架', 2);
INSERT INTO `sys_permission` VALUES (85, 'post', '帖子管理', 'post:del', '删除', 2);
INSERT INTO `sys_permission` VALUES (91, 'comment', '评论管理', 'comment:list', '列表', 1);
INSERT INTO `sys_permission` VALUES (93, 'comment', '评论管理', 'comment:del', '删除', 2);
INSERT INTO `sys_permission` VALUES (601, 'user', '用户', 'user:list', '列表', 1);
INSERT INTO `sys_permission` VALUES (602, 'user', '用户', 'user:add', '新增', 2);
INSERT INTO `sys_permission` VALUES (603, 'user', '用户', 'user:update', '修改', 2);
INSERT INTO `sys_permission` VALUES (701, 'role', '角色权限', 'role:list', '列表', 1);
INSERT INTO `sys_permission` VALUES (702, 'role', '角色权限', 'role:add', '新增', 2);
INSERT INTO `sys_permission` VALUES (703, 'role', '角色权限', 'role:update', '修改', 2);
INSERT INTO `sys_permission` VALUES (704, 'role', '角色权限', 'role:delete', '删除', 2);
INSERT INTO `sys_permission` VALUES (1010, 'genderStatistics', '数据统计', 'statistics:gender:show', '性别', 1);
INSERT INTO `sys_permission` VALUES (1020, 'postStatistics', '数据统计', 'statistics:post:show', '帖子', 1);
INSERT INTO `sys_permission` VALUES (1030, 'resonateStatistics', '数据统计', 'statistics:follow:show', '关注', 1);
INSERT INTO `sys_permission` VALUES (1040, 'regionStatistics', '数据统计', 'statistics:region:show', '地区', 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
