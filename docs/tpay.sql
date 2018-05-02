/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : tpay

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2018-04-23 09:52:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mch_info
-- ----------------------------
DROP TABLE IF EXISTS `mch_info`;
CREATE TABLE `mch_info` (
  `id_` bigint(32) NOT NULL COMMENT '商户id',
  `mch_id` bigint(32) NOT NULL,
  `mch_name` varchar(30) DEFAULT NULL COMMENT '商户名称',
  `mch_type` char(2) DEFAULT NULL COMMENT '商户类型',
  `mch_private_key` varchar(1800) DEFAULT NULL COMMENT '商户私钥',
  `mch_public_key` varchar(1000) DEFAULT NULL COMMENT '商户公钥',
  `tp_private_key` varchar(1800) DEFAULT NULL COMMENT '平台私钥',
  `tp_public_key` varchar(1000) DEFAULT NULL COMMENT '平台公钥',
  `mch_status` tinyint(2) DEFAULT NULL COMMENT '状态 0停用 1正常',
  `enable_` tinyint(1) DEFAULT NULL,
  `remark_` varchar(200) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_by` bigint(32) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_by` bigint(32) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of mch_info
-- ----------------------------
INSERT INTO `mch_info` VALUES ('1000001', '8000000000', 'Trazen', '00', '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', '1', '1', null, '2018-04-23 09:30:05', '1', '2018-04-23 09:30:05', '1');
INSERT INTO `mch_info` VALUES ('171567608770854912', '8000000001', 'TEST', '00', '1', '1', '1', '1', '1', '1', null, '2018-04-19 10:28:26', '1', '2018-04-19 10:28:26', '1');

-- ----------------------------
-- Table structure for pay_channel
-- ----------------------------
DROP TABLE IF EXISTS `pay_channel`;
CREATE TABLE `pay_channel` (
  `id_` bigint(32) NOT NULL,
  `channel_id` varchar(24) DEFAULT NULL COMMENT '渠道id',
  `channel_name` varchar(30) DEFAULT NULL COMMENT '渠道名称 alipay，wechat等',
  `channel_mch_id` varchar(32) DEFAULT NULL COMMENT '渠道商户id',
  `mch_id` bigint(32) DEFAULT NULL COMMENT '商户id',
  `channel_status` tinyint(4) DEFAULT NULL COMMENT '渠道状态 0停用 1正常',
  `param_` varchar(4096) DEFAULT NULL COMMENT '配置参数 json',
  `enable_` tinyint(1) DEFAULT NULL,
  `remark_` varchar(200) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `create_by` bigint(32) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_by` bigint(32) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of pay_channel

-- ----------------------------
-- Table structure for pay_order
-- ----------------------------
DROP TABLE IF EXISTS `pay_order`;
CREATE TABLE `pay_order` (
  `id_` bigint(32) NOT NULL,
  `pay_order_no` varchar(50) DEFAULT '' COMMENT '支付订单号',
  `order_type` char(2) DEFAULT NULL COMMENT '订单类型',
  `mch_id` bigint(32) DEFAULT NULL COMMENT '商户id',
  `mch_order_no` varchar(50) DEFAULT NULL COMMENT '商户订单号',
  `pay_channel` varchar(32) DEFAULT NULL COMMENT '支付渠道id',
  `amount_` decimal(18,2) DEFAULT NULL COMMENT '支付金额',
  `currency` varchar(3) DEFAULT NULL COMMENT '三位货币代码 人民币:cny',
  `pay_status` char(2) DEFAULT NULL COMMENT '支付状态（00：订单生成；01：支付中；02：支付成功；03：处理完成）',
  `client_ip` varchar(32) DEFAULT NULL COMMENT '客户端ip',
  `device_info` varchar(64) DEFAULT NULL COMMENT '支付设备',
  `subject_` varchar(64) DEFAULT NULL COMMENT '商品标题',
  `body_` varchar(255) DEFAULT NULL COMMENT '商品描述',
  `extra_` varchar(512) DEFAULT NULL COMMENT '额外参数',
  `channel_mch_id` varchar(32) DEFAULT NULL COMMENT '渠道商户ID',
  `channel_order_no` varchar(64) DEFAULT NULL COMMENT '渠道订单号',
  `err_code` varchar(64) DEFAULT NULL COMMENT '渠道支付错误码',
  `err_msg` varchar(128) DEFAULT NULL COMMENT '渠道支付错误描述',
  `notify_url` varchar(128) DEFAULT NULL COMMENT '通知地址',
  `notify_count` tinyint(6) DEFAULT '0' COMMENT '通知次数',
  `last_notify_time` timestamp NULL DEFAULT NULL COMMENT '最后一次通知事件',
  `expire_time` timestamp NULL DEFAULT NULL COMMENT '订单失效时间',
  `pay_success_time` timestamp NULL DEFAULT NULL COMMENT '订单支付成功时间',
  `enable_` tinyint(1) DEFAULT NULL,
  `remark_` varchar(200) DEFAULT NULL,
  `create_by` bigint(32) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_by` bigint(32) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of pay_order
-- ----------------------------
INSERT INTO `pay_order` VALUES ('1', '20180410171600004', '1', '1000001', '1231', 'ALIPAY_WAP', '100.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-04 15:56:52', '2018-04-04 15:56:55', '2018-04-10 17:31:05', '1', null, null, '2018-04-04 15:57:01', null, null);
INSERT INTO `pay_order` VALUES ('2', '20180410171600006', '1', '1000001', '1231', 'ALIPAY_PC', '100.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-10 18:06:56', '1', null, null, null, null, null);
INSERT INTO `pay_order` VALUES ('3', '20180410171600010', '1', '1000001', '1231', 'ALIPAY_QR', '100.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-11 09:47:06', '1', null, null, null, null, null);
INSERT INTO `pay_order` VALUES ('4', '20180410171600020', '1', '1000001', '1231', 'ALIPAY_MOBILE', '100.00', 'CNY', '00', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-10 18:06:56', '1', null, null, null, null, null);
INSERT INTO `pay_order` VALUES ('5', '20180410171600013', '1', '1000001', '1231', 'WX_JSAPI', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-12 17:17:36', '1', null, null, null, null, null);
INSERT INTO `pay_order` VALUES ('6', '20180410171600012', '1', '1000001', '1231', 'WX_JSAPI', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-12 15:33:06', '1', null, null, null, null, null);
INSERT INTO `pay_order` VALUES ('7', '20180410171600032', '1', '1000001', '1231', 'WX_NATIVE', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-12 17:59:17', '1', null, null, null, null, null);
INSERT INTO `pay_order` VALUES ('8', '20180410171600033', '1', '1000001', '1231', 'WX_NATIVE', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-12 17:30:58', '1', null, null, null, null, null);
INSERT INTO `pay_order` VALUES ('9', '20180410171600034', '1', '1000001', '1231', 'WX_NATIVE', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-12 18:08:43', '1', null, null, null, null, null);
INSERT INTO `pay_order` VALUES ('10', '20180410171600035', '1', '1000001', '1231', 'WX_NATIVE', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-12 18:03:20', '1', null, null, null, null, null);
INSERT INTO `pay_order` VALUES ('11', '20180410171600036', '1', '1000001', '1231', 'WX_NATIVE', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-12 17:05:42', '1', null, null, null, null, null);
INSERT INTO `pay_order` VALUES ('12', '20180410171600037', '1', '1000001', '1231', 'WX_NATIVE', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-12 17:14:44', '1', null, null, null, null, null);
INSERT INTO `pay_order` VALUES ('13', '20180410171600038', '1', '1000001', '1231', 'WX_NATIVE', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-12 17:44:24', '1', null, null, null, null, null);
INSERT INTO `pay_order` VALUES ('14', '20180410171600039', '1', '1000001', '1231', 'WX_NATIVE', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-12 17:57:42', '1', null, null, null, null, null);
INSERT INTO `pay_order` VALUES ('15', '20180410171600040', '1', '1000001', '1231', 'WX_NATIVE', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-12 18:01:19', '1', null, null, null, null, null);
INSERT INTO `pay_order` VALUES ('16', '20180410171600041', '1', '1000001', '1231', 'WX_NATIVE', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-13 10:14:29', '1', null, null, null, null, null);
INSERT INTO `pay_order` VALUES ('17', '20180410171600042', '1', '1000001', '1231', 'WX_NATIVE', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-13 10:13:19', '1', null, null, null, null, null);
INSERT INTO `pay_order` VALUES ('18', '20180410171600043', '1', '1000001', '1231', 'WX_NATIVE', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-13 10:25:24', '1', null, null, null, null, null);
INSERT INTO `pay_order` VALUES ('19', '20180410171600044', '1', '1000001', '1231', 'WX_NATIVE', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-13 15:42:22', '1', null, null, null, null, null);
INSERT INTO `pay_order` VALUES ('20', '20180410171600045', '1', '1000001', '1231', 'WX_NATIVE', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-13 16:42:07', '1', null, null, null, null, null);
INSERT INTO `pay_order` VALUES ('21', '20180410171600052', '1', '1000001', '1231', 'WX_MWEB', '1.00', 'CNY', '00', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-13 10:25:24', '1', null, null, null, null, null);
INSERT INTO `pay_order` VALUES ('22', '20180410171600053', '1', '1000001', '1231', 'WX_MWEB', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-16 10:01:21', '1', null, null, null, null, null);
INSERT INTO `pay_order` VALUES ('23', '20180410171600054', '1', '1000001', '1231', 'ALIPAY_PC', '1.00', 'CNY', '00', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-16 10:01:21', '1', null, null, null, null, null);

-- ----------------------------
-- Table structure for sys_dic
-- ----------------------------
DROP TABLE IF EXISTS `sys_dic`;
CREATE TABLE `sys_dic` (
  `id_` bigint(32) NOT NULL AUTO_INCREMENT,
  `index_id` bigint(32) DEFAULT NULL,
  `code_` varchar(50) DEFAULT NULL,
  `code_text` varchar(100) DEFAULT NULL,
  `sort_no` int(2) DEFAULT NULL,
  `editable_` tinyint(1) NOT NULL DEFAULT '1',
  `enable_` tinyint(1) NOT NULL DEFAULT '1',
  `remark_` varchar(500) DEFAULT NULL,
  `create_by` bigint(32) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_by` bigint(32) NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id_`),
  UNIQUE KEY `field_id_code` (`index_id`,`code_`)
) ENGINE=InnoDB AUTO_INCREMENT=911128170872573953 DEFAULT CHARSET=utf8 COMMENT='数据字典明细表';

-- ----------------------------
-- Records of sys_dic
-- ----------------------------
INSERT INTO `sys_dic` VALUES ('1', '1', '0', '未知', '1', '1', '1', null, '1', '2018-04-18 10:29:23', '1', '2017-08-22 17:11:10');
INSERT INTO `sys_dic` VALUES ('3', '1', '2', '女', '3', '1', '1', null, '1', '2018-04-18 10:29:22', '1', '2016-06-28 18:04:11');
INSERT INTO `sys_dic` VALUES ('4', '2', '0', '激活', '1', '0', '1', null, '1', '2016-06-28 18:04:06', '1', '2016-06-28 18:04:11');
INSERT INTO `sys_dic` VALUES ('5', '2', '1', '锁定', '2', '0', '1', null, '1', '2016-06-28 18:04:06', '1', '2016-06-28 18:04:10');
INSERT INTO `sys_dic` VALUES ('6', '3', '1', '业务角色', '1', '1', '1', null, '1', '2018-04-18 10:29:20', '1', '2016-06-28 18:04:09');
INSERT INTO `sys_dic` VALUES ('7', '3', '2', '管理角色', '2', '1', '1', null, '1', '2018-04-18 10:29:20', '1', '2016-06-28 18:04:09');
INSERT INTO `sys_dic` VALUES ('8', '3', '3', '系统内置角色', '3', '1', '1', null, '1', '2018-04-18 10:29:19', '1', '2016-06-28 18:04:08');
INSERT INTO `sys_dic` VALUES ('11', '5', '0', '只读', '1', '1', '1', null, '1', '2018-04-18 10:29:18', '1', '2016-06-28 18:04:06');
INSERT INTO `sys_dic` VALUES ('13', '6', '0', '禁用', '1', '1', '1', null, '1', '2018-04-18 10:29:18', '1', '2016-06-28 18:04:05');
INSERT INTO `sys_dic` VALUES ('14', '6', '1', '启用', '2', '1', '1', null, '1', '2018-04-18 10:29:17', '1', '2016-06-28 18:04:04');
INSERT INTO `sys_dic` VALUES ('17', '8', '1', '系统菜单', '1', '1', '1', null, '1', '2018-04-18 10:29:17', '1', '2016-06-28 18:04:03');
INSERT INTO `sys_dic` VALUES ('18', '8', '2', '业务菜单', '2', '1', '1', null, '1', '2018-04-18 10:29:16', '1', '2016-06-28 18:04:01');
INSERT INTO `sys_dic` VALUES ('19', '9', '1', '经办员', '1', '1', '1', null, '1', '2018-04-18 10:29:15', '1', '2016-06-28 18:03:50');
INSERT INTO `sys_dic` VALUES ('20', '9', '2', '管理员', '2', '1', '1', null, '1', '2018-04-18 10:29:14', '1', '2016-06-28 18:03:48');
INSERT INTO `sys_dic` VALUES ('21', '9', '3', '系统内置用户', '3', '1', '1', null, '1', '2018-04-18 10:29:14', '1', '2016-06-28 18:03:47');
INSERT INTO `sys_dic` VALUES ('171218900803911680', '907498118721208320', '0004', '/sequence/mchid', '4', '1', '1', '商户编号', '1', '2018-04-18 11:22:47', '1', '2018-04-18 11:22:47');
INSERT INTO `sys_dic` VALUES ('171227859954696192', '171227628970180608', '00', '订单创建', '1', '1', '1', '订单创建', '1', '2018-04-18 11:58:23', '1', '2018-04-18 11:58:23');
INSERT INTO `sys_dic` VALUES ('171227925931098112', '171227628970180608', '01', '支付中', '2', '1', '1', '支付中', '1', '2018-04-18 11:58:39', '1', '2018-04-18 11:58:39');
INSERT INTO `sys_dic` VALUES ('171227970373943296', '171227628970180608', '02', '支付成功', '3', '1', '1', '支付成功', '1', '2018-04-18 11:58:49', '1', '2018-04-18 11:58:49');
INSERT INTO `sys_dic` VALUES ('171228022492364800', '171227628970180608', '03', '订单完成', '4', '1', '1', '订单完成', '1', '2018-04-18 11:59:02', '1', '2018-04-18 11:59:02');
INSERT INTO `sys_dic` VALUES ('171636872622125056', '171636686013345792', '00', '平台商户', '1', '1', '1', '平台商户', '1', '2018-04-19 15:03:39', '1', '2018-04-19 15:03:39');
INSERT INTO `sys_dic` VALUES ('171636970420711424', '171636686013345792', '01', '私有商户', '2', '1', '1', '私有商户', '1', '2018-04-19 15:04:03', '1', '2018-04-19 15:04:03');
INSERT INTO `sys_dic` VALUES ('905354318158655488', '905354042106343424', 'APP_TOKEN', '259200', '1', '1', '1', 'apptoken过期时间', '1', '2018-04-18 10:29:10', '1', '2017-09-22 11:51:24');
INSERT INTO `sys_dic` VALUES ('907498362649346048', '907498118721208320', '0001', '/sequence/pay', '1', '0', '1', '支付订单流水号', '1', '2018-04-18 10:24:06', '1', '2018-04-18 10:24:07');
INSERT INTO `sys_dic` VALUES ('907503626261811200', '907498118721208320', '0002', '/sequence/transfer', '2', '0', '1', '转账订单流水号', '1', '2018-04-18 10:26:02', '1', '2018-04-18 10:26:03');
INSERT INTO `sys_dic` VALUES ('907503765667893248', '907498118721208320', '0003', '/sequence/refund', '3', '0', '1', '退款订单流水号', '1', '2018-04-18 10:26:31', '1', '2018-04-18 10:26:32');
INSERT INTO `sys_dic` VALUES ('910753594653196288', '910753141706113024', '0001', '本次操作验证码： ##smsCode## ，如非本人操作，请忽略。', '1', '1', '1', '注册验证码模板', '1', '2018-04-18 10:29:06', '1', '2017-09-21 14:32:33');
INSERT INTO `sys_dic` VALUES ('911128170872573952', '905354042106343424', 'SMS_CODE', '7200', '2', '1', '1', '验证码过期时间', '1', '2018-04-18 10:29:09', '1', '2017-09-22 15:20:59');

-- ----------------------------
-- Table structure for sys_dic_index
-- ----------------------------
DROP TABLE IF EXISTS `sys_dic_index`;
CREATE TABLE `sys_dic_index` (
  `id_` bigint(32) NOT NULL,
  `catalog_id` bigint(32) NOT NULL DEFAULT '0',
  `key_value` varchar(50) DEFAULT NULL,
  `key_name` varchar(200) DEFAULT NULL,
  `enable_` tinyint(1) DEFAULT '1',
  `remark_` varchar(1000) DEFAULT NULL,
  `create_by` bigint(32) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_by` bigint(32) NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id_`),
  UNIQUE KEY `code` (`key_value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典索引表';

-- ----------------------------
-- Records of sys_dic_index
-- ----------------------------
INSERT INTO `sys_dic_index` VALUES ('1', '6', 'SEX', '性别', '1', null, '1', '2016-06-28 18:04:06', '1', '2016-07-30 00:06:53');
INSERT INTO `sys_dic_index` VALUES ('2', '6', 'LOCKED', '锁定', '1', null, '1', '2016-06-28 18:04:06', '1', '2016-07-30 00:06:49');
INSERT INTO `sys_dic_index` VALUES ('3', '5', 'ROLETYPE', '角色类型', '1', null, '1', '2016-07-30 00:06:30', '1', '2016-07-30 00:06:50');
INSERT INTO `sys_dic_index` VALUES ('5', '5', 'EDITABLE', '编辑模式', '1', null, '1', '2016-07-30 00:06:33', '1', '2016-07-30 00:06:47');
INSERT INTO `sys_dic_index` VALUES ('6', '5', 'ENABLE', '启用状态', '1', null, '1', '2016-07-30 00:06:35', '1', '2016-07-30 00:06:45');
INSERT INTO `sys_dic_index` VALUES ('8', '5', 'MENUTYPE', '菜单类型', '1', null, '1', '2016-07-30 00:06:38', '1', '2016-07-30 00:06:40');
INSERT INTO `sys_dic_index` VALUES ('9', '5', 'USERTYPE', '人员类型', '1', null, '1', '2016-07-30 00:06:43', '1', '2016-07-30 00:06:41');
INSERT INTO `sys_dic_index` VALUES ('171227628970180608', '0', 'PAYSTATUS', '支付订单状态', '1', null, '1', '2018-04-18 11:57:28', '1', '2018-04-18 11:57:28');
INSERT INTO `sys_dic_index` VALUES ('171636686013345792', '0', 'MCHTYPE', '商户类型', '1', null, '1', '2018-04-19 15:02:55', '1', '2018-04-19 15:02:55');
INSERT INTO `sys_dic_index` VALUES ('905354042106343424', '0', 'TOKEN_EXPIRES', 'token时效', '1', null, '1', '2017-09-06 16:56:39', '1', '2017-09-06 16:56:39');
INSERT INTO `sys_dic_index` VALUES ('907498118721208320', '0', 'SEQUENCE', '序列号', '1', null, '1', '2017-09-12 14:56:27', '1', '2017-09-12 14:56:27');
INSERT INTO `sys_dic_index` VALUES ('910753141706113024', '0', 'SMS_TEMPLATE', '短信模板', '1', null, '1', '2017-09-21 14:30:45', '1', '2017-09-21 14:30:45');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id_` bigint(32) NOT NULL,
  `parent_id` bigint(32) DEFAULT NULL COMMENT '上级id',
  `menu_name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `menu_type` char(1) DEFAULT NULL COMMENT '菜单类型（0：目录 1：菜单 2：按钮）',
  `menu_key` varchar(50) DEFAULT NULL COMMENT '菜单key',
  `request_` varchar(50) DEFAULT NULL COMMENT '请求url',
  `icon_` varchar(50) DEFAULT NULL COMMENT 'css图标样式',
  `is_hide` char(1) DEFAULT NULL COMMENT '是否隐藏',
  `sort_` char(5) DEFAULT NULL COMMENT '排序号',
  `enable_` tinyint(1) DEFAULT NULL,
  `remark_` varchar(200) DEFAULT NULL,
  `create_by` bigint(32) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_by` bigint(32) DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统菜单';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统基础管理', '1', null, null, 'fa fa-desktop', '0', '1', '1', null, '1', '2017-06-21 14:32:30', '1', '2017-06-20 17:25:55');
INSERT INTO `sys_menu` VALUES ('2', '1', '用户管理', '2', 'manager:user:read', '/user/list', '', '0', '2', '1', null, '1', '2017-07-04 16:47:24', '1', '2017-07-04 16:47:24');
INSERT INTO `sys_menu` VALUES ('3', '1', '菜单管理', '2', 'manager:menu:read', '/menu/list', '', '0', '3', '1', null, '1', '2017-06-29 16:29:58', '1', '2017-06-20 17:31:57');
INSERT INTO `sys_menu` VALUES ('4', '1', '角色管理', '2', 'manager:role:read', '/role/list', '', '0', '4', '1', null, '1', '2017-06-26 17:58:10', '1', '2017-06-20 17:32:49');
INSERT INTO `sys_menu` VALUES ('8', '4', '新增角色', '3', 'manager:role:add', '/role/add', null, '0', '5', '1', null, '1', '2018-04-18 10:16:37', '1', '2017-06-26 17:59:04');
INSERT INTO `sys_menu` VALUES ('9', '3', '新增菜单', '3', 'manager:menu:add', '/menu/add', null, '0', '6', '1', null, '1', '2018-04-18 10:16:38', '1', '2017-06-26 17:59:04');
INSERT INTO `sys_menu` VALUES ('10', '3', '编辑菜单', '3', 'manager:menu:edit', '/menu/edit', null, '0', '7', '1', null, '1', '2018-04-18 10:16:39', '1', '2017-06-26 17:59:04');
INSERT INTO `sys_menu` VALUES ('11', '3', '删除菜单', '3', 'manager:menu:del', '/menu/del', null, '0', '8', '1', null, '1', '2018-04-18 10:16:40', '1', '2017-06-26 17:59:04');
INSERT INTO `sys_menu` VALUES ('12', '4', '编辑角色', '3', 'manager:role:edit', '/role/edit', null, '0', '9', '1', null, '1', '2018-04-18 10:16:43', '1', '2017-07-03 08:59:58');
INSERT INTO `sys_menu` VALUES ('13', '4', '删除角色', '3', 'manager:role:del', '/role/del', null, '0', '10', '1', null, '1', '2018-04-18 10:16:45', '1', '2017-07-03 09:00:51');
INSERT INTO `sys_menu` VALUES ('14', '4', '分配权限', '3', 'manager:role:auth', '/role/auth', null, '0', '11', '1', null, '1', '2018-04-18 10:16:46', '1', '2017-07-03 10:50:31');
INSERT INTO `sys_menu` VALUES ('165513593922523136', '883138222408269824', '会话列表', '2', 'manager:session:read', '/session/list', null, '0', '12', '1', null, '1', '2018-04-18 10:16:46', '1', '2018-04-02 17:41:29');
INSERT INTO `sys_menu` VALUES ('165753590428729344', '165513593922523136', '强制退出', '3', 'manager:session:forceout', '/session/forceout', null, '0', '13', '1', null, '1', '2018-04-18 10:16:47', '1', '2018-04-03 09:25:35');
INSERT INTO `sys_menu` VALUES ('166201582835204096', '0', '订单管理', '1', '-', '-', 'fa fa-desktop', '0', '14', '1', null, '1', '2018-04-18 10:16:49', '1', '2018-04-04 15:06:02');
INSERT INTO `sys_menu` VALUES ('166202003960102912', '166201582835204096', '支付订单', '2', 'manager:order:read', '/order/list', null, '0', '15', '1', null, '1', '2018-04-18 10:16:50', '1', '2018-04-04 15:07:25');
INSERT INTO `sys_menu` VALUES ('170956909011013632', '1', '字典管理', '2', 'manager:dictIndex:read', '/dictIndex/list', null, '0', '16', '1', null, '1', '2018-04-18 10:16:51', '1', '2018-04-17 18:01:43');
INSERT INTO `sys_menu` VALUES ('171188640246931456', '170956909011013632', '新增字典', '3', 'manager:dictIndex:add', '/dictIndex/add', null, '0', '17', '1', null, '1', '2018-04-18 10:16:53', '1', '2018-04-18 09:22:32');
INSERT INTO `sys_menu` VALUES ('171194020414885888', '170956909011013632', '删除字典', '3', 'manager:dictIndex:del', '/dictIndex/del', null, '0', '18', '1', null, '1', '2018-04-18 10:16:55', '1', '2018-04-18 09:43:55');
INSERT INTO `sys_menu` VALUES ('171194243107262464', '170956909011013632', '编辑字典', '3', 'manager:dictIndex:edit', '/dictIndex/edit', null, '0', '19', '1', null, '1', '2018-04-18 10:16:56', '1', '2018-04-18 09:44:48');
INSERT INTO `sys_menu` VALUES ('171305390070308864', '0', '商户配置', '1', '-', '-', 'fa fa-desktop', '0', '25', '1', null, '1', '2018-04-18 17:08:37', '1', '2018-04-18 17:06:27');
INSERT INTO `sys_menu` VALUES ('171305767331176448', '171305390070308864', '商户信息', '2', 'manager:mchinfo:read', '/mchinfo/list', null, '0', '26', '1', null, '1', '2018-04-19 09:57:06', '1', '2018-04-18 17:07:57');
INSERT INTO `sys_menu` VALUES ('171313540404092928', '171305767331176448', '新增商户', '3', 'manager:mchinfo:add', '/mchinfo/add', null, '0', '26', '1', null, '1', '2018-04-18 17:38:51', '1', '2018-04-18 17:38:50');
INSERT INTO `sys_menu` VALUES ('882402342949298176', '2', '新增用户', '3', 'manager:user:add', '/user/add', null, '0', '20', '1', null, '1', '2018-04-18 10:16:58', '1', '2017-07-05 21:34:22');
INSERT INTO `sys_menu` VALUES ('882592150598332416', '2', '编辑用户', '3', 'manager:user:edit', '/user/edit', null, '0', '21', '1', null, '1', '2018-04-18 10:16:59', '1', '2017-07-05 21:29:01');
INSERT INTO `sys_menu` VALUES ('882592261944520704', '2', '删除用户', '3', 'manager:user:del', '/user/del', null, '0', '22', '1', null, '1', '2018-04-18 10:17:00', '1', '2017-07-05 21:29:28');
INSERT INTO `sys_menu` VALUES ('882612153192165376', '2', '用户角色', '3', 'manager:user:role', '/user/role', null, '0', '23', '1', null, '1', '2018-04-18 10:17:02', '1', '2017-07-05 22:48:30');
INSERT INTO `sys_menu` VALUES ('883138222408269824', '0', '会话管理', '1', '-', '-', 'fa fa-desktop', '0', '24', '1', null, '1', '2018-04-18 10:17:04', '1', '2017-07-07 09:38:55');

-- ----------------------------
-- Table structure for sys_menu_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_user`;
CREATE TABLE `sys_menu_user` (
  `id_` bigint(32) NOT NULL,
  `menu_id` bigint(32) DEFAULT NULL,
  `user_id` bigint(32) DEFAULT NULL,
  `enable_` tinyint(1) DEFAULT NULL,
  `remark_` varchar(200) DEFAULT NULL,
  `create_by` bigint(32) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_by` bigint(32) DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单用户关联表';

-- ----------------------------
-- Records of sys_menu_user
-- ----------------------------
INSERT INTO `sys_menu_user` VALUES ('171194386674094080', '3', '1', '1', null, null, '2018-04-18 09:45:22', null, '2018-04-18 09:45:22');
INSERT INTO `sys_menu_user` VALUES ('171194386682482688', '4', '1', '1', null, null, '2018-04-18 09:45:22', null, '2018-04-18 09:45:22');
INSERT INTO `sys_menu_user` VALUES ('171194386690871296', '8', '1', '1', null, null, '2018-04-18 09:45:22', null, '2018-04-18 09:45:22');
INSERT INTO `sys_menu_user` VALUES ('171194386695065600', '12', '1', '1', null, null, '2018-04-18 09:45:22', null, '2018-04-18 09:45:22');
INSERT INTO `sys_menu_user` VALUES ('171194386699259904', '13', '1', '1', null, null, '2018-04-18 09:45:22', null, '2018-04-18 09:45:22');
INSERT INTO `sys_menu_user` VALUES ('171194386711842816', '14', '1', '1', null, null, '2018-04-18 09:45:22', null, '2018-04-18 09:45:22');
INSERT INTO `sys_menu_user` VALUES ('171194386720231424', '882592261944520704', '1', '1', null, null, '2018-04-18 09:45:22', null, '2018-04-18 09:45:22');
INSERT INTO `sys_menu_user` VALUES ('171194386724425728', '9', '1', '1', null, null, '2018-04-18 09:45:22', null, '2018-04-18 09:45:22');
INSERT INTO `sys_menu_user` VALUES ('171194386728620032', '10', '1', '1', null, null, '2018-04-18 09:45:22', null, '2018-04-18 09:45:22');
INSERT INTO `sys_menu_user` VALUES ('171194386737008640', '11', '1', '1', null, null, '2018-04-18 09:45:22', null, '2018-04-18 09:45:22');
INSERT INTO `sys_menu_user` VALUES ('171194386745397248', '883138222408269824', '1', '1', null, null, '2018-04-18 09:45:22', null, '2018-04-18 09:45:22');
INSERT INTO `sys_menu_user` VALUES ('171194386749591552', '165513593922523136', '1', '1', null, null, '2018-04-18 09:45:22', null, '2018-04-18 09:45:22');
INSERT INTO `sys_menu_user` VALUES ('171194386753785856', '165753590428729344', '1', '1', null, null, '2018-04-18 09:45:22', null, '2018-04-18 09:45:22');
INSERT INTO `sys_menu_user` VALUES ('171194386762174464', '1', '1', '1', null, null, '2018-04-18 09:45:22', null, '2018-04-18 09:45:22');
INSERT INTO `sys_menu_user` VALUES ('171194386766368768', '2', '1', '1', null, null, '2018-04-18 09:45:22', null, '2018-04-18 09:45:22');
INSERT INTO `sys_menu_user` VALUES ('171194386774757376', '882402342949298176', '1', '1', null, null, '2018-04-18 09:45:22', null, '2018-04-18 09:45:22');
INSERT INTO `sys_menu_user` VALUES ('171194386778951680', '170956909011013632', '1', '1', null, null, '2018-04-18 09:45:22', null, '2018-04-18 09:45:22');
INSERT INTO `sys_menu_user` VALUES ('171194386783145984', '171188640246931456', '1', '1', null, null, '2018-04-18 09:45:22', null, '2018-04-18 09:45:22');
INSERT INTO `sys_menu_user` VALUES ('171194386791534592', '171194020414885888', '1', '1', null, null, '2018-04-18 09:45:22', null, '2018-04-18 09:45:22');
INSERT INTO `sys_menu_user` VALUES ('171194386795728896', '171194243107262464', '1', '1', null, null, '2018-04-18 09:45:22', null, '2018-04-18 09:45:22');
INSERT INTO `sys_menu_user` VALUES ('171194386804117504', '882592150598332416', '1', '1', null, null, '2018-04-18 09:45:22', null, '2018-04-18 09:45:22');
INSERT INTO `sys_menu_user` VALUES ('171194386812506112', '882612153192165376', '1', '1', null, null, '2018-04-18 09:45:22', null, '2018-04-18 09:45:22');
INSERT INTO `sys_menu_user` VALUES ('171314511834259456', '166201582835204096', '882861947399626752', '1', null, null, '2018-04-18 17:42:42', null, '2018-04-18 17:42:42');
INSERT INTO `sys_menu_user` VALUES ('171314511838453760', '166202003960102912', '882861947399626752', '1', null, null, '2018-04-18 17:42:42', null, '2018-04-18 17:42:42');
INSERT INTO `sys_menu_user` VALUES ('171314511842648064', '171305390070308864', '882861947399626752', '1', null, null, '2018-04-18 17:42:42', null, '2018-04-18 17:42:42');
INSERT INTO `sys_menu_user` VALUES ('171314511846842368', '171305767331176448', '882861947399626752', '1', null, null, '2018-04-18 17:42:42', null, '2018-04-18 17:42:42');
INSERT INTO `sys_menu_user` VALUES ('171314511851036672', '171313540404092928', '882861947399626752', '1', null, null, '2018-04-18 17:42:42', null, '2018-04-18 17:42:42');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id_` bigint(32) NOT NULL,
  `role_name` varchar(50) DEFAULT NULL,
  `role_type` char(1) DEFAULT NULL,
  `role_key` varchar(50) DEFAULT NULL,
  `status_` char(1) DEFAULT NULL,
  `enable_` tinyint(1) DEFAULT NULL,
  `remark_` varchar(200) DEFAULT NULL,
  `create_by` bigint(32) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_by` bigint(32) DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '系统管理员', '1', '1', '1', '1', '1', '1', '2017-06-22 11:11:53', '1', '2017-06-22 11:11:53');
INSERT INTO `sys_role` VALUES ('2', '普通管理员', '2', '1', '1', '1', '1', '1', '2017-06-22 11:12:06', '1', '2017-06-22 11:12:06');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id_` bigint(32) NOT NULL,
  `role_id` bigint(32) DEFAULT NULL,
  `menu_id` bigint(32) DEFAULT NULL,
  `enable_` tinyint(1) DEFAULT NULL,
  `remark_` varchar(200) DEFAULT NULL,
  `create_by` bigint(32) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_by` bigint(32) DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('3', '1', '3', '1', '1', '1', '2017-06-20 17:35:42', '1', '2017-06-20 17:35:42');
INSERT INTO `sys_role_menu` VALUES ('4', '1', '4', '1', '1', '1', '2017-06-20 17:35:51', '1', '2017-06-20 17:35:51');
INSERT INTO `sys_role_menu` VALUES ('8', '1', '8', '1', '1', '1', '2017-07-03 10:53:40', '1', '2017-07-03 10:53:40');
INSERT INTO `sys_role_menu` VALUES ('12', '1', '12', '1', '1', '1', '2017-07-03 17:54:06', '1', '2017-07-03 11:03:55');
INSERT INTO `sys_role_menu` VALUES ('13', '1', '13', '1', '1', '1', '2017-07-03 17:54:15', '1', '2017-07-03 11:04:03');
INSERT INTO `sys_role_menu` VALUES ('14', '1', '14', '1', '1', '1', '2017-07-03 17:54:20', '1', '2017-07-03 11:05:45');
INSERT INTO `sys_role_menu` VALUES ('165478643974279168', '1', '882592261944520704', '1', null, null, '2018-04-02 15:13:03', null, '2018-04-02 15:13:03');
INSERT INTO `sys_role_menu` VALUES ('165507746911748096', '1', '9', '1', null, null, '2018-04-02 17:08:42', null, '2018-04-02 17:08:44');
INSERT INTO `sys_role_menu` VALUES ('165507757993099264', '1', '10', '1', null, null, '2018-04-02 17:08:44', null, '2018-04-02 17:08:46');
INSERT INTO `sys_role_menu` VALUES ('165507764498464768', '1', '11', '1', null, null, '2018-04-02 17:08:46', null, '2018-04-02 17:08:46');
INSERT INTO `sys_role_menu` VALUES ('165512527348764672', '1', '883138222408269824', '1', null, null, '2018-04-02 17:27:42', null, '2018-04-02 17:27:50');
INSERT INTO `sys_role_menu` VALUES ('165522139393298432', '1', '165513593922523136', '1', null, null, '2018-04-02 18:05:53', null, '2018-04-02 18:05:53');
INSERT INTO `sys_role_menu` VALUES ('165756131119992832', '1', '165753590428729344', '1', null, null, '2018-04-03 09:35:41', null, '2018-04-03 09:35:41');
INSERT INTO `sys_role_menu` VALUES ('166205392345370624', '2', '166201582835204096', '1', null, null, '2018-04-04 15:20:53', null, '2018-04-04 15:20:53');
INSERT INTO `sys_role_menu` VALUES ('166205392374730752', '2', '166202003960102912', '1', null, null, '2018-04-04 15:20:53', null, '2018-04-04 15:20:53');
INSERT INTO `sys_role_menu` VALUES ('170957105627402240', '1', '1', '1', null, null, '2018-04-17 18:02:30', null, '2018-04-17 18:02:30');
INSERT INTO `sys_role_menu` VALUES ('170957105673539584', '1', '2', '1', null, null, '2018-04-17 18:02:30', null, '2018-04-17 18:02:30');
INSERT INTO `sys_role_menu` VALUES ('170957105681928192', '1', '882402342949298176', '1', null, null, '2018-04-17 18:02:30', null, '2018-04-17 18:02:30');
INSERT INTO `sys_role_menu` VALUES ('170957105690316800', '1', '170956909011013632', '1', null, null, '2018-04-17 18:02:30', null, '2018-04-17 18:02:30');
INSERT INTO `sys_role_menu` VALUES ('171192469231247360', '1', '171188640246931456', '1', null, null, '2018-04-18 09:37:45', null, '2018-04-18 09:37:45');
INSERT INTO `sys_role_menu` VALUES ('171194348195549184', '1', '171194020414885888', '1', null, null, '2018-04-18 09:45:13', null, '2018-04-18 09:45:13');
INSERT INTO `sys_role_menu` VALUES ('171194348266852352', '1', '171194243107262464', '1', null, null, '2018-04-18 09:45:13', null, '2018-04-18 09:45:13');
INSERT INTO `sys_role_menu` VALUES ('171305488128942080', '2', '171305390070308864', '1', null, null, '2018-04-18 17:06:51', null, '2018-04-18 17:06:51');
INSERT INTO `sys_role_menu` VALUES ('171305806673747968', '2', '171305767331176448', '1', null, null, '2018-04-18 17:08:07', null, '2018-04-18 17:08:07');
INSERT INTO `sys_role_menu` VALUES ('171313587078307840', '2', '171313540404092928', '1', null, null, '2018-04-18 17:39:02', null, '2018-04-18 17:39:02');
INSERT INTO `sys_role_menu` VALUES ('882593540217384960', '1', '882592150598332416', '1', '1', '1', '2017-07-05 21:39:39', '1', '2017-07-05 21:34:32');
INSERT INTO `sys_role_menu` VALUES ('882612207441293312', '1', '882612153192165376', '1', null, null, '2017-07-05 22:48:43', null, '2017-07-05 22:48:43');

-- ----------------------------
-- Table structure for sys_system
-- ----------------------------
DROP TABLE IF EXISTS `sys_system`;
CREATE TABLE `sys_system` (
  `id_` bigint(32) NOT NULL COMMENT '编号',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `banner` varchar(150) DEFAULT NULL COMMENT '背景',
  `theme` varchar(50) DEFAULT NULL COMMENT '主题',
  `basepath` varchar(100) DEFAULT NULL COMMENT '根目录',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态(-1:黑名单,1:正常)',
  `name` varchar(20) DEFAULT NULL COMMENT '系统名称',
  `title` varchar(20) DEFAULT NULL COMMENT '系统标题',
  `description` varchar(300) DEFAULT NULL COMMENT '系统描述',
  `enable_` tinyint(1) DEFAULT NULL COMMENT '创建时间',
  `sort_no` bigint(20) DEFAULT NULL COMMENT '排序',
  `remark_` varchar(255) DEFAULT NULL,
  `create_by` bigint(32) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_by` bigint(32) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统';

-- ----------------------------
-- Records of sys_system
-- ----------------------------
-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id_` bigint(32) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户昵称',
  `login_name` varchar(255) DEFAULT NULL COMMENT '登录名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `salt` varchar(255) DEFAULT NULL COMMENT '盐值',
  `real_name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `avatar` varchar(150) DEFAULT NULL COMMENT '头像',
  `register_type` char(1) DEFAULT NULL COMMENT '注册类型(0:系统添加，1：手机号注册,2:第三方登录)',
  `user_type` char(1) DEFAULT NULL COMMENT '用户类型(0管理员 1系统用户)',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `sex` tinyint(4) DEFAULT NULL COMMENT '性别(0 女 1男)',
  `locked_` tinyint(1) DEFAULT NULL,
  `login_num` int(11) DEFAULT NULL COMMENT '登录次数',
  `login_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '当前登录时间',
  `old_login_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上一次登录时间',
  `login_ip` varchar(20) DEFAULT NULL COMMENT '当前登录ip',
  `old_login_ip` varchar(20) DEFAULT NULL COMMENT '上次登录ip',
  `status_` char(1) DEFAULT NULL COMMENT '状态(0:正常 1:冻结 ...)',
  `enable_` tinyint(1) DEFAULT NULL COMMENT '是否可用(0: 否 1:是)',
  `remark_` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_by` bigint(32) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint(32) DEFAULT NULL COMMENT '更新人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'admin', '3038D9CB63B3152A79B8153FB06C02F7', '66f1b370c660445a8657bf8bf1794486', '涂勇', 'http://122.114.96.161:8888/group1/M00/00/00/enJgoVqrakSALJSfAAVL_5MzVJ4789.png', '1', '1', '18559756158', '192312@qq.com', '1', null, '203', '2018-04-19 15:02:02', '2018-04-18 17:02:34', '192.168.56.1', '192.168.56.1', '1', '1', null, '1', '2017-06-20 15:02:41', '1', '2018-04-19 15:02:02');
INSERT INTO `sys_user` VALUES ('882861947399626752', 'tuyong212', 'tuyong', '940817937DA2C24B5D40514058C21033', 'd77ccdb686f04164a105f609532a19f6', null, 'http://192.168.1.220:8888/group1/M00/00/00/wKgB3Fpgo8aAdaUTAADf_sA3_Oc435.jpg', '1', '1', '18559756159', '1972873004@qq.com', '1', null, '22', '2018-04-23 09:22:17', '2018-04-19 14:57:45', '192.168.56.1', '192.168.56.1', '1', '1', null, '1', '2017-07-06 15:21:06', '1', '2018-04-23 09:22:17');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id_` bigint(32) NOT NULL,
  `user_id` bigint(32) DEFAULT NULL,
  `role_id` bigint(32) DEFAULT NULL,
  `enable_` tinyint(1) DEFAULT NULL,
  `remark_` varchar(200) DEFAULT NULL,
  `create_by` bigint(32) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_by` bigint(32) DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('165464916700954624', '1', '1', '0', null, null, '2018-04-18 09:45:22', '1', '2018-04-18 09:45:22');
INSERT INTO `sys_user_role` VALUES ('165480802728017920', '1', '1', '0', null, null, '2018-04-18 09:45:22', '1', '2018-04-18 09:45:22');
INSERT INTO `sys_user_role` VALUES ('165501843072352256', '1', '1', '0', null, null, '2018-04-18 09:45:22', '1', '2018-04-18 09:45:22');
INSERT INTO `sys_user_role` VALUES ('165510992317382656', '1', '1', '0', null, null, '2018-04-18 09:45:22', '1', '2018-04-18 09:45:22');
INSERT INTO `sys_user_role` VALUES ('165512627886231552', '1', '1', '0', null, null, '2018-04-18 09:45:22', '1', '2018-04-18 09:45:22');
INSERT INTO `sys_user_role` VALUES ('165522191390085120', '1', '1', '0', null, null, '2018-04-18 09:45:22', '1', '2018-04-18 09:45:22');
INSERT INTO `sys_user_role` VALUES ('165757566092050432', '1', '1', '0', null, null, '2018-04-18 09:45:22', '1', '2018-04-18 09:45:22');
INSERT INTO `sys_user_role` VALUES ('166107288573054976', '882861947399626752', '2', '0', null, null, '2018-04-18 17:42:42', '1', '2018-04-18 17:42:42');
INSERT INTO `sys_user_role` VALUES ('166205778221338624', '882861947399626752', '2', '0', null, null, '2018-04-18 17:42:42', '1', '2018-04-18 17:42:42');
INSERT INTO `sys_user_role` VALUES ('166206607837892608', '882861947399626752', '2', '0', null, null, '2018-04-18 17:42:42', '1', '2018-04-18 17:42:42');
INSERT INTO `sys_user_role` VALUES ('166208698102845440', '882861947399626752', '2', '0', null, null, '2018-04-18 17:42:42', '1', '2018-04-18 17:42:42');
INSERT INTO `sys_user_role` VALUES ('166209063921651712', '882861947399626752', '2', '0', null, null, '2018-04-18 17:42:42', '1', '2018-04-18 17:42:42');
INSERT INTO `sys_user_role` VALUES ('166209184507891712', '882861947399626752', '2', '0', null, null, '2018-04-18 17:42:42', '1', '2018-04-18 17:42:42');
INSERT INTO `sys_user_role` VALUES ('166214010977394688', '882861947399626752', '2', '0', null, null, '2018-04-18 17:42:42', '1', '2018-04-18 17:42:42');
INSERT INTO `sys_user_role` VALUES ('170957130382184448', '1', '1', '0', null, null, '2018-04-18 09:45:22', '1', '2018-04-18 09:45:22');
INSERT INTO `sys_user_role` VALUES ('171192528677117952', '1', '1', '0', null, null, '2018-04-18 09:45:22', '1', '2018-04-18 09:45:22');
INSERT INTO `sys_user_role` VALUES ('171194386653122560', '1', '1', '1', null, null, '2018-04-18 09:45:22', null, '2018-04-18 09:45:22');
INSERT INTO `sys_user_role` VALUES ('171305525495996416', '882861947399626752', '2', '0', null, null, '2018-04-18 17:42:42', '1', '2018-04-18 17:42:42');
INSERT INTO `sys_user_role` VALUES ('171305833819283456', '882861947399626752', '2', '0', null, null, '2018-04-18 17:42:42', '1', '2018-04-18 17:42:42');
INSERT INTO `sys_user_role` VALUES ('171314511767150592', '882861947399626752', '2', '1', null, null, '2018-04-18 17:42:42', null, '2018-04-18 17:42:42');
INSERT INTO `sys_user_role` VALUES ('882881064194240512', '882861947399626752', '11', '0', null, null, '2018-04-18 17:42:42', '1', '2018-04-18 17:42:42');
INSERT INTO `sys_user_role` VALUES ('882881067247693824', '882861947399626752', '10', '0', null, null, '2018-04-18 17:42:42', '1', '2018-04-18 17:42:42');
INSERT INTO `sys_user_role` VALUES ('955338601545175042', '1', '1', '0', null, null, '2018-04-18 09:45:22', '1', '2018-04-18 09:45:22');
