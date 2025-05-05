-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema BBDD-ProyectoProgra2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema BBDD-ProyectoProgra2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `BBDD-ProyectoProgra2` DEFAULT CHARACTER SET utf8 ;
USE `BBDD-ProyectoProgra2` ;

-- -----------------------------------------------------
-- Table `BBDD-ProyectoProgra2`.`PELICULA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BBDD-ProyectoProgra2`.`PELICULA` (
  `id_pelicula` INT NOT NULL auto_increment,
  `duracion_min` INT NOT NULL,
  `nombre_pelicula` VARCHAR(45) NOT NULL,
  `sinopsis` VARCHAR(200) NOT NULL,
  `Apto_publico` VARCHAR(45) NOT NULL,
  `fecha_estreno` VARCHAR(20) NOT NULL,
  `director` VARCHAR(45) NOT NULL,
  `imagen` VARCHAR(20) NOT NULL,
  `is_cartelera` BOOLEAN NOT NULL,
  PRIMARY KEY (`id_pelicula`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BBDD-ProyectoProgra2`.`SALA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BBDD-ProyectoProgra2`.`SALA` (
  `id_sala` int NOT NULL auto_increment,
  `cantidad_butacas` INT NOT NULL,
  `tipo_sala` ENUM("_2D", "_3D", "D_BOX") NOT NULL,
  PRIMARY KEY (`id_sala`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BBDD-ProyectoProgra2`.`FUNCION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BBDD-ProyectoProgra2`.`FUNCION` (
  `id_funcion` int NOT NULL auto_increment,
  `horario` enum("HS12","HS14","HS16","HS18","HS20") NOT NULL,
  `fecha` varchar(20) not null,
  `id_pelicula` INT NOT NULL,
  `id_sala` int NOT NULL,
  PRIMARY KEY (`id_funcion`),
  INDEX `fk_FUNCION_PELICULA1_idx` (`id_pelicula` ASC) VISIBLE,
  INDEX `fk_FUNCION_SALA1_idx` (`id_sala` ASC) VISIBLE,
  CONSTRAINT `fk_FUNCION_PELICULA1`
    FOREIGN KEY (`id_pelicula`)
    REFERENCES `BBDD-ProyectoProgra2`.`PELICULA` (`id_pelicula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_FUNCION_SALA1`
    FOREIGN KEY (`id_sala`)
    REFERENCES `BBDD-ProyectoProgra2`.`SALA` (`id_sala`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BBDD-ProyectoProgra2`.`USUARIO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BBDD-ProyectoProgra2`.`USUARIO` (
  `id_usuario` INT NOT NULL auto_increment,
  `nombre` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `contrasenia` VARCHAR(45) NOT NULL,
  `credito` INT NULL,
  `rol_usuario` ENUM("admin", "cliente") NOT NULL,
  PRIMARY KEY (`id_usuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BBDD-ProyectoProgra2`.`RESERVA_ENTRADA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BBDD-ProyectoProgra2`.`RESERVA_ENTRADA` (
  `id_reserva_entrada` INT NOT NULL auto_increment,
  `id_funcion` int NOT NULL,
  `costo_reserva` int NOT NULL,
  `fecha_reserva` varchar(20) not null,
  `horario_reserva` varchar(40) not null,
  `id_usuario` INT NOT NULL,
  `cantidad_entradas` INT NOT NULL,
  PRIMARY KEY (`id_reserva_entrada`),
  INDEX `fk_RESERVA_ENTRADA_FUNCION1_idx` (`id_funcion` ASC) VISIBLE,
  INDEX `fk_RESERVA_ENTRADA_USUARIO1_idx` (`id_usuario` ASC) VISIBLE,
  CONSTRAINT `fk_RESERVA_CAPACIDAD_FUNCION1`
    FOREIGN KEY (`id_funcion`)
    REFERENCES `BBDD-ProyectoProgra2`.`FUNCION` (`id_funcion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RESERVA_ENTRADA_USUARIO1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `BBDD-ProyectoProgra2`.`USUARIO` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;




SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
