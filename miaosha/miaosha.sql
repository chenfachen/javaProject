/*
 Navicat Premium Data Transfer

 Source Server         : chenfa
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : miaosha

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 25/04/2021 09:34:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `goods_title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品标题',
  `goods_img` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品图片',
  `goods_detail` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '商品详情',
  `goods_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '商品单价',
  `goods_stock` int(0) NULL DEFAULT 0 COMMENT '商品库存',
  `is_seckill` int(0) NULL DEFAULT 0 COMMENT '是否设为秒杀',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, 'iphoneX', 'Apple iPhone X(A1865) 64G 银色 移动联通电信4G手机', '/img/iphonex.png', 'Apple iphone X(A1865) 64G 银色 移动联通电信4G手机', 8765.00, 10000, 0);
INSERT INTO `goods` VALUES (2, '红米k40', '红米 k40  4GB+32GB版 月光银 移动联通电信4G手机 双卡双待', '/img/meta10.png', '红米k40 4GB+32GB版 月光银 移动联通电信4G手机双卡双待', 3212.00, -1, 0);

-- ----------------------------
-- Table structure for miaosha_goods
-- ----------------------------
DROP TABLE IF EXISTS `miaosha_goods`;
CREATE TABLE `miaosha_goods`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `goods_id` bigint(0) NULL DEFAULT NULL,
  `miaosha_price` decimal(10, 2) NULL DEFAULT 0.00,
  `stock_count` int(0) NULL DEFAULT NULL,
  `start_date` datetime(0) NULL DEFAULT NULL,
  `end_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of miaosha_goods
-- ----------------------------
INSERT INTO `miaosha_goods` VALUES (1, 1, 0.01, 9, '2021-04-24 21:18:00', '2021-04-25 14:00:18');
INSERT INTO `miaosha_goods` VALUES (2, 2, 0.01, 9, '2021-04-24 14:00:14', '2021-04-25 14:00:14');

-- ----------------------------
-- Table structure for miaosha_order
-- ----------------------------
DROP TABLE IF EXISTS `miaosha_order`;
CREATE TABLE `miaosha_order`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(0) NULL DEFAULT NULL,
  `order_id` bigint(0) NULL DEFAULT NULL,
  `goods_id` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `u_uid_gid`(`user_id`, `goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of miaosha_order
-- ----------------------------
INSERT INTO `miaosha_order` VALUES (44, 15251829950, 35, 2);
INSERT INTO `miaosha_order` VALUES (45, 15251829950, 36, 1);

-- ----------------------------
-- Table structure for miaosha_user
-- ----------------------------
DROP TABLE IF EXISTS `miaosha_user`;
CREATE TABLE `miaosha_user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `salt` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `head` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `register_date` datetime(0) NULL DEFAULT NULL,
  `last_login_date` datetime(0) NULL DEFAULT NULL,
  `login_count` int(0) NULL DEFAULT NULL,
  `delivery_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15251829952 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of miaosha_user
-- ----------------------------
INSERT INTO `miaosha_user` VALUES (15251829950, 'chen', '6fe005a54aa13d7650d727693a8457e8', '1a2b3c4d', NULL, '2021-04-13 15:19:10', '2021-04-15 15:19:16', 1, NULL);
INSERT INTO `miaosha_user` VALUES (15251829951, 'yang', '6fe005a54aa13d7650d727693a8457e8', '1a2b3c4d', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(0) NULL DEFAULT NULL,
  `user_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `goods_id` bigint(0) NULL DEFAULT NULL,
  `delivery_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `goods_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `goods_count` int(0) NULL DEFAULT NULL,
  `goods_price` decimal(10, 2) NULL DEFAULT NULL,
  `order_channel` tinyint(0) NULL DEFAULT NULL,
  `status` tinyint(0) NULL DEFAULT 0,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `pay_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES (28, 15251829950, 'chen', 2, NULL, '华为Mate9', 1, 0.01, 1, 0, '2021-04-24 19:36:11', NULL);
INSERT INTO `order_info` VALUES (29, 15251829950, 'chen', 2, NULL, '华为Mate9', 1, 0.01, 1, 0, '2021-04-24 19:39:37', NULL);
INSERT INTO `order_info` VALUES (30, 15251829950, 'chen', 2, NULL, '华为Mate9', 1, 0.01, 1, 0, '2021-04-24 19:41:57', NULL);
INSERT INTO `order_info` VALUES (31, 15251829950, 'chen', 2, NULL, '红米k40', 1, 0.01, 1, 0, '2021-04-24 19:44:50', NULL);
INSERT INTO `order_info` VALUES (32, 15251829950, 'chen', 2, NULL, '红米k40', 1, 0.01, 1, 0, '2021-04-24 19:49:01', NULL);
INSERT INTO `order_info` VALUES (33, 15251829950, 'chen', 2, NULL, '红米k40', 1, 0.01, 1, 0, '2021-04-24 19:56:46', NULL);
INSERT INTO `order_info` VALUES (34, 15251829950, 'chen', 2, NULL, '红米k40', 1, 0.01, 1, 0, '2021-04-24 19:59:12', NULL);
INSERT INTO `order_info` VALUES (35, 15251829950, 'chen', 2, NULL, '红米k40', 1, 0.01, 1, 0, '2021-04-24 20:38:18', NULL);
INSERT INTO `order_info` VALUES (36, 15251829950, 'chen', 1, NULL, 'iphoneX', 1, 0.01, 1, 0, '2021-04-24 22:47:02', NULL);

-- ----------------------------
-- Table structure for order_pay
-- ----------------------------
DROP TABLE IF EXISTS `order_pay`;
CREATE TABLE `order_pay`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(0) NULL DEFAULT NULL,
  `order_id` bigint(0) NULL DEFAULT NULL,
  `goods_id` bigint(0) NULL DEFAULT NULL,
  `seller_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `app_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `total_amount` decimal(10, 2) NULL DEFAULT 0.00,
  `trade_status` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `subject` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `body` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `out_trade_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `gmt_create` datetime(0) NULL DEFAULT NULL,
  `gmt_payment` datetime(0) NULL DEFAULT NULL,
  `gmt_refund` datetime(0) NULL DEFAULT NULL,
  `gmt_close` datetime(0) NULL DEFAULT NULL,
  `passback_params` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pay_info
-- ----------------------------
DROP TABLE IF EXISTS `pay_info`;
CREATE TABLE `pay_info`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(0) NULL DEFAULT NULL,
  `order_id` bigint(0) NULL DEFAULT NULL,
  `pay_platform` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `platform_number` int(0) NULL DEFAULT NULL,
  `platform_status` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '1111');
INSERT INTO `user` VALUES (2, '2222');

SET FOREIGN_KEY_CHECKS = 1;
