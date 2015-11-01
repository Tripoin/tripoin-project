-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 01, 2015 at 08:12 AM
-- Server version: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `tripoin`
--

-- --------------------------------------------------------

--
-- Table structure for table `mst_menu`
--

CREATE TABLE IF NOT EXISTS `mst_menu` (
`menu_id` bigint(20) NOT NULL,
  `menu_code` varchar(150) NOT NULL,
  `menu_name` varchar(255) DEFAULT NULL,
  `menu_parent_id` bigint(20) DEFAULT NULL,
  `menu_level` int(5) NOT NULL DEFAULT '1',
  `menu_order` int(5) NOT NULL DEFAULT '1',
  `menu_tree` varchar(20) NOT NULL,
  `menu_function` varchar(20) NOT NULL DEFAULT 'MENU-PAGE',
  `menu_view_type` varchar(50) NOT NULL DEFAULT 'WEB-MOBILE',
  `menu_status` smallint(6) DEFAULT '1',
  `menu_remarks` varchar(255) DEFAULT NULL,
  `menu_created_by` varchar(150) DEFAULT 'admin',
  `menu_created_ip` varchar(150) DEFAULT '127.0.0.1',
  `menu_created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `menu_created_platform` varchar(255) DEFAULT NULL,
  `menu_modified_by` varchar(150) DEFAULT NULL,
  `menu_modified_ip` varchar(150) DEFAULT NULL,
  `menu_modified_time` timestamp NULL DEFAULT NULL,
  `menu_modified_platform` varchar(255) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=27 ;

--
-- Dumping data for table `mst_menu`
--

INSERT INTO `mst_menu` (`menu_id`, `menu_code`, `menu_name`, `menu_parent_id`, `menu_level`, `menu_order`, `menu_tree`, `menu_function`, `menu_view_type`, `menu_status`, `menu_remarks`, `menu_created_by`, `menu_created_ip`, `menu_created_time`, `menu_created_platform`, `menu_modified_by`, `menu_modified_ip`, `menu_modified_time`, `menu_modified_platform`) VALUES
(1, 'activitySalesView', 'Activity Sales', NULL, 1, 2, '2', 'MENU-NON-PAGE', 'WEB-MOBILE', 1, 'Activity Sales', 'admin', '127.0.0.1', '2015-07-17 08:31:07', NULL, NULL, NULL, NULL, NULL),
(2, 'callPlanView', 'Call Plan', 1, 2, 1, '2.1', 'MENU-PAGE', 'WEB-MOBILE', 1, 'Call Plan', 'admin', '127.0.0.1', '2015-07-17 08:37:11', NULL, NULL, NULL, NULL, NULL),
(3, 'quotationView', 'Quotation', 1, 2, 2, '2.2', 'MENU-PAGE', 'WEB-MOBILE', 1, 'Quotation', 'admin', '127.0.0.1', '2015-07-17 08:37:38', NULL, NULL, NULL, NULL, NULL),
(4, 'callReportView', 'Call Report', 1, 2, 3, '2.3', 'MENU-PAGE', 'WEB-MOBILE', 1, 'Call Report', 'admin', '127.0.0.1', '2015-07-17 08:47:51', NULL, NULL, NULL, NULL, NULL),
(5, 'manageCustomerView', 'Manage Customer', NULL, 1, 3, '3', 'MENU-PAGE', 'WEB-MOBILE', 1, 'Manage Customer', 'admin', '127.0.0.1', '2015-07-17 08:48:17', NULL, NULL, NULL, NULL, NULL),
(6, 'auditTrailView', 'Audit Trail', NULL, 1, 4, '4', 'MENU-NON-PAGE', 'WEB', 1, 'Audit Trail', 'admin', '127.0.0.1', '2015-07-17 08:48:17', NULL, NULL, NULL, NULL, NULL),
(7, 'historyLoginView', 'History Login', 6, 2, 1, '4.1', 'MENU-PAGE', 'WEB', 1, 'History Login', 'admin', '127.0.0.1', '2015-07-17 08:49:22', NULL, NULL, NULL, NULL, NULL),
(8, 'historyCallPlanView', 'History Call Plan', 6, 2, 2, '4.2', 'MENU-PAGE', 'WEB', 1, 'History Call Plan', 'admin', '127.0.0.1', '2015-07-17 08:49:22', NULL, NULL, NULL, NULL, NULL),
(9, 'historyQuotationView', 'History Quotation', 6, 2, 3, '4.3', 'MENU-PAGE', 'WEB', 1, 'History Quotation', 'admin', '127.0.0.1', '2015-10-24 17:00:00', NULL, NULL, NULL, NULL, NULL),
(10, 'historyCallReportView', 'History Call Report', 6, 2, 4, '4.4', 'MENU-PAGE', 'WEB', 1, 'History Call Report', 'admin', '127.0.0.1', '2015-07-17 08:49:56', NULL, NULL, NULL, NULL, NULL),
(11, 'approvalView', 'Approval', NULL, 1, 5, '5', 'MENU-NON-PAGE', 'WEB', 1, 'Approval', 'admin', '127.0.0.1', '2015-07-17 08:49:56', NULL, NULL, NULL, NULL, NULL),
(12, 'approvalCallPlanView', 'Approval Call Plan', 11, 2, 1, '5.1', 'MENU-PAGE', 'WEB', 1, 'Approval Call Plan', 'admin', '127.0.0.1', '2015-07-17 08:55:18', NULL, NULL, NULL, NULL, NULL),
(13, 'approvalQuotationView', 'Approval Quotation', 11, 2, 2, '5.2', 'MENU-PAGE', 'WEB', 1, 'Approval Quotation', 'admin', '127.0.0.1', '2015-07-17 08:55:18', NULL, NULL, NULL, NULL, NULL),
(14, 'approvalCallReportView', 'Approval Call Report', 11, 2, 3, '5.3', 'MENU-PAGE', 'WEB', 1, 'Approval Call Report', 'admin', '127.0.0.1', '2015-07-17 08:55:53', NULL, NULL, NULL, NULL, NULL),
(15, 'dashboardView', 'Dashboard', NULL, 1, 1, '1', 'MENU-NON-PAGE', 'WEB-MOBILE', 1, 'Dashboard', 'admin', '127.0.0.1', '2015-07-17 08:55:53', NULL, NULL, NULL, NULL, NULL),
(16, 'salesPerformanceView', 'Sales Performance', 15, 2, 1, '1.1', 'MENU-PAGE', 'WEB-MOBILE', 1, 'Sales Performance', 'admin', '127.0.0.1', '2015-07-17 08:57:22', NULL, NULL, NULL, NULL, NULL),
(17, 'systemAnalyzerView', 'System Analyzer', 15, 2, 2, '1.2', 'MENU-PAGE', 'WEB-MOBILE', 1, 'System Analyzer', 'admin', '127.0.0.1', '2015-07-17 08:57:22', NULL, NULL, NULL, NULL, NULL),
(18, 'customerIncomeView', 'Customer Income', 15, 2, 3, '1.3', 'MENU-PAGE', 'WEB-MOBILE', 1, 'Customer Income', 'admin', '127.0.0.1', '2015-10-23 17:00:00', NULL, NULL, NULL, NULL, NULL),
(19, 'masterDataView', 'Master Data', NULL, 1, 6, '6', 'MENU-NON-PAGE', 'WEB', 1, 'Master Data', 'admin', '127.0.0.1', '2015-10-24 17:00:00', NULL, NULL, NULL, NULL, NULL),
(20, 'dataEmployeeView', 'Data Employee', 19, 2, 1, '6.1', 'MENU-PAGE', 'WEB', 1, 'Data Employee', 'admin', '127.0.0.1', '2015-10-24 17:27:24', NULL, NULL, NULL, NULL, NULL),
(21, 'dataOccupationView', 'Data Occupation', 19, 2, 2, '6.2', 'MENU-PAGE', 'WEB', 1, 'Data Occupation', 'admin', '127.0.0.1', '2015-10-24 17:00:00', NULL, NULL, NULL, NULL, NULL),
(22, 'dataAreaView', 'Data Area', 19, 2, 3, '6.3', 'MENU-PAGE', 'WEB', 1, 'Data Area', 'admin', '127.0.0.1', '2015-10-24 17:00:00', NULL, NULL, NULL, NULL, NULL),
(23, 'dataProjectView', 'Data Project', 19, 2, 4, '6.4', 'MENU-PAGE', 'WEB', 1, 'Data Project', 'admin', '127.0.0.1', '2015-10-24 17:00:00', NULL, NULL, NULL, NULL, NULL),
(24, 'dataCompetitiveInformationSourceView', 'Data Competitive Information Source', 19, 2, 5, '6.5', 'MENU-PAGE', 'WEB', 1, 'Data Competitive Information Source', 'admin', '127.0.0.1', '2015-10-24 17:00:00', NULL, NULL, NULL, NULL, NULL),
(25, 'dataComparisonView', 'Data Comparison', 19, 2, 6, '6.6', 'MENU-PAGE', 'WEB', 1, 'Data Comparison', 'admin', '127.0.0.1', '2015-10-24 17:00:00', NULL, NULL, NULL, NULL, NULL),
(26, 'dataCurrencyView', 'Data Currency', 19, 2, 7, '6.7', 'MENU-PAGE', 'WEB', 1, 'Data Currency', 'admin', '127.0.0.1', '2015-10-24 17:00:00', NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `mst_menu_role`
--

CREATE TABLE IF NOT EXISTS `mst_menu_role` (
`menu_role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=43 ;

--
-- Dumping data for table `mst_menu_role`
--

INSERT INTO `mst_menu_role` (`menu_role_id`, `menu_id`, `role_id`) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 1),
(4, 4, 1),
(5, 5, 1),
(6, 15, 1),
(7, 16, 1),
(8, 17, 1),
(9, 18, 1),
(10, 5, 2),
(11, 6, 2),
(12, 7, 2),
(13, 8, 2),
(14, 9, 2),
(15, 10, 2),
(16, 11, 2),
(22, 12, 2),
(23, 13, 2),
(24, 14, 2),
(25, 15, 2),
(26, 16, 2),
(27, 17, 2),
(28, 18, 2),
(29, 6, 3),
(30, 10, 3),
(31, 15, 3),
(32, 16, 3),
(33, 17, 3),
(34, 18, 3),
(35, 19, 4),
(36, 20, 4),
(37, 21, 4),
(38, 22, 4),
(39, 23, 4),
(40, 24, 4),
(41, 25, 4),
(42, 26, 4);

-- --------------------------------------------------------

--
-- Table structure for table `mst_profile`
--

CREATE TABLE IF NOT EXISTS `mst_profile` (
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
  `profile_forgot_uuid` varchar(255) DEFAULT NULL,
  `profile_forgot_expired` timestamp NULL DEFAULT NULL,
  `profile_created_by` varchar(150) DEFAULT 'admin',
  `profile_created_ip` varchar(150) DEFAULT '127.0.0.1',
  `profile_created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `profile_created_platform` varchar(255) DEFAULT NULL,
  `profile_modified_by` varchar(150) DEFAULT NULL,
  `profile_modified_ip` varchar(150) DEFAULT NULL,
  `profile_modified_time` timestamp NULL DEFAULT NULL,
  `profile_modified_platform` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mst_profile`
--

INSERT INTO `mst_profile` (`profile_id`, `profile_email`, `profile_name`, `profile_gender`, `profile_birthplace`, `profile_birthdate`, `profile_address`, `profile_telp`, `profile_photo`, `user_id`, `profile_phone`, `profile_bio`, `profile_forgot_uuid`, `profile_forgot_expired`, `profile_created_by`, `profile_created_ip`, `profile_created_time`, `profile_created_platform`, `profile_modified_by`, `profile_modified_ip`, `profile_modified_time`, `profile_modified_platform`) VALUES
(1, 'ridla.fadilah@tripoin.co.id', 'Ridla Fadilah', 'MALE', 'Bandung', '1990-12-27', 'Tangerang', '021234567891', '-', 1, '081234567891', '<font face="Courier New">This is Me</font>', NULL, NULL, 'admin', '127.0.0.1', '2015-10-28 03:57:43', NULL, 'ridla', '127.0.0.1', '2015-10-31 08:00:55', 'Computer | Windows | Chrome'),
(2, 'bangkit.pratolo@tripoin.co.id', 'Bangkit Pratolo', 'MALE', 'Tangerang', '2015-10-12', 'Tangerang', '-', '-', 2, '081234567892', '-', NULL, NULL, 'admin', '127.0.0.1', '2015-10-28 03:57:43', NULL, NULL, NULL, NULL, NULL),
(3, 'achmad.fauzi@tripoin.co.id', 'Achmad Fauzi', 'MALE', 'Tangerang', '2015-10-13', 'Tangerang', '-', '-', 3, '081234567893', '-', NULL, NULL, 'admin', '127.0.0.1', '2015-10-28 03:57:43', NULL, NULL, NULL, NULL, NULL),
(4, 'admin@tripoin.co.id', 'Administrator', 'FEMALE', 'Tangerang', '2015-10-14', 'Tangerang', '-', '-', 4, '081234567894', '-', NULL, NULL, 'admin', '127.0.0.1', '2015-10-28 03:57:43', NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `mst_system_parameter`
--

CREATE TABLE IF NOT EXISTS `mst_system_parameter` (
`system_parameter_id` bigint(20) NOT NULL,
  `system_parameter_code` varchar(100) NOT NULL,
  `system_parameter_value` text NOT NULL,
  `system_parameter_status` smallint(6) DEFAULT NULL,
  `system_parameter_remarks` varchar(255) DEFAULT NULL,
  `system_parameter_created_by` varchar(150) DEFAULT 'admin',
  `system_parameter_created_ip` varchar(150) DEFAULT '127.0.0.1',
  `system_parameter_created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `system_parameter_created_platform` varchar(255) DEFAULT NULL,
  `system_parameter_modified_by` varchar(150) DEFAULT NULL,
  `system_parameter_modified_ip` varchar(150) DEFAULT NULL,
  `system_parameter_modified_time` timestamp NULL DEFAULT NULL,
  `system_parameter_modified_platform` varchar(255) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `mst_system_parameter`
--

INSERT INTO `mst_system_parameter` (`system_parameter_id`, `system_parameter_code`, `system_parameter_value`, `system_parameter_status`, `system_parameter_remarks`, `system_parameter_created_by`, `system_parameter_created_ip`, `system_parameter_created_time`, `system_parameter_created_platform`, `system_parameter_modified_by`, `system_parameter_modified_ip`, `system_parameter_modified_time`, `system_parameter_modified_platform`) VALUES
(1, 'TRIPOIN.EMAIL.FORGOT.PASSWORD.SUBJECT', 'Request Reset Password', 1, 'Subject Email Forgot Password', 'admin', '127.0.0.1', '2015-10-31 17:00:00', NULL, NULL, NULL, NULL, NULL),
(2, 'TRIPOIN.EMAIL.FORGOT.PASSWORD.BODY.MESSAGE', '<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/htm=\r\nl4/strict.dtd">\r\n<html>\r\n<head>\r\n<meta http-equiv=3D"Content-Type" content=3D"text/html; charset=3Dutf-8" />\r\n<meta name=3D"viewport" content=3D"width=3Ddevice-width, minimum-scale=3D1.=\r\n0, maximum-scale=3D1.0, user-scalable=3D0" />\r\n<meta name=3D"apple-mobile-web-app-capable" content=3D"yes" />\r\n<style type=3D"text/css">\r\n\r\n@media only screen and (max-width: 320px) {\r\ntable[class=3D"edu-module"]{\r\nborder-radius: 0px !important;\r\n-webkit-border-radius: 0px !important;\r\n-moz-border-radius: 0px !important;\r\n}\r\ntd[class=3D"edu-margins"]{\r\nbackground-color: #f5f8fa;\r\n}\r\ntd[class=3D"edu-collapse"]{\r\nwidth: 0px !important;\r\n}\r\ntd[class=3D"edu-space"]{\r\nheight: 10px !important;\r\nbackground-color: #f5f8fa;\r\n}\r\ntd[class=3D"mobile-height"]{\r\nheight: 30px !important;\r\n}\r\n}\r\n\r\n@media only screen and (max-width: 420px) {\r\n\r\nspan[class=3D"address"] a {\r\nline-height:18px !important;\r\n}\r\ntd[class=3D"margins"]{\r\nwidth:18px !important;\r\n}\r\ntd[class=3D"edu-margins"]{\r\nwidth:18px !important;\r\n}\r\ntd[class=3D"logo_space"]{\r\nheight:12px !important;\r\n}\r\n}\r\n\r\n@media only screen and (max-width: 480px) {\r\n\r\ntable[class=3D"collapse"]{\r\nwidth:100% !important;\r\n}\r\n\r\ntable[class=3D"edu-module"]{\r\nwidth:100% !important;\r\n}\r\n\r\nspan[class=3D"address"]{\r\ndisplay:block !important;\r\nwidth:240px !important;\r\n}\r\ntd[class=3D"cut"]{\r\ndisplay:none !important;\r\n}\r\n\r\n}\r\n</style>\r\n</head>\r\n<body bgcolor=3D"#e1e8ed" style=3D"margin:0;padding:0;-webkit-text-size-adj=\r\nust:100%;-ms-text-size-adjust:100%;">\r\n<table cellpadding=3D"0" cellspacing=3D"0" border=3D"0" width=3D"100%" bgco=\r\nlor=3D"#e1e8ed" style=3D"background-color:#e1e8ed;padding:0;margin:0;line-h=\r\neight:1px;font-size:1px;" class=3D"body_wrapper">\r\n<tbody>\r\n<tr>\r\n<td align=3D"center" style=3D"padding:0;margin:0;line-height:1px;font-size:=\r\n1px;">\r\n<table class=3D"collapse" id=3D"header" align=3D"center" width=3D"500" styl=\r\ne=3D"width: 500px;padding:0;margin:0;line-height:1px;font-size:1px;" bgcolo=\r\nr=3D"#ffffff" cellpadding=3D"0" cellspacing=3D"0" border=3D"0">\r\n<tbody>\r\n<tr>\r\n<td style=3D"min-width: 500px;height:1px;padding:0;margin:0;line-height:1px=\r\n;font-size:1px;" class=3D"cut"> <img src=3D"https://ea.twimg.com/email/self=\r\n_serve/media/spacer-1402696023930.png" style=3D"min-width: 500px;height:1px=\r\n;margin:0;padding:0;display:block;-ms-interpolation-mode:bicubic;border:non=\r\ne;outline:none;" /> </td>\r\n</tr>\r\n</tbody>\r\n</table> </td>\r\n</tr>\r\n<tr>\r\n<td align=3D"center" style=3D"padding:0;margin:0;line-height:1px;font-size:=\r\n1px;">\r\n<!--///////////////////header///////////////////////////-->\r\n<table class=3D"collapse" id=3D"header" align=3D"center" width=3D"500" styl=\r\ne=3D"width:500px;background-color:#ffffff;padding:0;margin:0;line-height:1p=\r\nx;font-size:1px;" bgcolor=3D"#ffffff" cellpadding=3D"0" cellspacing=3D"0" b=\r\norder=3D"0">\r\n<tbody>\r\n<tr>\r\n<td height=3D"15" style=3D"height:15px;padding:0;margin:0;line-height:1px;f=\r\nont-size:1px;" class=3D"logo_space"> &nbsp; </td>\r\n</tr>\r\n<tr>\r\n<td style=3D"padding:0;margin:0;line-height:1px;font-size:1px;">\r\n<table cellpadding=3D"0" cellspacing=3D"0" border=3D"0" align=3D"left" styl=\r\ne=3D"padding:0;margin:0;line-height:1px;font-size:1px;">\r\n<tbody>\r\n<tr align=3D"left">\r\n<td align=3D"left" width=3D"15" style=3D"width:15px;padding:0;margin:0;line=\r\n-height:1px;font-size:1px;"></td>\r\n<td align=3D"left" width=3D"28" style=3D"padding:0;margin:0;line-height:1px=\r\n;font-size:1px;"> <a href=3D"https://twitter.com/i/redirect?url=3Dhttps%3A%=\r\n2F%2Ftwitter.com%2F%3Frefsrc%3Demail&amp;t=3D1&amp;cn=3DcGFzc3dvcmRfcmVzZXR=\r\nfdjI%3D&amp;sig=3D010081a632db83fd0764fe0a95a9ee4efe4c2773&amp;iid=3Dec1152=\r\n432cca4dbfa65e0f22b61234fc&amp;uid=3D326614242&amp;nid=3D248+1553" style=3D=\r\n"text-decoration:none;border-style:none;border:0;padding:0;margin:0;"><img =\r\nalign=3D"left" width=3D"28" src=3D"https://ea.twimg.com/email/self_serve/me=\r\ndia/logo-1400528502322.png" style=3D"width:28px;padding-bottom:2px;margin:0=\r\n;padding:0;display:block;-ms-interpolation-mode:bicubic;border:none;outline=\r\n:none;" /></a> </td>\r\n<td align=3D"left" width=3D"10" style=3D"width:10px;padding:0;margin:0;line=\r\n-height:1px;font-size:1px;"></td>\r\n<td align=3D"left" class=3D"greeting" style=3D"padding:0;margin:0;line-heig=\r\nht:1px;font-size:1px;font-family:''Helvetica Neue Light'', Helvetica, Arial, =\r\nsans-serif;-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:none=\r\n;color:#66757f;font-size:16px;padding:0px;margin:0px;font-weight:300;line-h=\r\neight:100%;text-align:left;"> Hi ridlafadilah, </td>\r\n<td width=3D"50" style=3D"width:50px;padding:0;margin:0;line-height:1px;fon=\r\nt-size:1px;" align=3D"left"></td>\r\n</tr>\r\n</tbody>\r\n</table> </td>\r\n</tr>\r\n<tr>\r\n<td height=3D"14" style=3D"height:14px;padding:0;margin:0;line-height:1px;f=\r\nont-size:1px;" class=3D"logo_space"> &nbsp; </td>\r\n</tr>\r\n</tbody>\r\n</table>\r\n<!--////////////////////border//////////////////////////-->\r\n<table class=3D"collapse" align=3D"center" width=3D"500" style=3D"width:500=\r\npx;background-color:#ffffff;padding:0;margin:0;line-height:1px;font-size:1p=\r\nx;" cellpadding=3D"0" cellspacing=3D"0" border=3D"0">\r\n<tbody>\r\n<tr id=3D"border">\r\n<td colspan=3D"2" height=3D"1" style=3D"line-height:1px;display:block;heigh=\r\nt:1px;background-color:#e1e8ed;padding:0;margin:0;line-height:1px;font-size=\r\n:1px;"></td>\r\n</tr>\r\n</tbody>\r\n</table>\r\n<!--//////////////////////////////////////////////-->\r\n<table class=3D"collapse" align=3D"center" width=3D"500" style=3D"width:500=\r\npx;background-color:#ffffff;padding:0;margin:0;line-height:1px;font-size:1p=\r\nx;" cellpadding=3D"0" cellspacing=3D"0" border=3D"0">\r\n<tbody>\r\n<tr>\r\n<td width=3D"50" style=3D"width:50px;padding:0;margin:0;line-height:1px;fon=\r\nt-size:1px;" class=3D"margins"></td>\r\n<td align=3D"center" style=3D"padding:0;margin:0;line-height:1px;font-size:=\r\n1px;">\r\n<table width=3D"100%" align=3D"center" cellpadding=3D"0" cellspacing=3D"0" =\r\nborder=3D"0" class=3D"collapse" style=3D"padding:0;margin:0;line-height:1px=\r\n;font-size:1px;">\r\n<tbody>\r\n<tr>\r\n<td height=3D"30" style=3D"height:45px;padding:0;margin:0;line-height:1px;f=\r\nont-size:1px;"></td>\r\n</tr>\r\n<tr>\r\n<td align=3D"left" class=3D"display" style=3D"padding:0;margin:0;line-heigh=\r\nt:1px;font-size:1px;font-family:''Helvetica Neue'', Helvetica, Arial, sans-se=\r\nrif;font-size:22px;line-height:30px;font-weight:300;-webkit-font-smoothing:=\r\nantialiased;-webkit-text-size-adjust:none;text-align:left;color:#292f33;"> =\r\nWe received a request to reset the password for your account. </td>\r\n</tr>\r\n<tr>\r\n<td height=3D"30" style=3D"height:30px;padding:0;margin:0;line-height:1px;f=\r\nont-size:1px;"></td>\r\n</tr>\r\n<tr>\r\n<td align=3D"left" class=3D"body-text" style=3D"padding:0;margin:0;line-hei=\r\nght:1px;font-size:1px;font-family:''Helvetica Neue'', Helvetica, Arial, sans-=\r\nserif;font-size:16px;line-height:22px;font-weight:400;-webkit-font-smoothin=\r\ng:antialiased;-webkit-text-size-adjust:none;text-align:left;color:#292f33;"=\r\n> If you requested a reset for <a href=3D"https://twitter.com/i/redirect?ur=\r\nl=3Dhttps%3A%2F%2Ftwitter.com%2FFadilahRidla%3Fcn%3DcGFzc3dvcmRfcmVzZXRfdjI=\r\n%253D%26refsrc%3Demail&amp;t=3D1&amp;cn=3DcGFzc3dvcmRfcmVzZXRfdjI%3D&amp;si=\r\ng=3Dbf357a266468612ba616fa4414911cf46bc37309&amp;iid=3Dec1152432cca4dbfa65e=\r\n0f22b61234fc&amp;uid=3D326614242&amp;nid=3D248+22" style=3D"text-decoration=\r\n:none;border-style:none;border:0;padding:0;margin:0;border:none;text-decora=\r\ntion:none;font-weight:400;color:#55acee;">@FadilahRidla</a>, click the butt=\r\non below. If you didn=E2=80=99t make this request, please ignore this email=\r\n. </td>\r\n</tr>\r\n<tr>\r\n<td height=3D"25" style=3D"height:25px;padding:0;margin:0;line-height:1px;f=\r\nont-size:1px;"></td>\r\n</tr>\r\n<!--*********** button ************-->\r\n<tr>\r\n<td align=3D"left" style=3D"padding:0;margin:0;line-height:1px;font-size:1p=\r\nx;">\r\n<table border=3D"0" cellspacing=3D"0" cellpadding=3D"0" style=3D"padding:0;=\r\nmargin:0;line-height:1px;font-size:1px;">\r\n<tbody>\r\n<tr>\r\n<td style=3D"padding:0;margin:0;line-height:1px;font-size:1px;">\r\n<!-- Tap, click, press, push the button -->\r\n<table width=3D"100%" border=3D"0" cellspacing=3D"0" cellpadding=3D"0" styl=\r\ne=3D"padding:0;margin:0;line-height:1px;font-size:1px;">\r\n<tbody>\r\n<tr>\r\n<td style=3D"padding:0;margin:0;line-height:1px;font-size:1px;">\r\n<table border=3D"0" cellspacing=3D"0" cellpadding=3D"0" style=3D"padding:0;=\r\nmargin:0;line-height:1px;font-size:1px;">\r\n<tbody>\r\n<tr>\r\n<td align=3D"center" class=3D"bulletproof-btn-1" bgcolor=3D"#55acee" style=\r\n=3D"padding:0;margin:0;line-height:1px;font-size:1px;-webkit-border-radius:=\r\n4px;-moz-border-radius:4px;border-radius:4px;line-height:18px;"><a href=3D"=\r\nhttps://twitter.com/i/redirect?url=3Dhttps%3A%2F%2Ftwitter.com%2Faccount%2F=\r\nconfirm_email_reset%3Freset_type%3De%26user_id%3D326614242%26token%3DfQKFuF=\r\nNu9UV0MLRe%21RaSAtKEuQ7mJ3MrxUL3UQVFEHw%3D-1440437221958%26confirm_pending_=\r\nemail%3D0%26token_version%3D0%26password_reset_cause%3Duser%26cn%3DcGFzc3dv=\r\ncmRfcmVzZXRfdjI%253D&amp;t=3D1&amp;cn=3DcGFzc3dvcmRfcmVzZXRfdjI%3D&amp;sig=\r\n=3D3c56d8fc2bc15ef51101a97a045f45a1e2a75302&amp;iid=3Dec1152432cca4dbfa65e0=\r\nf22b61234fc&amp;uid=3D326614242&amp;nid=3D248+1393" target=3D"_blank" class=\r\n=3D"bulletproof-btn-2" style=3D"text-decoration:none;border-style:none;bord=\r\ner:0;padding:0;margin:0;font-family:''Helvetica Neue'', Helvetica, Arial, san=\r\ns-serif;font-size:16px;line-height:22px;font-weight:500;-webkit-font-smooth=\r\ning:antialiased;-webkit-text-size-adjust:none;color:#ffffff;text-align:cent=\r\ner;text-decoration:none;-webkit-border-radius:4px;-moz-border-radius:4px;bo=\r\nrder-radius:4px;padding:11px 30px;border:1px solid #55acee;display:inline-b=\r\nlock;">\r\n<!--[if gte mso 11]>&nbsp; &nbsp; &nbsp;<![endif]--> <strong>Reset password=\r\n</strong>\r\n<!--[if gte mso 11]>&nbsp; &nbsp; &nbsp;<![endif]--> </a></td>\r\n</tr>\r\n</tbody>\r\n</table> </td>\r\n</tr>\r\n</tbody>\r\n</table> </td>\r\n</tr>\r\n</tbody>\r\n</table> </td>\r\n</tr>\r\n<!--*********** end button ************-->\r\n<tr>\r\n<td height=3D"55" style=3D"height:55px;padding:0;margin:0;line-height:1px;f=\r\nont-size:1px;"></td>\r\n</tr>\r\n</tbody>\r\n</table> </td>\r\n<td width=3D"50" style=3D"width:50px;padding:0;margin:0;line-height:1px;fon=\r\nt-size:1px;" class=3D"margins"></td>\r\n</tr>\r\n</tbody>\r\n</table>\r\n<!--////////////////////border//////////////////////////-->\r\n<table class=3D"collapse" align=3D"center" width=3D"500" style=3D"width:500=\r\npx;background-color:#ffffff;padding:0;margin:0;line-height:1px;font-size:1p=\r\nx;" cellpadding=3D"0" cellspacing=3D"0" border=3D"0">\r\n<tbody>\r\n<tr id=3D"border">\r\n<td colspan=3D"2" height=3D"1" style=3D"line-height:1px;display:block;heigh=\r\nt:1px;background-color:#e1e8ed;padding:0;margin:0;line-height:1px;font-size=\r\n:1px;"></td>\r\n</tr>\r\n</tbody>\r\n</table>\r\n<table class=3D"collapse" align=3D"center" width=3D"500" style=3D"width:500=\r\npx;background-color:#ffffff;padding:0;margin:0;line-height:1px;font-size:1p=\r\nx;" cellpadding=3D"0" cellspacing=3D"0" border=3D"0">\r\n<tbody>\r\n<tr>\r\n<td width=3D"50" style=3D"width:50px;padding:0;margin:0;line-height:1px;fon=\r\nt-size:1px;" class=3D"margins"></td>\r\n<td align=3D"center" style=3D"padding:0;margin:0;line-height:1px;font-size:=\r\n1px;">\r\n<table width=3D"100%" align=3D"center" cellpadding=3D"0" cellspacing=3D"0" =\r\nborder=3D"0" class=3D"collapse" style=3D"padding:0;margin:0;line-height:1px=\r\n;font-size:1px;">\r\n<tbody>\r\n<tr>\r\n<td height=3D"30" style=3D"height:45px;padding:0;margin:0;line-height:1px;f=\r\nont-size:1px;"></td>\r\n</tr>\r\n<tr>\r\n<td align=3D"left" class=3D"display" style=3D"padding:0;margin:0;line-heigh=\r\nt:1px;font-size:1px;font-family:''Helvetica Neue'', Helvetica, Arial, sans-se=\r\nrif;font-size:22px;line-height:30px;font-weight:300;-webkit-font-smoothing:=\r\nantialiased;-webkit-text-size-adjust:none;text-align:left;color:#292f33;"> =\r\nGetting a lot of password reset emails? </td>\r\n</tr>\r\n<tr>\r\n<td height=3D"13" style=3D"height:13px;padding:0;margin:0;line-height:1px;f=\r\nont-size:1px;"></td>\r\n</tr>\r\n<tr>\r\n<td align=3D"left" class=3D"body-text" style=3D"padding:0;margin:0;line-hei=\r\nght:1px;font-size:1px;font-family:''Helvetica Neue'', Helvetica, Arial, sans-=\r\nserif;font-size:16px;line-height:22px;font-weight:400;-webkit-font-smoothin=\r\ng:antialiased;-webkit-text-size-adjust:none;text-align:left;color:#292f33;"=\r\n> You can change your <a href=3D"https://twitter.com/i/redirect?url=3Dhttps=\r\n%3A%2F%2Ftwitter.com%2Fsettings%2Fsecurity%3Fcn%3DcGFzc3dvcmRfcmVzZXRfdjI%2=\r\n53D&amp;t=3D1&amp;cn=3DcGFzc3dvcmRfcmVzZXRfdjI%3D&amp;sig=3Dfec4f754288bb20=\r\n4f79dd6ce521932112cd9675c&amp;iid=3Dec1152432cca4dbfa65e0f22b61234fc&amp;ui=\r\nd=3D326614242&amp;nid=3D248+1362" style=3D"text-decoration:none;border-styl=\r\ne:none;border:0;padding:0;margin:0;border:none;text-decoration:none;font-we=\r\night:400;color:#55acee;">account settings</a> to require personal informati=\r\non to reset your password. </td>\r\n</tr>\r\n<tr>\r\n<td height=3D"35" style=3D"height:35px;padding:0;margin:0;line-height:1px;f=\r\nont-size:1px;"></td>\r\n</tr>\r\n</tbody>\r\n</table> </td>\r\n<td width=3D"50" style=3D"width:50px;padding:0;margin:0;line-height:1px;fon=\r\nt-size:1px;" class=3D"margins"></td>\r\n</tr>\r\n<tr>\r\n<td width=3D"50" style=3D"width:50px;padding:0;margin:0;line-height:1px;fon=\r\nt-size:1px;" class=3D"edu-margins"></td>\r\n<td align=3D"center" style=3D"padding:0;margin:0;line-height:1px;font-size:=\r\n1px;">\r\n<table width=3D"100%" align=3D"center" cellpadding=3D"0" cellspacing=3D"0" =\r\nborder=3D"0" class=3D"edu-module" bgcolor=3D"#f5f8fa" style=3D"padding:0;ma=\r\nrgin:0;line-height:1px;font-size:1px;background-color:#f5f8fa;border-radius=\r\n:5px;-webkit-border-radius:5px;-moz-border-radius:5px;">\r\n<tbody>\r\n<tr>\r\n<td width=3D"35" class=3D"edu-collapse" style=3D"padding:0;margin:0;line-he=\r\night:1px;font-size:1px;"></td>\r\n<td align=3D"left" style=3D"padding:0;margin:0;line-height:1px;font-size:1p=\r\nx;">\r\n<table border=3D"0" cellpadding=3D"0" cellspacing=3D"0" width=3D"100%" styl=\r\ne=3D"padding:0;margin:0;line-height:1px;font-size:1px;">\r\n<tbody>\r\n<tr>\r\n<td height=3D"25" class=3D"mobile-height" style=3D"padding:0;margin:0;line-=\r\nheight:1px;font-size:1px;"></td>\r\n</tr>\r\n<tr>\r\n<td class=3D"edu-header" style=3D"padding:0;margin:0;line-height:1px;font-s=\r\nize:1px;font-family:''Helvetica Neue'', Helvetica, Arial, sans-serif;font-siz=\r\ne:16px;line-height:22px;-webkit-font-smoothing:antialiased;-webkit-text-siz=\r\ne-adjust:none;text-align:left;color:#aab8c2;"> <strong>How do I know this a=\r\nn is from Twitter?</strong> </td>\r\n</tr>\r\n<tr>\r\n<td colspan=3D"3" height=3D"7" style=3D"padding:0;margin:0;line-height:1px;=\r\nfont-size:1px;"></td>\r\n</tr>\r\n<tr>\r\n<td class=3D"edu-text" style=3D"padding:0;margin:0;line-height:1px;font-siz=\r\ne:1px;font-family:''Helvetica Neue'', Helvetica, Arial, sans-serif;font-size:=\r\n14px;line-height:19px;font-weight:400;-webkit-font-smoothing:antialiased;-w=\r\nebkit-text-size-adjust:none;text-align:left;color:#aab8c2;"> Links in this =\r\nemail will start with =E2=80=9C<span class=3D"no-link"><a href=3D"#" style=\r\n=3D"text-decoration:none;border-style:none;border:0;padding:0;margin:0;colo=\r\nr:#aab8c2 !important;text-decoration:none;">https://</a></span>=E2=80=9D an=\r\nd contain =E2=80=9C<span class=3D"no-link"><a href=3D"https://twitter.com/i=\r\n/redirect?url=3Dhttps%3A%2F%2Ftwitter.com%2F%3Frefsrc%3Demail&amp;t=3D1&amp=\r\n;cn=3DcGFzc3dvcmRfcmVzZXRfdjI%3D&amp;sig=3D2a8f140bf032a59764dfc68ac20559dc=\r\n8965ea5d&amp;iid=3Dec1152432cca4dbfa65e0f22b61234fc&amp;uid=3D326614242&amp=\r\n;nid=3D248+1554" style=3D"text-decoration:none;border-style:none;border:0;p=\r\nadding:0;margin:0;color:#aab8c2 !important;text-decoration:none;">twitter.c=\r\nom</a></span>.=E2=80=9D Your browser will also display a padlock icon to le=\r\nt you know a site is secure. </td>\r\n</tr>\r\n<tr>\r\n<td height=3D"30" style=3D"padding:0;margin:0;line-height:1px;font-size:1px=\r\n;"></td>\r\n</tr>\r\n</tbody>\r\n</table> </td>\r\n<td width=3D"35" class=3D"edu-collapse" style=3D"padding:0;margin:0;line-he=\r\night:1px;font-size:1px;"></td>\r\n</tr>\r\n</tbody>\r\n</table> </td>\r\n<td width=3D"50" style=3D"width:50px;padding:0;margin:0;line-height:1px;fon=\r\nt-size:1px;" class=3D"edu-margins"></td>\r\n</tr>\r\n<tr>\r\n<td colspan=3D"3" height=3D"50" class=3D"edu-space" style=3D"padding:0;marg=\r\nin:0;line-height:1px;font-size:1px;"></td>\r\n</tr>\r\n</tbody>\r\n</table>\r\n<!--//////////////////////////////////////////////-->\r\n<table class=3D"collapse" id=3D"footer" align=3D"center" width=3D"500" styl=\r\ne=3D"width:500px;background-color:#ffffff;padding:0;margin:0;line-height:1p=\r\nx;font-size:1px;" cellpadding=3D"0" cellspacing=3D"0" border=3D"0">\r\n<tbody>\r\n<tr>\r\n<td height=3D"1" style=3D"line-height:1px;display:block;height:1px;backgrou=\r\nnd-color:#e1e8ed;padding:0;margin:0;line-height:1px;font-size:1px;"></td>\r\n</tr>\r\n<tr>\r\n<td height=3D"20" style=3D"height:20;padding:0;margin:0;line-height:1px;fon=\r\nt-size:1px;"></td>\r\n</tr>\r\n<tr>\r\n<td align=3D"center" style=3D"padding:0;margin:0;line-height:1px;font-size:=\r\n1px;"> <span class=3D"footer_type" style=3D"font-family:''Helvetica Neue Lig=\r\nht'', Helvetica, Arial, sans-serif;-webkit-font-smoothing:antialiased;color:=\r\n#8899a6;font-size:12px;padding:0px;margin:0px;font-weight:normal;line-heigh=\r\nt:12px;"> <a href=3D"https://support.twitter.com/articles/14663" class=3D"f=\r\nooter_link" style=3D"text-decoration:none;border-style:none;border:0;paddin=\r\ng:0;margin:0;font-family:''Helvetica Neue Light'', Helvetica, Arial, sans-ser=\r\nif;-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:none;color:#=\r\n55acee;font-size:12px;padding:0px;margin:0px;font-weight:600;line-height:12=\r\npx;">Help</a> &nbsp;|&nbsp; <a href=3D"https://twitter.com/i/redirect?url=\r\n=3Dhttps%3A%2F%2Ftwitter.com%2Faccount%2Fnot_my_account%2F326614242%2F57G3G=\r\n-GD53C-144043%3Fut%3D1%26cn%3DcGFzc3dvcmRfcmVzZXRfdjI%253D&amp;t=3D1&amp;cn=\r\n=3DcGFzc3dvcmRfcmVzZXRfdjI%3D&amp;sig=3Da81d15bd1b4ce55d16c99aed4848c106840=\r\ne3341&amp;iid=3Dec1152432cca4dbfa65e0f22b61234fc&amp;uid=3D326614242&amp;ni=\r\nd=3D248+25" class=3D"footer_link" style=3D"text-decoration:none;border-styl=\r\ne:none;border:0;padding:0;margin:0;font-family:''Helvetica Neue Light'', Helv=\r\netica, Arial, sans-serif;-webkit-font-smoothing:antialiased;-webkit-text-si=\r\nze-adjust:none;color:#55acee;font-size:12px;padding:0px;margin:0px;font-wei=\r\nght:600;line-height:12px;">Not my account</a> &nbsp;|&nbsp; <a href=3D"http=\r\ns://twitter.com/i/redirect?url=3Dhttps%3A%2F%2Fsupport.twitter.com%2Farticl=\r\nes%2F204820-fake-twitter-emails&amp;t=3D1&amp;cn=3DcGFzc3dvcmRfcmVzZXRfdjI%=\r\n3D&amp;sig=3D6cf29f75e19b2442eb7197459d3ce0e2354f7d50&amp;iid=3Dec1152432cc=\r\na4dbfa65e0f22b61234fc&amp;uid=3D326614242&amp;nid=3D248+1555" class=3D"foot=\r\ner_link" style=3D"text-decoration:none;border-style:none;border:0;padding:0=\r\n;margin:0;font-family:''Helvetica Neue Light'', Helvetica, Arial, sans-serif;=\r\n-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:none;color:#55a=\r\ncee;font-size:12px;padding:0px;margin:0px;font-weight:600;line-height:12px;=\r\n">Email security tips</a> </span> </td>\r\n</tr>\r\n<tr>\r\n<td height=3D"10" style=3D"height:10px;line-height:1px;font-size:1px;paddin=\r\ng:0;margin:0;line-height:1px;font-size:1px;"></td>\r\n</tr>\r\n<tr>\r\n<td align=3D"center" style=3D"padding:0;margin:0;line-height:1px;font-size:=\r\n1px;"> <span class=3D"address"> <a href=3D"" style=3D"text-decoration:none;=\r\nborder-style:none;border:0;padding:0;margin:0;font-family:''Helvetica Neue L=\r\night'', Helvetica, Arial, sans-serif;-webkit-font-smoothing:antialiased;colo=\r\nr:#8899a6;font-size:12px;padding:0px;margin:0px;font-weight:normal;line-hei=\r\nght:12px;cursor:default;">Twitter, Inc. 1355 Market Street, Suite 900 San F=\r\nrancisco, CA 94103</a> </span> </td>\r\n</tr>\r\n<tr>\r\n<td height=3D"26" style=3D"height:26;padding:0;margin:0;line-height:1px;fon=\r\nt-size:1px;"></td>\r\n</tr>\r\n</tbody>\r\n</table>\r\n<!--//////////////////////////////////////////////--> </td>\r\n</tr>\r\n</tbody>\r\n</table>\r\n</body>\r\n</html>', 1, 'Body Email Forgot Password', 'admin', '127.0.0.1', '2015-10-31 19:02:05', NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `sec_role`
--

CREATE TABLE IF NOT EXISTS `sec_role` (
`role_id` bigint(20) NOT NULL,
  `role_code` varchar(50) NOT NULL,
  `role_status` smallint(6) NOT NULL,
  `role_remarks` varchar(255) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `sec_role`
--

INSERT INTO `sec_role` (`role_id`, `role_code`, `role_status`, `role_remarks`) VALUES
(1, 'ROLE_SALESMAN', 1, 'Role Salesman'),
(2, 'ROLE_SALESSUPERVISOR', 1, 'Role Sales Supervisor'),
(3, 'ROLE_SALESMANAGER', 1, 'Role Sales Manager'),
(4, 'ROLE_ADMIN', 1, 'Role Admin'),
(5, 'ROLE_ANONYMOUS_SECURE', 0, 'Role Anonymous Secure');

-- --------------------------------------------------------

--
-- Table structure for table `sec_user`
--

CREATE TABLE IF NOT EXISTS `sec_user` (
`user_id` bigint(20) NOT NULL,
  `user_username` varchar(20) NOT NULL,
  `user_password` varchar(255) NOT NULL,
  `user_enabled` smallint(6) NOT NULL,
  `user_expired_date` timestamp NULL DEFAULT NULL,
  `user_non_locked` smallint(6) DEFAULT NULL,
  `user_auth` varchar(255) DEFAULT NULL,
  `user_status` smallint(6) NOT NULL,
  `user_remarks` varchar(255) DEFAULT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `sec_user`
--

INSERT INTO `sec_user` (`user_id`, `user_username`, `user_password`, `user_enabled`, `user_expired_date`, `user_non_locked`, `user_auth`, `user_status`, `user_remarks`, `role_id`) VALUES
(1, 'ridla', 'G7uXj51dkMPFRswYwNfuLxJ1x0w=', 1, NULL, NULL, NULL, 1, NULL, 1),
(2, 'bangkit', '6pzG9pirdnShIOxMaYgSexDkjiE=', 1, NULL, NULL, NULL, 1, NULL, 2),
(3, 'fauzi', 'keOitDilCH81MHUr2ftlRvgUse4=', 1, NULL, NULL, NULL, 1, NULL, 3),
(4, 'admin', 'M6cURmiwEwLW9jiBeYPiMq21DHY=', 1, NULL, NULL, NULL, 1, NULL, 4);

-- --------------------------------------------------------

--
-- Table structure for table `vcs_user`
--

CREATE TABLE IF NOT EXISTS `vcs_user` (
  `vcs_user_id` int(11) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `vcs_user_version` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `mst_menu`
--
ALTER TABLE `mst_menu`
 ADD PRIMARY KEY (`menu_id`), ADD UNIQUE KEY `menu_code` (`menu_code`), ADD UNIQUE KEY `menu_code_2` (`menu_code`);

--
-- Indexes for table `mst_menu_role`
--
ALTER TABLE `mst_menu_role`
 ADD PRIMARY KEY (`menu_role_id`);

--
-- Indexes for table `mst_profile`
--
ALTER TABLE `mst_profile`
 ADD PRIMARY KEY (`profile_id`), ADD UNIQUE KEY `profile_phone` (`profile_phone`), ADD UNIQUE KEY `profile_email` (`profile_email`,`profile_phone`);

--
-- Indexes for table `mst_system_parameter`
--
ALTER TABLE `mst_system_parameter`
 ADD PRIMARY KEY (`system_parameter_id`), ADD UNIQUE KEY `system_parameter_code` (`system_parameter_code`);

--
-- Indexes for table `sec_role`
--
ALTER TABLE `sec_role`
 ADD PRIMARY KEY (`role_id`), ADD UNIQUE KEY `role_code` (`role_code`);

--
-- Indexes for table `sec_user`
--
ALTER TABLE `sec_user`
 ADD PRIMARY KEY (`user_id`), ADD UNIQUE KEY `user_username` (`user_username`);

--
-- Indexes for table `vcs_user`
--
ALTER TABLE `vcs_user`
 ADD PRIMARY KEY (`vcs_user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `mst_menu`
--
ALTER TABLE `mst_menu`
MODIFY `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=27;
--
-- AUTO_INCREMENT for table `mst_menu_role`
--
ALTER TABLE `mst_menu_role`
MODIFY `menu_role_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=43;
--
-- AUTO_INCREMENT for table `mst_system_parameter`
--
ALTER TABLE `mst_system_parameter`
MODIFY `system_parameter_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `sec_role`
--
ALTER TABLE `sec_role`
MODIFY `role_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `sec_user`
--
ALTER TABLE `sec_user`
MODIFY `user_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
