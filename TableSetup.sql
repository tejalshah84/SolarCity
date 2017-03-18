CREATE DATABASE solarcity;
USE solarcity;
DROP TABLE IF EXISTS `prospect`;
CREATE TABLE `prospect` (
  `prospect_id` INT unsigned AUTO_INCREMENT NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `first_name` VARCHAR(50) NOT NULL,
  `age` INT NOT NULL DEFAULT 0,
  `email` VARCHAR(50) DEFAULT NULL,
  `phone_number` VARCHAR(10) DEFAULT NULL,
  `address_street` VARCHAR(50) DEFAULT NULL,
  `address_city` VARCHAR(50) DEFAULT NULL,
  `address_state` VARCHAR(2) DEFAULT NULL,
  `address_zip` MEDIUMINT unsigned DEFAULT 0,
  `installation` TINYINT(1) DEFAULT 0, 
  `reasonInterested` TEXT DEFAULT NULL,
  `prospect_created_dt` DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`prospect_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `solarcell`;
CREATE TABLE `solarcell` (
  `solarcell_type` VARCHAR(50) NOT NULL,
  `solarcell_type_cd` VARCHAR(25) DEFAULT NULL,
  `price_per_watt` INT NOT NULL DEFAULT 0,
  `solarcell_created_dt` DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`solarcell_type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO solarcell (solarcell_type, solarcell_type_cd, price_per_watt) values ("Amorphous Silicon solar cell","a-Si", 4);
INSERT INTO solarcell (solarcell_type, solarcell_type_cd, price_per_watt) values ("Biohybrid solar cell",null,6);
INSERT INTO solarcell (solarcell_type, solarcell_type_cd, price_per_watt) values ("Buried contact solar cell",null,3);
INSERT INTO solarcell (solarcell_type, solarcell_type_cd, price_per_watt) values ("Cadmium telluride solar cell","CdTe",7);
INSERT INTO solarcell (solarcell_type, solarcell_type_cd, price_per_watt) values ("Concentrated PV cell", "CVP/HCVP",8);
INSERT INTO solarcell (solarcell_type, solarcell_type_cd, price_per_watt) values ("Copper indium gallium selenide solar cells","CI(G)S",4);
INSERT INTO solarcell (solarcell_type, solarcell_type_cd, price_per_watt) values ("Crystalline silicon solar cell","c-Si",5);
INSERT INTO solarcell (solarcell_type, solarcell_type_cd, price_per_watt) values ("Dye-sensitized solar cell","DSSC",8);
INSERT INTO solarcell (solarcell_type, solarcell_type_cd, price_per_watt) values ("Gallium arsenide germanium solar cell","GaAs",7);
INSERT INTO solarcell (solarcell_type, solarcell_type_cd, price_per_watt) values ("Hybrid solar cell",null,6);
INSERT INTO solarcell (solarcell_type, solarcell_type_cd, price_per_watt) values ("Luminescent solar concentrator cell","LSC",3);
INSERT INTO solarcell (solarcell_type, solarcell_type_cd, price_per_watt) values ("Micromorph","tandem-cell using a-Si",5);
INSERT INTO solarcell (solarcell_type, solarcell_type_cd, price_per_watt) values ("Monocrystalline solar cell","mono-Si",10);
INSERT INTO solarcell (solarcell_type, solarcell_type_cd, price_per_watt) values ("Multi-junction solar cell","MJ",4);
INSERT INTO solarcell (solarcell_type, solarcell_type_cd, price_per_watt) values ("Nanocrystal solar cell",null,12);
INSERT INTO solarcell (solarcell_type, solarcell_type_cd, price_per_watt) values ("Organic solar cell","OPV",14);
INSERT INTO solarcell (solarcell_type, solarcell_type_cd, price_per_watt) values ("Perovskite solar cell",null,2);
INSERT INTO solarcell (solarcell_type, solarcell_type_cd, price_per_watt) values ("Photoelectrochemical cell","PEC",4);
INSERT INTO solarcell (solarcell_type, solarcell_type_cd, price_per_watt) values ("Plasmonic solar cell",null,6);
INSERT INTO solarcell (solarcell_type, solarcell_type_cd, price_per_watt) values ("Plastic solar cell",null,1);
INSERT INTO solarcell (solarcell_type, solarcell_type_cd, price_per_watt) values ("Polycrystalline solar cell","multi-Si",5);
INSERT INTO solarcell (solarcell_type, solarcell_type_cd, price_per_watt) values ("Polymer solar cell",null,3);
INSERT INTO solarcell (solarcell_type, solarcell_type_cd, price_per_watt) values ("Quantum dot solar cell",null,2);
INSERT INTO solarcell (solarcell_type, solarcell_type_cd, price_per_watt) values ("Solid-state solar cell",null,4);
INSERT INTO solarcell (solarcell_type, solarcell_type_cd, price_per_watt) values ("Thin-film solar cell","TFSC",2);
INSERT INTO solarcell (solarcell_type, solarcell_type_cd, price_per_watt) values ("Wafer solar cell",null,6);