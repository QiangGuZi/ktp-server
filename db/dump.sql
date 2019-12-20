-- MySQL dump 10.13  Distrib 5.7.27, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ktp
-- ------------------------------------------------------
-- Server version	5.7.27-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ktp_course`
--

DROP TABLE IF EXISTS `ktp_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ktp_course` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '课程ID',
  `coursename` varchar(20) NOT NULL COMMENT '课程名',
  `code` varchar(20) NOT NULL COMMENT '加课码',
  `classname` varchar(20) DEFAULT NULL COMMENT '班级名称',
  `semester_year` varchar(20) DEFAULT NULL COMMENT '学年',
  `semester` varchar(1) DEFAULT NULL COMMENT '0不限，1第一学期，2第二学期',
  `bg_image_url` varchar(100) DEFAULT NULL COMMENT '课程背景图片url',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='课程表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ktp_course`
--

LOCK TABLES `ktp_course` WRITE;
/*!40000 ALTER TABLE `ktp_course` DISABLE KEYS */;
INSERT INTO `ktp_course` VALUES (1,'全栈开发','HK4ZBM','117030802','2019-2020','1','','2019-01-01 00:00:00'),(2,'测试作业1','KKB1AC','117030801,117030802','2019-2020','2','','2019-12-18 22:13:21');
/*!40000 ALTER TABLE `ktp_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ktp_course_homework`
--

DROP TABLE IF EXISTS `ktp_course_homework`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ktp_course_homework` (
  `course_id` bigint(20) NOT NULL,
  `homework_id` bigint(20) NOT NULL,
  PRIMARY KEY (`course_id`,`homework_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课程-作业·关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ktp_course_homework`
--

LOCK TABLES `ktp_course_homework` WRITE;
/*!40000 ALTER TABLE `ktp_course_homework` DISABLE KEYS */;
INSERT INTO `ktp_course_homework` VALUES (1,1);
/*!40000 ALTER TABLE `ktp_course_homework` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ktp_homework`
--

DROP TABLE IF EXISTS `ktp_homework`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ktp_homework` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '作业id',
  `title` varchar(50) NOT NULL COMMENT '作业标题',
  `description` text COMMENT '作业详情富文本内容',
  `annex` text COMMENT '附件url，多个附件用 ; 隔开',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='作业表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ktp_homework`
--

LOCK TABLES `ktp_homework` WRITE;
/*!40000 ALTER TABLE `ktp_homework` DISABLE KEYS */;
INSERT INTO `ktp_homework` VALUES (1,'第一次作业：模仿课堂派','<b>加油</b>','','2019-01-01 00:00:00');
/*!40000 ALTER TABLE `ktp_homework` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ktp_perm`
--

DROP TABLE IF EXISTS `ktp_perm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ktp_perm` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '资源编号',
  `name` varchar(20) NOT NULL COMMENT '资源名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级ID',
  `url` varchar(100) DEFAULT NULL COMMENT 'URL',
  `perms` text COMMENT '权限标识',
  `type` varchar(20) NOT NULL COMMENT '类型：如button按钮 menu菜单',
  `icon` varchar(30) DEFAULT NULL COMMENT '菜单图标',
  `priority` bigint(20) DEFAULT NULL COMMENT '排序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ktp_perm`
--

LOCK TABLES `ktp_perm` WRITE;
/*!40000 ALTER TABLE `ktp_perm` DISABLE KEYS */;
/*!40000 ALTER TABLE `ktp_perm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ktp_role`
--

DROP TABLE IF EXISTS `ktp_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ktp_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `name` varchar(20) NOT NULL COMMENT '角色名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ktp_role`
--

LOCK TABLES `ktp_role` WRITE;
/*!40000 ALTER TABLE `ktp_role` DISABLE KEYS */;
INSERT INTO `ktp_role` VALUES (1,'student','2019-01-01 00:00:00','学生/教师'),(2,'admin','2019-01-01 00:00:00','管理员');
/*!40000 ALTER TABLE `ktp_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ktp_role_perm`
--

DROP TABLE IF EXISTS `ktp_role_perm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ktp_role_perm` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `perm_id` bigint(20) NOT NULL COMMENT '菜单/按钮ID',
  PRIMARY KEY (`role_id`,`perm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色-资源·关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ktp_role_perm`
--

LOCK TABLES `ktp_role_perm` WRITE;
/*!40000 ALTER TABLE `ktp_role_perm` DISABLE KEYS */;
/*!40000 ALTER TABLE `ktp_role_perm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ktp_user`
--

DROP TABLE IF EXISTS `ktp_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ktp_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `account` varchar(50) NOT NULL COMMENT '账户名',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `salt` varchar(100) NOT NULL COMMENT '盐值',
  `avatar` varchar(100) DEFAULT NULL COMMENT '头像',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机',
  `email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `school` varchar(20) DEFAULT NULL COMMENT '学校',
  `sno` varchar(20) DEFAULT NULL COMMENT '学号/教职号',
  `role` int(11) DEFAULT NULL COMMENT '角色 0学生 1教师',
  `department` varchar(20) DEFAULT NULL COMMENT '院系',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ktp_user`
--

LOCK TABLES `ktp_user` WRITE;
/*!40000 ALTER TABLE `ktp_user` DISABLE KEYS */;
INSERT INTO `ktp_user` VALUES (1,'ktp00000001','谭强','990106tq','','','17674720762',NULL,'重庆理工大学','117030802',0,NULL,NULL,'2019-01-01 00:00:00',NULL),(2,'ktp00000002','测试老师1',' 123456','','','17674720762',' 1164020907@qq.com','重庆理工大学','117030802',1,'人工智能学院','测试老师1，good day','2019-12-18 22:11:04',NULL);
/*!40000 ALTER TABLE `ktp_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ktp_user_course`
--

DROP TABLE IF EXISTS `ktp_user_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ktp_user_course` (
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `course_id` bigint(20) NOT NULL COMMENT '课程id',
  `pigeonhole` tinyint(1) DEFAULT NULL COMMENT '0未归档，1归档',
  `top` tinyint(1) DEFAULT NULL COMMENT '0未置顶，1置顶',
  `priority` bigint(20) DEFAULT NULL COMMENT '排序',
  `role` int(11) DEFAULT NULL COMMENT '0为学生，1为老师，2为助教',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`,`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户-课程·关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ktp_user_course`
--

LOCK TABLES `ktp_user_course` WRITE;
/*!40000 ALTER TABLE `ktp_user_course` DISABLE KEYS */;
INSERT INTO `ktp_user_course` VALUES (1,1,0,1,1,1,'2019-01-01 00:00:00'),(1,2,0,0,2,0,'2019-12-18 22:14:21'),(2,1,0,0,1,0,'2019-12-18 22:15:05'),(2,2,0,0,2,1,'2019-12-18 22:15:28');
/*!40000 ALTER TABLE `ktp_user_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ktp_user_course_homework`
--

DROP TABLE IF EXISTS `ktp_user_course_homework`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ktp_user_course_homework` (
  `user_id` bigint(20) NOT NULL,
  `course_id` bigint(20) NOT NULL,
  `homework_id` bigint(20) NOT NULL,
  `submit` tinyint(1) DEFAULT NULL COMMENT '作业提交状态，0未提交，1已提交',
  `annex` text COMMENT '附件url，多个附件用 ; 隔开',
  `student_work_message` varchar(200) DEFAULT NULL COMMENT '学生作业留言',
  `grade` int(20) DEFAULT NULL COMMENT '作业评分',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`,`course_id`,`homework_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学生-课程-作业·关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ktp_user_course_homework`
--

LOCK TABLES `ktp_user_course_homework` WRITE;
/*!40000 ALTER TABLE `ktp_user_course_homework` DISABLE KEYS */;
INSERT INTO `ktp_user_course_homework` VALUES (1,1,1,1,'','我尽力了兄弟',100,'2019-01-01 00:00:00',NULL);
/*!40000 ALTER TABLE `ktp_user_course_homework` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ktp_user_role`
--

DROP TABLE IF EXISTS `ktp_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ktp_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户-角色·关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ktp_user_role`
--

LOCK TABLES `ktp_user_role` WRITE;
/*!40000 ALTER TABLE `ktp_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `ktp_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-20  9:05:29
