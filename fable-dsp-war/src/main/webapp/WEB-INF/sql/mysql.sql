/*
SQLyog Enterprise - MySQL GUI v8.14 
MySQL - 5.5.30 : Database - fable
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`fable` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `fable`;

/*Table structure for table `dsp_data_source` */

DROP TABLE IF EXISTS `dsp_data_source`;

CREATE TABLE `dsp_data_source` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_TIME` datetime DEFAULT NULL,
  `CREATE_USER` varchar(16) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(16) DEFAULT NULL,
  `CONNECT_URL` varchar(255) DEFAULT NULL,
  `DB_NAME` varchar(255) DEFAULT NULL,
  `DB_TYPE` varchar(255) DEFAULT NULL,
  `DEL_FLAG` bigint(20) NOT NULL DEFAULT '0',
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `DEVICE_TYPE` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PORT` bigint(20) DEFAULT NULL,
  `ROOT_PATH` varchar(255) DEFAULT NULL,
  `SERVER_IP` varchar(255) DEFAULT NULL,
  `SOURCE_TYPE` varchar(255) DEFAULT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `dsp_data_source` */

insert  into `dsp_data_source`(`id`,`CREATE_TIME`,`CREATE_USER`,`UPDATE_TIME`,`UPDATE_USER`,`CONNECT_URL`,`DB_NAME`,`DB_TYPE`,`DEL_FLAG`,`DESCRIPTION`,`DEVICE_TYPE`,`NAME`,`PASSWORD`,`PORT`,`ROOT_PATH`,`SERVER_IP`,`SOURCE_TYPE`,`USERNAME`) values (1,'2014-04-15 09:55:34',NULL,'2014-04-15 10:01:43',NULL,'jdbc:oracle:thin:@192.168.1.156:1521:orcl','orcl','o',0,NULL,'0','156-oracle','xVlFBZNo9cU=',1521,NULL,'192.168.1.156','0','fable'),(2,'2014-04-15 09:55:57',NULL,'2014-04-15 10:01:49',NULL,'jdbc:oracle:thin:@192.168.1.173:1521:orcl','orcl','o',0,NULL,'0','173-oracle','xVlFBZNo9cU=',1521,NULL,'192.168.1.173','0','fable'),(3,'2014-04-19 09:20:30',NULL,NULL,NULL,'','','f',0,NULL,'0','FTP1','xVlFBZNo9cU=',21,'/','192.168.1.155','1','fable'),(4,'2014-04-22 19:06:24',NULL,NULL,NULL,'jdbc:oracle:thin:@192.168.1.174:1521:orcl','orcl','o',0,NULL,'0','174-oracle','xVlFBZNo9cU=',1521,NULL,'192.168.1.174','0','fable');

/*Table structure for table `dsp_etl_strategy` */

DROP TABLE IF EXISTS `dsp_etl_strategy`;

CREATE TABLE `dsp_etl_strategy` (
  `id` bigint(20) NOT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `CREATE_USER` varchar(16) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(16) DEFAULT NULL,
  `CONTENT_FILTER` longtext NOT NULL,
  `DEL_FLAG` char(1) NOT NULL,
  `DESCRIPTION` longtext,
  `FROM_TABLE` varchar(255) NOT NULL,
  `TO_TABLE` varchar(255) NOT NULL,
  `PIPELINE_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK372561D330AB99DA` (`PIPELINE_ID`),
  CONSTRAINT `FK372561D330AB99DA` FOREIGN KEY (`PIPELINE_ID`) REFERENCES `dsp_pipeline` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `dsp_etl_strategy` */

insert  into `dsp_etl_strategy`(`id`,`CREATE_TIME`,`CREATE_USER`,`UPDATE_TIME`,`UPDATE_USER`,`CONTENT_FILTER`,`DEL_FLAG`,`DESCRIPTION`,`FROM_TABLE`,`TO_TABLE`,`PIPELINE_ID`) values (1,'2014-04-15 14:49:39','1',NULL,NULL,'{\"pretreatment\":{\"files\":[],\"sourceColumns\":[{\"name\":\"CREATE_USER\",\"index\":0,\"type\":0},{\"name\":\"UPDATE_TIME\",\"index\":0,\"type\":0},{\"name\":\"UPDATE_USER\",\"index\":0,\"type\":0},{\"name\":\"CONNECT_URL\",\"index\":0,\"type\":0},{\"name\":\"DB_NAME\",\"index\":0,\"type\":0},{\"name\":\"DB_TYPE\",\"index\":0,\"type\":0},{\"name\":\"DEL_FLAG\",\"index\":0,\"type\":0},{\"name\":\"DESCRIPTION\",\"index\":0,\"type\":0},{\"name\":\"NAME\",\"index\":0,\"type\":0},{\"name\":\"PASSWORD\",\"index\":0,\"type\":0},{\"name\":\"PORT\",\"index\":0,\"type\":0},{\"name\":\"ROOT_PATH\",\"index\":0,\"type\":0},{\"name\":\"SERVER_IP\",\"index\":0,\"type\":0},{\"name\":\"SOURCE_TYPE\",\"index\":0,\"type\":0},{\"name\":\"USERNAME\",\"index\":0,\"type\":0},{\"name\":\"DEVICE_TYPE\",\"index\":0,\"type\":0},{\"name\":\"TESTCLOB\",\"index\":0,\"type\":0}],\"targetColumns\":[{\"name\":\"CREATE_USER\",\"index\":0,\"type\":0},{\"name\":\"UPDATE_TIME\",\"index\":0,\"type\":0},{\"name\":\"UPDATE_USER\",\"index\":0,\"type\":0},{\"name\":\"CONNECT_URL\",\"index\":0,\"type\":0},{\"name\":\"DB_NAME\",\"index\":0,\"type\":0},{\"name\":\"DB_TYPE\",\"index\":0,\"type\":0},{\"name\":\"DEL_FLAG\",\"index\":0,\"type\":0},{\"name\":\"DESCRIPTION\",\"index\":0,\"type\":0},{\"name\":\"NAME\",\"index\":0,\"type\":0},{\"name\":\"PASSWORD\",\"index\":0,\"type\":0},{\"name\":\"PORT\",\"index\":0,\"type\":0},{\"name\":\"ROOT_PATH\",\"index\":0,\"type\":0},{\"name\":\"SERVER_IP\",\"index\":0,\"type\":0},{\"name\":\"SOURCE_TYPE\",\"index\":0,\"type\":0},{\"name\":\"USERNAME\",\"index\":0,\"type\":0},{\"name\":\"DEVICE_TYPE\",\"index\":0,\"type\":0},{\"name\":\"TESTCLOB\",\"index\":0,\"type\":0}]},\"mapping\":{\"columnsMapping\":[{\"source\":\"CREATE_USER\",\"target\":\"CREATE_USER\"},{\"source\":\"UPDATE_TIME\",\"target\":\"UPDATE_TIME\"},{\"source\":\"UPDATE_USER\",\"target\":\"UPDATE_USER\"},{\"source\":\"CONNECT_URL\",\"target\":\"CONNECT_URL\"},{\"source\":\"DB_NAME\",\"target\":\"DB_NAME\"},{\"source\":\"DB_TYPE\",\"target\":\"DB_TYPE\"},{\"source\":\"DEL_FLAG\",\"target\":\"DEL_FLAG\"},{\"source\":\"DESCRIPTION\",\"target\":\"DESCRIPTION\"},{\"source\":\"NAME\",\"target\":\"NAME\"},{\"source\":\"PASSWORD\",\"target\":\"PASSWORD\"},{\"source\":\"PORT\",\"target\":\"PORT\"},{\"source\":\"ROOT_PATH\",\"target\":\"ROOT_PATH\"},{\"source\":\"SERVER_IP\",\"target\":\"SERVER_IP\"},{\"source\":\"SOURCE_TYPE\",\"target\":\"SOURCE_TYPE\"},{\"source\":\"USERNAME\",\"target\":\"USERNAME\"},{\"source\":\"DEVICE_TYPE\",\"target\":\"DEVICE_TYPE\"},{\"source\":\"TESTCLOB\",\"target\":\"TESTCLOB\"}]},\"filter\":{\"virusFilter\":{\"files\":[],\"columns\":[]},\"filecntFilter\":[],\"columnFilter\":[]},\"converter\":{\"columnConverter\":[],\"filecntConverter\":[]},\"extension\":null}','0',NULL,'DSP_DATA_SOURCE1','DSP_DATA_SOURCE1',14),(3,'2014-04-15 15:15:24','1',NULL,NULL,'{\"pretreatment\":{\"files\":[],\"sourceColumns\":[{\"name\":\"CREATE_USER\",\"index\":0,\"type\":0},{\"name\":\"UPDATE_TIME\",\"index\":0,\"type\":0},{\"name\":\"UPDATE_USER\",\"index\":0,\"type\":0},{\"name\":\"CONNECT_URL\",\"index\":0,\"type\":0},{\"name\":\"DB_NAME\",\"index\":0,\"type\":0},{\"name\":\"DB_TYPE\",\"index\":0,\"type\":0},{\"name\":\"DEL_FLAG\",\"index\":0,\"type\":0},{\"name\":\"DESCRIPTION\",\"index\":0,\"type\":0},{\"name\":\"NAME\",\"index\":0,\"type\":0},{\"name\":\"PASSWORD\",\"index\":0,\"type\":0},{\"name\":\"PORT\",\"index\":0,\"type\":0},{\"name\":\"ROOT_PATH\",\"index\":0,\"type\":0},{\"name\":\"SERVER_IP\",\"index\":0,\"type\":0},{\"name\":\"SOURCE_TYPE\",\"index\":0,\"type\":0},{\"name\":\"USERNAME\",\"index\":0,\"type\":0},{\"name\":\"DEVICE_TYPE\",\"index\":0,\"type\":0},{\"name\":\"TESTCLOB\",\"index\":0,\"type\":0}],\"targetColumns\":[{\"name\":\"CREATE_USER\",\"index\":0,\"type\":0},{\"name\":\"UPDATE_TIME\",\"index\":0,\"type\":0},{\"name\":\"UPDATE_USER\",\"index\":0,\"type\":0},{\"name\":\"CONNECT_URL\",\"index\":0,\"type\":0},{\"name\":\"DB_NAME\",\"index\":0,\"type\":0},{\"name\":\"DB_TYPE\",\"index\":0,\"type\":0},{\"name\":\"DEL_FLAG\",\"index\":0,\"type\":0},{\"name\":\"DESCRIPTION\",\"index\":0,\"type\":0},{\"name\":\"NAME\",\"index\":0,\"type\":0},{\"name\":\"PASSWORD\",\"index\":0,\"type\":0},{\"name\":\"PORT\",\"index\":0,\"type\":0},{\"name\":\"ROOT_PATH\",\"index\":0,\"type\":0},{\"name\":\"SERVER_IP\",\"index\":0,\"type\":0},{\"name\":\"SOURCE_TYPE\",\"index\":0,\"type\":0},{\"name\":\"USERNAME\",\"index\":0,\"type\":0},{\"name\":\"DEVICE_TYPE\",\"index\":0,\"type\":0},{\"name\":\"TESTCLOB\",\"index\":0,\"type\":0}]},\"mapping\":{\"columnsMapping\":[{\"source\":\"CREATE_USER\",\"target\":\"CREATE_USER\"},{\"source\":\"UPDATE_TIME\",\"target\":\"UPDATE_TIME\"},{\"source\":\"UPDATE_USER\",\"target\":\"UPDATE_USER\"},{\"source\":\"CONNECT_URL\",\"target\":\"CONNECT_URL\"},{\"source\":\"DB_NAME\",\"target\":\"DB_NAME\"},{\"source\":\"DB_TYPE\",\"target\":\"DB_TYPE\"},{\"source\":\"DEL_FLAG\",\"target\":\"DEL_FLAG\"},{\"source\":\"DESCRIPTION\",\"target\":\"DESCRIPTION\"},{\"source\":\"NAME\",\"target\":\"NAME\"},{\"source\":\"PASSWORD\",\"target\":\"PASSWORD\"},{\"source\":\"PORT\",\"target\":\"PORT\"},{\"source\":\"ROOT_PATH\",\"target\":\"ROOT_PATH\"},{\"source\":\"SERVER_IP\",\"target\":\"SERVER_IP\"},{\"source\":\"SOURCE_TYPE\",\"target\":\"SOURCE_TYPE\"},{\"source\":\"USERNAME\",\"target\":\"USERNAME\"},{\"source\":\"DEVICE_TYPE\",\"target\":\"DEVICE_TYPE\"},{\"source\":\"TESTCLOB\",\"target\":\"TESTCLOB\"}]},\"filter\":{\"virusFilter\":{\"files\":[],\"columns\":[]},\"filecntFilter\":[],\"columnFilter\":[]},\"converter\":{\"columnConverter\":[],\"filecntConverter\":[]},\"extension\":null}','0',NULL,'DSP_DATA_SOURCE1','DSP_DATA_SOURCE1',16),(4,'2014-04-15 16:10:35','1',NULL,NULL,'{\"pretreatment\":{\"files\":[],\"sourceColumns\":[],\"targetColumns\":[]},\"mapping\":{\"columnsMapping\":[]},\"filter\":{\"virusFilter\":{\"files\":[],\"columns\":[]},\"filecntFilter\":[],\"columnFilter\":[]},\"converter\":{\"columnConverter\":[],\"filecntConverter\":[]},\"extension\":null}','0',NULL,'DSP_DATA_SOURCE1','DSP_DATA_SOURCE1',17),(5,'2014-04-15 16:26:43','1',NULL,NULL,'{\"pretreatment\":{\"files\":[],\"sourceColumns\":[{\"name\":\"CREATE_USER\",\"index\":0,\"type\":0},{\"name\":\"UPDATE_TIME\",\"index\":0,\"type\":0},{\"name\":\"UPDATE_USER\",\"index\":0,\"type\":0},{\"name\":\"CONNECT_URL\",\"index\":0,\"type\":0},{\"name\":\"DB_NAME\",\"index\":0,\"type\":0},{\"name\":\"DB_TYPE\",\"index\":0,\"type\":0},{\"name\":\"DEL_FLAG\",\"index\":0,\"type\":0},{\"name\":\"DESCRIPTION\",\"index\":0,\"type\":0},{\"name\":\"NAME\",\"index\":0,\"type\":0},{\"name\":\"PASSWORD\",\"index\":0,\"type\":0},{\"name\":\"PORT\",\"index\":0,\"type\":0},{\"name\":\"ROOT_PATH\",\"index\":0,\"type\":0},{\"name\":\"SERVER_IP\",\"index\":0,\"type\":0},{\"name\":\"SOURCE_TYPE\",\"index\":0,\"type\":0},{\"name\":\"USERNAME\",\"index\":0,\"type\":0},{\"name\":\"DEVICE_TYPE\",\"index\":0,\"type\":0},{\"name\":\"TESTCLOB\",\"index\":0,\"type\":0}],\"targetColumns\":[{\"name\":\"CREATE_USER\",\"index\":0,\"type\":0},{\"name\":\"UPDATE_TIME\",\"index\":0,\"type\":0},{\"name\":\"UPDATE_USER\",\"index\":0,\"type\":0},{\"name\":\"CONNECT_URL\",\"index\":0,\"type\":0},{\"name\":\"DB_NAME\",\"index\":0,\"type\":0},{\"name\":\"DB_TYPE\",\"index\":0,\"type\":0},{\"name\":\"DEL_FLAG\",\"index\":0,\"type\":0},{\"name\":\"DESCRIPTION\",\"index\":0,\"type\":0},{\"name\":\"NAME\",\"index\":0,\"type\":0},{\"name\":\"PASSWORD\",\"index\":0,\"type\":0},{\"name\":\"PORT\",\"index\":0,\"type\":0},{\"name\":\"ROOT_PATH\",\"index\":0,\"type\":0},{\"name\":\"SERVER_IP\",\"index\":0,\"type\":0},{\"name\":\"SOURCE_TYPE\",\"index\":0,\"type\":0},{\"name\":\"USERNAME\",\"index\":0,\"type\":0},{\"name\":\"DEVICE_TYPE\",\"index\":0,\"type\":0},{\"name\":\"TESTCLOB\",\"index\":0,\"type\":0}]},\"mapping\":{\"columnsMapping\":[{\"source\":\"CREATE_USER\",\"target\":\"CREATE_USER\"},{\"source\":\"UPDATE_TIME\",\"target\":\"UPDATE_TIME\"},{\"source\":\"UPDATE_USER\",\"target\":\"UPDATE_USER\"},{\"source\":\"CONNECT_URL\",\"target\":\"CONNECT_URL\"},{\"source\":\"DB_NAME\",\"target\":\"DB_NAME\"},{\"source\":\"DB_TYPE\",\"target\":\"DB_TYPE\"},{\"source\":\"DEL_FLAG\",\"target\":\"DEL_FLAG\"},{\"source\":\"DESCRIPTION\",\"target\":\"DESCRIPTION\"},{\"source\":\"NAME\",\"target\":\"NAME\"},{\"source\":\"PASSWORD\",\"target\":\"PASSWORD\"},{\"source\":\"PORT\",\"target\":\"PORT\"},{\"source\":\"ROOT_PATH\",\"target\":\"ROOT_PATH\"},{\"source\":\"SERVER_IP\",\"target\":\"SERVER_IP\"},{\"source\":\"SOURCE_TYPE\",\"target\":\"SOURCE_TYPE\"},{\"source\":\"USERNAME\",\"target\":\"USERNAME\"},{\"source\":\"DEVICE_TYPE\",\"target\":\"DEVICE_TYPE\"},{\"source\":\"TESTCLOB\",\"target\":\"TESTCLOB\"}]},\"filter\":{\"virusFilter\":{\"files\":[],\"columns\":[]},\"filecntFilter\":[],\"columnFilter\":[]},\"converter\":{\"columnConverter\":[],\"filecntConverter\":[]},\"extension\":null}','0',NULL,'DSP_DATA_SOURCE1','DSP_DATA_SOURCE1',18),(6,'2014-04-15 16:28:56','1',NULL,NULL,'{\"pretreatment\":{\"files\":[],\"sourceColumns\":[],\"targetColumns\":[]},\"mapping\":{\"columnsMapping\":[]},\"filter\":{\"virusFilter\":{\"files\":[],\"columns\":[]},\"filecntFilter\":[],\"columnFilter\":[]},\"converter\":{\"columnConverter\":[],\"filecntConverter\":[]},\"extension\":null}','0',NULL,'DSP_DATA_TEST','',19),(7,'2014-04-15 16:39:54','1',NULL,NULL,'{\"pretreatment\":{\"files\":[],\"sourceColumns\":[],\"targetColumns\":[]},\"mapping\":{\"columnsMapping\":[]},\"filter\":{\"virusFilter\":{\"files\":[],\"columns\":[]},\"filecntFilter\":[],\"columnFilter\":[]},\"converter\":{\"columnConverter\":[],\"filecntConverter\":[]},\"extension\":null}','0',NULL,'FABLESJJH3','',20),(8,'2014-04-15 16:41:16','1',NULL,NULL,'{\"pretreatment\":{\"files\":[],\"sourceColumns\":[],\"targetColumns\":[]},\"mapping\":{\"columnsMapping\":[]},\"filter\":{\"virusFilter\":{\"files\":[],\"columns\":[]},\"filecntFilter\":[],\"columnFilter\":[]},\"converter\":{\"columnConverter\":[],\"filecntConverter\":[]},\"extension\":null}','0',NULL,'DSP_DATA_TEST','',21),(10,'2014-04-15 16:44:39','1',NULL,NULL,'{\"pretreatment\":{\"files\":[],\"sourceColumns\":[],\"targetColumns\":[]},\"mapping\":{\"columnsMapping\":[]},\"filter\":{\"virusFilter\":{\"files\":[],\"columns\":[]},\"filecntFilter\":[],\"columnFilter\":[]},\"converter\":{\"columnConverter\":[],\"filecntConverter\":[]},\"extension\":null}','0',NULL,'FABLESJJH3','FABLESJJH3',23),(11,'2014-04-15 17:44:00','1',NULL,NULL,'{\"pretreatment\":{\"files\":[],\"sourceColumns\":[{\"name\":\"CREATE_USER\",\"index\":0,\"type\":0},{\"name\":\"UPDATE_TIME\",\"index\":0,\"type\":0},{\"name\":\"UPDATE_USER\",\"index\":0,\"type\":0},{\"name\":\"CONNECT_URL\",\"index\":0,\"type\":0},{\"name\":\"DB_NAME\",\"index\":0,\"type\":0},{\"name\":\"DB_TYPE\",\"index\":0,\"type\":0},{\"name\":\"DEL_FLAG\",\"index\":0,\"type\":0},{\"name\":\"DESCRIPTION\",\"index\":0,\"type\":0},{\"name\":\"NAME\",\"index\":0,\"type\":0},{\"name\":\"PASSWORD\",\"index\":0,\"type\":0},{\"name\":\"PORT\",\"index\":0,\"type\":0},{\"name\":\"ROOT_PATH\",\"index\":0,\"type\":0},{\"name\":\"SERVER_IP\",\"index\":0,\"type\":0},{\"name\":\"SOURCE_TYPE\",\"index\":0,\"type\":0},{\"name\":\"USERNAME\",\"index\":0,\"type\":0},{\"name\":\"DEVICE_TYPE\",\"index\":0,\"type\":0},{\"name\":\"TESTCLOB\",\"index\":0,\"type\":0}],\"targetColumns\":[{\"name\":\"CREATE_USER\",\"index\":0,\"type\":0},{\"name\":\"UPDATE_TIME\",\"index\":0,\"type\":0},{\"name\":\"UPDATE_USER\",\"index\":0,\"type\":0},{\"name\":\"CONNECT_URL\",\"index\":0,\"type\":0},{\"name\":\"DB_NAME\",\"index\":0,\"type\":0},{\"name\":\"DB_TYPE\",\"index\":0,\"type\":0},{\"name\":\"DEL_FLAG\",\"index\":0,\"type\":0},{\"name\":\"DESCRIPTION\",\"index\":0,\"type\":0},{\"name\":\"NAME\",\"index\":0,\"type\":0},{\"name\":\"PASSWORD\",\"index\":0,\"type\":0},{\"name\":\"PORT\",\"index\":0,\"type\":0},{\"name\":\"ROOT_PATH\",\"index\":0,\"type\":0},{\"name\":\"SERVER_IP\",\"index\":0,\"type\":0},{\"name\":\"SOURCE_TYPE\",\"index\":0,\"type\":0},{\"name\":\"USERNAME\",\"index\":0,\"type\":0},{\"name\":\"DEVICE_TYPE\",\"index\":0,\"type\":0},{\"name\":\"TESTCLOB\",\"index\":0,\"type\":0}]},\"mapping\":{\"columnsMapping\":[{\"source\":\"CREATE_USER\",\"target\":\"CREATE_USER\"},{\"source\":\"UPDATE_TIME\",\"target\":\"UPDATE_TIME\"},{\"source\":\"UPDATE_USER\",\"target\":\"UPDATE_USER\"},{\"source\":\"CONNECT_URL\",\"target\":\"CONNECT_URL\"},{\"source\":\"DB_NAME\",\"target\":\"DB_NAME\"},{\"source\":\"DB_TYPE\",\"target\":\"DB_TYPE\"},{\"source\":\"DEL_FLAG\",\"target\":\"DEL_FLAG\"},{\"source\":\"DESCRIPTION\",\"target\":\"DESCRIPTION\"},{\"source\":\"NAME\",\"target\":\"NAME\"},{\"source\":\"PASSWORD\",\"target\":\"PASSWORD\"},{\"source\":\"PORT\",\"target\":\"PORT\"},{\"source\":\"ROOT_PATH\",\"target\":\"ROOT_PATH\"},{\"source\":\"SERVER_IP\",\"target\":\"SERVER_IP\"},{\"source\":\"SOURCE_TYPE\",\"target\":\"SOURCE_TYPE\"},{\"source\":\"USERNAME\",\"target\":\"USERNAME\"},{\"source\":\"DEVICE_TYPE\",\"target\":\"DEVICE_TYPE\"},{\"source\":\"TESTCLOB\",\"target\":\"TESTCLOB\"}]},\"filter\":{\"virusFilter\":{\"files\":[],\"columns\":[]},\"filecntFilter\":[],\"columnFilter\":[]},\"converter\":{\"columnConverter\":[],\"filecntConverter\":[]},\"extension\":null}','0',NULL,'DSP_DATA_SOURCE1','DSP_DATA_SOURCE1',24),(12,'2014-04-16 14:04:50','1',NULL,NULL,'{\"pretreatment\":{\"files\":[],\"sourceColumns\":[{\"name\":\"NAME1\",\"index\":0,\"type\":0},{\"name\":\"DESC1\",\"index\":0,\"type\":0}],\"targetColumns\":[{\"name\":\"NAME2\",\"index\":0,\"type\":0},{\"name\":\"DESC2\",\"index\":0,\"type\":0}]},\"mapping\":{\"columnsMapping\":[{\"source\":\"NAME1\",\"target\":\"NAME2\"},{\"source\":\"DESC1\",\"target\":\"DESC2\"}]},\"filter\":{\"virusFilter\":{\"files\":[],\"columns\":[]},\"filecntFilter\":[],\"columnFilter\":[{\"name\":\"ID1\",\"operator\":\"equal\",\"keyword\":null,\"value\":[\"1\"]}]},\"converter\":{\"columnConverter\":[],\"filecntConverter\":[]},\"extension\":null}','0',NULL,'WUHAO','WUHAO',25);

/*Table structure for table `dsp_ftp_mapping` */

DROP TABLE IF EXISTS `dsp_ftp_mapping`;

CREATE TABLE `dsp_ftp_mapping` (
  `id` bigint(20) NOT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `CREATE_USER` varchar(16) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(16) DEFAULT NULL,
  `INNER_ADDRESS` varchar(255) DEFAULT NULL,
  `INNER_USERNAME` varchar(32) DEFAULT NULL,
  `OUTER_ADDRESS` varchar(255) DEFAULT NULL,
  `OUTER_USERNAME` varchar(32) DEFAULT NULL,
  `USER_FLAG` char(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `dsp_ftp_mapping` */

insert  into `dsp_ftp_mapping`(`id`,`CREATE_TIME`,`CREATE_USER`,`UPDATE_TIME`,`UPDATE_USER`,`INNER_ADDRESS`,`INNER_USERNAME`,`OUTER_ADDRESS`,`OUTER_USERNAME`,`USER_FLAG`) values (1,'2014-04-25 15:19:32','1',NULL,NULL,'/innerqiu','qiu','/outertestqiu','testqiu','1'),(3,'2014-04-25 15:20:28','1',NULL,NULL,'/innerqiu','qiu','/outertestqiu','testqiu','0');

/*Table structure for table `dsp_pipeline` */

DROP TABLE IF EXISTS `dsp_pipeline`;

CREATE TABLE `dsp_pipeline` (
  `id` bigint(20) NOT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `CREATE_USER` varchar(16) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(16) DEFAULT NULL,
  `DEL_FLAG` char(1) NOT NULL DEFAULT '0',
  `DESCRIPTION` longtext,
  `SOURCE_ID` bigint(20) NOT NULL,
  `TARGET_ID` bigint(20) NOT NULL,
  `TASK_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC627078055CA1F1D` (`TASK_ID`),
  KEY `FKC62707805577DD2A` (`SOURCE_ID`),
  KEY `FKC62707808A16D7F4` (`TARGET_ID`),
  CONSTRAINT `FKC62707805577DD2A` FOREIGN KEY (`SOURCE_ID`) REFERENCES `dsp_trans_entity` (`id`),
  CONSTRAINT `FKC627078055CA1F1D` FOREIGN KEY (`TASK_ID`) REFERENCES `dsp_task` (`id`),
  CONSTRAINT `FKC62707808A16D7F4` FOREIGN KEY (`TARGET_ID`) REFERENCES `dsp_trans_entity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `dsp_pipeline` */

insert  into `dsp_pipeline`(`id`,`CREATE_TIME`,`CREATE_USER`,`UPDATE_TIME`,`UPDATE_USER`,`DEL_FLAG`,`DESCRIPTION`,`SOURCE_ID`,`TARGET_ID`,`TASK_ID`) values (14,'2014-04-15 14:49:08','1',NULL,NULL,'0',NULL,27,28,14),(16,'2014-04-15 15:15:21','1',NULL,NULL,'0',NULL,31,32,16),(17,'2014-04-15 16:10:35','1',NULL,NULL,'0',NULL,33,34,17),(18,'2014-04-15 16:26:39','1',NULL,NULL,'0',NULL,35,36,18),(19,'2014-04-15 16:28:56','1',NULL,NULL,'0',NULL,37,38,19),(20,'2014-04-15 16:39:54','1',NULL,NULL,'0',NULL,39,40,20),(21,'2014-04-15 16:41:16','1',NULL,NULL,'0',NULL,41,42,21),(23,'2014-04-15 16:44:39','1',NULL,NULL,'0',NULL,45,46,23),(24,'2014-04-15 17:44:00','1',NULL,NULL,'0',NULL,47,48,24),(25,'2014-04-16 14:04:50','1',NULL,NULL,'0',NULL,49,50,25),(27,'2014-04-22 19:07:03','1',NULL,NULL,'0',NULL,53,54,28);

/*Table structure for table `dsp_schedule_config` */

DROP TABLE IF EXISTS `dsp_schedule_config`;

CREATE TABLE `dsp_schedule_config` (
  `id` bigint(20) NOT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `CREATE_USER` varchar(16) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(16) DEFAULT NULL,
  `CRONTAB_EXPRESSION` varchar(255) DEFAULT NULL,
  `DEL_FLAG` bigint(20) DEFAULT NULL,
  `DESCRIPTION` longtext,
  `XML_EXPRESSION` longtext,
  `TASK_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7316274C55CA1F1D` (`TASK_ID`),
  CONSTRAINT `FK7316274C55CA1F1D` FOREIGN KEY (`TASK_ID`) REFERENCES `dsp_task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `dsp_schedule_config` */

insert  into `dsp_schedule_config`(`id`,`CREATE_TIME`,`CREATE_USER`,`UPDATE_TIME`,`UPDATE_USER`,`CRONTAB_EXPRESSION`,`DEL_FLAG`,`DESCRIPTION`,`XML_EXPRESSION`,`TASK_ID`) values (7,'2014-04-23 15:44:09','1','2014-04-24 10:57:47','1','2014-04-23 00:00:00$$* */4 * * * ? *',0,NULL,NULL,18),(13,'2014-04-23 16:08:29','1','2014-04-24 10:54:52','1','0 0 2 * * ?',0,NULL,NULL,17),(17,'2014-04-24 10:58:01','1','2014-05-19 19:33:53','2','2014-05-19 19:33:53$$* */6 * * * ? *',0,NULL,NULL,23),(18,'2014-05-19 16:37:07','2','2014-05-19 20:18:01','2','0 0 0 * * ?',0,NULL,NULL,19),(19,'2014-05-19 19:32:59','2','2014-05-19 20:17:49','2','2014-05-19 19:34:23$$*/1 * * * * ? *',0,NULL,NULL,19),(20,'2014-05-19 19:33:44','2',NULL,NULL,'2014-05-19 19:33:44$$*/1 * * * * ? *',0,NULL,NULL,21);

/*Table structure for table `dsp_task` */

DROP TABLE IF EXISTS `dsp_task`;

CREATE TABLE `dsp_task` (
  `id` bigint(20) NOT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `CREATE_USER` varchar(16) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(16) DEFAULT NULL,
  `DEL_FLAG` char(1) NOT NULL DEFAULT '0',
  `DELETE_SOURCEDATA` char(1) DEFAULT '0',
  `DESCRIPTION` longtext,
  `NAME` varchar(32) NOT NULL,
  `NEED_RESOURCE` int(11) DEFAULT '1',
  `REBUILD_TRIGGER` char(1) DEFAULT '0',
  `SYNCHROTYPE` char(1) DEFAULT '0',
  `ASSOCIATION` char(1) DEFAULT NULL,
  `ROWLEVELLOG` char(1) DEFAULT NULL,
  `ROWLOG_FLAG` char(1) DEFAULT NULL,
  `SYSLOG_FLAG` char(1) DEFAULT NULL,
  `SOURCE_FILE_DEAL` char(1) DEFAULT NULL,
  `TARGET_FILE_DEAL` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `NAME` (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `dsp_task` */

insert  into `dsp_task`(`id`,`CREATE_TIME`,`CREATE_USER`,`UPDATE_TIME`,`UPDATE_USER`,`DEL_FLAG`,`DELETE_SOURCEDATA`,`DESCRIPTION`,`NAME`,`NEED_RESOURCE`,`REBUILD_TRIGGER`,`SYNCHROTYPE`,`ASSOCIATION`,`ROWLEVELLOG`,`ROWLOG_FLAG`,`SYSLOG_FLAG`,`SOURCE_FILE_DEAL`,`TARGET_FILE_DEAL`) values (14,'2014-04-15 14:49:08','1',NULL,NULL,'0','1','oracle触发器','oracle触发器',1,'1','3','0',NULL,NULL,NULL,NULL,NULL),(16,'2014-04-15 15:15:21','1',NULL,NULL,'0','1','oracletest','oracletest',1,'1','3','0',NULL,NULL,NULL,NULL,NULL),(17,'2014-04-15 16:10:35','1',NULL,NULL,'0','1','','oracle2',1,'1','3','0',NULL,NULL,NULL,NULL,NULL),(18,'2014-04-15 16:26:39','1',NULL,NULL,'0','1','','test3',1,'1','3','0',NULL,NULL,NULL,NULL,NULL),(19,'2014-04-15 16:28:56','1',NULL,NULL,'0','1','','test4',1,'1','3','0',NULL,NULL,NULL,NULL,NULL),(20,'2014-04-15 16:39:54','1',NULL,NULL,'0','1','','test5',1,'1','3','0',NULL,NULL,NULL,NULL,NULL),(21,'2014-04-15 16:41:16','1',NULL,NULL,'0','1','','test6',1,'1','3','0',NULL,NULL,NULL,NULL,NULL),(23,'2014-04-15 16:44:39','1',NULL,NULL,'0','1','','test7',1,'1','0','0',NULL,NULL,NULL,NULL,NULL),(24,'2014-04-15 17:44:00','1',NULL,NULL,'0','1','','oracle1',1,'1','3','0',NULL,NULL,NULL,NULL,NULL),(25,'2014-04-16 14:04:50','1',NULL,NULL,'0','1','','1',1,'1','3','0',NULL,NULL,NULL,NULL,NULL),(28,'2014-04-22 19:07:03','1',NULL,NULL,'0','1','','173-174',1,'1','0','0',NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `dsp_timestamp` */

DROP TABLE IF EXISTS `dsp_timestamp`;

CREATE TABLE `dsp_timestamp` (
  `DATA_SOURCE_ID` bigint(20) NOT NULL,
  `TABLE_NAME` varchar(255) NOT NULL,
  `TASK_ID` bigint(20) NOT NULL,
  `DATA_COLUMN` varchar(255) DEFAULT NULL,
  `SWITCH_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`DATA_SOURCE_ID`,`TABLE_NAME`,`TASK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `dsp_timestamp` */

/*Table structure for table `dsp_trans_entity` */

DROP TABLE IF EXISTS `dsp_trans_entity`;

CREATE TABLE `dsp_trans_entity` (
  `id` bigint(20) NOT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `CREATE_USER` varchar(16) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(16) DEFAULT NULL,
  `DEL_FLAG` char(1) NOT NULL DEFAULT '0',
  `DESCRIPTION` longtext,
  `LOCATION` varchar(255) DEFAULT NULL,
  `TABLE_NAME` varchar(255) DEFAULT NULL,
  `TYPE` char(1) NOT NULL DEFAULT '0',
  `DATA_SOURCE_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK597E3F58503A41BF` (`DATA_SOURCE_ID`),
  CONSTRAINT `FK597E3F58503A41BF` FOREIGN KEY (`DATA_SOURCE_ID`) REFERENCES `dsp_data_source` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `dsp_trans_entity` */

insert  into `dsp_trans_entity`(`id`,`CREATE_TIME`,`CREATE_USER`,`UPDATE_TIME`,`UPDATE_USER`,`DEL_FLAG`,`DESCRIPTION`,`LOCATION`,`TABLE_NAME`,`TYPE`,`DATA_SOURCE_ID`) values (27,'2014-04-15 14:49:08','1',NULL,NULL,'0',NULL,NULL,'DSP_DATA_SOURCE1','0',1),(28,'2014-04-15 14:49:08',NULL,NULL,'1','0',NULL,NULL,'DSP_DATA_SOURCE1','0',1),(31,'2014-04-15 15:15:21','1',NULL,NULL,'0',NULL,NULL,'DSP_DATA_SOURCE1','0',1),(32,'2014-04-15 15:15:21',NULL,NULL,'1','0',NULL,NULL,'DSP_DATA_SOURCE1','0',2),(33,'2014-04-15 16:10:35','1',NULL,NULL,'0',NULL,NULL,'DSP_DATA_SOURCE1','0',1),(34,'2014-04-15 16:10:35',NULL,NULL,'1','0',NULL,NULL,'DSP_DATA_SOURCE1','0',2),(35,'2014-04-15 16:26:39','1',NULL,NULL,'0',NULL,NULL,'DSP_DATA_SOURCE1','0',1),(36,'2014-04-15 16:26:39',NULL,NULL,'1','0',NULL,NULL,'DSP_DATA_SOURCE1','0',2),(37,'2014-04-15 16:28:56','1',NULL,NULL,'0',NULL,NULL,'DSP_DATA_TEST','0',1),(38,'2014-04-15 16:28:56',NULL,NULL,'1','0',NULL,NULL,'TESTDIFF','0',1),(39,'2014-04-15 16:39:54','1',NULL,NULL,'0',NULL,NULL,'FABLESJJH3','0',1),(40,'2014-04-15 16:39:54',NULL,NULL,'1','0',NULL,NULL,'DSP_DATA_TEST','0',2),(41,'2014-04-15 16:41:16','1',NULL,NULL,'0',NULL,NULL,'DSP_DATA_TEST','0',1),(42,'2014-04-15 16:41:16',NULL,NULL,'1','0',NULL,NULL,'FABLESJJH3','0',1),(45,'2014-04-15 16:44:39','1',NULL,NULL,'0',NULL,NULL,'FABLESJJH3','0',1),(46,'2014-04-15 16:44:39',NULL,NULL,'1','0',NULL,NULL,'FABLESJJH3','0',1),(47,'2014-04-15 17:44:00','1',NULL,NULL,'0',NULL,NULL,'DSP_DATA_SOURCE1','0',1),(48,'2014-04-15 17:44:00',NULL,NULL,'1','0',NULL,NULL,'DSP_DATA_SOURCE1','0',2),(49,'2014-04-16 14:04:50','1',NULL,NULL,'0',NULL,NULL,'WUHAO','0',1),(50,'2014-04-16 14:04:50',NULL,NULL,'1','0',NULL,NULL,'WUHAO','0',2),(53,'2014-04-22 19:07:03','1',NULL,NULL,'0',NULL,NULL,'','0',2),(54,'2014-04-22 19:07:03',NULL,NULL,'1','0',NULL,NULL,'WUHAO','0',4);

/*Table structure for table `ftp_user` */

DROP TABLE IF EXISTS `ftp_user`;

CREATE TABLE `ftp_user` (
  `userid` varchar(64) NOT NULL,
  `userpassword` varchar(64) DEFAULT NULL,
  `homedirectory` varchar(128) NOT NULL,
  `enableflag` tinyint(1) DEFAULT '1',
  `writepermission` tinyint(1) DEFAULT '1',
  `idletime` int(11) DEFAULT '600000',
  `uploadrate` int(11) DEFAULT '1000',
  `downloadrate` int(11) DEFAULT '1000',
  `maxloginnumber` int(11) DEFAULT '40',
  `maxloginperip` int(11) DEFAULT '40',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ftp_user` */

insert  into `ftp_user`(`userid`,`userpassword`,`homedirectory`,`enableflag`,`writepermission`,`idletime`,`uploadrate`,`downloadrate`,`maxloginnumber`,`maxloginperip`) values ('qiu','123','/root/qiu',1,1,600000,0,0,40,40),('testqiu','123','/roo/testqiu',1,1,600000,0,0,40,40),('wuhao','456','/root/path',1,1,600000,6000,6000,40,40),('zhanglei','zhanglei','/root/zhanglei',1,1,600000,6000,6000,40,40);

/*Table structure for table `interest_info` */

DROP TABLE IF EXISTS `interest_info`;

CREATE TABLE `interest_info` (
  `id` bigint(20) NOT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `CREATE_USER` varchar(16) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(16) DEFAULT NULL,
  `EXT` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `interest_info` */

/*Table structure for table `job_run_info` */

DROP TABLE IF EXISTS `job_run_info`;

CREATE TABLE `job_run_info` (
  `id` bigint(20) NOT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `CREATE_USER` varchar(16) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(16) DEFAULT NULL,
  `CURRENT_STATUS` char(1) NOT NULL DEFAULT '0',
  `FINISH_TIME` datetime DEFAULT NULL,
  `LOG_PATH` varchar(255) DEFAULT NULL,
  `SATART_TIME` datetime DEFAULT NULL,
  `PIPELINE_ID` bigint(20) NOT NULL,
  `TASK_ID` bigint(20) NOT NULL,
  `BATCH` int(11) DEFAULT NULL,
  `LOAD_RATE` int(11) DEFAULT NULL,
  `SELECT_RATE` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8D4A746430AB99DA` (`PIPELINE_ID`),
  KEY `FK8D4A746455CA1F1D` (`TASK_ID`),
  CONSTRAINT `FK8D4A746430AB99DA` FOREIGN KEY (`PIPELINE_ID`) REFERENCES `dsp_pipeline` (`id`),
  CONSTRAINT `FK8D4A746455CA1F1D` FOREIGN KEY (`TASK_ID`) REFERENCES `dsp_task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_run_info` */

insert  into `job_run_info`(`id`,`CREATE_TIME`,`CREATE_USER`,`UPDATE_TIME`,`UPDATE_USER`,`CURRENT_STATUS`,`FINISH_TIME`,`LOG_PATH`,`SATART_TIME`,`PIPELINE_ID`,`TASK_ID`,`BATCH`,`LOAD_RATE`,`SELECT_RATE`) values (1,'2014-04-01 00:00:00','1','2014-04-01 00:00:00','2','1','2014-04-01 00:00:00','/','2014-04-01 00:00:00',14,14,NULL,NULL,NULL);

/*Table structure for table `net_gap_config` */

DROP TABLE IF EXISTS `net_gap_config`;

CREATE TABLE `net_gap_config` (
  `id` bigint(20) NOT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `CREATE_USER` varchar(16) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(16) DEFAULT NULL,
  `DEL_FLAG` varchar(255) DEFAULT NULL,
  `EXT` varchar(255) DEFAULT NULL,
  `FLOW` bigint(20) DEFAULT NULL,
  `GAP_INNER_NAME` varchar(255) DEFAULT NULL,
  `GAP_NO` bigint(20) DEFAULT NULL,
  `GAP_OUTER_NAME` varchar(255) DEFAULT NULL,
  `GAP_STATUS` varchar(255) DEFAULT NULL,
  `INNER_IP` varchar(255) DEFAULT NULL,
  `OUTER_IP` varchar(255) DEFAULT NULL,
  `INNER_PROXY_PORT` bigint(20) DEFAULT NULL,
  `OUTER_PROXY_PORT` bigint(20) DEFAULT NULL,
  `SERVICE_PORT` bigint(20) DEFAULT NULL,
  `GAP_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `net_gap_config` */

insert  into `net_gap_config`(`id`,`CREATE_TIME`,`CREATE_USER`,`UPDATE_TIME`,`UPDATE_USER`,`DEL_FLAG`,`EXT`,`FLOW`,`GAP_INNER_NAME`,`GAP_NO`,`GAP_OUTER_NAME`,`GAP_STATUS`,`INNER_IP`,`OUTER_IP`,`INNER_PROXY_PORT`,`OUTER_PROXY_PORT`,`SERVICE_PORT`,`GAP_NAME`) values (1,'2014-04-09 17:11:09',NULL,NULL,NULL,NULL,NULL,NULL,'sss',NULL,'',NULL,'','',111,NULL,NULL,NULL);

/*Table structure for table `net_group_config` */

DROP TABLE IF EXISTS `net_group_config`;

CREATE TABLE `net_group_config` (
  `id` bigint(20) NOT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `CREATE_USER` varchar(16) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(16) DEFAULT NULL,
  `EXT` varchar(255) DEFAULT NULL,
  `GAP_ID` bigint(20) DEFAULT NULL,
  `GROUP_NAME` varchar(255) DEFAULT NULL,
  `INNER_ID` bigint(20) DEFAULT NULL,
  `OUTER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `net_group_config` */

insert  into `net_group_config`(`id`,`CREATE_TIME`,`CREATE_USER`,`UPDATE_TIME`,`UPDATE_USER`,`EXT`,`GAP_ID`,`GROUP_NAME`,`INNER_ID`,`OUTER_ID`) values (1,'2014-04-09 17:11:09',NULL,NULL,NULL,NULL,1,NULL,1,1);

/*Table structure for table `net_inner_config` */

DROP TABLE IF EXISTS `net_inner_config`;

CREATE TABLE `net_inner_config` (
  `id` bigint(20) NOT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `CREATE_USER` varchar(16) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(16) DEFAULT NULL,
  `EXT` varchar(255) DEFAULT NULL,
  `NET_NAME` varchar(255) DEFAULT NULL,
  `NOW_INN_CARD` varchar(255) DEFAULT NULL,
  `NOW_OUT_CARD` varchar(255) DEFAULT NULL,
  `STATUS` bigint(20) DEFAULT NULL,
  `TO_GAP_GATEWAY` varchar(255) DEFAULT NULL,
  `TO_GAP_IP` varchar(255) DEFAULT NULL,
  `TO_GAP_MASK` varchar(255) DEFAULT NULL,
  `TO_INNERNET_GATEWAY` varchar(255) DEFAULT NULL,
  `TO_INNERNET_IP` varchar(255) DEFAULT NULL,
  `TO_INNERNET_MASK` varchar(255) DEFAULT NULL,
  `TYPE` bigint(20) DEFAULT NULL,
  `INNER_PROXY_PORT` bigint(20) DEFAULT NULL,
  `OUTER_PROXY_PORT` bigint(20) DEFAULT NULL,
  `SERVICE_PORT` bigint(20) DEFAULT NULL,
  `HOSTNAME` varchar(255) DEFAULT NULL,
  `SERVICE_IN_PORT` bigint(20) DEFAULT NULL,
  `SERVICE_OUT_PORT` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `net_inner_config` */

insert  into `net_inner_config`(`id`,`CREATE_TIME`,`CREATE_USER`,`UPDATE_TIME`,`UPDATE_USER`,`EXT`,`NET_NAME`,`NOW_INN_CARD`,`NOW_OUT_CARD`,`STATUS`,`TO_GAP_GATEWAY`,`TO_GAP_IP`,`TO_GAP_MASK`,`TO_INNERNET_GATEWAY`,`TO_INNERNET_IP`,`TO_INNERNET_MASK`,`TYPE`,`INNER_PROXY_PORT`,`OUTER_PROXY_PORT`,`SERVICE_PORT`,`HOSTNAME`,`SERVICE_IN_PORT`,`SERVICE_OUT_PORT`) values (1,'2014-04-09 17:11:08',NULL,NULL,NULL,NULL,NULL,'以太网适配器-以太网-2',NULL,NULL,'192.168.0.1','192.168.0.98','255.255.255.0','192.168.0.1','192.168.0.98','255.255.255.0',NULL,1122,1111,NULL,'aaa',2222,4444),(2,'2014-05-12 15:28:16',NULL,NULL,NULL,'',NULL,' 本地连接',' VMware Network Adapter VMnet8',NULL,'','192.168.253.6','','192.168.0.1','192.168.0.238','255.255.255.0',NULL,11,NULL,NULL,'',NULL,NULL),(3,'2014-05-12 15:31:06',NULL,NULL,NULL,'',NULL,' 本地连接',' VMware Network Adapter VMnet8',NULL,'','192.168.253.6','','192.168.0.1','192.168.0.238','255.255.255.0',NULL,11,NULL,NULL,'',NULL,NULL),(4,NULL,NULL,NULL,NULL,NULL,NULL,'以太网适配器-本地连接-2','以太网适配器-本地连接',NULL,'192.168.0.1','192.168.0.103','255.255.255.0','192.168.1.1','192.168.1.88','255.255.255.0',NULL,1111,1111,NULL,'1111',1111,1111),(5,NULL,NULL,NULL,NULL,NULL,NULL,'以太网适配器-本地连接-2','以太网适配器-本地连接',NULL,'192.168.0.1','192.168.0.103','255.255.255.0','192.168.1.1','192.168.1.100','255.255.255.0',NULL,1111,1111,NULL,'1111',1111,1111),(6,NULL,NULL,NULL,NULL,NULL,NULL,'以太网适配器-以太网-3','以太网适配器-以太网-2',NULL,'192.168.0.1','192.168.0.98','255.255.255.0','','169.254.230.41','255.255.0.0',NULL,1111,1111,NULL,'1111',1111,1111),(7,NULL,NULL,NULL,NULL,NULL,NULL,'以太网适配器-以太网-3','以太网适配器-以太网-2',NULL,'192.168.0.1','192.168.0.98','255.255.255.0','','169.254.230.42','255.255.0.0',NULL,1111,1111,NULL,'1111',1111,1111),(8,NULL,NULL,NULL,NULL,NULL,NULL,'以太网适配器-以太网-3','以太网适配器-以太网-2',NULL,'192.168.0.1','192.168.0.98','255.255.255.0','192.168.0.1','169.254.230.42','255.255.0.0',NULL,1111,1111,NULL,'1111',1111,1111),(9,NULL,NULL,NULL,NULL,NULL,NULL,'以太网适配器-本地连接-2','以太网适配器-本地连接',NULL,'192.168.0.1','192.168.0.103','255.255.255.0','192.168.1.1','192.168.1.88','255.255.255.0',NULL,1111,1111,NULL,'1111',1111,1111),(10,NULL,NULL,NULL,NULL,NULL,NULL,'以太网适配器-以太网-2','以太网适配器-以太网-2',NULL,'192.168.0.1','192.168.0.98','255.255.255.0','192.168.0.1','192.168.0.98','255.255.255.0',NULL,1111,1111,NULL,'1111',1111,1111),(11,NULL,NULL,NULL,NULL,NULL,NULL,'以太网适配器-以太网-2','以太网适配器-以太网-2',NULL,'192.168.0.1','192.168.0.98','255.255.255.0','192.168.0.1','192.168.0.98','255.255.255.0',NULL,1111,1111,NULL,'1111',1111,1111);

/*Table structure for table `net_outer_config` */

DROP TABLE IF EXISTS `net_outer_config`;

CREATE TABLE `net_outer_config` (
  `id` bigint(20) NOT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `CREATE_USER` varchar(16) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(16) DEFAULT NULL,
  `DEL_FLAG` varchar(255) DEFAULT NULL,
  `EXT` varchar(255) DEFAULT NULL,
  `NET_NAME` varchar(255) DEFAULT NULL,
  `NOW_GAP_CARD` varchar(255) DEFAULT NULL,
  `NOW_OUTER_CARD` varchar(255) DEFAULT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  `TO_GAP_GATEWAY` varchar(255) DEFAULT NULL,
  `TO_GAP_IP` varchar(255) DEFAULT NULL,
  `TO_GAP_MASK` varchar(255) DEFAULT NULL,
  `TO_OUTERNET_GATEWAY` varchar(255) DEFAULT NULL,
  `TO_OUTERNET_IP` varchar(255) DEFAULT NULL,
  `TO_OUTERNET_MASK` varchar(255) DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  `INNER_PROXY_PORT` bigint(20) DEFAULT NULL,
  `OUTER_PROXY_PORT` bigint(20) DEFAULT NULL,
  `SERVICE_PORT` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `net_outer_config` */

insert  into `net_outer_config`(`id`,`CREATE_TIME`,`CREATE_USER`,`UPDATE_TIME`,`UPDATE_USER`,`DEL_FLAG`,`EXT`,`NET_NAME`,`NOW_GAP_CARD`,`NOW_OUTER_CARD`,`STATUS`,`TO_GAP_GATEWAY`,`TO_GAP_IP`,`TO_GAP_MASK`,`TO_OUTERNET_GATEWAY`,`TO_OUTERNET_IP`,`TO_OUTERNET_MASK`,`TYPE`,`INNER_PROXY_PORT`,`OUTER_PROXY_PORT`,`SERVICE_PORT`) values (1,'2014-04-09 17:11:09',NULL,NULL,NULL,NULL,NULL,'1111','以太网适配器-以太网-3','以太网适配器-以太网-2',NULL,'192.168.0.1','169.254.230.45','255.255.0.0','192.168.0.1','192.168.0.98','255.255.255.0',NULL,2222,3333,4444);

/*Table structure for table `person_info` */

DROP TABLE IF EXISTS `person_info`;

CREATE TABLE `person_info` (
  `id` bigint(20) NOT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `CREATE_USER` varchar(16) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(16) DEFAULT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `ASSURANCE_DATE` date DEFAULT NULL,
  `BIRTHDAY` date DEFAULT NULL,
  `DRIVE_AGE` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `MOBILE` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `NOTE` varchar(255) DEFAULT NULL,
  `PERSON_LEVEL` varchar(255) DEFAULT NULL,
  `PERSON_STATUS` varchar(255) DEFAULT NULL,
  `SEX` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `MOBILE` (`MOBILE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `person_info` */

/*Table structure for table `person_interest` */

DROP TABLE IF EXISTS `person_interest`;

CREATE TABLE `person_interest` (
  `PERSON_ID` bigint(20) NOT NULL,
  `INTEREST_ID` bigint(20) NOT NULL,
  KEY `FK90E7A8D4FE2DF76A` (`INTEREST_ID`),
  KEY `FK90E7A8D4995168A` (`PERSON_ID`),
  CONSTRAINT `FK90E7A8D4995168A` FOREIGN KEY (`PERSON_ID`) REFERENCES `person_info` (`id`),
  CONSTRAINT `FK90E7A8D4FE2DF76A` FOREIGN KEY (`INTEREST_ID`) REFERENCES `interest_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `person_interest` */

/*Table structure for table `row_level_log` */

DROP TABLE IF EXISTS `row_level_log`;

CREATE TABLE `row_level_log` (
  `id` bigint(20) NOT NULL,
  `OPERATION_DATA` varchar(255) DEFAULT NULL,
  `SYS_LOG_DETAIL_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4A377F84496B2697` (`SYS_LOG_DETAIL_ID`),
  CONSTRAINT `FK4A377F84496B2697` FOREIGN KEY (`SYS_LOG_DETAIL_ID`) REFERENCES `sys_log_detail` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `row_level_log` */

/*Table structure for table `sys_config_info` */

DROP TABLE IF EXISTS `sys_config_info`;

CREATE TABLE `sys_config_info` (
  `id` bigint(20) NOT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `CREATE_USER` int(11) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `UPDATE_USER` int(11) DEFAULT NULL,
  `CATEGORY` varchar(32) DEFAULT NULL,
  `DESCRIPTION` varchar(1000) DEFAULT NULL,
  `SYS_CONFIG_NAME` varchar(64) NOT NULL,
  `SYS_CONFIG_VALUE` varchar(32) NOT NULL,
  `DEL_FLAG` char(1) NOT NULL DEFAULT '0',
  `NAME` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `SYS_CONFIG_NAME` (`SYS_CONFIG_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_config_info` */

insert  into `sys_config_info`(`id`,`CREATE_TIME`,`CREATE_USER`,`UPDATE_TIME`,`UPDATE_USER`,`CATEGORY`,`DESCRIPTION`,`SYS_CONFIG_NAME`,`SYS_CONFIG_VALUE`,`DEL_FLAG`,`NAME`) values (1,'2014-04-30 15:42:27',1,'2014-05-07 12:53:15',1,'sys','是否启用验证码，on表示启用，off表示禁用','CHECK_CODE','off','0','启用验证码'),(3,'2014-05-06 18:22:47',1,'2014-05-23 15:19:40',1,'sys','外rmi地址','OUTER_RMI_ADDRESS','localhost:1099','0','外服务RMI地址'),(4,'2014-05-06 19:08:35',1,'2014-05-07 11:14:00',1,'sys','manager rmi 地址','MANAGER_RMI_ADDRESS','192.168.0.155:1099','0','MANAGER RMI 地址'),(5,'2014-05-06 19:16:03',1,'2014-05-07 13:01:43',1,'sys','ftp服务器通信地址','FTP_SERVER_ADDRESS','localhost:1299','0','FTP服务器地址'),(7,'2014-05-06 19:18:04',1,'2014-05-07 12:54:07',1,'sys','ftp外服务器通信地址','FTP_OUTERSERVER_ADDRESS','localhost:1399','0','FTP外服务器地址'),(8,'2014-05-06 19:48:15',1,'2014-05-16 13:23:32',1,'sys','系统主题','THEME','default','0','系统主题'),(9,'2014-05-06 19:48:15',1,'2014-05-07 13:54:34',1,'sys','FTP内通讯路径','FTP_SERVER_INNER_ROOT_PATH','/root','0','FTP内通讯路径'),(10,'2014-05-06 19:48:15',1,'2014-05-06 19:48:15',1,'sys','FTP外通讯路径','FTP_SERVER_OUTER_ROOT_PATH','/root','0','FTP外通讯路径');

/*Table structure for table `sys_id_gen` */

DROP TABLE IF EXISTS `sys_id_gen`;

CREATE TABLE `sys_id_gen` (
  `GEN_KEY` varchar(255) DEFAULT NULL,
  `GEN_VALUE` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_id_gen` */

insert  into `sys_id_gen`(`GEN_KEY`,`GEN_VALUE`) values ('NET_INNER_CONFIG_ID',12),('NET_OUTER_CONFIG_ID',14),('NET_GAP_CONFIG_ID',2),('NET_GROUP_CONFIG_ID',2),('USER_OPERATION_LOG_ID',13),('DSP_TASK_ID',29),('DSP_TRANS_ENTITY_ID',55),('DSP_PIPELINE_ID',28),('DSP_ETL_STRATEGY_ID',13),('DSP_SCHEDULE_CONFIG_ID',21),('DSP_FTP_MAPPING_ID',8),('SYS_MENU_ID',57),('SYS_RESOURCE_ID',32),('SYS_ROLE_ID',14),('SYS_USER_ID',17),('DSP_DATA_SOURCE_ID',6);

/*Table structure for table `sys_log` */

DROP TABLE IF EXISTS `sys_log`;

CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `LOAD_COUNT` bigint(20) DEFAULT NULL,
  `LOAD_DATA` bigint(20) DEFAULT NULL,
  `OPERATION_RESULTS` char(1) DEFAULT NULL,
  `SELECT_COUNT` bigint(20) DEFAULT NULL,
  `SELECT_DATA` bigint(20) DEFAULT NULL,
  `START_TIME` datetime DEFAULT NULL,
  `TASK_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC3427A9255CA1F1D` (`TASK_ID`),
  CONSTRAINT `FKC3427A9255CA1F1D` FOREIGN KEY (`TASK_ID`) REFERENCES `dsp_task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_log` */

insert  into `sys_log`(`id`,`END_TIME`,`LOAD_COUNT`,`LOAD_DATA`,`OPERATION_RESULTS`,`SELECT_COUNT`,`SELECT_DATA`,`START_TIME`,`TASK_ID`) values (0,'2010-11-23 14:39:51',100,100,'0',10,20,'2009-11-23 14:39:51',14),(1,'2010-11-23 14:39:51',100,100,'0',10,20,'2009-11-23 14:39:51',14),(2,'2010-11-23 14:39:51',50,100,'0',10,20,'2009-11-23 14:39:51',17),(3,'2000-11-23 14:39:51',50,100,'0',10,20,NULL,18),(4,'2000-11-23 14:39:51',100,60,'0',10,100,NULL,18),(5,'1995-11-23 14:39:51',100,60,'0',10,100,'1990-11-23 14:39:51',18),(6,'1995-11-23 14:39:51',100,100,'0',10,20,'1990-11-23 14:39:51',18),(7,'1995-11-23 14:39:51',100,100,'0',10,20,'1990-11-23 14:39:51',18),(8,'1995-11-23 14:39:51',100,100,'0',10,20,'1990-11-23 14:39:51',18),(9,'1995-11-23 14:39:51',100,100,'0',10,20,'1990-11-23 14:39:51',18);

/*Table structure for table `sys_log_detail` */

DROP TABLE IF EXISTS `sys_log_detail`;

CREATE TABLE `sys_log_detail` (
  `id` bigint(20) NOT NULL,
  `OPERATION_DETAIL` varchar(255) DEFAULT NULL,
  `OPERATION_RESULTS` char(1) DEFAULT NULL,
  `OPERATION_TYPE` char(1) DEFAULT NULL,
  `SYS_LOG_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK15B8E69E14F37AB2` (`SYS_LOG_ID`),
  CONSTRAINT `FK15B8E69E14F37AB2` FOREIGN KEY (`SYS_LOG_ID`) REFERENCES `sys_log` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_log_detail` */

/*Table structure for table `sys_menu_info` */

DROP TABLE IF EXISTS `sys_menu_info`;

CREATE TABLE `sys_menu_info` (
  `id` bigint(20) NOT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `CREATE_USER` varchar(16) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(16) DEFAULT NULL,
  `DEL_FLAG` varchar(1) DEFAULT NULL,
  `DESCRIPTION` longtext,
  `ICON_URL` varchar(128) DEFAULT NULL,
  `MENU_NAME` varchar(32) DEFAULT NULL,
  `SORT_ORDER` int(11) DEFAULT NULL,
  `URL` varchar(128) DEFAULT NULL,
  `PID` bigint(20) DEFAULT NULL,
  `MENU_LEVEL` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3B7A147CA4BE80D2` (`PID`),
  CONSTRAINT `FK3B7A147CA4BE80D2` FOREIGN KEY (`PID`) REFERENCES `sys_menu_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_menu_info` */

insert  into `sys_menu_info`(`id`,`CREATE_TIME`,`CREATE_USER`,`UPDATE_TIME`,`UPDATE_USER`,`DEL_FLAG`,`DESCRIPTION`,`ICON_URL`,`MENU_NAME`,`SORT_ORDER`,`URL`,`PID`,`MENU_LEVEL`) values (1,NULL,NULL,'2014-05-16 13:20:24','1','0','导航菜单','menu_nav','导航菜单',1,NULL,NULL,1),(2,NULL,NULL,'2014-04-17 10:35:27','11','0','','menu_sysManage','权限管理',2,NULL,1,2),(3,NULL,NULL,'2014-04-25 15:22:16','1','0','用户管理','menu_sysManage_user','用户管理',1,'/userInfo/maintain',2,3),(4,NULL,NULL,'2014-04-28 13:34:00','11','0','角色管理','menu_sysManage_role','角色管理',1,'/roleInfo/maintain',2,3),(13,'2014-04-08 12:40:28','1','2014-04-22 13:55:17','12','0','菜单管理','menu_sysManage_menu','菜单管理',1,'/menuInfo/maintain',2,3),(16,'2014-04-11 11:20:35','1','2014-04-30 14:21:07','1','0','系统授权','menu_sysAuth','系统授权',3,NULL,1,2),(17,'2014-04-11 11:20:55','1','2014-04-17 10:36:22','11','0','','menu_dataSwitch','数据交换',2,NULL,1,2),(18,'2014-04-11 11:22:45','1','2014-04-17 10:36:51','11','0','任务管理','menu_dataSwitch_task','任务管理',2,'/dataswitch/task-maintain',17,3),(19,'2014-04-11 11:23:21','1','2014-04-22 13:55:37','11','0','网络配置','menu_dataSwitch_network','网络配置',1,'/networkCfg/network_new',17,3),(20,'2014-04-11 11:23:52','1','2014-04-17 10:36:44','11','0','数据资源配置','menu_dataSwitch_data','数据源配置',1,'/dataSource/maintain',17,3),(21,'2014-04-11 11:24:44','1','2014-04-17 11:05:25','11','0','系统服务','menu_ftpService','FTP服务',2,NULL,1,2),(22,'2014-04-11 11:29:21','1','2014-04-17 11:05:36','11','0','FTP用户管理','menu_ftpService_user','FTP用户管理',1,'/ftpservice/ftp-maintain',21,3),(23,'2014-04-11 11:30:04','1','2014-04-17 11:05:45','11','0','FTP映射目录管理','menu_ftpService_mapping','FTP目录管理',1,'/ftp/ftp-mapping',21,3),(24,'2014-04-11 11:30:45','1','2014-04-16 10:10:41','11','0','日志审计','menu_logAudit','日志审计',2,NULL,1,2),(25,'2014-04-11 11:31:34','1','2014-04-17 11:05:55','11','0','系统日志','menu_logAudit_sys','系统日志',1,'/systemLog/maintain',24,3),(26,'2014-04-11 11:32:01','1','2014-04-17 10:18:04','11','0','用户操作日志','menu_logAudit_user','用户日志',1,'/userOperationLog/maintain',24,3),(27,'2014-04-11 14:55:17','1','2014-04-17 11:06:04','11','0','授权管理','menu_sysAuth_auth','授权管理',1,'/sysauth/maintain',16,3),(41,'2014-04-16 09:58:27','11','2014-04-30 14:20:22','1','0','','menu_sysStatus','首页',1,NULL,1,2),(43,'2014-04-16 10:03:48','11','2014-04-17 10:34:30','11','0','','menu_sysStatus_info','dashboard',1,'/main.jsp',41,3),(48,'2014-04-29 15:25:48','1','2014-04-29 15:28:07','1','0','','icon-ok','系统管理',2,NULL,1,2),(49,'2014-04-29 15:27:14','1','2014-04-29 15:27:36',NULL,'0','','icon-ok','参数配置',1,'/sysConfigInfo/maintain',48,3),(56,'2014-05-20 11:11:24','1','2014-05-20 11:14:08','1','0','','icon-ok','任务状态信息',1,'/dashboard/jobRunInfo/maintain',41,3);

/*Table structure for table `sys_menu_res` */

DROP TABLE IF EXISTS `sys_menu_res`;

CREATE TABLE `sys_menu_res` (
  `MENU_ID` bigint(20) NOT NULL,
  `RESOURCE_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`MENU_ID`,`RESOURCE_ID`),
  KEY `FK860C52F2DD623B42` (`MENU_ID`),
  KEY `FK860C52F2B44C3C62` (`RESOURCE_ID`),
  CONSTRAINT `FK860C52F2B44C3C62` FOREIGN KEY (`RESOURCE_ID`) REFERENCES `sys_resource_info` (`id`),
  CONSTRAINT `FK860C52F2DD623B42` FOREIGN KEY (`MENU_ID`) REFERENCES `sys_menu_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_menu_res` */

insert  into `sys_menu_res`(`MENU_ID`,`RESOURCE_ID`) values (3,13),(4,11),(13,14),(18,16),(19,17),(20,18),(22,19),(23,22),(25,20),(26,21),(27,24),(43,25),(43,29),(49,30),(56,31);

/*Table structure for table `sys_resource_info` */

DROP TABLE IF EXISTS `sys_resource_info`;

CREATE TABLE `sys_resource_info` (
  `id` bigint(20) NOT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `CREATE_USER` varchar(16) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(16) DEFAULT NULL,
  `DEL_FLAG` varchar(1) DEFAULT NULL,
  `DESCRIPTION` longtext,
  `ENABLED` varchar(1) DEFAULT NULL,
  `RES_NAME` varchar(32) DEFAULT NULL,
  `RES_TYPE` varchar(2) DEFAULT NULL,
  `URL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `URL` (`URL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_resource_info` */

insert  into `sys_resource_info`(`id`,`CREATE_TIME`,`CREATE_USER`,`UPDATE_TIME`,`UPDATE_USER`,`DEL_FLAG`,`DESCRIPTION`,`ENABLED`,`RES_NAME`,`RES_TYPE`,`URL`) values (11,'2014-04-03 14:45:55','1','2014-04-22 10:21:57','11','0','角色资源',NULL,'角色资源','','/roleInfo/**'),(13,'2014-04-11 16:05:31','2','2014-04-22 10:21:34','11','0','',NULL,'用户资源','','/userInfo/**'),(14,'2014-04-11 16:06:16','2',NULL,NULL,'0','',NULL,'菜单资源','','/menuInfo/**'),(16,'2014-04-11 16:07:59','2',NULL,NULL,'0','',NULL,'任务管理资源','','/dataswitch/**'),(17,'2014-04-11 16:08:32','2',NULL,NULL,'0','',NULL,'网络配置资源','','/networkCfg/**'),(18,'2014-04-11 16:09:17','2',NULL,NULL,'0','',NULL,'数据源资源','','/dataSource/**'),(19,'2014-04-11 16:10:05','2','2014-04-11 17:16:48','1','0','',NULL,'FTP用户管理资源','','/ftpservice/**'),(20,'2014-04-11 16:11:27','2',NULL,NULL,'0','',NULL,'系统日志资源','','/systemLog/**'),(21,'2014-04-11 16:12:05','2',NULL,NULL,'0','',NULL,'用户操作日志资源','','/userOperationLog/**'),(22,'2014-04-11 17:17:16','1','2014-04-17 14:46:01','11','0','',NULL,'FTP目录管理资源','','/ftp/**'),(24,'2014-04-15 20:02:54','11',NULL,NULL,'0','',NULL,'授权管理资源','','/sysauth/**'),(25,'2014-04-16 10:05:25','11',NULL,NULL,'0','',NULL,'系统信息资源','','/sysinfo/**'),(29,'2014-04-16 17:34:43','11','2014-04-16 17:36:38','11','0','',NULL,'系统状态资源','','/main.jsp'),(30,'2014-04-29 15:27:36','1',NULL,NULL,'0','',NULL,'参数配置资源','','/sysConfigInfo/**'),(31,'2014-05-20 11:12:36','1',NULL,NULL,'0','',NULL,'任务状态信息资源','','/dashboard/jobRunInfo/**');

/*Table structure for table `sys_role_info` */

DROP TABLE IF EXISTS `sys_role_info`;

CREATE TABLE `sys_role_info` (
  `id` bigint(20) NOT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `CREATE_USER` varchar(16) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(16) DEFAULT NULL,
  `DEL_FLAG` varchar(1) DEFAULT NULL,
  `DESCRIPTION` longtext,
  `ROLE_NAME` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ROLE_NAME` (`ROLE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_info` */

insert  into `sys_role_info`(`id`,`CREATE_TIME`,`CREATE_USER`,`UPDATE_TIME`,`UPDATE_USER`,`DEL_FLAG`,`DESCRIPTION`,`ROLE_NAME`) values (1,'2014-03-27 19:17:05','1','2014-05-20 11:18:32','1','0','管理员','管理员'),(3,'2014-03-27 19:17:05','1','2014-04-29 16:10:43','1','0','操作员','操作员'),(13,'2014-04-28 16:06:42','11','2014-04-29 15:28:24','1','0','超级管理员','超级管理员');

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `ROLE_ID` bigint(20) NOT NULL,
  `MENU_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ROLE_ID`,`MENU_ID`),
  KEY `FKAA83B076DD623B42` (`MENU_ID`),
  KEY `FKAA83B076AE76C62` (`ROLE_ID`),
  CONSTRAINT `FKAA83B076AE76C62` FOREIGN KEY (`ROLE_ID`) REFERENCES `sys_role_info` (`id`),
  CONSTRAINT `FKAA83B076DD623B42` FOREIGN KEY (`MENU_ID`) REFERENCES `sys_menu_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`ROLE_ID`,`MENU_ID`) values (1,3),(13,3),(13,4),(13,13),(1,18),(3,18),(13,18),(1,19),(3,19),(13,19),(1,20),(3,20),(13,20),(1,22),(3,22),(13,22),(1,23),(3,23),(13,23),(1,25),(3,25),(13,25),(1,26),(3,26),(13,26),(1,27),(13,27),(1,43),(3,43),(13,43),(13,49),(1,56);

/*Table structure for table `sys_user_info` */

DROP TABLE IF EXISTS `sys_user_info`;

CREATE TABLE `sys_user_info` (
  `id` bigint(20) NOT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `CREATE_USER` bigint(16) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(16) DEFAULT NULL,
  `DEL_FLAG` varchar(1) DEFAULT NULL,
  `DESCRIPTION` longtext,
  `PASSWORD` varchar(32) DEFAULT NULL,
  `REAL_NAME` varchar(32) DEFAULT NULL,
  `USERNAME` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `USERNAME` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_info` */

insert  into `sys_user_info`(`id`,`CREATE_TIME`,`CREATE_USER`,`UPDATE_TIME`,`UPDATE_USER`,`DEL_FLAG`,`DESCRIPTION`,`PASSWORD`,`REAL_NAME`,`USERNAME`) values (0,'2014-05-04 11:00:33',1,'2014-05-04 11:00:33','11','0','123','52d04dc20036dbd8','操作员','login'),(1,NULL,1,'2014-04-28 16:09:42','11','0','1234','52d04dc20036dbd8','系统管理员','sys'),(2,'2014-03-27 17:32:00',1,'2014-04-28 16:10:09','11','0','12345678\r\n','52d04dc20036dbd8','管理员','admin'),(14,'2014-04-28 16:51:08',11,NULL,NULL,'0','操作员','52d04dc20036dbd8','操作员','fable'),(16,'2014-05-22 14:53:37',2,NULL,NULL,'0','','8f00b204e9800998','','');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `USER_ID` bigint(20) NOT NULL,
  `ROLE_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`USER_ID`,`ROLE_ID`),
  KEY `FKAABB7D58AE76C62` (`ROLE_ID`),
  KEY `FKAABB7D58B0127DC2` (`USER_ID`),
  CONSTRAINT `FKAABB7D58AE76C62` FOREIGN KEY (`ROLE_ID`) REFERENCES `sys_role_info` (`id`),
  CONSTRAINT `FKAABB7D58B0127DC2` FOREIGN KEY (`USER_ID`) REFERENCES `sys_user_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`USER_ID`,`ROLE_ID`) values (2,1),(16,1),(14,3),(1,13);

/*Table structure for table `user_operation_log` */

DROP TABLE IF EXISTS `user_operation_log`;

CREATE TABLE `user_operation_log` (
  `id` bigint(20) NOT NULL,
  `OPERATION_DESCRIBE` varchar(255) DEFAULT NULL,
  `OPERATION_TIME` datetime DEFAULT NULL,
  `OPERATION_TYPE` longtext,
  `OPERATION_USER` varchar(19) DEFAULT NULL,
  `TARGET_DETAILL` longtext,
  `TARGET_ID` varchar(19) DEFAULT NULL,
  `TARGET_NAME` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_operation_log` */

insert  into `user_operation_log`(`id`,`OPERATION_DESCRIBE`,`OPERATION_TIME`,`OPERATION_TYPE`,`OPERATION_USER`,`TARGET_DETAILL`,`TARGET_ID`,`TARGET_NAME`) values (1,'用户管理操作日志：插入一条用户记录','2014-04-11 16:49:26','0','1','{createTime=Fri Apr 11 16:49:26 CST 2014, enabled=true, updateTime=null, class=class com.fable.dsp.dmo.system.user.UserInfo, accountNonExpired=true, password=52d04dc20036dbd8, id=10, username=fable, createUser=1, authorities=com.fable.dsp.dmo.system.user.RoleInfo@20, description=地方税, roles=com.fable.dsp.dmo.system.user.RoleInfo@20, realname=可右, credentialsNonExpired=true, updateUser=null, accountNonLocked=true, delFlag=0}','10','sys_user_info-username'),(2,'用户管理操作日志：插入一条用户记录','2014-04-15 16:54:44','0','1','{createTime=Tue Apr 15 16:54:44 CST 2014, enabled=true, updateTime=null, class=class com.fable.dsp.dmo.system.user.UserInfo, accountNonExpired=true, password=52d04dc20036dbd8, id=11, username=test, createUser=1, authorities=com.fable.dsp.dmo.system.user.RoleInfo@20, description=, roles=com.fable.dsp.dmo.system.user.RoleInfo@20, realname=测试, credentialsNonExpired=true, updateUser=null, accountNonLocked=true, delFlag=0}','11',NULL),(3,'用户管理操作日志：插入一条用户记录','2014-04-17 16:49:19','0','2','{createTime=Thu Apr 17 16:49:19 CST 2014, enabled=true, updateTime=null, class=class com.fable.dsp.dmo.system.user.UserInfo, accountNonExpired=true, password=52d04dc20036dbd8, id=12, username=test1, createUser=2, authorities=com.fable.dsp.dmo.system.user.RoleInfo@2b, description=测试, roles=com.fable.dsp.dmo.system.user.RoleInfo@2b, realname=测试, credentialsNonExpired=true, updateUser=null, accountNonLocked=true, delFlag=0}','12',NULL),(4,'用户管理操作日志：插入一条用户记录','2014-04-22 09:44:42','0','12','{createTime=Tue Apr 22 09:44:41 CST 2014, enabled=true, updateTime=null, class=class com.fable.dsp.dmo.system.user.UserInfo, accountNonExpired=true, password=52d04dc20036dbd8, id=13, username=test2, createUser=12, authorities=com.fable.dsp.dmo.system.user.RoleInfo@2b, description=, roles=com.fable.dsp.dmo.system.user.RoleInfo@2b, realname=测试, credentialsNonExpired=true, updateUser=null, accountNonLocked=true, delFlag=0}','13','sys_user_info-username'),(5,'用户管理操作日志：插入一条用户记录','2014-04-28 16:51:08','0','11','{createTime=Mon Apr 28 16:51:08 CST 2014, enabled=true, updateTime=null, class=class com.fable.dsp.dmo.system.user.UserInfo, accountNonExpired=true, password=52d04dc20036dbd8, id=14, username=fable, createUser=11, authorities=com.fable.dsp.dmo.system.user.RoleInfo@22, description=操作员, roles=com.fable.dsp.dmo.system.user.RoleInfo@22, realname=操作员, credentialsNonExpired=true, updateUser=null, accountNonLocked=true, delFlag=0}','14','sys_user_info-username'),(6,'用户管理操作日志：插入一条用户记录','2014-04-29 14:17:04','0','2','{createTime=Tue Apr 29 14:17:04 CST 2014, enabled=true, updateTime=null, class=class com.fable.dsp.dmo.system.user.UserInfo, accountNonExpired=true, password=52d04dc20036dbd8, id=15, username=admin3, createUser=2, authorities=com.fable.dsp.dmo.system.user.RoleInfo@20, description=可可可可, roles=com.fable.dsp.dmo.system.user.RoleInfo@20, realname=可可可可, credentialsNonExpired=true, updateUser=null, accountNonLocked=true, delFlag=0}','15','sys_user_info-username'),(7,'用户管理操作日志：插入一条用户记录','2014-04-30 10:49:23','0','1','{createTime=Wed Apr 30 10:49:23 CST 2014, enabled=true, updateTime=null, class=class com.fable.dsp.dmo.system.user.UserInfo, accountNonExpired=true, password=52d04dc20036dbd8, id=16, username=fable1, createUser=1, authorities=com.fable.dsp.dmo.system.user.RoleInfo@20, description=, roles=com.fable.dsp.dmo.system.user.RoleInfo@20, realname=才做元, credentialsNonExpired=true, updateUser=null, accountNonLocked=true, delFlag=0}','16','sys_user_info-username'),(9,'用户管理操作日志：插入一条用户记录','2014-04-30 10:53:20','0','1','{createTime=Wed Apr 30 10:53:20 CST 2014, enabled=true, updateTime=null, class=class com.fable.dsp.dmo.system.user.UserInfo, accountNonExpired=true, password=52d04dc20036dbd8, id=15, username=fable1, createUser=1, authorities=com.fable.dsp.dmo.system.user.RoleInfo@20, description=, roles=com.fable.dsp.dmo.system.user.RoleInfo@20, realname=萨芬, credentialsNonExpired=true, updateUser=null, accountNonLocked=true, delFlag=0}','15','sys_user_info-username'),(11,'用户管理操作日志：插入一条用户记录','2014-04-30 14:01:44','0','1','{createTime=Wed Apr 30 14:01:44 CST 2014, enabled=true, updateTime=null, class=class com.fable.dsp.dmo.system.user.UserInfo, accountNonExpired=true, password=52d04dc20036dbd8, id=15, username=ff, createUser=1, authorities=com.fable.dsp.dmo.system.user.RoleInfo@20, description=, roles=com.fable.dsp.dmo.system.user.RoleInfo@20, realname=圣诞节, credentialsNonExpired=true, updateUser=null, accountNonLocked=true, delFlag=0}','15','sys_user_info-username'),(12,'用户管理操作日志：插入一条用户记录','2014-05-22 14:53:37','0','2','{createTime=Thu May 22 14:53:37 CST 2014, enabled=true, updateTime=null, class=class com.fable.dsp.dmo.system.user.UserInfo, accountNonExpired=true, password=8f00b204e9800998, id=16, username=, createUser=2, authorities=com.fable.dsp.dmo.system.user.RoleInfo@20, description=, roles=com.fable.dsp.dmo.system.user.RoleInfo@20, realname=, credentialsNonExpired=true, updateUser=null, accountNonLocked=true, delFlag=0}','16','sys_user_info-username'),(3333,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
