/*
Navicat MySQL Data Transfer

Source Server         : my
Source Server Version : 50731
Source Host           : localhost:3306
Source Database       : ordering

Target Server Type    : MYSQL
Target Server Version : 50731
File Encoding         : 65001

Date: 2021-11-05 22:00:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `name` varchar(255) NOT NULL COMMENT '餐品名称',
  `price` decimal(5,2) NOT NULL COMMENT '价格',
  `img` varchar(255) DEFAULT NULL COMMENT '图片路径',
  `showorder` int(2) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('4f7fece0358e4ab795a5fdd57f38fef0', '银耳羹', '5.00', '..\\..\\img\\lvdoutang.png', '3');
INSERT INTO `t_menu` VALUES ('b9a2b44fad8f4d119296ee1e7ae5d701', '绿豆糖水', '3.00', '..\\..\\img\\lvdoutang.png', '1');
INSERT INTO `t_menu` VALUES ('e22b40328e7646c7bddbabd159757936', '烧仙草', '7.00', '..\\..\\img\\lvdoutang.png', '2');
INSERT INTO `t_menu` VALUES ('e76f74fe522a496d8d0231d1ad0bbb95', '清爽白凉粉', '6.00', '..\\..\\img\\lvdoutang.png', '4');

-- ----------------------------
-- Table structure for t_option
-- ----------------------------
DROP TABLE IF EXISTS `t_option`;
CREATE TABLE `t_option` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `menuId` varchar(32) NOT NULL COMMENT '关联菜单id',
  `name` varchar(255) NOT NULL COMMENT '餐品名称',
  `price` decimal(5,2) NOT NULL COMMENT '价格',
  `showorder` int(2) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单规格表';

-- ----------------------------
-- Records of t_option
-- ----------------------------
INSERT INTO `t_option` VALUES ('420a5bf10ec3441f8f2aff1967ca1c39', '4f7fece0358e4ab795a5fdd57f38fef0', '热', '0.00', '2');
INSERT INTO `t_option` VALUES ('59c6580253444e7485d259e6071fcc0f', 'e76f74fe522a496d8d0231d1ad0bbb95', '冰', '0.00', '1');
INSERT INTO `t_option` VALUES ('96ee285bd9ec4013aafbe37c6005f984', 'e22b40328e7646c7bddbabd159757936', '常温', '0.00', '2');
INSERT INTO `t_option` VALUES ('9cb2425d649c4c7dac227d4aa94c0de2', 'e22b40328e7646c7bddbabd159757936', '冰', '0.00', '1');
INSERT INTO `t_option` VALUES ('ab12fba5e3dc482cba4f8ec6d0c1407b', 'b9a2b44fad8f4d119296ee1e7ae5d701', '冰', '0.00', '1');
INSERT INTO `t_option` VALUES ('e7cae5c23bf3487f94fc681822dad49a', '4f7fece0358e4ab795a5fdd57f38fef0', '冰', '0.00', '1');
INSERT INTO `t_option` VALUES ('f34173daaca7480f82d94e5db672b999', 'b9a2b44fad8f4d119296ee1e7ae5d701', '常温', '0.00', '2');
INSERT INTO `t_option` VALUES ('f572158b8af646d18a0c4cfbf7c79c97', '4f7fece0358e4ab795a5fdd57f38fef0', '常温', '0.00', '3');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `openId` varchar(255) DEFAULT NULL COMMENT '微信openid',
  `phoneNum` varchar(20) DEFAULT NULL COMMENT '手机号',
  `roleId` varchar(32) DEFAULT NULL COMMENT '所属角色',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('540edf67568142a891c81d4951f58d07', 'oV5ee6hh7n9ag4MNMiLqOnMX4ON8', '13913377301', null, '南京雨花台区123');
