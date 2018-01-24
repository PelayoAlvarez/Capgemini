DROP DATABASE IF EXISTS Banco;
CREATE DATABASE Banco;

CREATE TABLE Banco.Sucursal
 (Id INTEGER NOT NULL, 
 Nombre VARCHAR(15) NOT NULL,
 Direccion VARCHAR(50) NOT NULL,
 Fec_actu DATETIME NOT NULL,
 Fec_creacion DATETIME NOT NULL,
 Usuario VARCHAR(20) NOT NULL,
 Mca_habilitado BOOLEAN NOT NULL,
 PRIMARY KEY (Id));
 
 CREATE TABLE Banco.Sucursal_H
 (Id INTEGER NOT NULL, 
 Nombre VARCHAR(15) NOT NULL,
 Direccion VARCHAR(50) NOT NULL,
 Fec_actu DATETIME NOT NULL,
 Fec_creacion DATETIME NOT NULL,
 Usuario VARCHAR(20) NOT NULL,
 Mca_habilitado BOOLEAN NOT NULL,
 Fec_audit DATETIME NOT NULL,
 Usuario_h VARCHAR(20) NOT NULL,
 PRIMARY KEY (Id, Fec_audit));
 
 CREATE TABLE Banco.Cliente
 (Dni VARCHAR(9) NOT NULL,
 Nombre VARCHAR(15) NOT NULL,
 Apellidos VARCHAR(30) NOT NULL,
 Direccion VARCHAR(50) NOT NULL,
 Fijo VARCHAR(15) NOT NULL,
 Movil VARCHAR(15) NOT NULL,
 Email VARCHAR(30) NOT NULL,
 Fec_actu DATETIME NOT NULL,
 Fec_creacion DATETIME NOT NULL,
 Usuario VARCHAR(20) NOT NULL,
 Mca_habilitado BOOLEAN NOT NULL,
 PRIMARY KEY (Dni));
 
 CREATE TABLE Banco.Cliente_H
 (Dni VARCHAR(9) NOT NULL,
 Nombre VARCHAR(15) NOT NULL,
 Apellidos VARCHAR(30) NOT NULL,
 Direccion VARCHAR(50) NOT NULL,
 Fijo VARCHAR(15) NOT NULL,
 Movil VARCHAR(15) NOT NULL,
 Email VARCHAR(30) NOT NULL,
 Fec_actu DATETIME NOT NULL,
 Fec_creacion DATETIME NOT NULL,
 Usuario VARCHAR(20) NOT NULL,
 Mca_habilitado BOOLEAN NOT NULL,
 Fec_audit DATETIME NOT NULL,
 Usuario_h VARCHAR(20) NOT NULL,
 PRIMARY KEY (Dni, Fec_audit));
 
 CREATE TABLE Banco.Sucursal_Cliente
 (Id_sucursal INTEGER NOT NULL,
 Dni VARCHAR(9) NOT NULL,
 Fec_actu DATETIME NOT NULL,
 Fec_creacion DATETIME NOT NULL,
 Usuario VARCHAR(20) NOT NULL,
 Mca_habilitado BOOLEAN NOT NULL,
 PRIMARY KEY (Id_sucursal, Dni),
 FOREIGN KEY (Id_sucursal) REFERENCES Banco.Sucursal (Id),
 FOREIGN KEY (Dni) REFERENCES Banco.Cliente (Dni));
 
 CREATE TABLE Banco.Sucursal_Cliente_H
 (Id_sucursal INTEGER NOT NULL,
 Dni VARCHAR(9) NOT NULL,
 Fec_actu DATETIME NOT NULL,
 Fec_creacion DATETIME NOT NULL,
 Usuario VARCHAR(20) NOT NULL,
 Mca_habilitado BOOLEAN NOT NULL,
 Fec_audit DATETIME NOT NULL,
 Usuario_h VARCHAR(20) NOT NULL,
 PRIMARY KEY (Id_sucursal, Dni, Fec_audit),
 FOREIGN KEY (Id_sucursal) REFERENCES Banco.Sucursal (Id),
 FOREIGN KEY (Dni) REFERENCES Banco.Cliente (Dni));
 
 CREATE TABLE Banco.Empleado
 (Dni VARCHAR(9) NOT NULL,
 Nombre VARCHAR(15) NOT NULL,
 Apellidos VARCHAR(30) NOT NULL,
 Direccion VARCHAR(50) NOT NULL,
 Fijo VARCHAR(15) NOT NULL,
 Movil VARCHAR(15) NOT NULL,
 Email VARCHAR(30) NOT NULL,
 Fec_actu DATETIME NOT NULL,
 Fec_creacion DATETIME NOT NULL,
 Usuario VARCHAR(20) NOT NULL,
 Mca_habilitado BOOLEAN NOT NULL,
 PRIMARY KEY (Dni)); 
 
 CREATE TABLE Banco.Empleado_H
 (Dni VARCHAR(9) NOT NULL,
 Nombre VARCHAR(15) NOT NULL,
 Apellidos VARCHAR(30) NOT NULL,
 Direccion VARCHAR(50) NOT NULL,
 Fijo VARCHAR(15) NOT NULL,
 Movil VARCHAR(15) NOT NULL,
 Email VARCHAR(30) NOT NULL,
 Fec_actu DATETIME NOT NULL,
 Fec_creacion DATETIME NOT NULL,
 Usuario VARCHAR(20) NOT NULL,
 Mca_habilitado BOOLEAN NOT NULL,
 Fec_audit DATETIME NOT NULL,
 Usuario_h VARCHAR(20) NOT NULL,
 PRIMARY KEY (Dni, Fec_audit));
 
 CREATE TABLE Banco.Cuenta
 (Numero_cuenta VARCHAR(25) NOT NULL,
 Id_sucursal INTEGER NOT NULL,
 Fec_actu DATETIME NOT NULL,
 Fec_creacion DATETIME NOT NULL,
 Usuario VARCHAR(20) NOT NULL,
 Mca_habilitado BOOLEAN NOT NULL,
 PRIMARY KEY (Numero_cuenta),
 FOREIGN KEY (Id_sucursal) REFERENCES Banco.Sucursal (Id));
 
 CREATE TABLE Banco.Cuenta_H
 (Numero_cuenta VARCHAR(25) NOT NULL,
 Id_sucursal INTEGER NOT NULL,
 Fec_actu DATETIME NOT NULL,
 Fec_creacion DATETIME NOT NULL,
 Usuario VARCHAR(20) NOT NULL,
 Mca_habilitado BOOLEAN NOT NULL,
 Fec_audit DATETIME NOT NULL,
 Usuario_h VARCHAR(20) NOT NULL,
 PRIMARY KEY (Numero_cuenta, Fec_audit),
 FOREIGN KEY (Id_sucursal) REFERENCES Banco.Sucursal (Id));
 
 CREATE TABLE Banco.Cliente_Cuenta
 (Dni VARCHAR(9) NOT NULL,
 Numero_cuenta VARCHAR(25) NOT NULL,
 Fec_actu DATETIME NOT NULL,
 Fec_creacion DATETIME NOT NULL,
 Usuario VARCHAR(20) NOT NULL,
 Mca_habilitado BOOLEAN NOT NULL,
 PRIMARY KEY (Dni, Numero_cuenta),
 FOREIGN KEY (Dni) REFERENCES Banco.Cliente (Dni),
 FOREIGN KEY (Numero_cuenta) REFERENCES Banco.Cuenta (Numero_cuenta));
 
 CREATE TABLE Banco.Cliente_Cuenta_H
 (Dni VARCHAR(9) NOT NULL,
 Numero_cuenta VARCHAR(25) NOT NULL,
 Fec_actu DATETIME NOT NULL,
 Fec_creacion DATETIME NOT NULL,
 Usuario VARCHAR(20) NOT NULL,
 Mca_habilitado BOOLEAN NOT NULL,
 Fec_audit DATETIME NOT NULL,
 Usuario_h VARCHAR(20) NOT NULL,
 PRIMARY KEY (Dni, Numero_cuenta, Fec_audit),
 FOREIGN KEY (Dni) REFERENCES Banco.Cliente (Dni),
 FOREIGN KEY (Numero_cuenta) REFERENCES Banco.Cuenta (Numero_cuenta));
 
 CREATE TABLE Banco.Tarjeta
 (Numero_tarjeta VARCHAR(20) NOT NULL,
 Mes_caducidad INT NOT NULL,
 Anyo_caducidad INT NOT NULL,
 Ccv INTEGER NOT NULL, 
 Dni VARCHAR(9) NOT NULL,
 Numero_cuenta VARCHAR(25) NOT NULL,
 Fec_actu DATETIME NOT NULL,
 Fec_creacion DATETIME NOT NULL,
 Usuario VARCHAR(20) NOT NULL,
 Mca_habilitado BOOLEAN NOT NULL,
 PRIMARY KEY (Numero_tarjeta),
 FOREIGN KEY (Dni, Numero_cuenta) REFERENCES Banco.Cliente_Cuenta (Dni, Numero_cuenta));
 
 CREATE TABLE Banco.Tarjeta_H
 (Numero_tarjeta VARCHAR(20) NOT NULL,
 Mes_caducidad INT NOT NULL,
 Anyo_caducidad INT NOT NULL,
 Ccv INTEGER NOT NULL, 
 Dni VARCHAR(9) NOT NULL,
 Numero_cuenta VARCHAR(25) NOT NULL,
 Fec_actu DATETIME NOT NULL,
 Fec_creacion DATETIME NOT NULL,
 Usuario VARCHAR(20) NOT NULL,
 Mca_habilitado BOOLEAN NOT NULL,
 Fec_audit DATETIME NOT NULL,
 Usuario_h VARCHAR(20) NOT NULL,
 PRIMARY KEY (Numero_tarjeta, Fec_audit),
 FOREIGN KEY (Dni, Numero_cuenta) REFERENCES Banco.Cliente_Cuenta (Dni, Numero_cuenta));
 
 CREATE TABLE Banco.Movimiento
 (Id INTEGER NOT NULL,
 Importe DOUBLE(15,2) NOT NULL,
 Tipo ENUM('Abono', 'Cargo') NOT NULL,
 Fec_movimiento DATETIME NOT NULL,
 Descripcion VARCHAR(60) NOT NULL,
 Numero_cuenta VARCHAR(25) NOT NULL,
 Fec_actu DATETIME NOT NULL,
 Fec_creacion DATETIME NOT NULL,
 Usuario VARCHAR(20) NOT NULL,
 Mca_habilitado BOOLEAN NOT NULL,
 PRIMARY KEY (Id),
 FOREIGN KEY (Numero_cuenta) REFERENCES Banco.Cuenta (Numero_cuenta));
 
 CREATE TABLE Banco.Movimiento_H
 (Id INTEGER NOT NULL,
 Importe DOUBLE(15,2) NOT NULL,
 Tipo ENUM('Abono', 'Cargo') NOT NULL,
 Fec_movimiento DATETIME NOT NULL,
 Descripcion VARCHAR(60) NOT NULL,
 Numero_cuenta VARCHAR(25) NOT NULL,
 Fec_actu DATETIME NOT NULL,
 Fec_creacion DATETIME NOT NULL,
 Usuario VARCHAR(20) NOT NULL,
 Mca_habilitado BOOLEAN NOT NULL,
 Fec_audit DATETIME NOT NULL,
 Usuario_h VARCHAR(20) NOT NULL,
 PRIMARY KEY (Id, Fec_audit),
 FOREIGN KEY (Numero_cuenta) REFERENCES Banco.Cuenta (Numero_cuenta));
 
 CREATE TABLE Banco.Transferencia
 (Id INTEGER NOT NULL,
 Fec_transferencia DATETIME NOT NULL,
 Fec_consolidacion DATETIME NOT NULL,
 Importe DOUBLE(15,2) NOT NULL,
 Canal ENUM('Online', 'Oficina') NOT NULL,
 Numero_cuenta_origen VARCHAR(25) NOT NULL,
 Numero_cuenta_destino VARCHAR(25) NOT NULL,
 Dni_empleado VARCHAR(9),
 Fec_actu DATETIME NOT NULL,
 Fec_creacion DATETIME NOT NULL,
 Usuario VARCHAR(20) NOT NULL,
 Mca_habilitado BOOLEAN NOT NULL,
 PRIMARY KEY (Id),
 FOREIGN KEY (Numero_cuenta_origen) REFERENCES Banco.Cuenta (Numero_cuenta),
 FOREIGN KEY (Numero_cuenta_destino) REFERENCES Banco.Cuenta (Numero_cuenta),
 FOREIGN KEY (Dni_empleado) REFERENCES Banco.Empleado (Dni));
 
CREATE TABLE Banco.Transferencia_H
 (Id INTEGER NOT NULL,
 Fec_transferencia DATETIME NOT NULL,
 Fec_consolidacion DATETIME NOT NULL,
 Importe DOUBLE(15,2) NOT NULL,
 Canal ENUM('Online', 'Oficina') NOT NULL,
 Numero_cuenta_origen VARCHAR(25) NOT NULL,
 Numero_cuenta_destino VARCHAR(25) NOT NULL,
 Dni_empleado VARCHAR(9),
 Fec_actu DATETIME NOT NULL,
 Fec_creacion DATETIME NOT NULL,
 Usuario VARCHAR(20) NOT NULL,
 Mca_habilitado BOOLEAN NOT NULL,
 Fec_audit DATETIME NOT NULL,
 Usuario_h VARCHAR(20) NOT NULL,
 PRIMARY KEY (Id, Fec_audit),
 FOREIGN KEY (Numero_cuenta_origen) REFERENCES Banco.Cuenta (Numero_cuenta),
 FOREIGN KEY (Numero_cuenta_destino) REFERENCES Banco.Cuenta (Numero_cuenta),
 FOREIGN KEY (Dni_empleado) REFERENCES Banco.Empleado (Dni));