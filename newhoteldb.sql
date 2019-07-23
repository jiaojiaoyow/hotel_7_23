# Host: localhost  (Version 8.0.12)
# Date: 2019-06-25 14:31:51
# Generator: MySQL-Front 6.0  (Build 1.57)


#
# Structure for table "coupon"
#

DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon` (
  `cid` int(11) NOT NULL,
  `cname` varchar(60) NOT NULL,
  `min_amount` double DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `send_start_date` varchar(50) DEFAULT NULL,
  `send_end_date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

#
# Structure for table "getcoupon"
#

DROP TABLE IF EXISTS `getcoupon`;
CREATE TABLE `getcoupon` (
  `uid` char(55) NOT NULL,
  `cid` int(11) NOT NULL,
  `status` tinyint(4) DEFAULT '0',
  `use_start_date` varchar(50) DEFAULT NULL,
  `use_end_date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`uid`,`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

#
# Structure for table "room"
#

DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `roomname` varchar(50) NOT NULL,
  `roomintroduce` varchar(150) DEFAULT NULL,
  `roomnumber` int(4) DEFAULT NULL,
  `roomprice` double(16,4) DEFAULT NULL,
  PRIMARY KEY (`roomname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

#
# Structure for table "roomorder"
#

DROP TABLE IF EXISTS `roomorder`;
CREATE TABLE `roomorder` (
  `orderid` int(11) NOT NULL AUTO_INCREMENT,
  `roomname` varchar(50) NOT NULL,
  `uid` char(55) NOT NULL DEFAULT '',
  `uname` varchar(20) DEFAULT NULL,
  `uphone` varchar(20) DEFAULT NULL,
  `roomnumber` int(4) DEFAULT NULL,
  `ordertime` datetime DEFAULT NULL,
  `leavetime` datetime DEFAULT NULL,
  `orderday` int(4) DEFAULT NULL,
  `totalprice` double(16,4) DEFAULT NULL,
  `orderstatus` int(4) DEFAULT '1',
  `cid` int(11) DEFAULT NULL,
  `arrivetime` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`orderid`,`uid`,`roomname`),
  KEY `uid` (`uid`),
  KEY `roomname` (`roomname`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` char(55) NOT NULL,
  `ubalance` double DEFAULT NULL,
  `ugrade` int(11) DEFAULT NULL,
  `token` char(36) NOT NULL,
  `nickName` varchar(50) DEFAULT NULL,
  `avatarUrl` varchar(150) DEFAULT NULL,
  `gender` tinyint(4) DEFAULT NULL,
  `gmt_create` bigint(20) DEFAULT NULL,
  `gmt_modified` bigint(20) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
