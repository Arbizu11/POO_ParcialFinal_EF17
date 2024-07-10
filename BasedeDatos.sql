CREATE DATABASE dbBancoCentral;
USE dbBancoCentral;

CREATE TABLE tbCliente(
id int primary key auto_increment,
nombre varchar(50),
direccion varchar(50),
telefono varchar(50)
);

INSERT INTO tbCliente(nombre, direccion, telefono)
VALUES
('Fernando', 'Pasaje 5 pte, Antiguo Cuscatlan', '75841355'),
('Dayalin', 'Zaragoza', '78974332'),
('Anderson', 'Casa 30, Puerto La Libertad', '75544778'),
('Jose', 'Pasaje 10, El Encanto', '76431155'),
('Luis', 'Pasaje 2, San Salvador', '73821982'),
('Daniel', 'Casa 15, La Union', '73626683'),
('Edgar', 'San Salvador', '73457812');

SELECT * FROM tbCliente;

CREATE TABLE tbFacilitador(
id int primary key,
nombre varchar(50)
);

INSERT INTO tbFacilitador(id, nombre)
VALUES (1, 'Visa'), (2, 'MasterCard'), (3, 'American Express');

SELECT * FROM tbFacilitador;

CREATE TABLE tbTarjeta(
id_tarjeta int primary key auto_increment,
numero_tarjeta varchar(50),
fechaExp date,
tipo varchar(50),
id_cliente int,
id_facilitador int, 
index(id_cliente),
foreign key(id_cliente) 
references tbCliente(id) on delete cascade,
index(id_facilitador),
foreign key(id_facilitador) 
references tbFacilitador(id) on delete cascade
);

INSERT INTO tbTarjeta(numero_tarjeta, fechaExp, tipo, id_cliente, id_facilitador)
VALUES
('4341542955554085', '2029-09-12', 'Credito', 1, 1),
('5200874433353617', '2028-10-05', 'Debito', 3, 2),
('3777337854464116','2025-12-24', 'Debito', 2, 3 ),
('3427357854498112','2026-03-02', 'Credito', 5, 1 ),
('5677337823464993','2024-11-01', 'Credito', 6, 2 ),
('4277332743467919','2029-02-13', 'Debito', 4, 3 );

SELECT * FROM tbTarjeta;

DROP TABLE tbTarjeta;

CREATE TABLE tbTransaccion(
id int primary key auto_increment,
fecha date,
monto float, 
descripcion varchar(50),
id_tarjeta int,
index(id_tarjeta),
foreign key(id_tarjeta) 
references tbTarjeta(id_tarjeta) on delete cascade 
);

INSERT INTO tbTransaccion(fecha, monto, descripcion, id_tarjeta)
VALUES
('2024-06-07', 35.6, 'Compra en ofertas de Steam', 1),
('2024-05-20', 40, 'Compra pasaje de vuelo', 1),
('2024-04-23', 105.7, 'Airpods pro', 2),
('2024-04-03', 250.5, 'Cartera Chanel', 3),
('2024-02-01', 25, 'Mochila', 4),
('2024-03-20', 300, 'Consola VideoJuegos', 5);




