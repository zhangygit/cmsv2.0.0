/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.6.17 : Database - cmsv2
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cmsv2` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `cmsv2`;

/*Table structure for table `jc_content` */

DROP TABLE IF EXISTS `jc_content`;

CREATE TABLE `jc_content` (
  `content_id` int(11) NOT NULL AUTO_INCREMENT,
  `channel_id` int(11) NOT NULL COMMENT '栏目ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `type_id` int(11) NOT NULL COMMENT '属性ID',
  `model_id` int(11) NOT NULL COMMENT '模型ID',
  `site_id` int(11) NOT NULL COMMENT '站点ID',
  `sort_date` datetime NOT NULL COMMENT '排序日期',
  `top_level` tinyint(4) NOT NULL DEFAULT '0' COMMENT '固顶级别',
  `has_title_img` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否有标题图',
  `is_recommend` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否推荐',
  `status` tinyint(4) NOT NULL DEFAULT '2' COMMENT '状态(0:草稿;1:审核中;2:审核通过;3:回收站)',
  `views_day` int(11) NOT NULL DEFAULT '0' COMMENT '日访问数',
  `comments_day` smallint(6) NOT NULL DEFAULT '0' COMMENT '日评论数',
  `downloads_day` smallint(6) NOT NULL DEFAULT '0' COMMENT '日下载数',
  `ups_day` smallint(6) NOT NULL DEFAULT '0' COMMENT '日顶数',
  PRIMARY KEY (`content_id`),
  KEY `fk_jc_content_site` (`site_id`),
  KEY `fk_jc_content_type` (`type_id`),
  KEY `fk_jc_content_user` (`user_id`),
  KEY `fk_jc_contentchannel` (`channel_id`),
  KEY `fk_jc_content_model` (`model_id`),
  CONSTRAINT `fk_jc_contentchannel` FOREIGN KEY (`channel_id`) REFERENCES `jc_channel` (`channel_id`),
  CONSTRAINT `fk_jc_content_model` FOREIGN KEY (`model_id`) REFERENCES `jc_model` (`model_id`),
  CONSTRAINT `fk_jc_content_site` FOREIGN KEY (`site_id`) REFERENCES `jc_site` (`site_id`),
  CONSTRAINT `fk_jc_content_type` FOREIGN KEY (`type_id`) REFERENCES `jc_content_type` (`type_id`),
  CONSTRAINT `fk_jc_content_user` FOREIGN KEY (`user_id`) REFERENCES `jc_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8 COMMENT='CMS内容表';

/*Data for the table `jc_content` */

insert  into `jc_content`(`content_id`,`channel_id`,`user_id`,`type_id`,`model_id`,`site_id`,`sort_date`,`top_level`,`has_title_img`,`is_recommend`,`status`,`views_day`,`comments_day`,`downloads_day`,`ups_day`) values (2,3,1,2,1,3,'2014-08-05 09:58:39',0,0,0,2,2,0,42,0),(3,6,1,2,1,3,'2014-08-05 10:16:47',0,0,0,2,0,0,0,0),(85,3,1,1,1,3,'2014-08-11 11:43:28',0,0,0,2,1,0,0,0),(86,3,1,1,1,3,'2014-08-11 11:44:03',0,0,0,2,27,0,0,0),(87,6,1,2,1,3,'2014-08-11 15:51:57',0,0,0,2,0,0,0,0),(88,13,1,2,1,3,'2014-08-11 15:54:04',0,0,0,2,0,0,0,0),(89,13,1,2,1,3,'2014-08-11 15:55:19',0,0,0,2,0,0,0,0),(90,14,1,2,1,3,'2014-08-11 15:56:03',0,0,0,2,0,0,0,0),(91,14,1,2,1,3,'2014-08-11 15:56:43',0,0,0,2,0,0,0,0),(92,15,1,2,1,3,'2014-08-11 15:57:44',0,0,0,2,0,0,0,0),(93,3,1,1,1,3,'2014-08-14 15:06:50',0,0,0,2,1,0,0,0),(94,3,1,1,1,3,'2014-08-18 16:12:56',0,0,0,2,11,0,0,0);

/*Table structure for table `jc_content_attachment` */

DROP TABLE IF EXISTS `jc_content_attachment`;

CREATE TABLE `jc_content_attachment` (
  `content_id` int(11) NOT NULL,
  `priority` int(11) NOT NULL COMMENT '排列顺序',
  `attachment_path` varchar(255) NOT NULL COMMENT '附件路径',
  `attachment_name` varchar(100) NOT NULL COMMENT '附件名称',
  `filename` varchar(100) DEFAULT NULL COMMENT '文件名',
  `download_count` int(11) NOT NULL DEFAULT '0' COMMENT '下载次数',
  KEY `fk_jc_attachment_content` (`content_id`),
  CONSTRAINT `fk_jc_attachment_content` FOREIGN KEY (`content_id`) REFERENCES `jc_content` (`content_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='CMS内容附件表';

/*Data for the table `jc_content_attachment` */

insert  into `jc_content_attachment`(`content_id`,`priority`,`attachment_path`,`attachment_name`,`filename`,`download_count`) values (2,0,'/u/cms/website/201408/05122108o3th.doc','赤峰商贸城与物流WMS接口通讯文档V1.1.doc','赤峰商贸城与物流WMS接口通讯文档V1.1.doc',0),(2,1,'/u/cms/website/201408/07163548x5nr.png','2a.png','2a.png',0),(2,2,'/u/cms/website/201408/07163619kpll.jpg','dl_tu.jpg','dl_tu.jpg',0),(2,3,'/u/cms/website/201408/07163628e4k6.png','face3.png','face3.png',0);

/*Table structure for table `jc_content_attr` */

DROP TABLE IF EXISTS `jc_content_attr`;

CREATE TABLE `jc_content_attr` (
  `content_id` int(11) NOT NULL,
  `attr_name` varchar(30) NOT NULL COMMENT '名称',
  `attr_value` varchar(255) DEFAULT NULL COMMENT '值',
  KEY `fk_jc_attr_content` (`content_id`),
  CONSTRAINT `fk_jc_attr_content` FOREIGN KEY (`content_id`) REFERENCES `jc_content` (`content_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='CMS内容扩展属性表';

/*Data for the table `jc_content_attr` */

/*Table structure for table `jc_content_channel` */

DROP TABLE IF EXISTS `jc_content_channel`;

CREATE TABLE `jc_content_channel` (
  `channel_id` int(11) NOT NULL,
  `content_id` int(11) NOT NULL,
  PRIMARY KEY (`channel_id`,`content_id`),
  KEY `fk_jc_channel_content` (`content_id`),
  CONSTRAINT `fk_jc_channel_content` FOREIGN KEY (`content_id`) REFERENCES `jc_content` (`content_id`),
  CONSTRAINT `fk_jc_content_channel` FOREIGN KEY (`channel_id`) REFERENCES `jc_channel` (`channel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='CMS内容栏目关联表';

/*Data for the table `jc_content_channel` */

insert  into `jc_content_channel`(`channel_id`,`content_id`) values (3,2),(6,3),(3,85),(3,86),(6,87),(13,88),(13,89),(14,90),(14,91),(15,92),(3,93),(3,94);

/*Table structure for table `jc_content_check` */

DROP TABLE IF EXISTS `jc_content_check`;

CREATE TABLE `jc_content_check` (
  `content_id` int(11) NOT NULL,
  `check_step` tinyint(4) NOT NULL DEFAULT '0' COMMENT '审核步数',
  `check_opinion` varchar(255) DEFAULT NULL COMMENT '审核意见',
  `is_rejected` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否退回',
  `reviewer` int(11) DEFAULT NULL COMMENT '终审者',
  `check_date` datetime DEFAULT NULL COMMENT '终审时间',
  PRIMARY KEY (`content_id`),
  KEY `fk_jc_content_check_user` (`reviewer`),
  CONSTRAINT `fk_jc_check_content` FOREIGN KEY (`content_id`) REFERENCES `jc_content` (`content_id`),
  CONSTRAINT `fk_jc_content_check_user` FOREIGN KEY (`reviewer`) REFERENCES `jc_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='CMS内容审核信息表';

/*Data for the table `jc_content_check` */

insert  into `jc_content_check`(`content_id`,`check_step`,`check_opinion`,`is_rejected`,`reviewer`,`check_date`) values (2,2,NULL,0,NULL,NULL),(3,2,NULL,0,NULL,NULL),(85,2,NULL,0,NULL,NULL),(86,2,NULL,0,NULL,NULL),(87,2,NULL,0,NULL,NULL),(88,2,NULL,0,NULL,NULL),(89,2,NULL,0,NULL,NULL),(90,2,NULL,0,NULL,NULL),(91,2,NULL,0,NULL,NULL),(92,2,NULL,0,NULL,NULL),(93,2,NULL,0,NULL,NULL),(94,2,NULL,0,NULL,NULL);

/*Table structure for table `jc_content_count` */

DROP TABLE IF EXISTS `jc_content_count`;

CREATE TABLE `jc_content_count` (
  `content_id` int(11) NOT NULL,
  `views` int(11) NOT NULL DEFAULT '0' COMMENT '总访问数',
  `views_month` int(11) NOT NULL DEFAULT '0' COMMENT '月访问数',
  `views_week` int(11) NOT NULL DEFAULT '0' COMMENT '周访问数',
  `views_day` int(11) NOT NULL DEFAULT '0' COMMENT '日访问数',
  `comments` int(11) NOT NULL DEFAULT '0' COMMENT '总评论数',
  `comments_month` int(11) NOT NULL DEFAULT '0' COMMENT '月评论数',
  `comments_week` smallint(6) NOT NULL DEFAULT '0' COMMENT '周评论数',
  `comments_day` smallint(6) NOT NULL DEFAULT '0' COMMENT '日评论数',
  `downloads` int(11) NOT NULL DEFAULT '0' COMMENT '总下载数',
  `downloads_month` int(11) NOT NULL DEFAULT '0' COMMENT '月下载数',
  `downloads_week` smallint(6) NOT NULL DEFAULT '0' COMMENT '周下载数',
  `downloads_day` smallint(6) NOT NULL DEFAULT '0' COMMENT '日下载数',
  `ups` int(11) NOT NULL DEFAULT '0' COMMENT '总顶数',
  `ups_month` int(11) NOT NULL DEFAULT '0' COMMENT '月顶数',
  `ups_week` smallint(6) NOT NULL DEFAULT '0' COMMENT '周顶数',
  `ups_day` smallint(6) NOT NULL DEFAULT '0' COMMENT '日顶数',
  `downs` int(11) NOT NULL DEFAULT '0' COMMENT '总踩数',
  PRIMARY KEY (`content_id`),
  CONSTRAINT `fk_jc_count_content` FOREIGN KEY (`content_id`) REFERENCES `jc_content` (`content_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='CMS内容计数表';

/*Data for the table `jc_content_count` */

insert  into `jc_content_count`(`content_id`,`views`,`views_month`,`views_week`,`views_day`,`comments`,`comments_month`,`comments_week`,`comments_day`,`downloads`,`downloads_month`,`downloads_week`,`downloads_day`,`ups`,`ups_month`,`ups_week`,`ups_day`,`downs`) values (2,19,19,2,2,0,0,0,0,42,42,1,42,0,0,0,0,0),(3,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(85,2,2,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0),(86,28,28,27,27,0,0,0,0,0,0,0,0,0,0,0,0,0),(87,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(88,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(89,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(90,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(91,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(92,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(93,5,5,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0),(94,11,11,11,11,0,0,0,0,0,0,0,0,0,0,0,0,0);

/*Table structure for table `jc_content_ext` */

DROP TABLE IF EXISTS `jc_content_ext`;

CREATE TABLE `jc_content_ext` (
  `content_id` int(11) NOT NULL,
  `title` varchar(150) NOT NULL COMMENT '标题',
  `short_title` varchar(150) DEFAULT NULL COMMENT '简短标题',
  `author` varchar(100) DEFAULT NULL COMMENT '作者',
  `origin` varchar(100) DEFAULT NULL COMMENT '来源',
  `origin_url` varchar(255) DEFAULT NULL COMMENT '来源链接',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `release_date` datetime NOT NULL COMMENT '发布日期',
  `media_path` varchar(255) DEFAULT NULL COMMENT '媒体路径',
  `title_color` varchar(10) DEFAULT NULL COMMENT '标题颜色',
  `is_bold` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否加粗',
  `title_img` varchar(100) DEFAULT NULL COMMENT '标题图片',
  `content_img` varchar(100) DEFAULT NULL COMMENT '内容图片',
  `type_img` varchar(100) DEFAULT NULL COMMENT '类型图片',
  `link` varchar(255) DEFAULT NULL COMMENT '外部链接',
  `tpl_content` varchar(100) DEFAULT NULL COMMENT '指定模板',
  `need_regenerate` tinyint(1) NOT NULL DEFAULT '1' COMMENT '需要重新生成静态页',
  PRIMARY KEY (`content_id`),
  CONSTRAINT `fk_jc_ext_content` FOREIGN KEY (`content_id`) REFERENCES `jc_content` (`content_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='CMS内容扩展表';

/*Data for the table `jc_content_ext` */

insert  into `jc_content_ext`(`content_id`,`title`,`short_title`,`author`,`origin`,`origin_url`,`description`,`release_date`,`media_path`,`title_color`,`is_bold`,`title_img`,`content_img`,`type_img`,`link`,`tpl_content`,`need_regenerate`) values (2,'免费建网站≠免费建网站服务',NULL,NULL,NULL,NULL,NULL,'2014-08-05 09:58:39','/u/cms/website/201408/convert/18153339td0x.flv',NULL,0,NULL,NULL,'/u/cms/website/201408/07165048rwf8-thumb.png',NULL,NULL,1),(3,'服饰专题页面设计',NULL,NULL,NULL,NULL,NULL,'2014-08-05 10:16:47',NULL,NULL,0,NULL,NULL,'/u/cms/website/201408/11155007z2b6-thumb.jpg',NULL,NULL,1),(85,'挖掘企业网站内容资料',NULL,NULL,NULL,NULL,NULL,'2014-08-11 11:43:28','/u/cms/website/201408/convert/18112021ljnc.flv',NULL,0,NULL,NULL,NULL,NULL,NULL,1),(86,'抢商机拉客户先建网站再开公司',NULL,NULL,NULL,NULL,NULL,'2014-08-11 11:44:03','/u/cms/website/201408/convert/18110940vn1x.flv',NULL,0,NULL,NULL,NULL,NULL,NULL,1),(87,'化工生物药业企业网站建设',NULL,NULL,NULL,NULL,NULL,'2014-08-11 15:51:57',NULL,NULL,0,NULL,NULL,'/u/cms/website/201408/11155154rjt4-thumb.jpg',NULL,NULL,1),(88,'文化传播礼品企业网站建设',NULL,NULL,NULL,NULL,NULL,'2014-08-11 15:54:04',NULL,NULL,0,NULL,NULL,'/u/cms/website/201408/111554011z2i-thumb.jpg',NULL,NULL,1),(89,'花木园林企业网站建设 ',NULL,NULL,NULL,NULL,NULL,'2014-08-11 15:55:19',NULL,NULL,0,NULL,NULL,'/u/cms/website/201408/11155516frgo-thumb.jpg',NULL,NULL,1),(90,'钢琴吉他乐器企业网站建设 ',NULL,NULL,NULL,NULL,NULL,'2014-08-11 15:56:03',NULL,NULL,0,NULL,NULL,'/u/cms/website/201408/11155600lx3n-thumb.jpg',NULL,NULL,1),(91,'儿童婚纱摄影企业网站建设 ',NULL,NULL,NULL,NULL,NULL,'2014-08-11 15:56:43',NULL,NULL,0,NULL,NULL,'/u/cms/website/201408/11155640d6ke-thumb.jpg',NULL,NULL,1),(92,'西点建材公司网站建设 ',NULL,NULL,NULL,NULL,NULL,'2014-08-11 15:57:44','/u/cms/website/201408/convert/18104506e0kb.flv',NULL,0,NULL,NULL,'/u/cms/website/201408/111557412by0-thumb.jpg',NULL,NULL,1),(93,'笑红尘',NULL,NULL,NULL,NULL,NULL,'2014-08-14 15:06:50','/u/cms/website/201408/convert/18160509us3n.flv',NULL,0,NULL,NULL,NULL,NULL,NULL,1),(94,'我可以抱你吗',NULL,NULL,NULL,NULL,NULL,'2014-08-18 16:12:56','/u/cms/website/201408/convert/18161253gpli.flv',NULL,0,NULL,NULL,NULL,NULL,NULL,1);

/*Table structure for table `jc_content_group_view` */

DROP TABLE IF EXISTS `jc_content_group_view`;

CREATE TABLE `jc_content_group_view` (
  `content_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`content_id`,`group_id`),
  KEY `fk_jc_content_group_v` (`group_id`),
  CONSTRAINT `fk_jc_content_group_v` FOREIGN KEY (`group_id`) REFERENCES `jc_group` (`group_id`),
  CONSTRAINT `fk_jc_group_content_v` FOREIGN KEY (`content_id`) REFERENCES `jc_content` (`content_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='CMS内容浏览会员组关联表';

/*Data for the table `jc_content_group_view` */

/*Table structure for table `jc_content_media` */

DROP TABLE IF EXISTS `jc_content_media`;

CREATE TABLE `jc_content_media` (
  `content_id` int(11) NOT NULL,
  `priority` int(11) DEFAULT NULL,
  `media_path` varchar(255) DEFAULT NULL,
  `media_name` varchar(100) DEFAULT NULL,
  `filename` varchar(100) DEFAULT NULL,
  `media_type` varchar(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  KEY `FK_jc_content_media` (`content_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Data for the table `jc_content_media` */

/*Table structure for table `jc_content_picture` */

DROP TABLE IF EXISTS `jc_content_picture`;

CREATE TABLE `jc_content_picture` (
  `content_id` int(11) NOT NULL,
  `priority` int(11) NOT NULL COMMENT '排列顺序',
  `img_path` varchar(100) NOT NULL COMMENT '图片地址',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`content_id`,`priority`),
  CONSTRAINT `fk_jc_picture_content` FOREIGN KEY (`content_id`) REFERENCES `jc_content` (`content_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='CMS内容图片表';

/*Data for the table `jc_content_picture` */

insert  into `jc_content_picture`(`content_id`,`priority`,`img_path`,`description`) values (2,0,'/u/cms/website/201408/06163056vo2z-thumb.gif',''),(2,1,'/u/cms/website/201408/06163123a4z1-thumb.jpg',''),(2,2,'/u/cms/website/201408/0616311386yu-thumb.png',''),(2,3,'/u/cms/website/201408/061631303awb-thumb.png',''),(2,4,'/u/cms/website/201408/061631428mj7-thumb.jpg',''),(2,5,'/u/cms/website/201408/061637205bpm-thumb.jpg',''),(2,6,'/u/cms/website/201408/06163734onnc-thumb.png',''),(87,0,'/u/cms/website/201408/11165534vt9p-thumb.jpg',''),(87,1,'/u/cms/website/201408/11165537mkee-thumb.jpg',''),(87,2,'/u/cms/website/201408/11165542n60s-thumb.jpg',''),(87,3,'/u/cms/website/201408/1116554786p1-thumb.jpg',''),(87,4,'/u/cms/website/201408/11165552ekub-thumb.jpg',''),(87,5,'/u/cms/website/201408/1116555610mu-thumb.jpg',''),(87,6,'/u/cms/website/201408/11165602qsu1-thumb.jpg','');

/*Table structure for table `jc_content_tag` */

DROP TABLE IF EXISTS `jc_content_tag`;

CREATE TABLE `jc_content_tag` (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(50) NOT NULL COMMENT 'tag名称',
  `ref_counter` int(11) NOT NULL DEFAULT '1' COMMENT '被引用的次数',
  PRIMARY KEY (`tag_id`),
  UNIQUE KEY `ak_tag_name` (`tag_name`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8 COMMENT='CMS内容TAG表';

/*Data for the table `jc_content_tag` */

insert  into `jc_content_tag`(`tag_id`,`tag_name`,`ref_counter`) values (1,'2011',1),(2,'中国',1),(9,'机构',0),(16,'基金',0),(52,'调控',0),(58,'免费',2),(59,'建网站',3),(60,'服务',1),(61,'服饰',1),(62,'专题',1),(63,'页面',1),(64,'设计',1),(65,'挖掘',1),(66,'企业',6),(67,'网站',7),(68,'内容',1),(69,'资料',1),(70,'抢',1),(71,'商机',1),(72,'拉客户',1),(73,'先',1),(74,'再',1),(75,'开',1),(76,'公司',2),(77,'化工',1),(78,'生物',1),(79,'药业',1),(80,'建设',6),(81,'文化',1),(82,'传播',1),(83,'礼品',1),(84,'花木',1),(85,'园林',1),(86,'钢琴',1),(87,'吉他',1),(88,'乐器',1),(89,'儿童',1),(90,'婚纱',1),(91,'摄影',1),(92,'西点',1),(93,'建材',1),(94,'笑红尘',1),(95,'我',1),(96,'可以',1),(97,'抱你',1),(98,'吗',1);

/*Table structure for table `jc_content_topic` */

DROP TABLE IF EXISTS `jc_content_topic`;

CREATE TABLE `jc_content_topic` (
  `content_id` int(11) NOT NULL,
  `topic_id` int(11) NOT NULL,
  PRIMARY KEY (`content_id`,`topic_id`),
  KEY `fk_jc_content_topic` (`topic_id`),
  CONSTRAINT `fk_jc_content_topic` FOREIGN KEY (`topic_id`) REFERENCES `jc_topic` (`topic_id`),
  CONSTRAINT `fk_jc_topic_content` FOREIGN KEY (`content_id`) REFERENCES `jc_content` (`content_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='CMS专题内容关联表';

/*Data for the table `jc_content_topic` */

/*Table structure for table `jc_content_txt` */

DROP TABLE IF EXISTS `jc_content_txt`;

CREATE TABLE `jc_content_txt` (
  `content_id` int(11) NOT NULL,
  `txt` longtext COMMENT '文章内容',
  `txt1` longtext COMMENT '扩展内容1',
  `txt2` longtext COMMENT '扩展内容2',
  `txt3` longtext COMMENT '扩展内容3',
  PRIMARY KEY (`content_id`),
  CONSTRAINT `fk_jc_txt_content` FOREIGN KEY (`content_id`) REFERENCES `jc_content` (`content_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='CMS内容文本表';

/*Data for the table `jc_content_txt` */

insert  into `jc_content_txt`(`content_id`,`txt`,`txt1`,`txt2`,`txt3`) values (2,'<p>怎么建网站？不同的客户有不同的方法，而一些初次建网站的客户就是用免费建网站的方式来操作的，常听到某某客户告诉我们网上有免费建网站的事情，并 且还告诉我介绍内容，比如&times;&times;免费建站是一个专门为重兄企业提供免费空间和服务的建站技术网站，拥有丰富的网站模板，具备自助建站的简单操作特点，网站功 能较大，为中小企业免费建立网上宣传平台。我看其介绍和内容，确实强大。确实免费。那客户提出一个疑问，那他们建网站都是为人民服务了，他们的工作成本都 到什么地方去了啊？</p>\r\n<p>难道他们做事情都不需要成本，让我们都进入了共产主义社会了。全免费<a href=\"http://www.fena.cn/wangzhanzixun/\" target=\"_blank\" title=\"\">建网站</a>。</p>\r\n<p>我再仔细看了这些免费建站的网站，我觉得有以下几种可能性：</p>\r\n<p>让客户免费建网站，让客户试用一段时间后，就谈建网站服务；试用免费建网站的客户基本都是初次建网站的客户，他们对网站的建设和调整都不熟悉，对网 站还处于一个朦胧的阶段，他们还不知道建网站需要添加产品，不知道如何处理和制作图片，如何设计产品的摆放，如何让网站界面美观，如何写产品的描述，等等 一些问题都存在，这个时候就需要有对网站内容建设提供服务，既然是在这个地方建的网站，那么向建网站的公司提出提供<a href=\"http://www.fena.cn\" target=\"_blank\" title=\"\">网站建设</a>服务就是很正常的一种方式，这个时候收取客户的服务费用是很普遍的事情。这里是<a href=\"http://www.fena.cn/wangzhanzixun/\" target=\"_blank\" title=\"\">建网站公司</a>可以提供的普通服务。在这里为您免费建网站的公司可以获取一部分的人工效益。</p>\r\n<p>让客户免费建网站，在客户宣传的网站里植入某些产品广告；免费建网站公司提出的口号是免费建网站，那他们必须有一个途径来获利，而通过点击广告获取 利益也是网络上通用的一种操作模式，这种模式就是通过在网站上放置百度、Google的广告来获取点击收取费用。这些广告收益是建网站公司正常的收益范 围。总不能说为客户免费的建立了网站，而不能为建网站公司带来一点利益，这个说法大家也是不能接受的。</p>\r\n<p>让客户免费建网站，建立一段时间后，向客户推荐网站推广产品收费服务；免费建立了网站，总不能不推广吧，网站只是一个公司的宣传平 台，并不是说建了网站就一定有客户来咨询，需要建网站的客户不断的去宣传这个网站，而采用什么手段呢？这里就需要不同的网站推广产品来满足客户的需求。那 么免费建网站的公司就有义务为这些客户解除这样的烦恼，为客户的网站推广提供各种推广产品服务。而这部分也是一种获取利益的方式。</p>\r\n',NULL,NULL,NULL),(85,'<p><strong>企业建网站</strong>离不开网站内容资料，这些资料那里才有呢？<strong>企业网站建设者</strong>要到那里去寻找呢？这些头痛的问题每个网站建设者都遇到过。更何况，网站的内容资料和企业的宣传内容是否一致呢？这些都需要精心考虑。</p>\r\n<p><a href=\"http://www.fena.cn\" target=\"_blank\" title=\"企业网站建设,公司网站建设\">企业</a><a href=\"http://www.fena.cn\" target=\"_blank\" title=\"\">网站建设</a>需要准备网站的3个方面的内容</p>\r\n<p>第一，就是企业产品资料。比如产品的图片，产品的介绍，主推产品的营销介绍等。这些在企业网站建设过程中用到，需要企业用心准备，网络浏览客户能知道企业的网站是否是用心的。他们在浏览大量的网站后会详细对比，而产生印象。</p>\r\n<p>第二，网站需要的宣传的面的介绍，企业网站建设需要解决什么问题，而准备的资料。比如企业要招聘人员，需要放上最近公司的媒体视频等。</p>\r\n<p>第三，企业网站栏目的开设，针对需要解决的问题，开设不同的栏目来放上相关的资料，让顾客能明了在企业网站中，如何找到自己需要的资料。</p>\r\n',NULL,NULL,NULL),(86,'<p>没有客户我们怎么生存？没有业务我们怎么发展？没有网站我们怎么发展？快建立网站宣传自己吧！</p>\r\n<p>在当今快节奏的社会，在当今宣传型的社会，你还考虑不宣传自己，还不建立网站来宣传自己，那只有落后，那只有等客户。这样的思路不符合当前社会，你的业务也不会好到哪里去。加强宣传自己，加快<a href=\"http://www.fena.cn\" target=\"_blank\" title=\"\">网站建设</a>步伐，现在那个人不宣传自己，你在宣传过程中在开设公司这样才符合企业发展啊。没有说那个公司不做宣传的，只有说那个公司宣传的更好的。</p>\r\n<p>为了扩大业务，为了吸引更多的客户，建立网站宣传自己吧。</p>\r\n',NULL,NULL,NULL),(93,'<div class=\"site-mod site-my\" id=\"site-my-list\">\r\n	<ul>\r\n		<li class=\"site-multiple \">\r\n			<a href=\"http://www.baidu.com/index.php?tn=monline_5_dg\" title=\"百度\">百度</a><a class=\"site-sub  first \" href=\"http://tieba.baidu.com/\" title=\"贴吧\">贴吧</a></li>\r\n		<li class=\"site-multiple site-dropdown\">\r\n			<a class=\"dropdown-menu\" href=\"http://www.sina.com.cn/\" style=\"width:45px\" title=\"新 浪\">新 浪</a></li>\r\n		<li>\r\n			<a href=\"http://weibo.com/?c=spr_web_sq_firefox_weibo_t001\" title=\"微　博\">微　博</a></li>\r\n		<li>\r\n			<a href=\"http://www.xinhuanet.com/\" title=\"新 华 网\">新 华 网</a></li>\r\n		<li>\r\n			<a href=\"http://www.163.com/\" title=\"网　易\">网　易</a></li>\r\n		<li>\r\n			<a href=\"http://www.ifeng.com/\" title=\"凤 凰 网\">凤 凰 网</a></li>\r\n		<li>\r\n			<a href=\"http://www.qq.com/\" title=\"腾　讯\">腾　讯</a></li>\r\n		<li>\r\n			<a href=\"http://www.firefoxchina.cn/\" title=\"火狐中文网\">火狐中文网</a></li>\r\n		<li>\r\n			<a href=\"http://cps.youku.com/redirect.html?id=00000292\" title=\"优 酷 网\">优 酷 网</a></li>\r\n		<li>\r\n			<a href=\"http://www.iqiyi.com/\" title=\"爱 奇 艺\">爱 奇 艺</a></li>\r\n		<li>\r\n			<a href=\"http://www.cntv.cn/index.shtml\" title=\"央 视 网\">央 视 网</a></li>\r\n		<li>\r\n			<a href=\"http://renren.com/\" title=\"人 人 网\">人 人 网</a></li>\r\n		<li>\r\n			<a href=\"http://www.4399.com/\" title=\"4 3 9 9\">4 3 9 9</a></li>\r\n		<li>\r\n			<a href=\"http://qzone.qq.com/\" title=\"QQ空间\">QQ空间</a></li>\r\n	</ul>\r\n</div>\r\n<div class=\"site-sys-mod \" id=\"site-sys-1\" style=\"display:block;\">\r\n	<ul>\r\n		<li>\r\n			<a href=\"http://u.ctrip.com/union/CtripRedirect.aspx?TypeID=2&amp;Allianceid=1381&amp;sid=1624&amp;OUID=&amp;jumpUrl=http://www.ctrip.com\" title=\"携程旅行网\">携程旅行网</a></li>\r\n		<li>\r\n			<a href=\"http://www.fang.com/\" title=\"搜 房 网\">搜 房 网</a></li>\r\n		<li>\r\n			<a href=\"http://www.vogue.com.cn/\" title=\"VOGUE时尚\">VOGUE时尚</a></li>\r\n		<li>\r\n			<a href=\"http://www.eastmoney.com/\" title=\"东方财富\">东方财富</a></li>\r\n		<li class=\"site-multiple\">\r\n			<a href=\"http://www.tianya.cn/\" title=\"天涯\">天涯</a><a class=\"site-sub  \" href=\"http://www.mop.com/\" title=\"猫扑\">猫扑</a></li>\r\n		<li>\r\n			<a href=\"http://www.taobao.com/\" title=\"淘 宝 网\">淘 宝 网</a></li>\r\n		<li>\r\n			<a href=\"http://www.ganji.com/?ca_name=dh_huohu_quanguoshouye\" title=\"赶 集 网\">赶 集 网</a></li>\r\n		<li>\r\n			<a href=\"http://ai.taobao.com?pid=mm_28347190_2425761_16312272\" style=\"width:100px;\" title=\"爱淘宝(特卖)\">爱淘宝(特卖)</a></li>\r\n		<li>\r\n			<a href=\"http://s.click.taobao.com/t?e=m%3D2%26s%3DGhni6O8XTBUcQipKwQzePCperVdZeJviEViQ0P1Vf2kguMN8XjClAunGw%2FP9iJtEYknTRG9bvKRB7pb%2FgtNRqZt%2BCJFZpnsP8cLd%2BlHMehrCIZ5ub5r%2FJeo0BcZWWIRYYA%2FDpPH01wK9AmARIwX9K%2BAjBDXvuqoU47FHjfsActnIQu5PdXpojKJn5AyUbPoV\" title=\"聚 划 算\">聚 划 算</a></li>\r\n		<li>\r\n			<a href=\"http://www.yhd.com?tracker_u=10977119545&amp;union_ref=7\" title=\"1 号 店\">1 号 店</a></li>\r\n		<li class=\"site-multiple\">\r\n			<a href=\"http://www.amazon.cn/?source=Mozilla\" style=\"width:100px\" title=\"亚 马 逊\">亚 马 逊</a><a class=\"site-sub  \" href=\"http://www.amazon.cn/b?ie=UTF8&amp;node=276134071&amp;tag=mozilla\" style=\"z-index: 10;background: url(http://img.firefoxchina.cn/2014/08/20140805110200_111111.png) 0px 0px no-repeat;position: absolute;right: -24px;top: 4px;text-indent:-9999em;width: 40px;height:20px;overflow: hidden;\" title=\"亚马逊1元抢\">亚马逊1元抢</a></li>\r\n		<li>\r\n			<a href=\"http://c.duomai.com/track.php?site_id=347&amp;aid=708&amp;euid=&amp;t=http%3A%2F%2Fwww.dangdang.com%2F\" title=\"当 当 网\">当 当 网</a></li>\r\n		<li>\r\n			<a href=\"http://click.lergao.com/go.aspx?site=jumei&amp;uid=21958\" title=\"聚美优品\">聚美优品</a></li>\r\n		<li>\r\n			<a href=\"http://www.gome.com.cn/?cmpid=dh_huohu_mz\" title=\"国美在线\">国美在线</a></li>\r\n		<li>\r\n			<a href=\"http://www.tuniu.com/?p=13895&amp;cmpid=mkt_03028301&amp;utm_campaign=Lifestyle&amp;utm_source=firefoxchina&amp;utm_medium=referral\" title=\"途牛旅游网\">途牛旅游网</a></li>\r\n		<li>\r\n			<a href=\"http://hotel.elong.com/?banid=firefox\" title=\"艺龙酒店\">艺龙酒店</a></li>\r\n		<li>\r\n			<a href=\"http://s.click.taobao.com/t?e=m%3D2%26s%3DAI%2BtO%2FSURhAcQipKwQzePCperVdZeJviEViQ0P1Vf2kguMN8XjClAkQ%2FMIKXkuE%2B8DyfZK95mJNB7pb%2FgtNRqZt%2BCJFZpnsPL6HDuigafNQB1RX35o0ZDn7uq6sLYIqu0Q7QOybCaQy9AmARIwX9K9E0MBlxnM%2FDnaYpFBIfC%2F1nOz7XkZaoGoxa3vd5d7ugLbcK0SpY4rJxQR%2BKFY%2FY3xbofrrrsbVfMVFFAY1N49ljj0JDUvXfcW7Es1ETSoRVxg5p7bh%2BFbQ%3D\" title=\"淘宝旅行\">淘宝旅行</a></li>\r\n		<li>\r\n			<a href=\"http://www.niuche.com/\" title=\"牛车汽车网\">牛车汽车网</a></li>\r\n		<li>\r\n			<a href=\"http://count.chanet.com.cn/click.cgi?a=433588&amp;d=348491&amp;u=&amp;e=\" title=\"苹果商城\">苹果商城</a></li>\r\n		<li>\r\n			<a href=\"http://2sc.iautos.cn/?utm_source=firefox&amp;utm_medium=cpc&amp;utm_term=firefox\" title=\"第一车网\">第一车网</a></li>\r\n		<li>\r\n			<a href=\"http://www.qunar.com/?ex_track=auto_50e674e3\" title=\"去哪儿网\">去哪儿网</a></li>\r\n		<li class=\"site-multiple\">\r\n			<a href=\"http://click.union.vip.com/redirect.php?url=eyJkZXN0dXJsIjoiaHR0cDpcL1wvd3d3LnZpcC5jb21cLyIsInVjb2RlIjoiOTc2OGRmNGIiLCJzY2hlbWVjb2RlIjoiZDEyaGRpNWwifQ==\" style=\"width:100px\" title=\"唯 品 会\">唯 品 会</a><a class=\"site-sub  \" href=\"http://click.union.vip.com/redirect.php?url=eyJkZXN0dXJsIjoiaHR0cDpcL1wvd3d3LnZpcC5jb21cLzgwOVwvP2RhdGU9ODE0IiwidWNvZGUiOiI5NzY4ZGY0YiIsInNjaGVtZWNvZGUiOiJkMTJoZGk1bCJ9\" style=\"position: absolute;background: url(http://img.firefoxchina.cn/2014/08/20140813184904_vip_circle_2.png)0px 0px no-repeat;width: 50px;height: 30px;line-height: 28px;text-align: left;font-size: 12px;color: #b04375;text-indent: 0px;top: 1px;right: -31px;\" title=\"来撒娇\">来撒娇</a></li>\r\n		<li>\r\n			<a href=\"https://www.yooli.com/?sid=25&amp;aid=firefox&amp;utm_source=firefox&amp;utm_medium=cpc&amp;utm_campaign=firefox&amp;forceRecord=1\" title=\"有 利 网\">有 利 网</a></li>\r\n		<li>\r\n			<a href=\"http://shop.boqii.com/?utm_source=Firefox&amp;utm_medium=DH&amp;utm_campaign=SY\" title=\"波奇宠物\">波奇宠物</a></li>\r\n		<li>\r\n			<a href=\"http://c.duomai.com/track.php?site_id=347&amp;aid=299&amp;euid=&amp;t=http%3A%2F%2Ft.dianping.com%2F\" title=\"大众点评团\">大众点评团</a></li>\r\n		<li>\r\n			<a href=\"http://youxi.baidu.com/yxpm/pm.jsp?pid=101110069800091_2492296\" title=\"百度游戏\">百度游戏</a></li>\r\n		<li>\r\n			<a href=\"http://tj.ad12345.com/?id=108\" title=\"决胜教育\">决胜教育</a></li>\r\n		<li>\r\n			<a href=\"http://www.zhe800.com/?utm_medium=dh&amp;utm_source=huohu&amp;utm_content=mz&amp;utm_campaign=ad_c1\" title=\"折 800\">折 800</a></li>\r\n		<li>\r\n			<a href=\"http://count.chanet.com.cn/click.cgi?a=433588&amp;d=347618&amp;u=&amp;e=&amp;url=http%3A%2F%2Fwww.51buy.com\" title=\"易 迅 网\">易 迅 网</a></li>\r\n		<li>\r\n			<a href=\"http://click.lergao.com/go.aspx?site=suningroi&amp;uid=219581\" title=\"苏宁易购\">苏宁易购</a></li>\r\n		<li>\r\n			<a href=\"http://c.duomai.com/track.php?site_id=347&amp;aid=304&amp;euid=&amp;t=http%3A%2F%2Fwww.lefeng.com%2F\" title=\"乐 蜂 网\">乐 蜂 网</a></li>\r\n		<li>\r\n			<a href=\"http://c.duomai.com/track.php?site_id=122877&amp;aid=61&amp;euid=&amp;t=http%3A%2F%2Fwww.jd.com%2F\" style=\"width:60px;\" title=\"京东商城\">京东商城</a></li>\r\n		<li>\r\n			<a href=\"http://cps.yintai.com/Websource.aspx?source=ifirefox&amp;url=http://www.yintai.com/bargain/bargainhome.aspx\" title=\"银 泰 网\">银 泰 网</a></li>\r\n		<li>\r\n			<a _hover-ignore=\"1\" href=\"http://www.tmall.com/\" title=\"天猫商城\">天猫商城</a></li>\r\n		<li>\r\n			<a _hover-ignore=\"1\" href=\"http://r.union.meituan.com/url/visit/?a=1&amp;key=yKmOefsJ5QiYS98RvpLzMN2qxT7BFhr4&amp;url=http://www.meituan.com\" title=\"美 团 网\">美 团 网</a></li>\r\n	</ul>\r\n</div>\r\n<p>&nbsp;</p>\r\n',NULL,NULL,NULL);

/*Table structure for table `jc_content_type` */

DROP TABLE IF EXISTS `jc_content_type`;

CREATE TABLE `jc_content_type` (
  `type_id` int(11) NOT NULL,
  `type_name` varchar(20) NOT NULL COMMENT '名称',
  `img_width` int(11) DEFAULT NULL COMMENT '图片宽',
  `img_height` int(11) DEFAULT NULL COMMENT '图片高',
  `has_image` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否有图片',
  `is_disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='CMS内容类型表';

/*Data for the table `jc_content_type` */

insert  into `jc_content_type`(`type_id`,`type_name`,`img_width`,`img_height`,`has_image`,`is_disabled`) values (1,'普通',100,100,0,0),(2,'图文',143,98,1,0);

/*Table structure for table `jc_contenttag` */

DROP TABLE IF EXISTS `jc_contenttag`;

CREATE TABLE `jc_contenttag` (
  `content_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL,
  `priority` int(11) NOT NULL,
  KEY `fk_jc_content_tag` (`tag_id`),
  KEY `fk_jc_tag_content` (`content_id`),
  CONSTRAINT `fk_jc_content_tag` FOREIGN KEY (`tag_id`) REFERENCES `jc_content_tag` (`tag_id`),
  CONSTRAINT `fk_jc_tag_content` FOREIGN KEY (`content_id`) REFERENCES `jc_content` (`content_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='CMS内容标签关联表';

/*Data for the table `jc_contenttag` */

insert  into `jc_contenttag`(`content_id`,`tag_id`,`priority`) values (2,58,0),(2,59,1),(2,58,2),(2,59,3),(2,60,4),(3,61,0),(3,62,1),(3,63,2),(3,64,3),(85,65,0),(85,66,1),(85,67,2),(85,68,3),(85,69,4),(86,70,0),(86,71,1),(86,72,2),(86,73,3),(86,59,4),(86,74,5),(86,75,6),(86,76,7),(87,77,0),(87,78,1),(87,79,2),(87,66,3),(87,67,4),(87,80,5),(88,81,0),(88,82,1),(88,83,2),(88,66,3),(88,67,4),(88,80,5),(89,84,0),(89,85,1),(89,66,2),(89,67,3),(89,80,4),(90,86,0),(90,87,1),(90,88,2),(90,66,3),(90,67,4),(90,80,5),(91,89,0),(91,90,1),(91,91,2),(91,66,3),(91,67,4),(91,80,5),(92,92,0),(92,93,1),(92,76,2),(92,67,3),(92,80,4),(93,94,0),(94,95,0),(94,96,1),(94,97,2),(94,98,3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
