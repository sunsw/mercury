/*
 Navicat Premium Data Transfer

 Source Server         : .
 Source Server Type    : MySQL
 Source Server Version : 50715
 Source Host           : localhost
 Source Database       : mercury

 Target Server Type    : MySQL
 Target Server Version : 50715
 File Encoding         : utf-8

 Date: 02/14/2017 10:35:31 AM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `sys_login_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `ip` varchar(50) DEFAULT NULL COMMENT 'ip',
  `ext0` varchar(50) DEFAULT NULL,
  `ext1` varchar(50) DEFAULT NULL,
  `ext2` varchar(50) DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modifier` bigint(20) DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='用户登录日志';

-- ----------------------------
--  Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `menu_title` varchar(50) NOT NULL COMMENT '标题',
  `menu_url` varchar(255) DEFAULT NULL COMMENT '链接',
  `menu_pic` varchar(255) DEFAULT NULL COMMENT '图片',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父菜单id',
  `level` int(11) DEFAULT NULL COMMENT '菜单级别',
  `sort` int(11) DEFAULT NULL COMMENT '序号',
  `status` varchar(3) DEFAULT 'Y' COMMENT '状态',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `ext0` varchar(50) DEFAULT NULL,
  `ext1` varchar(50) DEFAULT NULL,
  `ext2` varchar(50) DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modifier` bigint(20) DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `usernameUnique` (`menu_title`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='用户';

-- ----------------------------
--  Records of `sys_menu`
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES ('0', 'ROOT', '', null, '-1', '0', '0', 'Y', null, null, null, null, null, '2017-01-02 20:59:50', null, '2017-01-02 21:00:02'), ('1', '首页', '/views/html/welcome.html', 'fa-home', '0', '1', '0', 'Y', '首页菜单', null, null, null, null, '2017-02-05 12:35:44', null, '2017-02-07 13:05:29'), ('2', '系统管理', '#', 'fa-gear', '0', '1', '100', 'Y', '', null, null, null, null, '2016-06-24 18:10:12', null, '2017-02-13 10:05:51'), ('4', '用户管理', '/user/view', 'fa-user', '2', '2', '0', 'Y', '', null, null, null, null, '2017-02-05 12:38:25', null, '2017-02-09 22:38:05'), ('5', '角色管理', '/role/view', 'fa-group', '2', '2', '5', 'Y', '', null, null, null, null, '2017-02-05 16:06:39', null, '2017-02-09 22:38:07'), ('6', '权限管理', '/permission/view', 'fa-lock', '2', '2', '10', 'Y', '', null, null, null, null, '2017-02-05 16:14:53', null, '2017-02-09 22:38:08'), ('7', '菜单管理', '/menu/view', 'fa-sitemap', '2', '2', '15', 'Y', '', '', '', '', null, '2016-06-24 18:10:12', null, '2017-02-09 22:38:09'), ('8', '组织管理', '/organization/view', 'fa-bank', '2', '2', '20', 'Y', '', null, null, null, null, '2017-02-07 13:04:02', null, '2017-02-09 22:38:12'), ('20', '登录日志管理', '/login_log/view', 'fa-history', '2', '2', '25', 'Y', '', null, null, null, null, '2017-02-07 09:36:22', '5', '2017-02-13 09:39:35'), ('21', '操作日志管理', '/operate_log/view', 'fa-history', '2', '2', '30', 'Y', '', null, null, null, null, '2017-02-07 11:07:25', '5', '2017-02-13 09:39:37');
COMMIT;

-- ----------------------------
--  Table structure for `sys_operate_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_operate_log`;
CREATE TABLE `sys_operate_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `request_id` varchar(40) DEFAULT NULL COMMENT '请求Id',
  `user_id` bigint(20) NOT NULL COMMENT '操作人Id',
  `operator` varchar(50) NOT NULL COMMENT '操作人',
  `operation_type` enum('Create','Modify','Remove') NOT NULL COMMENT '操作类型',
  `clazz` varchar(100) DEFAULT NULL COMMENT '类',
  `params` varchar(500) DEFAULT NULL COMMENT '参数',
  `note` varchar(500) DEFAULT '' COMMENT '备注',
  `ext0` varchar(50) DEFAULT NULL,
  `ext1` varchar(50) DEFAULT NULL,
  `ext2` varchar(50) DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modifier` bigint(20) DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='系统操作日志';

-- ----------------------------
--  Table structure for `sys_organization`
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE `sys_organization` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `org_name` varchar(50) NOT NULL COMMENT '名称',
  `org_code` varchar(50) NOT NULL COMMENT '代码',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父id',
  `level` int(11) DEFAULT NULL COMMENT '级别',
  `sort` int(11) DEFAULT NULL COMMENT '序号',
  `status` varchar(3) DEFAULT 'Y' COMMENT '状态',
  `manager` bigint(20) DEFAULT NULL COMMENT '管理员',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `ext0` varchar(50) DEFAULT NULL,
  `ext1` varchar(50) DEFAULT NULL,
  `ext2` varchar(50) DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modifier` bigint(20) DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `orgnameUnique` (`org_name`) USING BTREE,
  UNIQUE KEY `orgcodeUnique` (`org_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='组织';

-- ----------------------------
--  Records of `sys_organization`
-- ----------------------------
BEGIN;
INSERT INTO `sys_organization` VALUES ('0', '根组织', 'ROOT', '-1', '0', '1', 'Y', null, '', null, null, null, null, '2016-06-24 18:10:12', null, '2017-02-11 21:39:52');
COMMIT;

-- ----------------------------
--  Table structure for `sys_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(32) NOT NULL COMMENT '权限名称',
  `permission_sign` varchar(128) NOT NULL COMMENT '权限标识 "user:create"',
  `sort` int(255) unsigned zerofill DEFAULT NULL COMMENT '序号',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `ext0` varchar(50) DEFAULT NULL,
  `ext1` varchar(50) DEFAULT NULL,
  `ext2` varchar(50) DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modifier` bigint(20) DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `permissionSignUnique` (`permission_sign`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='权限';

-- ----------------------------
--  Records of `sys_permission`
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission` VALUES ('1', '用户修改', 'user:edit', null, '', null, null, null, null, '2016-06-23 16:40:51', '5', '2017-02-14 10:27:11');
COMMIT;

-- ----------------------------
--  Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `role_name` varchar(32) NOT NULL COMMENT '角色名称',
  `role_sign` varchar(128) NOT NULL COMMENT '角色标识 "admin"',
  `sort` int(255) unsigned zerofill DEFAULT NULL COMMENT '序号',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `ext0` varchar(50) DEFAULT NULL,
  `ext1` varchar(50) DEFAULT NULL,
  `ext2` varchar(50) DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modifier` bigint(20) DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `roleSignUnique` (`role_sign`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='角色';

-- ----------------------------
--  Records of `sys_role`
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES ('1', 'admin', 'admin', null, 'admin', null, null, null, null, '2016-06-23 16:42:19', null, '2016-06-23 16:42:19');
COMMIT;

-- ----------------------------
--  Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色id',
  `menu_id` bigint(20) unsigned NOT NULL COMMENT '菜单id',
  `ext0` varchar(50) DEFAULT NULL,
  `ext1` varchar(50) DEFAULT NULL,
  `ext2` varchar(50) DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modifier` bigint(20) DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `rolePermissionUnique` (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='角色菜单';

-- ----------------------------
--  Table structure for `sys_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色id',
  `permission_id` bigint(20) unsigned NOT NULL COMMENT '权限id',
  `ext0` varchar(50) DEFAULT NULL,
  `ext1` varchar(50) DEFAULT NULL,
  `ext2` varchar(50) DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modifier` bigint(20) DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `rolePermissionUnique` (`role_id`,`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='角色权限';

-- ----------------------------
--  Records of `sys_role_permission`
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_permission` VALUES ('1', '1', '1', null, null, null, null, '2017-02-07 17:28:24', null, '2017-02-14 10:30:26');
COMMIT;

-- ----------------------------
--  Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(200) NOT NULL COMMENT '密码',
  `organization_id` bigint(20) DEFAULT NULL COMMENT '组织',
  `phone` varchar(50) DEFAULT NULL COMMENT '电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `status` varchar(3) DEFAULT 'Y' COMMENT '状态',
  `ext0` varchar(50) DEFAULT NULL,
  `ext1` varchar(50) DEFAULT NULL,
  `ext2` varchar(50) DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modifier` bigint(20) DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `usernameUnique` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1101 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='用户';

-- ----------------------------
--  Records of `sys_user`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES ('1', 'admin', '25d55ad283aa400af464c76d713c07ad', '0', '1234567890', '756535761@qq.com', 'Y', null, null, null, null, '2016-06-23 16:45:55', '5', '2017-02-14 10:29:56');
COMMIT;

-- ----------------------------
--  Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户id',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色id',
  `ext0` varchar(50) DEFAULT NULL,
  `ext1` varchar(50) DEFAULT NULL,
  `ext2` varchar(50) DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modifier` bigint(20) DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userRoleUnique` (`user_id`,`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='用户角色';

-- ----------------------------
--  Records of `sys_user_role`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES ('1', '1', '1', null, null, null, null, '2017-02-07 15:14:29', null, '2017-02-14 10:29:58');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
