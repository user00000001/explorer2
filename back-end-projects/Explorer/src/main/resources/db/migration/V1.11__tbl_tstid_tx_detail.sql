/*
Navicat MySQL Data Transfer



Target Server Type    : MYSQL
Target Server Version : 50639
File Encoding         : 65001

Date: 2019-04-12 11:01:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_tst_tstid_detail
-- ----------------------------
DROP TABLE IF EXISTS `tbl_tstid_tx_detail`;
CREATE TABLE `tbl_tstid_tx_detail` (
  `tx_hash` varchar(64) NOT NULL DEFAULT '' COMMENT '交易hash',
  `tx_type` int(11) NOT NULL COMMENT '区块链交易类型，208：部署合约交易 209：调用合约交易',
  `tstid` varchar(255) NOT NULL DEFAULT '' COMMENT 'TST ID',
  `tx_time` int(11) NOT NULL COMMENT '交易时间戳',
  `block_height` int(12) NOT NULL COMMENT '区块高度',
  `description` varchar(255) NOT NULL DEFAULT '' COMMENT 'TST ID交易描述',
  `fee` decimal(25,9) NOT NULL COMMENT '交易手续费',
  PRIMARY KEY (`tx_hash`),
  KEY `idx_tstid` (`tstid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
