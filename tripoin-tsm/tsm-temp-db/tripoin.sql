-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 08, 2016 at 08:36 PM
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
-- Table structure for table `geo_user_route`
--

CREATE TABLE IF NOT EXISTS `geo_user_route` (
`user_route_id` bigint(20) NOT NULL,
  `user_route_lat` double(30,25) NOT NULL,
  `user_route_lon` double(30,25) NOT NULL,
  `user_route_center` smallint(5) NOT NULL DEFAULT '0',
  `user_route_zoom` int(10) NOT NULL DEFAULT '10',
  `user_route_drag` smallint(5) NOT NULL DEFAULT '0',
  `user_route_marker` varchar(255) DEFAULT NULL,
  `user_route_caption` varchar(255) DEFAULT NULL,
  `user_route_icon` varchar(255) DEFAULT NULL,
  `employee_id` bigint(20) NOT NULL,
  `status` smallint(5) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `created_by` varchar(150) DEFAULT 'admin',
  `created_ip` varchar(150) DEFAULT '127.0.0.1',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_platform` varchar(255) DEFAULT NULL,
  `modified_by` varchar(150) DEFAULT NULL,
  `modified_ip` varchar(150) DEFAULT NULL,
  `modified_time` timestamp NULL DEFAULT NULL,
  `modified_platform` varchar(255) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `geo_user_route`
--

INSERT INTO `geo_user_route` (`user_route_id`, `user_route_lat`, `user_route_lon`, `user_route_center`, `user_route_zoom`, `user_route_drag`, `user_route_marker`, `user_route_caption`, `user_route_icon`, `employee_id`, `status`, `remarks`, `created_by`, `created_ip`, `created_time`, `created_platform`, `modified_by`, `modified_ip`, `modified_time`, `modified_platform`) VALUES
(1, -6.2666000000000000000000000, 106.6598310000000000000000000, 0, 10, 0, 'Komplek Pondok Jagung BD 53', 'Prison', NULL, 3, NULL, NULL, 'admin', '127.0.0.1', '2015-11-23 10:00:00', NULL, NULL, NULL, NULL, NULL),
(2, -6.2977670000000000000000000, 106.6678370000000000000000000, 0, 10, 0, 'Jalan Kapten Subidjanto DJ', 'Kantin Belakang', NULL, 3, NULL, NULL, 'admin', '127.0.0.1', '2015-11-24 08:48:15', NULL, NULL, NULL, NULL, NULL),
(3, -6.2409321898086985000000000, 106.6284942626953100000000000, 0, 10, 0, 'Gading Serpong', 'Summarecon Mall Serpong', NULL, 3, NULL, NULL, 'admin', '127.0.0.1', '2015-11-24 08:49:21', NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `mst_area`
--

CREATE TABLE IF NOT EXISTS `mst_area` (
`area_id` bigint(20) NOT NULL,
  `area_code` varchar(150) NOT NULL,
  `area_name` varchar(255) NOT NULL,
  `status` smallint(5) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `created_by` varchar(150) DEFAULT 'admin',
  `created_ip` varchar(150) DEFAULT '127.0.0.1',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_platform` varchar(255) DEFAULT NULL,
  `modified_by` varchar(150) DEFAULT NULL,
  `modified_ip` varchar(150) DEFAULT NULL,
  `modified_time` timestamp NULL DEFAULT NULL,
  `modified_platform` varchar(255) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=37 ;

--
-- Dumping data for table `mst_area`
--

INSERT INTO `mst_area` (`area_id`, `area_code`, `area_name`, `status`, `remarks`, `created_by`, `created_ip`, `created_time`, `created_platform`, `modified_by`, `modified_ip`, `modified_time`, `modified_platform`) VALUES
(1, 'JAKARTA', 'JAKARTA', 1, '-', 'admin', '127.0.0.1', '2015-11-22 17:00:00', NULL, NULL, NULL, NULL, NULL),
(2, 'TANGERANG', 'TANGERANG', 1, '-', 'admin', '127.0.0.1', '2015-11-22 17:00:00', NULL, NULL, NULL, NULL, NULL),
(3, 'BANDUNG', 'BANDUNG', 1, '-', 'admin', '127.0.0.1', '2015-11-22 17:00:00', NULL, NULL, NULL, NULL, NULL),
(4, 'ACEH', 'ACEH', 1, NULL, 'admin', '127.0.0.1', '2016-02-20 15:03:52', NULL, NULL, NULL, NULL, NULL),
(5, 'SUMATERAUTARA', 'SUMATERA UTARA', 1, NULL, 'admin', '127.0.0.1', '2016-02-20 15:03:52', NULL, NULL, NULL, NULL, NULL),
(6, 'SUMATERABARAT', 'SUMATERA BARAT', 1, NULL, 'admin', '127.0.0.1', '2016-02-20 15:03:52', NULL, NULL, NULL, NULL, NULL),
(7, 'RIAU', 'RIAU', 1, NULL, 'admin', '127.0.0.1', '2016-02-20 15:03:52', NULL, NULL, NULL, NULL, NULL),
(8, 'JAMBI', 'JAMBI', 1, NULL, 'admin', '127.0.0.1', '2016-02-20 15:03:52', NULL, NULL, NULL, NULL, NULL),
(9, 'SUMATERASELATAN', 'SUMATERA SELATAN', 1, NULL, 'admin', '127.0.0.1', '2016-02-20 15:03:52', NULL, NULL, NULL, NULL, NULL),
(10, 'BENGKULU', 'BENGKULU', 1, NULL, 'admin', '127.0.0.1', '2016-02-20 15:03:52', NULL, NULL, NULL, NULL, NULL),
(11, 'LAMPUNG', 'LAMPUNG', 1, NULL, 'admin', '127.0.0.1', '2016-02-20 15:03:53', NULL, NULL, NULL, NULL, NULL),
(12, 'KEPULAUANBANGKABELITUNG', 'KEPULAUAN BANGKA BELITUNG', 1, NULL, 'admin', '127.0.0.1', '2016-02-20 15:03:53', NULL, NULL, NULL, NULL, NULL),
(13, 'KEPULAUANRIAU', 'KEPULAUAN RIAU', 1, NULL, 'admin', '127.0.0.1', '2016-02-20 15:03:53', NULL, NULL, NULL, NULL, NULL),
(14, 'DKIJAKARTA', 'DKI JAKARTA', 1, NULL, 'admin', '127.0.0.1', '2016-02-20 15:03:53', NULL, NULL, NULL, NULL, NULL),
(15, 'JAWABARAT', 'JAWA BARAT', 1, NULL, 'admin', '127.0.0.1', '2016-02-20 15:03:53', NULL, NULL, NULL, NULL, NULL),
(16, 'JAWATENGAH', 'JAWA TENGAH', 1, NULL, 'admin', '127.0.0.1', '2016-02-20 15:03:53', NULL, NULL, NULL, NULL, NULL),
(17, 'DIYOGYAKARTA', 'DI YOGYAKARTA', 1, NULL, 'admin', '127.0.0.1', '2016-02-20 15:03:53', NULL, NULL, NULL, NULL, NULL),
(18, 'JAWATIMUR', 'JAWA TIMUR', 1, NULL, 'admin', '127.0.0.1', '2016-02-20 15:03:53', NULL, NULL, NULL, NULL, NULL),
(19, 'BANTEN', 'BANTEN', 1, NULL, 'admin', '127.0.0.1', '2016-02-20 15:03:53', NULL, NULL, NULL, NULL, NULL),
(20, 'BALI', 'BALI', 1, NULL, 'admin', '127.0.0.1', '2016-02-20 15:03:53', NULL, NULL, NULL, NULL, NULL),
(21, 'NUSATENGGARABARAT', 'NUSA TENGGARA BARAT', 1, NULL, 'admin', '127.0.0.1', '2016-02-20 15:03:53', NULL, NULL, NULL, NULL, NULL),
(22, 'NUSATENGGARATIMUR', 'NUSA TENGGARA TIMUR', 1, NULL, 'admin', '127.0.0.1', '2016-02-20 15:03:54', NULL, NULL, NULL, NULL, NULL),
(23, 'KALIMANTANBARAT', 'KALIMANTAN BARAT', 1, NULL, 'admin', '127.0.0.1', '2016-02-20 15:03:54', NULL, NULL, NULL, NULL, NULL),
(24, 'KALIMANTANTENGAH', 'KALIMANTAN TENGAH', 1, NULL, 'admin', '127.0.0.1', '2016-02-20 15:03:54', NULL, NULL, NULL, NULL, NULL),
(25, 'KALIMANTANSELATAN', 'KALIMANTAN SELATAN', 1, NULL, 'admin', '127.0.0.1', '2016-02-20 15:03:54', NULL, NULL, NULL, NULL, NULL),
(26, 'KALIMANTANTIMUR', 'KALIMANTAN TIMUR', 1, NULL, 'admin', '127.0.0.1', '2016-02-20 15:03:54', NULL, NULL, NULL, NULL, NULL),
(27, 'SULAWESIUTARA', 'SULAWESI UTARA', 1, NULL, 'admin', '127.0.0.1', '2016-02-20 15:03:54', NULL, NULL, NULL, NULL, NULL),
(28, 'SULAWESITENGAH', 'SULAWESI TENGAH', 1, NULL, 'admin', '127.0.0.1', '2016-02-20 15:03:54', NULL, NULL, NULL, NULL, NULL),
(29, 'SULAWESISELATAN', 'SULAWESI SELATAN', 1, NULL, 'admin', '127.0.0.1', '2016-02-20 15:03:54', NULL, NULL, NULL, NULL, NULL),
(30, 'SULAWESITENGGARA', 'SULAWESI TENGGARA', 1, NULL, 'admin', '127.0.0.1', '2016-02-20 15:03:54', NULL, NULL, NULL, NULL, NULL),
(31, 'GORONTALO', 'GORONTALO', 1, NULL, 'admin', '127.0.0.1', '2016-02-20 15:03:54', NULL, NULL, NULL, NULL, NULL),
(32, 'SULAWESIBARAT', 'SULAWESI BARAT', 1, NULL, 'admin', '127.0.0.1', '2016-02-20 15:03:54', NULL, NULL, NULL, NULL, NULL),
(33, 'MALUKU', 'MALUKU', 1, NULL, 'admin', '127.0.0.1', '2016-02-20 15:03:54', NULL, NULL, NULL, NULL, NULL),
(34, 'MALUKUUTARA', 'MALUKU UTARA', 1, NULL, 'admin', '127.0.0.1', '2016-02-20 15:03:55', NULL, NULL, NULL, NULL, NULL),
(35, 'PAPUABARAT', 'PAPUA BARAT', 1, NULL, 'admin', '127.0.0.1', '2016-02-20 15:03:55', NULL, NULL, NULL, NULL, NULL),
(36, 'PAPUA', 'PAPUA', 1, NULL, 'admin', '127.0.0.1', '2016-02-20 15:03:55', NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `mst_bussiness_sector`
--

CREATE TABLE IF NOT EXISTS `mst_bussiness_sector` (
`bussiness_sector_id` bigint(20) NOT NULL,
  `bussiness_sector_code` varchar(150) NOT NULL,
  `bussiness_sector_name` varchar(255) NOT NULL,
  `bussiness_sector_description` text,
  `status` smallint(5) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `created_by` varchar(150) DEFAULT 'admin',
  `created_ip` varchar(150) DEFAULT '127.0.0.1',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_platform` varchar(255) DEFAULT NULL,
  `modified_by` varchar(150) DEFAULT NULL,
  `modified_ip` varchar(150) DEFAULT NULL,
  `modified_time` timestamp NULL DEFAULT NULL,
  `modified_platform` varchar(255) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `mst_bussiness_sector`
--

INSERT INTO `mst_bussiness_sector` (`bussiness_sector_id`, `bussiness_sector_code`, `bussiness_sector_name`, `bussiness_sector_description`, `status`, `remarks`, `created_by`, `created_ip`, `created_time`, `created_platform`, `modified_by`, `modified_ip`, `modified_time`, `modified_platform`) VALUES
(1, 'MINING', 'Mining', 'Mining', 1, 'Mining', 'admin', '127.0.0.1', '2016-02-28 06:02:32', NULL, NULL, NULL, NULL, NULL),
(2, 'OILANDGAS', 'Oil And Gas', 'Oil And Gas', 1, 'Oil And Gas', 'admin', '127.0.0.1', '2016-02-28 06:02:32', NULL, NULL, NULL, NULL, NULL),
(3, 'FORESTRY&AGRICULTURE', 'Forestry & Agriculture', 'Forestry & Agriculture', 1, 'Forestry & Agriculture', 'admin', '127.0.0.1', '2016-02-28 06:02:32', NULL, NULL, NULL, NULL, NULL),
(4, 'ONROANDLOGISTIC', 'On Road Logistic', 'On Road Logistic', 1, 'On Road Logistic', 'admin', '127.0.0.1', '2016-02-28 06:02:32', NULL, NULL, NULL, NULL, NULL),
(5, 'MUNICIPAL', 'Municipal', 'Municipal', 1, 'Municipal', 'admin', '127.0.0.1', '2016-02-28 06:02:32', NULL, NULL, NULL, NULL, NULL),
(6, 'CHEMICAL', 'Chemical', 'Chemical', 1, 'Chemical', 'admin', '127.0.0.1', '2016-02-28 06:03:56', NULL, NULL, NULL, NULL, NULL),
(7, 'OTHERS', 'Others', 'Others', 1, 'Others', 'admin', '127.0.0.1', '2016-02-28 06:03:56', NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `mst_country`
--

CREATE TABLE IF NOT EXISTS `mst_country` (
`country_id` bigint(20) NOT NULL,
  `country_code` varchar(150) NOT NULL,
  `country_name` varchar(255) NOT NULL,
  `status` smallint(5) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `created_by` varchar(150) DEFAULT 'admin',
  `created_ip` varchar(150) DEFAULT '127.0.0.1',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_platform` varchar(255) DEFAULT NULL,
  `modified_by` varchar(150) DEFAULT NULL,
  `modified_ip` varchar(150) DEFAULT NULL,
  `modified_time` timestamp NULL DEFAULT NULL,
  `modified_platform` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `mst_customer`
--

CREATE TABLE IF NOT EXISTS `mst_customer` (
`customer_id` bigint(20) NOT NULL,
  `customer_code` varchar(150) NOT NULL,
  `customer_company_name` varchar(255) NOT NULL,
  `customer_company_address1` text NOT NULL,
  `customer_company_telpfax1` varchar(150) NOT NULL,
  `customer_company_address2` text,
  `customer_company_telpfax2` varchar(150) DEFAULT NULL,
  `customer_company_npwp` varchar(150) NOT NULL,
  `customer_company_siup` varchar(150) NOT NULL,
  `customer_employee_office` bigint(10) DEFAULT NULL,
  `customer_employee_project` bigint(10) DEFAULT NULL,
  `customer_recommendation` text,
  `customer_priority` smallint(5) DEFAULT NULL,
  `customer_type_id` bigint(20) NOT NULL,
  `bussiness_sector_id` bigint(20) DEFAULT NULL,
  `area_id` bigint(20) DEFAULT NULL,
  `finance_id` bigint(20) DEFAULT NULL,
  `status` smallint(5) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `created_by` varchar(150) DEFAULT 'admin',
  `created_ip` varchar(150) DEFAULT '127.0.0.1',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_platform` varchar(255) DEFAULT NULL,
  `modified_by` varchar(150) DEFAULT NULL,
  `modified_ip` varchar(150) DEFAULT NULL,
  `modified_time` timestamp NULL DEFAULT NULL,
  `modified_platform` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `mst_customer_financing`
--

CREATE TABLE IF NOT EXISTS `mst_customer_financing` (
`customer_financing_id` bigint(20) NOT NULL,
  `customer_id` bigint(20) NOT NULL,
  `financing_id` bigint(20) NOT NULL,
  `customer_financing_value` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `mst_customer_operational`
--

CREATE TABLE IF NOT EXISTS `mst_customer_operational` (
`customer_operational_id` bigint(20) NOT NULL,
  `customer_id` bigint(20) NOT NULL,
  `unit_populate_id` bigint(20) NOT NULL,
  `total` bigint(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `mst_customer_pic`
--

CREATE TABLE IF NOT EXISTS `mst_customer_pic` (
`customer_pic_id` bigint(20) NOT NULL,
  `pic_id` bigint(20) NOT NULL,
  `customer_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `mst_customer_project`
--

CREATE TABLE IF NOT EXISTS `mst_customer_project` (
`customer_project_id` bigint(20) NOT NULL,
  `customer_id` bigint(20) NOT NULL,
  `project_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `mst_customer_site`
--

CREATE TABLE IF NOT EXISTS `mst_customer_site` (
`customer_site_id` bigint(20) NOT NULL,
  `customer_id` bigint(20) NOT NULL,
  `site_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `mst_customer_type`
--

CREATE TABLE IF NOT EXISTS `mst_customer_type` (
`customer_type_id` bigint(20) NOT NULL,
  `customer_type_code` varchar(150) NOT NULL,
  `customer_type_name` varchar(255) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `mst_customer_type`
--

INSERT INTO `mst_customer_type` (`customer_type_id`, `customer_type_code`, `customer_type_name`) VALUES
(1, 'NEWCUSTOMER', 'New Customer'),
(2, 'EXISTINGCUSTOMER', 'Existing Customer');

-- --------------------------------------------------------

--
-- Table structure for table `mst_employee`
--

CREATE TABLE IF NOT EXISTS `mst_employee` (
`employee_id` bigint(20) NOT NULL,
  `employee_code` varchar(150) NOT NULL,
  `employee_nik` varchar(150) NOT NULL,
  `profile_id` bigint(20) NOT NULL,
  `occupation_id` bigint(20) NOT NULL,
  `area_id` bigint(20) DEFAULT NULL,
  `employee_parent_id` bigint(20) DEFAULT NULL,
  `status` smallint(5) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `created_by` varchar(150) DEFAULT 'admin',
  `created_ip` varchar(150) DEFAULT '127.0.0.1',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_platform` varchar(255) DEFAULT NULL,
  `modified_by` varchar(150) DEFAULT NULL,
  `modified_ip` varchar(150) DEFAULT NULL,
  `modified_time` timestamp NULL DEFAULT NULL,
  `modified_platform` varchar(255) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `mst_employee`
--

INSERT INTO `mst_employee` (`employee_id`, `employee_code`, `employee_nik`, `profile_id`, `occupation_id`, `area_id`, `employee_parent_id`, `status`, `remarks`, `created_by`, `created_ip`, `created_time`, `created_platform`, `modified_by`, `modified_ip`, `modified_time`, `modified_platform`) VALUES
(1, 'TSM201511230001', 'TSM201511230001', 3, 3, NULL, NULL, 1, 'TSM201511230001', 'admin', '127.0.0.1', '2015-11-22 17:00:00', NULL, NULL, NULL, NULL, NULL),
(2, 'TSM201511240001', 'TSM201511240001', 2, 2, 1, 1, 1, 'TSM201511240001', 'admin', '127.0.0.1', '2015-11-23 17:00:00', NULL, NULL, NULL, NULL, NULL),
(3, 'TSM201511250001', 'TSM201511250001', 1, 1, NULL, 2, 1, 'TSM201511250001', 'admin', '127.0.0.1', '2015-11-24 17:00:00', NULL, 'admin', '127.0.0.1', '2016-02-22 12:36:56', 'Computer | Unknown Operating System | Unknown Browser');

-- --------------------------------------------------------

--
-- Table structure for table `mst_finance`
--

CREATE TABLE IF NOT EXISTS `mst_finance` (
`finance_id` bigint(20) NOT NULL,
  `finance_code` varchar(150) NOT NULL,
  `finance_average_gross` varchar(150) NOT NULL,
  `finance_credit_monthly` varchar(150) NOT NULL,
  `finance_no_rek` varchar(150) NOT NULL,
  `finance_bank_address` text NOT NULL,
  `finance_bank_telpfax` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `mst_financing`
--

CREATE TABLE IF NOT EXISTS `mst_financing` (
`financing_id` bigint(20) NOT NULL,
  `financing_code` varchar(150) NOT NULL,
  `financing_name` varchar(255) NOT NULL,
  `financing_description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `mst_financing`
--

INSERT INTO `mst_financing` (`financing_id`, `financing_code`, `financing_name`, `financing_description`) VALUES
(1, 'CSUL', 'CSUL (**USD/IDR)', 'CSUL (**USD/IDR)'),
(2, 'IBF', 'IBF (**USD/IDR)', 'IBF (**USD/IDR)'),
(3, 'ACC', 'ACC (**USD/IDR)', 'ACC (**USD/IDR)'),
(4, 'MPM', 'MPM (**USD/IDR)', 'MPM (**USD/IDR)'),
(5, 'SANFINANCE', 'SAN Finance (**USD/IDR)', 'SAN Finance (**USD/IDR)'),
(6, 'DANAMON', 'Danamon (**USD/IDR)', 'Danamon (**USD/IDR)'),
(7, 'MANDIRI', 'Mandiri (**USD/IDR)', 'Mandiri (**USD/IDR)'),
(8, 'OTHERS', 'Others', 'Others');

-- --------------------------------------------------------

--
-- Table structure for table `mst_fptengine`
--

CREATE TABLE IF NOT EXISTS `mst_fptengine` (
`fptengine_id` bigint(20) NOT NULL,
  `fptengine_code` varchar(150) NOT NULL,
  `fptengine_name` varchar(255) NOT NULL,
  `fptengine_detail` text,
  `fptengine_price` varchar(150) DEFAULT NULL,
  `fptengine_quantity` bigint(10) DEFAULT NULL,
  `status` smallint(5) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `created_by` varchar(150) DEFAULT 'admin',
  `created_ip` varchar(150) DEFAULT '127.0.0.1',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_platform` varchar(255) DEFAULT NULL,
  `modified_by` varchar(150) DEFAULT NULL,
  `modified_ip` varchar(150) DEFAULT NULL,
  `modified_time` timestamp NULL DEFAULT NULL,
  `modified_platform` varchar(255) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `mst_fptengine`
--

INSERT INTO `mst_fptengine` (`fptengine_id`, `fptengine_code`, `fptengine_name`, `fptengine_detail`, `fptengine_price`, `fptengine_quantity`, `status`, `remarks`, `created_by`, `created_ip`, `created_time`, `created_platform`, `modified_by`, `modified_ip`, `modified_time`, `modified_platform`) VALUES
(1, 'FPTENGINE', 'FPT Engine', 'The FPT Industrial range for power generation applications includes the F, N, and C series and has a wide range from 24 to 320 kW. In the range there also are several hybrid combinations, with various options available including a series of special products that can be adapted to satisfy even the most demanding of customers.', NULL, NULL, 1, 'FPT Engine', 'admin', '127.0.0.1', '2016-02-27 17:50:45', NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `mst_occupation`
--

CREATE TABLE IF NOT EXISTS `mst_occupation` (
`occupation_id` bigint(20) NOT NULL,
  `occupation_code` varchar(150) NOT NULL,
  `occupation_name` varchar(255) NOT NULL,
  `status` smallint(5) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `created_by` varchar(150) DEFAULT 'admin',
  `created_ip` varchar(150) DEFAULT '127.0.0.1',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_platform` varchar(255) DEFAULT NULL,
  `modified_by` varchar(150) DEFAULT NULL,
  `modified_ip` varchar(150) DEFAULT NULL,
  `modified_time` timestamp NULL DEFAULT NULL,
  `modified_platform` varchar(255) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `mst_occupation`
--

INSERT INTO `mst_occupation` (`occupation_id`, `occupation_code`, `occupation_name`, `status`, `remarks`, `created_by`, `created_ip`, `created_time`, `created_platform`, `modified_by`, `modified_ip`, `modified_time`, `modified_platform`) VALUES
(1, 'ROLE_SALESMAN', 'Salesman', 1, 'Salesman', 'admin', '127.0.0.1', '2015-11-22 17:00:00', NULL, NULL, NULL, NULL, NULL),
(2, 'ROLE_AREASALESMANAGER', 'Area Sales Manager', 1, 'Area Sales Manager', 'admin', '127.0.0.1', '2015-11-22 17:00:00', NULL, NULL, NULL, NULL, NULL),
(3, 'ROLE_NATIONALSALESMANAGER', 'National Sales Manager', 1, 'National Sales Manager', 'admin', '127.0.0.1', '2015-11-22 17:00:00', NULL, 'admin', '127.0.0.1', '2016-02-21 07:45:15', 'Computer | Mac OS X | Safari');

-- --------------------------------------------------------

--
-- Table structure for table `mst_pic`
--

CREATE TABLE IF NOT EXISTS `mst_pic` (
`pic_id` bigint(20) NOT NULL,
  `pic_code` varchar(150) NOT NULL,
  `pic_name` varchar(255) NOT NULL,
  `pic_occupation` varchar(255) NOT NULL,
  `pic_aged` smallint(5) DEFAULT NULL,
  `pic_telp` varchar(150) DEFAULT NULL,
  `pic_phone` varchar(150) NOT NULL,
  `pic_email` varchar(150) DEFAULT NULL,
  `status` smallint(5) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `created_by` varchar(150) DEFAULT 'admin',
  `created_ip` varchar(150) DEFAULT '127.0.0.1',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_platform` varchar(255) DEFAULT NULL,
  `modified_by` varchar(150) DEFAULT NULL,
  `modified_ip` varchar(150) DEFAULT NULL,
  `modified_time` timestamp NULL DEFAULT NULL,
  `modified_platform` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `mst_product`
--

CREATE TABLE IF NOT EXISTS `mst_product` (
`product_id` bigint(20) NOT NULL,
  `product_type_id` bigint(20) NOT NULL,
  `product_mapping_code` varchar(255) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Dumping data for table `mst_product`
--

INSERT INTO `mst_product` (`product_id`, `product_type_id`, `product_mapping_code`) VALUES
(1, 1, 'AD380T38H6X4'),
(2, 1, 'AD380T44W6x6'),
(3, 1, 'AD380T486x4'),
(4, 1, 'AD410T448x4'),
(5, 1, '44.424x4'),
(6, 1, '44.484x4'),
(7, 1, '64.54T6x4'),
(8, 1, '66.386x6'),
(9, 1, '66.44T6x6'),
(10, 1, '55S15DWH4x4'),
(11, 1, '55S15WH4x4'),
(12, 1, 'CabChassis6x4'),
(13, 1, 'PrimeMover6x4'),
(14, 1, 'Tipper6x4'),
(15, 2, 'FPTENGINE'),
(16, 3, 'ZF');

-- --------------------------------------------------------

--
-- Table structure for table `mst_product_type`
--

CREATE TABLE IF NOT EXISTS `mst_product_type` (
`product_type_id` bigint(20) NOT NULL,
  `product_type_code` varchar(150) NOT NULL,
  `product_type_name` varchar(255) NOT NULL,
  `status` smallint(5) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `created_by` varchar(150) DEFAULT 'admin',
  `created_ip` varchar(150) DEFAULT '127.0.0.1',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_platform` varchar(255) DEFAULT NULL,
  `modified_by` varchar(150) DEFAULT NULL,
  `modified_ip` varchar(150) DEFAULT NULL,
  `modified_time` timestamp NULL DEFAULT NULL,
  `modified_platform` varchar(255) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `mst_product_type`
--

INSERT INTO `mst_product_type` (`product_type_id`, `product_type_code`, `product_type_name`, `status`, `remarks`, `created_by`, `created_ip`, `created_time`, `created_platform`, `modified_by`, `modified_ip`, `modified_time`, `modified_platform`) VALUES
(1, 'TRUCK', 'Truck', 1, 'Truck', 'admin', '127.0.0.1', '2016-02-27 16:45:17', NULL, NULL, NULL, NULL, NULL),
(2, 'FPT', 'FPT Engine', 1, 'FPT Engine', 'admin', '127.0.0.1', '2016-02-27 16:45:17', NULL, NULL, NULL, NULL, NULL),
(3, 'OTHERS', 'Spare Parts', 1, 'Spare Parts', 'admin', '127.0.0.1', '2016-02-27 16:45:17', NULL, NULL, NULL, NULL, NULL);

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
  `profile_resources_uuid` varchar(255) NOT NULL,
  `profile_forgot_uuid` varchar(255) DEFAULT NULL,
  `profile_forgot_expired` timestamp NULL DEFAULT NULL,
  `status` smallint(5) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `created_by` varchar(150) DEFAULT 'admin',
  `created_ip` varchar(150) DEFAULT '127.0.0.1',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_platform` varchar(255) DEFAULT NULL,
  `modified_by` varchar(150) DEFAULT NULL,
  `modified_ip` varchar(150) DEFAULT NULL,
  `modified_time` timestamp NULL DEFAULT NULL,
  `modified_platform` varchar(255) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `mst_profile`
--

INSERT INTO `mst_profile` (`profile_id`, `profile_email`, `profile_name`, `profile_gender`, `profile_birthplace`, `profile_birthdate`, `profile_address`, `profile_telp`, `profile_photo`, `user_id`, `profile_phone`, `profile_bio`, `profile_resources_uuid`, `profile_forgot_uuid`, `profile_forgot_expired`, `status`, `remarks`, `created_by`, `created_ip`, `created_time`, `created_platform`, `modified_by`, `modified_ip`, `modified_time`, `modified_platform`) VALUES
(1, 'ridla.fadilah@tripoin.co.id', 'Ridla Fadilah', 'MALE', 'Bandung', '1990-12-27', 'Tangerang', '021234567891', NULL, 1, '081234567891', '<font face="Courier New">This is Me</font>', 'b1c52cdc-78ac-4677-899d-2cacb5cb72e0', '0d0bf53a-0d24-4290-b29b-9190ae5ccf0f', '2015-11-02 13:15:09', NULL, NULL, 'admin', '127.0.0.1', '2015-10-28 03:57:43', NULL, 'admin', '127.0.0.1', '2016-02-22 16:22:53', 'Computer | Windows | Chrome'),
(2, 'bangkit.pratolo@tripoin.co.id', 'Bangkit Pratolo', 'MALE', 'Tangerang', '2015-10-12', 'Tangerang', '-', NULL, 2, '081234567892', '-', '399820b9-14c8-4788-bdba-8789dc7ce533', NULL, NULL, NULL, NULL, 'admin', '127.0.0.1', '2015-10-28 03:57:43', NULL, NULL, NULL, NULL, NULL),
(3, 'achmad.fauzi@tripoin.co.id', 'Achmad Fauzi', 'MALE', 'Tangerang', '2015-10-13', 'Tangerang', '-', NULL, 3, '081234567893', '-', 'a1e87b78-4e1d-4f09-8eeb-8c78c7b8d22b', NULL, NULL, NULL, NULL, 'admin', '127.0.0.1', '2015-10-28 03:57:43', NULL, NULL, NULL, NULL, NULL),
(4, 'admin@tripoin.co.id', 'Administrator', 'FEMALE', 'Tangerang', '2015-10-14', 'Tangerang', '-', 'tomcat.gif8796357859637219542.gif', 4, '081234567894', '-', 'd42c93af-92e0-49ca-8989-4e6d14c6606c', NULL, NULL, NULL, NULL, 'admin', '127.0.0.1', '2015-10-28 03:57:43', NULL, 'admin', '127.0.0.1', '2016-01-04 02:51:50', 'Computer | Windows | Firefox');

-- --------------------------------------------------------

--
-- Table structure for table `mst_project`
--

CREATE TABLE IF NOT EXISTS `mst_project` (
`project_id` bigint(20) NOT NULL,
  `project_code` varchar(150) NOT NULL,
  `project_name` varchar(255) NOT NULL,
  `project_location` varchar(255) NOT NULL,
  `project_mine` varchar(255) DEFAULT NULL,
  `project_ob` varchar(150) DEFAULT NULL,
  `project_distance_hauling` varchar(150) DEFAULT NULL,
  `project_distance_ob` varchar(150) DEFAULT NULL,
  `project_gradient_road` varchar(150) DEFAULT NULL,
  `project_width_road` varchar(150) DEFAULT NULL,
  `project_texture_road` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `mst_project_target`
--

CREATE TABLE IF NOT EXISTS `mst_project_target` (
`project_target_id` bigint(20) NOT NULL COMMENT ' ',
  `project_id` bigint(20) NOT NULL,
  `target_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `mst_segment`
--

CREATE TABLE IF NOT EXISTS `mst_segment` (
`segment_id` bigint(20) NOT NULL,
  `segment_code` varchar(150) NOT NULL,
  `segment_name` varchar(255) NOT NULL,
  `status` smallint(5) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `created_by` varchar(150) DEFAULT 'admin',
  `created_ip` varchar(150) DEFAULT '127.0.0.1',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_platform` varchar(255) DEFAULT NULL,
  `modified_by` varchar(150) DEFAULT NULL,
  `modified_ip` varchar(150) DEFAULT NULL,
  `modified_time` timestamp NULL DEFAULT NULL,
  `modified_platform` varchar(255) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `mst_segment`
--

INSERT INTO `mst_segment` (`segment_id`, `segment_code`, `segment_name`, `status`, `remarks`, `created_by`, `created_ip`, `created_time`, `created_platform`, `modified_by`, `modified_ip`, `modified_time`, `modified_platform`) VALUES
(1, 'FORESTRY&AGRICULTURE', 'Forestry & Agriculture', NULL, 'Forestry & Agriculture', 'admin', '127.0.0.1', '2016-02-27 17:03:10', NULL, NULL, NULL, NULL, NULL),
(2, 'CONSTRUCTION', 'Construction', 1, 'Construction', 'admin', '127.0.0.1', '2016-02-27 17:03:10', NULL, NULL, NULL, NULL, NULL),
(3, 'OILANDGAS', 'Oil And Gas', 1, 'Oil And Gas', 'admin', '127.0.0.1', '2016-02-27 17:03:10', NULL, NULL, NULL, NULL, NULL),
(4, 'MINING', 'Mining', 1, 'Mining', 'admin', '127.0.0.1', '2016-02-27 17:03:10', NULL, NULL, NULL, NULL, NULL),
(5, 'TRANSPORTATION', 'Transportation', 1, 'Transportation', 'admin', '127.0.0.1', '2016-02-27 17:03:10', NULL, NULL, NULL, NULL, NULL),
(6, 'OTHERS', 'Others', 1, 'Others', 'admin', '127.0.0.1', '2016-02-27 17:03:10', NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `mst_site`
--

CREATE TABLE IF NOT EXISTS `mst_site` (
`site_id` bigint(20) NOT NULL,
  `site_code` varchar(150) NOT NULL,
  `site_pm` varchar(150) NOT NULL,
  `site_land_owner` varchar(150) NOT NULL,
  `site_land_status` varchar(150) NOT NULL,
  `site_kp_iup_pkp2b` varchar(150) NOT NULL,
  `site_location` varchar(150) NOT NULL,
  `site_land_area` varchar(150) NOT NULL,
  `site_calories_stone` varchar(150) NOT NULL,
  `site_distance_village` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `mst_spareparts`
--

CREATE TABLE IF NOT EXISTS `mst_spareparts` (
`spareparts_id` bigint(20) NOT NULL,
  `spareparts_code` varchar(150) NOT NULL,
  `spareparts_name` varchar(255) NOT NULL,
  `spareparts_detail` text,
  `spareparts_price` varchar(150) DEFAULT NULL,
  `spareparts_quantity` bigint(10) DEFAULT NULL,
  `status` smallint(5) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `created_by` varchar(150) DEFAULT 'admin',
  `created_ip` varchar(150) DEFAULT '127.0.0.1',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_platform` varchar(255) DEFAULT NULL,
  `modified_by` varchar(150) DEFAULT NULL,
  `modified_ip` varchar(150) DEFAULT NULL,
  `modified_time` timestamp NULL DEFAULT NULL,
  `modified_platform` varchar(255) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `mst_spareparts`
--

INSERT INTO `mst_spareparts` (`spareparts_id`, `spareparts_code`, `spareparts_name`, `spareparts_detail`, `spareparts_price`, `spareparts_quantity`, `status`, `remarks`, `created_by`, `created_ip`, `created_time`, `created_platform`, `modified_by`, `modified_ip`, `modified_time`, `modified_platform`) VALUES
(1, 'ZF', 'ZF', 'Headquartered in Germany, ZF is a leading worldwide automotive supplier for Driveline and Chassis Technology. Founded in 1915 for the development and production of transmissions for airships and vehicles, the group''s current product range comprises transmissions and steering systems as well as chassis components and complete axle systems and modules.', NULL, NULL, 1, 'ZF', 'admin', '127.0.0.1', '2016-02-27 17:51:34', NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `mst_target`
--

CREATE TABLE IF NOT EXISTS `mst_target` (
`target_id` bigint(20) NOT NULL,
  `target_code` varchar(150) NOT NULL,
  `target_mine` varchar(255) NOT NULL,
  `target_ob` varchar(255) NOT NULL,
  `target_distance_hauling` varchar(255) NOT NULL,
  `target_distance_ob` varchar(255) NOT NULL,
  `target_gradient_road` varchar(255) NOT NULL,
  `target_width_road` varchar(255) NOT NULL,
  `target_texture_road` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `mst_truck`
--

CREATE TABLE IF NOT EXISTS `mst_truck` (
`truck_id` bigint(20) NOT NULL,
  `truck_type_id` bigint(20) NOT NULL,
  `truck_model_id` bigint(20) NOT NULL,
  `truck_axle_id` bigint(20) NOT NULL,
  `truck_code` varchar(150) NOT NULL,
  `truck_name` varchar(255) NOT NULL,
  `status` smallint(5) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `created_by` varchar(150) DEFAULT 'admin',
  `created_ip` varchar(150) DEFAULT '127.0.0.1',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_platform` varchar(255) DEFAULT NULL,
  `modified_by` varchar(150) DEFAULT NULL,
  `modified_ip` varchar(150) DEFAULT NULL,
  `modified_time` timestamp NULL DEFAULT NULL,
  `modified_platform` varchar(255) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Dumping data for table `mst_truck`
--

INSERT INTO `mst_truck` (`truck_id`, `truck_type_id`, `truck_model_id`, `truck_axle_id`, `truck_code`, `truck_name`, `status`, `remarks`, `created_by`, `created_ip`, `created_time`, `created_platform`, `modified_by`, `modified_ip`, `modified_time`, `modified_platform`) VALUES
(1, 1, 1, 4, 'AD380T38H6X4', 'AD380T38H 6X4', 1, 'AD380T38H 6X4', 'admin', '127.0.0.1', '2016-02-27 19:51:57', NULL, NULL, NULL, NULL, NULL),
(2, 1, 1, 5, 'AD380T44W6x6', 'AD380T44W 6x6', 1, 'AD380T44W 6x6', 'admin', '127.0.0.1', '2016-02-27 19:53:34', NULL, NULL, NULL, NULL, NULL),
(3, 1, 1, 4, 'AD380T486x4', 'AD380T48 6x4', 1, 'AD380T48 6x4', 'admin', '127.0.0.1', '2016-02-27 19:54:38', NULL, NULL, NULL, NULL, NULL),
(4, 1, 1, 6, 'AD410T448x4', 'AD410T44 8x4', 1, 'AD410T44 8x4', 'admin', '127.0.0.1', '2016-02-27 19:55:26', NULL, NULL, NULL, NULL, NULL),
(5, 1, 2, 2, '44.424x4', '44.42 4x4', 1, '44.42 4x4', 'admin', '127.0.0.1', '2016-02-27 20:06:44', NULL, NULL, NULL, NULL, NULL),
(6, 1, 2, 2, '44.484x4', '44.48 4x4', 1, '44.48 4x4', 'admin', '127.0.0.1', '2016-02-27 20:06:44', NULL, NULL, NULL, NULL, NULL),
(7, 1, 2, 4, '64.54T6x4', '64.54 T 6x4', 1, '64.54 T 6x4', 'admin', '127.0.0.1', '2016-02-27 20:08:03', NULL, NULL, NULL, NULL, NULL),
(8, 1, 2, 5, '66.386x6', '66.38 6x6', 1, '66.38 6x6', 'admin', '127.0.0.1', '2016-02-27 20:08:03', NULL, NULL, NULL, NULL, NULL),
(9, 1, 2, 5, '66.44T6x6', '66.44 T 6x6', 1, '66.44 T 6x6', 'admin', '127.0.0.1', '2016-02-27 20:08:45', NULL, NULL, NULL, NULL, NULL),
(10, 1, 4, 2, '55S15DWH4x4', '55S15DWH 4x4', 1, '55S15DWH 4x4', 'admin', '127.0.0.1', '2016-02-27 20:09:35', NULL, NULL, NULL, NULL, NULL),
(11, 1, 4, 2, '55S15WH4x4', '55S15WH 4x4', 1, '55S15WH 4x4', 'admin', '127.0.0.1', '2016-02-27 20:10:03', NULL, NULL, NULL, NULL, NULL),
(12, 3, 5, 4, 'CabChassis6x4', 'Cab Chassis 6x4', 1, 'Cab Chassis 6x4', 'admin', '127.0.0.1', '2016-02-27 20:13:10', NULL, NULL, NULL, NULL, NULL),
(13, 2, 5, 4, 'PrimeMover6x4', 'Prime Mover 6x4', 1, 'Prime Mover 6x4', 'admin', '127.0.0.1', '2016-02-27 20:13:10', NULL, NULL, NULL, NULL, NULL),
(14, 4, 5, 4, 'Tipper6x4', 'Tipper 6x4', 1, 'Tipper 6x4', 'admin', '127.0.0.1', '2016-02-27 20:13:10', NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `mst_truck_axle`
--

CREATE TABLE IF NOT EXISTS `mst_truck_axle` (
`truck_axle_id` bigint(20) NOT NULL,
  `truck_axle_code` varchar(150) NOT NULL,
  `truck_axle_name` varchar(255) NOT NULL,
  `truck_axle_detail` text,
  `status` smallint(5) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `created_by` varchar(150) DEFAULT 'admin',
  `created_ip` varchar(150) DEFAULT '127.0.0.1',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_platform` varchar(255) DEFAULT NULL,
  `modified_by` varchar(150) DEFAULT NULL,
  `modified_ip` varchar(150) DEFAULT NULL,
  `modified_time` timestamp NULL DEFAULT NULL,
  `modified_platform` varchar(255) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `mst_truck_axle`
--

INSERT INTO `mst_truck_axle` (`truck_axle_id`, `truck_axle_code`, `truck_axle_name`, `truck_axle_detail`, `status`, `remarks`, `created_by`, `created_ip`, `created_time`, `created_platform`, `modified_by`, `modified_ip`, `modified_time`, `modified_platform`) VALUES
(1, '4X2', '4X2', '4X2', 1, '4X2', 'admin', '127.0.0.1', '2016-02-27 16:51:25', NULL, NULL, NULL, NULL, NULL),
(2, '4X4', '4X4', '4X4', 1, '4X4', 'admin', '127.0.0.1', '2016-02-27 16:51:25', NULL, NULL, NULL, NULL, NULL),
(3, '6X2', '6X2', '6X2', 1, '6X2', 'admin', '127.0.0.1', '2016-02-27 16:51:25', NULL, NULL, NULL, NULL, NULL),
(4, '6X4', '6X4', '6X4', 1, '6X4', 'admin', '127.0.0.1', '2016-02-27 16:51:25', NULL, NULL, NULL, NULL, NULL),
(5, '6X6', '6X6', '6X6', 1, '6X6', 'admin', '127.0.0.1', '2016-02-27 16:51:25', NULL, NULL, NULL, NULL, NULL),
(6, '8X4', '8X4', '8X4', 1, '8X4', 'admin', '127.0.0.1', '2016-02-27 16:51:25', NULL, NULL, NULL, NULL, NULL),
(7, '8X6', '8X6', '8X6', 1, '8X6', 'admin', '127.0.0.1', '2016-02-27 16:51:25', NULL, NULL, NULL, NULL, NULL),
(8, '8X8', '8X8', '8X8', 1, '8X8', 'admin', '127.0.0.1', '2016-02-27 16:51:25', NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `mst_truck_model`
--

CREATE TABLE IF NOT EXISTS `mst_truck_model` (
`truck_model_id` bigint(20) NOT NULL,
  `truck_model_code` varchar(150) NOT NULL,
  `truck_model_name` varchar(255) NOT NULL,
  `truck_model_detail` text,
  `status` smallint(5) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `created_by` varchar(150) DEFAULT 'admin',
  `created_ip` varchar(150) DEFAULT '127.0.0.1',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_platform` varchar(255) DEFAULT NULL,
  `modified_by` varchar(150) DEFAULT NULL,
  `modified_ip` varchar(150) DEFAULT NULL,
  `modified_time` timestamp NULL DEFAULT NULL,
  `modified_platform` varchar(255) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `mst_truck_model`
--

INSERT INTO `mst_truck_model` (`truck_model_id`, `truck_model_code`, `truck_model_name`, `truck_model_detail`, `status`, `remarks`, `created_by`, `created_ip`, `created_time`, `created_platform`, `modified_by`, `modified_ip`, `modified_time`, `modified_platform`) VALUES
(1, 'TRAKKER', 'Trakker', 'Trakker', 1, 'Trakker', 'admin', '127.0.0.1', '2016-02-27 16:49:07', NULL, NULL, NULL, NULL, NULL),
(2, 'ASTRA', 'Astra', 'Astra', 1, 'Astra', 'admin', '127.0.0.1', '2016-02-27 16:49:07', NULL, NULL, NULL, NULL, NULL),
(3, 'ADT', 'ADT', 'ADT', 1, 'ADT', 'admin', '127.0.0.1', '2016-02-27 16:49:07', NULL, NULL, NULL, NULL, NULL),
(4, 'DAILY', 'Daily', 'Daily', 1, 'Daily', 'admin', '127.0.0.1', '2016-02-27 16:49:07', NULL, NULL, NULL, NULL, NULL),
(5, '682', '682', '682', 1, '682', 'admin', '127.0.0.1', '2016-02-27 16:49:07', NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `mst_truck_type`
--

CREATE TABLE IF NOT EXISTS `mst_truck_type` (
`truck_type_id` bigint(20) NOT NULL,
  `truck_type_code` varchar(150) NOT NULL,
  `truck_type_name` varchar(255) NOT NULL,
  `truck_type_detail` text,
  `status` smallint(5) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `created_by` varchar(150) DEFAULT 'admin',
  `created_ip` varchar(150) DEFAULT '127.0.0.1',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_platform` varchar(255) DEFAULT NULL,
  `modified_by` varchar(150) DEFAULT NULL,
  `modified_ip` varchar(150) DEFAULT NULL,
  `modified_time` timestamp NULL DEFAULT NULL,
  `modified_platform` varchar(255) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `mst_truck_type`
--

INSERT INTO `mst_truck_type` (`truck_type_id`, `truck_type_code`, `truck_type_name`, `truck_type_detail`, `status`, `remarks`, `created_by`, `created_ip`, `created_time`, `created_platform`, `modified_by`, `modified_ip`, `modified_time`, `modified_platform`) VALUES
(1, 'RIDGID', 'Ridgid Truck', 'Ridgid Truck', 1, 'Ridgid Truck', 'admin', '127.0.0.1', '2016-02-27 16:46:55', NULL, NULL, NULL, NULL, NULL),
(2, 'PRIMEMOVER', 'Prime Mover Truck', 'Prime Mover Truck', 1, 'Prime Mover Truck', 'admin', '127.0.0.1', '2016-02-27 16:46:55', NULL, NULL, NULL, NULL, NULL),
(3, 'CABCHASSIS', 'Cab Chassis', 'Cab Chassis', 1, 'Cab Chassis', 'admin', '127.0.0.1', '2016-02-27 20:01:20', NULL, NULL, NULL, NULL, NULL),
(4, 'TIPPER', 'Tipper', 'Tipper', 1, 'Tipper', 'admin', '127.0.0.1', '2016-02-27 20:04:55', NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `mst_unit_brand`
--

CREATE TABLE IF NOT EXISTS `mst_unit_brand` (
`unit_brand_id` bigint(20) NOT NULL,
  `unit_brand_code` varchar(150) NOT NULL,
  `unit_brand_name` varchar(255) NOT NULL,
  `unit_brand_description` text,
  `country_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `mst_unit_populate`
--

CREATE TABLE IF NOT EXISTS `mst_unit_populate` (
`unit_populate_id` bigint(20) NOT NULL,
  `unit_brand_id` bigint(20) NOT NULL,
  `unit_type_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `mst_unit_type`
--

CREATE TABLE IF NOT EXISTS `mst_unit_type` (
`unit_type_id` bigint(20) NOT NULL,
  `unit_type_code` varchar(150) NOT NULL,
  `unit_type_name` varchar(255) NOT NULL,
  `unit_type_group` varchar(255) DEFAULT NULL,
  `unit_type_description` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `sec_role`
--

CREATE TABLE IF NOT EXISTS `sec_role` (
`role_id` bigint(20) NOT NULL,
  `role_code` varchar(50) NOT NULL,
  `role_status` smallint(5) NOT NULL,
  `role_remarks` varchar(255) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `sec_role`
--

INSERT INTO `sec_role` (`role_id`, `role_code`, `role_status`, `role_remarks`) VALUES
(1, 'ROLE_SALESMAN', 1, 'Role Salesman'),
(2, 'ROLE_AREASALESMANAGER', 1, 'Role Area Sales Manager'),
(3, 'ROLE_NATIONALSALESMANAGER', 1, 'Role National Sales Manager'),
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
  `user_enabled` smallint(5) NOT NULL,
  `user_expired_date` timestamp NULL DEFAULT NULL,
  `user_non_locked` smallint(5) DEFAULT NULL,
  `user_auth` varchar(255) DEFAULT NULL,
  `user_status` smallint(5) NOT NULL,
  `user_remarks` varchar(255) DEFAULT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `sec_user`
--

INSERT INTO `sec_user` (`user_id`, `user_username`, `user_password`, `user_enabled`, `user_expired_date`, `user_non_locked`, `user_auth`, `user_status`, `user_remarks`, `role_id`) VALUES
(1, 'ridla', 'M6cURmiwEwLW9jiBeYPiMq21DHY=', 1, NULL, NULL, NULL, 1, NULL, 1),
(2, 'bangkit', 'M6cURmiwEwLW9jiBeYPiMq21DHY=', 1, NULL, NULL, NULL, 1, NULL, 2),
(3, 'fauzi', 'M6cURmiwEwLW9jiBeYPiMq21DHY=', 1, NULL, NULL, NULL, 1, NULL, 3),
(4, 'admin', 'M6cURmiwEwLW9jiBeYPiMq21DHY=', 1, NULL, NULL, NULL, 1, NULL, 4),
(5, 'tripoin.app.web', 'g/vPwZ+XHlXlZCIIYgK28SUyX+Y=', 1, NULL, NULL, NULL, 1, NULL, 5),
(6, 'tripoin.app.android', 'g/vPwZ+XHlXlZCIIYgK28SUyX+Y=', 1, NULL, NULL, NULL, 1, NULL, 5);

-- --------------------------------------------------------

--
-- Table structure for table `sys_menu`
--

CREATE TABLE IF NOT EXISTS `sys_menu` (
`menu_id` bigint(20) NOT NULL,
  `menu_code` varchar(150) NOT NULL,
  `menu_name` varchar(255) DEFAULT NULL,
  `menu_parent_id` bigint(20) DEFAULT NULL,
  `menu_level` int(5) NOT NULL DEFAULT '1',
  `menu_order` int(5) NOT NULL DEFAULT '1',
  `menu_tree` varchar(20) NOT NULL,
  `menu_function` varchar(20) NOT NULL DEFAULT 'MENU-PAGE',
  `menu_view_type` varchar(50) NOT NULL DEFAULT 'WEB-MOBILE',
  `status` smallint(5) DEFAULT '1',
  `remarks` varchar(255) DEFAULT NULL,
  `created_by` varchar(150) DEFAULT 'admin',
  `created_ip` varchar(150) DEFAULT '127.0.0.1',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_platform` varchar(255) DEFAULT NULL,
  `modified_by` varchar(150) DEFAULT NULL,
  `modified_ip` varchar(150) DEFAULT NULL,
  `modified_time` timestamp NULL DEFAULT NULL,
  `modified_platform` varchar(255) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=28 ;

--
-- Dumping data for table `sys_menu`
--

INSERT INTO `sys_menu` (`menu_id`, `menu_code`, `menu_name`, `menu_parent_id`, `menu_level`, `menu_order`, `menu_tree`, `menu_function`, `menu_view_type`, `status`, `remarks`, `created_by`, `created_ip`, `created_time`, `created_platform`, `modified_by`, `modified_ip`, `modified_time`, `modified_platform`) VALUES
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
(19, 'masterDataView', 'Master Data', NULL, 1, 7, '7', 'MENU-NON-PAGE', 'WEB', 1, 'Master Data', 'admin', '127.0.0.1', '2015-10-24 17:00:00', NULL, NULL, NULL, NULL, NULL),
(20, 'dataEmployeeView', 'Data Employee', 19, 2, 1, '7.1', 'MENU-PAGE', 'WEB', 1, 'Data Employee', 'admin', '127.0.0.1', '2015-10-24 17:27:24', NULL, NULL, NULL, NULL, NULL),
(21, 'dataOccupationView', 'Data Occupation', 19, 2, 2, '7.2', 'MENU-PAGE', 'WEB', 1, 'Data Occupation', 'admin', '127.0.0.1', '2015-10-24 17:00:00', NULL, NULL, NULL, NULL, NULL),
(22, 'dataAreaView', 'Data Area', 19, 2, 3, '7.3', 'MENU-PAGE', 'WEB', 1, 'Data Area', 'admin', '127.0.0.1', '2015-10-24 17:00:00', NULL, NULL, NULL, NULL, NULL),
(23, 'dataProjectView', 'Data Project', 19, 2, 4, '7.4', 'MENU-PAGE', 'WEB', 1, 'Data Project', 'admin', '127.0.0.1', '2015-10-24 17:00:00', NULL, NULL, NULL, NULL, NULL),
(24, 'dataCompetitiveInformationSourceView', 'Data Competitive Information Source', 19, 2, 5, '7.5', 'MENU-PAGE', 'WEB', 1, 'Data Competitive Information Source', 'admin', '127.0.0.1', '2015-10-24 17:00:00', NULL, NULL, NULL, NULL, NULL),
(25, 'dataComparisonView', 'Data Comparison', 19, 2, 6, '7.6', 'MENU-PAGE', 'WEB', 1, 'Data Comparison', 'admin', '127.0.0.1', '2015-10-24 17:00:00', NULL, NULL, NULL, NULL, NULL),
(26, 'dataCurrencyView', 'Data Currency', 19, 2, 7, '7.7', 'MENU-PAGE', 'WEB', 1, 'Data Currency', 'admin', '127.0.0.1', '2015-10-24 17:00:00', NULL, NULL, NULL, NULL, NULL),
(27, 'salesTracking', 'Sales Tracking', NULL, 1, 6, '6', 'MENU-PAGE', 'WEB', 1, 'Sales Tracking', 'admin', '127.0.0.1', '2015-11-14 17:00:00', NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `sys_menu_role`
--

CREATE TABLE IF NOT EXISTS `sys_menu_role` (
`menu_role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=44 ;

--
-- Dumping data for table `sys_menu_role`
--

INSERT INTO `sys_menu_role` (`menu_role_id`, `menu_id`, `role_id`) VALUES
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
(42, 26, 4),
(43, 27, 2);

-- --------------------------------------------------------

--
-- Table structure for table `sys_system_parameter`
--

CREATE TABLE IF NOT EXISTS `sys_system_parameter` (
`system_parameter_id` bigint(20) NOT NULL,
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
  `system_parameter_modified_platform` varchar(255) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `sys_system_parameter`
--

INSERT INTO `sys_system_parameter` (`system_parameter_id`, `system_parameter_code`, `system_parameter_value`, `system_parameter_status`, `system_parameter_remarks`, `system_parameter_created_by`, `system_parameter_created_ip`, `system_parameter_created_time`, `system_parameter_created_platform`, `system_parameter_modified_by`, `system_parameter_modified_ip`, `system_parameter_modified_time`, `system_parameter_modified_platform`) VALUES
(1, 'TRIPOIN.EMAIL.FORGOT.PASSWORD.SUBJECT', 'Request Reset Password', 1, 'Subject Email Forgot Password', 'admin', '127.0.0.1', '2015-10-31 17:00:00', NULL, NULL, NULL, NULL, NULL),
(2, 'TRIPOIN.EMAIL.FORGOT.PASSWORD.BODY.MESSAGE', '<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd"> <html>  	<head> 	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" /> 	<meta name="apple-mobile-web-app-capable" content="yes" /> 	<style type="text/css"> 	@media only screen and (max-width: 320px) { 	table[class="edu-module"]{ 	border-radius: 0px !important; 	-webkit-border-radius: 0px !important; 	-moz-border-radius: 0px !important; 	} 	td[class="edu-margins"]{ 	background-color: #f5f8fa; 	} 	td[class="edu-collapse"]{ 	width: 0px !important; 	} 	td[class="edu-space"]{ 	height: 10px !important; 	background-color: #f5f8fa; 	} 	td[class="mobile-height"]{ 	height: 30px !important; 	} 	} 	@media only screen and (max-width: 420px) { 	span[class="address"] a { 	line-height:18px !important; 	} 	td[class="margins"]{ 	width:18px !important; 	} 	td[class="edu-margins"]{ 	width:18px !important; 	} 	td[class="logo_space"]{ 	height:12px !important; 				} 			}  			@media only screen and (max-width: 480px) { 				table[class="collapse"]{ 					width:100% !important; 				} 				table[class="edu-module"]{ 					width:100% !important; 				} 				span[class="address"]{ 					display:block !important; 					width:240px !important; 				} 				td[class="cut"]{ 					display:none !important; 				} 			} 		</style> 	</head> 	<body bgcolor="#FAFAFA" style="margin:0;padding:0;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;"> 		<table cellpadding="0" cellspacing="0" border="0" width="100%" bgcolor="#FAFAFA" style="background-color:#FAFAFA;padding:0;margin:0;line-height:1px;font-size:1px;" class="body_wrapper"> 			<tbody> 				<tr> 					<td align="center" style="padding:0;margin:0;line-height:1px;font-size:1px;"> 						<table class="collapse" id="header" align="center" width="650" style="width: 650px;padding:0;margin:0;line-height:1px;font-size:1px;" bgcolor="#ffffff" cellpadding="0" cellspacing="0" border="0"> 							<tbody> 								<tr> 									<td style="min-width: 650px;height:1px;padding:0;margin:0;line-height:1px;font-size:1px;" class="cut"></td> 								</tr> 							</tbody> 						</table>  					</td> 				</tr> 				<tr> 					<td align="center" style="padding:0;margin:0;line-height:1px;font-size:1px;"> 					<!--///////////////////header///////////////////////////--> 						<table class="collapse" id="header" align="center" width="650" style="width:650px;background-color:#ffffff;padding:0;margin:0;line-height:1px;font-size:1px;" bgcolor="#ffffff" cellpadding="0" cellspacing="0" border="0"> 							<tbody> 								<tr> 									<td height="15" style="height:15px;padding:0;margin:0;line-height:1px;font-size:1px;" class="logo_space"> &nbsp; </td> 								</tr> 								<tr> 									<td style="padding:0;margin:0;line-height:1px;font-size:1px;"> 										<table cellpadding="0" cellspacing="0" border="0" align="left" style="padding:0;margin:0;line-height:1px;font-size:1px;"> 											<tbody> 												<tr align="left"> 													<td align="left" width="15" style="width:15px;padding:0;margin:0;line-height:1px;font-size:1px;"></td> 													<td align="left" width="28" style="padding:0;margin:0;line-height:1px;font-size:1px;"></td> 													<td align="left" width="10" style="width:10px;padding:0;margin:0;line-height:1px;font-size:1px;"></td> 													<td align="left" class="greeting" style="padding:0;margin:0;line-height:1px;font-size:1px;font-family:''Helvetica Neue Light'', Helvetica, Arial, sans-serif;-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:none;color:#66757f;font-size:16px;padding:0px;margin:0px;font-weight:300;line-height:100%;text-align:left;"> Dear ${TRIPOIN.CONTENT.FULLNAME}, </td> 													<td width="50" style="width:50px;padding:0;margin:0;line-height:1px;font-size:1px;" align="left"></td> 												</tr> 											</tbody> 										</table>  									</td> 								</tr> 								<tr> 								<td height="14" style="height:14px;padding:0;margin:0;line-height:1px;font-size:1px;" class="logo_space"> &nbsp; </td> 								</tr> 							</tbody> 						</table> 						<!--////////////////////border//////////////////////////--> 						<table class="collapse" align="center" width="650" style="width:650px;background-color:#ffffff;padding:0;margin:0;line-height:1px;font-size:1px;" cellpadding="0" cellspacing="0" border="0"> 							<tbody> 								<tr id="border"> 									<td colspan="2" height="1" style="line-height:1px;display:block;height:1px;background-color:#FAFAFA;padding:0;margin:0;line-height:1px;font-size:1px;"></td> 								</tr> 							</tbody> 						</table> 						<!--//////////////////////////////////////////////--> 						<table class="collapse" align="center" width="650" style="width:650px;background-color:#ffffff;padding:0;margin:0;line-height:1px;font-size:1px;" cellpadding="0" cellspacing="0" border="0"> 							<tbody> 								<tr> 								<td width="50" style="width:50px;padding:0;margin:0;line-height:1px;font-size:1px;" class="margins"></td> 								<td align="center" style="padding:0;margin:0;line-height:1px;font-size:1px;"> 									<table width="100%" align="center" cellpadding="0" cellspacing="0" border="0" class="collapse" style="padding:0;margin:0;line-height:1px;font-size:1px;"> 										<tbody> 										<tr> 											<td height="30" style="height:45px;padding:0;margin:0;line-height:1px;font-size:1px;"></td> 										</tr> 										<tr> 											<td align="left" class="display" style="padding:0;margin:0;line-height:1px;font-size:1px;font-family:''Helvetica Neue'', Helvetica, Arial, sans-serif;font-size:22px;line-height:30px;font-weight:300;-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:none;text-align:left;color:#292f33;"> We received a request to reset the password for your account. </td> 										</tr> 										<tr> 											<td height="30" style="height:30px;padding:0;margin:0;line-height:1px;font-size:1px;"></td> 										</tr> 										<tr> 											<td align="left" class="body-text" style="padding:0;margin:0;line-height:1px;font-size:1px;font-family:''Helvetica Neue'', Helvetica, Arial, sans-serif;font-size:16px;line-height:22px;font-weight:400;-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:none;text-align:left;color:#292f33;"> If you requested a reset for username <span style="text-decoration:none;border-style:none;border:0;padding:0;margin:0;border:none;text-decoration:none;font-weight:400;color:#1879DB;">${TRIPOIN.CONTENT.USERNAME}</span>, click the button below. If you didn''t make this request, please ignore this email. </td> 										</tr> 										<tr> 											<td height="25" style="height:25px;padding:0;margin:0;line-height:1px;font-size:1px;"></td> 										</tr> 										<!--*********** button ************--> 										<tr> 											<td align="left" style="padding:0;margin:0;line-height:1px;font-size:1px;"> 												<table border="0" cellspacing="0" cellpadding="0" style="padding:0;margin:0;line-height:1px;font-size:1px;"> 													<tbody> 														<tr> 															<td style="padding:0;margin:0;line-height:1px;font-size:1px;"> 																<!-- Tap, click, press, push the button --> 																<table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding:0;margin:0;line-height:1px;font-size:1px;"> 																	<tbody> 																		<tr> 																			<td style="padding:0;margin:0;line-height:1px;font-size:1px;"> 																				<table border="0" cellspacing="0" cellpadding="0" style="padding:0;margin:0;line-height:1px;font-size:1px;"> 																					<tbody> 																						<tr> 																							<td align="center" class="bulletproof-btn-1" bgcolor="#1879DB" style="padding:0;margin:0;line-height:1px;font-size:1px;-webkit-border-radius:4px;-moz-border-radius:4px;border-radius:4px;line-height:18px;"> 																								<a href="${TRIPOIN.CONTENT.URL}" target="_blank" class="bulletproof-btn-2" style="text-decoration:none;border-style:none;border:0;padding:0;margin:0;font-family:''Helvetica Neue'', Helvetica, Arial, sans-serif;font-size:16px;line-height:22px;font-weight:650;-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:none;color:#ffffff;text-align:center;text-decoration:none;-webkit-border-radius:4px;-moz-border-radius:4px;border-radius:4px;padding:11px 30px;border:1px solid #1879DB;display:inline-block;"> 																									<strong>Reset password</strong> 																								</a> 																							</td> 																						</tr> 																					</tbody> 																				</table>  																			</td> 																		</tr> 																	</tbody> 																</table>  															</td> 														</tr> 													</tbody> 												</table>  											</td> 										</tr> 										<!--*********** end button ************--> 										<tr> 											<td height="55" style="height:55px;padding:0;margin:0;line-height:1px;font-size:1px;"></td> 										</tr> 										</tbody> 									</table>  								</td> 								<td width="50" style="width:50px;padding:0;margin:0;line-height:1px;font-size:1px;" class="margins"></td> 								</tr> 							</tbody> 						</table> 						<!--//////////////////////////////////////////////--> 						<table class="collapse" id="footer" align="center" width="650" style="width:650px;background-color:#ffffff;padding:0;margin:0;line-height:1px;font-size:1px;" cellpadding="0" cellspacing="0" border="0"> 							<tbody> 								<tr> 									<td height="1" style="line-height:1px;display:block;height:1px;background-color:#FAFAFA;padding:0;margin:0;line-height:1px;font-size:1px;"></td> 								</tr> 								<tr> 									<td height="20" style="height:20;padding:0;margin:0;line-height:1px;font-size:1px;"></td> 								</tr> 								<tr> 									<td align="center" style="padding:0;margin:0;line-height:1px;font-size:1px;"> <span class="footer_type" style="font-family:''Helvetica Neue Light'', Helvetica, Arial, sans-serif;-webkit-font-smoothing:antialiased;color:#8899a6;font-size:12px;padding:0px;margin:0px;font-weight:normal;line-height:12px;">  <a href="http://www.tripoin.co.id" class="footer_link" style="text-decoration:none;border-style:none;border:0;padding:0;margin:0;font-family:''Helvetica Neue Light'', Helvetica, Arial, sans-serif;-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:none;color:#1879DB;font-size:12px;padding:0px;margin:0px;font-weight:600;line-height:12px;">Tripoin, Inc.</a>  </span> </td> 								</tr> 								<tr> 									<td height="10" style="height:10px;line-height:1px;font-size:1px;padding:0;margin:0;line-height:1px;font-size:1px;"></td> 								</tr> 								<tr> 									<td align="center" style="padding:0;margin:0;line-height:1px;font-size:1px;"> <span class="address"> <a href="" style="text-decoration:none;border-style:none;border:0;padding:0;margin:0;font-family:''Helvetica Neue Light'', Helvetica, Arial, sans-serif;-webkit-font-smoothing:antialiased;color:#8899a6;font-size:12px;padding:0px;margin:0px;font-weight:normal;line-height:12px;cursor:default;">Indonesia, Jakarta</a> </span> </td> 								</tr> 								<tr> 									<td height="26" style="height:26;padding:0;margin:0;line-height:1px;font-size:1px;"></td> 								</tr> 							</tbody> 						</table>					 					</td> 				</tr> 			</tbody> 		</table> 	</body> </html>', 1, 'Body Email Forgot Password', 'admin', '127.0.0.1', '2015-10-31 19:02:05', NULL, NULL, NULL, NULL, NULL),
(3, 'TRIPOIN.EMAIL.FORGOT.PASSWORD.VERIFY.SUBJECT', 'Verify Reset Password', 1, 'Subject Email Verify Forgot Password', 'admin', '127.0.0.1', '2015-11-01 17:00:00', NULL, NULL, NULL, NULL, NULL),
(4, 'TRIPOIN.EMAIL.FORGOT.PASSWORD.VERIFY.BODY.MESSAGE', '<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd"><html> 	<head>	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />	<meta name="apple-mobile-web-app-capable" content="yes" />	<style type="text/css">	@media only screen and (max-width: 320px) { 	table[class="edu-module"]{ 	border-radius: 0px !important; 	-webkit-border-radius: 0px !important; 	-moz-border-radius: 0px !important; 	} 	td[class="edu-margins"]{ 	background-color: #f5f8fa; 	} 	td[class="edu-collapse"]{ 	width: 0px !important; 	} 	td[class="edu-space"]{ 	height: 10px !important; 	background-color: #f5f8fa; 	} 	td[class="mobile-height"]{ 	height: 30px !important; 	} 	} 	@media only screen and (max-width: 420px) { 	span[class="address"] a { 	line-height:18px !important; 	} 	td[class="margins"]{ 	width:18px !important; 	} 	td[class="edu-margins"]{ 	width:18px !important; 	} 	td[class="logo_space"]{ 	height:12px !important; 				} 			}  			@media only screen and (max-width: 480px) { 				table[class="collapse"]{ 					width:100% !important; 				} 				table[class="edu-module"]{ 					width:100% !important; 				} 				span[class="address"]{ 					display:block !important; 					width:240px !important; 				} 				td[class="cut"]{ 					display:none !important; 				} 			} 		</style>	</head>	<body bgcolor="#FAFAFA" style="margin:0;padding:0;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;">		<table cellpadding="0" cellspacing="0" border="0" width="100%" bgcolor="#FAFAFA" style="background-color:#FAFAFA;padding:0;margin:0;line-height:1px;font-size:1px;" class="body_wrapper">			<tbody>				<tr>					<td align="center" style="padding:0;margin:0;line-height:1px;font-size:1px;">						<table class="collapse" id="header" align="center" width="650" style="width: 650px;padding:0;margin:0;line-height:1px;font-size:1px;" bgcolor="#ffffff" cellpadding="0" cellspacing="0" border="0">							<tbody>								<tr>									<td style="min-width: 650px;height:1px;padding:0;margin:0;line-height:1px;font-size:1px;" class="cut"></td>								</tr>							</tbody>						</table> 					</td>				</tr>				<tr>					<td align="center" style="padding:0;margin:0;line-height:1px;font-size:1px;">					<!--///////////////////header///////////////////////////-->						<table class="collapse" id="header" align="center" width="650" style="width:650px;background-color:#ffffff;padding:0;margin:0;line-height:1px;font-size:1px;" bgcolor="#ffffff" cellpadding="0" cellspacing="0" border="0">							<tbody>								<tr>									<td height="15" style="height:15px;padding:0;margin:0;line-height:1px;font-size:1px;" class="logo_space">&nbsp; </td>								</tr>								<tr>									<td style="padding:0;margin:0;line-height:1px;font-size:1px;">										<table cellpadding="0" cellspacing="0" border="0" align="left" style="padding:0;margin:0;line-height:1px;font-size:1px;">											<tbody>												<tr align="left">													<td align="left" width="15" style="width:15px;padding:0;margin:0;line-height:1px;font-size:1px;"></td>													<td align="left" width="28" style="padding:0;margin:0;line-height:1px;font-size:1px;"></td>													<td align="left" width="10" style="width:10px;padding:0;margin:0;line-height:1px;font-size:1px;"></td>													<td align="left" class="greeting" style="padding:0;margin:0;line-height:1px;font-size:1px;font-family:''Helvetica Neue Light'', Helvetica, Arial, sans-serif;-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:none;color:#66757f;font-size:16px;padding:0px;margin:0px;font-weight:300;line-height:100%;text-align:left;">														Dear ${TRIPOIN.CONTENT.FULLNAME}, 													</td>													<td width="50" style="width:50px;padding:0;margin:0;line-height:1px;font-size:1px;" align="left"></td>												</tr>											</tbody>										</table> 									</td>								</tr>								<tr>								<td height="14" style="height:14px;padding:0;margin:0;line-height:1px;font-size:1px;" class="logo_space">&nbsp; </td>								</tr>							</tbody>						</table>						<!--////////////////////border//////////////////////////-->						<table class="collapse" align="center" width="650" style="width:650px;background-color:#ffffff;padding:0;margin:0;line-height:1px;font-size:1px;" cellpadding="0" cellspacing="0" border="0">							<tbody>								<tr id="border">									<td colspan="2" height="1" style="line-height:1px;display:block;height:1px;background-color:#FAFAFA;padding:0;margin:0;line-height:1px;font-size:1px;"></td>								</tr>							</tbody>						</table>						<!--//////////////////////////////////////////////-->						<table class="collapse" align="center" width="650" style="width:650px;background-color:#ffffff;padding:0;margin:0;line-height:1px;font-size:1px;" cellpadding="0" cellspacing="0" border="0">							<tbody>								<tr>								<td width="50" style="width:50px;padding:0;margin:0;line-height:1px;font-size:1px;" class="margins"></td>								<td align="center" style="padding:0;margin:0;line-height:1px;font-size:1px;">									<table width="100%" align="center" cellpadding="0" cellspacing="0" border="0" class="collapse" style="padding:0;margin:0;line-height:1px;font-size:1px;">										<tbody>										<tr>											<td height="30" style="height:45px;padding:0;margin:0;line-height:1px;font-size:1px;"></td>										</tr>										<tr>											<td align="left" class="display" style="padding:0;margin:0;line-height:1px;font-size:1px;font-family:''Helvetica Neue'', Helvetica, Arial, sans-serif;font-size:22px;line-height:30px;font-weight:300;-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:none;text-align:left;color:#292f33;">												We received a request to reset the password for this account.											</td>										</tr>										<tr>											<td height="30" style="height:45px;padding:0;margin:0;line-height:1px;font-size:1px;"></td>										</tr>										<tr>											<td align="left" class="body-text" style="padding:0;margin:0;line-height:1px;font-size:1px;font-family:''Helvetica Neue'', Helvetica, Arial, sans-serif;font-size:16px;line-height:22px;font-weight:400;-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:none;text-align:left;color:#292f33;">												Username : <span style="text-decoration:none;border-style:none;border:0;padding:0;margin:0;border:none;text-decoration:none;font-weight:400;color:#1879DB;">${TRIPOIN.CONTENT.USERNAME}</span>											</td>										</tr>										<tr>											<td align="left" class="body-text" style="padding:0;margin:0;line-height:1px;font-size:1px;font-family:''Helvetica Neue'', Helvetica, Arial, sans-serif;font-size:16px;line-height:22px;font-weight:400;-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:none;text-align:left;color:#292f33;">												Password : <span style="text-decoration:none;border-style:none;border:0;padding:0;margin:0;border:none;text-decoration:none;font-weight:400;color:#1879DB;">${TRIPOIN.CONTENT.PASSWORD}</span>											</td>										</tr>										<tr>											<td height="30" style="height:15px;padding:0;margin:0;line-height:1px;font-size:1px;"></td>										</tr>										<tr>											<td align="left" class="body-text" style="padding:0;margin:0;line-height:1px;font-size:1px;font-family:''Helvetica Neue'', Helvetica, Arial, sans-serif;font-size:16px;line-height:22px;font-weight:400;-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:none;text-align:left;color:#292f33;">												Please use this new password to login and then change your password to something more to your liking.											</td>										</tr>										<tr>											<td height="25" style="height:25px;padding:0;margin:0;line-height:1px;font-size:1px;"></td>										</tr>										<tr>											<td height="55" style="height:55px;padding:0;margin:0;line-height:1px;font-size:1px;"></td>										</tr>										</tbody>									</table> 								</td>								<td width="50" style="width:50px;padding:0;margin:0;line-height:1px;font-size:1px;" class="margins"></td>								</tr>							</tbody>						</table>						<!--//////////////////////////////////////////////-->						<table class="collapse" id="footer" align="center" width="650" style="width:650px;background-color:#ffffff;padding:0;margin:0;line-height:1px;font-size:1px;" cellpadding="0" cellspacing="0" border="0">							<tbody>								<tr>									<td height="1" style="line-height:1px;display:block;height:1px;background-color:#FAFAFA;padding:0;margin:0;line-height:1px;font-size:1px;"></td>								</tr>								<tr>									<td height="20" style="height:20;padding:0;margin:0;line-height:1px;font-size:1px;"></td>								</tr>								<tr>									<td align="center" style="padding:0;margin:0;line-height:1px;font-size:1px;"><span class="footer_type" style="font-family:''Helvetica Neue Light'', Helvetica, Arial, sans-serif;-webkit-font-smoothing:antialiased;color:#8899a6;font-size:12px;padding:0px;margin:0px;font-weight:normal;line-height:12px;"> <a href="http://www.tripoin.co.id" class="footer_link" style="text-decoration:none;border-style:none;border:0;padding:0;margin:0;font-family:''Helvetica Neue Light'', Helvetica, Arial, sans-serif;-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:none;color:#1879DB;font-size:12px;padding:0px;margin:0px;font-weight:600;line-height:12px;">Tripoin, Inc.</a> </span></td>								</tr>								<tr>									<td height="10" style="height:10px;line-height:1px;font-size:1px;padding:0;margin:0;line-height:1px;font-size:1px;"></td>								</tr>								<tr>									<td align="center" style="padding:0;margin:0;line-height:1px;font-size:1px;"><span class="address"><a href="" style="text-decoration:none;border-style:none;border:0;padding:0;margin:0;font-family:''Helvetica Neue Light'', Helvetica, Arial, sans-serif;-webkit-font-smoothing:antialiased;color:#8899a6;font-size:12px;padding:0px;margin:0px;font-weight:normal;line-height:12px;cursor:default;">Indonesia, Jakarta</a></span></td>								</tr>								<tr>									<td height="26" style="height:26;padding:0;margin:0;line-height:1px;font-size:1px;"></td>								</tr>							</tbody>						</table>					 					</td>				</tr>			</tbody>		</table>	</body></html>', 1, 'Body Email Verify Forgot Password', 'admin', '127.0.0.1', '2015-11-01 17:22:52', NULL, NULL, NULL, NULL, NULL),
(5, 'TRIPOIN.EMAIL.NEW.USER.SUBJECT', 'Tripoin Sales Management Account', 1, 'Subject Email New User', 'admin', '127.0.0.1', '2016-03-08 11:38:21', NULL, NULL, NULL, NULL, NULL),
(6, 'TRIPOIN.EMAIL.NEW.USER.BODY.MESSAGE', '<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd"><html> 	<head>	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />	<meta name="apple-mobile-web-app-capable" content="yes" />	<style type="text/css">	@media only screen and (max-width: 320px) { 	table[class="edu-module"]{ 	border-radius: 0px !important; 	-webkit-border-radius: 0px !important; 	-moz-border-radius: 0px !important; 	} 	td[class="edu-margins"]{ 	background-color: #f5f8fa; 	} 	td[class="edu-collapse"]{ 	width: 0px !important; 	} 	td[class="edu-space"]{ 	height: 10px !important; 	background-color: #f5f8fa; 	} 	td[class="mobile-height"]{ 	height: 30px !important; 	} 	} 	@media only screen and (max-width: 420px) { 	span[class="address"] a { 	line-height:18px !important; 	} 	td[class="margins"]{ 	width:18px !important; 	} 	td[class="edu-margins"]{ 	width:18px !important; 	} 	td[class="logo_space"]{ 	height:12px !important; 				} 			}  			@media only screen and (max-width: 480px) { 				table[class="collapse"]{ 					width:100% !important; 				} 				table[class="edu-module"]{ 					width:100% !important; 				} 				span[class="address"]{ 					display:block !important; 					width:240px !important; 				} 				td[class="cut"]{ 					display:none !important; 				} 			} 		</style>	</head>	<body bgcolor="#FAFAFA" style="margin:0;padding:0;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;">		<table cellpadding="0" cellspacing="0" border="0" width="100%" bgcolor="#FAFAFA" style="background-color:#FAFAFA;padding:0;margin:0;line-height:1px;font-size:1px;" class="body_wrapper">			<tbody>				<tr>					<td align="center" style="padding:0;margin:0;line-height:1px;font-size:1px;">						<table class="collapse" id="header" align="center" width="650" style="width: 650px;padding:0;margin:0;line-height:1px;font-size:1px;" bgcolor="#ffffff" cellpadding="0" cellspacing="0" border="0">							<tbody>								<tr>									<td style="min-width: 650px;height:1px;padding:0;margin:0;line-height:1px;font-size:1px;" class="cut"></td>								</tr>							</tbody>						</table> 					</td>				</tr>				<tr>					<td align="center" style="padding:0;margin:0;line-height:1px;font-size:1px;">					<!--///////////////////header///////////////////////////-->						<table class="collapse" id="header" align="center" width="650" style="width:650px;background-color:#ffffff;padding:0;margin:0;line-height:1px;font-size:1px;" bgcolor="#ffffff" cellpadding="0" cellspacing="0" border="0">							<tbody>								<tr>									<td height="15" style="height:15px;padding:0;margin:0;line-height:1px;font-size:1px;" class="logo_space">&nbsp; </td>								</tr>								<tr>									<td style="padding:0;margin:0;line-height:1px;font-size:1px;">										<table cellpadding="0" cellspacing="0" border="0" align="left" style="padding:0;margin:0;line-height:1px;font-size:1px;">											<tbody>												<tr align="left">													<td align="left" width="15" style="width:15px;padding:0;margin:0;line-height:1px;font-size:1px;"></td>													<td align="left" width="28" style="padding:0;margin:0;line-height:1px;font-size:1px;"></td>													<td align="left" width="10" style="width:10px;padding:0;margin:0;line-height:1px;font-size:1px;"></td>													<td align="left" class="greeting" style="padding:0;margin:0;line-height:1px;font-size:1px;font-family:''Helvetica Neue Light'', Helvetica, Arial, sans-serif;-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:none;color:#66757f;font-size:16px;padding:0px;margin:0px;font-weight:300;line-height:100%;text-align:left;">														Dear ${TRIPOIN.CONTENT.FULLNAME}, 													</td>													<td width="50" style="width:50px;padding:0;margin:0;line-height:1px;font-size:1px;" align="left"></td>												</tr>											</tbody>										</table> 									</td>								</tr>								<tr>								<td height="14" style="height:14px;padding:0;margin:0;line-height:1px;font-size:1px;" class="logo_space">&nbsp; </td>								</tr>							</tbody>						</table>						<!--////////////////////border//////////////////////////-->						<table class="collapse" align="center" width="650" style="width:650px;background-color:#ffffff;padding:0;margin:0;line-height:1px;font-size:1px;" cellpadding="0" cellspacing="0" border="0">							<tbody>								<tr id="border">									<td colspan="2" height="1" style="line-height:1px;display:block;height:1px;background-color:#FAFAFA;padding:0;margin:0;line-height:1px;font-size:1px;"></td>								</tr>							</tbody>						</table>						<!--//////////////////////////////////////////////-->						<table class="collapse" align="center" width="650" style="width:650px;background-color:#ffffff;padding:0;margin:0;line-height:1px;font-size:1px;" cellpadding="0" cellspacing="0" border="0">							<tbody>								<tr>								<td width="50" style="width:50px;padding:0;margin:0;line-height:1px;font-size:1px;" class="margins"></td>								<td align="center" style="padding:0;margin:0;line-height:1px;font-size:1px;">									<table width="100%" align="center" cellpadding="0" cellspacing="0" border="0" class="collapse" style="padding:0;margin:0;line-height:1px;font-size:1px;">										<tbody>										<tr>											<td height="30" style="height:45px;padding:0;margin:0;line-height:1px;font-size:1px;"></td>										</tr>										<tr>											<td align="left" class="display" style="padding:0;margin:0;line-height:1px;font-size:1px;font-family:''Helvetica Neue'', Helvetica, Arial, sans-serif;font-size:22px;line-height:30px;font-weight:300;-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:none;text-align:left;color:#292f33;">												We received a request to create account in Tripoin Sales Management.											</td>										</tr>										<tr>											<td height="30" style="height:45px;padding:0;margin:0;line-height:1px;font-size:1px;"></td>										</tr>										<tr>											<td align="left" class="body-text" style="padding:0;margin:0;line-height:1px;font-size:1px;font-family:''Helvetica Neue'', Helvetica, Arial, sans-serif;font-size:16px;line-height:22px;font-weight:400;-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:none;text-align:left;color:#292f33;">												Username : <span style="text-decoration:none;border-style:none;border:0;padding:0;margin:0;border:none;text-decoration:none;font-weight:400;color:#1879DB;">${TRIPOIN.CONTENT.USERNAME}</span>											</td>										</tr>										<tr>											<td align="left" class="body-text" style="padding:0;margin:0;line-height:1px;font-size:1px;font-family:''Helvetica Neue'', Helvetica, Arial, sans-serif;font-size:16px;line-height:22px;font-weight:400;-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:none;text-align:left;color:#292f33;">												Password : <span style="text-decoration:none;border-style:none;border:0;padding:0;margin:0;border:none;text-decoration:none;font-weight:400;color:#1879DB;">${TRIPOIN.CONTENT.PASSWORD}</span>											</td>										</tr>										<tr>											<td height="30" style="height:15px;padding:0;margin:0;line-height:1px;font-size:1px;"></td>										</tr>										<tr>											<td align="left" class="body-text" style="padding:0;margin:0;line-height:1px;font-size:1px;font-family:''Helvetica Neue'', Helvetica, Arial, sans-serif;font-size:16px;line-height:22px;font-weight:400;-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:none;text-align:left;color:#292f33;">												Please use this new password to login and then change your password to something more to your liking.											</td>										</tr>										<tr>											<td height="25" style="height:25px;padding:0;margin:0;line-height:1px;font-size:1px;"></td>										</tr>										<tr>											<td height="55" style="height:55px;padding:0;margin:0;line-height:1px;font-size:1px;"></td>										</tr>										</tbody>									</table> 								</td>								<td width="50" style="width:50px;padding:0;margin:0;line-height:1px;font-size:1px;" class="margins"></td>								</tr>							</tbody>						</table>						<!--//////////////////////////////////////////////-->						<table class="collapse" id="footer" align="center" width="650" style="width:650px;background-color:#ffffff;padding:0;margin:0;line-height:1px;font-size:1px;" cellpadding="0" cellspacing="0" border="0">							<tbody>								<tr>									<td height="1" style="line-height:1px;display:block;height:1px;background-color:#FAFAFA;padding:0;margin:0;line-height:1px;font-size:1px;"></td>								</tr>								<tr>									<td height="20" style="height:20;padding:0;margin:0;line-height:1px;font-size:1px;"></td>								</tr>								<tr>									<td align="center" style="padding:0;margin:0;line-height:1px;font-size:1px;"><span class="footer_type" style="font-family:''Helvetica Neue Light'', Helvetica, Arial, sans-serif;-webkit-font-smoothing:antialiased;color:#8899a6;font-size:12px;padding:0px;margin:0px;font-weight:normal;line-height:12px;"> <a href="http://www.tripoin.co.id" class="footer_link" style="text-decoration:none;border-style:none;border:0;padding:0;margin:0;font-family:''Helvetica Neue Light'', Helvetica, Arial, sans-serif;-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:none;color:#1879DB;font-size:12px;padding:0px;margin:0px;font-weight:600;line-height:12px;">Tripoin, Inc.</a> </span></td>								</tr>								<tr>									<td height="10" style="height:10px;line-height:1px;font-size:1px;padding:0;margin:0;line-height:1px;font-size:1px;"></td>								</tr>								<tr>									<td align="center" style="padding:0;margin:0;line-height:1px;font-size:1px;"><span class="address"><a href="" style="text-decoration:none;border-style:none;border:0;padding:0;margin:0;font-family:''Helvetica Neue Light'', Helvetica, Arial, sans-serif;-webkit-font-smoothing:antialiased;color:#8899a6;font-size:12px;padding:0px;margin:0px;font-weight:normal;line-height:12px;cursor:default;">Indonesia, Jakarta</a></span></td>								</tr>								<tr>									<td height="26" style="height:26;padding:0;margin:0;line-height:1px;font-size:1px;"></td>								</tr>							</tbody>						</table>					 					</td>				</tr>			</tbody>		</table>	</body></html>', 1, 'Body Email New User', 'admin', '127.0.0.1', '2016-03-08 11:46:51', NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `trx_availability`
--

CREATE TABLE IF NOT EXISTS `trx_availability` (
`availability_id` bigint(20) NOT NULL,
  `availability_code` varchar(150) NOT NULL,
  `availability_name` varchar(255) NOT NULL,
  `availability_value` varchar(255) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `trx_availability`
--

INSERT INTO `trx_availability` (`availability_id`, `availability_code`, `availability_name`, `availability_value`) VALUES
(1, 'READYSTOCK', 'Ready Stock', 'Ready Stock'),
(2, 'INDENT', 'Indent', 'Indent'),
(3, 'WAITINGFORATTACHMENT', 'Waiting for Attachment', 'Waiting for Attachment'),
(4, 'WAITINGFORINCOMING', 'Waiting for Incoming', 'Waiting for Incoming');

-- --------------------------------------------------------

--
-- Table structure for table `trx_balance_status`
--

CREATE TABLE IF NOT EXISTS `trx_balance_status` (
`balance_status_id` bigint(20) NOT NULL,
  `balance_status_code` varchar(150) NOT NULL,
  `balance_status_name` varchar(255) NOT NULL,
  `balance_status_value` varchar(255) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `trx_balance_status`
--

INSERT INTO `trx_balance_status` (`balance_status_id`, `balance_status_code`, `balance_status_name`, `balance_status_value`) VALUES
(1, 'AVAILABLE', 'Available', 'Available'),
(2, 'ONPROCESSLEASING', 'On Process Leasing', 'On Process Leasing'),
(3, 'LEASINGREADY', 'Leasing Ready', 'Leasing Ready'),
(4, 'CASHBEFOREDELIVERY', 'Cash Before Delivery', 'Cash Before Delivery'),
(5, 'OPENACCOUNT', 'Open Account', 'Open Account');

-- --------------------------------------------------------

--
-- Table structure for table `trx_call_plan_detail`
--

CREATE TABLE IF NOT EXISTS `trx_call_plan_detail` (
`call_plan_detail_id` bigint(20) NOT NULL,
  `call_plan_header_id` bigint(20) NOT NULL,
  `call_plan_detail_code` varchar(150) NOT NULL,
  `call_plan_detail_approved` varchar(150) NOT NULL DEFAULT 'N',
  `call_plan_detail_approved_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` smallint(5) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `created_by` varchar(150) DEFAULT 'admin',
  `created_ip` varchar(150) DEFAULT '127.0.0.1',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_platform` varchar(255) DEFAULT NULL,
  `modified_by` varchar(150) DEFAULT NULL,
  `modified_ip` varchar(150) DEFAULT NULL,
  `modified_time` timestamp NULL DEFAULT NULL,
  `modified_platform` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `trx_call_plan_header`
--

CREATE TABLE IF NOT EXISTS `trx_call_plan_header` (
`call_plan_header_id` bigint(20) NOT NULL,
  `employee_id` bigint(20) NOT NULL,
  `employee_parent_current_id` bigint(20) NOT NULL,
  `call_plan_header_code` varchar(150) NOT NULL,
  `call_plan_header_start_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `call_plan_header_end_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `call_plan_header_next_call_date` timestamp NULL DEFAULT NULL,
  `status` smallint(5) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `created_by` varchar(150) DEFAULT 'admin',
  `created_ip` varchar(150) DEFAULT '127.0.0.1',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_platform` varchar(255) DEFAULT NULL,
  `modified_by` varchar(150) DEFAULT NULL,
  `modified_ip` varchar(150) DEFAULT NULL,
  `modified_time` timestamp NULL DEFAULT NULL,
  `modified_platform` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `trx_call_plan_list`
--

CREATE TABLE IF NOT EXISTS `trx_call_plan_list` (
`call_plan_list_id` bigint(20) NOT NULL,
  `call_plan_detail_id` bigint(20) NOT NULL,
  `user_route_id` bigint(20) NOT NULL,
  `customer_id` bigint(20) NOT NULL,
  `customer_status_id` bigint(20) NOT NULL,
  `placeofvisit_id` bigint(20) NOT NULL,
  `call_plan_list_code` varchar(150) NOT NULL,
  `call_plan_list_purpose` text,
  `call_plan_list_foto` varchar(255) DEFAULT NULL,
  `status` smallint(5) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `created_by` varchar(150) DEFAULT 'admin',
  `created_ip` varchar(150) DEFAULT '127.0.0.1',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_platform` varchar(255) DEFAULT NULL,
  `modified_by` varchar(150) DEFAULT NULL,
  `modified_ip` varchar(150) DEFAULT NULL,
  `modified_time` timestamp NULL DEFAULT NULL,
  `modified_platform` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `trx_call_report`
--

CREATE TABLE IF NOT EXISTS `trx_call_report` (
`call_report_id` bigint(20) NOT NULL,
  `call_report_refno` varchar(150) NOT NULL,
  `call_plan_list_id` bigint(20) NOT NULL,
  `segment_id` bigint(20) NOT NULL,
  `project_id` bigint(20) NOT NULL,
  `call_report_delivery_plan_json` text,
  `call_report_isdeal` smallint(5) NOT NULL DEFAULT '0',
  `status` smallint(5) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `created_by` varchar(150) DEFAULT 'admin',
  `created_ip` varchar(150) DEFAULT '127.0.0.1',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_platform` varchar(255) DEFAULT NULL,
  `modified_by` varchar(150) DEFAULT NULL,
  `modified_ip` varchar(150) DEFAULT NULL,
  `modified_time` timestamp NULL DEFAULT NULL,
  `modified_platform` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `trx_call_report_product`
--

CREATE TABLE IF NOT EXISTS `trx_call_report_product` (
`call_report_product_id` bigint(20) NOT NULL,
  `call_report_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `trx_cis`
--

CREATE TABLE IF NOT EXISTS `trx_cis` (
`cis_id` bigint(20) NOT NULL,
  `cis_code` varchar(150) NOT NULL,
  `cis_name` varchar(255) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `trx_cis`
--

INSERT INTO `trx_cis` (`cis_id`, `cis_code`, `cis_name`) VALUES
(1, 'VERBALNOTTRUSTEDANDORINCOMPLETE', 'Verbal - Not Trusted and/or Incomplete'),
(2, 'VERBALTRUSTEDANDCOMPLETE', 'Verbal - Trusted and Complete'),
(3, 'COPYOFQUOTE/PO/INVOICEAVAILABLEATTACHED', 'Copy of Quote/PO/Invoice Available/Attached'),
(4, 'NANOCOMPETITORPRICINGAVAILABLE', 'Na - No Competitor Pricing Available');

-- --------------------------------------------------------

--
-- Table structure for table `trx_competitor`
--

CREATE TABLE IF NOT EXISTS `trx_competitor` (
`competitor_id` bigint(20) NOT NULL,
  `competitor_code` varchar(150) NOT NULL,
  `competitor_name` varchar(255) NOT NULL,
  `competitor_description` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `trx_customer_status`
--

CREATE TABLE IF NOT EXISTS `trx_customer_status` (
`customer_status_id` bigint(20) NOT NULL,
  `customer_status_code` varchar(150) NOT NULL,
  `customer_status_name` varchar(255) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Dumping data for table `trx_customer_status`
--

INSERT INTO `trx_customer_status` (`customer_status_id`, `customer_status_code`, `customer_status_name`) VALUES
(1, 'INTRODUCTION', 'Introduction'),
(2, 'PROSPECT/NEED', 'Prospect / Need'),
(3, 'REGULERVISIT', 'Reguler Visit'),
(4, 'QUOTATION', 'Quotation'),
(5, 'FOLLOWUP', 'Follow Up'),
(6, 'PJB/PO', 'PJB / PO'),
(7, 'DPCOLLECTION', 'DP Collection'),
(8, 'POLEASING', 'PO Leasing'),
(9, 'DELIVERY', 'Delivery'),
(10, 'BAST', 'BAST'),
(11, 'PAYMENTCOLLECTION', 'Payment Collection'),
(12, 'LOST', 'Lost');

-- --------------------------------------------------------

--
-- Table structure for table `trx_deal_report`
--

CREATE TABLE IF NOT EXISTS `trx_deal_report` (
`deal_report_id` bigint(20) NOT NULL,
  `call_report_id` bigint(20) NOT NULL,
  `dp_status_id` bigint(20) NOT NULL,
  `balance_status_id` bigint(20) NOT NULL,
  `availabilty_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `trx_dp_status`
--

CREATE TABLE IF NOT EXISTS `trx_dp_status` (
`dp_status_id` bigint(20) NOT NULL,
  `dp_status_code` varchar(150) NOT NULL,
  `dp_status_name` varchar(255) NOT NULL,
  `dp_status_value` varchar(255) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `trx_dp_status`
--

INSERT INTO `trx_dp_status` (`dp_status_id`, `dp_status_code`, `dp_status_name`, `dp_status_value`) VALUES
(1, 'PAID', 'Paid', 'Paid'),
(2, 'NOTYET', 'Not Yet', 'Not Yet');

-- --------------------------------------------------------

--
-- Table structure for table `trx_lost_reason`
--

CREATE TABLE IF NOT EXISTS `trx_lost_reason` (
`lost_reason_id` bigint(20) NOT NULL,
  `lost_eason_code` varchar(150) NOT NULL,
  `lost_reason_name` varchar(255) NOT NULL,
  `lost_reason_description` text
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Dumping data for table `trx_lost_reason`
--

INSERT INTO `trx_lost_reason` (`lost_reason_id`, `lost_eason_code`, `lost_reason_name`, `lost_reason_description`) VALUES
(1, 'DEALER/DISTRIBUTOR', 'Dealer / Distributor', 'Dealer / Distributor'),
(2, 'BRAND', 'Brand', 'Brand'),
(3, 'QTYXMODEL', 'QTY X Model', 'QTY X Model'),
(4, 'ATTACHMENT', 'Attachment (eg : OFT, Vacum, Scow End, etc)', 'Attachment (eg : OFT, Vacum, Scow End, etc)'),
(5, 'PRODUCTSOURCE', 'Product Source', 'Product Source'),
(6, 'PRICE', 'Price (Exclude VAT)', 'Price (Exclude VAT)'),
(7, 'TERMOFDELIVERY', 'Term of Delivery (DDP, FCA, CIF)', 'Term of Delivery (DDP, FCA, CIF)'),
(8, 'ULTIMATEDESTINATION', 'Ultimate Destination', 'Ultimate Destination'),
(9, 'TRANSACTION/SIGNIN', 'Transaction / Sign In', 'Transaction / Sign In'),
(10, 'TRANSACTIONDATE', 'Transaction Date', 'Transaction Date'),
(11, 'DELIVERYDATE', 'Delivery Date', 'Delivery Date'),
(12, 'TERMOFPAYMENT', 'Term of Payment', 'Term of Payment'),
(13, 'FINANCEDBY', 'Financed By', 'Financed By'),
(14, 'AWARDTO', 'Award To', 'Award To'),
(15, 'LOSTSALEREASON', 'Lost Sale Reason', 'Lost Sale Reason');

-- --------------------------------------------------------

--
-- Table structure for table `trx_lost_report`
--

CREATE TABLE IF NOT EXISTS `trx_lost_report` (
`lost_report_id` bigint(20) NOT NULL,
  `call_report_id` bigint(20) NOT NULL,
  `cis_id` bigint(20) NOT NULL,
  `status` smallint(5) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `created_by` varchar(150) DEFAULT 'admin',
  `created_ip` varchar(150) DEFAULT '127.0.0.1',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_platform` varchar(255) DEFAULT NULL,
  `modified_by` varchar(150) DEFAULT NULL,
  `modified_ip` varchar(150) DEFAULT NULL,
  `modified_time` timestamp NULL DEFAULT NULL,
  `modified_platform` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `trx_lost_report_competitor`
--

CREATE TABLE IF NOT EXISTS `trx_lost_report_competitor` (
`lost_report_competitor_id` bigint(20) NOT NULL,
  `lost_report_id` bigint(20) NOT NULL,
  `competitor_id` bigint(20) NOT NULL,
  `lost_reason_id` bigint(20) NOT NULL,
  `lost_report_competitor_description` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `trx_placeofvisit`
--

CREATE TABLE IF NOT EXISTS `trx_placeofvisit` (
`placeofvisit_id` bigint(20) NOT NULL,
  `placeofvisit_code` varchar(150) NOT NULL,
  `placeofvisit_name` varchar(255) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `trx_placeofvisit`
--

INSERT INTO `trx_placeofvisit` (`placeofvisit_id`, `placeofvisit_code`, `placeofvisit_name`) VALUES
(1, 'OFFICE', 'Office'),
(2, 'JOB/FIELDSITE', 'Job / Field Site'),
(3, 'HOME', 'Home'),
(4, 'DEALER(CUSTOMERCAMETOCHAKRAJAWARAOFFICE)', 'Dealer (Customer Came to Chakra Jawara Office)'),
(5, 'OTHERS', 'Others');

-- --------------------------------------------------------

--
-- Table structure for table `trx_quotation`
--

CREATE TABLE IF NOT EXISTS `trx_quotation` (
`quotation_id` bigint(20) NOT NULL,
  `quotation_refno` varchar(150) NOT NULL,
  `quotation_price` varchar(150) NOT NULL,
  `quotation_place` varchar(255) NOT NULL DEFAULT 'Jakarta',
  `quotation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `call_report_id` bigint(20) NOT NULL,
  `status` smallint(5) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `created_by` varchar(150) DEFAULT 'admin',
  `created_ip` varchar(150) DEFAULT '127.0.0.1',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_platform` varchar(255) DEFAULT NULL,
  `modified_by` varchar(150) DEFAULT NULL,
  `modified_ip` varchar(150) DEFAULT NULL,
  `modified_time` timestamp NULL DEFAULT NULL,
  `modified_platform` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `vcs_table`
--

CREATE TABLE IF NOT EXISTS `vcs_table` (
`vcs_table_id` bigint(20) NOT NULL,
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
  `vcs_table_modified_platform` varchar(255) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `vcs_table`
--

INSERT INTO `vcs_table` (`vcs_table_id`, `vcs_table_code`, `vcs_table_total_row`, `vcs_table_status`, `vcs_table_remarks`, `vcs_table_created_by`, `vcs_table_created_ip`, `vcs_table_created_time`, `vcs_table_created_platform`, `vcs_table_modified_by`, `vcs_table_modified_ip`, `vcs_table_modified_time`, `vcs_table_modified_platform`) VALUES
(1, 'mst_employee', 3, 1, 'Tabel of mst_employee', 'admin', '127.0.0.1', '2016-01-04 02:43:24', NULL, NULL, NULL, NULL, NULL),
(2, 'mst_occupation', 3, 1, 'Table of mst_occupation', 'admin', '127.0.0.1', '2016-01-04 02:43:24', NULL, NULL, NULL, NULL, NULL),
(3, 'mst_area', 36, 1, 'Table of mst_area', 'admin', '127.0.0.1', '2016-01-04 02:43:24', '', '', '', '2016-02-11 14:35:11', '');

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
-- Indexes for table `geo_user_route`
--
ALTER TABLE `geo_user_route`
 ADD PRIMARY KEY (`user_route_id`);

--
-- Indexes for table `mst_area`
--
ALTER TABLE `mst_area`
 ADD PRIMARY KEY (`area_id`), ADD UNIQUE KEY `occupation_code` (`area_code`);

--
-- Indexes for table `mst_bussiness_sector`
--
ALTER TABLE `mst_bussiness_sector`
 ADD PRIMARY KEY (`bussiness_sector_id`), ADD UNIQUE KEY `bussiness_sector_code` (`bussiness_sector_code`);

--
-- Indexes for table `mst_country`
--
ALTER TABLE `mst_country`
 ADD PRIMARY KEY (`country_id`), ADD UNIQUE KEY `country_code` (`country_code`);

--
-- Indexes for table `mst_customer`
--
ALTER TABLE `mst_customer`
 ADD PRIMARY KEY (`customer_id`), ADD UNIQUE KEY `customer_code` (`customer_code`);

--
-- Indexes for table `mst_customer_financing`
--
ALTER TABLE `mst_customer_financing`
 ADD PRIMARY KEY (`customer_financing_id`);

--
-- Indexes for table `mst_customer_operational`
--
ALTER TABLE `mst_customer_operational`
 ADD PRIMARY KEY (`customer_operational_id`);

--
-- Indexes for table `mst_customer_pic`
--
ALTER TABLE `mst_customer_pic`
 ADD PRIMARY KEY (`customer_pic_id`);

--
-- Indexes for table `mst_customer_project`
--
ALTER TABLE `mst_customer_project`
 ADD PRIMARY KEY (`customer_project_id`);

--
-- Indexes for table `mst_customer_site`
--
ALTER TABLE `mst_customer_site`
 ADD PRIMARY KEY (`customer_site_id`);

--
-- Indexes for table `mst_customer_type`
--
ALTER TABLE `mst_customer_type`
 ADD PRIMARY KEY (`customer_type_id`), ADD UNIQUE KEY `customer_type_code` (`customer_type_code`);

--
-- Indexes for table `mst_employee`
--
ALTER TABLE `mst_employee`
 ADD PRIMARY KEY (`employee_id`), ADD UNIQUE KEY `employee_code` (`employee_code`), ADD UNIQUE KEY `employee_nik` (`employee_nik`);

--
-- Indexes for table `mst_finance`
--
ALTER TABLE `mst_finance`
 ADD PRIMARY KEY (`finance_id`), ADD UNIQUE KEY `finance_code` (`finance_code`);

--
-- Indexes for table `mst_financing`
--
ALTER TABLE `mst_financing`
 ADD PRIMARY KEY (`financing_id`), ADD UNIQUE KEY `financing_code` (`financing_code`);

--
-- Indexes for table `mst_fptengine`
--
ALTER TABLE `mst_fptengine`
 ADD PRIMARY KEY (`fptengine_id`), ADD UNIQUE KEY `fptengine_code` (`fptengine_code`);

--
-- Indexes for table `mst_occupation`
--
ALTER TABLE `mst_occupation`
 ADD PRIMARY KEY (`occupation_id`), ADD UNIQUE KEY `occupation_code` (`occupation_code`);

--
-- Indexes for table `mst_pic`
--
ALTER TABLE `mst_pic`
 ADD PRIMARY KEY (`pic_id`), ADD UNIQUE KEY `pic_code` (`pic_code`);

--
-- Indexes for table `mst_product`
--
ALTER TABLE `mst_product`
 ADD PRIMARY KEY (`product_id`), ADD UNIQUE KEY `product_mapping_code` (`product_mapping_code`);

--
-- Indexes for table `mst_product_type`
--
ALTER TABLE `mst_product_type`
 ADD PRIMARY KEY (`product_type_id`), ADD UNIQUE KEY `type_product_code` (`product_type_code`);

--
-- Indexes for table `mst_profile`
--
ALTER TABLE `mst_profile`
 ADD PRIMARY KEY (`profile_id`), ADD UNIQUE KEY `profile_phone` (`profile_phone`), ADD UNIQUE KEY `profile_email` (`profile_email`), ADD UNIQUE KEY `user_id` (`user_id`);

--
-- Indexes for table `mst_project`
--
ALTER TABLE `mst_project`
 ADD PRIMARY KEY (`project_id`), ADD UNIQUE KEY `ext_project_code` (`project_code`);

--
-- Indexes for table `mst_project_target`
--
ALTER TABLE `mst_project_target`
 ADD PRIMARY KEY (`project_target_id`);

--
-- Indexes for table `mst_segment`
--
ALTER TABLE `mst_segment`
 ADD PRIMARY KEY (`segment_id`), ADD UNIQUE KEY `segment_product_code` (`segment_code`);

--
-- Indexes for table `mst_site`
--
ALTER TABLE `mst_site`
 ADD PRIMARY KEY (`site_id`), ADD UNIQUE KEY `site_code` (`site_code`);

--
-- Indexes for table `mst_spareparts`
--
ALTER TABLE `mst_spareparts`
 ADD PRIMARY KEY (`spareparts_id`), ADD UNIQUE KEY `spareparts_code` (`spareparts_code`);

--
-- Indexes for table `mst_target`
--
ALTER TABLE `mst_target`
 ADD PRIMARY KEY (`target_id`), ADD UNIQUE KEY `target_code` (`target_code`);

--
-- Indexes for table `mst_truck`
--
ALTER TABLE `mst_truck`
 ADD PRIMARY KEY (`truck_id`), ADD UNIQUE KEY `truck_code` (`truck_code`);

--
-- Indexes for table `mst_truck_axle`
--
ALTER TABLE `mst_truck_axle`
 ADD PRIMARY KEY (`truck_axle_id`), ADD UNIQUE KEY `axle_code` (`truck_axle_code`);

--
-- Indexes for table `mst_truck_model`
--
ALTER TABLE `mst_truck_model`
 ADD PRIMARY KEY (`truck_model_id`), ADD UNIQUE KEY `model_truck_code` (`truck_model_code`);

--
-- Indexes for table `mst_truck_type`
--
ALTER TABLE `mst_truck_type`
 ADD PRIMARY KEY (`truck_type_id`), ADD UNIQUE KEY `type_truck_code` (`truck_type_code`);

--
-- Indexes for table `mst_unit_brand`
--
ALTER TABLE `mst_unit_brand`
 ADD PRIMARY KEY (`unit_brand_id`), ADD UNIQUE KEY `unit_brand_code` (`unit_brand_code`);

--
-- Indexes for table `mst_unit_populate`
--
ALTER TABLE `mst_unit_populate`
 ADD PRIMARY KEY (`unit_populate_id`);

--
-- Indexes for table `mst_unit_type`
--
ALTER TABLE `mst_unit_type`
 ADD PRIMARY KEY (`unit_type_id`), ADD UNIQUE KEY `unit_type_code` (`unit_type_code`);

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
-- Indexes for table `sys_menu`
--
ALTER TABLE `sys_menu`
 ADD PRIMARY KEY (`menu_id`), ADD UNIQUE KEY `menu_code` (`menu_code`);

--
-- Indexes for table `sys_menu_role`
--
ALTER TABLE `sys_menu_role`
 ADD PRIMARY KEY (`menu_role_id`);

--
-- Indexes for table `sys_system_parameter`
--
ALTER TABLE `sys_system_parameter`
 ADD PRIMARY KEY (`system_parameter_id`), ADD UNIQUE KEY `system_parameter_code` (`system_parameter_code`);

--
-- Indexes for table `trx_availability`
--
ALTER TABLE `trx_availability`
 ADD PRIMARY KEY (`availability_id`), ADD UNIQUE KEY `availability_code` (`availability_code`);

--
-- Indexes for table `trx_balance_status`
--
ALTER TABLE `trx_balance_status`
 ADD PRIMARY KEY (`balance_status_id`), ADD UNIQUE KEY `balance_status_code` (`balance_status_code`);

--
-- Indexes for table `trx_call_plan_detail`
--
ALTER TABLE `trx_call_plan_detail`
 ADD PRIMARY KEY (`call_plan_detail_id`), ADD UNIQUE KEY `call_plan_detail_code` (`call_plan_detail_code`);

--
-- Indexes for table `trx_call_plan_header`
--
ALTER TABLE `trx_call_plan_header`
 ADD PRIMARY KEY (`call_plan_header_id`), ADD UNIQUE KEY `call_plan_header_code` (`call_plan_header_code`);

--
-- Indexes for table `trx_call_plan_list`
--
ALTER TABLE `trx_call_plan_list`
 ADD PRIMARY KEY (`call_plan_list_id`), ADD UNIQUE KEY `call_plan_list_code` (`call_plan_list_code`);

--
-- Indexes for table `trx_call_report`
--
ALTER TABLE `trx_call_report`
 ADD PRIMARY KEY (`call_report_id`), ADD UNIQUE KEY `call_report_refno` (`call_report_refno`);

--
-- Indexes for table `trx_call_report_product`
--
ALTER TABLE `trx_call_report_product`
 ADD PRIMARY KEY (`call_report_product_id`);

--
-- Indexes for table `trx_cis`
--
ALTER TABLE `trx_cis`
 ADD PRIMARY KEY (`cis_id`), ADD UNIQUE KEY `cis_code` (`cis_code`);

--
-- Indexes for table `trx_competitor`
--
ALTER TABLE `trx_competitor`
 ADD PRIMARY KEY (`competitor_id`), ADD UNIQUE KEY `competitor_code` (`competitor_code`);

--
-- Indexes for table `trx_customer_status`
--
ALTER TABLE `trx_customer_status`
 ADD PRIMARY KEY (`customer_status_id`), ADD UNIQUE KEY `customer_status_code` (`customer_status_code`);

--
-- Indexes for table `trx_deal_report`
--
ALTER TABLE `trx_deal_report`
 ADD PRIMARY KEY (`deal_report_id`);

--
-- Indexes for table `trx_dp_status`
--
ALTER TABLE `trx_dp_status`
 ADD PRIMARY KEY (`dp_status_id`), ADD UNIQUE KEY `dp_status_code` (`dp_status_code`);

--
-- Indexes for table `trx_lost_reason`
--
ALTER TABLE `trx_lost_reason`
 ADD PRIMARY KEY (`lost_reason_id`), ADD UNIQUE KEY `lost_other_reason_code` (`lost_eason_code`);

--
-- Indexes for table `trx_lost_report`
--
ALTER TABLE `trx_lost_report`
 ADD PRIMARY KEY (`lost_report_id`);

--
-- Indexes for table `trx_lost_report_competitor`
--
ALTER TABLE `trx_lost_report_competitor`
 ADD PRIMARY KEY (`lost_report_competitor_id`);

--
-- Indexes for table `trx_placeofvisit`
--
ALTER TABLE `trx_placeofvisit`
 ADD PRIMARY KEY (`placeofvisit_id`), ADD UNIQUE KEY `placeofvisit_code` (`placeofvisit_code`);

--
-- Indexes for table `trx_quotation`
--
ALTER TABLE `trx_quotation`
 ADD PRIMARY KEY (`quotation_id`), ADD UNIQUE KEY `quotation_refno` (`quotation_refno`);

--
-- Indexes for table `vcs_table`
--
ALTER TABLE `vcs_table`
 ADD PRIMARY KEY (`vcs_table_id`), ADD UNIQUE KEY `system_parameter_code` (`vcs_table_code`);

--
-- Indexes for table `vcs_user`
--
ALTER TABLE `vcs_user`
 ADD PRIMARY KEY (`vcs_user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `geo_user_route`
--
ALTER TABLE `geo_user_route`
MODIFY `user_route_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `mst_area`
--
ALTER TABLE `mst_area`
MODIFY `area_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=37;
--
-- AUTO_INCREMENT for table `mst_bussiness_sector`
--
ALTER TABLE `mst_bussiness_sector`
MODIFY `bussiness_sector_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `mst_country`
--
ALTER TABLE `mst_country`
MODIFY `country_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `mst_customer`
--
ALTER TABLE `mst_customer`
MODIFY `customer_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `mst_customer_financing`
--
ALTER TABLE `mst_customer_financing`
MODIFY `customer_financing_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `mst_customer_operational`
--
ALTER TABLE `mst_customer_operational`
MODIFY `customer_operational_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `mst_customer_pic`
--
ALTER TABLE `mst_customer_pic`
MODIFY `customer_pic_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `mst_customer_project`
--
ALTER TABLE `mst_customer_project`
MODIFY `customer_project_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `mst_customer_site`
--
ALTER TABLE `mst_customer_site`
MODIFY `customer_site_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `mst_customer_type`
--
ALTER TABLE `mst_customer_type`
MODIFY `customer_type_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `mst_employee`
--
ALTER TABLE `mst_employee`
MODIFY `employee_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `mst_finance`
--
ALTER TABLE `mst_finance`
MODIFY `finance_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `mst_financing`
--
ALTER TABLE `mst_financing`
MODIFY `financing_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `mst_fptengine`
--
ALTER TABLE `mst_fptengine`
MODIFY `fptengine_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `mst_occupation`
--
ALTER TABLE `mst_occupation`
MODIFY `occupation_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `mst_pic`
--
ALTER TABLE `mst_pic`
MODIFY `pic_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `mst_product`
--
ALTER TABLE `mst_product`
MODIFY `product_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `mst_product_type`
--
ALTER TABLE `mst_product_type`
MODIFY `product_type_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `mst_profile`
--
ALTER TABLE `mst_profile`
MODIFY `profile_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `mst_project`
--
ALTER TABLE `mst_project`
MODIFY `project_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `mst_project_target`
--
ALTER TABLE `mst_project_target`
MODIFY `project_target_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT ' ';
--
-- AUTO_INCREMENT for table `mst_segment`
--
ALTER TABLE `mst_segment`
MODIFY `segment_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `mst_site`
--
ALTER TABLE `mst_site`
MODIFY `site_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `mst_spareparts`
--
ALTER TABLE `mst_spareparts`
MODIFY `spareparts_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `mst_target`
--
ALTER TABLE `mst_target`
MODIFY `target_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `mst_truck`
--
ALTER TABLE `mst_truck`
MODIFY `truck_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table `mst_truck_axle`
--
ALTER TABLE `mst_truck_axle`
MODIFY `truck_axle_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `mst_truck_model`
--
ALTER TABLE `mst_truck_model`
MODIFY `truck_model_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `mst_truck_type`
--
ALTER TABLE `mst_truck_type`
MODIFY `truck_type_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `mst_unit_brand`
--
ALTER TABLE `mst_unit_brand`
MODIFY `unit_brand_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `mst_unit_populate`
--
ALTER TABLE `mst_unit_populate`
MODIFY `unit_populate_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `mst_unit_type`
--
ALTER TABLE `mst_unit_type`
MODIFY `unit_type_id` bigint(20) NOT NULL AUTO_INCREMENT;
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
--
-- AUTO_INCREMENT for table `sys_menu`
--
ALTER TABLE `sys_menu`
MODIFY `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=28;
--
-- AUTO_INCREMENT for table `sys_menu_role`
--
ALTER TABLE `sys_menu_role`
MODIFY `menu_role_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=44;
--
-- AUTO_INCREMENT for table `sys_system_parameter`
--
ALTER TABLE `sys_system_parameter`
MODIFY `system_parameter_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `trx_availability`
--
ALTER TABLE `trx_availability`
MODIFY `availability_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `trx_balance_status`
--
ALTER TABLE `trx_balance_status`
MODIFY `balance_status_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `trx_call_plan_detail`
--
ALTER TABLE `trx_call_plan_detail`
MODIFY `call_plan_detail_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `trx_call_plan_header`
--
ALTER TABLE `trx_call_plan_header`
MODIFY `call_plan_header_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `trx_call_plan_list`
--
ALTER TABLE `trx_call_plan_list`
MODIFY `call_plan_list_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `trx_call_report`
--
ALTER TABLE `trx_call_report`
MODIFY `call_report_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `trx_call_report_product`
--
ALTER TABLE `trx_call_report_product`
MODIFY `call_report_product_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `trx_cis`
--
ALTER TABLE `trx_cis`
MODIFY `cis_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `trx_competitor`
--
ALTER TABLE `trx_competitor`
MODIFY `competitor_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `trx_customer_status`
--
ALTER TABLE `trx_customer_status`
MODIFY `customer_status_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `trx_deal_report`
--
ALTER TABLE `trx_deal_report`
MODIFY `deal_report_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `trx_dp_status`
--
ALTER TABLE `trx_dp_status`
MODIFY `dp_status_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `trx_lost_reason`
--
ALTER TABLE `trx_lost_reason`
MODIFY `lost_reason_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `trx_lost_report`
--
ALTER TABLE `trx_lost_report`
MODIFY `lost_report_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `trx_lost_report_competitor`
--
ALTER TABLE `trx_lost_report_competitor`
MODIFY `lost_report_competitor_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `trx_placeofvisit`
--
ALTER TABLE `trx_placeofvisit`
MODIFY `placeofvisit_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `trx_quotation`
--
ALTER TABLE `trx_quotation`
MODIFY `quotation_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `vcs_table`
--
ALTER TABLE `vcs_table`
MODIFY `vcs_table_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
