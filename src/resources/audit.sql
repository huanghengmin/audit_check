# --------------------------------------------------------
# Host:                         127.0.0.1
# Server version:               5.6.13
# Server OS:                    Win32
# HeidiSQL version:             6.0.0.3811
# Date/time:                    2015-04-29 12:20:05
# --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

# Dumping database structure for audit
DROP DATABASE IF EXISTS `audit`;
CREATE DATABASE IF NOT EXISTS `audit` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `audit`;


# Dumping structure for table audit.account
DROP TABLE IF EXISTS `account`;
CREATE TABLE IF NOT EXISTS `account` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `user_name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `sex` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  `status` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `depart` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `title` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `start_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `end_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `start_hour` int(11) DEFAULT NULL,
  `end_hour` int(11) DEFAULT NULL,
  `description` text COLLATE utf8_bin,
  `remote_ip` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `mac` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `ip_type` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='账户表';

# Dumping data for table audit.account: ~5 rows (approximately)
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
REPLACE INTO `account` (`id`, `user_name`, `password`, `sex`, `phone`, `created_time`, `modified_time`, `status`, `depart`, `title`, `name`, `email`, `start_ip`, `end_ip`, `start_hour`, `end_hour`, `description`, `remote_ip`, `mac`, `ip_type`) VALUES
	(1, 'admin', 'S8W2gMnH8VWiT9pXRMPQxA==', '男', '0571-88888888', '2010-07-04 13:52:36', '2014-05-22 17:16:38', '有效', '信息中心', '主任', '初始化管理员', 'xiaom@hzih.net', '0.0.0.0', '192.168.254.254', 9, 18, '这是一个默认的超级用户信息', '192.168.2.176', '5C-63-BF-1D-72-07', 1),
	(2, 'authadmin', 'S8W2gMnH8VWiT9pXRMPQxA==', '男', '0571-88888888', '2012-04-12 14:22:35', '2013-05-07 18:27:30', '有效', '信息中心', '主任', '授权管理员', 'xiaom@hzih.net', '0.0.0.0', '192.168.200.254', 1, 22, '这是一个默认的授权用户信息', '', NULL, 1),
	(3, 'configadmin', 'S8W2gMnH8VWiT9pXRMPQxA==', '男', '0571-88888888', '2012-06-12 18:04:01', '2013-05-07 18:27:45', '有效', '信息中心', '主任', '配置管理员', 'xiaom@hzih.net', '0.0.0.0', '192.168.200.254', 9, 21, '这是一个默认的配置用户信息', '', NULL, 1),
	(4, 'auditadmin', 'S8W2gMnH8VWiT9pXRMPQxA==', '男', '0571-88888888', '2012-07-03 10:19:57', '2014-08-26 13:01:36', '有效', '信息中心', '主任', '审计管理员', 'xiaom@hzih.net', '0.0.0.0', '192.168.200.254', 7, 22, '这是一个默认的审计用户信息', NULL, NULL, 1),
	(5, 'test', 'S8W2gMnH8VWiT9pXRMPQxA==', '男', '0571-88880571', '2014-04-01 13:33:21', '2014-08-25 15:57:03', '有效', '信息部', '主任', 'test', '11@hzih.net', '192.168.1.1', '192.168.1.255', 9, 18, '这是一个用户信息', NULL, '', 1);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;


# Dumping structure for table audit.account_log
DROP TABLE IF EXISTS `account_log`;
CREATE TABLE IF NOT EXISTS `account_log` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `account` varchar(20) NOT NULL,
  `action` varchar(200) NOT NULL,
  `auditmodel` varchar(20) NOT NULL,
  `auditlevel` varchar(5) NOT NULL,
  `audittype` varchar(3) NOT NULL,
  `result` varchar(3) NOT NULL,
  `datetime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

# Dumping data for table audit.account_log: ~2 rows (approximately)
/*!40000 ALTER TABLE `account_log` DISABLE KEYS */;
REPLACE INTO `account_log` (`id`, `account`, `action`, `auditmodel`, `auditlevel`, `audittype`, `result`, `datetime`) VALUES
	(1, 'admin', 'action success', ' 审计模块', 'info', '001', '1', '2015-04-23 13:29:29'),
	(2, 'admin', 'action faild', '审计模块', 'error', '001', '0', '2015-01-23 13:30:05'),
	(3, 'admin', 'action success', '审计模块', 'info', '001', '1', '2014-04-23 13:39:50');
/*!40000 ALTER TABLE `account_log` ENABLE KEYS */;


# Dumping structure for table audit.account_role
DROP TABLE IF EXISTS `account_role`;
CREATE TABLE IF NOT EXISTS `account_role` (
  `account_id` bigint(20) NOT NULL DEFAULT '0',
  `role_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`account_id`,`role_id`),
  KEY `FK410D03481FCE46BD` (`role_id`),
  KEY `FK410D034811351AF7` (`account_id`),
  KEY `FK410D0348A472BB1A` (`role_id`),
  KEY `FK410D0348CEF66D7A` (`account_id`),
  KEY `FK410D034824B3696E` (`role_id`),
  KEY `FK410D0348B5F52EA6` (`account_id`),
  KEY `FK410D0348621EA781` (`role_id`),
  KEY `FK410D034818E1D3B3` (`account_id`),
  CONSTRAINT `FK410D034811351AF7` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FK410D034818E1D3B3` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FK410D03481B95B13D` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FK410D034824B3696E` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FK410D0348621EA781` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FK410D0348A472BB1A` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FK410D0348B5F52EA6` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FK410D0348CEF66D7A` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table audit.account_role: ~4 rows (approximately)
/*!40000 ALTER TABLE `account_role` DISABLE KEYS */;
REPLACE INTO `account_role` (`account_id`, `role_id`) VALUES
	(1, 1),
	(2, 2),
	(3, 3),
	(4, 4);
/*!40000 ALTER TABLE `account_role` ENABLE KEYS */;


# Dumping structure for table audit.client_log
DROP TABLE IF EXISTS `client_log`;
CREATE TABLE IF NOT EXISTS `client_log` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `CN` varchar(20) DEFAULT NULL,
  `SN` varchar(50) DEFAULT NULL,
  `O` varchar(20) DEFAULT NULL,
  `OU` varchar(20) DEFAULT NULL,
  `L` varchar(20) DEFAULT NULL,
  `ST` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `idcard` varchar(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `sourceip` varchar(20) DEFAULT NULL,
  `sourceport` varchar(20) DEFAULT NULL,
  `accessurl` varchar(200) DEFAULT NULL,
  `result` varchar(3) DEFAULT NULL,
  `upbytes` varchar(10) DEFAULT NULL,
  `downbytes` varchar(10) DEFAULT NULL,
  `audittype` varchar(3) DEFAULT NULL,
  `datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

# Dumping data for table audit.client_log: ~4 rows (approximately)
/*!40000 ALTER TABLE `client_log` DISABLE KEYS */;
REPLACE INTO `client_log` (`id`, `CN`, `SN`, `O`, `OU`, `L`, `ST`, `phone`, `idcard`, `email`, `sourceip`, `sourceport`, `accessurl`, `result`, `upbytes`, `downbytes`, `audittype`, `datetime`) VALUES
	(1, 'Trust', 'E1A273357E5D436EAC3A75C69A1D1E90', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '172.16.2.8', '3467', NULL, '1', NULL, NULL, '002', '2015-04-23 15:45:38'),
	(2, '再见', 'EAE0DA87A5F34414806073B3928B1F5C', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '172.16.2.8', '3433', NULL, '0', NULL, NULL, '005', '2015-04-23 15:45:39'),
	(3, '再见', 'EAE0DA87A5F34414806073B3928B1F5C', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '172.16.2.8', '3433', NULL, '0', NULL, NULL, '005', '2015-04-23 15:45:40'),
	(4, '再见', 'EAE0DA87A5F34414806073B3928B1F5C', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '172.16.2.8', '3433', NULL, '0', NULL, NULL, '005', '2015-04-23 15:45:42');
/*!40000 ALTER TABLE `client_log` ENABLE KEYS */;


# Dumping structure for table audit.permission
DROP TABLE IF EXISTS `permission`;
CREATE TABLE IF NOT EXISTS `permission` (
  `ID` bigint(20) NOT NULL DEFAULT '0',
  `CODE` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `PARENT_ID` int(11) DEFAULT NULL,
  `SEQ` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table audit.permission: ~23 rows (approximately)
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
REPLACE INTO `permission` (`ID`, `CODE`, `NAME`, `DESCRIPTION`, `PARENT_ID`, `SEQ`) VALUES
	(100, 'TOP_QXGL', '权限管理', NULL, 0, 0),
	(101, 'SECOND_YHGL', '用户管理', NULL, 100, 1),
	(102, 'SECOND_JSGL', '角色管理', NULL, 100, 2),
	(103, 'SECOND_AQCL', '安全策略', NULL, 100, 3),
	(110, 'TOP_WLGL', '网络管理', NULL, 0, 0),
	(111, 'SECOND_JKGL', '接口管理', NULL, 110, 1),
	(112, 'SECOND_LTCS', '连通测试', NULL, 110, 2),
	(113, 'SECOND_LYGL', '路由管理', NULL, 110, 3),
	(114, 'SECOND_PZGL', '安全配置', NULL, 110, 4),
	(120, 'TOP_XTGL', '系统管理', NULL, 0, 0),
	(121, 'SECOND_PTSM', '平台说明', NULL, 120, 1),
	(122, 'SECOND_PTGL', '平台管理', NULL, 120, 2),
	(123, 'SECOND_ZSGL', '证书管理', NULL, 120, 3),
	(124, 'SECOND_BBSJ', '版本升级', NULL, 120, 4),
	(125, 'SECOND_FWGL', '服务管理', NULL, 120, 5),
	(126, 'SECOND_BJPZ', '报警配置', NULL, 120, 6),
	(130, 'TOP_SJGL', '审计管理', NULL, 0, 0),
	(131, 'SECOND_GLRZ', '管理日志', NULL, 130, 1),
	(132, 'SECOND_RZXZ', '日志下载', NULL, 130, 2),
	(133, 'SECOND_SJCL', '审计处理', NULL, 130, 3),
	(134, 'SECOND_SJCC', '审计存储', NULL, 130, 4),
	(135, 'SECOND_SJFX', '审计分析', NULL, 130, 5),
	(140, 'TOP_JKGL', '监控管理', NULL, 0, 0),
	(141, 'SECOND_ZJJK', '主机监控', NULL, 140, 1),
	(150, 'TOP_PZGL', '配置管理', NULL, 0, 0),
	(151, 'SECOND_BJPZ', '报警配置', NULL, 150, 1);
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;


# Dumping structure for table audit.role
DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `modifiedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

# Dumping data for table audit.role: ~5 rows (approximately)
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
REPLACE INTO `role` (`id`, `name`, `description`, `createdTime`, `modifiedTime`) VALUES
	(1, '初始化管理员', '初始化管理员', '2010-07-04 15:07:08', '2015-04-24 09:29:04'),
	(2, '授权管理员', '授权管理员', '2012-07-03 10:06:20', '2012-07-03 10:06:20'),
	(3, '配置管理员', '配置管理员', '2012-03-14 12:33:05', '2012-03-14 12:33:05'),
	(4, '审计管理员', '审计管理员', '2012-06-12 18:37:24', '2015-04-20 13:02:25'),
	(5, '调试管理员', '调试管理员', '2014-04-01 15:51:14', NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;


# Dumping structure for table audit.role_permission
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE IF NOT EXISTS `role_permission` (
  `permission_id` bigint(20) NOT NULL DEFAULT '0',
  `role_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`permission_id`,`role_id`),
  KEY `FKBD40D53851BABF58` (`role_id`),
  KEY `FKBD40D53852A81638` (`permission_id`),
  KEY `FKBD40D538A472BB1A` (`role_id`),
  KEY `FKBD40D53852B0B87A` (`permission_id`),
  KEY `FKBD40D53824B3696E` (`role_id`),
  KEY `FKBD40D5387AC257CE` (`permission_id`),
  KEY `FKBD40D538621EA781` (`role_id`),
  KEY `FKBD40D5386B5D7BA1` (`permission_id`),
  CONSTRAINT `FK9C6EC93851BABF58` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FK9C6EC93852A81638` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`ID`),
  CONSTRAINT `FKBD40D53824B3696E` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKBD40D53852B0B87A` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`ID`),
  CONSTRAINT `FKBD40D538621EA781` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKBD40D5386B5D7BA1` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`ID`),
  CONSTRAINT `FKBD40D5387AC257CE` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`ID`),
  CONSTRAINT `FKBD40D538A472BB1A` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table audit.role_permission: ~49 rows (approximately)
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
REPLACE INTO `role_permission` (`permission_id`, `role_id`) VALUES
	(100, 1),
	(101, 1),
	(102, 1),
	(103, 1),
	(110, 1),
	(111, 1),
	(112, 1),
	(113, 1),
	(114, 1),
	(120, 1),
	(121, 1),
	(122, 1),
	(123, 1),
	(124, 1),
	(125, 1),
	(126, 1),
	(130, 1),
	(131, 1),
	(132, 1),
	(133, 1),
	(134, 1),
	(135, 1),
	(140, 1),
	(150, 1),
	(151, 1),
	(100, 4),
	(101, 4),
	(102, 4),
	(103, 4),
	(110, 4),
	(111, 4),
	(112, 4),
	(113, 4),
	(114, 4),
	(120, 4),
	(121, 4),
	(122, 4),
	(123, 4),
	(124, 4),
	(125, 4),
	(126, 4),
	(130, 4),
	(131, 4),
	(132, 4),
	(133, 4),
	(134, 4),
	(135, 4),
	(140, 4),
	(141, 4);
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;


# Dumping structure for table audit.safe_policy
DROP TABLE IF EXISTS `safe_policy`;
CREATE TABLE IF NOT EXISTS `safe_policy` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `timeout` int(11) DEFAULT NULL,
  `passwordLength` int(11) DEFAULT NULL,
  `errorLimit` int(11) DEFAULT NULL,
  `remoteDisabled` tinyint(1) DEFAULT NULL,
  `macDisabled` tinyint(1) DEFAULT NULL,
  `passwordRules` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `lockTime` int(10) NOT NULL DEFAULT '24' COMMENT '锁定时间(小时)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='安全策略表';

# Dumping data for table audit.safe_policy: ~0 rows (approximately)
/*!40000 ALTER TABLE `safe_policy` DISABLE KEYS */;
REPLACE INTO `safe_policy` (`id`, `timeout`, `passwordLength`, `errorLimit`, `remoteDisabled`, `macDisabled`, `passwordRules`, `lockTime`) VALUES
	(1, 600, 0, 3, 0, 0, '^[0-9a-zA-Z!$#%@^&amp;amp;amp;amp;amp;amp;amp;*()~_+]{8,20}$', 1);
/*!40000 ALTER TABLE `safe_policy` ENABLE KEYS */;


# Dumping structure for table audit.sys_log
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE IF NOT EXISTS `sys_log` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `log_time` datetime DEFAULT NULL COMMENT '产生时间',
  `level` varchar(10) DEFAULT NULL COMMENT '日志等级',
  `audit_module` varchar(40) DEFAULT NULL COMMENT '审计模块',
  `audit_action` varchar(40) DEFAULT NULL COMMENT '审计行为',
  `audit_info` varchar(255) DEFAULT NULL COMMENT '审计内容',
  PRIMARY KEY (`Id`),
  KEY `log_time` (`log_time`,`level`,`audit_module`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志审计表';

# Dumping data for table audit.sys_log: ~0 rows (approximately)
/*!40000 ALTER TABLE `sys_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_log` ENABLE KEYS */;


# Dumping structure for table audit.user_oper_log
DROP TABLE IF EXISTS `user_oper_log`;
CREATE TABLE IF NOT EXISTS `user_oper_log` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `log_time` datetime DEFAULT NULL COMMENT '审计时间',
  `level` varchar(10) DEFAULT NULL COMMENT '日志级别',
  `username` varchar(30) DEFAULT NULL COMMENT '用户名',
  `audit_module` varchar(255) DEFAULT NULL COMMENT '审计模块',
  `audit_info` varchar(255) DEFAULT NULL COMMENT '审计内容',
  PRIMARY KEY (`Id`),
  KEY `log_time` (`log_time`,`level`,`username`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8 COMMENT='用户操作审计表';

# Dumping data for table audit.user_oper_log: ~83 rows (approximately)
/*!40000 ALTER TABLE `user_oper_log` DISABLE KEYS */;
REPLACE INTO `user_oper_log` (`Id`, `log_time`, `level`, `username`, `audit_module`, `audit_info`) VALUES
	(26, '2015-04-20 15:31:06', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(27, '2015-04-20 15:37:28', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(28, '2015-04-23 09:19:17', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(29, '2015-04-23 09:59:24', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(30, '2015-04-23 10:46:58', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(31, '2015-04-23 10:51:38', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(32, '2015-04-23 11:11:51', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(33, '2015-04-23 15:35:34', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(34, '2015-04-23 15:38:04', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(35, '2015-04-23 15:42:05', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(36, '2015-04-23 17:21:06', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(37, '2015-04-23 17:29:58', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(38, '2015-04-23 17:34:55', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(39, '2015-04-23 17:35:38', 'ERROR', 'admin', '审计备份策略', '用户更新审计备份策略信息失败'),
	(40, '2015-04-23 17:37:19', 'INFO', 'admin', '审计备份策略', '用户更新审计备份策略信息成功'),
	(41, '2015-04-23 17:47:05', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(42, '2015-04-23 17:47:37', 'INFO', 'admin', '审计备份策略', '用户更新审计备份策略信息成功'),
	(43, '2015-04-23 17:56:26', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(44, '2015-04-23 17:57:22', 'INFO', 'admin', '审计备份策略', '用户更新审计备份策略信息成功'),
	(45, '2015-04-23 17:57:45', 'INFO', 'admin', '审计备份策略', '用户更新审计备份策略信息成功'),
	(46, '2015-04-23 17:57:52', 'INFO', 'admin', '审计备份策略', '用户更新审计备份策略信息成功'),
	(47, '2015-04-23 18:03:09', 'INFO', 'admin', '审计备份策略', '用户更新审计备份策略信息成功'),
	(48, '2015-04-23 18:04:22', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(49, '2015-04-23 18:06:50', 'INFO', 'admin', '审计备份策略', '用户更新审计备份策略信息成功'),
	(50, '2015-04-23 18:09:56', 'INFO', 'admin', '审计备份策略', '用户更新审计备份策略信息成功'),
	(51, '2015-04-23 18:10:17', 'INFO', 'admin', '审计备份策略', '用户更新审计备份策略信息成功'),
	(52, '2015-04-23 18:19:31', 'INFO', 'admin', '审计备份策略', '用户更新审计备份策略信息成功'),
	(53, '2015-04-23 18:20:15', 'INFO', 'admin', '审计备份策略', '用户更新审计备份策略信息成功'),
	(54, '2015-04-23 18:24:14', 'INFO', 'admin', '审计备份策略', '用户更新审计备份策略信息成功'),
	(55, '2015-04-23 18:24:31', 'INFO', 'admin', '审计备份策略', '用户更新审计备份策略信息成功'),
	(56, '2015-04-23 18:25:02', 'INFO', 'admin', '审计备份策略', '用户更新审计备份策略信息成功'),
	(57, '2015-04-23 18:25:10', 'INFO', 'admin', '审计备份策略', '用户更新审计备份策略信息成功'),
	(58, '2015-04-24 08:51:16', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(59, '2015-04-24 08:52:32', 'INFO', 'admin', '日志下载', '用户下载日志成功'),
	(60, '2015-04-24 08:53:59', 'INFO', 'admin', '审计备份策略', '用户更新审计备份策略信息成功'),
	(61, '2015-04-24 09:00:03', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(62, '2015-04-24 09:00:25', 'INFO', 'admin', '审计备份策略', '用户更新审计备份策略信息成功'),
	(63, '2015-04-24 09:00:32', 'INFO', 'admin', '审计备份策略', '用户更新审计备份策略信息成功'),
	(64, '2015-04-24 09:01:02', 'INFO', 'admin', '审计备份策略', '用户更新审计备份策略信息成功'),
	(65, '2015-04-24 09:03:23', 'INFO', 'admin', '审计备份策略', '用户更新审计备份策略信息成功'),
	(66, '2015-04-24 09:05:39', 'INFO', 'admin', '审计备份策略', '用户更新审计备份策略信息成功'),
	(67, '2015-04-24 09:05:47', 'INFO', 'admin', '审计备份策略', '用户更新审计备份策略信息成功'),
	(68, '2015-04-24 09:05:55', 'INFO', 'admin', '审计备份策略', '用户更新审计备份策略信息成功'),
	(69, '2015-04-24 09:06:06', 'INFO', 'admin', '审计备份策略', '用户更新审计备份策略信息成功'),
	(70, '2015-04-24 09:08:12', 'INFO', 'admin', '日志下载', '用户下载日志成功'),
	(71, '2015-04-24 09:28:50', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(72, '2015-04-24 09:29:05', 'INFO', 'admin', '角色管理', '用户更新角色信息成功'),
	(73, '2015-04-24 09:29:15', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(74, '2015-04-24 09:29:27', 'INFO', 'admin', '报警配置', '用户校验email成功'),
	(75, '2015-04-24 09:30:07', 'INFO', 'admin', '报警配置', '用户校验email成功'),
	(76, '2015-04-24 09:30:10', 'INFO', 'admin', '报警配置', '用户校验email成功'),
	(77, '2015-04-24 09:30:11', 'INFO', 'admin', '报警配置', '用删除Email地址成功'),
	(78, '2015-04-24 09:30:27', 'INFO', 'admin', '报警配置', '用户校验email成功'),
	(79, '2015-04-24 09:30:29', 'INFO', 'admin', '报警配置', '用户新增Email地址成功'),
	(80, '2015-04-24 09:30:51', 'INFO', 'admin', '报警配置', '用户测试邮件发送报警信息成功'),
	(81, '2015-04-24 09:30:59', 'INFO', 'admin', '报警配置', '用删除Email地址成功'),
	(82, '2015-04-24 09:33:36', 'INFO', 'admin', '报警配置', '用户测试邮件发送报警信息成功'),
	(83, '2015-04-24 09:34:19', 'INFO', 'admin', '报警配置', '用户修改报警配置信息成功'),
	(84, '2015-04-24 09:34:55', 'INFO', 'admin', '报警配置', '用户测试邮件发送报警信息成功'),
	(85, '2015-04-24 12:34:32', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(86, '2015-04-24 12:35:02', 'INFO', 'admin', '报警配置', '用户修改报警配置信息成功'),
	(87, '2015-04-24 12:40:43', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(88, '2015-04-24 12:43:51', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(89, '2015-04-24 12:46:42', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(90, '2015-04-24 12:47:18', 'INFO', 'admin', '报警配置', '用户修改报警配置信息成功'),
	(91, '2015-04-24 12:50:05', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(92, '2015-04-24 12:50:32', 'INFO', 'admin', '报警配置', '用户修改报警配置信息成功'),
	(93, '2015-04-24 12:50:47', 'INFO', 'admin', '报警配置', '用户测试邮件发送报警信息成功'),
	(94, '2015-04-24 12:51:15', 'INFO', 'admin', '报警配置', '用户测试邮件发送报警信息成功'),
	(95, '2015-04-24 12:51:33', 'INFO', 'admin', '报警配置', '用户测试邮件发送报警信息成功'),
	(96, '2015-04-24 12:57:54', 'INFO', 'admin', '报警配置', '用户校验email成功'),
	(97, '2015-04-24 12:58:26', 'INFO', 'admin', '报警配置', '用户校验email成功'),
	(98, '2015-04-24 12:58:43', 'INFO', 'admin', '报警配置', '用户校验email成功'),
	(99, '2015-04-24 12:58:46', 'INFO', 'admin', '报警配置', '用户新增Email地址成功'),
	(100, '2015-04-24 12:58:50', 'INFO', 'admin', '报警配置', '用户校验email成功'),
	(101, '2015-04-24 12:58:57', 'INFO', 'admin', '报警配置', '用户校验email成功'),
	(102, '2015-04-24 13:00:10', 'INFO', 'admin', '报警配置', '用户校验email成功'),
	(103, '2015-04-24 13:00:13', 'INFO', 'admin', '报警配置', '用户新增Email地址成功'),
	(104, '2015-04-24 13:00:50', 'INFO', 'admin', '报警配置', '用删除Email地址成功'),
	(105, '2015-04-24 13:00:54', 'INFO', 'admin', '报警配置', '用删除Email地址成功'),
	(106, '2015-04-24 14:00:46', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(107, '2015-04-24 14:37:15', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(108, '2015-04-24 14:38:52', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(109, '2015-04-24 14:39:45', 'INFO', 'admin', '审计备份策略', '用户更新审计备份策略信息成功'),
	(110, '2015-04-24 15:01:25', 'INFO', 'admin', '审计备份策略', '用户更新审计备份策略信息成功'),
	(111, '2015-04-24 15:01:28', 'INFO', 'admin', '审计备份策略', '用户更新审计备份策略信息成功'),
	(112, '2015-04-24 15:02:46', 'INFO', 'admin', '审计备份策略', '用户更新审计备份策略信息成功'),
	(113, '2015-04-24 15:07:09', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(114, '2015-04-24 15:07:29', 'INFO', 'admin', '审计备份策略', '用户更新审计备份策略信息成功'),
	(115, '2015-04-24 15:08:39', 'INFO', 'admin', '审计备份策略', '用户更新审计备份策略信息成功'),
	(116, '2015-04-24 15:12:13', 'INFO', 'admin', '日志下载', '用户下载日志成功'),
	(117, '2015-04-24 15:12:31', 'INFO', 'admin', '日志下载', '用户下载日志成功'),
	(118, '2015-04-27 09:23:23', 'INFO', 'admin', '用户登录', '用户登录成功'),
	(119, '2015-04-27 09:23:40', 'INFO', 'admin', '审计备份策略', '用户更新审计备份策略信息成功'),
	(120, '2015-04-27 09:23:52', 'INFO', 'admin', '审计备份策略', '用户更新审计备份策略信息成功'),
	(121, '2015-04-28 15:43:19', 'INFO', 'admin', '用户登录', '用户登录成功');
/*!40000 ALTER TABLE `user_oper_log` ENABLE KEYS */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
