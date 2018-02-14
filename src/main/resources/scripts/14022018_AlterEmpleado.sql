ALTER TABLE `banco`.`empleado` 
ADD COLUMN `Password` VARCHAR(32) NOT NULL AFTER `Mca_habilitado`;

ALTER TABLE `banco`.`empleado` 
ADD COLUMN `Role` VARCHAR(25) NOT NULL AFTER `Password`;