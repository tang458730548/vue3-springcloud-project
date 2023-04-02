/*
 Navicat Premium Data Transfer

 Source Server         : localhost(本机)
 Source Server Type    : MySQL
 Source Server Version : 50740 (5.7.40)
 Source Host           : localhost:3306
 Source Schema         : vue3-springcloud

 Target Server Type    : MySQL
 Target Server Version : 50740 (5.7.40)
 File Encoding         : 65001

 Date: 02/04/2023 09:38:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_permission`;
CREATE TABLE `tb_role_permission`  (
                                       `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '表主键id',
                                       `ROLE_ID` bigint(20) NULL DEFAULT NULL COMMENT '角色表的主键id',
                                       `PERMISSON_ID` bigint(20) NULL DEFAULT NULL COMMENT '权限表的主键id',
                                       `CREATE_TIME` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                                       `LAST_UPDATE_TIME` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                       PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_role_permission
-- ----------------------------
INSERT INTO `tb_role_permission` VALUES (1, 100, 1, '2023-03-31 17:49:21', '2023-03-31 17:49:21');
INSERT INTO `tb_role_permission` VALUES (2, 100, 2, '2023-03-31 17:49:21', '2023-03-31 17:49:21');
INSERT INTO `tb_role_permission` VALUES (3, 100, 3, '2023-03-31 17:49:21', '2023-03-31 17:49:21');
INSERT INTO `tb_role_permission` VALUES (4, 200, 4, '2023-03-31 17:49:21', '2023-03-31 17:49:21');
INSERT INTO `tb_role_permission` VALUES (5, 100, 4, '2023-03-31 17:49:21', '2023-03-31 17:49:21');

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role`  (
                                 `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '表主键id',
                                 `USER_ID` bigint(20) NULL DEFAULT NULL COMMENT '帐号表的主键id',
                                 `ROLE_ID` bigint(20) NULL DEFAULT NULL COMMENT '角色表的主键id',
                                 `CREATE_TIME` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                                 `LAST_UPDATE_TIME` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                 PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES (1, 10001, 100, '2023-03-31 17:49:29', '2023-03-31 17:49:29');
INSERT INTO `tb_user_role` VALUES (2, 20001, 200, '2023-03-31 17:49:29', '2023-03-31 17:49:29');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
                            `ID` bigint(20) NOT NULL COMMENT 'ID',
                            `NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
                            `LOGIN_NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登陆名称',
                            `PASSWORD` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
                            `CREATE_TIME` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                            `LAST_UPDATE_TIME` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            PRIMARY KEY (`ID`) USING BTREE,
                            UNIQUE INDEX `LOGIN_NAME`(`LOGIN_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (10001, 'admin', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '2023-03-29 16:07:36', '2023-03-29 16:07:36');
INSERT INTO `tb_user` VALUES (20001, 'user', 'user', 'e10adc3949ba59abbe56e057f20f883e', '2023-03-29 16:07:36', '2023-03-29 16:07:36');

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role`  (
                            `ID` bigint(20) NOT NULL COMMENT ' 角色id 作为表主键 用于关联',
                            `ROLE_NAME` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名',
                            `ROLE_REMARKS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注，预留字段',
                            `CREATE_TIME` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                            `LAST_UPDATE_TIME` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES (100, 'admin', '系统管理员', '2023-03-31 17:49:16', '2023-03-31 17:49:16');
INSERT INTO `tb_role` VALUES (200, 'common', '普通用户', '2023-03-31 17:49:16', '2023-03-31 17:49:16');

-- ----------------------------
-- Table structure for tb_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_permission`;
CREATE TABLE `tb_permission`  (
                                  `ID` bigint(20) NOT NULL COMMENT '权限表id 作为表主键 用于关联',
                                  `PERMISSION_NAME` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
                                  `PER_REMARKS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注，预留字段',
                                  `CREATE_TIME` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                                  `LAST_UPDATE_TIME` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_permission
-- ----------------------------
INSERT INTO `tb_permission` VALUES (1, 'resetPassword', '重置密码', '2023-03-31 17:49:12', '2023-03-31 17:49:12');
INSERT INTO `tb_permission` VALUES (2, 'querySystemLog', '查看系统日志', '2023-03-31 17:49:12', '2023-03-31 17:49:12');
INSERT INTO `tb_permission` VALUES (3, 'exportUserInfo', '导出用户信息', '2023-03-31 17:49:12', '2023-03-31 17:49:12');
INSERT INTO `tb_permission` VALUES (4, 'queryMyUserInfo', '查看个人信息', '2023-03-31 17:49:12', '2023-03-31 17:49:12');

-- ----------------------------
-- Table structure for tb_module
-- ----------------------------
DROP TABLE IF EXISTS `tb_module`;
CREATE TABLE `tb_module`  (
                              `ID` bigint(20) NOT NULL,
                              `PARENT_ID` bigint(20) NULL DEFAULT NULL,
                              `MODULE_NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                              `MODULE_SORT` int(10) NULL DEFAULT NULL,
                              `CREATE_TIME` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                              `LAST_UPDATE_TIME` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_module
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
