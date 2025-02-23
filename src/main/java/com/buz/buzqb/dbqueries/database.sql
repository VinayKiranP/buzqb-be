CREATE TABLE `buzqb`.`business` (`id` INT NOT NULL AUTO_INCREMENT , `username` VARCHAR(55) NOT NULL , `name` VARCHAR(255) NOT NULL DEFAULT '' , `pincode` VARCHAR(10) NOT NULL DEFAULT '' , `status` VARCHAR(55) NOT NULL DEFAULT '' , `created_by` INT NULL , `created_dt_tm` DATE NULL , `updated_by` INT NULL , `updated_dt_tm` DATE NULL , PRIMARY KEY (`id`), UNIQUE `business_username_unique03082024195408` (`username`(55))) ENGINE = InnoDB;

CREATE TABLE `buzqb`.`role` (
`id` INT NOT NULL AUTO_INCREMENT ,
`name` VARCHAR(255) NOT NULL DEFAULT '' ,
`description` VARCHAR(55) NOT NULL ,
`status` VARCHAR(55) NOT NULL DEFAULT '' ,
`created_by` INT NULL , `created_dt_tm` DATE NULL ,
`updated_by` INT NULL , `updated_dt_tm` DATE NULL ,
PRIMARY KEY (`id`),
UNIQUE `business_name_unique16082024105808` (`name`(255))
) ENGINE = InnoDB;


ALTER TABLE `business` ADD `address_line1` VARCHAR(255) NULL DEFAULT NULL AFTER `name`, ADD `address_line2` VARCHAR(255) NULL DEFAULT NULL AFTER `address_line1`, ADD `landmark` VARCHAR(255) NULL DEFAULT NULL AFTER `address_line2`, ADD `city` VARCHAR(124) NULL DEFAULT NULL AFTER `landmark`, ADD `state` INT NULL DEFAULT NULL AFTER `city`, ADD `country` INT NULL DEFAULT NULL AFTER `state`;


