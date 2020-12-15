 CREATE TABLE if not exists `hmily_fx1`.`fx_account` (
  `user_id` bigint(20) NOT NULL,
  `ccy_USD` double unsigned NOT NULL,
  `ccy_CNH` double unsigned NOT NULL,
  `freeze_amount` double unsigned DEFAULT NULL,
  `freeze_ccy` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ;
 CREATE TABLE if not exists `hmily_fx2`.`fx_account` (
  `user_id` bigint(20) NOT NULL,
  `ccy_USD` double unsigned NOT NULL,
  `ccy_CNH` double unsigned NOT NULL,
  `freeze_amount` double unsigned DEFAULT NULL,
  `freeze_ccy` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ;

insert into `hmily_fx1`.`fx_account` values(1,10000,10000,0,null);
insert into `hmily_fx1`.`fx_account` values(2,10000,10000,0,null);
