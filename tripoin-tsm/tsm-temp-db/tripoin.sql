-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 01, 2015 at 04:21 PM
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
(1, 'ridla.fadilah@tripoin.co.id', 'Ridla Fadilah', 'MALE', 'Bandung', '1990-12-27', 'Tangerang', '021234567891', '-', 1, '081234567891', '<font face="Courier New">This is Me</font>', '0d0bf53a-0d24-4290-b29b-9190ae5ccf0f', '2015-11-02 13:15:09', 'admin', '127.0.0.1', '2015-10-28 03:57:43', NULL, 'ridla', '127.0.0.1', '2015-10-31 08:00:55', 'Computer | Windows | Chrome'),
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
(2, 'TRIPOIN.EMAIL.FORGOT.PASSWORD.BODY.MESSAGE', '<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd"> <html>  	<head> 	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" /> 	<meta name="apple-mobile-web-app-capable" content="yes" /> 	<style type="text/css"> 	@media only screen and (max-width: 320px) { 	table[class="edu-module"]{ 	border-radius: 0px !important; 	-webkit-border-radius: 0px !important; 	-moz-border-radius: 0px !important; 	} 	td[class="edu-margins"]{ 	background-color: #f5f8fa; 	} 	td[class="edu-collapse"]{ 	width: 0px !important; 	} 	td[class="edu-space"]{ 	height: 10px !important; 	background-color: #f5f8fa; 	} 	td[class="mobile-height"]{ 	height: 30px !important; 	} 	} 	@media only screen and (max-width: 420px) { 	span[class="address"] a { 	line-height:18px !important; 	} 	td[class="margins"]{ 	width:18px !important; 	} 	td[class="edu-margins"]{ 	width:18px !important; 	} 	td[class="logo_space"]{ 	height:12px !important; 				} 			}  			@media only screen and (max-width: 480px) { 				table[class="collapse"]{ 					width:100% !important; 				} 				table[class="edu-module"]{ 					width:100% !important; 				} 				span[class="address"]{ 					display:block !important; 					width:240px !important; 				} 				td[class="cut"]{ 					display:none !important; 				} 			} 		</style> 	</head> 	<body bgcolor="#FAFAFA" style="margin:0;padding:0;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;"> 		<table cellpadding="0" cellspacing="0" border="0" width="100%" bgcolor="#FAFAFA" style="background-color:#FAFAFA;padding:0;margin:0;line-height:1px;font-size:1px;" class="body_wrapper"> 			<tbody> 				<tr> 					<td align="center" style="padding:0;margin:0;line-height:1px;font-size:1px;"> 						<table class="collapse" id="header" align="center" width="650" style="width: 650px;padding:0;margin:0;line-height:1px;font-size:1px;" bgcolor="#ffffff" cellpadding="0" cellspacing="0" border="0"> 							<tbody> 								<tr> 									<td style="min-width: 650px;height:1px;padding:0;margin:0;line-height:1px;font-size:1px;" class="cut"></td> 								</tr> 							</tbody> 						</table>  					</td> 				</tr> 				<tr> 					<td align="center" style="padding:0;margin:0;line-height:1px;font-size:1px;"> 					<!--///////////////////header///////////////////////////--> 						<table class="collapse" id="header" align="center" width="650" style="width:650px;background-color:#ffffff;padding:0;margin:0;line-height:1px;font-size:1px;" bgcolor="#ffffff" cellpadding="0" cellspacing="0" border="0"> 							<tbody> 								<tr> 									<td height="15" style="height:15px;padding:0;margin:0;line-height:1px;font-size:1px;" class="logo_space"> &nbsp; </td> 								</tr> 								<tr> 									<td style="padding:0;margin:0;line-height:1px;font-size:1px;"> 										<table cellpadding="0" cellspacing="0" border="0" align="left" style="padding:0;margin:0;line-height:1px;font-size:1px;"> 											<tbody> 												<tr align="left"> 													<td align="left" width="15" style="width:15px;padding:0;margin:0;line-height:1px;font-size:1px;"></td> 													<td align="left" width="28" style="padding:0;margin:0;line-height:1px;font-size:1px;"></td> 													<td align="left" width="10" style="width:10px;padding:0;margin:0;line-height:1px;font-size:1px;"></td> 													<td align="left" class="greeting" style="padding:0;margin:0;line-height:1px;font-size:1px;font-family:''Helvetica Neue Light'', Helvetica, Arial, sans-serif;-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:none;color:#66757f;font-size:16px;padding:0px;margin:0px;font-weight:300;line-height:100%;text-align:left;"> Dear ${TRIPOIN.CONTENT.FULLNAME}, </td> 													<td width="50" style="width:50px;padding:0;margin:0;line-height:1px;font-size:1px;" align="left"></td> 												</tr> 											</tbody> 										</table>  									</td> 								</tr> 								<tr> 								<td height="14" style="height:14px;padding:0;margin:0;line-height:1px;font-size:1px;" class="logo_space"> &nbsp; </td> 								</tr> 							</tbody> 						</table> 						<!--////////////////////border//////////////////////////--> 						<table class="collapse" align="center" width="650" style="width:650px;background-color:#ffffff;padding:0;margin:0;line-height:1px;font-size:1px;" cellpadding="0" cellspacing="0" border="0"> 							<tbody> 								<tr id="border"> 									<td colspan="2" height="1" style="line-height:1px;display:block;height:1px;background-color:#FAFAFA;padding:0;margin:0;line-height:1px;font-size:1px;"></td> 								</tr> 							</tbody> 						</table> 						<!--//////////////////////////////////////////////--> 						<table class="collapse" align="center" width="650" style="width:650px;background-color:#ffffff;padding:0;margin:0;line-height:1px;font-size:1px;" cellpadding="0" cellspacing="0" border="0"> 							<tbody> 								<tr> 								<td width="50" style="width:50px;padding:0;margin:0;line-height:1px;font-size:1px;" class="margins"></td> 								<td align="center" style="padding:0;margin:0;line-height:1px;font-size:1px;"> 									<table width="100%" align="center" cellpadding="0" cellspacing="0" border="0" class="collapse" style="padding:0;margin:0;line-height:1px;font-size:1px;"> 										<tbody> 										<tr> 											<td height="30" style="height:45px;padding:0;margin:0;line-height:1px;font-size:1px;"></td> 										</tr> 										<tr> 											<td align="left" class="display" style="padding:0;margin:0;line-height:1px;font-size:1px;font-family:''Helvetica Neue'', Helvetica, Arial, sans-serif;font-size:22px;line-height:30px;font-weight:300;-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:none;text-align:left;color:#292f33;"> We received a request to reset the password for your account. </td> 										</tr> 										<tr> 											<td height="30" style="height:30px;padding:0;margin:0;line-height:1px;font-size:1px;"></td> 										</tr> 										<tr> 											<td align="left" class="body-text" style="padding:0;margin:0;line-height:1px;font-size:1px;font-family:''Helvetica Neue'', Helvetica, Arial, sans-serif;font-size:16px;line-height:22px;font-weight:400;-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:none;text-align:left;color:#292f33;"> If you requested a reset for username <span style="text-decoration:none;border-style:none;border:0;padding:0;margin:0;border:none;text-decoration:none;font-weight:400;color:#1879DB;">${TRIPOIN.CONTENT.USERNAME}</span>, click the button below. If you didn''t make this request, please ignore this email. </td> 										</tr> 										<tr> 											<td height="25" style="height:25px;padding:0;margin:0;line-height:1px;font-size:1px;"></td> 										</tr> 										<!--*********** button ************--> 										<tr> 											<td align="left" style="padding:0;margin:0;line-height:1px;font-size:1px;"> 												<table border="0" cellspacing="0" cellpadding="0" style="padding:0;margin:0;line-height:1px;font-size:1px;"> 													<tbody> 														<tr> 															<td style="padding:0;margin:0;line-height:1px;font-size:1px;"> 																<!-- Tap, click, press, push the button --> 																<table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding:0;margin:0;line-height:1px;font-size:1px;"> 																	<tbody> 																		<tr> 																			<td style="padding:0;margin:0;line-height:1px;font-size:1px;"> 																				<table border="0" cellspacing="0" cellpadding="0" style="padding:0;margin:0;line-height:1px;font-size:1px;"> 																					<tbody> 																						<tr> 																							<td align="center" class="bulletproof-btn-1" bgcolor="#1879DB" style="padding:0;margin:0;line-height:1px;font-size:1px;-webkit-border-radius:4px;-moz-border-radius:4px;border-radius:4px;line-height:18px;"> 																								<a href="${TRIPOIN.CONTENT.URL}" target="_blank" class="bulletproof-btn-2" style="text-decoration:none;border-style:none;border:0;padding:0;margin:0;font-family:''Helvetica Neue'', Helvetica, Arial, sans-serif;font-size:16px;line-height:22px;font-weight:650;-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:none;color:#ffffff;text-align:center;text-decoration:none;-webkit-border-radius:4px;-moz-border-radius:4px;border-radius:4px;padding:11px 30px;border:1px solid #1879DB;display:inline-block;"> 																									<strong>Reset password</strong> 																								</a> 																							</td> 																						</tr> 																					</tbody> 																				</table>  																			</td> 																		</tr> 																	</tbody> 																</table>  															</td> 														</tr> 													</tbody> 												</table>  											</td> 										</tr> 										<!--*********** end button ************--> 										<tr> 											<td height="55" style="height:55px;padding:0;margin:0;line-height:1px;font-size:1px;"></td> 										</tr> 										</tbody> 									</table>  								</td> 								<td width="50" style="width:50px;padding:0;margin:0;line-height:1px;font-size:1px;" class="margins"></td> 								</tr> 							</tbody> 						</table> 						<!--//////////////////////////////////////////////--> 						<table class="collapse" id="footer" align="center" width="650" style="width:650px;background-color:#ffffff;padding:0;margin:0;line-height:1px;font-size:1px;" cellpadding="0" cellspacing="0" border="0"> 							<tbody> 								<tr> 									<td height="1" style="line-height:1px;display:block;height:1px;background-color:#FAFAFA;padding:0;margin:0;line-height:1px;font-size:1px;"></td> 								</tr> 								<tr> 									<td height="20" style="height:20;padding:0;margin:0;line-height:1px;font-size:1px;"></td> 								</tr> 								<tr> 									<td align="center" style="padding:0;margin:0;line-height:1px;font-size:1px;"> <span class="footer_type" style="font-family:''Helvetica Neue Light'', Helvetica, Arial, sans-serif;-webkit-font-smoothing:antialiased;color:#8899a6;font-size:12px;padding:0px;margin:0px;font-weight:normal;line-height:12px;">  <a href="http://www.tripoin.co.id" class="footer_link" style="text-decoration:none;border-style:none;border:0;padding:0;margin:0;font-family:''Helvetica Neue Light'', Helvetica, Arial, sans-serif;-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:none;color:#1879DB;font-size:12px;padding:0px;margin:0px;font-weight:600;line-height:12px;">Tripoin, Inc.</a>  </span> </td> 								</tr> 								<tr> 									<td height="10" style="height:10px;line-height:1px;font-size:1px;padding:0;margin:0;line-height:1px;font-size:1px;"></td> 								</tr> 								<tr> 									<td align="center" style="padding:0;margin:0;line-height:1px;font-size:1px;"> <span class="address"> <a href="" style="text-decoration:none;border-style:none;border:0;padding:0;margin:0;font-family:''Helvetica Neue Light'', Helvetica, Arial, sans-serif;-webkit-font-smoothing:antialiased;color:#8899a6;font-size:12px;padding:0px;margin:0px;font-weight:normal;line-height:12px;cursor:default;">Indonesia, Jakarta</a> </span> </td> 								</tr> 								<tr> 									<td height="26" style="height:26;padding:0;margin:0;line-height:1px;font-size:1px;"></td> 								</tr> 							</tbody> 						</table>					 					</td> 				</tr> 			</tbody> 		</table> 	</body> </html>', 1, 'Body Email Forgot Password', 'admin', '127.0.0.1', '2015-10-31 19:02:05', NULL, NULL, NULL, NULL, NULL);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `sec_user`
--

INSERT INTO `sec_user` (`user_id`, `user_username`, `user_password`, `user_enabled`, `user_expired_date`, `user_non_locked`, `user_auth`, `user_status`, `user_remarks`, `role_id`) VALUES
(1, 'ridla', 'G7uXj51dkMPFRswYwNfuLxJ1x0w=', 1, NULL, NULL, NULL, 1, NULL, 1),
(2, 'bangkit', '6pzG9pirdnShIOxMaYgSexDkjiE=', 1, NULL, NULL, NULL, 1, NULL, 2),
(3, 'fauzi', 'keOitDilCH81MHUr2ftlRvgUse4=', 1, NULL, NULL, NULL, 1, NULL, 3),
(4, 'admin', 'M6cURmiwEwLW9jiBeYPiMq21DHY=', 1, NULL, NULL, NULL, 1, NULL, 4),
(5, 'tripoin.app.web', 'g/vPwZ+XHlXlZCIIYgK28SUyX+Y=', 1, NULL, NULL, NULL, 1, NULL, 5),
(6, 'tripoin.app.android', 'g/vPwZ+XHlXlZCIIYgK28SUyX+Y=', 1, NULL, NULL, NULL, 1, NULL, 5);

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
MODIFY `user_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
