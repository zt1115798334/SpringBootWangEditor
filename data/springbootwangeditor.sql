/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50709
 Source Host           : localhost:3306
 Source Schema         : springbootwangeditor

 Target Server Type    : MySQL
 Target Server Version : 50709
 File Encoding         : 65001

 Date: 29/11/2017 15:25:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_image_info
-- ----------------------------
DROP TABLE IF EXISTS `t_image_info`;
CREATE TABLE `t_image_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `file_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `file_size` double NOT NULL,
  `file_md5` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `full_file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `original_file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `suffix_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_image_info
-- ----------------------------
INSERT INTO `t_image_info` VALUES (1, 'D:\\IdeaProjects\\SpringBootWangEditor\\image\\b627caaf-5f6d-474b-8e69-744a96dc6483.jpg', 0.05, '1d664f22e5f9cc7eccadb57238e8505e', 'b627caaf-5f6d-474b-8e69-744a96dc6483.jpg', '002VlHqdgy6SRwgWQOu7a.jpg', '002VlHqdgy6SRwgWQOu7a', 'jpg');

SET FOREIGN_KEY_CHECKS = 1;
