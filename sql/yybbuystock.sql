CREATE TABLE `yybBuyStock` (
  `id` bigint(200) NOT NULL,
  `buyCode` varchar(45) NOT NULL COMMENT '营业部代码',
  `buyName` varchar(45) DEFAULT NULL,
  `stockCode` varchar(45) NOT NULL COMMENT '股票代码',
  `stockName` varchar(45) DEFAULT NULL,
  `buyDate` datetime NOT NULL COMMENT '购买日期',
  `recommedScore` double NOT NULL COMMENT '推荐分',
  `buyCount` int(11) NOT NULL COMMENT '购买股数',
  `recommendDay` datetime NOT NULL,
  `holdDay` int(11) NOT NULL COMMENT '推荐持有天数',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table spider.yybBuyStock modify column id bigint auto_increment;
alter TABLE spider.yybBuyStock AUTO_INCREMENT=100000;
