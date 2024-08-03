CREATE TABLE `buzqb`.`business` (`id` INT NOT NULL AUTO_INCREMENT , `username` VARCHAR(55) NOT NULL , `name` VARCHAR(255) NOT NULL DEFAULT '' , `pincode` VARCHAR(10) NOT NULL DEFAULT '' , `status` VARCHAR(55) NOT NULL DEFAULT '' , `created_by` INT NULL , `created_dt_tm` DATE NULL , `updated_by` INT NULL , `updated_dt_tm` DATE NULL , PRIMARY KEY (`id`), UNIQUE `business_username_unique03082024195408` (`username`(55))) ENGINE = InnoDB;


