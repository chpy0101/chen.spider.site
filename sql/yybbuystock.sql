CREATE TABLE `yybbuystock` (
  `id` bigint(200) NOT NULL,
  `buyCode` varchar(45) NOT NULL COMMENT '营业部代码',
  `buyName` varchar(45) DEFAULT NULL,
  `stockCode` varchar(45) NOT NULL COMMENT '股票代码',
  `stockName` varchar(45) DEFAULT NULL,
  `buyDate` datetime NOT NULL COMMENT '购买日期',
  `recommedScore` decimal(15,0) NOT NULL COMMENT '推荐分',
  `buyCount` int(11) NOT NULL COMMENT '购买股数',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8