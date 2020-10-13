-- phpMyAdmin SQL Dump
-- version 4.7.1
-- https://www.phpmyadmin.net/
--
-- Host: sql12.freemysqlhosting.net
-- Generation Time: Oct 11, 2020 at 04:37 AM
-- Server version: 5.5.62-0ubuntu0.14.04.1
-- PHP Version: 7.0.33-0ubuntu0.16.04.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sql12369803`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `fullname` varchar(250) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(35) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `fullname`, `email`, `password`) VALUES
(1, 'Admin', 'admin@tutor.com', '21232f297a57a5a743894a0e4a801fc3');

-- --------------------------------------------------------

--
-- Table structure for table `applied_post`
--

CREATE TABLE `applied_post` (
  `id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `post_by` int(11) NOT NULL,
  `applied_by` int(11) NOT NULL,
  `applied_to` int(11) NOT NULL,
  `applied_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `student_ck` varchar(3) NOT NULL DEFAULT 'no',
  `tutor_ck` varchar(3) NOT NULL DEFAULT 'no',
  `tutor_cf` tinyint(4) NOT NULL DEFAULT '0',
  `tution_cf` int(11) NOT NULL DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `applied_post`
--

INSERT INTO `applied_post` (`id`, `post_id`, `post_by`, `applied_by`, `applied_to`, `applied_time`, `student_ck`, `tutor_ck`, `tutor_cf`, `tution_cf`) VALUES
(13, 8, 1, 5, 1, '2018-11-30 16:26:35', 'yes', 'yes', 0, 0),
(14, 9, 10, 9, 10, '2018-11-30 17:05:48', 'yes', 'yes', 0, 1),
(15, 0, 0, 12, 6, '2020-10-09 19:31:52', 'yes', 'yes', 0, 0),
(16, 10, 12, 13, 12, '2020-10-09 19:38:30', 'yes', 'yes', 0, 1),
(17, 0, 0, 13, 2, '2020-10-09 19:40:10', 'yes', 'yes', 0, 0),
(18, 0, 0, 14, 13, '2020-10-09 20:54:10', 'yes', 'yes', 0, 0),
(19, 11, 12, 14, 12, '2020-10-10 05:34:31', 'yes', 'yes', 0, 1),
(20, 0, 0, 12, 5, '2020-10-10 09:21:39', 'yes', 'yes', 0, 0),
(21, 12, 12, 13, 12, '2020-10-10 09:27:20', 'yes', 'yes', 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE `post` (
  `id` int(11) NOT NULL,
  `postby_id` int(11) NOT NULL,
  `subject` text NOT NULL,
  `class` text NOT NULL,
  `medium` varchar(20) NOT NULL,
  `salary` varchar(50) NOT NULL,
  `location` text NOT NULL,
  `p_university` text NOT NULL,
  `post_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deadline` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`id`, `postby_id`, `subject`, `class`, `medium`, `salary`, `location`, `p_university`, `post_time`, `deadline`) VALUES
(10, 12, 'Math', 'Nine-Ten', 'Any', 'None', 'Pune', 'None', '2020-10-09 19:32:43', '12/18/2020'),
(11, 12, 'Social Science', 'Six-Seven', 'English', 'None', 'Pune', 'Vellore Institute of Technology', '2020-10-10 05:33:48', '10/24/2020'),
(12, 12, 'Physics,Higher Math', 'College/University', 'Any', '2000-5000', 'Pune', 'FIITJEE', '2020-10-10 09:26:06', '10/10/2020'),
(13, 15, 'English,Social Science,Religion,History', 'One-Three,Six-Seven,Eight', 'Any', '1000-2000', 'Bangalore', 'FITJEE', '2020-10-11 03:56:44', '10/22/2020');

-- --------------------------------------------------------

--
-- Table structure for table `tutor`
--

CREATE TABLE `tutor` (
  `id` int(11) NOT NULL,
  `t_id` int(11) NOT NULL,
  `inst_name` varchar(150) NOT NULL,
  `prefer_sub` text NOT NULL,
  `class` text NOT NULL,
  `medium` text NOT NULL,
  `prefer_location` text NOT NULL,
  `salary` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tutor`
--

INSERT INTO `tutor` (`id`, `t_id`, `inst_name`, `prefer_sub`, `class`, `medium`, `prefer_location`, `salary`) VALUES
(5, 2, 'Southeast University', 'Bangla,Math,ICT,Computer Science', 'One-Three,Nine-Ten,Eleven-Twelve,College/University', 'Bangla,Any', 'Khilkhet', '1000-2000'),
(11, 6, 'Southeast University', 'Bangla,English,Religion,ICT,Physics,Sociology,Economics,Civics,Computer Science', 'Six-Seven,Nine-Ten,Eleven-Twelve', 'Bangla', 'banani,gulsan', '2000-5000'),
(15, 5, 'Southeast University', 'Bangla,Math,General Science,Religion,ICT,Physics,Higher Math,Computer Science', 'Nine-Ten,Eleven-Twelve,College/University', 'English,Any', 'Farmgate', '1000-2000'),
(17, 9, 'Southeast University', 'ICT,Physics,Higher Math,Computer Science', 'Nine-Ten,Eleven-Twelve,College/University', 'Any', 'Banani, Khilkhet, Uttara', '5000-10000'),
(19, 14, 'Vellore Institute of Technology', 'English,Chemistry,Biology,Sociology,Accounting', 'College/University', 'Bangla,Any', 'Gurgaon', '5000-10000'),
(22, 16, 'Southeast University', 'Math,Chemistry,Higher Math,Statistics,Computer Science', 'Nine-Ten', 'Any', 'Gurgaon', '10000-15000'),
(23, 13, 'FIITJEE', 'English,Math,Higher Math,Statistics,Computer Science', 'Eleven-Twelve,College/University', 'English,Any', 'Pune', '10000-15000');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `fullname` varchar(150) NOT NULL,
  `gender` varchar(6) NOT NULL,
  `email` varchar(40) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `address` varchar(200) NOT NULL DEFAULT '',
  `pass` varchar(50) NOT NULL,
  `confirmcode` varchar(7) NOT NULL,
  `activation` varchar(3) NOT NULL DEFAULT '',
  `type` varchar(10) NOT NULL,
  `user_pic` text,
  `last_logout` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `online` varchar(5) NOT NULL DEFAULT 'no'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `fullname`, `gender`, `email`, `phone`, `address`, `pass`, `confirmcode`, `activation`, `type`, `user_pic`, `last_logout`, `online`) VALUES
(1, 'Student', 'male', 'mohsin@gmail.com', '015976432566', 'Khilkhet, Dhaka, Bangladesh', 'e10adc3949ba59abbe56e057f20f883e', '205575', '', 'student', '1543554432.png', '2018-11-30 14:11:19', 'no'),
(2, 'Teacher', 'male', 'teacher@gmail.com', '014976432566', 'Banani, Dhaka, Bangladesh', '8d788385431273d11e8b43bb78f3aa41', '901358', '', 'teacher', '1515505450.jpg', '2020-10-10 22:14:26', 'no'),
(5, 'Teacher_1', 'female', 'teacher_1@gmail.com', '014976432566', '1,2 pacific home,Farmgate', '8d788385431273d11e8b43bb78f3aa41', '495196', '', 'teacher', '', '2020-10-10 09:25:26', 'no'),
(6, 'Teacher_2', 'male', 'teacher_2@gmail.com', '014976432566', 'Badda', '8d788385431273d11e8b43bb78f3aa41', '292470', '', 'teacher', '1515558340.jpeg', '2018-09-04 10:39:17', 'no'),
(9, 'Mohsin Gram', 'male', 'mohsingram@gmail.com', '01899761551', 'Dhaka', 'e10adc3949ba59abbe56e057f20f883e', '214114', '', 'teacher', '1543568429.jpg', '2018-11-30 17:00:29', 'yes'),
(10, 'Robin Student', 'male', 'robins@gmail.com', '01788651991', 'Uttara', 'e10adc3949ba59abbe56e057f20f883e', '946363', '', 'student', '1543568644.png', '2018-11-30 17:13:40', 'no'),
(12, 'Sai Chaitanya', 'male', 'saichaita@gmail.com', '97191666886', 'Gurugram', '5f4dcc3b5aa765d61d8327deb882cf99', '663840', '', 'student', '1602243410.png', '2020-10-11 04:06:21', 'no'),
(13, 'Randhir Kumar', 'male', 'randi.kumar@gmail.com', '8237001402', 'Pune', '5f4dcc3b5aa765d61d8327deb882cf99', '237830', '', 'teacher', '1602243546.png', '2020-10-10 09:27:35', 'no'),
(14, 'Satyavathi', 'male', 'sat1402@gmail.com', '8237001402', 'Gurugram', '5f4dcc3b5aa765d61d8327deb882cf99', '342276', '', 'teacher', '1602247755.png', '2020-10-10 05:34:37', 'no'),
(15, 'Sai Sukesh', 'male', 'sukesh.sai@gmail.com', '1234512345', 'Chennai', '5f4dcc3b5aa765d61d8327deb882cf99', '172582', '', 'student', '1602248636.png', '2020-10-10 22:14:41', 'yes'),
(16, 'Atul Jha', 'male', 'atul.jha@gmail.com', '8987612321', 'Delhi', '5f4dcc3b5aa765d61d8327deb882cf99', '318215', '', 'teacher', '1602249373.png', '2020-10-09 21:26:44', 'no'),
(17, 'Abhinav Jha', 'male', 'abhinav.jha@gmail.com', '987654321', 'Pune', '5f4dcc3b5aa765d61d8327deb882cf99', '378899', '', 'student', '1602310796.png', '2020-10-10 06:45:57', 'no'),
(18, 'Raghav Arora', 'male', 'raghav.arora@gmail.com', '9876501234', '', '5f4dcc3b5aa765d61d8327deb882cf99', '832320', '', 'student', NULL, '2020-10-10 06:46:53', 'no');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `applied_post`
--
ALTER TABLE `applied_post`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tutor`
--
ALTER TABLE `tutor`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `applied_post`
--
ALTER TABLE `applied_post`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
--
-- AUTO_INCREMENT for table `post`
--
ALTER TABLE `post`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `tutor`
--
ALTER TABLE `tutor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
