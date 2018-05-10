/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : tpay

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2018-05-10 16:12:09
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
INSERT INTO `mch_info` VALUES ('1000001', '8000000000', 'Trazen1', '00', 'MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC6nUK7p5+SoM0bgZh83qwBlanEFI26jhnDCCIEEVoo5G3H91T0YdRnAon5hgBDy+V61RRN9mSLqCjPyRE9nRbAWuw2I2YcTLrQinuzsOtTT88cBija79U+uPnuJWjJyQlK48hBpvsRu2PXP58Noug23BHBtC4Asjlho40HIfE4TqwHyoJWfuTmc3/p7gQqZwknA0JG2LWFmy14kM0A+3PWdkEY/ypbwXYioJ5t+hd8r68Fip8GjZdsetUMTWtKOKDa47MZoLX0YdnMojSxD5zuoc7amLzak0Miocc6og6bynAF2ZfQQMcjxFyRKJHbqvROMYk3A34m2ALX3s2+oPb7AgMBAAECggEAcmH2B2OlEZDyZ0u2FcO+lnIPzpnyjUiQTdTsVKX2J15sU8csEWbtc/5AZ+tUujl9/R4iBe9ijZ+S9Fl/8c4ZpemVI1HrQqldUHmxfOCSPUyL29eCrz+V644h03CmnBhXU8nucx9QgPvlJIhgi1ExGH8nex1fnRmgxlIjHR4W5rV17aiVvyuTxMhB0giB7EFwvirrQhQOaRa3IQ6N28E7n58lEKsnqCpRM0S3WIFCPcICvA3b4v5QctltREWTJPYAeeOdqB37vij7rq5AoSFF1OGauCp8kwIKSe87lbXQodsQkY3Ois85uQQtUGp4z/lEWi5rqvmHkt1lblKRtx9DsQKBgQDiRoHy20E7HWVpK8sQIvUzJag2Z/UiSnIfIakcNqfhCqjEkO5ro5esV6a8HoLf2rrefy+um+ifMPjr04e611lz9D6r0ZQ61Ev09zjEtN+Xvu2RnQgrBdhHhfCIR5+7+ec1xwyrYrV3YGERM1hG0HwzoNvltClyyLQ+vpqmD2kvxQKBgQDTIPnSDe/bUDlyQYS6DmHo+T0cLdDFm6ok9vtghyLgJSVj21Uf0krXnKvJUiAvHKinsxOZ4TRwthxDJUDERx7rvrYcWhykwJbRpz8zwJNxwRyxn8bCnMw7gdCAca5TWSYqeYsqzVSg6hW4nrQ7U0KBsNJr3d/0eUT3ji8I8Yc3vwKBgQDNLSX0MwAJPHSSUxWEgk5YnJLVEprjByJIPFt9q8m6c9Hou4qVq/eCXNBh0EDX/xxnWGjCKblbcCqmnF58+3yveg/B+P4yAgMGE440P9ZnYMdGvF+Fs30UDc23pUqgRtByoRVJ6u2lW41o7WfkfnPA1OHQffb/kCJwqqDMZzj4CQKBgQCwHgjnVWLpMqHJEqhyP/8ixY5ZjEpkHPcwgKqvGetYyQPIqbT3p4dxFqsidBSO2DxEMBjAu2DwSKmIxXGiiaVciCkHu4+7S0BEQVxygkk8kheqgBrgSsX/OcnA65O8yVgSBHO3z7KxzLMz34d/GXQYFXViL6JbIDIMw5muvzPJpwKBgQCvarqEy0jJA29SMvPIMLEsjm98AzkGHU0J0YG7OaPwnuE1pPKInYksquwGJkEUhfrtxns+7bYuO+9bIH2AJ+LMKx96RyWjVqLCWVSZg/E7A9z7yVjlvzJGiukw2YEmPASiJLYyTeYnHKO47bgUnc/4JXumaPSZ9WcS7dFl51Tulw==', 'MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAup1Cu6efkqDNG4GYfN6sAZWpxBSNuo4ZwwgiBBFaKORtx/dU9GHUZwKJ+YYAQ8vletUUTfZki6goz8kRPZ0WwFrsNiNmHEy60Ip7s7DrU0/PHAYo2u/VPrj57iVoyckJSuPIQab7Ebtj1z+fDaLoNtwRwbQuALI5YaONByHxOE6sB8qCVn7k5nN/6e4EKmcJJwNCRti1hZsteJDNAPtz1nZBGP8qW8F2IqCebfoXfK+vBYqfBo2XbHrVDE1rSjig2uOzGaC19GHZzKI0sQ+c7qHO2pi82pNDIqHHOqIOm8pwBdmX0EDHI8RckSiR26r0TjGJNwN+JtgC197NvqD2+wIDAQAB', 'MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDHojxgxIzTDVgpiitiF+MpYqA+zvu+3GsFNqt1kCqBq9JaqISPmTinvzblEcvh+4n+95UvRNeN+Ecc5tT4HM4lACsFWa6VKkJuTcayL0hCQSeayDvjsBYeIMu4nPAuLkxNQNS8Q5wu/S2vjmLhByE+pc7iOegPOYnVJ4qu3gYHDFJBYBcmt3c6z1+a1QYEXsxkeK2LTYYCDygG0hUipbjCWiD//RTRwMEAfkXCZUrWdee+bXaQs7Vj20LZiTY+UlxWMzbmqDVMu4aE9Sb8rWQlrLMylEuxmntQ2BlI49E+1ljEJ/VwD8RjHOAYeYVxXz9iVIQcf1Dj7TGvtdgTCjUlAgMBAAECggEABP7iU+mXPJDQ6/kxLLs8TATTzb6QPi513yOHFuIMrkIGeY5OAunqqQf377OKTpVk1qSLbyth2YOlA+wi2DKgVfyzFucvnzv3fZskpcDZJg9LVmw8U25Mn2RitLP4rq8p6aT9rdhjSCm9wu7lOoxbIJ41lyOr0vW1pUfgCJA1xmdVNgun910Ib8Fv1zjPPzDbwKxpU2Xa2gdT4BpS8GIaJy3ipuEoZVCJM3xVmYYzoOXulFMBA4No0dhw7Po+7Qfq4F7oAYSTLcT4mWH9dctmmjqTb3VwIq9m/txsYGHKU5BFlFMpTH4UU+37VGgTvasaOWMudWNXrRSoCZK0RAT64QKBgQDoWV35DiSVnFVt8LOgO1qOPl7SpNQyEjj/ouog+IbYpIQG6qViRX7pKTix+W04xwcRe4JjyNFYt/ZUBUJtB3YJoXGJIf3l0KVPN12sq8lC8qnXECofTmDneM2c8d5UKEbM5k4pve4uuY35NsgJNO8kLI+UmTDc2ZXN2kb8dnB5uwKBgQDb9FxXEYyrLIt9bNa1tQUINk89lW5csk5eLT2lEZtbi7kYFz7VwI9/zGrfC89OJGssEzeqHVwQ7KJQoxQ5t6pi5S5DjkCuBq2SuJUpLcp3vDFt7JSx/OWlmRF5F56qdQ/+CXFE4a6RmjZapThUoIVaMm2LhYXbn4TLwkX8R30unwKBgQC+JUybYnCcI2liZIaLzacJG4+8MPoa1ADja+7Q9rJcHx6RDnw0FQ2MQJjORYKV4YMuUnZdtN1mDn6/OeKnmWCG9yEUCcZfycVPM88sXRWC/VXy0561Kg5xv9H86494LdHrI1q6HF4MzPucBLHlnvVKFFGYWVm8kzBeW5CWUVMwuwKBgQCXz3ugOKOfTVplUiaLOYpklZ9xgN6wKfz+8Ti34oQY1kIFR539yC8C5OLqWWtWoro5rHXh+uudXsXU5AnEoGiAY2/f/qHTuxY3yyBgUJOKuqBfH0et5T/XYON3cX74ZV2JV5kPErj7E7XqMFjFyBjiHSYQgzLVV68/NzI4olrbPwKBgQDLLsqN3qqhnESy07l7yfb9uIWebNHlREcV00loEvL3LJMWGacHvY824yrx6iAI920ZWVCbqFOyZIPSG1XngKUdarh9AV5uIdSX5rueRQCaHbtv30vhF9gcTNYSCxKj6XnvzQuxIYSLCroEPpeG7Q6SU+qv+X34yAV+5RNFhFHE5A==', 'MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAx6I8YMSM0w1YKYorYhfjKWKgPs77vtxrBTardZAqgavSWqiEj5k4p7825RHL4fuJ/veVL0TXjfhHHObU+BzOJQArBVmulSpCbk3Gsi9IQkEnmsg747AWHiDLuJzwLi5MTUDUvEOcLv0tr45i4QchPqXO4jnoDzmJ1SeKrt4GBwxSQWAXJrd3Os9fmtUGBF7MZHiti02GAg8oBtIVIqW4wlog//0U0cDBAH5FwmVK1nXnvm12kLO1Y9tC2Yk2PlJcVjM25qg1TLuGhPUm/K1kJayzMpRLsZp7UNgZSOPRPtZYxCf1cA/EYxzgGHmFcV8/YlSEHH9Q4+0xr7XYEwo1JQIDAQAB', '1', '1', null, '2018-04-23 10:39:27', '1', '2018-04-23 10:39:28', '1');
INSERT INTO `mch_info` VALUES ('171567608770854912', '8000000001', 'TEST', '00', '1', '1', '1', '1', '1', '1', null, '2018-04-23 11:00:59', '1', '2018-04-23 11:00:59', '1');
INSERT INTO `mch_info` VALUES ('173106851888500736', '8000000002', '222', '00', '231', '3113', '2131', '12321', null, '0', null, '2018-04-23 16:24:56', '1', '2018-04-23 16:24:57', '1');

-- ----------------------------
-- Table structure for notify_record
-- ----------------------------
DROP TABLE IF EXISTS `notify_record`;
CREATE TABLE `notify_record` (
  `id_` bigint(32) NOT NULL,
  `last_notify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `notify_times` int(6) DEFAULT NULL,
  `limit_notify_times` int(6) DEFAULT NULL,
  `url_` varchar(3000) DEFAULT NULL,
  `cust_no` varchar(64) DEFAULT NULL,
  `order_no` varchar(64) DEFAULT NULL,
  `status_` int(6) DEFAULT '101' COMMENT '通知状态(100:成功:101:未成功,默认101)',
  `notify_type` int(6) DEFAULT '0',
  `remark_` varchar(500) DEFAULT NULL,
  `enable_` tinyint(1) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `create_by` bigint(32) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_by` bigint(32) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notify_record
-- ----------------------------
INSERT INTO `notify_record` VALUES ('174926655612010496', '2018-05-02 15:18:43', '5', '5', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600072&tradeStatus=02&totalAmount=1.00&payTime=2018-04-28 16:56:04.148&errCode=&errCodeMsg=&sign=0a545a5b6d172b93b120bf21cfa7f28e', '1000001', '20180410171600072', '100', '1', '', '1', '2018-04-28 16:56:05', null, '2018-05-02 15:18:43', null);
INSERT INTO `notify_record` VALUES ('176334139648126976', '2018-05-02 15:03:17', '3', '5', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600073&tradeStatus=02&totalAmount=1.00&payTime=2018-05-02 14:08:54.284&errCode=&errCodeMsg=&sign=b2cedc1bd4ecea6ecb9fa9c12fd3484f', '1000001', '20180410171600073', '100', '1', '', '1', '2018-05-02 14:08:55', null, '2018-05-02 15:03:17', null);
INSERT INTO `notify_record` VALUES ('176335792191320064', '2018-05-02 15:03:26', '3', '5', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600074&tradeStatus=02&totalAmount=1.00&payTime=2018-05-02 14:15:28.972&errCode=&errCodeMsg=&sign=59c0d50ed3e1aec8966092a84d3799a6', '1000001', '20180410171600074', '100', '1', '', '1', '2018-05-02 14:15:29', null, '2018-05-02 15:03:27', null);
INSERT INTO `notify_record` VALUES ('176336659850211328', '2018-05-02 15:17:28', '3', '5', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600075&tradeStatus=02&totalAmount=1.00&payTime=2018-05-02 14:18:55.831&errCode=&errCodeMsg=&sign=211b0172ff598ec4c95883d74472be23', '1000001', '20180410171600075', '100', '1', '', '1', '2018-05-02 14:18:56', null, '2018-05-02 15:17:28', null);
INSERT INTO `notify_record` VALUES ('176339092764635136', '2018-05-02 15:17:27', '3', '5', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600076&tradeStatus=02&totalAmount=1.00&payTime=2018-05-02 14:28:35.866&errCode=&errCodeMsg=&sign=af9566f01a1a596ea9d17c10e65bf489', '1000001', '20180410171600076', '100', '1', '', '1', '2018-05-02 14:28:36', null, '2018-05-02 15:17:27', null);
INSERT INTO `notify_record` VALUES ('176348803178180608', '2018-05-02 15:15:24', '2', '5', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600077&tradeStatus=02&totalAmount=1.00&payTime=2018-05-02 15:07:10.697&errCode=&errCodeMsg=&sign=790096d896cb63bc4dcf7e825cbb3b01', '1000001', '20180410171600077', '100', '1', '', '1', '2018-05-02 15:07:11', null, '2018-05-02 15:15:24', null);
INSERT INTO `notify_record` VALUES ('176349134276538368', '2018-05-02 15:16:23', '2', '5', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600078&tradeStatus=02&totalAmount=1.00&payTime=2018-05-02 15:08:29.959&errCode=&errCodeMsg=&sign=9ac844f9958372b222d7f74bec0189f5', '1000001', '20180410171600078', '100', '1', '', '1', '2018-05-02 15:08:30', null, '2018-05-02 15:16:24', null);
INSERT INTO `notify_record` VALUES ('176349566486982656', '2018-05-02 15:18:41', '3', '5', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600079&tradeStatus=02&totalAmount=1.00&payTime=2018-05-02 15:10:13.0&errCode=&errCodeMsg=&sign=a4e76631433caa719aac243e32eb0d40', '1000001', '20180410171600079', '100', '1', '', '1', '2018-05-02 15:10:13', null, '2018-05-02 15:18:41', null);
INSERT INTO `notify_record` VALUES ('176350108089069568', '2018-05-02 15:15:26', '2', '5', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600080&tradeStatus=02&totalAmount=1.00&payTime=2018-05-02 15:12:22.119&errCode=&errCodeMsg=&sign=37838532917f31db7ad6be6937f13702', '1000001', '20180410171600080', '100', '1', '', '1', '2018-05-02 15:12:22', null, '2018-05-02 15:15:27', null);
INSERT INTO `notify_record` VALUES ('176350349051834368', '2018-05-02 15:18:40', '3', '5', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600081&tradeStatus=02&totalAmount=1.00&payTime=2018-05-02 15:13:19.592&errCode=&errCodeMsg=&sign=be417f9e7081fb7646857e4239750e74', '1000001', '20180410171600081', '100', '1', '', '1', '2018-05-02 15:13:20', null, '2018-05-02 15:18:40', null);
INSERT INTO `notify_record` VALUES ('176353196338327552', '2018-05-02 15:24:40', '1', '5', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600083&tradeStatus=02&totalAmount=1.00&payTime=2018-05-02 15:24:38.171&errCode=&errCodeMsg=&sign=15266d0c0da7156db45203c4b904abad', '1000001', '20180410171600083', '100', '1', '', '1', '2018-05-02 15:24:38', null, '2018-05-02 15:24:41', null);
INSERT INTO `notify_record` VALUES ('178446324801212416', '2018-05-08 14:37:05', '4', '5', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600090&tradeStatus=02&totalAmount=0.01&payTime=2018-05-08 10:01:57.583&errCode=&errCodeMsg=&sign=a9f3801705ce02c57a945aee70e2452d', '1000001', '20180410171600090', '100', '1', '', '1', '2018-05-08 10:01:59', null, '2018-05-08 14:37:05', null);
INSERT INTO `notify_record` VALUES ('178470994694901760', '2018-05-08 11:40:02', '1', '5', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600092&tradeStatus=02&totalAmount=0.01&payTime=2018-05-08 11:40:00.261&errCode=&errCodeMsg=&sign=c8411f34aa7372c939afe1d7d0a9c992', '1000001', '20180410171600092', '100', '1', '', '1', '2018-05-08 11:40:01', null, '2018-05-08 11:40:03', null);
INSERT INTO `notify_record` VALUES ('178471023497187328', '2018-05-08 11:40:09', '1', '5', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600092&tradeStatus=02&totalAmount=0.01&payTime=2018-05-08 11:40:07.777&errCode=&errCodeMsg=&sign=b29e7caa5d50cdb33cf7d48ce90b18cd', '1000001', '20180410171600092', '100', '1', '', '1', '2018-05-08 11:40:08', null, '2018-05-08 11:40:09', null);
INSERT INTO `notify_record` VALUES ('178515146430222336', '2018-05-08 14:35:30', '1', '5', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600093&tradeStatus=02&totalAmount=0.01&payTime=2018-05-08 14:35:24.644&errCode=&errCodeMsg=&sign=f58f19f89c2f7962d580080c2f8527d7', '1000001', '20180410171600093', '100', '1', '', '1', '2018-05-08 14:35:28', null, '2018-05-08 14:35:31', null);
INSERT INTO `notify_record` VALUES ('178515266169212928', '2018-05-08 14:35:59', '1', '5', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600093&tradeStatus=02&totalAmount=0.01&payTime=2018-05-08 14:35:49.64&errCode=&errCodeMsg=&sign=cbcbe8fead16a028e8e7afa4c5b2f292', '1000001', '20180410171600093', '100', '1', '', '1', '2018-05-08 14:35:56', null, '2018-05-08 14:35:59', null);
INSERT INTO `notify_record` VALUES ('178515487888510976', '2018-05-08 14:36:51', '1', '5', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600093&tradeStatus=02&totalAmount=0.01&payTime=2018-05-08 14:36:10.372&errCode=&errCodeMsg=&sign=8dfa43aabffe7bdafeeebe62ebe6e3aa', '1000001', '20180410171600093', '100', '1', '', '1', '2018-05-08 14:36:49', null, '2018-05-08 14:36:52', null);
INSERT INTO `notify_record` VALUES ('178515488286969856', '2018-05-08 14:36:55', '1', '5', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600093&tradeStatus=02&totalAmount=0.01&payTime=2018-05-08 14:36:48.981&errCode=&errCodeMsg=&sign=431508760cf3083af5df48ea0949ae1c', '1000001', '20180410171600093', '100', '1', '', '1', '2018-05-08 14:36:49', null, '2018-05-08 14:36:55', null);
INSERT INTO `notify_record` VALUES ('178516045340872704', '2018-05-08 14:39:04', '1', '5', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600094&tradeStatus=02&totalAmount=0.01&payTime=2018-05-08 14:39:00.144&errCode=&errCodeMsg=&sign=172fbc07d98f4be32e25665880979023', '1000001', '20180410171600094', '100', '1', '', '1', '2018-05-08 14:39:02', null, '2018-05-08 14:39:04', null);
INSERT INTO `notify_record` VALUES ('178518066433363968', '2018-05-08 14:47:09', '1', '5', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600095&tradeStatus=02&totalAmount=0.01&payTime=2018-05-08 14:47:03.672&errCode=&errCodeMsg=&sign=ddfbec1964015d1ba90efba6f7119ac2', '1000001', '20180410171600095', '100', '1', '', '1', '2018-05-08 14:47:04', null, '2018-05-08 14:47:10', null);
INSERT INTO `notify_record` VALUES ('178522607581138944', '2018-05-08 15:05:09', '1', '5', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600096&tradeStatus=02&totalAmount=0.01&payTime=2018-05-08 15:05:06.371&errCode=&errCodeMsg=&sign=4b7e0f6ea8471e55fd73099a9b8e3a9e', '1000001', '20180410171600096', '100', '1', '', '1', '2018-05-08 15:05:06', null, '2018-05-08 15:05:10', null);
INSERT INTO `notify_record` VALUES ('178523833869799424', '2018-05-08 15:10:21', '1', '5', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600093&tradeStatus=02&totalAmount=0.01&payTime=2018-05-08 15:09:58.747&errCode=&errCodeMsg=&sign=b8626c4d37bce717d2cd8a4897739b92', '1000001', '20180410171600093', '100', '1', '', '1', '2018-05-08 15:09:59', null, '2018-05-08 15:10:22', null);
INSERT INTO `notify_record` VALUES ('179248107274375168', '2018-05-10 15:08:04', '1', '5', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=8000000000&outTradeNo=2018051015063810000005&tradeStatus=02&totalAmount=0.01&payTime=2018-05-10 15:07:58.401&errCode=&errCodeMsg=&sign=c2f5141f11a40baf14a45ac5595f4f21', '8000000000', '2018051015063810000005', '100', '1', '', '1', '2018-05-10 15:07:59', null, '2018-05-10 15:08:04', null);
INSERT INTO `notify_record` VALUES ('179250965063405568', '2018-05-10 15:19:25', '1', '5', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=8000000000&outTradeNo=2018051014441310000003&tradeStatus=02&totalAmount=0.01&payTime=2018/05/10 15:19:20&errCode=&errCodeMsg=&sign=241cb068f0a3d5e9ca034d0cd3115716', '8000000000', '2018051014441310000003', '100', '1', '', '1', '2018-05-10 15:19:20', null, '2018-05-10 15:19:25', null);
INSERT INTO `notify_record` VALUES ('179258547203215360', '2018-05-10 15:49:32', '1', '5', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=8000000000&outTradeNo=2018051015465410000020&tradeStatus=02&totalAmount=1.00&payTime=2018-05-10 15:49:28.018&errCode=&errCodeMsg=&sign=93f59b1dadb6327811fd251aee10e2e2', '8000000000', '2018051015465410000020', '100', '1', '', '1', '2018-05-10 15:49:28', null, '2018-05-10 15:49:33', null);
INSERT INTO `notify_record` VALUES ('955337236194365441', '2018-05-02 15:17:22', '5', '5', 'http://192.168.1.240:8080/test?money=21323&sign=sSadhvPiO4kPG78FEGYk3jR8S4neicO/VnbXLUpf4P4/VCTxclS3nWedkUzm3l0hr3sYMo+UMMb4N4Hy2KmG90iMFHDyaoQBer0CMFF2N+NzYqkbGI+zw2xbIjf99wZGVgeX+uFmM14R54HDhgNvvsZjsMU93TxQAIFIMWREMkQ=', '213131', '1231312321', '100', '0', '测试消息通知', '1', '2018-01-22 15:11:35', null, '2018-05-02 15:17:22', null);

-- ----------------------------
-- Table structure for notify_record_log
-- ----------------------------
DROP TABLE IF EXISTS `notify_record_log`;
CREATE TABLE `notify_record_log` (
  `id_` bigint(32) NOT NULL,
  `notify_id` bigint(32) DEFAULT NULL,
  `request_` varchar(3000) DEFAULT NULL,
  `response_` varchar(1000) DEFAULT NULL,
  `cust_no` varchar(64) DEFAULT NULL,
  `order_no` varchar(64) DEFAULT NULL,
  `http_status` int(6) DEFAULT NULL,
  `enable_` tinyint(4) DEFAULT NULL,
  `remark_` varchar(500) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_by` bigint(32) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_by` bigint(32) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notify_record_log
-- ----------------------------
INSERT INTO `notify_record_log` VALUES ('174939635380666368', '174926655612010496', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600072&tradeStatus=02&totalAmount=1.00&payTime=2018-04-28 16:56:04.148&errCode=&errCodeMsg=&sign=0a545a5b6d172b93b120bf21cfa7f28e', '', '1000001', '20180410171600072', '200', '1', null, '2018-04-28 17:47:39', null, '2018-04-28 17:47:39', null);
INSERT INTO `notify_record_log` VALUES ('174939876238573568', '955337236194365441', 'http://192.168.1.240:8080/test?money=21323&sign=sSadhvPiO4kPG78FEGYk3jR8S4neicO/VnbXLUpf4P4/VCTxclS3nWedkUzm3l0hr3sYMo+UMMb4N4Hy2KmG90iMFHDyaoQBer0CMFF2N+NzYqkbGI+zw2xbIjf99wZGVgeX+uFmM14R54HDhgNvvsZjsMU93TxQAIFIMWREMkQ=', 'success', '213131', '1231312321', '200', '1', null, '2018-04-28 17:48:37', null, '2018-04-28 17:48:37', null);
INSERT INTO `notify_record_log` VALUES ('174939903866454016', '174926655612010496', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600072&tradeStatus=02&totalAmount=1.00&payTime=2018-04-28 16:56:04.148&errCode=&errCodeMsg=&sign=0a545a5b6d172b93b120bf21cfa7f28e', '', '1000001', '20180410171600072', '200', '1', null, '2018-04-28 17:48:43', null, '2018-04-28 17:48:43', null);
INSERT INTO `notify_record_log` VALUES ('174940406608314368', '174926655612010496', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600072&tradeStatus=02&totalAmount=1.00&payTime=2018-04-28 16:56:04.148&errCode=&errCodeMsg=&sign=0a545a5b6d172b93b120bf21cfa7f28e', '', '1000001', '20180410171600072', '200', '1', null, '2018-04-28 17:50:43', null, '2018-04-28 17:50:43', null);
INSERT INTO `notify_record_log` VALUES ('174941647476375552', '174926655612010496', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600072&tradeStatus=02&totalAmount=1.00&payTime=2018-04-28 16:56:04.148&errCode=&errCodeMsg=&sign=0a545a5b6d172b93b120bf21cfa7f28e', '', '1000001', '20180410171600072', '405', '1', null, '2018-04-28 17:55:39', null, '2018-04-28 17:55:39', null);
INSERT INTO `notify_record_log` VALUES ('174945435054522368', '174926655612010496', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600072&tradeStatus=02&totalAmount=1.00&payTime=2018-04-28 16:56:04.148&errCode=&errCodeMsg=&sign=0a545a5b6d172b93b120bf21cfa7f28e', '', '1000001', '20180410171600072', '405', '1', null, '2018-04-28 18:10:42', null, '2018-04-28 18:10:42', null);
INSERT INTO `notify_record_log` VALUES ('176332959362592768', '955337236194365441', 'http://192.168.1.240:8080/test?money=21323&sign=sSadhvPiO4kPG78FEGYk3jR8S4neicO/VnbXLUpf4P4/VCTxclS3nWedkUzm3l0hr3sYMo+UMMb4N4Hy2KmG90iMFHDyaoQBer0CMFF2N+NzYqkbGI+zw2xbIjf99wZGVgeX+uFmM14R54HDhgNvvsZjsMU93TxQAIFIMWREMkQ=', '', '213131', '1231312321', '405', '1', null, '2018-05-02 14:04:14', null, '2018-05-02 14:04:14', null);
INSERT INTO `notify_record_log` VALUES ('176334224981241856', '176334139648126976', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600073&tradeStatus=02&totalAmount=1.00&payTime=2018-05-02 14:08:54.284&errCode=&errCodeMsg=&sign=b2cedc1bd4ecea6ecb9fa9c12fd3484f', '', '1000001', '20180410171600073', '-1', '1', null, '2018-05-02 14:09:15', null, '2018-05-02 14:09:15', null);
INSERT INTO `notify_record_log` VALUES ('176334226172424192', '955337236194365441', 'http://192.168.1.240:8080/test?money=21323&sign=sSadhvPiO4kPG78FEGYk3jR8S4neicO/VnbXLUpf4P4/VCTxclS3nWedkUzm3l0hr3sYMo+UMMb4N4Hy2KmG90iMFHDyaoQBer0CMFF2N+NzYqkbGI+zw2xbIjf99wZGVgeX+uFmM14R54HDhgNvvsZjsMU93TxQAIFIMWREMkQ=', '', '213131', '1231312321', '405', '1', null, '2018-05-02 14:09:16', null, '2018-05-02 14:09:16', null);
INSERT INTO `notify_record_log` VALUES ('176334437305298944', '176334139648126976', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600073&tradeStatus=02&totalAmount=1.00&payTime=2018-05-02 14:08:54.284&errCode=&errCodeMsg=&sign=b2cedc1bd4ecea6ecb9fa9c12fd3484f', 'success', '1000001', '20180410171600073', '200', '1', null, '2018-05-02 14:10:06', null, '2018-05-02 14:10:06', null);
INSERT INTO `notify_record_log` VALUES ('176335878140997632', '176335792191320064', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600074&tradeStatus=02&totalAmount=1.00&payTime=2018-05-02 14:15:28.972&errCode=&errCodeMsg=&sign=59c0d50ed3e1aec8966092a84d3799a6', '', '1000001', '20180410171600074', '-1', '1', null, '2018-05-02 14:15:50', null, '2018-05-02 14:15:50', null);
INSERT INTO `notify_record_log` VALUES ('176336116222275584', '176335792191320064', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600074&tradeStatus=02&totalAmount=1.00&payTime=2018-05-02 14:15:28.972&errCode=&errCodeMsg=&sign=59c0d50ed3e1aec8966092a84d3799a6', 'success', '1000001', '20180410171600074', '200', '1', null, '2018-05-02 14:16:46', null, '2018-05-02 14:16:46', null);
INSERT INTO `notify_record_log` VALUES ('176336700459462656', '176336659850211328', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600075&tradeStatus=02&totalAmount=1.00&payTime=2018-05-02 14:18:55.831&errCode=&errCodeMsg=&sign=211b0172ff598ec4c95883d74472be23', 'success', '1000001', '20180410171600075', '200', '1', null, '2018-05-02 14:19:06', null, '2018-05-02 14:19:06', null);
INSERT INTO `notify_record_log` VALUES ('176338002996375552', '955337236194365441', 'http://192.168.1.240:8080/test?money=21323&sign=sSadhvPiO4kPG78FEGYk3jR8S4neicO/VnbXLUpf4P4/VCTxclS3nWedkUzm3l0hr3sYMo+UMMb4N4Hy2KmG90iMFHDyaoQBer0CMFF2N+NzYqkbGI+zw2xbIjf99wZGVgeX+uFmM14R54HDhgNvvsZjsMU93TxQAIFIMWREMkQ=', '', '213131', '1231312321', '405', '1', null, '2018-05-02 14:24:16', null, '2018-05-02 14:24:16', null);
INSERT INTO `notify_record_log` VALUES ('176339107956404224', '176339092764635136', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600076&tradeStatus=02&totalAmount=1.00&payTime=2018-05-02 14:28:35.866&errCode=&errCodeMsg=&sign=af9566f01a1a596ea9d17c10e65bf489', 'success', '1000001', '20180410171600076', '200', '1', null, '2018-05-02 14:28:40', null, '2018-05-02 14:28:40', null);
INSERT INTO `notify_record_log` VALUES ('176347546564702208', '176336659850211328', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600075&tradeStatus=02&totalAmount=1.00&payTime=2018-05-02 14:18:55.831&errCode=&errCodeMsg=&sign=211b0172ff598ec4c95883d74472be23', 'success', '1000001', '20180410171600075', '200', '1', null, '2018-05-02 15:02:11', null, '2018-05-02 15:02:11', null);
INSERT INTO `notify_record_log` VALUES ('176347582816071680', '176339092764635136', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600076&tradeStatus=02&totalAmount=1.00&payTime=2018-05-02 14:28:35.866&errCode=&errCodeMsg=&sign=af9566f01a1a596ea9d17c10e65bf489', 'success', '1000001', '20180410171600076', '200', '1', null, '2018-05-02 15:02:20', null, '2018-05-02 15:02:20', null);
INSERT INTO `notify_record_log` VALUES ('176347822180806656', '176334139648126976', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600073&tradeStatus=02&totalAmount=1.00&payTime=2018-05-02 14:08:54.284&errCode=&errCodeMsg=&sign=b2cedc1bd4ecea6ecb9fa9c12fd3484f', 'success', '1000001', '20180410171600073', '200', '1', null, '2018-05-02 15:03:17', null, '2018-05-02 15:03:17', null);
INSERT INTO `notify_record_log` VALUES ('176347862244798464', '176335792191320064', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600074&tradeStatus=02&totalAmount=1.00&payTime=2018-05-02 14:15:28.972&errCode=&errCodeMsg=&sign=59c0d50ed3e1aec8966092a84d3799a6', 'success', '1000001', '20180410171600074', '200', '1', null, '2018-05-02 15:03:27', null, '2018-05-02 15:03:27', null);
INSERT INTO `notify_record_log` VALUES ('176348826150383616', '176348803178180608', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600077&tradeStatus=02&totalAmount=1.00&payTime=2018-05-02 15:07:10.697&errCode=&errCodeMsg=&sign=790096d896cb63bc4dcf7e825cbb3b01', 'success', '1000001', '20180410171600077', '200', '1', null, '2018-05-02 15:07:17', null, '2018-05-02 15:07:17', null);
INSERT INTO `notify_record_log` VALUES ('176349210839363584', '176349134276538368', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600078&tradeStatus=02&totalAmount=1.00&payTime=2018-05-02 15:08:29.959&errCode=&errCodeMsg=&sign=9ac844f9958372b222d7f74bec0189f5', 'success', '1000001', '20180410171600078', '200', '1', null, '2018-05-02 15:08:48', null, '2018-05-02 15:08:48', null);
INSERT INTO `notify_record_log` VALUES ('176349585692700672', '176349566486982656', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600079&tradeStatus=02&totalAmount=1.00&payTime=2018-05-02 15:10:13.0&errCode=&errCodeMsg=&sign=a4e76631433caa719aac243e32eb0d40', 'success', '1000001', '20180410171600079', '200', '1', null, '2018-05-02 15:10:18', null, '2018-05-02 15:10:18', null);
INSERT INTO `notify_record_log` VALUES ('176350151906963456', '176350108089069568', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600080&tradeStatus=02&totalAmount=1.00&payTime=2018-05-02 15:12:22.119&errCode=&errCodeMsg=&sign=37838532917f31db7ad6be6937f13702', 'success', '1000001', '20180410171600080', '200', '1', null, '2018-05-02 15:12:33', null, '2018-05-02 15:12:33', null);
INSERT INTO `notify_record_log` VALUES ('176350434967957504', '176350349051834368', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600081&tradeStatus=02&totalAmount=1.00&payTime=2018-05-02 15:13:19.592&errCode=&errCodeMsg=&sign=be417f9e7081fb7646857e4239750e74', '', '1000001', '20180410171600081', '-1', '1', null, '2018-05-02 15:13:40', null, '2018-05-02 15:13:40', null);
INSERT INTO `notify_record_log` VALUES ('176350871511117824', '176348803178180608', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600077&tradeStatus=02&totalAmount=1.00&payTime=2018-05-02 15:07:10.697&errCode=&errCodeMsg=&sign=790096d896cb63bc4dcf7e825cbb3b01', 'success', '1000001', '20180410171600077', '200', '1', null, '2018-05-02 15:15:24', null, '2018-05-02 15:15:24', null);
INSERT INTO `notify_record_log` VALUES ('176350882902847488', '176350108089069568', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600080&tradeStatus=02&totalAmount=1.00&payTime=2018-05-02 15:12:22.119&errCode=&errCodeMsg=&sign=37838532917f31db7ad6be6937f13702', 'success', '1000001', '20180410171600080', '200', '1', null, '2018-05-02 15:15:27', null, '2018-05-02 15:15:27', null);
INSERT INTO `notify_record_log` VALUES ('176350968416317440', '176350349051834368', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600081&tradeStatus=02&totalAmount=1.00&payTime=2018-05-02 15:13:19.592&errCode=&errCodeMsg=&sign=be417f9e7081fb7646857e4239750e74', '', '1000001', '20180410171600081', '-1', '1', null, '2018-05-02 15:15:47', null, '2018-05-02 15:15:47', null);
INSERT INTO `notify_record_log` VALUES ('176351053795569664', '176349566486982656', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600079&tradeStatus=02&totalAmount=1.00&payTime=2018-05-02 15:10:13.0&errCode=&errCodeMsg=&sign=a4e76631433caa719aac243e32eb0d40', '', '1000001', '20180410171600079', '-1', '1', null, '2018-05-02 15:16:08', null, '2018-05-02 15:16:08', null);
INSERT INTO `notify_record_log` VALUES ('176351122439548928', '176349134276538368', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600078&tradeStatus=02&totalAmount=1.00&payTime=2018-05-02 15:08:29.959&errCode=&errCodeMsg=&sign=9ac844f9958372b222d7f74bec0189f5', 'success', '1000001', '20180410171600078', '200', '1', null, '2018-05-02 15:16:24', null, '2018-05-02 15:16:24', null);
INSERT INTO `notify_record_log` VALUES ('176351208137568256', '176339092764635136', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600076&tradeStatus=02&totalAmount=1.00&payTime=2018-05-02 14:28:35.866&errCode=&errCodeMsg=&sign=af9566f01a1a596ea9d17c10e65bf489', '', '1000001', '20180410171600076', '-1', '1', null, '2018-05-02 15:16:44', null, '2018-05-02 15:16:44', null);
INSERT INTO `notify_record_log` VALUES ('176351293407768576', '176336659850211328', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600075&tradeStatus=02&totalAmount=1.00&payTime=2018-05-02 14:18:55.831&errCode=&errCodeMsg=&sign=211b0172ff598ec4c95883d74472be23', '', '1000001', '20180410171600075', '-1', '1', null, '2018-05-02 15:17:05', null, '2018-05-02 15:17:05', null);
INSERT INTO `notify_record_log` VALUES ('176351416078577664', '176350349051834368', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600081&tradeStatus=02&totalAmount=1.00&payTime=2018-05-02 15:13:19.592&errCode=&errCodeMsg=&sign=be417f9e7081fb7646857e4239750e74', '', '1000001', '20180410171600081', '-1', '1', null, '2018-05-02 15:17:34', null, '2018-05-02 15:17:34', null);
INSERT INTO `notify_record_log` VALUES ('176351428011372544', '176349566486982656', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600079&tradeStatus=02&totalAmount=1.00&payTime=2018-05-02 15:10:13.0&errCode=&errCodeMsg=&sign=a4e76631433caa719aac243e32eb0d40', '<!DOCTYPE html PUBLIC \'-//W3C//DTD XHTML 1.0 Transitional//EN\' \'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\'>\r\n<html xmlns=\'http://www.w3.org/1999/xhtml\'>\r\n<head>\r\n<meta http-equiv=\'Content-Type\' content=\'text/html; charset=utf-8\' />\r\n<title>sys故障检测页 - powered by nat123</title>\r\n<style type=\'text/css\'>\r\n.container{width:860px; height:500px;position:absolute;  margin-left:-430px; margin-top:-250px; top:50%;left:50%;padding:0px;font-size:14px; background:url(http://images.nat123.com/temple/images/bg.jpg); color:#414040; line-height:24px; font-family:\'微软雅黑\';}\r\n.main{ width:680px; heig', '1000001', '20180410171600079', '200', '1', null, '2018-05-02 15:17:37', null, '2018-05-02 15:17:37', null);
INSERT INTO `notify_record_log` VALUES ('176353206828281856', '176353196338327552', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600083&tradeStatus=02&totalAmount=1.00&payTime=2018-05-02 15:24:38.171&errCode=&errCodeMsg=&sign=15266d0c0da7156db45203c4b904abad', 'success', '1000001', '20180410171600083', '200', '1', null, '2018-05-02 15:24:41', null, '2018-05-02 15:24:41', null);
INSERT INTO `notify_record_log` VALUES ('178446337421873152', '178446324801212416', 'null?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600090&tradeStatus=02&totalAmount=0.01&payTime=2018-05-08 10:01:57.583&errCode=&errCodeMsg=&sign=a9f3801705ce02c57a945aee70e2452d', '', '1000001', '20180410171600090', '-1', '1', null, '2018-05-08 10:02:02', null, '2018-05-08 10:02:02', null);
INSERT INTO `notify_record_log` VALUES ('178446588123811840', '178446324801212416', 'null?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600090&tradeStatus=02&totalAmount=0.01&payTime=2018-05-08 10:01:57.583&errCode=&errCodeMsg=&sign=a9f3801705ce02c57a945aee70e2452d', '', '1000001', '20180410171600090', '-1', '1', null, '2018-05-08 10:03:02', null, '2018-05-08 10:03:02', null);
INSERT INTO `notify_record_log` VALUES ('178447095882059776', '178446324801212416', 'null?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600090&tradeStatus=02&totalAmount=0.01&payTime=2018-05-08 10:01:57.583&errCode=&errCodeMsg=&sign=a9f3801705ce02c57a945aee70e2452d', '', '1000001', '20180410171600090', '-1', '1', null, '2018-05-08 10:05:03', null, '2018-05-08 10:05:03', null);
INSERT INTO `notify_record_log` VALUES ('178448358233346048', '178446324801212416', 'null?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600090&tradeStatus=02&totalAmount=0.01&payTime=2018-05-08 10:01:57.583&errCode=&errCodeMsg=&sign=a9f3801705ce02c57a945aee70e2452d', '', '1000001', '20180410171600090', '-1', '1', null, '2018-05-08 10:10:04', null, '2018-05-08 10:10:04', null);
INSERT INTO `notify_record_log` VALUES ('178452145983459328', '178446324801212416', 'null?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600090&tradeStatus=02&totalAmount=0.01&payTime=2018-05-08 10:01:57.583&errCode=&errCodeMsg=&sign=a9f3801705ce02c57a945aee70e2452d', '', '1000001', '20180410171600090', '-1', '1', null, '2018-05-08 10:25:07', null, '2018-05-08 10:25:07', null);
INSERT INTO `notify_record_log` VALUES ('178471002395643904', '178470994694901760', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600092&tradeStatus=02&totalAmount=0.01&payTime=2018-05-08 11:40:00.261&errCode=&errCodeMsg=&sign=c8411f34aa7372c939afe1d7d0a9c992', 'success', '1000001', '20180410171600092', '200', '1', null, '2018-05-08 11:40:03', null, '2018-05-08 11:40:03', null);
INSERT INTO `notify_record_log` VALUES ('178471030254211072', '178471023497187328', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600092&tradeStatus=02&totalAmount=0.01&payTime=2018-05-08 11:40:07.777&errCode=&errCodeMsg=&sign=b29e7caa5d50cdb33cf7d48ce90b18cd', 'success', '1000001', '20180410171600092', '200', '1', null, '2018-05-08 11:40:09', null, '2018-05-08 11:40:09', null);
INSERT INTO `notify_record_log` VALUES ('178515160850239488', '178515146430222336', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600093&tradeStatus=02&totalAmount=0.01&payTime=2018-05-08 14:35:24.644&errCode=&errCodeMsg=&sign=f58f19f89c2f7962d580080c2f8527d7', 'success', '1000001', '20180410171600093', '200', '1', null, '2018-05-08 14:35:31', null, '2018-05-08 14:35:31', null);
INSERT INTO `notify_record_log` VALUES ('178515280165605376', '178515266169212928', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600093&tradeStatus=02&totalAmount=0.01&payTime=2018-05-08 14:35:49.64&errCode=&errCodeMsg=&sign=cbcbe8fead16a028e8e7afa4c5b2f292', 'success', '1000001', '20180410171600093', '200', '1', null, '2018-05-08 14:35:59', null, '2018-05-08 14:35:59', null);
INSERT INTO `notify_record_log` VALUES ('178515500773412864', '178515487888510976', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600093&tradeStatus=02&totalAmount=0.01&payTime=2018-05-08 14:36:10.372&errCode=&errCodeMsg=&sign=8dfa43aabffe7bdafeeebe62ebe6e3aa', 'success', '1000001', '20180410171600093', '200', '1', null, '2018-05-08 14:36:52', null, '2018-05-08 14:36:52', null);
INSERT INTO `notify_record_log` VALUES ('178515513939333120', '178515488286969856', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600093&tradeStatus=02&totalAmount=0.01&payTime=2018-05-08 14:36:48.981&errCode=&errCodeMsg=&sign=431508760cf3083af5df48ea0949ae1c', 'success', '1000001', '20180410171600093', '200', '1', null, '2018-05-08 14:36:55', null, '2018-05-08 14:36:55', null);
INSERT INTO `notify_record_log` VALUES ('178516055914713088', '178516045340872704', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600094&tradeStatus=02&totalAmount=0.01&payTime=2018-05-08 14:39:00.144&errCode=&errCodeMsg=&sign=172fbc07d98f4be32e25665880979023', 'success', '1000001', '20180410171600094', '200', '1', null, '2018-05-08 14:39:04', null, '2018-05-08 14:39:04', null);
INSERT INTO `notify_record_log` VALUES ('178518091095871488', '178518066433363968', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600095&tradeStatus=02&totalAmount=0.01&payTime=2018-05-08 14:47:03.672&errCode=&errCodeMsg=&sign=ddfbec1964015d1ba90efba6f7119ac2', 'success', '1000001', '20180410171600095', '200', '1', null, '2018-05-08 14:47:10', null, '2018-05-08 14:47:10', null);
INSERT INTO `notify_record_log` VALUES ('178522622198288384', '178522607581138944', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600096&tradeStatus=02&totalAmount=0.01&payTime=2018-05-08 15:05:06.371&errCode=&errCodeMsg=&sign=4b7e0f6ea8471e55fd73099a9b8e3a9e', 'success', '1000001', '20180410171600096', '200', '1', null, '2018-05-08 15:05:10', null, '2018-05-08 15:05:10', null);
INSERT INTO `notify_record_log` VALUES ('178523931093766144', '178523833869799424', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=1000001&outTradeNo=20180410171600093&tradeStatus=02&totalAmount=0.01&payTime=2018-05-08 15:09:58.747&errCode=&errCodeMsg=&sign=b8626c4d37bce717d2cd8a4897739b92', 'success', '1000001', '20180410171600093', '200', '1', null, '2018-05-08 15:10:22', null, '2018-05-08 15:10:22', null);
INSERT INTO `notify_record_log` VALUES ('179248130661814272', '179248107274375168', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=8000000000&outTradeNo=2018051015063810000005&tradeStatus=02&totalAmount=0.01&payTime=2018-05-10 15:07:58.401&errCode=&errCodeMsg=&sign=c2f5141f11a40baf14a45ac5595f4f21', 'success', '8000000000', '2018051015063810000005', '200', '1', null, '2018-05-10 15:08:05', null, '2018-05-10 15:08:05', null);
INSERT INTO `notify_record_log` VALUES ('179250986563407872', '179250965063405568', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=8000000000&outTradeNo=2018051014441310000003&tradeStatus=02&totalAmount=0.01&payTime=2018/05/10 15:19:20&errCode=&errCodeMsg=&sign=241cb068f0a3d5e9ca034d0cd3115716', 'success', '8000000000', '2018051014441310000003', '200', '1', null, '2018-05-10 15:19:26', null, '2018-05-10 15:19:26', null);
INSERT INTO `notify_record_log` VALUES ('179258566723506176', '179258547203215360', 'http://sys.javaxxw.cn/test/notify?returnCode=SUCCESS&returnMsg=OK&mchId=8000000000&outTradeNo=2018051015465410000020&tradeStatus=02&totalAmount=1.00&payTime=2018-05-10 15:49:28.018&errCode=&errCodeMsg=&sign=93f59b1dadb6327811fd251aee10e2e2', 'success', '8000000000', '2018051015465410000020', '200', '1', null, '2018-05-10 15:49:33', null, '2018-05-10 15:49:33', null);

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
INSERT INTO `pay_channel` VALUES ('1', 'ALIPAY_WAP', 'ALIPAY', '1000001', '8000000000', '1', '{\"isSandbox\":1,\"appid\": \"11\", \"private_key\": \"132132+MpYqA+zvu+3GsFNqt1kCqBq9JaqISPmTinvzblEcvh+4n+95UvRNeN+Ecc5tT4HM4lACsFWa6VKkJuTcayL0hCQSeayDvjsBYeIMu4nPAuLkxNQNS8Q5wu/S2vjmLhByE+pc7iOegPOYnVJ4qu3gYHDFJBYBcmt3c6z1+a1QYEXsxkeK2LTYYCDygG0hUipbjCWiD//RTRwMEAfkXCZUrWdee+bXaQs7Vj20LZiTY+UlxWMzbmqDVMu4aE9Sb8rWQlrLMylEuxmntQ2BlI49E+1ljEJ/VwD8RjHOAYeYVxXz9iVIQcf1Dj7TGvtdgTCjUlAgMBAAECggEABP7iU+mXPJDQ6/kxLLs8TATTzb6QPi513yOHFuIMrkIGeY5OAunqqQf377OKTpVk1qSLbyth2YOlA+wi2DKgVfyzFucvnzv3fZskpcDZJg9LVmw8U25Mn2RitLP4rq8p6aT9rdhjSCm9wu7lOoxbIJ41lyOr0vW1pUfgCJA1xmdVNgun910Ib8Fv1zjPPzDbwKxpU2Xa2gdT4BpS8GIaJy3ipuEoZVCJM3xVmYYzoOXulFMBA4No0dhw7Po+7Qfq4F7oAYSTLcT4mWH9dctmmjqTb3VwIq9m/txsYGHKU5BFlFMpTH4UU+37VGgTvasaOWMudWNXrRSoCZK0RAT64QKBgQDoWV35DiSVnFVt8LOgO1qOPl7SpNQyEjj/ouog+IbYpIQG6qViRX7pKTix+W04xwcRe4JjyNFYt/ZUBUJtB3YJoXGJIf3l0KVPN12sq8lC8qnXECofTmDneM2c8d5UKEbM5k4pve4uuY35NsgJNO8kLI+UmTDc2ZXN2kb8dnB5uwKBgQDb9FxXEYyrLIt9bNa1tQUINk89lW5csk5eLT2lEZtbi7kYFz7VwI9/zGrfC89OJGssEzeqHVwQ7KJQoxQ5t6pi5S5DjkCuBq2SuJUpLcp3vDFt7JSx/OWlmRF5F56qdQ/+CXFE4a6RmjZapThUoIVaMm2LhYXbn4TLwkX8R30unwKBgQC+JUybYnCcI2liZIaLzacJG4+8MPoa1ADja+7Q9rJcHx6RDnw0FQ2MQJjORYKV4YMuUnZdtN1mDn6/OeKnmWCG9yEUCcZfycVPM88sXRWC/VXy0561Kg5xv9H86494LdHrI1q6HF4MzPucBLHlnvVKFFGYWVm8kzBeW5CWUVMwuwKBgQCXz3ugOKOfTVplUiaLOYpklZ9xgN6wKfz+8Ti34oQY1kIFR539yC8C5OLqWWtWoro5rHXh+uudXsXU5AnEoGiAY2/f/qHTuxY3yyBgUJOKuqBfH0et5T/XYON3cX74ZV2JV5kPErj7E7XqMFjFyBjiHSYQgzLVV68/NzI4olrbPwKBgQDLLsqN3qqhnESy07l7yfb9uIWebNHlREcV00loEvL3LJMWGacHvY824yrx6iAI920ZWVCbqFOyZIPSG1XngKUdarh9AV5uIdSX5rueRQCaHbtv30vhF9gcTNYSCxKj6XnvzQuxIYSLCroEPpeG7Q6SU+qv+X34yAV+5RNFhFHE5A==\", \"alipay_public_key\": \"12311+LFlVLJm/SueP96LLW/VvE80Gddw3fipqIlUGwluYRBOF5l34RCAKhLX3KEE4wZ1M8wcoh3kBt/C2rCLH25sXnGqkNpwaLdTftMr0xNoDK0pXbtZ88PbZWE9GwVKGo7lzm/qUkRsdoQF9PcD7Vk0u6gwx506NgEOazCC8li3OajYsj3vxD207L7MrS11xp/0Gdv6uxQ6bh0CvKwzV0TeUteanHWmyLg7ky3X0dIOEOgdDZzzZhMgJKA6O9BDVZ5XMGXUFaYjR/q9SXfCQIDAQAB\"}', '1', null, '2018-04-10 11:40:02', null, '2018-05-10 14:19:59', null);
INSERT INTO `pay_channel` VALUES ('2', 'ALIPAY_PC', 'ALIPAY', '1000001', '8000000000', '1', '{\"isSandbox\":1,\"appid\": \"11\", \"private_key\": \"12313+MpYqA+zvu+3GsFNqt1kCqBq9JaqISPmTinvzblEcvh+4n+95UvRNeN+Ecc5tT4HM4lACsFWa6VKkJuTcayL0hCQSeayDvjsBYeIMu4nPAuLkxNQNS8Q5wu/S2vjmLhByE+pc7iOegPOYnVJ4qu3gYHDFJBYBcmt3c6z1+a1QYEXsxkeK2LTYYCDygG0hUipbjCWiD//RTRwMEAfkXCZUrWdee+bXaQs7Vj20LZiTY+UlxWMzbmqDVMu4aE9Sb8rWQlrLMylEuxmntQ2BlI49E+1ljEJ/VwD8RjHOAYeYVxXz9iVIQcf1Dj7TGvtdgTCjUlAgMBAAECggEABP7iU+mXPJDQ6/kxLLs8TATTzb6QPi513yOHFuIMrkIGeY5OAunqqQf377OKTpVk1qSLbyth2YOlA+wi2DKgVfyzFucvnzv3fZskpcDZJg9LVmw8U25Mn2RitLP4rq8p6aT9rdhjSCm9wu7lOoxbIJ41lyOr0vW1pUfgCJA1xmdVNgun910Ib8Fv1zjPPzDbwKxpU2Xa2gdT4BpS8GIaJy3ipuEoZVCJM3xVmYYzoOXulFMBA4No0dhw7Po+7Qfq4F7oAYSTLcT4mWH9dctmmjqTb3VwIq9m/txsYGHKU5BFlFMpTH4UU+37VGgTvasaOWMudWNXrRSoCZK0RAT64QKBgQDoWV35DiSVnFVt8LOgO1qOPl7SpNQyEjj/ouog+IbYpIQG6qViRX7pKTix+W04xwcRe4JjyNFYt/ZUBUJtB3YJoXGJIf3l0KVPN12sq8lC8qnXECofTmDneM2c8d5UKEbM5k4pve4uuY35NsgJNO8kLI+UmTDc2ZXN2kb8dnB5uwKBgQDb9FxXEYyrLIt9bNa1tQUINk89lW5csk5eLT2lEZtbi7kYFz7VwI9/zGrfC89OJGssEzeqHVwQ7KJQoxQ5t6pi5S5DjkCuBq2SuJUpLcp3vDFt7JSx/OWlmRF5F56qdQ/+CXFE4a6RmjZapThUoIVaMm2LhYXbn4TLwkX8R30unwKBgQC+JUybYnCcI2liZIaLzacJG4+8MPoa1ADja+7Q9rJcHx6RDnw0FQ2MQJjORYKV4YMuUnZdtN1mDn6/OeKnmWCG9yEUCcZfycVPM88sXRWC/VXy0561Kg5xv9H86494LdHrI1q6HF4MzPucBLHlnvVKFFGYWVm8kzBeW5CWUVMwuwKBgQCXz3ugOKOfTVplUiaLOYpklZ9xgN6wKfz+8Ti34oQY1kIFR539yC8C5OLqWWtWoro5rHXh+uudXsXU5AnEoGiAY2/f/qHTuxY3yyBgUJOKuqBfH0et5T/XYON3cX74ZV2JV5kPErj7E7XqMFjFyBjiHSYQgzLVV68/NzI4olrbPwKBgQDLLsqN3qqhnESy07l7yfb9uIWebNHlREcV00loEvL3LJMWGacHvY824yrx6iAI920ZWVCbqFOyZIPSG1XngKUdarh9AV5uIdSX5rueRQCaHbtv30vhF9gcTNYSCxKj6XnvzQuxIYSLCroEPpeG7Q6SU+qv+X34yAV+5RNFhFHE5A==\", \"alipay_public_key\": \"12312313+LFlVLJm/SueP96LLW/VvE80Gddw3fipqIlUGwluYRBOF5l34RCAKhLX3KEE4wZ1M8wcoh3kBt/C2rCLH25sXnGqkNpwaLdTftMr0xNoDK0pXbtZ88PbZWE9GwVKGo7lzm/qUkRsdoQF9PcD7Vk0u6gwx506NgEOazCC8li3OajYsj3vxD207L7MrS11xp/0Gdv6uxQ6bh0CvKwzV0TeUteanHWmyLg7ky3X0dIOEOgdDZzzZhMgJKA6O9BDVZ5XMGXUFaYjR/q9SXfCQIDAQAB\"}', '1', null, '2018-04-10 17:54:33', null, '2018-05-10 14:20:00', null);
INSERT INTO `pay_channel` VALUES ('3', 'ALIPAY_QR', 'ALIPAY', '1000001', '8000000000', '1', '{\"isSandbox\":1,\"appid\": \"11\", \"private_key\": \"1231+MpYqA+zvu+3GsFNqt1kCqBq9JaqISPmTinvzblEcvh+4n+95UvRNeN+Ecc5tT4HM4lACsFWa6VKkJuTcayL0hCQSeayDvjsBYeIMu4nPAuLkxNQNS8Q5wu/S2vjmLhByE+pc7iOegPOYnVJ4qu3gYHDFJBYBcmt3c6z1+a1QYEXsxkeK2LTYYCDygG0hUipbjCWiD//RTRwMEAfkXCZUrWdee+bXaQs7Vj20LZiTY+UlxWMzbmqDVMu4aE9Sb8rWQlrLMylEuxmntQ2BlI49E+1ljEJ/VwD8RjHOAYeYVxXz9iVIQcf1Dj7TGvtdgTCjUlAgMBAAECggEABP7iU+mXPJDQ6/kxLLs8TATTzb6QPi513yOHFuIMrkIGeY5OAunqqQf377OKTpVk1qSLbyth2YOlA+wi2DKgVfyzFucvnzv3fZskpcDZJg9LVmw8U25Mn2RitLP4rq8p6aT9rdhjSCm9wu7lOoxbIJ41lyOr0vW1pUfgCJA1xmdVNgun910Ib8Fv1zjPPzDbwKxpU2Xa2gdT4BpS8GIaJy3ipuEoZVCJM3xVmYYzoOXulFMBA4No0dhw7Po+7Qfq4F7oAYSTLcT4mWH9dctmmjqTb3VwIq9m/txsYGHKU5BFlFMpTH4UU+37VGgTvasaOWMudWNXrRSoCZK0RAT64QKBgQDoWV35DiSVnFVt8LOgO1qOPl7SpNQyEjj/ouog+IbYpIQG6qViRX7pKTix+W04xwcRe4JjyNFYt/ZUBUJtB3YJoXGJIf3l0KVPN12sq8lC8qnXECofTmDneM2c8d5UKEbM5k4pve4uuY35NsgJNO8kLI+UmTDc2ZXN2kb8dnB5uwKBgQDb9FxXEYyrLIt9bNa1tQUINk89lW5csk5eLT2lEZtbi7kYFz7VwI9/zGrfC89OJGssEzeqHVwQ7KJQoxQ5t6pi5S5DjkCuBq2SuJUpLcp3vDFt7JSx/OWlmRF5F56qdQ/+CXFE4a6RmjZapThUoIVaMm2LhYXbn4TLwkX8R30unwKBgQC+JUybYnCcI2liZIaLzacJG4+8MPoa1ADja+7Q9rJcHx6RDnw0FQ2MQJjORYKV4YMuUnZdtN1mDn6/OeKnmWCG9yEUCcZfycVPM88sXRWC/VXy0561Kg5xv9H86494LdHrI1q6HF4MzPucBLHlnvVKFFGYWVm8kzBeW5CWUVMwuwKBgQCXz3ugOKOfTVplUiaLOYpklZ9xgN6wKfz+8Ti34oQY1kIFR539yC8C5OLqWWtWoro5rHXh+uudXsXU5AnEoGiAY2/f/qHTuxY3yyBgUJOKuqBfH0et5T/XYON3cX74ZV2JV5kPErj7E7XqMFjFyBjiHSYQgzLVV68/NzI4olrbPwKBgQDLLsqN3qqhnESy07l7yfb9uIWebNHlREcV00loEvL3LJMWGacHvY824yrx6iAI920ZWVCbqFOyZIPSG1XngKUdarh9AV5uIdSX5rueRQCaHbtv30vhF9gcTNYSCxKj6XnvzQuxIYSLCroEPpeG7Q6SU+qv+X34yAV+5RNFhFHE5A==\", \"alipay_public_key\": \"3213213+LFlVLJm/SueP96LLW/VvE80Gddw3fipqIlUGwluYRBOF5l34RCAKhLX3KEE4wZ1M8wcoh3kBt/C2rCLH25sXnGqkNpwaLdTftMr0xNoDK0pXbtZ88PbZWE9GwVKGo7lzm/qUkRsdoQF9PcD7Vk0u6gwx506NgEOazCC8li3OajYsj3vxD207L7MrS11xp/0Gdv6uxQ6bh0CvKwzV0TeUteanHWmyLg7ky3X0dIOEOgdDZzzZhMgJKA6O9BDVZ5XMGXUFaYjR/q9SXfCQIDAQAB\"}', '1', null, '2018-04-10 17:54:33', null, '2018-05-10 14:20:01', null);
INSERT INTO `pay_channel` VALUES ('4', 'ALIPAY_MOBILE', 'ALIPAY', '1000001', '8000000000', '1', '{\"isSandbox\":1,\"appid\": \"11\", \"private_key\": \"23213+MpYqA+zvu+3GsFNqt1kCqBq9JaqISPmTinvzblEcvh+4n+95UvRNeN+Ecc5tT4HM4lACsFWa6VKkJuTcayL0hCQSeayDvjsBYeIMu4nPAuLkxNQNS8Q5wu/S2vjmLhByE+pc7iOegPOYnVJ4qu3gYHDFJBYBcmt3c6z1+a1QYEXsxkeK2LTYYCDygG0hUipbjCWiD//RTRwMEAfkXCZUrWdee+bXaQs7Vj20LZiTY+UlxWMzbmqDVMu4aE9Sb8rWQlrLMylEuxmntQ2BlI49E+1ljEJ/VwD8RjHOAYeYVxXz9iVIQcf1Dj7TGvtdgTCjUlAgMBAAECggEABP7iU+mXPJDQ6/kxLLs8TATTzb6QPi513yOHFuIMrkIGeY5OAunqqQf377OKTpVk1qSLbyth2YOlA+wi2DKgVfyzFucvnzv3fZskpcDZJg9LVmw8U25Mn2RitLP4rq8p6aT9rdhjSCm9wu7lOoxbIJ41lyOr0vW1pUfgCJA1xmdVNgun910Ib8Fv1zjPPzDbwKxpU2Xa2gdT4BpS8GIaJy3ipuEoZVCJM3xVmYYzoOXulFMBA4No0dhw7Po+7Qfq4F7oAYSTLcT4mWH9dctmmjqTb3VwIq9m/txsYGHKU5BFlFMpTH4UU+37VGgTvasaOWMudWNXrRSoCZK0RAT64QKBgQDoWV35DiSVnFVt8LOgO1qOPl7SpNQyEjj/ouog+IbYpIQG6qViRX7pKTix+W04xwcRe4JjyNFYt/ZUBUJtB3YJoXGJIf3l0KVPN12sq8lC8qnXECofTmDneM2c8d5UKEbM5k4pve4uuY35NsgJNO8kLI+UmTDc2ZXN2kb8dnB5uwKBgQDb9FxXEYyrLIt9bNa1tQUINk89lW5csk5eLT2lEZtbi7kYFz7VwI9/zGrfC89OJGssEzeqHVwQ7KJQoxQ5t6pi5S5DjkCuBq2SuJUpLcp3vDFt7JSx/OWlmRF5F56qdQ/+CXFE4a6RmjZapThUoIVaMm2LhYXbn4TLwkX8R30unwKBgQC+JUybYnCcI2liZIaLzacJG4+8MPoa1ADja+7Q9rJcHx6RDnw0FQ2MQJjORYKV4YMuUnZdtN1mDn6/OeKnmWCG9yEUCcZfycVPM88sXRWC/VXy0561Kg5xv9H86494LdHrI1q6HF4MzPucBLHlnvVKFFGYWVm8kzBeW5CWUVMwuwKBgQCXz3ugOKOfTVplUiaLOYpklZ9xgN6wKfz+8Ti34oQY1kIFR539yC8C5OLqWWtWoro5rHXh+uudXsXU5AnEoGiAY2/f/qHTuxY3yyBgUJOKuqBfH0et5T/XYON3cX74ZV2JV5kPErj7E7XqMFjFyBjiHSYQgzLVV68/NzI4olrbPwKBgQDLLsqN3qqhnESy07l7yfb9uIWebNHlREcV00loEvL3LJMWGacHvY824yrx6iAI920ZWVCbqFOyZIPSG1XngKUdarh9AV5uIdSX5rueRQCaHbtv30vhF9gcTNYSCxKj6XnvzQuxIYSLCroEPpeG7Q6SU+qv+X34yAV+5RNFhFHE5A==\", \"alipay_public_key\": \"2313132131+LFlVLJm/SueP96LLW/VvE80Gddw3fipqIlUGwluYRBOF5l34RCAKhLX3KEE4wZ1M8wcoh3kBt/C2rCLH25sXnGqkNpwaLdTftMr0xNoDK0pXbtZ88PbZWE9GwVKGo7lzm/qUkRsdoQF9PcD7Vk0u6gwx506NgEOazCC8li3OajYsj3vxD207L7MrS11xp/0Gdv6uxQ6bh0CvKwzV0TeUteanHWmyLg7ky3X0dIOEOgdDZzzZhMgJKA6O9BDVZ5XMGXUFaYjR/q9SXfCQIDAQAB\"}', '1', null, '2018-04-10 17:54:33', null, '2018-05-10 14:20:01', null);
INSERT INTO `pay_channel` VALUES ('5', 'WX_JSAPI', 'WX', '1000001', '8000000000', '1', '{\"mchId\":\"1406739302\",\"appId\":\"11\",\"mchKey\":\"3sdrew32432de34r445434353535fr43\",\"certLocalPath\":\"\",\"certPassword\":\"\"}', '1', '', '2018-04-23 11:42:41', null, '2018-05-10 14:20:02', null);
INSERT INTO `pay_channel` VALUES ('6', 'WX_APP', 'WX', '1000001', '8000000000', '1', '{\"mchId\":\"1447969502\",\"appId\":\"11\",\"mchKey\":\"23dhr32483jfhewr4390fgrjkt453535\",\"certLocalPath\":\"\",\"certPassword\":\"\"}', '1', '', '2018-04-23 11:42:43', null, '2018-05-10 14:20:03', null);
INSERT INTO `pay_channel` VALUES ('7', 'WX_NATIVE', 'WX', '1000001', '8000000000', '1', '{\"mchId\":\"1406739302\",\"appId\":\"11\",\"mchKey\":\"3sdrew32432de34r445434353535fr43\",\"certLocalPath\":\"\",\"certPassword\":\"\"}', '1', '', '2018-04-23 11:42:46', null, '2018-05-10 14:20:03', null);
INSERT INTO `pay_channel` VALUES ('8', 'WX_MWEB', 'WX', '1000001', '8000000000', '1', '{\"mchId\":\"1447969502\",\"appId\":\"11\",\"mchKey\":\"23dhr32483jfhewr4390fgrjkt453535\",\"certLocalPath\":\"\",\"certPassword\":\"\"}', '1', '', '2018-04-23 11:42:49', null, '2018-05-10 14:20:05', null);
INSERT INTO `pay_channel` VALUES ('173095533173284864', 'ALIPAY_WAP', 'ALIPAY', '123213', '8000000001', '1', '{\"isSandbox\":1,\"appid\": \"2016080300154678\", \"private_key\": \"MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDHojxgxIzTDVgpiitiF+MpYqA+zvu+3GsFNqt1kCqBq9JaqISPmTinvzblEcvh+4n+95UvRNeN+Ecc5tT4HM4lACsFWa6VKkJuTcayL0hCQSeayDvjsBYeIMu4nPAuLkxNQNS8Q5wu/S2vjmLhByE+pc7iOegPOYnVJ4qu3gYHDFJBYBcmt3c6z1+a1QYEXsxkeK2LTYYCDygG0hUipbjCWiD//RTRwMEAfkXCZUrWdee+bXaQs7Vj20LZiTY+UlxWMzbmqDVMu4aE9Sb8rWQlrLMylEuxmntQ2BlI49E+1ljEJ/VwD8RjHOAYeYVxXz9iVIQcf1Dj7TGvtdgTCjUlAgMBAAECggEABP7iU+mXPJDQ6/kxLLs8TATTzb6QPi513yOHFuIMrkIGeY5OAunqqQf377OKTpVk1qSLbyth2YOlA+wi2DKgVfyzFucvnzv3fZskpcDZJg9LVmw8U25Mn2RitLP4rq8p6aT9rdhjSCm9wu7lOoxbIJ41lyOr0vW1pUfgCJA1xmdVNgun910Ib8Fv1zjPPzDbwKxpU2Xa2gdT4BpS8GIaJy3ipuEoZVCJM3xVmYYzoOXulFMBA4No0dhw7Po+7Qfq4F7oAYSTLcT4mWH9dctmmjqTb3VwIq9m/txsYGHKU5BFlFMpTH4UU+37VGgTvasaOWMudWNXrRSoCZK0RAT64QKBgQDoWV35DiSVnFVt8LOgO1qOPl7SpNQyEjj/ouog+IbYpIQG6qViRX7pKTix+W04xwcRe4JjyNFYt/ZUBUJtB3YJoXGJIf3l0KVPN12sq8lC8qnXECofTmDneM2c8d5UKEbM5k4pve4uuY35NsgJNO8kLI+UmTDc2ZXN2kb8dnB5uwKBgQDb9FxXEYyrLIt9bNa1tQUINk89lW5csk5eLT2lEZtbi7kYFz7VwI9/zGrfC89OJGssEzeqHVwQ7KJQoxQ5t6pi5S5DjkCuBq2SuJUpLcp3vDFt7JSx/OWlmRF5F56qdQ/+CXFE4a6RmjZapThUoIVaMm2LhYXbn4TLwkX8R30unwKBgQC+JUybYnCcI2liZIaLzacJG4+8MPoa1ADja+7Q9rJcHx6RDnw0FQ2MQJjORYKV4YMuUnZdtN1mDn6/OeKnmWCG9yEUCcZfycVPM88sXRWC/VXy0561Kg5xv9H86494LdHrI1q6HF4MzPucBLHlnvVKFFGYWVm8kzBeW5CWUVMwuwKBgQCXz3ugOKOfTVplUiaLOYpklZ9xgN6wKfz+8Ti34oQY1kIFR539yC8C5OLqWWtWoro5rHXh+uudXsXU5AnEoGiAY2/f/qHTuxY3yyBgUJOKuqBfH0et5T/XYON3cX74ZV2JV5kPErj7E7XqMFjFyBjiHSYQgzLVV68/NzI4olrbPwKBgQDLLsqN3qqhnESy07l7yfb9uIWebNHlREcV00loEvL3LJMWGacHvY824yrx6iAI920ZWVCbqFOyZIPSG1XngKUdarh9AV5uIdSX5rueRQCaHbtv30vhF9gcTNYSCxKj6XnvzQuxIYSLCroEPpeG7Q6SU+qv+X34yAV+5RNFhFHE5A==\", \"alipay_public_key\": \"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAp61DVvZ25K8Svs9mnT5PE4BinlotyJ1f6CxaRTFb0HvIvOXMNgwtQ8dw4SnQsfcrJDpTh+LFlVLJm/SueP96LLW/VvE80Gddw3fipqIlUGwluYRBOF5l34RCAKhLX3KEE4wZ1M8wcoh3kBt/C2rCLH25sXnGqkNpwaLdTftMr0xNoDK0pXbtZ88PbZWE9GwVKGo7lzm/qUkRsdoQF9PcD7Vk0u6gwx506NgEOazCC8li3OajYsj3vxD207L7MrS11xp/0Gdv6uxQ6bh0CvKwzV0TeUteanHWmyLg7ky3X0dIOEOgdDZzzZhMgJKA6O9BDVZ5XMGXUFaYjR/q9SXfCQIDAQAB\"}', '1', null, '2018-04-23 15:39:51', '1', '2018-05-10 15:23:52', '1');
INSERT INTO `pay_channel` VALUES ('173106461151338496', 'ALIPAY_WAP', 'ALIPAY', '21313', '123123', null, '21313', '0', null, '2018-04-23 16:23:17', '1', '2018-05-10 14:20:31', '1');
INSERT INTO `pay_channel` VALUES ('173106728605327360', 'ALIPAY_WAP', 'ALIPAY', '1', '1', null, '1', '0', null, '2018-04-23 16:24:20', '1', '2018-05-10 14:20:32', '1');
INSERT INTO `pay_channel` VALUES ('173108437444468736', 'ALIPAY_WAP', 'ALIPAY', '1', '1', null, '1', '0', null, '2018-04-23 16:31:08', '1', '2018-05-10 14:20:33', '1');
INSERT INTO `pay_channel` VALUES ('173108511650095104', 'ALIPAY_WAP', 'ALIPAY', '12', '12', null, '21', '0', null, '2018-04-23 16:31:25', '1', '2018-05-10 14:20:34', '1');
INSERT INTO `pay_channel` VALUES ('173108896297132032', 'ALIPAY_WAP', 'ALIPAY', '2313', '1232131', '1', '13213', '1', null, '2018-04-23 16:32:57', '1', '2018-05-10 14:20:35', '1');

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
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of pay_order
-- ----------------------------
INSERT INTO `pay_order` VALUES ('1', '20180410171600004', '1', '8000000000', '1231', 'ALIPAY_WAP', '100.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-04 15:56:52', '2018-04-04 15:56:55', '2018-04-10 17:31:05', '1', null, '1', '2018-04-04 15:57:01', null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('2', '20180410171600006', '1', '8000000000', '1231', 'ALIPAY_PC', '100.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-10 18:06:56', '1', null, '1', null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('3', '20180410171600010', '1', '8000000000', '1231', 'ALIPAY_QR', '100.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-11 09:47:06', '1', null, '1', null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('4', '20180410171600020', '1', '8000000000', '1231', 'ALIPAY_MOBILE', '100.00', 'CNY', '00', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-10 18:06:56', '1', null, '1', null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('5', '20180410171600013', '1', '8000000000', '1231', 'WX_JSAPI', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-12 17:17:36', '1', null, '1', null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('6', '20180410171600012', '1', '8000000000', '1231', 'WX_JSAPI', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-12 15:33:06', '1', null, '1', null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('7', '20180410171600032', '1', '8000000000', '1231', 'WX_NATIVE', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-12 17:59:17', '1', null, '1', null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('8', '20180410171600033', '1', '8000000000', '1231', 'WX_NATIVE', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-12 17:30:58', '1', null, '1', null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('9', '20180410171600034', '1', '8000000000', '1231', 'WX_NATIVE', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-12 18:08:43', '1', null, '1', null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('10', '20180410171600035', '1', '8000000000', '1231', 'WX_NATIVE', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-12 18:03:20', '1', null, '1', null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('11', '20180410171600036', '1', '8000000000', '1231', 'WX_NATIVE', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-12 17:05:42', '1', null, '1', null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('12', '20180410171600037', '1', '8000000000', '1231', 'WX_NATIVE', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-12 17:14:44', '1', null, '1', null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('13', '20180410171600038', '1', '8000000000', '1231', 'WX_NATIVE', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-12 17:44:24', '1', null, '1', null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('14', '20180410171600039', '1', '8000000000', '1231', 'WX_NATIVE', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-12 17:57:42', '1', null, '1', null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('15', '20180410171600040', '1', '8000000000', '1231', 'WX_NATIVE', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-12 18:01:19', '1', null, '1', null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('16', '20180410171600041', '1', '8000000000', '1231', 'WX_NATIVE', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-13 10:14:29', '1', null, null, null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('17', '20180410171600042', '1', '8000000000', '1231', 'WX_NATIVE', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-13 10:13:19', '1', null, null, null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('18', '20180410171600043', '1', '8000000000', '1231', 'WX_NATIVE', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-13 10:25:24', '1', null, null, null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('19', '20180410171600044', '1', '8000000000', '1231', 'WX_NATIVE', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-13 15:42:22', '1', null, null, null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('20', '20180410171600045', '1', '8000000000', '1231', 'WX_NATIVE', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-13 16:42:07', '1', null, null, null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('21', '20180410171600052', '1', '8000000000', '1231', 'WX_MWEB', '1.00', 'CNY', '00', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-13 10:25:24', '1', null, null, null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('22', '20180410171600053', '1', '8000000000', '1231', 'WX_MWEB', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-16 10:01:21', '1', null, null, null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('23', '20180410171600054', '1', '8000000000', '1231', 'ALIPAY_PC', '1.00', 'CNY', '00', null, null, null, null, null, '1000001', null, null, null, null, '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-16 10:01:21', '1', null, null, null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('24', '20180410171600070', '1', '8000000000', '1231', 'ALIPAY_WAP', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', '2018042821001004800200323482', null, null, 'http://sys.javaxxw.cn/test/notify', '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-28 15:46:16', '1', null, null, null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('25', '20180410171600071', '1', '8000000000', '1231', 'ALIPAY_WAP', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', '2018042821001004800200323484', null, null, 'http://sys.javaxxw.cn/test/notify', '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-28 16:14:13', '1', null, null, null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('26', '20180410171600072', '1', '8000000000', '1231', 'ALIPAY_WAP', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', '2018042821001004800200323485', null, null, 'http://sys.javaxxw.cn/test/notify', '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-28 16:56:04', '1', null, null, null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('27', '20180410171600073', '1', '8000000000', '1231', 'ALIPAY_WAP', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', '2018050221001004800200324665', null, null, 'http://sys.javaxxw.cn/test/notify', '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-05-02 14:08:54', '1', null, null, null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('28', '20180410171600074', '1', '8000000000', '1231', 'ALIPAY_WAP', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', '2018050221001004800200324666', null, null, 'http://sys.javaxxw.cn/test/notify', '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-05-02 14:15:29', '1', null, null, null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('29', '20180410171600075', '1', '8000000000', '1231', 'ALIPAY_WAP', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', '2018050221001004800200324667', null, null, 'http://sys.javaxxw.cn/test/notify', '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-05-02 14:18:56', '1', null, null, null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('30', '20180410171600076', '1', '8000000000', '1231', 'ALIPAY_WAP', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', '2018050221001004800200324668', null, null, 'http://sys.javaxxw.cn/test/notify', '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-05-02 14:28:36', '1', null, null, null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('31', '20180410171600077', '1', '8000000000', '1231', 'ALIPAY_WAP', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', '2018050221001004800200324503', null, null, 'http://sys.javaxxw.cn/test/notify', '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-05-02 15:07:11', '1', null, null, null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('32', '20180410171600078', '1', '8000000000', '1231', 'ALIPAY_WAP', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', '2018050221001004800200324504', null, null, 'http://sys.javaxxw.cn/test/notify', '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-05-02 15:08:30', '1', null, null, null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('33', '20180410171600079', '1', '8000000000', '1231', 'ALIPAY_WAP', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', '2018050221001004800200324505', null, null, 'http://sys.javaxxw.cn/test/notify', '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-05-02 15:10:13', '1', null, null, null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('34', '20180410171600080', '1', '8000000000', '1231', 'ALIPAY_WAP', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', '2018050221001004800200324506', null, null, 'http://sys.javaxxw.cn/test/notify', '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-05-02 15:12:22', '1', null, null, null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('35', '20180410171600081', '1', '8000000000', '1231', 'ALIPAY_WAP', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', '2018050221001004800200324669', null, null, 'http://sys.javaxxw.cn/test/notify', '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-05-02 15:13:20', '1', null, null, null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('36', '20180410171600082', '1', '8000000000', '1231', 'ALIPAY_WAP', '1.00', 'CNY', '00', null, null, null, null, null, '1000001', '2018050221001004800200324505', null, null, 'http://sys.javaxxw.cn/test/notify', '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-05-02 15:10:13', '1', null, null, null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('37', '20180410171600083', '1', '8000000000', '1231', 'ALIPAY_WAP', '1.00', 'CNY', '02', null, null, null, null, null, '1000001', '2018050221001004800200324670', null, null, 'http://sys.javaxxw.cn/test/notify', '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-05-02 15:24:38', '1', null, null, null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('38', '20180410171600090', '1', '8000000000', '1231', 'WX_JSAPI', '0.01', 'CNY', '02', null, null, null, null, null, '1000001', '4200000125201805085237510485', null, null, 'http://sys.javaxxw.cn/test/notify', '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-05-08 12:32:09', '1', null, '1', null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('39', '20180410171600091', '1', '8000000000', '1231', 'WX_JSAPI', '0.01', 'CNY', '00', null, null, null, null, null, '1000001', null, null, null, 'http://sys.javaxxw.cn/test/notify', '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-04-12 17:17:36', '1', null, '1', null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('40', '20180410171600092', '1', '8000000000', '1231', 'WX_NATIVE', '0.01', 'CNY', '02', null, null, null, null, null, '1000001', '4200000117201805085299373326', null, null, 'http://sys.javaxxw.cn/test/notify', '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-05-08 11:40:08', '1', null, '1', null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('41', '20180410171600093', '1', '8000000000', '1231', 'WX_MWEB', '0.01', 'CNY', '02', null, null, null, null, null, '1000001', '4200000154201805084684635710', null, null, 'http://sys.javaxxw.cn/test/notify', '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-05-08 15:09:59', '1', null, null, null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('42', '20180410171600094', '1', '8000000000', '1231', 'WX_MWEB', '0.01', 'CNY', '02', null, null, null, null, null, '1000001', '4200000148201805085181608105', null, null, 'http://sys.javaxxw.cn/test/notify', '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-05-08 14:39:00', '1', null, null, null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('43', '20180410171600095', '1', '8000000000', '1231', 'WX_NATIVE', '0.01', 'CNY', '02', null, null, null, null, null, '1000001', '4200000120201805085110450593', null, null, 'http://sys.javaxxw.cn/test/notify', '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-05-08 14:47:04', '1', null, null, null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('44', '20180410171600096', '1', '8000000000', '1231', 'WX_MWEB', '0.01', 'CNY', '02', null, null, null, null, null, '1000001', '4200000154201805085167849386', null, null, 'http://sys.javaxxw.cn/test/notify', '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-05-08 15:05:06', '1', null, null, null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('45', '20180410171600097', '1', '8000000000', '1231', 'WX_APP', '0.01', 'CNY', '00', null, null, null, null, null, '1000001', '4200000148201805085181608105', null, null, 'http://sys.javaxxw.cn/test/notify', '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-05-08 14:39:00', '1', null, null, null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('46', '20180410171600098', '1', '8000000000', '1231', 'WX_NATIVE', '0.01', 'CNY', '00', null, null, null, null, null, '1000001', '4200000148201805085181608105', null, null, 'http://sys.javaxxw.cn/test/notify', '0', '2018-04-10 17:55:10', '2018-04-10 17:55:14', '2018-05-08 14:39:00', '1', null, null, null, null, '2018-05-10 11:53:47');
INSERT INTO `pay_order` VALUES ('179236228355325952', '2018051014204610000002', null, '8000000000', '20180410171600099', 'WX_NATIVE', '0.01', 'CNY', '00', '127.0.0.1', 'PC', '测试商品标题', '测试商品描述', '{\"productId\": \"20180410171600099\"}', '1000001', null, null, null, 'http://sys.javaxxw.cn/test/notify', '0', null, null, null, '1', '备注', null, '2018-05-10 14:20:47', null, '2018-05-10 15:22:36');
INSERT INTO `pay_order` VALUES ('179242127790702592', '2018051014441310000003', null, '8000000000', '20180410171600100', 'WX_NATIVE', '0.01', 'CNY', '02', '127.0.0.1', 'PC', '测试商品标题', '测试商品描述', '{\"productId\": \"20180410171600099\"}', '1000001', '4200000131201805106616620509', null, null, 'http://sys.javaxxw.cn/test/notify', '0', null, null, '2018-05-10 15:19:20', '1', '备注', null, '2018-05-10 14:44:13', null, '2018-05-10 15:07:53');
INSERT INTO `pay_order` VALUES ('179245905851994112', '2018051014591410000004', null, '8000000000', '20180410171600101', 'WX_NATIVE', '0.01', 'CNY', '02', '127.0.0.1', 'PC', '测试商品标题', '测试商品描述', '{\"productId\": \"20180410171600101\"}', '1000001', '4200000111201805106699097131', null, null, 'http://sys.javaxxw.cn/test/notify', '0', null, null, '2018-05-10 16:06:38', '1', '备注', null, '2018-05-10 14:59:14', null, '2018-05-10 15:07:54');
INSERT INTO `pay_order` VALUES ('179247768836001792', '2018051015063810000005', null, '8000000000', '20180410171600102', 'WX_NATIVE', '0.01', 'CNY', '02', '127.0.0.1', 'PC', '测试商品标题', '测试商品描述', '{\"productId\": \"20180410171600102\"}', '1000001', '4200000138201805106813667366', null, null, 'http://sys.javaxxw.cn/test/notify', '0', null, null, '2018-05-10 15:07:58', '1', '备注', null, '2018-05-10 15:06:38', null, '2018-05-10 15:07:54');
INSERT INTO `pay_order` VALUES ('179252227263709184', '2018051015242110000009', null, null, '20180410171600103', 'ALIPAY_WAP', '1.00', 'CNY', '00', '127.0.0.1', 'PC', '测试商品标题', '测试商品描述', '1212', '1000001', null, null, null, 'http://sys.javaxxw.cn/test/notify', '0', null, null, null, '1', '备注', null, '2018-05-10 15:24:21', null, null);
INSERT INTO `pay_order` VALUES ('179252407857856512', '2018051015250410000010', null, null, '20180410171600103', 'ALIPAY_WAP', '1.00', 'CNY', '00', '127.0.0.1', 'PC', '测试商品标题', '测试商品描述', '1212', '1000001', null, null, null, 'http://sys.javaxxw.cn/test/notify', '0', null, null, null, '1', '备注', null, '2018-05-10 15:25:04', null, null);
INSERT INTO `pay_order` VALUES ('179252633532383232', '2018051015255810000011', null, null, '20180410171600103', 'ALIPAY_WAP', '1.00', 'CNY', '00', '127.0.0.1', 'PC', '测试商品标题', '测试商品描述', '1212', '1000001', null, null, null, 'http://sys.javaxxw.cn/test/notify', '0', null, null, null, '1', '备注', null, '2018-05-10 15:25:58', null, null);
INSERT INTO `pay_order` VALUES ('179252989184196608', '2018051015272210000012', null, null, '20180410171600103', 'ALIPAY_WAP', '1.00', 'CNY', '00', '127.0.0.1', 'PC', '测试商品标题', '测试商品描述', '1212', '1000001', null, null, null, 'http://sys.javaxxw.cn/test/notify', '0', null, null, null, '1', '备注', null, '2018-05-10 15:27:23', null, null);
INSERT INTO `pay_order` VALUES ('179253166615838720', '2018051015280510000013', null, null, '20180410171600103', 'ALIPAY_WAP', '1.00', 'CNY', '00', '127.0.0.1', 'PC', '测试商品标题', '测试商品描述', '1212', '1000001', null, null, null, 'http://sys.javaxxw.cn/test/notify', '0', null, null, null, '1', '备注', null, '2018-05-10 15:28:05', null, null);
INSERT INTO `pay_order` VALUES ('179253554794479616', '2018051015293710000014', null, null, '20180410171600103', 'ALIPAY_WAP', '1.00', 'CNY', '00', '127.0.0.1', 'PC', '测试商品标题', '测试商品描述', '1212', '1000001', null, null, null, 'http://sys.javaxxw.cn/test/notify', '0', null, null, null, '1', '备注', null, '2018-05-10 15:29:38', null, null);
INSERT INTO `pay_order` VALUES ('179253682913689600', '2018051015300810000015', null, null, '20180410171600103', 'ALIPAY_WAP', '1.00', 'CNY', '00', '127.0.0.1', 'PC', '测试商品标题', '测试商品描述', '1212', '1000001', null, null, null, 'http://sys.javaxxw.cn/test/notify', '0', null, null, null, '1', '备注', null, '2018-05-10 15:30:08', null, null);
INSERT INTO `pay_order` VALUES ('179255095014211584', '2018051015354210000016', null, '8000000000', '20180410171600103', 'ALIPAY_WAP', '1.00', 'CNY', '00', '127.0.0.1', 'PC', '测试商品标题', '测试商品描述', '1212', '1000001', null, null, null, 'http://sys.javaxxw.cn/test/notify', '0', null, null, null, '1', '备注', null, '2018-05-10 15:35:45', null, null);
INSERT INTO `pay_order` VALUES ('179256672538083328', '2018051015420010000017', null, '8000000000', '20180410171600104', 'ALIPAY_QR', '1.00', 'CNY', '00', '127.0.0.1', 'PC', '测试商品标题', '测试商品描述', '1212', '1000001', null, null, null, 'http://sys.javaxxw.cn/test/notify', '0', null, null, null, '1', '备注', null, '2018-05-10 15:42:01', null, null);
INSERT INTO `pay_order` VALUES ('179257019990032384', '2018051015432310000018', null, '8000000000', '20180410171600104', 'ALIPAY_QR', '1.00', 'CNY', '00', '127.0.0.1', 'PC', '测试商品标题', '测试商品描述', '1212', '1000001', null, null, null, 'http://sys.javaxxw.cn/test/notify', '0', null, null, null, '1', '备注', null, '2018-05-10 15:43:24', null, null);
INSERT INTO `pay_order` VALUES ('179257085500866560', '2018051015433910000019', null, '8000000000', '20180410171600104', 'ALIPAY_QR', '1.00', 'CNY', '00', '127.0.0.1', 'PC', '测试商品标题', '测试商品描述', '1212', '1000001', null, null, null, 'http://sys.javaxxw.cn/test/notify', '0', null, null, null, '1', '备注', null, '2018-05-10 15:43:40', null, null);
INSERT INTO `pay_order` VALUES ('179257901750173696', '2018051015465410000020', null, '8000000000', '20180410171600104', 'ALIPAY_QR', '1.00', 'CNY', '02', '127.0.0.1', 'PC', '测试商品标题', '测试商品描述', '1212', '1000001', '2018051021001004800200327429', null, null, 'http://sys.javaxxw.cn/test/notify', '0', null, null, '2018-05-10 15:49:28', '1', '备注', null, '2018-05-10 15:46:54', null, '2018-05-10 15:49:28');
INSERT INTO `pay_order` VALUES ('179258906927710208', '2018051015505310000021', null, '8000000000', '20180410171600105', 'ALIPAY_QR', '1.00', 'CNY', '00', '127.0.0.1', 'PC', '测试商品标题', '测试商品描述', '1212', '1000001', null, null, null, 'http://sys.javaxxw.cn/test/notify', '0', null, null, null, '1', '备注', null, '2018-05-10 15:50:54', null, null);
INSERT INTO `pay_order` VALUES ('179259307387273216', '2018051015522910000022', null, '8000000000', '20180410171600105', 'ALIPAY_QR', '1.00', 'CNY', '00', '127.0.0.1', 'PC', '测试商品标题', '测试商品描述', '1212', '1000001', null, null, null, 'http://sys.javaxxw.cn/test/notify', '0', null, null, null, '1', '备注', null, '2018-05-10 15:52:29', null, null);
INSERT INTO `pay_order` VALUES ('179260306671812608', '2018051015562710000023', null, '8000000000', '20180410171600106', 'ALIPAY_MOBILE', '1.00', 'CNY', '00', '127.0.0.1', 'PC', '测试商品标题', '测试商品描述', '1212', '1000001', null, null, null, 'http://sys.javaxxw.cn/test/notify', '0', null, null, null, '1', '备注', null, '2018-05-10 15:56:28', null, null);

-- ----------------------------
-- Table structure for sys_dic
-- ----------------------------
DROP TABLE IF EXISTS `sys_dic`;
CREATE TABLE `sys_dic` (
  `id_` bigint(32) NOT NULL,
  `index_id` bigint(32) DEFAULT NULL,
  `code_` varchar(50) DEFAULT NULL,
  `code_text` varchar(100) DEFAULT NULL,
  `sort_no` int(2) DEFAULT NULL,
  `editable_` tinyint(1) NOT NULL DEFAULT '1',
  `enable_` tinyint(1) NOT NULL DEFAULT '1',
  `remark_` varchar(500) DEFAULT NULL,
  `create_by` bigint(32) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_by` bigint(32) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `field_id_code` (`index_id`,`code_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典明细表';

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
INSERT INTO `sys_dic` VALUES ('173039178399875072', '173039083105288192', 'WX', '微信', '1', '1', '1', '微信渠道', '1', '2018-04-23 11:55:55', '1', '2018-04-23 11:55:55');
INSERT INTO `sys_dic` VALUES ('173039237321457664', '173039083105288192', 'ALIPAY', '支付宝', '2', '1', '1', '支付宝渠道', '1', '2018-04-23 11:56:09', '1', '2018-04-23 11:56:09');
INSERT INTO `sys_dic` VALUES ('173083841353756672', '173083663888560128', 'ALIPAY_WAP', '支付宝wap支付', '1', '1', '1', '支付宝wap支付', '1', '2018-04-23 14:53:24', '1', '2018-04-23 14:53:24');
INSERT INTO `sys_dic` VALUES ('173083913361567744', '173083663888560128', 'ALIPAY_PC', '支付宝PC支付', '2', '1', '1', '支付宝PC支付', '1', '2018-04-23 14:53:41', '1', '2018-04-23 14:53:41');
INSERT INTO `sys_dic` VALUES ('173084000024276992', '173083663888560128', 'ALIPAY_QR', '支付宝扫码支付', '3', '1', '1', '支付宝扫码支付', '1', '2018-04-23 14:54:01', '1', '2018-04-23 14:54:01');
INSERT INTO `sys_dic` VALUES ('173084094337396736', '173083663888560128', 'ALIPAY_MOBILE', '支付宝移动支付', '4', '1', '1', '支付宝移动支付', '1', '2018-04-23 14:54:24', '1', '2018-04-23 14:54:24');
INSERT INTO `sys_dic` VALUES ('173084169209917440', '173083663888560128', 'WX_JSAPI', '微信公众号支付', '5', '1', '1', '微信公众号支付', '1', '2018-04-23 14:54:42', '1', '2018-04-23 14:54:42');
INSERT INTO `sys_dic` VALUES ('173084252735287296', '173083663888560128', 'WX_APP', '微信移动支付', '6', '1', '1', '微信移动支付', '1', '2018-04-23 14:55:02', '1', '2018-04-23 14:55:02');
INSERT INTO `sys_dic` VALUES ('173084314852929536', '173083663888560128', 'WX_NATIVE', '微信扫码支付', '7', '1', '1', '微信扫码支付', '1', '2018-04-23 14:55:16', '1', '2018-04-23 14:55:16');
INSERT INTO `sys_dic` VALUES ('173084399066165248', '173083663888560128', 'WX_MWEB', '微信H5支付', '8', '1', '1', '微信H5支付', '1', '2018-04-23 14:55:36', '1', '2018-04-23 14:55:36');
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
  `create_by` bigint(32) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_by` bigint(32) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
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
INSERT INTO `sys_dic_index` VALUES ('173039083105288192', '0', 'CHANNEL_NAME', '支付渠道名称', '1', null, '1', '2018-04-23 11:55:32', '1', '2018-04-23 11:55:32');
INSERT INTO `sys_dic_index` VALUES ('173083663888560128', '0', 'PAY_CHANNEL', '支付渠道', '1', null, '1', '2018-04-23 14:52:41', '1', '2018-04-23 14:52:41');
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
INSERT INTO `sys_menu` VALUES ('171313540404092928', '171305767331176448', '新增商户', '3', 'manager:mchinfo:add', '/mchinfo/add', null, '0', '27', '1', null, '1', '2018-04-23 10:01:18', '1', '2018-04-18 17:38:50');
INSERT INTO `sys_menu` VALUES ('173010378932883456', '171305767331176448', '编辑商户', '3', 'manager:mchinfo:edit', '/mchinfo/edit', null, '0', '28', '1', null, '1', '2018-04-23 10:01:29', '1', '2018-04-23 10:01:28');
INSERT INTO `sys_menu` VALUES ('173010544066826240', '171305767331176448', '删除商户', '3', 'manager:mchinfo:del', '/mchinfo/del', null, '0', '29', '1', null, '1', '2018-04-23 10:02:08', '1', '2018-04-23 10:02:08');
INSERT INTO `sys_menu` VALUES ('173035558686167040', '171305390070308864', '支付渠道', '2', 'manager:channel:read', '/channel/list', null, '0', '30', '1', null, '1', '2018-04-23 11:41:32', '1', '2018-04-23 11:41:32');
INSERT INTO `sys_menu` VALUES ('173079242307801088', '173035558686167040', '新增渠道', '3', 'manager:channel:add', '/channel/add', null, '0', '31', '1', null, '1', '2018-04-23 14:35:07', '1', '2018-04-23 14:35:07');
INSERT INTO `sys_menu` VALUES ('173079363246362624', '173035558686167040', '编辑渠道', '3', 'manager:channel:edit', '/channel/edit', null, '0', '32', '1', null, '1', '2018-04-23 14:35:36', '1', '2018-04-23 14:35:35');
INSERT INTO `sys_menu` VALUES ('173079475456577536', '173035558686167040', '删除渠道', '3', 'manager:channel:del', '/channel/del', null, '0', '33', '1', null, '1', '2018-04-23 14:36:03', '1', '2018-04-23 14:36:02');
INSERT INTO `sys_menu` VALUES ('174894106445615104', '0', 'MQ消息管理', '1', '-', '-', 'fa fa-desktop', '0', '34', '1', null, '1', '2018-04-28 14:50:45', '1', '2018-04-28 14:46:44');
INSERT INTO `sys_menu` VALUES ('174894815010361344', '174894106445615104', '通知消息管理', '2', 'manager:notify:read', '/notify/list', null, '0', '35', '1', null, '1', '2018-04-28 14:49:33', '1', '2018-04-28 14:49:33');
INSERT INTO `sys_menu` VALUES ('174894953153957888', '174894106445615104', '消息日志', '2', 'manager:notifyLog:read', '/notify/logList', null, '0', '36', '1', null, '1', '2018-04-28 14:50:06', '1', '2018-04-28 14:50:06');
INSERT INTO `sys_menu` VALUES ('174902977679593472', '174894815010361344', '消息重发', '3', 'manager:notify:resend', '/notify/resend', null, '0', '37', '1', null, '1', '2018-04-28 15:22:45', '1', '2018-04-28 15:22:45');
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
INSERT INTO `sys_menu_user` VALUES ('174903246119243776', '166201582835204096', '882861947399626752', '1', null, null, '2018-04-28 15:23:03', null, '2018-04-28 15:23:03');
INSERT INTO `sys_menu_user` VALUES ('174903246144409600', '166202003960102912', '882861947399626752', '1', null, null, '2018-04-28 15:23:03', null, '2018-04-28 15:23:03');
INSERT INTO `sys_menu_user` VALUES ('174903246152798208', '171305390070308864', '882861947399626752', '1', null, null, '2018-04-28 15:23:03', null, '2018-04-28 15:23:03');
INSERT INTO `sys_menu_user` VALUES ('174903246156992512', '171305767331176448', '882861947399626752', '1', null, null, '2018-04-28 15:23:03', null, '2018-04-28 15:23:03');
INSERT INTO `sys_menu_user` VALUES ('174903246165381120', '171313540404092928', '882861947399626752', '1', null, null, '2018-04-28 15:23:03', null, '2018-04-28 15:23:03');
INSERT INTO `sys_menu_user` VALUES ('174903246173769728', '173010378932883456', '882861947399626752', '1', null, null, '2018-04-28 15:23:03', null, '2018-04-28 15:23:03');
INSERT INTO `sys_menu_user` VALUES ('174903246177964032', '173010544066826240', '882861947399626752', '1', null, null, '2018-04-28 15:23:03', null, '2018-04-28 15:23:03');
INSERT INTO `sys_menu_user` VALUES ('174903246182158336', '173035558686167040', '882861947399626752', '1', null, null, '2018-04-28 15:23:03', null, '2018-04-28 15:23:03');
INSERT INTO `sys_menu_user` VALUES ('174903246186352640', '173079242307801088', '882861947399626752', '1', null, null, '2018-04-28 15:23:03', null, '2018-04-28 15:23:03');
INSERT INTO `sys_menu_user` VALUES ('174903246194741248', '173079363246362624', '882861947399626752', '1', null, null, '2018-04-28 15:23:03', null, '2018-04-28 15:23:03');
INSERT INTO `sys_menu_user` VALUES ('174903246198935552', '173079475456577536', '882861947399626752', '1', null, null, '2018-04-28 15:23:03', null, '2018-04-28 15:23:03');
INSERT INTO `sys_menu_user` VALUES ('174903246203129856', '174894106445615104', '882861947399626752', '1', null, null, '2018-04-28 15:23:03', null, '2018-04-28 15:23:03');
INSERT INTO `sys_menu_user` VALUES ('174903246207324160', '174894815010361344', '882861947399626752', '1', null, null, '2018-04-28 15:23:03', null, '2018-04-28 15:23:03');
INSERT INTO `sys_menu_user` VALUES ('174903246215712768', '174894953153957888', '882861947399626752', '1', null, null, '2018-04-28 15:23:03', null, '2018-04-28 15:23:03');
INSERT INTO `sys_menu_user` VALUES ('174903246219907072', '174902977679593472', '882861947399626752', '1', null, null, '2018-04-28 15:23:03', null, '2018-04-28 15:23:03');

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
INSERT INTO `sys_role_menu` VALUES ('173010627567030272', '2', '173010378932883456', '1', null, null, '2018-04-23 10:02:28', null, '2018-04-23 10:02:27');
INSERT INTO `sys_role_menu` VALUES ('173010627579613184', '2', '173010544066826240', '1', null, null, '2018-04-23 10:02:28', null, '2018-04-23 10:02:27');
INSERT INTO `sys_role_menu` VALUES ('173035612356481024', '2', '173035558686167040', '1', null, null, '2018-04-23 11:41:45', null, '2018-04-23 11:41:44');
INSERT INTO `sys_role_menu` VALUES ('173079516413956096', '2', '173079242307801088', '1', null, null, '2018-04-23 14:36:12', null, '2018-04-23 14:36:12');
INSERT INTO `sys_role_menu` VALUES ('173079516418150400', '2', '173079363246362624', '1', null, null, '2018-04-23 14:36:12', null, '2018-04-23 14:36:12');
INSERT INTO `sys_role_menu` VALUES ('173079516464287744', '2', '173079475456577536', '1', null, null, '2018-04-23 14:36:12', null, '2018-04-23 14:36:12');
INSERT INTO `sys_role_menu` VALUES ('174895015183519744', '2', '174894106445615104', '1', null, null, '2018-04-28 14:50:21', null, '2018-04-28 14:50:21');
INSERT INTO `sys_role_menu` VALUES ('174895015263211520', '2', '174894815010361344', '1', null, null, '2018-04-28 14:50:21', null, '2018-04-28 14:50:21');
INSERT INTO `sys_role_menu` VALUES ('174895015271600128', '2', '174894953153957888', '1', null, null, '2018-04-28 14:50:21', null, '2018-04-28 14:50:21');
INSERT INTO `sys_role_menu` VALUES ('174903208232095744', '2', '174902977679593472', '1', null, null, '2018-04-28 15:22:54', null, '2018-04-28 15:22:54');
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
INSERT INTO `sys_user` VALUES ('1', 'admin', 'admin', '3038D9CB63B3152A79B8153FB06C02F7', '66f1b370c660445a8657bf8bf1794486', '涂勇', 'http://122.114.96.161:8888/group1/M00/00/00/enJgoVqrakSALJSfAAVL_5MzVJ4789.png', '1', '1', '18559756158', '192312@qq.com', '1', null, '209', '2018-05-02 16:12:19', '2018-04-28 14:46:01', '192.168.56.1', '192.168.56.1', '1', '1', null, '1', '2017-06-20 15:02:41', '1', '2018-05-02 16:12:19');
INSERT INTO `sys_user` VALUES ('882861947399626752', 'tuyong212', 'tuyong', '940817937DA2C24B5D40514058C21033', 'd77ccdb686f04164a105f609532a19f6', null, 'http://192.168.1.220:8888/group1/M00/00/00/wKgB3Fpgo8aAdaUTAADf_sA3_Oc435.jpg', '1', '1', '18559756159', '1972873004@qq.com', '1', null, '37', '2018-05-02 16:11:48', '2018-05-02 16:11:44', '192.168.56.1', '192.168.56.1', '1', '1', null, '1', '2017-07-06 15:21:06', '1', '2018-05-02 16:11:48');

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
INSERT INTO `sys_user_role` VALUES ('166107288573054976', '882861947399626752', '2', '0', null, null, '2018-04-28 15:23:03', '1', '2018-04-28 15:23:03');
INSERT INTO `sys_user_role` VALUES ('166205778221338624', '882861947399626752', '2', '0', null, null, '2018-04-28 15:23:03', '1', '2018-04-28 15:23:03');
INSERT INTO `sys_user_role` VALUES ('166206607837892608', '882861947399626752', '2', '0', null, null, '2018-04-28 15:23:03', '1', '2018-04-28 15:23:03');
INSERT INTO `sys_user_role` VALUES ('166208698102845440', '882861947399626752', '2', '0', null, null, '2018-04-28 15:23:03', '1', '2018-04-28 15:23:03');
INSERT INTO `sys_user_role` VALUES ('166209063921651712', '882861947399626752', '2', '0', null, null, '2018-04-28 15:23:03', '1', '2018-04-28 15:23:03');
INSERT INTO `sys_user_role` VALUES ('166209184507891712', '882861947399626752', '2', '0', null, null, '2018-04-28 15:23:03', '1', '2018-04-28 15:23:03');
INSERT INTO `sys_user_role` VALUES ('166214010977394688', '882861947399626752', '2', '0', null, null, '2018-04-28 15:23:03', '1', '2018-04-28 15:23:03');
INSERT INTO `sys_user_role` VALUES ('170957130382184448', '1', '1', '0', null, null, '2018-04-18 09:45:22', '1', '2018-04-18 09:45:22');
INSERT INTO `sys_user_role` VALUES ('171192528677117952', '1', '1', '0', null, null, '2018-04-18 09:45:22', '1', '2018-04-18 09:45:22');
INSERT INTO `sys_user_role` VALUES ('171194386653122560', '1', '1', '1', null, null, '2018-04-18 09:45:22', null, '2018-04-18 09:45:22');
INSERT INTO `sys_user_role` VALUES ('171305525495996416', '882861947399626752', '2', '0', null, null, '2018-04-28 15:23:03', '1', '2018-04-28 15:23:03');
INSERT INTO `sys_user_role` VALUES ('171305833819283456', '882861947399626752', '2', '0', null, null, '2018-04-28 15:23:03', '1', '2018-04-28 15:23:03');
INSERT INTO `sys_user_role` VALUES ('171314511767150592', '882861947399626752', '2', '0', null, null, '2018-04-28 15:23:03', '1', '2018-04-28 15:23:03');
INSERT INTO `sys_user_role` VALUES ('173010673322692608', '882861947399626752', '2', '0', null, null, '2018-04-28 15:23:03', '1', '2018-04-28 15:23:03');
INSERT INTO `sys_user_role` VALUES ('173035636003966976', '882861947399626752', '2', '0', null, null, '2018-04-28 15:23:03', '1', '2018-04-28 15:23:03');
INSERT INTO `sys_user_role` VALUES ('173079538660544512', '882861947399626752', '2', '0', null, null, '2018-04-28 15:23:03', '1', '2018-04-28 15:23:03');
INSERT INTO `sys_user_role` VALUES ('174895044124217344', '882861947399626752', '2', '0', null, null, '2018-04-28 15:23:03', '1', '2018-04-28 15:23:03');
INSERT INTO `sys_user_role` VALUES ('174903246068912128', '882861947399626752', '2', '1', null, null, '2018-04-28 15:23:03', null, '2018-04-28 15:23:03');
INSERT INTO `sys_user_role` VALUES ('882881064194240512', '882861947399626752', '11', '0', null, null, '2018-04-28 15:23:03', '1', '2018-04-28 15:23:03');
INSERT INTO `sys_user_role` VALUES ('882881067247693824', '882861947399626752', '10', '0', null, null, '2018-04-28 15:23:03', '1', '2018-04-28 15:23:03');
INSERT INTO `sys_user_role` VALUES ('955338601545175042', '1', '1', '0', null, null, '2018-04-18 09:45:22', '1', '2018-04-18 09:45:22');
