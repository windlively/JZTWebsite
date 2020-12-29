/*
SQLyog Community v12.4.1 (64 bit)
MySQL - 5.7.17-log : Database - jzt_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`jzt_db` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `jzt_db`;

/*Table structure for table `abouts_organization` */

DROP TABLE IF EXISTS `abouts_organization`;

CREATE TABLE `abouts_organization` (
  `id` int(11) NOT NULL,
  `content` text NOT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `abouts_team` */

DROP TABLE IF EXISTS `abouts_team`;

CREATE TABLE `abouts_team` (
  `id` int(11) NOT NULL,
  `content` text NOT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `administrator` */

DROP TABLE IF EXISTS `administrator`;

CREATE TABLE `administrator` (
  `adminName` varchar(20) NOT NULL,
  `adminPassword` varchar(20) DEFAULT NULL,
  `permit` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`adminName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `article` */

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `articleId` int(11) NOT NULL AUTO_INCREMENT,
  `articleType` varchar(20) DEFAULT NULL,
  `articleTitle` varchar(50) DEFAULT NULL,
  `articleAuthor` varchar(20) DEFAULT NULL,
  `articleContent` text,
  `illuatrationPictureSrc` varchar(100) DEFAULT NULL,
  `readAmounts` int(11) DEFAULT '0',
  `uploadTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`articleId`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8;

/*Table structure for table `news` */

DROP TABLE IF EXISTS `news`;

CREATE TABLE `news` (
  `newsId` int(11) NOT NULL AUTO_INCREMENT,
  `newsTitle` varchar(50) NOT NULL,
  `newsInfo` varchar(100) DEFAULT NULL,
  `newsAuthor` varchar(20) DEFAULT NULL,
  `newsContent` text,
  `illuatrationPictureSrc` varchar(100) DEFAULT NULL,
  `readAmounts` int(11) DEFAULT NULL,
  `uploadTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`newsId`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8;

/*Table structure for table `picture` */

DROP TABLE IF EXISTS `picture`;

CREATE TABLE `picture` (
  `pictureId` int(11) NOT NULL AUTO_INCREMENT,
  `pictureType` varchar(20) DEFAULT NULL,
  `pictureName` varchar(20) DEFAULT NULL,
  `pictureInfo` varchar(100) DEFAULT NULL,
  `pictureSrc` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`pictureId`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*Table structure for table `poetry_review` */

DROP TABLE IF EXISTS `poetry_review`;

CREATE TABLE `poetry_review` (
  `reviewId` int(11) NOT NULL AUTO_INCREMENT,
  `poetryType` varchar(20) DEFAULT NULL,
  `reviewTitle` varchar(20) DEFAULT NULL,
  `poetryTitle` varchar(50) DEFAULT NULL,
  `poetryAuthor` varchar(20) DEFAULT NULL,
  `poetryAlign` varchar(10) DEFAULT 'justify',
  `poetryContent` text,
  `reviewAuthor` varchar(20) DEFAULT NULL,
  `reviewContent` text,
  `illuatrationPictureSrc` varchar(100) DEFAULT NULL,
  `readAmounts` int(11) NOT NULL,
  `uploadTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`reviewId`)
) ENGINE=InnoDB AUTO_INCREMENT=228 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
