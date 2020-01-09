/*
Navicat MySQL Data Transfer



Target Server Type    : MYSQL
Target Server Version : 50639
File Encoding         : 65001

Date: 2020-01-02 10:57:31
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tbl_tst_daily_summary
-- ----------------------------
DROP TABLE IF EXISTS `tbl_daily_summary`;
CREATE TABLE `tbl_daily_summary`
(
    `time`                 int(11)        NOT NULL COMMENT '当天的UTC0点时间戳',
    `block_count`          int(11)        NOT NULL COMMENT '当天的区块数量',
    `tx_count`             int(11)        NOT NULL COMMENT '当天的交易数量',
    `active_tstid_count`   int(11)        NOT NULL COMMENT '当天的活跃TST ID数量',
    `new_tstid_count`      int(11)        NOT NULL COMMENT '当天的新TST ID数量',
    `tst_sum`              decimal(25, 9) NOT NULL COMMENT '当天的tst流通量',
    `tsg_sum`              decimal(25, 9) NOT NULL COMMENT '当天的tsg流通量',
    `active_address_count` int(11)        NOT NULL COMMENT '当天的活跃地址数量',
    `new_address_count`    int(11)        NOT NULL COMMENT '当天的新地址数量',
    PRIMARY KEY (`time`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
