/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : es

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 30/08/2019 11:57:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dict
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` tinyint(2) NOT NULL COMMENT '1: 扩展词库 2:停词库',
  `key` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `status` tinyint(2) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dict
-- ----------------------------
INSERT INTO `dict` VALUES (2, 2, '的', 1);
INSERT INTO `dict` VALUES (4, 1, '桂林山水', 1);
INSERT INTO `dict` VALUES (6, 1, '徐长治', 1);

SET FOREIGN_KEY_CHECKS = 1;
