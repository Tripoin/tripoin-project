/*
Navicat MySQL Data Transfer

Source Server         : Tripoin-localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : tripoin

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-02-20 23:21:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for geo_user_route
-- ----------------------------
DROP TABLE IF EXISTS `geo_user_route`;
CREATE TABLE `geo_user_route` (
  `user_route_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_route_lat` double(30,25) NOT NULL,
  `user_route_lon` double(30,25) NOT NULL,
  `user_route_center` smallint(5) NOT NULL DEFAULT '0',
  `user_route_zoom` int(10) NOT NULL DEFAULT '10',
  `user_route_drag` smallint(5) NOT NULL DEFAULT '0',
  `user_route_marker` varchar(255) DEFAULT NULL,
  `user_route_caption` varchar(255) DEFAULT NULL,
  `user_route_icon` varchar(255) DEFAULT NULL,
  `employee_id` bigint(20) NOT NULL,
  `user_route_created_by` varchar(150) DEFAULT 'admin',
  `user_route_created_ip` varchar(150) DEFAULT '127.0.0.1',
  `user_route_created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `user_route_created_platform` varchar(255) DEFAULT NULL,
  `user_route_modified_by` varchar(150) DEFAULT NULL,
  `user_route_modified_ip` varchar(150) DEFAULT NULL,
  `user_route_modified_time` timestamp NULL DEFAULT NULL,
  `user_route_modified_platform` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_route_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of geo_user_route
-- ----------------------------
INSERT INTO `geo_user_route` VALUES ('1', '-6.2666000000000000000000000', '106.6598310000000000000000000', '0', '10', '0', 'Komplek Pondok Jagung BD 53', 'Prison', null, '3', 'admin', '127.0.0.1', '2015-11-23 17:00:00', null, null, null, null, null);
INSERT INTO `geo_user_route` VALUES ('2', '-6.2977670000000000000000000', '106.6678370000000000000000000', '0', '10', '0', 'Jalan Kapten Subidjanto DJ', 'Kantin Belakang', null, '3', 'admin', '127.0.0.1', '2015-11-24 15:48:15', null, null, null, null, null);
INSERT INTO `geo_user_route` VALUES ('3', '-6.2409321898086985000000000', '106.6284942626953100000000000', '0', '10', '0', 'Gading Serpong', 'Summarecon Mall Serpong', null, '3', 'admin', '127.0.0.1', '2015-11-24 15:49:21', null, null, null, null, null);

-- ----------------------------
-- Table structure for mst_area
-- ----------------------------
DROP TABLE IF EXISTS `mst_area`;
CREATE TABLE `mst_area` (
  `area_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `area_code` varchar(150) NOT NULL,
  `area_name` varchar(255) NOT NULL,
  `area_status` smallint(5) DEFAULT NULL,
  `area_remarks` varchar(255) DEFAULT NULL,
  `area_created_by` varchar(150) DEFAULT 'admin',
  `area_created_ip` varchar(150) DEFAULT '127.0.0.1',
  `area_created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `area_created_platform` varchar(255) DEFAULT NULL,
  `area_modified_by` varchar(150) DEFAULT NULL,
  `area_modified_ip` varchar(150) DEFAULT NULL,
  `area_modified_time` timestamp NULL DEFAULT NULL,
  `area_modified_platform` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`area_id`),
  UNIQUE KEY `occupation_code` (`area_code`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mst_area
-- ----------------------------
INSERT INTO `mst_area` VALUES ('1', 'JAKARTA', 'JAKARTA', '1', '-', 'admin', '127.0.0.1', '2015-11-23 00:00:00', null, null, null, null, null);
INSERT INTO `mst_area` VALUES ('2', 'TANGERANG', 'TANGERANG', '1', '-', 'admin', '127.0.0.1', '2015-11-23 00:00:00', null, null, null, null, null);
INSERT INTO `mst_area` VALUES ('3', 'BANDUNG', 'BANDUNG', '1', '-', 'admin', '127.0.0.1', '2015-11-23 00:00:00', null, null, null, null, null);
INSERT INTO `mst_area` VALUES ('7', '01', 'ACEH', '1', null, 'admin', '127.0.0.1', '2016-02-20 22:03:52', null, null, null, null, null);
INSERT INTO `mst_area` VALUES ('8', '02', 'SUMATERA UTARA', '1', null, 'admin', '127.0.0.1', '2016-02-20 22:03:52', null, null, null, null, null);
INSERT INTO `mst_area` VALUES ('9', '03', 'SUMATERA BARAT', '1', null, 'admin', '127.0.0.1', '2016-02-20 22:03:52', null, null, null, null, null);
INSERT INTO `mst_area` VALUES ('10', '04', 'RIAU', '1', null, 'admin', '127.0.0.1', '2016-02-20 22:03:52', null, null, null, null, null);
INSERT INTO `mst_area` VALUES ('11', '05', 'JAMBI', '1', null, 'admin', '127.0.0.1', '2016-02-20 22:03:52', null, null, null, null, null);
INSERT INTO `mst_area` VALUES ('12', '06', 'SUMATERA SELATAN', '1', null, 'admin', '127.0.0.1', '2016-02-20 22:03:52', null, null, null, null, null);
INSERT INTO `mst_area` VALUES ('13', '07', 'BENGKULU', '1', null, 'admin', '127.0.0.1', '2016-02-20 22:03:52', null, null, null, null, null);
INSERT INTO `mst_area` VALUES ('14', '08', 'LAMPUNG', '1', null, 'admin', '127.0.0.1', '2016-02-20 22:03:53', null, null, null, null, null);
INSERT INTO `mst_area` VALUES ('15', '09', 'KEPULAUAN BANGKA BELITUNG', '1', null, 'admin', '127.0.0.1', '2016-02-20 22:03:53', null, null, null, null, null);
INSERT INTO `mst_area` VALUES ('16', '10', 'KEPULAUAN RIAU', '1', null, 'admin', '127.0.0.1', '2016-02-20 22:03:53', null, null, null, null, null);
INSERT INTO `mst_area` VALUES ('17', '11', 'DKI JAKARTA', '1', null, 'admin', '127.0.0.1', '2016-02-20 22:03:53', null, null, null, null, null);
INSERT INTO `mst_area` VALUES ('18', '12', 'JAWA BARAT', '1', null, 'admin', '127.0.0.1', '2016-02-20 22:03:53', null, null, null, null, null);
INSERT INTO `mst_area` VALUES ('19', '13', 'JAWA TENGAH', '1', null, 'admin', '127.0.0.1', '2016-02-20 22:03:53', null, null, null, null, null);
INSERT INTO `mst_area` VALUES ('20', '14', 'DI YOGYAKARTA', '1', null, 'admin', '127.0.0.1', '2016-02-20 22:03:53', null, null, null, null, null);
INSERT INTO `mst_area` VALUES ('21', '15', 'JAWA TIMUR', '1', null, 'admin', '127.0.0.1', '2016-02-20 22:03:53', null, null, null, null, null);
INSERT INTO `mst_area` VALUES ('22', '16', 'BANTEN', '1', null, 'admin', '127.0.0.1', '2016-02-20 22:03:53', null, null, null, null, null);
INSERT INTO `mst_area` VALUES ('23', '17', 'BALI', '1', null, 'admin', '127.0.0.1', '2016-02-20 22:03:53', null, null, null, null, null);
INSERT INTO `mst_area` VALUES ('24', '18', 'NUSA TENGGARA BARAT', '1', null, 'admin', '127.0.0.1', '2016-02-20 22:03:53', null, null, null, null, null);
INSERT INTO `mst_area` VALUES ('25', '19', 'NUSA TENGGARA TIMUR', '1', null, 'admin', '127.0.0.1', '2016-02-20 22:03:54', null, null, null, null, null);
INSERT INTO `mst_area` VALUES ('26', '20', 'KALIMANTAN BARAT', '1', null, 'admin', '127.0.0.1', '2016-02-20 22:03:54', null, null, null, null, null);
INSERT INTO `mst_area` VALUES ('27', '21', 'KALIMANTAN TENGAH', '1', null, 'admin', '127.0.0.1', '2016-02-20 22:03:54', null, null, null, null, null);
INSERT INTO `mst_area` VALUES ('28', '22', 'KALIMANTAN SELATAN', '1', null, 'admin', '127.0.0.1', '2016-02-20 22:03:54', null, null, null, null, null);
INSERT INTO `mst_area` VALUES ('29', '23', 'KALIMANTAN TIMUR', '1', null, 'admin', '127.0.0.1', '2016-02-20 22:03:54', null, null, null, null, null);
INSERT INTO `mst_area` VALUES ('30', '24', 'SULAWESI UTARA', '1', null, 'admin', '127.0.0.1', '2016-02-20 22:03:54', null, null, null, null, null);
INSERT INTO `mst_area` VALUES ('31', '25', 'SULAWESI TENGAH', '1', null, 'admin', '127.0.0.1', '2016-02-20 22:03:54', null, null, null, null, null);
INSERT INTO `mst_area` VALUES ('32', '26', 'SULAWESI SELATAN', '1', null, 'admin', '127.0.0.1', '2016-02-20 22:03:54', null, null, null, null, null);
INSERT INTO `mst_area` VALUES ('33', '27', 'SULAWESI TENGGARA', '1', null, 'admin', '127.0.0.1', '2016-02-20 22:03:54', null, null, null, null, null);
INSERT INTO `mst_area` VALUES ('34', '28', 'GORONTALO', '1', null, 'admin', '127.0.0.1', '2016-02-20 22:03:54', null, null, null, null, null);
INSERT INTO `mst_area` VALUES ('35', '29', 'SULAWESI BARAT', '1', null, 'admin', '127.0.0.1', '2016-02-20 22:03:54', null, null, null, null, null);
INSERT INTO `mst_area` VALUES ('36', '30', 'MALUKU', '1', null, 'admin', '127.0.0.1', '2016-02-20 22:03:54', null, null, null, null, null);
INSERT INTO `mst_area` VALUES ('37', '31', 'MALUKU UTARA', '1', null, 'admin', '127.0.0.1', '2016-02-20 22:03:55', null, null, null, null, null);
INSERT INTO `mst_area` VALUES ('38', '32', 'PAPUA BARAT', '1', null, 'admin', '127.0.0.1', '2016-02-20 22:03:55', null, null, null, null, null);
INSERT INTO `mst_area` VALUES ('39', '33', 'PAPUA', '1', null, 'admin', '127.0.0.1', '2016-02-20 22:03:55', null, null, null, null, null);

-- ----------------------------
-- Table structure for mst_employee
-- ----------------------------
DROP TABLE IF EXISTS `mst_employee`;
CREATE TABLE `mst_employee` (
  `employee_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `employee_code` varchar(150) NOT NULL,
  `employee_nik` varchar(150) NOT NULL,
  `profile_id` bigint(20) NOT NULL,
  `occupation_id` bigint(20) NOT NULL,
  `employee_parent_id` bigint(20) DEFAULT NULL,
  `employee_status` smallint(5) DEFAULT NULL,
  `employee_remarks` varchar(255) DEFAULT NULL,
  `employee_created_by` varchar(150) DEFAULT 'admin',
  `employee_created_ip` varchar(150) DEFAULT '127.0.0.1',
  `employee_created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `employee_created_platform` varchar(255) DEFAULT NULL,
  `employee_modified_by` varchar(150) DEFAULT NULL,
  `employee_modified_ip` varchar(150) DEFAULT NULL,
  `employee_modified_time` timestamp NULL DEFAULT NULL,
  `employee_modified_platform` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  UNIQUE KEY `employee_code` (`employee_code`),
  UNIQUE KEY `employee_nik` (`employee_nik`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mst_employee
-- ----------------------------
INSERT INTO `mst_employee` VALUES ('1', 'TSM201511230001', 'TSM201511230001', '3', '3', null, '1', 'TSM201511230001', 'admin', '127.0.0.1', '2015-11-23 00:00:00', null, null, null, null, null);
INSERT INTO `mst_employee` VALUES ('2', 'TSM201511240001', 'TSM201511240001', '2', '2', '1', '1', 'TSM201511240001', 'admin', '127.0.0.1', '2015-11-24 00:00:00', null, null, null, null, null);
INSERT INTO `mst_employee` VALUES ('3', 'TSM201511250001', 'TSM201511250001', '1', '1', '2', '1', 'TSM201511250001', 'admin', '127.0.0.1', '2015-11-25 00:00:00', null, null, null, null, null);

-- ----------------------------
-- Table structure for mst_menu
-- ----------------------------
DROP TABLE IF EXISTS `mst_menu`;
CREATE TABLE `mst_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_code` varchar(150) NOT NULL,
  `menu_name` varchar(255) DEFAULT NULL,
  `menu_parent_id` bigint(20) DEFAULT NULL,
  `menu_level` int(5) NOT NULL DEFAULT '1',
  `menu_order` int(5) NOT NULL DEFAULT '1',
  `menu_tree` varchar(20) NOT NULL,
  `menu_function` varchar(20) NOT NULL DEFAULT 'MENU-PAGE',
  `menu_view_type` varchar(50) NOT NULL DEFAULT 'WEB-MOBILE',
  `menu_status` smallint(5) DEFAULT '1',
  `menu_remarks` varchar(255) DEFAULT NULL,
  `menu_created_by` varchar(150) DEFAULT 'admin',
  `menu_created_ip` varchar(150) DEFAULT '127.0.0.1',
  `menu_created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `menu_created_platform` varchar(255) DEFAULT NULL,
  `menu_modified_by` varchar(150) DEFAULT NULL,
  `menu_modified_ip` varchar(150) DEFAULT NULL,
  `menu_modified_time` timestamp NULL DEFAULT NULL,
  `menu_modified_platform` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`menu_id`),
  UNIQUE KEY `menu_code` (`menu_code`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mst_menu
-- ----------------------------
INSERT INTO `mst_menu` VALUES ('1', 'activitySalesView', 'Activity Sales', null, '1', '2', '2', 'MENU-NON-PAGE', 'WEB-MOBILE', '1', 'Activity Sales', 'admin', '127.0.0.1', '2015-07-17 15:31:07', null, null, null, null, null);
INSERT INTO `mst_menu` VALUES ('2', 'callPlanView', 'Call Plan', '1', '2', '1', '2.1', 'MENU-PAGE', 'WEB-MOBILE', '1', 'Call Plan', 'admin', '127.0.0.1', '2015-07-17 15:37:11', null, null, null, null, null);
INSERT INTO `mst_menu` VALUES ('3', 'quotationView', 'Quotation', '1', '2', '2', '2.2', 'MENU-PAGE', 'WEB-MOBILE', '1', 'Quotation', 'admin', '127.0.0.1', '2015-07-17 15:37:38', null, null, null, null, null);
INSERT INTO `mst_menu` VALUES ('4', 'callReportView', 'Call Report', '1', '2', '3', '2.3', 'MENU-PAGE', 'WEB-MOBILE', '1', 'Call Report', 'admin', '127.0.0.1', '2015-07-17 15:47:51', null, null, null, null, null);
INSERT INTO `mst_menu` VALUES ('5', 'manageCustomerView', 'Manage Customer', null, '1', '3', '3', 'MENU-PAGE', 'WEB-MOBILE', '1', 'Manage Customer', 'admin', '127.0.0.1', '2015-07-17 15:48:17', null, null, null, null, null);
INSERT INTO `mst_menu` VALUES ('6', 'auditTrailView', 'Audit Trail', null, '1', '4', '4', 'MENU-NON-PAGE', 'WEB', '1', 'Audit Trail', 'admin', '127.0.0.1', '2015-07-17 15:48:17', null, null, null, null, null);
INSERT INTO `mst_menu` VALUES ('7', 'historyLoginView', 'History Login', '6', '2', '1', '4.1', 'MENU-PAGE', 'WEB', '1', 'History Login', 'admin', '127.0.0.1', '2015-07-17 15:49:22', null, null, null, null, null);
INSERT INTO `mst_menu` VALUES ('8', 'historyCallPlanView', 'History Call Plan', '6', '2', '2', '4.2', 'MENU-PAGE', 'WEB', '1', 'History Call Plan', 'admin', '127.0.0.1', '2015-07-17 15:49:22', null, null, null, null, null);
INSERT INTO `mst_menu` VALUES ('9', 'historyQuotationView', 'History Quotation', '6', '2', '3', '4.3', 'MENU-PAGE', 'WEB', '1', 'History Quotation', 'admin', '127.0.0.1', '2015-10-25 00:00:00', null, null, null, null, null);
INSERT INTO `mst_menu` VALUES ('10', 'historyCallReportView', 'History Call Report', '6', '2', '4', '4.4', 'MENU-PAGE', 'WEB', '1', 'History Call Report', 'admin', '127.0.0.1', '2015-07-17 15:49:56', null, null, null, null, null);
INSERT INTO `mst_menu` VALUES ('11', 'approvalView', 'Approval', null, '1', '5', '5', 'MENU-NON-PAGE', 'WEB', '1', 'Approval', 'admin', '127.0.0.1', '2015-07-17 15:49:56', null, null, null, null, null);
INSERT INTO `mst_menu` VALUES ('12', 'approvalCallPlanView', 'Approval Call Plan', '11', '2', '1', '5.1', 'MENU-PAGE', 'WEB', '1', 'Approval Call Plan', 'admin', '127.0.0.1', '2015-07-17 15:55:18', null, null, null, null, null);
INSERT INTO `mst_menu` VALUES ('13', 'approvalQuotationView', 'Approval Quotation', '11', '2', '2', '5.2', 'MENU-PAGE', 'WEB', '1', 'Approval Quotation', 'admin', '127.0.0.1', '2015-07-17 15:55:18', null, null, null, null, null);
INSERT INTO `mst_menu` VALUES ('14', 'approvalCallReportView', 'Approval Call Report', '11', '2', '3', '5.3', 'MENU-PAGE', 'WEB', '1', 'Approval Call Report', 'admin', '127.0.0.1', '2015-07-17 15:55:53', null, null, null, null, null);
INSERT INTO `mst_menu` VALUES ('15', 'dashboardView', 'Dashboard', null, '1', '1', '1', 'MENU-NON-PAGE', 'WEB-MOBILE', '1', 'Dashboard', 'admin', '127.0.0.1', '2015-07-17 15:55:53', null, null, null, null, null);
INSERT INTO `mst_menu` VALUES ('16', 'salesPerformanceView', 'Sales Performance', '15', '2', '1', '1.1', 'MENU-PAGE', 'WEB-MOBILE', '1', 'Sales Performance', 'admin', '127.0.0.1', '2015-07-17 15:57:22', null, null, null, null, null);
INSERT INTO `mst_menu` VALUES ('17', 'systemAnalyzerView', 'System Analyzer', '15', '2', '2', '1.2', 'MENU-PAGE', 'WEB-MOBILE', '1', 'System Analyzer', 'admin', '127.0.0.1', '2015-07-17 15:57:22', null, null, null, null, null);
INSERT INTO `mst_menu` VALUES ('18', 'customerIncomeView', 'Customer Income', '15', '2', '3', '1.3', 'MENU-PAGE', 'WEB-MOBILE', '1', 'Customer Income', 'admin', '127.0.0.1', '2015-10-24 00:00:00', null, null, null, null, null);
INSERT INTO `mst_menu` VALUES ('19', 'masterDataView', 'Master Data', null, '1', '7', '7', 'MENU-NON-PAGE', 'WEB', '1', 'Master Data', 'admin', '127.0.0.1', '2015-10-25 00:00:00', null, null, null, null, null);
INSERT INTO `mst_menu` VALUES ('20', 'dataEmployeeView', 'Data Employee', '19', '2', '1', '7.1', 'MENU-PAGE', 'WEB', '1', 'Data Employee', 'admin', '127.0.0.1', '2015-10-25 00:27:24', null, null, null, null, null);
INSERT INTO `mst_menu` VALUES ('21', 'dataOccupationView', 'Data Occupation', '19', '2', '2', '7.2', 'MENU-PAGE', 'WEB', '1', 'Data Occupation', 'admin', '127.0.0.1', '2015-10-25 00:00:00', null, null, null, null, null);
INSERT INTO `mst_menu` VALUES ('22', 'dataAreaView', 'Data Area', '19', '2', '3', '7.3', 'MENU-PAGE', 'WEB', '1', 'Data Area', 'admin', '127.0.0.1', '2015-10-25 00:00:00', null, null, null, null, null);
INSERT INTO `mst_menu` VALUES ('23', 'dataProjectView', 'Data Project', '19', '2', '4', '7.4', 'MENU-PAGE', 'WEB', '1', 'Data Project', 'admin', '127.0.0.1', '2015-10-25 00:00:00', null, null, null, null, null);
INSERT INTO `mst_menu` VALUES ('24', 'dataCompetitiveInformationSourceView', 'Data Competitive Information Source', '19', '2', '5', '7.5', 'MENU-PAGE', 'WEB', '1', 'Data Competitive Information Source', 'admin', '127.0.0.1', '2015-10-25 00:00:00', null, null, null, null, null);
INSERT INTO `mst_menu` VALUES ('25', 'dataComparisonView', 'Data Comparison', '19', '2', '6', '7.6', 'MENU-PAGE', 'WEB', '1', 'Data Comparison', 'admin', '127.0.0.1', '2015-10-25 00:00:00', null, null, null, null, null);
INSERT INTO `mst_menu` VALUES ('26', 'dataCurrencyView', 'Data Currency', '19', '2', '7', '7.7', 'MENU-PAGE', 'WEB', '1', 'Data Currency', 'admin', '127.0.0.1', '2015-10-25 00:00:00', null, null, null, null, null);
INSERT INTO `mst_menu` VALUES ('27', 'salesTracking', 'Sales Tracking', null, '1', '6', '6', 'MENU-PAGE', 'WEB', '1', 'Sales Tracking', 'admin', '127.0.0.1', '2015-11-15 00:00:00', null, null, null, null, null);

-- ----------------------------
-- Table structure for mst_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `mst_menu_role`;
CREATE TABLE `mst_menu_role` (
  `menu_role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`menu_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mst_menu_role
-- ----------------------------
INSERT INTO `mst_menu_role` VALUES ('1', '1', '1');
INSERT INTO `mst_menu_role` VALUES ('2', '2', '1');
INSERT INTO `mst_menu_role` VALUES ('3', '3', '1');
INSERT INTO `mst_menu_role` VALUES ('4', '4', '1');
INSERT INTO `mst_menu_role` VALUES ('5', '5', '1');
INSERT INTO `mst_menu_role` VALUES ('6', '15', '1');
INSERT INTO `mst_menu_role` VALUES ('7', '16', '1');
INSERT INTO `mst_menu_role` VALUES ('8', '17', '1');
INSERT INTO `mst_menu_role` VALUES ('9', '18', '1');
INSERT INTO `mst_menu_role` VALUES ('10', '5', '2');
INSERT INTO `mst_menu_role` VALUES ('11', '6', '2');
INSERT INTO `mst_menu_role` VALUES ('12', '7', '2');
INSERT INTO `mst_menu_role` VALUES ('13', '8', '2');
INSERT INTO `mst_menu_role` VALUES ('14', '9', '2');
INSERT INTO `mst_menu_role` VALUES ('15', '10', '2');
INSERT INTO `mst_menu_role` VALUES ('16', '11', '2');
INSERT INTO `mst_menu_role` VALUES ('22', '12', '2');
INSERT INTO `mst_menu_role` VALUES ('23', '13', '2');
INSERT INTO `mst_menu_role` VALUES ('24', '14', '2');
INSERT INTO `mst_menu_role` VALUES ('25', '15', '2');
INSERT INTO `mst_menu_role` VALUES ('26', '16', '2');
INSERT INTO `mst_menu_role` VALUES ('27', '17', '2');
INSERT INTO `mst_menu_role` VALUES ('28', '18', '2');
INSERT INTO `mst_menu_role` VALUES ('29', '6', '3');
INSERT INTO `mst_menu_role` VALUES ('30', '10', '3');
INSERT INTO `mst_menu_role` VALUES ('31', '15', '3');
INSERT INTO `mst_menu_role` VALUES ('32', '16', '3');
INSERT INTO `mst_menu_role` VALUES ('33', '17', '3');
INSERT INTO `mst_menu_role` VALUES ('34', '18', '3');
INSERT INTO `mst_menu_role` VALUES ('35', '19', '4');
INSERT INTO `mst_menu_role` VALUES ('36', '20', '4');
INSERT INTO `mst_menu_role` VALUES ('37', '21', '4');
INSERT INTO `mst_menu_role` VALUES ('38', '22', '4');
INSERT INTO `mst_menu_role` VALUES ('39', '23', '4');
INSERT INTO `mst_menu_role` VALUES ('40', '24', '4');
INSERT INTO `mst_menu_role` VALUES ('41', '25', '4');
INSERT INTO `mst_menu_role` VALUES ('42', '26', '4');
INSERT INTO `mst_menu_role` VALUES ('43', '27', '2');

-- ----------------------------
-- Table structure for mst_occupation
-- ----------------------------
DROP TABLE IF EXISTS `mst_occupation`;
CREATE TABLE `mst_occupation` (
  `occupation_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `occupation_code` varchar(150) NOT NULL,
  `occupation_name` varchar(255) NOT NULL,
  `occupation_status` smallint(5) DEFAULT NULL,
  `occupation_remarks` varchar(255) DEFAULT NULL,
  `occupation_created_by` varchar(150) DEFAULT 'admin',
  `occupation_created_ip` varchar(150) DEFAULT '127.0.0.1',
  `occupation_created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `occupation_created_platform` varchar(255) DEFAULT NULL,
  `occupation_modified_by` varchar(150) DEFAULT NULL,
  `occupation_modified_ip` varchar(150) DEFAULT NULL,
  `occupation_modified_time` timestamp NULL DEFAULT NULL,
  `occupation_modified_platform` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`occupation_id`),
  UNIQUE KEY `occupation_code` (`occupation_code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mst_occupation
-- ----------------------------
INSERT INTO `mst_occupation` VALUES ('1', 'SALESMAN', 'Salesman', '1', 'Salesman', 'admin', '127.0.0.1', '2015-11-23 00:00:00', null, null, null, null, null);
INSERT INTO `mst_occupation` VALUES ('2', 'SALESSUPERVISOR', 'Sales Supervisor', '1', 'Sales Supervisor', 'admin', '127.0.0.1', '2015-11-23 00:00:00', null, null, null, null, null);
INSERT INTO `mst_occupation` VALUES ('3', 'SALESMANAGER', 'Sales Manager', '1', 'Sales Manager', 'admin', '127.0.0.1', '2015-11-23 00:00:00', null, null, null, null, null);

-- ----------------------------
-- Table structure for mst_profile
-- ----------------------------
DROP TABLE IF EXISTS `mst_profile`;
CREATE TABLE `mst_profile` (
  `profile_id` bigint(20) NOT NULL,
  `profile_email` varchar(255) NOT NULL,
  `profile_name` varchar(255) NOT NULL,
  `profile_gender` varchar(255) NOT NULL,
  `profile_birthplace` varchar(255) NOT NULL,
  `profile_birthdate` date NOT NULL,
  `profile_address` varchar(255) NOT NULL,
  `profile_telp` varchar(255) DEFAULT NULL,
  `profile_photo` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  `profile_phone` varchar(255) NOT NULL,
  `profile_bio` text,
  `profile_resources_uuid` varchar(255) NOT NULL,
  `profile_forgot_uuid` varchar(255) DEFAULT NULL,
  `profile_forgot_expired` timestamp NULL DEFAULT NULL,
  `profile_created_by` varchar(150) DEFAULT 'admin',
  `profile_created_ip` varchar(150) DEFAULT '127.0.0.1',
  `profile_created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `profile_created_platform` varchar(255) DEFAULT NULL,
  `profile_modified_by` varchar(150) DEFAULT NULL,
  `profile_modified_ip` varchar(150) DEFAULT NULL,
  `profile_modified_time` timestamp NULL DEFAULT NULL,
  `profile_modified_platform` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`profile_id`),
  UNIQUE KEY `profile_phone` (`profile_phone`),
  UNIQUE KEY `profile_email` (`profile_email`),
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mst_profile
-- ----------------------------
INSERT INTO `mst_profile` VALUES ('1', 'ridla.fadilah@tripoin.co.id', 'Ridla Fadilah', 'MALE', 'Bandung', '1990-12-27', 'Tangerang', '021234567891', null, '1', '081234567891', '<font face=\"Courier New\">This is Me</font>', 'b1c52cdc-78ac-4677-899d-2cacb5cb72e0', '0d0bf53a-0d24-4290-b29b-9190ae5ccf0f', '2015-11-02 20:15:09', 'admin', '127.0.0.1', '2015-10-28 10:57:43', null, 'ridla', '127.0.0.1', '2015-10-31 15:00:55', 'Computer | Windows | Chrome');
INSERT INTO `mst_profile` VALUES ('2', 'bangkit.pratolo@tripoin.co.id', 'Bangkit Pratolo', 'MALE', 'Tangerang', '2015-10-12', 'Tangerang', '-', null, '2', '081234567892', '-', '399820b9-14c8-4788-bdba-8789dc7ce533', null, null, 'admin', '127.0.0.1', '2015-10-28 10:57:43', null, null, null, null, null);
INSERT INTO `mst_profile` VALUES ('3', 'achmad.fauzi@tripoin.co.id', 'Achmad Fauzi', 'MALE', 'Tangerang', '2015-10-13', 'Tangerang', '-', null, '3', '081234567893', '-', 'a1e87b78-4e1d-4f09-8eeb-8c78c7b8d22b', null, null, 'admin', '127.0.0.1', '2015-10-28 10:57:43', null, null, null, null, null);
INSERT INTO `mst_profile` VALUES ('4', 'admin@tripoin.co.id', 'Administrator', 'FEMALE', 'Tangerang', '2015-10-14', 'Tangerang', '-', 'https://lh3.googleusercontent.com/-WuNBNTQkTmg/AAAAAAAAAAI/AAAAAAAAAAA/Xv-7H_t7nTA/photo.jpg', '4', '081234567894', '-', 'd42c93af-92e0-49ca-8989-4e6d14c6606c', null, null, 'admin', '127.0.0.1', '2015-10-28 10:57:43', null, 'admin', '127.0.0.1', '2016-01-04 09:51:50', 'Computer | Windows | Firefox');

-- ----------------------------
-- Table structure for mst_system_parameter
-- ----------------------------
DROP TABLE IF EXISTS `mst_system_parameter`;
CREATE TABLE `mst_system_parameter` (
  `system_parameter_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `system_parameter_code` varchar(100) NOT NULL,
  `system_parameter_value` text NOT NULL,
  `system_parameter_status` smallint(5) DEFAULT NULL,
  `system_parameter_remarks` varchar(255) DEFAULT NULL,
  `system_parameter_created_by` varchar(150) DEFAULT 'admin',
  `system_parameter_created_ip` varchar(150) DEFAULT '127.0.0.1',
  `system_parameter_created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `system_parameter_created_platform` varchar(255) DEFAULT NULL,
  `system_parameter_modified_by` varchar(150) DEFAULT NULL,
  `system_parameter_modified_ip` varchar(150) DEFAULT NULL,
  `system_parameter_modified_time` timestamp NULL DEFAULT NULL,
  `system_parameter_modified_platform` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`system_parameter_id`),
  UNIQUE KEY `system_parameter_code` (`system_parameter_code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mst_system_parameter
-- ----------------------------
INSERT INTO `mst_system_parameter` VALUES ('1', 'TRIPOIN.EMAIL.FORGOT.PASSWORD.SUBJECT', 'Request Reset Password', '1', 'Subject Email Forgot Password', 'admin', '127.0.0.1', '2015-11-01 00:00:00', null, null, null, null, null);
INSERT INTO `mst_system_parameter` VALUES ('2', 'TRIPOIN.EMAIL.FORGOT.PASSWORD.BODY.MESSAGE', '<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\"> <html>  	<head> 	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /> 	<meta name=\"viewport\" content=\"width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0\" /> 	<meta name=\"apple-mobile-web-app-capable\" content=\"yes\" /> 	<style type=\"text/css\"> 	@media only screen and (max-width: 320px) { 	table[class=\"edu-module\"]{ 	border-radius: 0px !important; 	-webkit-border-radius: 0px !important; 	-moz-border-radius: 0px !important; 	} 	td[class=\"edu-margins\"]{ 	background-color: #f5f8fa; 	} 	td[class=\"edu-collapse\"]{ 	width: 0px !important; 	} 	td[class=\"edu-space\"]{ 	height: 10px !important; 	background-color: #f5f8fa; 	} 	td[class=\"mobile-height\"]{ 	height: 30px !important; 	} 	} 	@media only screen and (max-width: 420px) { 	span[class=\"address\"] a { 	line-height:18px !important; 	} 	td[class=\"margins\"]{ 	width:18px !important; 	} 	td[class=\"edu-margins\"]{ 	width:18px !important; 	} 	td[class=\"logo_space\"]{ 	height:12px !important; 				} 			}  			@media only screen and (max-width: 480px) { 				table[class=\"collapse\"]{ 					width:100% !important; 				} 				table[class=\"edu-module\"]{ 					width:100% !important; 				} 				span[class=\"address\"]{ 					display:block !important; 					width:240px !important; 				} 				td[class=\"cut\"]{ 					display:none !important; 				} 			} 		</style> 	</head> 	<body bgcolor=\"#FAFAFA\" style=\"margin:0;padding:0;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;\"> 		<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\" bgcolor=\"#FAFAFA\" style=\"background-color:#FAFAFA;padding:0;margin:0;line-height:1px;font-size:1px;\" class=\"body_wrapper\"> 			<tbody> 				<tr> 					<td align=\"center\" style=\"padding:0;margin:0;line-height:1px;font-size:1px;\"> 						<table class=\"collapse\" id=\"header\" align=\"center\" width=\"650\" style=\"width: 650px;padding:0;margin:0;line-height:1px;font-size:1px;\" bgcolor=\"#ffffff\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"> 							<tbody> 								<tr> 									<td style=\"min-width: 650px;height:1px;padding:0;margin:0;line-height:1px;font-size:1px;\" class=\"cut\"></td> 								</tr> 							</tbody> 						</table>  					</td> 				</tr> 				<tr> 					<td align=\"center\" style=\"padding:0;margin:0;line-height:1px;font-size:1px;\"> 					<!--///////////////////header///////////////////////////--> 						<table class=\"collapse\" id=\"header\" align=\"center\" width=\"650\" style=\"width:650px;background-color:#ffffff;padding:0;margin:0;line-height:1px;font-size:1px;\" bgcolor=\"#ffffff\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"> 							<tbody> 								<tr> 									<td height=\"15\" style=\"height:15px;padding:0;margin:0;line-height:1px;font-size:1px;\" class=\"logo_space\"> &nbsp; </td> 								</tr> 								<tr> 									<td style=\"padding:0;margin:0;line-height:1px;font-size:1px;\"> 										<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"left\" style=\"padding:0;margin:0;line-height:1px;font-size:1px;\"> 											<tbody> 												<tr align=\"left\"> 													<td align=\"left\" width=\"15\" style=\"width:15px;padding:0;margin:0;line-height:1px;font-size:1px;\"></td> 													<td align=\"left\" width=\"28\" style=\"padding:0;margin:0;line-height:1px;font-size:1px;\"></td> 													<td align=\"left\" width=\"10\" style=\"width:10px;padding:0;margin:0;line-height:1px;font-size:1px;\"></td> 													<td align=\"left\" class=\"greeting\" style=\"padding:0;margin:0;line-height:1px;font-size:1px;font-family:\'Helvetica Neue Light\', Helvetica, Arial, sans-serif;-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:none;color:#66757f;font-size:16px;padding:0px;margin:0px;font-weight:300;line-height:100%;text-align:left;\"> Dear ${TRIPOIN.CONTENT.FULLNAME}, </td> 													<td width=\"50\" style=\"width:50px;padding:0;margin:0;line-height:1px;font-size:1px;\" align=\"left\"></td> 												</tr> 											</tbody> 										</table>  									</td> 								</tr> 								<tr> 								<td height=\"14\" style=\"height:14px;padding:0;margin:0;line-height:1px;font-size:1px;\" class=\"logo_space\"> &nbsp; </td> 								</tr> 							</tbody> 						</table> 						<!--////////////////////border//////////////////////////--> 						<table class=\"collapse\" align=\"center\" width=\"650\" style=\"width:650px;background-color:#ffffff;padding:0;margin:0;line-height:1px;font-size:1px;\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"> 							<tbody> 								<tr id=\"border\"> 									<td colspan=\"2\" height=\"1\" style=\"line-height:1px;display:block;height:1px;background-color:#FAFAFA;padding:0;margin:0;line-height:1px;font-size:1px;\"></td> 								</tr> 							</tbody> 						</table> 						<!--//////////////////////////////////////////////--> 						<table class=\"collapse\" align=\"center\" width=\"650\" style=\"width:650px;background-color:#ffffff;padding:0;margin:0;line-height:1px;font-size:1px;\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"> 							<tbody> 								<tr> 								<td width=\"50\" style=\"width:50px;padding:0;margin:0;line-height:1px;font-size:1px;\" class=\"margins\"></td> 								<td align=\"center\" style=\"padding:0;margin:0;line-height:1px;font-size:1px;\"> 									<table width=\"100%\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" class=\"collapse\" style=\"padding:0;margin:0;line-height:1px;font-size:1px;\"> 										<tbody> 										<tr> 											<td height=\"30\" style=\"height:45px;padding:0;margin:0;line-height:1px;font-size:1px;\"></td> 										</tr> 										<tr> 											<td align=\"left\" class=\"display\" style=\"padding:0;margin:0;line-height:1px;font-size:1px;font-family:\'Helvetica Neue\', Helvetica, Arial, sans-serif;font-size:22px;line-height:30px;font-weight:300;-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:none;text-align:left;color:#292f33;\"> We received a request to reset the password for your account. </td> 										</tr> 										<tr> 											<td height=\"30\" style=\"height:30px;padding:0;margin:0;line-height:1px;font-size:1px;\"></td> 										</tr> 										<tr> 											<td align=\"left\" class=\"body-text\" style=\"padding:0;margin:0;line-height:1px;font-size:1px;font-family:\'Helvetica Neue\', Helvetica, Arial, sans-serif;font-size:16px;line-height:22px;font-weight:400;-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:none;text-align:left;color:#292f33;\"> If you requested a reset for username <span style=\"text-decoration:none;border-style:none;border:0;padding:0;margin:0;border:none;text-decoration:none;font-weight:400;color:#1879DB;\">${TRIPOIN.CONTENT.USERNAME}</span>, click the button below. If you didn\'t make this request, please ignore this email. </td> 										</tr> 										<tr> 											<td height=\"25\" style=\"height:25px;padding:0;margin:0;line-height:1px;font-size:1px;\"></td> 										</tr> 										<!--*********** button ************--> 										<tr> 											<td align=\"left\" style=\"padding:0;margin:0;line-height:1px;font-size:1px;\"> 												<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"padding:0;margin:0;line-height:1px;font-size:1px;\"> 													<tbody> 														<tr> 															<td style=\"padding:0;margin:0;line-height:1px;font-size:1px;\"> 																<!-- Tap, click, press, push the button --> 																<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"padding:0;margin:0;line-height:1px;font-size:1px;\"> 																	<tbody> 																		<tr> 																			<td style=\"padding:0;margin:0;line-height:1px;font-size:1px;\"> 																				<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"padding:0;margin:0;line-height:1px;font-size:1px;\"> 																					<tbody> 																						<tr> 																							<td align=\"center\" class=\"bulletproof-btn-1\" bgcolor=\"#1879DB\" style=\"padding:0;margin:0;line-height:1px;font-size:1px;-webkit-border-radius:4px;-moz-border-radius:4px;border-radius:4px;line-height:18px;\"> 																								<a href=\"${TRIPOIN.CONTENT.URL}\" target=\"_blank\" class=\"bulletproof-btn-2\" style=\"text-decoration:none;border-style:none;border:0;padding:0;margin:0;font-family:\'Helvetica Neue\', Helvetica, Arial, sans-serif;font-size:16px;line-height:22px;font-weight:650;-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:none;color:#ffffff;text-align:center;text-decoration:none;-webkit-border-radius:4px;-moz-border-radius:4px;border-radius:4px;padding:11px 30px;border:1px solid #1879DB;display:inline-block;\"> 																									<strong>Reset password</strong> 																								</a> 																							</td> 																						</tr> 																					</tbody> 																				</table>  																			</td> 																		</tr> 																	</tbody> 																</table>  															</td> 														</tr> 													</tbody> 												</table>  											</td> 										</tr> 										<!--*********** end button ************--> 										<tr> 											<td height=\"55\" style=\"height:55px;padding:0;margin:0;line-height:1px;font-size:1px;\"></td> 										</tr> 										</tbody> 									</table>  								</td> 								<td width=\"50\" style=\"width:50px;padding:0;margin:0;line-height:1px;font-size:1px;\" class=\"margins\"></td> 								</tr> 							</tbody> 						</table> 						<!--//////////////////////////////////////////////--> 						<table class=\"collapse\" id=\"footer\" align=\"center\" width=\"650\" style=\"width:650px;background-color:#ffffff;padding:0;margin:0;line-height:1px;font-size:1px;\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"> 							<tbody> 								<tr> 									<td height=\"1\" style=\"line-height:1px;display:block;height:1px;background-color:#FAFAFA;padding:0;margin:0;line-height:1px;font-size:1px;\"></td> 								</tr> 								<tr> 									<td height=\"20\" style=\"height:20;padding:0;margin:0;line-height:1px;font-size:1px;\"></td> 								</tr> 								<tr> 									<td align=\"center\" style=\"padding:0;margin:0;line-height:1px;font-size:1px;\"> <span class=\"footer_type\" style=\"font-family:\'Helvetica Neue Light\', Helvetica, Arial, sans-serif;-webkit-font-smoothing:antialiased;color:#8899a6;font-size:12px;padding:0px;margin:0px;font-weight:normal;line-height:12px;\">  <a href=\"http://www.tripoin.co.id\" class=\"footer_link\" style=\"text-decoration:none;border-style:none;border:0;padding:0;margin:0;font-family:\'Helvetica Neue Light\', Helvetica, Arial, sans-serif;-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:none;color:#1879DB;font-size:12px;padding:0px;margin:0px;font-weight:600;line-height:12px;\">Tripoin, Inc.</a>  </span> </td> 								</tr> 								<tr> 									<td height=\"10\" style=\"height:10px;line-height:1px;font-size:1px;padding:0;margin:0;line-height:1px;font-size:1px;\"></td> 								</tr> 								<tr> 									<td align=\"center\" style=\"padding:0;margin:0;line-height:1px;font-size:1px;\"> <span class=\"address\"> <a href=\"\" style=\"text-decoration:none;border-style:none;border:0;padding:0;margin:0;font-family:\'Helvetica Neue Light\', Helvetica, Arial, sans-serif;-webkit-font-smoothing:antialiased;color:#8899a6;font-size:12px;padding:0px;margin:0px;font-weight:normal;line-height:12px;cursor:default;\">Indonesia, Jakarta</a> </span> </td> 								</tr> 								<tr> 									<td height=\"26\" style=\"height:26;padding:0;margin:0;line-height:1px;font-size:1px;\"></td> 								</tr> 							</tbody> 						</table>					 					</td> 				</tr> 			</tbody> 		</table> 	</body> </html>', '1', 'Body Email Forgot Password', 'admin', '127.0.0.1', '2015-11-01 02:02:05', null, null, null, null, null);
INSERT INTO `mst_system_parameter` VALUES ('3', 'TRIPOIN.EMAIL.FORGOT.PASSWORD.VERIFY.SUBJECT', 'Verify Reset Password', '1', 'Subject Email Verify Forgot Password', 'admin', '127.0.0.1', '2015-11-02 00:00:00', null, null, null, null, null);
INSERT INTO `mst_system_parameter` VALUES ('4', 'TRIPOIN.EMAIL.FORGOT.PASSWORD.VERIFY.BODY.MESSAGE', '<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\"><html> 	<head>	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />	<meta name=\"viewport\" content=\"width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0\" />	<meta name=\"apple-mobile-web-app-capable\" content=\"yes\" />	<style type=\"text/css\">	@media only screen and (max-width: 320px) { 	table[class=\"edu-module\"]{ 	border-radius: 0px !important; 	-webkit-border-radius: 0px !important; 	-moz-border-radius: 0px !important; 	} 	td[class=\"edu-margins\"]{ 	background-color: #f5f8fa; 	} 	td[class=\"edu-collapse\"]{ 	width: 0px !important; 	} 	td[class=\"edu-space\"]{ 	height: 10px !important; 	background-color: #f5f8fa; 	} 	td[class=\"mobile-height\"]{ 	height: 30px !important; 	} 	} 	@media only screen and (max-width: 420px) { 	span[class=\"address\"] a { 	line-height:18px !important; 	} 	td[class=\"margins\"]{ 	width:18px !important; 	} 	td[class=\"edu-margins\"]{ 	width:18px !important; 	} 	td[class=\"logo_space\"]{ 	height:12px !important; 				} 			}  			@media only screen and (max-width: 480px) { 				table[class=\"collapse\"]{ 					width:100% !important; 				} 				table[class=\"edu-module\"]{ 					width:100% !important; 				} 				span[class=\"address\"]{ 					display:block !important; 					width:240px !important; 				} 				td[class=\"cut\"]{ 					display:none !important; 				} 			} 		</style>	</head>	<body bgcolor=\"#FAFAFA\" style=\"margin:0;padding:0;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;\">		<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\" bgcolor=\"#FAFAFA\" style=\"background-color:#FAFAFA;padding:0;margin:0;line-height:1px;font-size:1px;\" class=\"body_wrapper\">			<tbody>				<tr>					<td align=\"center\" style=\"padding:0;margin:0;line-height:1px;font-size:1px;\">						<table class=\"collapse\" id=\"header\" align=\"center\" width=\"650\" style=\"width: 650px;padding:0;margin:0;line-height:1px;font-size:1px;\" bgcolor=\"#ffffff\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">							<tbody>								<tr>									<td style=\"min-width: 650px;height:1px;padding:0;margin:0;line-height:1px;font-size:1px;\" class=\"cut\"></td>								</tr>							</tbody>						</table> 					</td>				</tr>				<tr>					<td align=\"center\" style=\"padding:0;margin:0;line-height:1px;font-size:1px;\">					<!--///////////////////header///////////////////////////-->						<table class=\"collapse\" id=\"header\" align=\"center\" width=\"650\" style=\"width:650px;background-color:#ffffff;padding:0;margin:0;line-height:1px;font-size:1px;\" bgcolor=\"#ffffff\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">							<tbody>								<tr>									<td height=\"15\" style=\"height:15px;padding:0;margin:0;line-height:1px;font-size:1px;\" class=\"logo_space\">&nbsp; </td>								</tr>								<tr>									<td style=\"padding:0;margin:0;line-height:1px;font-size:1px;\">										<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"left\" style=\"padding:0;margin:0;line-height:1px;font-size:1px;\">											<tbody>												<tr align=\"left\">													<td align=\"left\" width=\"15\" style=\"width:15px;padding:0;margin:0;line-height:1px;font-size:1px;\"></td>													<td align=\"left\" width=\"28\" style=\"padding:0;margin:0;line-height:1px;font-size:1px;\"></td>													<td align=\"left\" width=\"10\" style=\"width:10px;padding:0;margin:0;line-height:1px;font-size:1px;\"></td>													<td align=\"left\" class=\"greeting\" style=\"padding:0;margin:0;line-height:1px;font-size:1px;font-family:\'Helvetica Neue Light\', Helvetica, Arial, sans-serif;-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:none;color:#66757f;font-size:16px;padding:0px;margin:0px;font-weight:300;line-height:100%;text-align:left;\">														Dear ${TRIPOIN.CONTENT.FULLNAME}, 													</td>													<td width=\"50\" style=\"width:50px;padding:0;margin:0;line-height:1px;font-size:1px;\" align=\"left\"></td>												</tr>											</tbody>										</table> 									</td>								</tr>								<tr>								<td height=\"14\" style=\"height:14px;padding:0;margin:0;line-height:1px;font-size:1px;\" class=\"logo_space\">&nbsp; </td>								</tr>							</tbody>						</table>						<!--////////////////////border//////////////////////////-->						<table class=\"collapse\" align=\"center\" width=\"650\" style=\"width:650px;background-color:#ffffff;padding:0;margin:0;line-height:1px;font-size:1px;\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">							<tbody>								<tr id=\"border\">									<td colspan=\"2\" height=\"1\" style=\"line-height:1px;display:block;height:1px;background-color:#FAFAFA;padding:0;margin:0;line-height:1px;font-size:1px;\"></td>								</tr>							</tbody>						</table>						<!--//////////////////////////////////////////////-->						<table class=\"collapse\" align=\"center\" width=\"650\" style=\"width:650px;background-color:#ffffff;padding:0;margin:0;line-height:1px;font-size:1px;\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">							<tbody>								<tr>								<td width=\"50\" style=\"width:50px;padding:0;margin:0;line-height:1px;font-size:1px;\" class=\"margins\"></td>								<td align=\"center\" style=\"padding:0;margin:0;line-height:1px;font-size:1px;\">									<table width=\"100%\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" class=\"collapse\" style=\"padding:0;margin:0;line-height:1px;font-size:1px;\">										<tbody>										<tr>											<td height=\"30\" style=\"height:45px;padding:0;margin:0;line-height:1px;font-size:1px;\"></td>										</tr>										<tr>											<td align=\"left\" class=\"display\" style=\"padding:0;margin:0;line-height:1px;font-size:1px;font-family:\'Helvetica Neue\', Helvetica, Arial, sans-serif;font-size:22px;line-height:30px;font-weight:300;-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:none;text-align:left;color:#292f33;\">												We received a request to reset the password for this account.											</td>										</tr>										<tr>											<td height=\"30\" style=\"height:45px;padding:0;margin:0;line-height:1px;font-size:1px;\"></td>										</tr>										<tr>											<td align=\"left\" class=\"body-text\" style=\"padding:0;margin:0;line-height:1px;font-size:1px;font-family:\'Helvetica Neue\', Helvetica, Arial, sans-serif;font-size:16px;line-height:22px;font-weight:400;-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:none;text-align:left;color:#292f33;\">												Username : <span style=\"text-decoration:none;border-style:none;border:0;padding:0;margin:0;border:none;text-decoration:none;font-weight:400;color:#1879DB;\">${TRIPOIN.CONTENT.USERNAME}</span>											</td>										</tr>										<tr>											<td align=\"left\" class=\"body-text\" style=\"padding:0;margin:0;line-height:1px;font-size:1px;font-family:\'Helvetica Neue\', Helvetica, Arial, sans-serif;font-size:16px;line-height:22px;font-weight:400;-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:none;text-align:left;color:#292f33;\">												Password : <span style=\"text-decoration:none;border-style:none;border:0;padding:0;margin:0;border:none;text-decoration:none;font-weight:400;color:#1879DB;\">${TRIPOIN.CONTENT.PASSWORD}</span>											</td>										</tr>										<tr>											<td height=\"30\" style=\"height:15px;padding:0;margin:0;line-height:1px;font-size:1px;\"></td>										</tr>										<tr>											<td align=\"left\" class=\"body-text\" style=\"padding:0;margin:0;line-height:1px;font-size:1px;font-family:\'Helvetica Neue\', Helvetica, Arial, sans-serif;font-size:16px;line-height:22px;font-weight:400;-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:none;text-align:left;color:#292f33;\">												Please use this new password to login and then change your password to something more to your liking.											</td>										</tr>										<tr>											<td height=\"25\" style=\"height:25px;padding:0;margin:0;line-height:1px;font-size:1px;\"></td>										</tr>										<tr>											<td height=\"55\" style=\"height:55px;padding:0;margin:0;line-height:1px;font-size:1px;\"></td>										</tr>										</tbody>									</table> 								</td>								<td width=\"50\" style=\"width:50px;padding:0;margin:0;line-height:1px;font-size:1px;\" class=\"margins\"></td>								</tr>							</tbody>						</table>						<!--//////////////////////////////////////////////-->						<table class=\"collapse\" id=\"footer\" align=\"center\" width=\"650\" style=\"width:650px;background-color:#ffffff;padding:0;margin:0;line-height:1px;font-size:1px;\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">							<tbody>								<tr>									<td height=\"1\" style=\"line-height:1px;display:block;height:1px;background-color:#FAFAFA;padding:0;margin:0;line-height:1px;font-size:1px;\"></td>								</tr>								<tr>									<td height=\"20\" style=\"height:20;padding:0;margin:0;line-height:1px;font-size:1px;\"></td>								</tr>								<tr>									<td align=\"center\" style=\"padding:0;margin:0;line-height:1px;font-size:1px;\"><span class=\"footer_type\" style=\"font-family:\'Helvetica Neue Light\', Helvetica, Arial, sans-serif;-webkit-font-smoothing:antialiased;color:#8899a6;font-size:12px;padding:0px;margin:0px;font-weight:normal;line-height:12px;\"> <a href=\"http://www.tripoin.co.id\" class=\"footer_link\" style=\"text-decoration:none;border-style:none;border:0;padding:0;margin:0;font-family:\'Helvetica Neue Light\', Helvetica, Arial, sans-serif;-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:none;color:#1879DB;font-size:12px;padding:0px;margin:0px;font-weight:600;line-height:12px;\">Tripoin, Inc.</a> </span></td>								</tr>								<tr>									<td height=\"10\" style=\"height:10px;line-height:1px;font-size:1px;padding:0;margin:0;line-height:1px;font-size:1px;\"></td>								</tr>								<tr>									<td align=\"center\" style=\"padding:0;margin:0;line-height:1px;font-size:1px;\"><span class=\"address\"><a href=\"\" style=\"text-decoration:none;border-style:none;border:0;padding:0;margin:0;font-family:\'Helvetica Neue Light\', Helvetica, Arial, sans-serif;-webkit-font-smoothing:antialiased;color:#8899a6;font-size:12px;padding:0px;margin:0px;font-weight:normal;line-height:12px;cursor:default;\">Indonesia, Jakarta</a></span></td>								</tr>								<tr>									<td height=\"26\" style=\"height:26;padding:0;margin:0;line-height:1px;font-size:1px;\"></td>								</tr>							</tbody>						</table>					 					</td>				</tr>			</tbody>		</table>	</body></html>', '1', 'Body Email Verify Forgot Password', 'admin', '127.0.0.1', '2015-11-02 00:22:52', null, null, null, null, null);

-- ----------------------------
-- Table structure for sec_role
-- ----------------------------
DROP TABLE IF EXISTS `sec_role`;
CREATE TABLE `sec_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(50) NOT NULL,
  `role_status` smallint(5) NOT NULL,
  `role_remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_code` (`role_code`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sec_role
-- ----------------------------
INSERT INTO `sec_role` VALUES ('1', 'ROLE_SALESMAN', '1', 'Role Salesman');
INSERT INTO `sec_role` VALUES ('2', 'ROLE_SALESSUPERVISOR', '1', 'Role Sales Supervisor');
INSERT INTO `sec_role` VALUES ('3', 'ROLE_SALESMANAGER', '1', 'Role Sales Manager');
INSERT INTO `sec_role` VALUES ('4', 'ROLE_ADMIN', '1', 'Role Admin');
INSERT INTO `sec_role` VALUES ('5', 'ROLE_ANONYMOUS_SECURE', '0', 'Role Anonymous Secure');

-- ----------------------------
-- Table structure for sec_user
-- ----------------------------
DROP TABLE IF EXISTS `sec_user`;
CREATE TABLE `sec_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_username` varchar(20) NOT NULL,
  `user_password` varchar(255) NOT NULL,
  `user_enabled` smallint(5) NOT NULL,
  `user_expired_date` timestamp NULL DEFAULT NULL,
  `user_non_locked` smallint(5) DEFAULT NULL,
  `user_auth` varchar(255) DEFAULT NULL,
  `user_status` smallint(5) NOT NULL,
  `user_remarks` varchar(255) DEFAULT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_username` (`user_username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sec_user
-- ----------------------------
INSERT INTO `sec_user` VALUES ('1', 'ridla', 'M6cURmiwEwLW9jiBeYPiMq21DHY=', '1', null, null, null, '1', null, '1');
INSERT INTO `sec_user` VALUES ('2', 'bangkit', 'M6cURmiwEwLW9jiBeYPiMq21DHY=', '1', null, null, null, '1', null, '2');
INSERT INTO `sec_user` VALUES ('3', 'fauzi', 'M6cURmiwEwLW9jiBeYPiMq21DHY=', '1', null, null, null, '1', null, '3');
INSERT INTO `sec_user` VALUES ('4', 'admin', 'M6cURmiwEwLW9jiBeYPiMq21DHY=', '1', null, null, null, '1', null, '4');
INSERT INTO `sec_user` VALUES ('5', 'tripoin.app.web', 'g/vPwZ+XHlXlZCIIYgK28SUyX+Y=', '1', null, null, null, '1', null, '5');
INSERT INTO `sec_user` VALUES ('6', 'tripoin.app.android', 'g/vPwZ+XHlXlZCIIYgK28SUyX+Y=', '1', null, null, null, '1', null, '5');

-- ----------------------------
-- Table structure for vcs_table
-- ----------------------------
DROP TABLE IF EXISTS `vcs_table`;
CREATE TABLE `vcs_table` (
  `vcs_table_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `vcs_table_code` varchar(100) NOT NULL,
  `vcs_table_total_row` bigint(20) NOT NULL,
  `vcs_table_status` smallint(5) DEFAULT NULL,
  `vcs_table_remarks` varchar(255) DEFAULT NULL,
  `vcs_table_created_by` varchar(150) DEFAULT 'admin',
  `vcs_table_created_ip` varchar(150) DEFAULT '127.0.0.1',
  `vcs_table_created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `vcs_table_created_platform` varchar(255) DEFAULT NULL,
  `vcs_table_modified_by` varchar(150) DEFAULT NULL,
  `vcs_table_modified_ip` varchar(150) DEFAULT NULL,
  `vcs_table_modified_time` timestamp NULL DEFAULT NULL,
  `vcs_table_modified_platform` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`vcs_table_id`),
  UNIQUE KEY `system_parameter_code` (`vcs_table_code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of vcs_table
-- ----------------------------
INSERT INTO `vcs_table` VALUES ('1', 'mst_employee', '3', '1', 'Tabel of mst_employee', 'admin', '127.0.0.1', '2016-01-04 09:43:24', null, null, null, null, null);
INSERT INTO `vcs_table` VALUES ('2', 'mst_occupation', '3', '1', 'Table of mst_occupation', 'admin', '127.0.0.1', '2016-01-04 09:43:24', null, null, null, null, null);
INSERT INTO `vcs_table` VALUES ('3', 'mst_area', '40', '1', 'Table of mst_area', 'admin', '127.0.0.1', '2016-01-04 09:43:24', '', '', '', '2016-02-11 21:35:11', '');

-- ----------------------------
-- Table structure for vcs_user
-- ----------------------------
DROP TABLE IF EXISTS `vcs_user`;
CREATE TABLE `vcs_user` (
  `vcs_user_id` int(11) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `vcs_user_version` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`vcs_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of vcs_user
-- ----------------------------
