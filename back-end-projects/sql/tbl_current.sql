/*
Navicat MySQL Data Transfer



Target Server Type    : MYSQL
Target Server Version : 50639
File Encoding         : 65001

Date: 2020-01-02 10:56:14
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tbl_tst_current
-- ----------------------------
DROP TABLE IF EXISTS `tbl_current`;
CREATE TABLE `tbl_current`
(
    `block_height`      int(11) NOT NULL COMMENT '当前同步的最新区块高度',
    `tx_count`          int(11) NOT NULL COMMENT '当前同步的最新交易数量',
    `tstid_count`       int(11) NOT NULL COMMENT '当前同步的最新TST ID数量',
    `tstid_tx_count`    int(11) NOT NULL COMMENT '当前同步的最新TST ID相关的交易数量',
    `nontstid_tx_count` int(11) NOT NULL COMMENT '当前同步的最新非TST ID相关的交易数量'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


insert into tbl_current(block_height, tx_count, tstid_count, tstid_tx_count, nontstid_tx_count)
values (-1, 0, 0, 0, 0);
